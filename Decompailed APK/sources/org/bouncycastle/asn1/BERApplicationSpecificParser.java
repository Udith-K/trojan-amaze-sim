package org.bouncycastle.asn1;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class BERApplicationSpecificParser extends BERTaggedObjectParser implements ASN1ApplicationSpecificParser {
    BERApplicationSpecificParser(int i, ASN1StreamParser aSN1StreamParser) {
        super(64, i, aSN1StreamParser);
    }

    @Override // org.bouncycastle.asn1.ASN1ApplicationSpecificParser
    public ASN1Encodable readObject() throws IOException {
        return parseExplicitBaseObject();
    }
}
