package org.fdroid.index;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: IndexUpdater.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lorg/fdroid/index/IndexFormatVersion;", "", "<init>", "(Ljava/lang/String;I)V", "ONE", "TWO", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class IndexFormatVersion {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ IndexFormatVersion[] $VALUES;
    public static final IndexFormatVersion ONE = new IndexFormatVersion("ONE", 0);
    public static final IndexFormatVersion TWO = new IndexFormatVersion("TWO", 1);

    private static final /* synthetic */ IndexFormatVersion[] $values() {
        return new IndexFormatVersion[]{ONE, TWO};
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    static {
        IndexFormatVersion[] indexFormatVersionArr$values = $values();
        $VALUES = indexFormatVersionArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(indexFormatVersionArr$values);
    }

    private IndexFormatVersion(String str, int i) {
    }

    public static IndexFormatVersion valueOf(String str) {
        return (IndexFormatVersion) Enum.valueOf(IndexFormatVersion.class, str);
    }

    public static IndexFormatVersion[] values() {
        return (IndexFormatVersion[]) $VALUES.clone();
    }
}
