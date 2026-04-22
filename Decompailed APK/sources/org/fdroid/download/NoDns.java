package org.fdroid.download;

import java.net.InetAddress;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Dns;

/* JADX INFO: compiled from: HttpManager.kt */
/* JADX INFO: loaded from: classes2.dex */
final class NoDns implements Dns {
    @Override // okhttp3.Dns
    public List lookup(String hostname) {
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        return CollectionsKt.listOf(InetAddress.getByAddress(hostname, new byte[4]));
    }
}
