package androidx.room;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.CancellationSignal;
import android.os.Looper;
import android.util.Log;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteCompat$Api19Impl;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Deprecated;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RoomDatabase.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class RoomDatabase {
    public static final Companion Companion = new Companion(null);
    public static final int MAX_BIND_PARAMETER_CNT = 999;
    private boolean allowMainThreadQueries;
    private AutoCloser autoCloser;
    private final Map<String, Object> backingFieldMap;
    private SupportSQLiteOpenHelper internalOpenHelper;
    private Executor internalQueryExecutor;
    private Executor internalTransactionExecutor;
    protected List<? extends Callback> mCallbacks;
    protected volatile SupportSQLiteDatabase mDatabase;
    private final Map<Class<?>, Object> typeConverters;
    private boolean writeAheadLoggingEnabled;
    private final InvalidationTracker invalidationTracker = createInvalidationTracker();
    private Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs = new LinkedHashMap();
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final ThreadLocal<Integer> suspendingTransactionId = new ThreadLocal<>();

    /* JADX INFO: compiled from: RoomDatabase.kt */
    public static abstract class Callback {
        public void onCreate(SupportSQLiteDatabase db) {
            Intrinsics.checkNotNullParameter(db, "db");
        }

        public void onDestructiveMigration(SupportSQLiteDatabase db) {
            Intrinsics.checkNotNullParameter(db, "db");
        }

        public void onOpen(SupportSQLiteDatabase db) {
            Intrinsics.checkNotNullParameter(db, "db");
        }
    }

    /* JADX INFO: compiled from: RoomDatabase.kt */
    public static abstract class PrepackagedDatabaseCallback {
    }

    @Deprecated
    protected static /* synthetic */ void getMCallbacks$annotations() {
    }

    @Deprecated
    protected static /* synthetic */ void getMDatabase$annotations() {
    }

    public static /* synthetic */ void isOpen$annotations() {
    }

    public static /* synthetic */ void isOpenInternal$annotations() {
    }

    public abstract void clearAllTables();

    protected abstract InvalidationTracker createInvalidationTracker();

    protected abstract SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration);

    public final Cursor query(SupportSQLiteQuery query) {
        Intrinsics.checkNotNullParameter(query, "query");
        return query$default(this, query, null, 2, null);
    }

    public RoomDatabase() {
        Map<String, Object> mapSynchronizedMap = Collections.synchronizedMap(new LinkedHashMap());
        Intrinsics.checkNotNullExpressionValue(mapSynchronizedMap, "synchronizedMap(mutableMapOf())");
        this.backingFieldMap = mapSynchronizedMap;
        this.typeConverters = new LinkedHashMap();
    }

    public Executor getQueryExecutor() {
        Executor executor = this.internalQueryExecutor;
        if (executor != null) {
            return executor;
        }
        Intrinsics.throwUninitializedPropertyAccessException("internalQueryExecutor");
        return null;
    }

    public Executor getTransactionExecutor() {
        Executor executor = this.internalTransactionExecutor;
        if (executor != null) {
            return executor;
        }
        Intrinsics.throwUninitializedPropertyAccessException("internalTransactionExecutor");
        return null;
    }

    public SupportSQLiteOpenHelper getOpenHelper() {
        SupportSQLiteOpenHelper supportSQLiteOpenHelper = this.internalOpenHelper;
        if (supportSQLiteOpenHelper != null) {
            return supportSQLiteOpenHelper;
        }
        Intrinsics.throwUninitializedPropertyAccessException("internalOpenHelper");
        return null;
    }

    public InvalidationTracker getInvalidationTracker() {
        return this.invalidationTracker;
    }

    protected final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> getAutoMigrationSpecs() {
        return this.autoMigrationSpecs;
    }

    protected final void setAutoMigrationSpecs(Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.autoMigrationSpecs = map;
    }

    public final Lock getCloseLock$room_runtime_release() {
        ReentrantReadWriteLock.ReadLock lock = this.readWriteLock.readLock();
        Intrinsics.checkNotNullExpressionValue(lock, "readWriteLock.readLock()");
        return lock;
    }

    public final ThreadLocal<Integer> getSuspendingTransactionId() {
        return this.suspendingTransactionId;
    }

    public final Map<String, Object> getBackingFieldMap() {
        return this.backingFieldMap;
    }

    public <T> T getTypeConverter(Class<T> klass) {
        Intrinsics.checkNotNullParameter(klass, "klass");
        return (T) this.typeConverters.get(klass);
    }

    public void init(DatabaseConfiguration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        this.internalOpenHelper = createOpenHelper(configuration);
        Set<Class<? extends AutoMigrationSpec>> requiredAutoMigrationSpecs = getRequiredAutoMigrationSpecs();
        BitSet bitSet = new BitSet();
        for (Class<? extends AutoMigrationSpec> cls : requiredAutoMigrationSpecs) {
            int size = configuration.autoMigrationSpecs.size() - 1;
            if (size >= 0) {
                while (true) {
                    int i = size - 1;
                    if (cls.isAssignableFrom(configuration.autoMigrationSpecs.get(size).getClass())) {
                        bitSet.set(size);
                        break;
                    } else if (i < 0) {
                        break;
                    } else {
                        size = i;
                    }
                }
                size = -1;
            } else {
                size = -1;
            }
            if (size < 0) {
                throw new IllegalArgumentException(("A required auto migration spec (" + cls.getCanonicalName() + ") is missing in the database configuration.").toString());
            }
            this.autoMigrationSpecs.put(cls, (AutoMigrationSpec) configuration.autoMigrationSpecs.get(size));
        }
        int size2 = configuration.autoMigrationSpecs.size() - 1;
        if (size2 >= 0) {
            while (true) {
                int i2 = size2 - 1;
                if (!bitSet.get(size2)) {
                    throw new IllegalArgumentException("Unexpected auto migration specs found. Annotate AutoMigrationSpec implementation with @ProvidedAutoMigrationSpec annotation or remove this spec from the builder.");
                }
                if (i2 < 0) {
                    break;
                } else {
                    size2 = i2;
                }
            }
        }
        for (Migration migration : getAutoMigrations(this.autoMigrationSpecs)) {
            if (!configuration.migrationContainer.contains(migration.startVersion, migration.endVersion)) {
                configuration.migrationContainer.addMigrations(migration);
            }
        }
        SQLiteCopyOpenHelper sQLiteCopyOpenHelper = (SQLiteCopyOpenHelper) unwrapOpenHelper(SQLiteCopyOpenHelper.class, getOpenHelper());
        if (sQLiteCopyOpenHelper != null) {
            sQLiteCopyOpenHelper.setDatabaseConfiguration(configuration);
        }
        AutoClosingRoomOpenHelper autoClosingRoomOpenHelper = (AutoClosingRoomOpenHelper) unwrapOpenHelper(AutoClosingRoomOpenHelper.class, getOpenHelper());
        if (autoClosingRoomOpenHelper != null) {
            this.autoCloser = autoClosingRoomOpenHelper.autoCloser;
            getInvalidationTracker().setAutoCloser$room_runtime_release(autoClosingRoomOpenHelper.autoCloser);
        }
        boolean z = configuration.journalMode == JournalMode.WRITE_AHEAD_LOGGING;
        getOpenHelper().setWriteAheadLoggingEnabled(z);
        this.mCallbacks = configuration.callbacks;
        this.internalQueryExecutor = configuration.queryExecutor;
        this.internalTransactionExecutor = new TransactionExecutor(configuration.transactionExecutor);
        this.allowMainThreadQueries = configuration.allowMainThreadQueries;
        this.writeAheadLoggingEnabled = z;
        if (configuration.multiInstanceInvalidationServiceIntent != null) {
            if (configuration.name == null) {
                throw new IllegalArgumentException("Required value was null.");
            }
            getInvalidationTracker().startMultiInstanceInvalidation$room_runtime_release(configuration.context, configuration.name, configuration.multiInstanceInvalidationServiceIntent);
        }
        Map<Class<?>, List<Class<?>>> requiredTypeConverters = getRequiredTypeConverters();
        BitSet bitSet2 = new BitSet();
        for (Map.Entry<Class<?>, List<Class<?>>> entry : requiredTypeConverters.entrySet()) {
            Class<?> key = entry.getKey();
            for (Class<?> cls2 : entry.getValue()) {
                int size3 = configuration.typeConverters.size() - 1;
                if (size3 >= 0) {
                    while (true) {
                        int i3 = size3 - 1;
                        if (cls2.isAssignableFrom(configuration.typeConverters.get(size3).getClass())) {
                            bitSet2.set(size3);
                            break;
                        } else if (i3 < 0) {
                            break;
                        } else {
                            size3 = i3;
                        }
                    }
                    size3 = -1;
                } else {
                    size3 = -1;
                }
                if (size3 < 0) {
                    throw new IllegalArgumentException(("A required type converter (" + cls2 + ") for " + key.getCanonicalName() + " is missing in the database configuration.").toString());
                }
                this.typeConverters.put(cls2, configuration.typeConverters.get(size3));
            }
        }
        int size4 = configuration.typeConverters.size() - 1;
        if (size4 < 0) {
            return;
        }
        while (true) {
            int i4 = size4 - 1;
            if (!bitSet2.get(size4)) {
                throw new IllegalArgumentException("Unexpected type converter " + configuration.typeConverters.get(size4) + ". Annotate TypeConverter class with @ProvidedTypeConverter annotation or remove this converter from the builder.");
            }
            if (i4 < 0) {
                return;
            } else {
                size4 = i4;
            }
        }
    }

    public List<Migration> getAutoMigrations(Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
        Intrinsics.checkNotNullParameter(autoMigrationSpecs, "autoMigrationSpecs");
        return CollectionsKt.emptyList();
    }

    private final Object unwrapOpenHelper(Class cls, SupportSQLiteOpenHelper supportSQLiteOpenHelper) {
        if (cls.isInstance(supportSQLiteOpenHelper)) {
            return supportSQLiteOpenHelper;
        }
        if (supportSQLiteOpenHelper instanceof DelegatingOpenHelper) {
            return unwrapOpenHelper(cls, ((DelegatingOpenHelper) supportSQLiteOpenHelper).getDelegate());
        }
        return null;
    }

    protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        return MapsKt.emptyMap();
    }

    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return SetsKt.emptySet();
    }

    public boolean isOpen() {
        Boolean boolValueOf;
        boolean zIsOpen;
        AutoCloser autoCloser = this.autoCloser;
        if (autoCloser != null) {
            zIsOpen = autoCloser.isActive();
        } else {
            SupportSQLiteDatabase supportSQLiteDatabase = this.mDatabase;
            if (supportSQLiteDatabase == null) {
                boolValueOf = null;
                return Intrinsics.areEqual(boolValueOf, Boolean.TRUE);
            }
            zIsOpen = supportSQLiteDatabase.isOpen();
        }
        boolValueOf = Boolean.valueOf(zIsOpen);
        return Intrinsics.areEqual(boolValueOf, Boolean.TRUE);
    }

    public final boolean isOpenInternal() {
        SupportSQLiteDatabase supportSQLiteDatabase = this.mDatabase;
        return supportSQLiteDatabase != null && supportSQLiteDatabase.isOpen();
    }

    public void close() {
        if (isOpen()) {
            ReentrantReadWriteLock.WriteLock writeLock = this.readWriteLock.writeLock();
            Intrinsics.checkNotNullExpressionValue(writeLock, "readWriteLock.writeLock()");
            writeLock.lock();
            try {
                getInvalidationTracker().stopMultiInstanceInvalidation$room_runtime_release();
                getOpenHelper().close();
            } finally {
                writeLock.unlock();
            }
        }
    }

    public final boolean isMainThread$room_runtime_release() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public void assertNotMainThread() {
        if (!this.allowMainThreadQueries && isMainThread$room_runtime_release()) {
            throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.");
        }
    }

    public void assertNotSuspendingTransaction() {
        if (!inTransaction() && this.suspendingTransactionId.get() != null) {
            throw new IllegalStateException("Cannot access database on a different coroutine context inherited from a suspending transaction.");
        }
    }

    public Cursor query(String query, Object[] objArr) {
        Intrinsics.checkNotNullParameter(query, "query");
        return getOpenHelper().getWritableDatabase().query(new SimpleSQLiteQuery(query, objArr));
    }

    public static /* synthetic */ Cursor query$default(RoomDatabase roomDatabase, SupportSQLiteQuery supportSQLiteQuery, CancellationSignal cancellationSignal, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: query");
        }
        if ((i & 2) != 0) {
            cancellationSignal = null;
        }
        return roomDatabase.query(supportSQLiteQuery, cancellationSignal);
    }

    public Cursor query(SupportSQLiteQuery query, CancellationSignal cancellationSignal) {
        Intrinsics.checkNotNullParameter(query, "query");
        assertNotMainThread();
        assertNotSuspendingTransaction();
        if (cancellationSignal != null) {
            return getOpenHelper().getWritableDatabase().query(query, cancellationSignal);
        }
        return getOpenHelper().getWritableDatabase().query(query);
    }

    public SupportSQLiteStatement compileStatement(String sql) {
        Intrinsics.checkNotNullParameter(sql, "sql");
        assertNotMainThread();
        assertNotSuspendingTransaction();
        return getOpenHelper().getWritableDatabase().compileStatement(sql);
    }

    @Deprecated
    public void beginTransaction() {
        assertNotMainThread();
        AutoCloser autoCloser = this.autoCloser;
        if (autoCloser == null) {
            internalBeginTransaction();
        } else {
            autoCloser.executeRefCountingFunction(new Function1() { // from class: androidx.room.RoomDatabase.beginTransaction.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(SupportSQLiteDatabase it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    RoomDatabase.this.internalBeginTransaction();
                    return null;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void internalBeginTransaction() {
        assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = getOpenHelper().getWritableDatabase();
        getInvalidationTracker().syncTriggers$room_runtime_release(writableDatabase);
        if (writableDatabase.isWriteAheadLoggingEnabled()) {
            writableDatabase.beginTransactionNonExclusive();
        } else {
            writableDatabase.beginTransaction();
        }
    }

    @Deprecated
    public void endTransaction() {
        AutoCloser autoCloser = this.autoCloser;
        if (autoCloser == null) {
            internalEndTransaction();
        } else {
            autoCloser.executeRefCountingFunction(new Function1() { // from class: androidx.room.RoomDatabase.endTransaction.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(SupportSQLiteDatabase it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    RoomDatabase.this.internalEndTransaction();
                    return null;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void internalEndTransaction() {
        getOpenHelper().getWritableDatabase().endTransaction();
        if (inTransaction()) {
            return;
        }
        getInvalidationTracker().refreshVersionsAsync();
    }

    @Deprecated
    public void setTransactionSuccessful() {
        getOpenHelper().getWritableDatabase().setTransactionSuccessful();
    }

    public void runInTransaction(Runnable body) {
        Intrinsics.checkNotNullParameter(body, "body");
        beginTransaction();
        try {
            body.run();
            setTransactionSuccessful();
        } finally {
            endTransaction();
        }
    }

    public <V> V runInTransaction(Callable<V> body) {
        Intrinsics.checkNotNullParameter(body, "body");
        beginTransaction();
        try {
            V vCall = body.call();
            setTransactionSuccessful();
            return vCall;
        } finally {
            endTransaction();
        }
    }

    protected void internalInitInvalidationTracker(SupportSQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        getInvalidationTracker().internalInit$room_runtime_release(db);
    }

    public boolean inTransaction() {
        return getOpenHelper().getWritableDatabase().inTransaction();
    }

    /* JADX INFO: compiled from: RoomDatabase.kt */
    public enum JournalMode {
        AUTOMATIC,
        TRUNCATE,
        WRITE_AHEAD_LOGGING;

        public final JournalMode resolve$room_runtime_release(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (this != AUTOMATIC) {
                return this;
            }
            Object systemService = context.getSystemService("activity");
            ActivityManager activityManager = systemService instanceof ActivityManager ? (ActivityManager) systemService : null;
            if (activityManager != null && !isLowRamDevice(activityManager)) {
                return WRITE_AHEAD_LOGGING;
            }
            return TRUNCATE;
        }

        private final boolean isLowRamDevice(ActivityManager activityManager) {
            return SupportSQLiteCompat$Api19Impl.isLowRamDevice(activityManager);
        }
    }

    /* JADX INFO: compiled from: RoomDatabase.kt */
    public static class Builder {
        private boolean allowDestructiveMigrationOnDowngrade;
        private boolean allowMainThreadQueries;
        private TimeUnit autoCloseTimeUnit;
        private long autoCloseTimeout;
        private List autoMigrationSpecs;
        private final List callbacks;
        private final Context context;
        private String copyFromAssetPath;
        private File copyFromFile;
        private Callable copyFromInputStream;
        private SupportSQLiteOpenHelper.Factory factory;
        private JournalMode journalMode;
        private final Class klass;
        private final MigrationContainer migrationContainer;
        private Set migrationStartAndEndVersions;
        private Set migrationsNotRequiredFrom;
        private Intent multiInstanceInvalidationIntent;
        private final String name;
        private Executor queryExecutor;
        private boolean requireMigration;
        private Executor transactionExecutor;
        private final List typeConverters;

        public Builder(Context context, Class klass, String str) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(klass, "klass");
            this.context = context;
            this.klass = klass;
            this.name = str;
            this.callbacks = new ArrayList();
            this.typeConverters = new ArrayList();
            this.autoMigrationSpecs = new ArrayList();
            this.journalMode = JournalMode.AUTOMATIC;
            this.requireMigration = true;
            this.autoCloseTimeout = -1L;
            this.migrationContainer = new MigrationContainer();
            this.migrationsNotRequiredFrom = new LinkedHashSet();
        }

        public Builder openHelperFactory(SupportSQLiteOpenHelper.Factory factory) {
            this.factory = factory;
            return this;
        }

        public Builder addMigrations(Migration... migrations) {
            Intrinsics.checkNotNullParameter(migrations, "migrations");
            if (this.migrationStartAndEndVersions == null) {
                this.migrationStartAndEndVersions = new HashSet();
            }
            for (Migration migration : migrations) {
                Set set = this.migrationStartAndEndVersions;
                Intrinsics.checkNotNull(set);
                set.add(Integer.valueOf(migration.startVersion));
                Set set2 = this.migrationStartAndEndVersions;
                Intrinsics.checkNotNull(set2);
                set2.add(Integer.valueOf(migration.endVersion));
            }
            this.migrationContainer.addMigrations((Migration[]) Arrays.copyOf(migrations, migrations.length));
            return this;
        }

        public Builder allowMainThreadQueries() {
            this.allowMainThreadQueries = true;
            return this;
        }

        public Builder setQueryExecutor(Executor executor) {
            Intrinsics.checkNotNullParameter(executor, "executor");
            this.queryExecutor = executor;
            return this;
        }

        public Builder fallbackToDestructiveMigration() {
            this.requireMigration = false;
            this.allowDestructiveMigrationOnDowngrade = true;
            return this;
        }

        public Builder addCallback(Callback callback) {
            Intrinsics.checkNotNullParameter(callback, "callback");
            this.callbacks.add(callback);
            return this;
        }

        public RoomDatabase build() {
            Executor executor = this.queryExecutor;
            if (executor == null && this.transactionExecutor == null) {
                Executor iOThreadExecutor = ArchTaskExecutor.getIOThreadExecutor();
                this.transactionExecutor = iOThreadExecutor;
                this.queryExecutor = iOThreadExecutor;
            } else if (executor != null && this.transactionExecutor == null) {
                this.transactionExecutor = executor;
            } else if (executor == null) {
                this.queryExecutor = this.transactionExecutor;
            }
            Set set = this.migrationStartAndEndVersions;
            if (set != null) {
                Intrinsics.checkNotNull(set);
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    int iIntValue = ((Number) it.next()).intValue();
                    if (this.migrationsNotRequiredFrom.contains(Integer.valueOf(iIntValue))) {
                        throw new IllegalArgumentException(("Inconsistency detected. A Migration was supplied to addMigration(Migration... migrations) that has a start or end version equal to a start version supplied to fallbackToDestructiveMigrationFrom(int... startVersions). Start version: " + iIntValue).toString());
                    }
                }
            }
            SupportSQLiteOpenHelper.Factory sQLiteCopyOpenHelperFactory = this.factory;
            if (sQLiteCopyOpenHelperFactory == null) {
                sQLiteCopyOpenHelperFactory = new FrameworkSQLiteOpenHelperFactory();
            }
            if (sQLiteCopyOpenHelperFactory != null) {
                if (this.autoCloseTimeout > 0) {
                    if (this.name == null) {
                        throw new IllegalArgumentException("Cannot create auto-closing database for an in-memory database.");
                    }
                    long j = this.autoCloseTimeout;
                    TimeUnit timeUnit = this.autoCloseTimeUnit;
                    if (timeUnit == null) {
                        throw new IllegalArgumentException("Required value was null.");
                    }
                    Executor executor2 = this.queryExecutor;
                    if (executor2 == null) {
                        throw new IllegalArgumentException("Required value was null.");
                    }
                    sQLiteCopyOpenHelperFactory = new AutoClosingRoomOpenHelperFactory(sQLiteCopyOpenHelperFactory, new AutoCloser(j, timeUnit, executor2));
                }
                String str = this.copyFromAssetPath;
                if (str != null || this.copyFromFile != null || this.copyFromInputStream != null) {
                    if (this.name == null) {
                        throw new IllegalArgumentException("Cannot create from asset or file for an in-memory database.");
                    }
                    int i = str == null ? 0 : 1;
                    File file = this.copyFromFile;
                    int i2 = file == null ? 0 : 1;
                    Callable callable = this.copyFromInputStream;
                    if (i + i2 + (callable != null ? 1 : 0) != 1) {
                        throw new IllegalArgumentException("More than one of createFromAsset(), createFromInputStream(), and createFromFile() were called on this Builder, but the database can only be created using one of the three configurations.");
                    }
                    sQLiteCopyOpenHelperFactory = new SQLiteCopyOpenHelperFactory(str, file, callable, sQLiteCopyOpenHelperFactory);
                }
            } else {
                sQLiteCopyOpenHelperFactory = null;
            }
            SupportSQLiteOpenHelper.Factory factory = sQLiteCopyOpenHelperFactory;
            if (factory == null) {
                throw new IllegalArgumentException("Required value was null.");
            }
            Context context = this.context;
            String str2 = this.name;
            MigrationContainer migrationContainer = this.migrationContainer;
            List list = this.callbacks;
            boolean z = this.allowMainThreadQueries;
            JournalMode journalModeResolve$room_runtime_release = this.journalMode.resolve$room_runtime_release(context);
            Executor executor3 = this.queryExecutor;
            if (executor3 == null) {
                throw new IllegalArgumentException("Required value was null.");
            }
            Executor executor4 = this.transactionExecutor;
            if (executor4 != null) {
                DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration(context, str2, factory, migrationContainer, list, z, journalModeResolve$room_runtime_release, executor3, executor4, this.multiInstanceInvalidationIntent, this.requireMigration, this.allowDestructiveMigrationOnDowngrade, this.migrationsNotRequiredFrom, this.copyFromAssetPath, this.copyFromFile, this.copyFromInputStream, null, this.typeConverters, this.autoMigrationSpecs);
                RoomDatabase roomDatabase = (RoomDatabase) Room.getGeneratedImplementation(this.klass, "_Impl");
                roomDatabase.init(databaseConfiguration);
                return roomDatabase;
            }
            throw new IllegalArgumentException("Required value was null.");
        }
    }

    /* JADX INFO: compiled from: RoomDatabase.kt */
    public static class MigrationContainer {
        private final Map migrations = new LinkedHashMap();

        private final void addMigration(Migration migration) {
            int i = migration.startVersion;
            int i2 = migration.endVersion;
            Map map = this.migrations;
            Integer numValueOf = Integer.valueOf(i);
            Object treeMap = map.get(numValueOf);
            if (treeMap == null) {
                treeMap = new TreeMap();
                map.put(numValueOf, treeMap);
            }
            TreeMap treeMap2 = (TreeMap) treeMap;
            if (treeMap2.containsKey(Integer.valueOf(i2))) {
                Log.w("ROOM", "Overriding migration " + treeMap2.get(Integer.valueOf(i2)) + " with " + migration);
            }
            treeMap2.put(Integer.valueOf(i2), migration);
        }

        public Map getMigrations() {
            return this.migrations;
        }

        public List findMigrationPath(int i, int i2) {
            if (i == i2) {
                return CollectionsKt.emptyList();
            }
            return findUpMigrationPath(new ArrayList(), i2 > i, i, i2);
        }

        /* JADX WARN: Removed duplicated region for block: B:31:0x0016 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:9:0x0017  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final java.util.List findUpMigrationPath(java.util.List r7, boolean r8, int r9, int r10) {
            /*
                r6 = this;
            L0:
                if (r8 == 0) goto L5
                if (r9 >= r10) goto L63
                goto L7
            L5:
                if (r9 <= r10) goto L63
            L7:
                java.util.Map r0 = r6.migrations
                java.lang.Integer r1 = java.lang.Integer.valueOf(r9)
                java.lang.Object r0 = r0.get(r1)
                java.util.TreeMap r0 = (java.util.TreeMap) r0
                r1 = 0
                if (r0 != 0) goto L17
                return r1
            L17:
                if (r8 == 0) goto L1e
                java.util.NavigableSet r2 = r0.descendingKeySet()
                goto L22
            L1e:
                java.util.Set r2 = r0.keySet()
            L22:
                java.util.Iterator r2 = r2.iterator()
            L26:
                boolean r3 = r2.hasNext()
                if (r3 == 0) goto L5f
                java.lang.Object r3 = r2.next()
                java.lang.Integer r3 = (java.lang.Integer) r3
                java.lang.String r4 = "targetVersion"
                if (r8 == 0) goto L44
                int r5 = r9 + 1
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
                int r4 = r3.intValue()
                if (r5 > r4) goto L26
                if (r4 > r10) goto L26
                goto L4f
            L44:
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
                int r4 = r3.intValue()
                if (r10 > r4) goto L26
                if (r4 >= r9) goto L26
            L4f:
                java.lang.Object r9 = r0.get(r3)
                kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
                r7.add(r9)
                int r9 = r3.intValue()
                r0 = 1
                goto L60
            L5f:
                r0 = 0
            L60:
                if (r0 != 0) goto L0
                return r1
            L63:
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomDatabase.MigrationContainer.findUpMigrationPath(java.util.List, boolean, int, int):java.util.List");
        }

        public final boolean contains(int i, int i2) {
            Map migrations = getMigrations();
            if (!migrations.containsKey(Integer.valueOf(i))) {
                return false;
            }
            Map mapEmptyMap = (Map) migrations.get(Integer.valueOf(i));
            if (mapEmptyMap == null) {
                mapEmptyMap = MapsKt.emptyMap();
            }
            return mapEmptyMap.containsKey(Integer.valueOf(i2));
        }

        public void addMigrations(Migration... migrations) {
            Intrinsics.checkNotNullParameter(migrations, "migrations");
            for (Migration migration : migrations) {
                addMigration(migration);
            }
        }
    }

    /* JADX INFO: compiled from: RoomDatabase.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
