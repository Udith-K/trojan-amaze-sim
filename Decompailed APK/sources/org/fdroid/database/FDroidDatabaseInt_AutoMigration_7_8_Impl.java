package org.fdroid.database;

import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteDatabase;

/* JADX INFO: loaded from: classes2.dex */
final class FDroidDatabaseInt_AutoMigration_7_8_Impl extends Migration {
    private final AutoMigrationSpec callback;

    public FDroidDatabaseInt_AutoMigration_7_8_Impl() {
        super(7, 8);
        this.callback = new CountryCodeMigration();
    }

    @Override // androidx.room.migration.Migration
    public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        supportSQLiteDatabase.execSQL("DROP VIEW LocalizedIcon");
        supportSQLiteDatabase.execSQL("DROP VIEW HighestVersion");
        supportSQLiteDatabase.execSQL("DROP VIEW PreferredRepo");
        supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `_new_Mirror` (`repoId` INTEGER NOT NULL, `url` TEXT NOT NULL, `countryCode` TEXT, PRIMARY KEY(`repoId`, `url`), FOREIGN KEY(`repoId`) REFERENCES `CoreRepository`(`repoId`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        supportSQLiteDatabase.execSQL("INSERT INTO `_new_Mirror` (`repoId`,`url`,`countryCode`) SELECT `repoId`,`url`,`location` FROM `Mirror`");
        supportSQLiteDatabase.execSQL("DROP TABLE `Mirror`");
        supportSQLiteDatabase.execSQL("ALTER TABLE `_new_Mirror` RENAME TO `Mirror`");
        DBUtil.foreignKeyCheck(supportSQLiteDatabase, Mirror.TABLE);
        supportSQLiteDatabase.execSQL("CREATE VIEW `LocalizedIcon` AS SELECT * FROM LocalizedFile WHERE type='icon'");
        supportSQLiteDatabase.execSQL("CREATE VIEW `HighestVersion` AS SELECT repoId, packageName, antiFeatures FROM Version\n    GROUP BY repoId, packageName HAVING MAX(manifest_versionCode)");
        supportSQLiteDatabase.execSQL("CREATE VIEW `PreferredRepo` AS SELECT packageName, repoId AS preferredRepoId FROM AppMetadata\n    JOIN RepositoryPreferences AS pref USING (repoId)\n    LEFT JOIN AppPrefs USING (packageName)\n    WHERE pref.enabled = 1 AND (repoId = COALESCE(preferredRepoId, repoId) OR\n      NOT EXISTS (SELECT 1 FROM AppMetadata WHERE repoId=AppPrefs.preferredRepoId AND packageName=AppPrefs.packageName)\n    )\n    GROUP BY packageName HAVING MAX(pref.weight)");
        this.callback.onPostMigrate(supportSQLiteDatabase);
    }
}
