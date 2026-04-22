package org.fdroid.database;

import androidx.core.os.LocaleListCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.i18n.ErrorBundle;
import org.fdroid.LocaleChooser;
import org.fdroid.fdroid.nearby.peers.BonjourPeer;
import org.fdroid.index.v2.FileV2;
import org.fdroid.index.v2.Screenshots;

/* JADX INFO: compiled from: App.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B5\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005¢\u0006\u0004\b\t\u0010\nJ$\u0010%\u001a\u0016\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016j\u0004\u0018\u0001`\u00182\u0006\u0010&\u001a\u00020\u0012H\u0002J*\u0010'\u001a\u001c\u0012\u0004\u0012\u00020\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u0005\u0018\u00010\u0016j\u0004\u0018\u0001`(2\u0006\u0010&\u001a\u00020\u0012H\u0002J\u0010\u0010-\u001a\u0004\u0018\u00010\u00122\u0006\u0010.\u001a\u00020/J\u0010\u00100\u001a\u0004\u0018\u00010\u00122\u0006\u0010.\u001a\u00020/J\u0012\u00101\u001a\u0004\u0018\u00010\u00172\u0006\u0010.\u001a\u00020/H\u0016J\u0010\u00102\u001a\u0004\u0018\u00010\u00172\u0006\u0010.\u001a\u00020/J\u0010\u00103\u001a\u0004\u0018\u00010\u00172\u0006\u0010.\u001a\u00020/J\u0010\u00104\u001a\u0004\u0018\u00010\u00172\u0006\u0010.\u001a\u00020/J\u0014\u00105\u001a\b\u0012\u0004\u0012\u00020\u00170\u00052\u0006\u0010.\u001a\u00020/J\u0014\u00106\u001a\b\u0012\u0004\u0012\u00020\u00170\u00052\u0006\u0010.\u001a\u00020/J\u0014\u00107\u001a\b\u0012\u0004\u0012\u00020\u00170\u00052\u0006\u0010.\u001a\u00020/J\u0014\u00108\u001a\b\u0012\u0004\u0012\u00020\u00170\u00052\u0006\u0010.\u001a\u00020/J\u0014\u00109\u001a\b\u0012\u0004\u0012\u00020\u00170\u00052\u0006\u0010.\u001a\u00020/J\t\u0010:\u001a\u00020\u0003HÆ\u0003J\u0011\u0010;\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÂ\u0003J\u0011\u0010<\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005HÂ\u0003J7\u0010=\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005HÆ\u0001J\u0013\u0010>\u001a\u00020?2\b\u0010@\u001a\u0004\u0018\u00010AHÖ\u0003J\t\u0010B\u001a\u00020CHÖ\u0001J\t\u0010D\u001a\u00020\u0012HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00058\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00058\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R(\u0010\u0015\u001a\u0016\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016j\u0004\u0018\u0001`\u00188@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR(\u0010\u001b\u001a\u0016\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016j\u0004\u0018\u0001`\u00188@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001aR(\u0010\u001d\u001a\u0016\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016j\u0004\u0018\u0001`\u00188@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001aR(\u0010\u001f\u001a\u0016\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016j\u0004\u0018\u0001`\u00188@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u001aR\u0016\u0010!\u001a\u0004\u0018\u00010\"8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0016\u0010)\u001a\u0004\u0018\u00010\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010\u0014R\u0016\u0010+\u001a\u0004\u0018\u00010\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b,\u0010\u0014¨\u0006E"}, d2 = {"Lorg/fdroid/database/App;", "Lorg/fdroid/database/MinimalApp;", "metadata", "Lorg/fdroid/database/AppMetadata;", "localizedFiles", "", "Lorg/fdroid/database/LocalizedFile;", "localizedFileLists", "Lorg/fdroid/database/LocalizedFileList;", "<init>", "(Lorg/fdroid/database/AppMetadata;Ljava/util/List;Ljava/util/List;)V", "getMetadata", "()Lorg/fdroid/database/AppMetadata;", "repoId", "", "getRepoId", "()J", "packageName", "", "getPackageName", "()Ljava/lang/String;", "icon", "", "Lorg/fdroid/index/v2/FileV2;", "Lorg/fdroid/index/v2/LocalizedFileV2;", "getIcon$database_release", "()Ljava/util/Map;", "featureGraphic", "getFeatureGraphic$database_release", "promoGraphic", "getPromoGraphic$database_release", "tvBanner", "getTvBanner$database_release", "screenshots", "Lorg/fdroid/index/v2/Screenshots;", "getScreenshots$database_release", "()Lorg/fdroid/index/v2/Screenshots;", "getLocalizedFile", BonjourPeer.TYPE, "getLocalizedFileList", "Lorg/fdroid/index/v2/LocalizedFileListV2;", "name", "getName", ErrorBundle.SUMMARY_ENTRY, "getSummary", "getDescription", "localeList", "Landroidx/core/os/LocaleListCompat;", "getVideo", "getIcon", "getFeatureGraphic", "getPromoGraphic", "getTvBanner", "getPhoneScreenshots", "getSevenInchScreenshots", "getTenInchScreenshots", "getTvScreenshots", "getWearScreenshots", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class App implements MinimalApp {
    private final List<LocalizedFileList> localizedFileLists;
    private final List<LocalizedFile> localizedFiles;
    private final AppMetadata metadata;

    private final List<LocalizedFile> component2() {
        return this.localizedFiles;
    }

    private final List<LocalizedFileList> component3() {
        return this.localizedFileLists;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ App copy$default(App app, AppMetadata appMetadata, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            appMetadata = app.metadata;
        }
        if ((i & 2) != 0) {
            list = app.localizedFiles;
        }
        if ((i & 4) != 0) {
            list2 = app.localizedFileLists;
        }
        return app.copy(appMetadata, list, list2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final AppMetadata getMetadata() {
        return this.metadata;
    }

    public final App copy(AppMetadata metadata, List<LocalizedFile> localizedFiles, List<LocalizedFileList> localizedFileLists) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        return new App(metadata, localizedFiles, localizedFileLists);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof App)) {
            return false;
        }
        App app = (App) other;
        return Intrinsics.areEqual(this.metadata, app.metadata) && Intrinsics.areEqual(this.localizedFiles, app.localizedFiles) && Intrinsics.areEqual(this.localizedFileLists, app.localizedFileLists);
    }

    public int hashCode() {
        int iHashCode = this.metadata.hashCode() * 31;
        List<LocalizedFile> list = this.localizedFiles;
        int iHashCode2 = (iHashCode + (list == null ? 0 : list.hashCode())) * 31;
        List<LocalizedFileList> list2 = this.localizedFileLists;
        return iHashCode2 + (list2 != null ? list2.hashCode() : 0);
    }

    public String toString() {
        return "App(metadata=" + this.metadata + ", localizedFiles=" + this.localizedFiles + ", localizedFileLists=" + this.localizedFileLists + ")";
    }

    public App(AppMetadata metadata, List<LocalizedFile> list, List<LocalizedFileList> list2) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        this.metadata = metadata;
        this.localizedFiles = list;
        this.localizedFileLists = list2;
    }

    public /* synthetic */ App(AppMetadata appMetadata, List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(appMetadata, (i & 2) != 0 ? null : list, (i & 4) != 0 ? null : list2);
    }

    public final AppMetadata getMetadata() {
        return this.metadata;
    }

    @Override // org.fdroid.database.MinimalApp
    public long getRepoId() {
        return this.metadata.getRepoId();
    }

    @Override // org.fdroid.database.MinimalApp
    public String getPackageName() {
        return this.metadata.getPackageName();
    }

    public final Map<String, FileV2> getIcon$database_release() {
        return getLocalizedFile("icon");
    }

    public final Map<String, FileV2> getFeatureGraphic$database_release() {
        return getLocalizedFile("featureGraphic");
    }

    public final Map<String, FileV2> getPromoGraphic$database_release() {
        return getLocalizedFile("promoGraphic");
    }

    public final Map<String, FileV2> getTvBanner$database_release() {
        return getLocalizedFile("tvBanner");
    }

    public final Screenshots getScreenshots$database_release() {
        List<LocalizedFileList> list = this.localizedFileLists;
        if (list == null || list.isEmpty()) {
            return null;
        }
        Screenshots screenshots = new Screenshots(getLocalizedFileList("phone"), getLocalizedFileList("sevenInch"), getLocalizedFileList("tenInch"), getLocalizedFileList("wear"), getLocalizedFileList("tv"));
        if (screenshots.isNull()) {
            return null;
        }
        return screenshots;
    }

    private final Map<String, FileV2> getLocalizedFile(String type) {
        List<LocalizedFile> list = this.localizedFiles;
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            LocalizedFile localizedFile = (LocalizedFile) obj;
            if (localizedFile.getRepoId() == this.metadata.getRepoId() && Intrinsics.areEqual(localizedFile.getType(), type)) {
                arrayList.add(obj);
            }
        }
        return AppKt.toLocalizedFileV2(arrayList);
    }

    private final Map<String, List<FileV2>> getLocalizedFileList(String type) {
        HashMap map = new HashMap();
        List<LocalizedFileList> list = this.localizedFileLists;
        if (list != null && (r1 = list.iterator()) != null) {
            for (LocalizedFileList localizedFileList : list) {
                if (localizedFileList.getRepoId() == this.metadata.getRepoId() && Intrinsics.areEqual(localizedFileList.getType(), type)) {
                    String locale = localizedFileList.getLocale();
                    Object arrayList = map.get(locale);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                        map.put(locale, arrayList);
                    }
                    ((ArrayList) arrayList).add(new FileV2(localizedFileList.getName(), localizedFileList.getSha256(), localizedFileList.getSize(), localizedFileList.getIpfsCidV1()));
                }
            }
        }
        if (map.isEmpty()) {
            return null;
        }
        return map;
    }

    @Override // org.fdroid.database.MinimalApp
    public String getName() {
        return this.metadata.getLocalizedName();
    }

    @Override // org.fdroid.database.MinimalApp
    public String getSummary() {
        return this.metadata.getLocalizedSummary();
    }

    public final String getDescription(LocaleListCompat localeList) {
        Intrinsics.checkNotNullParameter(localeList, "localeList");
        return (String) LocaleChooser.INSTANCE.getBestLocale(this.metadata.getDescription(), localeList);
    }

    public final String getVideo(LocaleListCompat localeList) {
        Intrinsics.checkNotNullParameter(localeList, "localeList");
        return (String) LocaleChooser.INSTANCE.getBestLocale(this.metadata.getVideo(), localeList);
    }

    @Override // org.fdroid.database.MinimalApp
    public FileV2 getIcon(LocaleListCompat localeList) {
        Intrinsics.checkNotNullParameter(localeList, "localeList");
        return (FileV2) LocaleChooser.INSTANCE.getBestLocale(getIcon$database_release(), localeList);
    }

    public final FileV2 getFeatureGraphic(LocaleListCompat localeList) {
        Intrinsics.checkNotNullParameter(localeList, "localeList");
        return (FileV2) LocaleChooser.INSTANCE.getBestLocale(getFeatureGraphic$database_release(), localeList);
    }

    public final FileV2 getPromoGraphic(LocaleListCompat localeList) {
        Intrinsics.checkNotNullParameter(localeList, "localeList");
        return (FileV2) LocaleChooser.INSTANCE.getBestLocale(getPromoGraphic$database_release(), localeList);
    }

    public final FileV2 getTvBanner(LocaleListCompat localeList) {
        Intrinsics.checkNotNullParameter(localeList, "localeList");
        return (FileV2) LocaleChooser.INSTANCE.getBestLocale(getTvBanner$database_release(), localeList);
    }

    public final List<FileV2> getPhoneScreenshots(LocaleListCompat localeList) {
        Intrinsics.checkNotNullParameter(localeList, "localeList");
        LocaleChooser localeChooser = LocaleChooser.INSTANCE;
        Screenshots screenshots$database_release = getScreenshots$database_release();
        List<FileV2> list = (List) localeChooser.getBestLocale(screenshots$database_release != null ? screenshots$database_release.getPhone() : null, localeList);
        return list == null ? CollectionsKt.emptyList() : list;
    }

    public final List<FileV2> getSevenInchScreenshots(LocaleListCompat localeList) {
        Intrinsics.checkNotNullParameter(localeList, "localeList");
        LocaleChooser localeChooser = LocaleChooser.INSTANCE;
        Screenshots screenshots$database_release = getScreenshots$database_release();
        List<FileV2> list = (List) localeChooser.getBestLocale(screenshots$database_release != null ? screenshots$database_release.getSevenInch() : null, localeList);
        return list == null ? CollectionsKt.emptyList() : list;
    }

    public final List<FileV2> getTenInchScreenshots(LocaleListCompat localeList) {
        Intrinsics.checkNotNullParameter(localeList, "localeList");
        LocaleChooser localeChooser = LocaleChooser.INSTANCE;
        Screenshots screenshots$database_release = getScreenshots$database_release();
        List<FileV2> list = (List) localeChooser.getBestLocale(screenshots$database_release != null ? screenshots$database_release.getTenInch() : null, localeList);
        return list == null ? CollectionsKt.emptyList() : list;
    }

    public final List<FileV2> getTvScreenshots(LocaleListCompat localeList) {
        Intrinsics.checkNotNullParameter(localeList, "localeList");
        LocaleChooser localeChooser = LocaleChooser.INSTANCE;
        Screenshots screenshots$database_release = getScreenshots$database_release();
        List<FileV2> list = (List) localeChooser.getBestLocale(screenshots$database_release != null ? screenshots$database_release.getTv() : null, localeList);
        return list == null ? CollectionsKt.emptyList() : list;
    }

    public final List<FileV2> getWearScreenshots(LocaleListCompat localeList) {
        Intrinsics.checkNotNullParameter(localeList, "localeList");
        LocaleChooser localeChooser = LocaleChooser.INSTANCE;
        Screenshots screenshots$database_release = getScreenshots$database_release();
        List<FileV2> list = (List) localeChooser.getBestLocale(screenshots$database_release != null ? screenshots$database_release.getWear() : null, localeList);
        return list == null ? CollectionsKt.emptyList() : list;
    }
}
