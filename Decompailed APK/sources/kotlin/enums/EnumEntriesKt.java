package kotlin.enums;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EnumEntries.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class EnumEntriesKt {
    public static final EnumEntries enumEntries(Enum[] entries) {
        Intrinsics.checkNotNullParameter(entries, "entries");
        return new EnumEntriesList(entries);
    }
}
