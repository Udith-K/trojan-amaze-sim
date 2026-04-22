package org.fdroid.download;

import android.net.Uri;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.IndexFile;
import org.fdroid.database.Repository;

/* JADX INFO: compiled from: DownloaderFactory.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ(\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J@\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H$¨\u0006\u0013"}, d2 = {"Lorg/fdroid/download/DownloaderFactory;", "", "<init>", "()V", "createWithTryFirstMirror", "Lorg/fdroid/download/Downloader;", "repo", "Lorg/fdroid/database/Repository;", "uri", "Landroid/net/Uri;", "indexFile", "Lorg/fdroid/IndexFile;", "destFile", "Ljava/io/File;", "create", "mirrors", "", "Lorg/fdroid/download/Mirror;", "tryFirst", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class DownloaderFactory {
    public abstract Downloader create(Repository repo, Uri uri, IndexFile indexFile, File destFile) throws IOException;

    protected abstract Downloader create(Repository repo, List<Mirror> mirrors, Uri uri, IndexFile indexFile, File destFile, Mirror tryFirst) throws IOException;

    public final Downloader createWithTryFirstMirror(Repository repo, Uri uri, IndexFile indexFile, File destFile) throws IOException {
        Object next;
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(indexFile, "indexFile");
        Intrinsics.checkNotNullParameter(destFile, "destFile");
        Iterator<T> it = repo.getMirrors().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (Intrinsics.areEqual(((Mirror) next).getBaseUrl(), repo.getAddress())) {
                break;
            }
        }
        Mirror mirror = (Mirror) next;
        if (mirror == null) {
            Log.w("DownloaderFactory", "Try-first mirror not found, disabled by user?");
        }
        return create(repo, repo.getMirrors(), uri, indexFile, destFile, mirror);
    }
}
