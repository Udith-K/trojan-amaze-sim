package androidx.room;

import androidx.sqlite.db.SupportSQLiteProgram;
import androidx.sqlite.db.SupportSQLiteQuery;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RoomSQLiteQuery.kt */
/* JADX INFO: loaded from: classes.dex */
public final class RoomSQLiteQuery implements SupportSQLiteQuery, SupportSQLiteProgram {
    public static final Companion Companion = new Companion(null);
    public static final TreeMap queryPool = new TreeMap();
    private int argCount;
    private final int[] bindingTypes;
    public final byte[][] blobBindings;
    private final int capacity;
    public final double[] doubleBindings;
    public final long[] longBindings;
    private volatile String query;
    public final String[] stringBindings;

    public /* synthetic */ RoomSQLiteQuery(int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(i);
    }

    public static final RoomSQLiteQuery acquire(String str, int i) {
        return Companion.acquire(str, i);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    private RoomSQLiteQuery(int i) {
        this.capacity = i;
        int i2 = i + 1;
        this.bindingTypes = new int[i2];
        this.longBindings = new long[i2];
        this.doubleBindings = new double[i2];
        this.stringBindings = new String[i2];
        this.blobBindings = new byte[i2][];
    }

    public int getArgCount() {
        return this.argCount;
    }

    public final void init(String query, int i) {
        Intrinsics.checkNotNullParameter(query, "query");
        this.query = query;
        this.argCount = i;
    }

    public final void release() {
        TreeMap treeMap = queryPool;
        synchronized (treeMap) {
            treeMap.put(Integer.valueOf(this.capacity), this);
            Companion.prunePoolLocked$room_runtime_release();
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public String getSql() {
        String str = this.query;
        if (str != null) {
            return str;
        }
        throw new IllegalStateException("Required value was null.");
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public void bindTo(SupportSQLiteProgram statement) {
        Intrinsics.checkNotNullParameter(statement, "statement");
        int argCount = getArgCount();
        if (1 > argCount) {
            return;
        }
        int i = 1;
        while (true) {
            int i2 = this.bindingTypes[i];
            if (i2 == 1) {
                statement.bindNull(i);
            } else if (i2 == 2) {
                statement.bindLong(i, this.longBindings[i]);
            } else if (i2 == 3) {
                statement.bindDouble(i, this.doubleBindings[i]);
            } else if (i2 == 4) {
                String str = this.stringBindings[i];
                if (str == null) {
                    throw new IllegalArgumentException("Required value was null.");
                }
                statement.bindString(i, str);
            } else if (i2 == 5) {
                byte[] bArr = this.blobBindings[i];
                if (bArr == null) {
                    throw new IllegalArgumentException("Required value was null.");
                }
                statement.bindBlob(i, bArr);
            }
            if (i == argCount) {
                return;
            } else {
                i++;
            }
        }
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindNull(int i) {
        this.bindingTypes[i] = 1;
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindLong(int i, long j) {
        this.bindingTypes[i] = 2;
        this.longBindings[i] = j;
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindDouble(int i, double d) {
        this.bindingTypes[i] = 3;
        this.doubleBindings[i] = d;
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindString(int i, String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.bindingTypes[i] = 4;
        this.stringBindings[i] = value;
    }

    @Override // androidx.sqlite.db.SupportSQLiteProgram
    public void bindBlob(int i, byte[] value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.bindingTypes[i] = 5;
        this.blobBindings[i] = value;
    }

    /* JADX INFO: compiled from: RoomSQLiteQuery.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final RoomSQLiteQuery acquire(String query, int i) {
            Intrinsics.checkNotNullParameter(query, "query");
            TreeMap treeMap = RoomSQLiteQuery.queryPool;
            synchronized (treeMap) {
                Map.Entry entryCeilingEntry = treeMap.ceilingEntry(Integer.valueOf(i));
                if (entryCeilingEntry != null) {
                    treeMap.remove(entryCeilingEntry.getKey());
                    RoomSQLiteQuery sqliteQuery = (RoomSQLiteQuery) entryCeilingEntry.getValue();
                    sqliteQuery.init(query, i);
                    Intrinsics.checkNotNullExpressionValue(sqliteQuery, "sqliteQuery");
                    return sqliteQuery;
                }
                Unit unit = Unit.INSTANCE;
                RoomSQLiteQuery roomSQLiteQuery = new RoomSQLiteQuery(i, null);
                roomSQLiteQuery.init(query, i);
                return roomSQLiteQuery;
            }
        }

        public final void prunePoolLocked$room_runtime_release() {
            TreeMap treeMap = RoomSQLiteQuery.queryPool;
            if (treeMap.size() <= 15) {
                return;
            }
            int size = treeMap.size() - 10;
            Iterator it = treeMap.descendingKeySet().iterator();
            Intrinsics.checkNotNullExpressionValue(it, "queryPool.descendingKeySet().iterator()");
            while (true) {
                int i = size - 1;
                if (size <= 0) {
                    return;
                }
                it.next();
                it.remove();
                size = i;
            }
        }
    }
}
