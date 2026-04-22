package org.fdroid.index.v2;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: IndexV2.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 H2\u00020\u0001:\u0002GHBÃ\u0001\u0012\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u0005\u0012\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0003j\u0002`\b\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\u0018\b\u0002\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u0005\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u0003\u0012\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00140\u0003\u0012\u0014\b\u0002\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00160\u0003¢\u0006\u0004\b\u0017\u0010\u0018BË\u0001\b\u0010\u0012\u0006\u0010\u0019\u001a\u00020\u001a\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0003\u0012\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0003\u0012\u0014\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0003\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c¢\u0006\u0004\b\u0017\u0010\u001dJ\u001c\u0010,\u001a\u00020-2\u0014\u0010.\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020-0/J\u0019\u00100\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u0005HÆ\u0003J\u0019\u00101\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0003j\u0002`\bHÆ\u0003J\t\u00102\u001a\u00020\u0004HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0019\u00104\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u0005HÆ\u0003J\u000f\u00105\u001a\b\u0012\u0004\u0012\u00020\u000e0\rHÆ\u0003J\t\u00106\u001a\u00020\u0010HÆ\u0003J\u0015\u00107\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u0003HÆ\u0003J\u0015\u00108\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00140\u0003HÆ\u0003J\u0015\u00109\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00160\u0003HÆ\u0003JÉ\u0001\u0010:\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u00052\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0003j\u0002`\b2\b\b\u0002\u0010\t\u001a\u00020\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00042\u0018\b\u0002\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u00052\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u00032\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00140\u00032\u0014\b\u0002\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00160\u0003HÆ\u0001J\u0013\u0010;\u001a\u00020<2\b\u0010=\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010>\u001a\u00020\u001aHÖ\u0001J\t\u0010?\u001a\u00020\u0004HÖ\u0001J%\u0010@\u001a\u00020-2\u0006\u0010A\u001a\u00020\u00002\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020EH\u0001¢\u0006\u0002\bFR!\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR!\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0003j\u0002`\b¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\"R!\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001fR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u001d\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00120\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001fR\u001d\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00140\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001fR\u001d\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00160\u0003¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001f¨\u0006I"}, d2 = {"Lorg/fdroid/index/v2/RepoV2;", "", "name", "", "", "Lorg/fdroid/index/v2/LocalizedTextV2;", "icon", "Lorg/fdroid/index/v2/FileV2;", "Lorg/fdroid/index/v2/LocalizedFileV2;", "address", "webBaseUrl", "description", "mirrors", "", "Lorg/fdroid/index/v2/MirrorV2;", "timestamp", "", "antiFeatures", "Lorg/fdroid/index/v2/AntiFeatureV2;", "categories", "Lorg/fdroid/index/v2/CategoryV2;", "releaseChannels", "Lorg/fdroid/index/v2/ReleaseChannelV2;", "<init>", "(Ljava/util/Map;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/List;JLjava/util/Map;Ljava/util/Map;Ljava/util/Map;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/Map;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/List;JLjava/util/Map;Ljava/util/Map;Ljava/util/Map;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getName", "()Ljava/util/Map;", "getIcon", "getAddress", "()Ljava/lang/String;", "getWebBaseUrl", "getDescription", "getMirrors", "()Ljava/util/List;", "getTimestamp", "()J", "getAntiFeatures", "getCategories", "getReleaseChannels", "walkFiles", "", "fileConsumer", "Lkotlin/Function1;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$index_release", "$serializer", "Companion", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class RepoV2 {
    private static final KSerializer[] $childSerializers;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String address;
    private final Map<String, AntiFeatureV2> antiFeatures;
    private final Map<String, CategoryV2> categories;
    private final Map<String, String> description;
    private final Map<String, FileV2> icon;
    private final List<MirrorV2> mirrors;
    private final Map<String, String> name;
    private final Map<String, ReleaseChannelV2> releaseChannels;
    private final long timestamp;
    private final String webBaseUrl;

    public final Map<String, String> component1() {
        return this.name;
    }

    public final Map<String, ReleaseChannelV2> component10() {
        return this.releaseChannels;
    }

    public final Map<String, FileV2> component2() {
        return this.icon;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getAddress() {
        return this.address;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getWebBaseUrl() {
        return this.webBaseUrl;
    }

    public final Map<String, String> component5() {
        return this.description;
    }

    public final List<MirrorV2> component6() {
        return this.mirrors;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final long getTimestamp() {
        return this.timestamp;
    }

    public final Map<String, AntiFeatureV2> component8() {
        return this.antiFeatures;
    }

    public final Map<String, CategoryV2> component9() {
        return this.categories;
    }

    public final RepoV2 copy(Map<String, String> name, Map<String, FileV2> icon, String address, String webBaseUrl, Map<String, String> description, List<MirrorV2> mirrors, long timestamp, Map<String, AntiFeatureV2> antiFeatures, Map<String, CategoryV2> categories, Map<String, ReleaseChannelV2> releaseChannels) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(mirrors, "mirrors");
        Intrinsics.checkNotNullParameter(antiFeatures, "antiFeatures");
        Intrinsics.checkNotNullParameter(categories, "categories");
        Intrinsics.checkNotNullParameter(releaseChannels, "releaseChannels");
        return new RepoV2(name, icon, address, webBaseUrl, description, mirrors, timestamp, antiFeatures, categories, releaseChannels);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RepoV2)) {
            return false;
        }
        RepoV2 repoV2 = (RepoV2) other;
        return Intrinsics.areEqual(this.name, repoV2.name) && Intrinsics.areEqual(this.icon, repoV2.icon) && Intrinsics.areEqual(this.address, repoV2.address) && Intrinsics.areEqual(this.webBaseUrl, repoV2.webBaseUrl) && Intrinsics.areEqual(this.description, repoV2.description) && Intrinsics.areEqual(this.mirrors, repoV2.mirrors) && this.timestamp == repoV2.timestamp && Intrinsics.areEqual(this.antiFeatures, repoV2.antiFeatures) && Intrinsics.areEqual(this.categories, repoV2.categories) && Intrinsics.areEqual(this.releaseChannels, repoV2.releaseChannels);
    }

    public int hashCode() {
        int iHashCode = ((((this.name.hashCode() * 31) + this.icon.hashCode()) * 31) + this.address.hashCode()) * 31;
        String str = this.webBaseUrl;
        return ((((((((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.description.hashCode()) * 31) + this.mirrors.hashCode()) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.timestamp)) * 31) + this.antiFeatures.hashCode()) * 31) + this.categories.hashCode()) * 31) + this.releaseChannels.hashCode();
    }

    public String toString() {
        return "RepoV2(name=" + this.name + ", icon=" + this.icon + ", address=" + this.address + ", webBaseUrl=" + this.webBaseUrl + ", description=" + this.description + ", mirrors=" + this.mirrors + ", timestamp=" + this.timestamp + ", antiFeatures=" + this.antiFeatures + ", categories=" + this.categories + ", releaseChannels=" + this.releaseChannels + ")";
    }

    /* JADX INFO: compiled from: IndexV2.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lorg/fdroid/index/v2/RepoV2$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lorg/fdroid/index/v2/RepoV2;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer serializer() {
            return RepoV2$$serializer.INSTANCE;
        }
    }

    static {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        $childSerializers = new KSerializer[]{new LinkedHashMapSerializer(stringSerializer, stringSerializer), new LinkedHashMapSerializer(stringSerializer, FileV2$$serializer.INSTANCE), null, null, new LinkedHashMapSerializer(stringSerializer, stringSerializer), new ArrayListSerializer(MirrorV2$$serializer.INSTANCE), null, new LinkedHashMapSerializer(stringSerializer, AntiFeatureV2$$serializer.INSTANCE), new LinkedHashMapSerializer(stringSerializer, CategoryV2$$serializer.INSTANCE), new LinkedHashMapSerializer(stringSerializer, ReleaseChannelV2$$serializer.INSTANCE)};
    }

    public /* synthetic */ RepoV2(int i, Map map, Map map2, String str, String str2, Map map3, List list, long j, Map map4, Map map5, Map map6, SerializationConstructorMarker serializationConstructorMarker) {
        if (68 != (i & 68)) {
            PluginExceptionsKt.throwMissingFieldException(i, 68, RepoV2$$serializer.INSTANCE.getDescriptor());
        }
        this.name = (i & 1) == 0 ? MapsKt.emptyMap() : map;
        if ((i & 2) == 0) {
            this.icon = MapsKt.emptyMap();
        } else {
            this.icon = map2;
        }
        this.address = str;
        if ((i & 8) == 0) {
            this.webBaseUrl = null;
        } else {
            this.webBaseUrl = str2;
        }
        if ((i & 16) == 0) {
            this.description = MapsKt.emptyMap();
        } else {
            this.description = map3;
        }
        if ((i & 32) == 0) {
            this.mirrors = CollectionsKt.emptyList();
        } else {
            this.mirrors = list;
        }
        this.timestamp = j;
        if ((i & 128) == 0) {
            this.antiFeatures = MapsKt.emptyMap();
        } else {
            this.antiFeatures = map4;
        }
        if ((i & 256) == 0) {
            this.categories = MapsKt.emptyMap();
        } else {
            this.categories = map5;
        }
        if ((i & 512) == 0) {
            this.releaseChannels = MapsKt.emptyMap();
        } else {
            this.releaseChannels = map6;
        }
    }

    public static final /* synthetic */ void write$Self$index_release(RepoV2 self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer[] kSerializerArr = $childSerializers;
        if (output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual(self.name, MapsKt.emptyMap())) {
            output.encodeSerializableElement(serialDesc, 0, kSerializerArr[0], self.name);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || !Intrinsics.areEqual(self.icon, MapsKt.emptyMap())) {
            output.encodeSerializableElement(serialDesc, 1, kSerializerArr[1], self.icon);
        }
        output.encodeStringElement(serialDesc, 2, self.address);
        if (output.shouldEncodeElementDefault(serialDesc, 3) || self.webBaseUrl != null) {
            output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.webBaseUrl);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 4) || !Intrinsics.areEqual(self.description, MapsKt.emptyMap())) {
            output.encodeSerializableElement(serialDesc, 4, kSerializerArr[4], self.description);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 5) || !Intrinsics.areEqual(self.mirrors, CollectionsKt.emptyList())) {
            output.encodeSerializableElement(serialDesc, 5, kSerializerArr[5], self.mirrors);
        }
        output.encodeLongElement(serialDesc, 6, self.timestamp);
        if (output.shouldEncodeElementDefault(serialDesc, 7) || !Intrinsics.areEqual(self.antiFeatures, MapsKt.emptyMap())) {
            output.encodeSerializableElement(serialDesc, 7, kSerializerArr[7], self.antiFeatures);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 8) || !Intrinsics.areEqual(self.categories, MapsKt.emptyMap())) {
            output.encodeSerializableElement(serialDesc, 8, kSerializerArr[8], self.categories);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 9) && Intrinsics.areEqual(self.releaseChannels, MapsKt.emptyMap())) {
            return;
        }
        output.encodeSerializableElement(serialDesc, 9, kSerializerArr[9], self.releaseChannels);
    }

    public RepoV2(Map<String, String> name, Map<String, FileV2> icon, String address, String str, Map<String, String> description, List<MirrorV2> mirrors, long j, Map<String, AntiFeatureV2> antiFeatures, Map<String, CategoryV2> categories, Map<String, ReleaseChannelV2> releaseChannels) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(mirrors, "mirrors");
        Intrinsics.checkNotNullParameter(antiFeatures, "antiFeatures");
        Intrinsics.checkNotNullParameter(categories, "categories");
        Intrinsics.checkNotNullParameter(releaseChannels, "releaseChannels");
        this.name = name;
        this.icon = icon;
        this.address = address;
        this.webBaseUrl = str;
        this.description = description;
        this.mirrors = mirrors;
        this.timestamp = j;
        this.antiFeatures = antiFeatures;
        this.categories = categories;
        this.releaseChannels = releaseChannels;
    }

    public /* synthetic */ RepoV2(Map map, Map map2, String str, String str2, Map map3, List list, long j, Map map4, Map map5, Map map6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? MapsKt.emptyMap() : map, (i & 2) != 0 ? MapsKt.emptyMap() : map2, str, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? MapsKt.emptyMap() : map3, (i & 32) != 0 ? CollectionsKt.emptyList() : list, j, (i & 128) != 0 ? MapsKt.emptyMap() : map4, (i & 256) != 0 ? MapsKt.emptyMap() : map5, (i & 512) != 0 ? MapsKt.emptyMap() : map6);
    }

    public final Map<String, String> getName() {
        return this.name;
    }

    public final Map<String, FileV2> getIcon() {
        return this.icon;
    }

    public final String getAddress() {
        return this.address;
    }

    public final String getWebBaseUrl() {
        return this.webBaseUrl;
    }

    public final Map<String, String> getDescription() {
        return this.description;
    }

    public final List<MirrorV2> getMirrors() {
        return this.mirrors;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final Map<String, AntiFeatureV2> getAntiFeatures() {
        return this.antiFeatures;
    }

    public final Map<String, CategoryV2> getCategories() {
        return this.categories;
    }

    public final Map<String, ReleaseChannelV2> getReleaseChannels() {
        return this.releaseChannels;
    }

    public final void walkFiles(Function1 fileConsumer) {
        Intrinsics.checkNotNullParameter(fileConsumer, "fileConsumer");
        Iterator<T> it = this.icon.values().iterator();
        while (it.hasNext()) {
            fileConsumer.invoke((FileV2) it.next());
        }
        Iterator<T> it2 = this.antiFeatures.values().iterator();
        while (it2.hasNext()) {
            Iterator<T> it3 = ((AntiFeatureV2) it2.next()).getIcon().values().iterator();
            while (it3.hasNext()) {
                fileConsumer.invoke((FileV2) it3.next());
            }
        }
        Iterator<T> it4 = this.categories.values().iterator();
        while (it4.hasNext()) {
            Iterator<T> it5 = ((CategoryV2) it4.next()).getIcon().values().iterator();
            while (it5.hasNext()) {
                fileConsumer.invoke((FileV2) it5.next());
            }
        }
    }
}
