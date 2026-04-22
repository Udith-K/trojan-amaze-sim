package org.bouncycastle.pqc.crypto.cmce;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;
import org.bouncycastle.util.Arrays;
import org.fdroid.fdroid.Preferences;

/* JADX INFO: loaded from: classes2.dex */
class CMCEEngine {
    private int COND_BYTES;
    private int GFBITS;
    private int GFMASK;
    private int IRR_BYTES;
    private int PK_NCOLS;
    private int PK_NROWS;
    private int PK_ROW_BYTES;
    private int SYND_BYTES;
    private int SYS_N;
    private int SYS_T;
    private BENES benes;
    private boolean countErrorIndices;
    private final int defaultKeySize;
    private GF gf;
    private int[] poly;
    private boolean usePadding;
    private boolean usePivots;

    public CMCEEngine(int i, int i2, int i3, int[] iArr, boolean z, int i4) {
        BENES benes13;
        this.usePivots = z;
        this.SYS_N = i2;
        this.SYS_T = i3;
        this.GFBITS = i;
        this.poly = iArr;
        this.defaultKeySize = i4;
        this.IRR_BYTES = i3 * 2;
        this.COND_BYTES = (1 << (i - 4)) * ((i * 2) - 1);
        int i5 = i3 * i;
        this.PK_NROWS = i5;
        int i6 = i2 - i5;
        this.PK_NCOLS = i6;
        this.PK_ROW_BYTES = (i6 + 7) / 8;
        this.SYND_BYTES = (i5 + 7) / 8;
        this.GFMASK = (1 << i) - 1;
        if (i == 12) {
            this.gf = new GF12(i);
            benes13 = new BENES12(this.SYS_N, this.SYS_T, this.GFBITS);
        } else {
            this.gf = new GF13(i);
            benes13 = new BENES13(this.SYS_N, this.SYS_T, this.GFBITS);
        }
        this.benes = benes13;
        this.usePadding = this.SYS_T % 8 != 0;
        this.countErrorIndices = (1 << this.GFBITS) > this.SYS_N;
    }

    private void GF_mul(short[] sArr, short[] sArr2, short[] sArr3) {
        int i;
        int i2;
        short[] sArr4 = new short[(this.SYS_T * 2) - 1];
        for (int i3 = 0; i3 < (this.SYS_T * 2) - 1; i3++) {
            sArr4[i3] = 0;
        }
        int i4 = 0;
        while (true) {
            i = this.SYS_T;
            if (i4 >= i) {
                break;
            }
            for (int i5 = 0; i5 < this.SYS_T; i5++) {
                int i6 = i4 + i5;
                sArr4[i6] = (short) (this.gf.gf_mul(sArr2[i4], sArr3[i5]) ^ sArr4[i6]);
            }
            i4++;
        }
        int i7 = (i - 1) * 2;
        while (true) {
            i2 = this.SYS_T;
            if (i7 < i2) {
                break;
            }
            int i8 = 0;
            while (true) {
                int[] iArr = this.poly;
                if (i8 != iArr.length) {
                    int i9 = iArr[i8];
                    if (i9 == 0 && this.GFBITS == 12) {
                        int i10 = i7 - this.SYS_T;
                        sArr4[i10] = (short) (sArr4[i10] ^ this.gf.gf_mul(sArr4[i7], (short) 2));
                    } else {
                        int i11 = (i7 - this.SYS_T) + i9;
                        sArr4[i11] = (short) (sArr4[i11] ^ sArr4[i7]);
                    }
                    i8++;
                }
            }
            i7--;
        }
        System.arraycopy(sArr4, 0, sArr, 0, i2);
        for (int i12 = 0; i12 < this.SYS_T; i12++) {
            sArr[i12] = sArr4[i12];
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1, types: [int, short] */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r12v2, types: [org.bouncycastle.pqc.crypto.cmce.GF] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private void bm(short[] sArr, short[] sArr2) {
        int i;
        int i2 = this.SYS_T;
        short[] sArr3 = new short[i2 + 1];
        short[] sArr4 = new short[i2 + 1];
        short[] sArr5 = new short[i2 + 1];
        int i3 = 0;
        for (int i4 = 0; i4 < this.SYS_T + 1; i4++) {
            sArr5[i4] = 0;
            sArr4[i4] = 0;
        }
        sArr4[0] = 1;
        sArr5[1] = 1;
        short s = 1;
        short s2 = 0;
        short s3 = 0;
        while (s2 < this.SYS_T * 2) {
            int i5 = i3;
            ?? r10 = i5;
            while (i5 <= min(s2, this.SYS_T)) {
                short sGf_mul = (short) (r10 ^ this.gf.gf_mul(sArr4[i5], sArr2[s2 - i5]));
                i5++;
                r10 = sGf_mul;
            }
            short s4 = (short) (((short) (((short) (((short) (r10 - 1)) >> 15)) & 1)) - 1);
            short s5 = (short) (((short) (((short) (((short) (((short) (s2 - (s3 * 2))) >> 15)) & 1)) - 1)) & s4);
            for (int i6 = i3; i6 <= this.SYS_T; i6++) {
                sArr3[i6] = sArr4[i6];
            }
            short sGf_frac = this.gf.gf_frac(s, r10);
            for (int i7 = i3; i7 <= this.SYS_T; i7++) {
                sArr4[i7] = (short) ((this.gf.gf_mul(sGf_frac, sArr5[i7]) & s4) ^ sArr4[i7]);
            }
            int i8 = ~s5;
            int i9 = s2 + 1;
            s3 = (short) (((i9 - s3) & s5) | (s3 & i8));
            int i10 = 0;
            while (true) {
                i = this.SYS_T;
                if (i10 > i) {
                    break;
                }
                sArr5[i10] = (short) ((sArr5[i10] & i8) | (sArr3[i10] & s5));
                i10++;
            }
            s = (short) ((i8 & s) | (r10 & s5));
            while (i >= 1) {
                sArr5[i] = sArr5[i - 1];
                i--;
            }
            i3 = 0;
            sArr5[0] = 0;
            s2 = (short) i9;
        }
        while (true) {
            int i11 = this.SYS_T;
            if (i3 > i11) {
                return;
            }
            sArr[i3] = sArr4[i11 - i3];
            i3++;
        }
    }

    static void cbrecursion(byte[] bArr, long j, long j2, short[] sArr, int i, long j3, long j4, int[] iArr) {
        long j5;
        long j6 = j4;
        if (j3 == 1) {
            int i2 = (int) (j >> 3);
            bArr[i2] = (byte) ((get_q_short(iArr, i) << ((int) (j & 7))) ^ bArr[i2]);
            return;
        }
        if (sArr != null) {
            for (long j7 = 0; j7 < j6; j7++) {
                int i3 = (int) j7;
                iArr[i3] = sArr[(int) (j7 ^ 1)] | ((sArr[i3] ^ 1) << 16);
            }
        } else {
            for (long j8 = 0; j8 < j6; j8++) {
                long j9 = i;
                iArr[(int) j8] = ((get_q_short(iArr, (int) (j9 + j8)) ^ 1) << 16) | get_q_short(iArr, (int) (j9 + (j8 ^ 1)));
            }
        }
        int i4 = (int) j6;
        sort32(iArr, 0, i4);
        for (long j10 = 0; j10 < j6; j10++) {
            int i5 = (int) j10;
            int i6 = 65535 & iArr[i5];
            if (j10 >= i6) {
                i5 = i6;
            }
            iArr[(int) (j6 + j10)] = i5 | (i6 << 16);
        }
        for (long j11 = 0; j11 < j6; j11++) {
            int i7 = (int) j11;
            iArr[i7] = (int) (((long) (iArr[i7] << 16)) | j11);
        }
        sort32(iArr, 0, i4);
        for (long j12 = 0; j12 < j6; j12++) {
            int i8 = (int) j12;
            iArr[i8] = (iArr[i8] << 16) + (iArr[(int) (j6 + j12)] >> 16);
        }
        sort32(iArr, 0, i4);
        if (j3 <= 10) {
            for (long j13 = 0; j13 < j6; j13++) {
                int i9 = (int) (j6 + j13);
                iArr[i9] = ((iArr[(int) j13] & 65535) << 10) | (iArr[i9] & 1023);
            }
            long j14 = 1;
            for (long j15 = 1; j14 < j3 - j15; j15 = 1) {
                long j16 = 0;
                while (j16 < j6) {
                    iArr[(int) j16] = (int) (((long) ((iArr[(int) (j6 + j16)] & (-1024)) << 6)) | j16);
                    j16++;
                    j14 = j14;
                }
                long j17 = j14;
                sort32(iArr, 0, i4);
                for (long j18 = 0; j18 < j6; j18++) {
                    int i10 = (int) j18;
                    iArr[i10] = (iArr[i10] << 20) | iArr[(int) (j6 + j18)];
                }
                sort32(iArr, 0, i4);
                for (long j19 = 0; j19 < j6; j19++) {
                    int i11 = iArr[(int) j19];
                    int i12 = 1048575 & i11;
                    int i13 = (int) (j6 + j19);
                    int i14 = (i11 & 1047552) | (iArr[i13] & 1023);
                    if (i12 >= i14) {
                        i12 = i14;
                    }
                    iArr[i13] = i12;
                }
                j14 = j17 + 1;
            }
            for (long j20 = 0; j20 < j6; j20++) {
                int i15 = (int) (j6 + j20);
                iArr[i15] = iArr[i15] & 1023;
            }
        } else {
            for (long j21 = 0; j21 < j6; j21++) {
                int i16 = (int) (j6 + j21);
                iArr[i16] = (iArr[(int) j21] << 16) | (iArr[i16] & 65535);
            }
            long j22 = 1;
            for (long j23 = 1; j22 < j3 - j23; j23 = 1) {
                for (long j24 = 0; j24 < j6; j24++) {
                    iArr[(int) j24] = (int) (((long) (iArr[(int) (j6 + j24)] & (-65536))) | j24);
                }
                sort32(iArr, 0, i4);
                for (long j25 = 0; j25 < j6; j25++) {
                    int i17 = (int) j25;
                    iArr[i17] = (iArr[i17] << 16) | (iArr[(int) (j6 + j25)] & 65535);
                }
                if (j22 < j3 - 2) {
                    for (long j26 = 0; j26 < j6; j26++) {
                        int i18 = (int) (j6 + j26);
                        iArr[i18] = (iArr[(int) j26] & (-65536)) | (iArr[i18] >> 16);
                    }
                    sort32(iArr, i4, (int) (j6 * 2));
                    for (long j27 = 0; j27 < j6; j27++) {
                        int i19 = (int) (j6 + j27);
                        iArr[i19] = (iArr[i19] << 16) | (iArr[(int) j27] & 65535);
                    }
                }
                sort32(iArr, 0, i4);
                for (long j28 = 0; j28 < j6; j28++) {
                    int i20 = (int) (j6 + j28);
                    int i21 = iArr[i20];
                    int i22 = (i21 & (-65536)) | (iArr[(int) j28] & 65535);
                    if (i22 < i21) {
                        iArr[i20] = i22;
                    }
                }
                j22++;
            }
            for (long j29 = 0; j29 < j6; j29++) {
                int i23 = (int) (j6 + j29);
                iArr[i23] = iArr[i23] & 65535;
            }
        }
        long j30 = 0;
        if (sArr != null) {
            while (j30 < j6) {
                int i24 = (int) j30;
                iArr[i24] = (int) (((long) (sArr[i24] << 16)) + j30);
                j30++;
            }
        } else {
            while (j30 < j6) {
                iArr[(int) j30] = (int) (((long) (get_q_short(iArr, (int) (((long) i) + j30)) << 16)) + j30);
                j30++;
            }
        }
        sort32(iArr, 0, i4);
        long j31 = j;
        long j32 = 2;
        long j33 = 0;
        while (true) {
            j5 = j6 / j32;
            if (j33 >= j5) {
                break;
            }
            long j34 = j33 * j32;
            long j35 = j6 + j34;
            int i25 = (int) j35;
            int i26 = iArr[i25] & 1;
            int i27 = (int) (((long) i26) + j34);
            int i28 = (int) (j31 >> 3);
            bArr[i28] = (byte) ((i26 << ((int) (j31 & 7))) ^ bArr[i28]);
            j31 += j2;
            iArr[i25] = (iArr[(int) j34] << 16) | i27;
            iArr[(int) (j35 + 1)] = (iArr[(int) (j34 + 1)] << 16) | (i27 ^ 1);
            j33++;
            j6 = j4;
            i4 = i4;
            j32 = 2;
        }
        long j36 = j32;
        long j37 = j4 * j36;
        sort32(iArr, i4, (int) j37);
        long j38 = j3 * j36;
        long j39 = j31 + ((j38 - 3) * j2 * j5);
        long j40 = 0;
        while (j40 < j5) {
            long j41 = j40 * j36;
            long j42 = j4 + j41;
            int i29 = iArr[(int) j42];
            int i30 = i29 & 1;
            long j43 = j39;
            int i31 = (int) (((long) i30) + j41);
            long j44 = j37;
            int i32 = (int) (j43 >> 3);
            bArr[i32] = (byte) (bArr[i32] ^ (i30 << ((int) (j43 & 7))));
            iArr[(int) j41] = (i29 & 65535) | (i31 << 16);
            iArr[(int) (j41 + 1)] = (iArr[(int) (j42 + 1)] & 65535) | ((i31 ^ 1) << 16);
            j40++;
            j39 = j43 + j2;
            j37 = j44;
            j38 = j38;
            j36 = 2;
        }
        long j45 = j37;
        sort32(iArr, 0, i4);
        long j46 = 2;
        long j47 = j39 - (((j38 - 2) * j2) * j5);
        short[] sArr2 = new short[i4 * 4];
        long j48 = 0;
        while (j48 < j45) {
            long j49 = j48 * j46;
            int i33 = iArr[(int) j48];
            sArr2[(int) j49] = (short) i33;
            sArr2[(int) (j49 + 1)] = (short) ((i33 & (-65536)) >> 16);
            j48++;
            j46 = 2;
        }
        for (long j50 = 0; j50 < j5; j50++) {
            long j51 = j50 * 2;
            sArr2[(int) j50] = (short) ((iArr[(int) j51] & 65535) >>> 1);
            sArr2[(int) (j50 + j5)] = (short) ((iArr[(int) (j51 + 1)] & 65535) >>> 1);
        }
        for (long j52 = 0; j52 < j5; j52++) {
            long j53 = j52 * 2;
            iArr[(int) (j4 + (j4 / 4) + j52)] = (sArr2[(int) (j53 + 1)] << 16) | sArr2[(int) j53];
        }
        long j54 = j2 * 2;
        long j55 = j4 + (j4 / 4);
        long j56 = j3 - 1;
        cbrecursion(bArr, j47, j54, null, ((int) j55) * 2, j56, j5, iArr);
        cbrecursion(bArr, j47 + j2, j54, null, (int) ((j55 * 2) + j5), j56, j5, iArr);
    }

    private static void controlbitsfrompermutation(byte[] bArr, short[] sArr, long j, long j2) {
        long j3 = 2;
        int[] iArr = new int[(int) (j2 * 2)];
        int i = (int) j2;
        short[] sArr2 = new short[i];
        while (true) {
            short s = 0;
            for (int i2 = 0; i2 < (((((j * j3) - 1) * j2) / j3) + 7) / 8; i2++) {
                bArr[i2] = 0;
            }
            int i3 = i;
            short[] sArr3 = sArr2;
            int[] iArr2 = iArr;
            cbrecursion(bArr, 0L, 1L, sArr, 0, j, j2, iArr);
            for (int i4 = 0; i4 < j2; i4++) {
                sArr3[i4] = (short) i4;
            }
            int i5 = 0;
            for (int i6 = 0; i6 < j; i6++) {
                layer(sArr3, bArr, i5, i6, i3);
                i5 = (int) (((long) i5) + (j2 >> 4));
            }
            for (int i7 = (int) (j - 2); i7 >= 0; i7--) {
                layer(sArr3, bArr, i5, i7, i3);
                i5 = (int) (((long) i5) + (j2 >> 4));
            }
            int i8 = 0;
            while (i8 < j2) {
                short s2 = (short) (s | (sArr[i8] ^ sArr3[i8]));
                i8++;
                s = s2;
            }
            if (s == 0) {
                return;
            }
            sArr2 = sArr3;
            i = i3;
            iArr = iArr2;
            j3 = 2;
        }
    }

    private static int ctz(long j) {
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < 64; i3++) {
            int i4 = (int) ((j >> i3) & 1);
            i2 |= i4;
            i += (i4 ^ 1) & (i2 ^ 1);
        }
        return i;
    }

    private int decrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int i;
        int i2;
        int i3 = this.SYS_T;
        short[] sArr = new short[i3 + 1];
        int i4 = this.SYS_N;
        short[] sArr2 = new short[i4];
        short[] sArr3 = new short[i3 * 2];
        short[] sArr4 = new short[i3 * 2];
        short[] sArr5 = new short[i3 + 1];
        short[] sArr6 = new short[i4];
        byte[] bArr4 = new byte[i4 / 8];
        int i5 = 0;
        while (true) {
            i = this.SYND_BYTES;
            if (i5 >= i) {
                break;
            }
            bArr4[i5] = bArr3[i5];
            i5++;
        }
        while (i < this.SYS_N / 8) {
            bArr4[i] = 0;
            i++;
        }
        int i6 = 0;
        while (true) {
            i2 = this.SYS_T;
            if (i6 >= i2) {
                break;
            }
            sArr[i6] = Utils.load_gf(bArr2, (i6 * 2) + 40, this.GFMASK);
            i6++;
        }
        sArr[i2] = 1;
        this.benes.support_gen(sArr2, bArr2);
        synd(sArr3, sArr, sArr2, bArr4);
        bm(sArr5, sArr3);
        root(sArr6, sArr5, sArr2);
        for (int i7 = 0; i7 < this.SYS_N / 8; i7++) {
            bArr[i7] = 0;
        }
        int i8 = 0;
        for (int i9 = 0; i9 < this.SYS_N; i9++) {
            short sGf_iszero = (short) (this.gf.gf_iszero(sArr6[i9]) & 1);
            int i10 = i9 / 8;
            bArr[i10] = (byte) (bArr[i10] | (sGf_iszero << (i9 % 8)));
            i8 += sGf_iszero;
        }
        synd(sArr4, sArr, sArr2, bArr);
        int i11 = this.SYS_T ^ i8;
        for (int i12 = 0; i12 < this.SYS_T * 2; i12++) {
            i11 |= sArr3[i12] ^ sArr4[i12];
        }
        return (((i11 - 1) >> 15) & 1) ^ 1;
    }

    private void encrypt(byte[] bArr, byte[] bArr2, byte[] bArr3, SecureRandom secureRandom) {
        generate_error_vector(bArr3, secureRandom);
        syndrome(bArr, bArr2, bArr3);
    }

    private short eval(short[] sArr, short s) {
        int i = this.SYS_T;
        short sGf_add = sArr[i];
        for (int i2 = i - 1; i2 >= 0; i2--) {
            sGf_add = this.gf.gf_add(this.gf.gf_mul(sGf_add, s), sArr[i2]);
        }
        return sGf_add;
    }

    private void generate_error_vector(byte[] bArr, SecureRandom secureRandom) {
        int i;
        int i2 = this.SYS_T;
        short[] sArr = new short[i2 * 2];
        short[] sArr2 = new short[i2];
        byte[] bArr2 = new byte[i2];
        while (true) {
            if (this.countErrorIndices) {
                byte[] bArr3 = new byte[this.SYS_T * 4];
                secureRandom.nextBytes(bArr3);
                for (int i3 = 0; i3 < this.SYS_T * 2; i3++) {
                    sArr[i3] = Utils.load_gf(bArr3, i3 * 2, this.GFMASK);
                }
                int i4 = 0;
                int i5 = 0;
                while (true) {
                    i = this.SYS_T;
                    if (i4 >= i * 2 || i5 >= i) {
                        break;
                    }
                    short s = sArr[i4];
                    if (s < this.SYS_N) {
                        sArr2[i5] = s;
                        i5++;
                    }
                    i4++;
                }
                if (i5 < i) {
                    continue;
                }
            } else {
                byte[] bArr4 = new byte[this.SYS_T * 2];
                secureRandom.nextBytes(bArr4);
                for (int i6 = 0; i6 < this.SYS_T; i6++) {
                    sArr2[i6] = Utils.load_gf(bArr4, i6 * 2, this.GFMASK);
                }
            }
            boolean z = false;
            for (int i7 = 1; i7 < this.SYS_T && !z; i7++) {
                int i8 = 0;
                while (true) {
                    if (i8 >= i7) {
                        break;
                    }
                    if (sArr2[i7] == sArr2[i8]) {
                        z = true;
                        break;
                    }
                    i8++;
                }
            }
            if (!z) {
                break;
            }
        }
        for (int i9 = 0; i9 < this.SYS_T; i9++) {
            bArr2[i9] = (byte) (1 << (sArr2[i9] & 7));
        }
        for (short s2 = 0; s2 < this.SYS_N / 8; s2 = (short) (s2 + 1)) {
            bArr[s2] = 0;
            for (int i10 = 0; i10 < this.SYS_T; i10++) {
                bArr[s2] = (byte) ((((short) (same_mask32(s2, (short) (sArr2[i10] >> 3)) & 255)) & bArr2[i10]) | bArr[s2]);
            }
        }
    }

    private int generate_irr_poly(short[] sArr) {
        int i = this.SYS_T;
        short[][] sArr2 = (short[][]) Array.newInstance((Class<?>) Short.TYPE, i + 1, i);
        sArr2[0][0] = 1;
        for (int i2 = 1; i2 < this.SYS_T; i2++) {
            sArr2[0][i2] = 0;
        }
        for (int i3 = 0; i3 < this.SYS_T; i3++) {
            sArr2[1][i3] = sArr[i3];
        }
        for (int i4 = 2; i4 <= this.SYS_T; i4++) {
            GF_mul(sArr2[i4], sArr2[i4 - 1], sArr);
        }
        int i5 = 0;
        while (i5 < this.SYS_T) {
            int i6 = i5 + 1;
            for (int i7 = i6; i7 < this.SYS_T; i7++) {
                short sGf_iszero = this.gf.gf_iszero(sArr2[i5][i5]);
                for (int i8 = i5; i8 < this.SYS_T + 1; i8++) {
                    short[] sArr3 = sArr2[i8];
                    sArr3[i5] = (short) (sArr3[i5] ^ (sArr3[i7] & sGf_iszero));
                }
            }
            short s = sArr2[i5][i5];
            if (s == 0) {
                return -1;
            }
            short sGf_inv = this.gf.gf_inv(s);
            for (int i9 = i5; i9 < this.SYS_T + 1; i9++) {
                short[] sArr4 = sArr2[i9];
                sArr4[i5] = this.gf.gf_mul(sArr4[i5], sGf_inv);
            }
            for (int i10 = 0; i10 < this.SYS_T; i10++) {
                if (i10 != i5) {
                    short s2 = sArr2[i5][i10];
                    for (int i11 = i5; i11 < this.SYS_T + 1; i11++) {
                        short[] sArr5 = sArr2[i11];
                        sArr5[i10] = (short) (sArr5[i10] ^ this.gf.gf_mul(sArr5[i5], s2));
                    }
                }
            }
            i5 = i6;
        }
        int i12 = 0;
        while (true) {
            int i13 = this.SYS_T;
            if (i12 >= i13) {
                return 0;
            }
            sArr[i12] = sArr2[i13][i12];
            i12++;
        }
    }

    static short get_q_short(int[] iArr, int i) {
        int i2 = i / 2;
        return (short) (i % 2 == 0 ? iArr[i2] : (iArr[i2] & (-65536)) >> 16);
    }

    private static void layer(short[] sArr, byte[] bArr, int i, int i2, int i3) {
        int i4 = 1 << i2;
        int i5 = 0;
        for (int i6 = 0; i6 < i3; i6 += i4 * 2) {
            for (int i7 = 0; i7 < i4; i7++) {
                int i8 = i6 + i7;
                short s = sArr[i8];
                int i9 = i8 + i4;
                int i10 = (sArr[i9] ^ s) & (-((bArr[(i5 >> 3) + i] >> (i5 & 7)) & 1));
                sArr[i8] = (short) (s ^ i10);
                sArr[i9] = (short) (sArr[i9] ^ i10);
                i5++;
            }
        }
    }

    private static int min(short s, int i) {
        return s < i ? s : i;
    }

    private int mov_columns(byte[][] bArr, short[] sArr, long[] jArr) {
        byte[] bArr2;
        long jLoad8;
        long[] jArr2 = new long[64];
        int i = 32;
        long[] jArr3 = new long[32];
        byte[] bArr3 = new byte[9];
        int i2 = this.PK_NROWS - 32;
        int i3 = i2 / 8;
        int i4 = i2 % 8;
        char c = 0;
        if (this.usePadding) {
            for (int i5 = 0; i5 < 32; i5++) {
                for (int i6 = 0; i6 < 9; i6++) {
                    bArr3[i6] = bArr[i2 + i5][i3 + i6];
                }
                int i7 = 0;
                while (i7 < 8) {
                    int i8 = i7 + 1;
                    bArr3[i7] = (byte) (((bArr3[i7] & 255) >> i4) | (bArr3[i8] << (8 - i4)));
                    i7 = i8;
                }
                jArr2[i5] = Utils.load8(bArr3, 0);
            }
        } else {
            for (int i9 = 0; i9 < 32; i9++) {
                jArr2[i9] = Utils.load8(bArr[i2 + i9], i3);
            }
        }
        long j = 0;
        jArr[0] = 0;
        int i10 = 0;
        while (i10 < 32) {
            long j2 = jArr2[i10];
            int i11 = i10 + 1;
            for (int i12 = i11; i12 < 32; i12++) {
                j2 |= jArr2[i12];
            }
            if (j2 == j) {
                return -1;
            }
            int iCtz = ctz(j2);
            long j3 = iCtz;
            jArr3[i10] = j3;
            jArr[c] = jArr[c] | (1 << ((int) j3));
            for (int i13 = i11; i13 < 32; i13++) {
                long j4 = jArr2[i10];
                jArr2[i10] = j4 ^ (jArr2[i13] & (((j4 >> iCtz) & 1) - 1));
            }
            int i14 = i11;
            while (i14 < 32) {
                long j5 = jArr2[i14];
                jArr2[i14] = j5 ^ (jArr2[i10] & (-((j5 >> iCtz) & 1)));
                i14++;
                iCtz = iCtz;
                c = 0;
            }
            i10 = i11;
            j = 0;
        }
        int i15 = 0;
        while (i15 < 32) {
            int i16 = i15 + 1;
            int i17 = i16;
            while (i17 < 64) {
                int i18 = i2 + i15;
                int i19 = i2 + i17;
                long jSame_mask64 = same_mask64((short) i17, (short) jArr3[i15]) & ((long) (sArr[i18] ^ sArr[i19]));
                sArr[i18] = (short) (((long) sArr[i18]) ^ jSame_mask64);
                sArr[i19] = (short) (jSame_mask64 ^ ((long) sArr[i19]));
                i17++;
                bArr3 = bArr3;
            }
            i15 = i16;
        }
        byte[] bArr4 = bArr3;
        int i20 = 0;
        while (i20 < this.PK_NROWS) {
            if (this.usePadding) {
                for (int i21 = 0; i21 < 9; i21++) {
                    bArr4[i21] = bArr[i20][i3 + i21];
                }
                int i22 = 0;
                while (i22 < 8) {
                    int i23 = i22 + 1;
                    bArr4[i22] = (byte) (((bArr4[i22] & 255) >> i4) | (bArr4[i23] << (8 - i4)));
                    i22 = i23;
                }
                bArr2 = bArr4;
                jLoad8 = Utils.load8(bArr2, 0);
            } else {
                bArr2 = bArr4;
                jLoad8 = Utils.load8(bArr[i20], i3);
            }
            int i24 = 0;
            while (i24 < i) {
                long j6 = jArr3[i24];
                long j7 = ((jLoad8 >> i24) ^ (jLoad8 >> ((int) j6))) & 1;
                jLoad8 = (j7 << i24) ^ ((j7 << ((int) j6)) ^ jLoad8);
                i24++;
                i = 32;
            }
            if (this.usePadding) {
                Utils.store8(bArr2, 0, jLoad8);
                byte[] bArr5 = bArr[i20];
                int i25 = i3 + 8;
                int i26 = 8 - i4;
                bArr5[i25] = (byte) ((((bArr5[i25] & 255) >>> i4) << i4) | ((bArr2[7] & 255) >>> i26));
                bArr5[i3] = (byte) (((bArr2[0] & 255) << i4) | (((bArr5[i3] & 255) << i26) >>> i26));
                for (int i27 = 7; i27 >= 1; i27--) {
                    bArr[i20][i3 + i27] = (byte) (((bArr2[i27] & 255) << i4) | ((bArr2[i27 - 1] & 255) >>> i26));
                }
            } else {
                Utils.store8(bArr[i20], i3, jLoad8);
            }
            i20++;
            bArr4 = bArr2;
            i = 32;
        }
        return 0;
    }

    private int pk_gen(byte[] bArr, byte[] bArr2, int[] iArr, short[] sArr, long[] jArr) {
        int i;
        int i2;
        int i3 = this.SYS_T;
        short[] sArr2 = new short[i3 + 1];
        sArr2[i3] = 1;
        for (int i4 = 0; i4 < this.SYS_T; i4++) {
            sArr2[i4] = Utils.load_gf(bArr2, (i4 * 2) + 40, this.GFMASK);
        }
        int i5 = 1 << this.GFBITS;
        long[] jArr2 = new long[i5];
        for (int i6 = 0; i6 < (1 << this.GFBITS); i6++) {
            long j = iArr[i6];
            jArr2[i6] = j;
            long j2 = j << 31;
            jArr2[i6] = j2;
            long j3 = j2 | ((long) i6);
            jArr2[i6] = j3;
            jArr2[i6] = j3 & Preferences.UPDATE_INTERVAL_DISABLED;
        }
        sort64(jArr2, 0, i5);
        for (int i7 = 1; i7 < (1 << this.GFBITS); i7++) {
            if ((jArr2[i7 - 1] >> 31) == (jArr2[i7] >> 31)) {
                return -1;
            }
        }
        short[] sArr3 = new short[this.SYS_N];
        for (int i8 = 0; i8 < (1 << this.GFBITS); i8++) {
            sArr[i8] = (short) (jArr2[i8] & ((long) this.GFMASK));
        }
        int i9 = 0;
        while (true) {
            i = this.SYS_N;
            if (i9 >= i) {
                break;
            }
            sArr3[i9] = Utils.bitrev(sArr[i9], this.GFBITS);
            i9++;
        }
        short[] sArr4 = new short[i];
        root(sArr4, sArr2, sArr3);
        int i10 = 0;
        while (true) {
            i2 = this.SYS_N;
            if (i10 >= i2) {
                break;
            }
            sArr4[i10] = this.gf.gf_inv(sArr4[i10]);
            i10++;
        }
        byte[][] bArr3 = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, this.PK_NROWS, i2 / 8);
        for (int i11 = 0; i11 < this.PK_NROWS; i11++) {
            for (int i12 = 0; i12 < this.SYS_N / 8; i12++) {
                bArr3[i11][i12] = 0;
            }
        }
        for (int i13 = 0; i13 < this.SYS_T; i13++) {
            for (int i14 = 0; i14 < this.SYS_N; i14 += 8) {
                int i15 = 0;
                while (true) {
                    int i16 = this.GFBITS;
                    if (i15 < i16) {
                        bArr3[(i16 * i13) + i15][i14 / 8] = (byte) (((byte) (((byte) (((byte) (((byte) (((byte) (((byte) (((byte) (((byte) (((byte) (((byte) (((byte) (((byte) (((byte) (((byte) ((sArr4[i14 + 7] >>> i15) & 1)) << 1)) | ((sArr4[i14 + 6] >>> i15) & 1))) << 1)) | ((sArr4[i14 + 5] >>> i15) & 1))) << 1)) | ((sArr4[i14 + 4] >>> i15) & 1))) << 1)) | ((sArr4[i14 + 3] >>> i15) & 1))) << 1)) | ((sArr4[i14 + 2] >>> i15) & 1))) << 1)) | ((sArr4[i14 + 1] >>> i15) & 1))) << 1)) | ((sArr4[i14] >>> i15) & 1));
                        i15++;
                    }
                }
            }
            for (int i17 = 0; i17 < this.SYS_N; i17++) {
                sArr4[i17] = this.gf.gf_mul(sArr4[i17], sArr3[i17]);
            }
        }
        int i18 = 0;
        while (true) {
            int i19 = this.PK_NROWS;
            if (i18 >= (i19 + 7) / 8) {
                if (bArr != null) {
                    if (this.usePadding) {
                        int i20 = i19 % 8;
                        int i21 = 0;
                        int i22 = 0;
                        while (true) {
                            int i23 = this.PK_NROWS;
                            if (i21 >= i23) {
                                break;
                            }
                            int i24 = (i23 - 1) / 8;
                            while (i24 < (this.SYS_N / 8) - 1) {
                                byte[] bArr4 = bArr3[i21];
                                int i25 = (bArr4[i24] & 255) >>> i20;
                                i24++;
                                bArr[i22] = (byte) ((bArr4[i24] << (8 - i20)) | i25);
                                i22++;
                            }
                            bArr[i22] = (byte) ((bArr3[i21][i24] & 255) >>> i20);
                            i21++;
                            i22++;
                        }
                    } else {
                        for (int i26 = 0; i26 < this.PK_NROWS; i26++) {
                            int i27 = 0;
                            int i28 = 0;
                            while (true) {
                                int i29 = this.SYS_N;
                                int i30 = this.PK_NROWS;
                                if (i27 < ((i29 - i30) + 7) / 8) {
                                    bArr[((((i29 - i30) + 7) / 8) * i26) + i28] = bArr3[i26][(i30 / 8) + i27];
                                    i28++;
                                    i27++;
                                }
                            }
                        }
                    }
                }
                return 0;
            }
            for (int i31 = 0; i31 < 8; i31++) {
                int i32 = (i18 * 8) + i31;
                int i33 = this.PK_NROWS;
                if (i32 >= i33) {
                    break;
                }
                if (this.usePivots && i32 == i33 - 32) {
                    if (mov_columns(bArr3, sArr, jArr) != 0) {
                        return -1;
                    }
                }
                for (int i34 = i32 + 1; i34 < this.PK_NROWS; i34++) {
                    byte b = (byte) (-((byte) (((byte) (((byte) (bArr3[i32][i18] ^ bArr3[i34][i18])) >> i31)) & 1)));
                    for (int i35 = 0; i35 < this.SYS_N / 8; i35++) {
                        byte[] bArr5 = bArr3[i32];
                        bArr5[i35] = (byte) (bArr5[i35] ^ (bArr3[i34][i35] & b));
                    }
                }
                if (((bArr3[i32][i18] >> i31) & 1) == 0) {
                    return -1;
                }
                for (int i36 = 0; i36 < this.PK_NROWS; i36++) {
                    if (i36 != i32) {
                        byte b2 = (byte) (-((byte) (((byte) (bArr3[i36][i18] >> i31)) & 1)));
                        for (int i37 = 0; i37 < this.SYS_N / 8; i37++) {
                            byte[] bArr6 = bArr3[i36];
                            bArr6[i37] = (byte) (bArr6[i37] ^ (bArr3[i32][i37] & b2));
                        }
                    }
                }
            }
            i18++;
        }
    }

    private void root(short[] sArr, short[] sArr2, short[] sArr3) {
        for (int i = 0; i < this.SYS_N; i++) {
            sArr[i] = eval(sArr2, sArr3[i]);
        }
    }

    private static byte same_mask32(short s, short s2) {
        return (byte) ((-(((s ^ s2) - 1) >>> 31)) & GF2Field.MASK);
    }

    private static long same_mask64(short s, short s2) {
        return -((((long) (s ^ s2)) - 1) >>> 63);
    }

    private static void sort32(int[] iArr, int i, int i2) {
        int i3 = i2 - i;
        if (i3 < 2) {
            return;
        }
        int i4 = 1;
        while (i4 < i3 - i4) {
            i4 += i4;
        }
        for (int i5 = i4; i5 > 0; i5 >>>= 1) {
            int i6 = 0;
            for (int i7 = 0; i7 < i3 - i5; i7++) {
                if ((i7 & i5) == 0) {
                    int i8 = i + i7;
                    int i9 = i8 + i5;
                    int i10 = iArr[i9];
                    int i11 = iArr[i8];
                    int i12 = i10 ^ i11;
                    int i13 = i10 - i11;
                    int i14 = ((((i10 ^ i13) & i12) ^ i13) >> 31) & i12;
                    iArr[i8] = i11 ^ i14;
                    iArr[i9] = iArr[i9] ^ i14;
                }
            }
            for (int i15 = i4; i15 > i5; i15 >>>= 1) {
                while (i6 < i3 - i15) {
                    if ((i6 & i5) == 0) {
                        int i16 = i + i6;
                        int i17 = i16 + i5;
                        int i18 = iArr[i17];
                        for (int i19 = i15; i19 > i5; i19 >>>= 1) {
                            int i20 = i16 + i19;
                            int i21 = iArr[i20];
                            int i22 = i21 ^ i18;
                            int i23 = i21 - i18;
                            int i24 = i22 & ((i23 ^ ((i23 ^ i21) & i22)) >> 31);
                            i18 ^= i24;
                            iArr[i20] = i21 ^ i24;
                        }
                        iArr[i17] = i18;
                    }
                    i6++;
                }
            }
        }
    }

    private static void sort64(long[] jArr, int i, int i2) {
        int i3 = i2 - i;
        if (i3 < 2) {
            return;
        }
        int i4 = 1;
        while (i4 < i3 - i4) {
            i4 += i4;
        }
        for (int i5 = i4; i5 > 0; i5 >>>= 1) {
            int i6 = 0;
            for (int i7 = 0; i7 < i3 - i5; i7++) {
                if ((i7 & i5) == 0) {
                    int i8 = i + i7;
                    int i9 = i8 + i5;
                    long j = jArr[i9];
                    long j2 = jArr[i8];
                    long j3 = (j ^ j2) & (-((j - j2) >>> 63));
                    jArr[i8] = j2 ^ j3;
                    jArr[i9] = jArr[i9] ^ j3;
                }
            }
            for (int i10 = i4; i10 > i5; i10 >>>= 1) {
                while (i6 < i3 - i10) {
                    if ((i6 & i5) == 0) {
                        int i11 = i + i6;
                        int i12 = i11 + i5;
                        long j4 = jArr[i12];
                        for (int i13 = i10; i13 > i5; i13 >>>= 1) {
                            int i14 = i11 + i13;
                            long j5 = jArr[i14];
                            long j6 = (-((j5 - j4) >>> 63)) & (j4 ^ j5);
                            j4 ^= j6;
                            jArr[i14] = j5 ^ j6;
                        }
                        jArr[i12] = j4;
                    }
                    i6++;
                }
            }
        }
    }

    private void synd(short[] sArr, short[] sArr2, short[] sArr3, byte[] bArr) {
        for (int i = 0; i < this.SYS_T * 2; i++) {
            sArr[i] = 0;
        }
        for (int i2 = 0; i2 < this.SYS_N; i2++) {
            short s = (short) ((bArr[i2 / 8] >> (i2 % 8)) & 1);
            short sEval = eval(sArr2, sArr3[i2]);
            GF gf = this.gf;
            short sGf_inv = gf.gf_inv(gf.gf_mul(sEval, sEval));
            for (int i3 = 0; i3 < this.SYS_T * 2; i3++) {
                GF gf2 = this.gf;
                sArr[i3] = gf2.gf_add(sArr[i3], gf2.gf_mul(sGf_inv, s));
                sGf_inv = this.gf.gf_mul(sGf_inv, sArr3[i2]);
            }
        }
    }

    private void syndrome(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        short[] sArr = new short[this.SYS_N / 8];
        int i = this.PK_NROWS % 8;
        for (int i2 = 0; i2 < this.SYND_BYTES; i2++) {
            bArr[i2] = 0;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.PK_NROWS; i4++) {
            for (int i5 = 0; i5 < this.SYS_N / 8; i5++) {
                sArr[i5] = 0;
            }
            int i6 = 0;
            while (true) {
                int i7 = this.PK_ROW_BYTES;
                if (i6 >= i7) {
                    break;
                }
                sArr[((this.SYS_N / 8) - i7) + i6] = bArr2[i3 + i6];
                i6++;
            }
            if (this.usePadding) {
                for (int i8 = (this.SYS_N / 8) - 1; i8 >= (this.SYS_N / 8) - this.PK_ROW_BYTES; i8--) {
                    sArr[i8] = (short) ((((sArr[i8] & 255) << i) | ((sArr[i8 - 1] & 255) >>> (8 - i))) & GF2Field.MASK);
                }
            }
            int i9 = i4 / 8;
            int i10 = i4 % 8;
            sArr[i9] = (short) (sArr[i9] | (1 << i10));
            byte b = 0;
            for (int i11 = 0; i11 < this.SYS_N / 8; i11++) {
                b = (byte) (b ^ (sArr[i11] & bArr3[i11]));
            }
            byte b2 = (byte) ((b >>> 4) ^ b);
            byte b3 = (byte) (b2 ^ (b2 >>> 2));
            bArr[i9] = (byte) ((((byte) (1 & ((byte) (b3 ^ (b3 >>> 1))))) << i10) | bArr[i9]);
            i3 += this.PK_ROW_BYTES;
        }
    }

    int check_c_padding(byte[] bArr) {
        return ((byte) ((((byte) (((byte) ((bArr[this.SYND_BYTES - 1] & 255) >>> (this.PK_NROWS % 8))) - 1)) & 255) >>> 7)) - 1;
    }

    int check_pk_padding(byte[] bArr) {
        byte b = 0;
        for (int i = 0; i < this.PK_NROWS; i++) {
            int i2 = this.PK_ROW_BYTES;
            b = (byte) (b | bArr[((i * i2) + i2) - 1]);
        }
        return ((byte) ((((byte) (((byte) ((b & 255) >>> (this.PK_NCOLS % 8))) - 1)) & 255) >>> 7)) - 1;
    }

    public byte[] decompress_private_key(byte[] bArr) {
        int i;
        byte[] bArr2 = new byte[getPrivateKeySize()];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        int i2 = (this.SYS_N / 8) + ((1 << this.GFBITS) * 4) + this.IRR_BYTES;
        int i3 = i2 + 32;
        byte[] bArr3 = new byte[i3];
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update((byte) 64);
        sHAKEDigest.update(bArr, 0, 32);
        sHAKEDigest.doFinal(bArr3, 0, i3);
        if (bArr.length <= 40) {
            short[] sArr = new short[this.SYS_T];
            int i4 = this.IRR_BYTES;
            byte[] bArr4 = new byte[i4];
            int i5 = i2 - i4;
            for (int i6 = 0; i6 < this.SYS_T; i6++) {
                sArr[i6] = Utils.load_gf(bArr3, (i6 * 2) + i5, this.GFMASK);
            }
            generate_irr_poly(sArr);
            for (int i7 = 0; i7 < this.SYS_T; i7++) {
                Utils.store_gf(bArr4, i7 * 2, sArr[i7]);
            }
            System.arraycopy(bArr4, 0, bArr2, 40, this.IRR_BYTES);
        }
        int length = bArr.length;
        int i8 = this.IRR_BYTES;
        if (length <= i8 + 40) {
            int i9 = this.GFBITS;
            int[] iArr = new int[1 << i9];
            short[] sArr2 = new short[1 << i9];
            int i10 = (i2 - i8) - ((1 << i9) * 4);
            int i11 = 0;
            while (true) {
                i = this.GFBITS;
                if (i11 >= (1 << i)) {
                    break;
                }
                iArr[i11] = Utils.load4(bArr3, (i11 * 4) + i10);
                i11++;
            }
            if (this.usePivots) {
                pk_gen(null, bArr2, iArr, sArr2, new long[]{0});
            } else {
                int i12 = 1 << i;
                long[] jArr = new long[i12];
                for (int i13 = 0; i13 < (1 << this.GFBITS); i13++) {
                    long j = iArr[i13];
                    jArr[i13] = j;
                    long j2 = j << 31;
                    jArr[i13] = j2;
                    long j3 = j2 | ((long) i13);
                    jArr[i13] = j3;
                    jArr[i13] = j3 & Preferences.UPDATE_INTERVAL_DISABLED;
                }
                sort64(jArr, 0, i12);
                for (int i14 = 0; i14 < (1 << this.GFBITS); i14++) {
                    sArr2[i14] = (short) (jArr[i14] & ((long) this.GFMASK));
                }
            }
            int i15 = this.COND_BYTES;
            byte[] bArr5 = new byte[i15];
            controlbitsfrompermutation(bArr5, sArr2, this.GFBITS, 1 << r2);
            System.arraycopy(bArr5, 0, bArr2, this.IRR_BYTES + 40, i15);
        }
        int privateKeySize = getPrivateKeySize();
        int i16 = this.SYS_N;
        System.arraycopy(bArr3, 0, bArr2, privateKeySize - (i16 / 8), i16 / 8);
        return bArr2;
    }

    public byte[] generate_public_key_from_private_key(byte[] bArr) {
        byte[] bArr2 = new byte[getPublicKeySize()];
        int i = this.GFBITS;
        short[] sArr = new short[1 << i];
        long[] jArr = {0};
        int[] iArr = new int[1 << i];
        int i2 = (this.SYS_N / 8) + ((1 << i) * 4);
        byte[] bArr3 = new byte[i2];
        int i3 = ((i2 - 32) - this.IRR_BYTES) - ((1 << i) * 4);
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update((byte) 64);
        sHAKEDigest.update(bArr, 0, 32);
        sHAKEDigest.doFinal(bArr3, 0, i2);
        for (int i4 = 0; i4 < (1 << this.GFBITS); i4++) {
            iArr[i4] = Utils.load4(bArr3, (i4 * 4) + i3);
        }
        pk_gen(bArr2, bArr, iArr, sArr, jArr);
        return bArr2;
    }

    public int getCipherTextSize() {
        return this.SYND_BYTES + 32;
    }

    public int getCondBytes() {
        return this.COND_BYTES;
    }

    public int getDefaultSessionKeySize() {
        return this.defaultKeySize;
    }

    public int getIrrBytes() {
        return this.IRR_BYTES;
    }

    public int getPrivateKeySize() {
        return this.COND_BYTES + this.IRR_BYTES + (this.SYS_N / 8) + 40;
    }

    public int getPublicKeySize() {
        if (!this.usePadding) {
            return (this.PK_NROWS * this.PK_NCOLS) / 8;
        }
        int i = this.PK_NROWS;
        return i * ((this.SYS_N / 8) - ((i - 1) / 8));
    }

    public int kem_dec(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[32];
        int i = this.SYS_N / 8;
        byte[] bArr5 = new byte[i];
        int iCheck_c_padding = this.usePadding ? check_c_padding(bArr2) : 0;
        byte bDecrypt = (byte) decrypt(bArr5, bArr3, bArr2);
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update((byte) 2);
        sHAKEDigest.update(bArr5, 0, i);
        sHAKEDigest.doFinal(bArr4, 0, 32);
        byte b = 0;
        for (int i2 = 0; i2 < 32; i2++) {
            b = (byte) (b | (bArr4[i2] ^ bArr2[this.SYND_BYTES + i2]));
        }
        short s = (short) (((short) (((short) (((short) (bDecrypt | b)) - 1)) >> 8)) & 255);
        int i3 = (this.SYS_N / 8) + 1 + this.SYND_BYTES + 32;
        byte[] bArr6 = new byte[i3];
        bArr6[0] = (byte) (s & 1);
        int i4 = 0;
        while (i4 < this.SYS_N / 8) {
            int i5 = i4 + 1;
            bArr6[i5] = (byte) ((bArr3[i4 + 40 + this.IRR_BYTES + this.COND_BYTES] & (~s)) | (bArr5[i4] & s));
            i4 = i5;
        }
        for (int i6 = 0; i6 < this.SYND_BYTES + 32; i6++) {
            bArr6[(this.SYS_N / 8) + 1 + i6] = bArr2[i6];
        }
        SHAKEDigest sHAKEDigest2 = new SHAKEDigest(256);
        sHAKEDigest2.update(bArr6, 0, i3);
        sHAKEDigest2.doFinal(bArr, 0, bArr.length);
        if (!this.usePadding) {
            return 0;
        }
        byte b2 = (byte) iCheck_c_padding;
        for (int i7 = 0; i7 < bArr.length; i7++) {
            bArr[i7] = (byte) (bArr[i7] | b2);
        }
        return iCheck_c_padding;
    }

    public int kem_enc(byte[] bArr, byte[] bArr2, byte[] bArr3, SecureRandom secureRandom) {
        int i = this.SYS_N / 8;
        byte[] bArr4 = new byte[i];
        int iCheck_pk_padding = this.usePadding ? check_pk_padding(bArr3) : 0;
        encrypt(bArr, bArr3, bArr4, secureRandom);
        SHAKEDigest sHAKEDigest = new SHAKEDigest(256);
        sHAKEDigest.update((byte) 2);
        sHAKEDigest.update(bArr4, 0, i);
        sHAKEDigest.doFinal(bArr, this.SYND_BYTES, 32);
        sHAKEDigest.update((byte) 1);
        sHAKEDigest.update(bArr4, 0, i);
        sHAKEDigest.update(bArr, 0, bArr.length);
        sHAKEDigest.doFinal(bArr2, 0, bArr2.length);
        if (!this.usePadding) {
            return 0;
        }
        byte b = (byte) (((byte) iCheck_pk_padding) ^ 255);
        for (int i2 = 0; i2 < this.SYND_BYTES + 32; i2++) {
            bArr[i2] = (byte) (bArr[i2] & b);
        }
        for (int i3 = 0; i3 < 32; i3++) {
            bArr2[i3] = (byte) (bArr2[i3] & b);
        }
        return iCheck_pk_padding;
    }

    public void kem_keypair(byte[] bArr, byte[] bArr2, SecureRandom secureRandom) {
        int i;
        int i2;
        short[] sArr;
        byte[] bArr3;
        SHAKEDigest sHAKEDigest;
        int i3;
        long j;
        int i4 = 32;
        byte[] bArr4 = new byte[32];
        int i5 = 0;
        byte[] bArr5 = {64};
        secureRandom.nextBytes(bArr4);
        int i6 = (this.SYS_N / 8) + ((1 << this.GFBITS) * 4) + (this.SYS_T * 2);
        int i7 = i6 + 32;
        byte[] bArr6 = new byte[i7];
        long[] jArr = {0};
        SHAKEDigest sHAKEDigest2 = new SHAKEDigest(256);
        byte[] bArr7 = bArr4;
        while (true) {
            sHAKEDigest2.update(bArr5, i5, 1);
            sHAKEDigest2.update(bArr4, i5, bArr4.length);
            sHAKEDigest2.doFinal(bArr6, i5, i7);
            byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr6, i6, i6 + 32);
            System.arraycopy(bArr7, i5, bArr2, i5, i4);
            byte[] bArrCopyOfRange2 = Arrays.copyOfRange(bArrCopyOfRange, i5, i4);
            int i8 = this.SYS_T;
            short[] sArr2 = new short[i8];
            int i9 = i6 - (i8 * 2);
            for (int i10 = i5; i10 < this.SYS_T; i10++) {
                sArr2[i10] = Utils.load_gf(bArr6, (i10 * 2) + i9, this.GFMASK);
            }
            if (generate_irr_poly(sArr2) != -1) {
                for (int i11 = i5; i11 < this.SYS_T; i11++) {
                    Utils.store_gf(bArr2, 40 + (i11 * 2), sArr2[i11]);
                }
                int i12 = this.GFBITS;
                int[] iArr = new int[1 << i12];
                i = i9 - ((1 << i12) * 4);
                int i13 = 0;
                while (true) {
                    i2 = this.GFBITS;
                    if (i13 >= (1 << i2)) {
                        break;
                    }
                    iArr[i13] = Utils.load4(bArr6, (i13 * 4) + i);
                    i13++;
                }
                sArr = new short[1 << i2];
                bArr3 = bArrCopyOfRange;
                sHAKEDigest = sHAKEDigest2;
                if (pk_gen(bArr, bArr2, iArr, sArr, jArr) != -1) {
                    break;
                }
            } else {
                bArr3 = bArrCopyOfRange;
                sHAKEDigest = sHAKEDigest2;
            }
            bArr7 = bArrCopyOfRange2;
            bArr4 = bArr3;
            sHAKEDigest2 = sHAKEDigest;
            i4 = 32;
            i5 = 0;
        }
        int i14 = this.COND_BYTES;
        byte[] bArr8 = new byte[i14];
        controlbitsfrompermutation(bArr8, sArr, this.GFBITS, 1 << r2);
        System.arraycopy(bArr8, 0, bArr2, this.IRR_BYTES + 40, i14);
        int i15 = this.SYS_N;
        System.arraycopy(bArr6, i - (i15 / 8), bArr2, bArr2.length - (i15 / 8), i15 / 8);
        if (this.usePivots) {
            i3 = 32;
            j = jArr[0];
        } else {
            j = BodyPartID.bodyIdMax;
            i3 = 32;
        }
        Utils.store8(bArr2, i3, j);
    }
}
