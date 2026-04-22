package kotlinx.coroutines.scheduling;

/* JADX INFO: compiled from: Tasks.kt */
/* JADX INFO: loaded from: classes2.dex */
final class TaskContextImpl implements TaskContext {
    private final int taskMode;

    @Override // kotlinx.coroutines.scheduling.TaskContext
    public void afterTask() {
    }

    public TaskContextImpl(int i) {
        this.taskMode = i;
    }

    @Override // kotlinx.coroutines.scheduling.TaskContext
    public int getTaskMode() {
        return this.taskMode;
    }
}
