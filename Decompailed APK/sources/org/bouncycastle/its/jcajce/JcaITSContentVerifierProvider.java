package org.bouncycastle.its.jcajce;

import java.io.IOException;
import java.io.OutputStream;
import java.security.Provider;
import java.security.Signature;
import java.security.interfaces.ECPublicKey;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.its.ITSCertificate;
import org.bouncycastle.its.ITSPublicVerificationKey;
import org.bouncycastle.its.operator.ITSContentVerifierProvider;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.oer.OEREncoder;
import org.bouncycastle.oer.its.ieee1609dot2.VerificationKeyIndicator;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.PublicVerificationKey;
import org.bouncycastle.oer.its.template.ieee1609dot2.IEEE1609dot2;
import org.bouncycastle.operator.ContentVerifier;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class JcaITSContentVerifierProvider implements ITSContentVerifierProvider {
    private AlgorithmIdentifier digestAlgo;
    private final JcaJceHelper helper;
    private final ITSCertificate issuer;
    private final byte[] parentData;
    private ECPublicKey pubParams;
    private int sigChoice;

    public static class Builder {
        private JcaJceHelper helper = new DefaultJcaJceHelper();

        public JcaITSContentVerifierProvider build(ITSCertificate iTSCertificate) {
            return new JcaITSContentVerifierProvider(iTSCertificate, this.helper);
        }

        public JcaITSContentVerifierProvider build(ITSPublicVerificationKey iTSPublicVerificationKey) {
            return new JcaITSContentVerifierProvider(iTSPublicVerificationKey, this.helper);
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

    private JcaITSContentVerifierProvider(ITSCertificate iTSCertificate, JcaJceHelper jcaJceHelper) {
        this.issuer = iTSCertificate;
        this.helper = jcaJceHelper;
        try {
            this.parentData = iTSCertificate.getEncoded();
            VerificationKeyIndicator verifyKeyIndicator = iTSCertificate.toASN1Structure().getToBeSigned().getVerifyKeyIndicator();
            if (!(verifyKeyIndicator.getVerificationKeyIndicator() instanceof PublicVerificationKey)) {
                throw new IllegalArgumentException("not public verification key");
            }
            initForPvi(PublicVerificationKey.getInstance(verifyKeyIndicator.getVerificationKeyIndicator()), jcaJceHelper);
        } catch (IOException e) {
            throw new IllegalStateException("unable to extract parent data: " + e.getMessage());
        }
    }

    private JcaITSContentVerifierProvider(ITSPublicVerificationKey iTSPublicVerificationKey, JcaJceHelper jcaJceHelper) {
        this.issuer = null;
        this.parentData = null;
        this.helper = jcaJceHelper;
        initForPvi(iTSPublicVerificationKey.toASN1Structure(), jcaJceHelper);
    }

    private void initForPvi(PublicVerificationKey publicVerificationKey, JcaJceHelper jcaJceHelper) {
        AlgorithmIdentifier algorithmIdentifier;
        this.sigChoice = publicVerificationKey.getChoice();
        int choice = publicVerificationKey.getChoice();
        if (choice == 0 || choice == 1) {
            algorithmIdentifier = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256);
        } else {
            if (choice != 2) {
                throw new IllegalArgumentException("unknown key type");
            }
            algorithmIdentifier = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha384);
        }
        this.digestAlgo = algorithmIdentifier;
        this.pubParams = (ECPublicKey) new JcaITSPublicVerificationKey(publicVerificationKey, jcaJceHelper).getKey();
    }

    @Override // org.bouncycastle.its.operator.ITSContentVerifierProvider
    public ContentVerifier get(int i) throws OperatorCreationException {
        byte[] digest;
        JcaJceHelper jcaJceHelper;
        String str;
        if (this.sigChoice != i) {
            throw new OperatorCreationException("wrong verifier for algorithm: " + i);
        }
        try {
            final DigestCalculator digestCalculator = new JcaDigestCalculatorProviderBuilder().setHelper(this.helper).build().get(this.digestAlgo);
            try {
                final OutputStream outputStream = digestCalculator.getOutputStream();
                byte[] bArr = this.parentData;
                if (bArr != null) {
                    outputStream.write(bArr, 0, bArr.length);
                }
                final byte[] digest2 = digestCalculator.getDigest();
                ITSCertificate iTSCertificate = this.issuer;
                if (iTSCertificate == null || !iTSCertificate.getIssuer().isSelf()) {
                    digest = null;
                } else {
                    byte[] byteArray = OEREncoder.toByteArray(this.issuer.toASN1Structure().getToBeSigned(), IEEE1609dot2.ToBeSignedCertificate.build());
                    outputStream.write(byteArray, 0, byteArray.length);
                    digest = digestCalculator.getDigest();
                }
                final byte[] bArr2 = digest;
                int i2 = this.sigChoice;
                if (i2 == 0 || i2 == 1) {
                    jcaJceHelper = this.helper;
                    str = "SHA256withECDSA";
                } else {
                    if (i2 != 2) {
                        throw new IllegalArgumentException("choice " + this.sigChoice + " not supported");
                    }
                    jcaJceHelper = this.helper;
                    str = "SHA384withECDSA";
                }
                final Signature signatureCreateSignature = jcaJceHelper.createSignature(str);
                return new ContentVerifier() { // from class: org.bouncycastle.its.jcajce.JcaITSContentVerifierProvider.1
                    @Override // org.bouncycastle.operator.ContentVerifier
                    public AlgorithmIdentifier getAlgorithmIdentifier() {
                        return null;
                    }

                    @Override // org.bouncycastle.operator.ContentVerifier
                    public OutputStream getOutputStream() {
                        return outputStream;
                    }

                    @Override // org.bouncycastle.operator.ContentVerifier
                    public boolean verify(byte[] bArr3) {
                        byte[] digest3 = digestCalculator.getDigest();
                        try {
                            signatureCreateSignature.initVerify(JcaITSContentVerifierProvider.this.pubParams);
                            signatureCreateSignature.update(digest3);
                            byte[] bArr4 = bArr2;
                            if (bArr4 == null || !Arrays.areEqual(digest3, bArr4)) {
                                signatureCreateSignature.update(digest2);
                            } else {
                                signatureCreateSignature.update(digestCalculator.getDigest());
                            }
                            return signatureCreateSignature.verify(bArr3);
                        } catch (Exception e) {
                            throw new RuntimeException(e.getMessage(), e);
                        }
                    }
                };
            } catch (Exception e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
        } catch (Exception e2) {
            throw new IllegalStateException(e2.getMessage(), e2);
        }
    }

    @Override // org.bouncycastle.its.operator.ITSContentVerifierProvider
    public ITSCertificate getAssociatedCertificate() {
        return this.issuer;
    }

    @Override // org.bouncycastle.its.operator.ITSContentVerifierProvider
    public boolean hasAssociatedCertificate() {
        return this.issuer != null;
    }
}
