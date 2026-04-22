package org.fdroid.database;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/* JADX INFO: loaded from: classes2.dex */
final class FDroidDatabaseInt_AutoMigration_3_4_Impl extends Migration {
    public FDroidDatabaseInt_AutoMigration_3_4_Impl() {
        super(3, 4);
    }

    @Override // androidx.room.migration.Migration
    public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        supportSQLiteDatabase.execSQL("DROP VIEW LocalizedIcon");
        supportSQLiteDatabase.execSQL("DROP VIEW HighestVersion");
        supportSQLiteDatabase.execSQL("DROP VIEW PreferredRepo");
        supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `_new_CoreRepository` (`repoId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `icon` TEXT, `address` TEXT NOT NULL, `webBaseUrl` TEXT, `timestamp` INTEGER NOT NULL, `version` INTEGER, `formatVersion` TEXT, `maxAge` INTEGER, `description` TEXT NOT NULL, `certificate` TEXT NOT NULL)");
        supportSQLiteDatabase.execSQL("INSERT INTO `_new_CoreRepository` (`repoId`,`name`,`icon`,`address`,`webBaseUrl`,`timestamp`,`version`,`formatVersion`,`maxAge`,`description`,`certificate`) SELECT `repoId`,`name`,`icon`,`address`,`webBaseUrl`,`timestamp`,`version`,`formatVersion`,`maxAge`,`description`,`certificate` FROM `CoreRepository`");
        supportSQLiteDatabase.execSQL("DROP TABLE `CoreRepository`");
        supportSQLiteDatabase.execSQL("ALTER TABLE `_new_CoreRepository` RENAME TO `CoreRepository`");
        supportSQLiteDatabase.execSQL("CREATE VIEW `LocalizedIcon` AS SELECT * FROM LocalizedFile WHERE type='icon'");
        supportSQLiteDatabase.execSQL("CREATE VIEW `HighestVersion` AS SELECT repoId, packageName, antiFeatures FROM Version\n    GROUP BY repoId, packageName HAVING MAX(manifest_versionCode)");
        supportSQLiteDatabase.execSQL("CREATE VIEW `PreferredRepo` AS SELECT packageName, repoId AS preferredRepoId FROM AppMetadata\n    JOIN RepositoryPreferences AS pref USING (repoId)\n    LEFT JOIN AppPrefs USING (packageName)\n    WHERE repoId = COALESCE(preferredRepoId, repoId)\n    GROUP BY packageName HAVING MAX(pref.weight)");
    }
}
