package androidx.room;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: RoomDatabaseExt.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TransactionElement implements CoroutineContext.Element {
    public static final Key Key = new Key(null);

    /* JADX INFO: compiled from: RoomDatabaseExt.kt */
    public static final class Key implements CoroutineContext.Key {
        public /* synthetic */ Key(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Key() {
        }
    }
}
