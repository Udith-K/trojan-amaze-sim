package androidx.compose.runtime;

import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: SlotTable.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class SlotTableKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final int countOneBits(int i) {
        switch (i) {
            case 0:
                return 0;
            case 1:
            case 2:
            case 4:
                return 1;
            case 3:
            case 5:
            case 6:
                return 2;
            default:
                return 3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int groupInfo(int[] iArr, int i) {
        return iArr[(i * 5) + 1];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isNode(int[] iArr, int i) {
        return (iArr[(i * 5) + 1] & 1073741824) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int nodeIndex(int[] iArr, int i) {
        return iArr[(i * 5) + 4];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean hasObjectKey(int[] iArr, int i) {
        return (iArr[(i * 5) + 1] & PKIFailureInfo.duplicateCertReq) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int objectKeyIndex(int[] iArr, int i) {
        int i2 = i * 5;
        return iArr[i2 + 4] + countOneBits(iArr[i2 + 1] >> 30);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean hasAux(int[] iArr, int i) {
        return (iArr[(i * 5) + 1] & 268435456) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean hasMark(int[] iArr, int i) {
        return (iArr[(i * 5) + 1] & 134217728) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateMark(int[] iArr, int i, boolean z) {
        int i2 = (i * 5) + 1;
        if (z) {
            iArr[i2] = iArr[i2] | 134217728;
        } else {
            iArr[i2] = iArr[i2] & (-134217729);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean containsMark(int[] iArr, int i) {
        return (iArr[(i * 5) + 1] & 67108864) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateContainsMark(int[] iArr, int i, boolean z) {
        int i2 = (i * 5) + 1;
        if (z) {
            iArr[i2] = iArr[i2] | 67108864;
        } else {
            iArr[i2] = iArr[i2] & (-67108865);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean containsAnyMark(int[] iArr, int i) {
        return (iArr[(i * 5) + 1] & 201326592) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int auxIndex(int[] iArr, int i) {
        int i2 = i * 5;
        if (i2 >= iArr.length) {
            return iArr.length;
        }
        return countOneBits(iArr[i2 + 1] >> 29) + iArr[i2 + 4];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int slotAnchor(int[] iArr, int i) {
        int i2 = i * 5;
        return iArr[i2 + 4] + countOneBits(iArr[i2 + 1] >> 28);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int key(int[] iArr, int i) {
        return iArr[i * 5];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int nodeCount(int[] iArr, int i) {
        return iArr[(i * 5) + 1] & 67108863;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateNodeCount(int[] iArr, int i, int i2) {
        ComposerKt.runtimeCheck(i2 >= 0 && i2 < 67108863);
        int i3 = (i * 5) + 1;
        iArr[i3] = i2 | (iArr[i3] & (-67108864));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int parentAnchor(int[] iArr, int i) {
        return iArr[(i * 5) + 2];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateParentAnchor(int[] iArr, int i, int i2) {
        iArr[(i * 5) + 2] = i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int groupSize(int[] iArr, int i) {
        return iArr[(i * 5) + 3];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateGroupSize(int[] iArr, int i, int i2) {
        ComposerKt.runtimeCheck(i2 >= 0);
        iArr[(i * 5) + 3] = i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int dataAnchor(int[] iArr, int i) {
        return iArr[(i * 5) + 4];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateDataAnchor(int[] iArr, int i, int i2) {
        iArr[(i * 5) + 4] = i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initGroup(int[] iArr, int i, int i2, boolean z, boolean z2, boolean z3, int i3, int i4) {
        int i5 = z ? 1073741824 : 0;
        int i6 = z2 ? PKIFailureInfo.duplicateCertReq : 0;
        int i7 = z3 ? 268435456 : 0;
        int i8 = i * 5;
        iArr[i8] = i2;
        iArr[i8 + 1] = i5 | i6 | i7;
        iArr[i8 + 2] = i3;
        iArr[i8 + 3] = 0;
        iArr[i8 + 4] = i4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Anchor find(ArrayList arrayList, int i, int i2) {
        int iSearch = search(arrayList, i, i2);
        if (iSearch >= 0) {
            return (Anchor) arrayList.get(iSearch);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int search(ArrayList arrayList, int i, int i2) {
        int size = arrayList.size() - 1;
        int i3 = 0;
        while (i3 <= size) {
            int i4 = (i3 + size) >>> 1;
            int location$runtime_release = ((Anchor) arrayList.get(i4)).getLocation$runtime_release();
            if (location$runtime_release < 0) {
                location$runtime_release += i2;
            }
            int iCompare = Intrinsics.compare(location$runtime_release, i);
            if (iCompare < 0) {
                i3 = i4 + 1;
            } else {
                if (iCompare <= 0) {
                    return i4;
                }
                size = i4 - 1;
            }
        }
        return -(i3 + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int locationOf(ArrayList arrayList, int i, int i2) {
        int iSearch = search(arrayList, i, i2);
        return iSearch >= 0 ? iSearch : -(iSearch + 1);
    }
}
