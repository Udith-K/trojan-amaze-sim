package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import java.util.List;

/* JADX INFO: compiled from: AndroidScrollable.android.kt */
/* JADX INFO: loaded from: classes.dex */
final class AndroidConfig implements ScrollConfig {
    public static final AndroidConfig INSTANCE = new AndroidConfig();

    private AndroidConfig() {
    }

    @Override // androidx.compose.foundation.gestures.ScrollConfig
    /* JADX INFO: renamed from: calculateMouseWheelScroll-8xgXZGE, reason: not valid java name */
    public long mo166calculateMouseWheelScroll8xgXZGE(Density density, PointerEvent pointerEvent, long j) {
        List changes = pointerEvent.getChanges();
        Offset offsetM1150boximpl = Offset.m1150boximpl(Offset.Companion.m1171getZeroF1C5BW0());
        int size = changes.size();
        for (int i = 0; i < size; i++) {
            offsetM1150boximpl = Offset.m1150boximpl(Offset.m1164plusMKHz9U(offsetM1150boximpl.m1168unboximpl(), ((PointerInputChange) changes.get(i)).m1684getScrollDeltaF1C5BW0()));
        }
        return Offset.m1165timestuRUvjQ(offsetM1150boximpl.m1168unboximpl(), -density.mo210toPx0680j_4(Dp.m2416constructorimpl(64)));
    }
}
