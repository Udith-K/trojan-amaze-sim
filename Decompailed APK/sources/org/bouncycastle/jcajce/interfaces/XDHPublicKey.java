package org.bouncycastle.jcajce.interfaces;

import java.math.BigInteger;
import java.security.PublicKey;

/* JADX INFO: loaded from: classes2.dex */
public interface XDHPublicKey extends XDHKey, PublicKey {
    BigInteger getU();

    byte[] getUEncoding();
}
