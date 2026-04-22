package org.fdroid.database;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.core.content.pm.PackageInfoCompat;
import androidx.core.os.LocaleListCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Transformations;
import androidx.room.RoomDatabase;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KTypeProjection;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import org.bouncycastle.asn1.BERTags;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.bouncycastle.i18n.ErrorBundle;
import org.fdroid.LocaleChooser;
import org.fdroid.database.AppDao;
import org.fdroid.database.AppDaoInt;
import org.fdroid.fdroid.nearby.peers.BonjourPeer;
import org.fdroid.index.IndexParser;
import org.fdroid.index.v2.FileV2;
import org.fdroid.index.v2.MetadataV2;
import org.fdroid.index.v2.ReflectionDiffer;
import org.fdroid.index.v2.Screenshots;

/* JADX INFO: compiled from: AppDao.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\ba\u0018\u00002\u00020\u0001:\u0001JJ(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0017J8\u0010\u0002\u001a\u00020\u0003*\u0016\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\r\u0018\u00010\fj\u0004\u0018\u0001`\u000e2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0007H\u0002J>\u0010\u0002\u001a\u00020\u0003*\u001c\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u0010\u0018\u00010\fj\u0004\u0018\u0001`\u00112\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0007H\u0003J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0013H'J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0010H'J\u0016\u0010\u0016\u001a\u00020\u00032\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00170\u0010H'J*\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\n\u001a\u00020\u000bH\u0017J2\u0010\u001b\u001a\u00020\u0003*\b\u0012\u0004\u0012\u00020\u00150\u00102\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J(\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\"\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u001e\u001a\u0004\u0018\u00010\u0007H'J\u0010\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J,\u0010 \u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010!\u001a\u0004\u0018\u00010\u00072\b\u0010\"\u001a\u0004\u0018\u00010\u0007H'J\u0010\u0010 \u001a\u00020#2\u0006\u0010\u0012\u001a\u00020\u0013H'J\u0018\u0010$\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010&0%2\u0006\u0010\u0006\u001a\u00020\u0007H'J\u001a\u0010$\u001a\u0004\u0018\u00010&2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H'J\u0016\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00050\u00102\u0006\u0010\u0006\u001a\u00020\u0007H'J\u001a\u0010(\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H'J\u000e\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00130\u0010H'J\u001e\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00150\u00102\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H'J\u001c\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0\u00100%2\u0006\u0010,\u001a\u00020#H'J$\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0\u00100%2\u0006\u0010-\u001a\u00020\u00072\u0006\u0010,\u001a\u00020#H'J\u001a\u0010.\u001a\u0004\u0018\u00010+2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H'J.\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000\u00100%2\u0006\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u00010\u00072\u0006\u00104\u001a\u000205H\u0016J6\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000\u00100%2\u0006\u00101\u001a\u0002022\u0006\u0010-\u001a\u00020\u00072\b\u00103\u001a\u0004\u0018\u00010\u00072\u0006\u00104\u001a\u000205H\u0016J6\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000\u00100%2\u0006\u00101\u001a\u0002022\u0006\u0010\u0004\u001a\u00020\u00052\b\u00103\u001a\u0004\u0018\u00010\u00072\u0006\u00104\u001a\u000205H\u0016J\u0010\u00106\u001a\u00020\u00072\u0006\u00103\u001a\u00020\u0007H\u0002JB\u00107\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000\u00100%*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000\u00100%2\u0006\u00101\u001a\u0002022\u0014\b\u0002\u00108\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002090\fH\u0002J\u001c\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000\u00100%2\u0006\u00103\u001a\u00020\u0007H'J$\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000\u00100%2\u0006\u0010-\u001a\u00020\u00072\u0006\u00103\u001a\u00020\u0007H'J$\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000\u00100%2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u00103\u001a\u00020\u0007H'J\u0014\u0010:\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000\u00100%H'J\u0014\u0010;\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000\u00100%H'J\u001c\u0010;\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000\u00100%2\u0006\u0010-\u001a\u00020\u0007H'J\u001c\u0010:\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000\u00100%2\u0006\u0010-\u001a\u00020\u0007H'J\u001c\u0010;\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000\u00100%2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u001c\u0010:\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000\u00100%2\u0006\u0010\u0004\u001a\u00020\u0005H'J\"\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000\u00100%2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010H'J\u001c\u0010=\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000\u00100%2\u0006\u00101\u001a\u000202H\u0016J\u0010\u0010>\u001a\u00020#2\u0006\u0010-\u001a\u00020\u0007H'J\u0010\u0010?\u001a\u00020#2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0018\u0010@\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H'J \u0010A\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0007H'J(\u0010B\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010C\u001a\u00020\u0007H'J\u0018\u0010D\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H'J \u0010D\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0007H'J(\u0010E\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010C\u001a\u00020\u0007H'J\b\u0010F\u001a\u00020#H'J\b\u0010G\u001a\u00020#H'J\b\u0010H\u001a\u00020#H'J\b\u0010I\u001a\u00020\u0003H'¨\u0006K"}, d2 = {"Lorg/fdroid/database/AppDaoInt;", "Lorg/fdroid/database/AppDao;", "insert", "", "repoId", "", "packageName", "", "app", "Lorg/fdroid/index/v2/MetadataV2;", "locales", "Landroidx/core/os/LocaleListCompat;", "", "Lorg/fdroid/index/v2/FileV2;", "Lorg/fdroid/index/v2/LocalizedFileV2;", BonjourPeer.TYPE, "", "Lorg/fdroid/index/v2/LocalizedFileListV2;", "appMetadata", "Lorg/fdroid/database/AppMetadata;", "localizedFiles", "Lorg/fdroid/database/LocalizedFile;", "insertLocalizedFileLists", "Lorg/fdroid/database/LocalizedFileList;", "updateApp", "jsonObject", "Lkotlinx/serialization/json/JsonObject;", "diffAndUpdate", "diffAndUpdateLocalizedFileList", "updatePreferredSigner", "preferredSigner", "updateCompatibility", "updateAppMetadata", "name", ErrorBundle.SUMMARY_ENTRY, "", "getApp", "Landroidx/lifecycle/LiveData;", "Lorg/fdroid/database/App;", "getRepositoryIdsForApp", "getAppMetadata", "getLocalizedFiles", "getAppOverviewItems", "Lorg/fdroid/database/AppOverviewItem;", "limit", "category", "getAppOverviewItem", "getAppListItems", "Lorg/fdroid/database/AppListItem;", "packageManager", "Landroid/content/pm/PackageManager;", "searchQuery", "sortOrder", "Lorg/fdroid/database/AppListSortOrder;", "escapeQuery", "map", "installedPackages", "Landroid/content/pm/PackageInfo;", "getAppListItemsByName", "getAppListItemsByLastUpdated", "packageNames", "getInstalledAppListItems", "getNumberOfAppsInCategory", "getNumberOfAppsInRepository", "deleteAppMetadata", "deleteLocalizedFiles", "deleteLocalizedFile", "locale", "deleteLocalizedFileLists", "deleteLocalizedFileList", "countApps", "countLocalizedFiles", "countLocalizedFileLists", "clearAll", "AppListLiveData", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface AppDaoInt extends AppDao {

    /* JADX INFO: compiled from: AppDao.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AppListSortOrder.values().length];
            try {
                iArr[AppListSortOrder.LAST_UPDATED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[AppListSortOrder.NAME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    void clearAll();

    int countApps();

    int countLocalizedFileLists();

    int countLocalizedFiles();

    void deleteAppMetadata(long repoId, String packageName);

    void deleteLocalizedFile(long repoId, String packageName, String type, String locale);

    void deleteLocalizedFileList(long repoId, String packageName, String type, String locale);

    void deleteLocalizedFileLists(long repoId, String packageName);

    void deleteLocalizedFileLists(long repoId, String packageName, String type);

    void deleteLocalizedFiles(long repoId, String packageName, String type);

    @Override // org.fdroid.database.AppDao
    LiveData getApp(String packageName);

    @Override // org.fdroid.database.AppDao
    App getApp(long repoId, String packageName);

    LiveData getAppListItems(long repoId, String searchQuery);

    @Override // org.fdroid.database.AppDao
    LiveData getAppListItems(PackageManager packageManager, long repoId, String searchQuery, AppListSortOrder sortOrder);

    @Override // org.fdroid.database.AppDao
    LiveData getAppListItems(PackageManager packageManager, String category, String searchQuery, AppListSortOrder sortOrder);

    @Override // org.fdroid.database.AppDao
    LiveData getAppListItems(PackageManager packageManager, String searchQuery, AppListSortOrder sortOrder);

    LiveData getAppListItems(String searchQuery);

    LiveData getAppListItems(String category, String searchQuery);

    LiveData getAppListItems(List<String> packageNames);

    LiveData getAppListItemsByLastUpdated();

    LiveData getAppListItemsByLastUpdated(long repoId);

    LiveData getAppListItemsByLastUpdated(String category);

    LiveData getAppListItemsByName();

    LiveData getAppListItemsByName(long repoId);

    LiveData getAppListItemsByName(String category);

    List<AppMetadata> getAppMetadata();

    AppMetadata getAppMetadata(long repoId, String packageName);

    AppOverviewItem getAppOverviewItem(long repoId, String packageName);

    @Override // org.fdroid.database.AppDao
    LiveData getAppOverviewItems(int limit);

    @Override // org.fdroid.database.AppDao
    LiveData getAppOverviewItems(String category, int limit);

    @Override // org.fdroid.database.AppDao
    LiveData getInstalledAppListItems(PackageManager packageManager);

    List<LocalizedFile> getLocalizedFiles(long repoId, String packageName);

    @Override // org.fdroid.database.AppDao
    int getNumberOfAppsInCategory(String category);

    @Override // org.fdroid.database.AppDao
    int getNumberOfAppsInRepository(long repoId);

    @Override // org.fdroid.database.AppDao
    List<Long> getRepositoryIdsForApp(String packageName);

    @Override // org.fdroid.database.AppDao
    void insert(long repoId, String packageName, MetadataV2 app, LocaleListCompat locales);

    void insert(List<LocalizedFile> localizedFiles);

    void insert(AppMetadata appMetadata);

    void insertLocalizedFileLists(List<LocalizedFileList> localizedFiles);

    void updateApp(long repoId, String packageName, JsonObject jsonObject, LocaleListCompat locales);

    int updateAppMetadata(AppMetadata appMetadata);

    void updateAppMetadata(long repoId, String packageName, String name, String summary);

    @Override // org.fdroid.database.AppDao
    void updateCompatibility(long repoId);

    @Deprecated
    void updatePreferredSigner(long repoId, String packageName, String preferredSigner);

    /* JADX INFO: compiled from: AppDao.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        public static void insert(AppDaoInt appDaoInt, long j, String packageName, MetadataV2 app, LocaleListCompat locales) {
            Intrinsics.checkNotNullParameter(packageName, "packageName");
            Intrinsics.checkNotNullParameter(app, "app");
            Intrinsics.checkNotNullParameter(locales, "locales");
            appDaoInt.insert(AppKt.toAppMetadata(app, j, packageName, false, locales));
            insert(appDaoInt, app.getIcon(), j, packageName, "icon");
            insert(appDaoInt, app.getFeatureGraphic(), j, packageName, "featureGraphic");
            insert(appDaoInt, app.getPromoGraphic(), j, packageName, "promoGraphic");
            insert(appDaoInt, app.getTvBanner(), j, packageName, "tvBanner");
            Screenshots screenshots = app.getScreenshots();
            if (screenshots != null) {
                insertLocalizedFileListV2(appDaoInt, screenshots.getPhone(), j, packageName, "phone");
                insertLocalizedFileListV2(appDaoInt, screenshots.getSevenInch(), j, packageName, "sevenInch");
                insertLocalizedFileListV2(appDaoInt, screenshots.getTenInch(), j, packageName, "tenInch");
                insertLocalizedFileListV2(appDaoInt, screenshots.getWear(), j, packageName, "wear");
                insertLocalizedFileListV2(appDaoInt, screenshots.getTv(), j, packageName, "tv");
            }
        }

        private static void insert(AppDaoInt appDaoInt, Map<String, FileV2> map, long j, String str, String str2) {
            List<LocalizedFile> localizedFile;
            if (map == null || (localizedFile = AppKt.toLocalizedFile(map, j, str, str2)) == null) {
                return;
            }
            appDaoInt.insert(localizedFile);
        }

        private static void insertLocalizedFileListV2(AppDaoInt appDaoInt, Map<String, ? extends List<FileV2>> map, long j, String str, String str2) {
            List<LocalizedFileList> localizedFileList;
            if (map == null || (localizedFileList = AppKt.toLocalizedFileList(map, j, str, str2)) == null) {
                return;
            }
            appDaoInt.insertLocalizedFileLists(localizedFileList);
        }

        public static void updateApp(AppDaoInt appDaoInt, long j, String packageName, JsonObject jsonObject, LocaleListCompat locales) {
            Intrinsics.checkNotNullParameter(packageName, "packageName");
            Intrinsics.checkNotNullParameter(locales, "locales");
            if (jsonObject == null) {
                appDaoInt.deleteAppMetadata(j, packageName);
                return;
            }
            AppMetadata appMetadata = appDaoInt.getAppMetadata(j, packageName);
            if (appMetadata == null) {
                Json json = IndexParser.getJson();
                KSerializer kSerializerSerializer = SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(MetadataV2.class));
                Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
                AppDao.DefaultImpls.insert$default(appDaoInt, j, packageName, (MetadataV2) json.decodeFromJsonElement(kSerializerSerializer, jsonObject), null, 8, null);
                return;
            }
            for (String str : AppDaoKt.DENY_LIST) {
                if (jsonObject.containsKey((Object) str)) {
                    throw new SerializationException(str);
                }
            }
            AppMetadata appMetadataCopy = (AppMetadata) ReflectionDiffer.INSTANCE.applyDiff(appMetadata, jsonObject);
            if (jsonObject.containsKey("name") || jsonObject.containsKey(ErrorBundle.SUMMARY_ENTRY)) {
                LocaleChooser localeChooser = LocaleChooser.INSTANCE;
                appMetadataCopy = appMetadataCopy.copy((1073741439 & 1) != 0 ? appMetadataCopy.repoId : 0L, (1073741439 & 2) != 0 ? appMetadataCopy.packageName : null, (1073741439 & 4) != 0 ? appMetadataCopy.added : 0L, (1073741439 & 8) != 0 ? appMetadataCopy.lastUpdated : 0L, (1073741439 & 16) != 0 ? appMetadataCopy.name : null, (1073741439 & 32) != 0 ? appMetadataCopy.summary : null, (1073741439 & 64) != 0 ? appMetadataCopy.description : null, (1073741439 & 128) != 0 ? appMetadataCopy.localizedName : (String) localeChooser.getBestLocale(appMetadataCopy.getName(), locales), (1073741439 & 256) != 0 ? appMetadataCopy.localizedSummary : (String) localeChooser.getBestLocale(appMetadataCopy.getSummary(), locales), (1073741439 & 512) != 0 ? appMetadataCopy.webSite : null, (1073741439 & 1024) != 0 ? appMetadataCopy.changelog : null, (1073741439 & 2048) != 0 ? appMetadataCopy.license : null, (1073741439 & PKIFailureInfo.certConfirmed) != 0 ? appMetadataCopy.sourceCode : null, (1073741439 & 8192) != 0 ? appMetadataCopy.issueTracker : null, (1073741439 & 16384) != 0 ? appMetadataCopy.translation : null, (1073741439 & 32768) != 0 ? appMetadataCopy.preferredSigner : null, (1073741439 & PKIFailureInfo.notAuthorized) != 0 ? appMetadataCopy.video : null, (1073741439 & PKIFailureInfo.unsupportedVersion) != 0 ? appMetadataCopy.authorName : null, (1073741439 & PKIFailureInfo.transactionIdInUse) != 0 ? appMetadataCopy.authorEmail : null, (1073741439 & PKIFailureInfo.signerNotTrusted) != 0 ? appMetadataCopy.authorWebSite : null, (1073741439 & PKIFailureInfo.badCertTemplate) != 0 ? appMetadataCopy.authorPhone : null, (1073741439 & PKIFailureInfo.badSenderNonce) != 0 ? appMetadataCopy.donate : null, (1073741439 & 4194304) != 0 ? appMetadataCopy.liberapayID : null, (1073741439 & 8388608) != 0 ? appMetadataCopy.liberapay : null, (1073741439 & 16777216) != 0 ? appMetadataCopy.openCollective : null, (1073741439 & 33554432) != 0 ? appMetadataCopy.bitcoin : null, (1073741439 & 67108864) != 0 ? appMetadataCopy.litecoin : null, (1073741439 & 134217728) != 0 ? appMetadataCopy.flattrID : null, (1073741439 & 268435456) != 0 ? appMetadataCopy.categories : null, (1073741439 & PKIFailureInfo.duplicateCertReq) != 0 ? appMetadataCopy.isCompatible : false);
            }
            appDaoInt.updateAppMetadata(appMetadataCopy);
            List<LocalizedFile> localizedFiles = appDaoInt.getLocalizedFiles(j, packageName);
            diffAndUpdate(appDaoInt, localizedFiles, j, packageName, "icon", jsonObject);
            diffAndUpdate(appDaoInt, localizedFiles, j, packageName, "featureGraphic", jsonObject);
            diffAndUpdate(appDaoInt, localizedFiles, j, packageName, "promoGraphic", jsonObject);
            diffAndUpdate(appDaoInt, localizedFiles, j, packageName, "tvBanner", jsonObject);
            JsonElement jsonElement = (JsonElement) jsonObject.get("screenshots");
            if (jsonElement instanceof JsonNull) {
                appDaoInt.deleteLocalizedFileLists(j, packageName);
                return;
            }
            if (jsonElement instanceof JsonObject) {
                JsonObject jsonObject2 = (JsonObject) jsonElement;
                diffAndUpdateLocalizedFileList(appDaoInt, j, packageName, "phone", jsonObject2);
                diffAndUpdateLocalizedFileList(appDaoInt, j, packageName, "sevenInch", jsonObject2);
                diffAndUpdateLocalizedFileList(appDaoInt, j, packageName, "tenInch", jsonObject2);
                diffAndUpdateLocalizedFileList(appDaoInt, j, packageName, "wear", jsonObject2);
                diffAndUpdateLocalizedFileList(appDaoInt, j, packageName, "tv", jsonObject2);
            }
        }

        private static void diffAndUpdate(final AppDaoInt appDaoInt, List<LocalizedFile> list, final long j, final String str, final String str2, JsonObject jsonObject) {
            DbDiffUtils dbDiffUtils = DbDiffUtils.INSTANCE;
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                if (Intrinsics.areEqual(((LocalizedFile) obj).getType(), str2)) {
                    arrayList.add(obj);
                }
            }
            dbDiffUtils.diffAndUpdateTable(jsonObject, str2, arrayList, new Function2() { // from class: org.fdroid.database.AppDaoInt$DefaultImpls$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return Boolean.valueOf(AppDaoInt.DefaultImpls.diffAndUpdate$lambda$5((String) obj2, (LocalizedFile) obj3));
                }
            }, new Function1() { // from class: org.fdroid.database.AppDaoInt$DefaultImpls$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return AppDaoInt.DefaultImpls.diffAndUpdate$lambda$6(j, str, str2, (String) obj2);
                }
            }, new Function0() { // from class: org.fdroid.database.AppDaoInt$DefaultImpls$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AppDaoInt.DefaultImpls.diffAndUpdate$lambda$7(appDaoInt, j, str, str2);
                }
            }, new Function1() { // from class: org.fdroid.database.AppDaoInt$DefaultImpls$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return AppDaoInt.DefaultImpls.diffAndUpdate$lambda$8(appDaoInt, j, str, str2, (String) obj2);
                }
            }, new Function1() { // from class: org.fdroid.database.AppDaoInt$DefaultImpls$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return AppDaoInt.DefaultImpls.diffAndUpdate$lambda$9(appDaoInt, (List) obj2);
                }
            }, new Function1() { // from class: org.fdroid.database.AppDaoInt$DefaultImpls$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return Boolean.valueOf(AppDaoInt.DefaultImpls.diffAndUpdate$lambda$10((LocalizedFile) obj2));
                }
            }, AppDaoKt.DENY_FILE_LIST);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean diffAndUpdate$lambda$5(String locale, LocalizedFile item) {
            Intrinsics.checkNotNullParameter(locale, "locale");
            Intrinsics.checkNotNullParameter(item, "item");
            return Intrinsics.areEqual(item.getLocale(), locale);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static LocalizedFile diffAndUpdate$lambda$6(long j, String str, String str2, String locale) {
            Intrinsics.checkNotNullParameter(locale, "locale");
            return new LocalizedFile(j, str, str2, locale, "", null, null, null, BERTags.FLAGS, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Unit diffAndUpdate$lambda$7(AppDaoInt appDaoInt, long j, String str, String str2) {
            appDaoInt.deleteLocalizedFiles(j, str, str2);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Unit diffAndUpdate$lambda$8(AppDaoInt appDaoInt, long j, String str, String str2, String locale) {
            Intrinsics.checkNotNullParameter(locale, "locale");
            appDaoInt.deleteLocalizedFile(j, str, str2, locale);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Unit diffAndUpdate$lambda$9(AppDaoInt appDaoInt, List list) {
            Intrinsics.checkNotNullParameter(list, "list");
            appDaoInt.insert((List<LocalizedFile>) list);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean diffAndUpdate$lambda$10(LocalizedFile it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return it.getName().length() > 0;
        }

        private static void diffAndUpdateLocalizedFileList(final AppDaoInt appDaoInt, final long j, final String str, final String str2, JsonObject jsonObject) {
            DbDiffUtils.INSTANCE.diffAndUpdateListTable(jsonObject, str2, new Function2() { // from class: org.fdroid.database.AppDaoInt$DefaultImpls$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppDaoInt.DefaultImpls.diffAndUpdateLocalizedFileList$lambda$12(j, str, str2, (String) obj, (JsonArray) obj2);
                }
            }, new Function0() { // from class: org.fdroid.database.AppDaoInt$DefaultImpls$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AppDaoInt.DefaultImpls.diffAndUpdateLocalizedFileList$lambda$13(appDaoInt, j, str, str2);
                }
            }, new Function1() { // from class: org.fdroid.database.AppDaoInt$DefaultImpls$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppDaoInt.DefaultImpls.diffAndUpdateLocalizedFileList$lambda$14(appDaoInt, j, str, str2, (String) obj);
                }
            }, new Function2() { // from class: org.fdroid.database.AppDaoInt$DefaultImpls$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppDaoInt.DefaultImpls.diffAndUpdateLocalizedFileList$lambda$15(appDaoInt, (String) obj, (List) obj2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static List diffAndUpdateLocalizedFileList$lambda$12(long j, String str, String str2, String locale, JsonArray jsonArray) {
            Intrinsics.checkNotNullParameter(locale, "locale");
            Intrinsics.checkNotNullParameter(jsonArray, "jsonArray");
            Json json = IndexParser.getJson();
            KSerializer kSerializerSerializer = SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(List.class, KTypeProjection.Companion.invariant(Reflection.typeOf(FileV2.class))));
            Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
            Iterable iterable = (Iterable) json.decodeFromJsonElement(kSerializerSerializer, jsonArray);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                arrayList.add(AppKt.toLocalizedFileList((FileV2) it.next(), j, str, str2, locale));
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Unit diffAndUpdateLocalizedFileList$lambda$13(AppDaoInt appDaoInt, long j, String str, String str2) {
            appDaoInt.deleteLocalizedFileLists(j, str, str2);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Unit diffAndUpdateLocalizedFileList$lambda$14(AppDaoInt appDaoInt, long j, String str, String str2, String locale) {
            Intrinsics.checkNotNullParameter(locale, "locale");
            appDaoInt.deleteLocalizedFileList(j, str, str2, locale);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Unit diffAndUpdateLocalizedFileList$lambda$15(AppDaoInt appDaoInt, String str, List fileLists) {
            Intrinsics.checkNotNullParameter(str, "<unused var>");
            Intrinsics.checkNotNullParameter(fileLists, "fileLists");
            appDaoInt.insertLocalizedFileLists(fileLists);
            return Unit.INSTANCE;
        }

        public static LiveData getAppListItems(AppDaoInt appDaoInt, PackageManager packageManager, String str, AppListSortOrder sortOrder) {
            Intrinsics.checkNotNullParameter(packageManager, "packageManager");
            Intrinsics.checkNotNullParameter(sortOrder, "sortOrder");
            if (str == null || str.length() == 0) {
                int i = WhenMappings.$EnumSwitchMapping$0[sortOrder.ordinal()];
                if (i == 1) {
                    return map$default(appDaoInt, appDaoInt.getAppListItemsByLastUpdated(), packageManager, null, 2, null);
                }
                if (i != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                return map$default(appDaoInt, appDaoInt.getAppListItemsByName(), packageManager, null, 2, null);
            }
            return map$default(appDaoInt, appDaoInt.getAppListItems(escapeQuery(appDaoInt, str)), packageManager, null, 2, null);
        }

        public static LiveData getAppListItems(AppDaoInt appDaoInt, PackageManager packageManager, String category, String str, AppListSortOrder sortOrder) {
            Intrinsics.checkNotNullParameter(packageManager, "packageManager");
            Intrinsics.checkNotNullParameter(category, "category");
            Intrinsics.checkNotNullParameter(sortOrder, "sortOrder");
            if (str == null || str.length() == 0) {
                int i = WhenMappings.$EnumSwitchMapping$0[sortOrder.ordinal()];
                if (i == 1) {
                    return map$default(appDaoInt, appDaoInt.getAppListItemsByLastUpdated(category), packageManager, null, 2, null);
                }
                if (i != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                return map$default(appDaoInt, appDaoInt.getAppListItemsByName(category), packageManager, null, 2, null);
            }
            return map$default(appDaoInt, appDaoInt.getAppListItems(category, escapeQuery(appDaoInt, str)), packageManager, null, 2, null);
        }

        public static LiveData getAppListItems(AppDaoInt appDaoInt, PackageManager packageManager, long j, String str, AppListSortOrder sortOrder) {
            Intrinsics.checkNotNullParameter(packageManager, "packageManager");
            Intrinsics.checkNotNullParameter(sortOrder, "sortOrder");
            if (str == null || str.length() == 0) {
                int i = WhenMappings.$EnumSwitchMapping$0[sortOrder.ordinal()];
                if (i == 1) {
                    return map$default(appDaoInt, appDaoInt.getAppListItemsByLastUpdated(j), packageManager, null, 2, null);
                }
                if (i != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                return map$default(appDaoInt, appDaoInt.getAppListItemsByName(j), packageManager, null, 2, null);
            }
            return map$default(appDaoInt, appDaoInt.getAppListItems(j, escapeQuery(appDaoInt, str)), packageManager, null, 2, null);
        }

        private static String escapeQuery(AppDaoInt appDaoInt, String str) {
            return "\"*" + Regex.Companion.fromLiteral("\"").replace(str, "\"\"") + "*\"";
        }

        public static /* synthetic */ LiveData map$default(AppDaoInt appDaoInt, LiveData liveData, PackageManager packageManager, Map map, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: map");
            }
            if ((i & 2) != 0) {
                List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
                Intrinsics.checkNotNullExpressionValue(installedPackages, "getInstalledPackages(...)");
                LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(installedPackages, 10)), 16));
                for (Object obj2 : installedPackages) {
                    String packageName = ((PackageInfo) obj2).packageName;
                    Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
                    linkedHashMap.put(packageName, obj2);
                }
                map = linkedHashMap;
            }
            return map(appDaoInt, liveData, packageManager, map);
        }

        private static LiveData map(AppDaoInt appDaoInt, LiveData liveData, PackageManager packageManager, final Map<String, ? extends PackageInfo> map) {
            return Transformations.map(liveData, new Function1() { // from class: org.fdroid.database.AppDaoInt$DefaultImpls$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppDaoInt.DefaultImpls.map$lambda$18(map, (List) obj);
                }
            });
        }

        public static LiveData getInstalledAppListItems(final AppDaoInt appDaoInt, PackageManager packageManager) {
            Intrinsics.checkNotNullParameter(packageManager, "packageManager");
            List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
            Intrinsics.checkNotNullExpressionValue(installedPackages, "getInstalledPackages(...)");
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(installedPackages, 10)), 16));
            for (Object obj : installedPackages) {
                linkedHashMap.put(((PackageInfo) obj).packageName, obj);
            }
            List<String> list = CollectionsKt.toList(linkedHashMap.keySet());
            if (list.size() <= 999) {
                return map(appDaoInt, appDaoInt.getAppListItems(list), packageManager, linkedHashMap);
            }
            final AppListLiveData appListLiveData = new AppListLiveData();
            CollectionsKt.chunked(list, RoomDatabase.MAX_BIND_PARAMETER_CNT, new Function1() { // from class: org.fdroid.database.AppDaoInt$DefaultImpls$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return AppDaoInt.DefaultImpls.getInstalledAppListItems$lambda$21$lambda$20(appListLiveData, appDaoInt, (List) obj2);
                }
            });
            return map(appDaoInt, appListLiveData, packageManager, linkedHashMap);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Unit getInstalledAppListItems$lambda$21$lambda$20(AppListLiveData appListLiveData, AppDaoInt appDaoInt, List it) {
            Intrinsics.checkNotNullParameter(it, "it");
            appListLiveData.addSource(appDaoInt.getAppListItems((List<String>) it));
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static List map$lambda$18(Map map, List items) {
            Intrinsics.checkNotNullParameter(items, "items");
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(items, 10));
            Iterator it = items.iterator();
            while (it.hasNext()) {
                AppListItem appListItemCopy = (AppListItem) it.next();
                PackageInfo packageInfo = (PackageInfo) map.get(appListItemCopy.getPackageName());
                if (packageInfo != null) {
                    appListItemCopy = appListItemCopy.copy((511 & 1) != 0 ? appListItemCopy.repoId : 0L, (511 & 2) != 0 ? appListItemCopy.packageName : null, (511 & 4) != 0 ? appListItemCopy.name : null, (511 & 8) != 0 ? appListItemCopy.summary : null, (511 & 16) != 0 ? appListItemCopy.lastUpdated : 0L, (511 & 32) != 0 ? appListItemCopy.antiFeatures : null, (511 & 64) != 0 ? appListItemCopy.localizedIcon : null, (511 & 128) != 0 ? appListItemCopy.isCompatible : false, (511 & 256) != 0 ? appListItemCopy.preferredSigner : null, (511 & 512) != 0 ? appListItemCopy.installedVersionName : packageInfo.versionName, (511 & 1024) != 0 ? appListItemCopy.installedVersionCode : Long.valueOf(PackageInfoCompat.getLongVersionCode(packageInfo)));
                }
                arrayList.add(appListItemCopy);
            }
            return arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: AppDao.kt */
    static final class AppListLiveData extends MediatorLiveData {
        private final ArrayList list = new ArrayList();

        public final void addSource(LiveData liveData) {
            Intrinsics.checkNotNullParameter(liveData, "liveData");
            this.list.add(liveData);
            addSource(liveData, new AppDaoKt$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: org.fdroid.database.AppDaoInt$AppListLiveData$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppDaoInt.AppListLiveData.addSource$lambda$4(this.f$0, (List) obj);
                }
            }));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit addSource$lambda$4(AppListLiveData appListLiveData, List list) {
            ArrayList arrayList = appListLiveData.list;
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            boolean z = true;
            while (it.hasNext()) {
                List listEmptyList = (List) ((LiveData) it.next()).getValue();
                if (listEmptyList == null) {
                    listEmptyList = CollectionsKt.emptyList();
                    z = false;
                }
                CollectionsKt.addAll(arrayList2, listEmptyList);
            }
            if (z) {
                final Function2 function2 = new Function2() { // from class: org.fdroid.database.AppDaoInt$AppListLiveData$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return Integer.valueOf(AppDaoInt.AppListLiveData.addSource$lambda$4$lambda$2((AppListItem) obj, (AppListItem) obj2));
                    }
                };
                appListLiveData.setValue(CollectionsKt.sortedWith(arrayList2, new Comparator() { // from class: org.fdroid.database.AppDaoInt$AppListLiveData$$ExternalSyntheticLambda2
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        return AppDaoInt.AppListLiveData.addSource$lambda$4$lambda$3(function2, obj, obj2);
                    }
                }));
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final int addSource$lambda$4$lambda$3(Function2 function2, Object obj, Object obj2) {
            return ((Number) function2.invoke(obj, obj2)).intValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final int addSource$lambda$4$lambda$2(AppListItem appListItem, AppListItem appListItem2) {
            String name = appListItem.getName();
            if (name == null) {
                name = "";
            }
            String name2 = appListItem2.getName();
            return StringsKt.compareTo(name, name2 != null ? name2 : "", true);
        }
    }
}
