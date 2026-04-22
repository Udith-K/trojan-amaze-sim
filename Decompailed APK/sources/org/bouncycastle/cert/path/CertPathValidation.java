package org.bouncycastle.cert.path;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.util.Memoable;

/* JADX INFO: loaded from: classes2.dex */
public interface CertPathValidation extends Memoable {
    void validate(CertPathValidationContext certPathValidationContext, X509CertificateHolder x509CertificateHolder) throws CertPathValidationException;
}
