package io.ktor.utils.io;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ByteChannelCtor.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ByteChannelCtorKt {
    public static final ByteReadChannel ByteReadChannel(byte[] content) {
        Intrinsics.checkNotNullParameter(content, "content");
        return ByteChannelKt.ByteReadChannel(content, 0, content.length);
    }
}
