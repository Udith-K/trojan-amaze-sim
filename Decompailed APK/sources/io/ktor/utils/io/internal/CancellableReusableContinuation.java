package io.ktor.utils.io.internal;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: CancellableReusableContinuation.kt */
/* JADX INFO: loaded from: classes.dex */
public final class CancellableReusableContinuation implements Continuation {
    private static final /* synthetic */ AtomicReferenceFieldUpdater state$FU = AtomicReferenceFieldUpdater.newUpdater(CancellableReusableContinuation.class, Object.class, "state");
    private static final /* synthetic */ AtomicReferenceFieldUpdater jobCancellationHandler$FU = AtomicReferenceFieldUpdater.newUpdater(CancellableReusableContinuation.class, Object.class, "jobCancellationHandler");
    private volatile /* synthetic */ Object state = null;
    private volatile /* synthetic */ Object jobCancellationHandler = null;

    public final void close(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        resumeWith(Result.m2639constructorimpl(value));
        JobRelation jobRelation = (JobRelation) jobCancellationHandler$FU.getAndSet(this, null);
        if (jobRelation != null) {
            jobRelation.dispose();
        }
    }

    public final void close(Throwable cause) {
        Intrinsics.checkNotNullParameter(cause, "cause");
        Result.Companion companion = Result.Companion;
        resumeWith(Result.m2639constructorimpl(ResultKt.createFailure(cause)));
        JobRelation jobRelation = (JobRelation) jobCancellationHandler$FU.getAndSet(this, null);
        if (jobRelation != null) {
            jobRelation.dispose();
        }
    }

    public final Object completeSuspendBlock(Continuation actual) {
        Intrinsics.checkNotNullParameter(actual, "actual");
        while (true) {
            Object obj = this.state;
            if (obj == null) {
                if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(state$FU, this, null, actual)) {
                    parent(actual.getContext());
                    return IntrinsicsKt.getCOROUTINE_SUSPENDED();
                }
            } else if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(state$FU, this, obj, null)) {
                if (obj instanceof Throwable) {
                    throw ((Throwable) obj);
                }
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type T of io.ktor.utils.io.internal.CancellableReusableContinuation");
                return obj;
            }
        }
    }

    private final void parent(CoroutineContext coroutineContext) {
        Object obj;
        JobRelation jobRelation;
        Job job = (Job) coroutineContext.get(Job.Key);
        JobRelation jobRelation2 = (JobRelation) this.jobCancellationHandler;
        if ((jobRelation2 != null ? jobRelation2.getJob() : null) == job) {
            return;
        }
        if (job == null) {
            JobRelation jobRelation3 = (JobRelation) jobCancellationHandler$FU.getAndSet(this, null);
            if (jobRelation3 != null) {
                jobRelation3.dispose();
                return;
            }
            return;
        }
        JobRelation jobRelation4 = new JobRelation(this, job);
        do {
            obj = this.jobCancellationHandler;
            jobRelation = (JobRelation) obj;
            if (jobRelation != null && jobRelation.getJob() == job) {
                jobRelation4.dispose();
                return;
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(jobCancellationHandler$FU, this, obj, jobRelation4));
        if (jobRelation != null) {
            jobRelation.dispose();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notParent(JobRelation jobRelation) {
        AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(jobCancellationHandler$FU, this, jobRelation, null);
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        CoroutineContext context;
        Object obj = this.state;
        Continuation continuation = obj instanceof Continuation ? (Continuation) obj : null;
        return (continuation == null || (context = continuation.getContext()) == null) ? EmptyCoroutineContext.INSTANCE : context;
    }

    /* JADX INFO: compiled from: CancellableReusableContinuation.kt */
    private final class JobRelation implements Function1 {
        private DisposableHandle handler;
        private final Job job;
        final /* synthetic */ CancellableReusableContinuation this$0;

        public JobRelation(CancellableReusableContinuation cancellableReusableContinuation, Job job) {
            Intrinsics.checkNotNullParameter(job, "job");
            this.this$0 = cancellableReusableContinuation;
            this.job = job;
            DisposableHandle disposableHandleInvokeOnCompletion$default = Job.DefaultImpls.invokeOnCompletion$default(job, true, false, this, 2, null);
            if (job.isActive()) {
                this.handler = disposableHandleInvokeOnCompletion$default;
            }
        }

        public final Job getJob() {
            return this.job;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Throwable) obj);
            return Unit.INSTANCE;
        }

        public void invoke(Throwable th) {
            this.this$0.notParent(this);
            dispose();
            if (th != null) {
                this.this$0.resumeWithExceptionContinuationOnly(this.job, th);
            }
        }

        public final void dispose() {
            DisposableHandle disposableHandle = this.handler;
            if (disposableHandle != null) {
                this.handler = null;
                disposableHandle.dispose();
            }
        }
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(Object obj) {
        Object obj2;
        Object objM2642exceptionOrNullimpl;
        do {
            obj2 = this.state;
            if (obj2 == null) {
                objM2642exceptionOrNullimpl = Result.m2642exceptionOrNullimpl(obj);
                if (objM2642exceptionOrNullimpl == null) {
                    ResultKt.throwOnFailure(obj);
                    objM2642exceptionOrNullimpl = obj;
                }
            } else if (!(obj2 instanceof Continuation)) {
                return;
            } else {
                objM2642exceptionOrNullimpl = null;
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(state$FU, this, obj2, objM2642exceptionOrNullimpl));
        if (obj2 instanceof Continuation) {
            ((Continuation) obj2).resumeWith(obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resumeWithExceptionContinuationOnly(Job job, Throwable th) {
        Object obj;
        Continuation continuation;
        do {
            obj = this.state;
            if (!(obj instanceof Continuation)) {
                return;
            }
            continuation = (Continuation) obj;
            if (continuation.getContext().get(Job.Key) != job) {
                return;
            }
        } while (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(state$FU, this, obj, null));
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.coroutines.Continuation<T of io.ktor.utils.io.internal.CancellableReusableContinuation>");
        Result.Companion companion = Result.Companion;
        continuation.resumeWith(Result.m2639constructorimpl(ResultKt.createFailure(th)));
    }
}
