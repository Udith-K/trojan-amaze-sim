package org.fdroid.database;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import androidx.compose.animation.ChangeSize$$ExternalSyntheticBackport0;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.fdroid.views.apps.AppListActivity;

/* JADX INFO: compiled from: Repository.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b)\b\u0081\b\u0018\u0000 42\u00020\u0001:\u00014Bu\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\f\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\f\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0010\u0010\u0011J\u000e\u0010$\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b%J\t\u0010&\u001a\u00020\u0005HÆ\u0003J\t\u0010'\u001a\u00020\u0007HÆ\u0003J\u0010\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0019J\u000b\u0010)\u001a\u0004\u0018\u00010\nHÆ\u0003J\u0011\u0010*\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\fHÆ\u0003J\u0011\u0010+\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\fHÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\nHÆ\u0003J\u0080\u0001\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\f2\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0002\u0010/J\u0013\u00100\u001a\u00020\u00072\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00102\u001a\u00020\u0005HÖ\u0001J\t\u00103\u001a\u00020\nHÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019R\u001e\u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0019\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0019\u0010\r\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b!\u0010 R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001eR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001e¨\u00065"}, d2 = {"Lorg/fdroid/database/RepositoryPreferences;", "", "repoId", "", "weight", "", "enabled", "", AppListActivity.SortClause.LAST_UPDATED, "lastETag", "", "userMirrors", "", "disabledMirrors", "username", "password", "<init>", "(JIZLjava/lang/Long;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "getRepoId$database_release", "()J", "getWeight", "()I", "getEnabled", "()Z", "getLastUpdated", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getLastETag$annotations", "()V", "getLastETag", "()Ljava/lang/String;", "getUserMirrors", "()Ljava/util/List;", "getDisabledMirrors", "getUsername", "getPassword", "component1", "component1$database_release", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JIZLjava/lang/Long;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)Lorg/fdroid/database/RepositoryPreferences;", "equals", "other", "hashCode", "toString", "Companion", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class RepositoryPreferences {
    public static final String TABLE = "RepositoryPreferences";
    private final List<String> disabledMirrors;
    private final boolean enabled;
    private final String lastETag;
    private final Long lastUpdated;
    private final String password;
    private final long repoId;
    private final List<String> userMirrors;
    private final String username;
    private final int weight;

    @Deprecated
    public static /* synthetic */ void getLastETag$annotations() {
    }

    /* JADX INFO: renamed from: component1$database_release, reason: from getter */
    public final long getRepoId() {
        return this.repoId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getWeight() {
        return this.weight;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getEnabled() {
        return this.enabled;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Long getLastUpdated() {
        return this.lastUpdated;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getLastETag() {
        return this.lastETag;
    }

    public final List<String> component6() {
        return this.userMirrors;
    }

    public final List<String> component7() {
        return this.disabledMirrors;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getUsername() {
        return this.username;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getPassword() {
        return this.password;
    }

    public final RepositoryPreferences copy(long repoId, int weight, boolean enabled, Long lastUpdated, String lastETag, List<String> userMirrors, List<String> disabledMirrors, String username, String password) {
        return new RepositoryPreferences(repoId, weight, enabled, lastUpdated, lastETag, userMirrors, disabledMirrors, username, password);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RepositoryPreferences)) {
            return false;
        }
        RepositoryPreferences repositoryPreferences = (RepositoryPreferences) other;
        return this.repoId == repositoryPreferences.repoId && this.weight == repositoryPreferences.weight && this.enabled == repositoryPreferences.enabled && Intrinsics.areEqual(this.lastUpdated, repositoryPreferences.lastUpdated) && Intrinsics.areEqual(this.lastETag, repositoryPreferences.lastETag) && Intrinsics.areEqual(this.userMirrors, repositoryPreferences.userMirrors) && Intrinsics.areEqual(this.disabledMirrors, repositoryPreferences.disabledMirrors) && Intrinsics.areEqual(this.username, repositoryPreferences.username) && Intrinsics.areEqual(this.password, repositoryPreferences.password);
    }

    public int hashCode() {
        int iM = ((((LongObjectMap$$ExternalSyntheticBackport0.m(this.repoId) * 31) + this.weight) * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.enabled)) * 31;
        Long l = this.lastUpdated;
        int iHashCode = (iM + (l == null ? 0 : l.hashCode())) * 31;
        String str = this.lastETag;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        List<String> list = this.userMirrors;
        int iHashCode3 = (iHashCode2 + (list == null ? 0 : list.hashCode())) * 31;
        List<String> list2 = this.disabledMirrors;
        int iHashCode4 = (iHashCode3 + (list2 == null ? 0 : list2.hashCode())) * 31;
        String str2 = this.username;
        int iHashCode5 = (iHashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.password;
        return iHashCode5 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "RepositoryPreferences(repoId=" + this.repoId + ", weight=" + this.weight + ", enabled=" + this.enabled + ", lastUpdated=" + this.lastUpdated + ", lastETag=" + this.lastETag + ", userMirrors=" + this.userMirrors + ", disabledMirrors=" + this.disabledMirrors + ", username=" + this.username + ", password=" + this.password + ")";
    }

    public RepositoryPreferences(long j, int i, boolean z, Long l, String str, List<String> list, List<String> list2, String str2, String str3) {
        this.repoId = j;
        this.weight = i;
        this.enabled = z;
        this.lastUpdated = l;
        this.lastETag = str;
        this.userMirrors = list;
        this.disabledMirrors = list2;
        this.username = str2;
        this.password = str3;
    }

    public final long getRepoId$database_release() {
        return this.repoId;
    }

    public final int getWeight() {
        return this.weight;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public /* synthetic */ RepositoryPreferences(long j, int i, boolean z, Long l, String str, List list, List list2, String str2, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, i, (i2 & 4) != 0 ? true : z, (i2 & 8) != 0 ? Long.valueOf(System.currentTimeMillis()) : l, (i2 & 16) != 0 ? null : str, (i2 & 32) != 0 ? null : list, (i2 & 64) != 0 ? null : list2, (i2 & 128) != 0 ? null : str2, (i2 & 256) != 0 ? null : str3);
    }

    public final Long getLastUpdated() {
        return this.lastUpdated;
    }

    public final String getLastETag() {
        return this.lastETag;
    }

    public final List<String> getUserMirrors() {
        return this.userMirrors;
    }

    public final List<String> getDisabledMirrors() {
        return this.disabledMirrors;
    }

    public final String getUsername() {
        return this.username;
    }

    public final String getPassword() {
        return this.password;
    }
}
