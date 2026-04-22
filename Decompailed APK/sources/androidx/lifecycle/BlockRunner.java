package androidx.lifecycle;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: CoroutineLiveData.kt */
/* JADX INFO: loaded from: classes.dex */
public final class BlockRunner {
    private final Function2 block;
    private Job cancellationJob;
    private final CoroutineLiveData liveData;
    private final Function0 onDone;
    private Job runningJob;
    private final CoroutineScope scope;
    private final long timeoutInMs;

    public BlockRunner(CoroutineLiveData liveData, Function2 block, long j, CoroutineScope scope, Function0 onDone) {
        Intrinsics.checkNotNullParameter(liveData, "liveData");
        Intrinsics.checkNotNullParameter(block, "block");
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(onDone, "onDone");
        this.liveData = liveData;
        this.block = block;
        this.timeoutInMs = j;
        this.scope = scope;
        this.onDone = onDone;
    }

    public final void maybeRun() {
        Job job = this.cancellationJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, null, 1, null);
        }
        this.cancellationJob = null;
        if (this.runningJob != null) {
            return;
        }
        this.runningJob = BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01631(null), 3, null);
    }

    /* JADX INFO: renamed from: androidx.lifecycle.BlockRunner$maybeRun$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CoroutineLiveData.kt */
    static final class C01631 extends SuspendLambda implements Function2 {
        private /* synthetic */ Object L$0;
        int label;

        C01631(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            C01631 c01631 = BlockRunner.this.new C01631(continuation);
            c01631.L$0 = obj;
            return c01631;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C01631) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                LiveDataScopeImpl liveDataScopeImpl = new LiveDataScopeImpl(BlockRunner.this.liveData, ((CoroutineScope) this.L$0).getCoroutineContext());
                Function2 function2 = BlockRunner.this.block;
                this.label = 1;
                if (function2.invoke(liveDataScopeImpl, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            BlockRunner.this.onDone.invoke();
            return Unit.INSTANCE;
        }
    }

    public final void cancel() {
        if (this.cancellationJob == null) {
            this.cancellationJob = BuildersKt__Builders_commonKt.launch$default(this.scope, Dispatchers.getMain().getImmediate(), null, new AnonymousClass1(null), 2, null);
            return;
        }
        throw new IllegalStateException("Cancel call cannot happen without a maybeRun");
    }

    /* JADX INFO: renamed from: androidx.lifecycle.BlockRunner$cancel$1, reason: invalid class name */
    /* JADX INFO: compiled from: CoroutineLiveData.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return BlockRunner.this.new AnonymousClass1(continuation);
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
                long j = BlockRunner.this.timeoutInMs;
                this.label = 1;
                if (DelayKt.delay(j, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            if (!BlockRunner.this.liveData.hasActiveObservers()) {
                Job job = BlockRunner.this.runningJob;
                if (job != null) {
                    Job.DefaultImpls.cancel$default(job, null, 1, null);
                }
                BlockRunner.this.runningJob = null;
            }
            return Unit.INSTANCE;
        }
    }
}
