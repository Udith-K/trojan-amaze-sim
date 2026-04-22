package org.fdroid.database;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.fdroid.nearby.peers.BonjourPeer;

/* JADX INFO: compiled from: App.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0081\b\u0018\u0000 *2\u00020\u0001:\u0001*BS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0017J\u000b\u0010!\u001a\u0004\u0018\u00010\u0005HÆ\u0003Jd\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010#J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010'\u001a\u00020(HÖ\u0001J\t\u0010)\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0015\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011¨\u0006+"}, d2 = {"Lorg/fdroid/database/LocalizedFileList;", "", "repoId", "", "packageName", "", BonjourPeer.TYPE, "locale", "name", "sha256", "size", "ipfsCidV1", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;)V", "getRepoId", "()J", "getPackageName", "()Ljava/lang/String;", "getType", "getLocale", "getName", "getSha256", "getSize", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getIpfsCidV1", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;)Lorg/fdroid/database/LocalizedFileList;", "equals", "", "other", "hashCode", "", "toString", "Companion", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class LocalizedFileList {
    public static final String TABLE = "LocalizedFileList";
    private final String ipfsCidV1;
    private final String locale;
    private final String name;
    private final String packageName;
    private final long repoId;
    private final String sha256;
    private final Long size;
    private final String type;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getRepoId() {
        return this.repoId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPackageName() {
        return this.packageName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getLocale() {
        return this.locale;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getSha256() {
        return this.sha256;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Long getSize() {
        return this.size;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getIpfsCidV1() {
        return this.ipfsCidV1;
    }

    public final LocalizedFileList copy(long repoId, String packageName, String type, String locale, String name, String sha256, Long size, String ipfsCidV1) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(locale, "locale");
        Intrinsics.checkNotNullParameter(name, "name");
        return new LocalizedFileList(repoId, packageName, type, locale, name, sha256, size, ipfsCidV1);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LocalizedFileList)) {
            return false;
        }
        LocalizedFileList localizedFileList = (LocalizedFileList) other;
        return this.repoId == localizedFileList.repoId && Intrinsics.areEqual(this.packageName, localizedFileList.packageName) && Intrinsics.areEqual(this.type, localizedFileList.type) && Intrinsics.areEqual(this.locale, localizedFileList.locale) && Intrinsics.areEqual(this.name, localizedFileList.name) && Intrinsics.areEqual(this.sha256, localizedFileList.sha256) && Intrinsics.areEqual(this.size, localizedFileList.size) && Intrinsics.areEqual(this.ipfsCidV1, localizedFileList.ipfsCidV1);
    }

    public int hashCode() {
        int iM = ((((((((LongObjectMap$$ExternalSyntheticBackport0.m(this.repoId) * 31) + this.packageName.hashCode()) * 31) + this.type.hashCode()) * 31) + this.locale.hashCode()) * 31) + this.name.hashCode()) * 31;
        String str = this.sha256;
        int iHashCode = (iM + (str == null ? 0 : str.hashCode())) * 31;
        Long l = this.size;
        int iHashCode2 = (iHashCode + (l == null ? 0 : l.hashCode())) * 31;
        String str2 = this.ipfsCidV1;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "LocalizedFileList(repoId=" + this.repoId + ", packageName=" + this.packageName + ", type=" + this.type + ", locale=" + this.locale + ", name=" + this.name + ", sha256=" + this.sha256 + ", size=" + this.size + ", ipfsCidV1=" + this.ipfsCidV1 + ")";
    }

    public LocalizedFileList(long j, String packageName, String type, String locale, String name, String str, Long l, String str2) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(locale, "locale");
        Intrinsics.checkNotNullParameter(name, "name");
        this.repoId = j;
        this.packageName = packageName;
        this.type = type;
        this.locale = locale;
        this.name = name;
        this.sha256 = str;
        this.size = l;
        this.ipfsCidV1 = str2;
    }

    public /* synthetic */ LocalizedFileList(long j, String str, String str2, String str3, String str4, String str5, Long l, String str6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, str, str2, str3, str4, (i & 32) != 0 ? null : str5, (i & 64) != 0 ? null : l, (i & 128) != 0 ? null : str6);
    }

    public final long getRepoId() {
        return this.repoId;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final String getType() {
        return this.type;
    }

    public final String getLocale() {
        return this.locale;
    }

    public final String getName() {
        return this.name;
    }

    public final String getSha256() {
        return this.sha256;
    }

    public final Long getSize() {
        return this.size;
    }

    public final String getIpfsCidV1() {
        return this.ipfsCidV1;
    }
}
