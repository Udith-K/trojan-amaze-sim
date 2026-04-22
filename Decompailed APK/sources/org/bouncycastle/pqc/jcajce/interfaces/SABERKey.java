package org.bouncycastle.pqc.jcajce.interfaces;

import java.security.Key;
import org.bouncycastle.pqc.jcajce.spec.SABERParameterSpec;

/* JADX INFO: loaded from: classes2.dex */
public interface SABERKey extends Key {
    SABERParameterSpec getParameterSpec();
}
