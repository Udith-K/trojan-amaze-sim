package androidx.room.util;

import androidx.room.Index$Order;
import androidx.sqlite.db.SupportSQLiteDatabase;
import ch.qos.logback.core.CoreConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: TableInfo.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TableInfo {
    public static final Companion Companion = new Companion(null);
    public final Map columns;
    public final Set foreignKeys;
    public final Set indices;
    public final String name;

    public static final TableInfo read(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
        return Companion.read(supportSQLiteDatabase, str);
    }

    public TableInfo(String name, Map columns, Set foreignKeys, Set set) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(columns, "columns");
        Intrinsics.checkNotNullParameter(foreignKeys, "foreignKeys");
        this.name = name;
        this.columns = columns;
        this.foreignKeys = foreignKeys;
        this.indices = set;
    }

    public boolean equals(Object obj) {
        Set set;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TableInfo)) {
            return false;
        }
        TableInfo tableInfo = (TableInfo) obj;
        if (!Intrinsics.areEqual(this.name, tableInfo.name) || !Intrinsics.areEqual(this.columns, tableInfo.columns) || !Intrinsics.areEqual(this.foreignKeys, tableInfo.foreignKeys)) {
            return false;
        }
        Set set2 = this.indices;
        if (set2 == null || (set = tableInfo.indices) == null) {
            return true;
        }
        return Intrinsics.areEqual(set2, set);
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + this.columns.hashCode()) * 31) + this.foreignKeys.hashCode();
    }

    public String toString() {
        return "TableInfo{name='" + this.name + "', columns=" + this.columns + ", foreignKeys=" + this.foreignKeys + ", indices=" + this.indices + CoreConstants.CURLY_RIGHT;
    }

    /* JADX INFO: compiled from: TableInfo.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TableInfo read(SupportSQLiteDatabase database, String tableName) {
            Intrinsics.checkNotNullParameter(database, "database");
            Intrinsics.checkNotNullParameter(tableName, "tableName");
            return TableInfoKt.readTableInfo(database, tableName);
        }
    }

    /* JADX INFO: compiled from: TableInfo.kt */
    public static final class Column {
        public static final Companion Companion = new Companion(null);
        public final int affinity;
        public final int createdFrom;
        public final String defaultValue;
        public final String name;
        public final boolean notNull;
        public final int primaryKeyPosition;
        public final String type;

        public Column(String name, String type, boolean z, int i, String str, int i2) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(type, "type");
            this.name = name;
            this.type = type;
            this.notNull = z;
            this.primaryKeyPosition = i;
            this.defaultValue = str;
            this.createdFrom = i2;
            this.affinity = findAffinity(type);
        }

        private final int findAffinity(String str) {
            if (str == null) {
                return 5;
            }
            Locale US = Locale.US;
            Intrinsics.checkNotNullExpressionValue(US, "US");
            String upperCase = str.toUpperCase(US);
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
            if (StringsKt.contains$default((CharSequence) upperCase, (CharSequence) "INT", false, 2, (Object) null)) {
                return 3;
            }
            if (StringsKt.contains$default((CharSequence) upperCase, (CharSequence) "CHAR", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) upperCase, (CharSequence) "CLOB", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) upperCase, (CharSequence) "TEXT", false, 2, (Object) null)) {
                return 2;
            }
            if (StringsKt.contains$default((CharSequence) upperCase, (CharSequence) "BLOB", false, 2, (Object) null)) {
                return 5;
            }
            return (StringsKt.contains$default((CharSequence) upperCase, (CharSequence) "REAL", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) upperCase, (CharSequence) "FLOA", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) upperCase, (CharSequence) "DOUB", false, 2, (Object) null)) ? 4 : 1;
        }

        /* JADX INFO: compiled from: TableInfo.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final boolean defaultValueEquals(String current, String str) {
                Intrinsics.checkNotNullParameter(current, "current");
                if (Intrinsics.areEqual(current, str)) {
                    return true;
                }
                if (!containsSurroundingParenthesis(current)) {
                    return false;
                }
                String strSubstring = current.substring(1, current.length() - 1);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                return Intrinsics.areEqual(StringsKt.trim(strSubstring).toString(), str);
            }

            private final boolean containsSurroundingParenthesis(String str) {
                if (str.length() == 0) {
                    return false;
                }
                int i = 0;
                int i2 = 0;
                int i3 = 0;
                while (i < str.length()) {
                    char cCharAt = str.charAt(i);
                    int i4 = i3 + 1;
                    if (i3 == 0 && cCharAt != '(') {
                        return false;
                    }
                    if (cCharAt == '(') {
                        i2++;
                    } else if (cCharAt == ')' && i2 - 1 == 0 && i3 != str.length() - 1) {
                        return false;
                    }
                    i++;
                    i3 = i4;
                }
                return i2 == 0;
            }
        }

        public boolean equals(Object obj) {
            String str;
            String str2;
            String str3;
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Column) || this.primaryKeyPosition != ((Column) obj).primaryKeyPosition) {
                return false;
            }
            Column column = (Column) obj;
            if (!Intrinsics.areEqual(this.name, column.name) || this.notNull != column.notNull) {
                return false;
            }
            if (this.createdFrom == 1 && column.createdFrom == 2 && (str3 = this.defaultValue) != null && !Companion.defaultValueEquals(str3, column.defaultValue)) {
                return false;
            }
            if (this.createdFrom == 2 && column.createdFrom == 1 && (str2 = column.defaultValue) != null && !Companion.defaultValueEquals(str2, this.defaultValue)) {
                return false;
            }
            int i = this.createdFrom;
            return (i == 0 || i != column.createdFrom || ((str = this.defaultValue) == null ? column.defaultValue == null : Companion.defaultValueEquals(str, column.defaultValue))) && this.affinity == column.affinity;
        }

        public int hashCode() {
            return (((((this.name.hashCode() * 31) + this.affinity) * 31) + (this.notNull ? 1231 : 1237)) * 31) + this.primaryKeyPosition;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Column{name='");
            sb.append(this.name);
            sb.append("', type='");
            sb.append(this.type);
            sb.append("', affinity='");
            sb.append(this.affinity);
            sb.append("', notNull=");
            sb.append(this.notNull);
            sb.append(", primaryKeyPosition=");
            sb.append(this.primaryKeyPosition);
            sb.append(", defaultValue='");
            String str = this.defaultValue;
            if (str == null) {
                str = "undefined";
            }
            sb.append(str);
            sb.append("'}");
            return sb.toString();
        }
    }

    /* JADX INFO: compiled from: TableInfo.kt */
    public static final class ForeignKey {
        public final List columnNames;
        public final String onDelete;
        public final String onUpdate;
        public final List referenceColumnNames;
        public final String referenceTable;

        public ForeignKey(String referenceTable, String onDelete, String onUpdate, List columnNames, List referenceColumnNames) {
            Intrinsics.checkNotNullParameter(referenceTable, "referenceTable");
            Intrinsics.checkNotNullParameter(onDelete, "onDelete");
            Intrinsics.checkNotNullParameter(onUpdate, "onUpdate");
            Intrinsics.checkNotNullParameter(columnNames, "columnNames");
            Intrinsics.checkNotNullParameter(referenceColumnNames, "referenceColumnNames");
            this.referenceTable = referenceTable;
            this.onDelete = onDelete;
            this.onUpdate = onUpdate;
            this.columnNames = columnNames;
            this.referenceColumnNames = referenceColumnNames;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ForeignKey)) {
                return false;
            }
            ForeignKey foreignKey = (ForeignKey) obj;
            if (Intrinsics.areEqual(this.referenceTable, foreignKey.referenceTable) && Intrinsics.areEqual(this.onDelete, foreignKey.onDelete) && Intrinsics.areEqual(this.onUpdate, foreignKey.onUpdate) && Intrinsics.areEqual(this.columnNames, foreignKey.columnNames)) {
                return Intrinsics.areEqual(this.referenceColumnNames, foreignKey.referenceColumnNames);
            }
            return false;
        }

        public int hashCode() {
            return (((((((this.referenceTable.hashCode() * 31) + this.onDelete.hashCode()) * 31) + this.onUpdate.hashCode()) * 31) + this.columnNames.hashCode()) * 31) + this.referenceColumnNames.hashCode();
        }

        public String toString() {
            return "ForeignKey{referenceTable='" + this.referenceTable + "', onDelete='" + this.onDelete + " +', onUpdate='" + this.onUpdate + "', columnNames=" + this.columnNames + ", referenceColumnNames=" + this.referenceColumnNames + CoreConstants.CURLY_RIGHT;
        }
    }

    /* JADX INFO: compiled from: TableInfo.kt */
    public static final class ForeignKeyWithSequence implements Comparable {
        private final String from;
        private final int id;
        private final int sequence;
        private final String to;

        public ForeignKeyWithSequence(int i, int i2, String from, String to) {
            Intrinsics.checkNotNullParameter(from, "from");
            Intrinsics.checkNotNullParameter(to, "to");
            this.id = i;
            this.sequence = i2;
            this.from = from;
            this.to = to;
        }

        public final int getId() {
            return this.id;
        }

        public final String getFrom() {
            return this.from;
        }

        public final String getTo() {
            return this.to;
        }

        @Override // java.lang.Comparable
        public int compareTo(ForeignKeyWithSequence other) {
            Intrinsics.checkNotNullParameter(other, "other");
            int i = this.id - other.id;
            return i == 0 ? this.sequence - other.sequence : i;
        }
    }

    /* JADX INFO: compiled from: TableInfo.kt */
    public static final class Index {
        public static final Companion Companion = new Companion(null);
        public final List columns;
        public final String name;
        public List orders;
        public final boolean unique;

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.Object, java.util.Collection, java.util.List] */
        /* JADX WARN: Type inference failed for: r5v1, types: [java.util.List] */
        /* JADX WARN: Type inference failed for: r5v2, types: [java.util.ArrayList] */
        public Index(String name, boolean z, List columns, List orders) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(columns, "columns");
            Intrinsics.checkNotNullParameter(orders, "orders");
            this.name = name;
            this.unique = z;
            this.columns = columns;
            this.orders = orders;
            if (orders.isEmpty()) {
                int size = columns.size();
                orders = new ArrayList(size);
                for (int i = 0; i < size; i++) {
                    orders.add(Index$Order.ASC.name());
                }
            }
            this.orders = orders;
        }

        /* JADX INFO: compiled from: TableInfo.kt */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Index)) {
                return false;
            }
            Index index = (Index) obj;
            if (this.unique != index.unique || !Intrinsics.areEqual(this.columns, index.columns) || !Intrinsics.areEqual(this.orders, index.orders)) {
                return false;
            }
            if (StringsKt.startsWith$default(this.name, "index_", false, 2, (Object) null)) {
                return StringsKt.startsWith$default(index.name, "index_", false, 2, (Object) null);
            }
            return Intrinsics.areEqual(this.name, index.name);
        }

        public int hashCode() {
            return ((((((StringsKt.startsWith$default(this.name, "index_", false, 2, (Object) null) ? -1184239155 : this.name.hashCode()) * 31) + (this.unique ? 1 : 0)) * 31) + this.columns.hashCode()) * 31) + this.orders.hashCode();
        }

        public String toString() {
            return "Index{name='" + this.name + "', unique=" + this.unique + ", columns=" + this.columns + ", orders=" + this.orders + "'}";
        }
    }
}
