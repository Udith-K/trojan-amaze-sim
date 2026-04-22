package org.fdroid.fdroid;

import java.security.DigestInputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DigestInputStream.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"getDigestHex", "", "Ljava/security/DigestInputStream;", "download_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class DigestInputStreamKt {
    public static final String getDigestHex(DigestInputStream digestInputStream) {
        Intrinsics.checkNotNullParameter(digestInputStream, "<this>");
        byte[] bArrDigest = digestInputStream.getMessageDigest().digest();
        Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
        return HashUtilsKt.toHex(bArrDigest);
    }
}
