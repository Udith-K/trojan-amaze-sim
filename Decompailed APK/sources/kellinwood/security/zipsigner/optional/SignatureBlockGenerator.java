package kellinwood.security.zipsigner.optional;

import java.util.Collections;
import kellinwood.security.zipsigner.KeySet;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;

/* JADX INFO: loaded from: classes.dex */
public class SignatureBlockGenerator {
    public static byte[] generate(KeySet keySet, byte[] bArr) {
        try {
            BouncyCastleProvider bouncyCastleProvider = new BouncyCastleProvider();
            CMSProcessableByteArray cMSProcessableByteArray = new CMSProcessableByteArray(bArr);
            JcaCertStore jcaCertStore = new JcaCertStore(Collections.singletonList(keySet.getPublicKey()));
            CMSSignedDataGenerator cMSSignedDataGenerator = new CMSSignedDataGenerator();
            ContentSigner contentSignerBuild = new JcaContentSignerBuilder(keySet.getSignatureAlgorithm()).setProvider(bouncyCastleProvider).build(keySet.getPrivateKey());
            JcaSignerInfoGeneratorBuilder jcaSignerInfoGeneratorBuilder = new JcaSignerInfoGeneratorBuilder(new JcaDigestCalculatorProviderBuilder().setProvider(bouncyCastleProvider).build());
            jcaSignerInfoGeneratorBuilder.setDirectSignature(true);
            cMSSignedDataGenerator.addSignerInfoGenerator(jcaSignerInfoGeneratorBuilder.build(contentSignerBuild, keySet.getPublicKey()));
            cMSSignedDataGenerator.addCertificates(jcaCertStore);
            return cMSSignedDataGenerator.generate(cMSProcessableByteArray, false).toASN1Structure().getEncoded(ASN1Encoding.DER);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
