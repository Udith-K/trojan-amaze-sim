package androidx.compose.ui.input.pointer;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.pointer.PointerEventType;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNode$Companion$TraverseDescendantsAction;
import androidx.compose.ui.node.TraversableNodeKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlin.jvm.internal.Ref$ObjectRef;

/* JADX INFO: compiled from: PointerIcon.kt */
/* JADX INFO: loaded from: classes.dex */
public final class PointerHoverIconModifierNode extends Modifier.Node implements TraversableNode, PointerInputModifierNode, CompositionLocalConsumerModifierNode {
    private boolean cursorInBoundsOfNode;
    private PointerIcon icon;
    private boolean overrideDescendants;
    private final String traverseKey = "androidx.compose.ui.input.pointer.PointerHoverIcon";

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public /* synthetic */ boolean interceptOutOfBoundsChildEvents() {
        return PointerInputModifierNode.CC.$default$interceptOutOfBoundsChildEvents(this);
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public /* synthetic */ void onDensityChange() {
        onCancelPointerInput();
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public /* synthetic */ void onViewConfigurationChange() {
        onCancelPointerInput();
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public /* synthetic */ boolean sharePointerInputWithSiblings() {
        return PointerInputModifierNode.CC.$default$sharePointerInputWithSiblings(this);
    }

    public PointerHoverIconModifierNode(PointerIcon pointerIcon, boolean z) {
        this.icon = pointerIcon;
        this.overrideDescendants = z;
    }

    @Override // androidx.compose.ui.node.TraversableNode
    public String getTraverseKey() {
        return this.traverseKey;
    }

    public final void setIcon(PointerIcon pointerIcon) {
        if (Intrinsics.areEqual(this.icon, pointerIcon)) {
            return;
        }
        this.icon = pointerIcon;
        if (this.cursorInBoundsOfNode) {
            displayIconIfDescendantsDoNotHavePriority();
        }
    }

    public final boolean getOverrideDescendants() {
        return this.overrideDescendants;
    }

    public final void setOverrideDescendants(boolean z) {
        if (this.overrideDescendants != z) {
            this.overrideDescendants = z;
            if (z) {
                if (this.cursorInBoundsOfNode) {
                    displayIcon();
                }
            } else if (this.cursorInBoundsOfNode) {
                displayIconFromCurrentNodeOrDescendantsWithCursorInBounds();
            }
        }
    }

    private final PointerIconService getPointerIconService() {
        return (PointerIconService) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalPointerIconService());
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY */
    public void mo84onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pointerEventPass, long j) {
        if (pointerEventPass == PointerEventPass.Main) {
            int iM1655getType7fucELk = pointerEvent.m1655getType7fucELk();
            PointerEventType.Companion companion = PointerEventType.Companion;
            if (PointerEventType.m1660equalsimpl0(iM1655getType7fucELk, companion.m1661getEnter7fucELk())) {
                onEnter();
            } else if (PointerEventType.m1660equalsimpl0(pointerEvent.m1655getType7fucELk(), companion.m1662getExit7fucELk())) {
                onExit();
            }
        }
    }

    private final void onEnter() {
        this.cursorInBoundsOfNode = true;
        displayIconIfDescendantsDoNotHavePriority();
    }

    private final void onExit() {
        if (this.cursorInBoundsOfNode) {
            this.cursorInBoundsOfNode = false;
            if (isAttached()) {
                displayIconFromAncestorNodeWithCursorInBoundsOrDefaultIcon();
            }
        }
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public void onCancelPointerInput() {
        onExit();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        onExit();
        super.onDetach();
    }

    private final void displayIcon() {
        PointerIcon pointerIcon;
        PointerHoverIconModifierNode pointerHoverIconModifierNodeFindOverridingAncestorNode = findOverridingAncestorNode();
        if (pointerHoverIconModifierNodeFindOverridingAncestorNode == null || (pointerIcon = pointerHoverIconModifierNodeFindOverridingAncestorNode.icon) == null) {
            pointerIcon = this.icon;
        }
        PointerIconService pointerIconService = getPointerIconService();
        if (pointerIconService != null) {
            pointerIconService.setIcon(pointerIcon);
        }
    }

    private final void displayDefaultIcon() {
        PointerIconService pointerIconService = getPointerIconService();
        if (pointerIconService != null) {
            pointerIconService.setIcon(null);
        }
    }

    private final void displayIconIfDescendantsDoNotHavePriority() {
        final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
        ref$BooleanRef.element = true;
        if (!this.overrideDescendants) {
            TraversableNodeKt.traverseDescendants(this, new Function1() { // from class: androidx.compose.ui.input.pointer.PointerHoverIconModifierNode.displayIconIfDescendantsDoNotHavePriority.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final TraversableNode$Companion$TraverseDescendantsAction invoke(PointerHoverIconModifierNode pointerHoverIconModifierNode) {
                    if (pointerHoverIconModifierNode.cursorInBoundsOfNode) {
                        ref$BooleanRef.element = false;
                        return TraversableNode$Companion$TraverseDescendantsAction.CancelTraversal;
                    }
                    return TraversableNode$Companion$TraverseDescendantsAction.ContinueTraversal;
                }
            });
        }
        if (ref$BooleanRef.element) {
            displayIcon();
        }
    }

    private final PointerHoverIconModifierNode findDescendantNodeWithCursorInBounds() {
        final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        TraversableNodeKt.traverseDescendants(this, new Function1() { // from class: androidx.compose.ui.input.pointer.PointerHoverIconModifierNode.findDescendantNodeWithCursorInBounds.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final TraversableNode$Companion$TraverseDescendantsAction invoke(PointerHoverIconModifierNode pointerHoverIconModifierNode) {
                TraversableNode$Companion$TraverseDescendantsAction traversableNode$Companion$TraverseDescendantsAction = TraversableNode$Companion$TraverseDescendantsAction.ContinueTraversal;
                if (!pointerHoverIconModifierNode.cursorInBoundsOfNode) {
                    return traversableNode$Companion$TraverseDescendantsAction;
                }
                ref$ObjectRef.element = pointerHoverIconModifierNode;
                return pointerHoverIconModifierNode.getOverrideDescendants() ? TraversableNode$Companion$TraverseDescendantsAction.SkipSubtreeAndContinueTraversal : traversableNode$Companion$TraverseDescendantsAction;
            }
        });
        return (PointerHoverIconModifierNode) ref$ObjectRef.element;
    }

    private final void displayIconFromCurrentNodeOrDescendantsWithCursorInBounds() {
        PointerHoverIconModifierNode pointerHoverIconModifierNodeFindDescendantNodeWithCursorInBounds;
        if (this.cursorInBoundsOfNode) {
            if (this.overrideDescendants || (pointerHoverIconModifierNodeFindDescendantNodeWithCursorInBounds = findDescendantNodeWithCursorInBounds()) == null) {
                pointerHoverIconModifierNodeFindDescendantNodeWithCursorInBounds = this;
            }
            pointerHoverIconModifierNodeFindDescendantNodeWithCursorInBounds.displayIcon();
        }
    }

    private final PointerHoverIconModifierNode findOverridingAncestorNode() {
        final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        TraversableNodeKt.traverseAncestors(this, new Function1() { // from class: androidx.compose.ui.input.pointer.PointerHoverIconModifierNode.findOverridingAncestorNode.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(PointerHoverIconModifierNode pointerHoverIconModifierNode) {
                if (pointerHoverIconModifierNode.getOverrideDescendants() && pointerHoverIconModifierNode.cursorInBoundsOfNode) {
                    ref$ObjectRef.element = pointerHoverIconModifierNode;
                }
                return Boolean.TRUE;
            }
        });
        return (PointerHoverIconModifierNode) ref$ObjectRef.element;
    }

    private final void displayIconFromAncestorNodeWithCursorInBoundsOrDefaultIcon() {
        Unit unit;
        final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        TraversableNodeKt.traverseAncestors(this, new Function1() { // from class: androidx.compose.ui.input.pointer.PointerHoverIconModifierNode.displayIconFromAncestorNodeWithCursorInBoundsOrDefaultIcon.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(PointerHoverIconModifierNode pointerHoverIconModifierNode) {
                if (ref$ObjectRef.element == null && pointerHoverIconModifierNode.cursorInBoundsOfNode) {
                    ref$ObjectRef.element = pointerHoverIconModifierNode;
                } else if (ref$ObjectRef.element != null && pointerHoverIconModifierNode.getOverrideDescendants() && pointerHoverIconModifierNode.cursorInBoundsOfNode) {
                    ref$ObjectRef.element = pointerHoverIconModifierNode;
                }
                return Boolean.TRUE;
            }
        });
        PointerHoverIconModifierNode pointerHoverIconModifierNode = (PointerHoverIconModifierNode) ref$ObjectRef.element;
        if (pointerHoverIconModifierNode != null) {
            pointerHoverIconModifierNode.displayIcon();
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            displayDefaultIcon();
        }
    }
}
