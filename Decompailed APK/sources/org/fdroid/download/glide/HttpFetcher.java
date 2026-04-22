package org.fdroid.download.glide;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.DataFetcher;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.jvm.javaio.BlockingKt;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import org.fdroid.download.DownloadRequest;
import org.fdroid.download.HttpManager;

/* JADX INFO: compiled from: HttpFetcher.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ \u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\fH\u0016J\b\u0010\u0012\u001a\u00020\fH\u0016J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/fdroid/download/glide/HttpFetcher;", "Lcom/bumptech/glide/load/data/DataFetcher;", "Ljava/io/InputStream;", "httpManager", "Lorg/fdroid/download/HttpManager;", "downloadRequest", "Lorg/fdroid/download/DownloadRequest;", "<init>", "(Lorg/fdroid/download/HttpManager;Lorg/fdroid/download/DownloadRequest;)V", "job", "Lkotlinx/coroutines/Job;", "loadData", "", "priority", "Lcom/bumptech/glide/Priority;", "callback", "Lcom/bumptech/glide/load/data/DataFetcher$DataCallback;", "cleanup", "cancel", "getDataClass", "Ljava/lang/Class;", "getDataSource", "Lcom/bumptech/glide/load/DataSource;", "download_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class HttpFetcher implements DataFetcher {
    private final DownloadRequest downloadRequest;
    private final HttpManager httpManager;
    private Job job;

    public HttpFetcher(HttpManager httpManager, DownloadRequest downloadRequest) {
        Intrinsics.checkNotNullParameter(httpManager, "httpManager");
        Intrinsics.checkNotNullParameter(downloadRequest, "downloadRequest");
        this.httpManager = httpManager;
        this.downloadRequest = downloadRequest;
    }

    /* JADX INFO: renamed from: org.fdroid.download.glide.HttpFetcher$loadData$1, reason: invalid class name */
    /* JADX INFO: compiled from: HttpFetcher.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ DataFetcher.DataCallback $callback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(DataFetcher.DataCallback dataCallback, Continuation continuation) {
            super(2, continuation);
            this.$callback = dataCallback;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return HttpFetcher.this.new AnonymousClass1(this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    HttpManager httpManager = HttpFetcher.this.httpManager;
                    DownloadRequest downloadRequest = HttpFetcher.this.downloadRequest;
                    this.label = 1;
                    obj = HttpManager.getChannel$download_release$default(httpManager, downloadRequest, null, this, 2, null);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                InputStream inputStream$default = BlockingKt.toInputStream$default((ByteReadChannel) obj, null, 1, null);
                String sha256 = HttpFetcher.this.downloadRequest.getIndexFile().getSha256();
                if (sha256 == null) {
                    this.$callback.onDataReady(inputStream$default);
                } else {
                    this.$callback.onDataReady(new AutoVerifyingInputStream(inputStream$default, sha256, 0L, 4, null));
                }
            } catch (Exception e) {
                this.$callback.onLoadFailed(e);
            }
            return Unit.INSTANCE;
        }
    }

    @Override // com.bumptech.glide.load.data.DataFetcher
    public void loadData(Priority priority, DataFetcher.DataCallback callback) {
        Intrinsics.checkNotNullParameter(priority, "priority");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.job = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new AnonymousClass1(callback, null), 2, null);
    }

    @Override // com.bumptech.glide.load.data.DataFetcher
    public void cleanup() {
        this.job = null;
    }

    @Override // com.bumptech.glide.load.data.DataFetcher
    public void cancel() {
        Job job = this.job;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, null, 1, null);
        }
    }

    @Override // com.bumptech.glide.load.data.DataFetcher
    public Class<InputStream> getDataClass() {
        return InputStream.class;
    }

    @Override // com.bumptech.glide.load.data.DataFetcher
    public DataSource getDataSource() {
        return DataSource.REMOTE;
    }
}
