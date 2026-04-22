package androidx.compose.ui.platform;

import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;

/* JADX INFO: compiled from: ViewConfiguration.kt */
/* JADX INFO: loaded from: classes.dex */
public interface ViewConfiguration {
    long getDoubleTapMinTimeMillis();

    long getDoubleTapTimeoutMillis();

    float getHandwritingGestureLineMargin();

    float getHandwritingSlop();

    long getLongPressTimeoutMillis();

    float getMaximumFlingVelocity();

    /* JADX INFO: renamed from: getMinimumTouchTargetSize-MYxV2XQ */
    long mo1813getMinimumTouchTargetSizeMYxV2XQ();

    float getTouchSlop();

    /* JADX INFO: renamed from: androidx.compose.ui.platform.ViewConfiguration$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: ViewConfiguration.kt */
    public abstract /* synthetic */ class CC {
        public static float $default$getHandwritingSlop(ViewConfiguration viewConfiguration) {
            return 2.0f;
        }

        public static float $default$getMaximumFlingVelocity(ViewConfiguration viewConfiguration) {
            return Float.MAX_VALUE;
        }

        public static float $default$getHandwritingGestureLineMargin(ViewConfiguration viewConfiguration) {
            return 16.0f;
        }

        /* JADX INFO: renamed from: $default$getMinimumTouchTargetSize-MYxV2XQ, reason: not valid java name */
        public static long m1993$default$getMinimumTouchTargetSizeMYxV2XQ(ViewConfiguration viewConfiguration) {
            float f = 48;
            return DpKt.m2427DpSizeYgX7TsA(Dp.m2416constructorimpl(f), Dp.m2416constructorimpl(f));
        }
    }
}
