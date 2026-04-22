package org.bouncycastle.asn1;

import java.io.IOException;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;

/* JADX INFO: loaded from: classes2.dex */
public class DERBitString extends ASN1BitString {
    public DERBitString(byte b, int i) {
        super(b, i);
    }

    public DERBitString(int i) {
        super(ASN1BitString.getBytes(i), ASN1BitString.getPadBits(i));
    }

    public DERBitString(ASN1Encodable aSN1Encodable) throws IOException {
        super(aSN1Encodable.toASN1Primitive().getEncoded(ASN1Encoding.DER), 0);
    }

    public DERBitString(byte[] bArr) {
        this(bArr, 0);
    }

    public DERBitString(byte[] bArr, int i) {
        super(bArr, i);
    }

    DERBitString(byte[] bArr, boolean z) {
        super(bArr, z);
    }

    public static DERBitString convert(ASN1BitString aSN1BitString) {
        return (DERBitString) aSN1BitString.toDERObject();
    }

    static DERBitString fromOctetString(ASN1OctetString aSN1OctetString) {
        return new DERBitString(aSN1OctetString.getOctets(), true);
    }

    public static DERBitString getInstance(Object obj) {
        if (obj == null || (obj instanceof DERBitString)) {
            return (DERBitString) obj;
        }
        if (obj instanceof ASN1BitString) {
            return convert((ASN1BitString) obj);
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return convert((ASN1BitString) ASN1Primitive.fromByteArray((byte[]) obj));
        } catch (Exception e) {
            throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
        }
    }

    public static DERBitString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive object = aSN1TaggedObject.getObject();
        return (z || (object instanceof DERBitString)) ? getInstance((Object) object) : fromOctetString(ASN1OctetString.getInstance(object));
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException {
        byte[] bArr = this.contents;
        int i = bArr[0] & 255;
        int length = bArr.length - 1;
        byte b = bArr[length];
        byte b2 = (byte) ((GF2Field.MASK << i) & b);
        if (b == b2) {
            aSN1OutputStream.writeEncodingDL(z, 3, bArr);
        } else {
            aSN1OutputStream.writeEncodingDL(z, 3, bArr, 0, length, b2);
        }
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    boolean encodeConstructed() {
        return false;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    int encodedLength(boolean z) {
        return ASN1OutputStream.getLengthOfEncodingDL(z, this.contents.length);
    }

    @Override // org.bouncycastle.asn1.ASN1BitString, org.bouncycastle.asn1.ASN1Primitive
    ASN1Primitive toDERObject() {
        return this;
    }

    @Override // org.bouncycastle.asn1.ASN1BitString, org.bouncycastle.asn1.ASN1Primitive
    ASN1Primitive toDLObject() {
        return this;
    }
}
