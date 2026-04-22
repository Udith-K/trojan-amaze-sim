package org.fdroid.database;

import android.content.pm.PackageManager;
import android.content.res.Resources;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import androidx.lifecycle.LiveData;
import java.util.List;
import kotlin.Metadata;
import org.fdroid.index.v2.MetadataV2;

/* JADX INFO: compiled from: AppDao.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000e2\u0006\u0010\u0006\u001a\u00020\u0007H&J\u001a\u0010\r\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u00112\u0006\u0010\u0006\u001a\u00020\u0007H&J\u001e\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00110\u000e2\b\b\u0002\u0010\u0014\u001a\u00020\u0015H&J&\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00110\u000e2\u0006\u0010\u0016\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u0015H&J.\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00110\u000e2\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001c\u001a\u00020\u001dH&J6\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00110\u000e2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u00072\b\u0010\u001b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001c\u001a\u00020\u001dH&J6\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00110\u000e2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u001b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001c\u001a\u00020\u001dH&J\u001c\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00110\u000e2\u0006\u0010\u0019\u001a\u00020\u001aH&J\u0010\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0007H&J\u0010\u0010 \u001a\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006!"}, d2 = {"Lorg/fdroid/database/AppDao;", "", "insert", "", "repoId", "", "packageName", "", "app", "Lorg/fdroid/index/v2/MetadataV2;", "locales", "Landroidx/core/os/LocaleListCompat;", "updateCompatibility", "getApp", "Landroidx/lifecycle/LiveData;", "Lorg/fdroid/database/App;", "getRepositoryIdsForApp", "", "getAppOverviewItems", "Lorg/fdroid/database/AppOverviewItem;", "limit", "", "category", "getAppListItems", "Lorg/fdroid/database/AppListItem;", "packageManager", "Landroid/content/pm/PackageManager;", "searchQuery", "sortOrder", "Lorg/fdroid/database/AppListSortOrder;", "getInstalledAppListItems", "getNumberOfAppsInCategory", "getNumberOfAppsInRepository", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface AppDao {
    LiveData getApp(String packageName);

    App getApp(long repoId, String packageName);

    LiveData getAppListItems(PackageManager packageManager, long repoId, String searchQuery, AppListSortOrder sortOrder);

    LiveData getAppListItems(PackageManager packageManager, String category, String searchQuery, AppListSortOrder sortOrder);

    LiveData getAppListItems(PackageManager packageManager, String searchQuery, AppListSortOrder sortOrder);

    LiveData getAppOverviewItems(int limit);

    LiveData getAppOverviewItems(String category, int limit);

    LiveData getInstalledAppListItems(PackageManager packageManager);

    int getNumberOfAppsInCategory(String category);

    int getNumberOfAppsInRepository(long repoId);

    List<Long> getRepositoryIdsForApp(String packageName);

    void insert(long repoId, String packageName, MetadataV2 app, LocaleListCompat locales);

    void updateCompatibility(long repoId);

    /* JADX INFO: compiled from: AppDao.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void insert$default(AppDao appDao, long j, String str, MetadataV2 metadataV2, LocaleListCompat localeListCompat, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: insert");
            }
            if ((i & 8) != 0) {
                localeListCompat = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
            }
            appDao.insert(j, str, metadataV2, localeListCompat);
        }

        public static /* synthetic */ LiveData getAppOverviewItems$default(AppDao appDao, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getAppOverviewItems");
            }
            if ((i2 & 1) != 0) {
                i = 200;
            }
            return appDao.getAppOverviewItems(i);
        }

        public static /* synthetic */ LiveData getAppOverviewItems$default(AppDao appDao, String str, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getAppOverviewItems");
            }
            if ((i2 & 2) != 0) {
                i = 50;
            }
            return appDao.getAppOverviewItems(str, i);
        }
    }
}
