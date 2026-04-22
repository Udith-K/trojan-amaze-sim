package androidx.sqlite.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.CancellationSignal;
import java.io.File;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SupportSQLiteCompat.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SupportSQLiteCompat$Api16Impl {
    public static final SupportSQLiteCompat$Api16Impl INSTANCE = new SupportSQLiteCompat$Api16Impl();

    private SupportSQLiteCompat$Api16Impl() {
    }

    public static final boolean deleteDatabase(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        return SQLiteDatabase.deleteDatabase(file);
    }

    public static final Cursor rawQueryWithFactory(SQLiteDatabase sQLiteDatabase, String sql, String[] selectionArgs, String str, CancellationSignal cancellationSignal, SQLiteDatabase.CursorFactory cursorFactory) {
        Intrinsics.checkNotNullParameter(sQLiteDatabase, "sQLiteDatabase");
        Intrinsics.checkNotNullParameter(sql, "sql");
        Intrinsics.checkNotNullParameter(selectionArgs, "selectionArgs");
        Intrinsics.checkNotNullParameter(cancellationSignal, "cancellationSignal");
        Intrinsics.checkNotNullParameter(cursorFactory, "cursorFactory");
        Cursor cursorRawQueryWithFactory = sQLiteDatabase.rawQueryWithFactory(cursorFactory, sql, selectionArgs, str, cancellationSignal);
        Intrinsics.checkNotNullExpressionValue(cursorRawQueryWithFactory, "sQLiteDatabase.rawQueryW…ationSignal\n            )");
        return cursorRawQueryWithFactory;
    }

    public static final boolean isWriteAheadLoggingEnabled(SQLiteDatabase sQLiteDatabase) {
        Intrinsics.checkNotNullParameter(sQLiteDatabase, "sQLiteDatabase");
        return sQLiteDatabase.isWriteAheadLoggingEnabled();
    }

    public static final void setWriteAheadLoggingEnabled(SQLiteOpenHelper sQLiteOpenHelper, boolean z) {
        Intrinsics.checkNotNullParameter(sQLiteOpenHelper, "sQLiteOpenHelper");
        sQLiteOpenHelper.setWriteAheadLoggingEnabled(z);
    }
}
