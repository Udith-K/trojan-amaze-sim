package androidx.compose.foundation.relocation;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: ScrollIntoViewRequester.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class ScrollIntoView__ScrollIntoViewRequesterKt {
    public static /* synthetic */ Object scrollIntoView$default(DelegatableNode delegatableNode, Rect rect, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            rect = null;
        }
        return ScrollIntoView.scrollIntoView(delegatableNode, rect, continuation);
    }

    public static final Object scrollIntoView(DelegatableNode delegatableNode, final Rect rect, Continuation continuation) {
        Object objBringChildIntoView;
        if (!delegatableNode.getNode().isAttached()) {
            return Unit.INSTANCE;
        }
        final LayoutCoordinates layoutCoordinatesRequireLayoutCoordinates = DelegatableNodeKt.requireLayoutCoordinates(delegatableNode);
        BringIntoViewParent bringIntoViewParentFindBringIntoViewParent = BringIntoViewRequesterKt.findBringIntoViewParent(delegatableNode);
        return (bringIntoViewParentFindBringIntoViewParent != null && (objBringChildIntoView = bringIntoViewParentFindBringIntoViewParent.bringChildIntoView(layoutCoordinatesRequireLayoutCoordinates, new Function0() { // from class: androidx.compose.foundation.relocation.ScrollIntoView__ScrollIntoViewRequesterKt.scrollIntoView.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Rect invoke() {
                Rect rect2 = rect;
                if (rect2 != null) {
                    return rect2;
                }
                LayoutCoordinates layoutCoordinates = layoutCoordinatesRequireLayoutCoordinates;
                if (!layoutCoordinates.isAttached()) {
                    layoutCoordinates = null;
                }
                if (layoutCoordinates != null) {
                    return SizeKt.m1204toRectuvyYCjk(IntSizeKt.m2483toSizeozmzZPI(layoutCoordinates.mo1745getSizeYbymL2g()));
                }
                return null;
            }
        }, continuation)) == IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? objBringChildIntoView : Unit.INSTANCE;
    }
}
