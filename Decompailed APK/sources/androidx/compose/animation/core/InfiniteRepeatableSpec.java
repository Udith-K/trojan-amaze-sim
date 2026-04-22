package androidx.compose.animation.core;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AnimationSpec.kt */
/* JADX INFO: loaded from: classes.dex */
public final class InfiniteRepeatableSpec implements AnimationSpec {
    public static final int $stable = 8;
    private final DurationBasedAnimationSpec animation;
    private final long initialStartOffset;
    private final RepeatMode repeatMode;

    public /* synthetic */ InfiniteRepeatableSpec(DurationBasedAnimationSpec durationBasedAnimationSpec, RepeatMode repeatMode, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(durationBasedAnimationSpec, repeatMode, j);
    }

    private InfiniteRepeatableSpec(DurationBasedAnimationSpec durationBasedAnimationSpec, RepeatMode repeatMode, long j) {
        this.animation = durationBasedAnimationSpec;
        this.repeatMode = repeatMode;
        this.initialStartOffset = j;
    }

    @Override // androidx.compose.animation.core.AnimationSpec
    public VectorizedAnimationSpec vectorize(TwoWayConverter twoWayConverter) {
        return new VectorizedInfiniteRepeatableSpec(this.animation.vectorize(twoWayConverter), this.repeatMode, this.initialStartOffset, null);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof InfiniteRepeatableSpec)) {
            return false;
        }
        InfiniteRepeatableSpec infiniteRepeatableSpec = (InfiniteRepeatableSpec) obj;
        return Intrinsics.areEqual(infiniteRepeatableSpec.animation, this.animation) && infiniteRepeatableSpec.repeatMode == this.repeatMode && StartOffset.m63equalsimpl0(infiniteRepeatableSpec.initialStartOffset, this.initialStartOffset);
    }

    public int hashCode() {
        return (((this.animation.hashCode() * 31) + this.repeatMode.hashCode()) * 31) + StartOffset.m64hashCodeimpl(this.initialStartOffset);
    }
}
