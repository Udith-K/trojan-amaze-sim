package androidx.room;

import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EntityInsertionAdapter.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class EntityInsertionAdapter extends SharedSQLiteStatement {
    protected abstract void bind(SupportSQLiteStatement supportSQLiteStatement, Object obj);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EntityInsertionAdapter(RoomDatabase database) {
        super(database);
        Intrinsics.checkNotNullParameter(database, "database");
    }

    public final void insert(Object obj) {
        SupportSQLiteStatement supportSQLiteStatementAcquire = acquire();
        try {
            bind(supportSQLiteStatementAcquire, obj);
            supportSQLiteStatementAcquire.executeInsert();
        } finally {
            release(supportSQLiteStatementAcquire);
        }
    }

    public final void insert(Iterable entities) {
        Intrinsics.checkNotNullParameter(entities, "entities");
        SupportSQLiteStatement supportSQLiteStatementAcquire = acquire();
        try {
            Iterator it = entities.iterator();
            while (it.hasNext()) {
                bind(supportSQLiteStatementAcquire, it.next());
                supportSQLiteStatementAcquire.executeInsert();
            }
        } finally {
            release(supportSQLiteStatementAcquire);
        }
    }

    public final long insertAndReturnId(Object obj) {
        SupportSQLiteStatement supportSQLiteStatementAcquire = acquire();
        try {
            bind(supportSQLiteStatementAcquire, obj);
            return supportSQLiteStatementAcquire.executeInsert();
        } finally {
            release(supportSQLiteStatementAcquire);
        }
    }
}
