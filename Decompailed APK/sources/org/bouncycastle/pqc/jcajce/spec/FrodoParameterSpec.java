package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.pqc.crypto.frodo.FrodoParameters;
import org.bouncycastle.util.Strings;

/* JADX INFO: loaded from: classes2.dex */
public class FrodoParameterSpec implements AlgorithmParameterSpec {
    public static final FrodoParameterSpec frodokem19888r3 = new FrodoParameterSpec(FrodoParameters.frodokem19888r3);
    public static final FrodoParameterSpec frodokem19888shaker3 = new FrodoParameterSpec(FrodoParameters.frodokem19888shaker3);
    public static final FrodoParameterSpec frodokem31296r3 = new FrodoParameterSpec(FrodoParameters.frodokem31296r3);
    public static final FrodoParameterSpec frodokem31296shaker3 = new FrodoParameterSpec(FrodoParameters.frodokem31296shaker3);
    public static final FrodoParameterSpec frodokem43088r3 = new FrodoParameterSpec(FrodoParameters.frodokem43088r3);
    public static final FrodoParameterSpec frodokem43088shaker3 = new FrodoParameterSpec(FrodoParameters.frodokem43088shaker3);
    private static Map parameters = new HashMap();
    private final String name;

    private FrodoParameterSpec(FrodoParameters frodoParameters) {
        this.name = frodoParameters.getName();
    }

    public static FrodoParameterSpec fromName(String str) {
        return (FrodoParameterSpec) parameters.get(Strings.toLowerCase(str));
    }

    public String getName() {
        return this.name;
    }
}
