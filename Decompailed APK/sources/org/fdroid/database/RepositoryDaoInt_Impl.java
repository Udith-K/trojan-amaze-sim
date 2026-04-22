package org.fdroid.database;

import android.database.Cursor;
import androidx.collection.LongSparseArray;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.serialization.json.JsonObject;
import org.fdroid.database.RepositoryDaoInt;
import org.fdroid.fdroid.views.apps.AppListActivity;
import org.fdroid.index.IndexFormatVersion;
import org.fdroid.index.v2.RepoV2;

/* JADX INFO: loaded from: classes2.dex */
public final class RepositoryDaoInt_Impl implements RepositoryDaoInt {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter __insertionAdapterOfAntiFeature;
    private final EntityInsertionAdapter __insertionAdapterOfCategory;
    private final EntityInsertionAdapter __insertionAdapterOfCoreRepository;
    private final EntityInsertionAdapter __insertionAdapterOfMirror;
    private final EntityInsertionAdapter __insertionAdapterOfReleaseChannel;
    private final EntityInsertionAdapter __insertionAdapterOfRepositoryPreferences;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAllCoreRepositories;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAllRepositoryPreferences;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAntiFeature;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAntiFeatures;
    private final SharedSQLiteStatement __preparedStmtOfDeleteCategories;
    private final SharedSQLiteStatement __preparedStmtOfDeleteCategory;
    private final SharedSQLiteStatement __preparedStmtOfDeleteCoreRepository;
    private final SharedSQLiteStatement __preparedStmtOfDeleteMirrors;
    private final SharedSQLiteStatement __preparedStmtOfDeleteReleaseChannel;
    private final SharedSQLiteStatement __preparedStmtOfDeleteReleaseChannels;
    private final SharedSQLiteStatement __preparedStmtOfDeleteRepositoryPreferences;
    private final SharedSQLiteStatement __preparedStmtOfResetETags;
    private final SharedSQLiteStatement __preparedStmtOfResetPreferredRepoInAppPrefs;
    private final SharedSQLiteStatement __preparedStmtOfResetTimestamps;
    private final SharedSQLiteStatement __preparedStmtOfSetRepositoryEnabledInternal;
    private final SharedSQLiteStatement __preparedStmtOfSetWeight;
    private final SharedSQLiteStatement __preparedStmtOfShiftRepoWeights;
    private final SharedSQLiteStatement __preparedStmtOfUpdateDisabledMirrors;
    private final SharedSQLiteStatement __preparedStmtOfUpdateUserMirrors;
    private final SharedSQLiteStatement __preparedStmtOfUpdateUsernameAndPassword;
    private final EntityDeletionOrUpdateAdapter __updateAdapterOfCoreRepository;
    private final EntityDeletionOrUpdateAdapter __updateAdapterOfRepositoryPreferences;

    public RepositoryDaoInt_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfRepositoryPreferences = new EntityInsertionAdapter(roomDatabase) { // from class: org.fdroid.database.RepositoryDaoInt_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `RepositoryPreferences` (`repoId`,`weight`,`enabled`,`lastUpdated`,`lastETag`,`userMirrors`,`disabledMirrors`,`username`,`password`) VALUES (?,?,?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, RepositoryPreferences repositoryPreferences) {
                supportSQLiteStatement.bindLong(1, repositoryPreferences.getRepoId$database_release());
                supportSQLiteStatement.bindLong(2, repositoryPreferences.getWeight());
                supportSQLiteStatement.bindLong(3, repositoryPreferences.getEnabled() ? 1L : 0L);
                if (repositoryPreferences.getLastUpdated() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindLong(4, repositoryPreferences.getLastUpdated().longValue());
                }
                if (repositoryPreferences.getLastETag() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, repositoryPreferences.getLastETag());
                }
                Converters converters = Converters.INSTANCE;
                String strListStringToString = converters.listStringToString(repositoryPreferences.getUserMirrors());
                if (strListStringToString == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, strListStringToString);
                }
                String strListStringToString2 = converters.listStringToString(repositoryPreferences.getDisabledMirrors());
                if (strListStringToString2 == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, strListStringToString2);
                }
                if (repositoryPreferences.getUsername() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, repositoryPreferences.getUsername());
                }
                if (repositoryPreferences.getPassword() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, repositoryPreferences.getPassword());
                }
            }
        };
        this.__insertionAdapterOfCoreRepository = new EntityInsertionAdapter(roomDatabase) { // from class: org.fdroid.database.RepositoryDaoInt_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `CoreRepository` (`repoId`,`name`,`icon`,`address`,`webBaseUrl`,`timestamp`,`version`,`formatVersion`,`maxAge`,`description`,`certificate`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, CoreRepository coreRepository) {
                supportSQLiteStatement.bindLong(1, coreRepository.getRepoId());
                Converters converters = Converters.INSTANCE;
                String strLocalizedTextV2toString = converters.localizedTextV2toString(coreRepository.getName());
                if (strLocalizedTextV2toString == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, strLocalizedTextV2toString);
                }
                String strLocalizedFileV2toString = converters.localizedFileV2toString(coreRepository.getIcon());
                if (strLocalizedFileV2toString == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, strLocalizedFileV2toString);
                }
                if (coreRepository.getAddress() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, coreRepository.getAddress());
                }
                if (coreRepository.getWebBaseUrl() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, coreRepository.getWebBaseUrl());
                }
                supportSQLiteStatement.bindLong(6, coreRepository.getTimestamp());
                if (coreRepository.getVersion() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindLong(7, coreRepository.getVersion().longValue());
                }
                if (coreRepository.getFormatVersion() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, RepositoryDaoInt_Impl.this.__IndexFormatVersion_enumToString(coreRepository.getFormatVersion()));
                }
                if (coreRepository.getMaxAge() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindLong(9, coreRepository.getMaxAge().intValue());
                }
                String strLocalizedTextV2toString2 = converters.localizedTextV2toString(coreRepository.getDescription());
                if (strLocalizedTextV2toString2 == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, strLocalizedTextV2toString2);
                }
                if (coreRepository.getCertificate() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, coreRepository.getCertificate());
                }
            }
        };
        this.__insertionAdapterOfMirror = new EntityInsertionAdapter(roomDatabase) { // from class: org.fdroid.database.RepositoryDaoInt_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `Mirror` (`repoId`,`url`,`countryCode`) VALUES (?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Mirror mirror) {
                supportSQLiteStatement.bindLong(1, mirror.getRepoId());
                if (mirror.getUrl() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, mirror.getUrl());
                }
                if (mirror.getCountryCode() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, mirror.getCountryCode());
                }
            }
        };
        this.__insertionAdapterOfAntiFeature = new EntityInsertionAdapter(roomDatabase) { // from class: org.fdroid.database.RepositoryDaoInt_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `AntiFeature` (`repoId`,`id`,`icon`,`name`,`description`) VALUES (?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, AntiFeature antiFeature) {
                supportSQLiteStatement.bindLong(1, antiFeature.getRepoId$database_release());
                if (antiFeature.getId$database_release() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, antiFeature.getId$database_release());
                }
                Converters converters = Converters.INSTANCE;
                String strLocalizedFileV2toString = converters.localizedFileV2toString(antiFeature.getIcon());
                if (strLocalizedFileV2toString == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, strLocalizedFileV2toString);
                }
                String strLocalizedTextV2toString = converters.localizedTextV2toString(antiFeature.getName$database_release());
                if (strLocalizedTextV2toString == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, strLocalizedTextV2toString);
                }
                String strLocalizedTextV2toString2 = converters.localizedTextV2toString(antiFeature.getDescription$database_release());
                if (strLocalizedTextV2toString2 == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, strLocalizedTextV2toString2);
                }
            }
        };
        this.__insertionAdapterOfCategory = new EntityInsertionAdapter(roomDatabase) { // from class: org.fdroid.database.RepositoryDaoInt_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `Category` (`repoId`,`id`,`icon`,`name`,`description`) VALUES (?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Category category) {
                supportSQLiteStatement.bindLong(1, category.getRepoId());
                if (category.getId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, category.getId());
                }
                Converters converters = Converters.INSTANCE;
                String strLocalizedFileV2toString = converters.localizedFileV2toString(category.getIcon());
                if (strLocalizedFileV2toString == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, strLocalizedFileV2toString);
                }
                String strLocalizedTextV2toString = converters.localizedTextV2toString(category.getName$database_release());
                if (strLocalizedTextV2toString == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, strLocalizedTextV2toString);
                }
                String strLocalizedTextV2toString2 = converters.localizedTextV2toString(category.getDescription$database_release());
                if (strLocalizedTextV2toString2 == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, strLocalizedTextV2toString2);
                }
            }
        };
        this.__insertionAdapterOfReleaseChannel = new EntityInsertionAdapter(roomDatabase) { // from class: org.fdroid.database.RepositoryDaoInt_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `ReleaseChannel` (`repoId`,`id`,`icon`,`name`,`description`) VALUES (?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, ReleaseChannel releaseChannel) {
                supportSQLiteStatement.bindLong(1, releaseChannel.getRepoId$database_release());
                if (releaseChannel.getId$database_release() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, releaseChannel.getId$database_release());
                }
                Converters converters = Converters.INSTANCE;
                String strLocalizedFileV2toString = converters.localizedFileV2toString(releaseChannel.getIcon());
                if (strLocalizedFileV2toString == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, strLocalizedFileV2toString);
                }
                String strLocalizedTextV2toString = converters.localizedTextV2toString(releaseChannel.getName$database_release());
                if (strLocalizedTextV2toString == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, strLocalizedTextV2toString);
                }
                String strLocalizedTextV2toString2 = converters.localizedTextV2toString(releaseChannel.getDescription$database_release());
                if (strLocalizedTextV2toString2 == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, strLocalizedTextV2toString2);
                }
            }
        };
        this.__updateAdapterOfCoreRepository = new EntityDeletionOrUpdateAdapter(roomDatabase) { // from class: org.fdroid.database.RepositoryDaoInt_Impl.7
            @Override // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "UPDATE OR ABORT `CoreRepository` SET `repoId` = ?,`name` = ?,`icon` = ?,`address` = ?,`webBaseUrl` = ?,`timestamp` = ?,`version` = ?,`formatVersion` = ?,`maxAge` = ?,`description` = ?,`certificate` = ? WHERE `repoId` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, CoreRepository coreRepository) {
                supportSQLiteStatement.bindLong(1, coreRepository.getRepoId());
                Converters converters = Converters.INSTANCE;
                String strLocalizedTextV2toString = converters.localizedTextV2toString(coreRepository.getName());
                if (strLocalizedTextV2toString == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, strLocalizedTextV2toString);
                }
                String strLocalizedFileV2toString = converters.localizedFileV2toString(coreRepository.getIcon());
                if (strLocalizedFileV2toString == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, strLocalizedFileV2toString);
                }
                if (coreRepository.getAddress() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, coreRepository.getAddress());
                }
                if (coreRepository.getWebBaseUrl() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, coreRepository.getWebBaseUrl());
                }
                supportSQLiteStatement.bindLong(6, coreRepository.getTimestamp());
                if (coreRepository.getVersion() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindLong(7, coreRepository.getVersion().longValue());
                }
                if (coreRepository.getFormatVersion() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, RepositoryDaoInt_Impl.this.__IndexFormatVersion_enumToString(coreRepository.getFormatVersion()));
                }
                if (coreRepository.getMaxAge() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindLong(9, coreRepository.getMaxAge().intValue());
                }
                String strLocalizedTextV2toString2 = converters.localizedTextV2toString(coreRepository.getDescription());
                if (strLocalizedTextV2toString2 == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, strLocalizedTextV2toString2);
                }
                if (coreRepository.getCertificate() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, coreRepository.getCertificate());
                }
                supportSQLiteStatement.bindLong(12, coreRepository.getRepoId());
            }
        };
        this.__updateAdapterOfRepositoryPreferences = new EntityDeletionOrUpdateAdapter(roomDatabase) { // from class: org.fdroid.database.RepositoryDaoInt_Impl.8
            @Override // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "UPDATE OR ABORT `RepositoryPreferences` SET `repoId` = ?,`weight` = ?,`enabled` = ?,`lastUpdated` = ?,`lastETag` = ?,`userMirrors` = ?,`disabledMirrors` = ?,`username` = ?,`password` = ? WHERE `repoId` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, RepositoryPreferences repositoryPreferences) {
                supportSQLiteStatement.bindLong(1, repositoryPreferences.getRepoId$database_release());
                supportSQLiteStatement.bindLong(2, repositoryPreferences.getWeight());
                supportSQLiteStatement.bindLong(3, repositoryPreferences.getEnabled() ? 1L : 0L);
                if (repositoryPreferences.getLastUpdated() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindLong(4, repositoryPreferences.getLastUpdated().longValue());
                }
                if (repositoryPreferences.getLastETag() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, repositoryPreferences.getLastETag());
                }
                Converters converters = Converters.INSTANCE;
                String strListStringToString = converters.listStringToString(repositoryPreferences.getUserMirrors());
                if (strListStringToString == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, strListStringToString);
                }
                String strListStringToString2 = converters.listStringToString(repositoryPreferences.getDisabledMirrors());
                if (strListStringToString2 == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, strListStringToString2);
                }
                if (repositoryPreferences.getUsername() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, repositoryPreferences.getUsername());
                }
                if (repositoryPreferences.getPassword() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, repositoryPreferences.getPassword());
                }
                supportSQLiteStatement.bindLong(10, repositoryPreferences.getRepoId$database_release());
            }
        };
        this.__preparedStmtOfUpdateUserMirrors = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.RepositoryDaoInt_Impl.9
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE RepositoryPreferences SET userMirrors = ?\n        WHERE repoId = ?";
            }
        };
        this.__preparedStmtOfUpdateUsernameAndPassword = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.RepositoryDaoInt_Impl.10
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE RepositoryPreferences SET username = ?, password = ?\n        WHERE repoId = ?";
            }
        };
        this.__preparedStmtOfUpdateDisabledMirrors = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.RepositoryDaoInt_Impl.11
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE RepositoryPreferences SET disabledMirrors = ?\n        WHERE repoId = ?";
            }
        };
        this.__preparedStmtOfSetRepositoryEnabledInternal = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.RepositoryDaoInt_Impl.12
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE RepositoryPreferences SET enabled = ? WHERE repoId = ?";
            }
        };
        this.__preparedStmtOfResetPreferredRepoInAppPrefs = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.RepositoryDaoInt_Impl.13
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE AppPrefs SET preferredRepoId = NULL WHERE preferredRepoId = ?";
            }
        };
        this.__preparedStmtOfSetWeight = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.RepositoryDaoInt_Impl.14
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE RepositoryPreferences SET weight = ? WHERE repoId = ?";
            }
        };
        this.__preparedStmtOfShiftRepoWeights = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.RepositoryDaoInt_Impl.15
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE RepositoryPreferences SET weight = weight + ?\n        WHERE weight >= ? AND weight <= ?";
            }
        };
        this.__preparedStmtOfDeleteCoreRepository = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.RepositoryDaoInt_Impl.16
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM CoreRepository WHERE repoId = ?";
            }
        };
        this.__preparedStmtOfDeleteRepositoryPreferences = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.RepositoryDaoInt_Impl.17
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM RepositoryPreferences WHERE repoId = ?";
            }
        };
        this.__preparedStmtOfDeleteAllCoreRepositories = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.RepositoryDaoInt_Impl.18
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM CoreRepository";
            }
        };
        this.__preparedStmtOfDeleteAllRepositoryPreferences = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.RepositoryDaoInt_Impl.19
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM RepositoryPreferences";
            }
        };
        this.__preparedStmtOfDeleteMirrors = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.RepositoryDaoInt_Impl.20
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM Mirror WHERE repoId = ?";
            }
        };
        this.__preparedStmtOfDeleteAntiFeatures = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.RepositoryDaoInt_Impl.21
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM AntiFeature WHERE repoId = ?";
            }
        };
        this.__preparedStmtOfDeleteAntiFeature = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.RepositoryDaoInt_Impl.22
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM AntiFeature WHERE repoId = ? AND id = ?";
            }
        };
        this.__preparedStmtOfDeleteCategories = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.RepositoryDaoInt_Impl.23
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM Category WHERE repoId = ?";
            }
        };
        this.__preparedStmtOfDeleteCategory = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.RepositoryDaoInt_Impl.24
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM Category WHERE repoId = ? AND id = ?";
            }
        };
        this.__preparedStmtOfDeleteReleaseChannels = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.RepositoryDaoInt_Impl.25
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM ReleaseChannel WHERE repoId = ?";
            }
        };
        this.__preparedStmtOfDeleteReleaseChannel = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.RepositoryDaoInt_Impl.26
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM ReleaseChannel WHERE repoId = ? AND id = ?";
            }
        };
        this.__preparedStmtOfResetTimestamps = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.RepositoryDaoInt_Impl.27
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE CoreRepository SET timestamp = -1";
            }
        };
        this.__preparedStmtOfResetETags = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.RepositoryDaoInt_Impl.28
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE RepositoryPreferences SET lastETag = NULL";
            }
        };
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public void insert(RepositoryPreferences repositoryPreferences) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfRepositoryPreferences.insert(repositoryPreferences);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public long insertOrReplace(CoreRepository coreRepository) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long jInsertAndReturnId = this.__insertionAdapterOfCoreRepository.insertAndReturnId(coreRepository);
            this.__db.setTransactionSuccessful();
            return jInsertAndReturnId;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public void insertMirrors(List<Mirror> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfMirror.insert((Iterable) list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public void insertAntiFeatures(List<AntiFeature> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfAntiFeature.insert((Iterable) list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public void insertCategories(List<Category> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfCategory.insert((Iterable) list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public void insertReleaseChannels(List<ReleaseChannel> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfReleaseChannel.insert((Iterable) list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public void update(CoreRepository coreRepository) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfCoreRepository.handle(coreRepository);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public int updateRepository(CoreRepository coreRepository) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            int iHandle = this.__updateAdapterOfCoreRepository.handle(coreRepository);
            this.__db.setTransactionSuccessful();
            return iHandle;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public void updateRepositoryPreferences(RepositoryPreferences repositoryPreferences) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfRepositoryPreferences.handle(repositoryPreferences);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt, org.fdroid.database.RepositoryDao
    public long insert(InitialRepository initialRepository) {
        this.__db.beginTransaction();
        try {
            long jInsert = RepositoryDaoInt.DefaultImpls.insert(this, initialRepository);
            this.__db.setTransactionSuccessful();
            return jInsert;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt, org.fdroid.database.RepositoryDao
    public long insert(NewRepository newRepository) {
        this.__db.beginTransaction();
        try {
            long jInsert = RepositoryDaoInt.DefaultImpls.insert(this, newRepository);
            this.__db.setTransactionSuccessful();
            return jInsert;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt, org.fdroid.database.RepositoryDao
    public void setRepositoryEnabled(long j, boolean z) {
        this.__db.beginTransaction();
        try {
            RepositoryDaoInt.DefaultImpls.setRepositoryEnabled(this, j, z);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt, org.fdroid.database.RepositoryDao
    public void deleteRepository(long j) {
        this.__db.beginTransaction();
        try {
            RepositoryDaoInt.DefaultImpls.deleteRepository(this, j);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt, org.fdroid.database.RepositoryDao
    public void clearAll() {
        this.__db.beginTransaction();
        try {
            RepositoryDaoInt.DefaultImpls.clearAll(this);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public long insertOrReplace(RepoV2 repoV2, long j) {
        this.__db.beginTransaction();
        try {
            long jInsertOrReplace = RepositoryDaoInt.DefaultImpls.insertOrReplace(this, repoV2, j);
            this.__db.setTransactionSuccessful();
            return jInsertOrReplace;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public void update(long j, RepoV2 repoV2, long j2, IndexFormatVersion indexFormatVersion) {
        this.__db.beginTransaction();
        try {
            RepositoryDaoInt.DefaultImpls.update(this, j, repoV2, j2, indexFormatVersion);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public long insertEmptyRepo(String str, String str2, String str3, String str4) {
        this.__db.beginTransaction();
        try {
            long jInsertEmptyRepo = RepositoryDaoInt.DefaultImpls.insertEmptyRepo(this, str, str2, str3, str4);
            this.__db.setTransactionSuccessful();
            return jInsertEmptyRepo;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public void updateRepository(long j, long j2, JsonObject jsonObject) {
        this.__db.beginTransaction();
        try {
            RepositoryDaoInt.DefaultImpls.updateRepository(this, j, j2, jsonObject);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public void reorderRepositories(Repository repository, Repository repository2) {
        this.__db.beginTransaction();
        try {
            RepositoryDaoInt.DefaultImpls.reorderRepositories(this, repository, repository2);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public void clear(long j) {
        this.__db.beginTransaction();
        try {
            RepositoryDaoInt.DefaultImpls.clear(this, j);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt, org.fdroid.database.RepositoryDao
    public void updateUserMirrors(long j, List<String> list) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfUpdateUserMirrors.acquire();
        String strListStringToString = Converters.INSTANCE.listStringToString(list);
        if (strListStringToString == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, strListStringToString);
        }
        supportSQLiteStatementAcquire.bindLong(2, j);
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatementAcquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOfUpdateUserMirrors.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt, org.fdroid.database.RepositoryDao
    public void updateUsernameAndPassword(long j, String str, String str2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfUpdateUsernameAndPassword.acquire();
        if (str == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, str);
        }
        if (str2 == null) {
            supportSQLiteStatementAcquire.bindNull(2);
        } else {
            supportSQLiteStatementAcquire.bindString(2, str2);
        }
        supportSQLiteStatementAcquire.bindLong(3, j);
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatementAcquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOfUpdateUsernameAndPassword.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt, org.fdroid.database.RepositoryDao
    public void updateDisabledMirrors(long j, List<String> list) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfUpdateDisabledMirrors.acquire();
        String strListStringToString = Converters.INSTANCE.listStringToString(list);
        if (strListStringToString == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, strListStringToString);
        }
        supportSQLiteStatementAcquire.bindLong(2, j);
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatementAcquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOfUpdateDisabledMirrors.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public void setRepositoryEnabledInternal(long j, boolean z) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfSetRepositoryEnabledInternal.acquire();
        supportSQLiteStatementAcquire.bindLong(1, z ? 1L : 0L);
        supportSQLiteStatementAcquire.bindLong(2, j);
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatementAcquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOfSetRepositoryEnabledInternal.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public void resetPreferredRepoInAppPrefs(long j) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfResetPreferredRepoInAppPrefs.acquire();
        supportSQLiteStatementAcquire.bindLong(1, j);
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatementAcquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOfResetPreferredRepoInAppPrefs.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public void setWeight(long j, int i) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfSetWeight.acquire();
        supportSQLiteStatementAcquire.bindLong(1, i);
        supportSQLiteStatementAcquire.bindLong(2, j);
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatementAcquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOfSetWeight.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public void shiftRepoWeights(int i, int i2, int i3) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfShiftRepoWeights.acquire();
        supportSQLiteStatementAcquire.bindLong(1, i3);
        supportSQLiteStatementAcquire.bindLong(2, i);
        supportSQLiteStatementAcquire.bindLong(3, i2);
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatementAcquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOfShiftRepoWeights.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public void deleteCoreRepository(long j) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteCoreRepository.acquire();
        supportSQLiteStatementAcquire.bindLong(1, j);
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatementAcquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOfDeleteCoreRepository.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public void deleteRepositoryPreferences(long j) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteRepositoryPreferences.acquire();
        supportSQLiteStatementAcquire.bindLong(1, j);
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatementAcquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOfDeleteRepositoryPreferences.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public void deleteAllCoreRepositories() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteAllCoreRepositories.acquire();
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatementAcquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOfDeleteAllCoreRepositories.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public void deleteAllRepositoryPreferences() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteAllRepositoryPreferences.acquire();
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatementAcquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOfDeleteAllRepositoryPreferences.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public void deleteMirrors(long j) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteMirrors.acquire();
        supportSQLiteStatementAcquire.bindLong(1, j);
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatementAcquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOfDeleteMirrors.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public void deleteAntiFeatures(long j) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteAntiFeatures.acquire();
        supportSQLiteStatementAcquire.bindLong(1, j);
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatementAcquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOfDeleteAntiFeatures.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public void deleteAntiFeature(long j, String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteAntiFeature.acquire();
        supportSQLiteStatementAcquire.bindLong(1, j);
        if (str == null) {
            supportSQLiteStatementAcquire.bindNull(2);
        } else {
            supportSQLiteStatementAcquire.bindString(2, str);
        }
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatementAcquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOfDeleteAntiFeature.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public void deleteCategories(long j) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteCategories.acquire();
        supportSQLiteStatementAcquire.bindLong(1, j);
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatementAcquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOfDeleteCategories.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public void deleteCategory(long j, String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteCategory.acquire();
        supportSQLiteStatementAcquire.bindLong(1, j);
        if (str == null) {
            supportSQLiteStatementAcquire.bindNull(2);
        } else {
            supportSQLiteStatementAcquire.bindString(2, str);
        }
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatementAcquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOfDeleteCategory.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public void deleteReleaseChannels(long j) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteReleaseChannels.acquire();
        supportSQLiteStatementAcquire.bindLong(1, j);
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatementAcquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOfDeleteReleaseChannels.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public void deleteReleaseChannel(long j, String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteReleaseChannel.acquire();
        supportSQLiteStatementAcquire.bindLong(1, j);
        if (str == null) {
            supportSQLiteStatementAcquire.bindNull(2);
        } else {
            supportSQLiteStatementAcquire.bindString(2, str);
        }
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatementAcquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOfDeleteReleaseChannel.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public void resetTimestamps() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfResetTimestamps.acquire();
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatementAcquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOfResetTimestamps.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public void resetETags() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfResetETags.acquire();
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatementAcquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOfResetETags.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt, org.fdroid.database.RepositoryDao
    public Repository getRepository(long j) {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        LongSparseArray longSparseArray;
        LongSparseArray longSparseArray2;
        Repository repository;
        int i;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM CoreRepository WHERE repoId = ?", 1);
        roomSQLiteQueryAcquire.bindLong(1, j);
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, true, null);
            try {
                columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "repoId");
                columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "name");
                columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "icon");
                columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "address");
                columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "webBaseUrl");
                columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "timestamp");
                columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "version");
                columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "formatVersion");
                columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "maxAge");
                columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "description");
                columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "certificate");
                longSparseArray = new LongSparseArray();
                longSparseArray2 = new LongSparseArray();
                roomSQLiteQuery = roomSQLiteQueryAcquire;
            } catch (Throwable th) {
                th = th;
                roomSQLiteQuery = roomSQLiteQueryAcquire;
            }
            try {
                LongSparseArray longSparseArray3 = new LongSparseArray();
                LongSparseArray longSparseArray4 = new LongSparseArray();
                LongSparseArray longSparseArray5 = new LongSparseArray();
                while (cursorQuery.moveToNext()) {
                    int i2 = columnIndexOrThrow8;
                    int i3 = columnIndexOrThrow9;
                    long j2 = cursorQuery.getLong(columnIndexOrThrow);
                    if (longSparseArray.containsKey(j2)) {
                        i = columnIndexOrThrow7;
                    } else {
                        i = columnIndexOrThrow7;
                        longSparseArray.put(j2, new ArrayList());
                    }
                    long j3 = cursorQuery.getLong(columnIndexOrThrow);
                    if (!longSparseArray2.containsKey(j3)) {
                        longSparseArray2.put(j3, new ArrayList());
                    }
                    long j4 = cursorQuery.getLong(columnIndexOrThrow);
                    if (!longSparseArray3.containsKey(j4)) {
                        longSparseArray3.put(j4, new ArrayList());
                    }
                    long j5 = cursorQuery.getLong(columnIndexOrThrow);
                    if (!longSparseArray4.containsKey(j5)) {
                        longSparseArray4.put(j5, new ArrayList());
                    }
                    longSparseArray5.put(cursorQuery.getLong(columnIndexOrThrow), null);
                    columnIndexOrThrow8 = i2;
                    columnIndexOrThrow9 = i3;
                    columnIndexOrThrow7 = i;
                }
                int i4 = columnIndexOrThrow7;
                int i5 = columnIndexOrThrow8;
                int i6 = columnIndexOrThrow9;
                cursorQuery.moveToPosition(-1);
                __fetchRelationshipMirrorAsorgFdroidDatabaseMirror(longSparseArray);
                __fetchRelationshipAntiFeatureAsorgFdroidDatabaseAntiFeature(longSparseArray2);
                __fetchRelationshipCategoryAsorgFdroidDatabaseCategory(longSparseArray3);
                __fetchRelationshipReleaseChannelAsorgFdroidDatabaseReleaseChannel(longSparseArray4);
                __fetchRelationshipRepositoryPreferencesAsorgFdroidDatabaseRepositoryPreferences(longSparseArray5);
                if (cursorQuery.moveToFirst()) {
                    long j6 = cursorQuery.getLong(columnIndexOrThrow);
                    String string = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                    Converters converters = Converters.INSTANCE;
                    repository = new Repository(new CoreRepository(j6, converters.fromStringToLocalizedTextV2(string), converters.fromStringToLocalizedFileV2(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3)), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.getLong(columnIndexOrThrow6), cursorQuery.isNull(i4) ? null : Long.valueOf(cursorQuery.getLong(i4)), cursorQuery.isNull(i5) ? null : __IndexFormatVersion_stringToEnum(cursorQuery.getString(i5)), cursorQuery.isNull(i6) ? null : Integer.valueOf(cursorQuery.getInt(i6)), converters.fromStringToLocalizedTextV2(cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10)), cursorQuery.isNull(columnIndexOrThrow11) ? null : cursorQuery.getString(columnIndexOrThrow11)), (ArrayList) longSparseArray.get(cursorQuery.getLong(columnIndexOrThrow)), (ArrayList) longSparseArray2.get(cursorQuery.getLong(columnIndexOrThrow)), (ArrayList) longSparseArray3.get(cursorQuery.getLong(columnIndexOrThrow)), (ArrayList) longSparseArray4.get(cursorQuery.getLong(columnIndexOrThrow)), (RepositoryPreferences) longSparseArray5.get(cursorQuery.getLong(columnIndexOrThrow)));
                } else {
                    repository = null;
                }
                this.__db.setTransactionSuccessful();
                cursorQuery.close();
                roomSQLiteQuery.release();
                return repository;
            } catch (Throwable th2) {
                th = th2;
                cursorQuery.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public Repository getRepository(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        LongSparseArray longSparseArray;
        LongSparseArray longSparseArray2;
        Repository repository;
        int i;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM CoreRepository\n        WHERE certificate = ? AND address NOT LIKE \"%/archive\" COLLATE NOCASE\n        LIMIT 1", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, true, null);
            try {
                columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "repoId");
                columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "name");
                columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "icon");
                columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "address");
                columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "webBaseUrl");
                columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "timestamp");
                columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "version");
                columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "formatVersion");
                columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "maxAge");
                columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "description");
                columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "certificate");
                longSparseArray = new LongSparseArray();
                longSparseArray2 = new LongSparseArray();
                roomSQLiteQuery = roomSQLiteQueryAcquire;
            } catch (Throwable th) {
                th = th;
                roomSQLiteQuery = roomSQLiteQueryAcquire;
            }
            try {
                LongSparseArray longSparseArray3 = new LongSparseArray();
                LongSparseArray longSparseArray4 = new LongSparseArray();
                LongSparseArray longSparseArray5 = new LongSparseArray();
                while (cursorQuery.moveToNext()) {
                    int i2 = columnIndexOrThrow8;
                    int i3 = columnIndexOrThrow9;
                    long j = cursorQuery.getLong(columnIndexOrThrow);
                    if (longSparseArray.containsKey(j)) {
                        i = columnIndexOrThrow7;
                    } else {
                        i = columnIndexOrThrow7;
                        longSparseArray.put(j, new ArrayList());
                    }
                    long j2 = cursorQuery.getLong(columnIndexOrThrow);
                    if (!longSparseArray2.containsKey(j2)) {
                        longSparseArray2.put(j2, new ArrayList());
                    }
                    long j3 = cursorQuery.getLong(columnIndexOrThrow);
                    if (!longSparseArray3.containsKey(j3)) {
                        longSparseArray3.put(j3, new ArrayList());
                    }
                    long j4 = cursorQuery.getLong(columnIndexOrThrow);
                    if (!longSparseArray4.containsKey(j4)) {
                        longSparseArray4.put(j4, new ArrayList());
                    }
                    longSparseArray5.put(cursorQuery.getLong(columnIndexOrThrow), null);
                    columnIndexOrThrow8 = i2;
                    columnIndexOrThrow9 = i3;
                    columnIndexOrThrow7 = i;
                }
                int i4 = columnIndexOrThrow7;
                int i5 = columnIndexOrThrow8;
                int i6 = columnIndexOrThrow9;
                cursorQuery.moveToPosition(-1);
                __fetchRelationshipMirrorAsorgFdroidDatabaseMirror(longSparseArray);
                __fetchRelationshipAntiFeatureAsorgFdroidDatabaseAntiFeature(longSparseArray2);
                __fetchRelationshipCategoryAsorgFdroidDatabaseCategory(longSparseArray3);
                __fetchRelationshipReleaseChannelAsorgFdroidDatabaseReleaseChannel(longSparseArray4);
                __fetchRelationshipRepositoryPreferencesAsorgFdroidDatabaseRepositoryPreferences(longSparseArray5);
                if (cursorQuery.moveToFirst()) {
                    long j5 = cursorQuery.getLong(columnIndexOrThrow);
                    String string = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                    Converters converters = Converters.INSTANCE;
                    repository = new Repository(new CoreRepository(j5, converters.fromStringToLocalizedTextV2(string), converters.fromStringToLocalizedFileV2(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3)), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.getLong(columnIndexOrThrow6), cursorQuery.isNull(i4) ? null : Long.valueOf(cursorQuery.getLong(i4)), cursorQuery.isNull(i5) ? null : __IndexFormatVersion_stringToEnum(cursorQuery.getString(i5)), cursorQuery.isNull(i6) ? null : Integer.valueOf(cursorQuery.getInt(i6)), converters.fromStringToLocalizedTextV2(cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10)), cursorQuery.isNull(columnIndexOrThrow11) ? null : cursorQuery.getString(columnIndexOrThrow11)), (ArrayList) longSparseArray.get(cursorQuery.getLong(columnIndexOrThrow)), (ArrayList) longSparseArray2.get(cursorQuery.getLong(columnIndexOrThrow)), (ArrayList) longSparseArray3.get(cursorQuery.getLong(columnIndexOrThrow)), (ArrayList) longSparseArray4.get(cursorQuery.getLong(columnIndexOrThrow)), (RepositoryPreferences) longSparseArray5.get(cursorQuery.getLong(columnIndexOrThrow)));
                } else {
                    repository = null;
                }
                this.__db.setTransactionSuccessful();
                cursorQuery.close();
                roomSQLiteQuery.release();
                return repository;
            } catch (Throwable th2) {
                th = th2;
                cursorQuery.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt, org.fdroid.database.RepositoryDao
    public List<Repository> getRepositories() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT `repoId`, `name`, `icon`, `address`, `webBaseUrl`, `timestamp`, `version`, `formatVersion`, `maxAge`, `description`, `certificate` FROM (SELECT * FROM CoreRepository\n        JOIN RepositoryPreferences AS pref USING (repoId)\n        ORDER BY pref.weight DESC)", 0);
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, true, null);
            try {
                LongSparseArray longSparseArray = new LongSparseArray();
                LongSparseArray longSparseArray2 = new LongSparseArray();
                LongSparseArray longSparseArray3 = new LongSparseArray();
                LongSparseArray longSparseArray4 = new LongSparseArray();
                LongSparseArray longSparseArray5 = new LongSparseArray();
                while (cursorQuery.moveToNext()) {
                    long j = cursorQuery.getLong(0);
                    if (!longSparseArray.containsKey(j)) {
                        longSparseArray.put(j, new ArrayList());
                    }
                    long j2 = cursorQuery.getLong(0);
                    if (!longSparseArray2.containsKey(j2)) {
                        longSparseArray2.put(j2, new ArrayList());
                    }
                    long j3 = cursorQuery.getLong(0);
                    if (!longSparseArray3.containsKey(j3)) {
                        longSparseArray3.put(j3, new ArrayList());
                    }
                    long j4 = cursorQuery.getLong(0);
                    if (!longSparseArray4.containsKey(j4)) {
                        longSparseArray4.put(j4, new ArrayList());
                    }
                    longSparseArray5.put(cursorQuery.getLong(0), null);
                }
                cursorQuery.moveToPosition(-1);
                __fetchRelationshipMirrorAsorgFdroidDatabaseMirror(longSparseArray);
                __fetchRelationshipAntiFeatureAsorgFdroidDatabaseAntiFeature(longSparseArray2);
                __fetchRelationshipCategoryAsorgFdroidDatabaseCategory(longSparseArray3);
                __fetchRelationshipReleaseChannelAsorgFdroidDatabaseReleaseChannel(longSparseArray4);
                __fetchRelationshipRepositoryPreferencesAsorgFdroidDatabaseRepositoryPreferences(longSparseArray5);
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    long j5 = cursorQuery.getLong(0);
                    String string = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                    Converters converters = Converters.INSTANCE;
                    arrayList.add(new Repository(new CoreRepository(j5, converters.fromStringToLocalizedTextV2(string), converters.fromStringToLocalizedFileV2(cursorQuery.isNull(2) ? null : cursorQuery.getString(2)), cursorQuery.isNull(3) ? null : cursorQuery.getString(3), cursorQuery.isNull(4) ? null : cursorQuery.getString(4), cursorQuery.getLong(5), cursorQuery.isNull(6) ? null : Long.valueOf(cursorQuery.getLong(6)), cursorQuery.isNull(7) ? null : __IndexFormatVersion_stringToEnum(cursorQuery.getString(7)), cursorQuery.isNull(8) ? null : Integer.valueOf(cursorQuery.getInt(8)), converters.fromStringToLocalizedTextV2(cursorQuery.isNull(9) ? null : cursorQuery.getString(9)), cursorQuery.isNull(10) ? null : cursorQuery.getString(10)), (ArrayList) longSparseArray.get(cursorQuery.getLong(0)), (ArrayList) longSparseArray2.get(cursorQuery.getLong(0)), (ArrayList) longSparseArray3.get(cursorQuery.getLong(0)), (ArrayList) longSparseArray4.get(cursorQuery.getLong(0)), (RepositoryPreferences) longSparseArray5.get(cursorQuery.getLong(0))));
                }
                this.__db.setTransactionSuccessful();
                cursorQuery.close();
                roomSQLiteQueryAcquire.release();
                return arrayList;
            } catch (Throwable th) {
                cursorQuery.close();
                roomSQLiteQueryAcquire.release();
                throw th;
            }
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt, org.fdroid.database.RepositoryDao
    public LiveData getLiveRepositories() {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT `repoId`, `name`, `icon`, `address`, `webBaseUrl`, `timestamp`, `version`, `formatVersion`, `maxAge`, `description`, `certificate` FROM (SELECT * FROM CoreRepository\n        JOIN RepositoryPreferences AS pref USING (repoId)\n        ORDER BY pref.weight DESC)", 0);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{Mirror.TABLE, AntiFeature.TABLE, Category.TABLE, ReleaseChannel.TABLE, RepositoryPreferences.TABLE, CoreRepository.TABLE}, true, new Callable() { // from class: org.fdroid.database.RepositoryDaoInt_Impl.29
            @Override // java.util.concurrent.Callable
            public List call() {
                RepositoryDaoInt_Impl.this.__db.beginTransaction();
                try {
                    int i = 1;
                    Cursor cursorQuery = DBUtil.query(RepositoryDaoInt_Impl.this.__db, roomSQLiteQueryAcquire, true, null);
                    try {
                        LongSparseArray longSparseArray = new LongSparseArray();
                        LongSparseArray longSparseArray2 = new LongSparseArray();
                        LongSparseArray longSparseArray3 = new LongSparseArray();
                        LongSparseArray longSparseArray4 = new LongSparseArray();
                        LongSparseArray longSparseArray5 = new LongSparseArray();
                        while (cursorQuery.moveToNext()) {
                            long j = cursorQuery.getLong(0);
                            if (!longSparseArray.containsKey(j)) {
                                longSparseArray.put(j, new ArrayList());
                            }
                            long j2 = cursorQuery.getLong(0);
                            if (!longSparseArray2.containsKey(j2)) {
                                longSparseArray2.put(j2, new ArrayList());
                            }
                            long j3 = cursorQuery.getLong(0);
                            if (!longSparseArray3.containsKey(j3)) {
                                longSparseArray3.put(j3, new ArrayList());
                            }
                            long j4 = cursorQuery.getLong(0);
                            if (!longSparseArray4.containsKey(j4)) {
                                longSparseArray4.put(j4, new ArrayList());
                            }
                            longSparseArray5.put(cursorQuery.getLong(0), null);
                        }
                        cursorQuery.moveToPosition(-1);
                        RepositoryDaoInt_Impl.this.__fetchRelationshipMirrorAsorgFdroidDatabaseMirror(longSparseArray);
                        RepositoryDaoInt_Impl.this.__fetchRelationshipAntiFeatureAsorgFdroidDatabaseAntiFeature(longSparseArray2);
                        RepositoryDaoInt_Impl.this.__fetchRelationshipCategoryAsorgFdroidDatabaseCategory(longSparseArray3);
                        RepositoryDaoInt_Impl.this.__fetchRelationshipReleaseChannelAsorgFdroidDatabaseReleaseChannel(longSparseArray4);
                        RepositoryDaoInt_Impl.this.__fetchRelationshipRepositoryPreferencesAsorgFdroidDatabaseRepositoryPreferences(longSparseArray5);
                        ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                        while (cursorQuery.moveToNext()) {
                            long j5 = cursorQuery.getLong(0);
                            String string = cursorQuery.isNull(i) ? null : cursorQuery.getString(i);
                            Converters converters = Converters.INSTANCE;
                            arrayList.add(new Repository(new CoreRepository(j5, converters.fromStringToLocalizedTextV2(string), converters.fromStringToLocalizedFileV2(cursorQuery.isNull(2) ? null : cursorQuery.getString(2)), cursorQuery.isNull(3) ? null : cursorQuery.getString(3), cursorQuery.isNull(4) ? null : cursorQuery.getString(4), cursorQuery.getLong(5), cursorQuery.isNull(6) ? null : Long.valueOf(cursorQuery.getLong(6)), cursorQuery.isNull(7) ? null : RepositoryDaoInt_Impl.this.__IndexFormatVersion_stringToEnum(cursorQuery.getString(7)), cursorQuery.isNull(8) ? null : Integer.valueOf(cursorQuery.getInt(8)), converters.fromStringToLocalizedTextV2(cursorQuery.isNull(9) ? null : cursorQuery.getString(9)), cursorQuery.isNull(10) ? null : cursorQuery.getString(10)), (ArrayList) longSparseArray.get(cursorQuery.getLong(0)), (ArrayList) longSparseArray2.get(cursorQuery.getLong(0)), (ArrayList) longSparseArray3.get(cursorQuery.getLong(0)), (ArrayList) longSparseArray4.get(cursorQuery.getLong(0)), (RepositoryPreferences) longSparseArray5.get(cursorQuery.getLong(0))));
                            i = 1;
                        }
                        RepositoryDaoInt_Impl.this.__db.setTransactionSuccessful();
                        cursorQuery.close();
                        return arrayList;
                    } catch (Throwable th) {
                        cursorQuery.close();
                        throw th;
                    }
                } finally {
                    RepositoryDaoInt_Impl.this.__db.endTransaction();
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    @Override // org.fdroid.database.RepositoryDaoInt, org.fdroid.database.RepositoryDao
    public LiveData getLiveCategories() {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT `repoId`, `id`, `icon`, `name`, `description` FROM (SELECT * FROM Category\n        JOIN RepositoryPreferences AS pref USING (repoId)\n        WHERE pref.enabled = 1 GROUP BY id HAVING MAX(pref.weight))", 0);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{Category.TABLE, RepositoryPreferences.TABLE}, false, new Callable() { // from class: org.fdroid.database.RepositoryDaoInt_Impl.30
            @Override // java.util.concurrent.Callable
            public List call() {
                Cursor cursorQuery = DBUtil.query(RepositoryDaoInt_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
                try {
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        long j = cursorQuery.getLong(0);
                        String string = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                        String string2 = cursorQuery.isNull(2) ? null : cursorQuery.getString(2);
                        Converters converters = Converters.INSTANCE;
                        arrayList.add(new Category(j, string, converters.fromStringToLocalizedFileV2(string2), converters.fromStringToLocalizedTextV2(cursorQuery.isNull(3) ? null : cursorQuery.getString(3)), converters.fromStringToLocalizedTextV2(cursorQuery.isNull(4) ? null : cursorQuery.getString(4))));
                    }
                    return arrayList;
                } finally {
                    cursorQuery.close();
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public int getMinRepositoryWeight() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT COALESCE(MIN(weight), 2147483647) FROM RepositoryPreferences", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            return cursorQuery.moveToFirst() ? cursorQuery.getInt(0) : 0;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public RepositoryPreferences getRepositoryPreferences(long j) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM RepositoryPreferences WHERE repoId = ?", 1);
        roomSQLiteQueryAcquire.bindLong(1, j);
        this.__db.assertNotSuspendingTransaction();
        RepositoryPreferences repositoryPreferences = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "repoId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "weight");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "enabled");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, AppListActivity.SortClause.LAST_UPDATED);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "lastETag");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "userMirrors");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "disabledMirrors");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "username");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "password");
            if (cursorQuery.moveToFirst()) {
                long j2 = cursorQuery.getLong(columnIndexOrThrow);
                int i = cursorQuery.getInt(columnIndexOrThrow2);
                boolean z = cursorQuery.getInt(columnIndexOrThrow3) != 0;
                Long lValueOf = cursorQuery.isNull(columnIndexOrThrow4) ? null : Long.valueOf(cursorQuery.getLong(columnIndexOrThrow4));
                String string = cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5);
                String string2 = cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6);
                Converters converters = Converters.INSTANCE;
                repositoryPreferences = new RepositoryPreferences(j2, i, z, lValueOf, string, converters.fromStringToListString(string2), converters.fromStringToListString(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7)), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9));
            }
            return repositoryPreferences;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public Long getArchiveRepoId(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT repoId FROM CoreRepository\n        WHERE certificate = ? AND address LIKE '%/archive' COLLATE NOCASE", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Long lValueOf = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            if (cursorQuery.moveToFirst() && !cursorQuery.isNull(0)) {
                lValueOf = Long.valueOf(cursorQuery.getLong(0));
            }
            return lValueOf;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public int countMirrors() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM Mirror", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            return cursorQuery.moveToFirst() ? cursorQuery.getInt(0) : 0;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public int countAntiFeatures() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM AntiFeature", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            return cursorQuery.moveToFirst() ? cursorQuery.getInt(0) : 0;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public int countCategories() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM Category", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            return cursorQuery.moveToFirst() ? cursorQuery.getInt(0) : 0;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public int countReleaseChannels() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM ReleaseChannel", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            return cursorQuery.moveToFirst() ? cursorQuery.getInt(0) : 0;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt
    public int rawCheckpoint(SupportSQLiteQuery supportSQLiteQuery) {
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, supportSQLiteQuery, false, null);
        try {
            return cursorQuery.moveToFirst() ? cursorQuery.getInt(0) : 0;
        } finally {
            cursorQuery.close();
        }
    }

    @Override // org.fdroid.database.RepositoryDaoInt, org.fdroid.database.RepositoryDao
    public void walCheckpoint() {
        RepositoryDaoInt.DefaultImpls.walCheckpoint(this);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    /* JADX INFO: renamed from: org.fdroid.database.RepositoryDaoInt_Impl$31, reason: invalid class name */
    static /* synthetic */ class AnonymousClass31 {
        static final /* synthetic */ int[] $SwitchMap$org$fdroid$index$IndexFormatVersion;

        static {
            int[] iArr = new int[IndexFormatVersion.values().length];
            $SwitchMap$org$fdroid$index$IndexFormatVersion = iArr;
            try {
                iArr[IndexFormatVersion.ONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$fdroid$index$IndexFormatVersion[IndexFormatVersion.TWO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String __IndexFormatVersion_enumToString(IndexFormatVersion indexFormatVersion) {
        int i = AnonymousClass31.$SwitchMap$org$fdroid$index$IndexFormatVersion[indexFormatVersion.ordinal()];
        if (i == 1) {
            return "ONE";
        }
        if (i == 2) {
            return "TWO";
        }
        throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + indexFormatVersion);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipMirrorAsorgFdroidDatabaseMirror(LongSparseArray longSparseArray) {
        if (longSparseArray.isEmpty()) {
            return;
        }
        if (longSparseArray.size() > 999) {
            RelationUtil.recursiveFetchLongSparseArray(longSparseArray, true, new Function1() { // from class: org.fdroid.database.RepositoryDaoInt_Impl$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return this.f$0.lambda$__fetchRelationshipMirrorAsorgFdroidDatabaseMirror$0((LongSparseArray) obj);
                }
            });
            return;
        }
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("SELECT `repoId`,`url`,`countryCode` FROM `Mirror` WHERE `repoId` IN (");
        int size = longSparseArray.size();
        StringUtil.appendPlaceholders(sbNewStringBuilder, size);
        sbNewStringBuilder.append(")");
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire(sbNewStringBuilder.toString(), size);
        int i = 1;
        for (int i2 = 0; i2 < longSparseArray.size(); i2++) {
            roomSQLiteQueryAcquire.bindLong(i, longSparseArray.keyAt(i2));
            i++;
        }
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(cursorQuery, "repoId");
            if (columnIndex == -1) {
                return;
            }
            while (cursorQuery.moveToNext()) {
                ArrayList arrayList = (ArrayList) longSparseArray.get(cursorQuery.getLong(columnIndex));
                if (arrayList != null) {
                    arrayList.add(new Mirror(cursorQuery.getLong(0), cursorQuery.isNull(1) ? null : cursorQuery.getString(1), cursorQuery.isNull(2) ? null : cursorQuery.getString(2)));
                }
            }
        } finally {
            cursorQuery.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$__fetchRelationshipMirrorAsorgFdroidDatabaseMirror$0(LongSparseArray longSparseArray) {
        __fetchRelationshipMirrorAsorgFdroidDatabaseMirror(longSparseArray);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipAntiFeatureAsorgFdroidDatabaseAntiFeature(LongSparseArray longSparseArray) {
        if (longSparseArray.isEmpty()) {
            return;
        }
        if (longSparseArray.size() > 999) {
            RelationUtil.recursiveFetchLongSparseArray(longSparseArray, true, new Function1() { // from class: org.fdroid.database.RepositoryDaoInt_Impl$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return this.f$0.lambda$__fetchRelationshipAntiFeatureAsorgFdroidDatabaseAntiFeature$1((LongSparseArray) obj);
                }
            });
            return;
        }
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("SELECT `repoId`,`id`,`icon`,`name`,`description` FROM `AntiFeature` WHERE `repoId` IN (");
        int size = longSparseArray.size();
        StringUtil.appendPlaceholders(sbNewStringBuilder, size);
        sbNewStringBuilder.append(")");
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire(sbNewStringBuilder.toString(), size);
        int i = 0;
        int i2 = 1;
        for (int i3 = 0; i3 < longSparseArray.size(); i3++) {
            roomSQLiteQueryAcquire.bindLong(i2, longSparseArray.keyAt(i3));
            i2++;
        }
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(cursorQuery, "repoId");
            if (columnIndex == -1) {
                return;
            }
            while (cursorQuery.moveToNext()) {
                ArrayList arrayList = (ArrayList) longSparseArray.get(cursorQuery.getLong(columnIndex));
                if (arrayList != null) {
                    long j = cursorQuery.getLong(i);
                    String string = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                    String string2 = cursorQuery.isNull(2) ? null : cursorQuery.getString(2);
                    Converters converters = Converters.INSTANCE;
                    arrayList.add(new AntiFeature(j, string, converters.fromStringToLocalizedFileV2(string2), converters.fromStringToLocalizedTextV2(cursorQuery.isNull(3) ? null : cursorQuery.getString(3)), converters.fromStringToLocalizedTextV2(cursorQuery.isNull(4) ? null : cursorQuery.getString(4))));
                }
                i = 0;
            }
        } finally {
            cursorQuery.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$__fetchRelationshipAntiFeatureAsorgFdroidDatabaseAntiFeature$1(LongSparseArray longSparseArray) {
        __fetchRelationshipAntiFeatureAsorgFdroidDatabaseAntiFeature(longSparseArray);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipCategoryAsorgFdroidDatabaseCategory(LongSparseArray longSparseArray) {
        if (longSparseArray.isEmpty()) {
            return;
        }
        if (longSparseArray.size() > 999) {
            RelationUtil.recursiveFetchLongSparseArray(longSparseArray, true, new Function1() { // from class: org.fdroid.database.RepositoryDaoInt_Impl$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return this.f$0.lambda$__fetchRelationshipCategoryAsorgFdroidDatabaseCategory$2((LongSparseArray) obj);
                }
            });
            return;
        }
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("SELECT `repoId`,`id`,`icon`,`name`,`description` FROM `Category` WHERE `repoId` IN (");
        int size = longSparseArray.size();
        StringUtil.appendPlaceholders(sbNewStringBuilder, size);
        sbNewStringBuilder.append(")");
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire(sbNewStringBuilder.toString(), size);
        int i = 0;
        int i2 = 1;
        for (int i3 = 0; i3 < longSparseArray.size(); i3++) {
            roomSQLiteQueryAcquire.bindLong(i2, longSparseArray.keyAt(i3));
            i2++;
        }
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(cursorQuery, "repoId");
            if (columnIndex == -1) {
                return;
            }
            while (cursorQuery.moveToNext()) {
                ArrayList arrayList = (ArrayList) longSparseArray.get(cursorQuery.getLong(columnIndex));
                if (arrayList != null) {
                    long j = cursorQuery.getLong(i);
                    String string = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                    String string2 = cursorQuery.isNull(2) ? null : cursorQuery.getString(2);
                    Converters converters = Converters.INSTANCE;
                    arrayList.add(new Category(j, string, converters.fromStringToLocalizedFileV2(string2), converters.fromStringToLocalizedTextV2(cursorQuery.isNull(3) ? null : cursorQuery.getString(3)), converters.fromStringToLocalizedTextV2(cursorQuery.isNull(4) ? null : cursorQuery.getString(4))));
                }
                i = 0;
            }
        } finally {
            cursorQuery.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$__fetchRelationshipCategoryAsorgFdroidDatabaseCategory$2(LongSparseArray longSparseArray) {
        __fetchRelationshipCategoryAsorgFdroidDatabaseCategory(longSparseArray);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipReleaseChannelAsorgFdroidDatabaseReleaseChannel(LongSparseArray longSparseArray) {
        if (longSparseArray.isEmpty()) {
            return;
        }
        if (longSparseArray.size() > 999) {
            RelationUtil.recursiveFetchLongSparseArray(longSparseArray, true, new Function1() { // from class: org.fdroid.database.RepositoryDaoInt_Impl$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return this.f$0.lambda$__fetchRelationshipReleaseChannelAsorgFdroidDatabaseReleaseChannel$3((LongSparseArray) obj);
                }
            });
            return;
        }
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("SELECT `repoId`,`id`,`icon`,`name`,`description` FROM `ReleaseChannel` WHERE `repoId` IN (");
        int size = longSparseArray.size();
        StringUtil.appendPlaceholders(sbNewStringBuilder, size);
        sbNewStringBuilder.append(")");
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire(sbNewStringBuilder.toString(), size);
        int i = 0;
        int i2 = 1;
        for (int i3 = 0; i3 < longSparseArray.size(); i3++) {
            roomSQLiteQueryAcquire.bindLong(i2, longSparseArray.keyAt(i3));
            i2++;
        }
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(cursorQuery, "repoId");
            if (columnIndex == -1) {
                return;
            }
            while (cursorQuery.moveToNext()) {
                ArrayList arrayList = (ArrayList) longSparseArray.get(cursorQuery.getLong(columnIndex));
                if (arrayList != null) {
                    long j = cursorQuery.getLong(i);
                    String string = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                    String string2 = cursorQuery.isNull(2) ? null : cursorQuery.getString(2);
                    Converters converters = Converters.INSTANCE;
                    arrayList.add(new ReleaseChannel(j, string, converters.fromStringToLocalizedFileV2(string2), converters.fromStringToLocalizedTextV2(cursorQuery.isNull(3) ? null : cursorQuery.getString(3)), converters.fromStringToLocalizedTextV2(cursorQuery.isNull(4) ? null : cursorQuery.getString(4))));
                }
                i = 0;
            }
        } finally {
            cursorQuery.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$__fetchRelationshipReleaseChannelAsorgFdroidDatabaseReleaseChannel$3(LongSparseArray longSparseArray) {
        __fetchRelationshipReleaseChannelAsorgFdroidDatabaseReleaseChannel(longSparseArray);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipRepositoryPreferencesAsorgFdroidDatabaseRepositoryPreferences(LongSparseArray longSparseArray) {
        if (longSparseArray.isEmpty()) {
            return;
        }
        if (longSparseArray.size() > 999) {
            RelationUtil.recursiveFetchLongSparseArray(longSparseArray, false, new Function1() { // from class: org.fdroid.database.RepositoryDaoInt_Impl$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return this.f$0.lambda$__fetchRelationshipRepositoryPreferencesAsorgFdroidDatabaseRepositoryPreferences$4((LongSparseArray) obj);
                }
            });
            return;
        }
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("SELECT `repoId`,`weight`,`enabled`,`lastUpdated`,`lastETag`,`userMirrors`,`disabledMirrors`,`username`,`password` FROM `RepositoryPreferences` WHERE `repoId` IN (");
        int size = longSparseArray.size();
        StringUtil.appendPlaceholders(sbNewStringBuilder, size);
        sbNewStringBuilder.append(")");
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire(sbNewStringBuilder.toString(), size);
        int i = 1;
        for (int i2 = 0; i2 < longSparseArray.size(); i2++) {
            roomSQLiteQueryAcquire.bindLong(i, longSparseArray.keyAt(i2));
            i++;
        }
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(cursorQuery, "repoId");
            if (columnIndex == -1) {
                return;
            }
            while (cursorQuery.moveToNext()) {
                long j = cursorQuery.getLong(columnIndex);
                if (longSparseArray.containsKey(j)) {
                    long j2 = cursorQuery.getLong(0);
                    int i3 = cursorQuery.getInt(1);
                    boolean z = cursorQuery.getInt(2) != 0;
                    Long lValueOf = cursorQuery.isNull(3) ? null : Long.valueOf(cursorQuery.getLong(3));
                    String string = cursorQuery.isNull(4) ? null : cursorQuery.getString(4);
                    String string2 = cursorQuery.isNull(5) ? null : cursorQuery.getString(5);
                    Converters converters = Converters.INSTANCE;
                    longSparseArray.put(j, new RepositoryPreferences(j2, i3, z, lValueOf, string, converters.fromStringToListString(string2), converters.fromStringToListString(cursorQuery.isNull(6) ? null : cursorQuery.getString(6)), cursorQuery.isNull(7) ? null : cursorQuery.getString(7), cursorQuery.isNull(8) ? null : cursorQuery.getString(8)));
                }
            }
        } finally {
            cursorQuery.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$__fetchRelationshipRepositoryPreferencesAsorgFdroidDatabaseRepositoryPreferences$4(LongSparseArray longSparseArray) {
        __fetchRelationshipRepositoryPreferencesAsorgFdroidDatabaseRepositoryPreferences(longSparseArray);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IndexFormatVersion __IndexFormatVersion_stringToEnum(String str) {
        str.hashCode();
        if (str.equals("ONE")) {
            return IndexFormatVersion.ONE;
        }
        if (str.equals("TWO")) {
            return IndexFormatVersion.TWO;
        }
        throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + str);
    }
}
