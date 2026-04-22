package org.bouncycastle.jcajce.interfaces;

import java.security.PublicKey;

/* JADX INFO: loaded from: classes2.dex */
public interface EdDSAPublicKey extends EdDSAKey, PublicKey {
    byte[] getPointEncoding();
}
