package org.bouncycastle.asn1.pkcs;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.DLSequence;

/* JADX INFO: loaded from: classes2.dex */
public class AuthenticatedSafe extends ASN1Object {

    /* JADX INFO: renamed from: info, reason: collision with root package name */
    private ContentInfo[] f70info;
    private boolean isBer;

    private AuthenticatedSafe(ASN1Sequence aSN1Sequence) {
        this.isBer = true;
        this.f70info = new ContentInfo[aSN1Sequence.size()];
        int i = 0;
        while (true) {
            ContentInfo[] contentInfoArr = this.f70info;
            if (i == contentInfoArr.length) {
                this.isBer = aSN1Sequence instanceof BERSequence;
                return;
            } else {
                contentInfoArr[i] = ContentInfo.getInstance(aSN1Sequence.getObjectAt(i));
                i++;
            }
        }
    }

    public AuthenticatedSafe(ContentInfo[] contentInfoArr) {
        this.isBer = true;
        this.f70info = copy(contentInfoArr);
    }

    private ContentInfo[] copy(ContentInfo[] contentInfoArr) {
        int length = contentInfoArr.length;
        ContentInfo[] contentInfoArr2 = new ContentInfo[length];
        System.arraycopy(contentInfoArr, 0, contentInfoArr2, 0, length);
        return contentInfoArr2;
    }

    public static AuthenticatedSafe getInstance(Object obj) {
        if (obj instanceof AuthenticatedSafe) {
            return (AuthenticatedSafe) obj;
        }
        if (obj != null) {
            return new AuthenticatedSafe(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ContentInfo[] getContentInfo() {
        return copy(this.f70info);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.isBer ? new BERSequence(this.f70info) : new DLSequence(this.f70info);
    }
}
