package org.bouncycastle.pqc.jcajce.provider.sphincsplus;

import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.NullDigest;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusSigner;

/* JADX INFO: loaded from: classes2.dex */
public class SignatureSpi extends java.security.SignatureSpi {
    private final Digest digest;
    private final SPHINCSPlusSigner signer;

    public static class Direct extends SignatureSpi {
        public Direct() {
            super(new NullDigest(), new SPHINCSPlusSigner());
        }
    }

    protected SignatureSpi(Digest digest, SPHINCSPlusSigner sPHINCSPlusSigner) {
        this.digest = digest;
        this.signer = sPHINCSPlusSigner;
    }

    @Override // java.security.SignatureSpi
    protected Object engineGetParameter(String str) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    @Override // java.security.SignatureSpi
    protected void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
        if (!(privateKey instanceof BCSPHINCSPlusPrivateKey)) {
            throw new InvalidKeyException("unknown private key passed to SPHINCS-256");
        }
        CipherParameters keyParams = ((BCSPHINCSPlusPrivateKey) privateKey).getKeyParams();
        SecureRandom secureRandom = ((java.security.SignatureSpi) this).appRandom;
        if (secureRandom != null) {
            this.signer.init(true, new ParametersWithRandom(keyParams, secureRandom));
        } else {
            this.signer.init(true, keyParams);
        }
    }

    @Override // java.security.SignatureSpi
    protected void engineInitSign(PrivateKey privateKey, SecureRandom secureRandom) throws InvalidKeyException {
        ((java.security.SignatureSpi) this).appRandom = secureRandom;
        engineInitSign(privateKey);
    }

    @Override // java.security.SignatureSpi
    protected void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        if (!(publicKey instanceof BCSPHINCSPlusPublicKey)) {
            throw new InvalidKeyException("unknown public key passed to SPHINCS-256");
        }
        this.signer.init(false, ((BCSPHINCSPlusPublicKey) publicKey).getKeyParams());
    }

    @Override // java.security.SignatureSpi
    protected void engineSetParameter(String str, Object obj) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    @Override // java.security.SignatureSpi
    protected void engineSetParameter(AlgorithmParameterSpec algorithmParameterSpec) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    @Override // java.security.SignatureSpi
    protected byte[] engineSign() throws SignatureException {
        byte[] bArr = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(bArr, 0);
        try {
            return this.signer.generateSignature(bArr);
        } catch (Exception e) {
            throw new SignatureException(e.toString());
        }
    }

    @Override // java.security.SignatureSpi
    protected void engineUpdate(byte b) throws SignatureException {
        this.digest.update(b);
    }

    @Override // java.security.SignatureSpi
    protected void engineUpdate(byte[] bArr, int i, int i2) throws SignatureException {
        this.digest.update(bArr, i, i2);
    }

    @Override // java.security.SignatureSpi
    protected boolean engineVerify(byte[] bArr) throws SignatureException {
        byte[] bArr2 = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(bArr2, 0);
        return this.signer.verifySignature(bArr2, bArr);
    }
}
