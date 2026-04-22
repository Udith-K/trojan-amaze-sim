package androidx.compose.ui.node;

import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LookaheadLayoutCoordinates;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LookaheadDelegate.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class LookaheadDelegate extends LookaheadCapablePlaceable implements Measurable {
    private MeasureResult _measureResult;
    private final NodeCoordinator coordinator;
    private Map oldAlignmentLines;
    private long position = IntOffset.Companion.m2464getZeronOccac();
    private final LookaheadLayoutCoordinates lookaheadLayoutCoordinates = new LookaheadLayoutCoordinates(this);
    private final Map cachedAlignmentLinesMap = new LinkedHashMap();

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable, androidx.compose.ui.layout.IntrinsicMeasureScope
    public boolean isLookingAhead() {
        return true;
    }

    public abstract int maxIntrinsicHeight(int i);

    public abstract int maxIntrinsicWidth(int i);

    public abstract int minIntrinsicHeight(int i);

    public abstract int minIntrinsicWidth(int i);

    public final NodeCoordinator getCoordinator() {
        return this.coordinator;
    }

    public LookaheadDelegate(NodeCoordinator nodeCoordinator) {
        this.coordinator = nodeCoordinator;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public LookaheadCapablePlaceable getChild() {
        NodeCoordinator wrapped$ui_release = this.coordinator.getWrapped$ui_release();
        if (wrapped$ui_release != null) {
            return wrapped$ui_release.getLookaheadDelegate();
        }
        return null;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public boolean getHasMeasureResult() {
        return this._measureResult != null;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    /* JADX INFO: renamed from: getPosition-nOcc-ac */
    public long mo1842getPositionnOccac() {
        return this.position;
    }

    /* JADX INFO: renamed from: setPosition--gyyYBs, reason: not valid java name */
    public void m1849setPositiongyyYBs(long j) {
        this.position = j;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public MeasureResult getMeasureResult$ui_release() {
        MeasureResult measureResult = this._measureResult;
        if (measureResult != null) {
            return measureResult;
        }
        throw new IllegalStateException("LookaheadDelegate has not been measured yet when measureResult is requested.");
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
    public LayoutDirection getLayoutDirection() {
        return this.coordinator.getLayoutDirection();
    }

    @Override // androidx.compose.ui.unit.Density
    public float getDensity() {
        return this.coordinator.getDensity();
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public float getFontScale() {
        return this.coordinator.getFontScale();
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public LookaheadCapablePlaceable getParent() {
        NodeCoordinator wrappedBy$ui_release = this.coordinator.getWrappedBy$ui_release();
        if (wrappedBy$ui_release != null) {
            return wrappedBy$ui_release.getLookaheadDelegate();
        }
        return null;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public LayoutNode getLayoutNode() {
        return this.coordinator.getLayoutNode();
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public LayoutCoordinates getCoordinates() {
        return this.lookaheadLayoutCoordinates;
    }

    /* JADX INFO: renamed from: getConstraints-msEJaDk$ui_release, reason: not valid java name */
    public final long m1846getConstraintsmsEJaDk$ui_release() {
        return m1761getMeasurementConstraintsmsEJaDk();
    }

    public final LookaheadLayoutCoordinates getLookaheadLayoutCoordinates() {
        return this.lookaheadLayoutCoordinates;
    }

    public AlignmentLinesOwner getAlignmentLinesOwner() {
        AlignmentLinesOwner lookaheadAlignmentLinesOwner$ui_release = this.coordinator.getLayoutNode().getLayoutDelegate$ui_release().getLookaheadAlignmentLinesOwner$ui_release();
        Intrinsics.checkNotNull(lookaheadAlignmentLinesOwner$ui_release);
        return lookaheadAlignmentLinesOwner$ui_release;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void set_measureResult(MeasureResult measureResult) {
        Unit unit;
        Map map;
        if (measureResult != null) {
            m1763setMeasuredSizeozmzZPI(IntSizeKt.IntSize(measureResult.getWidth(), measureResult.getHeight()));
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            m1763setMeasuredSizeozmzZPI(IntSize.Companion.m2480getZeroYbymL2g());
        }
        if (!Intrinsics.areEqual(this._measureResult, measureResult) && measureResult != null && ((((map = this.oldAlignmentLines) != null && !map.isEmpty()) || !measureResult.getAlignmentLines().isEmpty()) && !Intrinsics.areEqual(measureResult.getAlignmentLines(), this.oldAlignmentLines))) {
            getAlignmentLinesOwner().getAlignmentLines().onAlignmentsChanged();
            Map linkedHashMap = this.oldAlignmentLines;
            if (linkedHashMap == null) {
                linkedHashMap = new LinkedHashMap();
                this.oldAlignmentLines = linkedHashMap;
            }
            linkedHashMap.clear();
            linkedHashMap.putAll(measureResult.getAlignmentLines());
        }
        this._measureResult = measureResult;
    }

    protected final Map getCachedAlignmentLinesMap() {
        return this.cachedAlignmentLinesMap;
    }

    public final int getCachedAlignmentLine$ui_release(AlignmentLine alignmentLine) {
        Integer num = (Integer) this.cachedAlignmentLinesMap.get(alignmentLine);
        if (num != null) {
            return num.intValue();
        }
        return Integer.MIN_VALUE;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public void replace$ui_release() {
        mo1744placeAtf8xVGno(mo1842getPositionnOccac(), 0.0f, (Function1) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.compose.ui.layout.Placeable
    /* JADX INFO: renamed from: placeAt-f8xVGno */
    public final void mo1744placeAtf8xVGno(long j, float f, Function1 function1) {
        m1845placeSelfgyyYBs(j);
        if (isShallowPlacing$ui_release()) {
            return;
        }
        placeChildren();
    }

    /* JADX INFO: renamed from: placeSelf--gyyYBs, reason: not valid java name */
    private final void m1845placeSelfgyyYBs(long j) {
        if (!IntOffset.m2456equalsimpl0(mo1842getPositionnOccac(), j)) {
            m1849setPositiongyyYBs(j);
            LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate$ui_release = getLayoutNode().getLayoutDelegate$ui_release().getLookaheadPassDelegate$ui_release();
            if (lookaheadPassDelegate$ui_release != null) {
                lookaheadPassDelegate$ui_release.notifyChildrenUsingLookaheadCoordinatesWhilePlacing();
            }
            invalidateAlignmentLinesFromPositionChange(this.coordinator);
        }
        if (isPlacingForAlignment$ui_release()) {
            return;
        }
        captureRulers$ui_release(getMeasureResult$ui_release());
    }

    /* JADX INFO: renamed from: placeSelfApparentToRealOffset--gyyYBs$ui_release, reason: not valid java name */
    public final void m1847placeSelfApparentToRealOffsetgyyYBs$ui_release(long j) {
        m1845placeSelfgyyYBs(IntOffset.m2461plusqkQi6aY(j, m1759getApparentToRealOffsetnOccac()));
    }

    protected void placeChildren() {
        getMeasureResult$ui_release().placeChildren();
    }

    @Override // androidx.compose.ui.layout.Placeable, androidx.compose.ui.layout.IntrinsicMeasurable
    public Object getParentData() {
        return this.coordinator.getParentData();
    }

    /* JADX INFO: renamed from: positionIn-iSbpLlY$ui_release, reason: not valid java name */
    public final long m1848positionIniSbpLlY$ui_release(LookaheadDelegate lookaheadDelegate, boolean z) {
        long jM2464getZeronOccac = IntOffset.Companion.m2464getZeronOccac();
        LookaheadDelegate lookaheadDelegate2 = this;
        while (!Intrinsics.areEqual(lookaheadDelegate2, lookaheadDelegate)) {
            if (!lookaheadDelegate2.isPlacedUnderMotionFrameOfReference() || !z) {
                jM2464getZeronOccac = IntOffset.m2461plusqkQi6aY(jM2464getZeronOccac, lookaheadDelegate2.mo1842getPositionnOccac());
            }
            NodeCoordinator wrappedBy$ui_release = lookaheadDelegate2.coordinator.getWrappedBy$ui_release();
            Intrinsics.checkNotNull(wrappedBy$ui_release);
            lookaheadDelegate2 = wrappedBy$ui_release.getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate2);
        }
        return jM2464getZeronOccac;
    }
}
