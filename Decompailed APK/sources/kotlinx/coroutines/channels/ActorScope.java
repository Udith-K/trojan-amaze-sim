package kotlinx.coroutines.channels;

import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Actor.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface ActorScope extends CoroutineScope, ReceiveChannel {
    Channel getChannel();
}
