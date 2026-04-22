package kotlinx.coroutines.flow.internal;

import java.util.concurrent.CancellationException;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: FlowExceptions.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class AbortFlowException extends CancellationException {
    public final transient FlowCollector owner;

    public AbortFlowException(FlowCollector flowCollector) {
        super("Flow was aborted, no more elements needed");
        this.owner = flowCollector;
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }
}
