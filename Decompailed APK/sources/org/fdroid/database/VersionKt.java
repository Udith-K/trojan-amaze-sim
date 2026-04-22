package org.fdroid.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.fdroid.nearby.peers.BonjourPeer;
import org.fdroid.index.v2.FeatureV2;
import org.fdroid.index.v2.ManifestV2;
import org.fdroid.index.v2.PackageVersionV2;
import org.fdroid.index.v2.PermissionV2;
import org.fdroid.index.v2.SignerV2;
import org.fdroid.index.v2.UsesSdkV2;

/* JADX INFO: compiled from: Version.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a,\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0000\u001a\f\u0010\n\u001a\u00020\u000b*\u00020\fH\u0000\u001a(\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e*\b\u0012\u0004\u0012\u00020\u00100\u000e2\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0013H\u0000\u001a\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e*\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0001H\u0000\u001a \u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00100\u000e*\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0011\u001a\u00020\u0001H\u0000\u001a \u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00100\u000e*\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0011\u001a\u00020\u0001H\u0000\u001a7\u0010\u0017\u001a\u0004\u0018\u0001H\u0018\"\u0004\b\u0000\u0010\u0018*\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u00132\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001cH\u0002¢\u0006\u0002\u0010\u001d¨\u0006\u001e"}, d2 = {"toVersion", "Lorg/fdroid/database/Version;", "Lorg/fdroid/index/v2/PackageVersionV2;", "repoId", "", "packageName", "", "versionId", "isCompatible", "", "toManifest", "Lorg/fdroid/database/AppManifest;", "Lorg/fdroid/index/v2/ManifestV2;", "toVersionedString", "", "Lorg/fdroid/database/VersionedString;", "Lorg/fdroid/index/v2/PermissionV2;", "version", BonjourPeer.TYPE, "Lorg/fdroid/database/VersionedStringType;", "getVersionedStrings", "getPermissions", "getPermissionsSdk23", "map", "T", "v", "wantedType", "factory", "Lkotlin/Function0;", "(Lorg/fdroid/database/VersionedString;Lorg/fdroid/database/Version;Lorg/fdroid/database/VersionedStringType;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "database_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class VersionKt {
    public static final Version toVersion(PackageVersionV2 packageVersionV2, long j, String packageName, String versionId, boolean z) {
        Intrinsics.checkNotNullParameter(packageVersionV2, "<this>");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(versionId, "versionId");
        return new Version(j, packageName, versionId, packageVersionV2.getAdded(), packageVersionV2.getFile(), packageVersionV2.getSrc(), toManifest(packageVersionV2.getManifest()), packageVersionV2.getReleaseChannels(), packageVersionV2.getAntiFeatures(), packageVersionV2.getWhatsNew(), z);
    }

    public static final AppManifest toManifest(ManifestV2 manifestV2) {
        Intrinsics.checkNotNullParameter(manifestV2, "<this>");
        String versionName = manifestV2.getVersionName();
        long versionCode = manifestV2.getVersionCode();
        UsesSdkV2 usesSdk = manifestV2.getUsesSdk();
        Integer maxSdkVersion = manifestV2.getMaxSdkVersion();
        SignerV2 signer = manifestV2.getSigner();
        List<String> nativecode = manifestV2.getNativecode();
        List<FeatureV2> features = manifestV2.getFeatures();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(features, 10));
        Iterator<T> it = features.iterator();
        while (it.hasNext()) {
            arrayList.add(((FeatureV2) it.next()).getName());
        }
        return new AppManifest(versionName, versionCode, usesSdk, maxSdkVersion, signer, nativecode, arrayList);
    }

    public static final List<VersionedString> getVersionedStrings(ManifestV2 manifestV2, Version version) {
        Intrinsics.checkNotNullParameter(manifestV2, "<this>");
        Intrinsics.checkNotNullParameter(version, "version");
        return CollectionsKt.plus((Collection) toVersionedString(manifestV2.getUsesPermission(), version, VersionedStringType.PERMISSION), (Iterable) toVersionedString(manifestV2.getUsesPermissionSdk23(), version, VersionedStringType.PERMISSION_SDK_23));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PermissionV2 getPermissions$lambda$3$lambda$2(VersionedString versionedString) {
        return new PermissionV2(versionedString.getName(), versionedString.getVersion());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PermissionV2 getPermissionsSdk23$lambda$5$lambda$4(VersionedString versionedString) {
        return new PermissionV2(versionedString.getName(), versionedString.getVersion());
    }

    private static final <T> T map(VersionedString versionedString, Version version, VersionedStringType versionedStringType, Function0 function0) {
        if (versionedString.getRepoId() == version.getRepoId() && Intrinsics.areEqual(versionedString.getPackageName(), version.getPackageName()) && Intrinsics.areEqual(versionedString.getVersionId(), version.getVersionId()) && versionedString.getType() == versionedStringType) {
            return (T) function0.invoke();
        }
        return null;
    }

    public static final List<VersionedString> toVersionedString(List<PermissionV2> list, Version version, VersionedStringType type) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(type, "type");
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (PermissionV2 permissionV2 : list) {
            arrayList.add(new VersionedString(version.getRepoId(), version.getPackageName(), version.getVersionId(), type, permissionV2.getName(), permissionV2.getMaxSdkVersion()));
        }
        return arrayList;
    }

    public static final List<PermissionV2> getPermissions(List<VersionedString> list, Version version) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(version, "version");
        ArrayList arrayList = new ArrayList();
        for (final VersionedString versionedString : list) {
            PermissionV2 permissionV2 = (PermissionV2) map(versionedString, version, VersionedStringType.PERMISSION, new Function0() { // from class: org.fdroid.database.VersionKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return VersionKt.getPermissions$lambda$3$lambda$2(versionedString);
                }
            });
            if (permissionV2 != null) {
                arrayList.add(permissionV2);
            }
        }
        return arrayList;
    }

    public static final List<PermissionV2> getPermissionsSdk23(List<VersionedString> list, Version version) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(version, "version");
        ArrayList arrayList = new ArrayList();
        for (final VersionedString versionedString : list) {
            PermissionV2 permissionV2 = (PermissionV2) map(versionedString, version, VersionedStringType.PERMISSION_SDK_23, new Function0() { // from class: org.fdroid.database.VersionKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return VersionKt.getPermissionsSdk23$lambda$5$lambda$4(versionedString);
                }
            });
            if (permissionV2 != null) {
                arrayList.add(permissionV2);
            }
        }
        return arrayList;
    }
}
