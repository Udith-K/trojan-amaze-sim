package org.bouncycastle.math.ec.rfc8032;

import java.security.SecureRandom;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.math.ec.rfc7748.X448;
import org.bouncycastle.math.ec.rfc7748.X448Field;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Ed448 {
    private static final int COORD_INTS = 14;
    private static final int C_d = -39081;
    private static final int L4_0 = 43969588;
    private static final int L4_1 = 30366549;
    private static final int L4_2 = 163752818;
    private static final int L4_3 = 258169998;
    private static final int L4_4 = 96434764;
    private static final int L4_5 = 227822194;
    private static final int L4_6 = 149865618;
    private static final int L4_7 = 550336261;
    private static final int L_0 = 78101261;
    private static final int L_1 = 141809365;
    private static final int L_2 = 175155932;
    private static final int L_3 = 64542499;
    private static final int L_4 = 158326419;
    private static final int L_5 = 191173276;
    private static final int L_6 = 104575268;
    private static final int L_7 = 137584065;
    private static final long M26L = 67108863;
    private static final long M28L = 268435455;
    private static final long M32L = 4294967295L;
    private static final int POINT_BYTES = 57;
    private static final int PRECOMP_BLOCKS = 5;
    private static final int PRECOMP_MASK = 15;
    private static final int PRECOMP_POINTS = 16;
    private static final int PRECOMP_RANGE = 450;
    private static final int PRECOMP_SPACING = 18;
    private static final int PRECOMP_TEETH = 5;
    public static final int PREHASH_SIZE = 64;
    public static final int PUBLIC_KEY_SIZE = 57;
    private static final int SCALAR_BYTES = 57;
    private static final int SCALAR_INTS = 14;
    public static final int SECRET_KEY_SIZE = 57;
    public static final int SIGNATURE_SIZE = 114;
    private static final int WNAF_WIDTH_BASE = 7;
    private static final byte[] DOM4_PREFIX = {83, 105, 103, 69, 100, 52, 52, 56};
    private static final int[] P = {-1, -1, -1, -1, -1, -1, -1, -2, -1, -1, -1, -1, -1, -1};
    private static final int[] L = {-1420278541, 595116690, -1916432555, 560775794, -1361693040, -1001465015, 2093622249, -1, -1, -1, -1, -1, -1, 1073741823};
    private static final int[] B_x = {118276190, 40534716, 9670182, 135141552, 85017403, 259173222, 68333082, 171784774, 174973732, 15824510, 73756743, 57518561, 94773951, 248652241, 107736333, 82941708};
    private static final int[] B_y = {36764180, 8885695, 130592152, 20104429, 163904957, 30304195, 121295871, 5901357, 125344798, 171541512, 175338348, 209069246, 3626697, 38307682, 24032956, 110359655};
    private static final Object precompLock = new Object();
    private static PointExt[] precompBaseTable = null;
    private static int[] precompBase = null;

    public static final class Algorithm {
        public static final int Ed448 = 0;
        public static final int Ed448ph = 1;
    }

    private static class F extends X448Field {
        private F() {
        }
    }

    private static class PointExt {
        int[] x;
        int[] y;
        int[] z;

        private PointExt() {
            this.x = X448Field.create();
            this.y = X448Field.create();
            this.z = X448Field.create();
        }
    }

    private static class PointPrecomp {
        int[] x;
        int[] y;

        private PointPrecomp() {
            this.x = X448Field.create();
            this.y = X448Field.create();
        }
    }

    private static byte[] calculateS(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int[] iArr = new int[28];
        decodeScalar(bArr, 0, iArr);
        int[] iArr2 = new int[14];
        decodeScalar(bArr2, 0, iArr2);
        int[] iArr3 = new int[14];
        decodeScalar(bArr3, 0, iArr3);
        Nat.mulAddTo(14, iArr2, iArr3, iArr);
        byte[] bArr4 = new byte[114];
        for (int i = 0; i < 28; i++) {
            encode32(iArr[i], bArr4, i * 4);
        }
        return reduceScalar(bArr4);
    }

    private static boolean checkContextVar(byte[] bArr) {
        return bArr != null && bArr.length < 256;
    }

    private static int checkPoint(int[] iArr, int[] iArr2) {
        int[] iArrCreate = X448Field.create();
        int[] iArrCreate2 = X448Field.create();
        int[] iArrCreate3 = X448Field.create();
        X448Field.sqr(iArr, iArrCreate2);
        X448Field.sqr(iArr2, iArrCreate3);
        X448Field.mul(iArrCreate2, iArrCreate3, iArrCreate);
        X448Field.add(iArrCreate2, iArrCreate3, iArrCreate2);
        X448Field.mul(iArrCreate, 39081, iArrCreate);
        X448Field.subOne(iArrCreate);
        X448Field.add(iArrCreate, iArrCreate2, iArrCreate);
        X448Field.normalize(iArrCreate);
        return X448Field.isZero(iArrCreate);
    }

    private static int checkPoint(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] iArrCreate = X448Field.create();
        int[] iArrCreate2 = X448Field.create();
        int[] iArrCreate3 = X448Field.create();
        int[] iArrCreate4 = X448Field.create();
        X448Field.sqr(iArr, iArrCreate2);
        X448Field.sqr(iArr2, iArrCreate3);
        X448Field.sqr(iArr3, iArrCreate4);
        X448Field.mul(iArrCreate2, iArrCreate3, iArrCreate);
        X448Field.add(iArrCreate2, iArrCreate3, iArrCreate2);
        X448Field.mul(iArrCreate2, iArrCreate4, iArrCreate2);
        X448Field.sqr(iArrCreate4, iArrCreate4);
        X448Field.mul(iArrCreate, 39081, iArrCreate);
        X448Field.sub(iArrCreate, iArrCreate4, iArrCreate);
        X448Field.add(iArrCreate, iArrCreate2, iArrCreate);
        X448Field.normalize(iArrCreate);
        return X448Field.isZero(iArrCreate);
    }

    private static boolean checkPointVar(byte[] bArr) {
        if ((bArr[56] & 127) != 0) {
            return false;
        }
        decode32(bArr, 0, new int[14], 0, 14);
        return !Nat.gte(14, r2, P);
    }

    private static boolean checkScalarVar(byte[] bArr, int[] iArr) {
        if (bArr[56] != 0) {
            return false;
        }
        decodeScalar(bArr, 0, iArr);
        return !Nat.gte(14, iArr, L);
    }

    private static byte[] copy(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }

    public static Xof createPrehash() {
        return createXof();
    }

    private static Xof createXof() {
        return new SHAKEDigest(256);
    }

    private static int decode16(byte[] bArr, int i) {
        return ((bArr[i + 1] & 255) << 8) | (bArr[i] & 255);
    }

    private static int decode24(byte[] bArr, int i) {
        return ((bArr[i + 2] & 255) << 16) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8);
    }

    private static int decode32(byte[] bArr, int i) {
        return (bArr[i + 3] << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    private static void decode32(byte[] bArr, int i, int[] iArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            iArr[i2 + i4] = decode32(bArr, (i4 * 4) + i);
        }
    }

    private static boolean decodePointVar(byte[] bArr, int i, boolean z, PointExt pointExt) {
        byte[] bArrCopy = copy(bArr, i, 57);
        if (!checkPointVar(bArrCopy)) {
            return false;
        }
        byte b = bArrCopy[56];
        int i2 = (b & 128) >>> 7;
        bArrCopy[56] = (byte) (b & 127);
        X448Field.decode(bArrCopy, 0, pointExt.y);
        int[] iArrCreate = X448Field.create();
        int[] iArrCreate2 = X448Field.create();
        X448Field.sqr(pointExt.y, iArrCreate);
        X448Field.mul(iArrCreate, 39081, iArrCreate2);
        X448Field.negate(iArrCreate, iArrCreate);
        X448Field.addOne(iArrCreate);
        X448Field.addOne(iArrCreate2);
        if (!X448Field.sqrtRatioVar(iArrCreate, iArrCreate2, pointExt.x)) {
            return false;
        }
        X448Field.normalize(pointExt.x);
        if (i2 == 1 && X448Field.isZeroVar(pointExt.x)) {
            return false;
        }
        int[] iArr = pointExt.x;
        if (z ^ (i2 != (iArr[0] & 1))) {
            X448Field.negate(iArr, iArr);
        }
        pointExtendXY(pointExt);
        return true;
    }

    private static void decodeScalar(byte[] bArr, int i, int[] iArr) {
        decode32(bArr, i, iArr, 0, 14);
    }

    private static void dom4(Xof xof, byte b, byte[] bArr) {
        byte[] bArr2 = DOM4_PREFIX;
        int length = bArr2.length;
        int i = length + 2;
        int length2 = bArr.length + i;
        byte[] bArr3 = new byte[length2];
        System.arraycopy(bArr2, 0, bArr3, 0, length);
        bArr3[length] = b;
        bArr3[length + 1] = (byte) bArr.length;
        System.arraycopy(bArr, 0, bArr3, i, bArr.length);
        xof.update(bArr3, 0, length2);
    }

    private static void encode24(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2 + 2] = (byte) (i >>> 16);
    }

    private static void encode32(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 3] = (byte) (i >>> 24);
    }

    private static void encode56(long j, byte[] bArr, int i) {
        encode32((int) j, bArr, i);
        encode24((int) (j >>> 32), bArr, i + 4);
    }

    private static int encodePoint(PointExt pointExt, byte[] bArr, int i) {
        int[] iArrCreate = X448Field.create();
        int[] iArrCreate2 = X448Field.create();
        X448Field.inv(pointExt.z, iArrCreate2);
        X448Field.mul(pointExt.x, iArrCreate2, iArrCreate);
        X448Field.mul(pointExt.y, iArrCreate2, iArrCreate2);
        X448Field.normalize(iArrCreate);
        X448Field.normalize(iArrCreate2);
        int iCheckPoint = checkPoint(iArrCreate, iArrCreate2);
        X448Field.encode(iArrCreate2, bArr, i);
        bArr[i + 56] = (byte) ((iArrCreate[0] & 1) << 7);
        return iCheckPoint;
    }

    public static void generatePrivateKey(SecureRandom secureRandom, byte[] bArr) {
        secureRandom.nextBytes(bArr);
    }

    public static void generatePublicKey(byte[] bArr, int i, byte[] bArr2, int i2) {
        Xof xofCreateXof = createXof();
        byte[] bArr3 = new byte[114];
        xofCreateXof.update(bArr, i, 57);
        xofCreateXof.doFinal(bArr3, 0, 114);
        byte[] bArr4 = new byte[57];
        pruneScalar(bArr3, 0, bArr4);
        scalarMultBaseEncoded(bArr4, bArr2, i2);
    }

    private static int getWindow4(int[] iArr, int i) {
        return (iArr[i >>> 3] >>> ((i & 7) << 2)) & 15;
    }

    private static byte[] getWnafVar(int[] iArr, int i) {
        int[] iArr2 = new int[28];
        int i2 = 0;
        int i3 = 14;
        int i4 = 28;
        int i5 = 0;
        while (true) {
            i3--;
            if (i3 < 0) {
                break;
            }
            int i6 = iArr[i3];
            iArr2[i4 - 1] = (i5 << 16) | (i6 >>> 16);
            i4 -= 2;
            iArr2[i4] = i6;
            i5 = i6;
        }
        byte[] bArr = new byte[447];
        int i7 = 32 - i;
        int i8 = 0;
        int i9 = 0;
        while (i2 < 28) {
            int i10 = iArr2[i2];
            while (i8 < 16) {
                int i11 = i10 >>> i8;
                if ((i11 & 1) == i9) {
                    i8++;
                } else {
                    int i12 = (i11 | 1) << i7;
                    bArr[(i2 << 4) + i8] = (byte) (i12 >> i7);
                    i8 += i;
                    i9 = i12 >>> 31;
                }
            }
            i2++;
            i8 -= 16;
        }
        return bArr;
    }

    private static void implSign(Xof xof, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, byte b, byte[] bArr5, int i2, int i3, byte[] bArr6, int i4) {
        dom4(xof, b, bArr4);
        xof.update(bArr, 57, 57);
        xof.update(bArr5, i2, i3);
        xof.doFinal(bArr, 0, bArr.length);
        byte[] bArrReduceScalar = reduceScalar(bArr);
        byte[] bArr7 = new byte[57];
        scalarMultBaseEncoded(bArrReduceScalar, bArr7, 0);
        dom4(xof, b, bArr4);
        xof.update(bArr7, 0, 57);
        xof.update(bArr3, i, 57);
        xof.update(bArr5, i2, i3);
        xof.doFinal(bArr, 0, bArr.length);
        byte[] bArrCalculateS = calculateS(bArrReduceScalar, reduceScalar(bArr), bArr2);
        System.arraycopy(bArr7, 0, bArr6, i4, 57);
        System.arraycopy(bArrCalculateS, 0, bArr6, i4 + 57, 57);
    }

    private static void implSign(byte[] bArr, int i, byte[] bArr2, byte b, byte[] bArr3, int i2, int i3, byte[] bArr4, int i4) {
        if (!checkContextVar(bArr2)) {
            throw new IllegalArgumentException("ctx");
        }
        Xof xofCreateXof = createXof();
        byte[] bArr5 = new byte[114];
        xofCreateXof.update(bArr, i, 57);
        xofCreateXof.doFinal(bArr5, 0, 114);
        byte[] bArr6 = new byte[57];
        pruneScalar(bArr5, 0, bArr6);
        byte[] bArr7 = new byte[57];
        scalarMultBaseEncoded(bArr6, bArr7, 0);
        implSign(xofCreateXof, bArr5, bArr6, bArr7, 0, bArr2, b, bArr3, i2, i3, bArr4, i4);
    }

    private static void implSign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte b, byte[] bArr4, int i3, int i4, byte[] bArr5, int i5) {
        if (!checkContextVar(bArr3)) {
            throw new IllegalArgumentException("ctx");
        }
        Xof xofCreateXof = createXof();
        byte[] bArr6 = new byte[114];
        xofCreateXof.update(bArr, i, 57);
        xofCreateXof.doFinal(bArr6, 0, 114);
        byte[] bArr7 = new byte[57];
        pruneScalar(bArr6, 0, bArr7);
        implSign(xofCreateXof, bArr6, bArr7, bArr2, i2, bArr3, b, bArr4, i3, i4, bArr5, i5);
    }

    private static boolean implVerify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte b, byte[] bArr4, int i3, int i4) {
        if (!checkContextVar(bArr3)) {
            throw new IllegalArgumentException("ctx");
        }
        byte[] bArrCopy = copy(bArr, i, 57);
        byte[] bArrCopy2 = copy(bArr, i + 57, 57);
        if (!checkPointVar(bArrCopy)) {
            return false;
        }
        int[] iArr = new int[14];
        if (!checkScalarVar(bArrCopy2, iArr)) {
            return false;
        }
        PointExt pointExt = new PointExt();
        if (!decodePointVar(bArr2, i2, true, pointExt)) {
            return false;
        }
        Xof xofCreateXof = createXof();
        byte[] bArr5 = new byte[114];
        dom4(xofCreateXof, b, bArr3);
        xofCreateXof.update(bArrCopy, 0, 57);
        xofCreateXof.update(bArr2, i2, 57);
        xofCreateXof.update(bArr4, i3, i4);
        xofCreateXof.doFinal(bArr5, 0, 114);
        int[] iArr2 = new int[14];
        decodeScalar(reduceScalar(bArr5), 0, iArr2);
        PointExt pointExt2 = new PointExt();
        scalarMultStrausVar(iArr, iArr2, pointExt, pointExt2);
        byte[] bArr6 = new byte[57];
        return encodePoint(pointExt2, bArr6, 0) != 0 && Arrays.areEqual(bArr6, bArrCopy);
    }

    private static boolean isNeutralElementVar(int[] iArr, int[] iArr2, int[] iArr3) {
        return X448Field.isZeroVar(iArr) && X448Field.areEqualVar(iArr2, iArr3);
    }

    private static void pointAdd(PointExt pointExt, PointExt pointExt2) {
        int[] iArrCreate = X448Field.create();
        int[] iArrCreate2 = X448Field.create();
        int[] iArrCreate3 = X448Field.create();
        int[] iArrCreate4 = X448Field.create();
        int[] iArrCreate5 = X448Field.create();
        int[] iArrCreate6 = X448Field.create();
        int[] iArrCreate7 = X448Field.create();
        int[] iArrCreate8 = X448Field.create();
        X448Field.mul(pointExt.z, pointExt2.z, iArrCreate);
        X448Field.sqr(iArrCreate, iArrCreate2);
        X448Field.mul(pointExt.x, pointExt2.x, iArrCreate3);
        X448Field.mul(pointExt.y, pointExt2.y, iArrCreate4);
        X448Field.mul(iArrCreate3, iArrCreate4, iArrCreate5);
        X448Field.mul(iArrCreate5, 39081, iArrCreate5);
        X448Field.add(iArrCreate2, iArrCreate5, iArrCreate6);
        X448Field.sub(iArrCreate2, iArrCreate5, iArrCreate7);
        X448Field.add(pointExt.x, pointExt.y, iArrCreate2);
        X448Field.add(pointExt2.x, pointExt2.y, iArrCreate5);
        X448Field.mul(iArrCreate2, iArrCreate5, iArrCreate8);
        X448Field.add(iArrCreate4, iArrCreate3, iArrCreate2);
        X448Field.sub(iArrCreate4, iArrCreate3, iArrCreate5);
        X448Field.carry(iArrCreate2);
        X448Field.sub(iArrCreate8, iArrCreate2, iArrCreate8);
        X448Field.mul(iArrCreate8, iArrCreate, iArrCreate8);
        X448Field.mul(iArrCreate5, iArrCreate, iArrCreate5);
        X448Field.mul(iArrCreate6, iArrCreate8, pointExt2.x);
        X448Field.mul(iArrCreate5, iArrCreate7, pointExt2.y);
        X448Field.mul(iArrCreate6, iArrCreate7, pointExt2.z);
    }

    private static void pointAddPrecomp(PointPrecomp pointPrecomp, PointExt pointExt) {
        int[] iArrCreate = X448Field.create();
        int[] iArrCreate2 = X448Field.create();
        int[] iArrCreate3 = X448Field.create();
        int[] iArrCreate4 = X448Field.create();
        int[] iArrCreate5 = X448Field.create();
        int[] iArrCreate6 = X448Field.create();
        int[] iArrCreate7 = X448Field.create();
        X448Field.sqr(pointExt.z, iArrCreate);
        X448Field.mul(pointPrecomp.x, pointExt.x, iArrCreate2);
        X448Field.mul(pointPrecomp.y, pointExt.y, iArrCreate3);
        X448Field.mul(iArrCreate2, iArrCreate3, iArrCreate4);
        X448Field.mul(iArrCreate4, 39081, iArrCreate4);
        X448Field.add(iArrCreate, iArrCreate4, iArrCreate5);
        X448Field.sub(iArrCreate, iArrCreate4, iArrCreate6);
        X448Field.add(pointPrecomp.x, pointPrecomp.y, iArrCreate);
        X448Field.add(pointExt.x, pointExt.y, iArrCreate4);
        X448Field.mul(iArrCreate, iArrCreate4, iArrCreate7);
        X448Field.add(iArrCreate3, iArrCreate2, iArrCreate);
        X448Field.sub(iArrCreate3, iArrCreate2, iArrCreate4);
        X448Field.carry(iArrCreate);
        X448Field.sub(iArrCreate7, iArrCreate, iArrCreate7);
        X448Field.mul(iArrCreate7, pointExt.z, iArrCreate7);
        X448Field.mul(iArrCreate4, pointExt.z, iArrCreate4);
        X448Field.mul(iArrCreate5, iArrCreate7, pointExt.x);
        X448Field.mul(iArrCreate4, iArrCreate6, pointExt.y);
        X448Field.mul(iArrCreate5, iArrCreate6, pointExt.z);
    }

    private static void pointAddVar(boolean z, PointExt pointExt, PointExt pointExt2) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int[] iArrCreate = X448Field.create();
        int[] iArrCreate2 = X448Field.create();
        int[] iArrCreate3 = X448Field.create();
        int[] iArrCreate4 = X448Field.create();
        int[] iArrCreate5 = X448Field.create();
        int[] iArrCreate6 = X448Field.create();
        int[] iArrCreate7 = X448Field.create();
        int[] iArrCreate8 = X448Field.create();
        if (z) {
            X448Field.sub(pointExt.y, pointExt.x, iArrCreate8);
            iArr2 = iArrCreate2;
            iArr = iArrCreate5;
            iArr4 = iArrCreate6;
            iArr3 = iArrCreate7;
        } else {
            X448Field.add(pointExt.y, pointExt.x, iArrCreate8);
            iArr = iArrCreate2;
            iArr2 = iArrCreate5;
            iArr3 = iArrCreate6;
            iArr4 = iArrCreate7;
        }
        X448Field.mul(pointExt.z, pointExt2.z, iArrCreate);
        X448Field.sqr(iArrCreate, iArrCreate2);
        X448Field.mul(pointExt.x, pointExt2.x, iArrCreate3);
        X448Field.mul(pointExt.y, pointExt2.y, iArrCreate4);
        X448Field.mul(iArrCreate3, iArrCreate4, iArrCreate5);
        X448Field.mul(iArrCreate5, 39081, iArrCreate5);
        X448Field.add(iArrCreate2, iArrCreate5, iArr3);
        X448Field.sub(iArrCreate2, iArrCreate5, iArr4);
        X448Field.add(pointExt2.x, pointExt2.y, iArrCreate5);
        X448Field.mul(iArrCreate8, iArrCreate5, iArrCreate8);
        X448Field.add(iArrCreate4, iArrCreate3, iArr);
        X448Field.sub(iArrCreate4, iArrCreate3, iArr2);
        X448Field.carry(iArr);
        X448Field.sub(iArrCreate8, iArrCreate2, iArrCreate8);
        X448Field.mul(iArrCreate8, iArrCreate, iArrCreate8);
        X448Field.mul(iArrCreate5, iArrCreate, iArrCreate5);
        X448Field.mul(iArrCreate6, iArrCreate8, pointExt2.x);
        X448Field.mul(iArrCreate5, iArrCreate7, pointExt2.y);
        X448Field.mul(iArrCreate6, iArrCreate7, pointExt2.z);
    }

    private static PointExt pointCopy(PointExt pointExt) {
        PointExt pointExt2 = new PointExt();
        pointCopy(pointExt, pointExt2);
        return pointExt2;
    }

    private static void pointCopy(PointExt pointExt, PointExt pointExt2) {
        X448Field.copy(pointExt.x, 0, pointExt2.x, 0);
        X448Field.copy(pointExt.y, 0, pointExt2.y, 0);
        X448Field.copy(pointExt.z, 0, pointExt2.z, 0);
    }

    private static void pointDouble(PointExt pointExt) {
        int[] iArrCreate = X448Field.create();
        int[] iArrCreate2 = X448Field.create();
        int[] iArrCreate3 = X448Field.create();
        int[] iArrCreate4 = X448Field.create();
        int[] iArrCreate5 = X448Field.create();
        int[] iArrCreate6 = X448Field.create();
        X448Field.add(pointExt.x, pointExt.y, iArrCreate);
        X448Field.sqr(iArrCreate, iArrCreate);
        X448Field.sqr(pointExt.x, iArrCreate2);
        X448Field.sqr(pointExt.y, iArrCreate3);
        X448Field.add(iArrCreate2, iArrCreate3, iArrCreate4);
        X448Field.carry(iArrCreate4);
        X448Field.sqr(pointExt.z, iArrCreate5);
        X448Field.add(iArrCreate5, iArrCreate5, iArrCreate5);
        X448Field.carry(iArrCreate5);
        X448Field.sub(iArrCreate4, iArrCreate5, iArrCreate6);
        X448Field.sub(iArrCreate, iArrCreate4, iArrCreate);
        X448Field.sub(iArrCreate2, iArrCreate3, iArrCreate2);
        X448Field.mul(iArrCreate, iArrCreate6, pointExt.x);
        X448Field.mul(iArrCreate4, iArrCreate2, pointExt.y);
        X448Field.mul(iArrCreate4, iArrCreate6, pointExt.z);
    }

    private static void pointExtendXY(PointExt pointExt) {
        X448Field.one(pointExt.z);
    }

    private static void pointLookup(int i, int i2, PointPrecomp pointPrecomp) {
        int i3 = i * 512;
        for (int i4 = 0; i4 < 16; i4++) {
            int i5 = ((i4 ^ i2) - 1) >> 31;
            X448Field.cmov(i5, precompBase, i3, pointPrecomp.x, 0);
            X448Field.cmov(i5, precompBase, i3 + 16, pointPrecomp.y, 0);
            i3 += 32;
        }
    }

    private static void pointLookup(int[] iArr, int i, int[] iArr2, PointExt pointExt) {
        int window4 = getWindow4(iArr, i);
        int i2 = (window4 >>> 3) ^ 1;
        int i3 = (window4 ^ (-i2)) & 7;
        int i4 = 0;
        for (int i5 = 0; i5 < 8; i5++) {
            int i6 = ((i5 ^ i3) - 1) >> 31;
            X448Field.cmov(i6, iArr2, i4, pointExt.x, 0);
            X448Field.cmov(i6, iArr2, i4 + 16, pointExt.y, 0);
            X448Field.cmov(i6, iArr2, i4 + 32, pointExt.z, 0);
            i4 += 48;
        }
        X448Field.cnegate(i2, pointExt.x);
    }

    private static void pointLookup15(int[] iArr, PointExt pointExt) {
        X448Field.copy(iArr, 336, pointExt.x, 0);
        X448Field.copy(iArr, 352, pointExt.y, 0);
        X448Field.copy(iArr, 368, pointExt.z, 0);
    }

    private static int[] pointPrecompute(PointExt pointExt, int i) {
        PointExt pointExtPointCopy = pointCopy(pointExt);
        PointExt pointExtPointCopy2 = pointCopy(pointExtPointCopy);
        pointDouble(pointExtPointCopy2);
        int[] iArrCreateTable = X448Field.createTable(i * 3);
        int i2 = 0;
        int i3 = 0;
        while (true) {
            X448Field.copy(pointExtPointCopy.x, 0, iArrCreateTable, i2);
            X448Field.copy(pointExtPointCopy.y, 0, iArrCreateTable, i2 + 16);
            X448Field.copy(pointExtPointCopy.z, 0, iArrCreateTable, i2 + 32);
            i2 += 48;
            i3++;
            if (i3 == i) {
                return iArrCreateTable;
            }
            pointAdd(pointExtPointCopy2, pointExtPointCopy);
        }
    }

    private static PointExt[] pointPrecomputeVar(PointExt pointExt, int i) {
        PointExt pointExtPointCopy = pointCopy(pointExt);
        pointDouble(pointExtPointCopy);
        PointExt[] pointExtArr = new PointExt[i];
        pointExtArr[0] = pointCopy(pointExt);
        for (int i2 = 1; i2 < i; i2++) {
            PointExt pointExtPointCopy2 = pointCopy(pointExtArr[i2 - 1]);
            pointExtArr[i2] = pointExtPointCopy2;
            pointAddVar(false, pointExtPointCopy, pointExtPointCopy2);
        }
        return pointExtArr;
    }

    private static void pointSetNeutral(PointExt pointExt) {
        X448Field.zero(pointExt.x);
        X448Field.one(pointExt.y);
        X448Field.one(pointExt.z);
    }

    public static void precompute() {
        synchronized (precompLock) {
            try {
                if (precompBase != null) {
                    return;
                }
                PointExt pointExt = new PointExt();
                X448Field.copy(B_x, 0, pointExt.x, 0);
                X448Field.copy(B_y, 0, pointExt.y, 0);
                pointExtendXY(pointExt);
                precompBaseTable = pointPrecomputeVar(pointExt, 32);
                precompBase = X448Field.createTable(160);
                int i = 0;
                for (int i2 = 0; i2 < 5; i2++) {
                    PointExt[] pointExtArr = new PointExt[5];
                    PointExt pointExt2 = new PointExt();
                    pointSetNeutral(pointExt2);
                    int i3 = 0;
                    while (true) {
                        if (i3 >= 5) {
                            break;
                        }
                        pointAddVar(true, pointExt, pointExt2);
                        pointDouble(pointExt);
                        pointExtArr[i3] = pointCopy(pointExt);
                        if (i2 + i3 != 8) {
                            for (int i4 = 1; i4 < 18; i4++) {
                                pointDouble(pointExt);
                            }
                        }
                        i3++;
                    }
                    PointExt[] pointExtArr2 = new PointExt[16];
                    pointExtArr2[0] = pointExt2;
                    int i5 = 1;
                    for (int i6 = 0; i6 < 4; i6++) {
                        int i7 = 1 << i6;
                        int i8 = 0;
                        while (i8 < i7) {
                            PointExt pointExtPointCopy = pointCopy(pointExtArr2[i5 - i7]);
                            pointExtArr2[i5] = pointExtPointCopy;
                            pointAddVar(false, pointExtArr[i6], pointExtPointCopy);
                            i8++;
                            i5++;
                        }
                    }
                    int[] iArrCreateTable = X448Field.createTable(16);
                    int[] iArrCreate = X448Field.create();
                    X448Field.copy(pointExtArr2[0].z, 0, iArrCreate, 0);
                    X448Field.copy(iArrCreate, 0, iArrCreateTable, 0);
                    int i9 = 0;
                    while (true) {
                        int i10 = i9 + 1;
                        if (i10 >= 16) {
                            break;
                        }
                        X448Field.mul(iArrCreate, pointExtArr2[i10].z, iArrCreate);
                        X448Field.copy(iArrCreate, 0, iArrCreateTable, i10 * 16);
                        i9 = i10;
                    }
                    X448Field.invVar(iArrCreate, iArrCreate);
                    int[] iArrCreate2 = X448Field.create();
                    while (i9 > 0) {
                        int i11 = i9 - 1;
                        X448Field.copy(iArrCreateTable, i11 * 16, iArrCreate2, 0);
                        X448Field.mul(iArrCreate2, iArrCreate, iArrCreate2);
                        X448Field.copy(iArrCreate2, 0, iArrCreateTable, i9 * 16);
                        X448Field.mul(iArrCreate, pointExtArr2[i9].z, iArrCreate);
                        i9 = i11;
                    }
                    X448Field.copy(iArrCreate, 0, iArrCreateTable, 0);
                    for (int i12 = 0; i12 < 16; i12++) {
                        PointExt pointExt3 = pointExtArr2[i12];
                        X448Field.copy(iArrCreateTable, i12 * 16, pointExt3.z, 0);
                        int[] iArr = pointExt3.x;
                        X448Field.mul(iArr, pointExt3.z, iArr);
                        int[] iArr2 = pointExt3.y;
                        X448Field.mul(iArr2, pointExt3.z, iArr2);
                        X448Field.copy(pointExt3.x, 0, precompBase, i);
                        X448Field.copy(pointExt3.y, 0, precompBase, i + 16);
                        i += 32;
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private static void pruneScalar(byte[] bArr, int i, byte[] bArr2) {
        System.arraycopy(bArr, i, bArr2, 0, 56);
        bArr2[0] = (byte) (bArr2[0] & 252);
        bArr2[55] = (byte) (bArr2[55] | 128);
        bArr2[56] = 0;
    }

    private static byte[] reduceScalar(byte[] bArr) {
        long jDecode32 = ((long) decode32(bArr, 0)) & 4294967295L;
        long jDecode24 = ((long) (decode24(bArr, 4) << 4)) & 4294967295L;
        long jDecode322 = ((long) decode32(bArr, 7)) & 4294967295L;
        long jDecode242 = ((long) (decode24(bArr, 11) << 4)) & 4294967295L;
        long jDecode323 = ((long) decode32(bArr, 14)) & 4294967295L;
        long jDecode243 = ((long) (decode24(bArr, 18) << 4)) & 4294967295L;
        long jDecode324 = ((long) decode32(bArr, 21)) & 4294967295L;
        long jDecode244 = ((long) (decode24(bArr, 25) << 4)) & 4294967295L;
        long jDecode325 = ((long) decode32(bArr, 28)) & 4294967295L;
        long jDecode245 = ((long) (decode24(bArr, 32) << 4)) & 4294967295L;
        long jDecode326 = ((long) decode32(bArr, 35)) & 4294967295L;
        long jDecode246 = ((long) (decode24(bArr, 39) << 4)) & 4294967295L;
        long jDecode327 = ((long) decode32(bArr, 42)) & 4294967295L;
        long jDecode247 = ((long) (decode24(bArr, 46) << 4)) & 4294967295L;
        long jDecode328 = ((long) decode32(bArr, 49)) & 4294967295L;
        long jDecode248 = ((long) (decode24(bArr, 53) << 4)) & 4294967295L;
        long jDecode329 = ((long) decode32(bArr, 56)) & 4294967295L;
        long jDecode249 = ((long) (decode24(bArr, 60) << 4)) & 4294967295L;
        long jDecode3210 = ((long) decode32(bArr, 63)) & 4294967295L;
        long jDecode2410 = ((long) (decode24(bArr, 67) << 4)) & 4294967295L;
        long jDecode3211 = ((long) decode32(bArr, 70)) & 4294967295L;
        long jDecode2411 = ((long) (decode24(bArr, 74) << 4)) & 4294967295L;
        long jDecode3212 = ((long) decode32(bArr, 77)) & 4294967295L;
        long jDecode2412 = ((long) (decode24(bArr, 81) << 4)) & 4294967295L;
        long jDecode3213 = decode32(bArr, 84);
        long j = jDecode3213 & 4294967295L;
        long jDecode2413 = ((long) (decode24(bArr, 88) << 4)) & 4294967295L;
        long jDecode3214 = decode32(bArr, 91);
        long j2 = jDecode3214 & 4294967295L;
        long jDecode2414 = ((long) (decode24(bArr, 95) << 4)) & 4294967295L;
        long jDecode3215 = decode32(bArr, 98);
        long j3 = jDecode3215 & 4294967295L;
        long jDecode2415 = ((long) (decode24(bArr, 102) << 4)) & 4294967295L;
        long jDecode3216 = decode32(bArr, 105);
        long j4 = jDecode3216 & 4294967295L;
        long jDecode2416 = ((long) (decode24(bArr, 109) << 4)) & 4294967295L;
        long jDecode16 = ((long) decode16(bArr, 112)) & 4294967295L;
        long j5 = jDecode2416 + (j4 >>> 28);
        long j6 = jDecode3216 & M28L;
        long j7 = jDecode2411 + (jDecode16 * 227822194) + (j5 * 149865618);
        long j8 = jDecode3212 + (jDecode16 * 149865618) + (j5 * 550336261);
        long j9 = jDecode328 + (j6 * 43969588);
        long j10 = jDecode248 + (j5 * 43969588) + (j6 * 30366549);
        long j11 = jDecode329 + (jDecode16 * 43969588) + (j5 * 30366549) + (j6 * 163752818);
        long j12 = jDecode249 + (jDecode16 * 30366549) + (j5 * 163752818) + (j6 * 258169998);
        long j13 = jDecode3210 + (jDecode16 * 163752818) + (j5 * 258169998) + (j6 * 96434764);
        long j14 = jDecode2410 + (jDecode16 * 258169998) + (j5 * 96434764) + (j6 * 227822194);
        long j15 = jDecode3211 + (jDecode16 * 96434764) + (j5 * 227822194) + (j6 * 149865618);
        long j16 = jDecode2415 + (j3 >>> 28);
        long j17 = jDecode3215 & M28L;
        long j18 = jDecode247 + (j16 * 43969588);
        long j19 = j14 + (j16 * 149865618);
        long j20 = j15 + (j16 * 550336261);
        long j21 = jDecode327 + (j17 * 43969588);
        long j22 = j9 + (j16 * 30366549) + (j17 * 163752818);
        long j23 = j10 + (j16 * 163752818) + (j17 * 258169998);
        long j24 = j11 + (j16 * 258169998) + (j17 * 96434764);
        long j25 = j12 + (j16 * 96434764) + (j17 * 227822194);
        long j26 = j13 + (j16 * 227822194) + (j17 * 149865618);
        long j27 = jDecode2414 + (j2 >>> 28);
        long j28 = jDecode3214 & M28L;
        long j29 = jDecode246 + (j27 * 43969588);
        long j30 = j26 + (j27 * 550336261);
        long j31 = jDecode326 + (j28 * 43969588);
        long j32 = j21 + (j27 * 30366549) + (j28 * 163752818);
        long j33 = j18 + (j17 * 30366549) + (j27 * 163752818) + (j28 * 258169998);
        long j34 = j22 + (j27 * 258169998) + (j28 * 96434764);
        long j35 = j23 + (j27 * 96434764) + (j28 * 227822194);
        long j36 = j24 + (j27 * 227822194) + (j28 * 149865618);
        long j37 = j25 + (j27 * 149865618) + (j28 * 550336261);
        long j38 = jDecode2413 + (j >>> 28);
        long j39 = jDecode3213 & M28L;
        long j40 = j7 + (j6 * 550336261) + (j20 >>> 28);
        long j41 = j20 & M28L;
        long j42 = j8 + (j40 >>> 28);
        long j43 = j40 & M28L;
        long j44 = jDecode2412 + (jDecode16 * 550336261) + (j42 >>> 28);
        long j45 = j42 & M28L;
        long j46 = j39 + (j44 >>> 28);
        long j47 = j44 & M28L;
        long j48 = jDecode244 + (j47 * 43969588);
        long j49 = jDecode325 + (j46 * 43969588) + (j47 * 30366549);
        long j50 = jDecode245 + (j38 * 43969588) + (j46 * 30366549) + (j47 * 163752818);
        long j51 = j31 + (j38 * 30366549) + (j46 * 163752818) + (j47 * 258169998);
        long j52 = j29 + (j28 * 30366549) + (j38 * 163752818) + (j46 * 258169998) + (j47 * 96434764);
        long j53 = j32 + (j38 * 258169998) + (j46 * 96434764) + (j47 * 227822194);
        long j54 = j33 + (j38 * 96434764) + (j46 * 227822194) + (j47 * 149865618);
        long j55 = j34 + (j38 * 227822194) + (j46 * 149865618) + (j47 * 550336261);
        long j56 = jDecode324 + (j45 * 43969588);
        long j57 = j30 + (j37 >>> 28);
        long j58 = j37 & M28L;
        long j59 = j19 + (j17 * 550336261) + (j57 >>> 28);
        long j60 = j57 & M28L;
        long j61 = j41 + (j59 >>> 28);
        long j62 = j59 & M28L;
        long j63 = j43 + (j61 >>> 28);
        long j64 = j61 & M28L;
        long j65 = jDecode323 + (j64 * 43969588);
        long j66 = jDecode243 + (j63 * 43969588) + (j64 * 30366549);
        long j67 = j56 + (j63 * 30366549) + (j64 * 163752818);
        long j68 = j48 + (j45 * 30366549) + (j63 * 163752818) + (j64 * 258169998);
        long j69 = j49 + (j45 * 163752818) + (j63 * 258169998) + (j64 * 96434764);
        long j70 = j50 + (j45 * 258169998) + (j63 * 96434764) + (j64 * 227822194);
        long j71 = j51 + (j45 * 96434764) + (j63 * 227822194) + (j64 * 149865618);
        long j72 = j52 + (j45 * 227822194) + (j63 * 149865618) + (j64 * 550336261);
        long j73 = jDecode242 + (j62 * 43969588);
        long j74 = j65 + (j62 * 30366549);
        long j75 = j66 + (j62 * 163752818);
        long j76 = j67 + (j62 * 258169998);
        long j77 = j68 + (j62 * 96434764);
        long j78 = j69 + (j62 * 227822194);
        long j79 = j70 + (j62 * 149865618);
        long j80 = j71 + (j62 * 550336261);
        long j81 = j35 + (j38 * 149865618) + (j46 * 550336261) + (j55 >>> 28);
        long j82 = j55 & M28L;
        long j83 = j36 + (j38 * 550336261) + (j81 >>> 28);
        long j84 = j81 & M28L;
        long j85 = j58 + (j83 >>> 28);
        long j86 = j83 & M28L;
        long j87 = j60 + (j85 >>> 28);
        long j88 = j85 & M28L;
        long j89 = j78 + (j87 * 149865618);
        long j90 = j79 + (j87 * 550336261);
        long j91 = j81 & M26L;
        long j92 = (j86 * 4) + (j84 >>> 26) + 1;
        long j93 = jDecode32 + (78101261 * j92);
        long j94 = jDecode322 + (j87 * 43969588) + (30366549 * j88) + (175155932 * j92);
        long j95 = j73 + (j87 * 30366549) + (163752818 * j88) + (64542499 * j92);
        long j96 = j74 + (j87 * 163752818) + (258169998 * j88) + (158326419 * j92);
        long j97 = j75 + (j87 * 258169998) + (96434764 * j88) + (191173276 * j92);
        long j98 = j76 + (j87 * 96434764) + (227822194 * j88) + (104575268 * j92);
        long j99 = j77 + (j87 * 227822194) + (149865618 * j88) + (j92 * 137584065);
        long j100 = jDecode24 + (43969588 * j88) + (141809365 * j92) + (j93 >>> 28);
        long j101 = j93 & M28L;
        long j102 = j94 + (j100 >>> 28);
        long j103 = j100 & M28L;
        long j104 = j95 + (j102 >>> 28);
        long j105 = j102 & M28L;
        long j106 = j96 + (j104 >>> 28);
        long j107 = j104 & M28L;
        long j108 = j97 + (j106 >>> 28);
        long j109 = j106 & M28L;
        long j110 = j98 + (j108 >>> 28);
        long j111 = j108 & M28L;
        long j112 = j99 + (j110 >>> 28);
        long j113 = j110 & M28L;
        long j114 = j89 + (j88 * 550336261) + (j112 >>> 28);
        long j115 = j112 & M28L;
        long j116 = j90 + (j114 >>> 28);
        long j117 = j114 & M28L;
        long j118 = j80 + (j116 >>> 28);
        long j119 = j116 & M28L;
        long j120 = j72 + (j118 >>> 28);
        long j121 = j118 & M28L;
        long j122 = j53 + (j45 * 149865618) + (j63 * 550336261) + (j120 >>> 28);
        long j123 = j120 & M28L;
        long j124 = j54 + (j45 * 550336261) + (j122 >>> 28);
        long j125 = j122 & M28L;
        long j126 = j82 + (j124 >>> 28);
        long j127 = j124 & M28L;
        long j128 = j91 + (j126 >>> 28);
        long j129 = j126 & M28L;
        long j130 = j128 & M26L;
        long j131 = (j128 >>> 26) - 1;
        long j132 = j101 - (j131 & 78101261);
        long j133 = (j103 - (j131 & 141809365)) + (j132 >> 28);
        long j134 = j132 & M28L;
        long j135 = (j105 - (j131 & 175155932)) + (j133 >> 28);
        long j136 = j133 & M28L;
        long j137 = (j107 - (j131 & 64542499)) + (j135 >> 28);
        long j138 = j135 & M28L;
        long j139 = (j109 - (j131 & 158326419)) + (j137 >> 28);
        long j140 = j137 & M28L;
        long j141 = (j111 - (j131 & 191173276)) + (j139 >> 28);
        long j142 = j139 & M28L;
        long j143 = (j113 - (j131 & 104575268)) + (j141 >> 28);
        long j144 = j141 & M28L;
        long j145 = (j115 - (j131 & 137584065)) + (j143 >> 28);
        long j146 = j143 & M28L;
        long j147 = j117 + (j145 >> 28);
        long j148 = j145 & M28L;
        long j149 = j119 + (j147 >> 28);
        long j150 = j147 & M28L;
        long j151 = j121 + (j149 >> 28);
        long j152 = j149 & M28L;
        long j153 = j123 + (j151 >> 28);
        long j154 = j151 & M28L;
        long j155 = j125 + (j153 >> 28);
        long j156 = j153 & M28L;
        long j157 = j127 + (j155 >> 28);
        long j158 = j155 & M28L;
        long j159 = j129 + (j157 >> 28);
        long j160 = j157 & M28L;
        long j161 = j130 + (j159 >> 28);
        long j162 = j159 & M28L;
        byte[] bArr2 = new byte[57];
        encode56((j136 << 28) | j134, bArr2, 0);
        encode56((j140 << 28) | j138, bArr2, 7);
        encode56(j142 | (j144 << 28), bArr2, 14);
        encode56(j146 | (j148 << 28), bArr2, 21);
        encode56(j150 | (j152 << 28), bArr2, 28);
        encode56(j154 | (j156 << 28), bArr2, 35);
        encode56(j158 | (j160 << 28), bArr2, 42);
        encode56((j161 << 28) | j162, bArr2, 49);
        return bArr2;
    }

    private static void scalarMult(byte[] bArr, PointExt pointExt, PointExt pointExt2) {
        int[] iArr = new int[14];
        decodeScalar(bArr, 0, iArr);
        Nat.shiftDownBit(14, iArr, Nat.cadd(14, (~iArr[0]) & 1, iArr, L, iArr));
        int[] iArrPointPrecompute = pointPrecompute(pointExt, 8);
        PointExt pointExt3 = new PointExt();
        pointLookup15(iArrPointPrecompute, pointExt2);
        pointAdd(pointExt, pointExt2);
        int i = 111;
        while (true) {
            pointLookup(iArr, i, iArrPointPrecompute, pointExt3);
            pointAdd(pointExt3, pointExt2);
            i--;
            if (i < 0) {
                return;
            }
            for (int i2 = 0; i2 < 4; i2++) {
                pointDouble(pointExt2);
            }
        }
    }

    private static void scalarMultBase(byte[] bArr, PointExt pointExt) {
        precompute();
        int[] iArr = new int[15];
        decodeScalar(bArr, 0, iArr);
        iArr[14] = Nat.cadd(14, (~iArr[0]) & 1, iArr, L, iArr) + 4;
        Nat.shiftDownBit(15, iArr, 0);
        PointPrecomp pointPrecomp = new PointPrecomp();
        pointSetNeutral(pointExt);
        int i = 17;
        while (true) {
            int i2 = i;
            for (int i3 = 0; i3 < 5; i3++) {
                int i4 = 0;
                for (int i5 = 0; i5 < 5; i5++) {
                    i4 = (i4 & (~(1 << i5))) ^ ((iArr[i2 >>> 5] >>> (i2 & 31)) << i5);
                    i2 += 18;
                }
                int i6 = (i4 >>> 4) & 1;
                pointLookup(i3, ((-i6) ^ i4) & 15, pointPrecomp);
                X448Field.cnegate(i6, pointPrecomp.x);
                pointAddPrecomp(pointPrecomp, pointExt);
            }
            i--;
            if (i < 0) {
                return;
            } else {
                pointDouble(pointExt);
            }
        }
    }

    private static void scalarMultBaseEncoded(byte[] bArr, byte[] bArr2, int i) {
        PointExt pointExt = new PointExt();
        scalarMultBase(bArr, pointExt);
        if (encodePoint(pointExt, bArr2, i) == 0) {
            throw new IllegalStateException();
        }
    }

    public static void scalarMultBaseXY(X448.Friend friend, byte[] bArr, int i, int[] iArr, int[] iArr2) {
        if (friend == null) {
            throw new NullPointerException("This method is only for use by X448");
        }
        byte[] bArr2 = new byte[57];
        pruneScalar(bArr, i, bArr2);
        PointExt pointExt = new PointExt();
        scalarMultBase(bArr2, pointExt);
        if (checkPoint(pointExt.x, pointExt.y, pointExt.z) == 0) {
            throw new IllegalStateException();
        }
        X448Field.copy(pointExt.x, 0, iArr, 0);
        X448Field.copy(pointExt.y, 0, iArr2, 0);
    }

    private static void scalarMultOrderVar(PointExt pointExt, PointExt pointExt2) {
        byte[] wnafVar = getWnafVar(L, 5);
        PointExt[] pointExtArrPointPrecomputeVar = pointPrecomputeVar(pointExt, 8);
        pointSetNeutral(pointExt2);
        int i = 446;
        while (true) {
            byte b = wnafVar[i];
            if (b != 0) {
                int i2 = b >> 31;
                pointAddVar(i2 != 0, pointExtArrPointPrecomputeVar[(b ^ i2) >>> 1], pointExt2);
            }
            i--;
            if (i < 0) {
                return;
            } else {
                pointDouble(pointExt2);
            }
        }
    }

    private static void scalarMultStrausVar(int[] iArr, int[] iArr2, PointExt pointExt, PointExt pointExt2) {
        precompute();
        byte[] wnafVar = getWnafVar(iArr, 7);
        byte[] wnafVar2 = getWnafVar(iArr2, 5);
        PointExt[] pointExtArrPointPrecomputeVar = pointPrecomputeVar(pointExt, 8);
        pointSetNeutral(pointExt2);
        int i = 446;
        while (true) {
            byte b = wnafVar[i];
            if (b != 0) {
                int i2 = b >> 31;
                pointAddVar(i2 != 0, precompBaseTable[(b ^ i2) >>> 1], pointExt2);
            }
            byte b2 = wnafVar2[i];
            if (b2 != 0) {
                int i3 = b2 >> 31;
                pointAddVar(i3 != 0, pointExtArrPointPrecomputeVar[(b2 ^ i3) >>> 1], pointExt2);
            }
            i--;
            if (i < 0) {
                return;
            } else {
                pointDouble(pointExt2);
            }
        }
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, int i4, byte[] bArr5, int i5) {
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 0, bArr4, i3, i4, bArr5, i5);
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2, int i3, byte[] bArr4, int i4) {
        implSign(bArr, i, bArr2, (byte) 0, bArr3, i2, i3, bArr4, i4);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, Xof xof, byte[] bArr4, int i3) {
        byte[] bArr5 = new byte[64];
        if (64 != xof.doFinal(bArr5, 0, 64)) {
            throw new IllegalArgumentException("ph");
        }
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr5, 0, 64, bArr4, i3);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, byte[] bArr5, int i4) {
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, i3, 64, bArr5, i4);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, Xof xof, byte[] bArr3, int i2) {
        byte[] bArr4 = new byte[64];
        if (64 != xof.doFinal(bArr4, 0, 64)) {
            throw new IllegalArgumentException("ph");
        }
        implSign(bArr, i, bArr2, (byte) 1, bArr4, 0, 64, bArr3, i2);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2, byte[] bArr4, int i3) {
        implSign(bArr, i, bArr2, (byte) 1, bArr3, i2, 64, bArr4, i3);
    }

    public static boolean validatePublicKeyFull(byte[] bArr, int i) {
        PointExt pointExt = new PointExt();
        if (!decodePointVar(bArr, i, false, pointExt)) {
            return false;
        }
        X448Field.normalize(pointExt.x);
        X448Field.normalize(pointExt.y);
        X448Field.normalize(pointExt.z);
        if (isNeutralElementVar(pointExt.x, pointExt.y, pointExt.z)) {
            return false;
        }
        PointExt pointExt2 = new PointExt();
        scalarMultOrderVar(pointExt, pointExt2);
        X448Field.normalize(pointExt2.x);
        X448Field.normalize(pointExt2.y);
        X448Field.normalize(pointExt2.z);
        return isNeutralElementVar(pointExt2.x, pointExt2.y, pointExt2.z);
    }

    public static boolean validatePublicKeyPartial(byte[] bArr, int i) {
        return decodePointVar(bArr, i, false, new PointExt());
    }

    public static boolean verify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, int i4) {
        return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 0, bArr4, i3, i4);
    }

    public static boolean verifyPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, Xof xof) {
        byte[] bArr4 = new byte[64];
        if (64 == xof.doFinal(bArr4, 0, 64)) {
            return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, 0, 64);
        }
        throw new IllegalArgumentException("ph");
    }

    public static boolean verifyPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3) {
        return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, i3, 64);
    }
}
