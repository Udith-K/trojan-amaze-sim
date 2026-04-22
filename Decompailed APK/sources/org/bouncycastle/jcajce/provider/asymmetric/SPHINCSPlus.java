package org.bouncycastle.jcajce.provider.asymmetric;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.jcajce.provider.config.ConfigurableProvider;
import org.bouncycastle.jcajce.provider.util.AsymmetricAlgorithmProvider;
import org.bouncycastle.pqc.jcajce.provider.sphincsplus.SPHINCSPlusKeyFactorySpi;

/* JADX INFO: loaded from: classes2.dex */
public class SPHINCSPlus {
    private static final String PREFIX = "org.bouncycastle.pqc.jcajce.provider.sphincsplus.";

    public static class Mappings extends AsymmetricAlgorithmProvider {
        @Override // org.bouncycastle.jcajce.provider.util.AlgorithmProvider
        public void configure(ConfigurableProvider configurableProvider) {
            configurableProvider.addAlgorithm("KeyFactory.SPHINCSPLUS", "org.bouncycastle.pqc.jcajce.provider.sphincsplus.SPHINCSPlusKeyFactorySpi");
            configurableProvider.addAlgorithm("KeyPairGenerator.SPHINCSPLUS", "org.bouncycastle.pqc.jcajce.provider.sphincsplus.SPHINCSPlusKeyPairGeneratorSpi");
            configurableProvider.addAlgorithm("Alg.Alias.KeyFactory.SPHINCS+", "SPHINCSPLUS");
            configurableProvider.addAlgorithm("Alg.Alias.KeyPairGenerator.SPHINCS+", "SPHINCSPLUS");
            ASN1ObjectIdentifier aSN1ObjectIdentifier = BCObjectIdentifiers.sphincsPlus;
            addSignatureAlgorithm(configurableProvider, "SPHINCSPLUS", "org.bouncycastle.pqc.jcajce.provider.sphincsplus.SignatureSpi$Direct", aSN1ObjectIdentifier);
            StringBuilder sb = new StringBuilder();
            sb.append("Alg.Alias.Signature.");
            ASN1ObjectIdentifier aSN1ObjectIdentifier2 = BCObjectIdentifiers.sphincsPlus_shake_256;
            sb.append(aSN1ObjectIdentifier2.getId());
            configurableProvider.addAlgorithm(sb.toString(), "SPHINCSPLUS");
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Alg.Alias.Signature.");
            ASN1ObjectIdentifier aSN1ObjectIdentifier3 = BCObjectIdentifiers.sphincsPlus_sha_256;
            sb2.append(aSN1ObjectIdentifier3.getId());
            configurableProvider.addAlgorithm(sb2.toString(), "SPHINCSPLUS");
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Alg.Alias.Signature.");
            ASN1ObjectIdentifier aSN1ObjectIdentifier4 = BCObjectIdentifiers.sphincsPlus_sha_512;
            sb3.append(aSN1ObjectIdentifier4.getId());
            configurableProvider.addAlgorithm(sb3.toString(), "SPHINCSPLUS");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.OID." + aSN1ObjectIdentifier2.getId(), "SPHINCSPLUS");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.OID." + aSN1ObjectIdentifier3.getId(), "SPHINCSPLUS");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.OID." + aSN1ObjectIdentifier4.getId(), "SPHINCSPLUS");
            configurableProvider.addAlgorithm("Alg.Alias.Signature.SPHINCS+", "SPHINCSPLUS");
            SPHINCSPlusKeyFactorySpi sPHINCSPlusKeyFactorySpi = new SPHINCSPlusKeyFactorySpi();
            registerOid(configurableProvider, aSN1ObjectIdentifier, "SPHINCSPLUS", sPHINCSPlusKeyFactorySpi);
            registerOid(configurableProvider, aSN1ObjectIdentifier2, "SPHINCSPLUS", sPHINCSPlusKeyFactorySpi);
            registerOid(configurableProvider, aSN1ObjectIdentifier3, "SPHINCSPLUS", sPHINCSPlusKeyFactorySpi);
            registerOid(configurableProvider, aSN1ObjectIdentifier4, "SPHINCSPLUS", sPHINCSPlusKeyFactorySpi);
            registerOidAlgorithmParameters(configurableProvider, aSN1ObjectIdentifier, "SPHINCSPLUS");
        }
    }
}
