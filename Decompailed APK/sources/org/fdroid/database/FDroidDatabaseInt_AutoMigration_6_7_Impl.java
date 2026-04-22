package org.fdroid.database;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/* JADX INFO: loaded from: classes2.dex */
final class FDroidDatabaseInt_AutoMigration_6_7_Impl extends Migration {
    public FDroidDatabaseInt_AutoMigration_6_7_Impl() {
        super(6, 7);
    }

    @Override // androidx.room.migration.Migration
    public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        supportSQLiteDatabase.execSQL("DROP VIEW LocalizedIcon");
        supportSQLiteDatabase.execSQL("DROP VIEW HighestVersion");
        supportSQLiteDatabase.execSQL("DROP VIEW PreferredRepo");
        supportSQLiteDatabase.execSQL("CREATE VIEW `LocalizedIcon` AS SELECT * FROM LocalizedFile WHERE type='icon'");
        supportSQLiteDatabase.execSQL("CREATE VIEW `HighestVersion` AS SELECT repoId, packageName, antiFeatures FROM Version\n    GROUP BY repoId, packageName HAVING MAX(manifest_versionCode)");
        supportSQLiteDatabase.execSQL("CREATE VIEW `PreferredRepo` AS SELECT packageName, repoId AS preferredRepoId FROM AppMetadata\n    JOIN RepositoryPreferences AS pref USING (repoId)\n    LEFT JOIN AppPrefs USING (packageName)\n    WHERE pref.enabled = 1 AND (repoId = COALESCE(preferredRepoId, repoId) OR\n      NOT EXISTS (SELECT 1 FROM AppMetadata WHERE repoId=AppPrefs.preferredRepoId AND packageName=AppPrefs.packageName)\n    )\n    GROUP BY packageName HAVING MAX(pref.weight)");
    }
}
