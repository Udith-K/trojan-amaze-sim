package androidx.compose.animation.core;

import androidx.collection.IntList;
import androidx.collection.IntObjectMap;
import androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec;
import androidx.compose.animation.core.VectorizedFiniteAnimationSpec;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VectorizedAnimationSpec.kt */
/* JADX INFO: loaded from: classes.dex */
public final class VectorizedKeyframesSpec implements VectorizedDurationBasedAnimationSpec {
    private ArcSpline arcSpline;
    private final Easing defaultEasing;
    private final int delayMillis;
    private final int durationMillis;
    private final int initialArcMode;
    private final IntObjectMap keyframes;
    private AnimationVector lastInitialValue;
    private AnimationVector lastTargetValue;
    private int[] modes;
    private float[] posArray;
    private float[] slopeArray;
    private float[] times;
    private final IntList timestamps;
    private AnimationVector valueVector;
    private AnimationVector velocityVector;

    public /* synthetic */ VectorizedKeyframesSpec(IntList intList, IntObjectMap intObjectMap, int i, int i2, Easing easing, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(intList, intObjectMap, i, i2, easing, i3);
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public /* synthetic */ long getDurationNanos(AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        return VectorizedDurationBasedAnimationSpec.CC.$default$getDurationNanos(this, animationVector, animationVector2, animationVector3);
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public /* synthetic */ AnimationVector getEndVelocity(AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        return getVelocityFromNanos(getDurationNanos(animationVector, animationVector2, animationVector3), animationVector, animationVector2, animationVector3);
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public /* synthetic */ boolean isInfinite() {
        return VectorizedFiniteAnimationSpec.CC.$default$isInfinite(this);
    }

    private VectorizedKeyframesSpec(IntList intList, IntObjectMap intObjectMap, int i, int i2, Easing easing, int i3) {
        this.timestamps = intList;
        this.keyframes = intObjectMap;
        this.durationMillis = i;
        this.delayMillis = i2;
        this.defaultEasing = easing;
        this.initialArcMode = i3;
    }

    @Override // androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec
    public int getDurationMillis() {
        return this.durationMillis;
    }

    @Override // androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec
    public int getDelayMillis() {
        return this.delayMillis;
    }

    private final void init(AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        float[] fArr;
        float[] fArr2;
        boolean z = this.arcSpline != null;
        if (this.valueVector == null) {
            this.valueVector = AnimationVectorsKt.newInstance(animationVector);
            this.velocityVector = AnimationVectorsKt.newInstance(animationVector3);
            int size = this.timestamps.getSize();
            float[] fArr3 = new float[size];
            for (int i = 0; i < size; i++) {
                fArr3[i] = this.timestamps.get(i) / 1000;
            }
            this.times = fArr3;
            int size2 = this.timestamps.getSize();
            int[] iArr = new int[size2];
            for (int i2 = 0; i2 < size2; i2++) {
                VectorizedKeyframeSpecElementInfo vectorizedKeyframeSpecElementInfo = (VectorizedKeyframeSpecElementInfo) this.keyframes.get(this.timestamps.get(i2));
                int iM81getArcMode9TMq4 = vectorizedKeyframeSpecElementInfo != null ? vectorizedKeyframeSpecElementInfo.m81getArcMode9TMq4() : this.initialArcMode;
                if (!ArcMode.m49equalsimpl0(iM81getArcMode9TMq4, ArcMode.Companion.m52getArcLinear9TMq4())) {
                    z = true;
                }
                iArr[i2] = iM81getArcMode9TMq4;
            }
            this.modes = iArr;
        }
        if (z) {
            float[] fArr4 = null;
            if (this.arcSpline != null) {
                AnimationVector animationVector4 = this.lastInitialValue;
                if (animationVector4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("lastInitialValue");
                    animationVector4 = null;
                }
                if (Intrinsics.areEqual(animationVector4, animationVector)) {
                    AnimationVector animationVector5 = this.lastTargetValue;
                    if (animationVector5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("lastTargetValue");
                        animationVector5 = null;
                    }
                    if (Intrinsics.areEqual(animationVector5, animationVector2)) {
                        return;
                    }
                }
            }
            this.lastInitialValue = animationVector;
            this.lastTargetValue = animationVector2;
            int size$animation_core_release = (animationVector.getSize$animation_core_release() % 2) + animationVector.getSize$animation_core_release();
            this.posArray = new float[size$animation_core_release];
            this.slopeArray = new float[size$animation_core_release];
            int size3 = this.timestamps.getSize();
            float[][] fArr5 = new float[size3][];
            for (int i3 = 0; i3 < size3; i3++) {
                int i4 = this.timestamps.get(i3);
                if (i4 == 0) {
                    if (!this.keyframes.contains(i4)) {
                        fArr2 = new float[size$animation_core_release];
                        for (int i5 = 0; i5 < size$animation_core_release; i5++) {
                            fArr2[i5] = animationVector.get$animation_core_release(i5);
                        }
                    } else {
                        fArr = new float[size$animation_core_release];
                        Object obj = this.keyframes.get(i4);
                        Intrinsics.checkNotNull(obj);
                        AnimationVector vectorValue = ((VectorizedKeyframeSpecElementInfo) obj).getVectorValue();
                        for (int i6 = 0; i6 < size$animation_core_release; i6++) {
                            fArr[i6] = vectorValue.get$animation_core_release(i6);
                        }
                        fArr2 = fArr;
                    }
                } else {
                    if (i4 == getDurationMillis()) {
                        if (!this.keyframes.contains(i4)) {
                            fArr2 = new float[size$animation_core_release];
                            for (int i7 = 0; i7 < size$animation_core_release; i7++) {
                                fArr2[i7] = animationVector2.get$animation_core_release(i7);
                            }
                        } else {
                            fArr = new float[size$animation_core_release];
                            Object obj2 = this.keyframes.get(i4);
                            Intrinsics.checkNotNull(obj2);
                            AnimationVector vectorValue2 = ((VectorizedKeyframeSpecElementInfo) obj2).getVectorValue();
                            for (int i8 = 0; i8 < size$animation_core_release; i8++) {
                                fArr[i8] = vectorValue2.get$animation_core_release(i8);
                            }
                        }
                    } else {
                        fArr = new float[size$animation_core_release];
                        Object obj3 = this.keyframes.get(i4);
                        Intrinsics.checkNotNull(obj3);
                        AnimationVector vectorValue3 = ((VectorizedKeyframeSpecElementInfo) obj3).getVectorValue();
                        for (int i9 = 0; i9 < size$animation_core_release; i9++) {
                            fArr[i9] = vectorValue3.get$animation_core_release(i9);
                        }
                    }
                    fArr2 = fArr;
                }
                fArr5[i3] = fArr2;
            }
            int[] iArr2 = this.modes;
            if (iArr2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("modes");
                iArr2 = null;
            }
            float[] fArr6 = this.times;
            if (fArr6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("times");
            } else {
                fArr4 = fArr6;
            }
            this.arcSpline = new ArcSpline(iArr2, fArr4, fArr5);
        }
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public AnimationVector getValueFromNanos(long j, AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        int iClampPlayTime = (int) VectorizedAnimationSpecKt.clampPlayTime(this, j / 1000000);
        if (this.keyframes.contains(iClampPlayTime)) {
            Object obj = this.keyframes.get(iClampPlayTime);
            Intrinsics.checkNotNull(obj);
            return ((VectorizedKeyframeSpecElementInfo) obj).getVectorValue();
        }
        if (iClampPlayTime >= getDurationMillis()) {
            return animationVector2;
        }
        if (iClampPlayTime <= 0) {
            return animationVector;
        }
        init(animationVector, animationVector2, animationVector3);
        int i = 0;
        if (this.arcSpline != null) {
            float easedTime = getEasedTime(iClampPlayTime);
            ArcSpline arcSpline = this.arcSpline;
            if (arcSpline == null) {
                Intrinsics.throwUninitializedPropertyAccessException("arcSpline");
                arcSpline = null;
            }
            float[] fArr = this.posArray;
            if (fArr == null) {
                Intrinsics.throwUninitializedPropertyAccessException("posArray");
                fArr = null;
            }
            arcSpline.getPos(easedTime, fArr);
            float[] fArr2 = this.posArray;
            if (fArr2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("posArray");
                fArr2 = null;
            }
            int length = fArr2.length;
            while (i < length) {
                AnimationVector animationVector4 = this.valueVector;
                if (animationVector4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("valueVector");
                    animationVector4 = null;
                }
                float[] fArr3 = this.posArray;
                if (fArr3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("posArray");
                    fArr3 = null;
                }
                animationVector4.set$animation_core_release(i, fArr3[i]);
                i++;
            }
            AnimationVector animationVector5 = this.valueVector;
            if (animationVector5 != null) {
                return animationVector5;
            }
            Intrinsics.throwUninitializedPropertyAccessException("valueVector");
            return null;
        }
        int iFindEntryForTimeMillis = findEntryForTimeMillis(iClampPlayTime);
        float easedTimeFromIndex = getEasedTimeFromIndex(iFindEntryForTimeMillis, iClampPlayTime, true);
        int i2 = this.timestamps.get(iFindEntryForTimeMillis);
        if (this.keyframes.contains(i2)) {
            Object obj2 = this.keyframes.get(i2);
            Intrinsics.checkNotNull(obj2);
            animationVector = ((VectorizedKeyframeSpecElementInfo) obj2).getVectorValue();
        }
        int i3 = this.timestamps.get(iFindEntryForTimeMillis + 1);
        if (this.keyframes.contains(i3)) {
            Object obj3 = this.keyframes.get(i3);
            Intrinsics.checkNotNull(obj3);
            animationVector2 = ((VectorizedKeyframeSpecElementInfo) obj3).getVectorValue();
        }
        AnimationVector animationVector6 = this.valueVector;
        if (animationVector6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("valueVector");
            animationVector6 = null;
        }
        int size$animation_core_release = animationVector6.getSize$animation_core_release();
        while (i < size$animation_core_release) {
            AnimationVector animationVector7 = this.valueVector;
            if (animationVector7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("valueVector");
                animationVector7 = null;
            }
            animationVector7.set$animation_core_release(i, VectorConvertersKt.lerp(animationVector.get$animation_core_release(i), animationVector2.get$animation_core_release(i), easedTimeFromIndex));
            i++;
        }
        AnimationVector animationVector8 = this.valueVector;
        if (animationVector8 != null) {
            return animationVector8;
        }
        Intrinsics.throwUninitializedPropertyAccessException("valueVector");
        return null;
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public AnimationVector getVelocityFromNanos(long j, AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        long jClampPlayTime = VectorizedAnimationSpecKt.clampPlayTime(this, j / 1000000);
        if (jClampPlayTime < 0) {
            return animationVector3;
        }
        init(animationVector, animationVector2, animationVector3);
        int i = 0;
        if (this.arcSpline != null) {
            float easedTime = getEasedTime((int) jClampPlayTime);
            ArcSpline arcSpline = this.arcSpline;
            if (arcSpline == null) {
                Intrinsics.throwUninitializedPropertyAccessException("arcSpline");
                arcSpline = null;
            }
            float[] fArr = this.slopeArray;
            if (fArr == null) {
                Intrinsics.throwUninitializedPropertyAccessException("slopeArray");
                fArr = null;
            }
            arcSpline.getSlope(easedTime, fArr);
            float[] fArr2 = this.slopeArray;
            if (fArr2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("slopeArray");
                fArr2 = null;
            }
            int length = fArr2.length;
            while (i < length) {
                AnimationVector animationVector4 = this.velocityVector;
                if (animationVector4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("velocityVector");
                    animationVector4 = null;
                }
                float[] fArr3 = this.slopeArray;
                if (fArr3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("slopeArray");
                    fArr3 = null;
                }
                animationVector4.set$animation_core_release(i, fArr3[i]);
                i++;
            }
            AnimationVector animationVector5 = this.velocityVector;
            if (animationVector5 != null) {
                return animationVector5;
            }
            Intrinsics.throwUninitializedPropertyAccessException("velocityVector");
            return null;
        }
        AnimationVector valueFromMillis = VectorizedAnimationSpecKt.getValueFromMillis(this, jClampPlayTime - 1, animationVector, animationVector2, animationVector3);
        AnimationVector valueFromMillis2 = VectorizedAnimationSpecKt.getValueFromMillis(this, jClampPlayTime, animationVector, animationVector2, animationVector3);
        int size$animation_core_release = valueFromMillis.getSize$animation_core_release();
        while (i < size$animation_core_release) {
            AnimationVector animationVector6 = this.velocityVector;
            if (animationVector6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("velocityVector");
                animationVector6 = null;
            }
            animationVector6.set$animation_core_release(i, (valueFromMillis.get$animation_core_release(i) - valueFromMillis2.get$animation_core_release(i)) * 1000.0f);
            i++;
        }
        AnimationVector animationVector7 = this.velocityVector;
        if (animationVector7 != null) {
            return animationVector7;
        }
        Intrinsics.throwUninitializedPropertyAccessException("velocityVector");
        return null;
    }

    private final float getEasedTime(int i) {
        return getEasedTimeFromIndex(findEntryForTimeMillis(i), i, false);
    }

    private final float getEasedTimeFromIndex(int i, int i2, boolean z) {
        Easing easing;
        float f;
        IntList intList = this.timestamps;
        if (i >= intList._size - 1) {
            f = i2;
        } else {
            int i3 = intList.get(i);
            int i4 = this.timestamps.get(i + 1);
            if (i2 == i3) {
                f = i3;
            } else {
                int i5 = i4 - i3;
                VectorizedKeyframeSpecElementInfo vectorizedKeyframeSpecElementInfo = (VectorizedKeyframeSpecElementInfo) this.keyframes.get(i3);
                if (vectorizedKeyframeSpecElementInfo == null || (easing = vectorizedKeyframeSpecElementInfo.getEasing()) == null) {
                    easing = this.defaultEasing;
                }
                float f2 = i5;
                float fTransform = easing.transform((i2 - i3) / f2);
                if (z) {
                    return fTransform;
                }
                f = (f2 * fTransform) + i3;
            }
        }
        return f / 1000;
    }

    private final int findEntryForTimeMillis(int i) {
        int iBinarySearch$default = IntListExtensionKt.binarySearch$default(this.timestamps, i, 0, 0, 6, null);
        return iBinarySearch$default < -1 ? -(iBinarySearch$default + 2) : iBinarySearch$default;
    }
}
