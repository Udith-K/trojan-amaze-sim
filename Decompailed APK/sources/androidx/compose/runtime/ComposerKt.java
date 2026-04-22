package androidx.compose.runtime;

import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSetKt;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Composer.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ComposerKt {
    private static final Object invocation = new OpaqueKey("provider");
    private static final Object provider = new OpaqueKey("provider");
    private static final Object compositionLocalMap = new OpaqueKey("compositionLocalMap");
    private static final Object providerValues = new OpaqueKey("providerValues");
    private static final Object providerMaps = new OpaqueKey("providers");
    private static final Object reference = new OpaqueKey("reference");
    private static final Comparator InvalidationLocationAscending = new Comparator() { // from class: androidx.compose.runtime.ComposerKt$$ExternalSyntheticLambda0
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return ComposerKt.InvalidationLocationAscending$lambda$15((Invalidation) obj, (Invalidation) obj2);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean asBool(int i) {
        return i != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int asInt(boolean z) {
        return z ? 1 : 0;
    }

    public static final boolean isTraceInProgress() {
        return false;
    }

    public static final void traceEventEnd() {
    }

    public static final void traceEventStart(int i, int i2, int i3, String str) {
    }

    public static final void removeCurrentGroup(SlotWriter slotWriter, RememberManager rememberManager) {
        int slotsSize;
        int iDataIndex = slotWriter.dataIndex(slotWriter.groups, slotWriter.groupIndexToAddress(slotWriter.getCurrentGroup() + slotWriter.groupSize(slotWriter.getCurrentGroup())));
        for (int iDataIndex2 = slotWriter.dataIndex(slotWriter.groups, slotWriter.groupIndexToAddress(slotWriter.getCurrentGroup())); iDataIndex2 < iDataIndex; iDataIndex2++) {
            Object obj = slotWriter.slots[slotWriter.dataIndexToDataAddress(iDataIndex2)];
            int iAnchorIndex = -1;
            if (obj instanceof ComposeNodeLifecycleCallback) {
                rememberManager.releasing((ComposeNodeLifecycleCallback) obj, slotWriter.getSlotsSize() - iDataIndex2, -1, -1);
            }
            if (obj instanceof RememberObserverHolder) {
                int slotsSize2 = slotWriter.getSlotsSize() - iDataIndex2;
                RememberObserverHolder rememberObserverHolder = (RememberObserverHolder) obj;
                Anchor after = rememberObserverHolder.getAfter();
                if (after == null || !after.getValid()) {
                    slotsSize = -1;
                } else {
                    iAnchorIndex = slotWriter.anchorIndex(after);
                    slotsSize = slotWriter.getSlotsSize() - slotWriter.slotsEndAllIndex$runtime_release(iAnchorIndex);
                }
                rememberManager.forgetting(rememberObserverHolder.getWrapped(), slotsSize2, iAnchorIndex, slotsSize);
            }
            if (obj instanceof RecomposeScopeImpl) {
                ((RecomposeScopeImpl) obj).release();
            }
        }
        slotWriter.removeGroup();
    }

    public static final boolean isAfterFirstChild(SlotWriter slotWriter) {
        return slotWriter.getCurrentGroup() > slotWriter.getParent() + 1;
    }

    public static final boolean isAfterFirstChild(SlotReader slotReader) {
        return slotReader.getCurrentGroup() > slotReader.getParent() + 1;
    }

    public static final void deactivateCurrentGroup(SlotWriter slotWriter, RememberManager rememberManager) {
        int iAnchorIndex;
        int slotsSize;
        int currentGroup = slotWriter.getCurrentGroup();
        int currentGroupEnd = slotWriter.getCurrentGroupEnd();
        while (currentGroup < currentGroupEnd) {
            Object objNode = slotWriter.node(currentGroup);
            if (objNode instanceof ComposeNodeLifecycleCallback) {
                rememberManager.deactivating((ComposeNodeLifecycleCallback) objNode, slotWriter.getSlotsSize() - slotWriter.slotsStartIndex$runtime_release(currentGroup), -1, -1);
            }
            int iSlotIndex = slotWriter.slotIndex(slotWriter.groups, slotWriter.groupIndexToAddress(currentGroup));
            int i = currentGroup + 1;
            int iDataIndex = slotWriter.dataIndex(slotWriter.groups, slotWriter.groupIndexToAddress(i));
            for (int i2 = iSlotIndex; i2 < iDataIndex; i2++) {
                int i3 = i2 - iSlotIndex;
                Object obj = slotWriter.slots[slotWriter.dataIndexToDataAddress(i2)];
                if (obj instanceof RememberObserverHolder) {
                    RememberObserverHolder rememberObserverHolder = (RememberObserverHolder) obj;
                    RememberObserver wrapped = rememberObserverHolder.getWrapped();
                    if (!(wrapped instanceof ReusableRememberObserver)) {
                        removeData(slotWriter, currentGroup, i3, obj);
                        int slotsSize2 = slotWriter.getSlotsSize() - i3;
                        Anchor after = rememberObserverHolder.getAfter();
                        if (after == null || !after.getValid()) {
                            iAnchorIndex = -1;
                            slotsSize = -1;
                        } else {
                            iAnchorIndex = slotWriter.anchorIndex(after);
                            slotsSize = slotWriter.getSlotsSize() - slotWriter.slotsEndAllIndex$runtime_release(iAnchorIndex);
                        }
                        rememberManager.forgetting(wrapped, slotsSize2, iAnchorIndex, slotsSize);
                    }
                } else if (obj instanceof RecomposeScopeImpl) {
                    removeData(slotWriter, currentGroup, i3, obj);
                    ((RecomposeScopeImpl) obj).release();
                }
            }
            currentGroup = i;
        }
    }

    private static final void removeData(SlotWriter slotWriter, int i, int i2, Object obj) {
        if (obj == slotWriter.set(i, i2, Composer.Companion.getEmpty())) {
            return;
        }
        composeImmediateRuntimeError("Slot table is out of sync");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableScatterMap multiMap(int i) {
        return MutableScatterMultiMap.m1021constructorimpl(new MutableScatterMap(i));
    }

    private static final int findLocation(List list, int i) {
        int size = list.size() - 1;
        int i2 = 0;
        while (i2 <= size) {
            int i3 = (i2 + size) >>> 1;
            int iCompare = Intrinsics.compare(((Invalidation) list.get(i3)).getLocation(), i);
            if (iCompare < 0) {
                i2 = i3 + 1;
            } else {
                if (iCompare <= 0) {
                    return i3;
                }
                size = i3 - 1;
            }
        }
        return -(i2 + 1);
    }

    private static final int findInsertLocation(List list, int i) {
        int iFindLocation = findLocation(list, i);
        return iFindLocation < 0 ? -(iFindLocation + 1) : iFindLocation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void insertIfMissing(List list, int i, RecomposeScopeImpl recomposeScopeImpl, Object obj) {
        int iFindLocation = findLocation(list, i);
        if (iFindLocation < 0) {
            int i2 = -(iFindLocation + 1);
            if (!(obj instanceof DerivedState)) {
                obj = null;
            }
            list.add(i2, new Invalidation(recomposeScopeImpl, i, obj));
            return;
        }
        Invalidation invalidation = (Invalidation) list.get(iFindLocation);
        if (obj instanceof DerivedState) {
            Object instances = invalidation.getInstances();
            if (instances == null) {
                invalidation.setInstances(obj);
                return;
            } else if (instances instanceof MutableScatterSet) {
                ((MutableScatterSet) instances).add(obj);
                return;
            } else {
                invalidation.setInstances(ScatterSetKt.mutableScatterSetOf(instances, obj));
                return;
            }
        }
        invalidation.setInstances(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Invalidation firstInRange(List list, int i, int i2) {
        int iFindInsertLocation = findInsertLocation(list, i);
        if (iFindInsertLocation >= list.size()) {
            return null;
        }
        Invalidation invalidation = (Invalidation) list.get(iFindInsertLocation);
        if (invalidation.getLocation() < i2) {
            return invalidation;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Invalidation removeLocation(List list, int i) {
        int iFindLocation = findLocation(list, i);
        if (iFindLocation >= 0) {
            return (Invalidation) list.remove(iFindLocation);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void removeRange(List list, int i, int i2) {
        int iFindInsertLocation = findInsertLocation(list, i);
        while (iFindInsertLocation < list.size() && ((Invalidation) list.get(iFindInsertLocation)).getLocation() < i2) {
            list.remove(iFindInsertLocation);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List collectNodesFrom(SlotTable slotTable, Anchor anchor) {
        ArrayList arrayList = new ArrayList();
        SlotReader slotReaderOpenReader = slotTable.openReader();
        try {
            collectNodesFrom$lambda$10$collectFromGroup(slotReaderOpenReader, arrayList, slotTable.anchorIndex(anchor));
            Unit unit = Unit.INSTANCE;
            return arrayList;
        } finally {
            slotReaderOpenReader.close();
        }
    }

    private static final void collectNodesFrom$lambda$10$collectFromGroup(SlotReader slotReader, List list, int i) {
        if (slotReader.isNode(i)) {
            list.add(slotReader.node(i));
            return;
        }
        int iGroupSize = i + 1;
        int iGroupSize2 = i + slotReader.groupSize(i);
        while (iGroupSize < iGroupSize2) {
            collectNodesFrom$lambda$10$collectFromGroup(slotReader, list, iGroupSize);
            iGroupSize += slotReader.groupSize(iGroupSize);
        }
    }

    private static final int distanceFrom(SlotReader slotReader, int i, int i2) {
        int i3 = 0;
        while (i > 0 && i != i2) {
            i = slotReader.parent(i);
            i3++;
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int nearestCommonRootOf(SlotReader slotReader, int i, int i2, int i3) {
        if (i == i2) {
            return i;
        }
        if (i == i3 || i2 == i3) {
            return i3;
        }
        if (slotReader.parent(i) == i2) {
            return i2;
        }
        if (slotReader.parent(i2) == i) {
            return i;
        }
        if (slotReader.parent(i) == slotReader.parent(i2)) {
            return slotReader.parent(i);
        }
        int iDistanceFrom = distanceFrom(slotReader, i, i3);
        int iDistanceFrom2 = distanceFrom(slotReader, i2, i3);
        int i4 = iDistanceFrom - iDistanceFrom2;
        for (int i5 = 0; i5 < i4; i5++) {
            i = slotReader.parent(i);
        }
        int i6 = iDistanceFrom2 - iDistanceFrom;
        for (int i7 = 0; i7 < i6; i7++) {
            i2 = slotReader.parent(i2);
        }
        while (i != i2) {
            i = slotReader.parent(i);
            i2 = slotReader.parent(i2);
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object getJoinedKey(KeyInfo keyInfo) {
        return keyInfo.getObjectKey() != null ? new JoinedKey(Integer.valueOf(keyInfo.getKey()), keyInfo.getObjectKey()) : Integer.valueOf(keyInfo.getKey());
    }

    public static final Object getInvocation() {
        return invocation;
    }

    public static final Object getProvider() {
        return provider;
    }

    public static final Object getCompositionLocalMap() {
        return compositionLocalMap;
    }

    public static final Object getProviderMaps() {
        return providerMaps;
    }

    public static final Object getReference() {
        return reference;
    }

    public static final void runtimeCheck(boolean z) {
        if (z) {
            return;
        }
        composeImmediateRuntimeError("Check failed");
    }

    public static final Void composeRuntimeError(String str) {
        throw new ComposeRuntimeError("Compose Runtime internal error. Unexpected or incorrect use of the Compose internal runtime API (" + str + "). Please report to Google or use https://goo.gle/compose-feedback");
    }

    public static final void composeImmediateRuntimeError(String str) {
        throw new ComposeRuntimeError("Compose Runtime internal error. Unexpected or incorrect use of the Compose internal runtime API (" + str + "). Please report to Google or use https://goo.gle/compose-feedback");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int InvalidationLocationAscending$lambda$15(Invalidation invalidation, Invalidation invalidation2) {
        return Intrinsics.compare(invalidation.getLocation(), invalidation2.getLocation());
    }
}
