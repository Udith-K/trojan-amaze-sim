package androidx.compose.animation.core;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AnimationSpec.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SpringSpec implements FiniteAnimationSpec {
    private final float dampingRatio;
    private final float stiffness;
    private final Object visibilityThreshold;

    public SpringSpec(float f, float f2, Object obj) {
        this.dampingRatio = f;
        this.stiffness = f2;
        this.visibilityThreshold = obj;
    }

    public /* synthetic */ SpringSpec(float f, float f2, Object obj, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 1.0f : f, (i & 2) != 0 ? 1500.0f : f2, (i & 4) != 0 ? null : obj);
    }

    public final float getDampingRatio() {
        return this.dampingRatio;
    }

    public final float getStiffness() {
        return this.stiffness;
    }

    public final Object getVisibilityThreshold() {
        return this.visibilityThreshold;
    }

    @Override // androidx.compose.animation.core.AnimationSpec
    public VectorizedSpringSpec vectorize(TwoWayConverter twoWayConverter) {
        return new VectorizedSpringSpec(this.dampingRatio, this.stiffness, AnimationSpecKt.convert(twoWayConverter, this.visibilityThreshold));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SpringSpec)) {
            return false;
        }
        SpringSpec springSpec = (SpringSpec) obj;
        return springSpec.dampingRatio == this.dampingRatio && springSpec.stiffness == this.stiffness && Intrinsics.areEqual(springSpec.visibilityThreshold, this.visibilityThreshold);
    }

    public int hashCode() {
        Object obj = this.visibilityThreshold;
        return ((((obj != null ? obj.hashCode() : 0) * 31) + Float.floatToIntBits(this.dampingRatio)) * 31) + Float.floatToIntBits(this.stiffness);
    }
}
