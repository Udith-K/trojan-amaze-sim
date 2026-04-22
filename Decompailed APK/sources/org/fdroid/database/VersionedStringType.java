package org.fdroid.database;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: Version.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lorg/fdroid/database/VersionedStringType;", "", "<init>", "(Ljava/lang/String;I)V", "PERMISSION", "PERMISSION_SDK_23", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class VersionedStringType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ VersionedStringType[] $VALUES;
    public static final VersionedStringType PERMISSION = new VersionedStringType("PERMISSION", 0);
    public static final VersionedStringType PERMISSION_SDK_23 = new VersionedStringType("PERMISSION_SDK_23", 1);

    private static final /* synthetic */ VersionedStringType[] $values() {
        return new VersionedStringType[]{PERMISSION, PERMISSION_SDK_23};
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    private VersionedStringType(String str, int i) {
    }

    static {
        VersionedStringType[] versionedStringTypeArr$values = $values();
        $VALUES = versionedStringTypeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(versionedStringTypeArr$values);
    }

    public static VersionedStringType valueOf(String str) {
        return (VersionedStringType) Enum.valueOf(VersionedStringType.class, str);
    }

    public static VersionedStringType[] values() {
        return (VersionedStringType[]) $VALUES.clone();
    }
}
