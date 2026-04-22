package org.fdroid.database;

import android.content.pm.PackageManager;
import android.database.Cursor;
import androidx.collection.ArrayMap;
import androidx.core.os.LocaleListCompat;
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
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.serialization.json.JsonObject;
import org.bouncycastle.i18n.ErrorBundle;
import org.fdroid.database.AppDaoInt;
import org.fdroid.fdroid.nearby.peers.BonjourPeer;
import org.fdroid.fdroid.views.apps.AppListActivity;
import org.fdroid.index.v2.MetadataV2;

/* JADX INFO: loaded from: classes2.dex */
public final class AppDaoInt_Impl implements AppDaoInt {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter __insertionAdapterOfAppMetadata;
    private final EntityInsertionAdapter __insertionAdapterOfLocalizedFile;
    private final EntityInsertionAdapter __insertionAdapterOfLocalizedFileList;
    private final SharedSQLiteStatement __preparedStmtOfClearAll;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAppMetadata;
    private final SharedSQLiteStatement __preparedStmtOfDeleteLocalizedFile;
    private final SharedSQLiteStatement __preparedStmtOfDeleteLocalizedFileList;
    private final SharedSQLiteStatement __preparedStmtOfDeleteLocalizedFileLists;
    private final SharedSQLiteStatement __preparedStmtOfDeleteLocalizedFileLists_1;
    private final SharedSQLiteStatement __preparedStmtOfDeleteLocalizedFiles;
    private final SharedSQLiteStatement __preparedStmtOfUpdateAppMetadata;
    private final SharedSQLiteStatement __preparedStmtOfUpdateCompatibility;
    private final SharedSQLiteStatement __preparedStmtOfUpdatePreferredSigner;
    private final EntityDeletionOrUpdateAdapter __updateAdapterOfAppMetadata;

    public AppDaoInt_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfAppMetadata = new EntityInsertionAdapter(roomDatabase) { // from class: org.fdroid.database.AppDaoInt_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `AppMetadata` (`repoId`,`packageName`,`added`,`lastUpdated`,`name`,`summary`,`description`,`localizedName`,`localizedSummary`,`webSite`,`changelog`,`license`,`sourceCode`,`issueTracker`,`translation`,`preferredSigner`,`video`,`authorName`,`authorEmail`,`authorWebSite`,`authorPhone`,`donate`,`liberapayID`,`liberapay`,`openCollective`,`bitcoin`,`litecoin`,`flattrID`,`categories`,`isCompatible`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, AppMetadata appMetadata) {
                supportSQLiteStatement.bindLong(1, appMetadata.getRepoId());
                if (appMetadata.getPackageName() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, appMetadata.getPackageName());
                }
                supportSQLiteStatement.bindLong(3, appMetadata.getAdded());
                supportSQLiteStatement.bindLong(4, appMetadata.getLastUpdated());
                Converters converters = Converters.INSTANCE;
                String strLocalizedTextV2toString = converters.localizedTextV2toString(appMetadata.getName());
                if (strLocalizedTextV2toString == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, strLocalizedTextV2toString);
                }
                String strLocalizedTextV2toString2 = converters.localizedTextV2toString(appMetadata.getSummary());
                if (strLocalizedTextV2toString2 == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, strLocalizedTextV2toString2);
                }
                String strLocalizedTextV2toString3 = converters.localizedTextV2toString(appMetadata.getDescription());
                if (strLocalizedTextV2toString3 == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, strLocalizedTextV2toString3);
                }
                if (appMetadata.getLocalizedName() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, appMetadata.getLocalizedName());
                }
                if (appMetadata.getLocalizedSummary() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, appMetadata.getLocalizedSummary());
                }
                if (appMetadata.getWebSite() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, appMetadata.getWebSite());
                }
                if (appMetadata.getChangelog() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, appMetadata.getChangelog());
                }
                if (appMetadata.getLicense() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, appMetadata.getLicense());
                }
                if (appMetadata.getSourceCode() == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindString(13, appMetadata.getSourceCode());
                }
                if (appMetadata.getIssueTracker() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, appMetadata.getIssueTracker());
                }
                if (appMetadata.getTranslation() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, appMetadata.getTranslation());
                }
                if (appMetadata.getPreferredSigner() == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, appMetadata.getPreferredSigner());
                }
                String strLocalizedTextV2toString4 = converters.localizedTextV2toString(appMetadata.getVideo());
                if (strLocalizedTextV2toString4 == null) {
                    supportSQLiteStatement.bindNull(17);
                } else {
                    supportSQLiteStatement.bindString(17, strLocalizedTextV2toString4);
                }
                if (appMetadata.getAuthorName() == null) {
                    supportSQLiteStatement.bindNull(18);
                } else {
                    supportSQLiteStatement.bindString(18, appMetadata.getAuthorName());
                }
                if (appMetadata.getAuthorEmail() == null) {
                    supportSQLiteStatement.bindNull(19);
                } else {
                    supportSQLiteStatement.bindString(19, appMetadata.getAuthorEmail());
                }
                if (appMetadata.getAuthorWebSite() == null) {
                    supportSQLiteStatement.bindNull(20);
                } else {
                    supportSQLiteStatement.bindString(20, appMetadata.getAuthorWebSite());
                }
                if (appMetadata.getAuthorPhone() == null) {
                    supportSQLiteStatement.bindNull(21);
                } else {
                    supportSQLiteStatement.bindString(21, appMetadata.getAuthorPhone());
                }
                String strListStringToString = converters.listStringToString(appMetadata.getDonate());
                if (strListStringToString == null) {
                    supportSQLiteStatement.bindNull(22);
                } else {
                    supportSQLiteStatement.bindString(22, strListStringToString);
                }
                if (appMetadata.getLiberapayID() == null) {
                    supportSQLiteStatement.bindNull(23);
                } else {
                    supportSQLiteStatement.bindString(23, appMetadata.getLiberapayID());
                }
                if (appMetadata.getLiberapay() == null) {
                    supportSQLiteStatement.bindNull(24);
                } else {
                    supportSQLiteStatement.bindString(24, appMetadata.getLiberapay());
                }
                if (appMetadata.getOpenCollective() == null) {
                    supportSQLiteStatement.bindNull(25);
                } else {
                    supportSQLiteStatement.bindString(25, appMetadata.getOpenCollective());
                }
                if (appMetadata.getBitcoin() == null) {
                    supportSQLiteStatement.bindNull(26);
                } else {
                    supportSQLiteStatement.bindString(26, appMetadata.getBitcoin());
                }
                if (appMetadata.getLitecoin() == null) {
                    supportSQLiteStatement.bindNull(27);
                } else {
                    supportSQLiteStatement.bindString(27, appMetadata.getLitecoin());
                }
                if (appMetadata.getFlattrID() == null) {
                    supportSQLiteStatement.bindNull(28);
                } else {
                    supportSQLiteStatement.bindString(28, appMetadata.getFlattrID());
                }
                String strListStringToString2 = converters.listStringToString(appMetadata.getCategories());
                if (strListStringToString2 == null) {
                    supportSQLiteStatement.bindNull(29);
                } else {
                    supportSQLiteStatement.bindString(29, strListStringToString2);
                }
                supportSQLiteStatement.bindLong(30, appMetadata.isCompatible() ? 1L : 0L);
            }
        };
        this.__insertionAdapterOfLocalizedFile = new EntityInsertionAdapter(roomDatabase) { // from class: org.fdroid.database.AppDaoInt_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `LocalizedFile` (`repoId`,`packageName`,`type`,`locale`,`name`,`sha256`,`size`,`ipfsCidV1`) VALUES (?,?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, LocalizedFile localizedFile) {
                supportSQLiteStatement.bindLong(1, localizedFile.getRepoId());
                if (localizedFile.getPackageName() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, localizedFile.getPackageName());
                }
                if (localizedFile.getType() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, localizedFile.getType());
                }
                if (localizedFile.getLocale() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, localizedFile.getLocale());
                }
                if (localizedFile.getName() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, localizedFile.getName());
                }
                if (localizedFile.getSha256() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, localizedFile.getSha256());
                }
                if (localizedFile.getSize() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindLong(7, localizedFile.getSize().longValue());
                }
                if (localizedFile.getIpfsCidV1() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, localizedFile.getIpfsCidV1());
                }
            }
        };
        this.__insertionAdapterOfLocalizedFileList = new EntityInsertionAdapter(roomDatabase) { // from class: org.fdroid.database.AppDaoInt_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `LocalizedFileList` (`repoId`,`packageName`,`type`,`locale`,`name`,`sha256`,`size`,`ipfsCidV1`) VALUES (?,?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, LocalizedFileList localizedFileList) {
                supportSQLiteStatement.bindLong(1, localizedFileList.getRepoId());
                if (localizedFileList.getPackageName() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, localizedFileList.getPackageName());
                }
                if (localizedFileList.getType() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, localizedFileList.getType());
                }
                if (localizedFileList.getLocale() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, localizedFileList.getLocale());
                }
                if (localizedFileList.getName() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, localizedFileList.getName());
                }
                if (localizedFileList.getSha256() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, localizedFileList.getSha256());
                }
                if (localizedFileList.getSize() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindLong(7, localizedFileList.getSize().longValue());
                }
                if (localizedFileList.getIpfsCidV1() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, localizedFileList.getIpfsCidV1());
                }
            }
        };
        this.__updateAdapterOfAppMetadata = new EntityDeletionOrUpdateAdapter(roomDatabase) { // from class: org.fdroid.database.AppDaoInt_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "UPDATE OR ABORT `AppMetadata` SET `repoId` = ?,`packageName` = ?,`added` = ?,`lastUpdated` = ?,`name` = ?,`summary` = ?,`description` = ?,`localizedName` = ?,`localizedSummary` = ?,`webSite` = ?,`changelog` = ?,`license` = ?,`sourceCode` = ?,`issueTracker` = ?,`translation` = ?,`preferredSigner` = ?,`video` = ?,`authorName` = ?,`authorEmail` = ?,`authorWebSite` = ?,`authorPhone` = ?,`donate` = ?,`liberapayID` = ?,`liberapay` = ?,`openCollective` = ?,`bitcoin` = ?,`litecoin` = ?,`flattrID` = ?,`categories` = ?,`isCompatible` = ? WHERE `repoId` = ? AND `packageName` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, AppMetadata appMetadata) {
                supportSQLiteStatement.bindLong(1, appMetadata.getRepoId());
                if (appMetadata.getPackageName() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, appMetadata.getPackageName());
                }
                supportSQLiteStatement.bindLong(3, appMetadata.getAdded());
                supportSQLiteStatement.bindLong(4, appMetadata.getLastUpdated());
                Converters converters = Converters.INSTANCE;
                String strLocalizedTextV2toString = converters.localizedTextV2toString(appMetadata.getName());
                if (strLocalizedTextV2toString == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, strLocalizedTextV2toString);
                }
                String strLocalizedTextV2toString2 = converters.localizedTextV2toString(appMetadata.getSummary());
                if (strLocalizedTextV2toString2 == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, strLocalizedTextV2toString2);
                }
                String strLocalizedTextV2toString3 = converters.localizedTextV2toString(appMetadata.getDescription());
                if (strLocalizedTextV2toString3 == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, strLocalizedTextV2toString3);
                }
                if (appMetadata.getLocalizedName() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, appMetadata.getLocalizedName());
                }
                if (appMetadata.getLocalizedSummary() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, appMetadata.getLocalizedSummary());
                }
                if (appMetadata.getWebSite() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, appMetadata.getWebSite());
                }
                if (appMetadata.getChangelog() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, appMetadata.getChangelog());
                }
                if (appMetadata.getLicense() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, appMetadata.getLicense());
                }
                if (appMetadata.getSourceCode() == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindString(13, appMetadata.getSourceCode());
                }
                if (appMetadata.getIssueTracker() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, appMetadata.getIssueTracker());
                }
                if (appMetadata.getTranslation() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, appMetadata.getTranslation());
                }
                if (appMetadata.getPreferredSigner() == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, appMetadata.getPreferredSigner());
                }
                String strLocalizedTextV2toString4 = converters.localizedTextV2toString(appMetadata.getVideo());
                if (strLocalizedTextV2toString4 == null) {
                    supportSQLiteStatement.bindNull(17);
                } else {
                    supportSQLiteStatement.bindString(17, strLocalizedTextV2toString4);
                }
                if (appMetadata.getAuthorName() == null) {
                    supportSQLiteStatement.bindNull(18);
                } else {
                    supportSQLiteStatement.bindString(18, appMetadata.getAuthorName());
                }
                if (appMetadata.getAuthorEmail() == null) {
                    supportSQLiteStatement.bindNull(19);
                } else {
                    supportSQLiteStatement.bindString(19, appMetadata.getAuthorEmail());
                }
                if (appMetadata.getAuthorWebSite() == null) {
                    supportSQLiteStatement.bindNull(20);
                } else {
                    supportSQLiteStatement.bindString(20, appMetadata.getAuthorWebSite());
                }
                if (appMetadata.getAuthorPhone() == null) {
                    supportSQLiteStatement.bindNull(21);
                } else {
                    supportSQLiteStatement.bindString(21, appMetadata.getAuthorPhone());
                }
                String strListStringToString = converters.listStringToString(appMetadata.getDonate());
                if (strListStringToString == null) {
                    supportSQLiteStatement.bindNull(22);
                } else {
                    supportSQLiteStatement.bindString(22, strListStringToString);
                }
                if (appMetadata.getLiberapayID() == null) {
                    supportSQLiteStatement.bindNull(23);
                } else {
                    supportSQLiteStatement.bindString(23, appMetadata.getLiberapayID());
                }
                if (appMetadata.getLiberapay() == null) {
                    supportSQLiteStatement.bindNull(24);
                } else {
                    supportSQLiteStatement.bindString(24, appMetadata.getLiberapay());
                }
                if (appMetadata.getOpenCollective() == null) {
                    supportSQLiteStatement.bindNull(25);
                } else {
                    supportSQLiteStatement.bindString(25, appMetadata.getOpenCollective());
                }
                if (appMetadata.getBitcoin() == null) {
                    supportSQLiteStatement.bindNull(26);
                } else {
                    supportSQLiteStatement.bindString(26, appMetadata.getBitcoin());
                }
                if (appMetadata.getLitecoin() == null) {
                    supportSQLiteStatement.bindNull(27);
                } else {
                    supportSQLiteStatement.bindString(27, appMetadata.getLitecoin());
                }
                if (appMetadata.getFlattrID() == null) {
                    supportSQLiteStatement.bindNull(28);
                } else {
                    supportSQLiteStatement.bindString(28, appMetadata.getFlattrID());
                }
                String strListStringToString2 = converters.listStringToString(appMetadata.getCategories());
                if (strListStringToString2 == null) {
                    supportSQLiteStatement.bindNull(29);
                } else {
                    supportSQLiteStatement.bindString(29, strListStringToString2);
                }
                supportSQLiteStatement.bindLong(30, appMetadata.isCompatible() ? 1L : 0L);
                supportSQLiteStatement.bindLong(31, appMetadata.getRepoId());
                if (appMetadata.getPackageName() == null) {
                    supportSQLiteStatement.bindNull(32);
                } else {
                    supportSQLiteStatement.bindString(32, appMetadata.getPackageName());
                }
            }
        };
        this.__preparedStmtOfUpdateCompatibility = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.AppDaoInt_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE AppMetadata \n        SET isCompatible = (\n            SELECT TOTAL(isCompatible) > 0 FROM Version\n            WHERE repoId = ? AND AppMetadata.packageName = Version.packageName\n        )\n        WHERE repoId = ?";
            }
        };
        this.__preparedStmtOfUpdatePreferredSigner = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.AppDaoInt_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE AppMetadata SET preferredSigner = ?\n        WHERE repoId = ? AND packageName = ?";
            }
        };
        this.__preparedStmtOfUpdateAppMetadata = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.AppDaoInt_Impl.7
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE AppMetadata SET localizedName = ?, localizedSummary = ?\n        WHERE repoId = ? AND packageName = ?";
            }
        };
        this.__preparedStmtOfDeleteAppMetadata = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.AppDaoInt_Impl.8
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM AppMetadata WHERE repoId = ? AND packageName = ?";
            }
        };
        this.__preparedStmtOfDeleteLocalizedFiles = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.AppDaoInt_Impl.9
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM LocalizedFile\n        WHERE repoId = ? AND packageName = ? AND type = ?";
            }
        };
        this.__preparedStmtOfDeleteLocalizedFile = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.AppDaoInt_Impl.10
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM LocalizedFile\n        WHERE repoId = ? AND packageName = ? AND type = ?\n        AND locale = ?";
            }
        };
        this.__preparedStmtOfDeleteLocalizedFileLists = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.AppDaoInt_Impl.11
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM LocalizedFileList\n        WHERE repoId = ? AND packageName = ?";
            }
        };
        this.__preparedStmtOfDeleteLocalizedFileLists_1 = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.AppDaoInt_Impl.12
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM LocalizedFileList\n        WHERE repoId = ? AND packageName = ? AND type = ?";
            }
        };
        this.__preparedStmtOfDeleteLocalizedFileList = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.AppDaoInt_Impl.13
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM LocalizedFileList\n        WHERE repoId = ? AND packageName = ? AND type = ?\n        AND locale = ?";
            }
        };
        this.__preparedStmtOfClearAll = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.AppDaoInt_Impl.14
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM AppMetadata";
            }
        };
    }

    @Override // org.fdroid.database.AppDaoInt
    public void insert(AppMetadata appMetadata) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfAppMetadata.insert(appMetadata);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.AppDaoInt
    public void insert(List<LocalizedFile> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfLocalizedFile.insert((Iterable) list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.AppDaoInt
    public void insertLocalizedFileLists(List<LocalizedFileList> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfLocalizedFileList.insert((Iterable) list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.AppDaoInt
    public int updateAppMetadata(AppMetadata appMetadata) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            int iHandle = this.__updateAdapterOfAppMetadata.handle(appMetadata);
            this.__db.setTransactionSuccessful();
            return iHandle;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.AppDaoInt, org.fdroid.database.AppDao
    public void insert(long j, String str, MetadataV2 metadataV2, LocaleListCompat localeListCompat) {
        this.__db.beginTransaction();
        try {
            AppDaoInt.DefaultImpls.insert(this, j, str, metadataV2, localeListCompat);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.AppDaoInt
    public void updateApp(long j, String str, JsonObject jsonObject, LocaleListCompat localeListCompat) {
        this.__db.beginTransaction();
        try {
            AppDaoInt.DefaultImpls.updateApp(this, j, str, jsonObject, localeListCompat);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.AppDaoInt, org.fdroid.database.AppDao
    public void updateCompatibility(long j) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfUpdateCompatibility.acquire();
        supportSQLiteStatementAcquire.bindLong(1, j);
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
            this.__preparedStmtOfUpdateCompatibility.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.AppDaoInt
    public void updatePreferredSigner(long j, String str, String str2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfUpdatePreferredSigner.acquire();
        if (str2 == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, str2);
        }
        supportSQLiteStatementAcquire.bindLong(2, j);
        if (str == null) {
            supportSQLiteStatementAcquire.bindNull(3);
        } else {
            supportSQLiteStatementAcquire.bindString(3, str);
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
            this.__preparedStmtOfUpdatePreferredSigner.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.AppDaoInt
    public void updateAppMetadata(long j, String str, String str2, String str3) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfUpdateAppMetadata.acquire();
        if (str2 == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, str2);
        }
        if (str3 == null) {
            supportSQLiteStatementAcquire.bindNull(2);
        } else {
            supportSQLiteStatementAcquire.bindString(2, str3);
        }
        supportSQLiteStatementAcquire.bindLong(3, j);
        if (str == null) {
            supportSQLiteStatementAcquire.bindNull(4);
        } else {
            supportSQLiteStatementAcquire.bindString(4, str);
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
            this.__preparedStmtOfUpdateAppMetadata.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.AppDaoInt
    public void deleteAppMetadata(long j, String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteAppMetadata.acquire();
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
            this.__preparedStmtOfDeleteAppMetadata.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.AppDaoInt
    public void deleteLocalizedFiles(long j, String str, String str2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteLocalizedFiles.acquire();
        supportSQLiteStatementAcquire.bindLong(1, j);
        if (str == null) {
            supportSQLiteStatementAcquire.bindNull(2);
        } else {
            supportSQLiteStatementAcquire.bindString(2, str);
        }
        if (str2 == null) {
            supportSQLiteStatementAcquire.bindNull(3);
        } else {
            supportSQLiteStatementAcquire.bindString(3, str2);
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
            this.__preparedStmtOfDeleteLocalizedFiles.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.AppDaoInt
    public void deleteLocalizedFile(long j, String str, String str2, String str3) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteLocalizedFile.acquire();
        supportSQLiteStatementAcquire.bindLong(1, j);
        if (str == null) {
            supportSQLiteStatementAcquire.bindNull(2);
        } else {
            supportSQLiteStatementAcquire.bindString(2, str);
        }
        if (str2 == null) {
            supportSQLiteStatementAcquire.bindNull(3);
        } else {
            supportSQLiteStatementAcquire.bindString(3, str2);
        }
        if (str3 == null) {
            supportSQLiteStatementAcquire.bindNull(4);
        } else {
            supportSQLiteStatementAcquire.bindString(4, str3);
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
            this.__preparedStmtOfDeleteLocalizedFile.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.AppDaoInt
    public void deleteLocalizedFileLists(long j, String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteLocalizedFileLists.acquire();
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
            this.__preparedStmtOfDeleteLocalizedFileLists.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.AppDaoInt
    public void deleteLocalizedFileLists(long j, String str, String str2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteLocalizedFileLists_1.acquire();
        supportSQLiteStatementAcquire.bindLong(1, j);
        if (str == null) {
            supportSQLiteStatementAcquire.bindNull(2);
        } else {
            supportSQLiteStatementAcquire.bindString(2, str);
        }
        if (str2 == null) {
            supportSQLiteStatementAcquire.bindNull(3);
        } else {
            supportSQLiteStatementAcquire.bindString(3, str2);
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
            this.__preparedStmtOfDeleteLocalizedFileLists_1.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.AppDaoInt
    public void deleteLocalizedFileList(long j, String str, String str2, String str3) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteLocalizedFileList.acquire();
        supportSQLiteStatementAcquire.bindLong(1, j);
        if (str == null) {
            supportSQLiteStatementAcquire.bindNull(2);
        } else {
            supportSQLiteStatementAcquire.bindString(2, str);
        }
        if (str2 == null) {
            supportSQLiteStatementAcquire.bindNull(3);
        } else {
            supportSQLiteStatementAcquire.bindString(3, str2);
        }
        if (str3 == null) {
            supportSQLiteStatementAcquire.bindNull(4);
        } else {
            supportSQLiteStatementAcquire.bindString(4, str3);
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
            this.__preparedStmtOfDeleteLocalizedFileList.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.AppDaoInt
    public void clearAll() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfClearAll.acquire();
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatementAcquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOfClearAll.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.AppDaoInt, org.fdroid.database.AppDao
    public LiveData getApp(String str) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT AppMetadata.* FROM AppMetadata\n        JOIN RepositoryPreferences AS pref USING (repoId)\n        JOIN PreferredRepo USING (packageName)\n        WHERE packageName = ? AND pref.enabled = 1 AND repoId = preferredRepoId\n        ORDER BY pref.weight DESC LIMIT 1", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{LocalizedFile.TABLE, LocalizedFileList.TABLE, AppMetadata.TABLE, RepositoryPreferences.TABLE, "PreferredRepo"}, true, new Callable() { // from class: org.fdroid.database.AppDaoInt_Impl.15
            @Override // java.util.concurrent.Callable
            public App call() {
                App app;
                String string;
                int i;
                String string2;
                int i2;
                String string3;
                int i3;
                String string4;
                int i4;
                String string5;
                int i5;
                String string6;
                int i6;
                String string7;
                int i7;
                String string8;
                int i8;
                String string9;
                int i9;
                String string10;
                int i10;
                String string11;
                int i11;
                String string12;
                int i12;
                String string13;
                int i13;
                String string14;
                int i14;
                String string15;
                int i15;
                String string16;
                int i16;
                String string17;
                int i17;
                ArrayList arrayList;
                ArrayList arrayList2;
                int i18;
                String string18;
                int i19;
                AppDaoInt_Impl.this.__db.beginTransaction();
                try {
                    Cursor cursorQuery = DBUtil.query(AppDaoInt_Impl.this.__db, roomSQLiteQueryAcquire, true, null);
                    try {
                        int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "repoId");
                        int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "packageName");
                        int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "added");
                        int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, AppListActivity.SortClause.LAST_UPDATED);
                        int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "name");
                        int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ErrorBundle.SUMMARY_ENTRY);
                        int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "description");
                        int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "localizedName");
                        int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "localizedSummary");
                        int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "webSite");
                        int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "changelog");
                        int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "license");
                        int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sourceCode");
                        int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "issueTracker");
                        int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "translation");
                        int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "preferredSigner");
                        int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "video");
                        int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "authorName");
                        int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "authorEmail");
                        int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "authorWebSite");
                        int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "authorPhone");
                        int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "donate");
                        int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "liberapayID");
                        int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "liberapay");
                        int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "openCollective");
                        int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "bitcoin");
                        int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "litecoin");
                        int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "flattrID");
                        int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "categories");
                        int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "isCompatible");
                        ArrayMap arrayMap = new ArrayMap();
                        ArrayMap arrayMap2 = new ArrayMap();
                        while (cursorQuery.moveToNext()) {
                            if (cursorQuery.isNull(columnIndexOrThrow2)) {
                                i18 = columnIndexOrThrow12;
                                string18 = null;
                            } else {
                                i18 = columnIndexOrThrow12;
                                string18 = cursorQuery.getString(columnIndexOrThrow2);
                            }
                            if (string18 == null || arrayMap.containsKey(string18)) {
                                i19 = columnIndexOrThrow11;
                            } else {
                                i19 = columnIndexOrThrow11;
                                arrayMap.put(string18, new ArrayList());
                            }
                            String string19 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                            if (string19 != null && !arrayMap2.containsKey(string19)) {
                                arrayMap2.put(string19, new ArrayList());
                            }
                            columnIndexOrThrow11 = i19;
                            columnIndexOrThrow12 = i18;
                        }
                        int i20 = columnIndexOrThrow11;
                        int i21 = columnIndexOrThrow12;
                        cursorQuery.moveToPosition(-1);
                        AppDaoInt_Impl.this.__fetchRelationshipLocalizedFileAsorgFdroidDatabaseLocalizedFile(arrayMap);
                        AppDaoInt_Impl.this.__fetchRelationshipLocalizedFileListAsorgFdroidDatabaseLocalizedFileList(arrayMap2);
                        if (cursorQuery.moveToFirst()) {
                            long j = cursorQuery.getLong(columnIndexOrThrow);
                            String string20 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                            long j2 = cursorQuery.getLong(columnIndexOrThrow3);
                            long j3 = cursorQuery.getLong(columnIndexOrThrow4);
                            String string21 = cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5);
                            Converters converters = Converters.INSTANCE;
                            Map<String, String> mapFromStringToLocalizedTextV2 = converters.fromStringToLocalizedTextV2(string21);
                            Map<String, String> mapFromStringToLocalizedTextV22 = converters.fromStringToLocalizedTextV2(cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6));
                            Map<String, String> mapFromStringToLocalizedTextV23 = converters.fromStringToLocalizedTextV2(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                            String string22 = cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8);
                            String string23 = cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9);
                            if (cursorQuery.isNull(columnIndexOrThrow10)) {
                                i = i20;
                                string = null;
                            } else {
                                string = cursorQuery.getString(columnIndexOrThrow10);
                                i = i20;
                            }
                            if (cursorQuery.isNull(i)) {
                                i2 = i21;
                                string2 = null;
                            } else {
                                string2 = cursorQuery.getString(i);
                                i2 = i21;
                            }
                            if (cursorQuery.isNull(i2)) {
                                i3 = columnIndexOrThrow13;
                                string3 = null;
                            } else {
                                string3 = cursorQuery.getString(i2);
                                i3 = columnIndexOrThrow13;
                            }
                            if (cursorQuery.isNull(i3)) {
                                i4 = columnIndexOrThrow14;
                                string4 = null;
                            } else {
                                string4 = cursorQuery.getString(i3);
                                i4 = columnIndexOrThrow14;
                            }
                            if (cursorQuery.isNull(i4)) {
                                i5 = columnIndexOrThrow15;
                                string5 = null;
                            } else {
                                string5 = cursorQuery.getString(i4);
                                i5 = columnIndexOrThrow15;
                            }
                            if (cursorQuery.isNull(i5)) {
                                i6 = columnIndexOrThrow16;
                                string6 = null;
                            } else {
                                string6 = cursorQuery.getString(i5);
                                i6 = columnIndexOrThrow16;
                            }
                            if (cursorQuery.isNull(i6)) {
                                i7 = columnIndexOrThrow17;
                                string7 = null;
                            } else {
                                string7 = cursorQuery.getString(i6);
                                i7 = columnIndexOrThrow17;
                            }
                            Map<String, String> mapFromStringToLocalizedTextV24 = converters.fromStringToLocalizedTextV2(cursorQuery.isNull(i7) ? null : cursorQuery.getString(i7));
                            if (cursorQuery.isNull(columnIndexOrThrow18)) {
                                i8 = columnIndexOrThrow19;
                                string8 = null;
                            } else {
                                string8 = cursorQuery.getString(columnIndexOrThrow18);
                                i8 = columnIndexOrThrow19;
                            }
                            if (cursorQuery.isNull(i8)) {
                                i9 = columnIndexOrThrow20;
                                string9 = null;
                            } else {
                                string9 = cursorQuery.getString(i8);
                                i9 = columnIndexOrThrow20;
                            }
                            if (cursorQuery.isNull(i9)) {
                                i10 = columnIndexOrThrow21;
                                string10 = null;
                            } else {
                                string10 = cursorQuery.getString(i9);
                                i10 = columnIndexOrThrow21;
                            }
                            if (cursorQuery.isNull(i10)) {
                                i11 = columnIndexOrThrow22;
                                string11 = null;
                            } else {
                                string11 = cursorQuery.getString(i10);
                                i11 = columnIndexOrThrow22;
                            }
                            List<String> listFromStringToListString = converters.fromStringToListString(cursorQuery.isNull(i11) ? null : cursorQuery.getString(i11));
                            if (cursorQuery.isNull(columnIndexOrThrow23)) {
                                i12 = columnIndexOrThrow24;
                                string12 = null;
                            } else {
                                string12 = cursorQuery.getString(columnIndexOrThrow23);
                                i12 = columnIndexOrThrow24;
                            }
                            if (cursorQuery.isNull(i12)) {
                                i13 = columnIndexOrThrow25;
                                string13 = null;
                            } else {
                                string13 = cursorQuery.getString(i12);
                                i13 = columnIndexOrThrow25;
                            }
                            if (cursorQuery.isNull(i13)) {
                                i14 = columnIndexOrThrow26;
                                string14 = null;
                            } else {
                                string14 = cursorQuery.getString(i13);
                                i14 = columnIndexOrThrow26;
                            }
                            if (cursorQuery.isNull(i14)) {
                                i15 = columnIndexOrThrow27;
                                string15 = null;
                            } else {
                                string15 = cursorQuery.getString(i14);
                                i15 = columnIndexOrThrow27;
                            }
                            if (cursorQuery.isNull(i15)) {
                                i16 = columnIndexOrThrow28;
                                string16 = null;
                            } else {
                                string16 = cursorQuery.getString(i15);
                                i16 = columnIndexOrThrow28;
                            }
                            if (cursorQuery.isNull(i16)) {
                                i17 = columnIndexOrThrow29;
                                string17 = null;
                            } else {
                                string17 = cursorQuery.getString(i16);
                                i17 = columnIndexOrThrow29;
                            }
                            AppMetadata appMetadata = new AppMetadata(j, string20, j2, j3, mapFromStringToLocalizedTextV2, mapFromStringToLocalizedTextV22, mapFromStringToLocalizedTextV23, string22, string23, string, string2, string3, string4, string5, string6, string7, mapFromStringToLocalizedTextV24, string8, string9, string10, string11, listFromStringToListString, string12, string13, string14, string15, string16, string17, converters.fromStringToListString(cursorQuery.isNull(i17) ? null : cursorQuery.getString(i17)), cursorQuery.getInt(columnIndexOrThrow30) != 0);
                            String string24 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                            if (string24 != null) {
                                arrayList = (ArrayList) arrayMap.get(string24);
                            } else {
                                arrayList = new ArrayList();
                            }
                            String string25 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                            if (string25 != null) {
                                arrayList2 = (ArrayList) arrayMap2.get(string25);
                            } else {
                                arrayList2 = new ArrayList();
                            }
                            app = new App(appMetadata, arrayList, arrayList2);
                        } else {
                            app = null;
                        }
                        AppDaoInt_Impl.this.__db.setTransactionSuccessful();
                        cursorQuery.close();
                        return app;
                    } catch (Throwable th) {
                        cursorQuery.close();
                        throw th;
                    }
                } finally {
                    AppDaoInt_Impl.this.__db.endTransaction();
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    @Override // org.fdroid.database.AppDaoInt, org.fdroid.database.AppDao
    public App getApp(long j, String str) {
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
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        App app;
        String string;
        int i;
        String string2;
        int i2;
        String string3;
        int i3;
        String string4;
        int i4;
        String string5;
        int i5;
        String string6;
        int i6;
        String string7;
        int i7;
        String string8;
        int i8;
        String string9;
        int i9;
        String string10;
        int i10;
        String string11;
        int i11;
        String string12;
        int i12;
        String string13;
        int i13;
        String string14;
        int i14;
        String string15;
        int i15;
        String string16;
        int i16;
        String string17;
        int i17;
        ArrayList arrayList;
        ArrayList arrayList2;
        int i18;
        String string18;
        int i19;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM AppMetadata\n        WHERE repoId = ? AND packageName = ?", 2);
        roomSQLiteQueryAcquire.bindLong(1, j);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, str);
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, true, null);
            try {
                columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "repoId");
                columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "packageName");
                columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "added");
                columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, AppListActivity.SortClause.LAST_UPDATED);
                columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "name");
                columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ErrorBundle.SUMMARY_ENTRY);
                columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "description");
                columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "localizedName");
                columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "localizedSummary");
                columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "webSite");
                columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "changelog");
                columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "license");
                columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sourceCode");
                roomSQLiteQuery = roomSQLiteQueryAcquire;
            } catch (Throwable th) {
                th = th;
                roomSQLiteQuery = roomSQLiteQueryAcquire;
            }
            try {
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "issueTracker");
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "translation");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "preferredSigner");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "video");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "authorName");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "authorEmail");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "authorWebSite");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "authorPhone");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "donate");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "liberapayID");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "liberapay");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "openCollective");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "bitcoin");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "litecoin");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "flattrID");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "categories");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "isCompatible");
                ArrayMap arrayMap = new ArrayMap();
                ArrayMap arrayMap2 = new ArrayMap();
                while (cursorQuery.moveToNext()) {
                    if (cursorQuery.isNull(columnIndexOrThrow2)) {
                        i18 = columnIndexOrThrow12;
                        string18 = null;
                    } else {
                        i18 = columnIndexOrThrow12;
                        string18 = cursorQuery.getString(columnIndexOrThrow2);
                    }
                    if (string18 == null || arrayMap.containsKey(string18)) {
                        i19 = columnIndexOrThrow11;
                    } else {
                        i19 = columnIndexOrThrow11;
                        arrayMap.put(string18, new ArrayList());
                    }
                    String string19 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                    if (string19 != null && !arrayMap2.containsKey(string19)) {
                        arrayMap2.put(string19, new ArrayList());
                    }
                    columnIndexOrThrow11 = i19;
                    columnIndexOrThrow12 = i18;
                }
                int i20 = columnIndexOrThrow12;
                int i21 = columnIndexOrThrow11;
                cursorQuery.moveToPosition(-1);
                __fetchRelationshipLocalizedFileAsorgFdroidDatabaseLocalizedFile(arrayMap);
                __fetchRelationshipLocalizedFileListAsorgFdroidDatabaseLocalizedFileList(arrayMap2);
                if (cursorQuery.moveToFirst()) {
                    long j2 = cursorQuery.getLong(columnIndexOrThrow);
                    String string20 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                    long j3 = cursorQuery.getLong(columnIndexOrThrow3);
                    long j4 = cursorQuery.getLong(columnIndexOrThrow4);
                    String string21 = cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5);
                    Converters converters = Converters.INSTANCE;
                    Map<String, String> mapFromStringToLocalizedTextV2 = converters.fromStringToLocalizedTextV2(string21);
                    Map<String, String> mapFromStringToLocalizedTextV22 = converters.fromStringToLocalizedTextV2(cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6));
                    Map<String, String> mapFromStringToLocalizedTextV23 = converters.fromStringToLocalizedTextV2(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                    String string22 = cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8);
                    String string23 = cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9);
                    if (cursorQuery.isNull(columnIndexOrThrow10)) {
                        i = i21;
                        string = null;
                    } else {
                        string = cursorQuery.getString(columnIndexOrThrow10);
                        i = i21;
                    }
                    if (cursorQuery.isNull(i)) {
                        i2 = i20;
                        string2 = null;
                    } else {
                        string2 = cursorQuery.getString(i);
                        i2 = i20;
                    }
                    if (cursorQuery.isNull(i2)) {
                        i3 = columnIndexOrThrow13;
                        string3 = null;
                    } else {
                        string3 = cursorQuery.getString(i2);
                        i3 = columnIndexOrThrow13;
                    }
                    if (cursorQuery.isNull(i3)) {
                        i4 = columnIndexOrThrow14;
                        string4 = null;
                    } else {
                        string4 = cursorQuery.getString(i3);
                        i4 = columnIndexOrThrow14;
                    }
                    if (cursorQuery.isNull(i4)) {
                        i5 = columnIndexOrThrow15;
                        string5 = null;
                    } else {
                        string5 = cursorQuery.getString(i4);
                        i5 = columnIndexOrThrow15;
                    }
                    if (cursorQuery.isNull(i5)) {
                        i6 = columnIndexOrThrow16;
                        string6 = null;
                    } else {
                        string6 = cursorQuery.getString(i5);
                        i6 = columnIndexOrThrow16;
                    }
                    if (cursorQuery.isNull(i6)) {
                        i7 = columnIndexOrThrow17;
                        string7 = null;
                    } else {
                        string7 = cursorQuery.getString(i6);
                        i7 = columnIndexOrThrow17;
                    }
                    Map<String, String> mapFromStringToLocalizedTextV24 = converters.fromStringToLocalizedTextV2(cursorQuery.isNull(i7) ? null : cursorQuery.getString(i7));
                    if (cursorQuery.isNull(columnIndexOrThrow18)) {
                        i8 = columnIndexOrThrow19;
                        string8 = null;
                    } else {
                        string8 = cursorQuery.getString(columnIndexOrThrow18);
                        i8 = columnIndexOrThrow19;
                    }
                    if (cursorQuery.isNull(i8)) {
                        i9 = columnIndexOrThrow20;
                        string9 = null;
                    } else {
                        string9 = cursorQuery.getString(i8);
                        i9 = columnIndexOrThrow20;
                    }
                    if (cursorQuery.isNull(i9)) {
                        i10 = columnIndexOrThrow21;
                        string10 = null;
                    } else {
                        string10 = cursorQuery.getString(i9);
                        i10 = columnIndexOrThrow21;
                    }
                    if (cursorQuery.isNull(i10)) {
                        i11 = columnIndexOrThrow22;
                        string11 = null;
                    } else {
                        string11 = cursorQuery.getString(i10);
                        i11 = columnIndexOrThrow22;
                    }
                    List<String> listFromStringToListString = converters.fromStringToListString(cursorQuery.isNull(i11) ? null : cursorQuery.getString(i11));
                    if (cursorQuery.isNull(columnIndexOrThrow23)) {
                        i12 = columnIndexOrThrow24;
                        string12 = null;
                    } else {
                        string12 = cursorQuery.getString(columnIndexOrThrow23);
                        i12 = columnIndexOrThrow24;
                    }
                    if (cursorQuery.isNull(i12)) {
                        i13 = columnIndexOrThrow25;
                        string13 = null;
                    } else {
                        string13 = cursorQuery.getString(i12);
                        i13 = columnIndexOrThrow25;
                    }
                    if (cursorQuery.isNull(i13)) {
                        i14 = columnIndexOrThrow26;
                        string14 = null;
                    } else {
                        string14 = cursorQuery.getString(i13);
                        i14 = columnIndexOrThrow26;
                    }
                    if (cursorQuery.isNull(i14)) {
                        i15 = columnIndexOrThrow27;
                        string15 = null;
                    } else {
                        string15 = cursorQuery.getString(i14);
                        i15 = columnIndexOrThrow27;
                    }
                    if (cursorQuery.isNull(i15)) {
                        i16 = columnIndexOrThrow28;
                        string16 = null;
                    } else {
                        string16 = cursorQuery.getString(i15);
                        i16 = columnIndexOrThrow28;
                    }
                    if (cursorQuery.isNull(i16)) {
                        i17 = columnIndexOrThrow29;
                        string17 = null;
                    } else {
                        string17 = cursorQuery.getString(i16);
                        i17 = columnIndexOrThrow29;
                    }
                    AppMetadata appMetadata = new AppMetadata(j2, string20, j3, j4, mapFromStringToLocalizedTextV2, mapFromStringToLocalizedTextV22, mapFromStringToLocalizedTextV23, string22, string23, string, string2, string3, string4, string5, string6, string7, mapFromStringToLocalizedTextV24, string8, string9, string10, string11, listFromStringToListString, string12, string13, string14, string15, string16, string17, converters.fromStringToListString(cursorQuery.isNull(i17) ? null : cursorQuery.getString(i17)), cursorQuery.getInt(columnIndexOrThrow30) != 0);
                    String string24 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                    if (string24 != null) {
                        arrayList = (ArrayList) arrayMap.get(string24);
                    } else {
                        arrayList = new ArrayList();
                    }
                    String string25 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                    if (string25 != null) {
                        arrayList2 = (ArrayList) arrayMap2.get(string25);
                    } else {
                        arrayList2 = new ArrayList();
                    }
                    app = new App(appMetadata, arrayList, arrayList2);
                } else {
                    app = null;
                }
                this.__db.setTransactionSuccessful();
                cursorQuery.close();
                roomSQLiteQuery.release();
                return app;
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

    @Override // org.fdroid.database.AppDaoInt, org.fdroid.database.AppDao
    public List<Long> getRepositoryIdsForApp(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT repoId FROM AppMetadata\n        JOIN RepositoryPreferences AS pref USING (repoId)\n        WHERE pref.enabled = 1 AND packageName = ?", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(cursorQuery.isNull(0) ? null : Long.valueOf(cursorQuery.getLong(0)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // org.fdroid.database.AppDaoInt, org.fdroid.database.AppDao
    public LiveData getAppOverviewItems(int i) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT repoId, packageName, app.added, app.lastUpdated, localizedName,\n            localizedSummary, version.antiFeatures, app.isCompatible\n        FROM AppMetadata AS app\n        JOIN RepositoryPreferences AS pref USING (repoId)\n        JOIN PreferredRepo USING (packageName)\n        LEFT JOIN HighestVersion AS version USING (repoId, packageName)\n        LEFT JOIN LocalizedIcon AS icon USING (repoId, packageName)\n        WHERE pref.enabled = 1 AND repoId = preferredRepoId\n        GROUP BY packageName HAVING MAX(pref.weight)\n        ORDER BY localizedName IS NULL ASC, icon.packageName IS NULL ASC,\n            localizedSummary IS NULL ASC, app.lastUpdated DESC\n        LIMIT ?", 1);
        roomSQLiteQueryAcquire.bindLong(1, i);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{LocalizedIcon.TABLE, AppMetadata.TABLE, RepositoryPreferences.TABLE, "PreferredRepo", HighestVersion.TABLE}, true, new Callable() { // from class: org.fdroid.database.AppDaoInt_Impl.16
            @Override // java.util.concurrent.Callable
            public List call() {
                ArrayList arrayList;
                AppDaoInt_Impl.this.__db.beginTransaction();
                try {
                    String str = null;
                    Cursor cursorQuery = DBUtil.query(AppDaoInt_Impl.this.__db, roomSQLiteQueryAcquire, true, null);
                    try {
                        ArrayMap arrayMap = new ArrayMap();
                        while (cursorQuery.moveToNext()) {
                            String string = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            if (string != null && !arrayMap.containsKey(string)) {
                                arrayMap.put(string, new ArrayList());
                            }
                        }
                        cursorQuery.moveToPosition(-1);
                        AppDaoInt_Impl.this.__fetchRelationshipLocalizedIconAsorgFdroidDatabaseLocalizedIcon(arrayMap);
                        ArrayList arrayList2 = new ArrayList(cursorQuery.getCount());
                        while (cursorQuery.moveToNext()) {
                            long j = cursorQuery.getLong(0);
                            String string2 = cursorQuery.isNull(1) ? str : cursorQuery.getString(1);
                            long j2 = cursorQuery.getLong(2);
                            long j3 = cursorQuery.getLong(3);
                            String string3 = cursorQuery.isNull(4) ? str : cursorQuery.getString(4);
                            String string4 = cursorQuery.isNull(5) ? str : cursorQuery.getString(5);
                            Map<String, Map<String, String>> mapFromStringToMapOfLocalizedTextV2 = Converters.INSTANCE.fromStringToMapOfLocalizedTextV2(cursorQuery.isNull(6) ? str : cursorQuery.getString(6));
                            boolean z = cursorQuery.getInt(7) != 0;
                            String string5 = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            if (string5 != null) {
                                arrayList = (ArrayList) arrayMap.get(string5);
                            } else {
                                arrayList = new ArrayList();
                            }
                            arrayList2.add(new AppOverviewItem(j, string2, j2, j3, string3, string4, mapFromStringToMapOfLocalizedTextV2, arrayList, z));
                            str = null;
                        }
                        AppDaoInt_Impl.this.__db.setTransactionSuccessful();
                        cursorQuery.close();
                        return arrayList2;
                    } catch (Throwable th) {
                        cursorQuery.close();
                        throw th;
                    }
                } finally {
                    AppDaoInt_Impl.this.__db.endTransaction();
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    @Override // org.fdroid.database.AppDaoInt, org.fdroid.database.AppDao
    public LiveData getAppOverviewItems(String str, int i) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT repoId, packageName, app.added, app.lastUpdated, localizedName,\n             localizedSummary, version.antiFeatures, app.isCompatible\n        FROM AppMetadata AS app\n        JOIN RepositoryPreferences AS pref USING (repoId)\n        JOIN PreferredRepo USING (packageName)\n        LEFT JOIN HighestVersion AS version USING (repoId, packageName)\n        LEFT JOIN LocalizedIcon AS icon USING (repoId, packageName)\n        WHERE pref.enabled = 1 AND categories  LIKE '%,' || ? || ',%' AND\n            repoId = preferredRepoId\n        GROUP BY packageName HAVING MAX(pref.weight)\n        ORDER BY localizedName IS NULL ASC, icon.packageName IS NULL ASC,\n            localizedSummary IS NULL ASC, app.lastUpdated DESC\n        LIMIT ?", 2);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        roomSQLiteQueryAcquire.bindLong(2, i);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{LocalizedIcon.TABLE, AppMetadata.TABLE, RepositoryPreferences.TABLE, "PreferredRepo", HighestVersion.TABLE}, true, new Callable() { // from class: org.fdroid.database.AppDaoInt_Impl.17
            @Override // java.util.concurrent.Callable
            public List call() {
                ArrayList arrayList;
                AppDaoInt_Impl.this.__db.beginTransaction();
                try {
                    String str2 = null;
                    Cursor cursorQuery = DBUtil.query(AppDaoInt_Impl.this.__db, roomSQLiteQueryAcquire, true, null);
                    try {
                        ArrayMap arrayMap = new ArrayMap();
                        while (cursorQuery.moveToNext()) {
                            String string = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            if (string != null && !arrayMap.containsKey(string)) {
                                arrayMap.put(string, new ArrayList());
                            }
                        }
                        cursorQuery.moveToPosition(-1);
                        AppDaoInt_Impl.this.__fetchRelationshipLocalizedIconAsorgFdroidDatabaseLocalizedIcon(arrayMap);
                        ArrayList arrayList2 = new ArrayList(cursorQuery.getCount());
                        while (cursorQuery.moveToNext()) {
                            long j = cursorQuery.getLong(0);
                            String string2 = cursorQuery.isNull(1) ? str2 : cursorQuery.getString(1);
                            long j2 = cursorQuery.getLong(2);
                            long j3 = cursorQuery.getLong(3);
                            String string3 = cursorQuery.isNull(4) ? str2 : cursorQuery.getString(4);
                            String string4 = cursorQuery.isNull(5) ? str2 : cursorQuery.getString(5);
                            Map<String, Map<String, String>> mapFromStringToMapOfLocalizedTextV2 = Converters.INSTANCE.fromStringToMapOfLocalizedTextV2(cursorQuery.isNull(6) ? str2 : cursorQuery.getString(6));
                            boolean z = cursorQuery.getInt(7) != 0;
                            String string5 = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            if (string5 != null) {
                                arrayList = (ArrayList) arrayMap.get(string5);
                            } else {
                                arrayList = new ArrayList();
                            }
                            arrayList2.add(new AppOverviewItem(j, string2, j2, j3, string3, string4, mapFromStringToMapOfLocalizedTextV2, arrayList, z));
                            str2 = null;
                        }
                        AppDaoInt_Impl.this.__db.setTransactionSuccessful();
                        cursorQuery.close();
                        return arrayList2;
                    } catch (Throwable th) {
                        cursorQuery.close();
                        throw th;
                    }
                } finally {
                    AppDaoInt_Impl.this.__db.endTransaction();
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    @Override // org.fdroid.database.AppDaoInt
    public LiveData getAppListItems(String str) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("\n        SELECT repoId, packageName, app.localizedName, app.localizedSummary, app.lastUpdated, \n               version.antiFeatures, app.isCompatible, app.preferredSigner\n        FROM AppMetadata AS app\n        JOIN PreferredRepo USING (packageName)\n        JOIN AppMetadataFts USING (repoId, packageName)\n        LEFT JOIN HighestVersion AS version USING (repoId, packageName)\n        JOIN RepositoryPreferences AS pref USING (repoId)\n        WHERE pref.enabled = 1 AND AppMetadataFts MATCH ? AND\n            repoId = preferredRepoId\n        GROUP BY packageName HAVING MAX(pref.weight)", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{LocalizedIcon.TABLE, AppMetadata.TABLE, "PreferredRepo", AppMetadataFts.TABLE, HighestVersion.TABLE, RepositoryPreferences.TABLE}, true, new Callable() { // from class: org.fdroid.database.AppDaoInt_Impl.18
            @Override // java.util.concurrent.Callable
            public List call() {
                ArrayList arrayList;
                AppDaoInt_Impl.this.__db.beginTransaction();
                try {
                    Cursor cursorQuery = DBUtil.query(AppDaoInt_Impl.this.__db, roomSQLiteQueryAcquire, true, null);
                    try {
                        ArrayMap arrayMap = new ArrayMap();
                        while (cursorQuery.moveToNext()) {
                            String string = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            if (string != null && !arrayMap.containsKey(string)) {
                                arrayMap.put(string, new ArrayList());
                            }
                        }
                        cursorQuery.moveToPosition(-1);
                        AppDaoInt_Impl.this.__fetchRelationshipLocalizedIconAsorgFdroidDatabaseLocalizedIcon(arrayMap);
                        ArrayList arrayList2 = new ArrayList(cursorQuery.getCount());
                        while (cursorQuery.moveToNext()) {
                            long j = cursorQuery.getLong(0);
                            String string2 = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            String string3 = cursorQuery.isNull(2) ? null : cursorQuery.getString(2);
                            String string4 = cursorQuery.isNull(3) ? null : cursorQuery.getString(3);
                            long j2 = cursorQuery.getLong(4);
                            String string5 = cursorQuery.isNull(5) ? null : cursorQuery.getString(5);
                            boolean z = cursorQuery.getInt(6) != 0;
                            String string6 = cursorQuery.isNull(7) ? null : cursorQuery.getString(7);
                            String string7 = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            if (string7 != null) {
                                arrayList = (ArrayList) arrayMap.get(string7);
                            } else {
                                arrayList = new ArrayList();
                            }
                            arrayList2.add(new AppListItem(j, string2, string3, string4, j2, string5, arrayList, z, string6, null, null));
                        }
                        AppDaoInt_Impl.this.__db.setTransactionSuccessful();
                        cursorQuery.close();
                        return arrayList2;
                    } catch (Throwable th) {
                        cursorQuery.close();
                        throw th;
                    }
                } finally {
                    AppDaoInt_Impl.this.__db.endTransaction();
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    @Override // org.fdroid.database.AppDaoInt
    public LiveData getAppListItems(String str, String str2) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("\n        SELECT repoId, packageName, app.localizedName, app.localizedSummary, app.lastUpdated, \n               version.antiFeatures, app.isCompatible, app.preferredSigner\n        FROM AppMetadata AS app\n        JOIN AppMetadataFts USING (repoId, packageName)\n        JOIN PreferredRepo USING (packageName)\n        LEFT JOIN HighestVersion AS version USING (repoId, packageName)\n        JOIN RepositoryPreferences AS pref USING (repoId)\n        WHERE pref.enabled = 1 AND categories LIKE '%,' || ? || ',%' AND\n           AppMetadataFts MATCH ? AND repoId = preferredRepoId\n        GROUP BY packageName HAVING MAX(pref.weight)", 2);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        if (str2 == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, str2);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{LocalizedIcon.TABLE, AppMetadata.TABLE, AppMetadataFts.TABLE, "PreferredRepo", HighestVersion.TABLE, RepositoryPreferences.TABLE}, true, new Callable() { // from class: org.fdroid.database.AppDaoInt_Impl.19
            @Override // java.util.concurrent.Callable
            public List call() {
                ArrayList arrayList;
                AppDaoInt_Impl.this.__db.beginTransaction();
                try {
                    Cursor cursorQuery = DBUtil.query(AppDaoInt_Impl.this.__db, roomSQLiteQueryAcquire, true, null);
                    try {
                        ArrayMap arrayMap = new ArrayMap();
                        while (cursorQuery.moveToNext()) {
                            String string = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            if (string != null && !arrayMap.containsKey(string)) {
                                arrayMap.put(string, new ArrayList());
                            }
                        }
                        cursorQuery.moveToPosition(-1);
                        AppDaoInt_Impl.this.__fetchRelationshipLocalizedIconAsorgFdroidDatabaseLocalizedIcon(arrayMap);
                        ArrayList arrayList2 = new ArrayList(cursorQuery.getCount());
                        while (cursorQuery.moveToNext()) {
                            long j = cursorQuery.getLong(0);
                            String string2 = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            String string3 = cursorQuery.isNull(2) ? null : cursorQuery.getString(2);
                            String string4 = cursorQuery.isNull(3) ? null : cursorQuery.getString(3);
                            long j2 = cursorQuery.getLong(4);
                            String string5 = cursorQuery.isNull(5) ? null : cursorQuery.getString(5);
                            boolean z = cursorQuery.getInt(6) != 0;
                            String string6 = cursorQuery.isNull(7) ? null : cursorQuery.getString(7);
                            String string7 = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            if (string7 != null) {
                                arrayList = (ArrayList) arrayMap.get(string7);
                            } else {
                                arrayList = new ArrayList();
                            }
                            arrayList2.add(new AppListItem(j, string2, string3, string4, j2, string5, arrayList, z, string6, null, null));
                        }
                        AppDaoInt_Impl.this.__db.setTransactionSuccessful();
                        cursorQuery.close();
                        return arrayList2;
                    } catch (Throwable th) {
                        cursorQuery.close();
                        throw th;
                    }
                } finally {
                    AppDaoInt_Impl.this.__db.endTransaction();
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    @Override // org.fdroid.database.AppDaoInt
    public LiveData getAppListItems(long j, String str) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("\n        SELECT repoId, packageName, app.localizedName, app.localizedSummary, app.lastUpdated, \n               version.antiFeatures, app.isCompatible, app.preferredSigner\n        FROM AppMetadata AS app\n        LEFT JOIN HighestVersion AS version USING (repoId, packageName)\n        WHERE repoId = ? AND app.rowid IN (\n            SELECT rowid FROM AppMetadataFts\n            WHERE repoId = ? AND AppMetadataFts MATCH ?\n        )", 3);
        roomSQLiteQueryAcquire.bindLong(1, j);
        roomSQLiteQueryAcquire.bindLong(2, j);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(3);
        } else {
            roomSQLiteQueryAcquire.bindString(3, str);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{LocalizedIcon.TABLE, AppMetadata.TABLE, HighestVersion.TABLE, AppMetadataFts.TABLE}, true, new Callable() { // from class: org.fdroid.database.AppDaoInt_Impl.20
            @Override // java.util.concurrent.Callable
            public List call() {
                ArrayList arrayList;
                AppDaoInt_Impl.this.__db.beginTransaction();
                try {
                    Cursor cursorQuery = DBUtil.query(AppDaoInt_Impl.this.__db, roomSQLiteQueryAcquire, true, null);
                    try {
                        ArrayMap arrayMap = new ArrayMap();
                        while (cursorQuery.moveToNext()) {
                            String string = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            if (string != null && !arrayMap.containsKey(string)) {
                                arrayMap.put(string, new ArrayList());
                            }
                        }
                        cursorQuery.moveToPosition(-1);
                        AppDaoInt_Impl.this.__fetchRelationshipLocalizedIconAsorgFdroidDatabaseLocalizedIcon(arrayMap);
                        ArrayList arrayList2 = new ArrayList(cursorQuery.getCount());
                        while (cursorQuery.moveToNext()) {
                            long j2 = cursorQuery.getLong(0);
                            String string2 = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            String string3 = cursorQuery.isNull(2) ? null : cursorQuery.getString(2);
                            String string4 = cursorQuery.isNull(3) ? null : cursorQuery.getString(3);
                            long j3 = cursorQuery.getLong(4);
                            String string5 = cursorQuery.isNull(5) ? null : cursorQuery.getString(5);
                            boolean z = cursorQuery.getInt(6) != 0;
                            String string6 = cursorQuery.isNull(7) ? null : cursorQuery.getString(7);
                            String string7 = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            if (string7 != null) {
                                arrayList = (ArrayList) arrayMap.get(string7);
                            } else {
                                arrayList = new ArrayList();
                            }
                            arrayList2.add(new AppListItem(j2, string2, string3, string4, j3, string5, arrayList, z, string6, null, null));
                        }
                        AppDaoInt_Impl.this.__db.setTransactionSuccessful();
                        cursorQuery.close();
                        return arrayList2;
                    } catch (Throwable th) {
                        cursorQuery.close();
                        throw th;
                    }
                } finally {
                    AppDaoInt_Impl.this.__db.endTransaction();
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    @Override // org.fdroid.database.AppDaoInt
    public LiveData getAppListItems(List<String> list) {
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("SELECT repoId, packageName, localizedName, localizedSummary, app.lastUpdated, ");
        sbNewStringBuilder.append("\n");
        sbNewStringBuilder.append("                     app.isCompatible, app.preferredSigner");
        sbNewStringBuilder.append("\n");
        sbNewStringBuilder.append("        FROM AppMetadata AS app");
        sbNewStringBuilder.append("\n");
        sbNewStringBuilder.append("        JOIN RepositoryPreferences AS pref USING (repoId)");
        sbNewStringBuilder.append("\n");
        sbNewStringBuilder.append("        JOIN PreferredRepo USING (packageName)");
        sbNewStringBuilder.append("\n");
        sbNewStringBuilder.append("        WHERE pref.enabled = 1 AND packageName IN (");
        int size = list.size();
        StringUtil.appendPlaceholders(sbNewStringBuilder, size);
        sbNewStringBuilder.append(") AND repoId = preferredRepoId");
        sbNewStringBuilder.append("\n");
        sbNewStringBuilder.append("        GROUP BY packageName HAVING MAX(pref.weight)");
        sbNewStringBuilder.append("\n");
        sbNewStringBuilder.append("        ORDER BY localizedName COLLATE NOCASE ASC");
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire(sbNewStringBuilder.toString(), size);
        int i = 1;
        for (String str : list) {
            if (str == null) {
                roomSQLiteQueryAcquire.bindNull(i);
            } else {
                roomSQLiteQueryAcquire.bindString(i, str);
            }
            i++;
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{LocalizedIcon.TABLE, AppMetadata.TABLE, RepositoryPreferences.TABLE, "PreferredRepo"}, true, new Callable() { // from class: org.fdroid.database.AppDaoInt_Impl.21
            @Override // java.util.concurrent.Callable
            public List call() {
                ArrayList arrayList;
                AppDaoInt_Impl.this.__db.beginTransaction();
                try {
                    Cursor cursorQuery = DBUtil.query(AppDaoInt_Impl.this.__db, roomSQLiteQueryAcquire, true, null);
                    try {
                        ArrayMap arrayMap = new ArrayMap();
                        while (cursorQuery.moveToNext()) {
                            String string = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            if (string != null && !arrayMap.containsKey(string)) {
                                arrayMap.put(string, new ArrayList());
                            }
                        }
                        cursorQuery.moveToPosition(-1);
                        AppDaoInt_Impl.this.__fetchRelationshipLocalizedIconAsorgFdroidDatabaseLocalizedIcon(arrayMap);
                        ArrayList arrayList2 = new ArrayList(cursorQuery.getCount());
                        while (cursorQuery.moveToNext()) {
                            long j = cursorQuery.getLong(0);
                            String string2 = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            String string3 = cursorQuery.isNull(2) ? null : cursorQuery.getString(2);
                            String string4 = cursorQuery.isNull(3) ? null : cursorQuery.getString(3);
                            long j2 = cursorQuery.getLong(4);
                            boolean z = cursorQuery.getInt(5) != 0;
                            String string5 = cursorQuery.isNull(6) ? null : cursorQuery.getString(6);
                            String string6 = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            if (string6 != null) {
                                arrayList = (ArrayList) arrayMap.get(string6);
                            } else {
                                arrayList = new ArrayList();
                            }
                            arrayList2.add(new AppListItem(j, string2, string3, string4, j2, null, arrayList, z, string5, null, null));
                        }
                        AppDaoInt_Impl.this.__db.setTransactionSuccessful();
                        cursorQuery.close();
                        return arrayList2;
                    } catch (Throwable th) {
                        cursorQuery.close();
                        throw th;
                    }
                } finally {
                    AppDaoInt_Impl.this.__db.endTransaction();
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    @Override // org.fdroid.database.AppDaoInt, org.fdroid.database.AppDao
    public int getNumberOfAppsInCategory(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT COUNT(DISTINCT packageName) FROM AppMetadata\n        JOIN RepositoryPreferences AS pref USING (repoId)\n        JOIN PreferredRepo USING (packageName)\n        WHERE pref.enabled = 1 AND categories LIKE '%,' || ? || ',%' AND\n            repoId = preferredRepoId", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            return cursorQuery.moveToFirst() ? cursorQuery.getInt(0) : 0;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // org.fdroid.database.AppDaoInt, org.fdroid.database.AppDao
    public int getNumberOfAppsInRepository(long j) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM AppMetadata WHERE repoId = ?", 1);
        roomSQLiteQueryAcquire.bindLong(1, j);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            return cursorQuery.moveToFirst() ? cursorQuery.getInt(0) : 0;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // org.fdroid.database.AppDaoInt
    public AppMetadata getAppMetadata(long j, String str) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        AppMetadata appMetadata;
        String string;
        int i;
        String string2;
        int i2;
        String string3;
        int i3;
        String string4;
        int i4;
        String string5;
        int i5;
        String string6;
        int i6;
        String string7;
        int i7;
        String string8;
        int i8;
        String string9;
        int i9;
        String string10;
        int i10;
        String string11;
        int i11;
        String string12;
        int i12;
        String string13;
        int i13;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM AppMetadata\n        WHERE repoId = ? AND packageName = ?", 2);
        roomSQLiteQueryAcquire.bindLong(1, j);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "repoId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "packageName");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "added");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, AppListActivity.SortClause.LAST_UPDATED);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "name");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ErrorBundle.SUMMARY_ENTRY);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "description");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "localizedName");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "localizedSummary");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "webSite");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "changelog");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "license");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sourceCode");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "issueTracker");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "translation");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "preferredSigner");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "video");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "authorName");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "authorEmail");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "authorWebSite");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "authorPhone");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "donate");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "liberapayID");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "liberapay");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "openCollective");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "bitcoin");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "litecoin");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "flattrID");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "categories");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "isCompatible");
                if (cursorQuery.moveToFirst()) {
                    long j2 = cursorQuery.getLong(columnIndexOrThrow);
                    String string14 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                    long j3 = cursorQuery.getLong(columnIndexOrThrow3);
                    long j4 = cursorQuery.getLong(columnIndexOrThrow4);
                    String string15 = cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5);
                    Converters converters = Converters.INSTANCE;
                    Map<String, String> mapFromStringToLocalizedTextV2 = converters.fromStringToLocalizedTextV2(string15);
                    Map<String, String> mapFromStringToLocalizedTextV22 = converters.fromStringToLocalizedTextV2(cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6));
                    Map<String, String> mapFromStringToLocalizedTextV23 = converters.fromStringToLocalizedTextV2(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                    String string16 = cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8);
                    String string17 = cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9);
                    String string18 = cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10);
                    String string19 = cursorQuery.isNull(columnIndexOrThrow11) ? null : cursorQuery.getString(columnIndexOrThrow11);
                    String string20 = cursorQuery.isNull(columnIndexOrThrow12) ? null : cursorQuery.getString(columnIndexOrThrow12);
                    String string21 = cursorQuery.isNull(columnIndexOrThrow13) ? null : cursorQuery.getString(columnIndexOrThrow13);
                    if (cursorQuery.isNull(columnIndexOrThrow14)) {
                        i = columnIndexOrThrow15;
                        string = null;
                    } else {
                        string = cursorQuery.getString(columnIndexOrThrow14);
                        i = columnIndexOrThrow15;
                    }
                    if (cursorQuery.isNull(i)) {
                        i2 = columnIndexOrThrow16;
                        string2 = null;
                    } else {
                        string2 = cursorQuery.getString(i);
                        i2 = columnIndexOrThrow16;
                    }
                    if (cursorQuery.isNull(i2)) {
                        i3 = columnIndexOrThrow17;
                        string3 = null;
                    } else {
                        string3 = cursorQuery.getString(i2);
                        i3 = columnIndexOrThrow17;
                    }
                    Map<String, String> mapFromStringToLocalizedTextV24 = converters.fromStringToLocalizedTextV2(cursorQuery.isNull(i3) ? null : cursorQuery.getString(i3));
                    if (cursorQuery.isNull(columnIndexOrThrow18)) {
                        i4 = columnIndexOrThrow19;
                        string4 = null;
                    } else {
                        string4 = cursorQuery.getString(columnIndexOrThrow18);
                        i4 = columnIndexOrThrow19;
                    }
                    if (cursorQuery.isNull(i4)) {
                        i5 = columnIndexOrThrow20;
                        string5 = null;
                    } else {
                        string5 = cursorQuery.getString(i4);
                        i5 = columnIndexOrThrow20;
                    }
                    if (cursorQuery.isNull(i5)) {
                        i6 = columnIndexOrThrow21;
                        string6 = null;
                    } else {
                        string6 = cursorQuery.getString(i5);
                        i6 = columnIndexOrThrow21;
                    }
                    if (cursorQuery.isNull(i6)) {
                        i7 = columnIndexOrThrow22;
                        string7 = null;
                    } else {
                        string7 = cursorQuery.getString(i6);
                        i7 = columnIndexOrThrow22;
                    }
                    List<String> listFromStringToListString = converters.fromStringToListString(cursorQuery.isNull(i7) ? null : cursorQuery.getString(i7));
                    if (cursorQuery.isNull(columnIndexOrThrow23)) {
                        i8 = columnIndexOrThrow24;
                        string8 = null;
                    } else {
                        string8 = cursorQuery.getString(columnIndexOrThrow23);
                        i8 = columnIndexOrThrow24;
                    }
                    if (cursorQuery.isNull(i8)) {
                        i9 = columnIndexOrThrow25;
                        string9 = null;
                    } else {
                        string9 = cursorQuery.getString(i8);
                        i9 = columnIndexOrThrow25;
                    }
                    if (cursorQuery.isNull(i9)) {
                        i10 = columnIndexOrThrow26;
                        string10 = null;
                    } else {
                        string10 = cursorQuery.getString(i9);
                        i10 = columnIndexOrThrow26;
                    }
                    if (cursorQuery.isNull(i10)) {
                        i11 = columnIndexOrThrow27;
                        string11 = null;
                    } else {
                        string11 = cursorQuery.getString(i10);
                        i11 = columnIndexOrThrow27;
                    }
                    if (cursorQuery.isNull(i11)) {
                        i12 = columnIndexOrThrow28;
                        string12 = null;
                    } else {
                        string12 = cursorQuery.getString(i11);
                        i12 = columnIndexOrThrow28;
                    }
                    if (cursorQuery.isNull(i12)) {
                        i13 = columnIndexOrThrow29;
                        string13 = null;
                    } else {
                        string13 = cursorQuery.getString(i12);
                        i13 = columnIndexOrThrow29;
                    }
                    appMetadata = new AppMetadata(j2, string14, j3, j4, mapFromStringToLocalizedTextV2, mapFromStringToLocalizedTextV22, mapFromStringToLocalizedTextV23, string16, string17, string18, string19, string20, string21, string, string2, string3, mapFromStringToLocalizedTextV24, string4, string5, string6, string7, listFromStringToListString, string8, string9, string10, string11, string12, string13, converters.fromStringToListString(cursorQuery.isNull(i13) ? null : cursorQuery.getString(i13)), cursorQuery.getInt(columnIndexOrThrow30) != 0);
                } else {
                    appMetadata = null;
                }
                cursorQuery.close();
                roomSQLiteQuery.release();
                return appMetadata;
            } catch (Throwable th) {
                th = th;
                cursorQuery.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
    }

    @Override // org.fdroid.database.AppDaoInt
    public List<AppMetadata> getAppMetadata() throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        String string;
        int i;
        String string2;
        int i2;
        String string3;
        int i3;
        String string4;
        int i4;
        String string5;
        int i5;
        int i6;
        String string6;
        String string7;
        int i7;
        String string8;
        int i8;
        String string9;
        int i9;
        String string10;
        int i10;
        String string11;
        String string12;
        int i11;
        String string13;
        int i12;
        String string14;
        int i13;
        String string15;
        int i14;
        String string16;
        int i15;
        String string17;
        int i16;
        String string18;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM AppMetadata", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "repoId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "packageName");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "added");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, AppListActivity.SortClause.LAST_UPDATED);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "name");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ErrorBundle.SUMMARY_ENTRY);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "description");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "localizedName");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "localizedSummary");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "webSite");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "changelog");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "license");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sourceCode");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "issueTracker");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "translation");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "preferredSigner");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "video");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "authorName");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "authorEmail");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "authorWebSite");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "authorPhone");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "donate");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "liberapayID");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "liberapay");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "openCollective");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "bitcoin");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "litecoin");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "flattrID");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "categories");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "isCompatible");
                int i17 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    long j = cursorQuery.getLong(columnIndexOrThrow);
                    String string19 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                    long j2 = cursorQuery.getLong(columnIndexOrThrow3);
                    long j3 = cursorQuery.getLong(columnIndexOrThrow4);
                    if (cursorQuery.isNull(columnIndexOrThrow5)) {
                        i = columnIndexOrThrow;
                        string = null;
                    } else {
                        string = cursorQuery.getString(columnIndexOrThrow5);
                        i = columnIndexOrThrow;
                    }
                    Converters converters = Converters.INSTANCE;
                    Map<String, String> mapFromStringToLocalizedTextV2 = converters.fromStringToLocalizedTextV2(string);
                    Map<String, String> mapFromStringToLocalizedTextV22 = converters.fromStringToLocalizedTextV2(cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6));
                    Map<String, String> mapFromStringToLocalizedTextV23 = converters.fromStringToLocalizedTextV2(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                    String string20 = cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8);
                    String string21 = cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9);
                    String string22 = cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10);
                    String string23 = cursorQuery.isNull(columnIndexOrThrow11) ? null : cursorQuery.getString(columnIndexOrThrow11);
                    String string24 = cursorQuery.isNull(columnIndexOrThrow12) ? null : cursorQuery.getString(columnIndexOrThrow12);
                    if (cursorQuery.isNull(columnIndexOrThrow13)) {
                        i2 = i17;
                        string2 = null;
                    } else {
                        string2 = cursorQuery.getString(columnIndexOrThrow13);
                        i2 = i17;
                    }
                    if (cursorQuery.isNull(i2)) {
                        i17 = i2;
                        i3 = columnIndexOrThrow15;
                        string3 = null;
                    } else {
                        string3 = cursorQuery.getString(i2);
                        i17 = i2;
                        i3 = columnIndexOrThrow15;
                    }
                    if (cursorQuery.isNull(i3)) {
                        columnIndexOrThrow15 = i3;
                        i4 = columnIndexOrThrow16;
                        string4 = null;
                    } else {
                        string4 = cursorQuery.getString(i3);
                        columnIndexOrThrow15 = i3;
                        i4 = columnIndexOrThrow16;
                    }
                    if (cursorQuery.isNull(i4)) {
                        columnIndexOrThrow16 = i4;
                        i5 = columnIndexOrThrow17;
                        string5 = null;
                    } else {
                        string5 = cursorQuery.getString(i4);
                        columnIndexOrThrow16 = i4;
                        i5 = columnIndexOrThrow17;
                    }
                    if (cursorQuery.isNull(i5)) {
                        i6 = i5;
                        string6 = null;
                    } else {
                        i6 = i5;
                        string6 = cursorQuery.getString(i5);
                    }
                    Map<String, String> mapFromStringToLocalizedTextV24 = converters.fromStringToLocalizedTextV2(string6);
                    int i18 = columnIndexOrThrow18;
                    if (cursorQuery.isNull(i18)) {
                        columnIndexOrThrow18 = i18;
                        i7 = columnIndexOrThrow19;
                        string7 = null;
                    } else {
                        columnIndexOrThrow18 = i18;
                        string7 = cursorQuery.getString(i18);
                        i7 = columnIndexOrThrow19;
                    }
                    if (cursorQuery.isNull(i7)) {
                        columnIndexOrThrow19 = i7;
                        i8 = columnIndexOrThrow20;
                        string8 = null;
                    } else {
                        columnIndexOrThrow19 = i7;
                        string8 = cursorQuery.getString(i7);
                        i8 = columnIndexOrThrow20;
                    }
                    if (cursorQuery.isNull(i8)) {
                        columnIndexOrThrow20 = i8;
                        i9 = columnIndexOrThrow21;
                        string9 = null;
                    } else {
                        columnIndexOrThrow20 = i8;
                        string9 = cursorQuery.getString(i8);
                        i9 = columnIndexOrThrow21;
                    }
                    if (cursorQuery.isNull(i9)) {
                        columnIndexOrThrow21 = i9;
                        i10 = columnIndexOrThrow22;
                        string10 = null;
                    } else {
                        columnIndexOrThrow21 = i9;
                        string10 = cursorQuery.getString(i9);
                        i10 = columnIndexOrThrow22;
                    }
                    if (cursorQuery.isNull(i10)) {
                        columnIndexOrThrow22 = i10;
                        string11 = null;
                    } else {
                        columnIndexOrThrow22 = i10;
                        string11 = cursorQuery.getString(i10);
                    }
                    List<String> listFromStringToListString = converters.fromStringToListString(string11);
                    int i19 = columnIndexOrThrow23;
                    if (cursorQuery.isNull(i19)) {
                        columnIndexOrThrow23 = i19;
                        i11 = columnIndexOrThrow24;
                        string12 = null;
                    } else {
                        columnIndexOrThrow23 = i19;
                        string12 = cursorQuery.getString(i19);
                        i11 = columnIndexOrThrow24;
                    }
                    if (cursorQuery.isNull(i11)) {
                        columnIndexOrThrow24 = i11;
                        i12 = columnIndexOrThrow25;
                        string13 = null;
                    } else {
                        columnIndexOrThrow24 = i11;
                        string13 = cursorQuery.getString(i11);
                        i12 = columnIndexOrThrow25;
                    }
                    if (cursorQuery.isNull(i12)) {
                        columnIndexOrThrow25 = i12;
                        i13 = columnIndexOrThrow26;
                        string14 = null;
                    } else {
                        columnIndexOrThrow25 = i12;
                        string14 = cursorQuery.getString(i12);
                        i13 = columnIndexOrThrow26;
                    }
                    if (cursorQuery.isNull(i13)) {
                        columnIndexOrThrow26 = i13;
                        i14 = columnIndexOrThrow27;
                        string15 = null;
                    } else {
                        columnIndexOrThrow26 = i13;
                        string15 = cursorQuery.getString(i13);
                        i14 = columnIndexOrThrow27;
                    }
                    if (cursorQuery.isNull(i14)) {
                        columnIndexOrThrow27 = i14;
                        i15 = columnIndexOrThrow28;
                        string16 = null;
                    } else {
                        columnIndexOrThrow27 = i14;
                        string16 = cursorQuery.getString(i14);
                        i15 = columnIndexOrThrow28;
                    }
                    if (cursorQuery.isNull(i15)) {
                        columnIndexOrThrow28 = i15;
                        i16 = columnIndexOrThrow29;
                        string17 = null;
                    } else {
                        columnIndexOrThrow28 = i15;
                        string17 = cursorQuery.getString(i15);
                        i16 = columnIndexOrThrow29;
                    }
                    if (cursorQuery.isNull(i16)) {
                        columnIndexOrThrow29 = i16;
                        string18 = null;
                    } else {
                        columnIndexOrThrow29 = i16;
                        string18 = cursorQuery.getString(i16);
                    }
                    List<String> listFromStringToListString2 = converters.fromStringToListString(string18);
                    int i20 = columnIndexOrThrow30;
                    arrayList.add(new AppMetadata(j, string19, j2, j3, mapFromStringToLocalizedTextV2, mapFromStringToLocalizedTextV22, mapFromStringToLocalizedTextV23, string20, string21, string22, string23, string24, string2, string3, string4, string5, mapFromStringToLocalizedTextV24, string7, string8, string9, string10, listFromStringToListString, string12, string13, string14, string15, string16, string17, listFromStringToListString2, cursorQuery.getInt(i20) != 0));
                    columnIndexOrThrow30 = i20;
                    columnIndexOrThrow = i;
                    columnIndexOrThrow17 = i6;
                }
                cursorQuery.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                cursorQuery.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
    }

    @Override // org.fdroid.database.AppDaoInt
    public List<LocalizedFile> getLocalizedFiles(long j, String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM LocalizedFile\n        WHERE repoId = ? AND packageName = ?", 2);
        roomSQLiteQueryAcquire.bindLong(1, j);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "repoId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "packageName");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, BonjourPeer.TYPE);
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "locale");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "name");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "sha256");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "ipfsCidV1");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new LocalizedFile(cursorQuery.getLong(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : Long.valueOf(cursorQuery.getLong(columnIndexOrThrow7)), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // org.fdroid.database.AppDaoInt
    public AppOverviewItem getAppOverviewItem(long j, String str) {
        ArrayList arrayList;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT repoId, packageName, added, app.lastUpdated, localizedName,\n             localizedSummary, app.isCompatible\n        FROM AppMetadata AS app WHERE repoId = ? AND packageName = ?", 2);
        roomSQLiteQueryAcquire.bindLong(1, j);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, str);
        }
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            AppOverviewItem appOverviewItem = null;
            String string = null;
            Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, true, null);
            try {
                ArrayMap arrayMap = new ArrayMap();
                while (cursorQuery.moveToNext()) {
                    String string2 = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                    if (string2 != null && !arrayMap.containsKey(string2)) {
                        arrayMap.put(string2, new ArrayList());
                    }
                }
                cursorQuery.moveToPosition(-1);
                __fetchRelationshipLocalizedIconAsorgFdroidDatabaseLocalizedIcon(arrayMap);
                if (cursorQuery.moveToFirst()) {
                    long j2 = cursorQuery.getLong(0);
                    String string3 = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                    long j3 = cursorQuery.getLong(2);
                    long j4 = cursorQuery.getLong(3);
                    String string4 = cursorQuery.isNull(4) ? null : cursorQuery.getString(4);
                    String string5 = cursorQuery.isNull(5) ? null : cursorQuery.getString(5);
                    boolean z = cursorQuery.getInt(6) != 0;
                    if (!cursorQuery.isNull(1)) {
                        string = cursorQuery.getString(1);
                    }
                    if (string != null) {
                        arrayList = (ArrayList) arrayMap.get(string);
                    } else {
                        arrayList = new ArrayList();
                    }
                    appOverviewItem = new AppOverviewItem(j2, string3, j3, j4, string4, string5, null, arrayList, z);
                }
                this.__db.setTransactionSuccessful();
                cursorQuery.close();
                roomSQLiteQueryAcquire.release();
                return appOverviewItem;
            } catch (Throwable th) {
                cursorQuery.close();
                roomSQLiteQueryAcquire.release();
                throw th;
            }
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.AppDaoInt
    public LiveData getAppListItemsByName() {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("\n        SELECT repoId, packageName, localizedName, localizedSummary, app.lastUpdated, \n               version.antiFeatures, app.isCompatible, app.preferredSigner\n        FROM AppMetadata AS app\n        LEFT JOIN HighestVersion AS version USING (repoId, packageName)\n        JOIN PreferredRepo USING (packageName)\n        JOIN RepositoryPreferences AS pref USING (repoId)\n        WHERE pref.enabled = 1 AND repoId = preferredRepoId\n        GROUP BY packageName HAVING MAX(pref.weight)\n        ORDER BY localizedName COLLATE NOCASE ASC", 0);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{LocalizedIcon.TABLE, AppMetadata.TABLE, HighestVersion.TABLE, "PreferredRepo", RepositoryPreferences.TABLE}, true, new Callable() { // from class: org.fdroid.database.AppDaoInt_Impl.22
            @Override // java.util.concurrent.Callable
            public List call() {
                ArrayList arrayList;
                AppDaoInt_Impl.this.__db.beginTransaction();
                try {
                    Cursor cursorQuery = DBUtil.query(AppDaoInt_Impl.this.__db, roomSQLiteQueryAcquire, true, null);
                    try {
                        ArrayMap arrayMap = new ArrayMap();
                        while (cursorQuery.moveToNext()) {
                            String string = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            if (string != null && !arrayMap.containsKey(string)) {
                                arrayMap.put(string, new ArrayList());
                            }
                        }
                        cursorQuery.moveToPosition(-1);
                        AppDaoInt_Impl.this.__fetchRelationshipLocalizedIconAsorgFdroidDatabaseLocalizedIcon(arrayMap);
                        ArrayList arrayList2 = new ArrayList(cursorQuery.getCount());
                        while (cursorQuery.moveToNext()) {
                            long j = cursorQuery.getLong(0);
                            String string2 = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            String string3 = cursorQuery.isNull(2) ? null : cursorQuery.getString(2);
                            String string4 = cursorQuery.isNull(3) ? null : cursorQuery.getString(3);
                            long j2 = cursorQuery.getLong(4);
                            String string5 = cursorQuery.isNull(5) ? null : cursorQuery.getString(5);
                            boolean z = cursorQuery.getInt(6) != 0;
                            String string6 = cursorQuery.isNull(7) ? null : cursorQuery.getString(7);
                            String string7 = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            if (string7 != null) {
                                arrayList = (ArrayList) arrayMap.get(string7);
                            } else {
                                arrayList = new ArrayList();
                            }
                            arrayList2.add(new AppListItem(j, string2, string3, string4, j2, string5, arrayList, z, string6, null, null));
                        }
                        AppDaoInt_Impl.this.__db.setTransactionSuccessful();
                        cursorQuery.close();
                        return arrayList2;
                    } catch (Throwable th) {
                        cursorQuery.close();
                        throw th;
                    }
                } finally {
                    AppDaoInt_Impl.this.__db.endTransaction();
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    @Override // org.fdroid.database.AppDaoInt
    public LiveData getAppListItemsByName(String str) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("\n        SELECT repoId, packageName, localizedName, localizedSummary, app.lastUpdated,\n               version.antiFeatures, app.isCompatible, app.preferredSigner\n        FROM AppMetadata AS app\n        JOIN RepositoryPreferences AS pref USING (repoId)\n        JOIN PreferredRepo USING (packageName)\n        LEFT JOIN HighestVersion AS version USING (repoId, packageName)\n        WHERE pref.enabled = 1 AND categories LIKE '%,' || ? || ',%' AND\n            repoId = preferredRepoId\n        GROUP BY packageName HAVING MAX(pref.weight)\n        ORDER BY localizedName COLLATE NOCASE ASC", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{LocalizedIcon.TABLE, AppMetadata.TABLE, RepositoryPreferences.TABLE, "PreferredRepo", HighestVersion.TABLE}, true, new Callable() { // from class: org.fdroid.database.AppDaoInt_Impl.23
            @Override // java.util.concurrent.Callable
            public List call() {
                ArrayList arrayList;
                AppDaoInt_Impl.this.__db.beginTransaction();
                try {
                    Cursor cursorQuery = DBUtil.query(AppDaoInt_Impl.this.__db, roomSQLiteQueryAcquire, true, null);
                    try {
                        ArrayMap arrayMap = new ArrayMap();
                        while (cursorQuery.moveToNext()) {
                            String string = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            if (string != null && !arrayMap.containsKey(string)) {
                                arrayMap.put(string, new ArrayList());
                            }
                        }
                        cursorQuery.moveToPosition(-1);
                        AppDaoInt_Impl.this.__fetchRelationshipLocalizedIconAsorgFdroidDatabaseLocalizedIcon(arrayMap);
                        ArrayList arrayList2 = new ArrayList(cursorQuery.getCount());
                        while (cursorQuery.moveToNext()) {
                            long j = cursorQuery.getLong(0);
                            String string2 = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            String string3 = cursorQuery.isNull(2) ? null : cursorQuery.getString(2);
                            String string4 = cursorQuery.isNull(3) ? null : cursorQuery.getString(3);
                            long j2 = cursorQuery.getLong(4);
                            String string5 = cursorQuery.isNull(5) ? null : cursorQuery.getString(5);
                            boolean z = cursorQuery.getInt(6) != 0;
                            String string6 = cursorQuery.isNull(7) ? null : cursorQuery.getString(7);
                            String string7 = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            if (string7 != null) {
                                arrayList = (ArrayList) arrayMap.get(string7);
                            } else {
                                arrayList = new ArrayList();
                            }
                            arrayList2.add(new AppListItem(j, string2, string3, string4, j2, string5, arrayList, z, string6, null, null));
                        }
                        AppDaoInt_Impl.this.__db.setTransactionSuccessful();
                        cursorQuery.close();
                        return arrayList2;
                    } catch (Throwable th) {
                        cursorQuery.close();
                        throw th;
                    }
                } finally {
                    AppDaoInt_Impl.this.__db.endTransaction();
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    @Override // org.fdroid.database.AppDaoInt
    public LiveData getAppListItemsByName(long j) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("\n        SELECT repoId, packageName, localizedName, localizedSummary, app.lastUpdated,\n               version.antiFeatures, app.isCompatible, app.preferredSigner\n        FROM AppMetadata AS app\n        LEFT JOIN HighestVersion AS version USING (repoId, packageName)\n        WHERE repoId = ?\n        ORDER BY localizedName COLLATE NOCASE ASC", 1);
        roomSQLiteQueryAcquire.bindLong(1, j);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{LocalizedIcon.TABLE, AppMetadata.TABLE, HighestVersion.TABLE}, true, new Callable() { // from class: org.fdroid.database.AppDaoInt_Impl.24
            @Override // java.util.concurrent.Callable
            public List call() {
                ArrayList arrayList;
                AppDaoInt_Impl.this.__db.beginTransaction();
                try {
                    Cursor cursorQuery = DBUtil.query(AppDaoInt_Impl.this.__db, roomSQLiteQueryAcquire, true, null);
                    try {
                        ArrayMap arrayMap = new ArrayMap();
                        while (cursorQuery.moveToNext()) {
                            String string = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            if (string != null && !arrayMap.containsKey(string)) {
                                arrayMap.put(string, new ArrayList());
                            }
                        }
                        cursorQuery.moveToPosition(-1);
                        AppDaoInt_Impl.this.__fetchRelationshipLocalizedIconAsorgFdroidDatabaseLocalizedIcon(arrayMap);
                        ArrayList arrayList2 = new ArrayList(cursorQuery.getCount());
                        while (cursorQuery.moveToNext()) {
                            long j2 = cursorQuery.getLong(0);
                            String string2 = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            String string3 = cursorQuery.isNull(2) ? null : cursorQuery.getString(2);
                            String string4 = cursorQuery.isNull(3) ? null : cursorQuery.getString(3);
                            long j3 = cursorQuery.getLong(4);
                            String string5 = cursorQuery.isNull(5) ? null : cursorQuery.getString(5);
                            boolean z = cursorQuery.getInt(6) != 0;
                            String string6 = cursorQuery.isNull(7) ? null : cursorQuery.getString(7);
                            String string7 = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            if (string7 != null) {
                                arrayList = (ArrayList) arrayMap.get(string7);
                            } else {
                                arrayList = new ArrayList();
                            }
                            arrayList2.add(new AppListItem(j2, string2, string3, string4, j3, string5, arrayList, z, string6, null, null));
                        }
                        AppDaoInt_Impl.this.__db.setTransactionSuccessful();
                        cursorQuery.close();
                        return arrayList2;
                    } catch (Throwable th) {
                        cursorQuery.close();
                        throw th;
                    }
                } finally {
                    AppDaoInt_Impl.this.__db.endTransaction();
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    @Override // org.fdroid.database.AppDaoInt
    public LiveData getAppListItemsByLastUpdated() {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("\n        SELECT repoId, packageName, localizedName, localizedSummary, app.lastUpdated,\n               version.antiFeatures, app.isCompatible, app.preferredSigner\n        FROM AppMetadata AS app\n        JOIN RepositoryPreferences AS pref USING (repoId)\n        JOIN PreferredRepo USING (packageName)\n        LEFT JOIN HighestVersion AS version USING (repoId, packageName)\n        WHERE pref.enabled = 1 AND repoId = PreferredRepo.preferredRepoId\n        GROUP BY packageName HAVING MAX(pref.weight)\n        ORDER BY app.lastUpdated DESC", 0);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{LocalizedIcon.TABLE, AppMetadata.TABLE, RepositoryPreferences.TABLE, "PreferredRepo", HighestVersion.TABLE}, true, new Callable() { // from class: org.fdroid.database.AppDaoInt_Impl.25
            @Override // java.util.concurrent.Callable
            public List call() {
                ArrayList arrayList;
                AppDaoInt_Impl.this.__db.beginTransaction();
                try {
                    Cursor cursorQuery = DBUtil.query(AppDaoInt_Impl.this.__db, roomSQLiteQueryAcquire, true, null);
                    try {
                        ArrayMap arrayMap = new ArrayMap();
                        while (cursorQuery.moveToNext()) {
                            String string = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            if (string != null && !arrayMap.containsKey(string)) {
                                arrayMap.put(string, new ArrayList());
                            }
                        }
                        cursorQuery.moveToPosition(-1);
                        AppDaoInt_Impl.this.__fetchRelationshipLocalizedIconAsorgFdroidDatabaseLocalizedIcon(arrayMap);
                        ArrayList arrayList2 = new ArrayList(cursorQuery.getCount());
                        while (cursorQuery.moveToNext()) {
                            long j = cursorQuery.getLong(0);
                            String string2 = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            String string3 = cursorQuery.isNull(2) ? null : cursorQuery.getString(2);
                            String string4 = cursorQuery.isNull(3) ? null : cursorQuery.getString(3);
                            long j2 = cursorQuery.getLong(4);
                            String string5 = cursorQuery.isNull(5) ? null : cursorQuery.getString(5);
                            boolean z = cursorQuery.getInt(6) != 0;
                            String string6 = cursorQuery.isNull(7) ? null : cursorQuery.getString(7);
                            String string7 = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            if (string7 != null) {
                                arrayList = (ArrayList) arrayMap.get(string7);
                            } else {
                                arrayList = new ArrayList();
                            }
                            arrayList2.add(new AppListItem(j, string2, string3, string4, j2, string5, arrayList, z, string6, null, null));
                        }
                        AppDaoInt_Impl.this.__db.setTransactionSuccessful();
                        cursorQuery.close();
                        return arrayList2;
                    } catch (Throwable th) {
                        cursorQuery.close();
                        throw th;
                    }
                } finally {
                    AppDaoInt_Impl.this.__db.endTransaction();
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    @Override // org.fdroid.database.AppDaoInt
    public LiveData getAppListItemsByLastUpdated(String str) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("\n        SELECT repoId, packageName, localizedName, localizedSummary, app.lastUpdated, \n               version.antiFeatures, app.isCompatible, app.preferredSigner\n        FROM AppMetadata AS app\n        JOIN RepositoryPreferences AS pref USING (repoId)\n        JOIN PreferredRepo USING (packageName)\n        LEFT JOIN HighestVersion AS version USING (repoId, packageName)\n        LEFT JOIN AppPrefs USING (packageName)\n        WHERE pref.enabled = 1 AND categories LIKE '%,' || ? || ',%' AND\n            repoId = PreferredRepo.preferredRepoId\n        GROUP BY packageName HAVING MAX(pref.weight)\n        ORDER BY app.lastUpdated DESC", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{LocalizedIcon.TABLE, AppMetadata.TABLE, RepositoryPreferences.TABLE, "PreferredRepo", HighestVersion.TABLE, AppPrefs.TABLE}, true, new Callable() { // from class: org.fdroid.database.AppDaoInt_Impl.26
            @Override // java.util.concurrent.Callable
            public List call() {
                ArrayList arrayList;
                AppDaoInt_Impl.this.__db.beginTransaction();
                try {
                    Cursor cursorQuery = DBUtil.query(AppDaoInt_Impl.this.__db, roomSQLiteQueryAcquire, true, null);
                    try {
                        ArrayMap arrayMap = new ArrayMap();
                        while (cursorQuery.moveToNext()) {
                            String string = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            if (string != null && !arrayMap.containsKey(string)) {
                                arrayMap.put(string, new ArrayList());
                            }
                        }
                        cursorQuery.moveToPosition(-1);
                        AppDaoInt_Impl.this.__fetchRelationshipLocalizedIconAsorgFdroidDatabaseLocalizedIcon(arrayMap);
                        ArrayList arrayList2 = new ArrayList(cursorQuery.getCount());
                        while (cursorQuery.moveToNext()) {
                            long j = cursorQuery.getLong(0);
                            String string2 = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            String string3 = cursorQuery.isNull(2) ? null : cursorQuery.getString(2);
                            String string4 = cursorQuery.isNull(3) ? null : cursorQuery.getString(3);
                            long j2 = cursorQuery.getLong(4);
                            String string5 = cursorQuery.isNull(5) ? null : cursorQuery.getString(5);
                            boolean z = cursorQuery.getInt(6) != 0;
                            String string6 = cursorQuery.isNull(7) ? null : cursorQuery.getString(7);
                            String string7 = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            if (string7 != null) {
                                arrayList = (ArrayList) arrayMap.get(string7);
                            } else {
                                arrayList = new ArrayList();
                            }
                            arrayList2.add(new AppListItem(j, string2, string3, string4, j2, string5, arrayList, z, string6, null, null));
                        }
                        AppDaoInt_Impl.this.__db.setTransactionSuccessful();
                        cursorQuery.close();
                        return arrayList2;
                    } catch (Throwable th) {
                        cursorQuery.close();
                        throw th;
                    }
                } finally {
                    AppDaoInt_Impl.this.__db.endTransaction();
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    @Override // org.fdroid.database.AppDaoInt
    public LiveData getAppListItemsByLastUpdated(long j) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("\n        SELECT repoId, packageName, localizedName, localizedSummary, app.lastUpdated, \n               version.antiFeatures, app.isCompatible, app.preferredSigner\n        FROM AppMetadata AS app\n        LEFT JOIN HighestVersion AS version USING (repoId, packageName)\n        WHERE repoId = ?\n        ORDER BY app.lastUpdated DESC", 1);
        roomSQLiteQueryAcquire.bindLong(1, j);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{LocalizedIcon.TABLE, AppMetadata.TABLE, HighestVersion.TABLE}, true, new Callable() { // from class: org.fdroid.database.AppDaoInt_Impl.27
            @Override // java.util.concurrent.Callable
            public List call() {
                ArrayList arrayList;
                AppDaoInt_Impl.this.__db.beginTransaction();
                try {
                    Cursor cursorQuery = DBUtil.query(AppDaoInt_Impl.this.__db, roomSQLiteQueryAcquire, true, null);
                    try {
                        ArrayMap arrayMap = new ArrayMap();
                        while (cursorQuery.moveToNext()) {
                            String string = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            if (string != null && !arrayMap.containsKey(string)) {
                                arrayMap.put(string, new ArrayList());
                            }
                        }
                        cursorQuery.moveToPosition(-1);
                        AppDaoInt_Impl.this.__fetchRelationshipLocalizedIconAsorgFdroidDatabaseLocalizedIcon(arrayMap);
                        ArrayList arrayList2 = new ArrayList(cursorQuery.getCount());
                        while (cursorQuery.moveToNext()) {
                            long j2 = cursorQuery.getLong(0);
                            String string2 = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            String string3 = cursorQuery.isNull(2) ? null : cursorQuery.getString(2);
                            String string4 = cursorQuery.isNull(3) ? null : cursorQuery.getString(3);
                            long j3 = cursorQuery.getLong(4);
                            String string5 = cursorQuery.isNull(5) ? null : cursorQuery.getString(5);
                            boolean z = cursorQuery.getInt(6) != 0;
                            String string6 = cursorQuery.isNull(7) ? null : cursorQuery.getString(7);
                            String string7 = cursorQuery.isNull(1) ? null : cursorQuery.getString(1);
                            if (string7 != null) {
                                arrayList = (ArrayList) arrayMap.get(string7);
                            } else {
                                arrayList = new ArrayList();
                            }
                            arrayList2.add(new AppListItem(j2, string2, string3, string4, j3, string5, arrayList, z, string6, null, null));
                        }
                        AppDaoInt_Impl.this.__db.setTransactionSuccessful();
                        cursorQuery.close();
                        return arrayList2;
                    } catch (Throwable th) {
                        cursorQuery.close();
                        throw th;
                    }
                } finally {
                    AppDaoInt_Impl.this.__db.endTransaction();
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    @Override // org.fdroid.database.AppDaoInt
    public int countApps() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM AppMetadata", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            return cursorQuery.moveToFirst() ? cursorQuery.getInt(0) : 0;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // org.fdroid.database.AppDaoInt
    public int countLocalizedFiles() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM LocalizedFile", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            return cursorQuery.moveToFirst() ? cursorQuery.getInt(0) : 0;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // org.fdroid.database.AppDaoInt
    public int countLocalizedFileLists() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM LocalizedFileList", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            return cursorQuery.moveToFirst() ? cursorQuery.getInt(0) : 0;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // org.fdroid.database.AppDaoInt, org.fdroid.database.AppDao
    public LiveData getAppListItems(PackageManager packageManager, String str, AppListSortOrder appListSortOrder) {
        return AppDaoInt.DefaultImpls.getAppListItems(this, packageManager, str, appListSortOrder);
    }

    @Override // org.fdroid.database.AppDaoInt, org.fdroid.database.AppDao
    public LiveData getAppListItems(PackageManager packageManager, String str, String str2, AppListSortOrder appListSortOrder) {
        return AppDaoInt.DefaultImpls.getAppListItems(this, packageManager, str, str2, appListSortOrder);
    }

    @Override // org.fdroid.database.AppDaoInt, org.fdroid.database.AppDao
    public LiveData getAppListItems(PackageManager packageManager, long j, String str, AppListSortOrder appListSortOrder) {
        return AppDaoInt.DefaultImpls.getAppListItems(this, packageManager, j, str, appListSortOrder);
    }

    @Override // org.fdroid.database.AppDaoInt, org.fdroid.database.AppDao
    public LiveData getInstalledAppListItems(PackageManager packageManager) {
        return AppDaoInt.DefaultImpls.getInstalledAppListItems(this, packageManager);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipLocalizedFileAsorgFdroidDatabaseLocalizedFile(ArrayMap arrayMap) {
        ArrayList arrayList;
        Set<String> setKeySet = arrayMap.keySet();
        if (setKeySet.isEmpty()) {
            return;
        }
        if (arrayMap.size() > 999) {
            RelationUtil.recursiveFetchArrayMap(arrayMap, true, new Function1() { // from class: org.fdroid.database.AppDaoInt_Impl$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return this.f$0.lambda$__fetchRelationshipLocalizedFileAsorgFdroidDatabaseLocalizedFile$0((ArrayMap) obj);
                }
            });
            return;
        }
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("SELECT `repoId`,`packageName`,`type`,`locale`,`name`,`sha256`,`size`,`ipfsCidV1` FROM `LocalizedFile` WHERE `packageName` IN (");
        int size = setKeySet.size();
        StringUtil.appendPlaceholders(sbNewStringBuilder, size);
        sbNewStringBuilder.append(")");
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire(sbNewStringBuilder.toString(), size);
        int i = 1;
        for (String str : setKeySet) {
            if (str == null) {
                roomSQLiteQueryAcquire.bindNull(i);
            } else {
                roomSQLiteQueryAcquire.bindString(i, str);
            }
            i++;
        }
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(cursorQuery, "packageName");
            if (columnIndex == -1) {
                return;
            }
            while (cursorQuery.moveToNext()) {
                String string = cursorQuery.isNull(columnIndex) ? null : cursorQuery.getString(columnIndex);
                if (string != null && (arrayList = (ArrayList) arrayMap.get(string)) != null) {
                    arrayList.add(new LocalizedFile(cursorQuery.getLong(0), cursorQuery.isNull(1) ? null : cursorQuery.getString(1), cursorQuery.isNull(2) ? null : cursorQuery.getString(2), cursorQuery.isNull(3) ? null : cursorQuery.getString(3), cursorQuery.isNull(4) ? null : cursorQuery.getString(4), cursorQuery.isNull(5) ? null : cursorQuery.getString(5), cursorQuery.isNull(6) ? null : Long.valueOf(cursorQuery.getLong(6)), cursorQuery.isNull(7) ? null : cursorQuery.getString(7)));
                }
            }
        } finally {
            cursorQuery.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$__fetchRelationshipLocalizedFileAsorgFdroidDatabaseLocalizedFile$0(ArrayMap arrayMap) {
        __fetchRelationshipLocalizedFileAsorgFdroidDatabaseLocalizedFile(arrayMap);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipLocalizedFileListAsorgFdroidDatabaseLocalizedFileList(ArrayMap arrayMap) {
        ArrayList arrayList;
        Set<String> setKeySet = arrayMap.keySet();
        if (setKeySet.isEmpty()) {
            return;
        }
        if (arrayMap.size() > 999) {
            RelationUtil.recursiveFetchArrayMap(arrayMap, true, new Function1() { // from class: org.fdroid.database.AppDaoInt_Impl$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return this.f$0.lambda$__fetchRelationshipLocalizedFileListAsorgFdroidDatabaseLocalizedFileList$1((ArrayMap) obj);
                }
            });
            return;
        }
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("SELECT `repoId`,`packageName`,`type`,`locale`,`name`,`sha256`,`size`,`ipfsCidV1` FROM `LocalizedFileList` WHERE `packageName` IN (");
        int size = setKeySet.size();
        StringUtil.appendPlaceholders(sbNewStringBuilder, size);
        sbNewStringBuilder.append(")");
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire(sbNewStringBuilder.toString(), size);
        int i = 1;
        for (String str : setKeySet) {
            if (str == null) {
                roomSQLiteQueryAcquire.bindNull(i);
            } else {
                roomSQLiteQueryAcquire.bindString(i, str);
            }
            i++;
        }
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(cursorQuery, "packageName");
            if (columnIndex == -1) {
                return;
            }
            while (cursorQuery.moveToNext()) {
                String string = cursorQuery.isNull(columnIndex) ? null : cursorQuery.getString(columnIndex);
                if (string != null && (arrayList = (ArrayList) arrayMap.get(string)) != null) {
                    arrayList.add(new LocalizedFileList(cursorQuery.getLong(0), cursorQuery.isNull(1) ? null : cursorQuery.getString(1), cursorQuery.isNull(2) ? null : cursorQuery.getString(2), cursorQuery.isNull(3) ? null : cursorQuery.getString(3), cursorQuery.isNull(4) ? null : cursorQuery.getString(4), cursorQuery.isNull(5) ? null : cursorQuery.getString(5), cursorQuery.isNull(6) ? null : Long.valueOf(cursorQuery.getLong(6)), cursorQuery.isNull(7) ? null : cursorQuery.getString(7)));
                }
            }
        } finally {
            cursorQuery.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$__fetchRelationshipLocalizedFileListAsorgFdroidDatabaseLocalizedFileList$1(ArrayMap arrayMap) {
        __fetchRelationshipLocalizedFileListAsorgFdroidDatabaseLocalizedFileList(arrayMap);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipLocalizedIconAsorgFdroidDatabaseLocalizedIcon(ArrayMap arrayMap) {
        ArrayList arrayList;
        Set<String> setKeySet = arrayMap.keySet();
        if (setKeySet.isEmpty()) {
            return;
        }
        if (arrayMap.size() > 999) {
            RelationUtil.recursiveFetchArrayMap(arrayMap, true, new Function1() { // from class: org.fdroid.database.AppDaoInt_Impl$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return this.f$0.lambda$__fetchRelationshipLocalizedIconAsorgFdroidDatabaseLocalizedIcon$2((ArrayMap) obj);
                }
            });
            return;
        }
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("SELECT `repoId`,`packageName`,`type`,`locale`,`name`,`sha256`,`size`,`ipfsCidV1` FROM `LocalizedIcon` WHERE `packageName` IN (");
        int size = setKeySet.size();
        StringUtil.appendPlaceholders(sbNewStringBuilder, size);
        sbNewStringBuilder.append(")");
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire(sbNewStringBuilder.toString(), size);
        int i = 1;
        for (String str : setKeySet) {
            if (str == null) {
                roomSQLiteQueryAcquire.bindNull(i);
            } else {
                roomSQLiteQueryAcquire.bindString(i, str);
            }
            i++;
        }
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(cursorQuery, "packageName");
            if (columnIndex == -1) {
                return;
            }
            while (cursorQuery.moveToNext()) {
                String string = cursorQuery.isNull(columnIndex) ? null : cursorQuery.getString(columnIndex);
                if (string != null && (arrayList = (ArrayList) arrayMap.get(string)) != null) {
                    arrayList.add(new LocalizedIcon(cursorQuery.getLong(0), cursorQuery.isNull(1) ? null : cursorQuery.getString(1), cursorQuery.isNull(2) ? null : cursorQuery.getString(2), cursorQuery.isNull(3) ? null : cursorQuery.getString(3), cursorQuery.isNull(4) ? null : cursorQuery.getString(4), cursorQuery.isNull(5) ? null : cursorQuery.getString(5), cursorQuery.isNull(6) ? null : Long.valueOf(cursorQuery.getLong(6)), cursorQuery.isNull(7) ? null : cursorQuery.getString(7)));
                }
            }
        } finally {
            cursorQuery.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$__fetchRelationshipLocalizedIconAsorgFdroidDatabaseLocalizedIcon$2(ArrayMap arrayMap) {
        __fetchRelationshipLocalizedIconAsorgFdroidDatabaseLocalizedIcon(arrayMap);
        return Unit.INSTANCE;
    }
}
