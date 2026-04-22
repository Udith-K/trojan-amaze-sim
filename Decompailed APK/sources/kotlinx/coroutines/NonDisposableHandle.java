package kotlinx.coroutines;

/* JADX INFO: compiled from: Job.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class NonDisposableHandle implements DisposableHandle, ChildHandle {
    public static final NonDisposableHandle INSTANCE = new NonDisposableHandle();

    @Override // kotlinx.coroutines.ChildHandle
    public boolean childCancelled(Throwable th) {
        return false;
    }

    @Override // kotlinx.coroutines.DisposableHandle
    public void dispose() {
    }

    @Override // kotlinx.coroutines.ChildHandle
    public Job getParent() {
        return null;
    }

    private NonDisposableHandle() {
    }

    public String toString() {
        return "NonDisposableHandle";
    }
}
