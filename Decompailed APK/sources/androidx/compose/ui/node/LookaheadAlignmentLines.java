package androidx.compose.ui.node;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.unit.IntOffset;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LayoutNodeAlignmentLines.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LookaheadAlignmentLines extends AlignmentLines {
    public LookaheadAlignmentLines(AlignmentLinesOwner alignmentLinesOwner) {
        super(alignmentLinesOwner, null);
    }

    @Override // androidx.compose.ui.node.AlignmentLines
    protected Map getAlignmentLinesMap(NodeCoordinator nodeCoordinator) {
        LookaheadDelegate lookaheadDelegate = nodeCoordinator.getLookaheadDelegate();
        Intrinsics.checkNotNull(lookaheadDelegate);
        return lookaheadDelegate.getMeasureResult$ui_release().getAlignmentLines();
    }

    @Override // androidx.compose.ui.node.AlignmentLines
    protected int getPositionFor(NodeCoordinator nodeCoordinator, AlignmentLine alignmentLine) {
        LookaheadDelegate lookaheadDelegate = nodeCoordinator.getLookaheadDelegate();
        Intrinsics.checkNotNull(lookaheadDelegate);
        return lookaheadDelegate.get(alignmentLine);
    }

    @Override // androidx.compose.ui.node.AlignmentLines
    /* JADX INFO: renamed from: calculatePositionInParent-R5De75A */
    protected long mo1787calculatePositionInParentR5De75A(NodeCoordinator nodeCoordinator, long j) {
        LookaheadDelegate lookaheadDelegate = nodeCoordinator.getLookaheadDelegate();
        Intrinsics.checkNotNull(lookaheadDelegate);
        long jMo1842getPositionnOccac = lookaheadDelegate.mo1842getPositionnOccac();
        return Offset.m1164plusMKHz9U(OffsetKt.Offset(IntOffset.m2457getXimpl(jMo1842getPositionnOccac), IntOffset.m2458getYimpl(jMo1842getPositionnOccac)), j);
    }
}
