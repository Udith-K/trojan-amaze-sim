package org.fdroid.database;

import androidx.lifecycle.LiveData;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: RepositoryDao.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0012\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u0003H&J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\fH&J\u0014\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\f0\u000eH&J\u0014\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\f0\u000eH&J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0014H&J\u001e\u0010\u0015\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\u00032\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\fH&J$\u0010\u0018\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\u00032\b\u0010\u0019\u001a\u0004\u0018\u00010\u00172\b\u0010\u001a\u001a\u0004\u0018\u00010\u0017H&J\u001e\u0010\u001b\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\u00032\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00170\fH&J\u0010\u0010\u001d\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\u0003H&J\b\u0010\u001e\u001a\u00020\u0012H&J\b\u0010\u001f\u001a\u00020\u0012H&¨\u0006 "}, d2 = {"Lorg/fdroid/database/RepositoryDao;", "", "insert", "", "initialRepo", "Lorg/fdroid/database/InitialRepository;", "newRepository", "Lorg/fdroid/database/NewRepository;", "getRepository", "Lorg/fdroid/database/Repository;", "repoId", "getRepositories", "", "getLiveRepositories", "Landroidx/lifecycle/LiveData;", "getLiveCategories", "Lorg/fdroid/database/Category;", "setRepositoryEnabled", "", "enabled", "", "updateUserMirrors", "mirrors", "", "updateUsernameAndPassword", "username", "password", "updateDisabledMirrors", "disabledMirrors", "deleteRepository", "clearAll", "walCheckpoint", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface RepositoryDao {
    void clearAll();

    void deleteRepository(long repoId);

    LiveData getLiveCategories();

    LiveData getLiveRepositories();

    List<Repository> getRepositories();

    Repository getRepository(long repoId);

    long insert(InitialRepository initialRepo);

    long insert(NewRepository newRepository);

    void setRepositoryEnabled(long repoId, boolean enabled);

    void updateDisabledMirrors(long repoId, List<String> disabledMirrors);

    void updateUserMirrors(long repoId, List<String> mirrors);

    void updateUsernameAndPassword(long repoId, String username, String password);

    void walCheckpoint();
}
