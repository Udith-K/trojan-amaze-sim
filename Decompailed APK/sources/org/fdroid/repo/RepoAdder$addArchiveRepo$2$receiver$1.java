package org.fdroid.repo;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import org.fdroid.database.AppOverviewItem;
import org.fdroid.database.FDroidDatabase;
import org.fdroid.database.NewRepository;
import org.fdroid.database.Repository;
import org.fdroid.index.IndexFormatVersion;
import org.fdroid.index.v2.FileV2;

/* JADX INFO: compiled from: RepoAdder.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"org/fdroid/repo/RepoAdder$addArchiveRepo$2$receiver$1", "Lorg/fdroid/repo/RepoPreviewReceiver;", "onRepoReceived", "", "archiveRepo", "Lorg/fdroid/database/Repository;", "onAppReceived", "app", "Lorg/fdroid/database/AppOverviewItem;", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class RepoAdder$addArchiveRepo$2$receiver$1 implements RepoPreviewReceiver {
    final /* synthetic */ CoroutineScope $$this$withContext;
    final /* synthetic */ Ref$ObjectRef $archiveRepoId;
    final /* synthetic */ Repository $repo;
    final /* synthetic */ RepoAdder this$0;

    @Override // org.fdroid.repo.RepoPreviewReceiver
    public void onAppReceived(AppOverviewItem app) {
        Intrinsics.checkNotNullParameter(app, "app");
    }

    RepoAdder$addArchiveRepo$2$receiver$1(RepoAdder repoAdder, CoroutineScope coroutineScope, Repository repository, Ref$ObjectRef ref$ObjectRef) {
        this.this$0 = repoAdder;
        this.$$this$withContext = coroutineScope;
        this.$repo = repository;
        this.$archiveRepoId = ref$ObjectRef;
    }

    @Override // org.fdroid.repo.RepoPreviewReceiver
    public void onRepoReceived(Repository archiveRepo) {
        Intrinsics.checkNotNullParameter(archiveRepo, "archiveRepo");
        Map<String, String> name = archiveRepo.getRepository$database_release().getName();
        Map<String, FileV2> icon = archiveRepo.getRepository$database_release().getIcon();
        if (icon == null) {
            icon = MapsKt.emptyMap();
        }
        Map<String, FileV2> map = icon;
        String address = archiveRepo.getAddress();
        IndexFormatVersion formatVersion = archiveRepo.getFormatVersion();
        String certificate = archiveRepo.getCertificate();
        if (certificate != null) {
            final NewRepository newRepository = new NewRepository(name, map, address, formatVersion, certificate, archiveRepo.getUsername(), archiveRepo.getPassword());
            FDroidDatabase fDroidDatabase = this.this$0.db;
            final RepoAdder repoAdder = this.this$0;
            final Repository repository = this.$repo;
            final Ref$ObjectRef ref$ObjectRef = this.$archiveRepoId;
            fDroidDatabase.runInTransaction(new Runnable() { // from class: org.fdroid.repo.RepoAdder$addArchiveRepo$2$receiver$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    RepoAdder$addArchiveRepo$2$receiver$1.onRepoReceived$lambda$0(repoAdder, newRepository, repository, ref$ObjectRef);
                }
            });
            CoroutineScopeKt.cancel$default(this.$$this$withContext, "expected", null, 2, null);
            return;
        }
        throw new IllegalStateException("Repo had no certificate");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onRepoReceived$lambda$0(RepoAdder repoAdder, NewRepository newRepository, Repository repository, Ref$ObjectRef ref$ObjectRef) {
        long jInsert = repoAdder.repositoryDao.insert(newRepository);
        repoAdder.repositoryDao.setWeight(jInsert, repository.getWeight() - 1);
        ref$ObjectRef.element = Long.valueOf(jInsert);
    }
}
