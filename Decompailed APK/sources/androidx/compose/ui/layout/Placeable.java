package androidx.compose.ui.layout;

import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.layout.Measured;
import androidx.compose.ui.node.MotionReferencePlacementDelegate;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Placeable.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class Placeable implements Measured {
    private int height;
    private int width;
    private long measuredSize = IntSizeKt.IntSize(0, 0);
    private long measurementConstraints = PlaceableKt.DefaultConstraints;
    private long apparentToRealOffset = IntOffset.Companion.m2464getZeronOccac();

    public /* synthetic */ Object getParentData() {
        return Measured.CC.$default$getParentData(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: placeAt-f8xVGno */
    public abstract void mo1744placeAtf8xVGno(long j, float f, Function1 function1);

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    public int getMeasuredWidth() {
        return IntSize.m2476getWidthimpl(this.measuredSize);
    }

    public int getMeasuredHeight() {
        return IntSize.m2475getHeightimpl(this.measuredSize);
    }

    /* JADX INFO: renamed from: getMeasuredSize-YbymL2g, reason: not valid java name */
    protected final long m1760getMeasuredSizeYbymL2g() {
        return this.measuredSize;
    }

    /* JADX INFO: renamed from: setMeasuredSize-ozmzZPI, reason: not valid java name */
    protected final void m1763setMeasuredSizeozmzZPI(long j) {
        if (IntSize.m2474equalsimpl0(this.measuredSize, j)) {
            return;
        }
        this.measuredSize = j;
        onMeasuredSizeChanged();
    }

    private final void onMeasuredSizeChanged() {
        this.width = RangesKt.coerceIn(IntSize.m2476getWidthimpl(this.measuredSize), Constraints.m2391getMinWidthimpl(this.measurementConstraints), Constraints.m2389getMaxWidthimpl(this.measurementConstraints));
        this.height = RangesKt.coerceIn(IntSize.m2475getHeightimpl(this.measuredSize), Constraints.m2390getMinHeightimpl(this.measurementConstraints), Constraints.m2388getMaxHeightimpl(this.measurementConstraints));
        this.apparentToRealOffset = IntOffsetKt.IntOffset((this.width - IntSize.m2476getWidthimpl(this.measuredSize)) / 2, (this.height - IntSize.m2475getHeightimpl(this.measuredSize)) / 2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: placeAt-f8xVGno, reason: not valid java name */
    public void mo1762placeAtf8xVGno(long j, float f, GraphicsLayer graphicsLayer) {
        mo1744placeAtf8xVGno(j, f, (Function1) null);
    }

    /* JADX INFO: renamed from: getMeasurementConstraints-msEJaDk, reason: not valid java name */
    protected final long m1761getMeasurementConstraintsmsEJaDk() {
        return this.measurementConstraints;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: setMeasurementConstraints-BRTryo0, reason: not valid java name */
    public final void m1764setMeasurementConstraintsBRTryo0(long j) {
        if (Constraints.m2383equalsimpl0(this.measurementConstraints, j)) {
            return;
        }
        this.measurementConstraints = j;
        onMeasuredSizeChanged();
    }

    /* JADX INFO: renamed from: getApparentToRealOffset-nOcc-ac, reason: not valid java name */
    protected final long m1759getApparentToRealOffsetnOccac() {
        return this.apparentToRealOffset;
    }

    /* JADX INFO: compiled from: Placeable.kt */
    public static abstract class PlacementScope {
        private boolean motionFrameOfReferencePlacement;

        /* JADX INFO: Access modifiers changed from: protected */
        public abstract LayoutDirection getParentLayoutDirection();

        /* JADX INFO: Access modifiers changed from: protected */
        public abstract int getParentWidth();

        /* JADX INFO: renamed from: placeRelative-70tqf50$default, reason: not valid java name */
        public static /* synthetic */ void m1766placeRelative70tqf50$default(PlacementScope placementScope, Placeable placeable, long j, float f, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: placeRelative-70tqf50");
            }
            if ((i & 2) != 0) {
                f = 0.0f;
            }
            placementScope.m1772placeRelative70tqf50(placeable, j, f);
        }

        public static /* synthetic */ void placeRelative$default(PlacementScope placementScope, Placeable placeable, int i, int i2, float f, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: placeRelative");
            }
            if ((i3 & 4) != 0) {
                f = 0.0f;
            }
            placementScope.placeRelative(placeable, i, i2, f);
        }

        public final void placeRelative(Placeable placeable, int i, int i2, float f) {
            long jIntOffset = IntOffsetKt.IntOffset(i, i2);
            if (getParentLayoutDirection() == LayoutDirection.Ltr || getParentWidth() == 0) {
                handleMotionFrameOfReferencePlacement(placeable);
                placeable.mo1744placeAtf8xVGno(IntOffset.m2461plusqkQi6aY(jIntOffset, placeable.apparentToRealOffset), f, (Function1) null);
            } else {
                long jIntOffset2 = IntOffsetKt.IntOffset((getParentWidth() - placeable.getWidth()) - IntOffset.m2457getXimpl(jIntOffset), IntOffset.m2458getYimpl(jIntOffset));
                handleMotionFrameOfReferencePlacement(placeable);
                placeable.mo1744placeAtf8xVGno(IntOffset.m2461plusqkQi6aY(jIntOffset2, placeable.apparentToRealOffset), f, (Function1) null);
            }
        }

        public static /* synthetic */ void place$default(PlacementScope placementScope, Placeable placeable, int i, int i2, float f, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: place");
            }
            if ((i3 & 4) != 0) {
                f = 0.0f;
            }
            placementScope.place(placeable, i, i2, f);
        }

        public final void place(Placeable placeable, int i, int i2, float f) {
            long jIntOffset = IntOffsetKt.IntOffset(i, i2);
            handleMotionFrameOfReferencePlacement(placeable);
            placeable.mo1744placeAtf8xVGno(IntOffset.m2461plusqkQi6aY(jIntOffset, placeable.apparentToRealOffset), f, (Function1) null);
        }

        /* JADX INFO: renamed from: place-70tqf50$default, reason: not valid java name */
        public static /* synthetic */ void m1765place70tqf50$default(PlacementScope placementScope, Placeable placeable, long j, float f, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: place-70tqf50");
            }
            if ((i & 2) != 0) {
                f = 0.0f;
            }
            placementScope.m1771place70tqf50(placeable, j, f);
        }

        /* JADX INFO: renamed from: placeRelativeWithLayer-aW-9-wM$default, reason: not valid java name */
        public static /* synthetic */ void m1767placeRelativeWithLayeraW9wM$default(PlacementScope placementScope, Placeable placeable, long j, float f, Function1 function1, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: placeRelativeWithLayer-aW-9-wM");
            }
            if ((i & 2) != 0) {
                f = 0.0f;
            }
            float f2 = f;
            if ((i & 4) != 0) {
                function1 = PlaceableKt.DefaultLayerBlock;
            }
            placementScope.m1773placeRelativeWithLayeraW9wM(placeable, j, f2, function1);
        }

        public static /* synthetic */ void placeRelativeWithLayer$default(PlacementScope placementScope, Placeable placeable, int i, int i2, float f, Function1 function1, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: placeRelativeWithLayer");
            }
            if ((i3 & 4) != 0) {
                f = 0.0f;
            }
            float f2 = f;
            if ((i3 & 8) != 0) {
                function1 = PlaceableKt.DefaultLayerBlock;
            }
            placementScope.placeRelativeWithLayer(placeable, i, i2, f2, function1);
        }

        public final void placeRelativeWithLayer(Placeable placeable, int i, int i2, float f, Function1 function1) {
            long jIntOffset = IntOffsetKt.IntOffset(i, i2);
            if (getParentLayoutDirection() == LayoutDirection.Ltr || getParentWidth() == 0) {
                handleMotionFrameOfReferencePlacement(placeable);
                placeable.mo1744placeAtf8xVGno(IntOffset.m2461plusqkQi6aY(jIntOffset, placeable.apparentToRealOffset), f, function1);
            } else {
                long jIntOffset2 = IntOffsetKt.IntOffset((getParentWidth() - placeable.getWidth()) - IntOffset.m2457getXimpl(jIntOffset), IntOffset.m2458getYimpl(jIntOffset));
                handleMotionFrameOfReferencePlacement(placeable);
                placeable.mo1744placeAtf8xVGno(IntOffset.m2461plusqkQi6aY(jIntOffset2, placeable.apparentToRealOffset), f, function1);
            }
        }

        public static /* synthetic */ void placeWithLayer$default(PlacementScope placementScope, Placeable placeable, int i, int i2, float f, Function1 function1, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: placeWithLayer");
            }
            if ((i3 & 4) != 0) {
                f = 0.0f;
            }
            float f2 = f;
            if ((i3 & 8) != 0) {
                function1 = PlaceableKt.DefaultLayerBlock;
            }
            placementScope.placeWithLayer(placeable, i, i2, f2, function1);
        }

        public final void placeWithLayer(Placeable placeable, int i, int i2, float f, Function1 function1) {
            long jIntOffset = IntOffsetKt.IntOffset(i, i2);
            handleMotionFrameOfReferencePlacement(placeable);
            placeable.mo1744placeAtf8xVGno(IntOffset.m2461plusqkQi6aY(jIntOffset, placeable.apparentToRealOffset), f, function1);
        }

        /* JADX INFO: renamed from: placeWithLayer-aW-9-wM$default, reason: not valid java name */
        public static /* synthetic */ void m1769placeWithLayeraW9wM$default(PlacementScope placementScope, Placeable placeable, long j, float f, Function1 function1, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: placeWithLayer-aW-9-wM");
            }
            if ((i & 2) != 0) {
                f = 0.0f;
            }
            float f2 = f;
            if ((i & 4) != 0) {
                function1 = PlaceableKt.DefaultLayerBlock;
            }
            placementScope.m1775placeWithLayeraW9wM(placeable, j, f2, function1);
        }

        /* JADX INFO: renamed from: placeWithLayer-aW-9-wM$default, reason: not valid java name */
        public static /* synthetic */ void m1770placeWithLayeraW9wM$default(PlacementScope placementScope, Placeable placeable, long j, GraphicsLayer graphicsLayer, float f, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: placeWithLayer-aW-9-wM");
            }
            if ((i & 4) != 0) {
                f = 0.0f;
            }
            placementScope.m1776placeWithLayeraW9wM(placeable, j, graphicsLayer, f);
        }

        /* JADX INFO: renamed from: placeRelativeWithLayer-aW-9-wM$default, reason: not valid java name */
        public static /* synthetic */ void m1768placeRelativeWithLayeraW9wM$default(PlacementScope placementScope, Placeable placeable, long j, GraphicsLayer graphicsLayer, float f, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: placeRelativeWithLayer-aW-9-wM");
            }
            if ((i & 4) != 0) {
                f = 0.0f;
            }
            placementScope.m1774placeRelativeWithLayeraW9wM(placeable, j, graphicsLayer, f);
        }

        /* JADX INFO: renamed from: placeRelative-70tqf50, reason: not valid java name */
        public final void m1772placeRelative70tqf50(Placeable placeable, long j, float f) {
            if (getParentLayoutDirection() == LayoutDirection.Ltr || getParentWidth() == 0) {
                handleMotionFrameOfReferencePlacement(placeable);
                placeable.mo1744placeAtf8xVGno(IntOffset.m2461plusqkQi6aY(j, placeable.apparentToRealOffset), f, (Function1) null);
            } else {
                long jIntOffset = IntOffsetKt.IntOffset((getParentWidth() - placeable.getWidth()) - IntOffset.m2457getXimpl(j), IntOffset.m2458getYimpl(j));
                handleMotionFrameOfReferencePlacement(placeable);
                placeable.mo1744placeAtf8xVGno(IntOffset.m2461plusqkQi6aY(jIntOffset, placeable.apparentToRealOffset), f, (Function1) null);
            }
        }

        /* JADX INFO: renamed from: placeRelativeWithLayer-aW-9-wM, reason: not valid java name */
        public final void m1773placeRelativeWithLayeraW9wM(Placeable placeable, long j, float f, Function1 function1) {
            if (getParentLayoutDirection() == LayoutDirection.Ltr || getParentWidth() == 0) {
                handleMotionFrameOfReferencePlacement(placeable);
                placeable.mo1744placeAtf8xVGno(IntOffset.m2461plusqkQi6aY(j, placeable.apparentToRealOffset), f, function1);
            } else {
                long jIntOffset = IntOffsetKt.IntOffset((getParentWidth() - placeable.getWidth()) - IntOffset.m2457getXimpl(j), IntOffset.m2458getYimpl(j));
                handleMotionFrameOfReferencePlacement(placeable);
                placeable.mo1744placeAtf8xVGno(IntOffset.m2461plusqkQi6aY(jIntOffset, placeable.apparentToRealOffset), f, function1);
            }
        }

        /* JADX INFO: renamed from: placeRelativeWithLayer-aW-9-wM, reason: not valid java name */
        public final void m1774placeRelativeWithLayeraW9wM(Placeable placeable, long j, GraphicsLayer graphicsLayer, float f) {
            if (getParentLayoutDirection() == LayoutDirection.Ltr || getParentWidth() == 0) {
                handleMotionFrameOfReferencePlacement(placeable);
                placeable.mo1762placeAtf8xVGno(IntOffset.m2461plusqkQi6aY(j, placeable.apparentToRealOffset), f, graphicsLayer);
            } else {
                long jIntOffset = IntOffsetKt.IntOffset((getParentWidth() - placeable.getWidth()) - IntOffset.m2457getXimpl(j), IntOffset.m2458getYimpl(j));
                handleMotionFrameOfReferencePlacement(placeable);
                placeable.mo1762placeAtf8xVGno(IntOffset.m2461plusqkQi6aY(jIntOffset, placeable.apparentToRealOffset), f, graphicsLayer);
            }
        }

        /* JADX INFO: renamed from: place-70tqf50, reason: not valid java name */
        public final void m1771place70tqf50(Placeable placeable, long j, float f) {
            handleMotionFrameOfReferencePlacement(placeable);
            placeable.mo1744placeAtf8xVGno(IntOffset.m2461plusqkQi6aY(j, placeable.apparentToRealOffset), f, (Function1) null);
        }

        /* JADX INFO: renamed from: placeWithLayer-aW-9-wM, reason: not valid java name */
        public final void m1775placeWithLayeraW9wM(Placeable placeable, long j, float f, Function1 function1) {
            handleMotionFrameOfReferencePlacement(placeable);
            placeable.mo1744placeAtf8xVGno(IntOffset.m2461plusqkQi6aY(j, placeable.apparentToRealOffset), f, function1);
        }

        /* JADX INFO: renamed from: placeWithLayer-aW-9-wM, reason: not valid java name */
        public final void m1776placeWithLayeraW9wM(Placeable placeable, long j, GraphicsLayer graphicsLayer, float f) {
            handleMotionFrameOfReferencePlacement(placeable);
            placeable.mo1762placeAtf8xVGno(IntOffset.m2461plusqkQi6aY(j, placeable.apparentToRealOffset), f, graphicsLayer);
        }

        public final void withMotionFrameOfReferencePlacement(Function1 function1) {
            this.motionFrameOfReferencePlacement = true;
            function1.invoke(this);
            this.motionFrameOfReferencePlacement = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public final void handleMotionFrameOfReferencePlacement(Placeable placeable) {
            if (placeable instanceof MotionReferencePlacementDelegate) {
                ((MotionReferencePlacementDelegate) placeable).setPlacedUnderMotionFrameOfReference(this.motionFrameOfReferencePlacement);
            }
        }
    }
}
