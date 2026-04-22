package org.fdroid.download.glide;

import com.bumptech.glide.signature.ObjectKey;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.download.DownloadRequest;

/* JADX INFO: compiled from: DownloadRequestLoader.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"getKey", "Lcom/bumptech/glide/signature/ObjectKey;", "Lorg/fdroid/download/DownloadRequest;", "download_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class DownloadRequestLoaderKt {
    public static final ObjectKey getKey(DownloadRequest downloadRequest) {
        Intrinsics.checkNotNullParameter(downloadRequest, "<this>");
        String sha256 = downloadRequest.getIndexFile().getSha256();
        if (sha256 == null) {
            sha256 = downloadRequest.getMirrors().get(0).getBaseUrl() + downloadRequest.getIndexFile().getName();
        }
        return new ObjectKey(sha256);
    }
}
