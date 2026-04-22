package okhttp3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealCall;

/* JADX INFO: compiled from: Dispatcher.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class Dispatcher {
    private ExecutorService executorServiceOrNull;
    private Runnable idleCallback;
    private int maxRequests = 64;
    private int maxRequestsPerHost = 5;
    private final ArrayDeque readyAsyncCalls = new ArrayDeque();
    private final ArrayDeque runningAsyncCalls = new ArrayDeque();
    private final ArrayDeque runningSyncCalls = new ArrayDeque();

    public final synchronized ExecutorService executorService() {
        ExecutorService executorService;
        try {
            if (this.executorServiceOrNull == null) {
                this.executorServiceOrNull = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory(Util.okHttpName + " Dispatcher", false));
            }
            executorService = this.executorServiceOrNull;
            Intrinsics.checkNotNull(executorService);
        } catch (Throwable th) {
            throw th;
        }
        return executorService;
    }

    public final void enqueue$okhttp(RealCall.AsyncCall call) {
        RealCall.AsyncCall asyncCallFindExistingCallWithHost;
        Intrinsics.checkNotNullParameter(call, "call");
        synchronized (this) {
            try {
                this.readyAsyncCalls.add(call);
                if (!call.getCall().getForWebSocket() && (asyncCallFindExistingCallWithHost = findExistingCallWithHost(call.getHost())) != null) {
                    call.reuseCallsPerHostFrom(asyncCallFindExistingCallWithHost);
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
        promoteAndExecute();
    }

    private final RealCall.AsyncCall findExistingCallWithHost(String str) {
        for (RealCall.AsyncCall asyncCall : this.runningAsyncCalls) {
            if (Intrinsics.areEqual(asyncCall.getHost(), str)) {
                return asyncCall;
            }
        }
        for (RealCall.AsyncCall asyncCall2 : this.readyAsyncCalls) {
            if (Intrinsics.areEqual(asyncCall2.getHost(), str)) {
                return asyncCall2;
            }
        }
        return null;
    }

    public final void finished$okhttp(RealCall.AsyncCall call) {
        Intrinsics.checkNotNullParameter(call, "call");
        call.getCallsPerHost().decrementAndGet();
        finished(this.runningAsyncCalls, call);
    }

    private final void finished(Deque deque, Object obj) {
        Runnable runnable;
        synchronized (this) {
            if (!deque.remove(obj)) {
                throw new AssertionError("Call wasn't in-flight!");
            }
            runnable = this.idleCallback;
            Unit unit = Unit.INSTANCE;
        }
        if (promoteAndExecute() || runnable == null) {
            return;
        }
        runnable.run();
    }

    public final synchronized int runningCallsCount() {
        return this.runningAsyncCalls.size() + this.runningSyncCalls.size();
    }

    private final boolean promoteAndExecute() {
        int i;
        boolean z;
        if (!Util.assertionsEnabled || !Thread.holdsLock(this)) {
            ArrayList arrayList = new ArrayList();
            synchronized (this) {
                try {
                    Iterator it = this.readyAsyncCalls.iterator();
                    Intrinsics.checkNotNullExpressionValue(it, "readyAsyncCalls.iterator()");
                    while (it.hasNext()) {
                        RealCall.AsyncCall asyncCall = (RealCall.AsyncCall) it.next();
                        if (this.runningAsyncCalls.size() >= this.maxRequests) {
                            break;
                        }
                        if (asyncCall.getCallsPerHost().get() < this.maxRequestsPerHost) {
                            it.remove();
                            asyncCall.getCallsPerHost().incrementAndGet();
                            Intrinsics.checkNotNullExpressionValue(asyncCall, "asyncCall");
                            arrayList.add(asyncCall);
                            this.runningAsyncCalls.add(asyncCall);
                        }
                    }
                    z = runningCallsCount() > 0;
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    throw th;
                }
            }
            int size = arrayList.size();
            for (i = 0; i < size; i++) {
                ((RealCall.AsyncCall) arrayList.get(i)).executeOn(executorService());
            }
            return z;
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
    }
}
