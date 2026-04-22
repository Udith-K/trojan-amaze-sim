package org.bouncycastle.pqc.crypto.saber;

import org.bouncycastle.crypto.CipherParameters;

/* JADX INFO: loaded from: classes2.dex */
public class SABERParameters implements CipherParameters {
    private final int defaultKeySize;
    private final SABEREngine engine;
    private final int l;
    private final String name;
    public static final SABERParameters lightsaberkem128r3 = new SABERParameters("lightsaberkem128r3", 2, 128);
    public static final SABERParameters saberkem128r3 = new SABERParameters("saberkem128r3", 3, 128);
    public static final SABERParameters firesaberkem128r3 = new SABERParameters("firesaberkem128r3", 4, 128);
    public static final SABERParameters lightsaberkem192r3 = new SABERParameters("lightsaberkem192r3", 2, 192);
    public static final SABERParameters saberkem192r3 = new SABERParameters("saberkem192r3", 3, 192);
    public static final SABERParameters firesaberkem192r3 = new SABERParameters("firesaberkem192r3", 4, 192);
    public static final SABERParameters lightsaberkem256r3 = new SABERParameters("lightsaberkem256r3", 2, 256);
    public static final SABERParameters saberkem256r3 = new SABERParameters("saberkem256r3", 3, 256);
    public static final SABERParameters firesaberkem256r3 = new SABERParameters("firesaberkem256r3", 4, 256);

    public SABERParameters(String str, int i, int i2) {
        this.name = str;
        this.l = i;
        this.defaultKeySize = i2;
        this.engine = new SABEREngine(i, i2);
    }

    public int getDefaultKeySize() {
        return this.defaultKeySize;
    }

    public SABEREngine getEngine() {
        return this.engine;
    }

    public int getL() {
        return this.l;
    }

    public String getName() {
        return this.name;
    }
}
