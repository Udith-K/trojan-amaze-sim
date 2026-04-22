package org.fdroid.database;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import androidx.compose.animation.ChangeSize$$ExternalSyntheticBackport0;
import androidx.core.os.LocaleListCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.i18n.ErrorBundle;
import org.fdroid.LocaleChooser;
import org.fdroid.fdroid.views.apps.AppListActivity;
import org.fdroid.index.v2.FileV2;

/* JADX INFO: compiled from: App.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u007f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\u0012\u0010+\u001a\u0004\u0018\u00010,2\u0006\u0010-\u001a\u00020.H\u0016J\u0018\u00101\u001a\u0004\u0018\u00010\u00052\u0006\u00102\u001a\u00020\u00052\u0006\u0010-\u001a\u00020.J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0005HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u00107\u001a\u00020\u0003HÆ\u0003J\u0010\u00108\u001a\u0004\u0018\u00010\u0005HÀ\u0003¢\u0006\u0002\b9J\u0016\u0010:\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bHÀ\u0003¢\u0006\u0002\b;J\t\u0010<\u001a\u00020\u000eHÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010?\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\"J\u0090\u0001\u0010@\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010AJ\u0013\u0010B\u001a\u00020\u000e2\b\u0010C\u001a\u0004\u0018\u00010DHÖ\u0003J\t\u0010E\u001a\u00020FHÖ\u0001J\t\u0010G\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0016X\u0097\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u0016\u0010\t\u001a\u0004\u0018\u00010\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u001e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b8\u0000X\u0081\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u001eR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017R\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u00058G¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0017R\u0017\u0010\u0011\u001a\u0004\u0018\u00010\u00038G¢\u0006\n\n\u0002\u0010#\u001a\u0004\b!\u0010\"R9\u0010$\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050%j\u0002`&\u0018\u00010%8BX\u0083\u0084\u0002¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b'\u0010(R\u0017\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b8F¢\u0006\u0006\u001a\u0004\b0\u0010\u001d¨\u0006H"}, d2 = {"Lorg/fdroid/database/AppListItem;", "Lorg/fdroid/database/MinimalApp;", "repoId", "", "packageName", "", "name", ErrorBundle.SUMMARY_ENTRY, AppListActivity.SortClause.LAST_UPDATED, "antiFeatures", "localizedIcon", "", "Lorg/fdroid/database/LocalizedIcon;", "isCompatible", "", "preferredSigner", "installedVersionName", "installedVersionCode", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/util/List;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/Long;)V", "getRepoId", "()J", "getPackageName", "()Ljava/lang/String;", "getName", "getSummary", "getLastUpdated", "getAntiFeatures$database_release", "getLocalizedIcon$database_release", "()Ljava/util/List;", "()Z", "getPreferredSigner", "getInstalledVersionName", "getInstalledVersionCode", "()Ljava/lang/Long;", "Ljava/lang/Long;", "antiFeaturesDecoded", "", "Lorg/fdroid/index/v2/LocalizedTextV2;", "getAntiFeaturesDecoded", "()Ljava/util/Map;", "antiFeaturesDecoded$delegate", "Lkotlin/Lazy;", "getIcon", "Lorg/fdroid/index/v2/FileV2;", "localeList", "Landroidx/core/os/LocaleListCompat;", "antiFeatureKeys", "getAntiFeatureKeys", "getAntiFeatureReason", "antiFeatureKey", "component1", "component2", "component3", "component4", "component5", "component6", "component6$database_release", "component7", "component7$database_release", "component8", "component9", "component10", "component11", "copy", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/util/List;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/Long;)Lorg/fdroid/database/AppListItem;", "equals", "other", "", "hashCode", "", "toString", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class AppListItem implements MinimalApp {
    private final String antiFeatures;

    /* JADX INFO: renamed from: antiFeaturesDecoded$delegate, reason: from kotlin metadata */
    private final Lazy antiFeaturesDecoded;
    private final Long installedVersionCode;
    private final String installedVersionName;
    private final boolean isCompatible;
    private final long lastUpdated;
    private final List<LocalizedIcon> localizedIcon;
    private final String name;
    private final String packageName;
    private final String preferredSigner;
    private final long repoId;
    private final String summary;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getRepoId() {
        return this.repoId;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getInstalledVersionName() {
        return this.installedVersionName;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final Long getInstalledVersionCode() {
        return this.installedVersionCode;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPackageName() {
        return this.packageName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getSummary() {
        return this.summary;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getLastUpdated() {
        return this.lastUpdated;
    }

    /* JADX INFO: renamed from: component6$database_release, reason: from getter */
    public final String getAntiFeatures() {
        return this.antiFeatures;
    }

    public final List<LocalizedIcon> component7$database_release() {
        return this.localizedIcon;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final boolean getIsCompatible() {
        return this.isCompatible;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getPreferredSigner() {
        return this.preferredSigner;
    }

    public final AppListItem copy(long repoId, String packageName, String name, String summary, long lastUpdated, String antiFeatures, List<LocalizedIcon> localizedIcon, boolean isCompatible, String preferredSigner, String installedVersionName, Long installedVersionCode) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return new AppListItem(repoId, packageName, name, summary, lastUpdated, antiFeatures, localizedIcon, isCompatible, preferredSigner, installedVersionName, installedVersionCode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AppListItem)) {
            return false;
        }
        AppListItem appListItem = (AppListItem) other;
        return this.repoId == appListItem.repoId && Intrinsics.areEqual(this.packageName, appListItem.packageName) && Intrinsics.areEqual(this.name, appListItem.name) && Intrinsics.areEqual(this.summary, appListItem.summary) && this.lastUpdated == appListItem.lastUpdated && Intrinsics.areEqual(this.antiFeatures, appListItem.antiFeatures) && Intrinsics.areEqual(this.localizedIcon, appListItem.localizedIcon) && this.isCompatible == appListItem.isCompatible && Intrinsics.areEqual(this.preferredSigner, appListItem.preferredSigner) && Intrinsics.areEqual(this.installedVersionName, appListItem.installedVersionName) && Intrinsics.areEqual(this.installedVersionCode, appListItem.installedVersionCode);
    }

    public int hashCode() {
        int iM = ((LongObjectMap$$ExternalSyntheticBackport0.m(this.repoId) * 31) + this.packageName.hashCode()) * 31;
        String str = this.name;
        int iHashCode = (iM + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.summary;
        int iHashCode2 = (((iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.lastUpdated)) * 31;
        String str3 = this.antiFeatures;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        List<LocalizedIcon> list = this.localizedIcon;
        int iHashCode4 = (((iHashCode3 + (list == null ? 0 : list.hashCode())) * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.isCompatible)) * 31;
        String str4 = this.preferredSigner;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.installedVersionName;
        int iHashCode6 = (iHashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        Long l = this.installedVersionCode;
        return iHashCode6 + (l != null ? l.hashCode() : 0);
    }

    public String toString() {
        return "AppListItem(repoId=" + this.repoId + ", packageName=" + this.packageName + ", name=" + this.name + ", summary=" + this.summary + ", lastUpdated=" + this.lastUpdated + ", antiFeatures=" + this.antiFeatures + ", localizedIcon=" + this.localizedIcon + ", isCompatible=" + this.isCompatible + ", preferredSigner=" + this.preferredSigner + ", installedVersionName=" + this.installedVersionName + ", installedVersionCode=" + this.installedVersionCode + ")";
    }

    public AppListItem(long j, String packageName, String str, String str2, long j2, String str3, List<LocalizedIcon> list, boolean z, String str4, String str5, Long l) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.repoId = j;
        this.packageName = packageName;
        this.name = str;
        this.summary = str2;
        this.lastUpdated = j2;
        this.antiFeatures = str3;
        this.localizedIcon = list;
        this.isCompatible = z;
        this.preferredSigner = str4;
        this.installedVersionName = str5;
        this.installedVersionCode = l;
        this.antiFeaturesDecoded = LazyKt.lazy(new Function0() { // from class: org.fdroid.database.AppListItem$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AppListItem.antiFeaturesDecoded_delegate$lambda$0(this.f$0);
            }
        });
    }

    public /* synthetic */ AppListItem(long j, String str, String str2, String str3, long j2, String str4, List list, boolean z, String str5, String str6, Long l, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, j2, str4, list, z, (i & 256) != 0 ? null : str5, (i & 512) != 0 ? null : str6, (i & 1024) != 0 ? null : l);
    }

    @Override // org.fdroid.database.MinimalApp
    public long getRepoId() {
        return this.repoId;
    }

    @Override // org.fdroid.database.MinimalApp
    public String getPackageName() {
        return this.packageName;
    }

    @Override // org.fdroid.database.MinimalApp
    public String getName() {
        return this.name;
    }

    @Override // org.fdroid.database.MinimalApp
    public String getSummary() {
        return this.summary;
    }

    public final long getLastUpdated() {
        return this.lastUpdated;
    }

    public final String getAntiFeatures$database_release() {
        return this.antiFeatures;
    }

    public final List<LocalizedIcon> getLocalizedIcon$database_release() {
        return this.localizedIcon;
    }

    public final boolean isCompatible() {
        return this.isCompatible;
    }

    public final String getPreferredSigner() {
        return this.preferredSigner;
    }

    public final String getInstalledVersionName() {
        return this.installedVersionName;
    }

    public final Long getInstalledVersionCode() {
        return this.installedVersionCode;
    }

    private final Map<String, Map<String, String>> getAntiFeaturesDecoded() {
        return (Map) this.antiFeaturesDecoded.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Map antiFeaturesDecoded_delegate$lambda$0(AppListItem appListItem) {
        return Converters.INSTANCE.fromStringToMapOfLocalizedTextV2(appListItem.antiFeatures);
    }

    @Override // org.fdroid.database.MinimalApp
    public FileV2 getIcon(LocaleListCompat localeList) {
        Map<String, FileV2> localizedFileV2;
        Intrinsics.checkNotNullParameter(localeList, "localeList");
        LocaleChooser localeChooser = LocaleChooser.INSTANCE;
        List<LocalizedIcon> list = this.localizedIcon;
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                if (((LocalizedIcon) obj).getRepoId() == getRepoId()) {
                    arrayList.add(obj);
                }
            }
            localizedFileV2 = AppKt.toLocalizedFileV2(arrayList);
        } else {
            localizedFileV2 = null;
        }
        return (FileV2) localeChooser.getBestLocale(localizedFileV2, localeList);
    }

    public final List<String> getAntiFeatureKeys() {
        Map<String, Map<String, String>> antiFeaturesDecoded = getAntiFeaturesDecoded();
        if (antiFeaturesDecoded != null) {
            ArrayList arrayList = new ArrayList(antiFeaturesDecoded.size());
            Iterator<Map.Entry<String, Map<String, String>>> it = antiFeaturesDecoded.entrySet().iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getKey());
            }
            return arrayList;
        }
        return CollectionsKt.emptyList();
    }

    public final String getAntiFeatureReason(String antiFeatureKey, LocaleListCompat localeList) {
        Map<String, String> map;
        Intrinsics.checkNotNullParameter(antiFeatureKey, "antiFeatureKey");
        Intrinsics.checkNotNullParameter(localeList, "localeList");
        Map<String, Map<String, String>> antiFeaturesDecoded = getAntiFeaturesDecoded();
        if (antiFeaturesDecoded == null || (map = antiFeaturesDecoded.get(antiFeatureKey)) == null) {
            return null;
        }
        return (String) LocaleChooser.INSTANCE.getBestLocale(map, localeList);
    }
}
