package androidx.compose.animation.core;

/* JADX INFO: compiled from: VectorizedAnimationSpec.kt */
/* JADX INFO: loaded from: classes.dex */
public interface VectorizedAnimationSpec {
    long getDurationNanos(AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3);

    AnimationVector getEndVelocity(AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3);

    AnimationVector getValueFromNanos(long j, AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3);

    AnimationVector getVelocityFromNanos(long j, AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3);

    boolean isInfinite();

    /* JADX INFO: renamed from: androidx.compose.animation.core.VectorizedAnimationSpec$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: VectorizedAnimationSpec.kt */
    public abstract /* synthetic */ class CC {
    }
}
