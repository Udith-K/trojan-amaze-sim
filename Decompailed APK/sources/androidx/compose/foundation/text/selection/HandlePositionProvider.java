package androidx.compose.foundation.text.selection;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.PopupPositionProvider;

/* JADX INFO: compiled from: SelectionHandles.kt */
/* JADX INFO: loaded from: classes.dex */
public final class HandlePositionProvider implements PopupPositionProvider {
    private final Alignment handleReferencePoint;
    private final OffsetProvider positionProvider;
    private long prevPosition = Offset.Companion.m1171getZeroF1C5BW0();

    public HandlePositionProvider(Alignment alignment, OffsetProvider offsetProvider) {
        this.handleReferencePoint = alignment;
        this.positionProvider = offsetProvider;
    }

    @Override // androidx.compose.ui.window.PopupPositionProvider
    /* JADX INFO: renamed from: calculatePosition-llwVHH4 */
    public long mo151calculatePositionllwVHH4(IntRect intRect, long j, LayoutDirection layoutDirection, long j2) {
        long jMo380provideF1C5BW0 = this.positionProvider.mo380provideF1C5BW0();
        if (!OffsetKt.m1173isSpecifiedk4lQ0M(jMo380provideF1C5BW0)) {
            jMo380provideF1C5BW0 = this.prevPosition;
        }
        this.prevPosition = jMo380provideF1C5BW0;
        return IntOffset.m2461plusqkQi6aY(IntOffset.m2461plusqkQi6aY(intRect.m2469getTopLeftnOccac(), IntOffsetKt.m2467roundk4lQ0M(jMo380provideF1C5BW0)), this.handleReferencePoint.mo1066alignKFBX0sM(j2, IntSize.Companion.m2480getZeroYbymL2g(), layoutDirection));
    }
}
