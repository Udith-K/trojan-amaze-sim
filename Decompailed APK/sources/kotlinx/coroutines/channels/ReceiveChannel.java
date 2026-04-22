package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: Channel.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface ReceiveChannel {
    void cancel(CancellationException cancellationException);

    ChannelIterator iterator();

    Object receive(Continuation continuation);

    /* JADX INFO: renamed from: receiveCatching-JP2dKIU */
    Object mo2823receiveCatchingJP2dKIU(Continuation continuation);

    /* JADX INFO: renamed from: tryReceive-PtdJZtk */
    Object mo2824tryReceivePtdJZtk();
}
