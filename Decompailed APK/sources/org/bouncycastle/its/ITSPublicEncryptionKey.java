package org.bouncycastle.its;

import org.bouncycastle.oer.its.ieee1609dot2.basetypes.PublicEncryptionKey;
import org.bouncycastle.oer.its.ieee1609dot2.basetypes.SymmAlgorithm;

/* JADX INFO: loaded from: classes2.dex */
public class ITSPublicEncryptionKey {
    protected final PublicEncryptionKey encryptionKey;

    public enum symmAlgorithm {
        aes128Ccm(SymmAlgorithm.aes128Ccm.intValueExact());

        private final int tagValue;

        symmAlgorithm(int i) {
            this.tagValue = i;
        }
    }

    public ITSPublicEncryptionKey(PublicEncryptionKey publicEncryptionKey) {
        this.encryptionKey = publicEncryptionKey;
    }

    public PublicEncryptionKey toASN1Structure() {
        return this.encryptionKey;
    }
}
