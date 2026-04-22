package org.fdroid.index;

import android.net.Uri;
import kotlin.Metadata;
import org.fdroid.database.Repository;

/* JADX INFO: compiled from: IndexUpdater.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\bæ\u0080\u0001\u0018\u00002\u00020\u0001J)\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\"\u00020\bH&¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lorg/fdroid/index/RepoUriBuilder;", "", "getUri", "Landroid/net/Uri;", "repo", "Lorg/fdroid/database/Repository;", "pathElements", "", "", "(Lorg/fdroid/database/Repository;[Ljava/lang/String;)Landroid/net/Uri;", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface RepoUriBuilder {
    Uri getUri(Repository repo, String... pathElements);
}
