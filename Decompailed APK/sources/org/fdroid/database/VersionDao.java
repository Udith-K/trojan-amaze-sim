package org.fdroid.database;

import androidx.lifecycle.LiveData;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.fdroid.index.v2.PackageVersionV2;

/* JADX INFO: compiled from: VersionDao.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J@\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\n0\t2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\r0\fH&J\u001c\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000f2\u0006\u0010\u0006\u001a\u00020\u0007H&J$\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\u0012"}, d2 = {"Lorg/fdroid/database/VersionDao;", "", "insert", "", "repoId", "", "packageName", "", "packageVersions", "", "Lorg/fdroid/index/v2/PackageVersionV2;", "checkIfCompatible", "Lkotlin/Function1;", "", "getAppVersions", "Landroidx/lifecycle/LiveData;", "", "Lorg/fdroid/database/AppVersion;", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface VersionDao {
    LiveData getAppVersions(long repoId, String packageName);

    LiveData getAppVersions(String packageName);

    void insert(long repoId, String packageName, Map<String, PackageVersionV2> packageVersions, Function1 checkIfCompatible);
}
