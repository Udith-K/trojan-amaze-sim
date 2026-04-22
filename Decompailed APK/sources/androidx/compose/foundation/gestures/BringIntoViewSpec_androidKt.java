package androidx.compose.foundation.gestures;

import android.content.Context;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.runtime.CompositionLocalAccessorScope;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: BringIntoViewSpec.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class BringIntoViewSpec_androidKt {
    private static final ProvidableCompositionLocal LocalBringIntoViewSpec = CompositionLocalKt.compositionLocalWithComputedDefaultOf(new Function1() { // from class: androidx.compose.foundation.gestures.BringIntoViewSpec_androidKt$LocalBringIntoViewSpec$1
        @Override // kotlin.jvm.functions.Function1
        public final BringIntoViewSpec invoke(CompositionLocalAccessorScope compositionLocalAccessorScope) {
            if (!((Context) compositionLocalAccessorScope.getCurrentValue(AndroidCompositionLocals_androidKt.getLocalContext())).getPackageManager().hasSystemFeature("android.software.leanback")) {
                return BringIntoViewSpec.Companion.getDefaultBringIntoViewSpec$foundation_release();
            }
            return BringIntoViewSpec_androidKt.getPivotBringIntoViewSpec();
        }
    });
    private static final BringIntoViewSpec PivotBringIntoViewSpec = new BringIntoViewSpec() { // from class: androidx.compose.foundation.gestures.BringIntoViewSpec_androidKt$PivotBringIntoViewSpec$1
        private final float childFraction;
        private final float parentFraction = 0.3f;
        private final AnimationSpec scrollAnimationSpec = AnimationSpecKt.tween$default(125, 0, new CubicBezierEasing(0.25f, 0.1f, 0.25f, 1.0f), 2, null);

        @Override // androidx.compose.foundation.gestures.BringIntoViewSpec
        public AnimationSpec getScrollAnimationSpec() {
            return this.scrollAnimationSpec;
        }

        @Override // androidx.compose.foundation.gestures.BringIntoViewSpec
        public float calculateScrollDistance(float f, float f2, float f3) {
            float fAbs = Math.abs((f2 + f) - f);
            boolean z = fAbs <= f3;
            float f4 = (this.parentFraction * f3) - (this.childFraction * fAbs);
            float f5 = f3 - f4;
            if (z && f5 < fAbs) {
                f4 = f3 - fAbs;
            }
            return f - f4;
        }
    };

    public static final ProvidableCompositionLocal getLocalBringIntoViewSpec() {
        return LocalBringIntoViewSpec;
    }

    public static final BringIntoViewSpec getPivotBringIntoViewSpec() {
        return PivotBringIntoViewSpec;
    }
}
