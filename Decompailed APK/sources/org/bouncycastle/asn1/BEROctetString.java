package org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes2.dex */
public class BEROctetString extends ASN1OctetString {
    private static final int DEFAULT_SEGMENT_LIMIT = 1000;
    private final ASN1OctetString[] elements;
    private final int segmentLimit;

    public BEROctetString(byte[] bArr) {
        this(bArr, DEFAULT_SEGMENT_LIMIT);
    }

    public BEROctetString(byte[] bArr, int i) {
        this(bArr, null, i);
    }

    private BEROctetString(byte[] bArr, ASN1OctetString[] aSN1OctetStringArr, int i) {
        super(bArr);
        this.elements = aSN1OctetStringArr;
        this.segmentLimit = i;
    }

    public BEROctetString(ASN1OctetString[] aSN1OctetStringArr) {
        this(aSN1OctetStringArr, DEFAULT_SEGMENT_LIMIT);
    }

    public BEROctetString(ASN1OctetString[] aSN1OctetStringArr, int i) {
        this(flattenOctetStrings(aSN1OctetStringArr), aSN1OctetStringArr, i);
    }

    static byte[] flattenOctetStrings(ASN1OctetString[] aSN1OctetStringArr) {
        int length = aSN1OctetStringArr.length;
        if (length == 0) {
            return ASN1OctetString.EMPTY_OCTETS;
        }
        if (length == 1) {
            return aSN1OctetStringArr[0].string;
        }
        int length2 = 0;
        for (ASN1OctetString aSN1OctetString : aSN1OctetStringArr) {
            length2 += aSN1OctetString.string.length;
        }
        byte[] bArr = new byte[length2];
        int length3 = 0;
        for (ASN1OctetString aSN1OctetString2 : aSN1OctetStringArr) {
            byte[] bArr2 = aSN1OctetString2.string;
            System.arraycopy(bArr2, 0, bArr, length3, bArr2.length);
            length3 += bArr2.length;
        }
        return bArr;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException {
        if (!encodeConstructed()) {
            byte[] bArr = this.string;
            DEROctetString.encode(aSN1OutputStream, z, bArr, 0, bArr.length);
            return;
        }
        aSN1OutputStream.writeIdentifier(z, 36);
        aSN1OutputStream.write(128);
        ASN1OctetString[] aSN1OctetStringArr = this.elements;
        if (aSN1OctetStringArr == null) {
            int i = 0;
            while (true) {
                byte[] bArr2 = this.string;
                if (i >= bArr2.length) {
                    break;
                }
                int iMin = Math.min(bArr2.length - i, this.segmentLimit);
                DEROctetString.encode(aSN1OutputStream, true, this.string, i, iMin);
                i += iMin;
            }
        } else {
            aSN1OutputStream.writePrimitives(aSN1OctetStringArr);
        }
        aSN1OutputStream.write(0);
        aSN1OutputStream.write(0);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    boolean encodeConstructed() {
        return this.elements != null || this.string.length > this.segmentLimit;
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    int encodedLength(boolean z) throws IOException {
        if (!encodeConstructed()) {
            return DEROctetString.encodedLength(z, this.string.length);
        }
        int iEncodedLength = z ? 4 : 3;
        if (this.elements == null) {
            int length = this.string.length;
            int i = this.segmentLimit;
            int i2 = length / i;
            int iEncodedLength2 = iEncodedLength + (DEROctetString.encodedLength(true, i) * i2);
            int length2 = this.string.length - (i2 * this.segmentLimit);
            return length2 > 0 ? iEncodedLength2 + DEROctetString.encodedLength(true, length2) : iEncodedLength2;
        }
        int i3 = 0;
        while (true) {
            ASN1OctetString[] aSN1OctetStringArr = this.elements;
            if (i3 >= aSN1OctetStringArr.length) {
                return iEncodedLength;
            }
            iEncodedLength += aSN1OctetStringArr[i3].encodedLength(true);
            i3++;
        }
    }

    public Enumeration getObjects() {
        return this.elements == null ? new Enumeration() { // from class: org.bouncycastle.asn1.BEROctetString.1
            int pos = 0;

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.pos < BEROctetString.this.string.length;
            }

            @Override // java.util.Enumeration
            public Object nextElement() {
                int i = this.pos;
                BEROctetString bEROctetString = BEROctetString.this;
                byte[] bArr = bEROctetString.string;
                if (i >= bArr.length) {
                    throw new NoSuchElementException();
                }
                int iMin = Math.min(bArr.length - i, bEROctetString.segmentLimit);
                byte[] bArr2 = new byte[iMin];
                System.arraycopy(BEROctetString.this.string, this.pos, bArr2, 0, iMin);
                this.pos += iMin;
                return new DEROctetString(bArr2);
            }
        } : new Enumeration() { // from class: org.bouncycastle.asn1.BEROctetString.2
            int counter = 0;

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.counter < BEROctetString.this.elements.length;
            }

            @Override // java.util.Enumeration
            public Object nextElement() {
                if (this.counter >= BEROctetString.this.elements.length) {
                    throw new NoSuchElementException();
                }
                ASN1OctetString[] aSN1OctetStringArr = BEROctetString.this.elements;
                int i = this.counter;
                this.counter = i + 1;
                return aSN1OctetStringArr[i];
            }
        };
    }
}
