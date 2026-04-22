package com.google.zxing.common;

import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;

/* JADX INFO: loaded from: classes.dex */
public final class BitSource {
    private int bitOffset;
    private int byteOffset;
    private final byte[] bytes;

    public BitSource(byte[] bArr) {
        this.bytes = bArr;
    }

    public int getBitOffset() {
        return this.bitOffset;
    }

    public int getByteOffset() {
        return this.byteOffset;
    }

    public int readBits(int i) {
        if (i <= 0 || i > 32 || i > available()) {
            throw new IllegalArgumentException(String.valueOf(i));
        }
        int i2 = this.bitOffset;
        int i3 = 0;
        if (i2 > 0) {
            int i4 = 8 - i2;
            int i5 = i < i4 ? i : i4;
            int i6 = i4 - i5;
            int i7 = (GF2Field.MASK >> (8 - i5)) << i6;
            byte[] bArr = this.bytes;
            int i8 = this.byteOffset;
            int i9 = (i7 & bArr[i8]) >> i6;
            i -= i5;
            int i10 = i2 + i5;
            this.bitOffset = i10;
            if (i10 == 8) {
                this.bitOffset = 0;
                this.byteOffset = i8 + 1;
            }
            i3 = i9;
        }
        if (i <= 0) {
            return i3;
        }
        while (i >= 8) {
            int i11 = i3 << 8;
            byte[] bArr2 = this.bytes;
            int i12 = this.byteOffset;
            i3 = (bArr2[i12] & 255) | i11;
            this.byteOffset = i12 + 1;
            i -= 8;
        }
        if (i <= 0) {
            return i3;
        }
        int i13 = 8 - i;
        int i14 = (i3 << i) | ((((GF2Field.MASK >> i13) << i13) & this.bytes[this.byteOffset]) >> i13);
        this.bitOffset += i;
        return i14;
    }

    public int available() {
        return ((this.bytes.length - this.byteOffset) * 8) - this.bitOffset;
    }
}
