package org.fdroid.download;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MirrorChooser.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lorg/fdroid/download/MirrorChooserRandom;", "Lorg/fdroid/download/MirrorChooserImpl;", "<init>", "()V", "orderMirrors", "", "Lorg/fdroid/download/Mirror;", "downloadRequest", "Lorg/fdroid/download/DownloadRequest;", "download_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class MirrorChooserRandom extends MirrorChooserImpl {
    @Override // org.fdroid.download.MirrorChooser
    public List<Mirror> orderMirrors(final DownloadRequest downloadRequest) {
        Intrinsics.checkNotNullParameter(downloadRequest, "downloadRequest");
        List<Mirror> mutableList = CollectionsKt.toMutableList((Collection) downloadRequest.getMirrors());
        Collections.shuffle(mutableList);
        if (downloadRequest.getTryFirstMirror() != null && mutableList.size() > 1) {
            CollectionsKt.sortWith(mutableList, new Comparator() { // from class: org.fdroid.download.MirrorChooserRandom$orderMirrors$lambda$2$$inlined$sortBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Integer.valueOf(!Intrinsics.areEqual((Mirror) t, downloadRequest.getTryFirstMirror()) ? 1 : 0), Integer.valueOf(!Intrinsics.areEqual((Mirror) t2, downloadRequest.getTryFirstMirror()) ? 1 : 0));
                }
            });
        }
        return mutableList;
    }
}
