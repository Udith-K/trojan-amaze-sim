package org.fdroid.repo;

import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;

/* JADX INFO: compiled from: KnownRepos.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\" \u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"knownRepos", "", "", "getKnownRepos", "()Ljava/util/Map;", "database_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class KnownReposKt {
    private static final Map<String, String> knownRepos = MapsKt.mapOf(TuplesKt.to("https://apt.izzysoft.de/fdroid/repo", "3bf0d6abfeae2f401707b6d966be743bf0eee49c2561b9ba39073711f628937a"), TuplesKt.to("https://archive.newpipe.net/fdroid/repo", "e2402c78f9b97c6c89e97db914a2751fda1d02fe2039cc0897a462bdb57e7501"), TuplesKt.to("https://briarproject.org/fdroid/repo", "1fb874bee7276d28ecb2c9b06e8a122ec4bcb4008161436ce474c257cbf49bd6"), TuplesKt.to("https://guardianproject.info/fdroid/repo", "b7c2eefd8dac7806af67dfcd92eb18126bc08312a7f2d6f3862e46013c7a6135"), TuplesKt.to("https://microg.org/fdroid/repo", "9bd06727e62796c0130eb6dab39b73157451582cbd138e86c468acc395d14165"));

    public static final Map<String, String> getKnownRepos() {
        return knownRepos;
    }
}
