package io.ktor.utils.io;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ByteWriteChannel.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ByteWriteChannelKt {
    public static final boolean close(ByteWriteChannel byteWriteChannel) {
        Intrinsics.checkNotNullParameter(byteWriteChannel, "<this>");
        return byteWriteChannel.close(null);
    }
}
