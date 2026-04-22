package org.bouncycastle.cert.path.validations;

import org.bouncycastle.cert.X509CertificateHolder;

/* JADX INFO: loaded from: classes2.dex */
class ValidationUtils {
    ValidationUtils() {
    }

    static boolean isSelfIssued(X509CertificateHolder x509CertificateHolder) {
        return x509CertificateHolder.getSubject().equals(x509CertificateHolder.getIssuer());
    }
}
