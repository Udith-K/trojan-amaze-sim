package org.fdroid.database;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Repository.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0081\b\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\u000e\u001a\u00020\u000fJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J)\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u001b"}, d2 = {"Lorg/fdroid/database/Mirror;", "", "repoId", "", "url", "", "countryCode", "<init>", "(JLjava/lang/String;Ljava/lang/String;)V", "getRepoId", "()J", "getUrl", "()Ljava/lang/String;", "getCountryCode", "toDownloadMirror", "Lorg/fdroid/download/Mirror;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class Mirror {
    public static final String TABLE = "Mirror";
    private final String countryCode;
    private final long repoId;
    private final String url;

    public static /* synthetic */ Mirror copy$default(Mirror mirror, long j, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = mirror.repoId;
        }
        if ((i & 2) != 0) {
            str = mirror.url;
        }
        if ((i & 4) != 0) {
            str2 = mirror.countryCode;
        }
        return mirror.copy(j, str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getRepoId() {
        return this.repoId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getCountryCode() {
        return this.countryCode;
    }

    public final Mirror copy(long repoId, String url, String countryCode) {
        Intrinsics.checkNotNullParameter(url, "url");
        return new Mirror(repoId, url, countryCode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Mirror)) {
            return false;
        }
        Mirror mirror = (Mirror) other;
        return this.repoId == mirror.repoId && Intrinsics.areEqual(this.url, mirror.url) && Intrinsics.areEqual(this.countryCode, mirror.countryCode);
    }

    public int hashCode() {
        int iM = ((LongObjectMap$$ExternalSyntheticBackport0.m(this.repoId) * 31) + this.url.hashCode()) * 31;
        String str = this.countryCode;
        return iM + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "Mirror(repoId=" + this.repoId + ", url=" + this.url + ", countryCode=" + this.countryCode + ")";
    }

    public Mirror(long j, String url, String str) {
        Intrinsics.checkNotNullParameter(url, "url");
        this.repoId = j;
        this.url = url;
        this.countryCode = str;
    }

    public /* synthetic */ Mirror(long j, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, str, (i & 4) != 0 ? null : str2);
    }

    public final long getRepoId() {
        return this.repoId;
    }

    public final String getUrl() {
        return this.url;
    }

    public final String getCountryCode() {
        return this.countryCode;
    }

    public final org.fdroid.download.Mirror toDownloadMirror() {
        return new org.fdroid.download.Mirror(this.url, this.countryCode, false, 4, null);
    }
}
