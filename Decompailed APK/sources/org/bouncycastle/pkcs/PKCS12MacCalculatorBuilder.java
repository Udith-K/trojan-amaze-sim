package org.bouncycastle.pkcs;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.operator.MacCalculator;
import org.bouncycastle.operator.OperatorCreationException;

/* JADX INFO: loaded from: classes2.dex */
public interface PKCS12MacCalculatorBuilder {
    MacCalculator build(char[] cArr) throws OperatorCreationException;

    AlgorithmIdentifier getDigestAlgorithmIdentifier();
}
