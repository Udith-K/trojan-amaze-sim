package androidx.compose.runtime;

import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableObjectList;
import androidx.compose.runtime.Composer;
import ch.qos.logback.core.CoreConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SlotTable.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SlotWriter {
    private ArrayList anchors;
    private MutableIntObjectMap calledByMap;
    private boolean closed;
    private int currentGroup;
    private int currentGroupEnd;
    private int currentSlot;
    private int currentSlotEnd;
    private MutableIntObjectMap deferredSlotWrites;
    private int groupGapLen;
    private int groupGapStart;
    private int[] groups;
    private int insertCount;
    private int nodeCount;
    private PrioritySet pendingRecalculateMarks;
    private Object[] slots;
    private int slotsGapLen;
    private int slotsGapOwner;
    private int slotsGapStart;
    private HashMap sourceInformationMap;
    private final SlotTable table;
    public static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private final IntStack startStack = new IntStack();
    private final IntStack endStack = new IntStack();
    private final IntStack nodeCountStack = new IntStack();
    private int parent = -1;

    private final int dataAnchorToDataIndex(int i, int i2, int i3) {
        return i < 0 ? (i3 - i2) + i + 1 : i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dataIndexToDataAnchor(int i, int i2, int i3, int i4) {
        return i > i2 ? -(((i4 - i3) - i) + 1) : i;
    }

    public SlotWriter(SlotTable slotTable) {
        this.table = slotTable;
        this.groups = slotTable.getGroups();
        this.slots = slotTable.getSlots();
        this.anchors = slotTable.getAnchors$runtime_release();
        this.sourceInformationMap = slotTable.getSourceInformationMap$runtime_release();
        this.calledByMap = slotTable.getCalledByMap$runtime_release();
        this.groupGapStart = slotTable.getGroupsSize();
        this.groupGapLen = (this.groups.length / 5) - slotTable.getGroupsSize();
        this.slotsGapStart = slotTable.getSlotsSize();
        this.slotsGapLen = this.slots.length - slotTable.getSlotsSize();
        this.slotsGapOwner = slotTable.getGroupsSize();
        this.currentGroupEnd = slotTable.getGroupsSize();
    }

    public final SlotTable getTable$runtime_release() {
        return this.table;
    }

    public final int getCurrentGroup() {
        return this.currentGroup;
    }

    public final int getCurrentGroupEnd() {
        return this.currentGroupEnd;
    }

    public final int getSlotsSize() {
        return this.slots.length - this.slotsGapLen;
    }

    public final boolean isNode() {
        int i = this.currentGroup;
        return i < this.currentGroupEnd && SlotTableKt.isNode(this.groups, groupIndexToAddress(i));
    }

    public final boolean isNode(int i) {
        return SlotTableKt.isNode(this.groups, groupIndexToAddress(i));
    }

    public final int nodeCount(int i) {
        return SlotTableKt.nodeCount(this.groups, groupIndexToAddress(i));
    }

    public final int groupKey(int i) {
        return SlotTableKt.key(this.groups, groupIndexToAddress(i));
    }

    public final Object groupObjectKey(int i) {
        int iGroupIndexToAddress = groupIndexToAddress(i);
        if (SlotTableKt.hasObjectKey(this.groups, iGroupIndexToAddress)) {
            return this.slots[SlotTableKt.objectKeyIndex(this.groups, iGroupIndexToAddress)];
        }
        return null;
    }

    public final int groupSize(int i) {
        return SlotTableKt.groupSize(this.groups, groupIndexToAddress(i));
    }

    public final Object groupAux(int i) {
        int iGroupIndexToAddress = groupIndexToAddress(i);
        return SlotTableKt.hasAux(this.groups, iGroupIndexToAddress) ? this.slots[auxIndex(this.groups, iGroupIndexToAddress)] : Composer.Companion.getEmpty();
    }

    public final boolean indexInParent(int i) {
        int i2 = this.parent;
        return (i > i2 && i < this.currentGroupEnd) || (i2 == 0 && i == 0);
    }

    public final boolean indexInCurrentGroup(int i) {
        return indexInGroup(i, this.currentGroup);
    }

    public final boolean indexInGroup(int i, int i2) {
        int iIndexOf;
        int capacity;
        if (i2 == this.parent) {
            capacity = this.currentGroupEnd;
        } else if (i2 > this.startStack.peekOr(0) || (iIndexOf = this.startStack.indexOf(i2)) < 0) {
            int iGroupSize = groupSize(i2);
            capacity = iGroupSize + i2;
        } else {
            capacity = (getCapacity() - this.groupGapLen) - this.endStack.peek(iIndexOf);
        }
        return i > i2 && i < capacity;
    }

    public final Object node(int i) {
        int iGroupIndexToAddress = groupIndexToAddress(i);
        if (SlotTableKt.isNode(this.groups, iGroupIndexToAddress)) {
            return this.slots[dataIndexToDataAddress(nodeIndex(this.groups, iGroupIndexToAddress))];
        }
        return null;
    }

    public final Object node(Anchor anchor) {
        return node(anchor.toIndexFor(this));
    }

    public final int getParent() {
        return this.parent;
    }

    public final int parent(int i) {
        return parent(this.groups, i);
    }

    public final boolean getClosed() {
        return this.closed;
    }

    public final void close(boolean z) {
        this.closed = true;
        if (z && this.startStack.isEmpty()) {
            moveGroupGapTo(getSize$runtime_release());
            moveSlotGapTo(this.slots.length - this.slotsGapLen, this.groupGapStart);
            clearSlotGap();
            recalculateMarks();
        }
        this.table.close$runtime_release(this, this.groups, this.groupGapStart, this.slots, this.slotsGapStart, this.anchors, this.sourceInformationMap, this.calledByMap);
    }

    public final void reset() {
        if (!(this.insertCount == 0)) {
            ComposerKt.composeImmediateRuntimeError("Cannot reset when inserting");
        }
        recalculateMarks();
        this.currentGroup = 0;
        this.currentGroupEnd = getCapacity() - this.groupGapLen;
        this.currentSlot = 0;
        this.currentSlotEnd = 0;
        this.nodeCount = 0;
    }

    public final Object update(Object obj) {
        if (this.insertCount > 0 && this.currentSlot != this.slotsGapStart) {
            MutableIntObjectMap mutableIntObjectMap = this.deferredSlotWrites;
            DefaultConstructorMarker defaultConstructorMarker = null;
            int i = 1;
            int i2 = 0;
            if (mutableIntObjectMap == null) {
                mutableIntObjectMap = new MutableIntObjectMap(i2, i, defaultConstructorMarker);
            }
            this.deferredSlotWrites = mutableIntObjectMap;
            int i3 = this.parent;
            Object mutableObjectList = mutableIntObjectMap.get(i3);
            if (mutableObjectList == null) {
                mutableObjectList = new MutableObjectList(i2, i, defaultConstructorMarker);
                mutableIntObjectMap.set(i3, mutableObjectList);
            }
            ((MutableObjectList) mutableObjectList).add(obj);
            return Composer.Companion.getEmpty();
        }
        return rawUpdate(obj);
    }

    private final Object rawUpdate(Object obj) {
        Object objSkip = skip();
        set(obj);
        return objSkip;
    }

    public final void appendSlot(Anchor anchor, Object obj) {
        if (!(this.insertCount == 0)) {
            ComposerKt.composeImmediateRuntimeError("Can only append a slot if not current inserting");
        }
        int i = this.currentSlot;
        int i2 = this.currentSlotEnd;
        int iAnchorIndex = anchorIndex(anchor);
        int iDataIndex = dataIndex(this.groups, groupIndexToAddress(iAnchorIndex + 1));
        this.currentSlot = iDataIndex;
        this.currentSlotEnd = iDataIndex;
        insertSlots(1, iAnchorIndex);
        if (i >= iDataIndex) {
            i++;
            i2++;
        }
        this.slots[iDataIndex] = obj;
        this.currentSlot = i;
        this.currentSlotEnd = i2;
    }

    public final void trimTailSlots(int i) {
        ComposerKt.runtimeCheck(i > 0);
        int i2 = this.parent;
        int iSlotIndex = slotIndex(this.groups, groupIndexToAddress(i2));
        int iDataIndex = dataIndex(this.groups, groupIndexToAddress(i2 + 1)) - i;
        ComposerKt.runtimeCheck(iDataIndex >= iSlotIndex);
        removeSlots(iDataIndex, i, i2);
        int i3 = this.currentSlot;
        if (i3 >= iSlotIndex) {
            this.currentSlot = i3 - i;
        }
    }

    public final void updateAux(Object obj) {
        int iGroupIndexToAddress = groupIndexToAddress(this.currentGroup);
        if (!SlotTableKt.hasAux(this.groups, iGroupIndexToAddress)) {
            ComposerKt.composeImmediateRuntimeError("Updating the data of a group that was not created with a data slot");
        }
        this.slots[dataIndexToDataAddress(auxIndex(this.groups, iGroupIndexToAddress))] = obj;
    }

    public final void updateToTableMaps() {
        this.sourceInformationMap = this.table.getSourceInformationMap$runtime_release();
        this.calledByMap = this.table.getCalledByMap$runtime_release();
    }

    public final void updateNode(Object obj) {
        updateNodeOfGroup(this.currentGroup, obj);
    }

    public final void updateNode(Anchor anchor, Object obj) {
        updateNodeOfGroup(anchor.toIndexFor(this), obj);
    }

    public final void set(Object obj) {
        if (!(this.currentSlot <= this.currentSlotEnd)) {
            ComposerKt.composeImmediateRuntimeError("Writing to an invalid slot");
        }
        this.slots[dataIndexToDataAddress(this.currentSlot - 1)] = obj;
    }

    public final Object set(int i, Object obj) {
        return set(this.currentGroup, i, obj);
    }

    public final int slotIndexOfGroupSlotIndex(int i, int i2) {
        int iSlotIndex = slotIndex(this.groups, groupIndexToAddress(i));
        int i3 = iSlotIndex + i2;
        if (!(i3 >= iSlotIndex && i3 < dataIndex(this.groups, groupIndexToAddress(i + 1)))) {
            ComposerKt.composeImmediateRuntimeError("Write to an invalid slot index " + i2 + " for group " + i);
        }
        return i3;
    }

    public final Object set(int i, int i2, Object obj) {
        int iDataIndexToDataAddress = dataIndexToDataAddress(slotIndexOfGroupSlotIndex(i, i2));
        Object[] objArr = this.slots;
        Object obj2 = objArr[iDataIndexToDataAddress];
        objArr[iDataIndexToDataAddress] = obj;
        return obj2;
    }

    public final Object skip() {
        if (this.insertCount > 0) {
            insertSlots(1, this.parent);
        }
        Object[] objArr = this.slots;
        int i = this.currentSlot;
        this.currentSlot = i + 1;
        return objArr[dataIndexToDataAddress(i)];
    }

    public final Object slot(Anchor anchor, int i) {
        return slot(anchorIndex(anchor), i);
    }

    public final Object slot(int i, int i2) {
        int iSlotIndex = slotIndex(this.groups, groupIndexToAddress(i));
        int iDataIndex = dataIndex(this.groups, groupIndexToAddress(i + 1));
        int i3 = i2 + iSlotIndex;
        if (iSlotIndex > i3 || i3 >= iDataIndex) {
            return Composer.Companion.getEmpty();
        }
        return this.slots[dataIndexToDataAddress(i3)];
    }

    public final int slotsStartIndex$runtime_release(int i) {
        return slotIndex(this.groups, groupIndexToAddress(i));
    }

    public final int slotsEndIndex$runtime_release(int i) {
        return dataIndex(this.groups, groupIndexToAddress(i + 1));
    }

    public final int slotsEndAllIndex$runtime_release(int i) {
        return dataIndex(this.groups, groupIndexToAddress(i + groupSize(i)));
    }

    public final void advanceBy(int i) {
        boolean z = false;
        if (!(i >= 0)) {
            ComposerKt.composeImmediateRuntimeError("Cannot seek backwards");
        }
        if (!(this.insertCount <= 0)) {
            PreconditionsKt.throwIllegalStateException("Cannot call seek() while inserting");
        }
        if (i == 0) {
            return;
        }
        int i2 = this.currentGroup + i;
        if (i2 >= this.parent && i2 <= this.currentGroupEnd) {
            z = true;
        }
        if (!z) {
            ComposerKt.composeImmediateRuntimeError("Cannot seek outside the current group (" + this.parent + CoreConstants.DASH_CHAR + this.currentGroupEnd + CoreConstants.RIGHT_PARENTHESIS_CHAR);
        }
        this.currentGroup = i2;
        int iDataIndex = dataIndex(this.groups, groupIndexToAddress(i2));
        this.currentSlot = iDataIndex;
        this.currentSlotEnd = iDataIndex;
    }

    public final void skipToGroupEnd() {
        int i = this.currentGroupEnd;
        this.currentGroup = i;
        this.currentSlot = dataIndex(this.groups, groupIndexToAddress(i));
    }

    public final void beginInsert() {
        int i = this.insertCount;
        this.insertCount = i + 1;
        if (i == 0) {
            saveCurrentGroupEnd();
        }
    }

    public final void endInsert() {
        if (!(this.insertCount > 0)) {
            PreconditionsKt.throwIllegalStateException("Unbalanced begin/end insert");
        }
        int i = this.insertCount - 1;
        this.insertCount = i;
        if (i == 0) {
            if (!(this.nodeCountStack.getSize() == this.startStack.getSize())) {
                ComposerKt.composeImmediateRuntimeError("startGroup/endGroup mismatch while inserting");
            }
            restoreCurrentGroupEnd();
        }
    }

    public final void startGroup() {
        if (!(this.insertCount == 0)) {
            ComposerKt.composeImmediateRuntimeError("Key must be supplied when inserting");
        }
        Composer.Companion companion = Composer.Companion;
        startGroup(0, companion.getEmpty(), false, companion.getEmpty());
    }

    public final void startGroup(int i, Object obj) {
        startGroup(i, obj, false, Composer.Companion.getEmpty());
    }

    public final void startNode(int i, Object obj) {
        startGroup(i, obj, true, Composer.Companion.getEmpty());
    }

    public final void startData(int i, Object obj, Object obj2) {
        startGroup(i, obj, false, obj2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void startGroup(int i, Object obj, boolean z, Object obj2) {
        int iGroupSize;
        int i2 = this.parent;
        byte b = this.insertCount > 0;
        this.nodeCountStack.push(this.nodeCount);
        if (b != false) {
            int i3 = this.currentGroup;
            int iDataIndex = dataIndex(this.groups, groupIndexToAddress(i3));
            insertGroups(1);
            this.currentSlot = iDataIndex;
            this.currentSlotEnd = iDataIndex;
            int iGroupIndexToAddress = groupIndexToAddress(i3);
            Composer.Companion companion = Composer.Companion;
            int i4 = obj != companion.getEmpty() ? 1 : 0;
            int i5 = (z || obj2 == companion.getEmpty()) ? 0 : 1;
            int iDataIndexToDataAnchor = dataIndexToDataAnchor(iDataIndex, this.slotsGapStart, this.slotsGapLen, this.slots.length);
            if (iDataIndexToDataAnchor >= 0 && this.slotsGapOwner < i3) {
                iDataIndexToDataAnchor = -(((this.slots.length - this.slotsGapLen) - iDataIndexToDataAnchor) + 1);
            }
            SlotTableKt.initGroup(this.groups, iGroupIndexToAddress, i, z, i4, i5, this.parent, iDataIndexToDataAnchor);
            int i6 = (z ? 1 : 0) + i4 + i5;
            if (i6 > 0) {
                insertSlots(i6, i3);
                Object[] objArr = this.slots;
                int i7 = this.currentSlot;
                if (z) {
                    objArr[i7] = obj2;
                    i7++;
                }
                if (i4 != 0) {
                    objArr[i7] = obj;
                    i7++;
                }
                if (i5 != 0) {
                    objArr[i7] = obj2;
                    i7++;
                }
                this.currentSlot = i7;
            }
            this.nodeCount = 0;
            iGroupSize = i3 + 1;
            this.parent = i3;
            this.currentGroup = iGroupSize;
            if (i2 >= 0) {
                sourceInformationOf(i2);
            }
        } else {
            this.startStack.push(i2);
            saveCurrentGroupEnd();
            int i8 = this.currentGroup;
            int iGroupIndexToAddress2 = groupIndexToAddress(i8);
            if (!Intrinsics.areEqual(obj2, Composer.Companion.getEmpty())) {
                if (z) {
                    updateNode(obj2);
                } else {
                    updateAux(obj2);
                }
            }
            this.currentSlot = slotIndex(this.groups, iGroupIndexToAddress2);
            this.currentSlotEnd = dataIndex(this.groups, groupIndexToAddress(this.currentGroup + 1));
            this.nodeCount = SlotTableKt.nodeCount(this.groups, iGroupIndexToAddress2);
            this.parent = i8;
            this.currentGroup = i8 + 1;
            iGroupSize = i8 + SlotTableKt.groupSize(this.groups, iGroupIndexToAddress2);
        }
        this.currentGroupEnd = iGroupSize;
    }

    public final int endGroup() {
        MutableObjectList mutableObjectList;
        boolean z = this.insertCount > 0;
        int i = this.currentGroup;
        int i2 = this.currentGroupEnd;
        int i3 = this.parent;
        int iGroupIndexToAddress = groupIndexToAddress(i3);
        int i4 = this.nodeCount;
        int i5 = i - i3;
        boolean zIsNode = SlotTableKt.isNode(this.groups, iGroupIndexToAddress);
        if (!z) {
            if (!(i == i2)) {
                ComposerKt.composeImmediateRuntimeError("Expected to be at the end of a group");
            }
            int iGroupSize = SlotTableKt.groupSize(this.groups, iGroupIndexToAddress);
            int iNodeCount = SlotTableKt.nodeCount(this.groups, iGroupIndexToAddress);
            SlotTableKt.updateGroupSize(this.groups, iGroupIndexToAddress, i5);
            SlotTableKt.updateNodeCount(this.groups, iGroupIndexToAddress, i4);
            int iPop = this.startStack.pop();
            restoreCurrentGroupEnd();
            this.parent = iPop;
            int iParent = parent(this.groups, i3);
            int iPop2 = this.nodeCountStack.pop();
            this.nodeCount = iPop2;
            if (iParent == iPop) {
                this.nodeCount = iPop2 + (zIsNode ? 0 : i4 - iNodeCount);
            } else {
                int i6 = i5 - iGroupSize;
                int i7 = zIsNode ? 0 : i4 - iNodeCount;
                if (i6 != 0 || i7 != 0) {
                    while (iParent != 0 && iParent != iPop && (i7 != 0 || i6 != 0)) {
                        int iGroupIndexToAddress2 = groupIndexToAddress(iParent);
                        if (i6 != 0) {
                            SlotTableKt.updateGroupSize(this.groups, iGroupIndexToAddress2, SlotTableKt.groupSize(this.groups, iGroupIndexToAddress2) + i6);
                        }
                        if (i7 != 0) {
                            int[] iArr = this.groups;
                            SlotTableKt.updateNodeCount(iArr, iGroupIndexToAddress2, SlotTableKt.nodeCount(iArr, iGroupIndexToAddress2) + i7);
                        }
                        if (SlotTableKt.isNode(this.groups, iGroupIndexToAddress2)) {
                            i7 = 0;
                        }
                        iParent = parent(this.groups, iParent);
                    }
                }
                this.nodeCount += i7;
            }
        } else {
            MutableIntObjectMap mutableIntObjectMap = this.deferredSlotWrites;
            if (mutableIntObjectMap != null && (mutableObjectList = (MutableObjectList) mutableIntObjectMap.get(i3)) != null) {
                Object[] objArr = mutableObjectList.content;
                int i8 = mutableObjectList._size;
                for (int i9 = 0; i9 < i8; i9++) {
                    rawUpdate(objArr[i9]);
                }
            }
            SlotTableKt.updateGroupSize(this.groups, iGroupIndexToAddress, i5);
            SlotTableKt.updateNodeCount(this.groups, iGroupIndexToAddress, i4);
            this.nodeCount = this.nodeCountStack.pop() + (zIsNode ? 1 : i4);
            int iParent2 = parent(this.groups, i3);
            this.parent = iParent2;
            int size$runtime_release = iParent2 < 0 ? getSize$runtime_release() : groupIndexToAddress(iParent2 + 1);
            int iDataIndex = size$runtime_release >= 0 ? dataIndex(this.groups, size$runtime_release) : 0;
            this.currentSlot = iDataIndex;
            this.currentSlotEnd = iDataIndex;
        }
        return i4;
    }

    public final void ensureStarted(int i) {
        boolean z = false;
        if (!(this.insertCount <= 0)) {
            ComposerKt.composeImmediateRuntimeError("Cannot call ensureStarted() while inserting");
        }
        int i2 = this.parent;
        if (i2 != i) {
            if (i >= i2 && i < this.currentGroupEnd) {
                z = true;
            }
            if (!z) {
                ComposerKt.composeImmediateRuntimeError("Started group at " + i + " must be a subgroup of the group at " + i2);
            }
            int i3 = this.currentGroup;
            int i4 = this.currentSlot;
            int i5 = this.currentSlotEnd;
            this.currentGroup = i;
            startGroup();
            this.currentGroup = i3;
            this.currentSlot = i4;
            this.currentSlotEnd = i5;
        }
    }

    public final void ensureStarted(Anchor anchor) {
        ensureStarted(anchor.toIndexFor(this));
    }

    public final int skipGroup() {
        int iGroupIndexToAddress = groupIndexToAddress(this.currentGroup);
        int iGroupSize = this.currentGroup + SlotTableKt.groupSize(this.groups, iGroupIndexToAddress);
        this.currentGroup = iGroupSize;
        this.currentSlot = dataIndex(this.groups, groupIndexToAddress(iGroupSize));
        if (SlotTableKt.isNode(this.groups, iGroupIndexToAddress)) {
            return 1;
        }
        return SlotTableKt.nodeCount(this.groups, iGroupIndexToAddress);
    }

    public final boolean removeGroup() {
        if (!(this.insertCount == 0)) {
            ComposerKt.composeImmediateRuntimeError("Cannot remove group while inserting");
        }
        int i = this.currentGroup;
        int i2 = this.currentSlot;
        int iDataIndex = dataIndex(this.groups, groupIndexToAddress(i));
        int iSkipGroup = skipGroup();
        sourceInformationOf(this.parent);
        PrioritySet prioritySet = this.pendingRecalculateMarks;
        if (prioritySet != null) {
            while (prioritySet.isNotEmpty() && prioritySet.peek() >= i) {
                prioritySet.takeMax();
            }
        }
        boolean zRemoveGroups = removeGroups(i, this.currentGroup - i);
        removeSlots(iDataIndex, this.currentSlot - iDataIndex, i - 1);
        this.currentGroup = i;
        this.currentSlot = i2;
        this.nodeCount -= iSkipGroup;
        return zRemoveGroups;
    }

    public final void moveGroup(int i) {
        if (!(this.insertCount == 0)) {
            ComposerKt.composeImmediateRuntimeError("Cannot move a group while inserting");
        }
        if (!(i >= 0)) {
            ComposerKt.composeImmediateRuntimeError("Parameter offset is out of bounds");
        }
        if (i == 0) {
            return;
        }
        int i2 = this.currentGroup;
        int i3 = this.parent;
        int i4 = this.currentGroupEnd;
        int iGroupSize = i2;
        for (int i5 = i; i5 > 0; i5--) {
            iGroupSize += SlotTableKt.groupSize(this.groups, groupIndexToAddress(iGroupSize));
            if (!(iGroupSize <= i4)) {
                ComposerKt.composeImmediateRuntimeError("Parameter offset is out of bounds");
            }
        }
        int iGroupSize2 = SlotTableKt.groupSize(this.groups, groupIndexToAddress(iGroupSize));
        int iDataIndex = dataIndex(this.groups, groupIndexToAddress(this.currentGroup));
        int iDataIndex2 = dataIndex(this.groups, groupIndexToAddress(iGroupSize));
        int i6 = iGroupSize + iGroupSize2;
        int iDataIndex3 = dataIndex(this.groups, groupIndexToAddress(i6));
        int i7 = iDataIndex3 - iDataIndex2;
        insertSlots(i7, Math.max(this.currentGroup - 1, 0));
        insertGroups(iGroupSize2);
        int[] iArr = this.groups;
        int iGroupIndexToAddress = groupIndexToAddress(i6) * 5;
        ArraysKt.copyInto(iArr, iArr, groupIndexToAddress(i2) * 5, iGroupIndexToAddress, (iGroupSize2 * 5) + iGroupIndexToAddress);
        if (i7 > 0) {
            Object[] objArr = this.slots;
            ArraysKt.copyInto(objArr, objArr, iDataIndex, dataIndexToDataAddress(iDataIndex2 + i7), dataIndexToDataAddress(iDataIndex3 + i7));
        }
        int i8 = iDataIndex2 + i7;
        int i9 = i8 - iDataIndex;
        int i10 = this.slotsGapStart;
        int i11 = this.slotsGapLen;
        int length = this.slots.length;
        int i12 = this.slotsGapOwner;
        int i13 = i2 + iGroupSize2;
        int i14 = i2;
        while (i14 < i13) {
            int iGroupIndexToAddress2 = groupIndexToAddress(i14);
            int i15 = i10;
            int i16 = i9;
            updateDataIndex(iArr, iGroupIndexToAddress2, dataIndexToDataAnchor(dataIndex(iArr, iGroupIndexToAddress2) - i9, i12 < iGroupIndexToAddress2 ? 0 : i15, i11, length));
            i14++;
            i10 = i15;
            i9 = i16;
        }
        moveAnchors(i6, i2, iGroupSize2);
        if (removeGroups(i6, iGroupSize2)) {
            ComposerKt.composeImmediateRuntimeError("Unexpectedly removed anchors");
        }
        fixParentAnchorsFor(i3, this.currentGroupEnd, i2);
        if (i7 > 0) {
            removeSlots(i8, i7, i6 - 1);
        }
    }

    /* JADX INFO: compiled from: SlotTable.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        static /* synthetic */ List moveGroup$default(Companion companion, SlotWriter slotWriter, int i, SlotWriter slotWriter2, boolean z, boolean z2, boolean z3, int i2, Object obj) {
            if ((i2 & 32) != 0) {
                z3 = true;
            }
            return companion.moveGroup(slotWriter, i, slotWriter2, z, z2, z3);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List moveGroup(SlotWriter slotWriter, int i, SlotWriter slotWriter2, boolean z, boolean z2, boolean z3) {
            boolean zRemoveGroups;
            List listEmptyList;
            int i2;
            int iGroupSize = slotWriter.groupSize(i);
            int i3 = i + iGroupSize;
            int iDataIndex = slotWriter.dataIndex(i);
            int iDataIndex2 = slotWriter.dataIndex(i3);
            int i4 = iDataIndex2 - iDataIndex;
            boolean zContainsAnyGroupMarks = slotWriter.containsAnyGroupMarks(i);
            slotWriter2.insertGroups(iGroupSize);
            slotWriter2.insertSlots(i4, slotWriter2.getCurrentGroup());
            if (slotWriter.groupGapStart < i3) {
                slotWriter.moveGroupGapTo(i3);
            }
            if (slotWriter.slotsGapStart < iDataIndex2) {
                slotWriter.moveSlotGapTo(iDataIndex2, i3);
            }
            int[] iArr = slotWriter2.groups;
            int currentGroup = slotWriter2.getCurrentGroup();
            ArraysKt.copyInto(slotWriter.groups, iArr, currentGroup * 5, i * 5, i3 * 5);
            Object[] objArr = slotWriter2.slots;
            int i5 = slotWriter2.currentSlot;
            ArraysKt.copyInto(slotWriter.slots, objArr, i5, iDataIndex, iDataIndex2);
            int parent = slotWriter2.getParent();
            SlotTableKt.updateParentAnchor(iArr, currentGroup, parent);
            int i6 = currentGroup - i;
            int i7 = currentGroup + iGroupSize;
            int iDataIndex3 = i5 - slotWriter2.dataIndex(iArr, currentGroup);
            int i8 = slotWriter2.slotsGapOwner;
            int i9 = slotWriter2.slotsGapLen;
            int length = objArr.length;
            int i10 = i8;
            int i11 = currentGroup;
            while (true) {
                zRemoveGroups = false;
                if (i11 >= i7) {
                    break;
                }
                if (i11 != currentGroup) {
                    i2 = i7;
                    SlotTableKt.updateParentAnchor(iArr, i11, SlotTableKt.parentAnchor(iArr, i11) + i6);
                } else {
                    i2 = i7;
                }
                int i12 = iDataIndex3;
                SlotTableKt.updateDataAnchor(iArr, i11, slotWriter2.dataIndexToDataAnchor(slotWriter2.dataIndex(iArr, i11) + iDataIndex3, i10 >= i11 ? slotWriter2.slotsGapStart : 0, i9, length));
                if (i11 == i10) {
                    i10++;
                }
                i11++;
                iDataIndex3 = i12;
                i7 = i2;
            }
            int i13 = i7;
            slotWriter2.slotsGapOwner = i10;
            int iLocationOf = SlotTableKt.locationOf(slotWriter.anchors, i, slotWriter.getSize$runtime_release());
            int iLocationOf2 = SlotTableKt.locationOf(slotWriter.anchors, i3, slotWriter.getSize$runtime_release());
            if (iLocationOf < iLocationOf2) {
                ArrayList arrayList = slotWriter.anchors;
                ArrayList arrayList2 = new ArrayList(iLocationOf2 - iLocationOf);
                for (int i14 = iLocationOf; i14 < iLocationOf2; i14++) {
                    Anchor anchor = (Anchor) arrayList.get(i14);
                    anchor.setLocation$runtime_release(anchor.getLocation$runtime_release() + i6);
                    arrayList2.add(anchor);
                }
                slotWriter2.anchors.addAll(SlotTableKt.locationOf(slotWriter2.anchors, slotWriter2.getCurrentGroup(), slotWriter2.getSize$runtime_release()), arrayList2);
                arrayList.subList(iLocationOf, iLocationOf2).clear();
                listEmptyList = arrayList2;
            } else {
                listEmptyList = CollectionsKt.emptyList();
            }
            if (!listEmptyList.isEmpty()) {
                HashMap map = slotWriter.sourceInformationMap;
                HashMap map2 = slotWriter2.sourceInformationMap;
                if (map != null && map2 != null) {
                    int size = listEmptyList.size();
                    for (int i15 = 0; i15 < size; i15++) {
                    }
                }
            }
            slotWriter2.getParent();
            slotWriter2.sourceInformationOf(parent);
            int iParent = slotWriter.parent(i);
            if (z3) {
                if (!z) {
                    zRemoveGroups = slotWriter.removeGroups(i, iGroupSize);
                    slotWriter.removeSlots(iDataIndex, i4, i - 1);
                } else {
                    boolean z4 = iParent >= 0;
                    if (z4) {
                        slotWriter.startGroup();
                        slotWriter.advanceBy(iParent - slotWriter.getCurrentGroup());
                        slotWriter.startGroup();
                    }
                    slotWriter.advanceBy(i - slotWriter.getCurrentGroup());
                    boolean zRemoveGroup = slotWriter.removeGroup();
                    if (z4) {
                        slotWriter.skipToGroupEnd();
                        slotWriter.endGroup();
                        slotWriter.skipToGroupEnd();
                        slotWriter.endGroup();
                    }
                    zRemoveGroups = zRemoveGroup;
                }
            }
            if (zRemoveGroups) {
                ComposerKt.composeImmediateRuntimeError("Unexpectedly removed anchors");
            }
            slotWriter2.nodeCount += SlotTableKt.isNode(iArr, currentGroup) ? 1 : SlotTableKt.nodeCount(iArr, currentGroup);
            if (z2) {
                slotWriter2.currentGroup = i13;
                slotWriter2.currentSlot = i5 + i4;
            }
            if (zContainsAnyGroupMarks) {
                slotWriter2.updateContainsMark(parent);
            }
            return listEmptyList;
        }
    }

    public final List moveFrom(SlotTable slotTable, int i, boolean z) {
        ComposerKt.runtimeCheck(this.insertCount > 0);
        if (i != 0 || this.currentGroup != 0 || this.table.getGroupsSize() != 0 || SlotTableKt.groupSize(slotTable.getGroups(), i) != slotTable.getGroupsSize()) {
            SlotWriter slotWriterOpenWriter = slotTable.openWriter();
            try {
                List listMoveGroup = Companion.moveGroup(slotWriterOpenWriter, i, this, true, true, z);
                slotWriterOpenWriter.close(true);
                return listMoveGroup;
            } catch (Throwable th) {
                slotWriterOpenWriter.close(false);
                throw th;
            }
        }
        int[] iArr = this.groups;
        Object[] objArr = this.slots;
        ArrayList arrayList = this.anchors;
        HashMap map = this.sourceInformationMap;
        MutableIntObjectMap mutableIntObjectMap = this.calledByMap;
        int[] groups = slotTable.getGroups();
        int groupsSize = slotTable.getGroupsSize();
        Object[] slots = slotTable.getSlots();
        int slotsSize = slotTable.getSlotsSize();
        HashMap sourceInformationMap$runtime_release = slotTable.getSourceInformationMap$runtime_release();
        MutableIntObjectMap calledByMap$runtime_release = slotTable.getCalledByMap$runtime_release();
        this.groups = groups;
        this.slots = slots;
        this.anchors = slotTable.getAnchors$runtime_release();
        this.groupGapStart = groupsSize;
        this.groupGapLen = (groups.length / 5) - groupsSize;
        this.slotsGapStart = slotsSize;
        this.slotsGapLen = slots.length - slotsSize;
        this.slotsGapOwner = groupsSize;
        this.sourceInformationMap = sourceInformationMap$runtime_release;
        this.calledByMap = calledByMap$runtime_release;
        slotTable.setTo$runtime_release(iArr, 0, objArr, 0, arrayList, map, mutableIntObjectMap);
        return this.anchors;
    }

    public final List moveIntoGroupFrom(int i, SlotTable slotTable, int i2) {
        ComposerKt.runtimeCheck(this.insertCount <= 0 && groupSize(this.currentGroup + i) == 1);
        int i3 = this.currentGroup;
        int i4 = this.currentSlot;
        int i5 = this.currentSlotEnd;
        advanceBy(i);
        startGroup();
        beginInsert();
        SlotWriter slotWriterOpenWriter = slotTable.openWriter();
        try {
            List listMoveGroup$default = Companion.moveGroup$default(Companion, slotWriterOpenWriter, i2, this, false, true, false, 32, null);
            slotWriterOpenWriter.close(true);
            endInsert();
            endGroup();
            this.currentGroup = i3;
            this.currentSlot = i4;
            this.currentSlotEnd = i5;
            return listMoveGroup$default;
        } catch (Throwable th) {
            slotWriterOpenWriter.close(false);
            throw th;
        }
    }

    public final Anchor anchor(int i) {
        ArrayList arrayList = this.anchors;
        int iSearch = SlotTableKt.search(arrayList, i, getSize$runtime_release());
        if (iSearch < 0) {
            if (i > this.groupGapStart) {
                i = -(getSize$runtime_release() - i);
            }
            Anchor anchor = new Anchor(i);
            arrayList.add(-(iSearch + 1), anchor);
            return anchor;
        }
        return (Anchor) arrayList.get(iSearch);
    }

    public static /* synthetic */ void markGroup$default(SlotWriter slotWriter, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = slotWriter.parent;
        }
        slotWriter.markGroup(i);
    }

    public final void markGroup(int i) {
        int iGroupIndexToAddress = groupIndexToAddress(i);
        if (SlotTableKt.hasMark(this.groups, iGroupIndexToAddress)) {
            return;
        }
        SlotTableKt.updateMark(this.groups, iGroupIndexToAddress, true);
        if (SlotTableKt.containsMark(this.groups, iGroupIndexToAddress)) {
            return;
        }
        updateContainsMark(parent(i));
    }

    private final boolean containsGroupMark(int i) {
        return i >= 0 && SlotTableKt.containsMark(this.groups, groupIndexToAddress(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean containsAnyGroupMarks(int i) {
        return i >= 0 && SlotTableKt.containsAnyMark(this.groups, groupIndexToAddress(i));
    }

    private final void recalculateMarks() {
        PrioritySet prioritySet = this.pendingRecalculateMarks;
        if (prioritySet != null) {
            while (prioritySet.isNotEmpty()) {
                updateContainsMarkNow(prioritySet.takeMax(), prioritySet);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateContainsMark(int i) {
        if (i >= 0) {
            PrioritySet prioritySet = this.pendingRecalculateMarks;
            if (prioritySet == null) {
                prioritySet = new PrioritySet(null, 1, 0 == true ? 1 : 0);
                this.pendingRecalculateMarks = prioritySet;
            }
            prioritySet.add(i);
        }
    }

    private final void updateContainsMarkNow(int i, PrioritySet prioritySet) {
        int iGroupIndexToAddress = groupIndexToAddress(i);
        boolean zChildContainsAnyMarks = childContainsAnyMarks(i);
        if (SlotTableKt.containsMark(this.groups, iGroupIndexToAddress) != zChildContainsAnyMarks) {
            SlotTableKt.updateContainsMark(this.groups, iGroupIndexToAddress, zChildContainsAnyMarks);
            int iParent = parent(i);
            if (iParent >= 0) {
                prioritySet.add(iParent);
            }
        }
    }

    private final boolean childContainsAnyMarks(int i) {
        int iGroupSize = i + 1;
        int iGroupSize2 = i + groupSize(i);
        while (iGroupSize < iGroupSize2) {
            if (SlotTableKt.containsAnyMark(this.groups, groupIndexToAddress(iGroupSize))) {
                return true;
            }
            iGroupSize += groupSize(iGroupSize);
        }
        return false;
    }

    public final int anchorIndex(Anchor anchor) {
        int location$runtime_release = anchor.getLocation$runtime_release();
        return location$runtime_release < 0 ? location$runtime_release + getSize$runtime_release() : location$runtime_release;
    }

    public String toString() {
        return "SlotWriter(current = " + this.currentGroup + " end=" + this.currentGroupEnd + " size = " + getSize$runtime_release() + " gap=" + this.groupGapStart + CoreConstants.DASH_CHAR + (this.groupGapStart + this.groupGapLen) + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    private final void saveCurrentGroupEnd() {
        this.endStack.push((getCapacity() - this.groupGapLen) - this.currentGroupEnd);
    }

    private final int restoreCurrentGroupEnd() {
        int capacity = (getCapacity() - this.groupGapLen) - this.endStack.pop();
        this.currentGroupEnd = capacity;
        return capacity;
    }

    private final void fixParentAnchorsFor(int i, int i2, int i3) {
        int iParentIndexToAnchor = parentIndexToAnchor(i, this.groupGapStart);
        while (i3 < i2) {
            SlotTableKt.updateParentAnchor(this.groups, groupIndexToAddress(i3), iParentIndexToAnchor);
            int iGroupSize = SlotTableKt.groupSize(this.groups, groupIndexToAddress(i3)) + i3;
            fixParentAnchorsFor(i3, iGroupSize, i3 + 1);
            i3 = iGroupSize;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void moveGroupGapTo(int i) {
        int i2 = this.groupGapLen;
        int i3 = this.groupGapStart;
        if (i3 != i) {
            if (!this.anchors.isEmpty()) {
                updateAnchors(i3, i);
            }
            if (i2 > 0) {
                int[] iArr = this.groups;
                int i4 = i * 5;
                int i5 = i2 * 5;
                int i6 = i3 * 5;
                if (i < i3) {
                    ArraysKt.copyInto(iArr, iArr, i5 + i4, i4, i6);
                } else {
                    ArraysKt.copyInto(iArr, iArr, i6, i6 + i5, i4 + i5);
                }
            }
            if (i < i3) {
                i3 = i + i2;
            }
            int capacity = getCapacity();
            ComposerKt.runtimeCheck(i3 < capacity);
            while (i3 < capacity) {
                int iParentAnchor = SlotTableKt.parentAnchor(this.groups, i3);
                int iParentIndexToAnchor = parentIndexToAnchor(parentAnchorToIndex(iParentAnchor), i);
                if (iParentIndexToAnchor != iParentAnchor) {
                    SlotTableKt.updateParentAnchor(this.groups, i3, iParentIndexToAnchor);
                }
                i3++;
                if (i3 == i) {
                    i3 += i2;
                }
            }
        }
        this.groupGapStart = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void moveSlotGapTo(int i, int i2) {
        int i3 = this.slotsGapLen;
        int i4 = this.slotsGapStart;
        int i5 = this.slotsGapOwner;
        if (i4 != i) {
            Object[] objArr = this.slots;
            if (i < i4) {
                ArraysKt.copyInto(objArr, objArr, i + i3, i, i4);
            } else {
                ArraysKt.copyInto(objArr, objArr, i4, i4 + i3, i + i3);
            }
        }
        int iMin = Math.min(i2 + 1, getSize$runtime_release());
        if (i5 != iMin) {
            int length = this.slots.length - i3;
            if (iMin < i5) {
                int iGroupIndexToAddress = groupIndexToAddress(iMin);
                int iGroupIndexToAddress2 = groupIndexToAddress(i5);
                int i6 = this.groupGapStart;
                while (iGroupIndexToAddress < iGroupIndexToAddress2) {
                    int iDataAnchor = SlotTableKt.dataAnchor(this.groups, iGroupIndexToAddress);
                    if (!(iDataAnchor >= 0)) {
                        ComposerKt.composeImmediateRuntimeError("Unexpected anchor value, expected a positive anchor");
                    }
                    SlotTableKt.updateDataAnchor(this.groups, iGroupIndexToAddress, -((length - iDataAnchor) + 1));
                    iGroupIndexToAddress++;
                    if (iGroupIndexToAddress == i6) {
                        iGroupIndexToAddress += this.groupGapLen;
                    }
                }
            } else {
                int iGroupIndexToAddress3 = groupIndexToAddress(i5);
                int iGroupIndexToAddress4 = groupIndexToAddress(iMin);
                while (iGroupIndexToAddress3 < iGroupIndexToAddress4) {
                    int iDataAnchor2 = SlotTableKt.dataAnchor(this.groups, iGroupIndexToAddress3);
                    if (!(iDataAnchor2 < 0)) {
                        ComposerKt.composeImmediateRuntimeError("Unexpected anchor value, expected a negative anchor");
                    }
                    SlotTableKt.updateDataAnchor(this.groups, iGroupIndexToAddress3, iDataAnchor2 + length + 1);
                    iGroupIndexToAddress3++;
                    if (iGroupIndexToAddress3 == this.groupGapStart) {
                        iGroupIndexToAddress3 += this.groupGapLen;
                    }
                }
            }
            this.slotsGapOwner = iMin;
        }
        this.slotsGapStart = i;
    }

    private final void clearSlotGap() {
        int i = this.slotsGapStart;
        ArraysKt.fill(this.slots, (Object) null, i, this.slotsGapLen + i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void insertGroups(int i) {
        if (i > 0) {
            int i2 = this.currentGroup;
            moveGroupGapTo(i2);
            int i3 = this.groupGapStart;
            int i4 = this.groupGapLen;
            int[] iArr = this.groups;
            int length = iArr.length / 5;
            int i5 = length - i4;
            if (i4 < i) {
                int iMax = Math.max(Math.max(length * 2, i5 + i), 32);
                int[] iArr2 = new int[iMax * 5];
                int i6 = iMax - i5;
                ArraysKt.copyInto(iArr, iArr2, 0, 0, i3 * 5);
                ArraysKt.copyInto(iArr, iArr2, (i3 + i6) * 5, (i4 + i3) * 5, length * 5);
                this.groups = iArr2;
                i4 = i6;
            }
            int i7 = this.currentGroupEnd;
            if (i7 >= i3) {
                this.currentGroupEnd = i7 + i;
            }
            int i8 = i3 + i;
            this.groupGapStart = i8;
            this.groupGapLen = i4 - i;
            int iDataIndexToDataAnchor = dataIndexToDataAnchor(i5 > 0 ? dataIndex(i2 + i) : 0, this.slotsGapOwner >= i3 ? this.slotsGapStart : 0, this.slotsGapLen, this.slots.length);
            for (int i9 = i3; i9 < i8; i9++) {
                SlotTableKt.updateDataAnchor(this.groups, i9, iDataIndexToDataAnchor);
            }
            int i10 = this.slotsGapOwner;
            if (i10 >= i3) {
                this.slotsGapOwner = i10 + i;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void insertSlots(int i, int i2) {
        if (i > 0) {
            moveSlotGapTo(this.currentSlot, i2);
            int i3 = this.slotsGapStart;
            int i4 = this.slotsGapLen;
            if (i4 < i) {
                Object[] objArr = this.slots;
                int length = objArr.length;
                int i5 = length - i4;
                int iMax = Math.max(Math.max(length * 2, i5 + i), 32);
                Object[] objArr2 = new Object[iMax];
                for (int i6 = 0; i6 < iMax; i6++) {
                    objArr2[i6] = null;
                }
                int i7 = iMax - i5;
                ArraysKt.copyInto(objArr, objArr2, 0, 0, i3);
                ArraysKt.copyInto(objArr, objArr2, i3 + i7, i4 + i3, length);
                this.slots = objArr2;
                i4 = i7;
            }
            int i8 = this.currentSlotEnd;
            if (i8 >= i3) {
                this.currentSlotEnd = i8 + i;
            }
            this.slotsGapStart = i3 + i;
            this.slotsGapLen = i4 - i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean removeGroups(int i, int i2) {
        if (i2 > 0) {
            ArrayList arrayList = this.anchors;
            moveGroupGapTo(i);
            zRemoveAnchors = arrayList.isEmpty() ? false : removeAnchors(i, i2, this.sourceInformationMap);
            this.groupGapStart = i;
            this.groupGapLen += i2;
            int i3 = this.slotsGapOwner;
            if (i3 > i) {
                this.slotsGapOwner = Math.max(i, i3 - i2);
            }
            int i4 = this.currentGroupEnd;
            if (i4 >= this.groupGapStart) {
                this.currentGroupEnd = i4 - i2;
            }
            int i5 = this.parent;
            if (containsGroupMark(i5)) {
                updateContainsMark(i5);
            }
        }
        return zRemoveAnchors;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final GroupSourceInformation sourceInformationOf(int i) {
        Anchor anchorTryAnchor$runtime_release;
        HashMap map = this.sourceInformationMap;
        if (map == null || (anchorTryAnchor$runtime_release = tryAnchor$runtime_release(i)) == null) {
            return null;
        }
        return (GroupSourceInformation) map.get(anchorTryAnchor$runtime_release);
    }

    public final Anchor tryAnchor$runtime_release(int i) {
        if (i < 0 || i >= getSize$runtime_release()) {
            return null;
        }
        return SlotTableKt.find(this.anchors, i, getSize$runtime_release());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeSlots(int i, int i2, int i3) {
        if (i2 > 0) {
            int i4 = this.slotsGapLen;
            int i5 = i + i2;
            moveSlotGapTo(i5, i3);
            this.slotsGapStart = i;
            this.slotsGapLen = i4 + i2;
            ArraysKt.fill(this.slots, (Object) null, i, i5);
            int i6 = this.currentSlotEnd;
            if (i6 >= i) {
                this.currentSlotEnd = i6 - i2;
            }
        }
    }

    private final void updateNodeOfGroup(int i, Object obj) {
        int iGroupIndexToAddress = groupIndexToAddress(i);
        int[] iArr = this.groups;
        if (!(iGroupIndexToAddress < iArr.length && SlotTableKt.isNode(iArr, iGroupIndexToAddress))) {
            ComposerKt.composeImmediateRuntimeError("Updating the node of a group at " + i + " that was not created with as a node group");
        }
        this.slots[dataIndexToDataAddress(nodeIndex(this.groups, iGroupIndexToAddress))] = obj;
    }

    private final void updateAnchors(int i, int i2) {
        Anchor anchor;
        int location$runtime_release;
        Anchor anchor2;
        int location$runtime_release2;
        int i3;
        int capacity = getCapacity() - this.groupGapLen;
        if (i < i2) {
            for (int iLocationOf = SlotTableKt.locationOf(this.anchors, i, capacity); iLocationOf < this.anchors.size() && (location$runtime_release2 = (anchor2 = (Anchor) this.anchors.get(iLocationOf)).getLocation$runtime_release()) < 0 && (i3 = location$runtime_release2 + capacity) < i2; iLocationOf++) {
                anchor2.setLocation$runtime_release(i3);
            }
            return;
        }
        for (int iLocationOf2 = SlotTableKt.locationOf(this.anchors, i2, capacity); iLocationOf2 < this.anchors.size() && (location$runtime_release = (anchor = (Anchor) this.anchors.get(iLocationOf2)).getLocation$runtime_release()) >= 0; iLocationOf2++) {
            anchor.setLocation$runtime_release(-(capacity - location$runtime_release));
        }
    }

    private final boolean removeAnchors(int i, int i2, HashMap map) {
        int i3 = i2 + i;
        int iLocationOf = SlotTableKt.locationOf(this.anchors, i3, getCapacity() - this.groupGapLen);
        if (iLocationOf >= this.anchors.size()) {
            iLocationOf--;
        }
        int i4 = iLocationOf + 1;
        int i5 = 0;
        while (iLocationOf >= 0) {
            Anchor anchor = (Anchor) this.anchors.get(iLocationOf);
            int iAnchorIndex = anchorIndex(anchor);
            if (iAnchorIndex < i) {
                break;
            }
            if (iAnchorIndex < i3) {
                anchor.setLocation$runtime_release(Integer.MIN_VALUE);
                if (map != null) {
                }
                if (i5 == 0) {
                    i5 = iLocationOf + 1;
                }
                i4 = iLocationOf;
            }
            iLocationOf--;
        }
        boolean z = i4 < i5;
        if (z) {
            this.anchors.subList(i4, i5).clear();
        }
        return z;
    }

    private final void moveAnchors(int i, int i2, int i3) {
        Anchor anchor;
        int iAnchorIndex;
        int i4 = i3 + i;
        int size$runtime_release = getSize$runtime_release();
        int iLocationOf = SlotTableKt.locationOf(this.anchors, i, size$runtime_release);
        ArrayList arrayList = new ArrayList();
        if (iLocationOf >= 0) {
            while (iLocationOf < this.anchors.size() && (iAnchorIndex = anchorIndex((anchor = (Anchor) this.anchors.get(iLocationOf)))) >= i && iAnchorIndex < i4) {
                arrayList.add(anchor);
                this.anchors.remove(iLocationOf);
            }
        }
        int i5 = i2 - i;
        int size = arrayList.size();
        for (int i6 = 0; i6 < size; i6++) {
            Anchor anchor2 = (Anchor) arrayList.get(i6);
            int iAnchorIndex2 = anchorIndex(anchor2) + i5;
            if (iAnchorIndex2 >= this.groupGapStart) {
                anchor2.setLocation$runtime_release(-(size$runtime_release - iAnchorIndex2));
            } else {
                anchor2.setLocation$runtime_release(iAnchorIndex2);
            }
            this.anchors.add(SlotTableKt.locationOf(this.anchors, iAnchorIndex2, size$runtime_release), anchor2);
        }
    }

    public final int getSize$runtime_release() {
        return getCapacity() - this.groupGapLen;
    }

    private final int getCapacity() {
        return this.groups.length / 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int groupIndexToAddress(int i) {
        return i < this.groupGapStart ? i : i + this.groupGapLen;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dataIndexToDataAddress(int i) {
        return i < this.slotsGapStart ? i : i + this.slotsGapLen;
    }

    private final int parent(int[] iArr, int i) {
        return parentAnchorToIndex(SlotTableKt.parentAnchor(iArr, groupIndexToAddress(i)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dataIndex(int i) {
        return dataIndex(this.groups, groupIndexToAddress(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dataIndex(int[] iArr, int i) {
        return i >= getCapacity() ? this.slots.length - this.slotsGapLen : dataAnchorToDataIndex(SlotTableKt.dataAnchor(iArr, i), this.slotsGapLen, this.slots.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int slotIndex(int[] iArr, int i) {
        return i >= getCapacity() ? this.slots.length - this.slotsGapLen : dataAnchorToDataIndex(SlotTableKt.slotAnchor(iArr, i), this.slotsGapLen, this.slots.length);
    }

    private final void updateDataIndex(int[] iArr, int i, int i2) {
        SlotTableKt.updateDataAnchor(iArr, i, dataIndexToDataAnchor(i2, this.slotsGapStart, this.slotsGapLen, this.slots.length));
    }

    private final int nodeIndex(int[] iArr, int i) {
        return dataIndex(iArr, i);
    }

    private final int auxIndex(int[] iArr, int i) {
        return dataIndex(iArr, i) + SlotTableKt.countOneBits(SlotTableKt.groupInfo(iArr, i) >> 29);
    }

    private final int parentIndexToAnchor(int i, int i2) {
        return i < i2 ? i : -((getSize$runtime_release() - i) + 2);
    }

    private final int parentAnchorToIndex(int i) {
        return i > -2 ? i : getSize$runtime_release() + i + 2;
    }
}
