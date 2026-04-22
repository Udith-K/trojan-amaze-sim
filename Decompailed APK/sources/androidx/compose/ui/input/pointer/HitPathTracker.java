package androidx.compose.ui.input.pointer;

import androidx.collection.MutableLongObjectMap;
import androidx.collection.MutableObjectList;
import androidx.compose.ui.layout.LayoutCoordinates;

/* JADX INFO: compiled from: HitPathTracker.kt */
/* JADX INFO: loaded from: classes.dex */
public final class HitPathTracker {
    private final LayoutCoordinates rootCoordinates;
    private final NodeParent root = new NodeParent();
    private final MutableLongObjectMap hitPointerIdsAndNodes = new MutableLongObjectMap(10);

    public HitPathTracker(LayoutCoordinates layoutCoordinates) {
        this.rootCoordinates = layoutCoordinates;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x008a  */
    /* JADX INFO: renamed from: addHitPath-QJqDSyo, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m1648addHitPathQJqDSyo(long r18, java.util.List r20, boolean r21) {
        /*
            Method dump skipped, instruction units count: 233
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.pointer.HitPathTracker.m1648addHitPathQJqDSyo(long, java.util.List, boolean):void");
    }

    private final void removeInvalidPointerIdsAndChanges(long j, MutableObjectList mutableObjectList) {
        this.root.removeInvalidPointerIdsAndChanges(j, mutableObjectList);
    }

    public final boolean dispatchChanges(InternalPointerEvent internalPointerEvent, boolean z) {
        if (this.root.buildCache(internalPointerEvent.getChanges(), this.rootCoordinates, internalPointerEvent, z)) {
            return this.root.dispatchFinalEventPass(internalPointerEvent) || this.root.dispatchMainEventPass(internalPointerEvent.getChanges(), this.rootCoordinates, internalPointerEvent, z);
        }
        return false;
    }

    public final void clearPreviouslyHitModifierNodeCache() {
        this.root.clear();
    }

    public final void processCancel() {
        this.root.dispatchCancel();
        clearPreviouslyHitModifierNodeCache();
    }

    public final void removeDetachedPointerInputNodes() {
        this.root.removeDetachedPointerInputModifierNodes();
    }
}
