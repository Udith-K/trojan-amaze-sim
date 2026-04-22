package org.bouncycastle.its.jcajce;

import java.io.OutputStream;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Signature;
import java.security.interfaces.ECPrivateKey;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.its.ITSCertificate;
import org.bouncycastle.its.operator.ITSContentSigner;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class JcaITSContentSigner implements ITSContentSigner {
    private final ASN1ObjectIdentifier curveID;
    private final DigestCalculator digest;
    private final AlgorithmIdentifier digestAlgo;
    private final JcaJceHelper helper;
    private final byte[] parentData;
    private final byte[] parentDigest;
    private final ECPrivateKey privateKey;
    private final String signer;
    private final ITSCertificate signerCert;

    public static class Builder {
        private JcaJceHelper helper = new DefaultJcaJceHelper();

        public JcaITSContentSigner build(PrivateKey privateKey) {
            return new JcaITSContentSigner((ECPrivateKey) privateKey, null, this.helper);
        }

        public JcaITSContentSigner build(PrivateKey privateKey, ITSCertificate iTSCertificate) {
            return new JcaITSContentSigner((ECPrivateKey) privateKey, iTSCertificate, this.helper);
        }

        public Builder setProvider(String str) {
            this.helper = new NamedJcaJceHelper(str);
            return this;
        }

        public Builder setProvider(Provider provider) {
            this.helper = new ProviderJcaJceHelper(provider);
            return this;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0071 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private JcaITSContentSigner(java.security.interfaces.ECPrivateKey r3, org.bouncycastle.its.ITSCertificate r4, org.bouncycastle.jcajce.util.JcaJceHelper r5) {
        /*
            Method dump skipped, instruction units count: 225
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.its.jcajce.JcaITSContentSigner.<init>(java.security.interfaces.ECPrivateKey, org.bouncycastle.its.ITSCertificate, org.bouncycastle.jcajce.util.JcaJceHelper):void");
    }

    @Override // org.bouncycastle.its.operator.ITSContentSigner
    public ITSCertificate getAssociatedCertificate() {
        return this.signerCert;
    }

    @Override // org.bouncycastle.its.operator.ITSContentSigner
    public byte[] getAssociatedCertificateDigest() {
        return Arrays.clone(this.parentDigest);
    }

    @Override // org.bouncycastle.its.operator.ITSContentSigner
    public ASN1ObjectIdentifier getCurveID() {
        return this.curveID;
    }

    @Override // org.bouncycastle.its.operator.ITSContentSigner
    public AlgorithmIdentifier getDigestAlgorithm() {
        return this.digestAlgo;
    }

    @Override // org.bouncycastle.its.operator.ITSContentSigner
    public OutputStream getOutputStream() {
        return this.digest.getOutputStream();
    }

    @Override // org.bouncycastle.its.operator.ITSContentSigner
    public byte[] getSignature() {
        byte[] digest = this.digest.getDigest();
        try {
            Signature signatureCreateSignature = this.helper.createSignature(this.signer);
            signatureCreateSignature.initSign(this.privateKey);
            signatureCreateSignature.update(digest, 0, digest.length);
            byte[] bArr = this.parentDigest;
            signatureCreateSignature.update(bArr, 0, bArr.length);
            return signatureCreateSignature.sign();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override // org.bouncycastle.its.operator.ITSContentSigner
    public boolean isForSelfSigning() {
        return this.parentData == null;
    }
}
