package org.bouncycastle.pqc.jcajce.provider;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.pqc.jcajce.provider.saber.SABERKeyFactorySpi;

/* JADX INFO: loaded from: classes2.dex */
public class SABER {
    private static final String PREFIX = "org.bouncycastle.pqc.jcajce.provider.saber.";

    public static class Mappings extends AsymmetricAlgorithmProvider {
        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("KeyFactory.SABER", "org.bouncycastle.pqc.jcajce.provider.saber.SABERKeyFactorySpi");
            configurableProvider.addAlgorithm("KeyPairGenerator.SABER", "org.bouncycastle.pqc.jcajce.provider.saber.SABERKeyPairGeneratorSpi");
            configurableProvider.addAlgorithm("KeyGenerator.SABER", "org.bouncycastle.pqc.jcajce.provider.saber.SABERKeyGeneratorSpi");
            SABERKeyFactorySpi sABERKeyFactorySpi = new SABERKeyFactorySpi();
            configurableProvider.addAlgorithm("Cipher.SABER", "org.bouncycastle.pqc.jcajce.provider.saber.SABERCipherSpi$Base");
            StringBuilder sb = new StringBuilder();
            sb.append("Alg.Alias.Cipher.");
            ASN1ObjectIdentifier aSN1ObjectIdentifier = BCObjectIdentifiers.pqc_kem_saber;
            sb.append(aSN1ObjectIdentifier);
            configurableProvider.addAlgorithm(sb.toString(), "SABER");
            registerOid(configurableProvider, aSN1ObjectIdentifier, "SABER", sABERKeyFactorySpi);
            registerOidAlgorithmParameters(configurableProvider, aSN1ObjectIdentifier, "SABER");
        }
    }
}
