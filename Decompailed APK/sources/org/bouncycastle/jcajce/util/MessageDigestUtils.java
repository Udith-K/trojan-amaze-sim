package org.bouncycastle.jcajce.util;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.gm.GMObjectIdentifiers;
import org.bouncycastle.asn1.gnu.GNUObjectIdentifiers;
import org.bouncycastle.asn1.iso.ISOIECObjectIdentifiers;
import org.bouncycastle.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

/* JADX INFO: loaded from: classes2.dex */
public class MessageDigestUtils {
    private static Map<ASN1ObjectIdentifier, String> digestOidMap = new HashMap();
    private static Map<String, AlgorithmIdentifier> digestAlgIdMap = new HashMap();

    static {
        digestOidMap.put(PKCSObjectIdentifiers.md2, "MD2");
        digestOidMap.put(PKCSObjectIdentifiers.md4, "MD4");
        digestOidMap.put(PKCSObjectIdentifiers.md5, "MD5");
        Map<ASN1ObjectIdentifier, String> map = digestOidMap;
        ASN1ObjectIdentifier aSN1ObjectIdentifier = OIWObjectIdentifiers.idSHA1;
        map.put(aSN1ObjectIdentifier, McElieceCCA2KeyGenParameterSpec.SHA1);
        Map<ASN1ObjectIdentifier, String> map2 = digestOidMap;
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = NISTObjectIdentifiers.id_sha224;
        map2.put(aSN1ObjectIdentifier2, McElieceCCA2KeyGenParameterSpec.SHA224);
        Map<ASN1ObjectIdentifier, String> map3 = digestOidMap;
        ASN1ObjectIdentifier aSN1ObjectIdentifier3 = NISTObjectIdentifiers.id_sha256;
        map3.put(aSN1ObjectIdentifier3, "SHA-256");
        Map<ASN1ObjectIdentifier, String> map4 = digestOidMap;
        ASN1ObjectIdentifier aSN1ObjectIdentifier4 = NISTObjectIdentifiers.id_sha384;
        map4.put(aSN1ObjectIdentifier4, McElieceCCA2KeyGenParameterSpec.SHA384);
        Map<ASN1ObjectIdentifier, String> map5 = digestOidMap;
        ASN1ObjectIdentifier aSN1ObjectIdentifier5 = NISTObjectIdentifiers.id_sha512;
        map5.put(aSN1ObjectIdentifier5, "SHA-512");
        digestOidMap.put(NISTObjectIdentifiers.id_sha512_224, "SHA-512(224)");
        digestOidMap.put(NISTObjectIdentifiers.id_sha512_256, "SHA-512(256)");
        digestOidMap.put(TeleTrusTObjectIdentifiers.ripemd128, "RIPEMD-128");
        digestOidMap.put(TeleTrusTObjectIdentifiers.ripemd160, "RIPEMD-160");
        digestOidMap.put(TeleTrusTObjectIdentifiers.ripemd256, "RIPEMD-128");
        digestOidMap.put(ISOIECObjectIdentifiers.ripemd128, "RIPEMD-128");
        digestOidMap.put(ISOIECObjectIdentifiers.ripemd160, "RIPEMD-160");
        digestOidMap.put(CryptoProObjectIdentifiers.gostR3411, "GOST3411");
        digestOidMap.put(GNUObjectIdentifiers.Tiger_192, "Tiger");
        digestOidMap.put(ISOIECObjectIdentifiers.whirlpool, "Whirlpool");
        Map<ASN1ObjectIdentifier, String> map6 = digestOidMap;
        ASN1ObjectIdentifier aSN1ObjectIdentifier6 = NISTObjectIdentifiers.id_sha3_224;
        map6.put(aSN1ObjectIdentifier6, "SHA3-224");
        Map<ASN1ObjectIdentifier, String> map7 = digestOidMap;
        ASN1ObjectIdentifier aSN1ObjectIdentifier7 = NISTObjectIdentifiers.id_sha3_256;
        map7.put(aSN1ObjectIdentifier7, "SHA3-256");
        Map<ASN1ObjectIdentifier, String> map8 = digestOidMap;
        ASN1ObjectIdentifier aSN1ObjectIdentifier8 = NISTObjectIdentifiers.id_sha3_384;
        map8.put(aSN1ObjectIdentifier8, "SHA3-384");
        Map<ASN1ObjectIdentifier, String> map9 = digestOidMap;
        ASN1ObjectIdentifier aSN1ObjectIdentifier9 = NISTObjectIdentifiers.id_sha3_512;
        map9.put(aSN1ObjectIdentifier9, "SHA3-512");
        digestOidMap.put(NISTObjectIdentifiers.id_shake128, "SHAKE128");
        digestOidMap.put(NISTObjectIdentifiers.id_shake256, "SHAKE256");
        digestOidMap.put(GMObjectIdentifiers.sm3, "SM3");
        Map<ASN1ObjectIdentifier, String> map10 = digestOidMap;
        ASN1ObjectIdentifier aSN1ObjectIdentifier10 = MiscObjectIdentifiers.blake3_256;
        map10.put(aSN1ObjectIdentifier10, "BLAKE3-256");
        digestAlgIdMap.put(McElieceCCA2KeyGenParameterSpec.SHA1, new AlgorithmIdentifier(aSN1ObjectIdentifier, DERNull.INSTANCE));
        digestAlgIdMap.put(McElieceCCA2KeyGenParameterSpec.SHA224, new AlgorithmIdentifier(aSN1ObjectIdentifier2));
        digestAlgIdMap.put("SHA-256", new AlgorithmIdentifier(aSN1ObjectIdentifier3));
        digestAlgIdMap.put(McElieceCCA2KeyGenParameterSpec.SHA384, new AlgorithmIdentifier(aSN1ObjectIdentifier4));
        digestAlgIdMap.put("SHA-512", new AlgorithmIdentifier(aSN1ObjectIdentifier5));
        digestAlgIdMap.put("SHA3-224", new AlgorithmIdentifier(aSN1ObjectIdentifier6));
        digestAlgIdMap.put("SHA3-256", new AlgorithmIdentifier(aSN1ObjectIdentifier7));
        digestAlgIdMap.put("SHA3-384", new AlgorithmIdentifier(aSN1ObjectIdentifier8));
        digestAlgIdMap.put("SHA3-512", new AlgorithmIdentifier(aSN1ObjectIdentifier9));
        digestAlgIdMap.put("BLAKE3-256", new AlgorithmIdentifier(aSN1ObjectIdentifier10));
    }

    public static AlgorithmIdentifier getDigestAlgID(String str) {
        if (digestAlgIdMap.containsKey(str)) {
            return digestAlgIdMap.get(str);
        }
        throw new IllegalArgumentException("unknown digest: " + str);
    }

    public static String getDigestName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String str = digestOidMap.get(aSN1ObjectIdentifier);
        return str != null ? str : aSN1ObjectIdentifier.getId();
    }
}
