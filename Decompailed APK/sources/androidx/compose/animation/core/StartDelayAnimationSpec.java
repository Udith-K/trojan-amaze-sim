package androidx.compose.animation.core;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AnimationSpec.kt */
/* JADX INFO: loaded from: classes.dex */
final class StartDelayAnimationSpec implements AnimationSpec {
    private final AnimationSpec animationSpec;
    private final long startDelayNanos;

    public StartDelayAnimationSpec(AnimationSpec animationSpec, long j) {
        this.animationSpec = animationSpec;
        this.startDelayNanos = j;
    }

    @Override // androidx.compose.animation.core.AnimationSpec
    public VectorizedAnimationSpec vectorize(TwoWayConverter twoWayConverter) {
        return new StartDelayVectorizedAnimationSpec(this.animationSpec.vectorize(twoWayConverter), this.startDelayNanos);
    }

    public int hashCode() {
        return (this.animationSpec.hashCode() * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.startDelayNanos);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof StartDelayAnimationSpec)) {
            return false;
        }
        StartDelayAnimationSpec startDelayAnimationSpec = (StartDelayAnimationSpec) obj;
        return startDelayAnimationSpec.startDelayNanos == this.startDelayNanos && Intrinsics.areEqual(startDelayAnimationSpec.animationSpec, this.animationSpec);
    }
}
