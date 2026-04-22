package org.fdroid.database;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.fdroid.index.IndexFormatVersion;
import org.fdroid.index.v2.AntiFeatureV2;
import org.fdroid.index.v2.CategoryV2;
import org.fdroid.index.v2.MirrorV2;
import org.fdroid.index.v2.ReleaseChannelV2;
import org.fdroid.index.v2.RepoV2;

/* JADX INFO: compiled from: Repository.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u001a2\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u0001H\u0000\u001a\u0014\u0010\u000f\u001a\u00020\u0010*\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0006H\u0000\u001a \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00100\u0013*\b\u0012\u0004\u0012\u00020\u00110\u00132\u0006\u0010\u0005\u001a\u00020\u0006H\u0000\u001a&\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0013*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0005\u001a\u00020\u0006H\u0000\u001a&\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0013*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u001a0\u00162\u0006\u0010\u0005\u001a\u00020\u0006H\u0000\u001a&\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0013*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u001d0\u00162\u0006\u0010\u0005\u001a\u00020\u0006H\u0000\u001a\u0012\u0010\u001e\u001a\u00020\u001f2\b\u0010\n\u001a\u0004\u0018\u00010\u0001H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006 "}, d2 = {"TAG", "", "toCoreRepository", "Lorg/fdroid/database/CoreRepository;", "Lorg/fdroid/index/v2/RepoV2;", "repoId", "", "version", "formatVersion", "Lorg/fdroid/index/IndexFormatVersion;", "certificate", "DUMMY_TEST_REPO", "Lorg/fdroid/database/Repository;", "getDUMMY_TEST_REPO", "()Lorg/fdroid/database/Repository;", "toMirror", "Lorg/fdroid/database/Mirror;", "Lorg/fdroid/index/v2/MirrorV2;", "toMirrors", "", "toRepoAntiFeatures", "Lorg/fdroid/database/AntiFeature;", "", "Lorg/fdroid/index/v2/AntiFeatureV2;", "toRepoCategories", "Lorg/fdroid/database/Category;", "Lorg/fdroid/index/v2/CategoryV2;", "toRepoReleaseChannel", "Lorg/fdroid/database/ReleaseChannel;", "Lorg/fdroid/index/v2/ReleaseChannelV2;", "validateCertificate", "", "database_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class RepositoryKt {
    private static final Repository DUMMY_TEST_REPO;
    private static final String TAG = "Repository";

    public static /* synthetic */ CoreRepository toCoreRepository$default(RepoV2 repoV2, long j, long j2, IndexFormatVersion indexFormatVersion, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 0;
        }
        long j3 = j;
        if ((i & 4) != 0) {
            indexFormatVersion = null;
        }
        return toCoreRepository(repoV2, j3, j2, indexFormatVersion, str);
    }

    public static final CoreRepository toCoreRepository(RepoV2 repoV2, long j, long j2, IndexFormatVersion indexFormatVersion, String certificate) {
        Intrinsics.checkNotNullParameter(repoV2, "<this>");
        Intrinsics.checkNotNullParameter(certificate, "certificate");
        return new CoreRepository(j, repoV2.getName(), repoV2.getIcon(), repoV2.getAddress(), repoV2.getWebBaseUrl(), repoV2.getTimestamp(), Long.valueOf(j2), indexFormatVersion, null, repoV2.getDescription(), certificate);
    }

    public static final List<AntiFeature> toRepoAntiFeatures(Map<String, AntiFeatureV2> map, long j) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry<String, AntiFeatureV2> entry : map.entrySet()) {
            arrayList.add(new AntiFeature(j, entry.getKey(), entry.getValue().getIcon(), entry.getValue().getName(), entry.getValue().getDescription()));
        }
        return arrayList;
    }

    public static final List<Category> toRepoCategories(Map<String, CategoryV2> map, long j) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry<String, CategoryV2> entry : map.entrySet()) {
            arrayList.add(new Category(j, entry.getKey(), entry.getValue().getIcon(), entry.getValue().getName(), entry.getValue().getDescription()));
        }
        return arrayList;
    }

    public static final List<ReleaseChannel> toRepoReleaseChannel(Map<String, ReleaseChannelV2> map, long j) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry<String, ReleaseChannelV2> entry : map.entrySet()) {
            arrayList.add(new ReleaseChannel(j, entry.getKey(), null, entry.getValue().getName(), entry.getValue().getDescription(), 4, null));
        }
        return arrayList;
    }

    static {
        long jCurrentTimeMillis = System.currentTimeMillis();
        TimeUnit timeUnit = TimeUnit.DAYS;
        DUMMY_TEST_REPO = new Repository(1L, "https://example.com/fdroid/repo", jCurrentTimeMillis - timeUnit.toMillis(2L), IndexFormatVersion.TWO, "abc", 1L, 1, System.currentTimeMillis() - timeUnit.toMillis(1L), null, null, 768, null);
    }

    public static final Repository getDUMMY_TEST_REPO() {
        return DUMMY_TEST_REPO;
    }

    public static final Mirror toMirror(MirrorV2 mirrorV2, long j) {
        Intrinsics.checkNotNullParameter(mirrorV2, "<this>");
        return new Mirror(j, mirrorV2.getUrl(), mirrorV2.getCountryCode());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void validateCertificate(String str) throws IllegalArgumentException {
        Object next;
        if (str != null) {
            if (str.length() % 2 == 0) {
                Iterator it = StringsKt.chunked(str, 2).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    } else {
                        next = it.next();
                        if (StringsKt.toIntOrNull((String) next, 16) == null) {
                            break;
                        }
                    }
                }
                if (next == null) {
                    return;
                }
            }
            throw new IllegalArgumentException(("Invalid certificate: " + str).toString());
        }
    }

    public static final List<Mirror> toMirrors(List<MirrorV2> list, long j) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(toMirror((MirrorV2) it.next(), j));
        }
        return arrayList;
    }
}
