package kotlinx.serialization.internal;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: PrimitiveArraysSerializers.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class ShortArrayBuilder extends PrimitiveArrayBuilder {
    private short[] buffer;
    private int position;

    public ShortArrayBuilder(short[] bufferWithData) {
        Intrinsics.checkNotNullParameter(bufferWithData, "bufferWithData");
        this.buffer = bufferWithData;
        this.position = bufferWithData.length;
        ensureCapacity$kotlinx_serialization_core(10);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public int getPosition$kotlinx_serialization_core() {
        return this.position;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public void ensureCapacity$kotlinx_serialization_core(int i) {
        short[] sArr = this.buffer;
        if (sArr.length < i) {
            short[] sArrCopyOf = Arrays.copyOf(sArr, RangesKt.coerceAtLeast(i, sArr.length * 2));
            Intrinsics.checkNotNullExpressionValue(sArrCopyOf, "copyOf(this, newSize)");
            this.buffer = sArrCopyOf;
        }
    }

    public final void append$kotlinx_serialization_core(short s) {
        PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(this, 0, 1, null);
        short[] sArr = this.buffer;
        int position$kotlinx_serialization_core = getPosition$kotlinx_serialization_core();
        this.position = position$kotlinx_serialization_core + 1;
        sArr[position$kotlinx_serialization_core] = s;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public short[] build$kotlinx_serialization_core() {
        short[] sArrCopyOf = Arrays.copyOf(this.buffer, getPosition$kotlinx_serialization_core());
        Intrinsics.checkNotNullExpressionValue(sArrCopyOf, "copyOf(this, newSize)");
        return sArrCopyOf;
    }
}
