package org.fdroid.fdroid.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.core.util.ObjectsCompat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.fdroid.database.AppPrefs;
import org.fdroid.database.AppPrefsDao;
import org.fdroid.database.FDroidDatabase;
import org.fdroid.database.InitialRepository;
import org.fdroid.database.Repository;
import org.fdroid.database.RepositoryDao;
import org.fdroid.fdroid.Preferences;

/* JADX INFO: loaded from: classes2.dex */
final class ContentProviderMigrator {
    private static final String OLD_DB_NAME = "fdroid";

    ContentProviderMigrator() {
    }

    boolean needsMigration(Context context) {
        for (String str : context.databaseList()) {
            if (OLD_DB_NAME.equals(str)) {
                return true;
            }
        }
        return false;
    }

    void runMigrations(Context context, FDroidDatabase fDroidDatabase) {
        ContentProviderDbHelper contentProviderDbHelper = new ContentProviderDbHelper(context);
        try {
            SQLiteDatabase readableDatabase = contentProviderDbHelper.getReadableDatabase();
            migrateOldRepos(fDroidDatabase, readableDatabase);
            migrateIgnoredUpdates(fDroidDatabase, readableDatabase);
            contentProviderDbHelper.close();
        } catch (Throwable th) {
            try {
                contentProviderDbHelper.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private void migrateOldRepos(FDroidDatabase fDroidDatabase, SQLiteDatabase sQLiteDatabase) throws Throwable {
        String str;
        List<Repository> list;
        String str2;
        String str3 = "disabledMirrors";
        RepositoryDao repositoryDao = fDroidDatabase.getRepositoryDao();
        List<Repository> repositories = repositoryDao.getRepositories();
        try {
            str = null;
            Cursor cursorQuery = sQLiteDatabase.query("fdroid_repo", new String[]{"name", "address", "pubkey", "inuse", "userMirrors", "disabledMirrors", "username", "password"}, null, null, null, null, null);
            while (cursorQuery.moveToNext()) {
                try {
                    try {
                        String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("name"));
                        String string2 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("address"));
                        String string3 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("pubkey"));
                        if (string3 != null) {
                            String lowerCase = string3.toLowerCase(Locale.ROOT);
                            boolean z = cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("inuse")) == 1;
                            String string4 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("userMirrors"));
                            Repository repository = null;
                            List<String> listAsList = string4 == null ? null : Arrays.asList(string4.split(","));
                            String string5 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow(str3));
                            List<String> listAsList2 = string5 == null ? null : Arrays.asList(string5.split(","));
                            String string6 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("username"));
                            String string7 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("password"));
                            Iterator<Repository> it = repositories.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                Repository next = it.next();
                                if (next.getAddress().equals(string2)) {
                                    repository = next;
                                    break;
                                }
                            }
                            if (repository == null) {
                                list = repositories;
                                str = str3;
                                str2 = string7;
                                try {
                                    repository = (Repository) ObjectsCompat.requireNonNull(repositoryDao.getRepository(repositoryDao.insert(new InitialRepository(string, string2, "", lowerCase, 0L, z))));
                                } catch (Throwable th) {
                                    th = th;
                                    Throwable th2 = th;
                                    if (cursorQuery == null) {
                                        throw th2;
                                    }
                                    try {
                                        cursorQuery.close();
                                        throw th2;
                                    } catch (Throwable th3) {
                                        th2.addSuppressed(th3);
                                        throw th2;
                                    }
                                }
                            } else {
                                str = str3;
                                list = repositories;
                                str2 = string7;
                                if (lowerCase.equals(repository.getCertificate())) {
                                }
                                repositories = list;
                                str3 = str;
                            }
                            if (repository.getEnabled() != z) {
                                repositoryDao.setRepositoryEnabled(repository.getRepoId(), z);
                            }
                            if (listAsList != null && !listAsList.isEmpty()) {
                                repositoryDao.updateUserMirrors(repository.getRepoId(), listAsList);
                            }
                            if (listAsList2 != null && !listAsList2.isEmpty()) {
                                repositoryDao.updateDisabledMirrors(repository.getRepoId(), listAsList2);
                            }
                            if (string6 != null || str2 != null) {
                                repositoryDao.updateUsernameAndPassword(repository.getRepoId(), string6, str2);
                            }
                            repositories = list;
                            str3 = str;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        str = str3;
                    }
                } catch (SQLiteException e) {
                    e = e;
                    if (e.getMessage() != null && e.getMessage().contains(str)) {
                        Log.e("DbHelper", "disabledMirrors column missing. Can't migrate. ", e);
                        return;
                    }
                    throw e;
                }
            }
            cursorQuery.close();
        } catch (SQLiteException e2) {
            e = e2;
            str = "disabledMirrors";
        }
    }

    private void migrateIgnoredUpdates(FDroidDatabase fDroidDatabase, SQLiteDatabase sQLiteDatabase) {
        AppPrefsDao appPrefsDao = fDroidDatabase.getAppPrefsDao();
        Cursor cursorQuery = sQLiteDatabase.query("fdroid_appPrefs", new String[]{"packageName", "ignoreThisUpdate", "ignoreAllUpdates"}, null, null, null, null, null);
        while (cursorQuery.moveToNext()) {
            try {
                String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("packageName"));
                long j = cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow("ignoreThisUpdate"));
                if (cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("ignoreAllUpdates")) == 1) {
                    j = Preferences.UPDATE_INTERVAL_DISABLED;
                }
                appPrefsDao.update(new AppPrefs(string, j, null, null));
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    try {
                        cursorQuery.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        cursorQuery.close();
    }

    void removeOldDb(Context context) {
        context.deleteDatabase(OLD_DB_NAME);
    }

    private static class ContentProviderDbHelper extends SQLiteOpenHelper {
        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }

        ContentProviderDbHelper(Context context) {
            super(context, ContentProviderMigrator.OLD_DB_NAME, (SQLiteDatabase.CursorFactory) null, 85);
        }
    }
}
