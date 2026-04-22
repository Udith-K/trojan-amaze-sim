package org.bouncycastle.cms;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;

/* JADX INFO: loaded from: classes2.dex */
public interface CMSTypedData extends CMSProcessable {
    ASN1ObjectIdentifier getContentType();
}
