package androidx.compose.ui.node;

import androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.PaintingStyle;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.ApproachMeasureScopeImpl;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LayoutModifierNodeCoordinator.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LayoutModifierNodeCoordinator extends NodeCoordinator {
    public static final Companion Companion = new Companion(null);
    private static final Paint modifierBoundsPaint;
    private ApproachMeasureScopeImpl approachMeasureScope;
    private LayoutModifierNode layoutModifierNode;
    private Constraints lookaheadConstraints;
    private LookaheadDelegate lookaheadDelegate;

    public LayoutModifierNodeCoordinator(LayoutNode layoutNode, LayoutModifierNode layoutModifierNode) {
        super(layoutNode);
        this.layoutModifierNode = layoutModifierNode;
        ApproachMeasureScopeImpl approachMeasureScopeImpl = null;
        this.lookaheadDelegate = layoutNode.getLookaheadRoot$ui_release() != null ? new LookaheadDelegateForLayoutModifierNode() : null;
        if ((layoutModifierNode.getNode().getKindSet$ui_release() & NodeKind.m1895constructorimpl(512)) != 0) {
            Intrinsics.checkNotNull(layoutModifierNode, "null cannot be cast to non-null type androidx.compose.ui.layout.ApproachLayoutModifierNode");
            ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(layoutModifierNode);
            approachMeasureScopeImpl = new ApproachMeasureScopeImpl(this, null);
        }
        this.approachMeasureScope = approachMeasureScopeImpl;
    }

    public final LayoutModifierNode getLayoutModifierNode() {
        return this.layoutModifierNode;
    }

    public final void setLayoutModifierNode$ui_release(LayoutModifierNode layoutModifierNode) {
        if (!Intrinsics.areEqual(layoutModifierNode, this.layoutModifierNode)) {
            Modifier.Node node = layoutModifierNode.getNode();
            if ((node.getKindSet$ui_release() & NodeKind.m1895constructorimpl(512)) != 0) {
                Intrinsics.checkNotNull(layoutModifierNode, "null cannot be cast to non-null type androidx.compose.ui.layout.ApproachLayoutModifierNode");
                ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(layoutModifierNode);
                ApproachMeasureScopeImpl approachMeasureScopeImpl = this.approachMeasureScope;
                if (approachMeasureScopeImpl != null) {
                    ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(layoutModifierNode);
                    approachMeasureScopeImpl.setApproachNode(null);
                } else {
                    ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(layoutModifierNode);
                    approachMeasureScopeImpl = new ApproachMeasureScopeImpl(this, null);
                }
                this.approachMeasureScope = approachMeasureScopeImpl;
            } else {
                this.approachMeasureScope = null;
            }
        }
        this.layoutModifierNode = layoutModifierNode;
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public Modifier.Node getTail() {
        return this.layoutModifierNode.getNode();
    }

    public final NodeCoordinator getWrappedNonNull() {
        NodeCoordinator wrapped$ui_release = getWrapped$ui_release();
        Intrinsics.checkNotNull(wrapped$ui_release);
        return wrapped$ui_release;
    }

    /* JADX INFO: renamed from: setLookaheadConstraints-_Sx5XlM$ui_release, reason: not valid java name */
    public final void m1804setLookaheadConstraints_Sx5XlM$ui_release(Constraints constraints) {
        this.lookaheadConstraints = constraints;
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public LookaheadDelegate getLookaheadDelegate() {
        return this.lookaheadDelegate;
    }

    protected void setLookaheadDelegate(LookaheadDelegate lookaheadDelegate) {
        this.lookaheadDelegate = lookaheadDelegate;
    }

    /* JADX INFO: compiled from: LayoutModifierNodeCoordinator.kt */
    private final class LookaheadDelegateForLayoutModifierNode extends LookaheadDelegate {
        public LookaheadDelegateForLayoutModifierNode() {
            super(LayoutModifierNodeCoordinator.this);
        }

        @Override // androidx.compose.ui.layout.Measurable
        /* JADX INFO: renamed from: measure-BRTryo0 */
        public Placeable mo1743measureBRTryo0(long j) {
            LayoutModifierNodeCoordinator layoutModifierNodeCoordinator = LayoutModifierNodeCoordinator.this;
            m1764setMeasurementConstraintsBRTryo0(j);
            layoutModifierNodeCoordinator.m1804setLookaheadConstraints_Sx5XlM$ui_release(Constraints.m2378boximpl(j));
            LayoutModifierNode layoutModifierNode = layoutModifierNodeCoordinator.getLayoutModifierNode();
            LookaheadDelegate lookaheadDelegate = layoutModifierNodeCoordinator.getWrappedNonNull().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            set_measureResult(layoutModifierNode.mo36measure3p2s80s(this, lookaheadDelegate, j));
            return this;
        }

        @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
        public int calculateAlignmentLine(AlignmentLine alignmentLine) {
            int iCalculateAlignmentAndPlaceChildAsNeeded = LayoutModifierNodeCoordinatorKt.calculateAlignmentAndPlaceChildAsNeeded(this, alignmentLine);
            getCachedAlignmentLinesMap().put(alignmentLine, Integer.valueOf(iCalculateAlignmentAndPlaceChildAsNeeded));
            return iCalculateAlignmentAndPlaceChildAsNeeded;
        }

        @Override // androidx.compose.ui.node.LookaheadDelegate, androidx.compose.ui.layout.IntrinsicMeasurable
        public int minIntrinsicWidth(int i) {
            LayoutModifierNode layoutModifierNode = LayoutModifierNodeCoordinator.this.getLayoutModifierNode();
            LookaheadDelegate lookaheadDelegate = LayoutModifierNodeCoordinator.this.getWrappedNonNull().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            return layoutModifierNode.minIntrinsicWidth(this, lookaheadDelegate, i);
        }

        @Override // androidx.compose.ui.node.LookaheadDelegate, androidx.compose.ui.layout.IntrinsicMeasurable
        public int maxIntrinsicWidth(int i) {
            LayoutModifierNode layoutModifierNode = LayoutModifierNodeCoordinator.this.getLayoutModifierNode();
            LookaheadDelegate lookaheadDelegate = LayoutModifierNodeCoordinator.this.getWrappedNonNull().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            return layoutModifierNode.maxIntrinsicWidth(this, lookaheadDelegate, i);
        }

        @Override // androidx.compose.ui.node.LookaheadDelegate, androidx.compose.ui.layout.IntrinsicMeasurable
        public int minIntrinsicHeight(int i) {
            LayoutModifierNode layoutModifierNode = LayoutModifierNodeCoordinator.this.getLayoutModifierNode();
            LookaheadDelegate lookaheadDelegate = LayoutModifierNodeCoordinator.this.getWrappedNonNull().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            return layoutModifierNode.minIntrinsicHeight(this, lookaheadDelegate, i);
        }

        @Override // androidx.compose.ui.node.LookaheadDelegate, androidx.compose.ui.layout.IntrinsicMeasurable
        public int maxIntrinsicHeight(int i) {
            LayoutModifierNode layoutModifierNode = LayoutModifierNodeCoordinator.this.getLayoutModifierNode();
            LookaheadDelegate lookaheadDelegate = LayoutModifierNodeCoordinator.this.getWrappedNonNull().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            return layoutModifierNode.maxIntrinsicHeight(this, lookaheadDelegate, i);
        }
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public void ensureLookaheadDelegateCreated() {
        if (getLookaheadDelegate() == null) {
            setLookaheadDelegate(new LookaheadDelegateForLayoutModifierNode());
        }
    }

    @Override // androidx.compose.ui.layout.Measurable
    /* JADX INFO: renamed from: measure-BRTryo0 */
    public Placeable mo1743measureBRTryo0(long j) {
        if (getForceMeasureWithLookaheadConstraints$ui_release()) {
            Constraints constraints = this.lookaheadConstraints;
            if (constraints == null) {
                throw new IllegalArgumentException("Lookahead constraints cannot be null in approach pass.");
            }
            j = constraints.m2395unboximpl();
        }
        m1764setMeasurementConstraintsBRTryo0(j);
        ApproachMeasureScopeImpl approachMeasureScopeImpl = this.approachMeasureScope;
        if (approachMeasureScopeImpl == null) {
            setMeasureResult$ui_release(getLayoutModifierNode().mo36measure3p2s80s(this, getWrappedNonNull(), j));
            onMeasured();
            return this;
        }
        approachMeasureScopeImpl.getApproachNode();
        approachMeasureScopeImpl.m1724getLookaheadSizeYbymL2g();
        throw null;
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int minIntrinsicWidth(int i) {
        ApproachMeasureScopeImpl approachMeasureScopeImpl = this.approachMeasureScope;
        if (approachMeasureScopeImpl != null) {
            approachMeasureScopeImpl.getApproachNode();
            getWrappedNonNull();
            throw null;
        }
        return this.layoutModifierNode.minIntrinsicWidth(this, getWrappedNonNull(), i);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int maxIntrinsicWidth(int i) {
        ApproachMeasureScopeImpl approachMeasureScopeImpl = this.approachMeasureScope;
        if (approachMeasureScopeImpl != null) {
            approachMeasureScopeImpl.getApproachNode();
            getWrappedNonNull();
            throw null;
        }
        return this.layoutModifierNode.maxIntrinsicWidth(this, getWrappedNonNull(), i);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int minIntrinsicHeight(int i) {
        ApproachMeasureScopeImpl approachMeasureScopeImpl = this.approachMeasureScope;
        if (approachMeasureScopeImpl != null) {
            approachMeasureScopeImpl.getApproachNode();
            getWrappedNonNull();
            throw null;
        }
        return this.layoutModifierNode.minIntrinsicHeight(this, getWrappedNonNull(), i);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int maxIntrinsicHeight(int i) {
        ApproachMeasureScopeImpl approachMeasureScopeImpl = this.approachMeasureScope;
        if (approachMeasureScopeImpl != null) {
            approachMeasureScopeImpl.getApproachNode();
            getWrappedNonNull();
            throw null;
        }
        return this.layoutModifierNode.maxIntrinsicHeight(this, getWrappedNonNull(), i);
    }

    @Override // androidx.compose.ui.node.NodeCoordinator, androidx.compose.ui.layout.Placeable
    /* JADX INFO: renamed from: placeAt-f8xVGno */
    protected void mo1762placeAtf8xVGno(long j, float f, GraphicsLayer graphicsLayer) {
        super.mo1762placeAtf8xVGno(j, f, graphicsLayer);
        onAfterPlaceAt();
    }

    @Override // androidx.compose.ui.node.NodeCoordinator, androidx.compose.ui.layout.Placeable
    /* JADX INFO: renamed from: placeAt-f8xVGno */
    protected void mo1744placeAtf8xVGno(long j, float f, Function1 function1) {
        super.mo1744placeAtf8xVGno(j, f, function1);
        onAfterPlaceAt();
    }

    private final void onAfterPlaceAt() {
        if (isShallowPlacing$ui_release()) {
            return;
        }
        onPlaced();
        ApproachMeasureScopeImpl approachMeasureScopeImpl = this.approachMeasureScope;
        if (approachMeasureScopeImpl != null) {
            approachMeasureScopeImpl.getApproachNode();
            getPlacementScope();
            LookaheadDelegate lookaheadDelegate = getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            lookaheadDelegate.getLookaheadLayoutCoordinates();
            throw null;
        }
        getMeasureResult$ui_release().placeChildren();
        getWrappedNonNull().setForcePlaceWithLookaheadOffset$ui_release(false);
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public int calculateAlignmentLine(AlignmentLine alignmentLine) {
        LookaheadDelegate lookaheadDelegate = getLookaheadDelegate();
        return lookaheadDelegate != null ? lookaheadDelegate.getCachedAlignmentLine$ui_release(alignmentLine) : LayoutModifierNodeCoordinatorKt.calculateAlignmentAndPlaceChildAsNeeded(this, alignmentLine);
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public void performDraw(Canvas canvas, GraphicsLayer graphicsLayer) {
        getWrappedNonNull().draw(canvas, graphicsLayer);
        if (LayoutNodeKt.requireOwner(getLayoutNode()).getShowLayoutBounds()) {
            drawBorder(canvas, modifierBoundsPaint);
        }
    }

    /* JADX INFO: compiled from: LayoutModifierNodeCoordinator.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        Paint Paint = AndroidPaint_androidKt.Paint();
        Paint.mo1228setColor8_81llA(Color.Companion.m1306getBlue0d7_KjU());
        Paint.setStrokeWidth(1.0f);
        Paint.mo1232setStylek9PVt8s(PaintingStyle.Companion.m1382getStrokeTiuSbCo());
        modifierBoundsPaint = Paint;
    }
}
