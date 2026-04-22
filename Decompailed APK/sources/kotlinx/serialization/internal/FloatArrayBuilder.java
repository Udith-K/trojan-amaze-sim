package kotlinx.serialization.internal;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: PrimitiveArraysSerializers.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class FloatArrayBuilder extends PrimitiveArrayBuilder {
    private float[] buffer;
    private int position;

    public FloatArrayBuilder(float[] bufferWithData) {
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
        float[] fArr = this.buffer;
        if (fArr.length < i) {
            float[] fArrCopyOf = Arrays.copyOf(fArr, RangesKt.coerceAtLeast(i, fArr.length * 2));
            Intrinsics.checkNotNullExpressionValue(fArrCopyOf, "copyOf(this, newSize)");
            this.buffer = fArrCopyOf;
        }
    }

    public final void append$kotlinx_serialization_core(float f) {
        PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(this, 0, 1, null);
        float[] fArr = this.buffer;
        int position$kotlinx_serialization_core = getPosition$kotlinx_serialization_core();
        this.position = position$kotlinx_serialization_core + 1;
        fArr[position$kotlinx_serialization_core] = f;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public float[] build$kotlinx_serialization_core() {
        float[] fArrCopyOf = Arrays.copyOf(this.buffer, getPosition$kotlinx_serialization_core());
        Intrinsics.checkNotNullExpressionValue(fArrCopyOf, "copyOf(this, newSize)");
        return fArrCopyOf;
    }
}
