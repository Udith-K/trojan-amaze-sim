package androidx.compose.ui.node;

import androidx.collection.MutableObjectFloatMap;
import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.PlaceableKt;
import androidx.compose.ui.layout.Ruler;
import androidx.compose.ui.layout.RulerScope;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.FontScaling;
import androidx.compose.ui.unit.IntOffset;
import java.lang.ref.WeakReference;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LookaheadDelegate.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class LookaheadCapablePlaceable extends Placeable implements MeasureScope, MotionReferencePlacementDelegate {
    public static final Companion Companion = new Companion(null);
    private static final Function1 onCommitAffectingRuler = new Function1() { // from class: androidx.compose.ui.node.LookaheadCapablePlaceable$Companion$onCommitAffectingRuler$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((PlaceableResult) obj);
            return Unit.INSTANCE;
        }

        public final void invoke(PlaceableResult placeableResult) {
            if (placeableResult.isValidOwnerScope()) {
                placeableResult.getPlaceable().captureRulers(placeableResult);
            }
        }
    };
    private RulerScope _rulerScope;
    private boolean isPlacedUnderMotionFrameOfReference;
    private boolean isPlacingForAlignment;
    private boolean isShallowPlacing;
    private final Placeable.PlacementScope placementScope = PlaceableKt.PlacementScope(this);
    private MutableScatterMap rulerReaders;
    private MutableObjectFloatMap rulerValues;
    private MutableObjectFloatMap rulerValuesCache;

    public abstract int calculateAlignmentLine(AlignmentLine alignmentLine);

    public abstract LookaheadCapablePlaceable getChild();

    public abstract LayoutCoordinates getCoordinates();

    public abstract boolean getHasMeasureResult();

    public abstract LayoutNode getLayoutNode();

    public abstract MeasureResult getMeasureResult$ui_release();

    public abstract LookaheadCapablePlaceable getParent();

    /* JADX INFO: renamed from: getPosition-nOcc-ac, reason: not valid java name */
    public abstract long mo1842getPositionnOccac();

    @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
    public boolean isLookingAhead() {
        return false;
    }

    @Override // androidx.compose.ui.layout.MeasureScope
    public /* synthetic */ MeasureResult layout(int i, int i2, Map map, Function1 function1) {
        return layout(i, i2, map, null, function1);
    }

    public abstract void replace$ui_release();

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: roundToPx-0680j_4 */
    public /* synthetic */ int mo204roundToPx0680j_4(float f) {
        return Density.CC.m2406$default$roundToPx0680j_4(this, f);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toDp-GaN1DYA */
    public /* synthetic */ float mo205toDpGaN1DYA(long j) {
        return FontScaling.CC.m2449$default$toDpGaN1DYA(this, j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public /* synthetic */ float mo206toDpu2uoSUM(float f) {
        return Dp.m2416constructorimpl(f / getDensity());
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public /* synthetic */ float mo207toDpu2uoSUM(int i) {
        return Dp.m2416constructorimpl(i / getDensity());
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDpSize-k-rfVVM */
    public /* synthetic */ long mo208toDpSizekrfVVM(long j) {
        return Density.CC.m2409$default$toDpSizekrfVVM(this, j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx--R2X_6o */
    public /* synthetic */ float mo209toPxR2X_6o(long j) {
        return Density.CC.m2410$default$toPxR2X_6o(this, j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx-0680j_4 */
    public /* synthetic */ float mo210toPx0680j_4(float f) {
        return Density.CC.m2411$default$toPx0680j_4(this, f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSize-XkaWNTQ */
    public /* synthetic */ long mo211toSizeXkaWNTQ(long j) {
        return Density.CC.m2412$default$toSizeXkaWNTQ(this, j);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toSp-0xMU5do */
    public /* synthetic */ long mo212toSp0xMU5do(float f) {
        return FontScaling.CC.m2450$default$toSp0xMU5do(this, f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    public /* synthetic */ long mo213toSpkPz2Gy4(float f) {
        return mo212toSp0xMU5do(mo206toDpu2uoSUM(f));
    }

    public boolean isPlacedUnderMotionFrameOfReference() {
        return this.isPlacedUnderMotionFrameOfReference;
    }

    @Override // androidx.compose.ui.node.MotionReferencePlacementDelegate
    public void setPlacedUnderMotionFrameOfReference(boolean z) {
        this.isPlacedUnderMotionFrameOfReference = z;
    }

    public final RulerScope getRulerScope() {
        RulerScope rulerScope = this._rulerScope;
        return rulerScope == null ? new RulerScope() { // from class: androidx.compose.ui.node.LookaheadCapablePlaceable$rulerScope$1
            @Override // androidx.compose.ui.unit.Density
            /* JADX INFO: renamed from: roundToPx-0680j_4 */
            public /* synthetic */ int mo204roundToPx0680j_4(float f) {
                return Density.CC.m2406$default$roundToPx0680j_4(this, f);
            }

            @Override // androidx.compose.ui.unit.FontScaling
            /* JADX INFO: renamed from: toDp-GaN1DYA */
            public /* synthetic */ float mo205toDpGaN1DYA(long j) {
                return FontScaling.CC.m2449$default$toDpGaN1DYA(this, j);
            }

            @Override // androidx.compose.ui.unit.Density
            /* JADX INFO: renamed from: toDp-u2uoSUM */
            public /* synthetic */ float mo206toDpu2uoSUM(float f) {
                return Dp.m2416constructorimpl(f / getDensity());
            }

            @Override // androidx.compose.ui.unit.Density
            /* JADX INFO: renamed from: toDp-u2uoSUM */
            public /* synthetic */ float mo207toDpu2uoSUM(int i) {
                return Dp.m2416constructorimpl(i / getDensity());
            }

            @Override // androidx.compose.ui.unit.Density
            /* JADX INFO: renamed from: toDpSize-k-rfVVM */
            public /* synthetic */ long mo208toDpSizekrfVVM(long j) {
                return Density.CC.m2409$default$toDpSizekrfVVM(this, j);
            }

            @Override // androidx.compose.ui.unit.Density
            /* JADX INFO: renamed from: toPx--R2X_6o */
            public /* synthetic */ float mo209toPxR2X_6o(long j) {
                return Density.CC.m2410$default$toPxR2X_6o(this, j);
            }

            @Override // androidx.compose.ui.unit.Density
            /* JADX INFO: renamed from: toPx-0680j_4 */
            public /* synthetic */ float mo210toPx0680j_4(float f) {
                return Density.CC.m2411$default$toPx0680j_4(this, f);
            }

            @Override // androidx.compose.ui.unit.Density
            /* JADX INFO: renamed from: toSize-XkaWNTQ */
            public /* synthetic */ long mo211toSizeXkaWNTQ(long j) {
                return Density.CC.m2412$default$toSizeXkaWNTQ(this, j);
            }

            @Override // androidx.compose.ui.unit.FontScaling
            /* JADX INFO: renamed from: toSp-0xMU5do */
            public /* synthetic */ long mo212toSp0xMU5do(float f) {
                return FontScaling.CC.m2450$default$toSp0xMU5do(this, f);
            }

            @Override // androidx.compose.ui.unit.Density
            /* JADX INFO: renamed from: toSp-kPz2Gy4 */
            public /* synthetic */ long mo213toSpkPz2Gy4(float f) {
                return mo212toSp0xMU5do(mo206toDpu2uoSUM(f));
            }

            @Override // androidx.compose.ui.unit.Density
            public float getDensity() {
                return this.this$0.getDensity();
            }

            @Override // androidx.compose.ui.unit.FontScaling
            public float getFontScale() {
                return this.this$0.getFontScale();
            }
        } : rulerScope;
    }

    @Override // androidx.compose.ui.layout.Measured
    public final int get(AlignmentLine alignmentLine) {
        int iCalculateAlignmentLine;
        if (getHasMeasureResult() && (iCalculateAlignmentLine = calculateAlignmentLine(alignmentLine)) != Integer.MIN_VALUE) {
            return iCalculateAlignmentLine + IntOffset.m2458getYimpl(m1759getApparentToRealOffsetnOccac());
        }
        return Integer.MIN_VALUE;
    }

    public final boolean isShallowPlacing$ui_release() {
        return this.isShallowPlacing;
    }

    public final void setShallowPlacing$ui_release(boolean z) {
        this.isShallowPlacing = z;
    }

    public final boolean isPlacingForAlignment$ui_release() {
        return this.isPlacingForAlignment;
    }

    public final void setPlacingForAlignment$ui_release(boolean z) {
        this.isPlacingForAlignment = z;
    }

    public final Placeable.PlacementScope getPlacementScope() {
        return this.placementScope;
    }

    protected final void invalidateAlignmentLinesFromPositionChange(NodeCoordinator nodeCoordinator) {
        AlignmentLines alignmentLines;
        NodeCoordinator wrapped$ui_release = nodeCoordinator.getWrapped$ui_release();
        if (!Intrinsics.areEqual(wrapped$ui_release != null ? wrapped$ui_release.getLayoutNode() : null, nodeCoordinator.getLayoutNode())) {
            nodeCoordinator.getAlignmentLinesOwner().getAlignmentLines().onAlignmentsChanged();
            return;
        }
        AlignmentLinesOwner parentAlignmentLinesOwner = nodeCoordinator.getAlignmentLinesOwner().getParentAlignmentLinesOwner();
        if (parentAlignmentLinesOwner == null || (alignmentLines = parentAlignmentLinesOwner.getAlignmentLines()) == null) {
            return;
        }
        alignmentLines.onAlignmentsChanged();
    }

    private final LookaheadCapablePlaceable findAncestorRulerDefiner(Ruler ruler) {
        LookaheadCapablePlaceable parent;
        LookaheadCapablePlaceable lookaheadCapablePlaceable = this;
        while (true) {
            MutableObjectFloatMap mutableObjectFloatMap = lookaheadCapablePlaceable.rulerValues;
            if ((mutableObjectFloatMap != null && mutableObjectFloatMap.contains(ruler)) || (parent = lookaheadCapablePlaceable.getParent()) == null) {
                return lookaheadCapablePlaceable;
            }
            lookaheadCapablePlaceable = parent;
        }
    }

    private final void invalidateChildrenOfDefiningRuler(Ruler ruler) {
        MutableScatterMap mutableScatterMap = findAncestorRulerDefiner(ruler).rulerReaders;
        MutableScatterSet mutableScatterSet = mutableScatterMap != null ? (MutableScatterSet) mutableScatterMap.remove(ruler) : null;
        if (mutableScatterSet != null) {
            notifyRulerValueChange(mutableScatterSet);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void captureRulers$ui_release(androidx.compose.ui.layout.MeasureResult r14) {
        /*
            r13 = this;
            if (r14 == 0) goto Lb
            androidx.compose.ui.node.PlaceableResult r0 = new androidx.compose.ui.node.PlaceableResult
            r0.<init>(r14, r13)
            r13.captureRulers(r0)
            goto L62
        Lb:
            androidx.collection.MutableScatterMap r14 = r13.rulerReaders
            if (r14 == 0) goto L54
            java.lang.Object[] r0 = r14.values
            long[] r14 = r14.metadata
            int r1 = r14.length
            int r1 = r1 + (-2)
            if (r1 < 0) goto L54
            r2 = 0
            r3 = r2
        L1a:
            r4 = r14[r3]
            long r6 = ~r4
            r8 = 7
            long r6 = r6 << r8
            long r6 = r6 & r4
            r8 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r6 = r6 & r8
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 == 0) goto L4f
            int r6 = r3 - r1
            int r6 = ~r6
            int r6 = r6 >>> 31
            r7 = 8
            int r6 = 8 - r6
            r8 = r2
        L34:
            if (r8 >= r6) goto L4d
            r9 = 255(0xff, double:1.26E-321)
            long r9 = r9 & r4
            r11 = 128(0x80, double:6.3E-322)
            int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r9 >= 0) goto L49
            int r9 = r3 << 3
            int r9 = r9 + r8
            r9 = r0[r9]
            androidx.collection.MutableScatterSet r9 = (androidx.collection.MutableScatterSet) r9
            r13.notifyRulerValueChange(r9)
        L49:
            long r4 = r4 >> r7
            int r8 = r8 + 1
            goto L34
        L4d:
            if (r6 != r7) goto L54
        L4f:
            if (r3 == r1) goto L54
            int r3 = r3 + 1
            goto L1a
        L54:
            androidx.collection.MutableScatterMap r14 = r13.rulerReaders
            if (r14 == 0) goto L5b
            r14.clear()
        L5b:
            androidx.collection.MutableObjectFloatMap r14 = r13.rulerValues
            if (r14 == 0) goto L62
            r14.clear()
        L62:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.LookaheadCapablePlaceable.captureRulers$ui_release(androidx.compose.ui.layout.MeasureResult):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00fe  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void captureRulers(final androidx.compose.ui.node.PlaceableResult r25) {
        /*
            Method dump skipped, instruction units count: 375
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.LookaheadCapablePlaceable.captureRulers(androidx.compose.ui.node.PlaceableResult):void");
    }

    /* JADX INFO: compiled from: LookaheadDelegate.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Override // androidx.compose.ui.layout.MeasureScope
    public MeasureResult layout(final int i, final int i2, final Map map, final Function1 function1, final Function1 function12) {
        if (!((i & (-16777216)) == 0 && ((-16777216) & i2) == 0)) {
            InlineClassHelperKt.throwIllegalStateException("Size(" + i + " x " + i2 + ") is out of range. Each dimension must be between 0 and 16777215.");
        }
        return new MeasureResult() { // from class: androidx.compose.ui.node.LookaheadCapablePlaceable.layout.1
            @Override // androidx.compose.ui.layout.MeasureResult
            public int getWidth() {
                return i;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public int getHeight() {
                return i2;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public Map getAlignmentLines() {
                return map;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public Function1 getRulers() {
                return function1;
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public void placeChildren() {
                function12.invoke(this.getPlacementScope());
            }
        };
    }

    private final void notifyRulerValueChange(MutableScatterSet mutableScatterSet) {
        LayoutNode layoutNode;
        Object[] objArr = mutableScatterSet.elements;
        long[] jArr = mutableScatterSet.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128 && (layoutNode = (LayoutNode) ((WeakReference) objArr[(i << 3) + i3]).get()) != null) {
                        if (isLookingAhead()) {
                            layoutNode.requestLookaheadRelayout$ui_release(false);
                        } else {
                            layoutNode.requestRelayout$ui_release(false);
                        }
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }
}
