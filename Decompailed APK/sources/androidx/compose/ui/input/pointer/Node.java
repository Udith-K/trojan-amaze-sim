package androidx.compose.ui.input.pointer;

import androidx.collection.LongSparseArray;
import androidx.collection.MutableObjectList;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.util.PointerIdArray;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.PointerInputModifierNode;
import ch.qos.logback.core.CoreConstants;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HitPathTracker.kt */
/* JADX INFO: loaded from: classes.dex */
public final class Node extends NodeParent {
    private LayoutCoordinates coordinates;
    private final Modifier.Node modifierNode;
    private PointerEvent pointerEvent;
    private boolean wasIn;
    private final PointerIdArray pointerIds = new PointerIdArray();
    private final LongSparseArray relevantChanges = new LongSparseArray(2);
    private boolean isIn = true;
    private boolean hasExited = true;

    public Node(Modifier.Node node) {
        this.modifierNode = node;
    }

    public final Modifier.Node getModifierNode() {
        return this.modifierNode;
    }

    public final PointerIdArray getPointerIds() {
        return this.pointerIds;
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public void removeInvalidPointerIdsAndChanges(long j, MutableObjectList mutableObjectList) {
        if (this.pointerIds.contains(j) && !mutableObjectList.contains(this)) {
            this.pointerIds.remove(j);
            this.relevantChanges.remove(j);
        }
        MutableVector children = getChildren();
        int size = children.getSize();
        if (size > 0) {
            Object[] content = children.getContent();
            int i = 0;
            do {
                ((Node) content[i]).removeInvalidPointerIdsAndChanges(j, mutableObjectList);
                i++;
            } while (i < size);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0238  */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v11 */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v3, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r5v2, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r5v20 */
    /* JADX WARN: Type inference failed for: r5v21, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r5v22, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v23 */
    /* JADX WARN: Type inference failed for: r5v24 */
    /* JADX WARN: Type inference failed for: r5v25 */
    /* JADX WARN: Type inference failed for: r5v26 */
    /* JADX WARN: Type inference failed for: r5v27 */
    /* JADX WARN: Type inference failed for: r5v28 */
    /* JADX WARN: Type inference failed for: r5v3, types: [androidx.compose.ui.Modifier$Node] */
    @Override // androidx.compose.ui.input.pointer.NodeParent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean buildCache(androidx.collection.LongSparseArray r37, androidx.compose.ui.layout.LayoutCoordinates r38, androidx.compose.ui.input.pointer.InternalPointerEvent r39, boolean r40) {
        /*
            Method dump skipped, instruction units count: 667
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.pointer.Node.buildCache(androidx.collection.LongSparseArray, androidx.compose.ui.layout.LayoutCoordinates, androidx.compose.ui.input.pointer.InternalPointerEvent, boolean):boolean");
    }

    private final boolean hasPositionChanged(PointerEvent pointerEvent, PointerEvent pointerEvent2) {
        if (pointerEvent == null || pointerEvent.getChanges().size() != pointerEvent2.getChanges().size()) {
            return true;
        }
        int size = pointerEvent2.getChanges().size();
        for (int i = 0; i < size; i++) {
            if (!Offset.m1156equalsimpl0(((PointerInputChange) pointerEvent.getChanges().get(i)).m1682getPositionF1C5BW0(), ((PointerInputChange) pointerEvent2.getChanges().get(i)).m1682getPositionF1C5BW0())) {
                return true;
            }
        }
        return false;
    }

    private final void clearCache() {
        this.relevantChanges.clear();
        this.coordinates = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r4v0, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r4v1, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9 */
    @Override // androidx.compose.ui.input.pointer.NodeParent
    public boolean dispatchFinalEventPass(InternalPointerEvent internalPointerEvent) {
        MutableVector children;
        int size;
        boolean z = false;
        int i = 0;
        z = false;
        if (!this.relevantChanges.isEmpty() && this.modifierNode.isAttached()) {
            PointerEvent pointerEvent = this.pointerEvent;
            Intrinsics.checkNotNull(pointerEvent);
            LayoutCoordinates layoutCoordinates = this.coordinates;
            Intrinsics.checkNotNull(layoutCoordinates);
            long jMo1745getSizeYbymL2g = layoutCoordinates.mo1745getSizeYbymL2g();
            ?? Pop = this.modifierNode;
            int iM1895constructorimpl = NodeKind.m1895constructorimpl(16);
            ?? mutableVector = 0;
            while (Pop != 0) {
                if (!(Pop instanceof PointerInputModifierNode)) {
                    if ((Pop.getKindSet$ui_release() & iM1895constructorimpl) != 0 && (Pop instanceof DelegatingNode)) {
                        Modifier.Node delegate$ui_release = ((DelegatingNode) Pop).getDelegate$ui_release();
                        int i2 = 0;
                        Pop = Pop;
                        mutableVector = mutableVector;
                        while (delegate$ui_release != null) {
                            if ((delegate$ui_release.getKindSet$ui_release() & iM1895constructorimpl) != 0) {
                                i2++;
                                mutableVector = mutableVector;
                                if (i2 == 1) {
                                    Pop = delegate$ui_release;
                                } else {
                                    if (mutableVector == 0) {
                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                    }
                                    if (Pop != 0) {
                                        mutableVector.add(Pop);
                                        Pop = 0;
                                    }
                                    mutableVector.add(delegate$ui_release);
                                }
                            }
                            delegate$ui_release = delegate$ui_release.getChild$ui_release();
                            Pop = Pop;
                            mutableVector = mutableVector;
                        }
                        if (i2 == 1) {
                        }
                    }
                } else {
                    ((PointerInputModifierNode) Pop).mo84onPointerEventH0pRuoY(pointerEvent, PointerEventPass.Final, jMo1745getSizeYbymL2g);
                }
                Pop = DelegatableNodeKt.pop(mutableVector);
            }
            if (this.modifierNode.isAttached() && (size = (children = getChildren()).getSize()) > 0) {
                Object[] content = children.getContent();
                do {
                    ((Node) content[i]).dispatchFinalEventPass(internalPointerEvent);
                    i++;
                } while (i < size);
            }
            z = true;
        }
        cleanUpHits(internalPointerEvent);
        clearCache();
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v10 */
    /* JADX WARN: Type inference failed for: r14v11 */
    /* JADX WARN: Type inference failed for: r14v12 */
    /* JADX WARN: Type inference failed for: r14v13 */
    /* JADX WARN: Type inference failed for: r14v14 */
    /* JADX WARN: Type inference failed for: r14v3, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r14v4, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r14v6 */
    /* JADX WARN: Type inference failed for: r14v7, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r14v8, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r14v9 */
    /* JADX WARN: Type inference failed for: r2v0, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v1, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v10, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v17, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v27 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v10, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v13, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v16 */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v18 */
    /* JADX WARN: Type inference failed for: r6v9 */
    /* JADX WARN: Type inference failed for: r8v9 */
    @Override // androidx.compose.ui.input.pointer.NodeParent
    public boolean dispatchMainEventPass(LongSparseArray longSparseArray, LayoutCoordinates layoutCoordinates, InternalPointerEvent internalPointerEvent, boolean z) {
        MutableVector children;
        int size;
        if (this.relevantChanges.isEmpty() || !this.modifierNode.isAttached()) {
            return false;
        }
        PointerEvent pointerEvent = this.pointerEvent;
        Intrinsics.checkNotNull(pointerEvent);
        LayoutCoordinates layoutCoordinates2 = this.coordinates;
        Intrinsics.checkNotNull(layoutCoordinates2);
        long jMo1745getSizeYbymL2g = layoutCoordinates2.mo1745getSizeYbymL2g();
        ?? Pop = this.modifierNode;
        int iM1895constructorimpl = NodeKind.m1895constructorimpl(16);
        ?? mutableVector = 0;
        while (Pop != 0) {
            if (Pop instanceof PointerInputModifierNode) {
                ((PointerInputModifierNode) Pop).mo84onPointerEventH0pRuoY(pointerEvent, PointerEventPass.Initial, jMo1745getSizeYbymL2g);
            } else if ((Pop.getKindSet$ui_release() & iM1895constructorimpl) != 0 && (Pop instanceof DelegatingNode)) {
                Modifier.Node delegate$ui_release = ((DelegatingNode) Pop).getDelegate$ui_release();
                int i = 0;
                Pop = Pop;
                mutableVector = mutableVector;
                while (delegate$ui_release != null) {
                    if ((delegate$ui_release.getKindSet$ui_release() & iM1895constructorimpl) != 0) {
                        i++;
                        mutableVector = mutableVector;
                        if (i == 1) {
                            Pop = delegate$ui_release;
                        } else {
                            if (mutableVector == 0) {
                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                            }
                            if (Pop != 0) {
                                mutableVector.add(Pop);
                                Pop = 0;
                            }
                            mutableVector.add(delegate$ui_release);
                        }
                    }
                    delegate$ui_release = delegate$ui_release.getChild$ui_release();
                    Pop = Pop;
                    mutableVector = mutableVector;
                }
                if (i == 1) {
                }
            }
            Pop = DelegatableNodeKt.pop(mutableVector);
        }
        if (this.modifierNode.isAttached() && (size = (children = getChildren()).getSize()) > 0) {
            Object[] content = children.getContent();
            int i2 = 0;
            do {
                Node node = (Node) content[i2];
                LongSparseArray longSparseArray2 = this.relevantChanges;
                LayoutCoordinates layoutCoordinates3 = this.coordinates;
                Intrinsics.checkNotNull(layoutCoordinates3);
                node.dispatchMainEventPass(longSparseArray2, layoutCoordinates3, internalPointerEvent, z);
                i2++;
            } while (i2 < size);
        }
        if (this.modifierNode.isAttached()) {
            ?? Pop2 = this.modifierNode;
            int iM1895constructorimpl2 = NodeKind.m1895constructorimpl(16);
            ?? mutableVector2 = 0;
            while (Pop2 != 0) {
                if (Pop2 instanceof PointerInputModifierNode) {
                    ((PointerInputModifierNode) Pop2).mo84onPointerEventH0pRuoY(pointerEvent, PointerEventPass.Main, jMo1745getSizeYbymL2g);
                } else if ((Pop2.getKindSet$ui_release() & iM1895constructorimpl2) != 0 && (Pop2 instanceof DelegatingNode)) {
                    Modifier.Node delegate$ui_release2 = ((DelegatingNode) Pop2).getDelegate$ui_release();
                    int i3 = 0;
                    mutableVector2 = mutableVector2;
                    Pop2 = Pop2;
                    while (delegate$ui_release2 != null) {
                        if ((delegate$ui_release2.getKindSet$ui_release() & iM1895constructorimpl2) != 0) {
                            i3++;
                            mutableVector2 = mutableVector2;
                            if (i3 == 1) {
                                Pop2 = delegate$ui_release2;
                            } else {
                                if (mutableVector2 == 0) {
                                    mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                }
                                if (Pop2 != 0) {
                                    mutableVector2.add(Pop2);
                                    Pop2 = 0;
                                }
                                mutableVector2.add(delegate$ui_release2);
                            }
                        }
                        delegate$ui_release2 = delegate$ui_release2.getChild$ui_release();
                        mutableVector2 = mutableVector2;
                        Pop2 = Pop2;
                    }
                    if (i3 == 1) {
                    }
                }
                Pop2 = DelegatableNodeKt.pop(mutableVector2);
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v2, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9 */
    /* JADX WARN: Type inference failed for: r7v4 */
    @Override // androidx.compose.ui.input.pointer.NodeParent
    public void dispatchCancel() {
        MutableVector children = getChildren();
        int size = children.getSize();
        if (size > 0) {
            Object[] content = children.getContent();
            int i = 0;
            do {
                ((Node) content[i]).dispatchCancel();
                i++;
            } while (i < size);
        }
        ?? Pop = this.modifierNode;
        int iM1895constructorimpl = NodeKind.m1895constructorimpl(16);
        ?? mutableVector = 0;
        while (Pop != 0) {
            if (!(Pop instanceof PointerInputModifierNode)) {
                if ((Pop.getKindSet$ui_release() & iM1895constructorimpl) != 0 && (Pop instanceof DelegatingNode)) {
                    Modifier.Node delegate$ui_release = ((DelegatingNode) Pop).getDelegate$ui_release();
                    int i2 = 0;
                    Pop = Pop;
                    mutableVector = mutableVector;
                    while (delegate$ui_release != null) {
                        if ((delegate$ui_release.getKindSet$ui_release() & iM1895constructorimpl) != 0) {
                            i2++;
                            mutableVector = mutableVector;
                            if (i2 == 1) {
                                Pop = delegate$ui_release;
                            } else {
                                if (mutableVector == 0) {
                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                }
                                if (Pop != 0) {
                                    mutableVector.add(Pop);
                                    Pop = 0;
                                }
                                mutableVector.add(delegate$ui_release);
                            }
                        }
                        delegate$ui_release = delegate$ui_release.getChild$ui_release();
                        Pop = Pop;
                        mutableVector = mutableVector;
                    }
                    if (i2 == 1) {
                    }
                }
            } else {
                ((PointerInputModifierNode) Pop).onCancelPointerInput();
            }
            Pop = DelegatableNodeKt.pop(mutableVector);
        }
    }

    public final void markIsIn() {
        this.isIn = true;
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public void cleanUpHits(InternalPointerEvent internalPointerEvent) {
        super.cleanUpHits(internalPointerEvent);
        PointerEvent pointerEvent = this.pointerEvent;
        if (pointerEvent == null) {
            return;
        }
        this.wasIn = this.isIn;
        List changes = pointerEvent.getChanges();
        int size = changes.size();
        for (int i = 0; i < size; i++) {
            PointerInputChange pointerInputChange = (PointerInputChange) changes.get(i);
            boolean pressed = pointerInputChange.getPressed();
            boolean zM1649activeHoverEvent0FcD4WY = internalPointerEvent.m1649activeHoverEvent0FcD4WY(pointerInputChange.m1680getIdJ3iCeTQ());
            boolean z = this.isIn;
            if ((!pressed && !zM1649activeHoverEvent0FcD4WY) || (!pressed && !z)) {
                this.pointerIds.m1719remove0FcD4WY(pointerInputChange.m1680getIdJ3iCeTQ());
            }
        }
        this.isIn = false;
        this.hasExited = PointerEventType.m1660equalsimpl0(pointerEvent.m1655getType7fucELk(), PointerEventType.Companion.m1662getExit7fucELk());
    }

    public String toString() {
        return "Node(pointerInputFilter=" + this.modifierNode + ", children=" + getChildren() + ", pointerIds=" + this.pointerIds + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
