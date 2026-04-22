package okhttp3.internal.connection;

import java.lang.ref.Reference;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.platform.Platform;

/* JADX INFO: compiled from: RealConnectionPool.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class RealConnectionPool {
    public static final Companion Companion = new Companion(null);
    private final TaskQueue cleanupQueue;
    private final RealConnectionPool$cleanupTask$1 cleanupTask;
    private final ConcurrentLinkedQueue connections;
    private final long keepAliveDurationNs;
    private final int maxIdleConnections;

    /* JADX WARN: Type inference failed for: r4v3, types: [okhttp3.internal.connection.RealConnectionPool$cleanupTask$1] */
    public RealConnectionPool(TaskRunner taskRunner, int i, long j, TimeUnit timeUnit) {
        Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
        Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
        this.maxIdleConnections = i;
        this.keepAliveDurationNs = timeUnit.toNanos(j);
        this.cleanupQueue = taskRunner.newQueue();
        final String str = Util.okHttpName + " ConnectionPool";
        this.cleanupTask = new Task(str) { // from class: okhttp3.internal.connection.RealConnectionPool$cleanupTask$1
            @Override // okhttp3.internal.concurrent.Task
            public long runOnce() {
                return this.this$0.cleanup(System.nanoTime());
            }
        };
        this.connections = new ConcurrentLinkedQueue();
        if (j > 0) {
            return;
        }
        throw new IllegalArgumentException(("keepAliveDuration <= 0: " + j).toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0033 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean callAcquirePooledConnection(okhttp3.Address r4, okhttp3.internal.connection.RealCall r5, java.util.List r6, boolean r7) {
        /*
            r3 = this;
            java.lang.String r0 = "address"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "call"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.util.concurrent.ConcurrentLinkedQueue r0 = r3.connections
            java.util.Iterator r0 = r0.iterator()
        L10:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L3f
            java.lang.Object r1 = r0.next()
            okhttp3.internal.connection.RealConnection r1 = (okhttp3.internal.connection.RealConnection) r1
            java.lang.String r2 = "connection"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            monitor-enter(r1)
            if (r7 == 0) goto L2d
            boolean r2 = r1.isMultiplexed$okhttp()     // Catch: java.lang.Throwable -> L2b
            if (r2 == 0) goto L39
            goto L2d
        L2b:
            r4 = move-exception
            goto L3d
        L2d:
            boolean r2 = r1.isEligible$okhttp(r4, r6)     // Catch: java.lang.Throwable -> L2b
            if (r2 == 0) goto L39
            r5.acquireConnectionNoEvents(r1)     // Catch: java.lang.Throwable -> L2b
            monitor-exit(r1)
            r4 = 1
            return r4
        L39:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L2b
            monitor-exit(r1)
            goto L10
        L3d:
            monitor-exit(r1)
            throw r4
        L3f:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealConnectionPool.callAcquirePooledConnection(okhttp3.Address, okhttp3.internal.connection.RealCall, java.util.List, boolean):boolean");
    }

    public final void evictAll() {
        Socket socket;
        Iterator it = this.connections.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "connections.iterator()");
        while (it.hasNext()) {
            RealConnection connection = (RealConnection) it.next();
            Intrinsics.checkNotNullExpressionValue(connection, "connection");
            synchronized (connection) {
                if (connection.getCalls().isEmpty()) {
                    it.remove();
                    connection.setNoNewExchanges(true);
                    socket = connection.socket();
                } else {
                    socket = null;
                }
            }
            if (socket != null) {
                Util.closeQuietly(socket);
            }
        }
        if (this.connections.isEmpty()) {
            this.cleanupQueue.cancelAll();
        }
    }

    public final long cleanup(long j) {
        int i = 0;
        long j2 = Long.MIN_VALUE;
        RealConnection realConnection = null;
        int i2 = 0;
        for (RealConnection connection : this.connections) {
            Intrinsics.checkNotNullExpressionValue(connection, "connection");
            synchronized (connection) {
                if (pruneAndGetAllocationCount(connection, j) > 0) {
                    i2++;
                } else {
                    i++;
                    long idleAtNs$okhttp = j - connection.getIdleAtNs$okhttp();
                    if (idleAtNs$okhttp > j2) {
                        realConnection = connection;
                        j2 = idleAtNs$okhttp;
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
        }
        long j3 = this.keepAliveDurationNs;
        if (j2 < j3 && i <= this.maxIdleConnections) {
            if (i > 0) {
                return j3 - j2;
            }
            if (i2 > 0) {
                return j3;
            }
            return -1L;
        }
        Intrinsics.checkNotNull(realConnection);
        synchronized (realConnection) {
            if (!realConnection.getCalls().isEmpty()) {
                return 0L;
            }
            if (realConnection.getIdleAtNs$okhttp() + j2 != j) {
                return 0L;
            }
            realConnection.setNoNewExchanges(true);
            this.connections.remove(realConnection);
            Util.closeQuietly(realConnection.socket());
            if (this.connections.isEmpty()) {
                this.cleanupQueue.cancelAll();
            }
            return 0L;
        }
    }

    /* JADX INFO: compiled from: RealConnectionPool.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    private final int pruneAndGetAllocationCount(RealConnection realConnection, long j) {
        if (!Util.assertionsEnabled || Thread.holdsLock(realConnection)) {
            List calls = realConnection.getCalls();
            int i = 0;
            while (i < calls.size()) {
                Reference reference = (Reference) calls.get(i);
                if (reference.get() != null) {
                    i++;
                } else {
                    Intrinsics.checkNotNull(reference, "null cannot be cast to non-null type okhttp3.internal.connection.RealCall.CallReference");
                    Platform.Companion.get().logCloseableLeak("A connection to " + realConnection.route().address().url() + " was leaked. Did you forget to close a response body?", ((RealCall.CallReference) reference).getCallStackTrace());
                    calls.remove(i);
                    realConnection.setNoNewExchanges(true);
                    if (calls.isEmpty()) {
                        realConnection.setIdleAtNs$okhttp(j - this.keepAliveDurationNs);
                        return 0;
                    }
                }
            }
            return calls.size();
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + realConnection);
    }

    public final boolean connectionBecameIdle(RealConnection connection) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        if (!Util.assertionsEnabled || Thread.holdsLock(connection)) {
            if (connection.getNoNewExchanges() || this.maxIdleConnections == 0) {
                connection.setNoNewExchanges(true);
                this.connections.remove(connection);
                if (this.connections.isEmpty()) {
                    this.cleanupQueue.cancelAll();
                }
                return true;
            }
            TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);
            return false;
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + connection);
    }

    public final void put(RealConnection connection) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        if (!Util.assertionsEnabled || Thread.holdsLock(connection)) {
            this.connections.add(connection);
            TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);
            return;
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + connection);
    }
}
