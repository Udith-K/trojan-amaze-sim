package androidx.compose.animation.core;

/* JADX INFO: compiled from: DecayAnimationSpec.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class DecayAnimationSpecKt {
    public static final DecayAnimationSpec generateDecayAnimationSpec(FloatDecayAnimationSpec floatDecayAnimationSpec) {
        return new DecayAnimationSpecImpl(floatDecayAnimationSpec);
    }
}
