package androidx.compose.animation.core;

/* JADX INFO: compiled from: Animation.kt */
/* JADX INFO: loaded from: classes.dex */
public interface Animation {
    long getDurationNanos();

    Object getTargetValue();

    TwoWayConverter getTypeConverter();

    Object getValueFromNanos(long j);

    AnimationVector getVelocityVectorFromNanos(long j);

    boolean isFinishedFromNanos(long j);

    boolean isInfinite();

    /* JADX INFO: renamed from: androidx.compose.animation.core.Animation$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Animation.kt */
    public abstract /* synthetic */ class CC {
        public static boolean $default$isFinishedFromNanos(Animation animation, long j) {
            return j >= animation.getDurationNanos();
        }
    }
}
