package org.fdroid.database;

import androidx.core.os.LocaleListCompat;
import androidx.room.RoomDatabase;
import java.io.Closeable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.LocaleChooser;

/* JADX INFO: compiled from: FDroidDatabase.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b!\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\rH&J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u000e\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010\u0015\u001a\u00020\u000fH\u0016¨\u0006\u0016"}, d2 = {"Lorg/fdroid/database/FDroidDatabaseInt;", "Landroidx/room/RoomDatabase;", "Lorg/fdroid/database/FDroidDatabase;", "Ljava/io/Closeable;", "<init>", "()V", "getRepositoryDao", "Lorg/fdroid/database/RepositoryDaoInt;", "getAppDao", "Lorg/fdroid/database/AppDaoInt;", "getVersionDao", "Lorg/fdroid/database/VersionDaoInt;", "getAppPrefsDao", "Lorg/fdroid/database/AppPrefsDaoInt;", "afterLocalesChanged", "", "locales", "Landroidx/core/os/LocaleListCompat;", "afterUpdatingRepo", "repoId", "", "clearAllAppData", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class FDroidDatabaseInt extends RoomDatabase implements FDroidDatabase, Closeable {
    @Override // org.fdroid.database.FDroidDatabase
    public abstract AppDaoInt getAppDao();

    @Override // org.fdroid.database.FDroidDatabase
    public abstract AppPrefsDaoInt getAppPrefsDao();

    @Override // org.fdroid.database.FDroidDatabase
    public abstract RepositoryDaoInt getRepositoryDao();

    @Override // org.fdroid.database.FDroidDatabase
    public abstract VersionDaoInt getVersionDao();

    @Override // org.fdroid.database.FDroidDatabase
    public void afterLocalesChanged(final LocaleListCompat locales) {
        Intrinsics.checkNotNullParameter(locales, "locales");
        final AppDaoInt appDao = getAppDao();
        runInTransaction(new Runnable() { // from class: org.fdroid.database.FDroidDatabaseInt$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                FDroidDatabaseInt.afterLocalesChanged$lambda$1(appDao, locales);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void afterLocalesChanged$lambda$1(AppDaoInt appDaoInt, LocaleListCompat localeListCompat) {
        for (AppMetadata appMetadata : appDaoInt.getAppMetadata()) {
            long repoId = appMetadata.getRepoId();
            String packageName = appMetadata.getPackageName();
            LocaleChooser localeChooser = LocaleChooser.INSTANCE;
            appDaoInt.updateAppMetadata(repoId, packageName, (String) localeChooser.getBestLocale(appMetadata.getName(), localeListCompat), (String) localeChooser.getBestLocale(appMetadata.getSummary(), localeListCompat));
        }
    }

    public final void afterUpdatingRepo(long repoId) {
        getAppDao().updateCompatibility(repoId);
    }

    @Override // org.fdroid.database.FDroidDatabase
    public void clearAllAppData() {
        runInTransaction(new Runnable() { // from class: org.fdroid.database.FDroidDatabaseInt$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FDroidDatabaseInt.clearAllAppData$lambda$2(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void clearAllAppData$lambda$2(FDroidDatabaseInt fDroidDatabaseInt) {
        fDroidDatabaseInt.getAppDao().clearAll();
        fDroidDatabaseInt.getRepositoryDao().resetTimestamps();
        fDroidDatabaseInt.getRepositoryDao().resetETags();
    }
}
