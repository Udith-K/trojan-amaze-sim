package io.ktor.websocket;

import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.core.InputPrimitivesKt;
import io.ktor.utils.io.core.OutputKt;
import io.ktor.websocket.Frame;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FrameCommon.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class FrameCommonKt {
    public static final CloseReason readReason(Frame.Close close) {
        Intrinsics.checkNotNullParameter(close, "<this>");
        if (close.getData().length < 2) {
            return null;
        }
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, null);
        try {
            OutputKt.writeFully$default(bytePacketBuilder, close.getData(), 0, 0, 6, null);
            ByteReadPacket byteReadPacketBuild = bytePacketBuilder.build();
            return new CloseReason(InputPrimitivesKt.readShort(byteReadPacketBuild), Input.readText$default(byteReadPacketBuild, 0, 0, 3, null));
        } catch (Throwable th) {
            bytePacketBuilder.release();
            throw th;
        }
    }
}
