package org.fdroid.repo;

import java.io.InputStream;
import java.net.Proxy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mu.KLogger;
import mu.KotlinLogging;
import org.fdroid.download.DownloaderFactory;
import org.fdroid.download.HttpManager;
import org.fdroid.fdroid.nearby.peers.BonjourPeer;
import org.fdroid.index.IndexParser;
import org.fdroid.index.IndexParserKt;
import org.fdroid.index.RepoUriBuilder;
import org.fdroid.index.TempFileProvider;
import org.fdroid.index.v2.Entry;

/* JADX INFO: compiled from: RepoV2Fetcher.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\f\u0010\rJ0\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0096@¢\u0006\u0002\u0010\u001aR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/fdroid/repo/RepoV2Fetcher;", "Lorg/fdroid/repo/RepoFetcher;", "tempFileProvider", "Lorg/fdroid/index/TempFileProvider;", "downloaderFactory", "Lorg/fdroid/download/DownloaderFactory;", "httpManager", "Lorg/fdroid/download/HttpManager;", "repoUriBuilder", "Lorg/fdroid/index/RepoUriBuilder;", "proxy", "Ljava/net/Proxy;", "<init>", "(Lorg/fdroid/index/TempFileProvider;Lorg/fdroid/download/DownloaderFactory;Lorg/fdroid/download/HttpManager;Lorg/fdroid/index/RepoUriBuilder;Ljava/net/Proxy;)V", "log", "Lmu/KLogger;", "fetchRepo", "", "uri", "Landroid/net/Uri;", "repo", "Lorg/fdroid/database/Repository;", "receiver", "Lorg/fdroid/repo/RepoPreviewReceiver;", BonjourPeer.FINGERPRINT, "", "(Landroid/net/Uri;Lorg/fdroid/database/Repository;Lorg/fdroid/repo/RepoPreviewReceiver;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class RepoV2Fetcher implements RepoFetcher {
    private final DownloaderFactory downloaderFactory;
    private final HttpManager httpManager;
    private final KLogger log;
    private final Proxy proxy;
    private final RepoUriBuilder repoUriBuilder;
    private final TempFileProvider tempFileProvider;

    /* JADX INFO: renamed from: org.fdroid.repo.RepoV2Fetcher$fetchRepo$1, reason: invalid class name */
    /* JADX INFO: compiled from: RepoV2Fetcher.kt */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RepoV2Fetcher.this.fetchRepo(null, null, null, null, this);
        }
    }

    public RepoV2Fetcher(TempFileProvider tempFileProvider, DownloaderFactory downloaderFactory, HttpManager httpManager, RepoUriBuilder repoUriBuilder, Proxy proxy) {
        Intrinsics.checkNotNullParameter(tempFileProvider, "tempFileProvider");
        Intrinsics.checkNotNullParameter(downloaderFactory, "downloaderFactory");
        Intrinsics.checkNotNullParameter(httpManager, "httpManager");
        Intrinsics.checkNotNullParameter(repoUriBuilder, "repoUriBuilder");
        this.tempFileProvider = tempFileProvider;
        this.downloaderFactory = downloaderFactory;
        this.httpManager = httpManager;
        this.repoUriBuilder = repoUriBuilder;
        this.proxy = proxy;
        this.log = KotlinLogging.INSTANCE.logger(new Function0() { // from class: org.fdroid.repo.RepoV2Fetcher$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        });
    }

    public /* synthetic */ RepoV2Fetcher(TempFileProvider tempFileProvider, DownloaderFactory downloaderFactory, HttpManager httpManager, RepoUriBuilder repoUriBuilder, Proxy proxy, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(tempFileProvider, downloaderFactory, httpManager, repoUriBuilder, (i & 16) != 0 ? null : proxy);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001b  */
    @Override // org.fdroid.repo.RepoFetcher
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object fetchRepo(android.net.Uri r21, org.fdroid.database.Repository r22, org.fdroid.repo.RepoPreviewReceiver r23, java.lang.String r24, kotlin.coroutines.Continuation r25) throws java.lang.InterruptedException, java.io.IOException, org.fdroid.index.SigningException, org.fdroid.download.NotFoundException {
        /*
            Method dump skipped, instruction units count: 391
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.repo.RepoV2Fetcher.fetchRepo(android.net.Uri, org.fdroid.database.Repository, org.fdroid.repo.RepoPreviewReceiver, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Entry fetchRepo$lambda$1(InputStream inputStream) {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        return IndexParserKt.parseEntry(IndexParser.INSTANCE, inputStream);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object fetchRepo$lambda$2() {
        return "Downloaded entry, now streaming index...";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit fetchRepo$lambda$4$lambda$3(int i) {
        return Unit.INSTANCE;
    }
}
