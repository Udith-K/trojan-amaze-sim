package org.fdroid.database;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.fdroid.nearby.peers.BonjourPeer;

/* JADX INFO: compiled from: Version.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0081\b\u0018\u0000 &2\u00020\u0001:\u0001&B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\bHÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010\u0017JL\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bHÆ\u0001¢\u0006\u0002\u0010 J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\u000bHÖ\u0001J\t\u0010%\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017¨\u0006'"}, d2 = {"Lorg/fdroid/database/VersionedString;", "", "repoId", "", "packageName", "", "versionId", BonjourPeer.TYPE, "Lorg/fdroid/database/VersionedStringType;", "name", "version", "", "<init>", "(JLjava/lang/String;Ljava/lang/String;Lorg/fdroid/database/VersionedStringType;Ljava/lang/String;Ljava/lang/Integer;)V", "getRepoId", "()J", "getPackageName", "()Ljava/lang/String;", "getVersionId", "getType", "()Lorg/fdroid/database/VersionedStringType;", "getName", "getVersion", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(JLjava/lang/String;Ljava/lang/String;Lorg/fdroid/database/VersionedStringType;Ljava/lang/String;Ljava/lang/Integer;)Lorg/fdroid/database/VersionedString;", "equals", "", "other", "hashCode", "toString", "Companion", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class VersionedString {
    public static final String TABLE = "VersionedString";
    private final String name;
    private final String packageName;
    private final long repoId;
    private final VersionedStringType type;
    private final Integer version;
    private final String versionId;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getRepoId() {
        return this.repoId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPackageName() {
        return this.packageName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getVersionId() {
        return this.versionId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final VersionedStringType getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Integer getVersion() {
        return this.version;
    }

    public final VersionedString copy(long repoId, String packageName, String versionId, VersionedStringType type, String name, Integer version) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(versionId, "versionId");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(name, "name");
        return new VersionedString(repoId, packageName, versionId, type, name, version);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VersionedString)) {
            return false;
        }
        VersionedString versionedString = (VersionedString) other;
        return this.repoId == versionedString.repoId && Intrinsics.areEqual(this.packageName, versionedString.packageName) && Intrinsics.areEqual(this.versionId, versionedString.versionId) && this.type == versionedString.type && Intrinsics.areEqual(this.name, versionedString.name) && Intrinsics.areEqual(this.version, versionedString.version);
    }

    public int hashCode() {
        int iM = ((((((((LongObjectMap$$ExternalSyntheticBackport0.m(this.repoId) * 31) + this.packageName.hashCode()) * 31) + this.versionId.hashCode()) * 31) + this.type.hashCode()) * 31) + this.name.hashCode()) * 31;
        Integer num = this.version;
        return iM + (num == null ? 0 : num.hashCode());
    }

    public String toString() {
        return "VersionedString(repoId=" + this.repoId + ", packageName=" + this.packageName + ", versionId=" + this.versionId + ", type=" + this.type + ", name=" + this.name + ", version=" + this.version + ")";
    }

    public VersionedString(long j, String packageName, String versionId, VersionedStringType type, String name, Integer num) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(versionId, "versionId");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(name, "name");
        this.repoId = j;
        this.packageName = packageName;
        this.versionId = versionId;
        this.type = type;
        this.name = name;
        this.version = num;
    }

    public /* synthetic */ VersionedString(long j, String str, String str2, VersionedStringType versionedStringType, String str3, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, str, str2, versionedStringType, str3, (i & 32) != 0 ? null : num);
    }

    public final long getRepoId() {
        return this.repoId;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final String getVersionId() {
        return this.versionId;
    }

    public final VersionedStringType getType() {
        return this.type;
    }

    public final String getName() {
        return this.name;
    }

    public final Integer getVersion() {
        return this.version;
    }
}
