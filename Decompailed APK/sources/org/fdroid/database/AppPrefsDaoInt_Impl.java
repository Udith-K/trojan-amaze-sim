package org.fdroid.database;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import org.fdroid.database.AppPrefsDaoInt;

/* JADX INFO: loaded from: classes2.dex */
public final class AppPrefsDaoInt_Impl implements AppPrefsDaoInt {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter __insertionAdapterOfAppPrefs;

    public AppPrefsDaoInt_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfAppPrefs = new EntityInsertionAdapter(roomDatabase) { // from class: org.fdroid.database.AppPrefsDaoInt_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `AppPrefs` (`packageName`,`ignoreVersionCodeUpdate`,`preferredRepoId`,`appPrefReleaseChannels`) VALUES (?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, AppPrefs appPrefs) {
                if (appPrefs.getPackageName() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, appPrefs.getPackageName());
                }
                supportSQLiteStatement.bindLong(2, appPrefs.getIgnoreVersionCodeUpdate());
                if (appPrefs.getPreferredRepoId() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindLong(3, appPrefs.getPreferredRepoId().longValue());
                }
                String strListStringToString = Converters.INSTANCE.listStringToString(appPrefs.getAppPrefReleaseChannels$database_release());
                if (strListStringToString == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, strListStringToString);
                }
            }
        };
    }

    @Override // org.fdroid.database.AppPrefsDaoInt, org.fdroid.database.AppPrefsDao
    public void update(AppPrefs appPrefs) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfAppPrefs.insert(appPrefs);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // org.fdroid.database.AppPrefsDaoInt
    public LiveData getLiveAppPrefs(String str) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM AppPrefs WHERE packageName = ?", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{AppPrefs.TABLE}, false, new Callable() { // from class: org.fdroid.database.AppPrefsDaoInt_Impl.2
            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // java.util.concurrent.Callable
            public AppPrefs call() {
                AppPrefs appPrefs = null;
                String string = null;
                Cursor cursorQuery = DBUtil.query(AppPrefsDaoInt_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "packageName");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "ignoreVersionCodeUpdate");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "preferredRepoId");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "appPrefReleaseChannels");
                    if (cursorQuery.moveToFirst()) {
                        String string2 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                        long j = cursorQuery.getLong(columnIndexOrThrow2);
                        Long lValueOf = cursorQuery.isNull(columnIndexOrThrow3) ? null : Long.valueOf(cursorQuery.getLong(columnIndexOrThrow3));
                        if (!cursorQuery.isNull(columnIndexOrThrow4)) {
                            string = cursorQuery.getString(columnIndexOrThrow4);
                        }
                        appPrefs = new AppPrefs(string2, j, lValueOf, Converters.INSTANCE.fromStringToListString(string));
                    }
                    return appPrefs;
                } finally {
                    cursorQuery.close();
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // org.fdroid.database.AppPrefsDaoInt
    public AppPrefs getAppPrefsOrNull(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM AppPrefs WHERE packageName = ?", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        AppPrefs appPrefs = null;
        String string = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "packageName");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "ignoreVersionCodeUpdate");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "preferredRepoId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "appPrefReleaseChannels");
            if (cursorQuery.moveToFirst()) {
                String string2 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                long j = cursorQuery.getLong(columnIndexOrThrow2);
                Long lValueOf = cursorQuery.isNull(columnIndexOrThrow3) ? null : Long.valueOf(cursorQuery.getLong(columnIndexOrThrow3));
                if (!cursorQuery.isNull(columnIndexOrThrow4)) {
                    string = cursorQuery.getString(columnIndexOrThrow4);
                }
                appPrefs = new AppPrefs(string2, j, lValueOf, Converters.INSTANCE.fromStringToListString(string));
            }
            return appPrefs;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // org.fdroid.database.AppPrefsDaoInt
    public Map<String, Long> getPreferredReposInternal(List<String> list) {
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("SELECT packageName, preferredRepoId FROM PreferredRepo ");
        sbNewStringBuilder.append("\n");
        sbNewStringBuilder.append("             WHERE packageName IN (");
        int size = list.size();
        StringUtil.appendPlaceholders(sbNewStringBuilder, size);
        sbNewStringBuilder.append(")");
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire(sbNewStringBuilder.toString(), size);
        int i = 1;
        for (String str : list) {
            if (str == null) {
                roomSQLiteQueryAcquire.bindNull(i);
            } else {
                roomSQLiteQueryAcquire.bindString(i, str);
            }
            i++;
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "packageName");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "preferredRepoId");
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            while (cursorQuery.moveToNext()) {
                String string = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                if (cursorQuery.isNull(columnIndexOrThrow2)) {
                    linkedHashMap.put(string, null);
                } else {
                    Long lValueOf = cursorQuery.isNull(columnIndexOrThrow2) ? null : Long.valueOf(cursorQuery.getLong(columnIndexOrThrow2));
                    if (!linkedHashMap.containsKey(string)) {
                        linkedHashMap.put(string, lValueOf);
                    }
                }
            }
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
            return linkedHashMap;
        } catch (Throwable th) {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
            throw th;
        }
    }

    @Override // org.fdroid.database.AppPrefsDaoInt, org.fdroid.database.AppPrefsDao
    public LiveData getAppPrefs(String str) {
        return AppPrefsDaoInt.DefaultImpls.getAppPrefs(this, str);
    }

    @Override // org.fdroid.database.AppPrefsDaoInt
    public Map<String, Long> getPreferredRepos(List<String> list) {
        return AppPrefsDaoInt.DefaultImpls.getPreferredRepos(this, list);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
