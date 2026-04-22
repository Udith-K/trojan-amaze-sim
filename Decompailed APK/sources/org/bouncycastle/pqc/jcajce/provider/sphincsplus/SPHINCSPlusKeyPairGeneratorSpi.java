package org.bouncycastle.pqc.jcajce.provider.sphincsplus;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusKeyPairGenerator;
import org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusParameters;
import org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusPublicKeyParameters;
import org.bouncycastle.pqc.jcajce.provider.util.SpecUtil;
import org.bouncycastle.pqc.jcajce.spec.SPHINCSPlusParameterSpec;

/* JADX INFO: loaded from: classes2.dex */
public class SPHINCSPlusKeyPairGeneratorSpi extends KeyPairGenerator {
    private static Map parameters;
    SPHINCSPlusKeyPairGenerator engine;
    boolean initialised;
    SPHINCSPlusKeyGenerationParameters param;
    SecureRandom random;

    static {
        HashMap map = new HashMap();
        parameters = map;
        map.put(SPHINCSPlusParameterSpec.sha256_128f.getName(), SPHINCSPlusParameters.sha256_128f);
        parameters.put(SPHINCSPlusParameterSpec.sha256_128s.getName(), SPHINCSPlusParameters.sha256_128s);
        parameters.put(SPHINCSPlusParameterSpec.sha256_192f.getName(), SPHINCSPlusParameters.sha256_192f);
        parameters.put(SPHINCSPlusParameterSpec.sha256_192s.getName(), SPHINCSPlusParameters.sha256_192s);
        parameters.put(SPHINCSPlusParameterSpec.sha256_256f.getName(), SPHINCSPlusParameters.sha256_256f);
        parameters.put(SPHINCSPlusParameterSpec.sha256_256s.getName(), SPHINCSPlusParameters.sha256_256s);
        parameters.put(SPHINCSPlusParameterSpec.sha256_128f_simple.getName(), SPHINCSPlusParameters.sha256_128f_simple);
        parameters.put(SPHINCSPlusParameterSpec.sha256_128s_simple.getName(), SPHINCSPlusParameters.sha256_128s_simple);
        parameters.put(SPHINCSPlusParameterSpec.sha256_192f_simple.getName(), SPHINCSPlusParameters.sha256_192f_simple);
        parameters.put(SPHINCSPlusParameterSpec.sha256_192s_simple.getName(), SPHINCSPlusParameters.sha256_192s_simple);
        parameters.put(SPHINCSPlusParameterSpec.sha256_256f_simple.getName(), SPHINCSPlusParameters.sha256_256f_simple);
        parameters.put(SPHINCSPlusParameterSpec.sha256_256s_simple.getName(), SPHINCSPlusParameters.sha256_256s_simple);
        parameters.put(SPHINCSPlusParameterSpec.shake256_128f.getName(), SPHINCSPlusParameters.shake256_128f);
        parameters.put(SPHINCSPlusParameterSpec.shake256_128s.getName(), SPHINCSPlusParameters.shake256_128s);
        parameters.put(SPHINCSPlusParameterSpec.shake256_192f.getName(), SPHINCSPlusParameters.shake256_192f);
        parameters.put(SPHINCSPlusParameterSpec.shake256_192s.getName(), SPHINCSPlusParameters.shake256_192s);
        parameters.put(SPHINCSPlusParameterSpec.shake256_256f.getName(), SPHINCSPlusParameters.shake256_256f);
        parameters.put(SPHINCSPlusParameterSpec.shake256_256s.getName(), SPHINCSPlusParameters.shake256_256s);
        parameters.put(SPHINCSPlusParameterSpec.shake256_128f_simple.getName(), SPHINCSPlusParameters.shake256_128f_simple);
        parameters.put(SPHINCSPlusParameterSpec.shake256_128s_simple.getName(), SPHINCSPlusParameters.shake256_128s_simple);
        parameters.put(SPHINCSPlusParameterSpec.shake256_192f_simple.getName(), SPHINCSPlusParameters.shake256_192f_simple);
        parameters.put(SPHINCSPlusParameterSpec.shake256_192s_simple.getName(), SPHINCSPlusParameters.shake256_192s_simple);
        parameters.put(SPHINCSPlusParameterSpec.shake256_256f_simple.getName(), SPHINCSPlusParameters.shake256_256f_simple);
        parameters.put(SPHINCSPlusParameterSpec.shake256_256s_simple.getName(), SPHINCSPlusParameters.shake256_256s_simple);
    }

    public SPHINCSPlusKeyPairGeneratorSpi() {
        super("SPHINCS+");
        this.engine = new SPHINCSPlusKeyPairGenerator();
        this.random = CryptoServicesRegistrar.getSecureRandom();
        this.initialised = false;
    }

    private static String getNameFromParams(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidAlgorithmParameterException {
        return algorithmParameterSpec instanceof SPHINCSPlusParameterSpec ? ((SPHINCSPlusParameterSpec) algorithmParameterSpec).getName() : SpecUtil.getNameFrom(algorithmParameterSpec);
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public KeyPair generateKeyPair() {
        if (!this.initialised) {
            SPHINCSPlusKeyGenerationParameters sPHINCSPlusKeyGenerationParameters = new SPHINCSPlusKeyGenerationParameters(this.random, SPHINCSPlusParameters.sha256_256s);
            this.param = sPHINCSPlusKeyGenerationParameters;
            this.engine.init(sPHINCSPlusKeyGenerationParameters);
            this.initialised = true;
        }
        AsymmetricCipherKeyPair asymmetricCipherKeyPairGenerateKeyPair = this.engine.generateKeyPair();
        return new KeyPair(new BCSPHINCSPlusPublicKey((SPHINCSPlusPublicKeyParameters) asymmetricCipherKeyPairGenerateKeyPair.getPublic()), new BCSPHINCSPlusPrivateKey((SPHINCSPlusPrivateKeyParameters) asymmetricCipherKeyPairGenerateKeyPair.getPrivate()));
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(int i, SecureRandom secureRandom) {
        throw new IllegalArgumentException("use AlgorithmParameterSpec");
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        if (!(algorithmParameterSpec instanceof SPHINCSPlusParameterSpec)) {
            throw new InvalidAlgorithmParameterException("parameter object not a CMCEParameterSpec");
        }
        SPHINCSPlusKeyGenerationParameters sPHINCSPlusKeyGenerationParameters = new SPHINCSPlusKeyGenerationParameters(secureRandom, (SPHINCSPlusParameters) parameters.get(getNameFromParams(algorithmParameterSpec)));
        this.param = sPHINCSPlusKeyGenerationParameters;
        this.engine.init(sPHINCSPlusKeyGenerationParameters);
        this.initialised = true;
    }
}
