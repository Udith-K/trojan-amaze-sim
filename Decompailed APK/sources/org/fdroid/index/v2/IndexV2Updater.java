package org.fdroid.index.v2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ConcurrentModificationException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.fdroid.CompatibilityChecker;
import org.fdroid.database.DbV2DiffStreamReceiver;
import org.fdroid.database.DbV2StreamReceiver;
import org.fdroid.database.FDroidDatabase;
import org.fdroid.database.FDroidDatabaseInt;
import org.fdroid.database.Repository;
import org.fdroid.database.RepositoryDaoInt;
import org.fdroid.database.RepositoryPreferences;
import org.fdroid.download.Downloader;
import org.fdroid.download.DownloaderFactory;
import org.fdroid.index.IndexFormatVersion;
import org.fdroid.index.IndexParser;
import org.fdroid.index.IndexParserKt;
import org.fdroid.index.IndexUpdateListener;
import org.fdroid.index.IndexUpdateResult;
import org.fdroid.index.IndexUpdater;
import org.fdroid.index.IndexUpdaterKt;
import org.fdroid.index.RepoUriBuilder;
import org.fdroid.index.TempFileProvider;

/* JADX INFO: compiled from: IndexV2Updater.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0014J$\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u001b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001cH\u0002J(\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lorg/fdroid/index/v2/IndexV2Updater;", "Lorg/fdroid/index/IndexUpdater;", "database", "Lorg/fdroid/database/FDroidDatabase;", "tempFileProvider", "Lorg/fdroid/index/TempFileProvider;", "downloaderFactory", "Lorg/fdroid/download/DownloaderFactory;", "repoUriBuilder", "Lorg/fdroid/index/RepoUriBuilder;", "compatibilityChecker", "Lorg/fdroid/CompatibilityChecker;", "listener", "Lorg/fdroid/index/IndexUpdateListener;", "<init>", "(Lorg/fdroid/database/FDroidDatabase;Lorg/fdroid/index/TempFileProvider;Lorg/fdroid/download/DownloaderFactory;Lorg/fdroid/index/RepoUriBuilder;Lorg/fdroid/CompatibilityChecker;Lorg/fdroid/index/IndexUpdateListener;)V", "formatVersion", "Lorg/fdroid/index/IndexFormatVersion;", "getFormatVersion", "()Lorg/fdroid/index/IndexFormatVersion;", "db", "Lorg/fdroid/database/FDroidDatabaseInt;", "updateRepo", "Lorg/fdroid/index/IndexUpdateResult;", "repo", "Lorg/fdroid/database/Repository;", "getCertAndEntry", "Lkotlin/Pair;", "", "Lorg/fdroid/index/v2/Entry;", "certificate", "processStream", "entryFile", "Lorg/fdroid/index/v2/EntryFileV2;", "repoVersion", "", "streamProcessor", "Lorg/fdroid/index/v2/IndexV2StreamProcessor;", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class IndexV2Updater extends IndexUpdater {
    private final CompatibilityChecker compatibilityChecker;
    private final FDroidDatabaseInt db;
    private final DownloaderFactory downloaderFactory;
    private final IndexFormatVersion formatVersion;
    private final IndexUpdateListener listener;
    private final RepoUriBuilder repoUriBuilder;
    private final TempFileProvider tempFileProvider;

    public /* synthetic */ IndexV2Updater(FDroidDatabase fDroidDatabase, TempFileProvider tempFileProvider, DownloaderFactory downloaderFactory, RepoUriBuilder repoUriBuilder, CompatibilityChecker compatibilityChecker, IndexUpdateListener indexUpdateListener, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fDroidDatabase, tempFileProvider, downloaderFactory, (i & 8) != 0 ? IndexUpdaterKt.getDefaultRepoUriBuilder() : repoUriBuilder, compatibilityChecker, (i & 32) != 0 ? null : indexUpdateListener);
    }

    public IndexV2Updater(FDroidDatabase database, TempFileProvider tempFileProvider, DownloaderFactory downloaderFactory, RepoUriBuilder repoUriBuilder, CompatibilityChecker compatibilityChecker, IndexUpdateListener indexUpdateListener) {
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
        this.formatVersion = IndexFormatVersion.TWO;
        this.db = (FDroidDatabaseInt) database;
    }

    @Override // org.fdroid.index.IndexUpdater
    public IndexFormatVersion getFormatVersion() {
        return this.formatVersion;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.fdroid.index.IndexUpdater
    public IndexUpdateResult updateRepo(Repository repo) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Entry entry = (Entry) getCertAndEntry(repo, repo.getCertificate()).component2();
        if (entry.getTimestamp() <= repo.getTimestamp()) {
            return IndexUpdateResult.Unchanged.INSTANCE;
        }
        EntryFileV2 diff = entry.getDiff(repo.getTimestamp());
        if (diff == null || repo.getFormatVersion() == IndexFormatVersion.ONE) {
            return processStream(repo, entry.getIndex(), entry.getVersion(), new IndexV2FullStreamProcessor(new DbV2StreamReceiver(this.db, repo.getRepoId(), this.compatibilityChecker), null, 2, null));
        }
        return processStream(repo, diff, entry.getVersion(), new IndexV2DiffStreamProcessor(new DbV2DiffStreamReceiver(this.db, repo.getRepoId(), this.compatibilityChecker), null, 2, null));
    }

    private final Pair getCertAndEntry(Repository repo, String certificate) throws IOException {
        File fileCreateTempFile = this.tempFileProvider.createTempFile();
        Downloader downloaderCreateWithTryFirstMirror = this.downloaderFactory.createWithTryFirstMirror(repo, this.repoUriBuilder.getUri(repo, IndexV2UpdaterKt.SIGNED_FILE_NAME), FileV2.INSTANCE.fromPath("/entry.jar"), fileCreateTempFile);
        IndexUpdaterKt.setIndexUpdateListener(downloaderCreateWithTryFirstMirror, this.listener, repo);
        try {
            downloaderCreateWithTryFirstMirror.download();
            return new EntryVerifier(fileCreateTempFile, certificate, null).getStreamAndVerify(new Function1() { // from class: org.fdroid.index.v2.IndexV2Updater$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return IndexV2Updater.getCertAndEntry$lambda$1((InputStream) obj);
                }
            });
        } finally {
            fileCreateTempFile.delete();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Entry getCertAndEntry$lambda$1(InputStream inputStream) {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        return IndexParserKt.parseEntry(IndexParser.INSTANCE, inputStream);
    }

    private final IndexUpdateResult processStream(final Repository repo, final EntryFileV2 entryFile, final long repoVersion, final IndexV2StreamProcessor streamProcessor) throws IOException {
        File fileCreateTempFile = this.tempFileProvider.createTempFile();
        Downloader downloaderCreateWithTryFirstMirror = this.downloaderFactory.createWithTryFirstMirror(repo, this.repoUriBuilder.getUri(repo, StringsKt.trimStart(entryFile.getName(), '/')), entryFile, fileCreateTempFile);
        IndexUpdaterKt.setIndexUpdateListener(downloaderCreateWithTryFirstMirror, this.listener, repo);
        try {
            downloaderCreateWithTryFirstMirror.download();
            final FileInputStream fileInputStream = new FileInputStream(fileCreateTempFile);
            try {
                final RepositoryDaoInt repositoryDao = this.db.getRepositoryDao();
                this.db.runInTransaction(new Runnable() { // from class: org.fdroid.index.v2.IndexV2Updater$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        IndexV2Updater.processStream$lambda$5$lambda$4(repositoryDao, repo, streamProcessor, repoVersion, fileInputStream, this, entryFile);
                    }
                });
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(fileInputStream, null);
                fileCreateTempFile.delete();
                return IndexUpdateResult.Processed.INSTANCE;
            } finally {
            }
        } catch (Throwable th) {
            fileCreateTempFile.delete();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void processStream$lambda$5$lambda$4(RepositoryDaoInt repositoryDaoInt, final Repository repository, IndexV2StreamProcessor indexV2StreamProcessor, long j, FileInputStream fileInputStream, final IndexV2Updater indexV2Updater, final EntryFileV2 entryFileV2) {
        Repository repository2 = repositoryDaoInt.getRepository(repository.getRepoId());
        Long lValueOf = repository2 != null ? Long.valueOf(repository2.getTimestamp()) : null;
        long timestamp = repository.getTimestamp();
        if (lValueOf == null || lValueOf.longValue() != timestamp) {
            throw new ConcurrentModificationException("Repo timestamp expected " + repository.getTimestamp() + ", but was " + lValueOf);
        }
        indexV2StreamProcessor.process(j, fileInputStream, new Function1() { // from class: org.fdroid.index.v2.IndexV2Updater$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return IndexV2Updater.processStream$lambda$5$lambda$4$lambda$3(this.f$0, repository, entryFileV2, ((Integer) obj).intValue());
            }
        });
        RepositoryPreferences repositoryPreferences = repositoryDaoInt.getRepositoryPreferences(repository.getRepoId());
        if (repositoryPreferences == null) {
            throw new IllegalStateException(("No repo prefs for " + repository.getRepoId()).toString());
        }
        repositoryDaoInt.updateRepositoryPreferences(repositoryPreferences.copy((487 & 1) != 0 ? repositoryPreferences.repoId : 0L, (487 & 2) != 0 ? repositoryPreferences.weight : 0, (487 & 4) != 0 ? repositoryPreferences.enabled : false, (487 & 8) != 0 ? repositoryPreferences.lastUpdated : Long.valueOf(System.currentTimeMillis()), (487 & 16) != 0 ? repositoryPreferences.lastETag : null, (487 & 32) != 0 ? repositoryPreferences.userMirrors : null, (487 & 64) != 0 ? repositoryPreferences.disabledMirrors : null, (487 & 128) != 0 ? repositoryPreferences.username : null, (487 & 256) != 0 ? repositoryPreferences.password : null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit processStream$lambda$5$lambda$4$lambda$3(IndexV2Updater indexV2Updater, Repository repository, EntryFileV2 entryFileV2, int i) {
        IndexUpdateListener indexUpdateListener = indexV2Updater.listener;
        if (indexUpdateListener != null) {
            indexUpdateListener.onUpdateProgress(repository, i, entryFileV2.getNumPackages());
        }
        return Unit.INSTANCE;
    }
}
