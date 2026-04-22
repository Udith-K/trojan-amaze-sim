package org.bouncycastle.oer;

import org.bouncycastle.asn1.ASN1Encodable;

/* JADX INFO: loaded from: classes2.dex */
public interface Switch {
    ASN1Encodable[] keys();

    Element result(SwitchIndexer switchIndexer);
}
