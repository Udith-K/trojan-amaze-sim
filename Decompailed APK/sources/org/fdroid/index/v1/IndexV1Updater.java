package org.fdroid.index.v1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mu.KLogger;
import mu.KotlinLogging;
import org.fdroid.CompatibilityChecker;
import org.fdroid.database.DbV1StreamReceiver;
import org.fdroid.database.FDroidDatabase;
import org.fdroid.database.FDroidDatabaseInt;
import org.fdroid.database.Repository;
import org.fdroid.database.RepositoryDaoInt;
import org.fdroid.database.RepositoryPreferences;
import org.fdroid.download.Downloader;
import org.fdroid.download.DownloaderFactory;
import org.fdroid.index.IndexFormatVersion;
import org.fdroid.index.IndexUpdateListener;
import org.fdroid.index.IndexUpdateResult;
import org.fdroid.index.IndexUpdater;
import org.fdroid.index.IndexUpdaterKt;
import org.fdroid.index.RepoUriBuilder;
import org.fdroid.index.SigningException;
import org.fdroid.index.TempFileProvider;
import org.fdroid.index.v2.FileV2;

/* JADX INFO: compiled from: IndexV1Updater.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u00020\u0013X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lorg/fdroid/index/v1/IndexV1Updater;", "Lorg/fdroid/index/IndexUpdater;", "database", "Lorg/fdroid/database/FDroidDatabase;", "tempFileProvider", "Lorg/fdroid/index/TempFileProvider;", "downloaderFactory", "Lorg/fdroid/download/DownloaderFactory;", "repoUriBuilder", "Lorg/fdroid/index/RepoUriBuilder;", "compatibilityChecker", "Lorg/fdroid/CompatibilityChecker;", "listener", "Lorg/fdroid/index/IndexUpdateListener;", "<init>", "(Lorg/fdroid/database/FDroidDatabase;Lorg/fdroid/index/TempFileProvider;Lorg/fdroid/download/DownloaderFactory;Lorg/fdroid/index/RepoUriBuilder;Lorg/fdroid/CompatibilityChecker;Lorg/fdroid/index/IndexUpdateListener;)V", "log", "Lmu/KLogger;", "formatVersion", "Lorg/fdroid/index/IndexFormatVersion;", "getFormatVersion", "()Lorg/fdroid/index/IndexFormatVersion;", "db", "Lorg/fdroid/database/FDroidDatabaseInt;", "updateRepo", "Lorg/fdroid/index/IndexUpdateResult;", "repo", "Lorg/fdroid/database/Repository;", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class IndexV1Updater extends IndexUpdater {
    private final CompatibilityChecker compatibilityChecker;
    private final FDroidDatabaseInt db;
    private final DownloaderFactory downloaderFactory;
    private final IndexFormatVersion formatVersion;
    private final IndexUpdateListener listener;
    private final KLogger log;
    private final RepoUriBuilder repoUriBuilder;
    private final TempFileProvider tempFileProvider;

    public /* synthetic */ IndexV1Updater(FDroidDatabase fDroidDatabase, TempFileProvider tempFileProvider, DownloaderFactory downloaderFactory, RepoUriBuilder repoUriBuilder, CompatibilityChecker compatibilityChecker, IndexUpdateListener indexUpdateListener, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fDroidDatabase, tempFileProvider, downloaderFactory, (i & 8) != 0 ? IndexUpdaterKt.getDefaultRepoUriBuilder() : repoUriBuilder, compatibilityChecker, (i & 32) != 0 ? null : indexUpdateListener);
    }

    public IndexV1Updater(FDroidDatabase database, TempFileProvider tempFileProvider, DownloaderFactory downloaderFactory, RepoUriBuilder repoUriBuilder, CompatibilityChecker compatibilityChecker, IndexUpdateListener indexUpdateListener) {
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(tempFileProvider, "tempFileProvider");
        Intrinsics.checkNotNullParameter(downloaderFactory, "downloaderFactory");
        Intrinsics.checkNotNullParameter(repoUriBuilder, "repoUriBuilder");
        Intrinsics.checkNotNullParameter(compatibilityChecker, "compatibilityChecker");
        this.tempFileProvider = tempFileProvider;
        this.downloaderFactory = downloaderFactory;
        this.repoUriBuilder = repoUriBuilder;
        this.compatibilityChecker = compatibilityChecker;
        this.listener = indexUpdateListener;
        this.log = KotlinLogging.INSTANCE.logger(new Function0() { // from class: org.fdroid.index.v1.IndexV1Updater$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        });
        this.formatVersion = IndexFormatVersion.ONE;
        this.db = (FDroidDatabaseInt) database;
    }

    @Override // org.fdroid.index.IndexUpdater
    public IndexFormatVersion getFormatVersion() {
        return this.formatVersion;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.fdroid.index.IndexUpdater
    public IndexUpdateResult updateRepo(final Repository repo) throws IOException {
        Intrinsics.checkNotNullParameter(repo, "repo");
        if (repo.getFormatVersion() != null && repo.getFormatVersion() != IndexFormatVersion.ONE) {
            this.log.error(new Function0() { // from class: org.fdroid.index.v1.IndexV1Updater$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return IndexV1Updater.updateRepo$lambda$1(repo);
                }
            });
        }
        File fileCreateTempFile = this.tempFileProvider.createTempFile();
        Downloader downloaderCreateWithTryFirstMirror = this.downloaderFactory.createWithTryFirstMirror(repo, this.repoUriBuilder.getUri(repo, IndexV1UpdaterKt.SIGNED_FILE_NAME), FileV2.INSTANCE.fromPath("/index-v1.jar"), fileCreateTempFile);
        downloaderCreateWithTryFirstMirror.setCacheTag(repo.getLastETag());
        IndexUpdaterKt.setIndexUpdateListener(downloaderCreateWithTryFirstMirror, this.listener, repo);
        try {
            downloaderCreateWithTryFirstMirror.download();
            if (!downloaderCreateWithTryFirstMirror.getHasChanged()) {
                return IndexUpdateResult.Unchanged.INSTANCE;
            }
            final String cacheTag = downloaderCreateWithTryFirstMirror.getCacheTag();
            final IndexV1Verifier indexV1Verifier = new IndexV1Verifier(fileCreateTempFile, repo.getCertificate(), null);
            this.db.runInTransaction(new Runnable() { // from class: org.fdroid.index.v1.IndexV1Updater$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() throws IOException, SigningException {
                    IndexV1Updater.updateRepo$lambda$4(indexV1Verifier, this, repo, cacheTag);
                }
            });
            fileCreateTempFile.delete();
            return IndexUpdateResult.Processed.INSTANCE;
        } catch (OldIndexException e) {
            if (e.getIsSameTimestamp()) {
                return IndexUpdateResult.Unchanged.INSTANCE;
            }
            throw e;
        } finally {
            fileCreateTempFile.delete();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object updateRepo$lambda$1(Repository repository) {
        return "Format downgrade for " + repository.getAddress();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateRepo$lambda$4(IndexV1Verifier indexV1Verifier, final IndexV1Updater indexV1Updater, final Repository repository, String str) throws IOException, SigningException {
        indexV1Verifier.getStreamAndVerify(new Function1() { // from class: org.fdroid.index.v1.IndexV1Updater$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return IndexV1Updater.updateRepo$lambda$4$lambda$3(this.f$0, repository, (InputStream) obj);
            }
        });
        RepositoryDaoInt repositoryDao = indexV1Updater.db.getRepositoryDao();
        RepositoryPreferences preferences$database_release = repository.getPreferences$database_release();
        repositoryDao.updateRepositoryPreferences(preferences$database_release.copy((487 & 1) != 0 ? preferences$database_release.repoId : 0L, (487 & 2) != 0 ? preferences$database_release.weight : 0, (487 & 4) != 0 ? preferences$database_release.enabled : false, (487 & 8) != 0 ? preferences$database_release.lastUpdated : Long.valueOf(System.currentTimeMillis()), (487 & 16) != 0 ? preferences$database_release.lastETag : str, (487 & 32) != 0 ? preferences$database_release.userMirrors : null, (487 & 64) != 0 ? preferences$database_release.disabledMirrors : null, (487 & 128) != 0 ? preferences$database_release.username : null, (487 & 256) != 0 ? preferences$database_release.password : null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit updateRepo$lambda$4$lambda$3(IndexV1Updater indexV1Updater, Repository repository, InputStream inputStream) throws OldIndexException {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        IndexUpdateListener indexUpdateListener = indexV1Updater.listener;
        if (indexUpdateListener != null) {
            indexUpdateListener.onUpdateProgress(repository, 0, 0);
        }
        new IndexV1StreamProcessor(new DbV1StreamReceiver(indexV1Updater.db, repository.getRepoId(), indexV1Updater.compatibilityChecker), repository.getTimestamp(), null, null, 12, null).process(inputStream);
        return Unit.INSTANCE;
    }
}
