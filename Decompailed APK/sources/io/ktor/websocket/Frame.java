package io.ktor.websocket;

import ch.qos.logback.core.CoreConstants;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.OutputPrimitivesKt;
import io.ktor.utils.io.core.StringsKt;
import java.nio.ByteBuffer;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DisposableHandle;

/* JADX INFO: compiled from: Frame.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class Frame {
    public static final Companion Companion = new Companion(null);
    private static final byte[] Empty = new byte[0];
    private final ByteBuffer buffer;
    private final byte[] data;
    private final DisposableHandle disposableHandle;
    private final boolean fin;
    private final FrameType frameType;
    private final boolean rsv1;
    private final boolean rsv2;
    private final boolean rsv3;

    public /* synthetic */ Frame(boolean z, FrameType frameType, byte[] bArr, DisposableHandle disposableHandle, boolean z2, boolean z3, boolean z4, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, frameType, bArr, disposableHandle, z2, z3, z4);
    }

    /* JADX INFO: compiled from: Frame.kt */
    public static final class Close extends Frame {
        public Close(CloseReason reason) {
            Intrinsics.checkNotNullParameter(reason, "reason");
            BytePacketBuilder bytePacketBuilder = new BytePacketBuilder(null, 1, 0 == true ? 1 : 0);
            try {
                OutputPrimitivesKt.writeShort(bytePacketBuilder, reason.getCode());
                StringsKt.writeText$default(bytePacketBuilder, reason.getMessage(), 0, 0, null, 14, null);
                this(bytePacketBuilder.build());
            } catch (Throwable th) {
                bytePacketBuilder.release();
                throw th;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Close(byte[] data) {
            super(true, FrameType.CLOSE, data, NonDisposableHandle.INSTANCE, false, false, false, null);
            Intrinsics.checkNotNullParameter(data, "data");
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Close(ByteReadPacket packet) {
            this(StringsKt.readBytes$default(packet, 0, 1, null));
            Intrinsics.checkNotNullParameter(packet, "packet");
        }
    }

    private Frame(boolean z, FrameType frameType, byte[] bArr, DisposableHandle disposableHandle, boolean z2, boolean z3, boolean z4) {
        this.fin = z;
        this.frameType = frameType;
        this.data = bArr;
        this.disposableHandle = disposableHandle;
        this.rsv1 = z2;
        this.rsv2 = z3;
        this.rsv3 = z4;
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        Intrinsics.checkNotNullExpressionValue(byteBufferWrap, "wrap(data)");
        this.buffer = byteBufferWrap;
    }

    public final byte[] getData() {
        return this.data;
    }

    /* JADX INFO: compiled from: Frame.kt */
    public static final class Binary extends Frame {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Binary(boolean z, byte[] data, boolean z2, boolean z3, boolean z4) {
            super(z, FrameType.BINARY, data, NonDisposableHandle.INSTANCE, z2, z3, z4, null);
            Intrinsics.checkNotNullParameter(data, "data");
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Binary(boolean z, byte[] data) {
            this(z, data, false, false, false);
            Intrinsics.checkNotNullParameter(data, "data");
        }
    }

    /* JADX INFO: compiled from: Frame.kt */
    public static final class Text extends Frame {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Text(boolean z, byte[] data, boolean z2, boolean z3, boolean z4) {
            super(z, FrameType.TEXT, data, NonDisposableHandle.INSTANCE, z2, z3, z4, null);
            Intrinsics.checkNotNullParameter(data, "data");
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public Text(boolean z, byte[] data) {
            this(z, data, false, false, false);
            Intrinsics.checkNotNullParameter(data, "data");
        }
    }

    public String toString() {
        return "Frame " + this.frameType + " (fin=" + this.fin + ", buffer len = " + this.data.length + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    /* JADX INFO: compiled from: Frame.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
