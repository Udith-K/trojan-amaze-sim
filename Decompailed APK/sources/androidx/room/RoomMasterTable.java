package androidx.room;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RoomMasterTable.kt */
/* JADX INFO: loaded from: classes.dex */
public final class RoomMasterTable {
    public static final RoomMasterTable INSTANCE = new RoomMasterTable();

    private RoomMasterTable() {
    }

    public static final String createInsertQuery(String hash) {
        Intrinsics.checkNotNullParameter(hash, "hash");
        return "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '" + hash + "')";
    }
}
