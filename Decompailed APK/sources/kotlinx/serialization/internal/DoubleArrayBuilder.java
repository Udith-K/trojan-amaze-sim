package kotlinx.serialization.internal;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: PrimitiveArraysSerializers.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class DoubleArrayBuilder extends PrimitiveArrayBuilder {
    private double[] buffer;
    private int position;

    public DoubleArrayBuilder(double[] bufferWithData) {
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
        double[] dArr = this.buffer;
        if (dArr.length < i) {
            double[] dArrCopyOf = Arrays.copyOf(dArr, RangesKt.coerceAtLeast(i, dArr.length * 2));
            Intrinsics.checkNotNullExpressionValue(dArrCopyOf, "copyOf(this, newSize)");
            this.buffer = dArrCopyOf;
        }
    }

    public final void append$kotlinx_serialization_core(double d) {
        PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(this, 0, 1, null);
        double[] dArr = this.buffer;
        int position$kotlinx_serialization_core = getPosition$kotlinx_serialization_core();
        this.position = position$kotlinx_serialization_core + 1;
        dArr[position$kotlinx_serialization_core] = d;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public double[] build$kotlinx_serialization_core() {
        double[] dArrCopyOf = Arrays.copyOf(this.buffer, getPosition$kotlinx_serialization_core());
        Intrinsics.checkNotNullExpressionValue(dArrCopyOf, "copyOf(this, newSize)");
        return dArrCopyOf;
    }
}
