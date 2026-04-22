package org.bouncycastle.pqc.crypto;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

/* JADX INFO: loaded from: classes2.dex */
public interface ExchangePairGenerator {
    ExchangePair generateExchange(AsymmetricKeyParameter asymmetricKeyParameter);
}
