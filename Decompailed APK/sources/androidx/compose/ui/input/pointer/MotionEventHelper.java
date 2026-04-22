package androidx.compose.ui.input.pointer;

import android.view.MotionEvent;
import androidx.compose.ui.geometry.OffsetKt;

/* JADX INFO: compiled from: MotionEventAdapter.android.kt */
/* JADX INFO: loaded from: classes.dex */
final class MotionEventHelper {
    public static final MotionEventHelper INSTANCE = new MotionEventHelper();

    private MotionEventHelper() {
    }

    /* JADX INFO: renamed from: toRawOffset-dBAh8RU, reason: not valid java name */
    public final long m1651toRawOffsetdBAh8RU(MotionEvent motionEvent, int i) {
        return OffsetKt.Offset(motionEvent.getRawX(i), motionEvent.getRawY(i));
    }
}
