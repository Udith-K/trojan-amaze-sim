package kotlinx.serialization.internal;

import java.util.Arrays;
import kotlin.ULongArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: PrimitiveArraysSerializers.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class ULongArrayBuilder extends PrimitiveArrayBuilder {
    private long[] buffer;
    private int position;

    public /* synthetic */ ULongArrayBuilder(long[] jArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(jArr);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public /* bridge */ /* synthetic */ Object build$kotlinx_serialization_core() {
        return ULongArray.m2699boximpl(m2868buildY2RjT0g$kotlinx_serialization_core());
    }

    private ULongArrayBuilder(long[] jArr) {
        this.buffer = jArr;
        this.position = ULongArray.m2706getSizeimpl(jArr);
        ensureCapacity$kotlinx_serialization_core(10);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public int getPosition$kotlinx_serialization_core() {
        return this.position;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public void ensureCapacity$kotlinx_serialization_core(int i) {
        if (ULongArray.m2706getSizeimpl(this.buffer) < i) {
            long[] jArr = this.buffer;
            long[] jArrCopyOf = Arrays.copyOf(jArr, RangesKt.coerceAtLeast(i, ULongArray.m2706getSizeimpl(jArr) * 2));
            Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "copyOf(this, newSize)");
            this.buffer = ULongArray.m2701constructorimpl(jArrCopyOf);
        }
    }

    /* JADX INFO: renamed from: append-VKZWuLQ$kotlinx_serialization_core, reason: not valid java name */
    public final void m2867appendVKZWuLQ$kotlinx_serialization_core(long j) {
        PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(this, 0, 1, null);
        long[] jArr = this.buffer;
        int position$kotlinx_serialization_core = getPosition$kotlinx_serialization_core();
        this.position = position$kotlinx_serialization_core + 1;
        ULongArray.m2710setk8EXiF4(jArr, position$kotlinx_serialization_core, j);
    }

    /* JADX INFO: renamed from: build-Y2RjT0g$kotlinx_serialization_core, reason: not valid java name */
    public long[] m2868buildY2RjT0g$kotlinx_serialization_core() {
        long[] jArrCopyOf = Arrays.copyOf(this.buffer, getPosition$kotlinx_serialization_core());
        Intrinsics.checkNotNullExpressionValue(jArrCopyOf, "copyOf(this, newSize)");
        return ULongArray.m2701constructorimpl(jArrCopyOf);
    }
}
