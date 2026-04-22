package io.ktor.utils.io;

import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ByteReadChannel.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ByteReadChannelKt {
    public static final boolean cancel(ByteReadChannel byteReadChannel) {
        Intrinsics.checkNotNullParameter(byteReadChannel, "<this>");
        return byteReadChannel.cancel(null);
    }

    public static final Object readAvailable(ByteReadChannel byteReadChannel, byte[] bArr, Continuation continuation) {
        return byteReadChannel.readAvailable(bArr, 0, bArr.length, continuation);
    }
}
