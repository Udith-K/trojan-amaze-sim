package org.bouncycastle.its;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.HashAlgorithm;

/* JADX INFO: loaded from: classes2.dex */
public class ITSAlgorithmUtils {
    private static final Map<Object, HashAlgorithm> algoMap;

    static {
        HashMap map = new HashMap();
        algoMap = map;
        map.put(NISTObjectIdentifiers.id_sha256, HashAlgorithm.sha256);
        map.put(NISTObjectIdentifiers.id_sha384, HashAlgorithm.sha384);
    }

    public static HashAlgorithm getHashAlgorithm(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return algoMap.get(aSN1ObjectIdentifier);
    }
}
