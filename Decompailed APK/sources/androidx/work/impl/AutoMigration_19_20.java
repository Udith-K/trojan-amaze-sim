package androidx.work.impl;

import androidx.room.migration.AutoMigrationSpec;
import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WorkDatabaseMigrations.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AutoMigration_19_20 implements AutoMigrationSpec {
    @Override // androidx.room.migration.AutoMigrationSpec
    public void onPostMigrate(SupportSQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        db.execSQL("UPDATE WorkSpec SET `last_enqueue_time` = -1 WHERE `last_enqueue_time` = 0");
    }
}
