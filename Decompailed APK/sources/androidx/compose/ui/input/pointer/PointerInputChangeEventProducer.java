package androidx.compose.ui.input.pointer;

import androidx.collection.LongSparseArray;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: PointerInputEventProcessor.kt */
/* JADX INFO: loaded from: classes.dex */
final class PointerInputChangeEventProducer {
    private final LongSparseArray previousPointerInputData = new LongSparseArray(0, 1, null);

    public final InternalPointerEvent produce(PointerInputEvent pointerInputEvent, PositionCalculator positionCalculator) {
        long uptime;
        boolean down;
        long jMo1711screenToLocalMKHz9U;
        LongSparseArray longSparseArray = new LongSparseArray(pointerInputEvent.getPointers().size());
        List pointers = pointerInputEvent.getPointers();
        int size = pointers.size();
        for (int i = 0; i < size; i++) {
            PointerInputEventData pointerInputEventData = (PointerInputEventData) pointers.get(i);
            PointerInputData pointerInputData = (PointerInputData) this.previousPointerInputData.get(pointerInputEventData.m1687getIdJ3iCeTQ());
            if (pointerInputData == null) {
                uptime = pointerInputEventData.getUptime();
                jMo1711screenToLocalMKHz9U = pointerInputEventData.m1689getPositionF1C5BW0();
                down = false;
            } else {
                long uptime2 = pointerInputData.getUptime();
                uptime = uptime2;
                down = pointerInputData.getDown();
                jMo1711screenToLocalMKHz9U = positionCalculator.mo1711screenToLocalMKHz9U(pointerInputData.m1686getPositionOnScreenF1C5BW0());
            }
            longSparseArray.put(pointerInputEventData.m1687getIdJ3iCeTQ(), new PointerInputChange(pointerInputEventData.m1687getIdJ3iCeTQ(), pointerInputEventData.getUptime(), pointerInputEventData.m1689getPositionF1C5BW0(), pointerInputEventData.getDown(), pointerInputEventData.getPressure(), uptime, jMo1711screenToLocalMKHz9U, down, false, pointerInputEventData.m1692getTypeT8wyACA(), pointerInputEventData.getHistorical(), pointerInputEventData.m1691getScrollDeltaF1C5BW0(), pointerInputEventData.m1688getOriginalEventPositionF1C5BW0(), null));
            if (pointerInputEventData.getDown()) {
                this.previousPointerInputData.put(pointerInputEventData.m1687getIdJ3iCeTQ(), new PointerInputData(pointerInputEventData.getUptime(), pointerInputEventData.m1690getPositionOnScreenF1C5BW0(), pointerInputEventData.getDown(), pointerInputEventData.m1692getTypeT8wyACA(), null));
            } else {
                this.previousPointerInputData.remove(pointerInputEventData.m1687getIdJ3iCeTQ());
            }
        }
        return new InternalPointerEvent(longSparseArray, pointerInputEvent);
    }

    public final void clear() {
        this.previousPointerInputData.clear();
    }

    /* JADX INFO: compiled from: PointerInputEventProcessor.kt */
    private static final class PointerInputData {
        private final boolean down;
        private final long positionOnScreen;
        private final int type;
        private final long uptime;

        public /* synthetic */ PointerInputData(long j, long j2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(j, j2, z, i);
        }

        private PointerInputData(long j, long j2, boolean z, int i) {
            this.uptime = j;
            this.positionOnScreen = j2;
            this.down = z;
            this.type = i;
        }

        public final long getUptime() {
            return this.uptime;
        }

        /* JADX INFO: renamed from: getPositionOnScreen-F1C5BW0, reason: not valid java name */
        public final long m1686getPositionOnScreenF1C5BW0() {
            return this.positionOnScreen;
        }

        public final boolean getDown() {
            return this.down;
        }
    }
}
