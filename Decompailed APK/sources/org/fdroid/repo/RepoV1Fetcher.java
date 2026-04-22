package org.fdroid.repo;

import android.net.Uri;
import androidx.core.os.LocaleListCompat;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.SerializationException;
import org.fdroid.database.Repository;
import org.fdroid.download.DownloaderFactory;
import org.fdroid.fdroid.nearby.peers.BonjourPeer;
import org.fdroid.index.IndexConverter;
import org.fdroid.index.IndexFormatVersion;
import org.fdroid.index.IndexParser;
import org.fdroid.index.IndexParserKt;
import org.fdroid.index.RepoUriBuilder;
import org.fdroid.index.SigningException;
import org.fdroid.index.TempFileProvider;
import org.fdroid.index.v1.IndexV1;
import org.fdroid.index.v1.IndexV1UpdaterKt;
import org.fdroid.index.v1.IndexV1Verifier;
import org.fdroid.index.v2.FileV2;
import org.fdroid.index.v2.IndexV2;
import org.fdroid.index.v2.PackageV2;

/* JADX INFO: compiled from: RepoV1Fetcher.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ0\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0096@¢\u0006\u0002\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/fdroid/repo/RepoV1Fetcher;", "Lorg/fdroid/repo/RepoFetcher;", "tempFileProvider", "Lorg/fdroid/index/TempFileProvider;", "downloaderFactory", "Lorg/fdroid/download/DownloaderFactory;", "repoUriBuilder", "Lorg/fdroid/index/RepoUriBuilder;", "<init>", "(Lorg/fdroid/index/TempFileProvider;Lorg/fdroid/download/DownloaderFactory;Lorg/fdroid/index/RepoUriBuilder;)V", "locales", "Landroidx/core/os/LocaleListCompat;", "fetchRepo", "", "uri", "Landroid/net/Uri;", "repo", "Lorg/fdroid/database/Repository;", "receiver", "Lorg/fdroid/repo/RepoPreviewReceiver;", BonjourPeer.FINGERPRINT, "", "(Landroid/net/Uri;Lorg/fdroid/database/Repository;Lorg/fdroid/repo/RepoPreviewReceiver;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class RepoV1Fetcher implements RepoFetcher {
    private final DownloaderFactory downloaderFactory;
    private final LocaleListCompat locales;
    private final RepoUriBuilder repoUriBuilder;
    private final TempFileProvider tempFileProvider;

    public RepoV1Fetcher(TempFileProvider tempFileProvider, DownloaderFactory downloaderFactory, RepoUriBuilder repoUriBuilder) {
        Intrinsics.checkNotNullParameter(tempFileProvider, "tempFileProvider");
        Intrinsics.checkNotNullParameter(downloaderFactory, "downloaderFactory");
        Intrinsics.checkNotNullParameter(repoUriBuilder, "repoUriBuilder");
        this.tempFileProvider = tempFileProvider;
        this.downloaderFactory = downloaderFactory;
        this.repoUriBuilder = repoUriBuilder;
        LocaleListCompat localeListCompat = LocaleListCompat.getDefault();
        Intrinsics.checkNotNullExpressionValue(localeListCompat, "getDefault(...)");
        this.locales = localeListCompat;
    }

    @Override // org.fdroid.repo.RepoFetcher
    public Object fetchRepo(Uri uri, Repository repository, RepoPreviewReceiver repoPreviewReceiver, String str, Continuation continuation) throws SerializationException, IOException, SigningException {
        File fileCreateTempFile = this.tempFileProvider.createTempFile();
        try {
            this.downloaderFactory.create(repository, this.repoUriBuilder.getUri(repository, IndexV1UpdaterKt.SIGNED_FILE_NAME), FileV2.INSTANCE.fromPath("/index-v1.jar"), fileCreateTempFile).download();
            String str2 = null;
            byte b = 0;
            Pair streamAndVerify = new IndexV1Verifier(fileCreateTempFile, null, str).getStreamAndVerify(new Function1() { // from class: org.fdroid.repo.RepoV1Fetcher$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return RepoV1Fetcher.fetchRepo$lambda$0((InputStream) obj);
                }
            });
            fileCreateTempFile.delete();
            String str3 = (String) streamAndVerify.component1();
            IndexV1 indexV1 = (IndexV1) streamAndVerify.component2();
            int version = indexV1.getRepo().getVersion();
            IndexV2 indexV2 = new IndexConverter(str2, 1, b == true ? 1 : 0).toIndexV2(indexV1);
            repoPreviewReceiver.onRepoReceived(RepoV2StreamReceiver.INSTANCE.getRepository(indexV2.getRepo(), version, IndexFormatVersion.ONE, str3, repository.getUsername(), repository.getPassword()));
            for (Map.Entry<String, PackageV2> entry : indexV2.getPackages().entrySet()) {
                repoPreviewReceiver.onAppReceived(RepoV2StreamReceiver.INSTANCE.getAppOverViewItem(entry.getKey(), entry.getValue(), this.locales));
            }
            return Unit.INSTANCE;
        } catch (Throwable th) {
            fileCreateTempFile.delete();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IndexV1 fetchRepo$lambda$0(InputStream inputStream) {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        return IndexParserKt.parseV1(IndexParser.INSTANCE, inputStream);
    }
}
