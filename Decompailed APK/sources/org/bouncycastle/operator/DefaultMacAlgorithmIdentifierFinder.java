package org.bouncycastle.operator;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.util.Strings;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultMacAlgorithmIdentifierFinder implements MacAlgorithmIdentifierFinder {
    private static Map macNameToAlgIds;

    static {
        HashMap map = new HashMap();
        macNameToAlgIds = map;
        map.put("HMACSHA1", new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1));
        Map map2 = macNameToAlgIds;
        ASN1ObjectIdentifier aSN1ObjectIdentifier = PKCSObjectIdentifiers.id_hmacWithSHA224;
        DERNull dERNull = DERNull.INSTANCE;
        map2.put("HMACSHA224", new AlgorithmIdentifier(aSN1ObjectIdentifier, dERNull));
        macNameToAlgIds.put("HMACSHA256", new AlgorithmIdentifier(PKCSObjectIdentifiers.id_hmacWithSHA256, dERNull));
        macNameToAlgIds.put("HMACSHA384", new AlgorithmIdentifier(PKCSObjectIdentifiers.id_hmacWithSHA384, dERNull));
        macNameToAlgIds.put("HMACSHA512", new AlgorithmIdentifier(PKCSObjectIdentifiers.id_hmacWithSHA512, dERNull));
        macNameToAlgIds.put("HMACSHA512-224", new AlgorithmIdentifier(PKCSObjectIdentifiers.id_hmacWithSHA512_224, dERNull));
        macNameToAlgIds.put("HMACSHA512-256", new AlgorithmIdentifier(PKCSObjectIdentifiers.id_hmacWithSHA512_256, dERNull));
        macNameToAlgIds.put("HMACSHA3-224", new AlgorithmIdentifier(NISTObjectIdentifiers.id_hmacWithSHA3_224));
        macNameToAlgIds.put("HMACSHA3-256", new AlgorithmIdentifier(NISTObjectIdentifiers.id_hmacWithSHA3_256));
        macNameToAlgIds.put("HMACSHA3-384", new AlgorithmIdentifier(NISTObjectIdentifiers.id_hmacWithSHA3_384));
        macNameToAlgIds.put("HMACSHA3-512", new AlgorithmIdentifier(NISTObjectIdentifiers.id_hmacWithSHA3_512));
    }

    @Override // org.bouncycastle.operator.MacAlgorithmIdentifierFinder
    public AlgorithmIdentifier find(String str) {
        return (AlgorithmIdentifier) macNameToAlgIds.get(Strings.toUpperCase(str));
    }
}
