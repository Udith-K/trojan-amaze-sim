package org.fdroid.index;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mu.KLogger;
import mu.KotlinLogging;
import org.fdroid.CompatibilityChecker;
import org.fdroid.database.FDroidDatabase;
import org.fdroid.database.Repository;
import org.fdroid.download.DownloaderFactory;
import org.fdroid.index.IndexUpdateResult;
import org.fdroid.index.v1.IndexV1Updater;
import org.fdroid.index.v2.IndexV2Updater;

/* JADX INFO: compiled from: RepoUpdater.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ$\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00180\u001cH\u0002R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lorg/fdroid/index/RepoUpdater;", "", "tempDir", "Ljava/io/File;", "db", "Lorg/fdroid/database/FDroidDatabase;", "downloaderFactory", "Lorg/fdroid/download/DownloaderFactory;", "repoUriBuilder", "Lorg/fdroid/index/RepoUriBuilder;", "compatibilityChecker", "Lorg/fdroid/CompatibilityChecker;", "listener", "Lorg/fdroid/index/IndexUpdateListener;", "<init>", "(Ljava/io/File;Lorg/fdroid/database/FDroidDatabase;Lorg/fdroid/download/DownloaderFactory;Lorg/fdroid/index/RepoUriBuilder;Lorg/fdroid/CompatibilityChecker;Lorg/fdroid/index/IndexUpdateListener;)V", "log", "Lmu/KLogger;", "tempFileProvider", "Lorg/fdroid/index/TempFileProvider;", "indexUpdater", "", "Lorg/fdroid/index/IndexUpdater;", "update", "Lorg/fdroid/index/IndexUpdateResult;", "repo", "Lorg/fdroid/database/Repository;", "doUpdate", "Lkotlin/Function1;", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class RepoUpdater {
    private final List<IndexUpdater> indexUpdater;
    private final KLogger log;
    private final TempFileProvider tempFileProvider;

    public RepoUpdater(final File tempDir, FDroidDatabase db, DownloaderFactory downloaderFactory, RepoUriBuilder repoUriBuilder, CompatibilityChecker compatibilityChecker, IndexUpdateListener listener) {
        Intrinsics.checkNotNullParameter(tempDir, "tempDir");
        Intrinsics.checkNotNullParameter(db, "db");
        Intrinsics.checkNotNullParameter(downloaderFactory, "downloaderFactory");
        Intrinsics.checkNotNullParameter(repoUriBuilder, "repoUriBuilder");
        Intrinsics.checkNotNullParameter(compatibilityChecker, "compatibilityChecker");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.log = KotlinLogging.INSTANCE.logger(new Function0() { // from class: org.fdroid.index.RepoUpdater$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        });
        TempFileProvider tempFileProvider = new TempFileProvider() { // from class: org.fdroid.index.RepoUpdater$$ExternalSyntheticLambda3
            @Override // org.fdroid.index.TempFileProvider
            public final File createTempFile() {
                return RepoUpdater.tempFileProvider$lambda$1(tempDir);
            }
        };
        this.tempFileProvider = tempFileProvider;
        this.indexUpdater = CollectionsKt.listOf((Object[]) new IndexUpdater[]{new IndexV2Updater(db, tempFileProvider, downloaderFactory, repoUriBuilder, compatibilityChecker, listener), new IndexV1Updater(db, tempFileProvider, downloaderFactory, repoUriBuilder, compatibilityChecker, listener)});
    }

    public /* synthetic */ RepoUpdater(File file, FDroidDatabase fDroidDatabase, DownloaderFactory downloaderFactory, RepoUriBuilder repoUriBuilder, CompatibilityChecker compatibilityChecker, IndexUpdateListener indexUpdateListener, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(file, fDroidDatabase, downloaderFactory, (i & 8) != 0 ? IndexUpdaterKt.getDefaultRepoUriBuilder() : repoUriBuilder, compatibilityChecker, indexUpdateListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final File tempFileProvider$lambda$1(File file) throws IOException {
        File fileCreateTempFile = File.createTempFile("dl-", "", file);
        Intrinsics.checkNotNullExpressionValue(fileCreateTempFile, "createTempFile(...)");
        return fileCreateTempFile;
    }

    public final IndexUpdateResult update(final Repository repo) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        return update(repo, new Function1() { // from class: org.fdroid.index.RepoUpdater$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return RepoUpdater.update$lambda$2(repo, (IndexUpdater) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IndexUpdateResult update$lambda$2(Repository repository, IndexUpdater updater) {
        Intrinsics.checkNotNullParameter(updater, "updater");
        return updater.update(repository);
    }

    private final IndexUpdateResult update(final Repository repo, Function1 doUpdate) {
        for (IndexUpdater indexUpdater : this.indexUpdater) {
            IndexFormatVersion formatVersion = repo.getFormatVersion();
            if (formatVersion != null && formatVersion.compareTo(indexUpdater.getFormatVersion()) > 0) {
                final String strName = indexUpdater.getFormatVersion().name();
                this.log.warn(new Function0() { // from class: org.fdroid.index.RepoUpdater$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return RepoUpdater.update$lambda$4$lambda$3(strName, repo);
                    }
                });
            } else {
                IndexUpdateResult indexUpdateResult = (IndexUpdateResult) doUpdate.invoke(indexUpdater);
                if (!Intrinsics.areEqual(indexUpdateResult, IndexUpdateResult.NotFound.INSTANCE)) {
                    return indexUpdateResult;
                }
            }
        }
        return new IndexUpdateResult.Error(new FileNotFoundException("No files found for " + repo.getAddress()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object update$lambda$4$lambda$3(String str, Repository repository) {
        return "Not using updater " + str + " for repo " + repository.getAddress();
    }
}
