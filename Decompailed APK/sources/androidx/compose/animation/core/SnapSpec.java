package androidx.compose.animation.core;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: AnimationSpec.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SnapSpec implements DurationBasedAnimationSpec {
    private final int delay;

    public SnapSpec(int i) {
        this.delay = i;
    }

    public /* synthetic */ SnapSpec(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i);
    }

    @Override // androidx.compose.animation.core.AnimationSpec
    public VectorizedDurationBasedAnimationSpec vectorize(TwoWayConverter twoWayConverter) {
        return new VectorizedSnapSpec(this.delay);
    }

    public boolean equals(Object obj) {
        return (obj instanceof SnapSpec) && ((SnapSpec) obj).delay == this.delay;
    }

    public int hashCode() {
        return this.delay;
    }
}
