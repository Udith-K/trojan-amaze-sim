package org.bouncycastle.eac.jcajce;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/* JADX INFO: loaded from: classes2.dex */
interface EACHelper {
    KeyFactory createKeyFactory(String str) throws NoSuchAlgorithmException, NoSuchProviderException;
}
