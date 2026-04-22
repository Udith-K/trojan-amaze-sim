package org.fdroid.database;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.i18n.ErrorBundle;

/* JADX INFO: compiled from: App.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0081\b\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J5\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u001c"}, d2 = {"Lorg/fdroid/database/AppMetadataFts;", "", "repoId", "", "packageName", "", "name", ErrorBundle.SUMMARY_ENTRY, "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getRepoId", "()J", "getPackageName", "()Ljava/lang/String;", "getName", "getSummary", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class AppMetadataFts {
    public static final String TABLE = "AppMetadataFts";
    private final String name;
    private final String packageName;
    private final long repoId;
    private final String summary;

    public static /* synthetic */ AppMetadataFts copy$default(AppMetadataFts appMetadataFts, long j, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            j = appMetadataFts.repoId;
        }
        long j2 = j;
        if ((i & 2) != 0) {
            str = appMetadataFts.packageName;
        }
        String str4 = str;
        if ((i & 4) != 0) {
            str2 = appMetadataFts.name;
        }
        String str5 = str2;
        if ((i & 8) != 0) {
            str3 = appMetadataFts.summary;
        }
        return appMetadataFts.copy(j2, str4, str5, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getRepoId() {
        return this.repoId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPackageName() {
        return this.packageName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getSummary() {
        return this.summary;
    }

    public final AppMetadataFts copy(long repoId, String packageName, String name, String summary) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return new AppMetadataFts(repoId, packageName, name, summary);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AppMetadataFts)) {
            return false;
        }
        AppMetadataFts appMetadataFts = (AppMetadataFts) other;
        return this.repoId == appMetadataFts.repoId && Intrinsics.areEqual(this.packageName, appMetadataFts.packageName) && Intrinsics.areEqual(this.name, appMetadataFts.name) && Intrinsics.areEqual(this.summary, appMetadataFts.summary);
    }

    public int hashCode() {
        int iM = ((LongObjectMap$$ExternalSyntheticBackport0.m(this.repoId) * 31) + this.packageName.hashCode()) * 31;
        String str = this.name;
        int iHashCode = (iM + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.summary;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "AppMetadataFts(repoId=" + this.repoId + ", packageName=" + this.packageName + ", name=" + this.name + ", summary=" + this.summary + ")";
    }

    public AppMetadataFts(long j, String packageName, String str, String str2) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.repoId = j;
        this.packageName = packageName;
        this.name = str;
        this.summary = str2;
    }

    public /* synthetic */ AppMetadataFts(long j, String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3);
    }

    public final long getRepoId() {
        return this.repoId;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final String getName() {
        return this.name;
    }

    public final String getSummary() {
        return this.summary;
    }
}
