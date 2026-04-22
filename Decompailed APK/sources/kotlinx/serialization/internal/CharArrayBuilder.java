package kotlinx.serialization.internal;

import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: PrimitiveArraysSerializers.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class CharArrayBuilder extends PrimitiveArrayBuilder {
    private char[] buffer;
    private int position;

    public CharArrayBuilder(char[] bufferWithData) {
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
        char[] cArr = this.buffer;
        if (cArr.length < i) {
            char[] cArrCopyOf = Arrays.copyOf(cArr, RangesKt.coerceAtLeast(i, cArr.length * 2));
            Intrinsics.checkNotNullExpressionValue(cArrCopyOf, "copyOf(this, newSize)");
            this.buffer = cArrCopyOf;
        }
    }

    public final void append$kotlinx_serialization_core(char c) {
        PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(this, 0, 1, null);
        char[] cArr = this.buffer;
        int position$kotlinx_serialization_core = getPosition$kotlinx_serialization_core();
        this.position = position$kotlinx_serialization_core + 1;
        cArr[position$kotlinx_serialization_core] = c;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public char[] build$kotlinx_serialization_core() {
        char[] cArrCopyOf = Arrays.copyOf(this.buffer, getPosition$kotlinx_serialization_core());
        Intrinsics.checkNotNullExpressionValue(cArrCopyOf, "copyOf(this, newSize)");
        return cArrCopyOf;
    }
}
