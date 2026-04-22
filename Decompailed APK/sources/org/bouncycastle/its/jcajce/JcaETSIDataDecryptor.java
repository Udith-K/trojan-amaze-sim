package org.bouncycastle.its.jcajce;

import java.security.PrivateKey;
import java.security.Provider;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import org.bouncycastle.its.operator.ETSIDataDecryptor;
import org.bouncycastle.jcajce.spec.IESKEMParameterSpec;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class JcaETSIDataDecryptor implements ETSIDataDecryptor {
    private final JcaJceHelper helper;
    private final PrivateKey privateKey;
    private final byte[] recipientHash;
    private SecretKey secretKey = null;

    public static class Builder {
        private final PrivateKey key;
        private JcaJceHelper provider;
        private final byte[] recipientHash;

        public Builder(PrivateKey privateKey, byte[] bArr) {
            this.key = privateKey;
            this.recipientHash = Arrays.clone(bArr);
        }

        public JcaETSIDataDecryptor build() {
            return new JcaETSIDataDecryptor(this.key, this.recipientHash, this.provider);
        }

        public Builder provider(String str) {
            this.provider = new NamedJcaJceHelper(str);
            return this;
        }

        public Builder provider(Provider provider) {
            this.provider = new ProviderJcaJceHelper(provider);
            return this;
        }
    }

    JcaETSIDataDecryptor(PrivateKey privateKey, byte[] bArr, JcaJceHelper jcaJceHelper) {
        this.privateKey = privateKey;
        this.helper = jcaJceHelper;
        this.recipientHash = bArr;
    }

    public static Builder builder(PrivateKey privateKey, byte[] bArr) {
        return new Builder(privateKey, bArr);
    }

    @Override // org.bouncycastle.its.operator.ETSIDataDecryptor
    public byte[] decrypt(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        try {
            Cipher cipherCreateCipher = this.helper.createCipher("ETSIKEMwithSHA256");
            cipherCreateCipher.init(4, this.privateKey, new IESKEMParameterSpec(this.recipientHash));
            this.secretKey = (SecretKey) cipherCreateCipher.unwrap(bArr, "AES", 3);
            Cipher cipherCreateCipher2 = this.helper.createCipher("CCM");
            cipherCreateCipher2.init(2, this.secretKey, ClassUtil.getGCMSpec(bArr3, 128));
            return cipherCreateCipher2.doFinal(bArr2);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override // org.bouncycastle.its.operator.ETSIDataDecryptor
    public byte[] getKey() {
        SecretKey secretKey = this.secretKey;
        if (secretKey != null) {
            return secretKey.getEncoded();
        }
        throw new IllegalStateException("no secret key recovered");
    }
}
