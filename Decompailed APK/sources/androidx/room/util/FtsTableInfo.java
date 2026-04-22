package androidx.room.util;

import android.database.Cursor;
import androidx.sqlite.db.SupportSQLiteDatabase;
import ch.qos.logback.core.CoreConstants;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: FtsTableInfo.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FtsTableInfo {
    public static final Companion Companion = new Companion(null);
    private static final String[] FTS_OPTIONS = {"tokenize=", "compress=", "content=", "languageid=", "matchinfo=", "notindexed=", "order=", "prefix=", "uncompress="};
    public final Set columns;
    public final String name;
    public final Set options;

    public static final FtsTableInfo read(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
        return Companion.read(supportSQLiteDatabase, str);
    }

    public FtsTableInfo(String name, Set columns, Set options) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(columns, "columns");
        Intrinsics.checkNotNullParameter(options, "options");
        this.name = name;
        this.columns = columns;
        this.options = options;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FtsTableInfo(String name, Set columns, String createSql) {
        this(name, columns, Companion.parseOptions(createSql));
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(columns, "columns");
        Intrinsics.checkNotNullParameter(createSql, "createSql");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FtsTableInfo)) {
            return false;
        }
        FtsTableInfo ftsTableInfo = (FtsTableInfo) obj;
        if (Intrinsics.areEqual(this.name, ftsTableInfo.name) && Intrinsics.areEqual(this.columns, ftsTableInfo.columns)) {
            return Intrinsics.areEqual(this.options, ftsTableInfo.options);
        }
        return false;
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + this.columns.hashCode()) * 31) + this.options.hashCode();
    }

    public String toString() {
        return "FtsTableInfo{name='" + this.name + "', columns=" + this.columns + ", options=" + this.options + "'}";
    }

    /* JADX INFO: compiled from: FtsTableInfo.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FtsTableInfo read(SupportSQLiteDatabase database, String tableName) {
            Intrinsics.checkNotNullParameter(database, "database");
            Intrinsics.checkNotNullParameter(tableName, "tableName");
            return new FtsTableInfo(tableName, readColumns(database, tableName), readOptions(database, tableName));
        }

        private final Set readColumns(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
            Set setCreateSetBuilder = SetsKt.createSetBuilder();
            Cursor cursorQuery = supportSQLiteDatabase.query("PRAGMA table_info(`" + str + "`)");
            try {
                if (cursorQuery.getColumnCount() > 0) {
                    int columnIndex = cursorQuery.getColumnIndex("name");
                    while (cursorQuery.moveToNext()) {
                        String string = cursorQuery.getString(columnIndex);
                        Intrinsics.checkNotNullExpressionValue(string, "cursor.getString(nameIndex)");
                        setCreateSetBuilder.add(string);
                    }
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(cursorQuery, null);
                return SetsKt.build(setCreateSetBuilder);
            } finally {
            }
        }

        private final Set readOptions(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
            String sql;
            Cursor cursorQuery = supportSQLiteDatabase.query("SELECT * FROM sqlite_master WHERE `name` = '" + str + CoreConstants.SINGLE_QUOTE_CHAR);
            try {
                if (cursorQuery.moveToFirst()) {
                    sql = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("sql"));
                } else {
                    sql = "";
                }
                CloseableKt.closeFinally(cursorQuery, null);
                Intrinsics.checkNotNullExpressionValue(sql, "sql");
                return parseOptions(sql);
            } finally {
            }
        }

        public final Set parseOptions(String createStatement) {
            Character ch2;
            Intrinsics.checkNotNullParameter(createStatement, "createStatement");
            if (createStatement.length() == 0) {
                return SetsKt.emptySet();
            }
            String strSubstring = createStatement.substring(StringsKt.indexOf$default((CharSequence) createStatement, CoreConstants.LEFT_PARENTHESIS_CHAR, 0, false, 6, (Object) null) + 1, StringsKt.lastIndexOf$default((CharSequence) createStatement, CoreConstants.RIGHT_PARENTHESIS_CHAR, 0, false, 6, (Object) null));
            Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            ArrayList arrayList = new ArrayList();
            ArrayDeque arrayDeque = new ArrayDeque();
            int i = -1;
            int i2 = 0;
            int i3 = 0;
            while (i2 < strSubstring.length()) {
                char cCharAt = strSubstring.charAt(i2);
                int i4 = i3 + 1;
                if (cCharAt == '\'' || cCharAt == '\"' || cCharAt == '`') {
                    if (arrayDeque.isEmpty()) {
                        arrayDeque.push(Character.valueOf(cCharAt));
                    } else {
                        Character ch3 = (Character) arrayDeque.peek();
                        if (ch3 != null && ch3.charValue() == cCharAt) {
                            arrayDeque.pop();
                        }
                    }
                } else if (cCharAt == '[') {
                    if (arrayDeque.isEmpty()) {
                        arrayDeque.push(Character.valueOf(cCharAt));
                    }
                } else if (cCharAt == ']') {
                    if (!arrayDeque.isEmpty() && (ch2 = (Character) arrayDeque.peek()) != null && ch2.charValue() == '[') {
                        arrayDeque.pop();
                    }
                } else if (cCharAt == ',' && arrayDeque.isEmpty()) {
                    String strSubstring2 = strSubstring.substring(i + 1, i3);
                    Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
                    int length = strSubstring2.length() - 1;
                    int i5 = 0;
                    boolean z = false;
                    while (i5 <= length) {
                        boolean z2 = Intrinsics.compare((int) strSubstring2.charAt(!z ? i5 : length), 32) <= 0;
                        if (z) {
                            if (!z2) {
                                break;
                            }
                            length--;
                        } else if (z2) {
                            i5++;
                        } else {
                            z = true;
                        }
                    }
                    arrayList.add(strSubstring2.subSequence(i5, length + 1).toString());
                    i = i3;
                }
                i2++;
                i3 = i4;
            }
            String strSubstring3 = strSubstring.substring(i + 1);
            Intrinsics.checkNotNullExpressionValue(strSubstring3, "this as java.lang.String).substring(startIndex)");
            arrayList.add(StringsKt.trim(strSubstring3).toString());
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                String str = (String) obj;
                String[] strArr = FtsTableInfo.FTS_OPTIONS;
                int length2 = strArr.length;
                int i6 = 0;
                while (true) {
                    if (i6 >= length2) {
                        break;
                    }
                    if (StringsKt.startsWith$default(str, strArr[i6], false, 2, (Object) null)) {
                        arrayList2.add(obj);
                        break;
                    }
                    i6++;
                }
            }
            return CollectionsKt.toSet(arrayList2);
        }
    }
}
