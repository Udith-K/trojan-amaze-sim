package org.bouncycastle.tsp.ers;

import org.bouncycastle.operator.DigestCalculator;

/* JADX INFO: loaded from: classes2.dex */
public interface ERSData {
    byte[] getHash(DigestCalculator digestCalculator);
}
