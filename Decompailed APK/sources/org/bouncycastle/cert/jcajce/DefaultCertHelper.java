package org.bouncycastle.cert.jcajce;

import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

/* JADX INFO: loaded from: classes2.dex */
class DefaultCertHelper extends CertHelper {
    DefaultCertHelper() {
    }

    @Override // org.bouncycastle.cert.jcajce.CertHelper
    protected CertificateFactory createCertificateFactory(String str) throws CertificateException {
        return CertificateFactory.getInstance(str);
    }
}
