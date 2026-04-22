package androidx.compose.animation.core;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AnimationSpec.kt */
/* JADX INFO: loaded from: classes.dex */
final class StartDelayVectorizedAnimationSpec implements VectorizedAnimationSpec {
    private final long startDelayNanos;
    private final VectorizedAnimationSpec vectorizedAnimationSpec;

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public /* synthetic */ AnimationVector getEndVelocity(AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        return getVelocityFromNanos(getDurationNanos(animationVector, animationVector2, animationVector3), animationVector, animationVector2, animationVector3);
    }

    public StartDelayVectorizedAnimationSpec(VectorizedAnimationSpec vectorizedAnimationSpec, long j) {
        this.vectorizedAnimationSpec = vectorizedAnimationSpec;
        this.startDelayNanos = j;
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public boolean isInfinite() {
        return this.vectorizedAnimationSpec.isInfinite();
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public long getDurationNanos(AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        return this.vectorizedAnimationSpec.getDurationNanos(animationVector, animationVector2, animationVector3) + this.startDelayNanos;
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public AnimationVector getVelocityFromNanos(long j, AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        long j2 = this.startDelayNanos;
        return j < j2 ? animationVector3 : this.vectorizedAnimationSpec.getVelocityFromNanos(j - j2, animationVector, animationVector2, animationVector3);
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public AnimationVector getValueFromNanos(long j, AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        long j2 = this.startDelayNanos;
        return j < j2 ? animationVector : this.vectorizedAnimationSpec.getValueFromNanos(j - j2, animationVector, animationVector2, animationVector3);
    }

    public int hashCode() {
        return (this.vectorizedAnimationSpec.hashCode() * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.startDelayNanos);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof StartDelayVectorizedAnimationSpec)) {
            return false;
        }
        StartDelayVectorizedAnimationSpec startDelayVectorizedAnimationSpec = (StartDelayVectorizedAnimationSpec) obj;
        return startDelayVectorizedAnimationSpec.startDelayNanos == this.startDelayNanos && Intrinsics.areEqual(startDelayVectorizedAnimationSpec.vectorizedAnimationSpec, this.vectorizedAnimationSpec);
    }
}
