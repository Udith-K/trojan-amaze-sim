package org.fdroid.database;

import androidx.core.os.LocaleListCompat;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.JsonObject;
import org.fdroid.CompatibilityChecker;
import org.fdroid.index.v2.IndexV2DiffStreamReceiver;
import org.fdroid.index.v2.PackageManifest;

/* JADX INFO: compiled from: DbV2DiffStreamReceiver.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u001a\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0010H\u0016J(\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00132\u0016\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0018\u00010\u0017H\u0016J\b\u0010\u0018\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lorg/fdroid/database/DbV2DiffStreamReceiver;", "Lorg/fdroid/index/v2/IndexV2DiffStreamReceiver;", "db", "Lorg/fdroid/database/FDroidDatabaseInt;", "repoId", "", "compatibilityChecker", "Lorg/fdroid/CompatibilityChecker;", "<init>", "(Lorg/fdroid/database/FDroidDatabaseInt;JLorg/fdroid/CompatibilityChecker;)V", "locales", "Landroidx/core/os/LocaleListCompat;", "receiveRepoDiff", "", "version", "repoJsonObject", "Lkotlinx/serialization/json/JsonObject;", "receivePackageMetadataDiff", "packageName", "", "packageJsonObject", "receiveVersionsDiff", "versionsDiffMap", "", "onStreamEnded", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class DbV2DiffStreamReceiver implements IndexV2DiffStreamReceiver {
    private final CompatibilityChecker compatibilityChecker;
    private final FDroidDatabaseInt db;
    private final LocaleListCompat locales;
    private final long repoId;

    public DbV2DiffStreamReceiver(FDroidDatabaseInt db, long j, CompatibilityChecker compatibilityChecker) {
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(compatibilityChecker, "compatibilityChecker");
        this.db = db;
        this.repoId = j;
        this.compatibilityChecker = compatibilityChecker;
        LocaleListCompat localeListCompat = LocaleListCompat.getDefault();
        Intrinsics.checkNotNullExpressionValue(localeListCompat, "getDefault(...)");
        this.locales = localeListCompat;
    }

    @Override // org.fdroid.index.v2.IndexV2DiffStreamReceiver
    public void receiveRepoDiff(long version, JsonObject repoJsonObject) {
        Intrinsics.checkNotNullParameter(repoJsonObject, "repoJsonObject");
        this.db.getRepositoryDao().updateRepository(this.repoId, version, repoJsonObject);
    }

    @Override // org.fdroid.index.v2.IndexV2DiffStreamReceiver
    public void receivePackageMetadataDiff(String packageName, JsonObject packageJsonObject) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.db.getAppDao().updateApp(this.repoId, packageName, packageJsonObject, this.locales);
    }

    @Override // org.fdroid.index.v2.IndexV2DiffStreamReceiver
    public void receiveVersionsDiff(String packageName, Map<String, JsonObject> versionsDiffMap) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.db.getVersionDao().update(this.repoId, packageName, versionsDiffMap, new Function1() { // from class: org.fdroid.database.DbV2DiffStreamReceiver$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(DbV2DiffStreamReceiver.receiveVersionsDiff$lambda$0(this.f$0, (PackageManifest) obj));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean receiveVersionsDiff$lambda$0(DbV2DiffStreamReceiver dbV2DiffStreamReceiver, PackageManifest it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return dbV2DiffStreamReceiver.compatibilityChecker.isCompatible(it);
    }

    @Override // org.fdroid.index.v2.IndexV2DiffStreamReceiver
    public synchronized void onStreamEnded() {
        this.db.afterUpdatingRepo(this.repoId);
    }
}
