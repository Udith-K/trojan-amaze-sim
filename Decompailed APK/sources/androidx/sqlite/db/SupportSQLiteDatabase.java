package androidx.sqlite.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.CancellationSignal;
import java.io.Closeable;
import java.util.List;

/* JADX INFO: compiled from: SupportSQLiteDatabase.kt */
/* JADX INFO: loaded from: classes.dex */
public interface SupportSQLiteDatabase extends Closeable {
    void beginTransaction();

    void beginTransactionNonExclusive();

    SupportSQLiteStatement compileStatement(String str);

    int delete(String str, String str2, Object[] objArr);

    void endTransaction();

    void execSQL(String str);

    void execSQL(String str, Object[] objArr);

    List getAttachedDbs();

    String getPath();

    boolean inTransaction();

    boolean isOpen();

    boolean isWriteAheadLoggingEnabled();

    Cursor query(SupportSQLiteQuery supportSQLiteQuery);

    Cursor query(SupportSQLiteQuery supportSQLiteQuery, CancellationSignal cancellationSignal);

    Cursor query(String str);

    void setTransactionSuccessful();

    int update(String str, int i, ContentValues contentValues, String str2, Object[] objArr);
}
