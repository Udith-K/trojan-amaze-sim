package androidx.compose.foundation.text.selection;

import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.platform.ViewConfiguration;

/* JADX INFO: compiled from: SelectionGestures.kt */
/* JADX INFO: loaded from: classes.dex */
final class ClicksCounter {
    private int clicks;
    private PointerInputChange prevClick;
    private final ViewConfiguration viewConfiguration;

    public ClicksCounter(ViewConfiguration viewConfiguration) {
        this.viewConfiguration = viewConfiguration;
    }

    public final int getClicks() {
        return this.clicks;
    }

    public final void update(PointerEvent pointerEvent) {
        PointerInputChange pointerInputChange = this.prevClick;
        PointerInputChange pointerInputChange2 = (PointerInputChange) pointerEvent.getChanges().get(0);
        if (pointerInputChange != null && timeIsTolerable(pointerInputChange, pointerInputChange2) && positionIsTolerable(pointerInputChange, pointerInputChange2)) {
            this.clicks++;
        } else {
            this.clicks = 1;
        }
        this.prevClick = pointerInputChange2;
    }

    public final boolean timeIsTolerable(PointerInputChange pointerInputChange, PointerInputChange pointerInputChange2) {
        return pointerInputChange2.getUptimeMillis() - pointerInputChange.getUptimeMillis() < this.viewConfiguration.getDoubleTapTimeoutMillis();
    }

    public final boolean positionIsTolerable(PointerInputChange pointerInputChange, PointerInputChange pointerInputChange2) {
        return SelectionGesturesKt.distanceIsTolerable(this.viewConfiguration, pointerInputChange, pointerInputChange2);
    }
}
