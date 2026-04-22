package org.fdroid.database;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.PackagePreference;
import org.fdroid.fdroid.Preferences;

/* JADX INFO: compiled from: AppPrefs.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u0000 -2\u00020\u0001:\u0001-B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u0005J\u0006\u0010\u001c\u001a\u00020\u0000J\u000e\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0005J\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\"\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0016\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\bHÀ\u0003¢\u0006\u0002\b$J@\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010&J\u0013\u0010'\u001a\u00020\u00152\b\u0010(\u001a\u0004\u0018\u00010)HÖ\u0003J\t\u0010*\u001a\u00020+HÖ\u0001J\t\u0010,\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0013¨\u0006."}, d2 = {"Lorg/fdroid/database/AppPrefs;", "Lorg/fdroid/PackagePreference;", "packageName", "", "ignoreVersionCodeUpdate", "", "preferredRepoId", "appPrefReleaseChannels", "", "<init>", "(Ljava/lang/String;JLjava/lang/Long;Ljava/util/List;)V", "getPackageName", "()Ljava/lang/String;", "getIgnoreVersionCodeUpdate", "()J", "getPreferredRepoId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getAppPrefReleaseChannels$database_release", "()Ljava/util/List;", "ignoreAllUpdates", "", "getIgnoreAllUpdates", "()Z", "releaseChannels", "getReleaseChannels", "shouldIgnoreUpdate", "versionCode", "toggleIgnoreAllUpdates", "toggleIgnoreVersionCodeUpdate", "toggleReleaseChannel", "releaseChannel", "component1", "component2", "component3", "component4", "component4$database_release", "copy", "(Ljava/lang/String;JLjava/lang/Long;Ljava/util/List;)Lorg/fdroid/database/AppPrefs;", "equals", "other", "", "hashCode", "", "toString", "Companion", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class AppPrefs implements PackagePreference {
    public static final String TABLE = "AppPrefs";
    private final List<String> appPrefReleaseChannels;
    private final long ignoreVersionCodeUpdate;
    private final String packageName;
    private final Long preferredRepoId;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AppPrefs copy$default(AppPrefs appPrefs, String str, long j, Long l, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = appPrefs.packageName;
        }
        if ((i & 2) != 0) {
            j = appPrefs.ignoreVersionCodeUpdate;
        }
        long j2 = j;
        if ((i & 4) != 0) {
            l = appPrefs.preferredRepoId;
        }
        Long l2 = l;
        if ((i & 8) != 0) {
            list = appPrefs.appPrefReleaseChannels;
        }
        return appPrefs.copy(str, j2, l2, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPackageName() {
        return this.packageName;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getIgnoreVersionCodeUpdate() {
        return this.ignoreVersionCodeUpdate;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Long getPreferredRepoId() {
        return this.preferredRepoId;
    }

    public final List<String> component4$database_release() {
        return this.appPrefReleaseChannels;
    }

    public final AppPrefs copy(String packageName, long ignoreVersionCodeUpdate, Long preferredRepoId, List<String> appPrefReleaseChannels) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        return new AppPrefs(packageName, ignoreVersionCodeUpdate, preferredRepoId, appPrefReleaseChannels);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AppPrefs)) {
            return false;
        }
        AppPrefs appPrefs = (AppPrefs) other;
        return Intrinsics.areEqual(this.packageName, appPrefs.packageName) && this.ignoreVersionCodeUpdate == appPrefs.ignoreVersionCodeUpdate && Intrinsics.areEqual(this.preferredRepoId, appPrefs.preferredRepoId) && Intrinsics.areEqual(this.appPrefReleaseChannels, appPrefs.appPrefReleaseChannels);
    }

    public int hashCode() {
        int iHashCode = ((this.packageName.hashCode() * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.ignoreVersionCodeUpdate)) * 31;
        Long l = this.preferredRepoId;
        int iHashCode2 = (iHashCode + (l == null ? 0 : l.hashCode())) * 31;
        List<String> list = this.appPrefReleaseChannels;
        return iHashCode2 + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        return "AppPrefs(packageName=" + this.packageName + ", ignoreVersionCodeUpdate=" + this.ignoreVersionCodeUpdate + ", preferredRepoId=" + this.preferredRepoId + ", appPrefReleaseChannels=" + this.appPrefReleaseChannels + ")";
    }

    public AppPrefs(String packageName, long j, Long l, List<String> list) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        this.packageName = packageName;
        this.ignoreVersionCodeUpdate = j;
        this.preferredRepoId = l;
        this.appPrefReleaseChannels = list;
    }

    public /* synthetic */ AppPrefs(String str, long j, Long l, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? 0L : j, (i & 4) != 0 ? null : l, (i & 8) != 0 ? null : list);
    }

    public final String getPackageName() {
        return this.packageName;
    }

    @Override // org.fdroid.PackagePreference
    public long getIgnoreVersionCodeUpdate() {
        return this.ignoreVersionCodeUpdate;
    }

    public final Long getPreferredRepoId() {
        return this.preferredRepoId;
    }

    public final List<String> getAppPrefReleaseChannels$database_release() {
        return this.appPrefReleaseChannels;
    }

    public final boolean getIgnoreAllUpdates() {
        return getIgnoreVersionCodeUpdate() == Preferences.UPDATE_INTERVAL_DISABLED;
    }

    @Override // org.fdroid.PackagePreference
    public List<String> getReleaseChannels() {
        List<String> list = this.appPrefReleaseChannels;
        return list == null ? CollectionsKt.emptyList() : list;
    }

    public final boolean shouldIgnoreUpdate(long versionCode) {
        return getIgnoreVersionCodeUpdate() >= versionCode;
    }

    public final AppPrefs toggleIgnoreAllUpdates() {
        return copy$default(this, null, getIgnoreAllUpdates() ? 0L : Preferences.UPDATE_INTERVAL_DISABLED, null, null, 13, null);
    }

    public final AppPrefs toggleIgnoreVersionCodeUpdate(long versionCode) {
        if (shouldIgnoreUpdate(versionCode)) {
            versionCode = 0;
        }
        return copy$default(this, null, versionCode, null, null, 13, null);
    }

    public final AppPrefs toggleReleaseChannel(String releaseChannel) {
        List arrayList;
        Intrinsics.checkNotNullParameter(releaseChannel, "releaseChannel");
        List<String> list = this.appPrefReleaseChannels;
        if (list != null && list.contains(releaseChannel)) {
            arrayList = CollectionsKt.toMutableList((Collection) this.appPrefReleaseChannels);
            arrayList.remove(releaseChannel);
            Unit unit = Unit.INSTANCE;
        } else {
            List<String> list2 = this.appPrefReleaseChannels;
            if (list2 == null || (arrayList = CollectionsKt.toMutableList((Collection) list2)) == null) {
                arrayList = new ArrayList();
            }
            arrayList.add(releaseChannel);
            Unit unit2 = Unit.INSTANCE;
        }
        return copy$default(this, null, 0L, null, arrayList, 7, null);
    }
}
