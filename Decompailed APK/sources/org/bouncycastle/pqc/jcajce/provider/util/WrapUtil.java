package org.bouncycastle.pqc.jcajce.provider.util;

import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.engines.ARIAEngine;
import org.bouncycastle.crypto.engines.CamelliaEngine;
import org.bouncycastle.crypto.engines.RFC3394WrapEngine;
import org.bouncycastle.crypto.engines.RFC5649WrapEngine;
import org.bouncycastle.crypto.engines.SEEDEngine;

/* JADX INFO: loaded from: classes2.dex */
public class WrapUtil {
    public static Wrapper getWrapper(String str) {
        if (str.equalsIgnoreCase("AES")) {
            return new RFC3394WrapEngine(new AESEngine());
        }
        if (str.equalsIgnoreCase("ARIA")) {
            return new RFC3394WrapEngine(new ARIAEngine());
        }
        if (str.equalsIgnoreCase("Camellia")) {
            return new RFC3394WrapEngine(new CamelliaEngine());
        }
        if (str.equalsIgnoreCase("SEED")) {
            return new RFC3394WrapEngine(new SEEDEngine());
        }
        if (str.equalsIgnoreCase("AES-KWP")) {
            return new RFC5649WrapEngine(new AESEngine());
        }
        if (str.equalsIgnoreCase("Camellia-KWP")) {
            return new RFC5649WrapEngine(new CamelliaEngine());
        }
        if (str.equalsIgnoreCase("ARIA-KWP")) {
            return new RFC5649WrapEngine(new ARIAEngine());
        }
        throw new UnsupportedOperationException("unknown key algorithm: " + str);
    }
}
