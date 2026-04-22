package androidx.collection;

import androidx.collection.internal.RuntimeHelpersKt;
import kotlin.ULong;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ObjectLongMap.kt */
/* JADX INFO: loaded from: classes.dex */
public final class MutableObjectLongMap extends ObjectLongMap {
    private int growthLimit;

    public /* synthetic */ MutableObjectLongMap(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 6 : i);
    }

    public MutableObjectLongMap(int i) {
        super(null);
        if (!(i >= 0)) {
            RuntimeHelpersKt.throwIllegalArgumentException("Capacity must be a positive value.");
        }
        initializeStorage(ScatterMapKt.unloadedCapacity(i));
    }

    private final void initializeStorage(int i) {
        int iMax = i > 0 ? Math.max(7, ScatterMapKt.normalizeCapacity(i)) : 0;
        this._capacity = iMax;
        initializeMetadata(iMax);
        this.keys = new Object[iMax];
        this.values = new long[iMax];
    }

    private final void initializeMetadata(int i) {
        long[] jArr;
        if (i == 0) {
            jArr = ScatterMapKt.EmptyGroup;
        } else {
            jArr = new long[((i + 15) & (-8)) >> 3];
            ArraysKt.fill$default(jArr, -9187201950435737472L, 0, 0, 6, (Object) null);
        }
        this.metadata = jArr;
        int i2 = i >> 3;
        long j = 255 << ((i & 7) << 3);
        jArr[i2] = (jArr[i2] & (~j)) | j;
        initializeGrowth();
    }

    private final void initializeGrowth() {
        this.growthLimit = ScatterMapKt.loadedCapacity(getCapacity()) - this._size;
    }

    public final void set(Object obj, long j) {
        int iFindIndex = findIndex(obj);
        if (iFindIndex < 0) {
            iFindIndex = ~iFindIndex;
        }
        this.keys[iFindIndex] = obj;
        this.values[iFindIndex] = j;
    }

    private final int findFirstAvailableSlot(int i) {
        int i2 = this._capacity;
        int i3 = i & i2;
        int i4 = 0;
        while (true) {
            long[] jArr = this.metadata;
            int i5 = i3 >> 3;
            int i6 = (i3 & 7) << 3;
            long j = ((jArr[i5 + 1] << (64 - i6)) & ((-i6) >> 63)) | (jArr[i5] >>> i6);
            long j2 = j & ((~j) << 7) & (-9187201950435737472L);
            if (j2 != 0) {
                return (i3 + (Long.numberOfTrailingZeros(j2) >> 3)) & i2;
            }
            i4 += 8;
            i3 = (i3 + i4) & i2;
        }
    }

    private final void adjustStorage() {
        if (this._capacity > 8 && Long.compare(ULong.m2693constructorimpl(ULong.m2693constructorimpl(this._size) * 32) ^ Long.MIN_VALUE, ULong.m2693constructorimpl(ULong.m2693constructorimpl(this._capacity) * 25) ^ Long.MIN_VALUE) <= 0) {
            dropDeletes();
        } else {
            resizeStorage(ScatterMapKt.nextCapacity(this._capacity));
        }
    }

    private final void dropDeletes() {
        Object[] objArr;
        int i;
        int i2;
        long[] jArr = this.metadata;
        int i3 = this._capacity;
        Object[] objArr2 = this.keys;
        long[] jArr2 = this.values;
        ScatterMapKt.convertMetadataForCleanup(jArr, i3);
        int i4 = 0;
        int i5 = 0;
        int iFindEmptySlot = -1;
        while (i5 != i3) {
            int i6 = i5 >> 3;
            int i7 = (i5 & 7) << 3;
            long j = (jArr[i6] >> i7) & 255;
            if (j == 128) {
                iFindEmptySlot = i5;
                i5++;
            } else {
                if (j == 254) {
                    Object obj = objArr2[i5];
                    int iHashCode = (obj != null ? obj.hashCode() : i4) * (-862048943);
                    int i8 = iHashCode ^ (iHashCode << 16);
                    int i9 = i8 >>> 7;
                    int iFindFirstAvailableSlot = findFirstAvailableSlot(i9);
                    int i10 = i9 & i3;
                    if (((iFindFirstAvailableSlot - i10) & i3) / 8 == ((i5 - i10) & i3) / 8) {
                        jArr[i6] = (((long) (i8 & 127)) << i7) | ((~(255 << i7)) & jArr[i6]);
                        jArr[ArraysKt.getLastIndex(jArr)] = (jArr[i4] & 72057594037927935L) | Long.MIN_VALUE;
                    } else {
                        int i11 = iFindFirstAvailableSlot >> 3;
                        long j2 = jArr[i11];
                        int i12 = (iFindFirstAvailableSlot & 7) << 3;
                        if (((j2 >> i12) & 255) == 128) {
                            int i13 = i5;
                            objArr = objArr2;
                            jArr[i11] = (j2 & (~(255 << i12))) | (((long) (i8 & 127)) << i12);
                            jArr[i6] = (jArr[i6] & (~(255 << i7))) | (128 << i7);
                            objArr[iFindFirstAvailableSlot] = objArr[i13];
                            objArr[i13] = null;
                            jArr2[iFindFirstAvailableSlot] = jArr2[i13];
                            jArr2[i13] = 0;
                            i2 = i13;
                            iFindEmptySlot = i2;
                            i = i3;
                        } else {
                            int i14 = i3;
                            objArr = objArr2;
                            int i15 = i5;
                            jArr[i11] = (((long) (i8 & 127)) << i12) | (j2 & (~(255 << i12)));
                            if (iFindEmptySlot == -1) {
                                i = i14;
                                iFindEmptySlot = ScatterMapKt.findEmptySlot(jArr, i15 + 1, i);
                            } else {
                                i = i14;
                            }
                            objArr[iFindEmptySlot] = objArr[iFindFirstAvailableSlot];
                            objArr[iFindFirstAvailableSlot] = objArr[i15];
                            objArr[i15] = objArr[iFindEmptySlot];
                            jArr2[iFindEmptySlot] = jArr2[iFindFirstAvailableSlot];
                            jArr2[iFindFirstAvailableSlot] = jArr2[i15];
                            jArr2[i15] = jArr2[iFindEmptySlot];
                            i2 = i15 - 1;
                        }
                        i4 = 0;
                        jArr[ArraysKt.getLastIndex(jArr)] = (jArr[0] & 72057594037927935L) | Long.MIN_VALUE;
                        i5 = i2 + 1;
                        i3 = i;
                        objArr2 = objArr;
                    }
                }
                i5++;
            }
        }
        initializeGrowth();
    }

    private final void resizeStorage(int i) {
        int i2;
        long[] jArr = this.metadata;
        Object[] objArr = this.keys;
        long[] jArr2 = this.values;
        int i3 = this._capacity;
        initializeStorage(i);
        long[] jArr3 = this.metadata;
        Object[] objArr2 = this.keys;
        long[] jArr4 = this.values;
        int i4 = this._capacity;
        int i5 = 0;
        while (i5 < i3) {
            if (((jArr[i5 >> 3] >> ((i5 & 7) << 3)) & 255) < 128) {
                Object obj = objArr[i5];
                int iHashCode = (obj != null ? obj.hashCode() : 0) * (-862048943);
                int i6 = iHashCode ^ (iHashCode << 16);
                int iFindFirstAvailableSlot = findFirstAvailableSlot(i6 >>> 7);
                i2 = i5;
                long j = i6 & 127;
                int i7 = iFindFirstAvailableSlot >> 3;
                int i8 = (iFindFirstAvailableSlot & 7) << 3;
                long j2 = (j << i8) | (jArr3[i7] & (~(255 << i8)));
                jArr3[i7] = j2;
                jArr3[(((iFindFirstAvailableSlot - 7) & i4) + (i4 & 7)) >> 3] = j2;
                objArr2[iFindFirstAvailableSlot] = obj;
                jArr4[iFindFirstAvailableSlot] = jArr2[i2];
            } else {
                i2 = i5;
            }
            i5 = i2 + 1;
        }
    }

    private final int findIndex(Object obj) {
        int iHashCode = (obj != null ? obj.hashCode() : 0) * (-862048943);
        int i = iHashCode ^ (iHashCode << 16);
        int i2 = i >>> 7;
        int i3 = i & 127;
        int i4 = this._capacity;
        int i5 = i2 & i4;
        int i6 = 0;
        while (true) {
            long[] jArr = this.metadata;
            int i7 = i5 >> 3;
            int i8 = (i5 & 7) << 3;
            long j = ((jArr[i7 + 1] << (64 - i8)) & ((-i8) >> 63)) | (jArr[i7] >>> i8);
            long j2 = i3;
            int i9 = i3;
            long j3 = j ^ (j2 * 72340172838076673L);
            for (long j4 = (~j3) & (j3 - 72340172838076673L) & (-9187201950435737472L); j4 != 0; j4 &= j4 - 1) {
                int iNumberOfTrailingZeros = (i5 + (Long.numberOfTrailingZeros(j4) >> 3)) & i4;
                if (Intrinsics.areEqual(this.keys[iNumberOfTrailingZeros], obj)) {
                    return iNumberOfTrailingZeros;
                }
            }
            if ((((~j) << 6) & j & (-9187201950435737472L)) != 0) {
                int iFindFirstAvailableSlot = findFirstAvailableSlot(i2);
                if (this.growthLimit == 0 && ((this.metadata[iFindFirstAvailableSlot >> 3] >> ((iFindFirstAvailableSlot & 7) << 3)) & 255) != 254) {
                    adjustStorage();
                    iFindFirstAvailableSlot = findFirstAvailableSlot(i2);
                }
                this._size++;
                int i10 = this.growthLimit;
                long[] jArr2 = this.metadata;
                int i11 = iFindFirstAvailableSlot >> 3;
                long j5 = jArr2[i11];
                int i12 = (iFindFirstAvailableSlot & 7) << 3;
                this.growthLimit = i10 - (((j5 >> i12) & 255) == 128 ? 1 : 0);
                int i13 = this._capacity;
                long j6 = ((~(255 << i12)) & j5) | (j2 << i12);
                jArr2[i11] = j6;
                jArr2[(((iFindFirstAvailableSlot - 7) & i13) + (i13 & 7)) >> 3] = j6;
                return ~iFindFirstAvailableSlot;
            }
            i6 += 8;
            i5 = (i5 + i6) & i4;
            i3 = i9;
        }
    }
}
