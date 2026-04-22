package androidx.sqlite.db;

import android.database.Cursor;
import android.os.Bundle;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SupportSQLiteCompat.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SupportSQLiteCompat$Api23Impl {
    public static final SupportSQLiteCompat$Api23Impl INSTANCE = new SupportSQLiteCompat$Api23Impl();

    private SupportSQLiteCompat$Api23Impl() {
    }

    public static final void setExtras(Cursor cursor, Bundle extras) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        Intrinsics.checkNotNullParameter(extras, "extras");
        cursor.setExtras(extras);
    }
}
