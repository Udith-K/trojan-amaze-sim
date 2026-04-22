package androidx.room.migration;

import androidx.sqlite.db.SupportSQLiteDatabase;

/* JADX INFO: compiled from: AutoMigrationSpec.kt */
/* JADX INFO: loaded from: classes.dex */
public interface AutoMigrationSpec {

    /* JADX INFO: renamed from: androidx.room.migration.AutoMigrationSpec$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: AutoMigrationSpec.kt */
    public abstract /* synthetic */ class CC {
    }

    void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase);
}
