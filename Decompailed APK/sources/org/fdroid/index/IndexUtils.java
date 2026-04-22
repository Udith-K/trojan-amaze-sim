package org.fdroid.index;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: IndexUtils.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0007J\u0011\u0010\u000b\u001a\u00020\t*\u00020\u0005H\u0000¢\u0006\u0002\b\fJ\u0011\u0010\r\u001a\u00020\u0005*\u00020\tH\u0000¢\u0006\u0002\b\u000eJ\u0015\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\tH\u0000¢\u0006\u0002\b\u0011J\u0015\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\tH\u0001¢\u0006\u0002\b\u0013¨\u0006\u0014"}, d2 = {"Lorg/fdroid/index/IndexUtils;", "", "<init>", "()V", "getFingerprint", "", "certificate", "getPackageSigner", "signerBytes", "", "getsig", "decodeHex", "decodeHex$index_release", "toHex", "toHex$index_release", "sha256", "bytes", "sha256$index_release", "md5", "md5$index_release", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class IndexUtils {
    public static final IndexUtils INSTANCE = new IndexUtils();

    private IndexUtils() {
    }

    public final String getFingerprint(String certificate) {
        Intrinsics.checkNotNullParameter(certificate, "certificate");
        return toHex$index_release(sha256$index_release(decodeHex$index_release(certificate)));
    }

    public final String getPackageSigner(byte[] signerBytes) {
        Intrinsics.checkNotNullParameter(signerBytes, "signerBytes");
        return toHex$index_release(sha256$index_release(signerBytes));
    }

    @Deprecated
    public final String getsig(byte[] signerBytes) {
        Intrinsics.checkNotNullParameter(signerBytes, "signerBytes");
        return toHex$index_release(md5$index_release(StringsKt.encodeToByteArray(toHex$index_release(signerBytes))));
    }

    public final byte[] decodeHex$index_release(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (str.length() % 2 != 0) {
            throw new IllegalStateException("Must have an even length");
        }
        List listChunked = StringsKt.chunked(str, 2);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listChunked, 10));
        Iterator it = listChunked.iterator();
        while (it.hasNext()) {
            arrayList.add(Byte.valueOf((byte) Integer.parseInt((String) it.next(), CharsKt.checkRadix(16))));
        }
        return CollectionsKt.toByteArray(arrayList);
    }

    public final String toHex$index_release(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return ArraysKt.joinToString$default(bArr, "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: org.fdroid.index.IndexUtils$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return IndexUtils.toHex$lambda$2(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence toHex$lambda$2(byte b) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    public final byte[] sha256$index_release(byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bytes);
            byte[] bArrDigest = messageDigest.digest();
            Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
            return bArrDigest;
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    @Deprecated
    public final byte[] md5$index_release(byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            byte[] bArrDigest = messageDigest.digest();
            Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
            return bArrDigest;
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }
}
