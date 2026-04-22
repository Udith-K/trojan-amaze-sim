package org.fdroid.index.v2;

import kotlin.Metadata;

/* JADX INFO: compiled from: IndexV2StreamReceiver.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\u0003H&¨\u0006\r"}, d2 = {"Lorg/fdroid/index/v2/IndexV2StreamReceiver;", "", "receive", "", "repo", "Lorg/fdroid/index/v2/RepoV2;", "version", "", "packageName", "", "p", "Lorg/fdroid/index/v2/PackageV2;", "onStreamEnded", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface IndexV2StreamReceiver {
    void onStreamEnded();

    void receive(String packageName, PackageV2 p);

    void receive(RepoV2 repo, long version);
}
