package androidx.compose.foundation.lazy;

import androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: LazyListMeasuredItem.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LazyListMeasuredItem implements LazyListItemInfo, LazyLayoutMeasuredItem {
    private final int afterContentPadding;
    private final LazyLayoutItemAnimator animator;
    private final int beforeContentPadding;
    private final long constraints;
    private final Object contentType;
    private final int crossAxisSize;
    private final Alignment.Horizontal horizontalAlignment;
    private final int index;
    private final boolean isVertical;
    private final Object key;
    private final int lane;
    private final LayoutDirection layoutDirection;
    private int mainAxisLayoutSize;
    private final int mainAxisSizeWithSpacings;
    private int maxMainAxisOffset;
    private int minMainAxisOffset;
    private boolean nonScrollableItem;
    private int offset;
    private final int[] placeableOffsets;
    private final List placeables;
    private final boolean reverseLayout;
    private final int size;
    private final int spacing;
    private final int span;
    private final Alignment.Vertical verticalAlignment;
    private final long visualOffset;

    public /* synthetic */ LazyListMeasuredItem(int i, List list, boolean z, Alignment.Horizontal horizontal, Alignment.Vertical vertical, LayoutDirection layoutDirection, boolean z2, int i2, int i3, int i4, long j, Object obj, Object obj2, LazyLayoutItemAnimator lazyLayoutItemAnimator, long j2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, list, z, horizontal, vertical, layoutDirection, z2, i2, i3, i4, j, obj, obj2, lazyLayoutItemAnimator, j2);
    }

    private LazyListMeasuredItem(int i, List list, boolean z, Alignment.Horizontal horizontal, Alignment.Vertical vertical, LayoutDirection layoutDirection, boolean z2, int i2, int i3, int i4, long j, Object obj, Object obj2, LazyLayoutItemAnimator lazyLayoutItemAnimator, long j2) {
        this.index = i;
        this.placeables = list;
        this.isVertical = z;
        this.horizontalAlignment = horizontal;
        this.verticalAlignment = vertical;
        this.layoutDirection = layoutDirection;
        this.reverseLayout = z2;
        this.beforeContentPadding = i2;
        this.afterContentPadding = i3;
        this.spacing = i4;
        this.visualOffset = j;
        this.key = obj;
        this.contentType = obj2;
        this.animator = lazyLayoutItemAnimator;
        this.constraints = j2;
        this.span = 1;
        this.mainAxisLayoutSize = Integer.MIN_VALUE;
        int size = list.size();
        int height = 0;
        int iMax = 0;
        for (int i5 = 0; i5 < size; i5++) {
            Placeable placeable = (Placeable) list.get(i5);
            height += isVertical() ? placeable.getHeight() : placeable.getWidth();
            iMax = Math.max(iMax, !isVertical() ? placeable.getHeight() : placeable.getWidth());
        }
        this.size = height;
        this.mainAxisSizeWithSpacings = RangesKt.coerceAtLeast(getSize() + this.spacing, 0);
        this.crossAxisSize = iMax;
        this.placeableOffsets = new int[this.placeables.size() * 2];
    }

    @Override // androidx.compose.foundation.lazy.LazyListItemInfo, androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public int getIndex() {
        return this.index;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public boolean isVertical() {
        return this.isVertical;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public Object getKey() {
        return this.key;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    /* JADX INFO: renamed from: getConstraints-msEJaDk, reason: not valid java name */
    public long mo317getConstraintsmsEJaDk() {
        return this.constraints;
    }

    @Override // androidx.compose.foundation.lazy.LazyListItemInfo
    public int getOffset() {
        return this.offset;
    }

    @Override // androidx.compose.foundation.lazy.LazyListItemInfo
    public int getSize() {
        return this.size;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public int getLane() {
        return this.lane;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public int getSpan() {
        return this.span;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public int getMainAxisSizeWithSpacings() {
        return this.mainAxisSizeWithSpacings;
    }

    public final int getCrossAxisSize() {
        return this.crossAxisSize;
    }

    public boolean getNonScrollableItem() {
        return this.nonScrollableItem;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public void setNonScrollableItem(boolean z) {
        this.nonScrollableItem = z;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public int getPlaceablesCount() {
        return this.placeables.size();
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public Object getParentData(int i) {
        return ((Placeable) this.placeables.get(i)).getParentData();
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    public void position(int i, int i2, int i3, int i4) {
        position(i, i3, i4);
    }

    public final void position(int i, int i2, int i3) {
        int width;
        this.offset = i;
        this.mainAxisLayoutSize = isVertical() ? i3 : i2;
        List list = this.placeables;
        int size = list.size();
        for (int i4 = 0; i4 < size; i4++) {
            Placeable placeable = (Placeable) list.get(i4);
            int i5 = i4 * 2;
            if (isVertical()) {
                int[] iArr = this.placeableOffsets;
                Alignment.Horizontal horizontal = this.horizontalAlignment;
                if (horizontal == null) {
                    throw new IllegalArgumentException("null horizontalAlignment when isVertical == true");
                }
                iArr[i5] = horizontal.align(placeable.getWidth(), i2, this.layoutDirection);
                this.placeableOffsets[i5 + 1] = i;
                width = placeable.getHeight();
            } else {
                int[] iArr2 = this.placeableOffsets;
                iArr2[i5] = i;
                int i6 = i5 + 1;
                Alignment.Vertical vertical = this.verticalAlignment;
                if (vertical == null) {
                    throw new IllegalArgumentException("null verticalAlignment when isVertical == false");
                }
                iArr2[i6] = vertical.align(placeable.getHeight(), i3);
                width = placeable.getWidth();
            }
            i += width;
        }
        this.minMainAxisOffset = -this.beforeContentPadding;
        this.maxMainAxisOffset = this.mainAxisLayoutSize + this.afterContentPadding;
    }

    public final void updateMainAxisLayoutSize(int i) {
        this.mainAxisLayoutSize = i;
        this.maxMainAxisOffset = i + this.afterContentPadding;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem
    /* JADX INFO: renamed from: getOffset-Bjo55l4, reason: not valid java name */
    public long mo318getOffsetBjo55l4(int i) {
        int[] iArr = this.placeableOffsets;
        int i2 = i * 2;
        return IntOffsetKt.IntOffset(iArr[i2], iArr[i2 + 1]);
    }

    public final void applyScrollDelta(int i, boolean z) {
        if (getNonScrollableItem()) {
            return;
        }
        this.offset = getOffset() + i;
        int length = this.placeableOffsets.length;
        for (int i2 = 0; i2 < length; i2++) {
            if ((isVertical() && i2 % 2 == 1) || (!isVertical() && i2 % 2 == 0)) {
                int[] iArr = this.placeableOffsets;
                iArr[i2] = iArr[i2] + i;
            }
        }
        if (z) {
            int placeablesCount = getPlaceablesCount();
            for (int i3 = 0; i3 < placeablesCount; i3++) {
                LazyLayoutItemAnimation animation = this.animator.getAnimation(getKey(), i3);
                if (animation != null) {
                    long jM337getRawOffsetnOccac = animation.m337getRawOffsetnOccac();
                    int iM2457getXimpl = isVertical() ? IntOffset.m2457getXimpl(jM337getRawOffsetnOccac) : Integer.valueOf(IntOffset.m2457getXimpl(jM337getRawOffsetnOccac) + i).intValue();
                    boolean zIsVertical = isVertical();
                    int iM2458getYimpl = IntOffset.m2458getYimpl(jM337getRawOffsetnOccac);
                    if (zIsVertical) {
                        iM2458getYimpl += i;
                    }
                    animation.m340setRawOffsetgyyYBs(IntOffsetKt.IntOffset(iM2457getXimpl, iM2458getYimpl));
                }
            }
        }
    }

    public final void place(Placeable.PlacementScope placementScope, boolean z) {
        GraphicsLayer layer;
        int iM2458getYimpl;
        if (this.mainAxisLayoutSize == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("position() should be called first");
        }
        int placeablesCount = getPlaceablesCount();
        for (int i = 0; i < placeablesCount; i++) {
            Placeable placeable = (Placeable) this.placeables.get(i);
            int mainAxisSize = this.minMainAxisOffset - getMainAxisSize(placeable);
            int i2 = this.maxMainAxisOffset;
            long jMo318getOffsetBjo55l4 = mo318getOffsetBjo55l4(i);
            LazyLayoutItemAnimation animation = this.animator.getAnimation(getKey(), i);
            if (animation != null) {
                if (z) {
                    animation.m339setLookaheadOffsetgyyYBs(jMo318getOffsetBjo55l4);
                } else {
                    if (!IntOffset.m2456equalsimpl0(animation.m335getLookaheadOffsetnOccac(), LazyLayoutItemAnimation.Companion.m341getNotInitializednOccac())) {
                        jMo318getOffsetBjo55l4 = animation.m335getLookaheadOffsetnOccac();
                    }
                    long jM2461plusqkQi6aY = IntOffset.m2461plusqkQi6aY(jMo318getOffsetBjo55l4, animation.m336getPlacementDeltanOccac());
                    if ((m316getMainAxisgyyYBs(jMo318getOffsetBjo55l4) <= mainAxisSize && m316getMainAxisgyyYBs(jM2461plusqkQi6aY) <= mainAxisSize) || (m316getMainAxisgyyYBs(jMo318getOffsetBjo55l4) >= i2 && m316getMainAxisgyyYBs(jM2461plusqkQi6aY) >= i2)) {
                        animation.cancelPlacementAnimation();
                    }
                    jMo318getOffsetBjo55l4 = jM2461plusqkQi6aY;
                }
                layer = animation.getLayer();
            } else {
                layer = null;
            }
            if (this.reverseLayout) {
                int iM2457getXimpl = isVertical() ? IntOffset.m2457getXimpl(jMo318getOffsetBjo55l4) : (this.mainAxisLayoutSize - IntOffset.m2457getXimpl(jMo318getOffsetBjo55l4)) - getMainAxisSize(placeable);
                if (isVertical()) {
                    iM2458getYimpl = (this.mainAxisLayoutSize - IntOffset.m2458getYimpl(jMo318getOffsetBjo55l4)) - getMainAxisSize(placeable);
                } else {
                    iM2458getYimpl = IntOffset.m2458getYimpl(jMo318getOffsetBjo55l4);
                }
                jMo318getOffsetBjo55l4 = IntOffsetKt.IntOffset(iM2457getXimpl, iM2458getYimpl);
            }
            long jM2461plusqkQi6aY2 = IntOffset.m2461plusqkQi6aY(jMo318getOffsetBjo55l4, this.visualOffset);
            if (!z && animation != null) {
                animation.m338setFinalOffsetgyyYBs(jM2461plusqkQi6aY2);
            }
            if (isVertical()) {
                if (layer != null) {
                    Placeable.PlacementScope.m1770placeWithLayeraW9wM$default(placementScope, placeable, jM2461plusqkQi6aY2, layer, 0.0f, 4, (Object) null);
                } else {
                    Placeable.PlacementScope.m1769placeWithLayeraW9wM$default(placementScope, placeable, jM2461plusqkQi6aY2, 0.0f, (Function1) null, 6, (Object) null);
                }
            } else if (layer != null) {
                Placeable.PlacementScope.m1768placeRelativeWithLayeraW9wM$default(placementScope, placeable, jM2461plusqkQi6aY2, layer, 0.0f, 4, (Object) null);
            } else {
                Placeable.PlacementScope.m1767placeRelativeWithLayeraW9wM$default(placementScope, placeable, jM2461plusqkQi6aY2, 0.0f, (Function1) null, 6, (Object) null);
            }
        }
    }

    /* JADX INFO: renamed from: getMainAxis--gyyYBs, reason: not valid java name */
    private final int m316getMainAxisgyyYBs(long j) {
        return isVertical() ? IntOffset.m2458getYimpl(j) : IntOffset.m2457getXimpl(j);
    }

    private final int getMainAxisSize(Placeable placeable) {
        return isVertical() ? placeable.getHeight() : placeable.getWidth();
    }
}
