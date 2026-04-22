package org.bouncycastle.pqc.crypto.frodo;

import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.pqc.crypto.frodo.FrodoMatrixGenerator;

/* JADX INFO: loaded from: classes2.dex */
public class FrodoParameters implements CipherParameters {
    private static final short[] cdf_table1344;
    private static final short[] cdf_table640;
    private static final short[] cdf_table976;
    public static final FrodoParameters frodokem19888r3;
    public static final FrodoParameters frodokem19888shaker3;
    public static final FrodoParameters frodokem31296r3;
    public static final FrodoParameters frodokem31296shaker3;
    public static final FrodoParameters frodokem43088r3;
    public static final FrodoParameters frodokem43088shaker3;
    private final int B;
    private final int D;
    private final short[] cdf_table;
    private final int defaultKeySize;
    private final Xof digest;
    private final FrodoEngine engine;
    private final FrodoMatrixGenerator mGen;
    private final int n;
    private final String name;

    static {
        short[] sArr = {4643, 13363, 20579, 25843, 29227, 31145, 32103, 32525, 32689, 32745, 32762, 32766, Short.MAX_VALUE};
        cdf_table640 = sArr;
        short[] sArr2 = {5638, 15915, 23689, 28571, 31116, 32217, 32613, 32731, 32760, 32766, Short.MAX_VALUE};
        cdf_table976 = sArr2;
        short[] sArr3 = {9142, 23462, 30338, 32361, 32725, 32765, Short.MAX_VALUE};
        cdf_table1344 = sArr3;
        frodokem19888r3 = new FrodoParameters("frodokem19888", 640, 15, 2, sArr, new SHAKEDigest(128), new FrodoMatrixGenerator.Aes128MatrixGenerator(640, 32768), 64);
        frodokem19888shaker3 = new FrodoParameters("frodokem19888shake", 640, 15, 2, sArr, new SHAKEDigest(128), new FrodoMatrixGenerator.Shake128MatrixGenerator(640, 32768), 64);
        frodokem31296r3 = new FrodoParameters("frodokem31296", 976, 16, 3, sArr2, new SHAKEDigest(256), new FrodoMatrixGenerator.Aes128MatrixGenerator(976, PKIFailureInfo.notAuthorized), 96);
        frodokem31296shaker3 = new FrodoParameters("frodokem31296shake", 976, 16, 3, sArr2, new SHAKEDigest(256), new FrodoMatrixGenerator.Shake128MatrixGenerator(976, PKIFailureInfo.notAuthorized), 96);
        frodokem43088r3 = new FrodoParameters("frodokem43088", 1344, 16, 4, sArr3, new SHAKEDigest(256), new FrodoMatrixGenerator.Aes128MatrixGenerator(1344, PKIFailureInfo.notAuthorized), 128);
        frodokem43088shaker3 = new FrodoParameters("frodokem43088shake", 1344, 16, 4, sArr3, new SHAKEDigest(256), new FrodoMatrixGenerator.Shake128MatrixGenerator(1344, PKIFailureInfo.notAuthorized), 128);
    }

    public FrodoParameters(String str, int i, int i2, int i3, short[] sArr, Xof xof, FrodoMatrixGenerator frodoMatrixGenerator, int i4) {
        this.name = str;
        this.n = i;
        this.D = i2;
        this.B = i3;
        this.cdf_table = sArr;
        this.digest = xof;
        this.mGen = frodoMatrixGenerator;
        this.defaultKeySize = i4;
        this.engine = new FrodoEngine(i, i2, i3, sArr, xof, frodoMatrixGenerator);
    }

    public int getB() {
        return this.B;
    }

    public short[] getCdf_table() {
        return this.cdf_table;
    }

    public int getD() {
        return this.D;
    }

    public int getDefaultKeySize() {
        return this.defaultKeySize;
    }

    public Xof getDigest() {
        return this.digest;
    }

    FrodoEngine getEngine() {
        return this.engine;
    }

    public int getN() {
        return this.n;
    }

    public String getName() {
        return this.name;
    }

    public FrodoMatrixGenerator getmGen() {
        return this.mGen;
    }
}
