package org.fdroid.repo;

import kotlin.Metadata;
import org.fdroid.database.AppOverviewItem;
import org.fdroid.database.Repository;

/* JADX INFO: compiled from: RepoFetcher.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lorg/fdroid/repo/RepoPreviewReceiver;", "", "onRepoReceived", "", "repo", "Lorg/fdroid/database/Repository;", "onAppReceived", "app", "Lorg/fdroid/database/AppOverviewItem;", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface RepoPreviewReceiver {
    void onAppReceived(AppOverviewItem app);

    void onRepoReceived(Repository repo);
}
