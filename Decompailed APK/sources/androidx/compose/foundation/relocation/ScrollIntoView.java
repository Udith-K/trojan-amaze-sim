package androidx.compose.foundation.relocation;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.node.DelegatableNode;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: ScrollIntoViewRequester.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ScrollIntoView {
    public static final Object scrollIntoView(DelegatableNode delegatableNode, Rect rect, Continuation continuation) {
        return ScrollIntoView__ScrollIntoViewRequesterKt.scrollIntoView(delegatableNode, rect, continuation);
    }
}
