package androidx.compose.foundation;

import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: Scroll.kt */
/* JADX INFO: loaded from: classes.dex */
final class ScrollSemanticsModifierNode extends Modifier.Node implements SemanticsModifierNode {
    private FlingBehavior flingBehavior;
    private boolean isScrollable;
    private boolean isVertical;
    private boolean reverseScrolling;
    private ScrollState state;

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public /* synthetic */ boolean getShouldClearDescendantSemantics() {
        return SemanticsModifierNode.CC.$default$getShouldClearDescendantSemantics(this);
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public /* synthetic */ boolean getShouldMergeDescendantSemantics() {
        return SemanticsModifierNode.CC.$default$getShouldMergeDescendantSemantics(this);
    }

    public final ScrollState getState() {
        return this.state;
    }

    public final void setState(ScrollState scrollState) {
        this.state = scrollState;
    }

    public final void setReverseScrolling(boolean z) {
        this.reverseScrolling = z;
    }

    public final void setFlingBehavior(FlingBehavior flingBehavior) {
        this.flingBehavior = flingBehavior;
    }

    public final void setScrollable(boolean z) {
        this.isScrollable = z;
    }

    public final void setVertical(boolean z) {
        this.isVertical = z;
    }

    public ScrollSemanticsModifierNode(ScrollState scrollState, boolean z, FlingBehavior flingBehavior, boolean z2, boolean z3) {
        this.state = scrollState;
        this.reverseScrolling = z;
        this.flingBehavior = flingBehavior;
        this.isScrollable = z2;
        this.isVertical = z3;
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setTraversalGroup(semanticsPropertyReceiver, true);
        ScrollAxisRange scrollAxisRange = new ScrollAxisRange(new Function0() { // from class: androidx.compose.foundation.ScrollSemanticsModifierNode$applySemantics$accessibilityScrollState$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                return Float.valueOf(this.this$0.getState().getValue());
            }
        }, new Function0() { // from class: androidx.compose.foundation.ScrollSemanticsModifierNode$applySemantics$accessibilityScrollState$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Float invoke() {
                return Float.valueOf(this.this$0.getState().getMaxValue());
            }
        }, this.reverseScrolling);
        if (this.isVertical) {
            SemanticsPropertiesKt.setVerticalScrollAxisRange(semanticsPropertyReceiver, scrollAxisRange);
        } else {
            SemanticsPropertiesKt.setHorizontalScrollAxisRange(semanticsPropertyReceiver, scrollAxisRange);
        }
    }
}
