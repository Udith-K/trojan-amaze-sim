package org.fdroid.download;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: HttpManager.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\bæ\u0080\u0001\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H¦@¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lorg/fdroid/download/BytesReceiver;", "", "receive", "", "bytes", "", "numTotalBytes", "", "([BLjava/lang/Long;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "download_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface BytesReceiver {
    Object receive(byte[] bArr, Long l, Continuation continuation);
}
