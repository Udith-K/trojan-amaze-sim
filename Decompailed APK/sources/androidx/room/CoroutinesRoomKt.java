package androidx.room;

import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExecutorsKt;

/* JADX INFO: compiled from: CoroutinesRoom.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class CoroutinesRoomKt {
    public static final CoroutineDispatcher getQueryDispatcher(RoomDatabase roomDatabase) {
        Map<String, Object> backingFieldMap = roomDatabase.getBackingFieldMap();
        Object objFrom = backingFieldMap.get("QueryDispatcher");
        if (objFrom == null) {
            objFrom = ExecutorsKt.from(roomDatabase.getQueryExecutor());
            backingFieldMap.put("QueryDispatcher", objFrom);
        }
        Intrinsics.checkNotNull(objFrom, "null cannot be cast to non-null type kotlinx.coroutines.CoroutineDispatcher");
        return (CoroutineDispatcher) objFrom;
    }

    public static final CoroutineDispatcher getTransactionDispatcher(RoomDatabase roomDatabase) {
        Map<String, Object> backingFieldMap = roomDatabase.getBackingFieldMap();
        Object objFrom = backingFieldMap.get("TransactionDispatcher");
        if (objFrom == null) {
            objFrom = ExecutorsKt.from(roomDatabase.getTransactionExecutor());
            backingFieldMap.put("TransactionDispatcher", objFrom);
        }
        Intrinsics.checkNotNull(objFrom, "null cannot be cast to non-null type kotlinx.coroutines.CoroutineDispatcher");
        return (CoroutineDispatcher) objFrom;
    }
}
