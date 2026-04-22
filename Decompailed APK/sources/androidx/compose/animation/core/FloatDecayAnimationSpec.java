package androidx.compose.animation.core;

/* JADX INFO: compiled from: FloatDecayAnimationSpec.kt */
/* JADX INFO: loaded from: classes.dex */
public interface FloatDecayAnimationSpec {
    float getAbsVelocityThreshold();

    long getDurationNanos(float f, float f2);

    float getTargetValue(float f, float f2);

    float getValueFromNanos(long j, float f, float f2);

    float getVelocityFromNanos(long j, float f, float f2);
}
