package org.bouncycastle.asn1.util;

import java.io.FileInputStream;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Primitive;

/* JADX INFO: loaded from: classes2.dex */
public class Dump {
    public static void main(String[] strArr) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(strArr[0]);
        try {
            ASN1InputStream aSN1InputStream = new ASN1InputStream(fileInputStream);
            while (true) {
                ASN1Primitive object = aSN1InputStream.readObject();
                if (object == null) {
                    return;
                } else {
                    System.out.println(ASN1Dump.dumpAsString(object));
                }
            }
        } finally {
            fileInputStream.close();
        }
    }
}
