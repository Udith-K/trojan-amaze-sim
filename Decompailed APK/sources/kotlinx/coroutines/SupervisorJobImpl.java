package kotlinx.coroutines;

/* JADX INFO: compiled from: Supervisor.kt */
/* JADX INFO: loaded from: classes2.dex */
final class SupervisorJobImpl extends JobImpl {
    @Override // kotlinx.coroutines.JobSupport
    public boolean childCancelled(Throwable th) {
        return false;
    }

    public SupervisorJobImpl(Job job) {
        super(job);
    }
}
