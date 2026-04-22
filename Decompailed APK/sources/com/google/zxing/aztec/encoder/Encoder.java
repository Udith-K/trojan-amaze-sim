package com.google.zxing.aztec.encoder;

import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonEncoder;

/* JADX INFO: loaded from: classes.dex */
public abstract class Encoder {
    private static final int[] WORD_SIZE = {4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};

    private static int totalBitsInLayer(int i, boolean z) {
        return ((z ? 88 : 112) + (i << 4)) * i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static AztecCode encode(byte[] bArr, int i, int i2) {
        BitArray bitArrayStuffBits;
        int i3;
        boolean z;
        int iAbs;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8 = 2;
        BitArray bitArrayEncode = new HighLevelEncoder(bArr).encode();
        int size = ((bitArrayEncode.getSize() * i) / 100) + 11;
        int size2 = bitArrayEncode.getSize() + size;
        int i9 = 0;
        int i10 = 1;
        if (i2 != 0) {
            boolean z2 = i2 < 0;
            iAbs = Math.abs(i2);
            if (iAbs > (z2 ? 4 : 32)) {
                throw new IllegalArgumentException(String.format("Illegal value %s for layers", Integer.valueOf(i2)));
            }
            i4 = totalBitsInLayer(iAbs, z2);
            i3 = WORD_SIZE[iAbs];
            int i11 = i4 - (i4 % i3);
            bitArrayStuffBits = stuffBits(bitArrayEncode, i3);
            z = z2;
            if (bitArrayStuffBits.getSize() + size > i11) {
                throw new IllegalArgumentException("Data to large for user specified layer");
            }
            if (z2) {
                z = z2;
                if (bitArrayStuffBits.getSize() > (i3 << 6)) {
                    throw new IllegalArgumentException("Data to large for user specified layer");
                }
            }
        } else {
            BitArray bitArrayStuffBits2 = null;
            int i12 = 0;
            int i13 = 0;
            while (i12 <= i) {
                boolean z3 = i12 <= 3 ? i10 : i9;
                int i14 = z3 != 0 ? i12 + 1 : i12;
                int i15 = totalBitsInLayer(i14, z3);
                if (size2 <= i15) {
                    if (bitArrayStuffBits2 == null || i13 != WORD_SIZE[i14]) {
                        int i16 = WORD_SIZE[i14];
                        i13 = i16;
                        bitArrayStuffBits2 = stuffBits(bitArrayEncode, i16);
                    }
                    int i17 = i15 - (i15 % i13);
                    if ((z3 == 0 || bitArrayStuffBits2.getSize() <= (i13 << 6)) && bitArrayStuffBits2.getSize() + size <= i17) {
                        bitArrayStuffBits = bitArrayStuffBits2;
                        i3 = i13;
                        z = z3;
                        iAbs = i14;
                        i4 = i15;
                    }
                }
                int i18 = i8;
                int i19 = i10;
                i12 += i19;
                i10 = i19;
                i8 = i18;
                i = 32;
                i9 = 0;
            }
            throw new IllegalArgumentException("Data too large for an Aztec code");
        }
        BitArray bitArrayGenerateCheckWords = generateCheckWords(bitArrayStuffBits, i4, i3);
        int size3 = bitArrayStuffBits.getSize() / i3;
        BitArray bitArrayGenerateModeMessage = generateModeMessage(z, iAbs, size3);
        int i20 = (z ? 11 : 14) + (iAbs << 2);
        int[] iArr = new int[i20];
        if (z) {
            for (int i21 = i9; i21 < i20; i21 += i10) {
                iArr[i21] = i21;
            }
            i5 = i20;
        } else {
            int i22 = i20 / 2;
            i5 = i20 + 1 + (((i22 - 1) / 15) * i8);
            int i23 = i5 / 2;
            for (int i24 = i9; i24 < i22; i24 += i10) {
                iArr[(i22 - i24) - 1] = (i23 - r15) - 1;
                iArr[i22 + i24] = (i24 / 15) + i24 + i23 + i10;
            }
        }
        BitMatrix bitMatrix = new BitMatrix(i5);
        int i25 = i9;
        int i26 = i25;
        while (i25 < iAbs) {
            int i27 = ((iAbs - i25) << i8) + (z ? 9 : 12);
            while (i9 < i27) {
                int i28 = i9 << 1;
                int i29 = 0;
                while (i29 < i8) {
                    if (bitArrayGenerateCheckWords.get(i26 + i28 + i29)) {
                        int i30 = i25 << 1;
                        bitMatrix.set(iArr[i30 + i29], iArr[i30 + i9]);
                    }
                    if (bitArrayGenerateCheckWords.get((i27 << 1) + i26 + i28 + i29)) {
                        int i31 = i25 << 1;
                        i6 = size3;
                        bitMatrix.set(iArr[i31 + i9], iArr[((i20 - 1) - i31) - i29]);
                    } else {
                        i6 = size3;
                    }
                    if (bitArrayGenerateCheckWords.get((i27 << 2) + i26 + i28 + i29)) {
                        int i32 = (i20 - 1) - (i25 << 1);
                        bitMatrix.set(iArr[i32 - i29], iArr[i32 - i9]);
                    }
                    if (bitArrayGenerateCheckWords.get((i27 * 6) + i26 + i28 + i29)) {
                        i7 = 1;
                        int i33 = i25 << 1;
                        bitMatrix.set(iArr[((i20 - 1) - i33) - i9], iArr[i33 + i29]);
                    } else {
                        i7 = 1;
                    }
                    i29 += i7;
                    size3 = i6;
                    i10 = i7;
                    i8 = 2;
                }
                i9 += i10;
                i8 = 2;
            }
            i26 += i27 << 3;
            i25 += i10;
            size3 = size3;
            i8 = 2;
            i9 = 0;
        }
        int i34 = size3;
        drawModeMessage(bitMatrix, z, i5, bitArrayGenerateModeMessage);
        if (z) {
            drawBullsEye(bitMatrix, i5 / 2, 5);
        } else {
            int i35 = i5 / 2;
            drawBullsEye(bitMatrix, i35, 7);
            int i36 = 0;
            int i37 = 0;
            while (i36 < (i20 / 2) - 1) {
                for (int i38 = i35 & 1; i38 < i5; i38 += 2) {
                    int i39 = i35 - i37;
                    bitMatrix.set(i39, i38);
                    int i40 = i35 + i37;
                    bitMatrix.set(i40, i38);
                    bitMatrix.set(i38, i39);
                    bitMatrix.set(i38, i40);
                }
                i36 += 15;
                i37 += 16;
            }
        }
        AztecCode aztecCode = new AztecCode();
        aztecCode.setCompact(z);
        aztecCode.setSize(i5);
        aztecCode.setLayers(iAbs);
        aztecCode.setCodeWords(i34);
        aztecCode.setMatrix(bitMatrix);
        return aztecCode;
    }

    private static void drawBullsEye(BitMatrix bitMatrix, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3 += 2) {
            int i4 = i - i3;
            int i5 = i4;
            while (true) {
                int i6 = i + i3;
                if (i5 <= i6) {
                    bitMatrix.set(i5, i4);
                    bitMatrix.set(i5, i6);
                    bitMatrix.set(i4, i5);
                    bitMatrix.set(i6, i5);
                    i5++;
                }
            }
        }
        int i7 = i - i2;
        bitMatrix.set(i7, i7);
        int i8 = i7 + 1;
        bitMatrix.set(i8, i7);
        bitMatrix.set(i7, i8);
        int i9 = i + i2;
        bitMatrix.set(i9, i7);
        bitMatrix.set(i9, i8);
        bitMatrix.set(i9, i9 - 1);
    }

    static BitArray generateModeMessage(boolean z, int i, int i2) {
        BitArray bitArray = new BitArray();
        if (z) {
            bitArray.appendBits(i - 1, 2);
            bitArray.appendBits(i2 - 1, 6);
            return generateCheckWords(bitArray, 28, 4);
        }
        bitArray.appendBits(i - 1, 5);
        bitArray.appendBits(i2 - 1, 11);
        return generateCheckWords(bitArray, 40, 4);
    }

    private static void drawModeMessage(BitMatrix bitMatrix, boolean z, int i, BitArray bitArray) {
        int i2 = i / 2;
        int i3 = 0;
        if (z) {
            while (i3 < 7) {
                int i4 = (i2 - 3) + i3;
                if (bitArray.get(i3)) {
                    bitMatrix.set(i4, i2 - 5);
                }
                if (bitArray.get(i3 + 7)) {
                    bitMatrix.set(i2 + 5, i4);
                }
                if (bitArray.get(20 - i3)) {
                    bitMatrix.set(i4, i2 + 5);
                }
                if (bitArray.get(27 - i3)) {
                    bitMatrix.set(i2 - 5, i4);
                }
                i3++;
            }
            return;
        }
        while (i3 < 10) {
            int i5 = (i2 - 5) + i3 + (i3 / 5);
            if (bitArray.get(i3)) {
                bitMatrix.set(i5, i2 - 7);
            }
            if (bitArray.get(i3 + 10)) {
                bitMatrix.set(i2 + 7, i5);
            }
            if (bitArray.get(29 - i3)) {
                bitMatrix.set(i5, i2 + 7);
            }
            if (bitArray.get(39 - i3)) {
                bitMatrix.set(i2 - 7, i5);
            }
            i3++;
        }
    }

    private static BitArray generateCheckWords(BitArray bitArray, int i, int i2) {
        int size = bitArray.getSize() / i2;
        ReedSolomonEncoder reedSolomonEncoder = new ReedSolomonEncoder(getGF(i2));
        int i3 = i / i2;
        int[] iArrBitsToWords = bitsToWords(bitArray, i2, i3);
        reedSolomonEncoder.encode(iArrBitsToWords, i3 - size);
        BitArray bitArray2 = new BitArray();
        bitArray2.appendBits(0, i % i2);
        for (int i4 : iArrBitsToWords) {
            bitArray2.appendBits(i4, i2);
        }
        return bitArray2;
    }

    private static int[] bitsToWords(BitArray bitArray, int i, int i2) {
        int[] iArr = new int[i2];
        int size = bitArray.getSize() / i;
        for (int i3 = 0; i3 < size; i3++) {
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                i4 |= bitArray.get((i3 * i) + i5) ? 1 << ((i - i5) - 1) : 0;
            }
            iArr[i3] = i4;
        }
        return iArr;
    }

    private static GenericGF getGF(int i) {
        if (i == 4) {
            return GenericGF.AZTEC_PARAM;
        }
        if (i == 6) {
            return GenericGF.AZTEC_DATA_6;
        }
        if (i == 8) {
            return GenericGF.AZTEC_DATA_8;
        }
        if (i == 10) {
            return GenericGF.AZTEC_DATA_10;
        }
        if (i == 12) {
            return GenericGF.AZTEC_DATA_12;
        }
        throw new IllegalArgumentException("Unsupported word size ".concat(String.valueOf(i)));
    }

    static BitArray stuffBits(BitArray bitArray, int i) {
        BitArray bitArray2 = new BitArray();
        int size = bitArray.getSize();
        int i2 = (1 << i) - 2;
        int i3 = 0;
        while (i3 < size) {
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                int i6 = i3 + i5;
                if (i6 >= size || bitArray.get(i6)) {
                    i4 |= 1 << ((i - 1) - i5);
                }
            }
            int i7 = i4 & i2;
            if (i7 == i2) {
                bitArray2.appendBits(i7, i);
            } else if (i7 == 0) {
                bitArray2.appendBits(i4 | 1, i);
            } else {
                bitArray2.appendBits(i4, i);
                i3 += i;
            }
            i3--;
            i3 += i;
        }
        return bitArray2;
    }
}
