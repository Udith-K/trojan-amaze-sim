package androidx.room;

import java.util.concurrent.Callable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: CoroutinesRoom.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class CoroutinesRoom {
    public static final Companion Companion = new Companion(null);

    public static final Flow createFlow(RoomDatabase roomDatabase, boolean z, String[] strArr, Callable callable) {
        return Companion.createFlow(roomDatabase, z, strArr, callable);
    }

    /* JADX INFO: compiled from: CoroutinesRoom.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Flow createFlow(RoomDatabase roomDatabase, boolean z, String[] strArr, Callable callable) {
            return FlowKt.flow(new CoroutinesRoom$Companion$createFlow$1(z, roomDatabase, strArr, callable, null));
        }
    }
}
