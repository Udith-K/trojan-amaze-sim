package org.fdroid.database;

import android.annotation.SuppressLint;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.core.content.pm.PackageInfoCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.CompatibilityChecker;
import org.fdroid.CompatibilityCheckerImpl;
import org.fdroid.UpdateChecker;

/* JADX INFO: compiled from: DbUpdateChecker.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B#\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ4\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00132\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u0018H\u0007J:\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u00162\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00162\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00132\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0007JN\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u00132\u0006\u0010\u001c\u001a\u00020\u00162\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00162\u000e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00132\b\b\u0002\u0010\u0019\u001a\u00020\u0018H\u0002J\"\u0010#\u001a\u0004\u0018\u00010\u00142\u0006\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u0018H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lorg/fdroid/database/DbUpdateChecker;", "", "db", "Lorg/fdroid/database/FDroidDatabase;", "packageManager", "Landroid/content/pm/PackageManager;", "compatibilityChecker", "Lorg/fdroid/CompatibilityChecker;", "<init>", "(Lorg/fdroid/database/FDroidDatabase;Landroid/content/pm/PackageManager;Lorg/fdroid/CompatibilityChecker;)V", "appDao", "Lorg/fdroid/database/AppDaoInt;", "versionDao", "Lorg/fdroid/database/VersionDaoInt;", "appPrefsDao", "Lorg/fdroid/database/AppPrefsDaoInt;", "updateChecker", "Lorg/fdroid/UpdateChecker;", "getUpdatableApps", "", "Lorg/fdroid/database/UpdatableApp;", "releaseChannels", "", "onlyFromPreferredRepo", "", "includeKnownVulnerabilities", "getSuggestedVersion", "Lorg/fdroid/database/AppVersion;", "packageName", "preferredSigner", "getVersion", "Lorg/fdroid/database/Version;", "versions", "packageInfo", "Landroid/content/pm/PackageInfo;", "getUpdatableApp", "version", "installedVersionCode", "", "isFromPreferredRepo", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class DbUpdateChecker {
    private final AppDaoInt appDao;
    private final AppPrefsDaoInt appPrefsDao;
    private final PackageManager packageManager;
    private final UpdateChecker updateChecker;
    private final VersionDaoInt versionDao;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DbUpdateChecker(FDroidDatabase db, PackageManager packageManager) {
        this(db, packageManager, null, 4, null);
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(packageManager, "packageManager");
    }

    public final List<UpdatableApp> getUpdatableApps() {
        return getUpdatableApps$default(this, null, false, false, 7, null);
    }

    public final List<UpdatableApp> getUpdatableApps(List<String> list) {
        return getUpdatableApps$default(this, list, false, false, 6, null);
    }

    public final List<UpdatableApp> getUpdatableApps(List<String> list, boolean z) {
        return getUpdatableApps$default(this, list, z, false, 4, null);
    }

    public DbUpdateChecker(FDroidDatabase db, PackageManager packageManager, CompatibilityChecker compatibilityChecker) {
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(packageManager, "packageManager");
        Intrinsics.checkNotNullParameter(compatibilityChecker, "compatibilityChecker");
        this.packageManager = packageManager;
        AppDao appDao = db.getAppDao();
        Intrinsics.checkNotNull(appDao, "null cannot be cast to non-null type org.fdroid.database.AppDaoInt");
        this.appDao = (AppDaoInt) appDao;
        VersionDao versionDao = db.getVersionDao();
        Intrinsics.checkNotNull(versionDao, "null cannot be cast to non-null type org.fdroid.database.VersionDaoInt");
        this.versionDao = (VersionDaoInt) versionDao;
        AppPrefsDao appPrefsDao = db.getAppPrefsDao();
        Intrinsics.checkNotNull(appPrefsDao, "null cannot be cast to non-null type org.fdroid.database.AppPrefsDaoInt");
        this.appPrefsDao = (AppPrefsDaoInt) appPrefsDao;
        this.updateChecker = new UpdateChecker(compatibilityChecker);
    }

    public /* synthetic */ DbUpdateChecker(FDroidDatabase fDroidDatabase, PackageManager packageManager, CompatibilityChecker compatibilityChecker, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fDroidDatabase, packageManager, (i & 4) != 0 ? new CompatibilityCheckerImpl(packageManager, false, 0, null, 14, null) : compatibilityChecker);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ List getUpdatableApps$default(DbUpdateChecker dbUpdateChecker, List list, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        return dbUpdateChecker.getUpdatableApps(list, z, z2);
    }

    public final List<UpdatableApp> getUpdatableApps(List<String> releaseChannels, boolean onlyFromPreferredRepo, boolean includeKnownVulnerabilities) {
        Version version;
        ArrayList arrayList = new ArrayList();
        List<PackageInfo> installedPackages = this.packageManager.getInstalledPackages(64);
        Intrinsics.checkNotNullExpressionValue(installedPackages, "getInstalledPackages(...)");
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(installedPackages, 10));
        Iterator<T> it = installedPackages.iterator();
        while (it.hasNext()) {
            arrayList2.add(((PackageInfo) it.next()).packageName);
        }
        Map<String, Long> preferredRepos = this.appPrefsDao.getPreferredRepos(arrayList2);
        HashMap map = new HashMap(arrayList2.size());
        for (Version version2 : this.versionDao.getVersions(arrayList2)) {
            Long l = preferredRepos.get(version2.getPackageName());
            if (l == null) {
                throw new IllegalStateException(("No preferred repo for " + version2.getPackageName() + " in repo " + version2.getRepoId()).toString());
            }
            long jLongValue = l.longValue();
            if (!onlyFromPreferredRepo || jLongValue == version2.getRepoId()) {
                String packageName = version2.getPackageName();
                Object arrayList3 = map.get(packageName);
                if (arrayList3 == null) {
                    arrayList3 = new ArrayList();
                    map.put(packageName, arrayList3);
                }
                ((ArrayList) arrayList3).add(version2);
            }
        }
        for (PackageInfo packageInfo : installedPackages) {
            String packageName2 = packageInfo.packageName;
            Intrinsics.checkNotNullExpressionValue(packageName2, "packageName");
            ArrayList arrayList4 = (ArrayList) map.get(packageName2);
            if (arrayList4 != null && (version = getVersion(arrayList4, packageName2, packageInfo, null, releaseChannels, includeKnownVulnerabilities)) != null) {
                Long l2 = preferredRepos.get(packageName2);
                if (l2 == null) {
                    throw new IllegalStateException(("No preferred repo for " + packageName2).toString());
                }
                UpdatableApp updatableApp = getUpdatableApp(version, PackageInfoCompat.getLongVersionCode(packageInfo), l2.longValue() == version.getRepoId());
                if (updatableApp != null) {
                    arrayList.add(updatableApp);
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AppVersion getSuggestedVersion$default(DbUpdateChecker dbUpdateChecker, String str, String str2, List list, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            list = null;
        }
        if ((i & 8) != 0) {
            z = false;
        }
        return dbUpdateChecker.getSuggestedVersion(str, str2, list, z);
    }

    @SuppressLint({"PackageManagerGetSignatures"})
    public final AppVersion getSuggestedVersion(String packageName, String preferredSigner, List<String> releaseChannels, boolean onlyFromPreferredRepo) throws PackageManager.NameNotFoundException {
        long jLongValue;
        List versions;
        PackageInfo packageInfo;
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        if (onlyFromPreferredRepo) {
            Long l = this.appPrefsDao.getPreferredRepos(CollectionsKt.listOf(packageName)).get(packageName);
            if (l == null) {
                throw new IllegalStateException(("No preferred repo for " + packageName).toString());
            }
            jLongValue = l.longValue();
        } else {
            jLongValue = 0;
        }
        if (onlyFromPreferredRepo) {
            List<Version> versions2 = this.versionDao.getVersions(CollectionsKt.listOf(packageName));
            ArrayList arrayList = new ArrayList();
            for (Object obj : versions2) {
                if (((Version) obj).getRepoId() == jLongValue) {
                    arrayList.add(obj);
                }
            }
            versions = arrayList;
        } else {
            versions = this.versionDao.getVersions(CollectionsKt.listOf(packageName));
        }
        if (versions.isEmpty()) {
            return null;
        }
        try {
            packageInfo = this.packageManager.getPackageInfo(packageName, 64);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        Version version$default = getVersion$default(this, versions, packageName, packageInfo, preferredSigner, releaseChannels, false, 32, null);
        if (version$default == null) {
            return null;
        }
        return version$default.toAppVersion$database_release(this.versionDao.getVersionedStrings(version$default.getRepoId(), version$default.getPackageName(), version$default.getVersionId()));
    }

    static /* synthetic */ Version getVersion$default(DbUpdateChecker dbUpdateChecker, List list, String str, PackageInfo packageInfo, String str2, List list2, boolean z, int i, Object obj) {
        if ((i & 32) != 0) {
            z = false;
        }
        return dbUpdateChecker.getVersion(list, str, packageInfo, str2, list2, z);
    }

    private final Version getVersion(List<Version> versions, final String packageName, PackageInfo packageInfo, String preferredSigner, List<String> releaseChannels, boolean includeKnownVulnerabilities) {
        Function0 function0 = new Function0() { // from class: org.fdroid.database.DbUpdateChecker$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DbUpdateChecker.getVersion$lambda$5(this.f$0, packageName);
            }
        };
        if (packageInfo == null) {
            return (Version) this.updateChecker.getSuggestedVersion(versions, preferredSigner, releaseChannels, function0);
        }
        return (Version) this.updateChecker.getUpdate(versions, packageInfo, releaseChannels, includeKnownVulnerabilities, function0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AppPrefs getVersion$lambda$5(DbUpdateChecker dbUpdateChecker, String str) {
        return dbUpdateChecker.appPrefsDao.getAppPrefsOrNull(str);
    }

    private final UpdatableApp getUpdatableApp(Version version, long installedVersionCode, boolean isFromPreferredRepo) {
        List<VersionedString> versionedStrings = this.versionDao.getVersionedStrings(version.getRepoId(), version.getPackageName(), version.getVersionId());
        AppOverviewItem appOverviewItem = this.appDao.getAppOverviewItem(version.getRepoId(), version.getPackageName());
        if (appOverviewItem == null) {
            return null;
        }
        return new UpdatableApp(version.getRepoId(), version.getPackageName(), installedVersionCode, version.toAppVersion$database_release(versionedStrings), isFromPreferredRepo, version.getHasKnownVulnerability(), appOverviewItem.getName(), appOverviewItem.getSummary(), appOverviewItem.getLocalizedIcon$database_release());
    }
}
