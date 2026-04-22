package kotlinx.coroutines;

import kotlin.Unit;

/* JADX INFO: compiled from: JobSupport.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class ChildHandleNode extends JobCancellingNode implements ChildHandle {
    public final ChildJob childJob;

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public ChildHandleNode(ChildJob childJob) {
        this.childJob = childJob;
    }

    @Override // kotlinx.coroutines.ChildHandle
    public Job getParent() {
        return getJob();
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    public void invoke(Throwable th) {
        this.childJob.parentCancelled(getJob());
    }

    @Override // kotlinx.coroutines.ChildHandle
    public boolean childCancelled(Throwable th) {
        return getJob().childCancelled(th);
    }
}
