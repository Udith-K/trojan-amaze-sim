package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import java.util.Comparator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OnPositionedDispatcher.kt */
/* JADX INFO: loaded from: classes.dex */
public final class OnPositionedDispatcher {
    private LayoutNode[] cachedNodes;
    private final MutableVector layoutNodes = new MutableVector(new LayoutNode[16], 0);
    public static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    public final boolean isNotEmpty() {
        return this.layoutNodes.isNotEmpty();
    }

    public final void onNodePositioned(LayoutNode layoutNode) {
        this.layoutNodes.add(layoutNode);
        layoutNode.setNeedsOnPositionedDispatch$ui_release(true);
    }

    public final void remove(LayoutNode layoutNode) {
        this.layoutNodes.remove(layoutNode);
    }

    public final void onRootNodePositioned(LayoutNode layoutNode) {
        this.layoutNodes.clear();
        this.layoutNodes.add(layoutNode);
        layoutNode.setNeedsOnPositionedDispatch$ui_release(true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void dispatch() {
        /*
            r4 = this;
            androidx.compose.runtime.collection.MutableVector r0 = r4.layoutNodes
            androidx.compose.ui.node.OnPositionedDispatcher$Companion$DepthComparator r1 = androidx.compose.ui.node.OnPositionedDispatcher.Companion.DepthComparator.INSTANCE
            r0.sortWith(r1)
            androidx.compose.runtime.collection.MutableVector r0 = r4.layoutNodes
            int r0 = r0.getSize()
            androidx.compose.ui.node.LayoutNode[] r1 = r4.cachedNodes
            if (r1 == 0) goto L14
            int r2 = r1.length
            if (r2 >= r0) goto L22
        L14:
            androidx.compose.runtime.collection.MutableVector r1 = r4.layoutNodes
            int r1 = r1.getSize()
            r2 = 16
            int r1 = java.lang.Math.max(r2, r1)
            androidx.compose.ui.node.LayoutNode[] r1 = new androidx.compose.ui.node.LayoutNode[r1]
        L22:
            r2 = 0
            r4.cachedNodes = r2
            r2 = 0
        L26:
            if (r2 >= r0) goto L35
            androidx.compose.runtime.collection.MutableVector r3 = r4.layoutNodes
            java.lang.Object[] r3 = r3.getContent()
            r3 = r3[r2]
            r1[r2] = r3
            int r2 = r2 + 1
            goto L26
        L35:
            androidx.compose.runtime.collection.MutableVector r2 = r4.layoutNodes
            r2.clear()
            int r0 = r0 + (-1)
        L3c:
            r2 = -1
            if (r2 >= r0) goto L50
            r2 = r1[r0]
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            boolean r3 = r2.getNeedsOnPositionedDispatch$ui_release()
            if (r3 == 0) goto L4d
            r4.dispatchHierarchy(r2)
        L4d:
            int r0 = r0 + (-1)
            goto L3c
        L50:
            r4.cachedNodes = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.OnPositionedDispatcher.dispatch():void");
    }

    private final void dispatchHierarchy(LayoutNode layoutNode) {
        layoutNode.dispatchOnPositionedCallbacks$ui_release();
        int i = 0;
        layoutNode.setNeedsOnPositionedDispatch$ui_release(false);
        MutableVector mutableVector = layoutNode.get_children$ui_release();
        int size = mutableVector.getSize();
        if (size > 0) {
            Object[] content = mutableVector.getContent();
            do {
                dispatchHierarchy((LayoutNode) content[i]);
                i++;
            } while (i < size);
        }
    }

    /* JADX INFO: compiled from: OnPositionedDispatcher.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: compiled from: OnPositionedDispatcher.kt */
        private static final class DepthComparator implements Comparator {
            public static final DepthComparator INSTANCE = new DepthComparator();

            private DepthComparator() {
            }

            @Override // java.util.Comparator
            public int compare(LayoutNode layoutNode, LayoutNode layoutNode2) {
                int iCompare = Intrinsics.compare(layoutNode2.getDepth$ui_release(), layoutNode.getDepth$ui_release());
                return iCompare != 0 ? iCompare : Intrinsics.compare(layoutNode.hashCode(), layoutNode2.hashCode());
            }
        }
    }
}
