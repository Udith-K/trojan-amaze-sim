package androidx.compose.animation.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: VectorizedAnimationSpec.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class VectorizedAnimationSpecKt {
    public static final AnimationVector getValueFromMillis(VectorizedAnimationSpec vectorizedAnimationSpec, long j, AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        return vectorizedAnimationSpec.getValueFromNanos(j * 1000000, animationVector, animationVector2, animationVector3);
    }

    public static final long clampPlayTime(VectorizedDurationBasedAnimationSpec vectorizedDurationBasedAnimationSpec, long j) {
        return RangesKt.coerceIn(j - ((long) vectorizedDurationBasedAnimationSpec.getDelayMillis()), 0L, vectorizedDurationBasedAnimationSpec.getDurationMillis());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Animations createSpringAnimations(AnimationVector animationVector, float f, float f2) {
        if (animationVector != null) {
            return new Animations(animationVector, f, f2) { // from class: androidx.compose.animation.core.VectorizedAnimationSpecKt.createSpringAnimations.1
                private final List anims;

                {
                    IntRange intRangeUntil = RangesKt.until(0, animationVector.getSize$animation_core_release());
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil, 10));
                    Iterator it = intRangeUntil.iterator();
                    while (it.hasNext()) {
                        arrayList.add(new FloatSpringSpec(f, f2, animationVector.get$animation_core_release(((IntIterator) it).nextInt())));
                    }
                    this.anims = arrayList;
                }

                @Override // androidx.compose.animation.core.Animations
                public FloatSpringSpec get(int i) {
                    return (FloatSpringSpec) this.anims.get(i);
                }
            };
        }
        return new Animations(f, f2) { // from class: androidx.compose.animation.core.VectorizedAnimationSpecKt.createSpringAnimations.2
            private final FloatSpringSpec anim;

            {
                this.anim = new FloatSpringSpec(f, f2, 0.0f, 4, null);
            }

            @Override // androidx.compose.animation.core.Animations
            public FloatSpringSpec get(int i) {
                return this.anim;
            }
        };
    }
}
