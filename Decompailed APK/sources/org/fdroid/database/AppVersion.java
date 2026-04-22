package org.fdroid.database;

import androidx.core.os.LocaleListCompat;
import ch.qos.logback.core.joran.action.Action;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.LocaleChooser;
import org.fdroid.index.v2.FileV1;
import org.fdroid.index.v2.FileV2;
import org.fdroid.index.v2.PermissionV2;

/* JADX INFO: compiled from: Version.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u00103\u001a\u0004\u0018\u00010\u00122\u0006\u00104\u001a\u000205J\u0018\u00106\u001a\u0004\u0018\u00010\u00122\u0006\u00107\u001a\u00020\u00122\u0006\u00104\u001a\u000205J\u000e\u00108\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\b9J\u0016\u0010:\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÀ\u0003¢\u0006\u0002\b;J%\u0010<\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0001J\u0013\u0010=\u001a\u00020\u00182\b\u0010>\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010?\u001a\u00020@HÖ\u0001J\t\u0010A\u001a\u00020\u0012HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00058\u0000X\u0081\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0010R\u0011\u0010\u0017\u001a\u00020\u00188F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u001b8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001e\u001a\u00020\u001f8F¢\u0006\u0006\u001a\u0004\b \u0010!R\u0013\u0010\"\u001a\u0004\u0018\u00010#8F¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020'0\u00058F¢\u0006\u0006\u001a\u0004\b(\u0010\fR\u0017\u0010)\u001a\b\u0012\u0004\u0012\u00020'0\u00058F¢\u0006\u0006\u001a\u0004\b*\u0010\fR\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00120\u00058F¢\u0006\u0006\u001a\u0004\b,\u0010\fR\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00120\u00058F¢\u0006\u0006\u001a\u0004\b.\u0010\fR\u0017\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00120\u00058F¢\u0006\u0006\u001a\u0004\b0\u0010\fR\u0017\u00101\u001a\b\u0012\u0004\u0012\u00020\u00120\u00058F¢\u0006\u0006\u001a\u0004\b2\u0010\f¨\u0006B"}, d2 = {"Lorg/fdroid/database/AppVersion;", "", "version", "Lorg/fdroid/database/Version;", "versionedStrings", "", "Lorg/fdroid/database/VersionedString;", "<init>", "(Lorg/fdroid/database/Version;Ljava/util/List;)V", "getVersion$database_release", "()Lorg/fdroid/database/Version;", "getVersionedStrings$database_release", "()Ljava/util/List;", "repoId", "", "getRepoId", "()J", "packageName", "", "getPackageName", "()Ljava/lang/String;", "added", "getAdded", "isCompatible", "", "()Z", "manifest", "Lorg/fdroid/database/AppManifest;", "getManifest", "()Lorg/fdroid/database/AppManifest;", Action.FILE_ATTRIBUTE, "Lorg/fdroid/index/v2/FileV1;", "getFile", "()Lorg/fdroid/index/v2/FileV1;", "src", "Lorg/fdroid/index/v2/FileV2;", "getSrc", "()Lorg/fdroid/index/v2/FileV2;", "usesPermission", "Lorg/fdroid/index/v2/PermissionV2;", "getUsesPermission", "usesPermissionSdk23", "getUsesPermissionSdk23", "featureNames", "getFeatureNames", "nativeCode", "getNativeCode", "releaseChannels", "getReleaseChannels", "antiFeatureKeys", "getAntiFeatureKeys", "getWhatsNew", "localeList", "Landroidx/core/os/LocaleListCompat;", "getAntiFeatureReason", "antiFeatureKey", "component1", "component1$database_release", "component2", "component2$database_release", "copy", "equals", "other", "hashCode", "", "toString", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class AppVersion {
    private final Version version;
    private final List<VersionedString> versionedStrings;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AppVersion copy$default(AppVersion appVersion, Version version, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            version = appVersion.version;
        }
        if ((i & 2) != 0) {
            list = appVersion.versionedStrings;
        }
        return appVersion.copy(version, list);
    }

    /* JADX INFO: renamed from: component1$database_release, reason: from getter */
    public final Version getVersion() {
        return this.version;
    }

    public final List<VersionedString> component2$database_release() {
        return this.versionedStrings;
    }

    public final AppVersion copy(Version version, List<VersionedString> versionedStrings) {
        Intrinsics.checkNotNullParameter(version, "version");
        return new AppVersion(version, versionedStrings);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AppVersion)) {
            return false;
        }
        AppVersion appVersion = (AppVersion) other;
        return Intrinsics.areEqual(this.version, appVersion.version) && Intrinsics.areEqual(this.versionedStrings, appVersion.versionedStrings);
    }

    public int hashCode() {
        int iHashCode = this.version.hashCode() * 31;
        List<VersionedString> list = this.versionedStrings;
        return iHashCode + (list == null ? 0 : list.hashCode());
    }

    public String toString() {
        return "AppVersion(version=" + this.version + ", versionedStrings=" + this.versionedStrings + ")";
    }

    public AppVersion(Version version, List<VersionedString> list) {
        Intrinsics.checkNotNullParameter(version, "version");
        this.version = version;
        this.versionedStrings = list;
    }

    public final Version getVersion$database_release() {
        return this.version;
    }

    public final List<VersionedString> getVersionedStrings$database_release() {
        return this.versionedStrings;
    }

    public final long getRepoId() {
        return this.version.getRepoId();
    }

    public final String getPackageName() {
        return this.version.getPackageName();
    }

    public final long getAdded() {
        return this.version.getAdded();
    }

    public final boolean isCompatible() {
        return this.version.isCompatible();
    }

    public final AppManifest getManifest() {
        return this.version.getManifest();
    }

    public final FileV1 getFile() {
        return this.version.getFile();
    }

    public final FileV2 getSrc() {
        return this.version.getSrc();
    }

    public final List<PermissionV2> getUsesPermission() {
        List<PermissionV2> permissions;
        List<VersionedString> list = this.versionedStrings;
        return (list == null || (permissions = VersionKt.getPermissions(list, this.version)) == null) ? CollectionsKt.emptyList() : permissions;
    }

    public final List<PermissionV2> getUsesPermissionSdk23() {
        List<PermissionV2> permissionsSdk23;
        List<VersionedString> list = this.versionedStrings;
        return (list == null || (permissionsSdk23 = VersionKt.getPermissionsSdk23(list, this.version)) == null) ? CollectionsKt.emptyList() : permissionsSdk23;
    }

    public final List<String> getFeatureNames() {
        List<String> features = this.version.getManifest().getFeatures();
        return features == null ? CollectionsKt.emptyList() : features;
    }

    public final List<String> getNativeCode() {
        List<String> nativecode = this.version.getManifest().getNativecode();
        return nativecode == null ? CollectionsKt.emptyList() : nativecode;
    }

    public final List<String> getReleaseChannels() {
        List<String> releaseChannels = this.version.getReleaseChannels();
        return releaseChannels == null ? CollectionsKt.emptyList() : releaseChannels;
    }

    public final List<String> getAntiFeatureKeys() {
        Map<String, Map<String, String>> antiFeatures = this.version.getAntiFeatures();
        if (antiFeatures == null) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList(antiFeatures.size());
        Iterator<Map.Entry<String, Map<String, String>>> it = antiFeatures.entrySet().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getKey());
        }
        return arrayList;
    }

    public final String getWhatsNew(LocaleListCompat localeList) {
        Intrinsics.checkNotNullParameter(localeList, "localeList");
        return (String) LocaleChooser.INSTANCE.getBestLocale(this.version.getWhatsNew(), localeList);
    }

    public final String getAntiFeatureReason(String antiFeatureKey, LocaleListCompat localeList) {
        Map<String, String> map;
        Intrinsics.checkNotNullParameter(antiFeatureKey, "antiFeatureKey");
        Intrinsics.checkNotNullParameter(localeList, "localeList");
        Map<String, Map<String, String>> antiFeatures = this.version.getAntiFeatures();
        if (antiFeatures == null || (map = antiFeatures.get(antiFeatureKey)) == null) {
            return null;
        }
        return (String) LocaleChooser.INSTANCE.getBestLocale(map, localeList);
    }
}
