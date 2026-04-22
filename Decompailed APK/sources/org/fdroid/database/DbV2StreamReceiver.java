package org.fdroid.database;

import androidx.core.os.LocaleListCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.SerializationException;
import org.fdroid.CompatibilityChecker;
import org.fdroid.index.IndexFormatVersion;
import org.fdroid.index.v2.FileV2;
import org.fdroid.index.v2.IndexV2StreamReceiver;
import org.fdroid.index.v2.PackageV2;
import org.fdroid.index.v2.PackageVersionV2;
import org.fdroid.index.v2.RepoV2;

/* JADX INFO: compiled from: DbV2StreamReceiver.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0005H\u0016J\u0018\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0011H\u0016J\b\u0010\u001b\u001a\u00020\u0011H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0004\u0012\u00020\u00110\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lorg/fdroid/database/DbV2StreamReceiver;", "Lorg/fdroid/index/v2/IndexV2StreamReceiver;", "db", "Lorg/fdroid/database/FDroidDatabaseInt;", "repoId", "", "compatibilityChecker", "Lorg/fdroid/CompatibilityChecker;", "<init>", "(Lorg/fdroid/database/FDroidDatabaseInt;JLorg/fdroid/CompatibilityChecker;)V", "locales", "Landroidx/core/os/LocaleListCompat;", "clearedRepoData", "", "nonNullFileV2", "Lkotlin/Function1;", "Lorg/fdroid/index/v2/FileV2;", "", "receive", "repo", "Lorg/fdroid/index/v2/RepoV2;", "version", "packageName", "", "p", "Lorg/fdroid/index/v2/PackageV2;", "onStreamEnded", "clearRepoDataIfNeeded", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class DbV2StreamReceiver implements IndexV2StreamReceiver {
    private boolean clearedRepoData;
    private final CompatibilityChecker compatibilityChecker;
    private final FDroidDatabaseInt db;
    private final LocaleListCompat locales;
    private final Function1 nonNullFileV2;
    private final long repoId;

    public DbV2StreamReceiver(FDroidDatabaseInt db, long j, CompatibilityChecker compatibilityChecker) {
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(compatibilityChecker, "compatibilityChecker");
        this.db = db;
        this.repoId = j;
        this.compatibilityChecker = compatibilityChecker;
        LocaleListCompat localeListCompat = LocaleListCompat.getDefault();
        Intrinsics.checkNotNullExpressionValue(localeListCompat, "getDefault(...)");
        this.locales = localeListCompat;
        this.nonNullFileV2 = new Function1() { // from class: org.fdroid.database.DbV2StreamReceiver$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DbV2StreamReceiver.nonNullFileV2$lambda$0((FileV2) obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit nonNullFileV2$lambda$0(FileV2 fileV2) {
        if (fileV2 != null) {
            if (fileV2.getSha256() == null) {
                throw new SerializationException(fileV2.getName() + " has no sha256");
            }
            if (fileV2.getSize() == null) {
                throw new SerializationException(fileV2.getName() + " has no size");
            }
            if (!StringsKt.startsWith$default((CharSequence) fileV2.getName(), '/', false, 2, (Object) null)) {
                throw new SerializationException(fileV2.getName() + " does not start with /");
            }
        }
        return Unit.INSTANCE;
    }

    @Override // org.fdroid.index.v2.IndexV2StreamReceiver
    public synchronized void receive(RepoV2 repo, long version) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        repo.walkFiles(this.nonNullFileV2);
        clearRepoDataIfNeeded();
        this.db.getRepositoryDao().update(this.repoId, repo, version, IndexFormatVersion.TWO);
    }

    @Override // org.fdroid.index.v2.IndexV2StreamReceiver
    public synchronized void receive(String packageName, PackageV2 p) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(p, "p");
        p.walkFiles(this.nonNullFileV2);
        clearRepoDataIfNeeded();
        this.db.getAppDao().insert(this.repoId, packageName, p.getMetadata(), this.locales);
        this.db.getVersionDao().insert(this.repoId, packageName, p.getVersions(), new Function1() { // from class: org.fdroid.database.DbV2StreamReceiver$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(DbV2StreamReceiver.receive$lambda$1(this.f$0, (PackageVersionV2) obj));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean receive$lambda$1(DbV2StreamReceiver dbV2StreamReceiver, PackageVersionV2 it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return dbV2StreamReceiver.compatibilityChecker.isCompatible(it.getManifest());
    }

    @Override // org.fdroid.index.v2.IndexV2StreamReceiver
    public synchronized void onStreamEnded() {
        this.db.afterUpdatingRepo(this.repoId);
    }

    private final void clearRepoDataIfNeeded() {
        if (this.clearedRepoData) {
            return;
        }
        this.db.getRepositoryDao().clear(this.repoId);
        this.clearedRepoData = true;
    }
}
