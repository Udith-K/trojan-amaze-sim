package androidx.compose.ui.input.pointer;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.unit.IntSize;

/* JADX INFO: compiled from: PointerEvent.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class PointerEventKt {
    public static final boolean changedToDown(PointerInputChange pointerInputChange) {
        return (pointerInputChange.isConsumed() || pointerInputChange.getPreviousPressed() || !pointerInputChange.getPressed()) ? false : true;
    }

    public static final boolean changedToDownIgnoreConsumed(PointerInputChange pointerInputChange) {
        return !pointerInputChange.getPreviousPressed() && pointerInputChange.getPressed();
    }

    public static final boolean changedToUp(PointerInputChange pointerInputChange) {
        return (pointerInputChange.isConsumed() || !pointerInputChange.getPreviousPressed() || pointerInputChange.getPressed()) ? false : true;
    }

    public static final boolean changedToUpIgnoreConsumed(PointerInputChange pointerInputChange) {
        return pointerInputChange.getPreviousPressed() && !pointerInputChange.getPressed();
    }

    public static final boolean positionChangedIgnoreConsumed(PointerInputChange pointerInputChange) {
        return !Offset.m1156equalsimpl0(positionChangeInternal(pointerInputChange, true), Offset.Companion.m1171getZeroF1C5BW0());
    }

    public static final long positionChange(PointerInputChange pointerInputChange) {
        return positionChangeInternal(pointerInputChange, false);
    }

    public static final long positionChangeIgnoreConsumed(PointerInputChange pointerInputChange) {
        return positionChangeInternal(pointerInputChange, true);
    }

    private static final long positionChangeInternal(PointerInputChange pointerInputChange, boolean z) {
        long jM1163minusMKHz9U = Offset.m1163minusMKHz9U(pointerInputChange.m1682getPositionF1C5BW0(), pointerInputChange.m1683getPreviousPositionF1C5BW0());
        return (z || !pointerInputChange.isConsumed()) ? jM1163minusMKHz9U : Offset.Companion.m1171getZeroF1C5BW0();
    }

    /* JADX INFO: renamed from: isOutOfBounds-O0kMr_c, reason: not valid java name */
    public static final boolean m1657isOutOfBoundsO0kMr_c(PointerInputChange pointerInputChange, long j) {
        long jM1682getPositionF1C5BW0 = pointerInputChange.m1682getPositionF1C5BW0();
        float fM1159getXimpl = Offset.m1159getXimpl(jM1682getPositionF1C5BW0);
        float fM1160getYimpl = Offset.m1160getYimpl(jM1682getPositionF1C5BW0);
        return fM1159getXimpl < 0.0f || fM1159getXimpl > ((float) IntSize.m2476getWidthimpl(j)) || fM1160getYimpl < 0.0f || fM1160getYimpl > ((float) IntSize.m2475getHeightimpl(j));
    }

    /* JADX INFO: renamed from: isOutOfBounds-jwHxaWs, reason: not valid java name */
    public static final boolean m1658isOutOfBoundsjwHxaWs(PointerInputChange pointerInputChange, long j, long j2) {
        if (!PointerType.m1701equalsimpl0(pointerInputChange.m1685getTypeT8wyACA(), PointerType.Companion.m1707getTouchT8wyACA())) {
            return m1657isOutOfBoundsO0kMr_c(pointerInputChange, j);
        }
        long jM1682getPositionF1C5BW0 = pointerInputChange.m1682getPositionF1C5BW0();
        float fM1159getXimpl = Offset.m1159getXimpl(jM1682getPositionF1C5BW0);
        float fM1160getYimpl = Offset.m1160getYimpl(jM1682getPositionF1C5BW0);
        return fM1159getXimpl < (-Size.m1196getWidthimpl(j2)) || fM1159getXimpl > ((float) IntSize.m2476getWidthimpl(j)) + Size.m1196getWidthimpl(j2) || fM1160getYimpl < (-Size.m1194getHeightimpl(j2)) || fM1160getYimpl > ((float) IntSize.m2475getHeightimpl(j)) + Size.m1194getHeightimpl(j2);
    }
}
