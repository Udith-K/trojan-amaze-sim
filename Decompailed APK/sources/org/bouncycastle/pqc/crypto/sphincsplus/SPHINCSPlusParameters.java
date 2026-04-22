package org.bouncycastle.pqc.crypto.sphincsplus;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusEngine;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;

/* JADX INFO: loaded from: classes2.dex */
public class SPHINCSPlusParameters {
    private static final Map oidToParams;
    private static final Map paramsToOid;
    public static final SPHINCSPlusParameters sha256_128f;
    public static final SPHINCSPlusParameters sha256_128f_simple;
    public static final SPHINCSPlusParameters sha256_128s;
    public static final SPHINCSPlusParameters sha256_128s_simple;
    public static final SPHINCSPlusParameters sha256_192f;
    public static final SPHINCSPlusParameters sha256_192f_simple;
    public static final SPHINCSPlusParameters sha256_192s;
    public static final SPHINCSPlusParameters sha256_192s_simple;
    public static final SPHINCSPlusParameters sha256_256f;
    public static final SPHINCSPlusParameters sha256_256f_simple;
    public static final SPHINCSPlusParameters sha256_256s;
    public static final SPHINCSPlusParameters sha256_256s_simple;
    public static final SPHINCSPlusParameters shake256_128f;
    public static final SPHINCSPlusParameters shake256_128f_simple;
    public static final SPHINCSPlusParameters shake256_128s;
    public static final SPHINCSPlusParameters shake256_128s_simple;
    public static final SPHINCSPlusParameters shake256_192f;
    public static final SPHINCSPlusParameters shake256_192f_simple;
    public static final SPHINCSPlusParameters shake256_192s;
    public static final SPHINCSPlusParameters shake256_192s_simple;
    public static final SPHINCSPlusParameters shake256_256f;
    public static final SPHINCSPlusParameters shake256_256f_simple;
    public static final SPHINCSPlusParameters shake256_256s;
    public static final SPHINCSPlusParameters shake256_256s_simple;
    private static final Integer sphincsPlus_sha256_128f_robust;
    private static final Integer sphincsPlus_sha256_128f_simple;
    private static final Integer sphincsPlus_sha256_128s_robust;
    private static final Integer sphincsPlus_sha256_128s_simple;
    private static final Integer sphincsPlus_sha256_192f_robust;
    private static final Integer sphincsPlus_sha256_192f_simple;
    private static final Integer sphincsPlus_sha256_192s_robust;
    private static final Integer sphincsPlus_sha256_192s_simple;
    private static final Integer sphincsPlus_sha256_256f_robust;
    private static final Integer sphincsPlus_sha256_256f_simple;
    private static final Integer sphincsPlus_sha256_256s_robust;
    private static final Integer sphincsPlus_sha256_256s_simple;
    private static final Integer sphincsPlus_shake256_128f_robust;
    private static final Integer sphincsPlus_shake256_128f_simple;
    private static final Integer sphincsPlus_shake256_128s_robust;
    private static final Integer sphincsPlus_shake256_128s_simple;
    private static final Integer sphincsPlus_shake256_192f_robust;
    private static final Integer sphincsPlus_shake256_192f_simple;
    private static final Integer sphincsPlus_shake256_192s_robust;
    private static final Integer sphincsPlus_shake256_192s_simple;
    private static final Integer sphincsPlus_shake256_256f_robust;
    private static final Integer sphincsPlus_shake256_256f_simple;
    private static final Integer sphincsPlus_shake256_256s_robust;
    private static final Integer sphincsPlus_shake256_256s_simple;
    private final SPHINCSPlusEngine engine;
    private final String name;

    static {
        SPHINCSPlusParameters sPHINCSPlusParameters = new SPHINCSPlusParameters("sha256-128f-robust", new SPHINCSPlusEngine.Sha256Engine(true, 16, 16, 22, 6, 33, 66));
        sha256_128f = sPHINCSPlusParameters;
        SPHINCSPlusParameters sPHINCSPlusParameters2 = new SPHINCSPlusParameters("sha256-128s-robust", new SPHINCSPlusEngine.Sha256Engine(true, 16, 16, 7, 12, 14, 63));
        sha256_128s = sPHINCSPlusParameters2;
        SPHINCSPlusParameters sPHINCSPlusParameters3 = new SPHINCSPlusParameters("sha256-192f-robust", new SPHINCSPlusEngine.Sha256Engine(true, 24, 16, 22, 8, 33, 66));
        sha256_192f = sPHINCSPlusParameters3;
        SPHINCSPlusParameters sPHINCSPlusParameters4 = new SPHINCSPlusParameters("sha256-192s-robust", new SPHINCSPlusEngine.Sha256Engine(true, 24, 16, 7, 14, 17, 63));
        sha256_192s = sPHINCSPlusParameters4;
        SPHINCSPlusParameters sPHINCSPlusParameters5 = new SPHINCSPlusParameters("sha256-256f-robust", new SPHINCSPlusEngine.Sha256Engine(true, 32, 16, 17, 9, 35, 68));
        sha256_256f = sPHINCSPlusParameters5;
        SPHINCSPlusParameters sPHINCSPlusParameters6 = new SPHINCSPlusParameters("sha256-256s-robust", new SPHINCSPlusEngine.Sha256Engine(true, 32, 16, 8, 14, 22, 64));
        sha256_256s = sPHINCSPlusParameters6;
        SPHINCSPlusParameters sPHINCSPlusParameters7 = new SPHINCSPlusParameters("sha256-128f-simple", new SPHINCSPlusEngine.Sha256Engine(false, 16, 16, 22, 6, 33, 66));
        sha256_128f_simple = sPHINCSPlusParameters7;
        SPHINCSPlusParameters sPHINCSPlusParameters8 = new SPHINCSPlusParameters("sha256-128s-simple", new SPHINCSPlusEngine.Sha256Engine(false, 16, 16, 7, 12, 14, 63));
        sha256_128s_simple = sPHINCSPlusParameters8;
        SPHINCSPlusParameters sPHINCSPlusParameters9 = new SPHINCSPlusParameters("sha256-192f-simple", new SPHINCSPlusEngine.Sha256Engine(false, 24, 16, 22, 8, 33, 66));
        sha256_192f_simple = sPHINCSPlusParameters9;
        SPHINCSPlusParameters sPHINCSPlusParameters10 = new SPHINCSPlusParameters("sha256-192s-simple", new SPHINCSPlusEngine.Sha256Engine(false, 24, 16, 7, 14, 17, 63));
        sha256_192s_simple = sPHINCSPlusParameters10;
        SPHINCSPlusParameters sPHINCSPlusParameters11 = new SPHINCSPlusParameters("sha256-256f-simple", new SPHINCSPlusEngine.Sha256Engine(false, 32, 16, 17, 9, 35, 68));
        sha256_256f_simple = sPHINCSPlusParameters11;
        SPHINCSPlusParameters sPHINCSPlusParameters12 = new SPHINCSPlusParameters("sha256-256s-simple", new SPHINCSPlusEngine.Sha256Engine(false, 32, 16, 8, 14, 22, 64));
        sha256_256s_simple = sPHINCSPlusParameters12;
        SPHINCSPlusParameters sPHINCSPlusParameters13 = new SPHINCSPlusParameters("shake256-128f-robust", new SPHINCSPlusEngine.Shake256Engine(true, 16, 16, 22, 6, 33, 66));
        shake256_128f = sPHINCSPlusParameters13;
        SPHINCSPlusParameters sPHINCSPlusParameters14 = new SPHINCSPlusParameters("shake256-128s-robust", new SPHINCSPlusEngine.Shake256Engine(true, 16, 16, 7, 12, 14, 63));
        shake256_128s = sPHINCSPlusParameters14;
        SPHINCSPlusParameters sPHINCSPlusParameters15 = new SPHINCSPlusParameters("shake256-192f-robust", new SPHINCSPlusEngine.Shake256Engine(true, 24, 16, 22, 8, 33, 66));
        shake256_192f = sPHINCSPlusParameters15;
        SPHINCSPlusParameters sPHINCSPlusParameters16 = new SPHINCSPlusParameters("shake256-192s-robust", new SPHINCSPlusEngine.Shake256Engine(true, 24, 16, 7, 14, 17, 63));
        shake256_192s = sPHINCSPlusParameters16;
        SPHINCSPlusParameters sPHINCSPlusParameters17 = new SPHINCSPlusParameters("shake256-256f-robust", new SPHINCSPlusEngine.Shake256Engine(true, 32, 16, 17, 9, 35, 68));
        shake256_256f = sPHINCSPlusParameters17;
        SPHINCSPlusParameters sPHINCSPlusParameters18 = new SPHINCSPlusParameters("shake256-256s-robust", new SPHINCSPlusEngine.Shake256Engine(true, 32, 16, 8, 14, 22, 64));
        shake256_256s = sPHINCSPlusParameters18;
        SPHINCSPlusParameters sPHINCSPlusParameters19 = new SPHINCSPlusParameters("shake256-128f-simple", new SPHINCSPlusEngine.Shake256Engine(false, 16, 16, 22, 6, 33, 66));
        shake256_128f_simple = sPHINCSPlusParameters19;
        SPHINCSPlusParameters sPHINCSPlusParameters20 = new SPHINCSPlusParameters("shake256-128s-simple", new SPHINCSPlusEngine.Shake256Engine(false, 16, 16, 7, 12, 14, 63));
        shake256_128s_simple = sPHINCSPlusParameters20;
        SPHINCSPlusParameters sPHINCSPlusParameters21 = new SPHINCSPlusParameters("shake256-192f-simple", new SPHINCSPlusEngine.Shake256Engine(false, 24, 16, 22, 8, 33, 66));
        shake256_192f_simple = sPHINCSPlusParameters21;
        SPHINCSPlusParameters sPHINCSPlusParameters22 = new SPHINCSPlusParameters("shake256-192s-simple", new SPHINCSPlusEngine.Shake256Engine(false, 24, 16, 7, 14, 17, 63));
        shake256_192s_simple = sPHINCSPlusParameters22;
        SPHINCSPlusParameters sPHINCSPlusParameters23 = new SPHINCSPlusParameters("shake256-256f-simple", new SPHINCSPlusEngine.Shake256Engine(false, 32, 16, 17, 9, 35, 68));
        shake256_256f_simple = sPHINCSPlusParameters23;
        SPHINCSPlusParameters sPHINCSPlusParameters24 = new SPHINCSPlusParameters("shake256-256s-simple", new SPHINCSPlusEngine.Shake256Engine(false, 32, 16, 8, 14, 22, 64));
        shake256_256s_simple = sPHINCSPlusParameters24;
        Integer numValueOf = Integers.valueOf(65793);
        sphincsPlus_sha256_128f_robust = numValueOf;
        Integer numValueOf2 = Integers.valueOf(65794);
        sphincsPlus_sha256_128s_robust = numValueOf2;
        Integer numValueOf3 = Integers.valueOf(65795);
        sphincsPlus_sha256_192f_robust = numValueOf3;
        Integer numValueOf4 = Integers.valueOf(65796);
        sphincsPlus_sha256_192s_robust = numValueOf4;
        Integer numValueOf5 = Integers.valueOf(65797);
        sphincsPlus_sha256_256f_robust = numValueOf5;
        Integer numValueOf6 = Integers.valueOf(65798);
        sphincsPlus_sha256_256s_robust = numValueOf6;
        Integer numValueOf7 = Integers.valueOf(66049);
        sphincsPlus_sha256_128f_simple = numValueOf7;
        Integer numValueOf8 = Integers.valueOf(66050);
        sphincsPlus_sha256_128s_simple = numValueOf8;
        Integer numValueOf9 = Integers.valueOf(66051);
        sphincsPlus_sha256_192f_simple = numValueOf9;
        Integer numValueOf10 = Integers.valueOf(66052);
        sphincsPlus_sha256_192s_simple = numValueOf10;
        Integer numValueOf11 = Integers.valueOf(66053);
        sphincsPlus_sha256_256f_simple = numValueOf11;
        Integer numValueOf12 = Integers.valueOf(66054);
        sphincsPlus_sha256_256s_simple = numValueOf12;
        Integer numValueOf13 = Integers.valueOf(131329);
        sphincsPlus_shake256_128f_robust = numValueOf13;
        Integer numValueOf14 = Integers.valueOf(131330);
        sphincsPlus_shake256_128s_robust = numValueOf14;
        Integer numValueOf15 = Integers.valueOf(131331);
        sphincsPlus_shake256_192f_robust = numValueOf15;
        Integer numValueOf16 = Integers.valueOf(131332);
        sphincsPlus_shake256_192s_robust = numValueOf16;
        Integer numValueOf17 = Integers.valueOf(131333);
        sphincsPlus_shake256_256f_robust = numValueOf17;
        Integer numValueOf18 = Integers.valueOf(131334);
        sphincsPlus_shake256_256s_robust = numValueOf18;
        Integer numValueOf19 = Integers.valueOf(131585);
        sphincsPlus_shake256_128f_simple = numValueOf19;
        Integer numValueOf20 = Integers.valueOf(131586);
        sphincsPlus_shake256_128s_simple = numValueOf20;
        Integer numValueOf21 = Integers.valueOf(131587);
        sphincsPlus_shake256_192f_simple = numValueOf21;
        Integer numValueOf22 = Integers.valueOf(131588);
        sphincsPlus_shake256_192s_simple = numValueOf22;
        Integer numValueOf23 = Integers.valueOf(131589);
        sphincsPlus_shake256_256f_simple = numValueOf23;
        Integer numValueOf24 = Integers.valueOf(131590);
        sphincsPlus_shake256_256s_simple = numValueOf24;
        HashMap map = new HashMap();
        oidToParams = map;
        HashMap map2 = new HashMap();
        paramsToOid = map2;
        map.put(numValueOf, sPHINCSPlusParameters);
        map.put(numValueOf2, sPHINCSPlusParameters2);
        map.put(numValueOf3, sPHINCSPlusParameters3);
        map.put(numValueOf4, sPHINCSPlusParameters4);
        map.put(numValueOf5, sPHINCSPlusParameters5);
        map.put(numValueOf6, sPHINCSPlusParameters6);
        map.put(numValueOf7, sPHINCSPlusParameters7);
        map.put(numValueOf8, sPHINCSPlusParameters8);
        map.put(numValueOf9, sPHINCSPlusParameters9);
        map.put(numValueOf10, sPHINCSPlusParameters10);
        map.put(numValueOf11, sPHINCSPlusParameters11);
        map.put(numValueOf12, sPHINCSPlusParameters12);
        map.put(numValueOf13, sPHINCSPlusParameters13);
        map.put(numValueOf14, sPHINCSPlusParameters14);
        map.put(numValueOf15, sPHINCSPlusParameters15);
        map.put(numValueOf16, sPHINCSPlusParameters16);
        map.put(numValueOf17, sPHINCSPlusParameters17);
        map.put(numValueOf18, sPHINCSPlusParameters18);
        map.put(numValueOf19, sPHINCSPlusParameters19);
        map.put(numValueOf20, sPHINCSPlusParameters20);
        map.put(numValueOf21, sPHINCSPlusParameters21);
        map.put(numValueOf22, sPHINCSPlusParameters22);
        map.put(numValueOf23, sPHINCSPlusParameters23);
        map.put(numValueOf24, sPHINCSPlusParameters24);
        map2.put(sPHINCSPlusParameters, numValueOf);
        map2.put(sPHINCSPlusParameters2, numValueOf2);
        map2.put(sPHINCSPlusParameters3, numValueOf3);
        map2.put(sPHINCSPlusParameters4, numValueOf4);
        map2.put(sPHINCSPlusParameters5, numValueOf5);
        map2.put(sPHINCSPlusParameters6, numValueOf6);
        map2.put(sPHINCSPlusParameters7, numValueOf7);
        map2.put(sPHINCSPlusParameters8, numValueOf8);
        map2.put(sPHINCSPlusParameters9, numValueOf9);
        map2.put(sPHINCSPlusParameters10, numValueOf10);
        map2.put(sPHINCSPlusParameters11, numValueOf11);
        map2.put(sPHINCSPlusParameters12, numValueOf12);
        map2.put(sPHINCSPlusParameters13, numValueOf13);
        map2.put(sPHINCSPlusParameters14, numValueOf14);
        map2.put(sPHINCSPlusParameters15, numValueOf15);
        map2.put(sPHINCSPlusParameters16, numValueOf16);
        map2.put(sPHINCSPlusParameters17, numValueOf17);
        map2.put(sPHINCSPlusParameters18, numValueOf18);
        map2.put(sPHINCSPlusParameters19, numValueOf19);
        map2.put(sPHINCSPlusParameters20, numValueOf20);
        map2.put(sPHINCSPlusParameters21, numValueOf21);
        map2.put(sPHINCSPlusParameters22, numValueOf22);
        map2.put(sPHINCSPlusParameters23, numValueOf23);
        map2.put(sPHINCSPlusParameters24, numValueOf24);
    }

    private SPHINCSPlusParameters(String str, SPHINCSPlusEngine sPHINCSPlusEngine) {
        this.name = str;
        this.engine = sPHINCSPlusEngine;
    }

    public static Integer getID(SPHINCSPlusParameters sPHINCSPlusParameters) {
        return (Integer) paramsToOid.get(sPHINCSPlusParameters);
    }

    public static SPHINCSPlusParameters getParams(Integer num) {
        return (SPHINCSPlusParameters) oidToParams.get(num);
    }

    public byte[] getEncoded() {
        return Pack.intToBigEndian(getID(this).intValue());
    }

    SPHINCSPlusEngine getEngine() {
        return this.engine;
    }

    public String getName() {
        return this.name;
    }
}
