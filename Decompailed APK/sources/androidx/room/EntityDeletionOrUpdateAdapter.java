package androidx.room;

import androidx.sqlite.db.SupportSQLiteStatement;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EntityDeletionOrUpdateAdapter.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class EntityDeletionOrUpdateAdapter extends SharedSQLiteStatement {
    protected abstract void bind(SupportSQLiteStatement supportSQLiteStatement, Object obj);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EntityDeletionOrUpdateAdapter(RoomDatabase database) {
        super(database);
        Intrinsics.checkNotNullParameter(database, "database");
    }

    public final int handle(Object obj) {
        SupportSQLiteStatement supportSQLiteStatementAcquire = acquire();
        try {
            bind(supportSQLiteStatementAcquire, obj);
            return supportSQLiteStatementAcquire.executeUpdateDelete();
        } finally {
            release(supportSQLiteStatementAcquire);
        }
    }
}
