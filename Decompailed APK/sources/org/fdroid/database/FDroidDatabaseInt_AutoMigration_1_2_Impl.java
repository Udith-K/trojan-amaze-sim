package org.fdroid.database;

import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/* JADX INFO: loaded from: classes2.dex */
final class FDroidDatabaseInt_AutoMigration_1_2_Impl extends Migration {
    private final AutoMigrationSpec callback;

    public FDroidDatabaseInt_AutoMigration_1_2_Impl() {
        super(1, 2);
        this.callback = new MultiRepoMigration();
    }

    @Override // androidx.room.migration.Migration
    public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        supportSQLiteDatabase.execSQL("DROP VIEW LocalizedIcon");
        supportSQLiteDatabase.execSQL("DROP VIEW HighestVersion");
        supportSQLiteDatabase.execSQL("ALTER TABLE `AppPrefs` ADD COLUMN `preferredRepoId` INTEGER DEFAULT NULL");
        supportSQLiteDatabase.execSQL("CREATE VIEW `LocalizedIcon` AS SELECT * FROM LocalizedFile WHERE type='icon'");
        supportSQLiteDatabase.execSQL("CREATE VIEW `HighestVersion` AS SELECT repoId, packageName, antiFeatures FROM Version\n    GROUP BY repoId, packageName HAVING MAX(manifest_versionCode)");
        supportSQLiteDatabase.execSQL("CREATE VIEW `PreferredRepo` AS SELECT packageName, repoId AS preferredRepoId FROM AppMetadata\n    JOIN RepositoryPreferences AS pref USING (repoId)\n    LEFT JOIN AppPrefs USING (packageName)\n    WHERE repoId = COALESCE(preferredRepoId, repoId)\n    GROUP BY packageName HAVING MAX(pref.weight)");
        this.callback.onPostMigrate(supportSQLiteDatabase);
    }
}
