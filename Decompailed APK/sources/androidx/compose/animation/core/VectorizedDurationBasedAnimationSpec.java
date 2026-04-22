package androidx.compose.animation.core;

/* JADX INFO: compiled from: VectorizedAnimationSpec.kt */
/* JADX INFO: loaded from: classes.dex */
public interface VectorizedDurationBasedAnimationSpec extends VectorizedFiniteAnimationSpec {
    int getDelayMillis();

    int getDurationMillis();

    /* JADX INFO: renamed from: androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: VectorizedAnimationSpec.kt */
    public abstract /* synthetic */ class CC {
        public static long $default$getDurationNanos(VectorizedDurationBasedAnimationSpec vectorizedDurationBasedAnimationSpec, AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
            return ((long) (vectorizedDurationBasedAnimationSpec.getDelayMillis() + vectorizedDurationBasedAnimationSpec.getDurationMillis())) * 1000000;
        }
    }
}
