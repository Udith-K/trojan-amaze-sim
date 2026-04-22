package androidx.compose.runtime;

import ch.qos.logback.core.CoreConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: compiled from: SlotTable.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SlotReader {
    private boolean closed;
    private int currentEnd;
    private int currentGroup;
    private int currentSlot;
    private int currentSlotEnd;
    private final IntStack currentSlotStack;
    private int emptyCount;
    private final int[] groups;
    private final int groupsSize;
    private boolean hadNext;
    private int parent;
    private final Object[] slots;
    private final int slotsSize;
    private HashMap sourceInformationMap;
    private final SlotTable table;

    public SlotReader(SlotTable slotTable) {
        this.table = slotTable;
        this.groups = slotTable.getGroups();
        int groupsSize = slotTable.getGroupsSize();
        this.groupsSize = groupsSize;
        this.slots = slotTable.getSlots();
        this.slotsSize = slotTable.getSlotsSize();
        this.currentEnd = groupsSize;
        this.parent = -1;
        this.currentSlotStack = new IntStack();
    }

    public final SlotTable getTable$runtime_release() {
        return this.table;
    }

    public final boolean getClosed() {
        return this.closed;
    }

    public final int getCurrentGroup() {
        return this.currentGroup;
    }

    public final int getCurrentEnd() {
        return this.currentEnd;
    }

    public final int getParent() {
        return this.parent;
    }

    public final int getSize() {
        return this.groupsSize;
    }

    public final int parent(int i) {
        return SlotTableKt.parentAnchor(this.groups, i);
    }

    public final boolean isNode() {
        return SlotTableKt.isNode(this.groups, this.currentGroup);
    }

    public final boolean isNode(int i) {
        return SlotTableKt.isNode(this.groups, i);
    }

    public final int nodeCount(int i) {
        return SlotTableKt.nodeCount(this.groups, i);
    }

    public final Object node(int i) {
        if (SlotTableKt.isNode(this.groups, i)) {
            return node(this.groups, i);
        }
        return null;
    }

    public final boolean isGroupEnd() {
        return getInEmpty() || this.currentGroup == this.currentEnd;
    }

    public final boolean getInEmpty() {
        return this.emptyCount > 0;
    }

    public final int getGroupSize() {
        return SlotTableKt.groupSize(this.groups, this.currentGroup);
    }

    public final int groupSize(int i) {
        return SlotTableKt.groupSize(this.groups, i);
    }

    public final int getGroupEnd() {
        return this.currentEnd;
    }

    public final int getGroupKey() {
        int i = this.currentGroup;
        if (i < this.currentEnd) {
            return SlotTableKt.key(this.groups, i);
        }
        return 0;
    }

    public final int groupKey(int i) {
        return SlotTableKt.key(this.groups, i);
    }

    public final int getGroupSlotIndex() {
        return this.currentSlot - SlotTableKt.slotAnchor(this.groups, this.parent);
    }

    public final boolean hasObjectKey(int i) {
        return SlotTableKt.hasObjectKey(this.groups, i);
    }

    public final boolean getHasObjectKey() {
        int i = this.currentGroup;
        return i < this.currentEnd && SlotTableKt.hasObjectKey(this.groups, i);
    }

    public final Object getGroupObjectKey() {
        int i = this.currentGroup;
        if (i < this.currentEnd) {
            return objectKey(this.groups, i);
        }
        return null;
    }

    public final Object groupObjectKey(int i) {
        return objectKey(this.groups, i);
    }

    public final Object getGroupAux() {
        int i = this.currentGroup;
        if (i < this.currentEnd) {
            return aux(this.groups, i);
        }
        return 0;
    }

    public final Object groupAux(int i) {
        return aux(this.groups, i);
    }

    public final boolean hasMark(int i) {
        return SlotTableKt.hasMark(this.groups, i);
    }

    public final boolean containsMark(int i) {
        return SlotTableKt.containsMark(this.groups, i);
    }

    public final int getParentNodes() {
        int i = this.parent;
        if (i >= 0) {
            return SlotTableKt.nodeCount(this.groups, i);
        }
        return 0;
    }

    public final int getRemainingSlots() {
        return this.currentSlotEnd - this.currentSlot;
    }

    public final Object groupGet(int i) {
        return groupGet(this.currentGroup, i);
    }

    public final Object groupGet(int i, int i2) {
        int iSlotAnchor = SlotTableKt.slotAnchor(this.groups, i);
        int i3 = i + 1;
        int i4 = iSlotAnchor + i2;
        return i4 < (i3 < this.groupsSize ? SlotTableKt.dataAnchor(this.groups, i3) : this.slotsSize) ? this.slots[i4] : Composer.Companion.getEmpty();
    }

    public final Object next() {
        int i;
        if (this.emptyCount > 0 || (i = this.currentSlot) >= this.currentSlotEnd) {
            this.hadNext = false;
            return Composer.Companion.getEmpty();
        }
        this.hadNext = true;
        Object[] objArr = this.slots;
        this.currentSlot = i + 1;
        return objArr[i];
    }

    public final boolean getHadNext() {
        return this.hadNext;
    }

    public final void beginEmpty() {
        this.emptyCount++;
    }

    public final void endEmpty() {
        if (!(this.emptyCount > 0)) {
            PreconditionsKt.throwIllegalArgumentException("Unbalanced begin/end empty");
        }
        this.emptyCount--;
    }

    public final void close() {
        this.closed = true;
        this.table.close$runtime_release(this, this.sourceInformationMap);
    }

    public final void startGroup() {
        int iDataAnchor;
        if (this.emptyCount <= 0) {
            int i = this.parent;
            int i2 = this.currentGroup;
            if (!(SlotTableKt.parentAnchor(this.groups, i2) == i)) {
                PreconditionsKt.throwIllegalArgumentException("Invalid slot table detected");
            }
            HashMap map = this.sourceInformationMap;
            if (map != null) {
            }
            IntStack intStack = this.currentSlotStack;
            int i3 = this.currentSlot;
            int i4 = this.currentSlotEnd;
            if (i3 == 0 && i4 == 0) {
                intStack.push(-1);
            } else {
                intStack.push(i3);
            }
            this.parent = i2;
            this.currentEnd = SlotTableKt.groupSize(this.groups, i2) + i2;
            int i5 = i2 + 1;
            this.currentGroup = i5;
            this.currentSlot = SlotTableKt.slotAnchor(this.groups, i2);
            if (i2 < this.groupsSize - 1) {
                iDataAnchor = SlotTableKt.dataAnchor(this.groups, i5);
            } else {
                iDataAnchor = this.slotsSize;
            }
            this.currentSlotEnd = iDataAnchor;
        }
    }

    public final void startNode() {
        if (this.emptyCount <= 0) {
            if (!SlotTableKt.isNode(this.groups, this.currentGroup)) {
                PreconditionsKt.throwIllegalArgumentException("Expected a node group");
            }
            startGroup();
        }
    }

    public final int skipGroup() {
        if (!(this.emptyCount == 0)) {
            ComposerKt.composeImmediateRuntimeError("Cannot skip while in an empty region");
        }
        int iNodeCount = SlotTableKt.isNode(this.groups, this.currentGroup) ? 1 : SlotTableKt.nodeCount(this.groups, this.currentGroup);
        int i = this.currentGroup;
        this.currentGroup = i + SlotTableKt.groupSize(this.groups, i);
        return iNodeCount;
    }

    public final void skipToGroupEnd() {
        if (!(this.emptyCount == 0)) {
            ComposerKt.composeImmediateRuntimeError("Cannot skip the enclosing group while in an empty region");
        }
        this.currentGroup = this.currentEnd;
        this.currentSlot = 0;
        this.currentSlotEnd = 0;
    }

    public final void reposition(int i) {
        if (!(this.emptyCount == 0)) {
            ComposerKt.composeImmediateRuntimeError("Cannot reposition while in an empty region");
        }
        this.currentGroup = i;
        int iParentAnchor = i < this.groupsSize ? SlotTableKt.parentAnchor(this.groups, i) : -1;
        this.parent = iParentAnchor;
        if (iParentAnchor >= 0) {
            this.currentEnd = iParentAnchor + SlotTableKt.groupSize(this.groups, iParentAnchor);
        } else {
            this.currentEnd = this.groupsSize;
        }
        this.currentSlot = 0;
        this.currentSlotEnd = 0;
    }

    public final void restoreParent(int i) {
        int iGroupSize = SlotTableKt.groupSize(this.groups, i) + i;
        int i2 = this.currentGroup;
        if (!(i2 >= i && i2 <= iGroupSize)) {
            ComposerKt.composeImmediateRuntimeError("Index " + i + " is not a parent of " + i2);
        }
        this.parent = i;
        this.currentEnd = iGroupSize;
        this.currentSlot = 0;
        this.currentSlotEnd = 0;
    }

    public final void endGroup() {
        int iGroupSize;
        int iDataAnchor;
        if (this.emptyCount == 0) {
            if (!(this.currentGroup == this.currentEnd)) {
                ComposerKt.composeImmediateRuntimeError("endGroup() not called at the end of a group");
            }
            int iParentAnchor = SlotTableKt.parentAnchor(this.groups, this.parent);
            this.parent = iParentAnchor;
            if (iParentAnchor >= 0) {
                iGroupSize = SlotTableKt.groupSize(this.groups, iParentAnchor) + iParentAnchor;
            } else {
                iGroupSize = this.groupsSize;
            }
            this.currentEnd = iGroupSize;
            int iPop = this.currentSlotStack.pop();
            if (iPop < 0) {
                this.currentSlot = 0;
                this.currentSlotEnd = 0;
                return;
            }
            this.currentSlot = iPop;
            if (iParentAnchor < this.groupsSize - 1) {
                iDataAnchor = SlotTableKt.dataAnchor(this.groups, iParentAnchor + 1);
            } else {
                iDataAnchor = this.slotsSize;
            }
            this.currentSlotEnd = iDataAnchor;
        }
    }

    public final List extractKeys() {
        ArrayList arrayList = new ArrayList();
        if (this.emptyCount > 0) {
            return arrayList;
        }
        int iGroupSize = this.currentGroup;
        int i = 0;
        while (iGroupSize < this.currentEnd) {
            arrayList.add(new KeyInfo(SlotTableKt.key(this.groups, iGroupSize), objectKey(this.groups, iGroupSize), iGroupSize, SlotTableKt.isNode(this.groups, iGroupSize) ? 1 : SlotTableKt.nodeCount(this.groups, iGroupSize), i));
            iGroupSize += SlotTableKt.groupSize(this.groups, iGroupSize);
            i++;
        }
        return arrayList;
    }

    public String toString() {
        return "SlotReader(current=" + this.currentGroup + ", key=" + getGroupKey() + ", parent=" + this.parent + ", end=" + this.currentEnd + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public final Anchor anchor(int i) {
        ArrayList anchors$runtime_release = this.table.getAnchors$runtime_release();
        int iSearch = SlotTableKt.search(anchors$runtime_release, i, this.groupsSize);
        if (iSearch < 0) {
            Anchor anchor = new Anchor(i);
            anchors$runtime_release.add(-(iSearch + 1), anchor);
            return anchor;
        }
        return (Anchor) anchors$runtime_release.get(iSearch);
    }

    private final Object node(int[] iArr, int i) {
        if (SlotTableKt.isNode(iArr, i)) {
            return this.slots[SlotTableKt.nodeIndex(iArr, i)];
        }
        return Composer.Companion.getEmpty();
    }

    private final Object aux(int[] iArr, int i) {
        if (SlotTableKt.hasAux(iArr, i)) {
            return this.slots[SlotTableKt.auxIndex(iArr, i)];
        }
        return Composer.Companion.getEmpty();
    }

    private final Object objectKey(int[] iArr, int i) {
        if (SlotTableKt.hasObjectKey(iArr, i)) {
            return this.slots[SlotTableKt.objectKeyIndex(iArr, i)];
        }
        return null;
    }
}
