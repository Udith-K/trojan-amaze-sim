package org.bouncycastle.pqc.crypto.cmce;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

/* JADX INFO: loaded from: classes2.dex */
public class CMCEKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private CMCEKeyGenerationParameters cmceParams;
    private int m;
    private int n;
    private SecureRandom random;
    private int t;

    private AsymmetricCipherKeyPair genKeyPair() {
        CMCEEngine engine = this.cmceParams.getParameters().getEngine();
        byte[] bArr = new byte[engine.getPrivateKeySize()];
        byte[] bArr2 = new byte[engine.getPublicKeySize()];
        engine.kem_keypair(bArr2, bArr, this.random);
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new CMCEPublicKeyParameters(this.cmceParams.getParameters(), bArr2), (AsymmetricKeyParameter) new CMCEPrivateKeyParameters(this.cmceParams.getParameters(), bArr));
    }

    private void initialize(KeyGenerationParameters keyGenerationParameters) {
        this.cmceParams = (CMCEKeyGenerationParameters) keyGenerationParameters;
        this.random = keyGenerationParameters.getRandom();
        this.m = this.cmceParams.getParameters().getM();
        this.n = this.cmceParams.getParameters().getN();
        this.t = this.cmceParams.getParameters().getT();
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        return genKeyPair();
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        initialize(keyGenerationParameters);
    }
}
