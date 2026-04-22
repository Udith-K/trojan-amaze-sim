package kotlinx.serialization.internal;

import java.util.Arrays;
import kotlin.UIntArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: PrimitiveArraysSerializers.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class UIntArrayBuilder extends PrimitiveArrayBuilder {
    private int[] buffer;
    private int position;

    public /* synthetic */ UIntArrayBuilder(int[] iArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(iArr);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public /* bridge */ /* synthetic */ Object build$kotlinx_serialization_core() {
        return UIntArray.m2676boximpl(m2860buildhP7Qyg$kotlinx_serialization_core());
    }

    private UIntArrayBuilder(int[] iArr) {
        this.buffer = iArr;
        this.position = UIntArray.m2683getSizeimpl(iArr);
        ensureCapacity$kotlinx_serialization_core(10);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public int getPosition$kotlinx_serialization_core() {
        return this.position;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public void ensureCapacity$kotlinx_serialization_core(int i) {
        if (UIntArray.m2683getSizeimpl(this.buffer) < i) {
            int[] iArr = this.buffer;
            int[] iArrCopyOf = Arrays.copyOf(iArr, RangesKt.coerceAtLeast(i, UIntArray.m2683getSizeimpl(iArr) * 2));
            Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(this, newSize)");
            this.buffer = UIntArray.m2678constructorimpl(iArrCopyOf);
        }
    }

    /* JADX INFO: renamed from: append-WZ4Q5Ns$kotlinx_serialization_core, reason: not valid java name */
    public final void m2859appendWZ4Q5Ns$kotlinx_serialization_core(int i) {
        PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(this, 0, 1, null);
        int[] iArr = this.buffer;
        int position$kotlinx_serialization_core = getPosition$kotlinx_serialization_core();
        this.position = position$kotlinx_serialization_core + 1;
        UIntArray.m2687setVXSXFK8(iArr, position$kotlinx_serialization_core, i);
    }

    /* JADX INFO: renamed from: build--hP7Qyg$kotlinx_serialization_core, reason: not valid java name */
    public int[] m2860buildhP7Qyg$kotlinx_serialization_core() {
        int[] iArrCopyOf = Arrays.copyOf(this.buffer, getPosition$kotlinx_serialization_core());
        Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(this, newSize)");
        return UIntArray.m2678constructorimpl(iArrCopyOf);
    }
}
