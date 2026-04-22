package javax.jmdns.impl.constants;

import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;

/* JADX INFO: loaded from: classes.dex */
public enum DNSResultCode {
    Unknown("Unknown", 65535),
    NoError("No Error", 0),
    FormErr("Format Error", 1),
    ServFail("Server Failure", 2),
    NXDomain("Non-Existent Domain", 3),
    NotImp("Not Implemented", 4),
    Refused("Query Refused", 5),
    YXDomain("Name Exists when it should not", 6),
    YXRRSet("RR Set Exists when it should not", 7),
    NXRRSet("RR Set that should exist does not", 8),
    NotAuth("Server Not Authoritative for zone", 9),
    NotZone("NotZone Name not contained in zone", 10);

    private final String _externalName;
    private final int _index;

    DNSResultCode(String str, int i) {
        this._externalName = str;
        this._index = i;
    }

    public int indexValue() {
        return this._index;
    }

    public static DNSResultCode resultCodeForFlags(int i, int i2) {
        int i3 = (i & 15) | ((i2 >> 28) & GF2Field.MASK);
        for (DNSResultCode dNSResultCode : values()) {
            if (dNSResultCode._index == i3) {
                return dNSResultCode;
            }
        }
        return Unknown;
    }

    @Override // java.lang.Enum
    public String toString() {
        return name() + " index " + indexValue();
    }
}
