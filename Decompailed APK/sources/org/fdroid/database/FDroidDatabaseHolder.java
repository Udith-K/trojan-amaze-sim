package org.fdroid.database;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import ch.qos.logback.core.CoreConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: FDroidDatabaseHolder.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0017B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\t2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0007R\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\u0004\u0018\u00010\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0018"}, d2 = {"Lorg/fdroid/database/FDroidDatabaseHolder;", "", "<init>", "()V", "instance", "Lorg/fdroid/database/FDroidDatabaseInt;", "lock", "Ljava/lang/Object;", "TAG", "", "getTAG$database_release", "()Ljava/lang/String;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "getDispatcher$database_release", "()Lkotlinx/coroutines/CoroutineDispatcher;", "getDb", "Lorg/fdroid/database/FDroidDatabase;", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "name", "fixture", "Lorg/fdroid/database/FDroidFixture;", "FixtureCallback", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class FDroidDatabaseHolder {
    private static volatile FDroidDatabaseInt instance;
    public static final FDroidDatabaseHolder INSTANCE = new FDroidDatabaseHolder();
    private static final Object lock = new Object();
    private static final String TAG = Reflection.getOrCreateKotlinClass(FDroidDatabase.class).getSimpleName();

    public static final FDroidDatabase getDb(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getDb$default(context, null, null, 6, null);
    }

    public static final FDroidDatabase getDb(Context context, String name) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(name, "name");
        return getDb$default(context, name, null, 4, null);
    }

    private FDroidDatabaseHolder() {
    }

    public final String getTAG$database_release() {
        return TAG;
    }

    public final CoroutineDispatcher getDispatcher$database_release() {
        return Dispatchers.getIO();
    }

    public static /* synthetic */ FDroidDatabase getDb$default(Context context, String str, FDroidFixture fDroidFixture, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "fdroid_db";
        }
        if ((i & 4) != 0) {
            fDroidFixture = null;
        }
        return getDb(context, str, fDroidFixture);
    }

    public static final FDroidDatabase getDb(Context context, String name, FDroidFixture fixture) {
        FDroidDatabaseInt fDroidDatabaseInt;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(name, "name");
        FDroidDatabaseInt fDroidDatabaseInt2 = instance;
        if (fDroidDatabaseInt2 != null) {
            return fDroidDatabaseInt2;
        }
        synchronized (lock) {
            try {
                Context applicationContext = context.getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                RoomDatabase.Builder builderDatabaseBuilder = Room.databaseBuilder(applicationContext, FDroidDatabaseInt.class, name);
                builderDatabaseBuilder.addMigrations(MigrationsKt.getMIGRATION_2_3(), MigrationsKt.getMIGRATION_5_6());
                builderDatabaseBuilder.fallbackToDestructiveMigration();
                if (fixture != null) {
                    builderDatabaseBuilder.addCallback(new FixtureCallback(fixture));
                }
                fDroidDatabaseInt = (FDroidDatabaseInt) builderDatabaseBuilder.build();
                instance = fDroidDatabaseInt;
            } catch (Throwable th) {
                throw th;
            }
        }
        return fDroidDatabaseInt;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: FDroidDatabaseHolder.kt */
    static final class FixtureCallback extends RoomDatabase.Callback {
        private final FDroidFixture fixture;

        public FixtureCallback(FDroidFixture fixture) {
            Intrinsics.checkNotNullParameter(fixture, "fixture");
            this.fixture = fixture;
        }

        @Override // androidx.room.RoomDatabase.Callback
        public void onCreate(SupportSQLiteDatabase db) {
            Intrinsics.checkNotNullParameter(db, "db");
            super.onCreate(db);
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, FDroidDatabaseHolder.INSTANCE.getDispatcher$database_release(), null, new FDroidDatabaseHolder$FixtureCallback$onCreate$1(this, null), 2, null);
        }

        @Override // androidx.room.RoomDatabase.Callback
        public void onDestructiveMigration(SupportSQLiteDatabase db) {
            Intrinsics.checkNotNullParameter(db, "db");
            onCreate(db);
        }
    }
}
