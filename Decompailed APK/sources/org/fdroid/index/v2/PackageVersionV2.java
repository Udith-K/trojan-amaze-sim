package org.fdroid.index.v2;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import ch.qos.logback.core.joran.action.Action;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.PolymorphicSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: PackageV2.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 L2\u00020\u0001:\u0002KLB{\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012$\b\u0002\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\f\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000ej\u0002`\u000f0\u000e\u0012\u0018\b\u0002\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000ej\u0002`\u000f¢\u0006\u0004\b\u0011\u0010\u0012B¥\u0001\b\u0010\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b\u0012 \u0010\r\u001a\u001c\u0012\u0004\u0012\u00020\f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000e\u0018\u00010\u000e\u0012\u0014\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f\u0018\u00010\u000e\u0012\u0006\u0010\u0015\u001a\u00020\u0003\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b¢\u0006\u0004\b\u0011\u0010\u001cJ\u001c\u00103\u001a\u0002042\u0014\u00105\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020406J\t\u00107\u001a\u00020\u0003HÆ\u0003J\t\u00108\u001a\u00020\u0005HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010:\u001a\u00020\tHÆ\u0003J\u000f\u0010;\u001a\b\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0003J%\u0010<\u001a\u001e\u0012\u0004\u0012\u00020\f\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000ej\u0002`\u000f0\u000eHÆ\u0003J\u0019\u0010=\u001a\u0012\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000ej\u0002`\u000fHÆ\u0003J\u0083\u0001\u0010>\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2$\b\u0002\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\f\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000ej\u0002`\u000f0\u000e2\u0018\b\u0002\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000ej\u0002`\u000fHÆ\u0001J\u0013\u0010?\u001a\u0002002\b\u0010@\u001a\u0004\u0018\u00010AHÖ\u0003J\t\u0010B\u001a\u00020\u0014HÖ\u0001J\t\u0010C\u001a\u00020\fHÖ\u0001J%\u0010D\u001a\u0002042\u0006\u0010E\u001a\u00020\u00002\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020IH\u0001¢\u0006\u0002\bJR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R-\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\f\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000ej\u0002`\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R!\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000ej\u0002`\u000f¢\u0006\b\n\u0000\u001a\u0004\b)\u0010(R\u0014\u0010\u0015\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001eR\u0016\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0014\u0010\u0018\u001a\u00020\u0019X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0014\u0010/\u001a\u0002008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b1\u00102¨\u0006M"}, d2 = {"Lorg/fdroid/index/v2/PackageVersionV2;", "Lorg/fdroid/index/v2/PackageVersion;", "added", "", Action.FILE_ATTRIBUTE, "Lorg/fdroid/index/v2/FileV1;", "src", "Lorg/fdroid/index/v2/FileV2;", "manifest", "Lorg/fdroid/index/v2/ManifestV2;", "releaseChannels", "", "", "antiFeatures", "", "Lorg/fdroid/index/v2/LocalizedTextV2;", "whatsNew", "<init>", "(JLorg/fdroid/index/v2/FileV1;Lorg/fdroid/index/v2/FileV2;Lorg/fdroid/index/v2/ManifestV2;Ljava/util/List;Ljava/util/Map;Ljava/util/Map;)V", "seen0", "", "versionCode", "signer", "Lorg/fdroid/index/v2/SignerV2;", "packageManifest", "Lorg/fdroid/index/v2/PackageManifest;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IJLorg/fdroid/index/v2/FileV1;Lorg/fdroid/index/v2/FileV2;Lorg/fdroid/index/v2/ManifestV2;Ljava/util/List;Ljava/util/Map;Ljava/util/Map;JLorg/fdroid/index/v2/SignerV2;Lorg/fdroid/index/v2/PackageManifest;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getAdded", "()J", "getFile", "()Lorg/fdroid/index/v2/FileV1;", "getSrc", "()Lorg/fdroid/index/v2/FileV2;", "getManifest", "()Lorg/fdroid/index/v2/ManifestV2;", "getReleaseChannels", "()Ljava/util/List;", "getAntiFeatures", "()Ljava/util/Map;", "getWhatsNew", "getVersionCode", "getSigner", "()Lorg/fdroid/index/v2/SignerV2;", "getPackageManifest", "()Lorg/fdroid/index/v2/PackageManifest;", "hasKnownVulnerability", "", "getHasKnownVulnerability", "()Z", "walkFiles", "", "fileConsumer", "Lkotlin/Function1;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "", "hashCode", "toString", "write$Self", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$index_release", "$serializer", "Companion", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class PackageVersionV2 implements PackageVersion {
    private static final KSerializer[] $childSerializers;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final long added;
    private final Map<String, Map<String, String>> antiFeatures;
    private final FileV1 file;
    private final ManifestV2 manifest;
    private final PackageManifest packageManifest;
    private final List<String> releaseChannels;
    private final SignerV2 signer;
    private final FileV2 src;
    private final long versionCode;
    private final Map<String, String> whatsNew;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getAdded() {
        return this.added;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final FileV1 getFile() {
        return this.file;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final FileV2 getSrc() {
        return this.src;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final ManifestV2 getManifest() {
        return this.manifest;
    }

    public final List<String> component5() {
        return this.releaseChannels;
    }

    public final Map<String, Map<String, String>> component6() {
        return this.antiFeatures;
    }

    public final Map<String, String> component7() {
        return this.whatsNew;
    }

    public final PackageVersionV2 copy(long added, FileV1 file, FileV2 src, ManifestV2 manifest, List<String> releaseChannels, Map<String, ? extends Map<String, String>> antiFeatures, Map<String, String> whatsNew) {
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(manifest, "manifest");
        Intrinsics.checkNotNullParameter(releaseChannels, "releaseChannels");
        Intrinsics.checkNotNullParameter(antiFeatures, "antiFeatures");
        Intrinsics.checkNotNullParameter(whatsNew, "whatsNew");
        return new PackageVersionV2(added, file, src, manifest, releaseChannels, antiFeatures, whatsNew);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PackageVersionV2)) {
            return false;
        }
        PackageVersionV2 packageVersionV2 = (PackageVersionV2) other;
        return this.added == packageVersionV2.added && Intrinsics.areEqual(this.file, packageVersionV2.file) && Intrinsics.areEqual(this.src, packageVersionV2.src) && Intrinsics.areEqual(this.manifest, packageVersionV2.manifest) && Intrinsics.areEqual(this.releaseChannels, packageVersionV2.releaseChannels) && Intrinsics.areEqual(this.antiFeatures, packageVersionV2.antiFeatures) && Intrinsics.areEqual(this.whatsNew, packageVersionV2.whatsNew);
    }

    public int hashCode() {
        int iM = ((LongObjectMap$$ExternalSyntheticBackport0.m(this.added) * 31) + this.file.hashCode()) * 31;
        FileV2 fileV2 = this.src;
        return ((((((((iM + (fileV2 == null ? 0 : fileV2.hashCode())) * 31) + this.manifest.hashCode()) * 31) + this.releaseChannels.hashCode()) * 31) + this.antiFeatures.hashCode()) * 31) + this.whatsNew.hashCode();
    }

    public String toString() {
        return "PackageVersionV2(added=" + this.added + ", file=" + this.file + ", src=" + this.src + ", manifest=" + this.manifest + ", releaseChannels=" + this.releaseChannels + ", antiFeatures=" + this.antiFeatures + ", whatsNew=" + this.whatsNew + ")";
    }

    /* JADX INFO: compiled from: PackageV2.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lorg/fdroid/index/v2/PackageVersionV2$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lorg/fdroid/index/v2/PackageVersionV2;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer serializer() {
            return PackageVersionV2$$serializer.INSTANCE;
        }
    }

    static {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        $childSerializers = new KSerializer[]{null, null, null, null, new ArrayListSerializer(stringSerializer), new LinkedHashMapSerializer(stringSerializer, new LinkedHashMapSerializer(stringSerializer, stringSerializer)), new LinkedHashMapSerializer(stringSerializer, stringSerializer), null, null, new PolymorphicSerializer(Reflection.getOrCreateKotlinClass(PackageManifest.class), new Annotation[0])};
    }

    public /* synthetic */ PackageVersionV2(int i, long j, FileV1 fileV1, FileV2 fileV2, ManifestV2 manifestV2, List list, Map map, Map map2, long j2, SignerV2 signerV2, PackageManifest packageManifest, SerializationConstructorMarker serializationConstructorMarker) {
        if (11 != (i & 11)) {
            PluginExceptionsKt.throwMissingFieldException(i, 11, PackageVersionV2$$serializer.INSTANCE.getDescriptor());
        }
        this.added = j;
        this.file = fileV1;
        if ((i & 4) == 0) {
            this.src = null;
        } else {
            this.src = fileV2;
        }
        this.manifest = manifestV2;
        if ((i & 16) == 0) {
            this.releaseChannels = CollectionsKt.emptyList();
        } else {
            this.releaseChannels = list;
        }
        if ((i & 32) == 0) {
            this.antiFeatures = MapsKt.emptyMap();
        } else {
            this.antiFeatures = map;
        }
        if ((i & 64) == 0) {
            this.whatsNew = MapsKt.emptyMap();
        } else {
            this.whatsNew = map2;
        }
        if ((i & 128) == 0) {
            this.versionCode = manifestV2.getVersionCode();
        } else {
            this.versionCode = j2;
        }
        if ((i & 256) == 0) {
            this.signer = manifestV2.getSigner();
        } else {
            this.signer = signerV2;
        }
        if ((i & 512) == 0) {
            this.packageManifest = manifestV2;
        } else {
            this.packageManifest = packageManifest;
        }
    }

    public static final /* synthetic */ void write$Self$index_release(PackageVersionV2 self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer[] kSerializerArr = $childSerializers;
        output.encodeLongElement(serialDesc, 0, self.added);
        output.encodeSerializableElement(serialDesc, 1, FileV1$$serializer.INSTANCE, self.file);
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.src != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, FileV2$$serializer.INSTANCE, self.src);
        }
        output.encodeSerializableElement(serialDesc, 3, ManifestV2$$serializer.INSTANCE, self.manifest);
        if (output.shouldEncodeElementDefault(serialDesc, 4) || !Intrinsics.areEqual(self.getReleaseChannels(), CollectionsKt.emptyList())) {
            output.encodeSerializableElement(serialDesc, 4, kSerializerArr[4], self.getReleaseChannels());
        }
        if (output.shouldEncodeElementDefault(serialDesc, 5) || !Intrinsics.areEqual(self.antiFeatures, MapsKt.emptyMap())) {
            output.encodeSerializableElement(serialDesc, 5, kSerializerArr[5], self.antiFeatures);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 6) || !Intrinsics.areEqual(self.whatsNew, MapsKt.emptyMap())) {
            output.encodeSerializableElement(serialDesc, 6, kSerializerArr[6], self.whatsNew);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 7) || self.getVersionCode() != self.manifest.getVersionCode()) {
            output.encodeLongElement(serialDesc, 7, self.getVersionCode());
        }
        if (output.shouldEncodeElementDefault(serialDesc, 8) || !Intrinsics.areEqual(self.getSigner(), self.manifest.getSigner())) {
            output.encodeNullableSerializableElement(serialDesc, 8, SignerV2$$serializer.INSTANCE, self.getSigner());
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 9) && Intrinsics.areEqual(self.getPackageManifest(), self.manifest)) {
            return;
        }
        output.encodeSerializableElement(serialDesc, 9, kSerializerArr[9], self.getPackageManifest());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PackageVersionV2(long j, FileV1 file, FileV2 fileV2, ManifestV2 manifest, List<String> releaseChannels, Map<String, ? extends Map<String, String>> antiFeatures, Map<String, String> whatsNew) {
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(manifest, "manifest");
        Intrinsics.checkNotNullParameter(releaseChannels, "releaseChannels");
        Intrinsics.checkNotNullParameter(antiFeatures, "antiFeatures");
        Intrinsics.checkNotNullParameter(whatsNew, "whatsNew");
        this.added = j;
        this.file = file;
        this.src = fileV2;
        this.manifest = manifest;
        this.releaseChannels = releaseChannels;
        this.antiFeatures = antiFeatures;
        this.whatsNew = whatsNew;
        this.versionCode = manifest.getVersionCode();
        this.signer = manifest.getSigner();
        this.packageManifest = manifest;
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

    public final ManifestV2 getManifest() {
        return this.manifest;
    }

    public /* synthetic */ PackageVersionV2(long j, FileV1 fileV1, FileV2 fileV2, ManifestV2 manifestV2, List list, Map map, Map map2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, fileV1, (i & 4) != 0 ? null : fileV2, manifestV2, (i & 16) != 0 ? CollectionsKt.emptyList() : list, (i & 32) != 0 ? MapsKt.emptyMap() : map, (i & 64) != 0 ? MapsKt.emptyMap() : map2);
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

    @Override // org.fdroid.index.v2.PackageVersion
    public long getVersionCode() {
        return this.versionCode;
    }

    @Override // org.fdroid.index.v2.PackageVersion
    public SignerV2 getSigner() {
        return this.signer;
    }

    @Override // org.fdroid.index.v2.PackageVersion
    public PackageManifest getPackageManifest() {
        return this.packageManifest;
    }

    @Override // org.fdroid.index.v2.PackageVersion
    public boolean getHasKnownVulnerability() {
        return this.antiFeatures.containsKey(PackageV2Kt.ANTI_FEATURE_KNOWN_VULNERABILITY);
    }

    public final void walkFiles(Function1 fileConsumer) {
        Intrinsics.checkNotNullParameter(fileConsumer, "fileConsumer");
        fileConsumer.invoke(this.src);
    }
}
