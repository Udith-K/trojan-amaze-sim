package io.ktor.utils.io.jvm.javaio;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.EventLoopKt;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: Blocking.kt */
/* JADX INFO: loaded from: classes.dex */
abstract class BlockingAdapter {
    static final /* synthetic */ AtomicReferenceFieldUpdater state$FU = AtomicReferenceFieldUpdater.newUpdater(BlockingAdapter.class, Object.class, "state");
    private final DisposableHandle disposable;
    private final Continuation end;
    private int length;
    private int offset;
    private final Job parent;
    volatile /* synthetic */ int result;
    volatile /* synthetic */ Object state;

    protected abstract Object loop(Continuation continuation);

    public BlockingAdapter(Job job) {
        this.parent = job;
        Continuation continuation = new Continuation() { // from class: io.ktor.utils.io.jvm.javaio.BlockingAdapter$end$1
            private final CoroutineContext context;

            {
                this.context = this.this$0.getParent() != null ? UnsafeBlockingTrampoline.INSTANCE.plus(this.this$0.getParent()) : UnsafeBlockingTrampoline.INSTANCE;
            }

            @Override // kotlin.coroutines.Continuation
            public CoroutineContext getContext() {
                return this.context;
            }

            @Override // kotlin.coroutines.Continuation
            public void resumeWith(Object obj) {
                Object obj2;
                boolean z;
                Throwable thM2642exceptionOrNullimpl;
                Job parent;
                Object objM2642exceptionOrNullimpl = Result.m2642exceptionOrNullimpl(obj);
                if (objM2642exceptionOrNullimpl == null) {
                    objM2642exceptionOrNullimpl = Unit.INSTANCE;
                }
                BlockingAdapter blockingAdapter = this.this$0;
                do {
                    obj2 = blockingAdapter.state;
                    z = obj2 instanceof Thread;
                    if (!(z ? true : obj2 instanceof Continuation ? true : Intrinsics.areEqual(obj2, this))) {
                        return;
                    }
                } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(BlockingAdapter.state$FU, blockingAdapter, obj2, objM2642exceptionOrNullimpl));
                if (z) {
                    PollersKt.getParkingImpl().unpark(obj2);
                } else if ((obj2 instanceof Continuation) && (thM2642exceptionOrNullimpl = Result.m2642exceptionOrNullimpl(obj)) != null) {
                    ((Continuation) obj2).resumeWith(Result.m2639constructorimpl(ResultKt.createFailure(thM2642exceptionOrNullimpl)));
                }
                if (Result.m2644isFailureimpl(obj) && !(Result.m2642exceptionOrNullimpl(obj) instanceof CancellationException) && (parent = this.this$0.getParent()) != null) {
                    Job.DefaultImpls.cancel$default(parent, null, 1, null);
                }
                DisposableHandle disposableHandle = this.this$0.disposable;
                if (disposableHandle != null) {
                    disposableHandle.dispose();
                }
            }
        };
        this.end = continuation;
        this.state = this;
        this.result = 0;
        this.disposable = job != null ? job.invokeOnCompletion(new Function1() { // from class: io.ktor.utils.io.jvm.javaio.BlockingAdapter$disposable$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Throwable) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable th) {
                if (th != null) {
                    Continuation continuation2 = this.this$0.end;
                    Result.Companion companion = Result.Companion;
                    continuation2.resumeWith(Result.m2639constructorimpl(ResultKt.createFailure(th)));
                }
            }
        }) : null;
        ((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(new BlockingAdapter$block$1(this, null), 1)).invoke(continuation);
        if (this.state == this) {
            throw new IllegalArgumentException("Failed requirement.");
        }
    }

    public final Job getParent() {
        return this.parent;
    }

    protected final int getOffset() {
        return this.offset;
    }

    protected final int getLength() {
        return this.length;
    }

    public final void shutdown() {
        DisposableHandle disposableHandle = this.disposable;
        if (disposableHandle != null) {
            disposableHandle.dispose();
        }
        Continuation continuation = this.end;
        Result.Companion companion = Result.Companion;
        continuation.resumeWith(Result.m2639constructorimpl(ResultKt.createFailure(new CancellationException("Stream closed"))));
    }

    public final int submitAndAwait(byte[] buffer, int i, int i2) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        this.offset = i;
        this.length = i2;
        return submitAndAwait(buffer);
    }

    public final int submitAndAwait(Object jobToken) throws Throwable {
        Object obj;
        Object noWhenBranchMatchedException;
        Intrinsics.checkNotNullParameter(jobToken, "jobToken");
        Thread thread = Thread.currentThread();
        Continuation continuation = null;
        do {
            obj = this.state;
            if (obj instanceof Continuation) {
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any>");
                continuation = (Continuation) obj;
                noWhenBranchMatchedException = thread;
            } else {
                if (obj instanceof Unit) {
                    return this.result;
                }
                if (obj instanceof Throwable) {
                    throw ((Throwable) obj);
                }
                if (obj instanceof Thread) {
                    throw new IllegalStateException("There is already thread owning adapter");
                }
                if (Intrinsics.areEqual(obj, this)) {
                    throw new IllegalStateException("Not yet started");
                }
                noWhenBranchMatchedException = new NoWhenBranchMatchedException();
            }
            Intrinsics.checkNotNullExpressionValue(noWhenBranchMatchedException, "when (value) {\n         …Exception()\n            }");
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(state$FU, this, obj, noWhenBranchMatchedException));
        Intrinsics.checkNotNull(continuation);
        continuation.resumeWith(Result.m2639constructorimpl(jobToken));
        Intrinsics.checkNotNullExpressionValue(thread, "thread");
        parkingLoop(thread);
        Object obj2 = this.state;
        if (obj2 instanceof Throwable) {
            throw ((Throwable) obj2);
        }
        return this.result;
    }

    private final void parkingLoop(Thread thread) {
        if (this.state != thread) {
            return;
        }
        if (!PollersKt.isParkingAllowed()) {
            BlockingKt.getADAPTER_LOGGER().warn("Blocking network thread detected. \nIt can possible lead to a performance decline or even a deadlock.\nPlease make sure you're using blocking IO primitives like InputStream and OutputStream only in \nthe context of Dispatchers.IO:\n```\nwithContext(Dispatchers.IO) {\n    myInputStream.read()\n}\n```");
        }
        while (true) {
            long jProcessNextEventInCurrentThread = EventLoopKt.processNextEventInCurrentThread();
            if (this.state != thread) {
                return;
            }
            if (jProcessNextEventInCurrentThread > 0) {
                PollersKt.getParkingImpl().park(jProcessNextEventInCurrentThread);
            }
        }
    }

    protected final void finish(int i) {
        this.result = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object rendezvousBlock(Continuation continuation) {
        Object obj;
        Continuation continuationIntercepted;
        Object obj2 = null;
        while (true) {
            Object obj3 = this.state;
            if (obj3 instanceof Thread) {
                continuationIntercepted = IntrinsicsKt.intercepted(continuation);
                obj = obj3;
            } else {
                if (!Intrinsics.areEqual(obj3, this)) {
                    throw new IllegalStateException("Already suspended or in finished state");
                }
                obj = obj2;
                continuationIntercepted = IntrinsicsKt.intercepted(continuation);
            }
            if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(state$FU, this, obj3, continuationIntercepted)) {
                if (obj != null) {
                    PollersKt.getParkingImpl().unpark(obj);
                }
                return IntrinsicsKt.getCOROUTINE_SUSPENDED();
            }
            obj2 = obj;
        }
    }
}
