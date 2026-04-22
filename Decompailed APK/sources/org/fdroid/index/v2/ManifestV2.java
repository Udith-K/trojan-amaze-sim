package org.fdroid.index.v2;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: PackageV2.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 I2\u00020\u0001:\u0002HIB{\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\r\u0012\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\r¢\u0006\u0004\b\u0013\u0010\u0014B¯\u0001\b\u0010\u0012\u0006\u0010\u0015\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u0012\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u0012\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\r\u0012\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\r\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\t\u0012\u000e\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\r\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a¢\u0006\u0004\b\u0013\u0010\u001bJ\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0005HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u0010\u00102\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010#J\u000b\u00103\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000f\u00104\u001a\b\u0012\u0004\u0012\u00020\u000e0\rHÆ\u0003J\u000f\u00105\u001a\b\u0012\u0004\u0012\u00020\u000e0\rHÆ\u0003J\u000f\u00106\u001a\b\u0012\u0004\u0012\u00020\u00030\rHÆ\u0003J\u000f\u00107\u001a\b\u0012\u0004\u0012\u00020\u00120\rHÆ\u0003J\u0086\u0001\u00108\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\r2\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\rHÆ\u0001¢\u0006\u0002\u00109J\u0013\u0010:\u001a\u00020;2\b\u0010<\u001a\u0004\u0018\u00010=HÖ\u0003J\t\u0010>\u001a\u00020\tHÖ\u0001J\t\u0010?\u001a\u00020\u0003HÖ\u0001J%\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020\u00002\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020FH\u0001¢\u0006\u0002\bGR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0018\u0010\b\u001a\u0004\u0018\u00010\tX\u0096\u0004¢\u0006\n\n\u0002\u0010$\u001a\u0004\b\"\u0010#R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b)\u0010(R\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010(R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\r¢\u0006\b\n\u0000\u001a\u0004\b+\u0010(R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\tX\u0096\u0004¢\u0006\n\n\u0002\u0010$\u001a\u0004\b,\u0010#R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010(R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\tX\u0096\u0004¢\u0006\n\n\u0002\u0010$\u001a\u0004\b.\u0010#¨\u0006J"}, d2 = {"Lorg/fdroid/index/v2/ManifestV2;", "Lorg/fdroid/index/v2/PackageManifest;", "versionName", "", "versionCode", "", "usesSdk", "Lorg/fdroid/index/v2/UsesSdkV2;", "maxSdkVersion", "", "signer", "Lorg/fdroid/index/v2/SignerV2;", "usesPermission", "", "Lorg/fdroid/index/v2/PermissionV2;", "usesPermissionSdk23", "nativecode", "features", "Lorg/fdroid/index/v2/FeatureV2;", "<init>", "(Ljava/lang/String;JLorg/fdroid/index/v2/UsesSdkV2;Ljava/lang/Integer;Lorg/fdroid/index/v2/SignerV2;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "seen0", "minSdkVersion", "featureNames", "targetSdkVersion", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;JLorg/fdroid/index/v2/UsesSdkV2;Ljava/lang/Integer;Lorg/fdroid/index/v2/SignerV2;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/Integer;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getVersionName", "()Ljava/lang/String;", "getVersionCode", "()J", "getUsesSdk", "()Lorg/fdroid/index/v2/UsesSdkV2;", "getMaxSdkVersion", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getSigner", "()Lorg/fdroid/index/v2/SignerV2;", "getUsesPermission", "()Ljava/util/List;", "getUsesPermissionSdk23", "getNativecode", "getFeatures", "getMinSdkVersion", "getFeatureNames", "getTargetSdkVersion", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;JLorg/fdroid/index/v2/UsesSdkV2;Ljava/lang/Integer;Lorg/fdroid/index/v2/SignerV2;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)Lorg/fdroid/index/v2/ManifestV2;", "equals", "", "other", "", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$index_release", "$serializer", "Companion", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class ManifestV2 implements PackageManifest {
    private static final KSerializer[] $childSerializers;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final List<String> featureNames;
    private final List<FeatureV2> features;
    private final Integer maxSdkVersion;
    private final Integer minSdkVersion;
    private final List<String> nativecode;
    private final SignerV2 signer;
    private final Integer targetSdkVersion;
    private final List<PermissionV2> usesPermission;
    private final List<PermissionV2> usesPermissionSdk23;
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

    public final List<PermissionV2> component6() {
        return this.usesPermission;
    }

    public final List<PermissionV2> component7() {
        return this.usesPermissionSdk23;
    }

    public final List<String> component8() {
        return this.nativecode;
    }

    public final List<FeatureV2> component9() {
        return this.features;
    }

    public final ManifestV2 copy(String versionName, long versionCode, UsesSdkV2 usesSdk, Integer maxSdkVersion, SignerV2 signer, List<PermissionV2> usesPermission, List<PermissionV2> usesPermissionSdk23, List<String> nativecode, List<FeatureV2> features) {
        Intrinsics.checkNotNullParameter(versionName, "versionName");
        Intrinsics.checkNotNullParameter(usesPermission, "usesPermission");
        Intrinsics.checkNotNullParameter(usesPermissionSdk23, "usesPermissionSdk23");
        Intrinsics.checkNotNullParameter(nativecode, "nativecode");
        Intrinsics.checkNotNullParameter(features, "features");
        return new ManifestV2(versionName, versionCode, usesSdk, maxSdkVersion, signer, usesPermission, usesPermissionSdk23, nativecode, features);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ManifestV2)) {
            return false;
        }
        ManifestV2 manifestV2 = (ManifestV2) other;
        return Intrinsics.areEqual(this.versionName, manifestV2.versionName) && this.versionCode == manifestV2.versionCode && Intrinsics.areEqual(this.usesSdk, manifestV2.usesSdk) && Intrinsics.areEqual(this.maxSdkVersion, manifestV2.maxSdkVersion) && Intrinsics.areEqual(this.signer, manifestV2.signer) && Intrinsics.areEqual(this.usesPermission, manifestV2.usesPermission) && Intrinsics.areEqual(this.usesPermissionSdk23, manifestV2.usesPermissionSdk23) && Intrinsics.areEqual(this.nativecode, manifestV2.nativecode) && Intrinsics.areEqual(this.features, manifestV2.features);
    }

    public int hashCode() {
        int iHashCode = ((this.versionName.hashCode() * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.versionCode)) * 31;
        UsesSdkV2 usesSdkV2 = this.usesSdk;
        int iHashCode2 = (iHashCode + (usesSdkV2 == null ? 0 : usesSdkV2.hashCode())) * 31;
        Integer num = this.maxSdkVersion;
        int iHashCode3 = (iHashCode2 + (num == null ? 0 : num.hashCode())) * 31;
        SignerV2 signerV2 = this.signer;
        return ((((((((iHashCode3 + (signerV2 != null ? signerV2.hashCode() : 0)) * 31) + this.usesPermission.hashCode()) * 31) + this.usesPermissionSdk23.hashCode()) * 31) + this.nativecode.hashCode()) * 31) + this.features.hashCode();
    }

    public String toString() {
        return "ManifestV2(versionName=" + this.versionName + ", versionCode=" + this.versionCode + ", usesSdk=" + this.usesSdk + ", maxSdkVersion=" + this.maxSdkVersion + ", signer=" + this.signer + ", usesPermission=" + this.usesPermission + ", usesPermissionSdk23=" + this.usesPermissionSdk23 + ", nativecode=" + this.nativecode + ", features=" + this.features + ")";
    }

    /* JADX INFO: compiled from: PackageV2.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lorg/fdroid/index/v2/ManifestV2$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lorg/fdroid/index/v2/ManifestV2;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer serializer() {
            return ManifestV2$$serializer.INSTANCE;
        }
    }

    static {
        PermissionV2$$serializer permissionV2$$serializer = PermissionV2$$serializer.INSTANCE;
        ArrayListSerializer arrayListSerializer = new ArrayListSerializer(permissionV2$$serializer);
        ArrayListSerializer arrayListSerializer2 = new ArrayListSerializer(permissionV2$$serializer);
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        $childSerializers = new KSerializer[]{null, null, null, null, null, arrayListSerializer, arrayListSerializer2, new ArrayListSerializer(stringSerializer), new ArrayListSerializer(FeatureV2$$serializer.INSTANCE), null, new ArrayListSerializer(stringSerializer), null};
    }

    public /* synthetic */ ManifestV2(int i, String str, long j, UsesSdkV2 usesSdkV2, Integer num, SignerV2 signerV2, List list, List list2, List list3, List list4, Integer num2, List list5, Integer num3, SerializationConstructorMarker serializationConstructorMarker) {
        Integer numValueOf;
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, ManifestV2$$serializer.INSTANCE.getDescriptor());
        }
        this.versionName = str;
        this.versionCode = j;
        if ((i & 4) == 0) {
            this.usesSdk = null;
        } else {
            this.usesSdk = usesSdkV2;
        }
        if ((i & 8) == 0) {
            this.maxSdkVersion = null;
        } else {
            this.maxSdkVersion = num;
        }
        if ((i & 16) == 0) {
            this.signer = null;
        } else {
            this.signer = signerV2;
        }
        this.usesPermission = (i & 32) == 0 ? CollectionsKt.emptyList() : list;
        this.usesPermissionSdk23 = (i & 64) == 0 ? CollectionsKt.emptyList() : list2;
        this.nativecode = (i & 128) == 0 ? CollectionsKt.emptyList() : list3;
        this.features = (i & 256) == 0 ? CollectionsKt.emptyList() : list4;
        if ((i & 512) == 0) {
            UsesSdkV2 usesSdkV22 = this.usesSdk;
            numValueOf = usesSdkV22 != null ? Integer.valueOf(usesSdkV22.getMinSdkVersion()) : null;
        } else {
            numValueOf = num2;
        }
        this.minSdkVersion = numValueOf;
        if ((i & 1024) == 0) {
            List<FeatureV2> list6 = this.features;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list6, 10));
            Iterator<T> it = list6.iterator();
            while (it.hasNext()) {
                arrayList.add(((FeatureV2) it.next()).getName());
            }
            this.featureNames = arrayList;
        } else {
            this.featureNames = list5;
        }
        if ((i & 2048) != 0) {
            this.targetSdkVersion = num3;
        } else {
            UsesSdkV2 usesSdkV23 = this.usesSdk;
            this.targetSdkVersion = usesSdkV23 != null ? Integer.valueOf(usesSdkV23.getTargetSdkVersion()) : null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x011d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final /* synthetic */ void write$Self$index_release(org.fdroid.index.v2.ManifestV2 r7, kotlinx.serialization.encoding.CompositeEncoder r8, kotlinx.serialization.descriptors.SerialDescriptor r9) {
        /*
            Method dump skipped, instruction units count: 335
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.index.v2.ManifestV2.write$Self$index_release(org.fdroid.index.v2.ManifestV2, kotlinx.serialization.encoding.CompositeEncoder, kotlinx.serialization.descriptors.SerialDescriptor):void");
    }

    public ManifestV2(String versionName, long j, UsesSdkV2 usesSdkV2, Integer num, SignerV2 signerV2, List<PermissionV2> usesPermission, List<PermissionV2> usesPermissionSdk23, List<String> nativecode, List<FeatureV2> features) {
        Intrinsics.checkNotNullParameter(versionName, "versionName");
        Intrinsics.checkNotNullParameter(usesPermission, "usesPermission");
        Intrinsics.checkNotNullParameter(usesPermissionSdk23, "usesPermissionSdk23");
        Intrinsics.checkNotNullParameter(nativecode, "nativecode");
        Intrinsics.checkNotNullParameter(features, "features");
        this.versionName = versionName;
        this.versionCode = j;
        this.usesSdk = usesSdkV2;
        this.maxSdkVersion = num;
        this.signer = signerV2;
        this.usesPermission = usesPermission;
        this.usesPermissionSdk23 = usesPermissionSdk23;
        this.nativecode = nativecode;
        this.features = features;
        this.minSdkVersion = usesSdkV2 != null ? Integer.valueOf(usesSdkV2.getMinSdkVersion()) : null;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(features, 10));
        Iterator<T> it = features.iterator();
        while (it.hasNext()) {
            arrayList.add(((FeatureV2) it.next()).getName());
        }
        this.featureNames = arrayList;
        UsesSdkV2 usesSdkV22 = this.usesSdk;
        this.targetSdkVersion = usesSdkV22 != null ? Integer.valueOf(usesSdkV22.getTargetSdkVersion()) : null;
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

    public /* synthetic */ ManifestV2(String str, long j, UsesSdkV2 usesSdkV2, Integer num, SignerV2 signerV2, List list, List list2, List list3, List list4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, j, (i & 4) != 0 ? null : usesSdkV2, (i & 8) != 0 ? null : num, (i & 16) != 0 ? null : signerV2, (i & 32) != 0 ? CollectionsKt.emptyList() : list, (i & 64) != 0 ? CollectionsKt.emptyList() : list2, (i & 128) != 0 ? CollectionsKt.emptyList() : list3, (i & 256) != 0 ? CollectionsKt.emptyList() : list4);
    }

    public final List<PermissionV2> getUsesPermission() {
        return this.usesPermission;
    }

    public final List<PermissionV2> getUsesPermissionSdk23() {
        return this.usesPermissionSdk23;
    }

    @Override // org.fdroid.index.v2.PackageManifest
    public List<String> getNativecode() {
        return this.nativecode;
    }

    public final List<FeatureV2> getFeatures() {
        return this.features;
    }

    @Override // org.fdroid.index.v2.PackageManifest
    public Integer getMinSdkVersion() {
        return this.minSdkVersion;
    }

    @Override // org.fdroid.index.v2.PackageManifest
    public List<String> getFeatureNames() {
        return this.featureNames;
    }

    @Override // org.fdroid.index.v2.PackageManifest
    public Integer getTargetSdkVersion() {
        return this.targetSdkVersion;
    }
}
