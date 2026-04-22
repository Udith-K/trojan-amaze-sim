package androidx.room;

import androidx.lifecycle.LiveData;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import java.util.concurrent.Callable;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InvalidationLiveDataContainer.kt */
/* JADX INFO: loaded from: classes.dex */
public final class InvalidationLiveDataContainer {
    private final RoomDatabase database;
    private final Set liveDataSet;

    public InvalidationLiveDataContainer(RoomDatabase database) {
        Intrinsics.checkNotNullParameter(database, "database");
        this.database = database;
        Set setNewSetFromMap = Collections.newSetFromMap(new IdentityHashMap());
        Intrinsics.checkNotNullExpressionValue(setNewSetFromMap, "newSetFromMap(IdentityHashMap())");
        this.liveDataSet = setNewSetFromMap;
    }

    public final LiveData create(String[] tableNames, boolean z, Callable computeFunction) {
        Intrinsics.checkNotNullParameter(tableNames, "tableNames");
        Intrinsics.checkNotNullParameter(computeFunction, "computeFunction");
        return new RoomTrackingLiveData(this.database, this, z, computeFunction, tableNames);
    }

    public final void onActive(LiveData liveData) {
        Intrinsics.checkNotNullParameter(liveData, "liveData");
        this.liveDataSet.add(liveData);
    }

    public final void onInactive(LiveData liveData) {
        Intrinsics.checkNotNullParameter(liveData, "liveData");
        this.liveDataSet.remove(liveData);
    }
}
