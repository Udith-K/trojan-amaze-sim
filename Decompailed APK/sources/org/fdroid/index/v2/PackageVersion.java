package org.fdroid.index.v2;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: PackageV2.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0012\u0010\u000f\u001a\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001a\u00020\u0014X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Lorg/fdroid/index/v2/PackageVersion;", "", "versionCode", "", "getVersionCode", "()J", "signer", "Lorg/fdroid/index/v2/SignerV2;", "getSigner", "()Lorg/fdroid/index/v2/SignerV2;", "releaseChannels", "", "", "getReleaseChannels", "()Ljava/util/List;", "packageManifest", "Lorg/fdroid/index/v2/PackageManifest;", "getPackageManifest", "()Lorg/fdroid/index/v2/PackageManifest;", "hasKnownVulnerability", "", "getHasKnownVulnerability", "()Z", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface PackageVersion {
    boolean getHasKnownVulnerability();

    PackageManifest getPackageManifest();

    List<String> getReleaseChannels();

    SignerV2 getSigner();

    long getVersionCode();
}
