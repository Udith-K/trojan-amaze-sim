package org.fdroid.database;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.fdroid.fdroid.nearby.peers.BonjourPeer;

/* JADX INFO: compiled from: AppDao.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0004"}, d2 = {"DENY_LIST", "", "", "DENY_FILE_LIST", "database_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AppDaoKt {
    private static final List<String> DENY_LIST = CollectionsKt.listOf((Object[]) new String[]{"packageName", "repoId"});
    private static final List<String> DENY_FILE_LIST = CollectionsKt.listOf((Object[]) new String[]{"packageName", "repoId", BonjourPeer.TYPE});
}
