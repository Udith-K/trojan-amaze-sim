package kellinwood.security.zipsigner;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import org.fdroid.fdroid.nearby.LocalRepoKeyStore;

/* JADX INFO: loaded from: classes.dex */
public class KeySet {
    String name;
    PrivateKey privateKey;
    X509Certificate publicKey;
    byte[] sigBlockTemplate;
    String signatureAlgorithm;

    public KeySet() {
        this.publicKey = null;
        this.privateKey = null;
        this.sigBlockTemplate = null;
        this.signatureAlgorithm = LocalRepoKeyStore.DEFAULT_SIG_ALG;
    }

    public KeySet(String str, X509Certificate x509Certificate, PrivateKey privateKey, byte[] bArr) {
        this.signatureAlgorithm = LocalRepoKeyStore.DEFAULT_SIG_ALG;
        this.name = str;
        this.publicKey = x509Certificate;
        this.privateKey = privateKey;
        this.sigBlockTemplate = bArr;
    }

    public KeySet(String str, X509Certificate x509Certificate, PrivateKey privateKey, String str2, byte[] bArr) {
        this.sigBlockTemplate = null;
        this.signatureAlgorithm = LocalRepoKeyStore.DEFAULT_SIG_ALG;
        this.name = str;
        this.publicKey = x509Certificate;
        this.privateKey = privateKey;
        if (str2 != null) {
            this.signatureAlgorithm = str2;
        }
        this.sigBlockTemplate = bArr;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public X509Certificate getPublicKey() {
        return this.publicKey;
    }

    public void setPublicKey(X509Certificate x509Certificate) {
        this.publicKey = x509Certificate;
    }

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public byte[] getSigBlockTemplate() {
        return this.sigBlockTemplate;
    }

    public void setSigBlockTemplate(byte[] bArr) {
        this.sigBlockTemplate = bArr;
    }

    public String getSignatureAlgorithm() {
        return this.signatureAlgorithm;
    }

    public void setSignatureAlgorithm(String str) {
        if (str == null) {
            return;
        }
        this.signatureAlgorithm = str;
    }
}
