package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.util.Strings;

/* JADX INFO: loaded from: classes2.dex */
public class SPHINCSPlusParameterSpec implements AlgorithmParameterSpec {
    private static Map parameters;
    public static final SPHINCSPlusParameterSpec sha256_128f;
    public static final SPHINCSPlusParameterSpec sha256_128f_simple;
    public static final SPHINCSPlusParameterSpec sha256_128s;
    public static final SPHINCSPlusParameterSpec sha256_128s_simple;
    public static final SPHINCSPlusParameterSpec sha256_192f;
    public static final SPHINCSPlusParameterSpec sha256_192f_simple;
    public static final SPHINCSPlusParameterSpec sha256_192s;
    public static final SPHINCSPlusParameterSpec sha256_192s_simple;
    public static final SPHINCSPlusParameterSpec sha256_256f;
    public static final SPHINCSPlusParameterSpec sha256_256f_simple;
    public static final SPHINCSPlusParameterSpec sha256_256s;
    public static final SPHINCSPlusParameterSpec sha256_256s_simple;
    public static final SPHINCSPlusParameterSpec shake256_128f;
    public static final SPHINCSPlusParameterSpec shake256_128f_simple;
    public static final SPHINCSPlusParameterSpec shake256_128s;
    public static final SPHINCSPlusParameterSpec shake256_128s_simple;
    public static final SPHINCSPlusParameterSpec shake256_192f;
    public static final SPHINCSPlusParameterSpec shake256_192f_simple;
    public static final SPHINCSPlusParameterSpec shake256_192s;
    public static final SPHINCSPlusParameterSpec shake256_192s_simple;
    public static final SPHINCSPlusParameterSpec shake256_256f;
    public static final SPHINCSPlusParameterSpec shake256_256f_simple;
    public static final SPHINCSPlusParameterSpec shake256_256s;
    public static final SPHINCSPlusParameterSpec shake256_256s_simple;
    private final String name;

    static {
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec = new SPHINCSPlusParameterSpec("sha256-128f-robust");
        sha256_128f = sPHINCSPlusParameterSpec;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec2 = new SPHINCSPlusParameterSpec("sha256-128s-robust");
        sha256_128s = sPHINCSPlusParameterSpec2;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec3 = new SPHINCSPlusParameterSpec("sha256-192f-robust");
        sha256_192f = sPHINCSPlusParameterSpec3;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec4 = new SPHINCSPlusParameterSpec("sha256-192s-robust");
        sha256_192s = sPHINCSPlusParameterSpec4;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec5 = new SPHINCSPlusParameterSpec("sha256-256f-robust");
        sha256_256f = sPHINCSPlusParameterSpec5;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec6 = new SPHINCSPlusParameterSpec("sha256-256s-robust");
        sha256_256s = sPHINCSPlusParameterSpec6;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec7 = new SPHINCSPlusParameterSpec("sha256-128s-simple");
        sha256_128f_simple = sPHINCSPlusParameterSpec7;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec8 = new SPHINCSPlusParameterSpec("sha256-128f-simple");
        sha256_128s_simple = sPHINCSPlusParameterSpec8;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec9 = new SPHINCSPlusParameterSpec("sha256-192f-simple");
        sha256_192f_simple = sPHINCSPlusParameterSpec9;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec10 = new SPHINCSPlusParameterSpec("sha256-192s-simple");
        sha256_192s_simple = sPHINCSPlusParameterSpec10;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec11 = new SPHINCSPlusParameterSpec("sha256-256f-simple");
        sha256_256f_simple = sPHINCSPlusParameterSpec11;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec12 = new SPHINCSPlusParameterSpec("sha256-256s-simple");
        sha256_256s_simple = sPHINCSPlusParameterSpec12;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec13 = new SPHINCSPlusParameterSpec("shake256-128f-robust");
        shake256_128f = sPHINCSPlusParameterSpec13;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec14 = new SPHINCSPlusParameterSpec("shake256-128s-robust");
        shake256_128s = sPHINCSPlusParameterSpec14;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec15 = new SPHINCSPlusParameterSpec("shake256-192f-robust");
        shake256_192f = sPHINCSPlusParameterSpec15;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec16 = new SPHINCSPlusParameterSpec("shake256-192s-robust");
        shake256_192s = sPHINCSPlusParameterSpec16;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec17 = new SPHINCSPlusParameterSpec("shake256-256f-robust");
        shake256_256f = sPHINCSPlusParameterSpec17;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec18 = new SPHINCSPlusParameterSpec("shake256-256s-robust");
        shake256_256s = sPHINCSPlusParameterSpec18;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec19 = new SPHINCSPlusParameterSpec("shake256-128f-simple");
        shake256_128f_simple = sPHINCSPlusParameterSpec19;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec20 = new SPHINCSPlusParameterSpec("shake256-128s-simple");
        shake256_128s_simple = sPHINCSPlusParameterSpec20;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec21 = new SPHINCSPlusParameterSpec("shake256-192f-simple");
        shake256_192f_simple = sPHINCSPlusParameterSpec21;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec22 = new SPHINCSPlusParameterSpec("shake256-192s-simple");
        shake256_192s_simple = sPHINCSPlusParameterSpec22;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec23 = new SPHINCSPlusParameterSpec("shake256-256f-simple");
        shake256_256f_simple = sPHINCSPlusParameterSpec23;
        SPHINCSPlusParameterSpec sPHINCSPlusParameterSpec24 = new SPHINCSPlusParameterSpec("shake256-256s-simple");
        shake256_256s_simple = sPHINCSPlusParameterSpec24;
        HashMap map = new HashMap();
        parameters = map;
        map.put(sPHINCSPlusParameterSpec.getName(), sPHINCSPlusParameterSpec);
        parameters.put(sPHINCSPlusParameterSpec2.getName(), sPHINCSPlusParameterSpec2);
        parameters.put(sPHINCSPlusParameterSpec3.getName(), sPHINCSPlusParameterSpec3);
        parameters.put(sPHINCSPlusParameterSpec4.getName(), sPHINCSPlusParameterSpec4);
        parameters.put(sPHINCSPlusParameterSpec5.getName(), sPHINCSPlusParameterSpec5);
        parameters.put(sPHINCSPlusParameterSpec6.getName(), sPHINCSPlusParameterSpec6);
        parameters.put(sPHINCSPlusParameterSpec7.getName(), sPHINCSPlusParameterSpec7);
        parameters.put(sPHINCSPlusParameterSpec8.getName(), sPHINCSPlusParameterSpec8);
        parameters.put(sPHINCSPlusParameterSpec9.getName(), sPHINCSPlusParameterSpec9);
        parameters.put(sPHINCSPlusParameterSpec10.getName(), sPHINCSPlusParameterSpec10);
        parameters.put(sPHINCSPlusParameterSpec11.getName(), sPHINCSPlusParameterSpec11);
        parameters.put(sPHINCSPlusParameterSpec12.getName(), sPHINCSPlusParameterSpec12);
        parameters.put(sPHINCSPlusParameterSpec13.getName(), sPHINCSPlusParameterSpec13);
        parameters.put(sPHINCSPlusParameterSpec14.getName(), sPHINCSPlusParameterSpec14);
        parameters.put(sPHINCSPlusParameterSpec15.getName(), sPHINCSPlusParameterSpec15);
        parameters.put(sPHINCSPlusParameterSpec16.getName(), sPHINCSPlusParameterSpec16);
        parameters.put(sPHINCSPlusParameterSpec17.getName(), sPHINCSPlusParameterSpec17);
        parameters.put(sPHINCSPlusParameterSpec18.getName(), sPHINCSPlusParameterSpec18);
        parameters.put(sPHINCSPlusParameterSpec19.getName(), sPHINCSPlusParameterSpec19);
        parameters.put(sPHINCSPlusParameterSpec20.getName(), sPHINCSPlusParameterSpec20);
        parameters.put(sPHINCSPlusParameterSpec21.getName(), sPHINCSPlusParameterSpec21);
        parameters.put(sPHINCSPlusParameterSpec22.getName(), sPHINCSPlusParameterSpec22);
        parameters.put(sPHINCSPlusParameterSpec23.getName(), sPHINCSPlusParameterSpec23);
        parameters.put(sPHINCSPlusParameterSpec24.getName(), sPHINCSPlusParameterSpec24);
    }

    private SPHINCSPlusParameterSpec(String str) {
        this.name = str;
    }

    public static SPHINCSPlusParameterSpec fromName(String str) {
        return (SPHINCSPlusParameterSpec) parameters.get(Strings.toLowerCase(str));
    }

    public String getName() {
        return this.name;
    }
}
