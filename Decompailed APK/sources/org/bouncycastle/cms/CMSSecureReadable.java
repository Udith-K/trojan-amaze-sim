package org.bouncycastle.cms;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;

/* JADX INFO: loaded from: classes2.dex */
interface CMSSecureReadable {
    ASN1ObjectIdentifier getContentType();

    InputStream getInputStream() throws CMSException, IOException;
}
