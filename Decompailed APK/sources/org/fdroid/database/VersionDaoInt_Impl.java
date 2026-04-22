package org.fdroid.database;

import android.database.Cursor;
import androidx.collection.ArrayMap;
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
import org.fdroid.database.VersionDaoInt;
import org.fdroid.fdroid.nearby.peers.BonjourPeer;
import org.fdroid.index.v2.FileV1;
import org.fdroid.index.v2.FileV2;
import org.fdroid.index.v2.PackageVersionV2;
import org.fdroid.index.v2.SignerV2;
import org.fdroid.index.v2.UsesSdkV2;

/* JADX INFO: loaded from: classes2.dex */
public final class VersionDaoInt_Impl implements VersionDaoInt {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter __insertionAdapterOfVersion;
    private final EntityInsertionAdapter __insertionAdapterOfVersionedString;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAppVersion;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAppVersion_1;
    private final SharedSQLiteStatement __preparedStmtOfDeleteVersionedStrings;
    private final SharedSQLiteStatement __preparedStmtOfDeleteVersionedStrings_1;
    private final EntityDeletionOrUpdateAdapter __updateAdapterOfVersion;

    public VersionDaoInt_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfVersion = new EntityInsertionAdapter(roomDatabase) { // from class: org.fdroid.database.VersionDaoInt_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `Version` (`repoId`,`packageName`,`versionId`,`added`,`releaseChannels`,`antiFeatures`,`whatsNew`,`isCompatible`,`file_name`,`file_sha256`,`file_size`,`file_ipfsCidV1`,`src_name`,`src_sha256`,`src_size`,`src_ipfsCidV1`,`manifest_versionName`,`manifest_versionCode`,`manifest_maxSdkVersion`,`manifest_nativecode`,`manifest_features`,`manifest_usesSdk_minSdkVersion`,`manifest_usesSdk_targetSdkVersion`,`manifest_signer_sha256`,`manifest_signer_hasMultipleSigners`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Version version) {
                supportSQLiteStatement.bindLong(1, version.getRepoId());
                if (version.getPackageName() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, version.getPackageName());
                }
                if (version.getVersionId() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, version.getVersionId());
                }
                supportSQLiteStatement.bindLong(4, version.getAdded());
                Converters converters = Converters.INSTANCE;
                String strListStringToString = converters.listStringToString(version.getReleaseChannels());
                if (strListStringToString == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, strListStringToString);
                }
                String strMapOfLocalizedTextV2toString = converters.mapOfLocalizedTextV2toString(version.getAntiFeatures());
                if (strMapOfLocalizedTextV2toString == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, strMapOfLocalizedTextV2toString);
                }
                String strLocalizedTextV2toString = converters.localizedTextV2toString(version.getWhatsNew());
                if (strLocalizedTextV2toString == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, strLocalizedTextV2toString);
                }
                supportSQLiteStatement.bindLong(8, version.isCompatible() ? 1L : 0L);
                FileV1 file = version.getFile();
                if (file.getName() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, file.getName());
                }
                if (file.getSha256() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, file.getSha256());
                }
                if (file.getSize() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindLong(11, file.getSize().longValue());
                }
                if (file.getIpfsCidV1() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, file.getIpfsCidV1());
                }
                FileV2 src = version.getSrc();
                if (src != null) {
                    if (src.getName() == null) {
                        supportSQLiteStatement.bindNull(13);
                    } else {
                        supportSQLiteStatement.bindString(13, src.getName());
                    }
                    if (src.getSha256() == null) {
                        supportSQLiteStatement.bindNull(14);
                    } else {
                        supportSQLiteStatement.bindString(14, src.getSha256());
                    }
                    if (src.getSize() == null) {
                        supportSQLiteStatement.bindNull(15);
                    } else {
                        supportSQLiteStatement.bindLong(15, src.getSize().longValue());
                    }
                    if (src.getIpfsCidV1() == null) {
                        supportSQLiteStatement.bindNull(16);
                    } else {
                        supportSQLiteStatement.bindString(16, src.getIpfsCidV1());
                    }
                } else {
                    supportSQLiteStatement.bindNull(13);
                    supportSQLiteStatement.bindNull(14);
                    supportSQLiteStatement.bindNull(15);
                    supportSQLiteStatement.bindNull(16);
                }
                AppManifest manifest = version.getManifest();
                if (manifest.getVersionName() == null) {
                    supportSQLiteStatement.bindNull(17);
                } else {
                    supportSQLiteStatement.bindString(17, manifest.getVersionName());
                }
                supportSQLiteStatement.bindLong(18, manifest.getVersionCode());
                if (manifest.getMaxSdkVersion() == null) {
                    supportSQLiteStatement.bindNull(19);
                } else {
                    supportSQLiteStatement.bindLong(19, manifest.getMaxSdkVersion().intValue());
                }
                String strListStringToString2 = converters.listStringToString(manifest.getNativecode());
                if (strListStringToString2 == null) {
                    supportSQLiteStatement.bindNull(20);
                } else {
                    supportSQLiteStatement.bindString(20, strListStringToString2);
                }
                String strListStringToString3 = converters.listStringToString(manifest.getFeatures());
                if (strListStringToString3 == null) {
                    supportSQLiteStatement.bindNull(21);
                } else {
                    supportSQLiteStatement.bindString(21, strListStringToString3);
                }
                if (manifest.getUsesSdk() != null) {
                    supportSQLiteStatement.bindLong(22, r1.getMinSdkVersion());
                    supportSQLiteStatement.bindLong(23, r1.getTargetSdkVersion());
                } else {
                    supportSQLiteStatement.bindNull(22);
                    supportSQLiteStatement.bindNull(23);
                }
                SignerV2 signer = manifest.getSigner();
                if (signer != null) {
                    String strListStringToString4 = converters.listStringToString(signer.getSha256());
                    if (strListStringToString4 == null) {
                        supportSQLiteStatement.bindNull(24);
                    } else {
                        supportSQLiteStatement.bindString(24, strListStringToString4);
                    }
                    supportSQLiteStatement.bindLong(25, signer.getHasMultipleSigners() ? 1L : 0L);
                    return;
                }
                supportSQLiteStatement.bindNull(24);
                supportSQLiteStatement.bindNull(25);
            }
        };
        this.__insertionAdapterOfVersionedString = new EntityInsertionAdapter(roomDatabase) { // from class: org.fdroid.database.VersionDaoInt_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `VersionedString` (`repoId`,`packageName`,`versionId`,`type`,`name`,`version`) VALUES (?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, VersionedString versionedString) {
                supportSQLiteStatement.bindLong(1, versionedString.getRepoId());
                if (versionedString.getPackageName() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, versionedString.getPackageName());
                }
                if (versionedString.getVersionId() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, versionedString.getVersionId());
                }
                supportSQLiteStatement.bindString(4, VersionDaoInt_Impl.this.__VersionedStringType_enumToString(versionedString.getType()));
                if (versionedString.getName() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, versionedString.getName());
                }
                if (versionedString.getVersion() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindLong(6, versionedString.getVersion().intValue());
                }
            }
        };
        this.__updateAdapterOfVersion = new EntityDeletionOrUpdateAdapter(roomDatabase) { // from class: org.fdroid.database.VersionDaoInt_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "UPDATE OR ABORT `Version` SET `repoId` = ?,`packageName` = ?,`versionId` = ?,`added` = ?,`releaseChannels` = ?,`antiFeatures` = ?,`whatsNew` = ?,`isCompatible` = ?,`file_name` = ?,`file_sha256` = ?,`file_size` = ?,`file_ipfsCidV1` = ?,`src_name` = ?,`src_sha256` = ?,`src_size` = ?,`src_ipfsCidV1` = ?,`manifest_versionName` = ?,`manifest_versionCode` = ?,`manifest_maxSdkVersion` = ?,`manifest_nativecode` = ?,`manifest_features` = ?,`manifest_usesSdk_minSdkVersion` = ?,`manifest_usesSdk_targetSdkVersion` = ?,`manifest_signer_sha256` = ?,`manifest_signer_hasMultipleSigners` = ? WHERE `repoId` = ? AND `packageName` = ? AND `versionId` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Version version) {
                supportSQLiteStatement.bindLong(1, version.getRepoId());
                if (version.getPackageName() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, version.getPackageName());
                }
                if (version.getVersionId() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, version.getVersionId());
                }
                supportSQLiteStatement.bindLong(4, version.getAdded());
                Converters converters = Converters.INSTANCE;
                String strListStringToString = converters.listStringToString(version.getReleaseChannels());
                if (strListStringToString == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, strListStringToString);
                }
                String strMapOfLocalizedTextV2toString = converters.mapOfLocalizedTextV2toString(version.getAntiFeatures());
                if (strMapOfLocalizedTextV2toString == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, strMapOfLocalizedTextV2toString);
                }
                String strLocalizedTextV2toString = converters.localizedTextV2toString(version.getWhatsNew());
                if (strLocalizedTextV2toString == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, strLocalizedTextV2toString);
                }
                supportSQLiteStatement.bindLong(8, version.isCompatible() ? 1L : 0L);
                FileV1 file = version.getFile();
                if (file.getName() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, file.getName());
                }
                if (file.getSha256() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, file.getSha256());
                }
                if (file.getSize() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindLong(11, file.getSize().longValue());
                }
                if (file.getIpfsCidV1() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, file.getIpfsCidV1());
                }
                FileV2 src = version.getSrc();
                if (src != null) {
                    if (src.getName() == null) {
                        supportSQLiteStatement.bindNull(13);
                    } else {
                        supportSQLiteStatement.bindString(13, src.getName());
                    }
                    if (src.getSha256() == null) {
                        supportSQLiteStatement.bindNull(14);
                    } else {
                        supportSQLiteStatement.bindString(14, src.getSha256());
                    }
                    if (src.getSize() == null) {
                        supportSQLiteStatement.bindNull(15);
                    } else {
                        supportSQLiteStatement.bindLong(15, src.getSize().longValue());
                    }
                    if (src.getIpfsCidV1() == null) {
                        supportSQLiteStatement.bindNull(16);
                    } else {
                        supportSQLiteStatement.bindString(16, src.getIpfsCidV1());
                    }
                } else {
                    supportSQLiteStatement.bindNull(13);
                    supportSQLiteStatement.bindNull(14);
                    supportSQLiteStatement.bindNull(15);
                    supportSQLiteStatement.bindNull(16);
                }
                AppManifest manifest = version.getManifest();
                if (manifest.getVersionName() == null) {
                    supportSQLiteStatement.bindNull(17);
                } else {
                    supportSQLiteStatement.bindString(17, manifest.getVersionName());
                }
                supportSQLiteStatement.bindLong(18, manifest.getVersionCode());
                if (manifest.getMaxSdkVersion() == null) {
                    supportSQLiteStatement.bindNull(19);
                } else {
                    supportSQLiteStatement.bindLong(19, manifest.getMaxSdkVersion().intValue());
                }
                String strListStringToString2 = converters.listStringToString(manifest.getNativecode());
                if (strListStringToString2 == null) {
                    supportSQLiteStatement.bindNull(20);
                } else {
                    supportSQLiteStatement.bindString(20, strListStringToString2);
                }
                String strListStringToString3 = converters.listStringToString(manifest.getFeatures());
                if (strListStringToString3 == null) {
                    supportSQLiteStatement.bindNull(21);
                } else {
                    supportSQLiteStatement.bindString(21, strListStringToString3);
                }
                if (manifest.getUsesSdk() != null) {
                    supportSQLiteStatement.bindLong(22, r2.getMinSdkVersion());
                    supportSQLiteStatement.bindLong(23, r2.getTargetSdkVersion());
                } else {
                    supportSQLiteStatement.bindNull(22);
                    supportSQLiteStatement.bindNull(23);
                }
                SignerV2 signer = manifest.getSigner();
                if (signer != null) {
                    String strListStringToString4 = converters.listStringToString(signer.getSha256());
                    if (strListStringToString4 == null) {
                        supportSQLiteStatement.bindNull(24);
                    } else {
                        supportSQLiteStatement.bindString(24, strListStringToString4);
                    }
                    supportSQLiteStatement.bindLong(25, signer.getHasMultipleSigners() ? 1L : 0L);
                } else {
                    supportSQLiteStatement.bindNull(24);
                    supportSQLiteStatement.bindNull(25);
                }
                supportSQLiteStatement.bindLong(26, version.getRepoId());
                if (version.getPackageName() == null) {
                    supportSQLiteStatement.bindNull(27);
                } else {
                    supportSQLiteStatement.bindString(27, version.getPackageName());
                }
                if (version.getVersionId() == null) {
                    supportSQLiteStatement.bindNull(28);
                } else {
                    supportSQLiteStatement.bindString(28, version.getVersionId());
                }
            }
        };
        this.__preparedStmtOfDeleteAppVersion = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.VersionDaoInt_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM Version WHERE repoId = ? AND packageName = ?";
            }
        };
        this.__preparedStmtOfDeleteAppVersion_1 = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.VersionDaoInt_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM Version\n        WHERE repoId = ? AND packageName = ? AND versionId = ?";
            }
        };
        this.__preparedStmtOfDeleteVersionedStrings = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.VersionDaoInt_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM VersionedString\n        WHERE repoId = ? AND packageName = ? AND versionId = ?";
            }
        };
        this.__preparedStmtOfDeleteVersionedStrings_1 = new SharedSQLiteStatement(roomDatabase) { // from class: org.fdroid.database.VersionDaoInt_Impl.7
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM VersionedString WHERE repoId = ?\n        AND packageName = ? AND versionId = ? AND type = ?";
            }
        };
    }

    @Override // org.fdroid.database.VersionDaoInt
    public void insert(Version version) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfVersion.insert(version);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.VersionDaoInt
    public void insert(List<VersionedString> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfVersionedString.insert((Iterable) list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.VersionDaoInt
    public void update(Version version) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfVersion.handle(version);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.VersionDaoInt, org.fdroid.database.VersionDao
    public void insert(long j, String str, Map<String, PackageVersionV2> map, Function1 function1) {
        this.__db.beginTransaction();
        try {
            VersionDaoInt.DefaultImpls.insert(this, j, str, map, function1);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.VersionDaoInt
    public void insert(long j, String str, String str2, PackageVersionV2 packageVersionV2, boolean z) {
        this.__db.beginTransaction();
        try {
            VersionDaoInt.DefaultImpls.insert(this, j, str, str2, packageVersionV2, z);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.VersionDaoInt
    public void deleteAppVersion(long j, String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteAppVersion.acquire();
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
            this.__preparedStmtOfDeleteAppVersion.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.VersionDaoInt
    public void deleteAppVersion(long j, String str, String str2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteAppVersion_1.acquire();
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
            this.__preparedStmtOfDeleteAppVersion_1.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.VersionDaoInt
    public void deleteVersionedStrings(long j, String str, String str2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteVersionedStrings.acquire();
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
            this.__preparedStmtOfDeleteVersionedStrings.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.VersionDaoInt
    public void deleteVersionedStrings(long j, String str, String str2, VersionedStringType versionedStringType) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteVersionedStrings_1.acquire();
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
        supportSQLiteStatementAcquire.bindString(4, __VersionedStringType_enumToString(versionedStringType));
        try {
            this.__db.beginTransaction();
            try {
                supportSQLiteStatementAcquire.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOfDeleteVersionedStrings_1.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // org.fdroid.database.VersionDaoInt, org.fdroid.database.VersionDao
    public LiveData getAppVersions(String str) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT `repoId`, `packageName`, `versionId`, `added`, `releaseChannels`, `antiFeatures`, `whatsNew`, `isCompatible`, `file_name`, `file_sha256`, `file_size`, `file_ipfsCidV1`, `src_name`, `src_sha256`, `src_size`, `src_ipfsCidV1`, `manifest_versionName`, `manifest_versionCode`, `manifest_maxSdkVersion`, `manifest_nativecode`, `manifest_features`, `manifest_usesSdk_minSdkVersion`, `manifest_usesSdk_targetSdkVersion`, `manifest_signer_sha256`, `manifest_signer_hasMultipleSigners` FROM (SELECT * FROM Version\n        JOIN RepositoryPreferences AS pref USING (repoId)\n        WHERE pref.enabled = 1 AND packageName = ?\n        ORDER BY manifest_versionCode DESC, pref.weight DESC, manifest_nativecode ASC)", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{VersionedString.TABLE, Version.TABLE, RepositoryPreferences.TABLE}, true, new Callable() { // from class: org.fdroid.database.VersionDaoInt_Impl.8
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.concurrent.Callable
            public List call() {
                ArrayMap arrayMap;
                int i;
                FileV2 fileV2;
                SignerV2 signerV2;
                ArrayList arrayList;
                AnonymousClass8 anonymousClass8 = this;
                VersionDaoInt_Impl.this.__db.beginTransaction();
                try {
                    int i2 = 1;
                    String str2 = null;
                    Cursor cursorQuery = DBUtil.query(VersionDaoInt_Impl.this.__db, roomSQLiteQueryAcquire, true, null);
                    try {
                        arrayMap = new ArrayMap();
                    } catch (Throwable th) {
                        th = th;
                    }
                    while (true) {
                        i = 2;
                        if (!cursorQuery.moveToNext()) {
                            break;
                        }
                        String string = cursorQuery.isNull(2) ? null : cursorQuery.getString(2);
                        if (string != null && !arrayMap.containsKey(string)) {
                            arrayMap.put(string, new ArrayList());
                        }
                        cursorQuery.close();
                        throw th;
                    }
                    cursorQuery.moveToPosition(-1);
                    VersionDaoInt_Impl.this.__fetchRelationshipVersionedStringAsorgFdroidDatabaseVersionedString(arrayMap);
                    ArrayList arrayList2 = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        try {
                            long j = cursorQuery.getLong(0);
                            String string2 = cursorQuery.isNull(i2) ? str2 : cursorQuery.getString(i2);
                            String string3 = cursorQuery.isNull(i) ? str2 : cursorQuery.getString(i);
                            long j2 = cursorQuery.getLong(3);
                            String string4 = cursorQuery.isNull(4) ? str2 : cursorQuery.getString(4);
                            Converters converters = Converters.INSTANCE;
                            List<String> listFromStringToListString = converters.fromStringToListString(string4);
                            Map<String, Map<String, String>> mapFromStringToMapOfLocalizedTextV2 = converters.fromStringToMapOfLocalizedTextV2(cursorQuery.isNull(5) ? str2 : cursorQuery.getString(5));
                            Map<String, String> mapFromStringToLocalizedTextV2 = converters.fromStringToLocalizedTextV2(cursorQuery.isNull(6) ? str2 : cursorQuery.getString(6));
                            boolean z = cursorQuery.getInt(7) != 0 ? i2 : 0;
                            FileV1 fileV1 = new FileV1(cursorQuery.isNull(8) ? str2 : cursorQuery.getString(8), cursorQuery.isNull(9) ? str2 : cursorQuery.getString(9), cursorQuery.isNull(10) ? null : Long.valueOf(cursorQuery.getLong(10)), cursorQuery.isNull(11) ? null : cursorQuery.getString(11));
                            if (cursorQuery.isNull(12) && cursorQuery.isNull(13) && cursorQuery.isNull(14) && cursorQuery.isNull(15)) {
                                fileV2 = null;
                            } else {
                                fileV2 = new FileV2(cursorQuery.isNull(12) ? null : cursorQuery.getString(12), cursorQuery.isNull(13) ? null : cursorQuery.getString(13), cursorQuery.isNull(14) ? null : Long.valueOf(cursorQuery.getLong(14)), cursorQuery.isNull(15) ? null : cursorQuery.getString(15));
                            }
                            String string5 = cursorQuery.isNull(16) ? null : cursorQuery.getString(16);
                            long j3 = cursorQuery.getLong(17);
                            Integer numValueOf = cursorQuery.isNull(18) ? null : Integer.valueOf(cursorQuery.getInt(18));
                            List<String> listFromStringToListString2 = converters.fromStringToListString(cursorQuery.isNull(19) ? null : cursorQuery.getString(19));
                            List<String> listFromStringToListString3 = converters.fromStringToListString(cursorQuery.isNull(20) ? null : cursorQuery.getString(20));
                            UsesSdkV2 usesSdkV2 = (cursorQuery.isNull(21) && cursorQuery.isNull(22)) ? null : new UsesSdkV2(cursorQuery.getInt(21), cursorQuery.getInt(22));
                            if (cursorQuery.isNull(23) && cursorQuery.isNull(24)) {
                                signerV2 = null;
                            } else {
                                signerV2 = new SignerV2(converters.fromStringToListString(cursorQuery.isNull(23) ? null : cursorQuery.getString(23)), cursorQuery.getInt(24) != 0);
                            }
                            Version version = new Version(j, string2, string3, j2, fileV1, fileV2, new AppManifest(string5, j3, usesSdkV2, numValueOf, signerV2, listFromStringToListString2, listFromStringToListString3), listFromStringToListString, mapFromStringToMapOfLocalizedTextV2, mapFromStringToLocalizedTextV2, z);
                            String string6 = cursorQuery.isNull(2) ? null : cursorQuery.getString(2);
                            if (string6 != null) {
                                arrayList = (ArrayList) arrayMap.get(string6);
                            } else {
                                arrayList = new ArrayList();
                            }
                            arrayList2.add(new AppVersion(version, arrayList));
                            i2 = 1;
                            str2 = null;
                            i = 2;
                            anonymousClass8 = this;
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                    VersionDaoInt_Impl.this.__db.setTransactionSuccessful();
                    cursorQuery.close();
                    return arrayList2;
                } finally {
                    VersionDaoInt_Impl.this.__db.endTransaction();
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    @Override // org.fdroid.database.VersionDaoInt, org.fdroid.database.VersionDao
    public LiveData getAppVersions(long j, String str) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM Version\n        WHERE repoId = ? AND packageName = ?\n        ORDER BY manifest_versionCode DESC, manifest_nativecode ASC", 2);
        roomSQLiteQueryAcquire.bindLong(1, j);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, str);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{VersionedString.TABLE, Version.TABLE}, true, new Callable() { // from class: org.fdroid.database.VersionDaoInt_Impl.9
            /* JADX WARN: Removed duplicated region for block: B:100:0x0293 A[Catch: all -> 0x00f7, TryCatch #1 {all -> 0x00f7, blocks: (B:4:0x0019, B:5:0x00cc, B:7:0x00d2, B:12:0x00e6, B:14:0x00ec, B:10:0x00dc, B:19:0x0101, B:20:0x0117, B:22:0x011d, B:26:0x0130, B:30:0x013f, B:34:0x0153, B:38:0x0165, B:42:0x0175, B:46:0x0186, B:50:0x0192, B:54:0x01a4, B:59:0x01c1, B:63:0x01d7, B:65:0x01e4, B:67:0x01ec, B:69:0x01f4, B:76:0x0216, B:80:0x0228, B:84:0x023a, B:88:0x0250, B:92:0x0266, B:93:0x026f, B:97:0x0282, B:101:0x029f, B:105:0x02ab, B:109:0x02c3, B:111:0x02cf, B:116:0x02ec, B:117:0x0301, B:119:0x0307, B:124:0x0317, B:128:0x0323, B:132:0x0333, B:133:0x033a, B:138:0x0358, B:140:0x0364, B:139:0x035f, B:136:0x0352, B:127:0x031f, B:108:0x02bb, B:104:0x02a7, B:100:0x0293, B:96:0x027a, B:91:0x025c, B:87:0x0244, B:83:0x0232, B:79:0x0220, B:62:0x01cd, B:58:0x01b4, B:53:0x019c, B:49:0x018e, B:41:0x0171, B:37:0x0161, B:33:0x014d, B:29:0x0139, B:25:0x012a, B:141:0x0390), top: B:152:0x0019, outer: #0 }] */
            /* JADX WARN: Removed duplicated region for block: B:103:0x02a5  */
            /* JADX WARN: Removed duplicated region for block: B:104:0x02a7 A[Catch: all -> 0x00f7, TryCatch #1 {all -> 0x00f7, blocks: (B:4:0x0019, B:5:0x00cc, B:7:0x00d2, B:12:0x00e6, B:14:0x00ec, B:10:0x00dc, B:19:0x0101, B:20:0x0117, B:22:0x011d, B:26:0x0130, B:30:0x013f, B:34:0x0153, B:38:0x0165, B:42:0x0175, B:46:0x0186, B:50:0x0192, B:54:0x01a4, B:59:0x01c1, B:63:0x01d7, B:65:0x01e4, B:67:0x01ec, B:69:0x01f4, B:76:0x0216, B:80:0x0228, B:84:0x023a, B:88:0x0250, B:92:0x0266, B:93:0x026f, B:97:0x0282, B:101:0x029f, B:105:0x02ab, B:109:0x02c3, B:111:0x02cf, B:116:0x02ec, B:117:0x0301, B:119:0x0307, B:124:0x0317, B:128:0x0323, B:132:0x0333, B:133:0x033a, B:138:0x0358, B:140:0x0364, B:139:0x035f, B:136:0x0352, B:127:0x031f, B:108:0x02bb, B:104:0x02a7, B:100:0x0293, B:96:0x027a, B:91:0x025c, B:87:0x0244, B:83:0x0232, B:79:0x0220, B:62:0x01cd, B:58:0x01b4, B:53:0x019c, B:49:0x018e, B:41:0x0171, B:37:0x0161, B:33:0x014d, B:29:0x0139, B:25:0x012a, B:141:0x0390), top: B:152:0x0019, outer: #0 }] */
            /* JADX WARN: Removed duplicated region for block: B:107:0x02b7  */
            /* JADX WARN: Removed duplicated region for block: B:108:0x02bb A[Catch: all -> 0x00f7, TryCatch #1 {all -> 0x00f7, blocks: (B:4:0x0019, B:5:0x00cc, B:7:0x00d2, B:12:0x00e6, B:14:0x00ec, B:10:0x00dc, B:19:0x0101, B:20:0x0117, B:22:0x011d, B:26:0x0130, B:30:0x013f, B:34:0x0153, B:38:0x0165, B:42:0x0175, B:46:0x0186, B:50:0x0192, B:54:0x01a4, B:59:0x01c1, B:63:0x01d7, B:65:0x01e4, B:67:0x01ec, B:69:0x01f4, B:76:0x0216, B:80:0x0228, B:84:0x023a, B:88:0x0250, B:92:0x0266, B:93:0x026f, B:97:0x0282, B:101:0x029f, B:105:0x02ab, B:109:0x02c3, B:111:0x02cf, B:116:0x02ec, B:117:0x0301, B:119:0x0307, B:124:0x0317, B:128:0x0323, B:132:0x0333, B:133:0x033a, B:138:0x0358, B:140:0x0364, B:139:0x035f, B:136:0x0352, B:127:0x031f, B:108:0x02bb, B:104:0x02a7, B:100:0x0293, B:96:0x027a, B:91:0x025c, B:87:0x0244, B:83:0x0232, B:79:0x0220, B:62:0x01cd, B:58:0x01b4, B:53:0x019c, B:49:0x018e, B:41:0x0171, B:37:0x0161, B:33:0x014d, B:29:0x0139, B:25:0x012a, B:141:0x0390), top: B:152:0x0019, outer: #0 }] */
            /* JADX WARN: Removed duplicated region for block: B:111:0x02cf A[Catch: all -> 0x00f7, TryCatch #1 {all -> 0x00f7, blocks: (B:4:0x0019, B:5:0x00cc, B:7:0x00d2, B:12:0x00e6, B:14:0x00ec, B:10:0x00dc, B:19:0x0101, B:20:0x0117, B:22:0x011d, B:26:0x0130, B:30:0x013f, B:34:0x0153, B:38:0x0165, B:42:0x0175, B:46:0x0186, B:50:0x0192, B:54:0x01a4, B:59:0x01c1, B:63:0x01d7, B:65:0x01e4, B:67:0x01ec, B:69:0x01f4, B:76:0x0216, B:80:0x0228, B:84:0x023a, B:88:0x0250, B:92:0x0266, B:93:0x026f, B:97:0x0282, B:101:0x029f, B:105:0x02ab, B:109:0x02c3, B:111:0x02cf, B:116:0x02ec, B:117:0x0301, B:119:0x0307, B:124:0x0317, B:128:0x0323, B:132:0x0333, B:133:0x033a, B:138:0x0358, B:140:0x0364, B:139:0x035f, B:136:0x0352, B:127:0x031f, B:108:0x02bb, B:104:0x02a7, B:100:0x0293, B:96:0x027a, B:91:0x025c, B:87:0x0244, B:83:0x0232, B:79:0x0220, B:62:0x01cd, B:58:0x01b4, B:53:0x019c, B:49:0x018e, B:41:0x0171, B:37:0x0161, B:33:0x014d, B:29:0x0139, B:25:0x012a, B:141:0x0390), top: B:152:0x0019, outer: #0 }] */
            /* JADX WARN: Removed duplicated region for block: B:115:0x02e7  */
            /* JADX WARN: Removed duplicated region for block: B:119:0x0307 A[Catch: all -> 0x00f7, TryCatch #1 {all -> 0x00f7, blocks: (B:4:0x0019, B:5:0x00cc, B:7:0x00d2, B:12:0x00e6, B:14:0x00ec, B:10:0x00dc, B:19:0x0101, B:20:0x0117, B:22:0x011d, B:26:0x0130, B:30:0x013f, B:34:0x0153, B:38:0x0165, B:42:0x0175, B:46:0x0186, B:50:0x0192, B:54:0x01a4, B:59:0x01c1, B:63:0x01d7, B:65:0x01e4, B:67:0x01ec, B:69:0x01f4, B:76:0x0216, B:80:0x0228, B:84:0x023a, B:88:0x0250, B:92:0x0266, B:93:0x026f, B:97:0x0282, B:101:0x029f, B:105:0x02ab, B:109:0x02c3, B:111:0x02cf, B:116:0x02ec, B:117:0x0301, B:119:0x0307, B:124:0x0317, B:128:0x0323, B:132:0x0333, B:133:0x033a, B:138:0x0358, B:140:0x0364, B:139:0x035f, B:136:0x0352, B:127:0x031f, B:108:0x02bb, B:104:0x02a7, B:100:0x0293, B:96:0x027a, B:91:0x025c, B:87:0x0244, B:83:0x0232, B:79:0x0220, B:62:0x01cd, B:58:0x01b4, B:53:0x019c, B:49:0x018e, B:41:0x0171, B:37:0x0161, B:33:0x014d, B:29:0x0139, B:25:0x012a, B:141:0x0390), top: B:152:0x0019, outer: #0 }] */
            /* JADX WARN: Removed duplicated region for block: B:123:0x0315  */
            /* JADX WARN: Removed duplicated region for block: B:126:0x031d  */
            /* JADX WARN: Removed duplicated region for block: B:127:0x031f A[Catch: all -> 0x00f7, TryCatch #1 {all -> 0x00f7, blocks: (B:4:0x0019, B:5:0x00cc, B:7:0x00d2, B:12:0x00e6, B:14:0x00ec, B:10:0x00dc, B:19:0x0101, B:20:0x0117, B:22:0x011d, B:26:0x0130, B:30:0x013f, B:34:0x0153, B:38:0x0165, B:42:0x0175, B:46:0x0186, B:50:0x0192, B:54:0x01a4, B:59:0x01c1, B:63:0x01d7, B:65:0x01e4, B:67:0x01ec, B:69:0x01f4, B:76:0x0216, B:80:0x0228, B:84:0x023a, B:88:0x0250, B:92:0x0266, B:93:0x026f, B:97:0x0282, B:101:0x029f, B:105:0x02ab, B:109:0x02c3, B:111:0x02cf, B:116:0x02ec, B:117:0x0301, B:119:0x0307, B:124:0x0317, B:128:0x0323, B:132:0x0333, B:133:0x033a, B:138:0x0358, B:140:0x0364, B:139:0x035f, B:136:0x0352, B:127:0x031f, B:108:0x02bb, B:104:0x02a7, B:100:0x0293, B:96:0x027a, B:91:0x025c, B:87:0x0244, B:83:0x0232, B:79:0x0220, B:62:0x01cd, B:58:0x01b4, B:53:0x019c, B:49:0x018e, B:41:0x0171, B:37:0x0161, B:33:0x014d, B:29:0x0139, B:25:0x012a, B:141:0x0390), top: B:152:0x0019, outer: #0 }] */
            /* JADX WARN: Removed duplicated region for block: B:130:0x032f  */
            /* JADX WARN: Removed duplicated region for block: B:131:0x0331  */
            /* JADX WARN: Removed duplicated region for block: B:135:0x0350  */
            /* JADX WARN: Removed duplicated region for block: B:136:0x0352 A[Catch: all -> 0x00f7, TryCatch #1 {all -> 0x00f7, blocks: (B:4:0x0019, B:5:0x00cc, B:7:0x00d2, B:12:0x00e6, B:14:0x00ec, B:10:0x00dc, B:19:0x0101, B:20:0x0117, B:22:0x011d, B:26:0x0130, B:30:0x013f, B:34:0x0153, B:38:0x0165, B:42:0x0175, B:46:0x0186, B:50:0x0192, B:54:0x01a4, B:59:0x01c1, B:63:0x01d7, B:65:0x01e4, B:67:0x01ec, B:69:0x01f4, B:76:0x0216, B:80:0x0228, B:84:0x023a, B:88:0x0250, B:92:0x0266, B:93:0x026f, B:97:0x0282, B:101:0x029f, B:105:0x02ab, B:109:0x02c3, B:111:0x02cf, B:116:0x02ec, B:117:0x0301, B:119:0x0307, B:124:0x0317, B:128:0x0323, B:132:0x0333, B:133:0x033a, B:138:0x0358, B:140:0x0364, B:139:0x035f, B:136:0x0352, B:127:0x031f, B:108:0x02bb, B:104:0x02a7, B:100:0x0293, B:96:0x027a, B:91:0x025c, B:87:0x0244, B:83:0x0232, B:79:0x0220, B:62:0x01cd, B:58:0x01b4, B:53:0x019c, B:49:0x018e, B:41:0x0171, B:37:0x0161, B:33:0x014d, B:29:0x0139, B:25:0x012a, B:141:0x0390), top: B:152:0x0019, outer: #0 }] */
            /* JADX WARN: Removed duplicated region for block: B:138:0x0358 A[Catch: all -> 0x00f7, TryCatch #1 {all -> 0x00f7, blocks: (B:4:0x0019, B:5:0x00cc, B:7:0x00d2, B:12:0x00e6, B:14:0x00ec, B:10:0x00dc, B:19:0x0101, B:20:0x0117, B:22:0x011d, B:26:0x0130, B:30:0x013f, B:34:0x0153, B:38:0x0165, B:42:0x0175, B:46:0x0186, B:50:0x0192, B:54:0x01a4, B:59:0x01c1, B:63:0x01d7, B:65:0x01e4, B:67:0x01ec, B:69:0x01f4, B:76:0x0216, B:80:0x0228, B:84:0x023a, B:88:0x0250, B:92:0x0266, B:93:0x026f, B:97:0x0282, B:101:0x029f, B:105:0x02ab, B:109:0x02c3, B:111:0x02cf, B:116:0x02ec, B:117:0x0301, B:119:0x0307, B:124:0x0317, B:128:0x0323, B:132:0x0333, B:133:0x033a, B:138:0x0358, B:140:0x0364, B:139:0x035f, B:136:0x0352, B:127:0x031f, B:108:0x02bb, B:104:0x02a7, B:100:0x0293, B:96:0x027a, B:91:0x025c, B:87:0x0244, B:83:0x0232, B:79:0x0220, B:62:0x01cd, B:58:0x01b4, B:53:0x019c, B:49:0x018e, B:41:0x0171, B:37:0x0161, B:33:0x014d, B:29:0x0139, B:25:0x012a, B:141:0x0390), top: B:152:0x0019, outer: #0 }] */
            /* JADX WARN: Removed duplicated region for block: B:139:0x035f A[Catch: all -> 0x00f7, TryCatch #1 {all -> 0x00f7, blocks: (B:4:0x0019, B:5:0x00cc, B:7:0x00d2, B:12:0x00e6, B:14:0x00ec, B:10:0x00dc, B:19:0x0101, B:20:0x0117, B:22:0x011d, B:26:0x0130, B:30:0x013f, B:34:0x0153, B:38:0x0165, B:42:0x0175, B:46:0x0186, B:50:0x0192, B:54:0x01a4, B:59:0x01c1, B:63:0x01d7, B:65:0x01e4, B:67:0x01ec, B:69:0x01f4, B:76:0x0216, B:80:0x0228, B:84:0x023a, B:88:0x0250, B:92:0x0266, B:93:0x026f, B:97:0x0282, B:101:0x029f, B:105:0x02ab, B:109:0x02c3, B:111:0x02cf, B:116:0x02ec, B:117:0x0301, B:119:0x0307, B:124:0x0317, B:128:0x0323, B:132:0x0333, B:133:0x033a, B:138:0x0358, B:140:0x0364, B:139:0x035f, B:136:0x0352, B:127:0x031f, B:108:0x02bb, B:104:0x02a7, B:100:0x0293, B:96:0x027a, B:91:0x025c, B:87:0x0244, B:83:0x0232, B:79:0x0220, B:62:0x01cd, B:58:0x01b4, B:53:0x019c, B:49:0x018e, B:41:0x0171, B:37:0x0161, B:33:0x014d, B:29:0x0139, B:25:0x012a, B:141:0x0390), top: B:152:0x0019, outer: #0 }] */
            /* JADX WARN: Removed duplicated region for block: B:78:0x021c  */
            /* JADX WARN: Removed duplicated region for block: B:79:0x0220 A[Catch: all -> 0x00f7, TryCatch #1 {all -> 0x00f7, blocks: (B:4:0x0019, B:5:0x00cc, B:7:0x00d2, B:12:0x00e6, B:14:0x00ec, B:10:0x00dc, B:19:0x0101, B:20:0x0117, B:22:0x011d, B:26:0x0130, B:30:0x013f, B:34:0x0153, B:38:0x0165, B:42:0x0175, B:46:0x0186, B:50:0x0192, B:54:0x01a4, B:59:0x01c1, B:63:0x01d7, B:65:0x01e4, B:67:0x01ec, B:69:0x01f4, B:76:0x0216, B:80:0x0228, B:84:0x023a, B:88:0x0250, B:92:0x0266, B:93:0x026f, B:97:0x0282, B:101:0x029f, B:105:0x02ab, B:109:0x02c3, B:111:0x02cf, B:116:0x02ec, B:117:0x0301, B:119:0x0307, B:124:0x0317, B:128:0x0323, B:132:0x0333, B:133:0x033a, B:138:0x0358, B:140:0x0364, B:139:0x035f, B:136:0x0352, B:127:0x031f, B:108:0x02bb, B:104:0x02a7, B:100:0x0293, B:96:0x027a, B:91:0x025c, B:87:0x0244, B:83:0x0232, B:79:0x0220, B:62:0x01cd, B:58:0x01b4, B:53:0x019c, B:49:0x018e, B:41:0x0171, B:37:0x0161, B:33:0x014d, B:29:0x0139, B:25:0x012a, B:141:0x0390), top: B:152:0x0019, outer: #0 }] */
            /* JADX WARN: Removed duplicated region for block: B:82:0x022e  */
            /* JADX WARN: Removed duplicated region for block: B:83:0x0232 A[Catch: all -> 0x00f7, TryCatch #1 {all -> 0x00f7, blocks: (B:4:0x0019, B:5:0x00cc, B:7:0x00d2, B:12:0x00e6, B:14:0x00ec, B:10:0x00dc, B:19:0x0101, B:20:0x0117, B:22:0x011d, B:26:0x0130, B:30:0x013f, B:34:0x0153, B:38:0x0165, B:42:0x0175, B:46:0x0186, B:50:0x0192, B:54:0x01a4, B:59:0x01c1, B:63:0x01d7, B:65:0x01e4, B:67:0x01ec, B:69:0x01f4, B:76:0x0216, B:80:0x0228, B:84:0x023a, B:88:0x0250, B:92:0x0266, B:93:0x026f, B:97:0x0282, B:101:0x029f, B:105:0x02ab, B:109:0x02c3, B:111:0x02cf, B:116:0x02ec, B:117:0x0301, B:119:0x0307, B:124:0x0317, B:128:0x0323, B:132:0x0333, B:133:0x033a, B:138:0x0358, B:140:0x0364, B:139:0x035f, B:136:0x0352, B:127:0x031f, B:108:0x02bb, B:104:0x02a7, B:100:0x0293, B:96:0x027a, B:91:0x025c, B:87:0x0244, B:83:0x0232, B:79:0x0220, B:62:0x01cd, B:58:0x01b4, B:53:0x019c, B:49:0x018e, B:41:0x0171, B:37:0x0161, B:33:0x014d, B:29:0x0139, B:25:0x012a, B:141:0x0390), top: B:152:0x0019, outer: #0 }] */
            /* JADX WARN: Removed duplicated region for block: B:86:0x0240  */
            /* JADX WARN: Removed duplicated region for block: B:87:0x0244 A[Catch: all -> 0x00f7, TryCatch #1 {all -> 0x00f7, blocks: (B:4:0x0019, B:5:0x00cc, B:7:0x00d2, B:12:0x00e6, B:14:0x00ec, B:10:0x00dc, B:19:0x0101, B:20:0x0117, B:22:0x011d, B:26:0x0130, B:30:0x013f, B:34:0x0153, B:38:0x0165, B:42:0x0175, B:46:0x0186, B:50:0x0192, B:54:0x01a4, B:59:0x01c1, B:63:0x01d7, B:65:0x01e4, B:67:0x01ec, B:69:0x01f4, B:76:0x0216, B:80:0x0228, B:84:0x023a, B:88:0x0250, B:92:0x0266, B:93:0x026f, B:97:0x0282, B:101:0x029f, B:105:0x02ab, B:109:0x02c3, B:111:0x02cf, B:116:0x02ec, B:117:0x0301, B:119:0x0307, B:124:0x0317, B:128:0x0323, B:132:0x0333, B:133:0x033a, B:138:0x0358, B:140:0x0364, B:139:0x035f, B:136:0x0352, B:127:0x031f, B:108:0x02bb, B:104:0x02a7, B:100:0x0293, B:96:0x027a, B:91:0x025c, B:87:0x0244, B:83:0x0232, B:79:0x0220, B:62:0x01cd, B:58:0x01b4, B:53:0x019c, B:49:0x018e, B:41:0x0171, B:37:0x0161, B:33:0x014d, B:29:0x0139, B:25:0x012a, B:141:0x0390), top: B:152:0x0019, outer: #0 }] */
            /* JADX WARN: Removed duplicated region for block: B:90:0x0256  */
            /* JADX WARN: Removed duplicated region for block: B:91:0x025c A[Catch: all -> 0x00f7, TryCatch #1 {all -> 0x00f7, blocks: (B:4:0x0019, B:5:0x00cc, B:7:0x00d2, B:12:0x00e6, B:14:0x00ec, B:10:0x00dc, B:19:0x0101, B:20:0x0117, B:22:0x011d, B:26:0x0130, B:30:0x013f, B:34:0x0153, B:38:0x0165, B:42:0x0175, B:46:0x0186, B:50:0x0192, B:54:0x01a4, B:59:0x01c1, B:63:0x01d7, B:65:0x01e4, B:67:0x01ec, B:69:0x01f4, B:76:0x0216, B:80:0x0228, B:84:0x023a, B:88:0x0250, B:92:0x0266, B:93:0x026f, B:97:0x0282, B:101:0x029f, B:105:0x02ab, B:109:0x02c3, B:111:0x02cf, B:116:0x02ec, B:117:0x0301, B:119:0x0307, B:124:0x0317, B:128:0x0323, B:132:0x0333, B:133:0x033a, B:138:0x0358, B:140:0x0364, B:139:0x035f, B:136:0x0352, B:127:0x031f, B:108:0x02bb, B:104:0x02a7, B:100:0x0293, B:96:0x027a, B:91:0x025c, B:87:0x0244, B:83:0x0232, B:79:0x0220, B:62:0x01cd, B:58:0x01b4, B:53:0x019c, B:49:0x018e, B:41:0x0171, B:37:0x0161, B:33:0x014d, B:29:0x0139, B:25:0x012a, B:141:0x0390), top: B:152:0x0019, outer: #0 }] */
            /* JADX WARN: Removed duplicated region for block: B:95:0x0275  */
            /* JADX WARN: Removed duplicated region for block: B:96:0x027a A[Catch: all -> 0x00f7, TryCatch #1 {all -> 0x00f7, blocks: (B:4:0x0019, B:5:0x00cc, B:7:0x00d2, B:12:0x00e6, B:14:0x00ec, B:10:0x00dc, B:19:0x0101, B:20:0x0117, B:22:0x011d, B:26:0x0130, B:30:0x013f, B:34:0x0153, B:38:0x0165, B:42:0x0175, B:46:0x0186, B:50:0x0192, B:54:0x01a4, B:59:0x01c1, B:63:0x01d7, B:65:0x01e4, B:67:0x01ec, B:69:0x01f4, B:76:0x0216, B:80:0x0228, B:84:0x023a, B:88:0x0250, B:92:0x0266, B:93:0x026f, B:97:0x0282, B:101:0x029f, B:105:0x02ab, B:109:0x02c3, B:111:0x02cf, B:116:0x02ec, B:117:0x0301, B:119:0x0307, B:124:0x0317, B:128:0x0323, B:132:0x0333, B:133:0x033a, B:138:0x0358, B:140:0x0364, B:139:0x035f, B:136:0x0352, B:127:0x031f, B:108:0x02bb, B:104:0x02a7, B:100:0x0293, B:96:0x027a, B:91:0x025c, B:87:0x0244, B:83:0x0232, B:79:0x0220, B:62:0x01cd, B:58:0x01b4, B:53:0x019c, B:49:0x018e, B:41:0x0171, B:37:0x0161, B:33:0x014d, B:29:0x0139, B:25:0x012a, B:141:0x0390), top: B:152:0x0019, outer: #0 }] */
            /* JADX WARN: Removed duplicated region for block: B:99:0x028e  */
            @Override // java.util.concurrent.Callable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public java.util.List call() {
                /*
                    Method dump skipped, instruction units count: 950
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: org.fdroid.database.VersionDaoInt_Impl.AnonymousClass9.call():java.util.List");
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0233 A[Catch: all -> 0x01a3, TryCatch #0 {all -> 0x01a3, blocks: (B:12:0x0089, B:14:0x00e5, B:18:0x00f8, B:22:0x0107, B:26:0x0117, B:30:0x0129, B:34:0x0139, B:38:0x0148, B:42:0x0154, B:46:0x0160, B:50:0x0170, B:54:0x017c, B:56:0x0187, B:58:0x018d, B:60:0x0195, B:68:0x01ac, B:72:0x01b8, B:76:0x01c4, B:80:0x01d4, B:84:0x01e0, B:85:0x01e9, B:89:0x01fc, B:93:0x0219, B:97:0x0225, B:101:0x0237, B:103:0x0243, B:108:0x0253, B:109:0x0264, B:111:0x026a, B:124:0x0294, B:115:0x0274, B:119:0x0280, B:123:0x028d, B:118:0x027c, B:100:0x0233, B:96:0x0221, B:92:0x020d, B:88:0x01f4, B:83:0x01dc, B:79:0x01cc, B:75:0x01c0, B:71:0x01b4, B:53:0x0178, B:49:0x0168, B:45:0x015c, B:41:0x0150, B:33:0x0135, B:29:0x0125, B:25:0x0113, B:21:0x0101, B:17:0x00f2), top: B:133:0x0089 }] */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0243 A[Catch: all -> 0x01a3, TryCatch #0 {all -> 0x01a3, blocks: (B:12:0x0089, B:14:0x00e5, B:18:0x00f8, B:22:0x0107, B:26:0x0117, B:30:0x0129, B:34:0x0139, B:38:0x0148, B:42:0x0154, B:46:0x0160, B:50:0x0170, B:54:0x017c, B:56:0x0187, B:58:0x018d, B:60:0x0195, B:68:0x01ac, B:72:0x01b8, B:76:0x01c4, B:80:0x01d4, B:84:0x01e0, B:85:0x01e9, B:89:0x01fc, B:93:0x0219, B:97:0x0225, B:101:0x0237, B:103:0x0243, B:108:0x0253, B:109:0x0264, B:111:0x026a, B:124:0x0294, B:115:0x0274, B:119:0x0280, B:123:0x028d, B:118:0x027c, B:100:0x0233, B:96:0x0221, B:92:0x020d, B:88:0x01f4, B:83:0x01dc, B:79:0x01cc, B:75:0x01c0, B:71:0x01b4, B:53:0x0178, B:49:0x0168, B:45:0x015c, B:41:0x0150, B:33:0x0135, B:29:0x0125, B:25:0x0113, B:21:0x0101, B:17:0x00f2), top: B:133:0x0089 }] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x026a A[Catch: all -> 0x01a3, TryCatch #0 {all -> 0x01a3, blocks: (B:12:0x0089, B:14:0x00e5, B:18:0x00f8, B:22:0x0107, B:26:0x0117, B:30:0x0129, B:34:0x0139, B:38:0x0148, B:42:0x0154, B:46:0x0160, B:50:0x0170, B:54:0x017c, B:56:0x0187, B:58:0x018d, B:60:0x0195, B:68:0x01ac, B:72:0x01b8, B:76:0x01c4, B:80:0x01d4, B:84:0x01e0, B:85:0x01e9, B:89:0x01fc, B:93:0x0219, B:97:0x0225, B:101:0x0237, B:103:0x0243, B:108:0x0253, B:109:0x0264, B:111:0x026a, B:124:0x0294, B:115:0x0274, B:119:0x0280, B:123:0x028d, B:118:0x027c, B:100:0x0233, B:96:0x0221, B:92:0x020d, B:88:0x01f4, B:83:0x01dc, B:79:0x01cc, B:75:0x01c0, B:71:0x01b4, B:53:0x0178, B:49:0x0168, B:45:0x015c, B:41:0x0150, B:33:0x0135, B:29:0x0125, B:25:0x0113, B:21:0x0101, B:17:0x00f2), top: B:133:0x0089 }] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0274 A[Catch: all -> 0x01a3, TryCatch #0 {all -> 0x01a3, blocks: (B:12:0x0089, B:14:0x00e5, B:18:0x00f8, B:22:0x0107, B:26:0x0117, B:30:0x0129, B:34:0x0139, B:38:0x0148, B:42:0x0154, B:46:0x0160, B:50:0x0170, B:54:0x017c, B:56:0x0187, B:58:0x018d, B:60:0x0195, B:68:0x01ac, B:72:0x01b8, B:76:0x01c4, B:80:0x01d4, B:84:0x01e0, B:85:0x01e9, B:89:0x01fc, B:93:0x0219, B:97:0x0225, B:101:0x0237, B:103:0x0243, B:108:0x0253, B:109:0x0264, B:111:0x026a, B:124:0x0294, B:115:0x0274, B:119:0x0280, B:123:0x028d, B:118:0x027c, B:100:0x0233, B:96:0x0221, B:92:0x020d, B:88:0x01f4, B:83:0x01dc, B:79:0x01cc, B:75:0x01c0, B:71:0x01b4, B:53:0x0178, B:49:0x0168, B:45:0x015c, B:41:0x0150, B:33:0x0135, B:29:0x0125, B:25:0x0113, B:21:0x0101, B:17:0x00f2), top: B:133:0x0089 }] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x027c A[Catch: all -> 0x01a3, TryCatch #0 {all -> 0x01a3, blocks: (B:12:0x0089, B:14:0x00e5, B:18:0x00f8, B:22:0x0107, B:26:0x0117, B:30:0x0129, B:34:0x0139, B:38:0x0148, B:42:0x0154, B:46:0x0160, B:50:0x0170, B:54:0x017c, B:56:0x0187, B:58:0x018d, B:60:0x0195, B:68:0x01ac, B:72:0x01b8, B:76:0x01c4, B:80:0x01d4, B:84:0x01e0, B:85:0x01e9, B:89:0x01fc, B:93:0x0219, B:97:0x0225, B:101:0x0237, B:103:0x0243, B:108:0x0253, B:109:0x0264, B:111:0x026a, B:124:0x0294, B:115:0x0274, B:119:0x0280, B:123:0x028d, B:118:0x027c, B:100:0x0233, B:96:0x0221, B:92:0x020d, B:88:0x01f4, B:83:0x01dc, B:79:0x01cc, B:75:0x01c0, B:71:0x01b4, B:53:0x0178, B:49:0x0168, B:45:0x015c, B:41:0x0150, B:33:0x0135, B:29:0x0125, B:25:0x0113, B:21:0x0101, B:17:0x00f2), top: B:133:0x0089 }] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01b4 A[Catch: all -> 0x01a3, TryCatch #0 {all -> 0x01a3, blocks: (B:12:0x0089, B:14:0x00e5, B:18:0x00f8, B:22:0x0107, B:26:0x0117, B:30:0x0129, B:34:0x0139, B:38:0x0148, B:42:0x0154, B:46:0x0160, B:50:0x0170, B:54:0x017c, B:56:0x0187, B:58:0x018d, B:60:0x0195, B:68:0x01ac, B:72:0x01b8, B:76:0x01c4, B:80:0x01d4, B:84:0x01e0, B:85:0x01e9, B:89:0x01fc, B:93:0x0219, B:97:0x0225, B:101:0x0237, B:103:0x0243, B:108:0x0253, B:109:0x0264, B:111:0x026a, B:124:0x0294, B:115:0x0274, B:119:0x0280, B:123:0x028d, B:118:0x027c, B:100:0x0233, B:96:0x0221, B:92:0x020d, B:88:0x01f4, B:83:0x01dc, B:79:0x01cc, B:75:0x01c0, B:71:0x01b4, B:53:0x0178, B:49:0x0168, B:45:0x015c, B:41:0x0150, B:33:0x0135, B:29:0x0125, B:25:0x0113, B:21:0x0101, B:17:0x00f2), top: B:133:0x0089 }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01c0 A[Catch: all -> 0x01a3, TryCatch #0 {all -> 0x01a3, blocks: (B:12:0x0089, B:14:0x00e5, B:18:0x00f8, B:22:0x0107, B:26:0x0117, B:30:0x0129, B:34:0x0139, B:38:0x0148, B:42:0x0154, B:46:0x0160, B:50:0x0170, B:54:0x017c, B:56:0x0187, B:58:0x018d, B:60:0x0195, B:68:0x01ac, B:72:0x01b8, B:76:0x01c4, B:80:0x01d4, B:84:0x01e0, B:85:0x01e9, B:89:0x01fc, B:93:0x0219, B:97:0x0225, B:101:0x0237, B:103:0x0243, B:108:0x0253, B:109:0x0264, B:111:0x026a, B:124:0x0294, B:115:0x0274, B:119:0x0280, B:123:0x028d, B:118:0x027c, B:100:0x0233, B:96:0x0221, B:92:0x020d, B:88:0x01f4, B:83:0x01dc, B:79:0x01cc, B:75:0x01c0, B:71:0x01b4, B:53:0x0178, B:49:0x0168, B:45:0x015c, B:41:0x0150, B:33:0x0135, B:29:0x0125, B:25:0x0113, B:21:0x0101, B:17:0x00f2), top: B:133:0x0089 }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01cc A[Catch: all -> 0x01a3, TryCatch #0 {all -> 0x01a3, blocks: (B:12:0x0089, B:14:0x00e5, B:18:0x00f8, B:22:0x0107, B:26:0x0117, B:30:0x0129, B:34:0x0139, B:38:0x0148, B:42:0x0154, B:46:0x0160, B:50:0x0170, B:54:0x017c, B:56:0x0187, B:58:0x018d, B:60:0x0195, B:68:0x01ac, B:72:0x01b8, B:76:0x01c4, B:80:0x01d4, B:84:0x01e0, B:85:0x01e9, B:89:0x01fc, B:93:0x0219, B:97:0x0225, B:101:0x0237, B:103:0x0243, B:108:0x0253, B:109:0x0264, B:111:0x026a, B:124:0x0294, B:115:0x0274, B:119:0x0280, B:123:0x028d, B:118:0x027c, B:100:0x0233, B:96:0x0221, B:92:0x020d, B:88:0x01f4, B:83:0x01dc, B:79:0x01cc, B:75:0x01c0, B:71:0x01b4, B:53:0x0178, B:49:0x0168, B:45:0x015c, B:41:0x0150, B:33:0x0135, B:29:0x0125, B:25:0x0113, B:21:0x0101, B:17:0x00f2), top: B:133:0x0089 }] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01dc A[Catch: all -> 0x01a3, TryCatch #0 {all -> 0x01a3, blocks: (B:12:0x0089, B:14:0x00e5, B:18:0x00f8, B:22:0x0107, B:26:0x0117, B:30:0x0129, B:34:0x0139, B:38:0x0148, B:42:0x0154, B:46:0x0160, B:50:0x0170, B:54:0x017c, B:56:0x0187, B:58:0x018d, B:60:0x0195, B:68:0x01ac, B:72:0x01b8, B:76:0x01c4, B:80:0x01d4, B:84:0x01e0, B:85:0x01e9, B:89:0x01fc, B:93:0x0219, B:97:0x0225, B:101:0x0237, B:103:0x0243, B:108:0x0253, B:109:0x0264, B:111:0x026a, B:124:0x0294, B:115:0x0274, B:119:0x0280, B:123:0x028d, B:118:0x027c, B:100:0x0233, B:96:0x0221, B:92:0x020d, B:88:0x01f4, B:83:0x01dc, B:79:0x01cc, B:75:0x01c0, B:71:0x01b4, B:53:0x0178, B:49:0x0168, B:45:0x015c, B:41:0x0150, B:33:0x0135, B:29:0x0125, B:25:0x0113, B:21:0x0101, B:17:0x00f2), top: B:133:0x0089 }] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01f4 A[Catch: all -> 0x01a3, TryCatch #0 {all -> 0x01a3, blocks: (B:12:0x0089, B:14:0x00e5, B:18:0x00f8, B:22:0x0107, B:26:0x0117, B:30:0x0129, B:34:0x0139, B:38:0x0148, B:42:0x0154, B:46:0x0160, B:50:0x0170, B:54:0x017c, B:56:0x0187, B:58:0x018d, B:60:0x0195, B:68:0x01ac, B:72:0x01b8, B:76:0x01c4, B:80:0x01d4, B:84:0x01e0, B:85:0x01e9, B:89:0x01fc, B:93:0x0219, B:97:0x0225, B:101:0x0237, B:103:0x0243, B:108:0x0253, B:109:0x0264, B:111:0x026a, B:124:0x0294, B:115:0x0274, B:119:0x0280, B:123:0x028d, B:118:0x027c, B:100:0x0233, B:96:0x0221, B:92:0x020d, B:88:0x01f4, B:83:0x01dc, B:79:0x01cc, B:75:0x01c0, B:71:0x01b4, B:53:0x0178, B:49:0x0168, B:45:0x015c, B:41:0x0150, B:33:0x0135, B:29:0x0125, B:25:0x0113, B:21:0x0101, B:17:0x00f2), top: B:133:0x0089 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x020d A[Catch: all -> 0x01a3, TryCatch #0 {all -> 0x01a3, blocks: (B:12:0x0089, B:14:0x00e5, B:18:0x00f8, B:22:0x0107, B:26:0x0117, B:30:0x0129, B:34:0x0139, B:38:0x0148, B:42:0x0154, B:46:0x0160, B:50:0x0170, B:54:0x017c, B:56:0x0187, B:58:0x018d, B:60:0x0195, B:68:0x01ac, B:72:0x01b8, B:76:0x01c4, B:80:0x01d4, B:84:0x01e0, B:85:0x01e9, B:89:0x01fc, B:93:0x0219, B:97:0x0225, B:101:0x0237, B:103:0x0243, B:108:0x0253, B:109:0x0264, B:111:0x026a, B:124:0x0294, B:115:0x0274, B:119:0x0280, B:123:0x028d, B:118:0x027c, B:100:0x0233, B:96:0x0221, B:92:0x020d, B:88:0x01f4, B:83:0x01dc, B:79:0x01cc, B:75:0x01c0, B:71:0x01b4, B:53:0x0178, B:49:0x0168, B:45:0x015c, B:41:0x0150, B:33:0x0135, B:29:0x0125, B:25:0x0113, B:21:0x0101, B:17:0x00f2), top: B:133:0x0089 }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0221 A[Catch: all -> 0x01a3, TryCatch #0 {all -> 0x01a3, blocks: (B:12:0x0089, B:14:0x00e5, B:18:0x00f8, B:22:0x0107, B:26:0x0117, B:30:0x0129, B:34:0x0139, B:38:0x0148, B:42:0x0154, B:46:0x0160, B:50:0x0170, B:54:0x017c, B:56:0x0187, B:58:0x018d, B:60:0x0195, B:68:0x01ac, B:72:0x01b8, B:76:0x01c4, B:80:0x01d4, B:84:0x01e0, B:85:0x01e9, B:89:0x01fc, B:93:0x0219, B:97:0x0225, B:101:0x0237, B:103:0x0243, B:108:0x0253, B:109:0x0264, B:111:0x026a, B:124:0x0294, B:115:0x0274, B:119:0x0280, B:123:0x028d, B:118:0x027c, B:100:0x0233, B:96:0x0221, B:92:0x020d, B:88:0x01f4, B:83:0x01dc, B:79:0x01cc, B:75:0x01c0, B:71:0x01b4, B:53:0x0178, B:49:0x0168, B:45:0x015c, B:41:0x0150, B:33:0x0135, B:29:0x0125, B:25:0x0113, B:21:0x0101, B:17:0x00f2), top: B:133:0x0089 }] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0231  */
    @Override // org.fdroid.database.VersionDaoInt
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.fdroid.database.Version getVersion(long r50, java.lang.String r52, java.lang.String r53) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 695
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.database.VersionDaoInt_Impl.getVersion(long, java.lang.String, java.lang.String):org.fdroid.database.Version");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.fdroid.database.VersionDaoInt
    public List<Version> getVersionsInternal(List<String> list) {
        FileV2 fileV2;
        SignerV2 signerV2;
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("SELECT `repoId`, `packageName`, `versionId`, `added`, `releaseChannels`, `antiFeatures`, `whatsNew`, `isCompatible`, `file_name`, `file_sha256`, `file_size`, `file_ipfsCidV1`, `src_name`, `src_sha256`, `src_size`, `src_ipfsCidV1`, `manifest_versionName`, `manifest_versionCode`, `manifest_maxSdkVersion`, `manifest_nativecode`, `manifest_features`, `manifest_usesSdk_minSdkVersion`, `manifest_usesSdk_targetSdkVersion`, `manifest_signer_sha256`, `manifest_signer_hasMultipleSigners` FROM (SELECT * FROM Version");
        sbNewStringBuilder.append("\n");
        sbNewStringBuilder.append("        JOIN RepositoryPreferences AS pref USING (repoId)");
        sbNewStringBuilder.append("\n");
        sbNewStringBuilder.append("        LEFT JOIN AppPrefs AS appPrefs USING (packageName)");
        sbNewStringBuilder.append("\n");
        sbNewStringBuilder.append("        WHERE pref.enabled = 1 AND");
        sbNewStringBuilder.append("\n");
        sbNewStringBuilder.append("              manifest_versionCode > COALESCE(appPrefs.ignoreVersionCodeUpdate, 0) AND");
        sbNewStringBuilder.append("\n");
        sbNewStringBuilder.append("              packageName IN (");
        int size = list.size();
        StringUtil.appendPlaceholders(sbNewStringBuilder, size);
        sbNewStringBuilder.append(")");
        sbNewStringBuilder.append("\n");
        sbNewStringBuilder.append("        ORDER BY manifest_versionCode DESC, pref.weight DESC)");
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire(sbNewStringBuilder.toString(), size);
        int i = 1;
        int i2 = 1;
        for (String str : list) {
            if (str == null) {
                roomSQLiteQueryAcquire.bindNull(i2);
            } else {
                roomSQLiteQueryAcquire.bindString(i2, str);
            }
            i2++;
        }
        this.__db.assertNotSuspendingTransaction();
        int i3 = 0;
        String str2 = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                long j = cursorQuery.getLong(i3);
                String string = cursorQuery.isNull(i) ? str2 : cursorQuery.getString(i);
                String string2 = cursorQuery.isNull(2) ? str2 : cursorQuery.getString(2);
                long j2 = cursorQuery.getLong(3);
                String string3 = cursorQuery.isNull(4) ? str2 : cursorQuery.getString(4);
                Converters converters = Converters.INSTANCE;
                List<String> listFromStringToListString = converters.fromStringToListString(string3);
                Map<String, Map<String, String>> mapFromStringToMapOfLocalizedTextV2 = converters.fromStringToMapOfLocalizedTextV2(cursorQuery.isNull(5) ? str2 : cursorQuery.getString(5));
                Map<String, String> mapFromStringToLocalizedTextV2 = converters.fromStringToLocalizedTextV2(cursorQuery.isNull(6) ? str2 : cursorQuery.getString(6));
                boolean z = cursorQuery.getInt(7) != 0 ? i : i3;
                FileV1 fileV1 = new FileV1(cursorQuery.isNull(8) ? str2 : cursorQuery.getString(8), cursorQuery.isNull(9) ? str2 : cursorQuery.getString(9), cursorQuery.isNull(10) ? str2 : Long.valueOf(cursorQuery.getLong(10)), cursorQuery.isNull(11) ? str2 : cursorQuery.getString(11));
                if (cursorQuery.isNull(12) && cursorQuery.isNull(13) && cursorQuery.isNull(14) && cursorQuery.isNull(15)) {
                    fileV2 = null;
                } else {
                    fileV2 = new FileV2(cursorQuery.isNull(12) ? null : cursorQuery.getString(12), cursorQuery.isNull(13) ? null : cursorQuery.getString(13), cursorQuery.isNull(14) ? null : Long.valueOf(cursorQuery.getLong(14)), cursorQuery.isNull(15) ? null : cursorQuery.getString(15));
                }
                String string4 = cursorQuery.isNull(16) ? null : cursorQuery.getString(16);
                long j3 = cursorQuery.getLong(17);
                Integer numValueOf = cursorQuery.isNull(18) ? null : Integer.valueOf(cursorQuery.getInt(18));
                List<String> listFromStringToListString2 = converters.fromStringToListString(cursorQuery.isNull(19) ? null : cursorQuery.getString(19));
                List<String> listFromStringToListString3 = converters.fromStringToListString(cursorQuery.isNull(20) ? null : cursorQuery.getString(20));
                UsesSdkV2 usesSdkV2 = (cursorQuery.isNull(21) && cursorQuery.isNull(22)) ? null : new UsesSdkV2(cursorQuery.getInt(21), cursorQuery.getInt(22));
                if (cursorQuery.isNull(23) && cursorQuery.isNull(24)) {
                    signerV2 = null;
                } else {
                    signerV2 = new SignerV2(converters.fromStringToListString(cursorQuery.isNull(23) ? null : cursorQuery.getString(23)), cursorQuery.getInt(24) != 0);
                }
                arrayList.add(new Version(j, string, string2, j2, fileV1, fileV2, new AppManifest(string4, j3, usesSdkV2, numValueOf, signerV2, listFromStringToListString2, listFromStringToListString3), listFromStringToListString, mapFromStringToMapOfLocalizedTextV2, mapFromStringToLocalizedTextV2, z));
                i = 1;
                i3 = 0;
                str2 = null;
            }
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
            return arrayList;
        } catch (Throwable th) {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
            throw th;
        }
    }

    @Override // org.fdroid.database.VersionDaoInt
    public List<VersionedString> getVersionedStrings(long j, String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM VersionedString\n        WHERE repoId = ? AND packageName = ?", 2);
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
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, BonjourPeer.TYPE);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "name");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "version");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new VersionedString(cursorQuery.getLong(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), __VersionedStringType_stringToEnum(cursorQuery.getString(columnIndexOrThrow4)), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : Integer.valueOf(cursorQuery.getInt(columnIndexOrThrow6))));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // org.fdroid.database.VersionDaoInt
    public List<VersionedString> getVersionedStrings(long j, String str, String str2) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM VersionedString\n        WHERE repoId = ? AND packageName = ? AND versionId = ?", 3);
        roomSQLiteQueryAcquire.bindLong(1, j);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, str);
        }
        if (str2 == null) {
            roomSQLiteQueryAcquire.bindNull(3);
        } else {
            roomSQLiteQueryAcquire.bindString(3, str2);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "repoId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "packageName");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "versionId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, BonjourPeer.TYPE);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "name");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "version");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new VersionedString(cursorQuery.getLong(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), __VersionedStringType_stringToEnum(cursorQuery.getString(columnIndexOrThrow4)), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : Integer.valueOf(cursorQuery.getInt(columnIndexOrThrow6))));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // org.fdroid.database.VersionDaoInt
    public int countAppVersions() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM Version", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            return cursorQuery.moveToFirst() ? cursorQuery.getInt(0) : 0;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // org.fdroid.database.VersionDaoInt
    public int countVersionedStrings() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM VersionedString", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            return cursorQuery.moveToFirst() ? cursorQuery.getInt(0) : 0;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // org.fdroid.database.VersionDaoInt
    public void update(long j, String str, Map<String, JsonObject> map, Function1 function1) {
        VersionDaoInt.DefaultImpls.update(this, j, str, map, function1);
    }

    @Override // org.fdroid.database.VersionDaoInt
    public List<Version> getVersions(List<String> list) {
        return VersionDaoInt.DefaultImpls.getVersions(this, list);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    /* JADX INFO: renamed from: org.fdroid.database.VersionDaoInt_Impl$10, reason: invalid class name */
    static /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] $SwitchMap$org$fdroid$database$VersionedStringType;

        static {
            int[] iArr = new int[VersionedStringType.values().length];
            $SwitchMap$org$fdroid$database$VersionedStringType = iArr;
            try {
                iArr[VersionedStringType.PERMISSION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$fdroid$database$VersionedStringType[VersionedStringType.PERMISSION_SDK_23.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String __VersionedStringType_enumToString(VersionedStringType versionedStringType) {
        int i = AnonymousClass10.$SwitchMap$org$fdroid$database$VersionedStringType[versionedStringType.ordinal()];
        if (i == 1) {
            return "PERMISSION";
        }
        if (i == 2) {
            return "PERMISSION_SDK_23";
        }
        throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + versionedStringType);
    }

    private VersionedStringType __VersionedStringType_stringToEnum(String str) {
        str.hashCode();
        if (str.equals("PERMISSION_SDK_23")) {
            return VersionedStringType.PERMISSION_SDK_23;
        }
        if (str.equals("PERMISSION")) {
            return VersionedStringType.PERMISSION;
        }
        throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipVersionedStringAsorgFdroidDatabaseVersionedString(ArrayMap arrayMap) {
        ArrayList arrayList;
        Set<String> setKeySet = arrayMap.keySet();
        if (setKeySet.isEmpty()) {
            return;
        }
        if (arrayMap.size() > 999) {
            RelationUtil.recursiveFetchArrayMap(arrayMap, true, new Function1() { // from class: org.fdroid.database.VersionDaoInt_Impl$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return this.f$0.lambda$__fetchRelationshipVersionedStringAsorgFdroidDatabaseVersionedString$0((ArrayMap) obj);
                }
            });
            return;
        }
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("SELECT `repoId`,`packageName`,`versionId`,`type`,`name`,`version` FROM `VersionedString` WHERE `versionId` IN (");
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
            int columnIndex = CursorUtil.getColumnIndex(cursorQuery, "versionId");
            if (columnIndex == -1) {
                return;
            }
            while (cursorQuery.moveToNext()) {
                String string = cursorQuery.isNull(columnIndex) ? null : cursorQuery.getString(columnIndex);
                if (string != null && (arrayList = (ArrayList) arrayMap.get(string)) != null) {
                    arrayList.add(new VersionedString(cursorQuery.getLong(0), cursorQuery.isNull(1) ? null : cursorQuery.getString(1), cursorQuery.isNull(2) ? null : cursorQuery.getString(2), __VersionedStringType_stringToEnum(cursorQuery.getString(3)), cursorQuery.isNull(4) ? null : cursorQuery.getString(4), cursorQuery.isNull(5) ? null : Integer.valueOf(cursorQuery.getInt(5))));
                }
            }
        } finally {
            cursorQuery.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$__fetchRelationshipVersionedStringAsorgFdroidDatabaseVersionedString$0(ArrayMap arrayMap) {
        __fetchRelationshipVersionedStringAsorgFdroidDatabaseVersionedString(arrayMap);
        return Unit.INSTANCE;
    }
}
