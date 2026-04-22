package kotlinx.serialization.internal;

import java.util.Arrays;
import kotlin.UByteArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: PrimitiveArraysSerializers.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class UByteArrayBuilder extends PrimitiveArrayBuilder {
    private byte[] buffer;
    private int position;

    public /* synthetic */ UByteArrayBuilder(byte[] bArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(bArr);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public /* bridge */ /* synthetic */ Object build$kotlinx_serialization_core() {
        return UByteArray.m2654boximpl(m2852buildTcUX1vc$kotlinx_serialization_core());
    }

    private UByteArrayBuilder(byte[] bArr) {
        this.buffer = bArr;
        this.position = UByteArray.m2661getSizeimpl(bArr);
        ensureCapacity$kotlinx_serialization_core(10);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public int getPosition$kotlinx_serialization_core() {
        return this.position;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public void ensureCapacity$kotlinx_serialization_core(int i) {
        if (UByteArray.m2661getSizeimpl(this.buffer) < i) {
            byte[] bArr = this.buffer;
            byte[] bArrCopyOf = Arrays.copyOf(bArr, RangesKt.coerceAtLeast(i, UByteArray.m2661getSizeimpl(bArr) * 2));
            Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(this, newSize)");
            this.buffer = UByteArray.m2656constructorimpl(bArrCopyOf);
        }
    }

    /* JADX INFO: renamed from: append-7apg3OU$kotlinx_serialization_core, reason: not valid java name */
    public final void m2851append7apg3OU$kotlinx_serialization_core(byte b) {
        PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(this, 0, 1, null);
        byte[] bArr = this.buffer;
        int position$kotlinx_serialization_core = getPosition$kotlinx_serialization_core();
        this.position = position$kotlinx_serialization_core + 1;
        UByteArray.m2665setVurrAj0(bArr, position$kotlinx_serialization_core, b);
    }

    /* JADX INFO: renamed from: build-TcUX1vc$kotlinx_serialization_core, reason: not valid java name */
    public byte[] m2852buildTcUX1vc$kotlinx_serialization_core() {
        byte[] bArrCopyOf = Arrays.copyOf(this.buffer, getPosition$kotlinx_serialization_core());
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(this, newSize)");
        return UByteArray.m2656constructorimpl(bArrCopyOf);
    }
}
