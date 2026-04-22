package org.bouncycastle.openssl;

import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
interface PEMKeyPairParser {
    PEMKeyPair parse(byte[] bArr) throws IOException;
}
