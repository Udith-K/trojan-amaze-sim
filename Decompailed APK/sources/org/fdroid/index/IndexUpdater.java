package org.fdroid.index;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.database.Repository;
import org.fdroid.download.NotFoundException;
import org.fdroid.index.IndexUpdateResult;

/* JADX INFO: compiled from: IndexUpdater.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\t2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH$R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lorg/fdroid/index/IndexUpdater;", "", "<init>", "()V", "formatVersion", "Lorg/fdroid/index/IndexFormatVersion;", "getFormatVersion", "()Lorg/fdroid/index/IndexFormatVersion;", "update", "Lorg/fdroid/index/IndexUpdateResult;", "repo", "Lorg/fdroid/database/Repository;", "catchExceptions", "block", "Lkotlin/Function0;", "updateRepo", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class IndexUpdater {
    public abstract IndexFormatVersion getFormatVersion();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract IndexUpdateResult updateRepo(Repository repo);

    public final IndexUpdateResult update(final Repository repo) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        return catchExceptions(new Function0() { // from class: org.fdroid.index.IndexUpdater$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.updateRepo(repo);
            }
        });
    }

    private final IndexUpdateResult catchExceptions(Function0 block) {
        try {
            return (IndexUpdateResult) block.invoke();
        } catch (NotFoundException unused) {
            return IndexUpdateResult.NotFound.INSTANCE;
        } catch (Exception e) {
            return new IndexUpdateResult.Error(e);
        }
    }
}
