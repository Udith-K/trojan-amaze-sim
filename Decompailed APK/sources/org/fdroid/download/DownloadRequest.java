package org.fdroid.download;

import java.net.Proxy;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.IndexFile;
import org.fdroid.fdroid.nearby.peers.BonjourPeer;

/* JADX INFO: compiled from: DownloadRequest.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BU\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u000e\u0010\u000fBU\b\u0017\u0012\u0006\u0010\u0010\u001a\u00020\u000b\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u000e\u0010\u0011J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u0011\u0010#\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\tHÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0006HÆ\u0003JY\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0010\b\u0002\u0010\u0007\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010(\u001a\u00020\u001e2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020+HÖ\u0001J\t\u0010,\u001a\u00020\u000bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\u0007\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001d\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u0006-"}, d2 = {"Lorg/fdroid/download/DownloadRequest;", "", "indexFile", "Lorg/fdroid/IndexFile;", "mirrors", "", "Lorg/fdroid/download/Mirror;", "proxy", "Ljava/net/Proxy;", "Lio/ktor/client/engine/ProxyConfig;", "username", "", "password", "tryFirstMirror", "<init>", "(Lorg/fdroid/IndexFile;Ljava/util/List;Ljava/net/Proxy;Ljava/lang/String;Ljava/lang/String;Lorg/fdroid/download/Mirror;)V", BonjourPeer.PATH, "(Ljava/lang/String;Ljava/util/List;Ljava/net/Proxy;Ljava/lang/String;Ljava/lang/String;Lorg/fdroid/download/Mirror;)V", "getIndexFile", "()Lorg/fdroid/IndexFile;", "getMirrors", "()Ljava/util/List;", "getProxy", "()Ljava/net/Proxy;", "getUsername", "()Ljava/lang/String;", "getPassword", "getTryFirstMirror", "()Lorg/fdroid/download/Mirror;", "hasCredentials", "", "getHasCredentials", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "download_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class DownloadRequest {
    private final boolean hasCredentials;
    private final IndexFile indexFile;
    private final List<Mirror> mirrors;
    private final String password;
    private final Proxy proxy;
    private final Mirror tryFirstMirror;
    private final String username;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated
    public DownloadRequest(String path, List<Mirror> mirrors) {
        this(path, mirrors, (Proxy) null, (String) null, (String) null, (Mirror) null, 60, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(mirrors, "mirrors");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated
    public DownloadRequest(String path, List<Mirror> mirrors, Proxy proxy) {
        this(path, mirrors, proxy, (String) null, (String) null, (Mirror) null, 56, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(mirrors, "mirrors");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated
    public DownloadRequest(String path, List<Mirror> mirrors, Proxy proxy, String str) {
        this(path, mirrors, proxy, str, (String) null, (Mirror) null, 48, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(mirrors, "mirrors");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated
    public DownloadRequest(String path, List<Mirror> mirrors, Proxy proxy, String str, String str2) {
        this(path, mirrors, proxy, str, str2, (Mirror) null, 32, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(mirrors, "mirrors");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DownloadRequest(IndexFile indexFile, List<Mirror> mirrors) {
        this(indexFile, mirrors, (Proxy) null, (String) null, (String) null, (Mirror) null, 60, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(indexFile, "indexFile");
        Intrinsics.checkNotNullParameter(mirrors, "mirrors");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DownloadRequest(IndexFile indexFile, List<Mirror> mirrors, Proxy proxy) {
        this(indexFile, mirrors, proxy, (String) null, (String) null, (Mirror) null, 56, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(indexFile, "indexFile");
        Intrinsics.checkNotNullParameter(mirrors, "mirrors");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DownloadRequest(IndexFile indexFile, List<Mirror> mirrors, Proxy proxy, String str) {
        this(indexFile, mirrors, proxy, str, (String) null, (Mirror) null, 48, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(indexFile, "indexFile");
        Intrinsics.checkNotNullParameter(mirrors, "mirrors");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DownloadRequest(IndexFile indexFile, List<Mirror> mirrors, Proxy proxy, String str, String str2) {
        this(indexFile, mirrors, proxy, str, str2, (Mirror) null, 32, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(indexFile, "indexFile");
        Intrinsics.checkNotNullParameter(mirrors, "mirrors");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DownloadRequest copy$default(DownloadRequest downloadRequest, IndexFile indexFile, List list, Proxy proxy, String str, String str2, Mirror mirror, int i, Object obj) {
        if ((i & 1) != 0) {
            indexFile = downloadRequest.indexFile;
        }
        if ((i & 2) != 0) {
            list = downloadRequest.mirrors;
        }
        List list2 = list;
        if ((i & 4) != 0) {
            proxy = downloadRequest.proxy;
        }
        Proxy proxy2 = proxy;
        if ((i & 8) != 0) {
            str = downloadRequest.username;
        }
        String str3 = str;
        if ((i & 16) != 0) {
            str2 = downloadRequest.password;
        }
        String str4 = str2;
        if ((i & 32) != 0) {
            mirror = downloadRequest.tryFirstMirror;
        }
        return downloadRequest.copy(indexFile, list2, proxy2, str3, str4, mirror);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final IndexFile getIndexFile() {
        return this.indexFile;
    }

    public final List<Mirror> component2() {
        return this.mirrors;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Proxy getProxy() {
        return this.proxy;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getUsername() {
        return this.username;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getPassword() {
        return this.password;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Mirror getTryFirstMirror() {
        return this.tryFirstMirror;
    }

    public final DownloadRequest copy(IndexFile indexFile, List<Mirror> mirrors, Proxy proxy, String username, String password, Mirror tryFirstMirror) {
        Intrinsics.checkNotNullParameter(indexFile, "indexFile");
        Intrinsics.checkNotNullParameter(mirrors, "mirrors");
        return new DownloadRequest(indexFile, mirrors, proxy, username, password, tryFirstMirror);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DownloadRequest)) {
            return false;
        }
        DownloadRequest downloadRequest = (DownloadRequest) other;
        return Intrinsics.areEqual(this.indexFile, downloadRequest.indexFile) && Intrinsics.areEqual(this.mirrors, downloadRequest.mirrors) && Intrinsics.areEqual(this.proxy, downloadRequest.proxy) && Intrinsics.areEqual(this.username, downloadRequest.username) && Intrinsics.areEqual(this.password, downloadRequest.password) && Intrinsics.areEqual(this.tryFirstMirror, downloadRequest.tryFirstMirror);
    }

    public int hashCode() {
        int iHashCode = ((this.indexFile.hashCode() * 31) + this.mirrors.hashCode()) * 31;
        Proxy proxy = this.proxy;
        int iHashCode2 = (iHashCode + (proxy == null ? 0 : proxy.hashCode())) * 31;
        String str = this.username;
        int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.password;
        int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Mirror mirror = this.tryFirstMirror;
        return iHashCode4 + (mirror != null ? mirror.hashCode() : 0);
    }

    public String toString() {
        return "DownloadRequest(indexFile=" + this.indexFile + ", mirrors=" + this.mirrors + ", proxy=" + this.proxy + ", username=" + this.username + ", password=" + this.password + ", tryFirstMirror=" + this.tryFirstMirror + ")";
    }

    public DownloadRequest(IndexFile indexFile, List<Mirror> mirrors, Proxy proxy, String str, String str2, Mirror mirror) {
        Intrinsics.checkNotNullParameter(indexFile, "indexFile");
        Intrinsics.checkNotNullParameter(mirrors, "mirrors");
        this.indexFile = indexFile;
        this.mirrors = mirrors;
        this.proxy = proxy;
        this.username = str;
        this.password = str2;
        this.tryFirstMirror = mirror;
        this.hasCredentials = (str == null || str2 == null) ? false : true;
    }

    public /* synthetic */ DownloadRequest(IndexFile indexFile, List list, Proxy proxy, String str, String str2, Mirror mirror, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(indexFile, (List<Mirror>) list, (i & 4) != 0 ? null : proxy, (i & 8) != 0 ? null : str, (i & 16) != 0 ? null : str2, (i & 32) != 0 ? null : mirror);
    }

    public final IndexFile getIndexFile() {
        return this.indexFile;
    }

    public final List<Mirror> getMirrors() {
        return this.mirrors;
    }

    public final Proxy getProxy() {
        return this.proxy;
    }

    public final String getUsername() {
        return this.username;
    }

    public final String getPassword() {
        return this.password;
    }

    public final Mirror getTryFirstMirror() {
        return this.tryFirstMirror;
    }

    public /* synthetic */ DownloadRequest(String str, List list, Proxy proxy, String str2, String str3, Mirror mirror, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (List<Mirror>) list, (i & 4) != 0 ? null : proxy, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : mirror);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated
    public DownloadRequest(String path, List<Mirror> mirrors, Proxy proxy, String str, String str2, Mirror mirror) {
        this(new IndexFile(path) { // from class: org.fdroid.download.DownloadRequest.1
            private final String ipfsCidV1;
            private final String name;
            private final String sha256;
            private final long size;

            {
                this.name = path;
            }

            @Override // org.fdroid.IndexFile
            public String getName() {
                return this.name;
            }

            @Override // org.fdroid.IndexFile
            public String getSha256() {
                return this.sha256;
            }

            @Override // org.fdroid.IndexFile
            public Long getSize() {
                return Long.valueOf(this.size);
            }

            @Override // org.fdroid.IndexFile
            public String getIpfsCidV1() {
                return this.ipfsCidV1;
            }

            @Override // org.fdroid.IndexFile
            public String serialize() {
                throw new NotImplementedError("Serialization is not implemented.");
            }
        }, mirrors, proxy, str, str2, mirror);
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(mirrors, "mirrors");
    }

    public final boolean getHasCredentials() {
        return this.hasCredentials;
    }
}
