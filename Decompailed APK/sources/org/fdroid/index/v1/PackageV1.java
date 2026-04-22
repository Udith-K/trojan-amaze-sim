package org.fdroid.index.v1;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.fdroid.index.v1.PermissionV1;
import org.fdroid.index.v2.FeatureV2;
import org.fdroid.index.v2.FileV1;
import org.fdroid.index.v2.FileV2;
import org.fdroid.index.v2.ManifestV2;
import org.fdroid.index.v2.PackageVersionV2;
import org.fdroid.index.v2.PermissionV2;
import org.fdroid.index.v2.SignerV2;
import org.fdroid.index.v2.UsesSdkV2;

/* JADX INFO: compiled from: PackageV1.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 i2\u00020\u0001:\u0002hiBí\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012\u0012\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0016\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0012\u0012\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0012\u0012\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0012¢\u0006\u0004\b\u001a\u0010\u001bBõ\u0001\b\u0010\u0012\u0006\u0010\u001c\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012\u0012\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0012\u0012\u000e\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0012\u0012\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0012\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e¢\u0006\u0004\b\u001a\u0010\u001fJT\u0010?\u001a\u00020@2\f\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00050\u00122\"\u0010B\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050Cj\u0002`D0C2\u001a\u0010E\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010Cj\u0004\u0018\u0001`DJ\u0010\u0010F\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010!J\t\u0010G\u001a\u00020\u0005HÆ\u0003J\t\u0010H\u001a\u00020\u0005HÆ\u0003J\t\u0010I\u001a\u00020\u0005HÆ\u0003J\u0010\u0010J\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010(J\u0010\u0010K\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010(J\u0010\u0010L\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010(J\t\u0010M\u001a\u00020\u0005HÆ\u0003J\u000b\u0010N\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010O\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010P\u001a\u00020\u0003HÆ\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000f\u0010R\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012HÆ\u0003J\u000f\u0010S\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012HÆ\u0003J\u0010\u0010T\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010!J\t\u0010U\u001a\u00020\u0005HÆ\u0003J\u0011\u0010V\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0012HÆ\u0003J\u0011\u0010W\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0012HÆ\u0003J\u0011\u0010X\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0012HÆ\u0003J\u0080\u0002\u0010Y\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\f\u001a\u00020\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u00052\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00122\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00122\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0012HÆ\u0001¢\u0006\u0002\u0010ZJ\u0013\u0010[\u001a\u00020\\2\b\u0010]\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010^\u001a\u00020\tHÖ\u0001J\t\u0010_\u001a\u00020\u0005HÖ\u0001J%\u0010`\u001a\u00020a2\u0006\u0010b\u001a\u00020\u00002\u0006\u0010c\u001a\u00020d2\u0006\u0010e\u001a\u00020fH\u0001¢\u0006\u0002\bgR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b \u0010!R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010$R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010$R\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010)\u001a\u0004\b'\u0010(R\u0015\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010)\u001a\u0004\b*\u0010(R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010)\u001a\u0004\b+\u0010(R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b,\u0010$R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b-\u0010$R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b.\u0010$R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b1\u00102\u001a\u0004\b3\u0010$R\"\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00128\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b4\u00102\u001a\u0004\b5\u00106R\"\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00128\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b7\u00102\u001a\u0004\b8\u00106R\u0015\u0010\u0015\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b9\u0010!R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b:\u0010$R$\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00128\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b;\u00102\u001a\u0004\b<\u00106R\u0019\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b=\u00106R\u0019\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b>\u00106¨\u0006j"}, d2 = {"Lorg/fdroid/index/v1/PackageV1;", "", "added", "", "apkName", "", "hash", "hashType", "minSdkVersion", "", "maxSdkVersion", "targetSdkVersion", "packageName", "sig", "signer", "size", "srcName", "usesPermission", "", "Lorg/fdroid/index/v1/PermissionV1;", "usesPermission23", "versionCode", "versionName", "nativeCode", "features", "antiFeatures", "<init>", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/Long;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/Long;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getAdded", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getApkName", "()Ljava/lang/String;", "getHash", "getHashType", "getMinSdkVersion", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMaxSdkVersion", "getTargetSdkVersion", "getPackageName", "getSig", "getSigner", "getSize", "()J", "getSrcName$annotations", "()V", "getSrcName", "getUsesPermission$annotations", "getUsesPermission", "()Ljava/util/List;", "getUsesPermission23$annotations", "getUsesPermission23", "getVersionCode", "getVersionName", "getNativeCode$annotations", "getNativeCode", "getFeatures", "getAntiFeatures", "toPackageVersionV2", "Lorg/fdroid/index/v2/PackageVersionV2;", "releaseChannels", "appAntiFeatures", "", "Lorg/fdroid/index/v2/LocalizedTextV2;", "whatsNew", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "copy", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/Long;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;)Lorg/fdroid/index/v1/PackageV1;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$index_release", "$serializer", "Companion", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class PackageV1 {
    private static final KSerializer[] $childSerializers;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Long added;
    private final List<String> antiFeatures;
    private final String apkName;
    private final List<String> features;
    private final String hash;
    private final String hashType;
    private final Integer maxSdkVersion;
    private final Integer minSdkVersion;
    private final List<String> nativeCode;
    private final String packageName;
    private final String sig;
    private final String signer;
    private final long size;
    private final String srcName;
    private final Integer targetSdkVersion;
    private final List<PermissionV1> usesPermission;
    private final List<PermissionV1> usesPermission23;
    private final Long versionCode;
    private final String versionName;

    public static /* synthetic */ void getNativeCode$annotations() {
    }

    public static /* synthetic */ void getSrcName$annotations() {
    }

    public static /* synthetic */ void getUsesPermission$annotations() {
    }

    public static /* synthetic */ void getUsesPermission23$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Long getAdded() {
        return this.added;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getSigner() {
        return this.signer;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final long getSize() {
        return this.size;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getSrcName() {
        return this.srcName;
    }

    public final List<PermissionV1> component13() {
        return this.usesPermission;
    }

    public final List<PermissionV1> component14() {
        return this.usesPermission23;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final Long getVersionCode() {
        return this.versionCode;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getVersionName() {
        return this.versionName;
    }

    public final List<String> component17() {
        return this.nativeCode;
    }

    public final List<String> component18() {
        return this.features;
    }

    public final List<String> component19() {
        return this.antiFeatures;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getApkName() {
        return this.apkName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getHash() {
        return this.hash;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getHashType() {
        return this.hashType;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Integer getMinSdkVersion() {
        return this.minSdkVersion;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Integer getMaxSdkVersion() {
        return this.maxSdkVersion;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Integer getTargetSdkVersion() {
        return this.targetSdkVersion;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getPackageName() {
        return this.packageName;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getSig() {
        return this.sig;
    }

    public final PackageV1 copy(Long added, String apkName, String hash, String hashType, Integer minSdkVersion, Integer maxSdkVersion, Integer targetSdkVersion, String packageName, String sig, String signer, long size, String srcName, List<PermissionV1> usesPermission, List<PermissionV1> usesPermission23, Long versionCode, String versionName, List<String> nativeCode, List<String> features, List<String> antiFeatures) {
        Intrinsics.checkNotNullParameter(apkName, "apkName");
        Intrinsics.checkNotNullParameter(hash, "hash");
        Intrinsics.checkNotNullParameter(hashType, "hashType");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(usesPermission, "usesPermission");
        Intrinsics.checkNotNullParameter(usesPermission23, "usesPermission23");
        Intrinsics.checkNotNullParameter(versionName, "versionName");
        return new PackageV1(added, apkName, hash, hashType, minSdkVersion, maxSdkVersion, targetSdkVersion, packageName, sig, signer, size, srcName, usesPermission, usesPermission23, versionCode, versionName, nativeCode, features, antiFeatures);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PackageV1)) {
            return false;
        }
        PackageV1 packageV1 = (PackageV1) other;
        return Intrinsics.areEqual(this.added, packageV1.added) && Intrinsics.areEqual(this.apkName, packageV1.apkName) && Intrinsics.areEqual(this.hash, packageV1.hash) && Intrinsics.areEqual(this.hashType, packageV1.hashType) && Intrinsics.areEqual(this.minSdkVersion, packageV1.minSdkVersion) && Intrinsics.areEqual(this.maxSdkVersion, packageV1.maxSdkVersion) && Intrinsics.areEqual(this.targetSdkVersion, packageV1.targetSdkVersion) && Intrinsics.areEqual(this.packageName, packageV1.packageName) && Intrinsics.areEqual(this.sig, packageV1.sig) && Intrinsics.areEqual(this.signer, packageV1.signer) && this.size == packageV1.size && Intrinsics.areEqual(this.srcName, packageV1.srcName) && Intrinsics.areEqual(this.usesPermission, packageV1.usesPermission) && Intrinsics.areEqual(this.usesPermission23, packageV1.usesPermission23) && Intrinsics.areEqual(this.versionCode, packageV1.versionCode) && Intrinsics.areEqual(this.versionName, packageV1.versionName) && Intrinsics.areEqual(this.nativeCode, packageV1.nativeCode) && Intrinsics.areEqual(this.features, packageV1.features) && Intrinsics.areEqual(this.antiFeatures, packageV1.antiFeatures);
    }

    public int hashCode() {
        Long l = this.added;
        int iHashCode = (((((((l == null ? 0 : l.hashCode()) * 31) + this.apkName.hashCode()) * 31) + this.hash.hashCode()) * 31) + this.hashType.hashCode()) * 31;
        Integer num = this.minSdkVersion;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.maxSdkVersion;
        int iHashCode3 = (iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.targetSdkVersion;
        int iHashCode4 = (((iHashCode3 + (num3 == null ? 0 : num3.hashCode())) * 31) + this.packageName.hashCode()) * 31;
        String str = this.sig;
        int iHashCode5 = (iHashCode4 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.signer;
        int iHashCode6 = (((iHashCode5 + (str2 == null ? 0 : str2.hashCode())) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.size)) * 31;
        String str3 = this.srcName;
        int iHashCode7 = (((((iHashCode6 + (str3 == null ? 0 : str3.hashCode())) * 31) + this.usesPermission.hashCode()) * 31) + this.usesPermission23.hashCode()) * 31;
        Long l2 = this.versionCode;
        int iHashCode8 = (((iHashCode7 + (l2 == null ? 0 : l2.hashCode())) * 31) + this.versionName.hashCode()) * 31;
        List<String> list = this.nativeCode;
        int iHashCode9 = (iHashCode8 + (list == null ? 0 : list.hashCode())) * 31;
        List<String> list2 = this.features;
        int iHashCode10 = (iHashCode9 + (list2 == null ? 0 : list2.hashCode())) * 31;
        List<String> list3 = this.antiFeatures;
        return iHashCode10 + (list3 != null ? list3.hashCode() : 0);
    }

    public String toString() {
        return "PackageV1(added=" + this.added + ", apkName=" + this.apkName + ", hash=" + this.hash + ", hashType=" + this.hashType + ", minSdkVersion=" + this.minSdkVersion + ", maxSdkVersion=" + this.maxSdkVersion + ", targetSdkVersion=" + this.targetSdkVersion + ", packageName=" + this.packageName + ", sig=" + this.sig + ", signer=" + this.signer + ", size=" + this.size + ", srcName=" + this.srcName + ", usesPermission=" + this.usesPermission + ", usesPermission23=" + this.usesPermission23 + ", versionCode=" + this.versionCode + ", versionName=" + this.versionName + ", nativeCode=" + this.nativeCode + ", features=" + this.features + ", antiFeatures=" + this.antiFeatures + ")";
    }

    /* JADX INFO: compiled from: PackageV1.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lorg/fdroid/index/v1/PackageV1$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lorg/fdroid/index/v1/PackageV1;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer serializer() {
            return PackageV1$$serializer.INSTANCE;
        }
    }

    static {
        PermissionV1.Companion companion = PermissionV1.INSTANCE;
        ArrayListSerializer arrayListSerializer = new ArrayListSerializer(companion.serializer());
        ArrayListSerializer arrayListSerializer2 = new ArrayListSerializer(companion.serializer());
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        $childSerializers = new KSerializer[]{null, null, null, null, null, null, null, null, null, null, null, null, arrayListSerializer, arrayListSerializer2, null, null, new ArrayListSerializer(stringSerializer), new ArrayListSerializer(stringSerializer), new ArrayListSerializer(stringSerializer)};
    }

    public /* synthetic */ PackageV1(int i, Long l, String str, String str2, String str3, Integer num, Integer num2, Integer num3, String str4, String str5, String str6, long j, String str7, List list, List list2, Long l2, String str8, List list3, List list4, List list5, SerializationConstructorMarker serializationConstructorMarker) {
        if (33934 != (i & 33934)) {
            PluginExceptionsKt.throwMissingFieldException(i, 33934, PackageV1$$serializer.INSTANCE.getDescriptor());
        }
        if ((i & 1) == 0) {
            this.added = null;
        } else {
            this.added = l;
        }
        this.apkName = str;
        this.hash = str2;
        this.hashType = str3;
        if ((i & 16) == 0) {
            this.minSdkVersion = null;
        } else {
            this.minSdkVersion = num;
        }
        if ((i & 32) == 0) {
            this.maxSdkVersion = null;
        } else {
            this.maxSdkVersion = num2;
        }
        this.targetSdkVersion = (i & 64) == 0 ? this.minSdkVersion : num3;
        this.packageName = str4;
        if ((i & 256) == 0) {
            this.sig = null;
        } else {
            this.sig = str5;
        }
        if ((i & 512) == 0) {
            this.signer = null;
        } else {
            this.signer = str6;
        }
        this.size = j;
        if ((i & 2048) == 0) {
            this.srcName = null;
        } else {
            this.srcName = str7;
        }
        this.usesPermission = (i & PKIFailureInfo.certConfirmed) == 0 ? CollectionsKt.emptyList() : list;
        this.usesPermission23 = (i & 8192) == 0 ? CollectionsKt.emptyList() : list2;
        if ((i & 16384) == 0) {
            this.versionCode = null;
        } else {
            this.versionCode = l2;
        }
        this.versionName = str8;
        if ((65536 & i) == 0) {
            this.nativeCode = null;
        } else {
            this.nativeCode = list3;
        }
        if ((131072 & i) == 0) {
            this.features = null;
        } else {
            this.features = list4;
        }
        if ((i & PKIFailureInfo.transactionIdInUse) == 0) {
            this.antiFeatures = null;
        } else {
            this.antiFeatures = list5;
        }
    }

    public static final /* synthetic */ void write$Self$index_release(PackageV1 self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer[] kSerializerArr = $childSerializers;
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.added != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, LongSerializer.INSTANCE, self.added);
        }
        output.encodeStringElement(serialDesc, 1, self.apkName);
        output.encodeStringElement(serialDesc, 2, self.hash);
        output.encodeStringElement(serialDesc, 3, self.hashType);
        if (output.shouldEncodeElementDefault(serialDesc, 4) || self.minSdkVersion != null) {
            output.encodeNullableSerializableElement(serialDesc, 4, IntSerializer.INSTANCE, self.minSdkVersion);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 5) || self.maxSdkVersion != null) {
            output.encodeNullableSerializableElement(serialDesc, 5, IntSerializer.INSTANCE, self.maxSdkVersion);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 6) || !Intrinsics.areEqual(self.targetSdkVersion, self.minSdkVersion)) {
            output.encodeNullableSerializableElement(serialDesc, 6, IntSerializer.INSTANCE, self.targetSdkVersion);
        }
        output.encodeStringElement(serialDesc, 7, self.packageName);
        if (output.shouldEncodeElementDefault(serialDesc, 8) || self.sig != null) {
            output.encodeNullableSerializableElement(serialDesc, 8, StringSerializer.INSTANCE, self.sig);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 9) || self.signer != null) {
            output.encodeNullableSerializableElement(serialDesc, 9, StringSerializer.INSTANCE, self.signer);
        }
        output.encodeLongElement(serialDesc, 10, self.size);
        if (output.shouldEncodeElementDefault(serialDesc, 11) || self.srcName != null) {
            output.encodeNullableSerializableElement(serialDesc, 11, StringSerializer.INSTANCE, self.srcName);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 12) || !Intrinsics.areEqual(self.usesPermission, CollectionsKt.emptyList())) {
            output.encodeSerializableElement(serialDesc, 12, kSerializerArr[12], self.usesPermission);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 13) || !Intrinsics.areEqual(self.usesPermission23, CollectionsKt.emptyList())) {
            output.encodeSerializableElement(serialDesc, 13, kSerializerArr[13], self.usesPermission23);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 14) || self.versionCode != null) {
            output.encodeNullableSerializableElement(serialDesc, 14, LongSerializer.INSTANCE, self.versionCode);
        }
        output.encodeStringElement(serialDesc, 15, self.versionName);
        if (output.shouldEncodeElementDefault(serialDesc, 16) || self.nativeCode != null) {
            output.encodeNullableSerializableElement(serialDesc, 16, kSerializerArr[16], self.nativeCode);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 17) || self.features != null) {
            output.encodeNullableSerializableElement(serialDesc, 17, kSerializerArr[17], self.features);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 18) && self.antiFeatures == null) {
            return;
        }
        output.encodeNullableSerializableElement(serialDesc, 18, kSerializerArr[18], self.antiFeatures);
    }

    public PackageV1(Long l, String apkName, String hash, String hashType, Integer num, Integer num2, Integer num3, String packageName, String str, String str2, long j, String str3, List<PermissionV1> usesPermission, List<PermissionV1> usesPermission23, Long l2, String versionName, List<String> list, List<String> list2, List<String> list3) {
        Intrinsics.checkNotNullParameter(apkName, "apkName");
        Intrinsics.checkNotNullParameter(hash, "hash");
        Intrinsics.checkNotNullParameter(hashType, "hashType");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(usesPermission, "usesPermission");
        Intrinsics.checkNotNullParameter(usesPermission23, "usesPermission23");
        Intrinsics.checkNotNullParameter(versionName, "versionName");
        this.added = l;
        this.apkName = apkName;
        this.hash = hash;
        this.hashType = hashType;
        this.minSdkVersion = num;
        this.maxSdkVersion = num2;
        this.targetSdkVersion = num3;
        this.packageName = packageName;
        this.sig = str;
        this.signer = str2;
        this.size = j;
        this.srcName = str3;
        this.usesPermission = usesPermission;
        this.usesPermission23 = usesPermission23;
        this.versionCode = l2;
        this.versionName = versionName;
        this.nativeCode = list;
        this.features = list2;
        this.antiFeatures = list3;
    }

    public final Long getAdded() {
        return this.added;
    }

    public final String getApkName() {
        return this.apkName;
    }

    public final String getHash() {
        return this.hash;
    }

    public final String getHashType() {
        return this.hashType;
    }

    public final Integer getMinSdkVersion() {
        return this.minSdkVersion;
    }

    public final Integer getMaxSdkVersion() {
        return this.maxSdkVersion;
    }

    public final Integer getTargetSdkVersion() {
        return this.targetSdkVersion;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final String getSig() {
        return this.sig;
    }

    public final String getSigner() {
        return this.signer;
    }

    public final long getSize() {
        return this.size;
    }

    public final String getSrcName() {
        return this.srcName;
    }

    public final List<PermissionV1> getUsesPermission() {
        return this.usesPermission;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ PackageV1(Long l, String str, String str2, String str3, Integer num, Integer num2, Integer num3, String str4, String str5, String str6, long j, String str7, List list, List list2, Long l2, String str8, List list3, List list4, List list5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        Long l3 = (i & 1) != 0 ? null : l;
        Integer num4 = (i & 16) != 0 ? null : num;
        this(l3, str, str2, str3, num4, (i & 32) != 0 ? null : num2, (i & 64) != 0 ? num4 : num3, str4, (i & 256) != 0 ? null : str5, (i & 512) != 0 ? null : str6, j, (i & 2048) != 0 ? null : str7, (i & PKIFailureInfo.certConfirmed) != 0 ? CollectionsKt.emptyList() : list, (i & 8192) != 0 ? CollectionsKt.emptyList() : list2, (i & 16384) != 0 ? null : l2, str8, (65536 & i) != 0 ? null : list3, (131072 & i) != 0 ? null : list4, (i & PKIFailureInfo.transactionIdInUse) != 0 ? null : list5);
    }

    public final List<PermissionV1> getUsesPermission23() {
        return this.usesPermission23;
    }

    public final Long getVersionCode() {
        return this.versionCode;
    }

    public final String getVersionName() {
        return this.versionName;
    }

    public final List<String> getNativeCode() {
        return this.nativeCode;
    }

    public final List<String> getFeatures() {
        return this.features;
    }

    public final List<String> getAntiFeatures() {
        return this.antiFeatures;
    }

    public final PackageVersionV2 toPackageVersionV2(List<String> releaseChannels, Map<String, ? extends Map<String, String>> appAntiFeatures, Map<String, String> whatsNew) {
        UsesSdkV2 usesSdkV2;
        FileV2 fileV2;
        List listEmptyList;
        Map mapEmptyMap;
        Intrinsics.checkNotNullParameter(releaseChannels, "releaseChannels");
        Intrinsics.checkNotNullParameter(appAntiFeatures, "appAntiFeatures");
        Long l = this.added;
        long jLongValue = l != null ? l.longValue() : 0L;
        String str = "/" + this.apkName;
        String str2 = this.hash;
        if (!Intrinsics.areEqual(this.hashType, "sha256")) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        Unit unit = Unit.INSTANCE;
        FileV1 fileV1 = new FileV1(str, str2, Long.valueOf(this.size), (String) null, 8, (DefaultConstructorMarker) null);
        String str3 = this.srcName;
        FileV2 fileV22 = str3 != null ? new FileV2("/" + str3, (String) null, (Long) null, (String) null, 14, (DefaultConstructorMarker) null) : null;
        String str4 = this.versionName;
        Long l2 = this.versionCode;
        long jLongValue2 = l2 != null ? l2.longValue() : 1L;
        if (this.minSdkVersion == null && this.targetSdkVersion == null) {
            usesSdkV2 = null;
        } else {
            Integer num = this.minSdkVersion;
            int iIntValue = num != null ? num.intValue() : 1;
            Integer num2 = this.targetSdkVersion;
            usesSdkV2 = new UsesSdkV2(iIntValue, (num2 == null && (num2 = this.minSdkVersion) == null) ? 1 : num2.intValue());
        }
        Integer num3 = this.maxSdkVersion;
        String str5 = this.signer;
        SignerV2 signerV2 = str5 != null ? new SignerV2(CollectionsKt.listOf(str5), false, 2, (DefaultConstructorMarker) null) : null;
        List<PermissionV1> list = this.usesPermission;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (PermissionV1 permissionV1 : list) {
            arrayList.add(new PermissionV2(permissionV1.getName(), permissionV1.getMaxSdk()));
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(this.usesPermission23, 10));
        for (Iterator it = r3.iterator(); it.hasNext(); it = it) {
            PermissionV1 permissionV12 = (PermissionV1) it.next();
            arrayList2.add(new PermissionV2(permissionV12.getName(), permissionV12.getMaxSdk()));
        }
        List<String> listEmptyList2 = this.nativeCode;
        if (listEmptyList2 == null) {
            listEmptyList2 = CollectionsKt.emptyList();
        }
        List<String> list2 = listEmptyList2;
        List<String> list3 = this.features;
        if (list3 != null) {
            fileV2 = fileV22;
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
            Iterator<T> it2 = list3.iterator();
            while (it2.hasNext()) {
                arrayList3.add(new FeatureV2((String) it2.next()));
            }
            listEmptyList = arrayList3;
        } else {
            fileV2 = fileV22;
            listEmptyList = CollectionsKt.emptyList();
        }
        ManifestV2 manifestV2 = new ManifestV2(str4, jLongValue2, usesSdkV2, num3, signerV2, arrayList, arrayList2, list2, listEmptyList);
        List<String> list4 = this.antiFeatures;
        if (list4 != null) {
            mapEmptyMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list4, 10)), 16));
            Iterator<T> it3 = list4.iterator();
            while (it3.hasNext()) {
                Pair pair = TuplesKt.to((String) it3.next(), MapsKt.emptyMap());
                mapEmptyMap.put(pair.getFirst(), pair.getSecond());
            }
        } else {
            mapEmptyMap = MapsKt.emptyMap();
        }
        return new PackageVersionV2(jLongValue, fileV1, fileV2, manifestV2, releaseChannels, MapsKt.plus(appAntiFeatures, mapEmptyMap), whatsNew == null ? MapsKt.emptyMap() : whatsNew);
    }
}
