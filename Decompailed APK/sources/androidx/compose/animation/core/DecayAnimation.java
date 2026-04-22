package androidx.compose.animation.core;

import androidx.compose.animation.core.Animation;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Animation.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DecayAnimation implements Animation {
    private final VectorizedDecayAnimationSpec animationSpec;
    private final long durationNanos;
    private final AnimationVector endVelocity;
    private final Object initialValue;
    private final AnimationVector initialValueVector;
    private final AnimationVector initialVelocityVector;
    private final boolean isInfinite;
    private final Object targetValue;
    private final TwoWayConverter typeConverter;

    @Override // androidx.compose.animation.core.Animation
    public /* synthetic */ boolean isFinishedFromNanos(long j) {
        return Animation.CC.$default$isFinishedFromNanos(this, j);
    }

    public DecayAnimation(VectorizedDecayAnimationSpec vectorizedDecayAnimationSpec, TwoWayConverter twoWayConverter, Object obj, AnimationVector animationVector) {
        this.animationSpec = vectorizedDecayAnimationSpec;
        this.typeConverter = twoWayConverter;
        this.initialValue = obj;
        AnimationVector animationVector2 = (AnimationVector) getTypeConverter().getConvertToVector().invoke(obj);
        this.initialValueVector = animationVector2;
        this.initialVelocityVector = AnimationVectorsKt.copy(animationVector);
        this.targetValue = getTypeConverter().getConvertFromVector().invoke(vectorizedDecayAnimationSpec.getTargetValue(animationVector2, animationVector));
        this.durationNanos = vectorizedDecayAnimationSpec.getDurationNanos(animationVector2, animationVector);
        AnimationVector animationVectorCopy = AnimationVectorsKt.copy(vectorizedDecayAnimationSpec.getVelocityFromNanos(getDurationNanos(), animationVector2, animationVector));
        this.endVelocity = animationVectorCopy;
        int size$animation_core_release = animationVectorCopy.getSize$animation_core_release();
        for (int i = 0; i < size$animation_core_release; i++) {
            AnimationVector animationVector3 = this.endVelocity;
            animationVector3.set$animation_core_release(i, RangesKt.coerceIn(animationVector3.get$animation_core_release(i), -this.animationSpec.getAbsVelocityThreshold(), this.animationSpec.getAbsVelocityThreshold()));
        }
    }

    @Override // androidx.compose.animation.core.Animation
    public TwoWayConverter getTypeConverter() {
        return this.typeConverter;
    }

    @Override // androidx.compose.animation.core.Animation
    public Object getTargetValue() {
        return this.targetValue;
    }

    @Override // androidx.compose.animation.core.Animation
    public long getDurationNanos() {
        return this.durationNanos;
    }

    @Override // androidx.compose.animation.core.Animation
    public boolean isInfinite() {
        return this.isInfinite;
    }

    public DecayAnimation(DecayAnimationSpec decayAnimationSpec, TwoWayConverter twoWayConverter, Object obj, AnimationVector animationVector) {
        this(decayAnimationSpec.vectorize(twoWayConverter), twoWayConverter, obj, animationVector);
    }

    @Override // androidx.compose.animation.core.Animation
    public Object getValueFromNanos(long j) {
        if (!isFinishedFromNanos(j)) {
            return getTypeConverter().getConvertFromVector().invoke(this.animationSpec.getValueFromNanos(j, this.initialValueVector, this.initialVelocityVector));
        }
        return getTargetValue();
    }

    @Override // androidx.compose.animation.core.Animation
    public AnimationVector getVelocityVectorFromNanos(long j) {
        if (!isFinishedFromNanos(j)) {
            return this.animationSpec.getVelocityFromNanos(j, this.initialValueVector, this.initialVelocityVector);
        }
        return this.endVelocity;
    }
}
