package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ObjectFloatMap.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ObjectFloatMap {
    public int _capacity;
    public int _size;
    public Object[] keys;
    public long[] metadata;
    public float[] values;

    public /* synthetic */ ObjectFloatMap(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private ObjectFloatMap() {
        this.metadata = ScatterMapKt.EmptyGroup;
        this.keys = ContainerHelpersKt.EMPTY_OBJECTS;
        this.values = FloatSetKt.getEmptyFloatArray();
    }

    public final int getCapacity() {
        return this._capacity;
    }

    public final int getSize() {
        return this._size;
    }

    public final boolean isEmpty() {
        return this._size == 0;
    }

    public final float get(Object obj) {
        int iFindKeyIndex = findKeyIndex(obj);
        if (iFindKeyIndex < 0) {
            throw new NoSuchElementException("There is no key " + obj + " in the map");
        }
        return this.values[iFindKeyIndex];
    }

    public final float getOrDefault(Object obj, float f) {
        int iFindKeyIndex = findKeyIndex(obj);
        return iFindKeyIndex >= 0 ? this.values[iFindKeyIndex] : f;
    }

    public int hashCode() {
        Object[] objArr = this.keys;
        float[] fArr = this.values;
        long[] jArr = this.metadata;
        int length = jArr.length - 2;
        int i = 0;
        if (length >= 0) {
            int i2 = 0;
            int iFloatToIntBits = 0;
            while (true) {
                long j = jArr[i2];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i3 = 8 - ((~(i2 - length)) >>> 31);
                    for (int i4 = 0; i4 < i3; i4++) {
                        if ((255 & j) < 128) {
                            int i5 = (i2 << 3) + i4;
                            Object obj = objArr[i5];
                            iFloatToIntBits += Float.floatToIntBits(fArr[i5]) ^ (obj != null ? obj.hashCode() : 0);
                        }
                        j >>= 8;
                    }
                    if (i3 != 8) {
                        return iFloatToIntBits;
                    }
                }
                if (i2 == length) {
                    i = iFloatToIntBits;
                    break;
                }
                i2++;
            }
        }
        return i;
    }

    public final boolean contains(Object obj) {
        return findKeyIndex(obj) >= 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean equals(java.lang.Object r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = 1
            if (r1 != r0) goto L8
            return r2
        L8:
            boolean r3 = r1 instanceof androidx.collection.ObjectFloatMap
            r4 = 0
            if (r3 != 0) goto Le
            return r4
        Le:
            androidx.collection.ObjectFloatMap r1 = (androidx.collection.ObjectFloatMap) r1
            int r3 = r1.getSize()
            int r5 = r18.getSize()
            if (r3 == r5) goto L1b
            return r4
        L1b:
            java.lang.Object[] r3 = r0.keys
            float[] r5 = r0.values
            long[] r6 = r0.metadata
            int r7 = r6.length
            int r7 = r7 + (-2)
            if (r7 < 0) goto L68
            r8 = r4
        L27:
            r9 = r6[r8]
            long r11 = ~r9
            r13 = 7
            long r11 = r11 << r13
            long r11 = r11 & r9
            r13 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r11 = r11 & r13
            int r11 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r11 == 0) goto L63
            int r11 = r8 - r7
            int r11 = ~r11
            int r11 = r11 >>> 31
            r12 = 8
            int r11 = 8 - r11
            r13 = r4
        L41:
            if (r13 >= r11) goto L61
            r14 = 255(0xff, double:1.26E-321)
            long r14 = r14 & r9
            r16 = 128(0x80, double:6.3E-322)
            int r14 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r14 >= 0) goto L5d
            int r14 = r8 << 3
            int r14 = r14 + r13
            r15 = r3[r14]
            r14 = r5[r14]
            float r15 = r1.get(r15)
            int r14 = (r14 > r15 ? 1 : (r14 == r15 ? 0 : -1))
            if (r14 != 0) goto L5c
            goto L5d
        L5c:
            return r4
        L5d:
            long r9 = r9 >> r12
            int r13 = r13 + 1
            goto L41
        L61:
            if (r11 != r12) goto L68
        L63:
            if (r8 == r7) goto L68
            int r8 = r8 + 1
            goto L27
        L68:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.ObjectFloatMap.equals(java.lang.Object):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0074 A[PHI: r8
  0x0074: PHI (r8v2 int) = (r8v1 int), (r8v3 int) binds: [B:10:0x0031, B:22:0x0072] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String toString() {
        /*
            r18 = this;
            r0 = r18
            boolean r1 = r18.isEmpty()
            if (r1 == 0) goto Lb
            java.lang.String r1 = "{}"
            return r1
        Lb:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r2 = 123(0x7b, float:1.72E-43)
            r1.append(r2)
            java.lang.Object[] r2 = r0.keys
            float[] r3 = r0.values
            long[] r4 = r0.metadata
            int r5 = r4.length
            int r5 = r5 + (-2)
            if (r5 < 0) goto L79
            r6 = 0
            r7 = r6
            r8 = r7
        L23:
            r9 = r4[r7]
            long r11 = ~r9
            r13 = 7
            long r11 = r11 << r13
            long r11 = r11 & r9
            r13 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r11 = r11 & r13
            int r11 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r11 == 0) goto L74
            int r11 = r7 - r5
            int r11 = ~r11
            int r11 = r11 >>> 31
            r12 = 8
            int r11 = 8 - r11
            r13 = r6
        L3d:
            if (r13 >= r11) goto L72
            r14 = 255(0xff, double:1.26E-321)
            long r14 = r14 & r9
            r16 = 128(0x80, double:6.3E-322)
            int r14 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r14 >= 0) goto L6e
            int r14 = r7 << 3
            int r14 = r14 + r13
            r15 = r2[r14]
            r14 = r3[r14]
            if (r15 != r0) goto L53
            java.lang.String r15 = "(this)"
        L53:
            r1.append(r15)
            java.lang.String r15 = "="
            r1.append(r15)
            r1.append(r14)
            int r8 = r8 + 1
            int r14 = r0._size
            if (r8 >= r14) goto L6e
            r14 = 44
            r1.append(r14)
            r14 = 32
            r1.append(r14)
        L6e:
            long r9 = r9 >> r12
            int r13 = r13 + 1
            goto L3d
        L72:
            if (r11 != r12) goto L79
        L74:
            if (r7 == r5) goto L79
            int r7 = r7 + 1
            goto L23
        L79:
            r2 = 125(0x7d, float:1.75E-43)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "s.append('}').toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.ObjectFloatMap.toString():java.lang.String");
    }

    public final int findKeyIndex(Object obj) {
        int i = 0;
        int iHashCode = (obj != null ? obj.hashCode() : 0) * (-862048943);
        int i2 = iHashCode ^ (iHashCode << 16);
        int i3 = i2 & 127;
        int i4 = this._capacity;
        int i5 = i2 >>> 7;
        while (true) {
            int i6 = i5 & i4;
            long[] jArr = this.metadata;
            int i7 = i6 >> 3;
            int i8 = (i6 & 7) << 3;
            long j = ((jArr[i7 + 1] << (64 - i8)) & ((-i8) >> 63)) | (jArr[i7] >>> i8);
            long j2 = (((long) i3) * 72340172838076673L) ^ j;
            for (long j3 = (~j2) & (j2 - 72340172838076673L) & (-9187201950435737472L); j3 != 0; j3 &= j3 - 1) {
                int iNumberOfTrailingZeros = ((Long.numberOfTrailingZeros(j3) >> 3) + i6) & i4;
                if (Intrinsics.areEqual(this.keys[iNumberOfTrailingZeros], obj)) {
                    return iNumberOfTrailingZeros;
                }
            }
            if ((j & ((~j) << 6) & (-9187201950435737472L)) != 0) {
                return -1;
            }
            i += 8;
            i5 = i6 + i;
        }
    }
}
