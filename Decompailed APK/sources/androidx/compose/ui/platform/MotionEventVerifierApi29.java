package androidx.compose.ui.platform;

import android.view.MotionEvent;

/* JADX INFO: compiled from: AndroidComposeView.android.kt */
/* JADX INFO: loaded from: classes.dex */
final class MotionEventVerifierApi29 {
    public static final MotionEventVerifierApi29 INSTANCE = new MotionEventVerifierApi29();

    private MotionEventVerifierApi29() {
    }

    public final boolean isValidMotionEvent(MotionEvent motionEvent, int i) {
        float rawX = motionEvent.getRawX(i);
        if (!Float.isInfinite(rawX) && !Float.isNaN(rawX)) {
            float rawY = motionEvent.getRawY(i);
            if (!Float.isInfinite(rawY) && !Float.isNaN(rawY)) {
                return true;
            }
        }
        return false;
    }
}
