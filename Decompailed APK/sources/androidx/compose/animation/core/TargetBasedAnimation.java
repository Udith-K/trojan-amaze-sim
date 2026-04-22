package androidx.compose.animation.core;

import androidx.compose.animation.core.Animation;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Animation.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TargetBasedAnimation implements Animation {
    private long _durationNanos;
    private AnimationVector _endVelocity;
    private final VectorizedAnimationSpec animationSpec;
    private AnimationVector initialValueVector;
    private final AnimationVector initialVelocityVector;
    private Object mutableInitialValue;
    private Object mutableTargetValue;
    private AnimationVector targetValueVector;
    private final TwoWayConverter typeConverter;

    @Override // androidx.compose.animation.core.Animation
    public /* synthetic */ boolean isFinishedFromNanos(long j) {
        return Animation.CC.$default$isFinishedFromNanos(this, j);
    }

    public TargetBasedAnimation(VectorizedAnimationSpec vectorizedAnimationSpec, TwoWayConverter twoWayConverter, Object obj, Object obj2, AnimationVector animationVector) {
        AnimationVector animationVectorCopy;
        this.animationSpec = vectorizedAnimationSpec;
        this.typeConverter = twoWayConverter;
        this.mutableTargetValue = obj2;
        this.mutableInitialValue = obj;
        this.initialValueVector = (AnimationVector) getTypeConverter().getConvertToVector().invoke(obj);
        this.targetValueVector = (AnimationVector) getTypeConverter().getConvertToVector().invoke(obj2);
        this.initialVelocityVector = (animationVector == null || (animationVectorCopy = AnimationVectorsKt.copy(animationVector)) == null) ? AnimationVectorsKt.newInstance((AnimationVector) getTypeConverter().getConvertToVector().invoke(obj)) : animationVectorCopy;
        this._durationNanos = -1L;
    }

    @Override // androidx.compose.animation.core.Animation
    public TwoWayConverter getTypeConverter() {
        return this.typeConverter;
    }

    public final Object getInitialValue() {
        return this.mutableInitialValue;
    }

    @Override // androidx.compose.animation.core.Animation
    public Object getTargetValue() {
        return this.mutableTargetValue;
    }

    public /* synthetic */ TargetBasedAnimation(AnimationSpec animationSpec, TwoWayConverter twoWayConverter, Object obj, Object obj2, AnimationVector animationVector, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(animationSpec, twoWayConverter, obj, obj2, (i & 16) != 0 ? null : animationVector);
    }

    public TargetBasedAnimation(AnimationSpec animationSpec, TwoWayConverter twoWayConverter, Object obj, Object obj2, AnimationVector animationVector) {
        this(animationSpec.vectorize(twoWayConverter), twoWayConverter, obj, obj2, animationVector);
    }

    @Override // androidx.compose.animation.core.Animation
    public boolean isInfinite() {
        return this.animationSpec.isInfinite();
    }

    @Override // androidx.compose.animation.core.Animation
    public Object getValueFromNanos(long j) {
        if (!isFinishedFromNanos(j)) {
            AnimationVector valueFromNanos = this.animationSpec.getValueFromNanos(j, this.initialValueVector, this.targetValueVector, this.initialVelocityVector);
            int size$animation_core_release = valueFromNanos.getSize$animation_core_release();
            for (int i = 0; i < size$animation_core_release; i++) {
                if (Float.isNaN(valueFromNanos.get$animation_core_release(i))) {
                    PreconditionsKt.throwIllegalStateException("AnimationVector cannot contain a NaN. " + valueFromNanos + ". Animation: " + this + ", playTimeNanos: " + j);
                }
            }
            return getTypeConverter().getConvertFromVector().invoke(valueFromNanos);
        }
        return getTargetValue();
    }

    @Override // androidx.compose.animation.core.Animation
    public long getDurationNanos() {
        if (this._durationNanos < 0) {
            this._durationNanos = this.animationSpec.getDurationNanos(this.initialValueVector, this.targetValueVector, this.initialVelocityVector);
        }
        return this._durationNanos;
    }

    private final AnimationVector getEndVelocity() {
        AnimationVector animationVector = this._endVelocity;
        if (animationVector != null) {
            return animationVector;
        }
        AnimationVector endVelocity = this.animationSpec.getEndVelocity(this.initialValueVector, this.targetValueVector, this.initialVelocityVector);
        this._endVelocity = endVelocity;
        return endVelocity;
    }

    @Override // androidx.compose.animation.core.Animation
    public AnimationVector getVelocityVectorFromNanos(long j) {
        if (!isFinishedFromNanos(j)) {
            return this.animationSpec.getVelocityFromNanos(j, this.initialValueVector, this.targetValueVector, this.initialVelocityVector);
        }
        return getEndVelocity();
    }

    public String toString() {
        return "TargetBasedAnimation: " + getInitialValue() + " -> " + getTargetValue() + ",initial velocity: " + this.initialVelocityVector + ", duration: " + AnimationKt.getDurationMillis(this) + " ms,animationSpec: " + this.animationSpec;
    }
}
