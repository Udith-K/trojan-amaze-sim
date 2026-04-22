package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.unit.IntSize;

/* JADX INFO: compiled from: LayoutCoordinates.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class LayoutCoordinatesKt {
    public static final long positionInRoot(LayoutCoordinates layoutCoordinates) {
        return layoutCoordinates.mo1748localToRootMKHz9U(Offset.Companion.m1171getZeroF1C5BW0());
    }

    public static final long positionInWindow(LayoutCoordinates layoutCoordinates) {
        return layoutCoordinates.mo1749localToWindowMKHz9U(Offset.Companion.m1171getZeroF1C5BW0());
    }

    public static final Rect boundsInRoot(LayoutCoordinates layoutCoordinates) {
        return LayoutCoordinates.CC.localBoundingBoxOf$default(findRootCoordinates(layoutCoordinates), layoutCoordinates, false, 2, null);
    }

    public static final Rect boundsInWindow(LayoutCoordinates layoutCoordinates) {
        LayoutCoordinates layoutCoordinatesFindRootCoordinates = findRootCoordinates(layoutCoordinates);
        float fM2476getWidthimpl = IntSize.m2476getWidthimpl(layoutCoordinatesFindRootCoordinates.mo1745getSizeYbymL2g());
        float fM2475getHeightimpl = IntSize.m2475getHeightimpl(layoutCoordinatesFindRootCoordinates.mo1745getSizeYbymL2g());
        Rect rectBoundsInRoot = boundsInRoot(layoutCoordinates);
        float left = rectBoundsInRoot.getLeft();
        if (left < 0.0f) {
            left = 0.0f;
        }
        if (left > fM2476getWidthimpl) {
            left = fM2476getWidthimpl;
        }
        float top = rectBoundsInRoot.getTop();
        if (top < 0.0f) {
            top = 0.0f;
        }
        if (top > fM2475getHeightimpl) {
            top = fM2475getHeightimpl;
        }
        float right = rectBoundsInRoot.getRight();
        if (right < 0.0f) {
            right = 0.0f;
        }
        if (right <= fM2476getWidthimpl) {
            fM2476getWidthimpl = right;
        }
        float bottom = rectBoundsInRoot.getBottom();
        float f = bottom >= 0.0f ? bottom : 0.0f;
        if (f <= fM2475getHeightimpl) {
            fM2475getHeightimpl = f;
        }
        if (left == fM2476getWidthimpl || top == fM2475getHeightimpl) {
            return Rect.Companion.getZero();
        }
        long jMo1749localToWindowMKHz9U = layoutCoordinatesFindRootCoordinates.mo1749localToWindowMKHz9U(OffsetKt.Offset(left, top));
        long jMo1749localToWindowMKHz9U2 = layoutCoordinatesFindRootCoordinates.mo1749localToWindowMKHz9U(OffsetKt.Offset(fM2476getWidthimpl, top));
        long jMo1749localToWindowMKHz9U3 = layoutCoordinatesFindRootCoordinates.mo1749localToWindowMKHz9U(OffsetKt.Offset(fM2476getWidthimpl, fM2475getHeightimpl));
        long jMo1749localToWindowMKHz9U4 = layoutCoordinatesFindRootCoordinates.mo1749localToWindowMKHz9U(OffsetKt.Offset(left, fM2475getHeightimpl));
        float fM1159getXimpl = Offset.m1159getXimpl(jMo1749localToWindowMKHz9U);
        float fM1159getXimpl2 = Offset.m1159getXimpl(jMo1749localToWindowMKHz9U2);
        float fM1159getXimpl3 = Offset.m1159getXimpl(jMo1749localToWindowMKHz9U4);
        float fM1159getXimpl4 = Offset.m1159getXimpl(jMo1749localToWindowMKHz9U3);
        float fMin = Math.min(fM1159getXimpl, Math.min(fM1159getXimpl2, Math.min(fM1159getXimpl3, fM1159getXimpl4)));
        float fMax = Math.max(fM1159getXimpl, Math.max(fM1159getXimpl2, Math.max(fM1159getXimpl3, fM1159getXimpl4)));
        float fM1160getYimpl = Offset.m1160getYimpl(jMo1749localToWindowMKHz9U);
        float fM1160getYimpl2 = Offset.m1160getYimpl(jMo1749localToWindowMKHz9U2);
        float fM1160getYimpl3 = Offset.m1160getYimpl(jMo1749localToWindowMKHz9U4);
        float fM1160getYimpl4 = Offset.m1160getYimpl(jMo1749localToWindowMKHz9U3);
        return new Rect(fMin, Math.min(fM1160getYimpl, Math.min(fM1160getYimpl2, Math.min(fM1160getYimpl3, fM1160getYimpl4))), fMax, Math.max(fM1160getYimpl, Math.max(fM1160getYimpl2, Math.max(fM1160getYimpl3, fM1160getYimpl4))));
    }

    public static final Rect boundsInParent(LayoutCoordinates layoutCoordinates) {
        Rect rectLocalBoundingBoxOf$default;
        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        return (parentLayoutCoordinates == null || (rectLocalBoundingBoxOf$default = LayoutCoordinates.CC.localBoundingBoxOf$default(parentLayoutCoordinates, layoutCoordinates, false, 2, null)) == null) ? new Rect(0.0f, 0.0f, IntSize.m2476getWidthimpl(layoutCoordinates.mo1745getSizeYbymL2g()), IntSize.m2475getHeightimpl(layoutCoordinates.mo1745getSizeYbymL2g())) : rectLocalBoundingBoxOf$default;
    }

    public static final LayoutCoordinates findRootCoordinates(LayoutCoordinates layoutCoordinates) {
        LayoutCoordinates layoutCoordinates2;
        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        while (true) {
            LayoutCoordinates layoutCoordinates3 = parentLayoutCoordinates;
            layoutCoordinates2 = layoutCoordinates;
            layoutCoordinates = layoutCoordinates3;
            if (layoutCoordinates == null) {
                break;
            }
            parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        }
        NodeCoordinator nodeCoordinator = layoutCoordinates2 instanceof NodeCoordinator ? (NodeCoordinator) layoutCoordinates2 : null;
        if (nodeCoordinator == null) {
            return layoutCoordinates2;
        }
        NodeCoordinator wrappedBy$ui_release = nodeCoordinator.getWrappedBy$ui_release();
        while (true) {
            NodeCoordinator nodeCoordinator2 = wrappedBy$ui_release;
            NodeCoordinator nodeCoordinator3 = nodeCoordinator;
            nodeCoordinator = nodeCoordinator2;
            if (nodeCoordinator == null) {
                return nodeCoordinator3;
            }
            wrappedBy$ui_release = nodeCoordinator.getWrappedBy$ui_release();
        }
    }
}
