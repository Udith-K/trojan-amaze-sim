package androidx.compose.animation.core;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VectorizedAnimationSpec.kt */
/* JADX INFO: loaded from: classes.dex */
public final class VectorizedKeyframeSpecElementInfo {
    private final int arcMode;
    private final Easing easing;
    private final AnimationVector vectorValue;

    public /* synthetic */ VectorizedKeyframeSpecElementInfo(AnimationVector animationVector, Easing easing, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(animationVector, easing, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VectorizedKeyframeSpecElementInfo)) {
            return false;
        }
        VectorizedKeyframeSpecElementInfo vectorizedKeyframeSpecElementInfo = (VectorizedKeyframeSpecElementInfo) obj;
        return Intrinsics.areEqual(this.vectorValue, vectorizedKeyframeSpecElementInfo.vectorValue) && Intrinsics.areEqual(this.easing, vectorizedKeyframeSpecElementInfo.easing) && ArcMode.m49equalsimpl0(this.arcMode, vectorizedKeyframeSpecElementInfo.arcMode);
    }

    public int hashCode() {
        return (((this.vectorValue.hashCode() * 31) + this.easing.hashCode()) * 31) + ArcMode.m50hashCodeimpl(this.arcMode);
    }

    public String toString() {
        return "VectorizedKeyframeSpecElementInfo(vectorValue=" + this.vectorValue + ", easing=" + this.easing + ", arcMode=" + ((Object) ArcMode.m51toStringimpl(this.arcMode)) + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    private VectorizedKeyframeSpecElementInfo(AnimationVector animationVector, Easing easing, int i) {
        this.vectorValue = animationVector;
        this.easing = easing;
        this.arcMode = i;
    }

    public final AnimationVector getVectorValue() {
        return this.vectorValue;
    }

    public final Easing getEasing() {
        return this.easing;
    }

    /* JADX INFO: renamed from: getArcMode--9T-Mq4, reason: not valid java name */
    public final int m81getArcMode9TMq4() {
        return this.arcMode;
    }
}
