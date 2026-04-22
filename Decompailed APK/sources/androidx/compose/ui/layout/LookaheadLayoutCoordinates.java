package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.LookaheadDelegate;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LookaheadLayoutCoordinates.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LookaheadLayoutCoordinates implements LayoutCoordinates {
    private final LookaheadDelegate lookaheadDelegate;

    public LookaheadLayoutCoordinates(LookaheadDelegate lookaheadDelegate) {
        this.lookaheadDelegate = lookaheadDelegate;
    }

    public final NodeCoordinator getCoordinator() {
        return this.lookaheadDelegate.getCoordinator();
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: getSize-YbymL2g */
    public long mo1745getSizeYbymL2g() {
        LookaheadDelegate lookaheadDelegate = this.lookaheadDelegate;
        return IntSizeKt.IntSize(lookaheadDelegate.getWidth(), lookaheadDelegate.getHeight());
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public LayoutCoordinates getParentLayoutCoordinates() {
        LookaheadDelegate lookaheadDelegate;
        if (!isAttached()) {
            InlineClassHelperKt.throwIllegalStateException("LayoutCoordinate operations are only valid when isAttached is true");
        }
        NodeCoordinator wrappedBy$ui_release = getCoordinator().getLayoutNode().getOuterCoordinator$ui_release().getWrappedBy$ui_release();
        if (wrappedBy$ui_release == null || (lookaheadDelegate = wrappedBy$ui_release.getLookaheadDelegate()) == null) {
            return null;
        }
        return lookaheadDelegate.getCoordinates();
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public boolean isAttached() {
        return getCoordinator().isAttached();
    }

    /* JADX INFO: renamed from: getLookaheadOffset-F1C5BW0, reason: not valid java name */
    private final long m1755getLookaheadOffsetF1C5BW0() {
        LookaheadDelegate rootLookaheadDelegate = LookaheadLayoutCoordinatesKt.getRootLookaheadDelegate(this.lookaheadDelegate);
        LayoutCoordinates coordinates = rootLookaheadDelegate.getCoordinates();
        Offset.Companion companion = Offset.Companion;
        return Offset.m1163minusMKHz9U(mo1746localPositionOfR5De75A(coordinates, companion.m1171getZeroF1C5BW0()), getCoordinator().mo1746localPositionOfR5De75A(rootLookaheadDelegate.getCoordinator(), companion.m1171getZeroF1C5BW0()));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: screenToLocal-MK-Hz9U */
    public long mo1750screenToLocalMKHz9U(long j) {
        return Offset.m1164plusMKHz9U(getCoordinator().mo1750screenToLocalMKHz9U(j), m1755getLookaheadOffsetF1C5BW0());
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: windowToLocal-MK-Hz9U */
    public long mo1753windowToLocalMKHz9U(long j) {
        return Offset.m1164plusMKHz9U(getCoordinator().mo1753windowToLocalMKHz9U(j), m1755getLookaheadOffsetF1C5BW0());
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localToWindow-MK-Hz9U */
    public long mo1749localToWindowMKHz9U(long j) {
        return getCoordinator().mo1749localToWindowMKHz9U(Offset.m1164plusMKHz9U(j, m1755getLookaheadOffsetF1C5BW0()));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localToRoot-MK-Hz9U */
    public long mo1748localToRootMKHz9U(long j) {
        return getCoordinator().mo1748localToRootMKHz9U(Offset.m1164plusMKHz9U(j, m1755getLookaheadOffsetF1C5BW0()));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localPositionOf-R5De75A */
    public long mo1746localPositionOfR5De75A(LayoutCoordinates layoutCoordinates, long j) {
        return mo1747localPositionOfS_NoaFU(layoutCoordinates, j, true);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localPositionOf-S_NoaFU */
    public long mo1747localPositionOfS_NoaFU(LayoutCoordinates layoutCoordinates, long j, boolean z) {
        if (layoutCoordinates instanceof LookaheadLayoutCoordinates) {
            LookaheadDelegate lookaheadDelegate = ((LookaheadLayoutCoordinates) layoutCoordinates).lookaheadDelegate;
            lookaheadDelegate.getCoordinator().onCoordinatesUsed$ui_release();
            LookaheadDelegate lookaheadDelegate2 = getCoordinator().findCommonAncestor$ui_release(lookaheadDelegate.getCoordinator()).getLookaheadDelegate();
            if (lookaheadDelegate2 != null) {
                long jM2460minusqkQi6aY = IntOffset.m2460minusqkQi6aY(IntOffset.m2461plusqkQi6aY(lookaheadDelegate.m1848positionIniSbpLlY$ui_release(lookaheadDelegate2, !z), IntOffsetKt.m2467roundk4lQ0M(j)), this.lookaheadDelegate.m1848positionIniSbpLlY$ui_release(lookaheadDelegate2, !z));
                return OffsetKt.Offset(IntOffset.m2457getXimpl(jM2460minusqkQi6aY), IntOffset.m2458getYimpl(jM2460minusqkQi6aY));
            }
            LookaheadDelegate rootLookaheadDelegate = LookaheadLayoutCoordinatesKt.getRootLookaheadDelegate(lookaheadDelegate);
            long jM2461plusqkQi6aY = IntOffset.m2461plusqkQi6aY(IntOffset.m2461plusqkQi6aY(lookaheadDelegate.m1848positionIniSbpLlY$ui_release(rootLookaheadDelegate, !z), rootLookaheadDelegate.mo1842getPositionnOccac()), IntOffsetKt.m2467roundk4lQ0M(j));
            LookaheadDelegate rootLookaheadDelegate2 = LookaheadLayoutCoordinatesKt.getRootLookaheadDelegate(this.lookaheadDelegate);
            long jM2460minusqkQi6aY2 = IntOffset.m2460minusqkQi6aY(jM2461plusqkQi6aY, IntOffset.m2461plusqkQi6aY(this.lookaheadDelegate.m1848positionIniSbpLlY$ui_release(rootLookaheadDelegate2, !z), rootLookaheadDelegate2.mo1842getPositionnOccac()));
            long jOffset = OffsetKt.Offset(IntOffset.m2457getXimpl(jM2460minusqkQi6aY2), IntOffset.m2458getYimpl(jM2460minusqkQi6aY2));
            NodeCoordinator wrappedBy$ui_release = rootLookaheadDelegate2.getCoordinator().getWrappedBy$ui_release();
            Intrinsics.checkNotNull(wrappedBy$ui_release);
            NodeCoordinator wrappedBy$ui_release2 = rootLookaheadDelegate.getCoordinator().getWrappedBy$ui_release();
            Intrinsics.checkNotNull(wrappedBy$ui_release2);
            return wrappedBy$ui_release.mo1747localPositionOfS_NoaFU(wrappedBy$ui_release2, jOffset, z);
        }
        LookaheadDelegate rootLookaheadDelegate3 = LookaheadLayoutCoordinatesKt.getRootLookaheadDelegate(this.lookaheadDelegate);
        return Offset.m1164plusMKHz9U(mo1747localPositionOfS_NoaFU(rootLookaheadDelegate3.getLookaheadLayoutCoordinates(), j, z), rootLookaheadDelegate3.getCoordinator().getCoordinates().mo1747localPositionOfS_NoaFU(layoutCoordinates, Offset.Companion.m1171getZeroF1C5BW0(), z));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public Rect localBoundingBoxOf(LayoutCoordinates layoutCoordinates, boolean z) {
        return getCoordinator().localBoundingBoxOf(layoutCoordinates, z);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: transformFrom-EL8BTi8 */
    public void mo1751transformFromEL8BTi8(LayoutCoordinates layoutCoordinates, float[] fArr) {
        getCoordinator().mo1751transformFromEL8BTi8(layoutCoordinates, fArr);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: transformToScreen-58bKbWc */
    public void mo1752transformToScreen58bKbWc(float[] fArr) {
        getCoordinator().mo1752transformToScreen58bKbWc(fArr);
    }
}
