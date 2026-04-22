package androidx.room.util;

import android.database.Cursor;
import androidx.sqlite.db.SupportSQLiteDatabase;
import ch.qos.logback.core.CoreConstants;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ViewInfo.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ViewInfo {
    public static final Companion Companion = new Companion(null);
    public final String name;
    public final String sql;

    public static final ViewInfo read(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
        return Companion.read(supportSQLiteDatabase, str);
    }

    public ViewInfo(String name, String str) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        this.sql = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ViewInfo)) {
            return false;
        }
        ViewInfo viewInfo = (ViewInfo) obj;
        if (Intrinsics.areEqual(this.name, viewInfo.name)) {
            String str = this.sql;
            String str2 = viewInfo.sql;
            if (str != null ? Intrinsics.areEqual(str, str2) : str2 == null) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int iHashCode = this.name.hashCode() * 31;
        String str = this.sql;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "ViewInfo{name='" + this.name + "', sql='" + this.sql + "'}";
    }

    /* JADX INFO: compiled from: ViewInfo.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ViewInfo read(SupportSQLiteDatabase database, String viewName) {
            ViewInfo viewInfo;
            Intrinsics.checkNotNullParameter(database, "database");
            Intrinsics.checkNotNullParameter(viewName, "viewName");
            Cursor cursorQuery = database.query("SELECT name, sql FROM sqlite_master WHERE type = 'view' AND name = '" + viewName + CoreConstants.SINGLE_QUOTE_CHAR);
            try {
                if (cursorQuery.moveToFirst()) {
                    String string = cursorQuery.getString(0);
                    Intrinsics.checkNotNullExpressionValue(string, "cursor.getString(0)");
                    viewInfo = new ViewInfo(string, cursorQuery.getString(1));
                } else {
                    viewInfo = new ViewInfo(viewName, null);
                }
                CloseableKt.closeFinally(cursorQuery, null);
                return viewInfo;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(cursorQuery, th);
                    throw th2;
                }
            }
        }
    }
}
