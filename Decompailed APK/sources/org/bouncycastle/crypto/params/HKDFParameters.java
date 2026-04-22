package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class HKDFParameters implements DerivationParameters {
    private final byte[] ikm;

    /* JADX INFO: renamed from: info, reason: collision with root package name */
    private final byte[] f78info;
    private final byte[] salt;
    private final boolean skipExpand;

    private HKDFParameters(byte[] bArr, boolean z, byte[] bArr2, byte[] bArr3) {
        if (bArr == null) {
            throw new IllegalArgumentException("IKM (input keying material) should not be null");
        }
        this.ikm = Arrays.clone(bArr);
        this.skipExpand = z;
        if (bArr2 == null || bArr2.length == 0) {
            this.salt = null;
        } else {
            this.salt = Arrays.clone(bArr2);
        }
        if (bArr3 == null) {
            this.f78info = new byte[0];
        } else {
            this.f78info = Arrays.clone(bArr3);
        }
    }

    public HKDFParameters(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this(bArr, false, bArr2, bArr3);
    }

    public static HKDFParameters defaultParameters(byte[] bArr) {
        return new HKDFParameters(bArr, false, null, null);
    }

    public static HKDFParameters skipExtractParameters(byte[] bArr, byte[] bArr2) {
        return new HKDFParameters(bArr, true, null, bArr2);
    }

    public byte[] getIKM() {
        return Arrays.clone(this.ikm);
    }

    public byte[] getInfo() {
        return Arrays.clone(this.f78info);
    }

    public byte[] getSalt() {
        return Arrays.clone(this.salt);
    }

    public boolean skipExtract() {
        return this.skipExpand;
    }
}
