package org.bouncycastle.pqc.jcajce.provider.frodo;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.pqc.crypto.frodo.FrodoKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.frodo.FrodoKeyPairGenerator;
import org.bouncycastle.pqc.crypto.frodo.FrodoParameters;
import org.bouncycastle.pqc.crypto.frodo.FrodoPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.frodo.FrodoPublicKeyParameters;
import org.bouncycastle.pqc.jcajce.provider.util.SpecUtil;
import org.bouncycastle.pqc.jcajce.spec.FrodoParameterSpec;

/* JADX INFO: loaded from: classes2.dex */
public class FrodoKeyPairGeneratorSpi extends KeyPairGenerator {
    private static Map parameters;
    FrodoKeyPairGenerator engine;
    boolean initialised;
    FrodoKeyGenerationParameters param;
    SecureRandom random;

    static {
        HashMap map = new HashMap();
        parameters = map;
        map.put(FrodoParameterSpec.frodokem19888r3.getName(), FrodoParameters.frodokem19888r3);
        parameters.put(FrodoParameterSpec.frodokem19888shaker3.getName(), FrodoParameters.frodokem19888shaker3);
        parameters.put(FrodoParameterSpec.frodokem31296r3.getName(), FrodoParameters.frodokem31296r3);
        parameters.put(FrodoParameterSpec.frodokem31296shaker3.getName(), FrodoParameters.frodokem31296shaker3);
        parameters.put(FrodoParameterSpec.frodokem43088r3.getName(), FrodoParameters.frodokem43088r3);
        parameters.put(FrodoParameterSpec.frodokem43088shaker3.getName(), FrodoParameters.frodokem43088shaker3);
    }

    public FrodoKeyPairGeneratorSpi() {
        super("Frodo");
        this.engine = new FrodoKeyPairGenerator();
        this.random = CryptoServicesRegistrar.getSecureRandom();
        this.initialised = false;
    }

    private static String getNameFromParams(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidAlgorithmParameterException {
        return algorithmParameterSpec instanceof FrodoParameterSpec ? ((FrodoParameterSpec) algorithmParameterSpec).getName() : SpecUtil.getNameFrom(algorithmParameterSpec);
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public KeyPair generateKeyPair() {
        if (!this.initialised) {
            FrodoKeyGenerationParameters frodoKeyGenerationParameters = new FrodoKeyGenerationParameters(this.random, FrodoParameters.frodokem43088shaker3);
            this.param = frodoKeyGenerationParameters;
            this.engine.init(frodoKeyGenerationParameters);
            this.initialised = true;
        }
        AsymmetricCipherKeyPair asymmetricCipherKeyPairGenerateKeyPair = this.engine.generateKeyPair();
        return new KeyPair(new BCFrodoPublicKey((FrodoPublicKeyParameters) asymmetricCipherKeyPairGenerateKeyPair.getPublic()), new BCFrodoPrivateKey((FrodoPrivateKeyParameters) asymmetricCipherKeyPairGenerateKeyPair.getPrivate()));
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(int i, SecureRandom secureRandom) {
        throw new IllegalArgumentException("use AlgorithmParameterSpec");
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        if (!(algorithmParameterSpec instanceof FrodoParameterSpec)) {
            throw new InvalidAlgorithmParameterException("parameter object not a FrodoParameterSpec");
        }
        FrodoKeyGenerationParameters frodoKeyGenerationParameters = new FrodoKeyGenerationParameters(secureRandom, (FrodoParameters) parameters.get(getNameFromParams(algorithmParameterSpec)));
        this.param = frodoKeyGenerationParameters;
        this.engine.init(frodoKeyGenerationParameters);
        this.initialised = true;
    }
}
