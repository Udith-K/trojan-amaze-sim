package org.fdroid.database;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.index.v2.PackageManifest;
import org.fdroid.index.v2.SignerV2;
import org.fdroid.index.v2.UsesSdkV2;

/* JADX INFO: compiled from: Version.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b!\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B_\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\r\u0012\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\r¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0005HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u0010\u0010(\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0018J\u000b\u0010)\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u0011\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\rHÆ\u0003J\u0011\u0010+\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\rHÆ\u0003Jj\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\r2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\rHÆ\u0001¢\u0006\u0002\u0010-J\u0013\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u000101HÖ\u0003J\t\u00102\u001a\u00020\tHÖ\u0001J\t\u00103\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0018\u0010\b\u001a\u0004\u0018\u00010\tX\u0096\u0004¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0018\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001c\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0019\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001dR\u0016\u0010\u001f\u001a\u0004\u0018\u00010\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u0018R\u001c\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u001dR\u0016\u0010#\u001a\u0004\u0018\u00010\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u0018¨\u00064"}, d2 = {"Lorg/fdroid/database/AppManifest;", "Lorg/fdroid/index/v2/PackageManifest;", "versionName", "", "versionCode", "", "usesSdk", "Lorg/fdroid/index/v2/UsesSdkV2;", "maxSdkVersion", "", "signer", "Lorg/fdroid/index/v2/SignerV2;", "nativecode", "", "features", "<init>", "(Ljava/lang/String;JLorg/fdroid/index/v2/UsesSdkV2;Ljava/lang/Integer;Lorg/fdroid/index/v2/SignerV2;Ljava/util/List;Ljava/util/List;)V", "getVersionName", "()Ljava/lang/String;", "getVersionCode", "()J", "getUsesSdk", "()Lorg/fdroid/index/v2/UsesSdkV2;", "getMaxSdkVersion", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getSigner", "()Lorg/fdroid/index/v2/SignerV2;", "getNativecode", "()Ljava/util/List;", "getFeatures", "minSdkVersion", "getMinSdkVersion", "featureNames", "getFeatureNames", "targetSdkVersion", "getTargetSdkVersion", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;JLorg/fdroid/index/v2/UsesSdkV2;Ljava/lang/Integer;Lorg/fdroid/index/v2/SignerV2;Ljava/util/List;Ljava/util/List;)Lorg/fdroid/database/AppManifest;", "equals", "", "other", "", "hashCode", "toString", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class AppManifest implements PackageManifest {
    private final List<String> features;
    private final Integer maxSdkVersion;
    private final List<String> nativecode;
    private final SignerV2 signer;
    private final UsesSdkV2 usesSdk;
    private final long versionCode;
    private final String versionName;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getVersionName() {
        return this.versionName;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getVersionCode() {
        return this.versionCode;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final UsesSdkV2 getUsesSdk() {
        return this.usesSdk;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Integer getMaxSdkVersion() {
        return this.maxSdkVersion;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final SignerV2 getSigner() {
        return this.signer;
    }

    public final List<String> component6() {
        return this.nativecode;
    }

    public final List<String> component7() {
        return this.features;
    }

    public final AppManifest copy(String versionName, long versionCode, UsesSdkV2 usesSdk, Integer maxSdkVersion, SignerV2 signer, List<String> nativecode, List<String> features) {
        Intrinsics.checkNotNullParameter(versionName, "versionName");
        return new AppManifest(versionName, versionCode, usesSdk, maxSdkVersion, signer, nativecode, features);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AppManifest)) {
            return false;
        }
        AppManifest appManifest = (AppManifest) other;
        return Intrinsics.areEqual(this.versionName, appManifest.versionName) && this.versionCode == appManifest.versionCode && Intrinsics.areEqual(this.usesSdk, appManifest.usesSdk) && Intrinsics.areEqual(this.maxSdkVersion, appManifest.maxSdkVersion) && Intrinsics.areEqual(this.signer, appManifest.signer) && Intrinsics.areEqual(this.nativecode, appManifest.nativecode) && Intrinsics.areEqual(this.features, appManifest.features);
    }

    public int hashCode() {
        int iHashCode = ((this.versionName.hashCode() * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.versionCode)) * 31;
        UsesSdkV2 usesSdkV2 = this.usesSdk;
        int iHashCode2 = (iHashCode + (usesSdkV2 == null ? 0 : usesSdkV2.hashCode())) * 31;
        Integer num = this.maxSdkVersion;
        int iHashCode3 = (iHashCode2 + (num == null ? 0 : num.hashCode())) * 31;
        SignerV2 signerV2 = this.signer;
        int iHashCode4 = (iHashCode3 + (signerV2 == null ? 0 : signerV2.hashCode())) * 31;
        List<String> list = this.nativecode;
        int iHashCode5 = (iHashCode4 + (list == null ? 0 : list.hashCode())) * 31;
        List<String> list2 = this.features;
        return iHashCode5 + (list2 != null ? list2.hashCode() : 0);
    }

    public String toString() {
        return "AppManifest(versionName=" + this.versionName + ", versionCode=" + this.versionCode + ", usesSdk=" + this.usesSdk + ", maxSdkVersion=" + this.maxSdkVersion + ", signer=" + this.signer + ", nativecode=" + this.nativecode + ", features=" + this.features + ")";
    }

    public AppManifest(String versionName, long j, UsesSdkV2 usesSdkV2, Integer num, SignerV2 signerV2, List<String> list, List<String> list2) {
        Intrinsics.checkNotNullParameter(versionName, "versionName");
        this.versionName = versionName;
        this.versionCode = j;
        this.usesSdk = usesSdkV2;
        this.maxSdkVersion = num;
        this.signer = signerV2;
        this.nativecode = list;
        this.features = list2;
    }

    public final String getVersionName() {
        return this.versionName;
    }

    public final long getVersionCode() {
        return this.versionCode;
    }

    public final UsesSdkV2 getUsesSdk() {
        return this.usesSdk;
    }

    @Override // org.fdroid.index.v2.PackageManifest
    public Integer getMaxSdkVersion() {
        return this.maxSdkVersion;
    }

    public final SignerV2 getSigner() {
        return this.signer;
    }

    public /* synthetic */ AppManifest(String str, long j, UsesSdkV2 usesSdkV2, Integer num, SignerV2 signerV2, List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, j, (i & 4) != 0 ? null : usesSdkV2, (i & 8) != 0 ? null : num, (i & 16) != 0 ? null : signerV2, (i & 32) != 0 ? CollectionsKt.emptyList() : list, (i & 64) != 0 ? CollectionsKt.emptyList() : list2);
    }

    @Override // org.fdroid.index.v2.PackageManifest
    public List<String> getNativecode() {
        return this.nativecode;
    }

    public final List<String> getFeatures() {
        return this.features;
    }

    @Override // org.fdroid.index.v2.PackageManifest
    public Integer getMinSdkVersion() {
        UsesSdkV2 usesSdkV2 = this.usesSdk;
        if (usesSdkV2 != null) {
            return Integer.valueOf(usesSdkV2.getMinSdkVersion());
        }
        return null;
    }

    @Override // org.fdroid.index.v2.PackageManifest
    public List<String> getFeatureNames() {
        return this.features;
    }

    @Override // org.fdroid.index.v2.PackageManifest
    public Integer getTargetSdkVersion() {
        UsesSdkV2 usesSdkV2 = this.usesSdk;
        if (usesSdkV2 != null) {
            return Integer.valueOf(usesSdkV2.getTargetSdkVersion());
        }
        return null;
    }
}
