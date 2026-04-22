package kotlinx.coroutines;

import java.util.concurrent.Future;
import kotlin.Unit;

/* JADX INFO: compiled from: Future.kt */
/* JADX INFO: loaded from: classes2.dex */
final class CancelFutureOnCancel extends CancelHandler {
    private final Future future;

    public CancelFutureOnCancel(Future future) {
        this.future = future;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.CancelHandlerBase
    public void invoke(Throwable th) {
        if (th != null) {
            this.future.cancel(false);
        }
    }

    public String toString() {
        return "CancelFutureOnCancel[" + this.future + ']';
    }
}
