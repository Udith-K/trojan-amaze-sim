package org.bouncycastle.crypto.engines;

import androidx.recyclerview.widget.ItemTouchHelper;
import java.lang.reflect.Array;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StatelessProcessing;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.math.ec.Tnaf;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;
import org.bouncycastle.util.Pack;

/* JADX INFO: loaded from: classes2.dex */
public class AESLightEngine implements BlockCipher, StatelessProcessing {
    private static final int BLOCK_SIZE = 16;
    private static final int m1 = -2139062144;
    private static final int m2 = 2139062143;
    private static final int m3 = 27;
    private static final int m4 = -1061109568;
    private static final int m5 = 1061109567;
    private int ROUNDS;
    private int[][] WorkingKey = null;
    private boolean forEncryption;
    private static final byte[] S = {99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118, -54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64, -73, -3, -109, 38, 54, 63, -9, -52, 52, -91, -27, -15, 113, -40, 49, 21, 4, -57, 35, -61, 24, -106, 5, -102, 7, 18, -128, -30, -21, 39, -78, 117, 9, -125, 44, 26, 27, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49, -48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, 127, 80, 60, -97, -88, 81, -93, 64, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, 33, Tnaf.POW_2_WIDTH, -1, -13, -46, -51, 12, 19, -20, 95, -105, 68, 23, -60, -89, 126, 61, 100, 93, 25, 115, 96, -127, 79, -36, 34, 42, -112, -120, 70, -18, -72, 20, -34, 94, 11, -37, -32, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, 120, 37, 46, 28, -90, -76, -58, -24, -35, 116, 31, 75, -67, -117, -118, 112, 62, -75, 102, 72, 3, -10, 14, 97, 53, 87, -71, -122, -63, 29, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33, -116, -95, -119, 13, -65, -26, 66, 104, 65, -103, 45, 15, -80, 84, -69, 22};
    private static final byte[] Si = {82, 9, 106, -43, 48, 54, -91, 56, -65, 64, -93, -98, -127, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, 50, -90, -62, 35, 61, -18, 76, -107, 11, 66, -6, -61, 78, 8, 46, -95, 102, 40, -39, 36, -78, 118, 91, -94, 73, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, 22, -44, -92, 92, -52, 93, 101, -74, -110, 108, 112, 72, 80, -3, -19, -71, -38, 94, 21, 70, 87, -89, -115, -99, -124, -112, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, 88, 5, -72, -77, 69, 6, -48, 44, 30, -113, -54, 63, 15, 2, -63, -81, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, 103, -36, -22, -105, -14, -49, -50, -16, -76, -26, 115, -106, -84, 116, 34, -25, -83, 53, -123, -30, -7, 55, -24, 28, 117, -33, 110, 71, -15, 26, 113, 29, 41, -59, -119, 111, -73, 98, 14, -86, 24, -66, 27, -4, 86, 62, 75, -58, -46, 121, 32, -102, -37, -64, -2, 120, -51, 90, -12, 31, -35, -88, 51, -120, 7, -57, 49, -79, 18, Tnaf.POW_2_WIDTH, 89, 39, -128, -20, 95, 96, 81, 127, -87, 25, -75, 74, 13, 45, -27, 122, -97, -109, -55, -100, -17, -96, -32, 59, 77, -82, 42, -11, -80, -56, -21, -69, 60, -125, 83, -103, 97, 23, 43, 4, 126, -70, 119, -42, 38, -31, 105, 20, 99, 85, 33, 12, 125};
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, 216, 171, 77, 154, 47, 94, 188, 99, 198, 151, 53, 106, 212, 179, 125, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 239, 197, 145};

    private static int FFmulX(int i) {
        return (((i & m1) >>> 7) * 27) ^ ((m2 & i) << 1);
    }

    private static int FFmulX2(int i) {
        int i2 = (m5 & i) << 2;
        int i3 = i & m4;
        int i4 = i3 ^ (i3 >>> 1);
        return (i4 >>> 5) ^ (i2 ^ (i4 >>> 2));
    }

    private void decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2, int[][] iArr) {
        int iLittleEndianToInt = Pack.littleEndianToInt(bArr, i);
        int iLittleEndianToInt2 = Pack.littleEndianToInt(bArr, i + 4);
        int iLittleEndianToInt3 = Pack.littleEndianToInt(bArr, i + 8);
        int iLittleEndianToInt4 = Pack.littleEndianToInt(bArr, i + 12);
        int i3 = this.ROUNDS;
        int[] iArr2 = iArr[i3];
        char c = 0;
        int i4 = iLittleEndianToInt ^ iArr2[0];
        int i5 = 1;
        int i6 = iLittleEndianToInt2 ^ iArr2[1];
        int i7 = iLittleEndianToInt3 ^ iArr2[2];
        int i8 = i3 - 1;
        int iInv_mcol = iLittleEndianToInt4 ^ iArr2[3];
        while (true) {
            byte[] bArr3 = Si;
            if (i8 <= i5) {
                int iInv_mcol2 = inv_mcol((((bArr3[i4 & GF2Field.MASK] & 255) ^ ((bArr3[(iInv_mcol >> 8) & GF2Field.MASK] & 255) << 8)) ^ ((bArr3[(i7 >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr3[(i6 >> 24) & GF2Field.MASK] << 24)) ^ iArr[i8][0];
                int iInv_mcol3 = inv_mcol((((bArr3[i6 & GF2Field.MASK] & 255) ^ ((bArr3[(i4 >> 8) & GF2Field.MASK] & 255) << 8)) ^ ((bArr3[(iInv_mcol >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr3[(i7 >> 24) & GF2Field.MASK] << 24)) ^ iArr[i8][1];
                int iInv_mcol4 = inv_mcol((((bArr3[i7 & GF2Field.MASK] & 255) ^ ((bArr3[(i6 >> 8) & GF2Field.MASK] & 255) << 8)) ^ ((bArr3[(i4 >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr3[(iInv_mcol >> 24) & GF2Field.MASK] << 24)) ^ iArr[i8][2];
                int iInv_mcol5 = inv_mcol((((bArr3[iInv_mcol & GF2Field.MASK] & 255) ^ ((bArr3[(i7 >> 8) & GF2Field.MASK] & 255) << 8)) ^ ((bArr3[(i6 >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr3[(i4 >> 24) & GF2Field.MASK] << 24)) ^ iArr[i8][3];
                int i9 = (((bArr3[iInv_mcol2 & GF2Field.MASK] & 255) ^ ((bArr3[(iInv_mcol5 >> 8) & GF2Field.MASK] & 255) << 8)) ^ ((bArr3[(iInv_mcol4 >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr3[(iInv_mcol3 >> 24) & GF2Field.MASK] << 24);
                int[] iArr3 = iArr[0];
                int i10 = i9 ^ iArr3[0];
                int i11 = ((((bArr3[iInv_mcol3 & GF2Field.MASK] & 255) ^ ((bArr3[(iInv_mcol2 >> 8) & GF2Field.MASK] & 255) << 8)) ^ ((bArr3[(iInv_mcol5 >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr3[(iInv_mcol4 >> 24) & GF2Field.MASK] << 24)) ^ iArr3[1];
                int i12 = ((((bArr3[iInv_mcol4 & GF2Field.MASK] & 255) ^ ((bArr3[(iInv_mcol3 >> 8) & GF2Field.MASK] & 255) << 8)) ^ ((bArr3[(iInv_mcol2 >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr3[(iInv_mcol5 >> 24) & GF2Field.MASK] << 24)) ^ iArr3[2];
                int i13 = ((((bArr3[iInv_mcol5 & GF2Field.MASK] & 255) ^ ((bArr3[(iInv_mcol4 >> 8) & GF2Field.MASK] & 255) << 8)) ^ ((bArr3[(iInv_mcol3 >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr3[(iInv_mcol2 >> 24) & GF2Field.MASK] << 24)) ^ iArr3[3];
                Pack.intToLittleEndian(i10, bArr2, i2);
                Pack.intToLittleEndian(i11, bArr2, i2 + 4);
                Pack.intToLittleEndian(i12, bArr2, i2 + 8);
                Pack.intToLittleEndian(i13, bArr2, i2 + 12);
                return;
            }
            int iInv_mcol6 = inv_mcol((((bArr3[i4 & GF2Field.MASK] & 255) ^ ((bArr3[(iInv_mcol >> 8) & GF2Field.MASK] & 255) << 8)) ^ ((bArr3[(i7 >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr3[(i6 >> 24) & GF2Field.MASK] << 24)) ^ iArr[i8][c];
            int iInv_mcol7 = inv_mcol((((bArr3[i6 & GF2Field.MASK] & 255) ^ ((bArr3[(i4 >> 8) & GF2Field.MASK] & 255) << 8)) ^ ((bArr3[(iInv_mcol >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr3[(i7 >> 24) & GF2Field.MASK] << 24)) ^ iArr[i8][i5];
            int iInv_mcol8 = inv_mcol(((((bArr3[(i6 >> 8) & GF2Field.MASK] & 255) << 8) ^ (bArr3[i7 & GF2Field.MASK] & 255)) ^ ((bArr3[(i4 >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr3[(iInv_mcol >> 24) & GF2Field.MASK] << 24)) ^ iArr[i8][2];
            int iInv_mcol9 = inv_mcol((((bArr3[iInv_mcol & GF2Field.MASK] & 255) ^ ((bArr3[(i7 >> 8) & GF2Field.MASK] & 255) << 8)) ^ ((bArr3[(i6 >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr3[(i4 >> 24) & GF2Field.MASK] << 24));
            int i14 = i8 - 1;
            int i15 = iInv_mcol9 ^ iArr[i8][3];
            int iInv_mcol10 = inv_mcol((((bArr3[iInv_mcol6 & GF2Field.MASK] & 255) ^ ((bArr3[(i15 >> 8) & GF2Field.MASK] & 255) << 8)) ^ ((bArr3[(iInv_mcol8 >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr3[(iInv_mcol7 >> 24) & GF2Field.MASK] << 24)) ^ iArr[i14][c];
            int iInv_mcol11 = inv_mcol((((bArr3[iInv_mcol7 & GF2Field.MASK] & 255) ^ ((bArr3[(iInv_mcol6 >> 8) & GF2Field.MASK] & 255) << 8)) ^ ((bArr3[(i15 >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr3[(iInv_mcol8 >> 24) & GF2Field.MASK] << 24)) ^ iArr[i14][1];
            int iInv_mcol12 = inv_mcol(((((bArr3[(iInv_mcol7 >> 8) & GF2Field.MASK] & 255) << 8) ^ (bArr3[iInv_mcol8 & GF2Field.MASK] & 255)) ^ ((bArr3[(iInv_mcol6 >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr3[(i15 >> 24) & GF2Field.MASK] << 24)) ^ iArr[i14][2];
            i8 -= 2;
            iInv_mcol = inv_mcol((((bArr3[i15 & GF2Field.MASK] & 255) ^ ((bArr3[(iInv_mcol8 >> 8) & GF2Field.MASK] & 255) << 8)) ^ ((bArr3[(iInv_mcol7 >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr3[(iInv_mcol6 >> 24) & GF2Field.MASK] << 24)) ^ iArr[i14][3];
            i4 = iInv_mcol10;
            i6 = iInv_mcol11;
            i7 = iInv_mcol12;
            c = 0;
            i5 = 1;
        }
    }

    private void encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2, int[][] iArr) {
        int iLittleEndianToInt = Pack.littleEndianToInt(bArr, i);
        int iLittleEndianToInt2 = Pack.littleEndianToInt(bArr, i + 4);
        int iLittleEndianToInt3 = Pack.littleEndianToInt(bArr, i + 8);
        int iLittleEndianToInt4 = Pack.littleEndianToInt(bArr, i + 12);
        char c = 0;
        int[] iArr2 = iArr[0];
        int i3 = iLittleEndianToInt ^ iArr2[0];
        int i4 = iLittleEndianToInt2 ^ iArr2[1];
        int i5 = iLittleEndianToInt3 ^ iArr2[2];
        int iMcol = iLittleEndianToInt4 ^ iArr2[3];
        int i6 = 1;
        for (int i7 = 1; i6 < this.ROUNDS - i7; i7 = 1) {
            byte[] bArr3 = S;
            int iMcol2 = mcol((((bArr3[i3 & GF2Field.MASK] & 255) ^ ((bArr3[(i4 >> 8) & GF2Field.MASK] & 255) << 8)) ^ ((bArr3[(i5 >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr3[(iMcol >> 24) & GF2Field.MASK] << 24)) ^ iArr[i6][c];
            int iMcol3 = mcol((((bArr3[i4 & GF2Field.MASK] & 255) ^ ((bArr3[(i5 >> 8) & GF2Field.MASK] & 255) << 8)) ^ ((bArr3[(iMcol >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr3[(i3 >> 24) & GF2Field.MASK] << 24)) ^ iArr[i6][i7];
            int iMcol4 = mcol(((((bArr3[(iMcol >> 8) & GF2Field.MASK] & 255) << 8) ^ (bArr3[i5 & GF2Field.MASK] & 255)) ^ ((bArr3[(i3 >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr3[(i4 >> 24) & GF2Field.MASK] << 24)) ^ iArr[i6][2];
            int iMcol5 = mcol((((bArr3[iMcol & GF2Field.MASK] & 255) ^ ((bArr3[(i3 >> 8) & GF2Field.MASK] & 255) << 8)) ^ ((bArr3[(i4 >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr3[(i5 >> 24) & GF2Field.MASK] << 24));
            int i8 = i6 + 1;
            int i9 = iMcol5 ^ iArr[i6][3];
            int iMcol6 = mcol((((bArr3[iMcol2 & GF2Field.MASK] & 255) ^ ((bArr3[(iMcol3 >> 8) & GF2Field.MASK] & 255) << 8)) ^ ((bArr3[(iMcol4 >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr3[(i9 >> 24) & GF2Field.MASK] << 24)) ^ iArr[i8][c];
            int iMcol7 = mcol((((bArr3[iMcol3 & GF2Field.MASK] & 255) ^ ((bArr3[(iMcol4 >> 8) & GF2Field.MASK] & 255) << 8)) ^ ((bArr3[(i9 >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr3[(iMcol2 >> 24) & GF2Field.MASK] << 24)) ^ iArr[i8][1];
            int iMcol8 = mcol(((((bArr3[(i9 >> 8) & GF2Field.MASK] & 255) << 8) ^ (bArr3[iMcol4 & GF2Field.MASK] & 255)) ^ ((bArr3[(iMcol2 >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr3[(iMcol3 >> 24) & GF2Field.MASK] << 24)) ^ iArr[i8][2];
            i6 += 2;
            iMcol = mcol((((bArr3[i9 & GF2Field.MASK] & 255) ^ ((bArr3[(iMcol2 >> 8) & GF2Field.MASK] & 255) << 8)) ^ ((bArr3[(iMcol3 >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr3[(iMcol4 >> 24) & GF2Field.MASK] << 24)) ^ iArr[i8][3];
            i3 = iMcol6;
            i4 = iMcol7;
            i5 = iMcol8;
            c = 0;
        }
        byte[] bArr4 = S;
        int iMcol9 = mcol((((bArr4[i3 & GF2Field.MASK] & 255) ^ ((bArr4[(i4 >> 8) & GF2Field.MASK] & 255) << 8)) ^ ((bArr4[(i5 >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr4[(iMcol >> 24) & GF2Field.MASK] << 24)) ^ iArr[i6][0];
        int iMcol10 = mcol((((bArr4[i4 & GF2Field.MASK] & 255) ^ ((bArr4[(i5 >> 8) & GF2Field.MASK] & 255) << 8)) ^ ((bArr4[(iMcol >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr4[(i3 >> 24) & GF2Field.MASK] << 24)) ^ iArr[i6][1];
        int iMcol11 = mcol((((bArr4[i5 & GF2Field.MASK] & 255) ^ ((bArr4[(iMcol >> 8) & GF2Field.MASK] & 255) << 8)) ^ ((bArr4[(i3 >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr4[(i4 >> 24) & GF2Field.MASK] << 24)) ^ iArr[i6][2];
        int iMcol12 = mcol((((bArr4[iMcol & GF2Field.MASK] & 255) ^ ((bArr4[(i3 >> 8) & GF2Field.MASK] & 255) << 8)) ^ ((bArr4[(i4 >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr4[(i5 >> 24) & GF2Field.MASK] << 24)) ^ iArr[i6][3];
        int i10 = (((bArr4[iMcol9 & GF2Field.MASK] & 255) ^ ((bArr4[(iMcol10 >> 8) & GF2Field.MASK] & 255) << 8)) ^ ((bArr4[(iMcol11 >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr4[(iMcol12 >> 24) & GF2Field.MASK] << 24);
        int[] iArr3 = iArr[i6 + 1];
        int i11 = i10 ^ iArr3[0];
        int i12 = ((((bArr4[iMcol10 & GF2Field.MASK] & 255) ^ ((bArr4[(iMcol11 >> 8) & GF2Field.MASK] & 255) << 8)) ^ ((bArr4[(iMcol12 >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr4[(iMcol9 >> 24) & GF2Field.MASK] << 24)) ^ iArr3[1];
        int i13 = iArr3[2] ^ ((((bArr4[iMcol11 & GF2Field.MASK] & 255) ^ ((bArr4[(iMcol12 >> 8) & GF2Field.MASK] & 255) << 8)) ^ ((bArr4[(iMcol9 >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr4[(iMcol10 >> 24) & GF2Field.MASK] << 24));
        int i14 = ((((bArr4[iMcol12 & GF2Field.MASK] & 255) ^ ((bArr4[(iMcol9 >> 8) & GF2Field.MASK] & 255) << 8)) ^ ((bArr4[(iMcol10 >> 16) & GF2Field.MASK] & 255) << 16)) ^ (bArr4[(iMcol11 >> 24) & GF2Field.MASK] << 24)) ^ iArr3[3];
        Pack.intToLittleEndian(i11, bArr2, i2);
        Pack.intToLittleEndian(i12, bArr2, i2 + 4);
        Pack.intToLittleEndian(i13, bArr2, i2 + 8);
        Pack.intToLittleEndian(i14, bArr2, i2 + 12);
    }

    private int[][] generateWorkingKey(byte[] bArr, boolean z) {
        int length = bArr.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int i = length >>> 2;
        this.ROUNDS = i + 6;
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i + 7, 4);
        int i2 = 8;
        char c = 3;
        if (i == 4) {
            int iLittleEndianToInt = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = iLittleEndianToInt;
            int iLittleEndianToInt2 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = iLittleEndianToInt2;
            int iLittleEndianToInt3 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = iLittleEndianToInt3;
            int iLittleEndianToInt4 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = iLittleEndianToInt4;
            for (int i3 = 1; i3 <= 10; i3++) {
                iLittleEndianToInt ^= subWord(shift(iLittleEndianToInt4, 8)) ^ rcon[i3 - 1];
                int[] iArr2 = iArr[i3];
                iArr2[0] = iLittleEndianToInt;
                iLittleEndianToInt2 ^= iLittleEndianToInt;
                iArr2[1] = iLittleEndianToInt2;
                iLittleEndianToInt3 ^= iLittleEndianToInt2;
                iArr2[2] = iLittleEndianToInt3;
                iLittleEndianToInt4 ^= iLittleEndianToInt3;
                iArr2[3] = iLittleEndianToInt4;
            }
        } else if (i == 6) {
            int iLittleEndianToInt5 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = iLittleEndianToInt5;
            int iLittleEndianToInt6 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = iLittleEndianToInt6;
            int iLittleEndianToInt7 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = iLittleEndianToInt7;
            int iLittleEndianToInt8 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = iLittleEndianToInt8;
            int iLittleEndianToInt9 = Pack.littleEndianToInt(bArr, 16);
            int iLittleEndianToInt10 = Pack.littleEndianToInt(bArr, 20);
            int i4 = 1;
            int i5 = 1;
            while (true) {
                int[] iArr3 = iArr[i4];
                iArr3[0] = iLittleEndianToInt9;
                iArr3[1] = iLittleEndianToInt10;
                int iSubWord = iLittleEndianToInt5 ^ (subWord(shift(iLittleEndianToInt10, 8)) ^ i5);
                int[] iArr4 = iArr[i4];
                iArr4[2] = iSubWord;
                int i6 = iLittleEndianToInt6 ^ iSubWord;
                iArr4[3] = i6;
                int i7 = iLittleEndianToInt7 ^ i6;
                int[] iArr5 = iArr[i4 + 1];
                iArr5[0] = i7;
                int i8 = iLittleEndianToInt8 ^ i7;
                iArr5[1] = i8;
                int i9 = iLittleEndianToInt9 ^ i8;
                iArr5[2] = i9;
                int i10 = iLittleEndianToInt10 ^ i9;
                iArr5[3] = i10;
                int iSubWord2 = subWord(shift(i10, 8)) ^ (i5 << 1);
                i5 <<= 2;
                iLittleEndianToInt5 = iSubWord ^ iSubWord2;
                int[] iArr6 = iArr[i4 + 2];
                iArr6[0] = iLittleEndianToInt5;
                iLittleEndianToInt6 = i6 ^ iLittleEndianToInt5;
                iArr6[1] = iLittleEndianToInt6;
                iLittleEndianToInt7 = i7 ^ iLittleEndianToInt6;
                iArr6[2] = iLittleEndianToInt7;
                iLittleEndianToInt8 = i8 ^ iLittleEndianToInt7;
                iArr6[3] = iLittleEndianToInt8;
                i4 += 3;
                if (i4 >= 13) {
                    break;
                }
                iLittleEndianToInt9 = i9 ^ iLittleEndianToInt8;
                iLittleEndianToInt10 = i10 ^ iLittleEndianToInt9;
            }
        } else {
            if (i != 8) {
                throw new IllegalStateException("Should never get here");
            }
            int iLittleEndianToInt11 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = iLittleEndianToInt11;
            int iLittleEndianToInt12 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = iLittleEndianToInt12;
            int iLittleEndianToInt13 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = iLittleEndianToInt13;
            int iLittleEndianToInt14 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = iLittleEndianToInt14;
            int iLittleEndianToInt15 = Pack.littleEndianToInt(bArr, 16);
            iArr[1][0] = iLittleEndianToInt15;
            int iLittleEndianToInt16 = Pack.littleEndianToInt(bArr, 20);
            iArr[1][1] = iLittleEndianToInt16;
            int iLittleEndianToInt17 = Pack.littleEndianToInt(bArr, 24);
            iArr[1][2] = iLittleEndianToInt17;
            int iLittleEndianToInt18 = Pack.littleEndianToInt(bArr, 28);
            iArr[1][3] = iLittleEndianToInt18;
            int i11 = 2;
            int i12 = 1;
            while (true) {
                int iSubWord3 = subWord(shift(iLittleEndianToInt18, i2)) ^ i12;
                i12 <<= 1;
                iLittleEndianToInt11 ^= iSubWord3;
                int[] iArr7 = iArr[i11];
                iArr7[0] = iLittleEndianToInt11;
                iLittleEndianToInt12 ^= iLittleEndianToInt11;
                iArr7[1] = iLittleEndianToInt12;
                iLittleEndianToInt13 ^= iLittleEndianToInt12;
                iArr7[2] = iLittleEndianToInt13;
                iLittleEndianToInt14 ^= iLittleEndianToInt13;
                iArr7[c] = iLittleEndianToInt14;
                int i13 = i11 + 1;
                if (i13 >= 15) {
                    break;
                }
                iLittleEndianToInt15 ^= subWord(iLittleEndianToInt14);
                int[] iArr8 = iArr[i13];
                iArr8[0] = iLittleEndianToInt15;
                iLittleEndianToInt16 ^= iLittleEndianToInt15;
                iArr8[1] = iLittleEndianToInt16;
                iLittleEndianToInt17 ^= iLittleEndianToInt16;
                iArr8[2] = iLittleEndianToInt17;
                iLittleEndianToInt18 ^= iLittleEndianToInt17;
                iArr8[3] = iLittleEndianToInt18;
                i11 += 2;
                i2 = 8;
                c = 3;
            }
        }
        if (!z) {
            for (int i14 = 1; i14 < this.ROUNDS; i14++) {
                for (int i15 = 0; i15 < 4; i15++) {
                    int[] iArr9 = iArr[i14];
                    iArr9[i15] = inv_mcol(iArr9[i15]);
                }
            }
        }
        return iArr;
    }

    private static int inv_mcol(int i) {
        int iShift = shift(i, 8) ^ i;
        int iFFmulX = i ^ FFmulX(iShift);
        int iFFmulX2 = iShift ^ FFmulX2(iFFmulX);
        return iFFmulX ^ (iFFmulX2 ^ shift(iFFmulX2, 16));
    }

    private static int mcol(int i) {
        int iShift = shift(i, 8);
        int i2 = i ^ iShift;
        return FFmulX(i2) ^ (iShift ^ shift(i2, 16));
    }

    private static int shift(int i, int i2) {
        return (i << (-i2)) | (i >>> i2);
    }

    private static int subWord(int i) {
        byte[] bArr = S;
        return (bArr[(i >> 24) & GF2Field.MASK] << 24) | (bArr[i & GF2Field.MASK] & 255) | ((bArr[(i >> 8) & GF2Field.MASK] & 255) << 8) | ((bArr[(i >> 16) & GF2Field.MASK] & 255) << 16);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "AES";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.WorkingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey(), z);
            this.forEncryption = z;
        } else {
            throw new IllegalArgumentException("invalid parameter passed to AES init - " + cipherParameters.getClass().getName());
        }
    }

    @Override // org.bouncycastle.crypto.StatelessProcessing
    public BlockCipher newInstance() {
        return new AESLightEngine();
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int[][] iArr = this.WorkingKey;
        if (iArr == null) {
            throw new IllegalStateException("AES engine not initialised");
        }
        if (i > bArr.length - 16) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 > bArr2.length - 16) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.forEncryption) {
            encryptBlock(bArr, i, bArr2, i2, iArr);
        } else {
            decryptBlock(bArr, i, bArr2, i2, iArr);
        }
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
