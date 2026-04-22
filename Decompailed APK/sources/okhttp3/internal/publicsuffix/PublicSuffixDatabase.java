package okhttp3.internal.publicsuffix;

import ch.qos.logback.core.CoreConstants;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.IDN;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import okhttp3.internal.Util;
import okhttp3.internal.platform.Platform;
import okio.BufferedSource;
import okio.GzipSource;
import okio.Okio;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;

/* JADX INFO: compiled from: PublicSuffixDatabase.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0005\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ#\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\r\u0010\u0003J\u000f\u0010\u000e\u001a\u00020\fH\u0002¢\u0006\u0004\b\u000e\u0010\u0003J\u0017\u0010\u000f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0012\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0018\u001a\u00020\u00178\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0016\u0010\u001a\u001a\u00020\u00178\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u001a\u0010\u0019¨\u0006\u001c"}, d2 = {"Lokhttp3/internal/publicsuffix/PublicSuffixDatabase;", "", "<init>", "()V", "", "domain", "", "splitDomain", "(Ljava/lang/String;)Ljava/util/List;", "domainLabels", "findMatchingRule", "(Ljava/util/List;)Ljava/util/List;", "", "readTheListUninterruptibly", "readTheList", "getEffectiveTldPlusOne", "(Ljava/lang/String;)Ljava/lang/String;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "listRead", "Ljava/util/concurrent/atomic/AtomicBoolean;", "Ljava/util/concurrent/CountDownLatch;", "readCompleteLatch", "Ljava/util/concurrent/CountDownLatch;", "", "publicSuffixListBytes", "[B", "publicSuffixExceptionListBytes", "Companion", "okhttp"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PublicSuffixDatabase {
    private byte[] publicSuffixExceptionListBytes;
    private byte[] publicSuffixListBytes;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final byte[] WILDCARD_LABEL = {42};
    private static final List PREVAILING_RULE = CollectionsKt.listOf("*");
    private static final PublicSuffixDatabase instance = new PublicSuffixDatabase();
    private final AtomicBoolean listRead = new AtomicBoolean(false);
    private final CountDownLatch readCompleteLatch = new CountDownLatch(1);

    public final String getEffectiveTldPlusOne(String domain) {
        int size;
        int size2;
        Intrinsics.checkNotNullParameter(domain, "domain");
        String unicodeDomain = IDN.toUnicode(domain);
        Intrinsics.checkNotNullExpressionValue(unicodeDomain, "unicodeDomain");
        List listSplitDomain = splitDomain(unicodeDomain);
        List listFindMatchingRule = findMatchingRule(listSplitDomain);
        if (listSplitDomain.size() == listFindMatchingRule.size() && ((String) listFindMatchingRule.get(0)).charAt(0) != '!') {
            return null;
        }
        if (((String) listFindMatchingRule.get(0)).charAt(0) == '!') {
            size = listSplitDomain.size();
            size2 = listFindMatchingRule.size();
        } else {
            size = listSplitDomain.size();
            size2 = listFindMatchingRule.size() + 1;
        }
        return SequencesKt.joinToString$default(SequencesKt.drop(CollectionsKt.asSequence(splitDomain(domain)), size - size2), ".", null, null, 0, null, null, 62, null);
    }

    private final List splitDomain(String domain) {
        List listSplit$default = StringsKt.split$default((CharSequence) domain, new char[]{CoreConstants.DOT}, false, 0, 6, (Object) null);
        return Intrinsics.areEqual(CollectionsKt.last(listSplit$default), "") ? CollectionsKt.dropLast(listSplit$default, 1) : listSplit$default;
    }

    private final List findMatchingRule(List domainLabels) {
        String str;
        String strBinarySearch;
        String str2;
        List listEmptyList;
        List listEmptyList2;
        if (!this.listRead.get() && this.listRead.compareAndSet(false, true)) {
            readTheListUninterruptibly();
        } else {
            try {
                this.readCompleteLatch.await();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
        if (this.publicSuffixListBytes == null) {
            throw new IllegalStateException("Unable to load publicsuffixes.gz resource from the classpath.");
        }
        int size = domainLabels.size();
        byte[][] bArr = new byte[size][];
        for (int i = 0; i < size; i++) {
            String str3 = (String) domainLabels.get(i);
            Charset UTF_8 = StandardCharsets.UTF_8;
            Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
            byte[] bytes = str3.getBytes(UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            bArr[i] = bytes;
        }
        int i2 = 0;
        while (true) {
            str = null;
            if (i2 >= size) {
                strBinarySearch = null;
                break;
            }
            Companion companion = INSTANCE;
            byte[] bArr2 = this.publicSuffixListBytes;
            if (bArr2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("publicSuffixListBytes");
                bArr2 = null;
            }
            strBinarySearch = companion.binarySearch(bArr2, bArr, i2);
            if (strBinarySearch != null) {
                break;
            }
            i2++;
        }
        if (size > 1) {
            byte[][] bArr3 = (byte[][]) bArr.clone();
            int length = bArr3.length - 1;
            for (int i3 = 0; i3 < length; i3++) {
                bArr3[i3] = WILDCARD_LABEL;
                Companion companion2 = INSTANCE;
                byte[] bArr4 = this.publicSuffixListBytes;
                if (bArr4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("publicSuffixListBytes");
                    bArr4 = null;
                }
                String strBinarySearch2 = companion2.binarySearch(bArr4, bArr3, i3);
                if (strBinarySearch2 != null) {
                    str2 = strBinarySearch2;
                    break;
                }
            }
            str2 = null;
        } else {
            str2 = null;
        }
        if (str2 != null) {
            int i4 = size - 1;
            int i5 = 0;
            while (true) {
                if (i5 >= i4) {
                    break;
                }
                Companion companion3 = INSTANCE;
                byte[] bArr5 = this.publicSuffixExceptionListBytes;
                if (bArr5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("publicSuffixExceptionListBytes");
                    bArr5 = null;
                }
                String strBinarySearch3 = companion3.binarySearch(bArr5, bArr, i5);
                if (strBinarySearch3 != null) {
                    str = strBinarySearch3;
                    break;
                }
                i5++;
            }
        }
        if (str != null) {
            return StringsKt.split$default((CharSequence) ('!' + str), new char[]{CoreConstants.DOT}, false, 0, 6, (Object) null);
        }
        if (strBinarySearch == null && str2 == null) {
            return PREVAILING_RULE;
        }
        if (strBinarySearch == null || (listEmptyList = StringsKt.split$default((CharSequence) strBinarySearch, new char[]{CoreConstants.DOT}, false, 0, 6, (Object) null)) == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        if (str2 == null || (listEmptyList2 = StringsKt.split$default((CharSequence) str2, new char[]{CoreConstants.DOT}, false, 0, 6, (Object) null)) == null) {
            listEmptyList2 = CollectionsKt.emptyList();
        }
        return listEmptyList.size() > listEmptyList2.size() ? listEmptyList : listEmptyList2;
    }

    private final void readTheListUninterruptibly() {
        boolean z = false;
        while (true) {
            try {
                try {
                    readTheList();
                    break;
                } catch (InterruptedIOException unused) {
                    Thread.interrupted();
                    z = true;
                } catch (IOException e) {
                    Platform.Companion.get().log("Failed to read public suffix list", 5, e);
                    if (z) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    return;
                }
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
    }

    private final void readTheList() {
        try {
            Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
            Ref$ObjectRef ref$ObjectRef2 = new Ref$ObjectRef();
            InputStream resourceAsStream = PublicSuffixDatabase.class.getResourceAsStream("publicsuffixes.gz");
            if (resourceAsStream == null) {
                return;
            }
            BufferedSource bufferedSourceBuffer = Okio.buffer(new GzipSource(Okio.source(resourceAsStream)));
            try {
                ref$ObjectRef.element = bufferedSourceBuffer.readByteArray(bufferedSourceBuffer.readInt());
                ref$ObjectRef2.element = bufferedSourceBuffer.readByteArray(bufferedSourceBuffer.readInt());
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(bufferedSourceBuffer, null);
                synchronized (this) {
                    Object obj = ref$ObjectRef.element;
                    Intrinsics.checkNotNull(obj);
                    this.publicSuffixListBytes = (byte[]) obj;
                    Object obj2 = ref$ObjectRef2.element;
                    Intrinsics.checkNotNull(obj2);
                    this.publicSuffixExceptionListBytes = (byte[]) obj2;
                }
            } finally {
            }
        } finally {
            this.readCompleteLatch.countDown();
        }
    }

    /* JADX INFO: compiled from: PublicSuffixDatabase.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final PublicSuffixDatabase get() {
            return PublicSuffixDatabase.instance;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String binarySearch(byte[] bArr, byte[][] bArr2, int i) {
            int i2;
            int iAnd;
            boolean z;
            int iAnd2;
            int length = bArr.length;
            int i3 = 0;
            while (i3 < length) {
                int i4 = (i3 + length) / 2;
                while (i4 > -1 && bArr[i4] != 10) {
                    i4--;
                }
                int i5 = i4 + 1;
                int i6 = 1;
                while (true) {
                    i2 = i5 + i6;
                    if (bArr[i2] == 10) {
                        break;
                    }
                    i6++;
                }
                int i7 = i2 - i5;
                int i8 = i;
                boolean z2 = false;
                int i9 = 0;
                int i10 = 0;
                while (true) {
                    if (z2) {
                        iAnd = 46;
                        z = false;
                    } else {
                        boolean z3 = z2;
                        iAnd = Util.and(bArr2[i8][i9], GF2Field.MASK);
                        z = z3;
                    }
                    iAnd2 = iAnd - Util.and(bArr[i5 + i10], GF2Field.MASK);
                    if (iAnd2 != 0) {
                        break;
                    }
                    i10++;
                    i9++;
                    if (i10 == i7) {
                        break;
                    }
                    if (bArr2[i8].length != i9) {
                        z2 = z;
                    } else {
                        if (i8 == bArr2.length - 1) {
                            break;
                        }
                        i8++;
                        z2 = true;
                        i9 = -1;
                    }
                }
                if (iAnd2 >= 0) {
                    if (iAnd2 <= 0) {
                        int i11 = i7 - i10;
                        int length2 = bArr2[i8].length - i9;
                        int length3 = bArr2.length;
                        for (int i12 = i8 + 1; i12 < length3; i12++) {
                            length2 += bArr2[i12].length;
                        }
                        if (length2 >= i11) {
                            if (length2 <= i11) {
                                Charset UTF_8 = StandardCharsets.UTF_8;
                                Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
                                return new String(bArr, i5, i7, UTF_8);
                            }
                        }
                    }
                    i3 = i2 + 1;
                }
                length = i4;
            }
            return null;
        }
    }
}
