package org.bouncycastle.cms;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;

/* JADX INFO: loaded from: classes2.dex */
public class PKCS7TypedStream extends CMSTypedStream {
    private final ASN1Encodable content;

    public PKCS7TypedStream(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) throws IOException {
        super(aSN1ObjectIdentifier);
        this.content = aSN1Encodable;
    }

    private InputStream getContentStream(ASN1Encodable aSN1Encodable) throws IOException {
        int i;
        byte[] encoded = aSN1Encodable.toASN1Primitive().getEncoded(ASN1Encoding.DER);
        int i2 = 1;
        if ((encoded[0] & 31) == 31) {
            do {
                i = encoded[i2] & 128;
                i2++;
            } while (i != 0);
        }
        int i3 = i2 + 1;
        byte b = encoded[i2];
        if ((b & 128) != 0) {
            i3 += b & 127;
        }
        return new ByteArrayInputStream(encoded, i3, encoded.length - i3);
    }

    @Override // org.bouncycastle.cms.CMSTypedStream
    public void drain() throws IOException {
        this.content.toASN1Primitive();
    }

    public ASN1Encodable getContent() {
        return this.content;
    }

    @Override // org.bouncycastle.cms.CMSTypedStream
    public InputStream getContentStream() {
        try {
            return getContentStream(this.content);
        } catch (IOException e) {
            throw new CMSRuntimeException("unable to convert content to stream: " + e.getMessage(), e);
        }
    }
}
