package androidx.compose.animation.core;

import androidx.collection.MutableIntList;
import androidx.collection.MutableIntObjectMap;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AnimationSpec.kt */
/* JADX INFO: loaded from: classes.dex */
public final class KeyframesSpec implements DurationBasedAnimationSpec {
    private final KeyframesSpecConfig config;

    public KeyframesSpec(KeyframesSpecConfig keyframesSpecConfig) {
        this.config = keyframesSpecConfig;
    }

    /* JADX INFO: compiled from: AnimationSpec.kt */
    public static final class KeyframesSpecConfig extends KeyframesSpecBaseConfig {
        public KeyframesSpecConfig() {
            super(null);
        }

        public KeyframeEntity at(Object obj, int i) {
            KeyframeEntity keyframeEntity = new KeyframeEntity(obj, null, 0, 6, null);
            getKeyframes$animation_core_release().set(i, keyframeEntity);
            return keyframeEntity;
        }
    }

    @Override // androidx.compose.animation.core.DurationBasedAnimationSpec, androidx.compose.animation.core.AnimationSpec
    public VectorizedKeyframesSpec vectorize(TwoWayConverter twoWayConverter) {
        long[] jArr;
        int[] iArr;
        long[] jArr2;
        int[] iArr2;
        int i;
        MutableIntList mutableIntList = new MutableIntList(this.config.getKeyframes$animation_core_release().getSize() + 2);
        MutableIntObjectMap mutableIntObjectMap = new MutableIntObjectMap(this.config.getKeyframes$animation_core_release().getSize());
        MutableIntObjectMap keyframes$animation_core_release = this.config.getKeyframes$animation_core_release();
        int[] iArr3 = keyframes$animation_core_release.keys;
        Object[] objArr = keyframes$animation_core_release.values;
        long[] jArr3 = keyframes$animation_core_release.metadata;
        int length = jArr3.length - 2;
        if (length >= 0) {
            int i2 = 0;
            while (true) {
                long j = jArr3[i2];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i3 = 8;
                    int i4 = 8 - ((~(i2 - length)) >>> 31);
                    int i5 = 0;
                    while (i5 < i4) {
                        if ((255 & j) < 128) {
                            int i6 = (i2 << 3) + i5;
                            int i7 = iArr3[i6];
                            KeyframeEntity keyframeEntity = (KeyframeEntity) objArr[i6];
                            mutableIntList.add(i7);
                            jArr2 = jArr3;
                            iArr2 = iArr3;
                            mutableIntObjectMap.set(i7, new VectorizedKeyframeSpecElementInfo((AnimationVector) twoWayConverter.getConvertToVector().invoke(keyframeEntity.getValue$animation_core_release()), keyframeEntity.getEasing$animation_core_release(), keyframeEntity.m55getArcMode9TMq4$animation_core_release(), null));
                            i = 8;
                        } else {
                            jArr2 = jArr3;
                            iArr2 = iArr3;
                            i = i3;
                        }
                        j >>= i;
                        i5++;
                        i3 = i;
                        jArr3 = jArr2;
                        iArr3 = iArr2;
                    }
                    jArr = jArr3;
                    iArr = iArr3;
                    if (i4 != i3) {
                        break;
                    }
                } else {
                    jArr = jArr3;
                    iArr = iArr3;
                }
                if (i2 == length) {
                    break;
                }
                i2++;
                jArr3 = jArr;
                iArr3 = iArr;
            }
        }
        if (!this.config.getKeyframes$animation_core_release().contains(0)) {
            mutableIntList.add(0, 0);
        }
        if (!this.config.getKeyframes$animation_core_release().contains(this.config.getDurationMillis())) {
            mutableIntList.add(this.config.getDurationMillis());
        }
        mutableIntList.sort();
        return new VectorizedKeyframesSpec(mutableIntList, mutableIntObjectMap, this.config.getDurationMillis(), this.config.getDelayMillis(), EasingKt.getLinearEasing(), ArcMode.Companion.m52getArcLinear9TMq4(), null);
    }

    /* JADX INFO: compiled from: AnimationSpec.kt */
    public static final class KeyframeEntity extends KeyframeBaseEntity {
        private int arcMode;

        public /* synthetic */ KeyframeEntity(Object obj, Easing easing, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(obj, easing, i);
        }

        public /* synthetic */ KeyframeEntity(Object obj, Easing easing, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(obj, (i2 & 2) != 0 ? EasingKt.getLinearEasing() : easing, (i2 & 4) != 0 ? ArcMode.Companion.m52getArcLinear9TMq4() : i, null);
        }

        /* JADX INFO: renamed from: getArcMode--9T-Mq4$animation_core_release, reason: not valid java name */
        public final int m55getArcMode9TMq4$animation_core_release() {
            return this.arcMode;
        }

        private KeyframeEntity(Object obj, Easing easing, int i) {
            super(obj, easing, null);
            this.arcMode = i;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof KeyframeEntity)) {
                return false;
            }
            KeyframeEntity keyframeEntity = (KeyframeEntity) obj;
            return Intrinsics.areEqual(keyframeEntity.getValue$animation_core_release(), getValue$animation_core_release()) && Intrinsics.areEqual(keyframeEntity.getEasing$animation_core_release(), getEasing$animation_core_release()) && ArcMode.m49equalsimpl0(keyframeEntity.arcMode, this.arcMode);
        }

        public int hashCode() {
            Object value$animation_core_release = getValue$animation_core_release();
            return ((((value$animation_core_release != null ? value$animation_core_release.hashCode() : 0) * 31) + ArcMode.m50hashCodeimpl(this.arcMode)) * 31) + getEasing$animation_core_release().hashCode();
        }
    }
}
