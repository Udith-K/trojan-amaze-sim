package org.fdroid.database;

import androidx.lifecycle.LiveData;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KTypeProjection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonObject;
import org.fdroid.database.RepositoryDaoInt;
import org.fdroid.index.IndexConverterKt;
import org.fdroid.index.IndexFormatVersion;
import org.fdroid.index.IndexParser;
import org.fdroid.index.v2.MirrorV2;
import org.fdroid.index.v2.ReflectionDiffer;
import org.fdroid.index.v2.RepoV2;

/* JADX INFO: compiled from: RepositoryDao.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b*\n\u0002\u0018\u0002\n\u0000\ba\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0016\u0010\b\u001a\u00020\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH'J\u0016\u0010\f\u001a\u00020\u00072\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\nH'J\u0016\u0010\u000f\u001a\u00020\u00072\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00100\nH'J\u0016\u0010\u0011\u001a\u00020\u00072\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00120\nH'J\u0010\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0015H'J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H\u0017J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0019H\u0017J2\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001c2\b\b\u0002\u0010\u001f\u001a\u00020\u001cH\u0017J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\u0003H\u0017J\b\u0010\"\u001a\u00020#H'J\u0012\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020\u0003H'J\u0012\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010\u001f\u001a\u00020\u001cH'J\u000e\u0010'\u001a\b\u0012\u0004\u0012\u00020%0\nH'J\u0014\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0\n0)H'J\u0012\u0010*\u001a\u0004\u0018\u00010\u00152\u0006\u0010&\u001a\u00020\u0003H'J\u0014\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\n0)H'J(\u0010\u0006\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00032\u0006\u0010,\u001a\u00020-H\u0017J\u0018\u0010.\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020 H\u0002J\u0010\u0010/\u001a\u00020#2\u0006\u00100\u001a\u00020\u0005H'J\u0010\u00101\u001a\u00020\u00072\u0006\u00102\u001a\u00020\u0015H'J \u0010/\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00032\u0006\u00103\u001a\u000204H\u0017J\u0018\u00105\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u00032\u0006\u00106\u001a\u000207H\u0017J\u0018\u00108\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u00032\u0006\u00106\u001a\u000207H'J\u0010\u00109\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0003H'J\u001e\u0010:\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u001c0\nH'J$\u0010;\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u00032\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001cH'J\u001e\u0010<\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u00032\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\u001c0\nH'J\u0018\u0010>\u001a\u00020\u00072\u0006\u0010?\u001a\u00020%2\u0006\u0010@\u001a\u00020%H\u0017J\u0018\u0010A\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u00032\u0006\u0010B\u001a\u00020#H'J \u0010C\u001a\u00020\u00072\u0006\u0010D\u001a\u00020#2\u0006\u0010E\u001a\u00020#2\u0006\u0010F\u001a\u00020#H'J\u0017\u0010G\u001a\u0004\u0018\u00010\u00032\u0006\u0010H\u001a\u00020\u001cH'¢\u0006\u0002\u0010IJ\u0010\u0010J\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0003H\u0017J\u0010\u0010K\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0003H'J\u0010\u0010L\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0003H'J\b\u0010M\u001a\u00020\u0007H'J\b\u0010N\u001a\u00020\u0007H'J\u0010\u0010O\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0003H'J\u0010\u0010P\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0003H'J\u0018\u0010Q\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u00032\u0006\u0010R\u001a\u00020\u001cH'J\u0010\u0010S\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0003H'J\u0018\u0010T\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u00032\u0006\u0010R\u001a\u00020\u001cH'J\u0010\u0010U\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0003H'J\u0018\u0010V\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u00032\u0006\u0010R\u001a\u00020\u001cH'J\b\u0010W\u001a\u00020\u0007H'J\b\u0010X\u001a\u00020\u0007H'J\u0010\u0010Y\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u0003H\u0017J\b\u0010Z\u001a\u00020\u0007H\u0017J\b\u0010[\u001a\u00020#H'J\b\u0010\\\u001a\u00020#H'J\b\u0010]\u001a\u00020#H'J\b\u0010^\u001a\u00020#H'J\b\u0010_\u001a\u00020\u0007H\u0016J\u0010\u0010`\u001a\u00020#2\u0006\u0010a\u001a\u00020bH'¨\u0006c"}, d2 = {"Lorg/fdroid/database/RepositoryDaoInt;", "Lorg/fdroid/database/RepositoryDao;", "insertOrReplace", "", "repository", "Lorg/fdroid/database/CoreRepository;", "update", "", "insertMirrors", "mirrors", "", "Lorg/fdroid/database/Mirror;", "insertAntiFeatures", "repoFeature", "Lorg/fdroid/database/AntiFeature;", "insertCategories", "Lorg/fdroid/database/Category;", "insertReleaseChannels", "Lorg/fdroid/database/ReleaseChannel;", "insert", "repositoryPreferences", "Lorg/fdroid/database/RepositoryPreferences;", "initialRepo", "Lorg/fdroid/database/InitialRepository;", "newRepository", "Lorg/fdroid/database/NewRepository;", "insertEmptyRepo", "address", "", "username", "password", "certificate", "Lorg/fdroid/index/v2/RepoV2;", "version", "getMinRepositoryWeight", "", "getRepository", "Lorg/fdroid/database/Repository;", "repoId", "getRepositories", "getLiveRepositories", "Landroidx/lifecycle/LiveData;", "getRepositoryPreferences", "getLiveCategories", "formatVersion", "Lorg/fdroid/index/IndexFormatVersion;", "insertRepoTables", "updateRepository", "repo", "updateRepositoryPreferences", "preferences", "jsonObject", "Lkotlinx/serialization/json/JsonObject;", "setRepositoryEnabled", "enabled", "", "setRepositoryEnabledInternal", "resetPreferredRepoInAppPrefs", "updateUserMirrors", "updateUsernameAndPassword", "updateDisabledMirrors", "disabledMirrors", "reorderRepositories", "repoToReorder", "repoTarget", "setWeight", "weight", "shiftRepoWeights", "weightFrom", "weightTo", "offset", "getArchiveRepoId", "cert", "(Ljava/lang/String;)Ljava/lang/Long;", "deleteRepository", "deleteCoreRepository", "deleteRepositoryPreferences", "deleteAllCoreRepositories", "deleteAllRepositoryPreferences", "deleteMirrors", "deleteAntiFeatures", "deleteAntiFeature", "id", "deleteCategories", "deleteCategory", "deleteReleaseChannels", "deleteReleaseChannel", "resetTimestamps", "resetETags", "clear", "clearAll", "countMirrors", "countAntiFeatures", "countCategories", "countReleaseChannels", "walCheckpoint", "rawCheckpoint", "supportSQLiteQuery", "Landroidx/sqlite/db/SupportSQLiteQuery;", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface RepositoryDaoInt extends RepositoryDao {
    void clear(long repoId);

    @Override // org.fdroid.database.RepositoryDao
    void clearAll();

    int countAntiFeatures();

    int countCategories();

    int countMirrors();

    int countReleaseChannels();

    void deleteAllCoreRepositories();

    void deleteAllRepositoryPreferences();

    void deleteAntiFeature(long repoId, String id);

    void deleteAntiFeatures(long repoId);

    void deleteCategories(long repoId);

    void deleteCategory(long repoId, String id);

    void deleteCoreRepository(long repoId);

    void deleteMirrors(long repoId);

    void deleteReleaseChannel(long repoId, String id);

    void deleteReleaseChannels(long repoId);

    @Override // org.fdroid.database.RepositoryDao
    void deleteRepository(long repoId);

    void deleteRepositoryPreferences(long repoId);

    Long getArchiveRepoId(String cert);

    @Override // org.fdroid.database.RepositoryDao
    LiveData getLiveCategories();

    @Override // org.fdroid.database.RepositoryDao
    LiveData getLiveRepositories();

    int getMinRepositoryWeight();

    @Override // org.fdroid.database.RepositoryDao
    List<Repository> getRepositories();

    @Override // org.fdroid.database.RepositoryDao
    Repository getRepository(long repoId);

    Repository getRepository(String certificate);

    RepositoryPreferences getRepositoryPreferences(long repoId);

    @Override // org.fdroid.database.RepositoryDao
    long insert(InitialRepository initialRepo);

    @Override // org.fdroid.database.RepositoryDao
    long insert(NewRepository newRepository);

    void insert(RepositoryPreferences repositoryPreferences);

    void insertAntiFeatures(List<AntiFeature> repoFeature);

    void insertCategories(List<Category> repoFeature);

    @Deprecated
    long insertEmptyRepo(String address, String username, String password, String certificate);

    void insertMirrors(List<Mirror> mirrors);

    long insertOrReplace(CoreRepository repository);

    long insertOrReplace(RepoV2 repository, long version);

    void insertReleaseChannels(List<ReleaseChannel> repoFeature);

    int rawCheckpoint(SupportSQLiteQuery supportSQLiteQuery);

    void reorderRepositories(Repository repoToReorder, Repository repoTarget);

    void resetETags();

    void resetPreferredRepoInAppPrefs(long repoId);

    void resetTimestamps();

    @Override // org.fdroid.database.RepositoryDao
    void setRepositoryEnabled(long repoId, boolean enabled);

    void setRepositoryEnabledInternal(long repoId, boolean enabled);

    void setWeight(long repoId, int weight);

    void shiftRepoWeights(int weightFrom, int weightTo, int offset);

    void update(long repoId, RepoV2 repository, long version, IndexFormatVersion formatVersion);

    void update(CoreRepository repository);

    @Override // org.fdroid.database.RepositoryDao
    void updateDisabledMirrors(long repoId, List<String> disabledMirrors);

    int updateRepository(CoreRepository repo);

    void updateRepository(long repoId, long version, JsonObject jsonObject);

    void updateRepositoryPreferences(RepositoryPreferences preferences);

    @Override // org.fdroid.database.RepositoryDao
    void updateUserMirrors(long repoId, List<String> mirrors);

    @Override // org.fdroid.database.RepositoryDao
    void updateUsernameAndPassword(long repoId, String username, String password);

    @Override // org.fdroid.database.RepositoryDao
    void walCheckpoint();

    /* JADX INFO: compiled from: RepositoryDao.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        public static long insert(RepositoryDaoInt repositoryDaoInt, InitialRepository initialRepo) {
            Intrinsics.checkNotNullParameter(initialRepo, "initialRepo");
            long jInsertOrReplace = repositoryDaoInt.insertOrReplace(new CoreRepository(0L, MapsKt.mapOf(TuplesKt.to(IndexConverterKt.DEFAULT_LOCALE, initialRepo.getName())), null, initialRepo.getAddress(), null, -1L, Long.valueOf(initialRepo.getVersion()), null, null, MapsKt.mapOf(TuplesKt.to(IndexConverterKt.DEFAULT_LOCALE, initialRepo.getDescription())), initialRepo.getCertificate(), 17, null));
            repositoryDaoInt.insert(new RepositoryPreferences(jInsertOrReplace, repositoryDaoInt.getMinRepositoryWeight() - 2, initialRepo.getEnabled(), null, null, null, null, null, null, 496, null));
            List<String> mirrors = initialRepo.getMirrors();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(mirrors, 10));
            Iterator<T> it = mirrors.iterator();
            while (it.hasNext()) {
                arrayList.add(new Mirror(jInsertOrReplace, (String) it.next(), null));
            }
            repositoryDaoInt.insertMirrors(arrayList);
            return jInsertOrReplace;
        }

        public static long insert(RepositoryDaoInt repositoryDaoInt, NewRepository newRepository) {
            Intrinsics.checkNotNullParameter(newRepository, "newRepository");
            long jInsertOrReplace = repositoryDaoInt.insertOrReplace(new CoreRepository(0L, newRepository.getName(), newRepository.getIcon(), newRepository.getAddress(), null, -1L, null, newRepository.getFormatVersion(), null, null, newRepository.getCertificate(), 529, null));
            repositoryDaoInt.insert(new RepositoryPreferences(jInsertOrReplace, repositoryDaoInt.getMinRepositoryWeight() - 2, false, null, null, null, null, newRepository.getUsername(), newRepository.getPassword(), 116, null));
            return jInsertOrReplace;
        }

        public static /* synthetic */ long insertEmptyRepo$default(RepositoryDaoInt repositoryDaoInt, String str, String str2, String str3, String str4, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: insertEmptyRepo");
            }
            if ((i & 2) != 0) {
                str2 = null;
            }
            if ((i & 4) != 0) {
                str3 = null;
            }
            if ((i & 8) != 0) {
                str4 = "6789";
            }
            return repositoryDaoInt.insertEmptyRepo(str, str2, str3, str4);
        }

        @Deprecated
        public static long insertEmptyRepo(RepositoryDaoInt repositoryDaoInt, String address, String str, String str2, String certificate) {
            Intrinsics.checkNotNullParameter(address, "address");
            Intrinsics.checkNotNullParameter(certificate, "certificate");
            long jInsertOrReplace = repositoryDaoInt.insertOrReplace(new CoreRepository(0L, MapsKt.mapOf(TuplesKt.to(IndexConverterKt.DEFAULT_LOCALE, address)), null, address, null, -1L, null, null, null, null, certificate, 529, null));
            repositoryDaoInt.insert(new RepositoryPreferences(jInsertOrReplace, repositoryDaoInt.getMinRepositoryWeight() - 2, false, null, null, null, null, str, str2, 116, null));
            return jInsertOrReplace;
        }

        public static /* synthetic */ long insertOrReplace$default(RepositoryDaoInt repositoryDaoInt, RepoV2 repoV2, long j, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: insertOrReplace");
            }
            if ((i & 2) != 0) {
                j = 0;
            }
            return repositoryDaoInt.insertOrReplace(repoV2, j);
        }

        public static long insertOrReplace(RepositoryDaoInt repositoryDaoInt, RepoV2 repository, long j) {
            Intrinsics.checkNotNullParameter(repository, "repository");
            long jInsertOrReplace = repositoryDaoInt.insertOrReplace(RepositoryKt.toCoreRepository$default(repository, 0L, j, null, "0123", 5, null));
            repositoryDaoInt.insert(new RepositoryPreferences(jInsertOrReplace, repositoryDaoInt.getMinRepositoryWeight() - 2, false, null, null, null, null, null, null, 508, null));
            insertRepoTables(repositoryDaoInt, jInsertOrReplace, repository);
            return jInsertOrReplace;
        }

        public static void update(RepositoryDaoInt repositoryDaoInt, long j, RepoV2 repository, long j2, IndexFormatVersion formatVersion) {
            Intrinsics.checkNotNullParameter(repository, "repository");
            Intrinsics.checkNotNullParameter(formatVersion, "formatVersion");
            Repository repository2 = repositoryDaoInt.getRepository(j);
            if (repository2 == null) {
                throw new IllegalStateException(("Repo with id " + j + " did not exist").toString());
            }
            repositoryDaoInt.update(RepositoryKt.toCoreRepository(repository, j, j2, formatVersion, repository2.getCertificate()));
            insertRepoTables(repositoryDaoInt, j, repository);
        }

        private static void insertRepoTables(RepositoryDaoInt repositoryDaoInt, long j, RepoV2 repoV2) {
            List<MirrorV2> mirrors = repoV2.getMirrors();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(mirrors, 10));
            Iterator<T> it = mirrors.iterator();
            while (it.hasNext()) {
                arrayList.add(RepositoryKt.toMirror((MirrorV2) it.next(), j));
            }
            repositoryDaoInt.insertMirrors(arrayList);
            repositoryDaoInt.insertAntiFeatures(RepositoryKt.toRepoAntiFeatures(repoV2.getAntiFeatures(), j));
            repositoryDaoInt.insertCategories(RepositoryKt.toRepoCategories(repoV2.getCategories(), j));
            repositoryDaoInt.insertReleaseChannels(RepositoryKt.toRepoReleaseChannel(repoV2.getReleaseChannels(), j));
        }

        public static void updateRepository(final RepositoryDaoInt repositoryDaoInt, final long j, long j2, JsonObject jsonObject) {
            Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
            Repository repository = repositoryDaoInt.getRepository(j);
            if (repository == null) {
                throw new IllegalStateException(("Repo " + j + " does not exist").toString());
            }
            CoreRepository coreRepository = (CoreRepository) ReflectionDiffer.INSTANCE.applyDiff(repository.getRepository$database_release(), jsonObject);
            repositoryDaoInt.updateRepository(coreRepository.copy((1983 & 1) != 0 ? coreRepository.repoId : 0L, (1983 & 2) != 0 ? coreRepository.name : null, (1983 & 4) != 0 ? coreRepository.icon : null, (1983 & 8) != 0 ? coreRepository.address : null, (1983 & 16) != 0 ? coreRepository.webBaseUrl : null, (1983 & 32) != 0 ? coreRepository.timestamp : 0L, (1983 & 64) != 0 ? coreRepository.version : Long.valueOf(j2), (1983 & 128) != 0 ? coreRepository.formatVersion : null, (1983 & 256) != 0 ? coreRepository.maxAge : null, (1983 & 512) != 0 ? coreRepository.description : null, (1983 & 1024) != 0 ? coreRepository.certificate : null));
            DbDiffUtils dbDiffUtils = DbDiffUtils.INSTANCE;
            dbDiffUtils.diffAndUpdateListTable(jsonObject, "mirrors", new Function1() { // from class: org.fdroid.database.RepositoryDaoInt$DefaultImpls$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return RepositoryDaoInt.DefaultImpls.updateRepository$lambda$3(j, (JsonArray) obj);
                }
            }, new Function0() { // from class: org.fdroid.database.RepositoryDaoInt$DefaultImpls$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return RepositoryDaoInt.DefaultImpls.updateRepository$lambda$4(repositoryDaoInt, j);
                }
            }, new Function1() { // from class: org.fdroid.database.RepositoryDaoInt$DefaultImpls$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return RepositoryDaoInt.DefaultImpls.updateRepository$lambda$5(repositoryDaoInt, (List) obj);
                }
            });
            dbDiffUtils.diffAndUpdateTable(jsonObject, "antiFeatures", repository.getAntiFeatures$database_release(), new Function2() { // from class: org.fdroid.database.RepositoryDaoInt$DefaultImpls$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return Boolean.valueOf(RepositoryDaoInt.DefaultImpls.updateRepository$lambda$6((String) obj, (AntiFeature) obj2));
                }
            }, new Function1() { // from class: org.fdroid.database.RepositoryDaoInt$DefaultImpls$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return RepositoryDaoInt.DefaultImpls.updateRepository$lambda$7(j, (String) obj);
                }
            }, new Function0() { // from class: org.fdroid.database.RepositoryDaoInt$DefaultImpls$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return RepositoryDaoInt.DefaultImpls.updateRepository$lambda$8(repositoryDaoInt, j);
                }
            }, new Function1() { // from class: org.fdroid.database.RepositoryDaoInt$DefaultImpls$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return RepositoryDaoInt.DefaultImpls.updateRepository$lambda$9(repositoryDaoInt, j, (String) obj);
                }
            }, new Function1() { // from class: org.fdroid.database.RepositoryDaoInt$DefaultImpls$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return RepositoryDaoInt.DefaultImpls.updateRepository$lambda$10(repositoryDaoInt, (List) obj);
                }
            }, (768 & 256) != 0 ? new Function1() { // from class: org.fdroid.database.DbDiffUtils$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(DbDiffUtils.diffAndUpdateTable$lambda$0(obj));
                }
            } : null, (768 & 512) != 0 ? null : null);
            dbDiffUtils.diffAndUpdateTable(jsonObject, "categories", repository.getCategories$database_release(), new Function2() { // from class: org.fdroid.database.RepositoryDaoInt$DefaultImpls$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return Boolean.valueOf(RepositoryDaoInt.DefaultImpls.updateRepository$lambda$11((String) obj, (Category) obj2));
                }
            }, new Function1() { // from class: org.fdroid.database.RepositoryDaoInt$DefaultImpls$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return RepositoryDaoInt.DefaultImpls.updateRepository$lambda$12(j, (String) obj);
                }
            }, new Function0() { // from class: org.fdroid.database.RepositoryDaoInt$DefaultImpls$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return RepositoryDaoInt.DefaultImpls.updateRepository$lambda$13(repositoryDaoInt, j);
                }
            }, new Function1() { // from class: org.fdroid.database.RepositoryDaoInt$DefaultImpls$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return RepositoryDaoInt.DefaultImpls.updateRepository$lambda$14(repositoryDaoInt, j, (String) obj);
                }
            }, new Function1() { // from class: org.fdroid.database.RepositoryDaoInt$DefaultImpls$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return RepositoryDaoInt.DefaultImpls.updateRepository$lambda$15(repositoryDaoInt, (List) obj);
                }
            }, (768 & 256) != 0 ? new Function1() { // from class: org.fdroid.database.DbDiffUtils$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(DbDiffUtils.diffAndUpdateTable$lambda$0(obj));
                }
            } : null, (768 & 512) != 0 ? null : null);
            dbDiffUtils.diffAndUpdateTable(jsonObject, "releaseChannels", repository.getReleaseChannels$database_release(), new Function2() { // from class: org.fdroid.database.RepositoryDaoInt$DefaultImpls$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return Boolean.valueOf(RepositoryDaoInt.DefaultImpls.updateRepository$lambda$16((String) obj, (ReleaseChannel) obj2));
                }
            }, new Function1() { // from class: org.fdroid.database.RepositoryDaoInt$DefaultImpls$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return RepositoryDaoInt.DefaultImpls.updateRepository$lambda$17(j, (String) obj);
                }
            }, new Function0() { // from class: org.fdroid.database.RepositoryDaoInt$DefaultImpls$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return RepositoryDaoInt.DefaultImpls.updateRepository$lambda$18(repositoryDaoInt, j);
                }
            }, new Function1() { // from class: org.fdroid.database.RepositoryDaoInt$DefaultImpls$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return RepositoryDaoInt.DefaultImpls.updateRepository$lambda$19(repositoryDaoInt, j, (String) obj);
                }
            }, new Function1() { // from class: org.fdroid.database.RepositoryDaoInt$DefaultImpls$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return RepositoryDaoInt.DefaultImpls.updateRepository$lambda$20(repositoryDaoInt, (List) obj);
                }
            }, (768 & 256) != 0 ? new Function1() { // from class: org.fdroid.database.DbDiffUtils$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(DbDiffUtils.diffAndUpdateTable$lambda$0(obj));
                }
            } : null, (768 & 512) != 0 ? null : null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static List updateRepository$lambda$3(long j, JsonArray mirrorArray) {
            Intrinsics.checkNotNullParameter(mirrorArray, "mirrorArray");
            Json json = IndexParser.getJson();
            KSerializer kSerializerSerializer = SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(List.class, KTypeProjection.Companion.invariant(Reflection.typeOf(MirrorV2.class))));
            Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
            Iterable iterable = (Iterable) json.decodeFromJsonElement(kSerializerSerializer, mirrorArray);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                arrayList.add(RepositoryKt.toMirror((MirrorV2) it.next(), j));
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Unit updateRepository$lambda$4(RepositoryDaoInt repositoryDaoInt, long j) {
            repositoryDaoInt.deleteMirrors(j);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Unit updateRepository$lambda$5(RepositoryDaoInt repositoryDaoInt, List mirrors) {
            Intrinsics.checkNotNullParameter(mirrors, "mirrors");
            repositoryDaoInt.insertMirrors(mirrors);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean updateRepository$lambda$6(String key, AntiFeature item) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(item, "item");
            return Intrinsics.areEqual(item.getId$database_release(), key);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static AntiFeature updateRepository$lambda$7(long j, String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            return new AntiFeature(j, key, MapsKt.emptyMap(), MapsKt.emptyMap(), MapsKt.emptyMap());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Unit updateRepository$lambda$8(RepositoryDaoInt repositoryDaoInt, long j) {
            repositoryDaoInt.deleteAntiFeatures(j);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Unit updateRepository$lambda$9(RepositoryDaoInt repositoryDaoInt, long j, String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            repositoryDaoInt.deleteAntiFeature(j, key);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Unit updateRepository$lambda$10(RepositoryDaoInt repositoryDaoInt, List list) {
            Intrinsics.checkNotNullParameter(list, "list");
            repositoryDaoInt.insertAntiFeatures(list);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean updateRepository$lambda$11(String key, Category item) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(item, "item");
            return Intrinsics.areEqual(item.getId(), key);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Category updateRepository$lambda$12(long j, String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            return new Category(j, key, MapsKt.emptyMap(), MapsKt.emptyMap(), MapsKt.emptyMap());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Unit updateRepository$lambda$13(RepositoryDaoInt repositoryDaoInt, long j) {
            repositoryDaoInt.deleteCategories(j);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Unit updateRepository$lambda$14(RepositoryDaoInt repositoryDaoInt, long j, String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            repositoryDaoInt.deleteCategory(j, key);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Unit updateRepository$lambda$15(RepositoryDaoInt repositoryDaoInt, List list) {
            Intrinsics.checkNotNullParameter(list, "list");
            repositoryDaoInt.insertCategories(list);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean updateRepository$lambda$16(String key, ReleaseChannel item) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(item, "item");
            return Intrinsics.areEqual(item.getId$database_release(), key);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static ReleaseChannel updateRepository$lambda$17(long j, String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            return new ReleaseChannel(j, key, MapsKt.emptyMap(), MapsKt.emptyMap(), MapsKt.emptyMap());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Unit updateRepository$lambda$18(RepositoryDaoInt repositoryDaoInt, long j) {
            repositoryDaoInt.deleteReleaseChannels(j);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Unit updateRepository$lambda$19(RepositoryDaoInt repositoryDaoInt, long j, String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            repositoryDaoInt.deleteReleaseChannel(j, key);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Unit updateRepository$lambda$20(RepositoryDaoInt repositoryDaoInt, List list) {
            Intrinsics.checkNotNullParameter(list, "list");
            repositoryDaoInt.insertReleaseChannels(list);
            return Unit.INSTANCE;
        }

        public static void setRepositoryEnabled(RepositoryDaoInt repositoryDaoInt, long j, boolean z) {
            if (!z) {
                repositoryDaoInt.resetPreferredRepoInAppPrefs(j);
            }
            repositoryDaoInt.setRepositoryEnabledInternal(j, z);
        }

        public static void reorderRepositories(RepositoryDaoInt repositoryDaoInt, Repository repoToReorder, Repository repoTarget) {
            Intrinsics.checkNotNullParameter(repoToReorder, "repoToReorder");
            Intrinsics.checkNotNullParameter(repoTarget, "repoTarget");
            if (repoToReorder.isArchiveRepo() || repoTarget.isArchiveRepo()) {
                throw new IllegalArgumentException("Re-ordering of archive repos is not supported");
            }
            if (repoToReorder.getWeight() > repoTarget.getWeight()) {
                repositoryDaoInt.shiftRepoWeights(repoTarget.getWeight(), repoToReorder.getWeight() - 2, 2);
            } else if (repoToReorder.getWeight() >= repoTarget.getWeight()) {
                return;
            } else {
                repositoryDaoInt.shiftRepoWeights(repoToReorder.getWeight() + 1, repoTarget.getWeight(), -2);
            }
            repositoryDaoInt.setWeight(repoToReorder.getRepoId(), repoTarget.getWeight());
            Long archiveRepoId = repositoryDaoInt.getArchiveRepoId(repoToReorder.getCertificate());
            if (archiveRepoId != null) {
                repositoryDaoInt.setWeight(archiveRepoId.longValue(), repoTarget.getWeight() - 1);
            }
        }

        public static void deleteRepository(RepositoryDaoInt repositoryDaoInt, long j) {
            repositoryDaoInt.deleteCoreRepository(j);
            repositoryDaoInt.deleteRepositoryPreferences(j);
            repositoryDaoInt.resetPreferredRepoInAppPrefs(j);
        }

        public static void clear(RepositoryDaoInt repositoryDaoInt, long j) {
            Repository repository = repositoryDaoInt.getRepository(j);
            if (repository == null) {
                throw new IllegalStateException(("repo with id " + j + " does not exist").toString());
            }
            repositoryDaoInt.insertOrReplace(repository.getRepository$database_release());
        }

        public static void clearAll(RepositoryDaoInt repositoryDaoInt) {
            repositoryDaoInt.deleteAllCoreRepositories();
            repositoryDaoInt.deleteAllRepositoryPreferences();
        }

        public static void walCheckpoint(RepositoryDaoInt repositoryDaoInt) {
            repositoryDaoInt.rawCheckpoint(new SimpleSQLiteQuery("pragma wal_checkpoint(truncate)"));
        }
    }
}
