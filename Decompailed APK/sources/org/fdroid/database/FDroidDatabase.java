package org.fdroid.database;

import android.content.res.Resources;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import java.util.concurrent.Callable;
import kotlin.Metadata;

/* JADX INFO: compiled from: FDroidDatabase.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\u0012\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH&J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H&J!\u0010\u000e\u001a\u0002H\u0011\"\u0004\b\u0000\u0010\u00112\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0012H&¢\u0006\u0002\u0010\u0013J\b\u0010\u0014\u001a\u00020\u000bH&¨\u0006\u0015"}, d2 = {"Lorg/fdroid/database/FDroidDatabase;", "", "getRepositoryDao", "Lorg/fdroid/database/RepositoryDao;", "getAppDao", "Lorg/fdroid/database/AppDao;", "getVersionDao", "Lorg/fdroid/database/VersionDao;", "getAppPrefsDao", "Lorg/fdroid/database/AppPrefsDao;", "afterLocalesChanged", "", "locales", "Landroidx/core/os/LocaleListCompat;", "runInTransaction", "body", "Ljava/lang/Runnable;", "V", "Ljava/util/concurrent/Callable;", "(Ljava/util/concurrent/Callable;)Ljava/lang/Object;", "clearAllAppData", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface FDroidDatabase {
    void afterLocalesChanged(LocaleListCompat locales);

    void clearAllAppData();

    AppDao getAppDao();

    AppPrefsDao getAppPrefsDao();

    RepositoryDao getRepositoryDao();

    VersionDao getVersionDao();

    <V> V runInTransaction(Callable<V> body);

    void runInTransaction(Runnable body);

    /* JADX INFO: compiled from: FDroidDatabase.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void afterLocalesChanged$default(FDroidDatabase fDroidDatabase, LocaleListCompat localeListCompat, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: afterLocalesChanged");
            }
            if ((i & 1) != 0) {
                localeListCompat = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
            }
            fDroidDatabase.afterLocalesChanged(localeListCompat);
        }
    }
}
