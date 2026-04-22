package androidx.compose.ui.node;

import android.view.View;
import androidx.compose.ui.internal.InlineClassHelperKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DelegatableNode.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class DelegatableNode_androidKt {
    public static final View requireView(DelegatableNode delegatableNode) {
        if (!delegatableNode.getNode().isAttached()) {
            InlineClassHelperKt.throwIllegalStateException("Cannot get View because the Modifier node is not currently attached.");
        }
        Object objRequireOwner = LayoutNodeKt.requireOwner(DelegatableNodeKt.requireLayoutNode(delegatableNode));
        Intrinsics.checkNotNull(objRequireOwner, "null cannot be cast to non-null type android.view.View");
        return (View) objRequireOwner;
    }
}
