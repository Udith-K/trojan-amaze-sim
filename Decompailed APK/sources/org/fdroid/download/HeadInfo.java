package org.fdroid.download;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HeadInfo.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003J<\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u00032\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000e¨\u0006\u001e"}, d2 = {"Lorg/fdroid/download/HeadInfo;", "", "eTagChanged", "", "eTag", "", "contentLength", "", "lastModified", "<init>", "(ZLjava/lang/String;Ljava/lang/Long;Ljava/lang/String;)V", "getETagChanged", "()Z", "getETag", "()Ljava/lang/String;", "getContentLength", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getLastModified", "component1", "component2", "component3", "component4", "copy", "(ZLjava/lang/String;Ljava/lang/Long;Ljava/lang/String;)Lorg/fdroid/download/HeadInfo;", "equals", "other", "hashCode", "", "toString", "download_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class HeadInfo {
    private final Long contentLength;
    private final String eTag;
    private final boolean eTagChanged;
    private final String lastModified;

    public static /* synthetic */ HeadInfo copy$default(HeadInfo headInfo, boolean z, String str, Long l, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = headInfo.eTagChanged;
        }
        if ((i & 2) != 0) {
            str = headInfo.eTag;
        }
        if ((i & 4) != 0) {
            l = headInfo.contentLength;
        }
        if ((i & 8) != 0) {
            str2 = headInfo.lastModified;
        }
        return headInfo.copy(z, str, l, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getETagChanged() {
        return this.eTagChanged;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getETag() {
        return this.eTag;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Long getContentLength() {
        return this.contentLength;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getLastModified() {
        return this.lastModified;
    }

    public final HeadInfo copy(boolean eTagChanged, String eTag, Long contentLength, String lastModified) {
        return new HeadInfo(eTagChanged, eTag, contentLength, lastModified);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HeadInfo)) {
            return false;
        }
        HeadInfo headInfo = (HeadInfo) other;
        return this.eTagChanged == headInfo.eTagChanged && Intrinsics.areEqual(this.eTag, headInfo.eTag) && Intrinsics.areEqual(this.contentLength, headInfo.contentLength) && Intrinsics.areEqual(this.lastModified, headInfo.lastModified);
    }

    public int hashCode() {
        int iM = ChangeSize$$ExternalSyntheticBackport0.m(this.eTagChanged) * 31;
        String str = this.eTag;
        int iHashCode = (iM + (str == null ? 0 : str.hashCode())) * 31;
        Long l = this.contentLength;
        int iHashCode2 = (iHashCode + (l == null ? 0 : l.hashCode())) * 31;
        String str2 = this.lastModified;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "HeadInfo(eTagChanged=" + this.eTagChanged + ", eTag=" + this.eTag + ", contentLength=" + this.contentLength + ", lastModified=" + this.lastModified + ")";
    }

    public HeadInfo(boolean z, String str, Long l, String str2) {
        this.eTagChanged = z;
        this.eTag = str;
        this.contentLength = l;
        this.lastModified = str2;
    }

    public final boolean getETagChanged() {
        return this.eTagChanged;
    }

    public final String getETag() {
        return this.eTag;
    }

    public final Long getContentLength() {
        return this.contentLength;
    }

    public final String getLastModified() {
        return this.lastModified;
    }
}
