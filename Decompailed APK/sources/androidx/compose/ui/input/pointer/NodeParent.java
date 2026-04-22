package androidx.compose.ui.input.pointer;

import androidx.collection.LongSparseArray;
import androidx.collection.MutableObjectList;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.layout.LayoutCoordinates;

/* JADX INFO: compiled from: HitPathTracker.kt */
/* JADX INFO: loaded from: classes.dex */
public class NodeParent {
    public static final int $stable = MutableVector.$stable;
    private final MutableVector children = new MutableVector(new Node[16], 0);

    public final MutableVector getChildren() {
        return this.children;
    }

    public boolean buildCache(LongSparseArray longSparseArray, LayoutCoordinates layoutCoordinates, InternalPointerEvent internalPointerEvent, boolean z) {
        MutableVector mutableVector = this.children;
        int size = mutableVector.getSize();
        if (size <= 0) {
            return false;
        }
        Object[] content = mutableVector.getContent();
        int i = 0;
        boolean z2 = false;
        do {
            z2 = ((Node) content[i]).buildCache(longSparseArray, layoutCoordinates, internalPointerEvent, z) || z2;
            i++;
        } while (i < size);
        return z2;
    }

    public boolean dispatchMainEventPass(LongSparseArray longSparseArray, LayoutCoordinates layoutCoordinates, InternalPointerEvent internalPointerEvent, boolean z) {
        MutableVector mutableVector = this.children;
        int size = mutableVector.getSize();
        if (size <= 0) {
            return false;
        }
        Object[] content = mutableVector.getContent();
        int i = 0;
        boolean z2 = false;
        do {
            z2 = ((Node) content[i]).dispatchMainEventPass(longSparseArray, layoutCoordinates, internalPointerEvent, z) || z2;
            i++;
        } while (i < size);
        return z2;
    }

    public boolean dispatchFinalEventPass(InternalPointerEvent internalPointerEvent) {
        MutableVector mutableVector = this.children;
        int size = mutableVector.getSize();
        boolean z = false;
        if (size > 0) {
            Object[] content = mutableVector.getContent();
            int i = 0;
            boolean z2 = false;
            do {
                z2 = ((Node) content[i]).dispatchFinalEventPass(internalPointerEvent) || z2;
                i++;
            } while (i < size);
            z = z2;
        }
        cleanUpHits(internalPointerEvent);
        return z;
    }

    public void dispatchCancel() {
        MutableVector mutableVector = this.children;
        int size = mutableVector.getSize();
        if (size > 0) {
            Object[] content = mutableVector.getContent();
            int i = 0;
            do {
                ((Node) content[i]).dispatchCancel();
                i++;
            } while (i < size);
        }
    }

    public final void clear() {
        this.children.clear();
    }

    public void removeInvalidPointerIdsAndChanges(long j, MutableObjectList mutableObjectList) {
        MutableVector mutableVector = this.children;
        int size = mutableVector.getSize();
        if (size > 0) {
            Object[] content = mutableVector.getContent();
            int i = 0;
            do {
                ((Node) content[i]).removeInvalidPointerIdsAndChanges(j, mutableObjectList);
                i++;
            } while (i < size);
        }
    }

    public final void removeDetachedPointerInputModifierNodes() {
        int i = 0;
        while (i < this.children.getSize()) {
            Node node = (Node) this.children.getContent()[i];
            if (!node.getModifierNode().isAttached()) {
                node.dispatchCancel();
                this.children.removeAt(i);
            } else {
                i++;
                node.removeDetachedPointerInputModifierNodes();
            }
        }
    }

    public void cleanUpHits(InternalPointerEvent internalPointerEvent) {
        int size = this.children.getSize();
        while (true) {
            size--;
            if (-1 >= size) {
                return;
            }
            if (((Node) this.children.getContent()[size]).getPointerIds().isEmpty()) {
                this.children.removeAt(size);
            }
        }
    }
}
