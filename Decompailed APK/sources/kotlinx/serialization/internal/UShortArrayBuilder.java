package kotlinx.serialization.internal;

import java.util.Arrays;
import kotlin.UShortArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: PrimitiveArraysSerializers.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class UShortArrayBuilder extends PrimitiveArrayBuilder {
    private short[] buffer;
    private int position;

    public /* synthetic */ UShortArrayBuilder(short[] sArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(sArr);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public /* bridge */ /* synthetic */ Object build$kotlinx_serialization_core() {
        return UShortArray.m2721boximpl(m2876buildamswpOA$kotlinx_serialization_core());
    }

    private UShortArrayBuilder(short[] sArr) {
        this.buffer = sArr;
        this.position = UShortArray.m2728getSizeimpl(sArr);
        ensureCapacity$kotlinx_serialization_core(10);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public int getPosition$kotlinx_serialization_core() {
        return this.position;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public void ensureCapacity$kotlinx_serialization_core(int i) {
        if (UShortArray.m2728getSizeimpl(this.buffer) < i) {
            short[] sArr = this.buffer;
            short[] sArrCopyOf = Arrays.copyOf(sArr, RangesKt.coerceAtLeast(i, UShortArray.m2728getSizeimpl(sArr) * 2));
            Intrinsics.checkNotNullExpressionValue(sArrCopyOf, "copyOf(this, newSize)");
            this.buffer = UShortArray.m2723constructorimpl(sArrCopyOf);
        }
    }

    /* JADX INFO: renamed from: append-xj2QHRw$kotlinx_serialization_core, reason: not valid java name */
    public final void m2875appendxj2QHRw$kotlinx_serialization_core(short s) {
        PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(this, 0, 1, null);
        short[] sArr = this.buffer;
        int position$kotlinx_serialization_core = getPosition$kotlinx_serialization_core();
        this.position = position$kotlinx_serialization_core + 1;
        UShortArray.m2732set01HTLdE(sArr, position$kotlinx_serialization_core, s);
    }

    /* JADX INFO: renamed from: build-amswpOA$kotlinx_serialization_core, reason: not valid java name */
    public short[] m2876buildamswpOA$kotlinx_serialization_core() {
        short[] sArrCopyOf = Arrays.copyOf(this.buffer, getPosition$kotlinx_serialization_core());
        Intrinsics.checkNotNullExpressionValue(sArrCopyOf, "copyOf(this, newSize)");
        return UShortArray.m2723constructorimpl(sArrCopyOf);
    }
}
