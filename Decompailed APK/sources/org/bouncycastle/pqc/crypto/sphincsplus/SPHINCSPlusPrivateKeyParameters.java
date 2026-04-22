package org.bouncycastle.pqc.crypto.sphincsplus;

import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* JADX INFO: loaded from: classes2.dex */
public class SPHINCSPlusPrivateKeyParameters extends SPHINCSPlusKeyParameters {
    final PK pk;
    final SK sk;

    SPHINCSPlusPrivateKeyParameters(SPHINCSPlusParameters sPHINCSPlusParameters, SK sk, PK pk) {
        super(true, sPHINCSPlusParameters);
        this.sk = sk;
        this.pk = pk;
    }

    public SPHINCSPlusPrivateKeyParameters(SPHINCSPlusParameters sPHINCSPlusParameters, byte[] bArr) {
        super(true, sPHINCSPlusParameters);
        int i = sPHINCSPlusParameters.getEngine().N;
        int i2 = i * 4;
        if (bArr.length != i2) {
            throw new IllegalArgumentException("private key encoding does not match parameters");
        }
        int i3 = i * 2;
        this.sk = new SK(Arrays.copyOfRange(bArr, 0, i), Arrays.copyOfRange(bArr, i, i3));
        int i4 = i * 3;
        this.pk = new PK(Arrays.copyOfRange(bArr, i3, i4), Arrays.copyOfRange(bArr, i4, i2));
    }

    public byte[] getEncoded() {
        byte[] bArrIntToBigEndian = Pack.intToBigEndian(SPHINCSPlusParameters.getID(getParameters()).intValue());
        SK sk = this.sk;
        byte[] bArr = sk.seed;
        byte[] bArr2 = sk.prf;
        PK pk = this.pk;
        return Arrays.concatenate(bArrIntToBigEndian, Arrays.concatenate(bArr, bArr2, pk.seed, pk.root));
    }

    public byte[] getEncodedPublicKey() {
        byte[] bArrIntToBigEndian = Pack.intToBigEndian(SPHINCSPlusParameters.getID(getParameters()).intValue());
        PK pk = this.pk;
        return Arrays.concatenate(bArrIntToBigEndian, pk.seed, pk.root);
    }

    public byte[] getPrf() {
        return Arrays.clone(this.sk.prf);
    }

    public byte[] getPublicKey() {
        PK pk = this.pk;
        return Arrays.concatenate(pk.seed, pk.root);
    }

    public byte[] getPublicSeed() {
        return Arrays.clone(this.pk.seed);
    }

    public byte[] getSeed() {
        return Arrays.clone(this.sk.seed);
    }
}
