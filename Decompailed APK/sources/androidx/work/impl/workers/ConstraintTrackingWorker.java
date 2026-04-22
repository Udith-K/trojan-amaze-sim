package androidx.work.impl.workers;

import android.content.Context;
import androidx.concurrent.futures.ListenableFutureKt;
import androidx.work.CoroutineWorker;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.WorkerParameters;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.model.WorkSpec;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: ConstraintTrackingWorker.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0013B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\tJ&\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0082@¢\u0006\u0002\u0010\u0011J\u000e\u0010\u0012\u001a\u00020\bH\u0082@¢\u0006\u0002\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/work/impl/workers/ConstraintTrackingWorker;", "Landroidx/work/CoroutineWorker;", "appContext", "Landroid/content/Context;", "workerParameters", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "runWorker", "delegate", "Landroidx/work/ListenableWorker;", "workConstraintsTracker", "Landroidx/work/impl/constraints/WorkConstraintsTracker;", "workSpec", "Landroidx/work/impl/model/WorkSpec;", "(Landroidx/work/ListenableWorker;Landroidx/work/impl/constraints/WorkConstraintsTracker;Landroidx/work/impl/model/WorkSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setupAndRunConstraintTrackingWork", "ConstraintUnsatisfiedException", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ConstraintTrackingWorker extends CoroutineWorker {
    private final WorkerParameters workerParameters;

    /* JADX INFO: renamed from: androidx.work.impl.workers.ConstraintTrackingWorker$runWorker$1, reason: invalid class name */
    /* JADX INFO: compiled from: ConstraintTrackingWorker.kt */
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ConstraintTrackingWorker.this.runWorker(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.work.impl.workers.ConstraintTrackingWorker$setupAndRunConstraintTrackingWork$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ConstraintTrackingWorker.kt */
    static final class C01721 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C01721(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ConstraintTrackingWorker.this.setupAndRunConstraintTrackingWork(this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConstraintTrackingWorker(Context appContext, WorkerParameters workerParameters) {
        super(appContext, workerParameters);
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(workerParameters, "workerParameters");
        this.workerParameters = workerParameters;
    }

    /* JADX INFO: renamed from: androidx.work.impl.workers.ConstraintTrackingWorker$doWork$2, reason: invalid class name */
    /* JADX INFO: compiled from: ConstraintTrackingWorker.kt */
    static final class AnonymousClass2 extends SuspendLambda implements Function2 {
        int label;

        AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return ConstraintTrackingWorker.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ConstraintTrackingWorker constraintTrackingWorker = ConstraintTrackingWorker.this;
                this.label = 1;
                obj = constraintTrackingWorker.setupAndRunConstraintTrackingWork(this);
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

    @Override // androidx.work.CoroutineWorker
    public Object doWork(Continuation continuation) {
        Executor backgroundExecutor = getBackgroundExecutor();
        Intrinsics.checkNotNullExpressionValue(backgroundExecutor, "backgroundExecutor");
        return BuildersKt.withContext(ExecutorsKt.from(backgroundExecutor), new AnonymousClass2(null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object setupAndRunConstraintTrackingWork(kotlin.coroutines.Continuation r14) {
        /*
            Method dump skipped, instruction units count: 430
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.workers.ConstraintTrackingWorker.setupAndRunConstraintTrackingWork(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: androidx.work.impl.workers.ConstraintTrackingWorker$setupAndRunConstraintTrackingWork$5, reason: invalid class name */
    /* JADX INFO: compiled from: ConstraintTrackingWorker.kt */
    static final class AnonymousClass5 extends SuspendLambda implements Function2 {
        final /* synthetic */ ListenableWorker $delegate;
        final /* synthetic */ WorkConstraintsTracker $workConstraintsTracker;
        final /* synthetic */ WorkSpec $workSpec;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass5(ListenableWorker listenableWorker, WorkConstraintsTracker workConstraintsTracker, WorkSpec workSpec, Continuation continuation) {
            super(2, continuation);
            this.$delegate = listenableWorker;
            this.$workConstraintsTracker = workConstraintsTracker;
            this.$workSpec = workSpec;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return ConstraintTrackingWorker.this.new AnonymousClass5(this.$delegate, this.$workConstraintsTracker, this.$workSpec, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass5) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ConstraintTrackingWorker constraintTrackingWorker = ConstraintTrackingWorker.this;
                ListenableWorker listenableWorker = this.$delegate;
                WorkConstraintsTracker workConstraintsTracker = this.$workConstraintsTracker;
                WorkSpec workSpec = this.$workSpec;
                this.label = 1;
                obj = constraintTrackingWorker.runWorker(listenableWorker, workConstraintsTracker, workSpec, this);
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

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object runWorker(androidx.work.ListenableWorker r5, androidx.work.impl.constraints.WorkConstraintsTracker r6, androidx.work.impl.model.WorkSpec r7, kotlin.coroutines.Continuation r8) {
        /*
            r4 = this;
            boolean r0 = r8 instanceof androidx.work.impl.workers.ConstraintTrackingWorker.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            androidx.work.impl.workers.ConstraintTrackingWorker$runWorker$1 r0 = (androidx.work.impl.workers.ConstraintTrackingWorker.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.work.impl.workers.ConstraintTrackingWorker$runWorker$1 r0 = new androidx.work.impl.workers.ConstraintTrackingWorker$runWorker$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r8)
            goto L43
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            kotlin.ResultKt.throwOnFailure(r8)
            androidx.work.impl.workers.ConstraintTrackingWorker$runWorker$2 r8 = new androidx.work.impl.workers.ConstraintTrackingWorker$runWorker$2
            r2 = 0
            r8.<init>(r5, r6, r7, r2)
            r0.label = r3
            java.lang.Object r8 = kotlinx.coroutines.CoroutineScopeKt.coroutineScope(r8, r0)
            if (r8 != r1) goto L43
            return r1
        L43:
            java.lang.String r5 = "delegate: ListenableWork….cancel()\n        }\n    }"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r5)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.workers.ConstraintTrackingWorker.runWorker(androidx.work.ListenableWorker, androidx.work.impl.constraints.WorkConstraintsTracker, androidx.work.impl.model.WorkSpec, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: androidx.work.impl.workers.ConstraintTrackingWorker$runWorker$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ConstraintTrackingWorker.kt */
    static final class C01712 extends SuspendLambda implements Function2 {
        final /* synthetic */ ListenableWorker $delegate;
        final /* synthetic */ WorkConstraintsTracker $workConstraintsTracker;
        final /* synthetic */ WorkSpec $workSpec;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01712(ListenableWorker listenableWorker, WorkConstraintsTracker workConstraintsTracker, WorkSpec workSpec, Continuation continuation) {
            super(2, continuation);
            this.$delegate = listenableWorker;
            this.$workConstraintsTracker = workConstraintsTracker;
            this.$workSpec = workSpec;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            C01712 c01712 = new C01712(this.$delegate, this.$workConstraintsTracker, this.$workSpec, continuation);
            c01712.L$0 = obj;
            return c01712;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((C01712) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference failed for: r2v0, types: [int, kotlinx.coroutines.Job] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            AtomicInteger atomicInteger;
            ListenableFuture listenableFuture;
            Object objAwait;
            AtomicInteger atomicInteger2;
            Job job;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ?? r2 = this.label;
            try {
                if (r2 == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                    AtomicInteger atomicInteger3 = new AtomicInteger(-256);
                    ListenableFuture listenableFutureStartWork = this.$delegate.startWork();
                    Intrinsics.checkNotNullExpressionValue(listenableFutureStartWork, "delegate.startWork()");
                    Job jobLaunch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1(this.$workConstraintsTracker, this.$workSpec, atomicInteger3, listenableFutureStartWork, null), 3, null);
                    try {
                        this.L$0 = atomicInteger3;
                        this.L$1 = listenableFutureStartWork;
                        this.L$2 = jobLaunch$default;
                        this.label = 1;
                        objAwait = ListenableFutureKt.await(listenableFutureStartWork, this);
                        if (objAwait == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        atomicInteger2 = atomicInteger3;
                        job = jobLaunch$default;
                        listenableFuture = listenableFutureStartWork;
                        ListenableWorker.Result result = (ListenableWorker.Result) objAwait;
                        Job.DefaultImpls.cancel$default(job, null, 1, null);
                        return result;
                    } catch (CancellationException e) {
                        e = e;
                        atomicInteger = atomicInteger3;
                        listenableFuture = listenableFutureStartWork;
                    } catch (Throwable th) {
                        th = th;
                        String str = ConstraintTrackingWorkerKt.TAG;
                        ListenableWorker listenableWorker = this.$delegate;
                        Logger.get().debug(str, "Delegated worker " + listenableWorker.getClass() + " threw exception in startWork.", th);
                        throw th;
                    }
                } else {
                    if (r2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    job = (Job) this.L$2;
                    listenableFuture = (ListenableFuture) this.L$1;
                    atomicInteger = (AtomicInteger) this.L$0;
                    try {
                        try {
                            ResultKt.throwOnFailure(obj);
                            atomicInteger2 = atomicInteger;
                            objAwait = obj;
                            try {
                                ListenableWorker.Result result2 = (ListenableWorker.Result) objAwait;
                                Job.DefaultImpls.cancel$default(job, null, 1, null);
                                return result2;
                            } catch (CancellationException e2) {
                                e = e2;
                                atomicInteger = atomicInteger2;
                            }
                        } catch (CancellationException e3) {
                            e = e3;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        String str2 = ConstraintTrackingWorkerKt.TAG;
                        ListenableWorker listenableWorker2 = this.$delegate;
                        Logger.get().debug(str2, "Delegated worker " + listenableWorker2.getClass() + " threw exception in startWork.", th);
                        throw th;
                    }
                }
                String str3 = ConstraintTrackingWorkerKt.TAG;
                ListenableWorker listenableWorker3 = this.$delegate;
                Logger.get().debug(str3, "Delegated worker " + listenableWorker3.getClass() + " was cancelled", e);
                boolean z = atomicInteger.get() != -256;
                if (listenableFuture.isCancelled() && z) {
                    throw new ConstraintUnsatisfiedException(atomicInteger.get());
                }
                throw e;
            } catch (Throwable th3) {
                Job.DefaultImpls.cancel$default(r2, null, 1, null);
                throw th3;
            }
        }
    }

    /* JADX INFO: compiled from: ConstraintTrackingWorker.kt */
    private static final class ConstraintUnsatisfiedException extends CancellationException {
        private final int stopReason;

        public ConstraintUnsatisfiedException(int i) {
            this.stopReason = i;
        }

        public final int getStopReason() {
            return this.stopReason;
        }
    }
}
