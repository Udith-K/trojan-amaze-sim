package org.fdroid.index.v1;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.fdroid.index.IndexConverterKt;
import org.fdroid.index.v2.AntiFeatureV2;
import org.fdroid.index.v2.CategoryV2;
import org.fdroid.index.v2.FileV2;
import org.fdroid.index.v2.MirrorV2;
import org.fdroid.index.v2.ReleaseChannelV2;
import org.fdroid.index.v2.RepoV2;

/* JADX INFO: compiled from: IndexV1.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 F2\u00020\u0001:\u0002EFBS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\r¢\u0006\u0004\b\u000e\u0010\u000fBm\b\u0010\u0012\u0006\u0010\u0010\u001a\u00020\u0005\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\b\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\r\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u000e\u0010\u0013JL\u0010$\u001a\u00020%2\b\b\u0002\u0010&\u001a\u00020\b2\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020)0(2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020+0(2\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020-0(J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0005HÆ\u0003J\u0010\u00100\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u001bJ\t\u00101\u001a\u00020\bHÆ\u0003J\t\u00102\u001a\u00020\bHÆ\u0003J\t\u00103\u001a\u00020\bHÆ\u0003J\t\u00104\u001a\u00020\bHÆ\u0003J\u000f\u00105\u001a\b\u0012\u0004\u0012\u00020\b0\rHÆ\u0003Jf\u00106\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\rHÆ\u0001¢\u0006\u0002\u00107J\u0013\u00108\u001a\u0002092\b\u0010:\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010;\u001a\u00020\u0005HÖ\u0001J\t\u0010<\u001a\u00020\bHÖ\u0001J%\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020\u00002\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020CH\u0001¢\u0006\u0002\bDR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R \u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001c\u0012\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001eR\u0011\u0010\n\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001eR\u0011\u0010\u000b\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001eR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\r¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#¨\u0006G"}, d2 = {"Lorg/fdroid/index/v1/RepoV1;", "", "timestamp", "", "version", "", "maxAge", "name", "", "icon", "address", "description", "mirrors", "", "<init>", "(JILjava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IJILjava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getTimestamp", "()J", "getVersion", "()I", "getMaxAge$annotations", "()V", "getMaxAge", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getName", "()Ljava/lang/String;", "getIcon", "getAddress", "getDescription", "getMirrors", "()Ljava/util/List;", "toRepoV2", "Lorg/fdroid/index/v2/RepoV2;", "locale", "antiFeatures", "", "Lorg/fdroid/index/v2/AntiFeatureV2;", "categories", "Lorg/fdroid/index/v2/CategoryV2;", "releaseChannels", "Lorg/fdroid/index/v2/ReleaseChannelV2;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(JILjava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)Lorg/fdroid/index/v1/RepoV1;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$index_release", "$serializer", "Companion", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class RepoV1 {
    private final String address;
    private final String description;
    private final String icon;
    private final Integer maxAge;
    private final List<String> mirrors;
    private final String name;
    private final long timestamp;
    private final int version;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KSerializer[] $childSerializers = {null, null, null, null, null, null, null, new ArrayListSerializer(StringSerializer.INSTANCE)};

    public static /* synthetic */ void getMaxAge$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getTimestamp() {
        return this.timestamp;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getVersion() {
        return this.version;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Integer getMaxAge() {
        return this.maxAge;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getIcon() {
        return this.icon;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getAddress() {
        return this.address;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    public final List<String> component8() {
        return this.mirrors;
    }

    public final RepoV1 copy(long timestamp, int version, Integer maxAge, String name, String icon, String address, String description, List<String> mirrors) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(mirrors, "mirrors");
        return new RepoV1(timestamp, version, maxAge, name, icon, address, description, mirrors);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RepoV1)) {
            return false;
        }
        RepoV1 repoV1 = (RepoV1) other;
        return this.timestamp == repoV1.timestamp && this.version == repoV1.version && Intrinsics.areEqual(this.maxAge, repoV1.maxAge) && Intrinsics.areEqual(this.name, repoV1.name) && Intrinsics.areEqual(this.icon, repoV1.icon) && Intrinsics.areEqual(this.address, repoV1.address) && Intrinsics.areEqual(this.description, repoV1.description) && Intrinsics.areEqual(this.mirrors, repoV1.mirrors);
    }

    public int hashCode() {
        int iM = ((LongObjectMap$$ExternalSyntheticBackport0.m(this.timestamp) * 31) + this.version) * 31;
        Integer num = this.maxAge;
        return ((((((((((iM + (num == null ? 0 : num.hashCode())) * 31) + this.name.hashCode()) * 31) + this.icon.hashCode()) * 31) + this.address.hashCode()) * 31) + this.description.hashCode()) * 31) + this.mirrors.hashCode();
    }

    public String toString() {
        return "RepoV1(timestamp=" + this.timestamp + ", version=" + this.version + ", maxAge=" + this.maxAge + ", name=" + this.name + ", icon=" + this.icon + ", address=" + this.address + ", description=" + this.description + ", mirrors=" + this.mirrors + ")";
    }

    /* JADX INFO: compiled from: IndexV1.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lorg/fdroid/index/v1/RepoV1$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lorg/fdroid/index/v1/RepoV1;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer serializer() {
            return RepoV1$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ RepoV1(int i, long j, int i2, Integer num, String str, String str2, String str3, String str4, List list, SerializationConstructorMarker serializationConstructorMarker) {
        if (123 != (i & 123)) {
            PluginExceptionsKt.throwMissingFieldException(i, 123, RepoV1$$serializer.INSTANCE.getDescriptor());
        }
        this.timestamp = j;
        this.version = i2;
        if ((i & 4) == 0) {
            this.maxAge = null;
        } else {
            this.maxAge = num;
        }
        this.name = str;
        this.icon = str2;
        this.address = str3;
        this.description = str4;
        if ((i & 128) == 0) {
            this.mirrors = CollectionsKt.emptyList();
        } else {
            this.mirrors = list;
        }
    }

    public static final /* synthetic */ void write$Self$index_release(RepoV1 self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer[] kSerializerArr = $childSerializers;
        output.encodeLongElement(serialDesc, 0, self.timestamp);
        output.encodeIntElement(serialDesc, 1, self.version);
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.maxAge != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, IntSerializer.INSTANCE, self.maxAge);
        }
        output.encodeStringElement(serialDesc, 3, self.name);
        output.encodeStringElement(serialDesc, 4, self.icon);
        output.encodeStringElement(serialDesc, 5, self.address);
        output.encodeStringElement(serialDesc, 6, self.description);
        if (!output.shouldEncodeElementDefault(serialDesc, 7) && Intrinsics.areEqual(self.mirrors, CollectionsKt.emptyList())) {
            return;
        }
        output.encodeSerializableElement(serialDesc, 7, kSerializerArr[7], self.mirrors);
    }

    public RepoV1(long j, int i, Integer num, String name, String icon, String address, String description, List<String> mirrors) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(mirrors, "mirrors");
        this.timestamp = j;
        this.version = i;
        this.maxAge = num;
        this.name = name;
        this.icon = icon;
        this.address = address;
        this.description = description;
        this.mirrors = mirrors;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final int getVersion() {
        return this.version;
    }

    public final Integer getMaxAge() {
        return this.maxAge;
    }

    public final String getName() {
        return this.name;
    }

    public final String getIcon() {
        return this.icon;
    }

    public final String getAddress() {
        return this.address;
    }

    public final String getDescription() {
        return this.description;
    }

    public /* synthetic */ RepoV1(long j, int i, Integer num, String str, String str2, String str3, String str4, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, i, (i2 & 4) != 0 ? null : num, str, str2, str3, str4, (i2 & 128) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final List<String> getMirrors() {
        return this.mirrors;
    }

    public static /* synthetic */ RepoV2 toRepoV2$default(RepoV1 repoV1, String str, Map map, Map map2, Map map3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = IndexConverterKt.DEFAULT_LOCALE;
        }
        return repoV1.toRepoV2(str, map, map2, map3);
    }

    public final RepoV2 toRepoV2(String locale, Map<String, AntiFeatureV2> antiFeatures, Map<String, CategoryV2> categories, Map<String, ReleaseChannelV2> releaseChannels) {
        Intrinsics.checkNotNullParameter(locale, "locale");
        Intrinsics.checkNotNullParameter(antiFeatures, "antiFeatures");
        Intrinsics.checkNotNullParameter(categories, "categories");
        Intrinsics.checkNotNullParameter(releaseChannels, "releaseChannels");
        Map mapMapOf = MapsKt.mapOf(TuplesKt.to(locale, this.name));
        Map mapMapOf2 = MapsKt.mapOf(TuplesKt.to(locale, new FileV2("/icons/" + this.icon, (String) null, (Long) null, (String) null, 14, (DefaultConstructorMarker) null)));
        String str = this.address;
        Map mapMapOf3 = MapsKt.mapOf(TuplesKt.to(locale, this.description));
        List<String> list = this.mirrors;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new MirrorV2((String) it.next(), (String) null, 2, (DefaultConstructorMarker) null));
        }
        return new RepoV2(mapMapOf, mapMapOf2, str, null, mapMapOf3, arrayList, this.timestamp, antiFeatures, categories, releaseChannels);
    }
}
