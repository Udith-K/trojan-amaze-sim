package org.fdroid.database;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import androidx.compose.animation.ChangeSize$$ExternalSyntheticBackport0;
import ch.qos.logback.core.joran.action.Action;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.index.v2.FileV1;
import org.fdroid.index.v2.FileV2;
import org.fdroid.index.v2.PackageManifest;
import org.fdroid.index.v2.PackageV2Kt;
import org.fdroid.index.v2.PackageVersion;
import org.fdroid.index.v2.SignerV2;

/* JADX INFO: compiled from: Version.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0081\b\u0018\u0000 M2\u00020\u0001:\u0001MB£\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000f\u0012&\b\u0002\u0010\u0010\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0011j\u0002`\u0012\u0018\u00010\u0011\u0012\u001c\b\u0002\u0010\u0013\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0011j\u0004\u0018\u0001`\u0012\u0012\u0006\u0010\u0014\u001a\u00020\u0015¢\u0006\u0004\b\u0016\u0010\u0017J\u001b\u00106\u001a\u0002072\f\u00108\u001a\b\u0012\u0004\u0012\u0002090\u000fH\u0000¢\u0006\u0002\b:J\t\u0010;\u001a\u00020\u0003HÆ\u0003J\t\u0010<\u001a\u00020\u0005HÆ\u0003J\t\u0010=\u001a\u00020\u0005HÆ\u0003J\t\u0010>\u001a\u00020\u0003HÆ\u0003J\t\u0010?\u001a\u00020\tHÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\t\u0010A\u001a\u00020\rHÆ\u0003J\u0011\u0010B\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000fHÆ\u0003J'\u0010C\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0011j\u0002`\u0012\u0018\u00010\u0011HÆ\u0003J\u001d\u0010D\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0011j\u0004\u0018\u0001`\u0012HÆ\u0003J\t\u0010E\u001a\u00020\u0015HÆ\u0003J³\u0001\u0010F\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000f2&\b\u0002\u0010\u0010\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0011j\u0002`\u0012\u0018\u00010\u00112\u001c\b\u0002\u0010\u0013\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0011j\u0004\u0018\u0001`\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u0015HÆ\u0001J\u0013\u0010G\u001a\u00020\u00152\b\u0010H\u001a\u0004\u0018\u00010IHÖ\u0003J\t\u0010J\u001a\u00020KHÖ\u0001J\t\u0010L\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u0016\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0018\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0016\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u001c\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R/\u0010\u0010\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0011j\u0002`\u0012\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R%\u0010\u0013\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0011j\u0004\u0018\u0001`\u0012¢\u0006\b\n\u0000\u001a\u0004\b(\u0010'R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010)R\u0014\u0010*\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b+\u0010\u0019R\u0016\u0010,\u001a\u0004\u0018\u00010-8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R\u0014\u00100\u001a\u0002018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b2\u00103R\u0014\u00104\u001a\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b5\u0010)¨\u0006N"}, d2 = {"Lorg/fdroid/database/Version;", "Lorg/fdroid/index/v2/PackageVersion;", "repoId", "", "packageName", "", "versionId", "added", Action.FILE_ATTRIBUTE, "Lorg/fdroid/index/v2/FileV1;", "src", "Lorg/fdroid/index/v2/FileV2;", "manifest", "Lorg/fdroid/database/AppManifest;", "releaseChannels", "", "antiFeatures", "", "Lorg/fdroid/index/v2/LocalizedTextV2;", "whatsNew", "isCompatible", "", "<init>", "(JLjava/lang/String;Ljava/lang/String;JLorg/fdroid/index/v2/FileV1;Lorg/fdroid/index/v2/FileV2;Lorg/fdroid/database/AppManifest;Ljava/util/List;Ljava/util/Map;Ljava/util/Map;Z)V", "getRepoId", "()J", "getPackageName", "()Ljava/lang/String;", "getVersionId", "getAdded", "getFile", "()Lorg/fdroid/index/v2/FileV1;", "getSrc", "()Lorg/fdroid/index/v2/FileV2;", "getManifest", "()Lorg/fdroid/database/AppManifest;", "getReleaseChannels", "()Ljava/util/List;", "getAntiFeatures", "()Ljava/util/Map;", "getWhatsNew", "()Z", "versionCode", "getVersionCode", "signer", "Lorg/fdroid/index/v2/SignerV2;", "getSigner", "()Lorg/fdroid/index/v2/SignerV2;", "packageManifest", "Lorg/fdroid/index/v2/PackageManifest;", "getPackageManifest", "()Lorg/fdroid/index/v2/PackageManifest;", "hasKnownVulnerability", "getHasKnownVulnerability", "toAppVersion", "Lorg/fdroid/database/AppVersion;", "versionedStrings", "Lorg/fdroid/database/VersionedString;", "toAppVersion$database_release", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "equals", "other", "", "hashCode", "", "toString", "Companion", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class Version implements PackageVersion {
    public static final String TABLE = "Version";
    private final long added;
    private final Map<String, Map<String, String>> antiFeatures;
    private final FileV1 file;
    private final boolean isCompatible;
    private final AppManifest manifest;
    private final String packageName;
    private final List<String> releaseChannels;
    private final long repoId;
    private final FileV2 src;
    private final String versionId;
    private final Map<String, String> whatsNew;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getRepoId() {
        return this.repoId;
    }

    public final Map<String, String> component10() {
        return this.whatsNew;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final boolean getIsCompatible() {
        return this.isCompatible;
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
    public final long getAdded() {
        return this.added;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final FileV1 getFile() {
        return this.file;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final FileV2 getSrc() {
        return this.src;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final AppManifest getManifest() {
        return this.manifest;
    }

    public final List<String> component8() {
        return this.releaseChannels;
    }

    public final Map<String, Map<String, String>> component9() {
        return this.antiFeatures;
    }

    public final Version copy(long repoId, String packageName, String versionId, long added, FileV1 file, FileV2 src, AppManifest manifest, List<String> releaseChannels, Map<String, ? extends Map<String, String>> antiFeatures, Map<String, String> whatsNew, boolean isCompatible) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(versionId, "versionId");
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(manifest, "manifest");
        return new Version(repoId, packageName, versionId, added, file, src, manifest, releaseChannels, antiFeatures, whatsNew, isCompatible);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Version)) {
            return false;
        }
        Version version = (Version) other;
        return this.repoId == version.repoId && Intrinsics.areEqual(this.packageName, version.packageName) && Intrinsics.areEqual(this.versionId, version.versionId) && this.added == version.added && Intrinsics.areEqual(this.file, version.file) && Intrinsics.areEqual(this.src, version.src) && Intrinsics.areEqual(this.manifest, version.manifest) && Intrinsics.areEqual(this.releaseChannels, version.releaseChannels) && Intrinsics.areEqual(this.antiFeatures, version.antiFeatures) && Intrinsics.areEqual(this.whatsNew, version.whatsNew) && this.isCompatible == version.isCompatible;
    }

    public int hashCode() {
        int iM = ((((((((LongObjectMap$$ExternalSyntheticBackport0.m(this.repoId) * 31) + this.packageName.hashCode()) * 31) + this.versionId.hashCode()) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.added)) * 31) + this.file.hashCode()) * 31;
        FileV2 fileV2 = this.src;
        int iHashCode = (((iM + (fileV2 == null ? 0 : fileV2.hashCode())) * 31) + this.manifest.hashCode()) * 31;
        List<String> list = this.releaseChannels;
        int iHashCode2 = (iHashCode + (list == null ? 0 : list.hashCode())) * 31;
        Map<String, Map<String, String>> map = this.antiFeatures;
        int iHashCode3 = (iHashCode2 + (map == null ? 0 : map.hashCode())) * 31;
        Map<String, String> map2 = this.whatsNew;
        return ((iHashCode3 + (map2 != null ? map2.hashCode() : 0)) * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.isCompatible);
    }

    public String toString() {
        return "Version(repoId=" + this.repoId + ", packageName=" + this.packageName + ", versionId=" + this.versionId + ", added=" + this.added + ", file=" + this.file + ", src=" + this.src + ", manifest=" + this.manifest + ", releaseChannels=" + this.releaseChannels + ", antiFeatures=" + this.antiFeatures + ", whatsNew=" + this.whatsNew + ", isCompatible=" + this.isCompatible + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Version(long j, String packageName, String versionId, long j2, FileV1 file, FileV2 fileV2, AppManifest manifest, List<String> list, Map<String, ? extends Map<String, String>> map, Map<String, String> map2, boolean z) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(versionId, "versionId");
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(manifest, "manifest");
        this.repoId = j;
        this.packageName = packageName;
        this.versionId = versionId;
        this.added = j2;
        this.file = file;
        this.src = fileV2;
        this.manifest = manifest;
        this.releaseChannels = list;
        this.antiFeatures = map;
        this.whatsNew = map2;
        this.isCompatible = z;
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

    public final long getAdded() {
        return this.added;
    }

    public final FileV1 getFile() {
        return this.file;
    }

    public final FileV2 getSrc() {
        return this.src;
    }

    public final AppManifest getManifest() {
        return this.manifest;
    }

    public /* synthetic */ Version(long j, String str, String str2, long j2, FileV1 fileV1, FileV2 fileV2, AppManifest appManifest, List list, Map map, Map map2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, str, str2, j2, fileV1, (i & 32) != 0 ? null : fileV2, appManifest, (i & 128) != 0 ? CollectionsKt.emptyList() : list, (i & 256) != 0 ? null : map, (i & 512) != 0 ? null : map2, z);
    }

    @Override // org.fdroid.index.v2.PackageVersion
    public List<String> getReleaseChannels() {
        return this.releaseChannels;
    }

    public final Map<String, Map<String, String>> getAntiFeatures() {
        return this.antiFeatures;
    }

    public final Map<String, String> getWhatsNew() {
        return this.whatsNew;
    }

    public final boolean isCompatible() {
        return this.isCompatible;
    }

    @Override // org.fdroid.index.v2.PackageVersion
    public long getVersionCode() {
        return this.manifest.getVersionCode();
    }

    @Override // org.fdroid.index.v2.PackageVersion
    public SignerV2 getSigner() {
        return this.manifest.getSigner();
    }

    @Override // org.fdroid.index.v2.PackageVersion
    public PackageManifest getPackageManifest() {
        return this.manifest;
    }

    @Override // org.fdroid.index.v2.PackageVersion
    public boolean getHasKnownVulnerability() {
        Map<String, Map<String, String>> map = this.antiFeatures;
        return map != null && map.containsKey(PackageV2Kt.ANTI_FEATURE_KNOWN_VULNERABILITY);
    }

    public final AppVersion toAppVersion$database_release(List<VersionedString> versionedStrings) {
        Intrinsics.checkNotNullParameter(versionedStrings, "versionedStrings");
        return new AppVersion(this, versionedStrings);
    }
}
