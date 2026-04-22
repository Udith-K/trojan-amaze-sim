package kotlinx.coroutines.flow.internal;

import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: FlowExceptions.common.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class FlowExceptions_commonKt {
    public static final void checkOwnership(AbortFlowException abortFlowException, FlowCollector flowCollector) {
        if (abortFlowException.owner != flowCollector) {
            throw abortFlowException;
        }
    }
}
