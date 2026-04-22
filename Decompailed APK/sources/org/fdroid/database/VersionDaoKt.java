package org.fdroid.database;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: VersionDao.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0003"}, d2 = {"DENY_LIST", "", "", "database_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class VersionDaoKt {
    private static final List<String> DENY_LIST = CollectionsKt.listOf((Object[]) new String[]{"packageName", "repoId", "versionId"});
}
