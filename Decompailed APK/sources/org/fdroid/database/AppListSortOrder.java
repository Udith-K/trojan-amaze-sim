package org.fdroid.database;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: AppDao.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lorg/fdroid/database/AppListSortOrder;", "", "<init>", "(Ljava/lang/String;I)V", "LAST_UPDATED", "NAME", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AppListSortOrder {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ AppListSortOrder[] $VALUES;
    public static final AppListSortOrder LAST_UPDATED = new AppListSortOrder("LAST_UPDATED", 0);
    public static final AppListSortOrder NAME = new AppListSortOrder("NAME", 1);

    private static final /* synthetic */ AppListSortOrder[] $values() {
        return new AppListSortOrder[]{LAST_UPDATED, NAME};
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    private AppListSortOrder(String str, int i) {
    }

    static {
        AppListSortOrder[] appListSortOrderArr$values = $values();
        $VALUES = appListSortOrderArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(appListSortOrderArr$values);
    }

    public static AppListSortOrder valueOf(String str) {
        return (AppListSortOrder) Enum.valueOf(AppListSortOrder.class, str);
    }

    public static AppListSortOrder[] values() {
        return (AppListSortOrder[]) $VALUES.clone();
    }
}
