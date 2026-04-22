package org.fdroid.index;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.database.Repository;
import org.fdroid.download.Downloader;
import org.fdroid.fdroid.ProgressListener;

/* JADX INFO: compiled from: IndexUpdater.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u000b"}, d2 = {"defaultRepoUriBuilder", "Lorg/fdroid/index/RepoUriBuilder;", "getDefaultRepoUriBuilder", "()Lorg/fdroid/index/RepoUriBuilder;", "setIndexUpdateListener", "", "Lorg/fdroid/download/Downloader;", "listener", "Lorg/fdroid/index/IndexUpdateListener;", "repo", "Lorg/fdroid/database/Repository;", "database_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class IndexUpdaterKt {
    private static final RepoUriBuilder defaultRepoUriBuilder = new RepoUriBuilder() { // from class: org.fdroid.index.IndexUpdaterKt$$ExternalSyntheticLambda1
        @Override // org.fdroid.index.RepoUriBuilder
        public final Uri getUri(Repository repository, String[] strArr) {
            return IndexUpdaterKt.defaultRepoUriBuilder$lambda$1(repository, strArr);
        }
    };

    public static final RepoUriBuilder getDefaultRepoUriBuilder() {
        return defaultRepoUriBuilder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Uri defaultRepoUriBuilder$lambda$1(Repository repo, String[] pathElements) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(pathElements, "pathElements");
        Uri.Builder builderBuildUpon = Uri.parse(repo.getAddress()).buildUpon();
        for (String str : pathElements) {
            builderBuildUpon.appendEncodedPath(str);
        }
        Uri uriBuild = builderBuildUpon.build();
        Intrinsics.checkNotNullExpressionValue(uriBuild, "build(...)");
        return uriBuild;
    }

    public static final void setIndexUpdateListener(Downloader downloader, final IndexUpdateListener indexUpdateListener, final Repository repo) {
        Intrinsics.checkNotNullParameter(downloader, "<this>");
        Intrinsics.checkNotNullParameter(repo, "repo");
        if (indexUpdateListener != null) {
            downloader.setListener(new ProgressListener() { // from class: org.fdroid.index.IndexUpdaterKt$$ExternalSyntheticLambda0
                @Override // org.fdroid.fdroid.ProgressListener
                public final void onProgress(long j, long j2) {
                    indexUpdateListener.onDownloadProgress(repo, j, j2);
                }
            });
        }
    }
}
