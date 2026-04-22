package org.fdroid.download.glide;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.download.DownloadRequest;
import org.fdroid.download.HttpManager;

/* JADX INFO: compiled from: DownloadRequestLoader.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0012B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0002H\u0016J.\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lorg/fdroid/download/glide/DownloadRequestLoader;", "Lcom/bumptech/glide/load/model/ModelLoader;", "Lorg/fdroid/download/DownloadRequest;", "Ljava/io/InputStream;", "httpManager", "Lorg/fdroid/download/HttpManager;", "<init>", "(Lorg/fdroid/download/HttpManager;)V", "handles", "", "downloadRequest", "buildLoadData", "Lcom/bumptech/glide/load/model/ModelLoader$LoadData;", "width", "", "height", "options", "Lcom/bumptech/glide/load/Options;", "Factory", "download_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class DownloadRequestLoader implements ModelLoader {
    private final HttpManager httpManager;

    @Override // com.bumptech.glide.load.model.ModelLoader
    public boolean handles(DownloadRequest downloadRequest) {
        Intrinsics.checkNotNullParameter(downloadRequest, "downloadRequest");
        return true;
    }

    public DownloadRequestLoader(HttpManager httpManager) {
        Intrinsics.checkNotNullParameter(httpManager, "httpManager");
        this.httpManager = httpManager;
    }

    @Override // com.bumptech.glide.load.model.ModelLoader
    public ModelLoader.LoadData buildLoadData(DownloadRequest downloadRequest, int width, int height, Options options) {
        Intrinsics.checkNotNullParameter(downloadRequest, "downloadRequest");
        Intrinsics.checkNotNullParameter(options, "options");
        return new ModelLoader.LoadData(DownloadRequestLoaderKt.getKey(downloadRequest), new HttpFetcher(this.httpManager, downloadRequest));
    }

    /* JADX INFO: compiled from: DownloadRequestLoader.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001c\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/fdroid/download/glide/DownloadRequestLoader$Factory;", "Lcom/bumptech/glide/load/model/ModelLoaderFactory;", "Lorg/fdroid/download/DownloadRequest;", "Ljava/io/InputStream;", "httpManager", "Lorg/fdroid/download/HttpManager;", "<init>", "(Lorg/fdroid/download/HttpManager;)V", "build", "Lcom/bumptech/glide/load/model/ModelLoader;", "multiFactory", "Lcom/bumptech/glide/load/model/MultiModelLoaderFactory;", "teardown", "", "download_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Factory implements ModelLoaderFactory {
        private final HttpManager httpManager;

        public void teardown() {
        }

        public Factory(HttpManager httpManager) {
            Intrinsics.checkNotNullParameter(httpManager, "httpManager");
            this.httpManager = httpManager;
        }

        @Override // com.bumptech.glide.load.model.ModelLoaderFactory
        public ModelLoader build(MultiModelLoaderFactory multiFactory) {
            Intrinsics.checkNotNullParameter(multiFactory, "multiFactory");
            return new DownloadRequestLoader(this.httpManager);
        }
    }
}
