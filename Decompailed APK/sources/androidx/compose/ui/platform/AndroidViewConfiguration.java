package androidx.compose.ui.platform;

import android.os.Build;
import androidx.compose.ui.platform.ViewConfiguration;

/* JADX INFO: compiled from: AndroidViewConfiguration.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AndroidViewConfiguration implements ViewConfiguration {
    private final android.view.ViewConfiguration viewConfiguration;

    @Override // androidx.compose.ui.platform.ViewConfiguration
    public long getDoubleTapMinTimeMillis() {
        return 40L;
    }

    @Override // androidx.compose.ui.platform.ViewConfiguration
    /* JADX INFO: renamed from: getMinimumTouchTargetSize-MYxV2XQ */
    public /* synthetic */ long mo1813getMinimumTouchTargetSizeMYxV2XQ() {
        return ViewConfiguration.CC.m1993$default$getMinimumTouchTargetSizeMYxV2XQ(this);
    }

    public AndroidViewConfiguration(android.view.ViewConfiguration viewConfiguration) {
        this.viewConfiguration = viewConfiguration;
    }

    @Override // androidx.compose.ui.platform.ViewConfiguration
    public long getLongPressTimeoutMillis() {
        return android.view.ViewConfiguration.getLongPressTimeout();
    }

    @Override // androidx.compose.ui.platform.ViewConfiguration
    public long getDoubleTapTimeoutMillis() {
        return android.view.ViewConfiguration.getDoubleTapTimeout();
    }

    @Override // androidx.compose.ui.platform.ViewConfiguration
    public float getTouchSlop() {
        return this.viewConfiguration.getScaledTouchSlop();
    }

    @Override // androidx.compose.ui.platform.ViewConfiguration
    public float getHandwritingSlop() {
        if (Build.VERSION.SDK_INT >= 34) {
            return AndroidViewConfigurationApi34.INSTANCE.getScaledHandwritingSlop(this.viewConfiguration);
        }
        return ViewConfiguration.CC.$default$getHandwritingSlop(this);
    }

    @Override // androidx.compose.ui.platform.ViewConfiguration
    public float getMaximumFlingVelocity() {
        return this.viewConfiguration.getScaledMaximumFlingVelocity();
    }

    @Override // androidx.compose.ui.platform.ViewConfiguration
    public float getHandwritingGestureLineMargin() {
        if (Build.VERSION.SDK_INT >= 34) {
            return AndroidViewConfigurationApi34.INSTANCE.getScaledHandwritingGestureLineMargin(this.viewConfiguration);
        }
        return ViewConfiguration.CC.$default$getHandwritingGestureLineMargin(this);
    }
}
