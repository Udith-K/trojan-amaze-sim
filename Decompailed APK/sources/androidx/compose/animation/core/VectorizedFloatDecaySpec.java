package androidx.compose.animation.core;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DecayAnimationSpec.kt */
/* JADX INFO: loaded from: classes.dex */
final class VectorizedFloatDecaySpec implements VectorizedDecayAnimationSpec {
    private final float absVelocityThreshold;
    private final FloatDecayAnimationSpec floatDecaySpec;
    private AnimationVector targetVector;
    private AnimationVector valueVector;
    private AnimationVector velocityVector;

    public VectorizedFloatDecaySpec(FloatDecayAnimationSpec floatDecayAnimationSpec) {
        this.floatDecaySpec = floatDecayAnimationSpec;
        this.absVelocityThreshold = floatDecayAnimationSpec.getAbsVelocityThreshold();
    }

    @Override // androidx.compose.animation.core.VectorizedDecayAnimationSpec
    public float getAbsVelocityThreshold() {
        return this.absVelocityThreshold;
    }

    @Override // androidx.compose.animation.core.VectorizedDecayAnimationSpec
    public AnimationVector getValueFromNanos(long j, AnimationVector animationVector, AnimationVector animationVector2) {
        if (this.valueVector == null) {
            this.valueVector = AnimationVectorsKt.newInstance(animationVector);
        }
        AnimationVector animationVector3 = this.valueVector;
        if (animationVector3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("valueVector");
            animationVector3 = null;
        }
        int size$animation_core_release = animationVector3.getSize$animation_core_release();
        for (int i = 0; i < size$animation_core_release; i++) {
            AnimationVector animationVector4 = this.valueVector;
            if (animationVector4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("valueVector");
                animationVector4 = null;
            }
            animationVector4.set$animation_core_release(i, this.floatDecaySpec.getValueFromNanos(j, animationVector.get$animation_core_release(i), animationVector2.get$animation_core_release(i)));
        }
        AnimationVector animationVector5 = this.valueVector;
        if (animationVector5 != null) {
            return animationVector5;
        }
        Intrinsics.throwUninitializedPropertyAccessException("valueVector");
        return null;
    }

    @Override // androidx.compose.animation.core.VectorizedDecayAnimationSpec
    public long getDurationNanos(AnimationVector animationVector, AnimationVector animationVector2) {
        if (this.velocityVector == null) {
            this.velocityVector = AnimationVectorsKt.newInstance(animationVector);
        }
        AnimationVector animationVector3 = this.velocityVector;
        if (animationVector3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("velocityVector");
            animationVector3 = null;
        }
        int size$animation_core_release = animationVector3.getSize$animation_core_release();
        long jMax = 0;
        for (int i = 0; i < size$animation_core_release; i++) {
            jMax = Math.max(jMax, this.floatDecaySpec.getDurationNanos(animationVector.get$animation_core_release(i), animationVector2.get$animation_core_release(i)));
        }
        return jMax;
    }

    @Override // androidx.compose.animation.core.VectorizedDecayAnimationSpec
    public AnimationVector getVelocityFromNanos(long j, AnimationVector animationVector, AnimationVector animationVector2) {
        if (this.velocityVector == null) {
            this.velocityVector = AnimationVectorsKt.newInstance(animationVector);
        }
        AnimationVector animationVector3 = this.velocityVector;
        if (animationVector3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("velocityVector");
            animationVector3 = null;
        }
        int size$animation_core_release = animationVector3.getSize$animation_core_release();
        for (int i = 0; i < size$animation_core_release; i++) {
            AnimationVector animationVector4 = this.velocityVector;
            if (animationVector4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("velocityVector");
                animationVector4 = null;
            }
            animationVector4.set$animation_core_release(i, this.floatDecaySpec.getVelocityFromNanos(j, animationVector.get$animation_core_release(i), animationVector2.get$animation_core_release(i)));
        }
        AnimationVector animationVector5 = this.velocityVector;
        if (animationVector5 != null) {
            return animationVector5;
        }
        Intrinsics.throwUninitializedPropertyAccessException("velocityVector");
        return null;
    }

    @Override // androidx.compose.animation.core.VectorizedDecayAnimationSpec
    public AnimationVector getTargetValue(AnimationVector animationVector, AnimationVector animationVector2) {
        if (this.targetVector == null) {
            this.targetVector = AnimationVectorsKt.newInstance(animationVector);
        }
        AnimationVector animationVector3 = this.targetVector;
        if (animationVector3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("targetVector");
            animationVector3 = null;
        }
        int size$animation_core_release = animationVector3.getSize$animation_core_release();
        for (int i = 0; i < size$animation_core_release; i++) {
            AnimationVector animationVector4 = this.targetVector;
            if (animationVector4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("targetVector");
                animationVector4 = null;
            }
            animationVector4.set$animation_core_release(i, this.floatDecaySpec.getTargetValue(animationVector.get$animation_core_release(i), animationVector2.get$animation_core_release(i)));
        }
        AnimationVector animationVector5 = this.targetVector;
        if (animationVector5 != null) {
            return animationVector5;
        }
        Intrinsics.throwUninitializedPropertyAccessException("targetVector");
        return null;
    }
}
