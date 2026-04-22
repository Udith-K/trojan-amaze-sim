package org.fdroid.fdroid.views.repos;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: RepoDetailsViewModel.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lorg/fdroid/fdroid/views/repos/ArchiveState;", "", "<init>", "(Ljava/lang/String;I)V", "ENABLED", "DISABLED", "LOADING", "UNKNOWN", "app_fullRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ArchiveState {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ ArchiveState[] $VALUES;
    public static final ArchiveState ENABLED = new ArchiveState("ENABLED", 0);
    public static final ArchiveState DISABLED = new ArchiveState("DISABLED", 1);
    public static final ArchiveState LOADING = new ArchiveState("LOADING", 2);
    public static final ArchiveState UNKNOWN = new ArchiveState("UNKNOWN", 3);

    private static final /* synthetic */ ArchiveState[] $values() {
        return new ArchiveState[]{ENABLED, DISABLED, LOADING, UNKNOWN};
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    private ArchiveState(String str, int i) {
    }

    static {
        ArchiveState[] archiveStateArr$values = $values();
        $VALUES = archiveStateArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(archiveStateArr$values);
    }

    public static ArchiveState valueOf(String str) {
        return (ArchiveState) Enum.valueOf(ArchiveState.class, str);
    }

    public static ArchiveState[] values() {
        return (ArchiveState[]) $VALUES.clone();
    }
}
