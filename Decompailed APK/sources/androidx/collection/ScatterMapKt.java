package androidx.collection;

import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScatterMap.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ScatterMapKt {
    public static final long[] EmptyGroup = {-9187201950435737345L, -1};
    private static final MutableScatterMap EmptyScatterMap = new MutableScatterMap(0);

    public static final int nextCapacity(int i) {
        if (i == 0) {
            return 6;
        }
        return (i * 2) + 1;
    }

    public static final MutableScatterMap mutableScatterMapOf() {
        return new MutableScatterMap(0, 1, null);
    }

    public static final void convertMetadataForCleanup(long[] metadata, int i) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        int i2 = (i + 7) >> 3;
        for (int i3 = 0; i3 < i2; i3++) {
            long j = metadata[i3] & (-9187201950435737472L);
            metadata[i3] = (-72340172838076674L) & ((~j) + (j >>> 7));
        }
        int lastIndex = ArraysKt.getLastIndex(metadata);
        int i4 = lastIndex - 1;
        metadata[i4] = (metadata[i4] & 72057594037927935L) | (-72057594037927936L);
        metadata[lastIndex] = metadata[0];
    }

    public static final int normalizeCapacity(int i) {
        if (i > 0) {
            return (-1) >>> Integer.numberOfLeadingZeros(i);
        }
        return 0;
    }

    public static final int loadedCapacity(int i) {
        if (i == 7) {
            return 6;
        }
        return i - (i / 8);
    }

    public static final int unloadedCapacity(int i) {
        if (i == 7) {
            return 8;
        }
        return i + ((i - 1) / 7);
    }

    public static final int findEmptySlot(long[] metadata, int i, int i2) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        while (i < i2) {
            if (((metadata[i >> 3] >> ((i & 7) << 3)) & 255) == 128) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
