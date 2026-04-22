package androidx.compose.animation.core;

/* JADX INFO: compiled from: DecayAnimationSpec.kt */
/* JADX INFO: loaded from: classes.dex */
final class DecayAnimationSpecImpl implements DecayAnimationSpec {
    private final FloatDecayAnimationSpec floatDecaySpec;

    public DecayAnimationSpecImpl(FloatDecayAnimationSpec floatDecayAnimationSpec) {
        this.floatDecaySpec = floatDecayAnimationSpec;
    }

    @Override // androidx.compose.animation.core.DecayAnimationSpec
    public VectorizedDecayAnimationSpec vectorize(TwoWayConverter twoWayConverter) {
        return new VectorizedFloatDecaySpec(this.floatDecaySpec);
    }
}
