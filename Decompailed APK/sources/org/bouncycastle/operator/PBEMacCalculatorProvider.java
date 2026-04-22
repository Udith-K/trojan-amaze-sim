package org.bouncycastle.operator;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

/* JADX INFO: loaded from: classes2.dex */
public interface PBEMacCalculatorProvider {
    MacCalculator get(AlgorithmIdentifier algorithmIdentifier, char[] cArr) throws OperatorCreationException;
}
