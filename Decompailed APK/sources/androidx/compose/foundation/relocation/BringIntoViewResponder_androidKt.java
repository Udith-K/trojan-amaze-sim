package androidx.compose.foundation.relocation;

import android.view.View;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNode_androidKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: BringIntoViewResponder.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class BringIntoViewResponder_androidKt {
    public static final BringIntoViewParent defaultBringIntoViewParent(final DelegatableNode delegatableNode) {
        return new BringIntoViewParent() { // from class: androidx.compose.foundation.relocation.BringIntoViewResponder_androidKt.defaultBringIntoViewParent.1
            @Override // androidx.compose.foundation.relocation.BringIntoViewParent
            public final Object bringChildIntoView(LayoutCoordinates layoutCoordinates, Function0 function0, Continuation continuation) {
                View viewRequireView = DelegatableNode_androidKt.requireView(delegatableNode);
                long jPositionInRoot = LayoutCoordinatesKt.positionInRoot(layoutCoordinates);
                Rect rect = (Rect) function0.invoke();
                Rect rectM1181translatek4lQ0M = rect != null ? rect.m1181translatek4lQ0M(jPositionInRoot) : null;
                if (rectM1181translatek4lQ0M != null) {
                    viewRequireView.requestRectangleOnScreen(BringIntoViewResponder_androidKt.toRect(rectM1181translatek4lQ0M), false);
                }
                return Unit.INSTANCE;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final android.graphics.Rect toRect(Rect rect) {
        return new android.graphics.Rect((int) rect.getLeft(), (int) rect.getTop(), (int) rect.getRight(), (int) rect.getBottom());
    }
}
