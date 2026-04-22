package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.CommonFunctionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.DeltaCounter;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.MutabilityOwnership;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: TrieNode.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TrieNode {
    private Object[] buffer;
    private int dataMap;
    private int nodeMap;
    private final MutabilityOwnership ownedBy;
    public static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final TrieNode EMPTY = new TrieNode(0, 0, new Object[0]);

    public TrieNode(int i, int i2, Object[] objArr, MutabilityOwnership mutabilityOwnership) {
        this.dataMap = i;
        this.nodeMap = i2;
        this.ownedBy = mutabilityOwnership;
        this.buffer = objArr;
    }

    public TrieNode(int i, int i2, Object[] objArr) {
        this(i, i2, objArr, null);
    }

    /* JADX INFO: compiled from: TrieNode.kt */
    public static final class ModificationResult {
        private TrieNode node;
        private final int sizeDelta;

        public ModificationResult(TrieNode trieNode, int i) {
            this.node = trieNode;
            this.sizeDelta = i;
        }

        public final TrieNode getNode() {
            return this.node;
        }

        public final int getSizeDelta() {
            return this.sizeDelta;
        }

        public final void setNode(TrieNode trieNode) {
            this.node = trieNode;
        }
    }

    private final ModificationResult asInsertResult() {
        return new ModificationResult(this, 1);
    }

    private final ModificationResult asUpdateResult() {
        return new ModificationResult(this, 0);
    }

    public final Object[] getBuffer$runtime_release() {
        return this.buffer;
    }

    public final int entryCount$runtime_release() {
        return Integer.bitCount(this.dataMap);
    }

    public final boolean hasEntryAt$runtime_release(int i) {
        return (i & this.dataMap) != 0;
    }

    private final boolean hasNodeAt(int i) {
        return (i & this.nodeMap) != 0;
    }

    public final int entryKeyIndex$runtime_release(int i) {
        return Integer.bitCount((i - 1) & this.dataMap) * 2;
    }

    public final int nodeIndex$runtime_release(int i) {
        return (this.buffer.length - 1) - Integer.bitCount((i - 1) & this.nodeMap);
    }

    private final Object keyAtIndex(int i) {
        return this.buffer[i];
    }

    private final Object valueAtKeyIndex(int i) {
        return this.buffer[i + 1];
    }

    public final TrieNode nodeAtIndex$runtime_release(int i) {
        Object obj = this.buffer[i];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode<K of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode, V of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode>");
        return (TrieNode) obj;
    }

    private final TrieNode insertEntryAt(int i, Object obj, Object obj2) {
        return new TrieNode(i | this.dataMap, this.nodeMap, TrieNodeKt.insertEntryAtIndex(this.buffer, entryKeyIndex$runtime_release(i), obj, obj2));
    }

    private final TrieNode mutableInsertEntryAt(int i, Object obj, Object obj2, MutabilityOwnership mutabilityOwnership) {
        int iEntryKeyIndex$runtime_release = entryKeyIndex$runtime_release(i);
        if (this.ownedBy == mutabilityOwnership) {
            this.buffer = TrieNodeKt.insertEntryAtIndex(this.buffer, iEntryKeyIndex$runtime_release, obj, obj2);
            this.dataMap = i | this.dataMap;
            return this;
        }
        return new TrieNode(i | this.dataMap, this.nodeMap, TrieNodeKt.insertEntryAtIndex(this.buffer, iEntryKeyIndex$runtime_release, obj, obj2), mutabilityOwnership);
    }

    private final TrieNode updateValueAtIndex(int i, Object obj) {
        Object[] objArr = this.buffer;
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, size)");
        objArrCopyOf[i + 1] = obj;
        return new TrieNode(this.dataMap, this.nodeMap, objArrCopyOf);
    }

    private final TrieNode mutableUpdateValueAtIndex(int i, Object obj, PersistentHashMapBuilder persistentHashMapBuilder) {
        if (this.ownedBy == persistentHashMapBuilder.getOwnership()) {
            this.buffer[i + 1] = obj;
            return this;
        }
        persistentHashMapBuilder.setModCount$runtime_release(persistentHashMapBuilder.getModCount$runtime_release() + 1);
        Object[] objArr = this.buffer;
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, size)");
        objArrCopyOf[i + 1] = obj;
        return new TrieNode(this.dataMap, this.nodeMap, objArrCopyOf, persistentHashMapBuilder.getOwnership());
    }

    private final TrieNode updateNodeAtIndex(int i, int i2, TrieNode trieNode) {
        Object[] objArr = trieNode.buffer;
        if (objArr.length == 2 && trieNode.nodeMap == 0) {
            if (this.buffer.length == 1) {
                trieNode.dataMap = this.nodeMap;
                return trieNode;
            }
            return new TrieNode(this.dataMap ^ i2, i2 ^ this.nodeMap, TrieNodeKt.replaceNodeWithEntry(this.buffer, i, entryKeyIndex$runtime_release(i2), objArr[0], objArr[1]));
        }
        Object[] objArr2 = this.buffer;
        Object[] objArrCopyOf = Arrays.copyOf(objArr2, objArr2.length);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
        objArrCopyOf[i] = trieNode;
        return new TrieNode(this.dataMap, this.nodeMap, objArrCopyOf);
    }

    private final TrieNode mutableUpdateNodeAtIndex(int i, TrieNode trieNode, MutabilityOwnership mutabilityOwnership) {
        Object[] objArr = this.buffer;
        if (objArr.length == 1 && trieNode.buffer.length == 2 && trieNode.nodeMap == 0) {
            trieNode.dataMap = this.nodeMap;
            return trieNode;
        }
        if (this.ownedBy == mutabilityOwnership) {
            objArr[i] = trieNode;
            return this;
        }
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, size)");
        objArrCopyOf[i] = trieNode;
        return new TrieNode(this.dataMap, this.nodeMap, objArrCopyOf, mutabilityOwnership);
    }

    private final TrieNode removeNodeAtIndex(int i, int i2) {
        Object[] objArr = this.buffer;
        if (objArr.length == 1) {
            return null;
        }
        return new TrieNode(this.dataMap, i2 ^ this.nodeMap, TrieNodeKt.removeNodeAtIndex(objArr, i));
    }

    private final TrieNode mutableRemoveNodeAtIndex(int i, int i2, MutabilityOwnership mutabilityOwnership) {
        Object[] objArr = this.buffer;
        if (objArr.length == 1) {
            return null;
        }
        if (this.ownedBy == mutabilityOwnership) {
            this.buffer = TrieNodeKt.removeNodeAtIndex(objArr, i);
            this.nodeMap ^= i2;
            return this;
        }
        return new TrieNode(this.dataMap, i2 ^ this.nodeMap, TrieNodeKt.removeNodeAtIndex(objArr, i), mutabilityOwnership);
    }

    private final Object[] bufferMoveEntryToNode(int i, int i2, int i3, Object obj, Object obj2, int i4, MutabilityOwnership mutabilityOwnership) {
        Object objKeyAtIndex = keyAtIndex(i);
        return TrieNodeKt.replaceEntryWithNode(this.buffer, i, nodeIndex$runtime_release(i2) + 1, makeNode(objKeyAtIndex != null ? objKeyAtIndex.hashCode() : 0, objKeyAtIndex, valueAtKeyIndex(i), i3, obj, obj2, i4 + 5, mutabilityOwnership));
    }

    private final TrieNode moveEntryToNode(int i, int i2, int i3, Object obj, Object obj2, int i4) {
        return new TrieNode(this.dataMap ^ i2, i2 | this.nodeMap, bufferMoveEntryToNode(i, i2, i3, obj, obj2, i4, null));
    }

    private final TrieNode mutableMoveEntryToNode(int i, int i2, int i3, Object obj, Object obj2, int i4, MutabilityOwnership mutabilityOwnership) {
        if (this.ownedBy == mutabilityOwnership) {
            this.buffer = bufferMoveEntryToNode(i, i2, i3, obj, obj2, i4, mutabilityOwnership);
            this.dataMap ^= i2;
            this.nodeMap |= i2;
            return this;
        }
        return new TrieNode(this.dataMap ^ i2, i2 | this.nodeMap, bufferMoveEntryToNode(i, i2, i3, obj, obj2, i4, mutabilityOwnership), mutabilityOwnership);
    }

    private final TrieNode makeNode(int i, Object obj, Object obj2, int i2, Object obj3, Object obj4, int i3, MutabilityOwnership mutabilityOwnership) {
        Object[] objArr;
        if (i3 > 30) {
            return new TrieNode(0, 0, new Object[]{obj, obj2, obj3, obj4}, mutabilityOwnership);
        }
        int iIndexSegment = TrieNodeKt.indexSegment(i, i3);
        int iIndexSegment2 = TrieNodeKt.indexSegment(i2, i3);
        if (iIndexSegment != iIndexSegment2) {
            if (iIndexSegment < iIndexSegment2) {
                objArr = new Object[]{obj, obj2, obj3, obj4};
            } else {
                objArr = new Object[]{obj3, obj4, obj, obj2};
            }
            return new TrieNode((1 << iIndexSegment) | (1 << iIndexSegment2), 0, objArr, mutabilityOwnership);
        }
        return new TrieNode(0, 1 << iIndexSegment, new Object[]{makeNode(i, obj, obj2, i2, obj3, obj4, i3 + 5, mutabilityOwnership)}, mutabilityOwnership);
    }

    private final TrieNode removeEntryAtIndex(int i, int i2) {
        Object[] objArr = this.buffer;
        if (objArr.length == 2) {
            return null;
        }
        return new TrieNode(i2 ^ this.dataMap, this.nodeMap, TrieNodeKt.removeEntryAtIndex(objArr, i));
    }

    private final TrieNode mutableRemoveEntryAtIndex(int i, int i2, PersistentHashMapBuilder persistentHashMapBuilder) {
        persistentHashMapBuilder.setSize(persistentHashMapBuilder.size() - 1);
        persistentHashMapBuilder.setOperationResult$runtime_release(valueAtKeyIndex(i));
        if (this.buffer.length == 2) {
            return null;
        }
        if (this.ownedBy == persistentHashMapBuilder.getOwnership()) {
            this.buffer = TrieNodeKt.removeEntryAtIndex(this.buffer, i);
            this.dataMap ^= i2;
            return this;
        }
        return new TrieNode(i2 ^ this.dataMap, this.nodeMap, TrieNodeKt.removeEntryAtIndex(this.buffer, i), persistentHashMapBuilder.getOwnership());
    }

    private final TrieNode collisionRemoveEntryAtIndex(int i) {
        Object[] objArr = this.buffer;
        if (objArr.length == 2) {
            return null;
        }
        return new TrieNode(0, 0, TrieNodeKt.removeEntryAtIndex(objArr, i));
    }

    private final TrieNode mutableCollisionRemoveEntryAtIndex(int i, PersistentHashMapBuilder persistentHashMapBuilder) {
        persistentHashMapBuilder.setSize(persistentHashMapBuilder.size() - 1);
        persistentHashMapBuilder.setOperationResult$runtime_release(valueAtKeyIndex(i));
        if (this.buffer.length == 2) {
            return null;
        }
        if (this.ownedBy == persistentHashMapBuilder.getOwnership()) {
            this.buffer = TrieNodeKt.removeEntryAtIndex(this.buffer, i);
            return this;
        }
        return new TrieNode(0, 0, TrieNodeKt.removeEntryAtIndex(this.buffer, i), persistentHashMapBuilder.getOwnership());
    }

    private final boolean collisionContainsKey(Object obj) {
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, this.buffer.length), 2);
        int first = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
            while (!Intrinsics.areEqual(obj, this.buffer[first])) {
                if (first != last) {
                    first += step;
                }
            }
            return true;
        }
        return false;
    }

    private final Object collisionGet(Object obj) {
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, this.buffer.length), 2);
        int first = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step <= 0 || first > last) && (step >= 0 || last > first)) {
            return null;
        }
        while (!Intrinsics.areEqual(obj, keyAtIndex(first))) {
            if (first == last) {
                return null;
            }
            first += step;
        }
        return valueAtKeyIndex(first);
    }

    private final ModificationResult collisionPut(Object obj, Object obj2) {
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, this.buffer.length), 2);
        int first = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
            while (!Intrinsics.areEqual(obj, keyAtIndex(first))) {
                if (first != last) {
                    first += step;
                }
            }
            if (obj2 == valueAtKeyIndex(first)) {
                return null;
            }
            Object[] objArr = this.buffer;
            Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
            Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, size)");
            objArrCopyOf[first + 1] = obj2;
            return new TrieNode(0, 0, objArrCopyOf).asUpdateResult();
        }
        return new TrieNode(0, 0, TrieNodeKt.insertEntryAtIndex(this.buffer, 0, obj, obj2)).asInsertResult();
    }

    private final TrieNode mutableCollisionPut(Object obj, Object obj2, PersistentHashMapBuilder persistentHashMapBuilder) {
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, this.buffer.length), 2);
        int first = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
            while (!Intrinsics.areEqual(obj, keyAtIndex(first))) {
                if (first != last) {
                    first += step;
                }
            }
            persistentHashMapBuilder.setOperationResult$runtime_release(valueAtKeyIndex(first));
            if (this.ownedBy == persistentHashMapBuilder.getOwnership()) {
                this.buffer[first + 1] = obj2;
                return this;
            }
            persistentHashMapBuilder.setModCount$runtime_release(persistentHashMapBuilder.getModCount$runtime_release() + 1);
            Object[] objArr = this.buffer;
            Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
            Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, size)");
            objArrCopyOf[first + 1] = obj2;
            return new TrieNode(0, 0, objArrCopyOf, persistentHashMapBuilder.getOwnership());
        }
        persistentHashMapBuilder.setSize(persistentHashMapBuilder.size() + 1);
        return new TrieNode(0, 0, TrieNodeKt.insertEntryAtIndex(this.buffer, 0, obj, obj2), persistentHashMapBuilder.getOwnership());
    }

    private final TrieNode collisionRemove(Object obj) {
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, this.buffer.length), 2);
        int first = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
            while (!Intrinsics.areEqual(obj, keyAtIndex(first))) {
                if (first != last) {
                    first += step;
                }
            }
            return collisionRemoveEntryAtIndex(first);
        }
        return this;
    }

    private final TrieNode mutableCollisionRemove(Object obj, PersistentHashMapBuilder persistentHashMapBuilder) {
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, this.buffer.length), 2);
        int first = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
            while (!Intrinsics.areEqual(obj, keyAtIndex(first))) {
                if (first != last) {
                    first += step;
                }
            }
            return mutableCollisionRemoveEntryAtIndex(first, persistentHashMapBuilder);
        }
        return this;
    }

    private final TrieNode mutableCollisionRemove(Object obj, Object obj2, PersistentHashMapBuilder persistentHashMapBuilder) {
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, this.buffer.length), 2);
        int first = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
            while (true) {
                if (!Intrinsics.areEqual(obj, keyAtIndex(first)) || !Intrinsics.areEqual(obj2, valueAtKeyIndex(first))) {
                    if (first == last) {
                        break;
                    }
                    first += step;
                } else {
                    return mutableCollisionRemoveEntryAtIndex(first, persistentHashMapBuilder);
                }
            }
        }
        return this;
    }

    private final TrieNode mutableCollisionPutAll(TrieNode trieNode, DeltaCounter deltaCounter, MutabilityOwnership mutabilityOwnership) {
        CommonFunctionsKt.m1053assert(this.nodeMap == 0);
        CommonFunctionsKt.m1053assert(this.dataMap == 0);
        CommonFunctionsKt.m1053assert(trieNode.nodeMap == 0);
        CommonFunctionsKt.m1053assert(trieNode.dataMap == 0);
        Object[] objArr = this.buffer;
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length + trieNode.buffer.length);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(this, newSize)");
        int length = this.buffer.length;
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, trieNode.buffer.length), 2);
        int first = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
            while (true) {
                if (!collisionContainsKey(trieNode.buffer[first])) {
                    Object[] objArr2 = trieNode.buffer;
                    objArrCopyOf[length] = objArr2[first];
                    objArrCopyOf[length + 1] = objArr2[first + 1];
                    length += 2;
                } else {
                    deltaCounter.setCount(deltaCounter.getCount() + 1);
                }
                if (first == last) {
                    break;
                }
                first += step;
            }
        }
        if (length == this.buffer.length) {
            return this;
        }
        if (length == trieNode.buffer.length) {
            return trieNode;
        }
        if (length == objArrCopyOf.length) {
            return new TrieNode(0, 0, objArrCopyOf, mutabilityOwnership);
        }
        Object[] objArrCopyOf2 = Arrays.copyOf(objArrCopyOf, length);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf2, "copyOf(this, newSize)");
        return new TrieNode(0, 0, objArrCopyOf2, mutabilityOwnership);
    }

    private final TrieNode mutablePutAllFromOtherNodeCell(TrieNode trieNode, int i, int i2, DeltaCounter deltaCounter, PersistentHashMapBuilder persistentHashMapBuilder) {
        if (hasNodeAt(i)) {
            TrieNode trieNodeNodeAtIndex$runtime_release = nodeAtIndex$runtime_release(nodeIndex$runtime_release(i));
            if (trieNode.hasNodeAt(i)) {
                return trieNodeNodeAtIndex$runtime_release.mutablePutAll(trieNode.nodeAtIndex$runtime_release(trieNode.nodeIndex$runtime_release(i)), i2 + 5, deltaCounter, persistentHashMapBuilder);
            }
            if (!trieNode.hasEntryAt$runtime_release(i)) {
                return trieNodeNodeAtIndex$runtime_release;
            }
            int iEntryKeyIndex$runtime_release = trieNode.entryKeyIndex$runtime_release(i);
            Object objKeyAtIndex = trieNode.keyAtIndex(iEntryKeyIndex$runtime_release);
            Object objValueAtKeyIndex = trieNode.valueAtKeyIndex(iEntryKeyIndex$runtime_release);
            int size = persistentHashMapBuilder.size();
            TrieNode trieNodeMutablePut = trieNodeNodeAtIndex$runtime_release.mutablePut(objKeyAtIndex != null ? objKeyAtIndex.hashCode() : 0, objKeyAtIndex, objValueAtKeyIndex, i2 + 5, persistentHashMapBuilder);
            if (persistentHashMapBuilder.size() != size) {
                return trieNodeMutablePut;
            }
            deltaCounter.setCount(deltaCounter.getCount() + 1);
            return trieNodeMutablePut;
        }
        if (trieNode.hasNodeAt(i)) {
            TrieNode trieNodeNodeAtIndex$runtime_release2 = trieNode.nodeAtIndex$runtime_release(trieNode.nodeIndex$runtime_release(i));
            if (hasEntryAt$runtime_release(i)) {
                int iEntryKeyIndex$runtime_release2 = entryKeyIndex$runtime_release(i);
                Object objKeyAtIndex2 = keyAtIndex(iEntryKeyIndex$runtime_release2);
                int i3 = i2 + 5;
                if (!trieNodeNodeAtIndex$runtime_release2.containsKey(objKeyAtIndex2 != null ? objKeyAtIndex2.hashCode() : 0, objKeyAtIndex2, i3)) {
                    return trieNodeNodeAtIndex$runtime_release2.mutablePut(objKeyAtIndex2 != null ? objKeyAtIndex2.hashCode() : 0, objKeyAtIndex2, valueAtKeyIndex(iEntryKeyIndex$runtime_release2), i3, persistentHashMapBuilder);
                }
                deltaCounter.setCount(deltaCounter.getCount() + 1);
            }
            return trieNodeNodeAtIndex$runtime_release2;
        }
        int iEntryKeyIndex$runtime_release3 = entryKeyIndex$runtime_release(i);
        Object objKeyAtIndex3 = keyAtIndex(iEntryKeyIndex$runtime_release3);
        Object objValueAtKeyIndex2 = valueAtKeyIndex(iEntryKeyIndex$runtime_release3);
        int iEntryKeyIndex$runtime_release4 = trieNode.entryKeyIndex$runtime_release(i);
        Object objKeyAtIndex4 = trieNode.keyAtIndex(iEntryKeyIndex$runtime_release4);
        return makeNode(objKeyAtIndex3 != null ? objKeyAtIndex3.hashCode() : 0, objKeyAtIndex3, objValueAtKeyIndex2, objKeyAtIndex4 != null ? objKeyAtIndex4.hashCode() : 0, objKeyAtIndex4, trieNode.valueAtKeyIndex(iEntryKeyIndex$runtime_release4), i2 + 5, persistentHashMapBuilder.getOwnership());
    }

    private final int calculateSize() {
        if (this.nodeMap == 0) {
            return this.buffer.length / 2;
        }
        int iBitCount = Integer.bitCount(this.dataMap);
        int length = this.buffer.length;
        for (int i = iBitCount * 2; i < length; i++) {
            iBitCount += nodeAtIndex$runtime_release(i).calculateSize();
        }
        return iBitCount;
    }

    private final boolean elementsIdentityEquals(TrieNode trieNode) {
        if (this == trieNode) {
            return true;
        }
        if (this.nodeMap != trieNode.nodeMap || this.dataMap != trieNode.dataMap) {
            return false;
        }
        int length = this.buffer.length;
        for (int i = 0; i < length; i++) {
            if (this.buffer[i] != trieNode.buffer[i]) {
                return false;
            }
        }
        return true;
    }

    public final boolean containsKey(int i, Object obj, int i2) {
        int iIndexSegment = 1 << TrieNodeKt.indexSegment(i, i2);
        if (hasEntryAt$runtime_release(iIndexSegment)) {
            return Intrinsics.areEqual(obj, keyAtIndex(entryKeyIndex$runtime_release(iIndexSegment)));
        }
        if (!hasNodeAt(iIndexSegment)) {
            return false;
        }
        TrieNode trieNodeNodeAtIndex$runtime_release = nodeAtIndex$runtime_release(nodeIndex$runtime_release(iIndexSegment));
        if (i2 == 30) {
            return trieNodeNodeAtIndex$runtime_release.collisionContainsKey(obj);
        }
        return trieNodeNodeAtIndex$runtime_release.containsKey(i, obj, i2 + 5);
    }

    public final Object get(int i, Object obj, int i2) {
        int iIndexSegment = 1 << TrieNodeKt.indexSegment(i, i2);
        if (hasEntryAt$runtime_release(iIndexSegment)) {
            int iEntryKeyIndex$runtime_release = entryKeyIndex$runtime_release(iIndexSegment);
            if (Intrinsics.areEqual(obj, keyAtIndex(iEntryKeyIndex$runtime_release))) {
                return valueAtKeyIndex(iEntryKeyIndex$runtime_release);
            }
            return null;
        }
        if (!hasNodeAt(iIndexSegment)) {
            return null;
        }
        TrieNode trieNodeNodeAtIndex$runtime_release = nodeAtIndex$runtime_release(nodeIndex$runtime_release(iIndexSegment));
        if (i2 == 30) {
            return trieNodeNodeAtIndex$runtime_release.collisionGet(obj);
        }
        return trieNodeNodeAtIndex$runtime_release.get(i, obj, i2 + 5);
    }

    public final TrieNode mutablePutAll(TrieNode trieNode, int i, DeltaCounter deltaCounter, PersistentHashMapBuilder persistentHashMapBuilder) {
        if (this == trieNode) {
            deltaCounter.plusAssign(calculateSize());
            return this;
        }
        if (i > 30) {
            return mutableCollisionPutAll(trieNode, deltaCounter, persistentHashMapBuilder.getOwnership());
        }
        int i2 = this.nodeMap | trieNode.nodeMap;
        int i3 = this.dataMap;
        int i4 = trieNode.dataMap;
        int i5 = (i3 ^ i4) & (~i2);
        int i6 = i3 & i4;
        int i7 = i5;
        while (i6 != 0) {
            int iLowestOneBit = Integer.lowestOneBit(i6);
            if (Intrinsics.areEqual(keyAtIndex(entryKeyIndex$runtime_release(iLowestOneBit)), trieNode.keyAtIndex(trieNode.entryKeyIndex$runtime_release(iLowestOneBit)))) {
                i7 |= iLowestOneBit;
            } else {
                i2 |= iLowestOneBit;
            }
            i6 ^= iLowestOneBit;
        }
        int i8 = 0;
        if (!((i2 & i7) == 0)) {
            PreconditionsKt.throwIllegalStateException("Check failed.");
        }
        TrieNode trieNode2 = (Intrinsics.areEqual(this.ownedBy, persistentHashMapBuilder.getOwnership()) && this.dataMap == i7 && this.nodeMap == i2) ? this : new TrieNode(i7, i2, new Object[(Integer.bitCount(i7) * 2) + Integer.bitCount(i2)]);
        int i9 = i2;
        int i10 = 0;
        while (i9 != 0) {
            int iLowestOneBit2 = Integer.lowestOneBit(i9);
            Object[] objArr = trieNode2.buffer;
            objArr[(objArr.length - 1) - i10] = mutablePutAllFromOtherNodeCell(trieNode, iLowestOneBit2, i, deltaCounter, persistentHashMapBuilder);
            i10++;
            i9 ^= iLowestOneBit2;
        }
        while (i7 != 0) {
            int iLowestOneBit3 = Integer.lowestOneBit(i7);
            int i11 = i8 * 2;
            if (!trieNode.hasEntryAt$runtime_release(iLowestOneBit3)) {
                int iEntryKeyIndex$runtime_release = entryKeyIndex$runtime_release(iLowestOneBit3);
                trieNode2.buffer[i11] = keyAtIndex(iEntryKeyIndex$runtime_release);
                trieNode2.buffer[i11 + 1] = valueAtKeyIndex(iEntryKeyIndex$runtime_release);
            } else {
                int iEntryKeyIndex$runtime_release2 = trieNode.entryKeyIndex$runtime_release(iLowestOneBit3);
                trieNode2.buffer[i11] = trieNode.keyAtIndex(iEntryKeyIndex$runtime_release2);
                trieNode2.buffer[i11 + 1] = trieNode.valueAtKeyIndex(iEntryKeyIndex$runtime_release2);
                if (hasEntryAt$runtime_release(iLowestOneBit3)) {
                    deltaCounter.setCount(deltaCounter.getCount() + 1);
                }
            }
            i8++;
            i7 ^= iLowestOneBit3;
        }
        return elementsIdentityEquals(trieNode2) ? this : trieNode.elementsIdentityEquals(trieNode2) ? trieNode : trieNode2;
    }

    public final ModificationResult put(int i, Object obj, Object obj2, int i2) {
        ModificationResult modificationResultPut;
        int iIndexSegment = 1 << TrieNodeKt.indexSegment(i, i2);
        if (hasEntryAt$runtime_release(iIndexSegment)) {
            int iEntryKeyIndex$runtime_release = entryKeyIndex$runtime_release(iIndexSegment);
            if (Intrinsics.areEqual(obj, keyAtIndex(iEntryKeyIndex$runtime_release))) {
                if (valueAtKeyIndex(iEntryKeyIndex$runtime_release) == obj2) {
                    return null;
                }
                return updateValueAtIndex(iEntryKeyIndex$runtime_release, obj2).asUpdateResult();
            }
            return moveEntryToNode(iEntryKeyIndex$runtime_release, iIndexSegment, i, obj, obj2, i2).asInsertResult();
        }
        if (hasNodeAt(iIndexSegment)) {
            int iNodeIndex$runtime_release = nodeIndex$runtime_release(iIndexSegment);
            TrieNode trieNodeNodeAtIndex$runtime_release = nodeAtIndex$runtime_release(iNodeIndex$runtime_release);
            if (i2 == 30) {
                modificationResultPut = trieNodeNodeAtIndex$runtime_release.collisionPut(obj, obj2);
                if (modificationResultPut == null) {
                    return null;
                }
            } else {
                modificationResultPut = trieNodeNodeAtIndex$runtime_release.put(i, obj, obj2, i2 + 5);
                if (modificationResultPut == null) {
                    return null;
                }
            }
            modificationResultPut.setNode(updateNodeAtIndex(iNodeIndex$runtime_release, iIndexSegment, modificationResultPut.getNode()));
            return modificationResultPut;
        }
        return insertEntryAt(iIndexSegment, obj, obj2).asInsertResult();
    }

    public final TrieNode mutablePut(int i, Object obj, Object obj2, int i2, PersistentHashMapBuilder persistentHashMapBuilder) {
        TrieNode trieNodeMutablePut;
        int iIndexSegment = 1 << TrieNodeKt.indexSegment(i, i2);
        if (hasEntryAt$runtime_release(iIndexSegment)) {
            int iEntryKeyIndex$runtime_release = entryKeyIndex$runtime_release(iIndexSegment);
            if (Intrinsics.areEqual(obj, keyAtIndex(iEntryKeyIndex$runtime_release))) {
                persistentHashMapBuilder.setOperationResult$runtime_release(valueAtKeyIndex(iEntryKeyIndex$runtime_release));
                return valueAtKeyIndex(iEntryKeyIndex$runtime_release) == obj2 ? this : mutableUpdateValueAtIndex(iEntryKeyIndex$runtime_release, obj2, persistentHashMapBuilder);
            }
            persistentHashMapBuilder.setSize(persistentHashMapBuilder.size() + 1);
            return mutableMoveEntryToNode(iEntryKeyIndex$runtime_release, iIndexSegment, i, obj, obj2, i2, persistentHashMapBuilder.getOwnership());
        }
        if (hasNodeAt(iIndexSegment)) {
            int iNodeIndex$runtime_release = nodeIndex$runtime_release(iIndexSegment);
            TrieNode trieNodeNodeAtIndex$runtime_release = nodeAtIndex$runtime_release(iNodeIndex$runtime_release);
            if (i2 == 30) {
                trieNodeMutablePut = trieNodeNodeAtIndex$runtime_release.mutableCollisionPut(obj, obj2, persistentHashMapBuilder);
            } else {
                trieNodeMutablePut = trieNodeNodeAtIndex$runtime_release.mutablePut(i, obj, obj2, i2 + 5, persistentHashMapBuilder);
            }
            return trieNodeNodeAtIndex$runtime_release == trieNodeMutablePut ? this : mutableUpdateNodeAtIndex(iNodeIndex$runtime_release, trieNodeMutablePut, persistentHashMapBuilder.getOwnership());
        }
        persistentHashMapBuilder.setSize(persistentHashMapBuilder.size() + 1);
        return mutableInsertEntryAt(iIndexSegment, obj, obj2, persistentHashMapBuilder.getOwnership());
    }

    public final TrieNode remove(int i, Object obj, int i2) {
        TrieNode trieNodeRemove;
        int iIndexSegment = 1 << TrieNodeKt.indexSegment(i, i2);
        if (hasEntryAt$runtime_release(iIndexSegment)) {
            int iEntryKeyIndex$runtime_release = entryKeyIndex$runtime_release(iIndexSegment);
            return Intrinsics.areEqual(obj, keyAtIndex(iEntryKeyIndex$runtime_release)) ? removeEntryAtIndex(iEntryKeyIndex$runtime_release, iIndexSegment) : this;
        }
        if (!hasNodeAt(iIndexSegment)) {
            return this;
        }
        int iNodeIndex$runtime_release = nodeIndex$runtime_release(iIndexSegment);
        TrieNode trieNodeNodeAtIndex$runtime_release = nodeAtIndex$runtime_release(iNodeIndex$runtime_release);
        if (i2 == 30) {
            trieNodeRemove = trieNodeNodeAtIndex$runtime_release.collisionRemove(obj);
        } else {
            trieNodeRemove = trieNodeNodeAtIndex$runtime_release.remove(i, obj, i2 + 5);
        }
        return replaceNode(trieNodeNodeAtIndex$runtime_release, trieNodeRemove, iNodeIndex$runtime_release, iIndexSegment);
    }

    private final TrieNode replaceNode(TrieNode trieNode, TrieNode trieNode2, int i, int i2) {
        if (trieNode2 == null) {
            return removeNodeAtIndex(i, i2);
        }
        return trieNode != trieNode2 ? updateNodeAtIndex(i, i2, trieNode2) : this;
    }

    public final TrieNode mutableRemove(int i, Object obj, int i2, PersistentHashMapBuilder persistentHashMapBuilder) {
        TrieNode trieNodeMutableRemove;
        int iIndexSegment = 1 << TrieNodeKt.indexSegment(i, i2);
        if (hasEntryAt$runtime_release(iIndexSegment)) {
            int iEntryKeyIndex$runtime_release = entryKeyIndex$runtime_release(iIndexSegment);
            return Intrinsics.areEqual(obj, keyAtIndex(iEntryKeyIndex$runtime_release)) ? mutableRemoveEntryAtIndex(iEntryKeyIndex$runtime_release, iIndexSegment, persistentHashMapBuilder) : this;
        }
        if (!hasNodeAt(iIndexSegment)) {
            return this;
        }
        int iNodeIndex$runtime_release = nodeIndex$runtime_release(iIndexSegment);
        TrieNode trieNodeNodeAtIndex$runtime_release = nodeAtIndex$runtime_release(iNodeIndex$runtime_release);
        if (i2 == 30) {
            trieNodeMutableRemove = trieNodeNodeAtIndex$runtime_release.mutableCollisionRemove(obj, persistentHashMapBuilder);
        } else {
            trieNodeMutableRemove = trieNodeNodeAtIndex$runtime_release.mutableRemove(i, obj, i2 + 5, persistentHashMapBuilder);
        }
        return mutableReplaceNode(trieNodeNodeAtIndex$runtime_release, trieNodeMutableRemove, iNodeIndex$runtime_release, iIndexSegment, persistentHashMapBuilder.getOwnership());
    }

    private final TrieNode mutableReplaceNode(TrieNode trieNode, TrieNode trieNode2, int i, int i2, MutabilityOwnership mutabilityOwnership) {
        if (trieNode2 == null) {
            return mutableRemoveNodeAtIndex(i, i2, mutabilityOwnership);
        }
        return (this.ownedBy == mutabilityOwnership || trieNode != trieNode2) ? mutableUpdateNodeAtIndex(i, trieNode2, mutabilityOwnership) : this;
    }

    public final TrieNode mutableRemove(int i, Object obj, Object obj2, int i2, PersistentHashMapBuilder persistentHashMapBuilder) {
        TrieNode trieNodeMutableRemove;
        int iIndexSegment = 1 << TrieNodeKt.indexSegment(i, i2);
        if (hasEntryAt$runtime_release(iIndexSegment)) {
            int iEntryKeyIndex$runtime_release = entryKeyIndex$runtime_release(iIndexSegment);
            return (Intrinsics.areEqual(obj, keyAtIndex(iEntryKeyIndex$runtime_release)) && Intrinsics.areEqual(obj2, valueAtKeyIndex(iEntryKeyIndex$runtime_release))) ? mutableRemoveEntryAtIndex(iEntryKeyIndex$runtime_release, iIndexSegment, persistentHashMapBuilder) : this;
        }
        if (!hasNodeAt(iIndexSegment)) {
            return this;
        }
        int iNodeIndex$runtime_release = nodeIndex$runtime_release(iIndexSegment);
        TrieNode trieNodeNodeAtIndex$runtime_release = nodeAtIndex$runtime_release(iNodeIndex$runtime_release);
        if (i2 == 30) {
            trieNodeMutableRemove = trieNodeNodeAtIndex$runtime_release.mutableCollisionRemove(obj, obj2, persistentHashMapBuilder);
        } else {
            trieNodeMutableRemove = trieNodeNodeAtIndex$runtime_release.mutableRemove(i, obj, obj2, i2 + 5, persistentHashMapBuilder);
        }
        return mutableReplaceNode(trieNodeNodeAtIndex$runtime_release, trieNodeMutableRemove, iNodeIndex$runtime_release, iIndexSegment, persistentHashMapBuilder.getOwnership());
    }

    /* JADX INFO: compiled from: TrieNode.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TrieNode getEMPTY$runtime_release() {
            return TrieNode.EMPTY;
        }
    }
}
