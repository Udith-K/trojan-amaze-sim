package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;

/* JADX INFO: compiled from: SharedFlow.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class SharedFlowSlot extends AbstractSharedFlowSlot {
    public Continuation cont;
    public long index = -1;

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    public boolean allocateLocked(SharedFlowImpl sharedFlowImpl) {
        if (this.index >= 0) {
            return false;
        }
        this.index = sharedFlowImpl.updateNewCollectorIndexLocked$kotlinx_coroutines_core();
        return true;
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    public Continuation[] freeLocked(SharedFlowImpl sharedFlowImpl) {
        long j = this.index;
        this.index = -1L;
        this.cont = null;
        return sharedFlowImpl.updateCollectorIndexLocked$kotlinx_coroutines_core(j);
    }
}
