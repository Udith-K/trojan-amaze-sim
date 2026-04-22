package org.fdroid.database;

import android.net.Uri;
import android.util.Log;
import androidx.core.os.LocaleListCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.fdroid.LocaleChooser;
import org.fdroid.fdroid.nearby.peers.BonjourPeer;
import org.fdroid.fdroid.views.apps.AppListActivity;
import org.fdroid.index.IndexFormatVersion;
import org.fdroid.index.IndexUtils;
import org.fdroid.index.v2.FileV2;

/* JADX INFO: compiled from: Repository.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u001a\b\u0086\b\u0018\u00002\u00020\u0001BQ\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010Ba\b\u0017\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0012\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0014\u0012\u0006\u0010\u0019\u001a\u00020\u0012\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\u0006\u0010\u001c\u001a\u00020\u0012\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0014¢\u0006\u0004\b\u000f\u0010\u001fJ\u0010\u00107\u001a\u0004\u0018\u00010\u00142\u0006\u00108\u001a\u000209J\u0010\u0010:\u001a\u0004\u0018\u00010\u00142\u0006\u00108\u001a\u000209J\u0010\u0010;\u001a\u0004\u0018\u00010<2\u0006\u00108\u001a\u000209J\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\b0>J\u0012\u0010?\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\n0>J\u0012\u0010@\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\f0>J\f\u0010U\u001a\b\u0012\u0004\u0012\u00020V0\u0005J\u0018\u0010[\u001a\b\u0012\u0004\u0012\u00020V0\u00052\b\b\u0002\u0010\\\u001a\u000205H\u0007J\u000e\u0010_\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b`J\u0014\u0010a\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÀ\u0003¢\u0006\u0002\bbJ\u0014\u0010c\u001a\b\u0012\u0004\u0012\u00020\b0\u0005HÀ\u0003¢\u0006\u0002\bdJ\u0014\u0010e\u001a\b\u0012\u0004\u0012\u00020\n0\u0005HÀ\u0003¢\u0006\u0002\bfJ\u0014\u0010g\u001a\b\u0012\u0004\u0012\u00020\f0\u0005HÀ\u0003¢\u0006\u0002\bhJ\u000e\u0010i\u001a\u00020\u000eHÀ\u0003¢\u0006\u0002\bjJ]\u0010k\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00052\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00052\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00052\b\b\u0002\u0010\r\u001a\u00020\u000eHÆ\u0001J\u0013\u0010l\u001a\u0002052\b\u0010m\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010n\u001a\u00020\u001bHÖ\u0001J\t\u0010o\u001a\u00020\u0014HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0000X\u0081\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00058\u0000X\u0081\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010#R\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00058\u0000X\u0081\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010#R\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00058\u0000X\u0081\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010#R\u0016\u0010\r\u001a\u00020\u000e8\u0000X\u0081\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b)\u0010*R\u0011\u0010\u0013\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0013\u0010-\u001a\u0004\u0018\u00010\u00148F¢\u0006\u0006\u001a\u0004\b.\u0010,R\u0011\u0010\u0015\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b/\u0010*R\u0011\u0010\u0019\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b0\u0010*R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u00178F¢\u0006\u0006\u001a\u0004\b1\u00102R\u0011\u0010\u0018\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b3\u0010,R\u0011\u00104\u001a\u0002058F¢\u0006\u0006\u001a\u0004\b4\u00106R\u0011\u0010\u001a\u001a\u00020\u001b8F¢\u0006\u0006\u001a\u0004\bA\u0010BR\u0011\u0010C\u001a\u0002058F¢\u0006\u0006\u001a\u0004\bD\u00106R\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u00128F¢\u0006\u0006\u001a\u0004\bE\u0010FR\u0017\u0010G\u001a\b\u0012\u0004\u0012\u00020\u00140\u00058F¢\u0006\u0006\u001a\u0004\bH\u0010#R\u0017\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00140\u00058F¢\u0006\u0006\u001a\u0004\bJ\u0010#R\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u00148F¢\u0006\u0006\u001a\u0004\bK\u0010,R\u0013\u0010\u001e\u001a\u0004\u0018\u00010\u00148F¢\u0006\u0006\u001a\u0004\bL\u0010,R\u001c\u0010M\u001a\u0004\u0018\u00010\u00148FX\u0087\u0004¢\u0006\f\u0012\u0004\bN\u0010O\u001a\u0004\bP\u0010,R\u001d\u0010Q\u001a\u0004\u0018\u00010\u00148FX\u0087\u0084\u0002¢\u0006\f\n\u0004\bS\u0010T\u001a\u0004\bR\u0010,R\u0017\u0010W\u001a\b\u0012\u0004\u0012\u00020V0\u00058F¢\u0006\u0006\u001a\u0004\bX\u0010#R\u0017\u0010Y\u001a\b\u0012\u0004\u0012\u00020V0\u00058F¢\u0006\u0006\u001a\u0004\bZ\u0010#R\u0011\u0010]\u001a\u00020\u00148G¢\u0006\u0006\u001a\u0004\b^\u0010,¨\u0006p"}, d2 = {"Lorg/fdroid/database/Repository;", "", "repository", "Lorg/fdroid/database/CoreRepository;", "mirrors", "", "Lorg/fdroid/database/Mirror;", "antiFeatures", "Lorg/fdroid/database/AntiFeature;", "categories", "Lorg/fdroid/database/Category;", "releaseChannels", "Lorg/fdroid/database/ReleaseChannel;", "preferences", "Lorg/fdroid/database/RepositoryPreferences;", "<init>", "(Lorg/fdroid/database/CoreRepository;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lorg/fdroid/database/RepositoryPreferences;)V", "repoId", "", "address", "", "timestamp", "formatVersion", "Lorg/fdroid/index/IndexFormatVersion;", "certificate", "version", "weight", "", AppListActivity.SortClause.LAST_UPDATED, "username", "password", "(JLjava/lang/String;JLorg/fdroid/index/IndexFormatVersion;Ljava/lang/String;JIJLjava/lang/String;Ljava/lang/String;)V", "getRepository$database_release", "()Lorg/fdroid/database/CoreRepository;", "getMirrors$database_release", "()Ljava/util/List;", "getAntiFeatures$database_release", "getCategories$database_release", "getReleaseChannels$database_release", "getPreferences$database_release", "()Lorg/fdroid/database/RepositoryPreferences;", "getRepoId", "()J", "getAddress", "()Ljava/lang/String;", "webBaseUrl", "getWebBaseUrl", "getTimestamp", "getVersion", "getFormatVersion", "()Lorg/fdroid/index/IndexFormatVersion;", "getCertificate", "isArchiveRepo", "", "()Z", "getName", "localeList", "Landroidx/core/os/LocaleListCompat;", "getDescription", "getIcon", "Lorg/fdroid/index/v2/FileV2;", "getAntiFeatures", "", "getCategories", "getReleaseChannels", "getWeight", "()I", "enabled", "getEnabled", "getLastUpdated", "()Ljava/lang/Long;", "userMirrors", "getUserMirrors", "disabledMirrors", "getDisabledMirrors", "getUsername", "getPassword", "lastETag", "getLastETag$annotations", "()V", "getLastETag", BonjourPeer.FINGERPRINT, "getFingerprint", "fingerprint$delegate", "Lkotlin/Lazy;", "getMirrors", "Lorg/fdroid/download/Mirror;", "allUserMirrors", "getAllUserMirrors", "allOfficialMirrors", "getAllOfficialMirrors", "getAllMirrors", "includeUserMirrors", "shareUri", "getShareUri", "component1", "component1$database_release", "component2", "component2$database_release", "component3", "component3$database_release", "component4", "component4$database_release", "component5", "component5$database_release", "component6", "component6$database_release", "copy", "equals", "other", "hashCode", "toString", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class Repository {
    private final List<AntiFeature> antiFeatures;
    private final List<Category> categories;

    /* JADX INFO: renamed from: fingerprint$delegate, reason: from kotlin metadata */
    private final Lazy fingerprint;
    private final List<Mirror> mirrors;
    private final RepositoryPreferences preferences;
    private final List<ReleaseChannel> releaseChannels;
    private final CoreRepository repository;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Repository(long j, String address, long j2, IndexFormatVersion formatVersion, String certificate, long j3, int i, long j4) {
        this(j, address, j2, formatVersion, certificate, j3, i, j4, null, null, 768, null);
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(formatVersion, "formatVersion");
        Intrinsics.checkNotNullParameter(certificate, "certificate");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Repository(long j, String address, long j2, IndexFormatVersion formatVersion, String certificate, long j3, int i, long j4, String str) {
        this(j, address, j2, formatVersion, certificate, j3, i, j4, str, null, 512, null);
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(formatVersion, "formatVersion");
        Intrinsics.checkNotNullParameter(certificate, "certificate");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Repository copy$default(Repository repository, CoreRepository coreRepository, List list, List list2, List list3, List list4, RepositoryPreferences repositoryPreferences, int i, Object obj) {
        if ((i & 1) != 0) {
            coreRepository = repository.repository;
        }
        if ((i & 2) != 0) {
            list = repository.mirrors;
        }
        List list5 = list;
        if ((i & 4) != 0) {
            list2 = repository.antiFeatures;
        }
        List list6 = list2;
        if ((i & 8) != 0) {
            list3 = repository.categories;
        }
        List list7 = list3;
        if ((i & 16) != 0) {
            list4 = repository.releaseChannels;
        }
        List list8 = list4;
        if ((i & 32) != 0) {
            repositoryPreferences = repository.preferences;
        }
        return repository.copy(coreRepository, list5, list6, list7, list8, repositoryPreferences);
    }

    @Deprecated
    public static /* synthetic */ void getLastETag$annotations() {
    }

    /* JADX INFO: renamed from: component1$database_release, reason: from getter */
    public final CoreRepository getRepository() {
        return this.repository;
    }

    public final List<Mirror> component2$database_release() {
        return this.mirrors;
    }

    public final List<AntiFeature> component3$database_release() {
        return this.antiFeatures;
    }

    public final List<Category> component4$database_release() {
        return this.categories;
    }

    public final List<ReleaseChannel> component5$database_release() {
        return this.releaseChannels;
    }

    /* JADX INFO: renamed from: component6$database_release, reason: from getter */
    public final RepositoryPreferences getPreferences() {
        return this.preferences;
    }

    public final Repository copy(CoreRepository repository, List<Mirror> mirrors, List<AntiFeature> antiFeatures, List<Category> categories, List<ReleaseChannel> releaseChannels, RepositoryPreferences preferences) {
        Intrinsics.checkNotNullParameter(repository, "repository");
        Intrinsics.checkNotNullParameter(mirrors, "mirrors");
        Intrinsics.checkNotNullParameter(antiFeatures, "antiFeatures");
        Intrinsics.checkNotNullParameter(categories, "categories");
        Intrinsics.checkNotNullParameter(releaseChannels, "releaseChannels");
        Intrinsics.checkNotNullParameter(preferences, "preferences");
        return new Repository(repository, mirrors, antiFeatures, categories, releaseChannels, preferences);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Repository)) {
            return false;
        }
        Repository repository = (Repository) other;
        return Intrinsics.areEqual(this.repository, repository.repository) && Intrinsics.areEqual(this.mirrors, repository.mirrors) && Intrinsics.areEqual(this.antiFeatures, repository.antiFeatures) && Intrinsics.areEqual(this.categories, repository.categories) && Intrinsics.areEqual(this.releaseChannels, repository.releaseChannels) && Intrinsics.areEqual(this.preferences, repository.preferences);
    }

    public final List<org.fdroid.download.Mirror> getAllMirrors() {
        return getAllMirrors$default(this, false, 1, null);
    }

    public int hashCode() {
        return (((((((((this.repository.hashCode() * 31) + this.mirrors.hashCode()) * 31) + this.antiFeatures.hashCode()) * 31) + this.categories.hashCode()) * 31) + this.releaseChannels.hashCode()) * 31) + this.preferences.hashCode();
    }

    public String toString() {
        return "Repository(repository=" + this.repository + ", mirrors=" + this.mirrors + ", antiFeatures=" + this.antiFeatures + ", categories=" + this.categories + ", releaseChannels=" + this.releaseChannels + ", preferences=" + this.preferences + ")";
    }

    public Repository(CoreRepository repository, List<Mirror> mirrors, List<AntiFeature> antiFeatures, List<Category> categories, List<ReleaseChannel> releaseChannels, RepositoryPreferences preferences) {
        Intrinsics.checkNotNullParameter(repository, "repository");
        Intrinsics.checkNotNullParameter(mirrors, "mirrors");
        Intrinsics.checkNotNullParameter(antiFeatures, "antiFeatures");
        Intrinsics.checkNotNullParameter(categories, "categories");
        Intrinsics.checkNotNullParameter(releaseChannels, "releaseChannels");
        Intrinsics.checkNotNullParameter(preferences, "preferences");
        this.repository = repository;
        this.mirrors = mirrors;
        this.antiFeatures = antiFeatures;
        this.categories = categories;
        this.releaseChannels = releaseChannels;
        this.preferences = preferences;
        this.fingerprint = LazyKt.lazy(new Function0() { // from class: org.fdroid.database.Repository$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Repository.fingerprint_delegate$lambda$4(this.f$0);
            }
        });
    }

    public final CoreRepository getRepository$database_release() {
        return this.repository;
    }

    public final List<Mirror> getMirrors$database_release() {
        return this.mirrors;
    }

    public final List<AntiFeature> getAntiFeatures$database_release() {
        return this.antiFeatures;
    }

    public final List<Category> getCategories$database_release() {
        return this.categories;
    }

    public final List<ReleaseChannel> getReleaseChannels$database_release() {
        return this.releaseChannels;
    }

    public final RepositoryPreferences getPreferences$database_release() {
        return this.preferences;
    }

    public /* synthetic */ Repository(long j, String str, long j2, IndexFormatVersion indexFormatVersion, String str2, long j3, int i, long j4, String str3, String str4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, str, j2, indexFormatVersion, str2, j3, i, j4, (i2 & 256) != 0 ? null : str3, (i2 & 512) != 0 ? null : str4);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Repository(long j, String address, long j2, IndexFormatVersion formatVersion, String certificate, long j3, int i, long j4, String str, String str2) {
        this(new CoreRepository(j, null, null, address, null, j2, Long.valueOf(j3), formatVersion, 42, null, certificate, 530, null), CollectionsKt.emptyList(), CollectionsKt.emptyList(), CollectionsKt.emptyList(), CollectionsKt.emptyList(), new RepositoryPreferences(j, i, false, Long.valueOf(j4), null, null, null, str, str2, 116, null));
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(formatVersion, "formatVersion");
        Intrinsics.checkNotNullParameter(certificate, "certificate");
    }

    public final long getRepoId() {
        return this.repository.getRepoId();
    }

    public final String getAddress() {
        return this.repository.getAddress();
    }

    public final String getWebBaseUrl() {
        return this.repository.getWebBaseUrl();
    }

    public final long getTimestamp() {
        return this.repository.getTimestamp();
    }

    public final long getVersion() {
        Long version = this.repository.getVersion();
        if (version != null) {
            return version.longValue();
        }
        return 0L;
    }

    public final IndexFormatVersion getFormatVersion() {
        return this.repository.getFormatVersion();
    }

    public final String getCertificate() {
        return this.repository.getCertificate();
    }

    public final boolean isArchiveRepo() {
        return StringsKt.endsWith$default(StringsKt.trimEnd(this.repository.getAddress(), '/'), "/archive", false, 2, (Object) null);
    }

    public final String getName(LocaleListCompat localeList) {
        Intrinsics.checkNotNullParameter(localeList, "localeList");
        return (String) LocaleChooser.INSTANCE.getBestLocale(this.repository.getName(), localeList);
    }

    public final String getDescription(LocaleListCompat localeList) {
        Intrinsics.checkNotNullParameter(localeList, "localeList");
        return (String) LocaleChooser.INSTANCE.getBestLocale(this.repository.getDescription(), localeList);
    }

    public final FileV2 getIcon(LocaleListCompat localeList) {
        Intrinsics.checkNotNullParameter(localeList, "localeList");
        return (FileV2) LocaleChooser.INSTANCE.getBestLocale(this.repository.getIcon(), localeList);
    }

    public final Map<String, AntiFeature> getAntiFeatures() {
        List<AntiFeature> list = this.antiFeatures;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list, 10)), 16));
        for (Object obj : list) {
            linkedHashMap.put(((AntiFeature) obj).getId$database_release(), obj);
        }
        return linkedHashMap;
    }

    public final Map<String, Category> getCategories() {
        List<Category> list = this.categories;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list, 10)), 16));
        for (Object obj : list) {
            linkedHashMap.put(((Category) obj).getId(), obj);
        }
        return linkedHashMap;
    }

    public final Map<String, ReleaseChannel> getReleaseChannels() {
        List<ReleaseChannel> list = this.releaseChannels;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list, 10)), 16));
        for (Object obj : list) {
            linkedHashMap.put(((ReleaseChannel) obj).getId$database_release(), obj);
        }
        return linkedHashMap;
    }

    public final int getWeight() {
        return this.preferences.getWeight();
    }

    public final boolean getEnabled() {
        return this.preferences.getEnabled();
    }

    public final Long getLastUpdated() {
        return this.preferences.getLastUpdated();
    }

    public final List<String> getUserMirrors() {
        List<String> userMirrors = this.preferences.getUserMirrors();
        return userMirrors == null ? CollectionsKt.emptyList() : userMirrors;
    }

    public final List<String> getDisabledMirrors() {
        List<String> disabledMirrors = this.preferences.getDisabledMirrors();
        return disabledMirrors == null ? CollectionsKt.emptyList() : disabledMirrors;
    }

    public final String getUsername() {
        return this.preferences.getUsername();
    }

    public final String getPassword() {
        return this.preferences.getPassword();
    }

    public final String getLastETag() {
        return this.preferences.getLastETag();
    }

    public final String getFingerprint() {
        return (String) this.fingerprint.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String fingerprint_delegate$lambda$4(Repository repository) {
        String certificate = repository.getCertificate();
        if (certificate != null) {
            return IndexUtils.INSTANCE.getFingerprint(certificate);
        }
        return null;
    }

    public final List<org.fdroid.download.Mirror> getMirrors() {
        List<org.fdroid.download.Mirror> allMirrors = getAllMirrors(true);
        ArrayList arrayList = new ArrayList();
        for (Object obj : allMirrors) {
            if (!getDisabledMirrors().contains(((org.fdroid.download.Mirror) obj).getBaseUrl())) {
                arrayList.add(obj);
            }
        }
        if (!arrayList.isEmpty()) {
            return arrayList;
        }
        return CollectionsKt.listOf(new org.fdroid.download.Mirror(getAddress(), null, false, 6, null));
    }

    public final List<org.fdroid.download.Mirror> getAllUserMirrors() {
        List<String> userMirrors = getUserMirrors();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(userMirrors, 10));
        Iterator<T> it = userMirrors.iterator();
        while (it.hasNext()) {
            arrayList.add(new org.fdroid.download.Mirror((String) it.next(), null, false, 6, null));
        }
        return arrayList;
    }

    public final List<org.fdroid.download.Mirror> getAllOfficialMirrors() {
        return getAllMirrors(false);
    }

    public static /* synthetic */ List getAllMirrors$default(Repository repository, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return repository.getAllMirrors(z);
    }

    public final List<org.fdroid.download.Mirror> getAllMirrors(boolean includeUserMirrors) {
        Collection collectionEmptyList;
        Object next;
        List<Mirror> list = this.mirrors;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((Mirror) it.next()).toDownloadMirror());
        }
        if (includeUserMirrors) {
            List<String> userMirrors = getUserMirrors();
            collectionEmptyList = new ArrayList(CollectionsKt.collectionSizeOrDefault(userMirrors, 10));
            Iterator<T> it2 = userMirrors.iterator();
            while (it2.hasNext()) {
                collectionEmptyList.add(new org.fdroid.download.Mirror((String) it2.next(), null, false, 6, null));
            }
        } else {
            collectionEmptyList = CollectionsKt.emptyList();
        }
        List<org.fdroid.download.Mirror> listPlus = CollectionsKt.plus((Collection) arrayList, (Iterable) collectionEmptyList);
        Iterator<T> it3 = listPlus.iterator();
        while (true) {
            if (!it3.hasNext()) {
                next = null;
                break;
            }
            next = it3.next();
            if (Intrinsics.areEqual(((org.fdroid.download.Mirror) next).getBaseUrl(), getAddress())) {
                break;
            }
        }
        if (next != null) {
            return listPlus;
        }
        List<org.fdroid.download.Mirror> mutableList = CollectionsKt.toMutableList((Collection) listPlus);
        mutableList.add(0, new org.fdroid.download.Mirror(getAddress(), null, false, 6, null));
        return mutableList;
    }

    public final String getShareUri() {
        Uri uriBuild = Uri.parse(getAddress());
        String fingerprint = getFingerprint();
        if (fingerprint != null) {
            try {
                uriBuild = uriBuild.buildUpon().appendQueryParameter(BonjourPeer.FINGERPRINT, fingerprint).build();
                Unit unit = Unit.INSTANCE;
            } catch (UnsupportedOperationException e) {
                Log.e("Repository", "Failed to append fingerprint to URI: " + e);
            }
        }
        String string = uriBuild.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }
}
