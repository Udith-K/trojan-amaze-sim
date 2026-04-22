package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NodeChain.kt */
/* JADX INFO: loaded from: classes.dex */
public final class NodeChain {
    private MutableVector buffer;
    private Differ cachedDiffer;
    private MutableVector current;
    private Modifier.Node head;
    private final InnerNodeCoordinator innerCoordinator;
    private final LayoutNode layoutNode;
    private NodeCoordinator outerCoordinator;
    private final Modifier.Node tail;

    /* JADX INFO: compiled from: NodeChain.kt */
    public interface Logger {
    }

    public NodeChain(LayoutNode layoutNode) {
        this.layoutNode = layoutNode;
        InnerNodeCoordinator innerNodeCoordinator = new InnerNodeCoordinator(layoutNode);
        this.innerCoordinator = innerNodeCoordinator;
        this.outerCoordinator = innerNodeCoordinator;
        TailModifierNode tail = innerNodeCoordinator.getTail();
        this.tail = tail;
        this.head = tail;
    }

    public static final /* synthetic */ Logger access$getLogger$p(NodeChain nodeChain) {
        nodeChain.getClass();
        return null;
    }

    public final LayoutNode getLayoutNode() {
        return this.layoutNode;
    }

    public final InnerNodeCoordinator getInnerCoordinator$ui_release() {
        return this.innerCoordinator;
    }

    public final NodeCoordinator getOuterCoordinator$ui_release() {
        return this.outerCoordinator;
    }

    public final Modifier.Node getTail$ui_release() {
        return this.tail;
    }

    public final Modifier.Node getHead$ui_release() {
        return this.head;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getAggregateChildKindSet() {
        return this.head.getAggregateChildKindSet$ui_release();
    }

    private final Modifier.Node padChain() {
        if (!(this.head != NodeChainKt.SentinelHead)) {
            InlineClassHelperKt.throwIllegalStateException("padChain called on already padded chain");
        }
        Modifier.Node node = this.head;
        node.setParent$ui_release(NodeChainKt.SentinelHead);
        NodeChainKt.SentinelHead.setChild$ui_release(node);
        return NodeChainKt.SentinelHead;
    }

    private final Modifier.Node trimChain(Modifier.Node node) {
        if (!(node == NodeChainKt.SentinelHead)) {
            InlineClassHelperKt.throwIllegalStateException("trimChain called on already trimmed chain");
        }
        Modifier.Node child$ui_release = NodeChainKt.SentinelHead.getChild$ui_release();
        if (child$ui_release == null) {
            child$ui_release = this.tail;
        }
        child$ui_release.setParent$ui_release(null);
        NodeChainKt.SentinelHead.setChild$ui_release(null);
        NodeChainKt.SentinelHead.setAggregateChildKindSet$ui_release(-1);
        NodeChainKt.SentinelHead.updateCoordinator$ui_release(null);
        if (!(child$ui_release != NodeChainKt.SentinelHead)) {
            InlineClassHelperKt.throwIllegalStateException("trimChain did not update the head");
        }
        return child$ui_release;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x005d, code lost:
    
        r5 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0068, code lost:
    
        if (r2 >= r1) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x006a, code lost:
    
        if (r7 == null) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x006c, code lost:
    
        if (r5 == null) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x006e, code lost:
    
        structuralUpdate(r2, r7, r13, r5, !r12.layoutNode.getApplyingModifierOnAttach$ui_release());
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0082, code lost:
    
        androidx.compose.ui.internal.InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("structuralUpdate requires a non-null tail");
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x008c, code lost:
    
        throw new kotlin.KotlinNothingValueException();
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x008d, code lost:
    
        androidx.compose.ui.internal.InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("expected prior modifier list to be non-empty");
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0095, code lost:
    
        throw new kotlin.KotlinNothingValueException();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void updateFrom$ui_release(androidx.compose.ui.Modifier r13) {
        /*
            Method dump skipped, instruction units count: 300
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.NodeChain.updateFrom$ui_release(androidx.compose.ui.Modifier):void");
    }

    public final void syncCoordinators() {
        NodeCoordinator layoutModifierNodeCoordinator;
        NodeCoordinator nodeCoordinator = this.innerCoordinator;
        for (Modifier.Node parent$ui_release = this.tail.getParent$ui_release(); parent$ui_release != null; parent$ui_release = parent$ui_release.getParent$ui_release()) {
            LayoutModifierNode layoutModifierNodeAsLayoutModifierNode = DelegatableNodeKt.asLayoutModifierNode(parent$ui_release);
            if (layoutModifierNodeAsLayoutModifierNode != null) {
                if (parent$ui_release.getCoordinator$ui_release() != null) {
                    NodeCoordinator coordinator$ui_release = parent$ui_release.getCoordinator$ui_release();
                    Intrinsics.checkNotNull(coordinator$ui_release, "null cannot be cast to non-null type androidx.compose.ui.node.LayoutModifierNodeCoordinator");
                    layoutModifierNodeCoordinator = (LayoutModifierNodeCoordinator) coordinator$ui_release;
                    LayoutModifierNode layoutModifierNode = layoutModifierNodeCoordinator.getLayoutModifierNode();
                    layoutModifierNodeCoordinator.setLayoutModifierNode$ui_release(layoutModifierNodeAsLayoutModifierNode);
                    if (layoutModifierNode != parent$ui_release) {
                        layoutModifierNodeCoordinator.onLayoutModifierNodeChanged();
                    }
                } else {
                    layoutModifierNodeCoordinator = new LayoutModifierNodeCoordinator(this.layoutNode, layoutModifierNodeAsLayoutModifierNode);
                    parent$ui_release.updateCoordinator$ui_release(layoutModifierNodeCoordinator);
                }
                nodeCoordinator.setWrappedBy$ui_release(layoutModifierNodeCoordinator);
                layoutModifierNodeCoordinator.setWrapped$ui_release(nodeCoordinator);
                nodeCoordinator = layoutModifierNodeCoordinator;
            } else {
                parent$ui_release.updateCoordinator$ui_release(nodeCoordinator);
            }
        }
        LayoutNode parent$ui_release2 = this.layoutNode.getParent$ui_release();
        nodeCoordinator.setWrappedBy$ui_release(parent$ui_release2 != null ? parent$ui_release2.getInnerCoordinator$ui_release() : null);
        this.outerCoordinator = nodeCoordinator;
    }

    private final void syncAggregateChildKindSet() {
        int kindSet$ui_release = 0;
        for (Modifier.Node parent$ui_release = this.tail.getParent$ui_release(); parent$ui_release != null && parent$ui_release != NodeChainKt.SentinelHead; parent$ui_release = parent$ui_release.getParent$ui_release()) {
            kindSet$ui_release |= parent$ui_release.getKindSet$ui_release();
            parent$ui_release.setAggregateChildKindSet$ui_release(kindSet$ui_release);
        }
    }

    private final Differ getDiffer(Modifier.Node node, int i, MutableVector mutableVector, MutableVector mutableVector2, boolean z) {
        Differ differ = this.cachedDiffer;
        if (differ == null) {
            Differ differ2 = new Differ(node, i, mutableVector, mutableVector2, z);
            this.cachedDiffer = differ2;
            return differ2;
        }
        differ.setNode(node);
        differ.setOffset(i);
        differ.setBefore(mutableVector);
        differ.setAfter(mutableVector2);
        differ.setShouldAttachOnInsert(z);
        return differ;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void propagateCoordinator(Modifier.Node node, NodeCoordinator nodeCoordinator) {
        for (Modifier.Node parent$ui_release = node.getParent$ui_release(); parent$ui_release != null; parent$ui_release = parent$ui_release.getParent$ui_release()) {
            if (parent$ui_release != NodeChainKt.SentinelHead) {
                if ((NodeKind.m1895constructorimpl(2) & parent$ui_release.getKindSet$ui_release()) != 0) {
                    return;
                }
                parent$ui_release.updateCoordinator$ui_release(nodeCoordinator);
            } else {
                LayoutNode parent$ui_release2 = this.layoutNode.getParent$ui_release();
                nodeCoordinator.setWrappedBy$ui_release(parent$ui_release2 != null ? parent$ui_release2.getInnerCoordinator$ui_release() : null);
                this.outerCoordinator = nodeCoordinator;
                return;
            }
        }
    }

    /* JADX INFO: compiled from: NodeChain.kt */
    private final class Differ implements DiffCallback {
        private MutableVector after;
        private MutableVector before;
        private Modifier.Node node;
        private int offset;
        private boolean shouldAttachOnInsert;

        public Differ(Modifier.Node node, int i, MutableVector mutableVector, MutableVector mutableVector2, boolean z) {
            this.node = node;
            this.offset = i;
            this.before = mutableVector;
            this.after = mutableVector2;
            this.shouldAttachOnInsert = z;
        }

        public final void setNode(Modifier.Node node) {
            this.node = node;
        }

        public final void setOffset(int i) {
            this.offset = i;
        }

        public final void setBefore(MutableVector mutableVector) {
            this.before = mutableVector;
        }

        public final void setAfter(MutableVector mutableVector) {
            this.after = mutableVector;
        }

        public final void setShouldAttachOnInsert(boolean z) {
            this.shouldAttachOnInsert = z;
        }

        @Override // androidx.compose.ui.node.DiffCallback
        public boolean areItemsTheSame(int i, int i2) {
            return NodeChainKt.actionForModifiers((Modifier.Element) this.before.getContent()[this.offset + i], (Modifier.Element) this.after.getContent()[this.offset + i2]) != 0;
        }

        @Override // androidx.compose.ui.node.DiffCallback
        public void insert(int i) {
            int i2 = this.offset + i;
            this.node = NodeChain.this.createAndInsertNodeAsChild((Modifier.Element) this.after.getContent()[i2], this.node);
            NodeChain.access$getLogger$p(NodeChain.this);
            if (this.shouldAttachOnInsert) {
                Modifier.Node child$ui_release = this.node.getChild$ui_release();
                Intrinsics.checkNotNull(child$ui_release);
                NodeCoordinator coordinator$ui_release = child$ui_release.getCoordinator$ui_release();
                Intrinsics.checkNotNull(coordinator$ui_release);
                LayoutModifierNode layoutModifierNodeAsLayoutModifierNode = DelegatableNodeKt.asLayoutModifierNode(this.node);
                if (layoutModifierNodeAsLayoutModifierNode != null) {
                    LayoutModifierNodeCoordinator layoutModifierNodeCoordinator = new LayoutModifierNodeCoordinator(NodeChain.this.getLayoutNode(), layoutModifierNodeAsLayoutModifierNode);
                    this.node.updateCoordinator$ui_release(layoutModifierNodeCoordinator);
                    NodeChain.this.propagateCoordinator(this.node, layoutModifierNodeCoordinator);
                    layoutModifierNodeCoordinator.setWrappedBy$ui_release(coordinator$ui_release.getWrappedBy$ui_release());
                    layoutModifierNodeCoordinator.setWrapped$ui_release(coordinator$ui_release);
                    coordinator$ui_release.setWrappedBy$ui_release(layoutModifierNodeCoordinator);
                } else {
                    this.node.updateCoordinator$ui_release(coordinator$ui_release);
                }
                this.node.markAsAttached$ui_release();
                this.node.runAttachLifecycle$ui_release();
                NodeKindKt.autoInvalidateInsertedNode(this.node);
                return;
            }
            this.node.setInsertedNodeAwaitingAttachForInvalidation$ui_release(true);
        }

        @Override // androidx.compose.ui.node.DiffCallback
        public void remove(int i, int i2) {
            Modifier.Node child$ui_release = this.node.getChild$ui_release();
            Intrinsics.checkNotNull(child$ui_release);
            NodeChain.access$getLogger$p(NodeChain.this);
            if ((NodeKind.m1895constructorimpl(2) & child$ui_release.getKindSet$ui_release()) != 0) {
                NodeCoordinator coordinator$ui_release = child$ui_release.getCoordinator$ui_release();
                Intrinsics.checkNotNull(coordinator$ui_release);
                NodeCoordinator wrappedBy$ui_release = coordinator$ui_release.getWrappedBy$ui_release();
                NodeCoordinator wrapped$ui_release = coordinator$ui_release.getWrapped$ui_release();
                Intrinsics.checkNotNull(wrapped$ui_release);
                if (wrappedBy$ui_release != null) {
                    wrappedBy$ui_release.setWrapped$ui_release(wrapped$ui_release);
                }
                wrapped$ui_release.setWrappedBy$ui_release(wrappedBy$ui_release);
                NodeChain.this.propagateCoordinator(this.node, wrapped$ui_release);
            }
            this.node = NodeChain.this.detachAndRemoveNode(child$ui_release);
        }

        @Override // androidx.compose.ui.node.DiffCallback
        public void same(int i, int i2) {
            Modifier.Node child$ui_release = this.node.getChild$ui_release();
            Intrinsics.checkNotNull(child$ui_release);
            this.node = child$ui_release;
            MutableVector mutableVector = this.before;
            Modifier.Element element = (Modifier.Element) mutableVector.getContent()[this.offset + i];
            MutableVector mutableVector2 = this.after;
            Modifier.Element element2 = (Modifier.Element) mutableVector2.getContent()[this.offset + i2];
            if (!Intrinsics.areEqual(element, element2)) {
                NodeChain.this.updateNode(element, element2, this.node);
                NodeChain.access$getLogger$p(NodeChain.this);
            } else {
                NodeChain.access$getLogger$p(NodeChain.this);
            }
        }
    }

    private final void structuralUpdate(int i, MutableVector mutableVector, MutableVector mutableVector2, Modifier.Node node, boolean z) {
        MyersDiffKt.executeDiff(mutableVector.getSize() - i, mutableVector2.getSize() - i, getDiffer(node, i, mutableVector, mutableVector2, z));
        syncAggregateChildKindSet();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Modifier.Node detachAndRemoveNode(Modifier.Node node) {
        if (node.isAttached()) {
            NodeKindKt.autoInvalidateRemovedNode(node);
            node.runDetachLifecycle$ui_release();
            node.markAsDetached$ui_release();
        }
        return removeNode(node);
    }

    private final Modifier.Node removeNode(Modifier.Node node) {
        Modifier.Node child$ui_release = node.getChild$ui_release();
        Modifier.Node parent$ui_release = node.getParent$ui_release();
        if (child$ui_release != null) {
            child$ui_release.setParent$ui_release(parent$ui_release);
            node.setChild$ui_release(null);
        }
        if (parent$ui_release != null) {
            parent$ui_release.setChild$ui_release(child$ui_release);
            node.setParent$ui_release(null);
        }
        Intrinsics.checkNotNull(parent$ui_release);
        return parent$ui_release;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Modifier.Node createAndInsertNodeAsChild(Modifier.Element element, Modifier.Node node) {
        Modifier.Node backwardsCompatNode;
        if (element instanceof ModifierNodeElement) {
            backwardsCompatNode = ((ModifierNodeElement) element).getNode();
            backwardsCompatNode.setKindSet$ui_release(NodeKindKt.calculateNodeKindSetFromIncludingDelegates(backwardsCompatNode));
        } else {
            backwardsCompatNode = new BackwardsCompatNode(element);
        }
        if (backwardsCompatNode.isAttached()) {
            InlineClassHelperKt.throwIllegalStateException("A ModifierNodeElement cannot return an already attached node from create() ");
        }
        backwardsCompatNode.setInsertedNodeAwaitingAttachForInvalidation$ui_release(true);
        return insertChild(backwardsCompatNode, node);
    }

    private final Modifier.Node insertChild(Modifier.Node node, Modifier.Node node2) {
        Modifier.Node child$ui_release = node2.getChild$ui_release();
        if (child$ui_release != null) {
            child$ui_release.setParent$ui_release(node);
            node.setChild$ui_release(child$ui_release);
        }
        node2.setChild$ui_release(node);
        node.setParent$ui_release(node2);
        return node;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateNode(Modifier.Element element, Modifier.Element element2, Modifier.Node node) {
        if ((element instanceof ModifierNodeElement) && (element2 instanceof ModifierNodeElement)) {
            NodeChainKt.updateUnsafe((ModifierNodeElement) element2, node);
            if (node.isAttached()) {
                NodeKindKt.autoInvalidateUpdatedNode(node);
                return;
            } else {
                node.setUpdatedNodeAwaitingAttachForInvalidation$ui_release(true);
                return;
            }
        }
        if (node instanceof BackwardsCompatNode) {
            ((BackwardsCompatNode) node).setElement(element2);
            if (node.isAttached()) {
                NodeKindKt.autoInvalidateUpdatedNode(node);
                return;
            } else {
                node.setUpdatedNodeAwaitingAttachForInvalidation$ui_release(true);
                return;
            }
        }
        throw new IllegalStateException("Unknown Modifier.Node type");
    }

    public final void markAsAttached() {
        for (Modifier.Node head$ui_release = getHead$ui_release(); head$ui_release != null; head$ui_release = head$ui_release.getChild$ui_release()) {
            head$ui_release.markAsAttached$ui_release();
        }
    }

    public final void runAttachLifecycle() {
        for (Modifier.Node head$ui_release = getHead$ui_release(); head$ui_release != null; head$ui_release = head$ui_release.getChild$ui_release()) {
            head$ui_release.runAttachLifecycle$ui_release();
            if (head$ui_release.getInsertedNodeAwaitingAttachForInvalidation$ui_release()) {
                NodeKindKt.autoInvalidateInsertedNode(head$ui_release);
            }
            if (head$ui_release.getUpdatedNodeAwaitingAttachForInvalidation$ui_release()) {
                NodeKindKt.autoInvalidateUpdatedNode(head$ui_release);
            }
            head$ui_release.setInsertedNodeAwaitingAttachForInvalidation$ui_release(false);
            head$ui_release.setUpdatedNodeAwaitingAttachForInvalidation$ui_release(false);
        }
    }

    public final void markAsDetached$ui_release() {
        for (Modifier.Node tail$ui_release = getTail$ui_release(); tail$ui_release != null; tail$ui_release = tail$ui_release.getParent$ui_release()) {
            if (tail$ui_release.isAttached()) {
                tail$ui_release.markAsDetached$ui_release();
            }
        }
    }

    public final void resetState$ui_release() {
        for (Modifier.Node tail$ui_release = getTail$ui_release(); tail$ui_release != null; tail$ui_release = tail$ui_release.getParent$ui_release()) {
            if (tail$ui_release.isAttached()) {
                tail$ui_release.reset$ui_release();
            }
        }
        runDetachLifecycle$ui_release();
        markAsDetached$ui_release();
    }

    public final void runDetachLifecycle$ui_release() {
        for (Modifier.Node tail$ui_release = getTail$ui_release(); tail$ui_release != null; tail$ui_release = tail$ui_release.getParent$ui_release()) {
            if (tail$ui_release.isAttached()) {
                tail$ui_release.runDetachLifecycle$ui_release();
            }
        }
    }

    /* JADX INFO: renamed from: has-H91voCI$ui_release, reason: not valid java name */
    public final boolean m1857hasH91voCI$ui_release(int i) {
        return (i & getAggregateChildKindSet()) != 0;
    }

    public final boolean has$ui_release(int i) {
        return (i & getAggregateChildKindSet()) != 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (this.head != this.tail) {
            Modifier.Node head$ui_release = getHead$ui_release();
            while (true) {
                if (head$ui_release == null || head$ui_release == getTail$ui_release()) {
                    break;
                }
                sb.append(String.valueOf(head$ui_release));
                if (head$ui_release.getChild$ui_release() == this.tail) {
                    sb.append("]");
                    break;
                }
                sb.append(",");
                head$ui_release = head$ui_release.getChild$ui_release();
            }
        } else {
            sb.append("]");
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
