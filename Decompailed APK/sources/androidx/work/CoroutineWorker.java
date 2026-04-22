package androidx.work;

import android.content.Context;
import com.google.common.util.concurrent.ListenableFuture;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.JobKt__JobKt;

/* JADX INFO: compiled from: CoroutineWorker.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001:\u0001\u001eB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\r\u001a\u00020\u000eH¦@¢\u0006\u0002\u0010\u000fJ\u000e\u0010\u0010\u001a\u00020\u0011H\u0096@¢\u0006\u0002\u0010\u000fJ\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013J\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u001bH\u0086@¢\u0006\u0002\u0010\u001cJ\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0013R\u001c\u0010\u0007\u001a\u00020\b8\u0016X\u0097\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Landroidx/work/CoroutineWorker;", "Landroidx/work/ListenableWorker;", "appContext", "Landroid/content/Context;", "params", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "coroutineContext", "Lkotlinx/coroutines/CoroutineDispatcher;", "getCoroutineContext$annotations", "()V", "getCoroutineContext", "()Lkotlinx/coroutines/CoroutineDispatcher;", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getForegroundInfo", "Landroidx/work/ForegroundInfo;", "getForegroundInfoAsync", "Lcom/google/common/util/concurrent/ListenableFuture;", "onStopped", "", "setForeground", "foregroundInfo", "(Landroidx/work/ForegroundInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setProgress", "data", "Landroidx/work/Data;", "(Landroidx/work/Data;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startWork", "DeprecatedDispatcher", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class CoroutineWorker extends ListenableWorker {
    private final CoroutineDispatcher coroutineContext;
    private final WorkerParameters params;

    @Deprecated
    public static /* synthetic */ void getCoroutineContext$annotations() {
    }

    public abstract Object doWork(Continuation continuation);

    public Object getForegroundInfo(Continuation continuation) {
        return getForegroundInfo$suspendImpl(this, continuation);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutineWorker(Context appContext, WorkerParameters params) {
        super(appContext, params);
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(params, "params");
        this.params = params;
        this.coroutineContext = DeprecatedDispatcher.INSTANCE;
    }

    public CoroutineDispatcher getCoroutineContext() {
        return this.coroutineContext;
    }

    @Override // androidx.work.ListenableWorker
    public final ListenableFuture startWork() {
        CoroutineContext workerContext;
        if (!Intrinsics.areEqual(getCoroutineContext(), DeprecatedDispatcher.INSTANCE)) {
            workerContext = getCoroutineContext();
        } else {
            workerContext = this.params.getWorkerContext();
        }
        Intrinsics.checkNotNullExpressionValue(workerContext, "if (coroutineContext != …rkerContext\n            }");
        return ListenableFutureKt.launchFuture$default(workerContext.plus(JobKt__JobKt.Job$default(null, 1, null)), null, new C01671(null), 2, null);
    }

    /* JADX INFO: renamed from: androidx.work.CoroutineWorker$startWork$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CoroutineWorker.kt */
    static final class C01671 extends SuspendLambda implements Function2 {
        int label;

        C01671(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return CoroutineWorker.this.new C01671(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C01671) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineWorker coroutineWorker = CoroutineWorker.this;
                this.label = 1;
                obj = coroutineWorker.doWork(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }

    static /* synthetic */ Object getForegroundInfo$suspendImpl(CoroutineWorker coroutineWorker, Continuation continuation) {
        throw new IllegalStateException("Not implemented");
    }

    public final Object setProgress(Data data, Continuation continuation) throws Throwable {
        ListenableFuture progressAsync = setProgressAsync(data);
        Intrinsics.checkNotNullExpressionValue(progressAsync, "setProgressAsync(data)");
        Object objAwait = androidx.concurrent.futures.ListenableFutureKt.await(progressAsync, continuation);
        return objAwait == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwait : Unit.INSTANCE;
    }

    public final Object setForeground(ForegroundInfo foregroundInfo, Continuation continuation) throws Throwable {
        ListenableFuture foregroundAsync = setForegroundAsync(foregroundInfo);
        Intrinsics.checkNotNullExpressionValue(foregroundAsync, "setForegroundAsync(foregroundInfo)");
        Object objAwait = androidx.concurrent.futures.ListenableFutureKt.await(foregroundAsync, continuation);
        return objAwait == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwait : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.work.CoroutineWorker$getForegroundInfoAsync$1, reason: invalid class name */
    /* JADX INFO: compiled from: CoroutineWorker.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return CoroutineWorker.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineWorker coroutineWorker = CoroutineWorker.this;
                this.label = 1;
                obj = coroutineWorker.getForegroundInfo(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }

    @Override // androidx.work.ListenableWorker
    public final ListenableFuture getForegroundInfoAsync() {
        return ListenableFutureKt.launchFuture$default(getCoroutineContext().plus(JobKt__JobKt.Job$default(null, 1, null)), null, new AnonymousClass1(null), 2, null);
    }

    @Override // androidx.work.ListenableWorker
    public final void onStopped() {
        super.onStopped();
    }

    /* JADX INFO: compiled from: CoroutineWorker.kt */
    private static final class DeprecatedDispatcher extends CoroutineDispatcher {
        public static final DeprecatedDispatcher INSTANCE = new DeprecatedDispatcher();
        private static final CoroutineDispatcher dispatcher = Dispatchers.getDefault();

        private DeprecatedDispatcher() {
        }

        @Override // kotlinx.coroutines.CoroutineDispatcher
        public void dispatch(CoroutineContext context, Runnable block) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(block, "block");
            dispatcher.dispatch(context, block);
        }

        @Override // kotlinx.coroutines.CoroutineDispatcher
        public boolean isDispatchNeeded(CoroutineContext context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return dispatcher.isDispatchNeeded(context);
        }
    }
}
