package org.fdroid.database;

import kotlin.Metadata;
import org.fdroid.fdroid.nearby.peers.BonjourPeer;

/* JADX INFO: compiled from: App.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0005\b`\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0012\u0010\b\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0005R\u0014\u0010\n\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0005R\u0014\u0010\f\u001a\u0004\u0018\u00010\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0005¨\u0006\u0012"}, d2 = {"Lorg/fdroid/database/IFile;", "", BonjourPeer.TYPE, "", "getType", "()Ljava/lang/String;", "locale", "getLocale", "name", "getName", "sha256", "getSha256", "size", "", "getSize", "()Ljava/lang/Long;", "ipfsCidV1", "getIpfsCidV1", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface IFile {
    String getIpfsCidV1();

    String getLocale();

    String getName();

    String getSha256();

    Long getSize();

    String getType();
}
