package org.fdroid.index.v2;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: IndexV2.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 22\u00020\u0001:\u000212BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\b0\n¢\u0006\u0004\b\f\u0010\rBU\b\u0010\u0012\u0006\u0010\u000e\u001a\u00020\u0006\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\b\u0018\u00010\n\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\f\u0010\u0011J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0002\u001a\u00020\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0016J\t\u0010 \u001a\u00020\bHÆ\u0003J\u0015\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\b0\nHÆ\u0003JN\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\b0\nHÆ\u0001¢\u0006\u0002\u0010#J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010'\u001a\u00020\u0006HÖ\u0001J\t\u0010(\u001a\u00020\u000bHÖ\u0001J%\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00002\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0001¢\u0006\u0002\b0R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u00063"}, d2 = {"Lorg/fdroid/index/v2/Entry;", "", "timestamp", "", "version", "maxAge", "", "index", "Lorg/fdroid/index/v2/EntryFileV2;", "diffs", "", "", "<init>", "(JJLjava/lang/Integer;Lorg/fdroid/index/v2/EntryFileV2;Ljava/util/Map;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IJJLjava/lang/Integer;Lorg/fdroid/index/v2/EntryFileV2;Ljava/util/Map;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getTimestamp", "()J", "getVersion", "getMaxAge", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getIndex", "()Lorg/fdroid/index/v2/EntryFileV2;", "getDiffs", "()Ljava/util/Map;", "getDiff", "component1", "component2", "component3", "component4", "component5", "copy", "(JJLjava/lang/Integer;Lorg/fdroid/index/v2/EntryFileV2;Ljava/util/Map;)Lorg/fdroid/index/v2/Entry;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$index_release", "$serializer", "Companion", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class Entry {
    private final Map<String, EntryFileV2> diffs;
    private final EntryFileV2 index;
    private final Integer maxAge;
    private final long timestamp;
    private final long version;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KSerializer[] $childSerializers = {null, null, null, null, new LinkedHashMapSerializer(StringSerializer.INSTANCE, EntryFileV2$$serializer.INSTANCE)};

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getTimestamp() {
        return this.timestamp;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getVersion() {
        return this.version;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Integer getMaxAge() {
        return this.maxAge;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final EntryFileV2 getIndex() {
        return this.index;
    }

    public final Map<String, EntryFileV2> component5() {
        return this.diffs;
    }

    public final Entry copy(long timestamp, long version, Integer maxAge, EntryFileV2 index, Map<String, EntryFileV2> diffs) {
        Intrinsics.checkNotNullParameter(index, "index");
        Intrinsics.checkNotNullParameter(diffs, "diffs");
        return new Entry(timestamp, version, maxAge, index, diffs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Entry)) {
            return false;
        }
        Entry entry = (Entry) other;
        return this.timestamp == entry.timestamp && this.version == entry.version && Intrinsics.areEqual(this.maxAge, entry.maxAge) && Intrinsics.areEqual(this.index, entry.index) && Intrinsics.areEqual(this.diffs, entry.diffs);
    }

    public int hashCode() {
        int iM = ((LongObjectMap$$ExternalSyntheticBackport0.m(this.timestamp) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.version)) * 31;
        Integer num = this.maxAge;
        return ((((iM + (num == null ? 0 : num.hashCode())) * 31) + this.index.hashCode()) * 31) + this.diffs.hashCode();
    }

    public String toString() {
        return "Entry(timestamp=" + this.timestamp + ", version=" + this.version + ", maxAge=" + this.maxAge + ", index=" + this.index + ", diffs=" + this.diffs + ")";
    }

    /* JADX INFO: compiled from: IndexV2.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lorg/fdroid/index/v2/Entry$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lorg/fdroid/index/v2/Entry;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer serializer() {
            return Entry$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ Entry(int i, long j, long j2, Integer num, EntryFileV2 entryFileV2, Map map, SerializationConstructorMarker serializationConstructorMarker) {
        if (11 != (i & 11)) {
            PluginExceptionsKt.throwMissingFieldException(i, 11, Entry$$serializer.INSTANCE.getDescriptor());
        }
        this.timestamp = j;
        this.version = j2;
        if ((i & 4) == 0) {
            this.maxAge = null;
        } else {
            this.maxAge = num;
        }
        this.index = entryFileV2;
        if ((i & 16) == 0) {
            this.diffs = MapsKt.emptyMap();
        } else {
            this.diffs = map;
        }
    }

    public static final /* synthetic */ void write$Self$index_release(Entry self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer[] kSerializerArr = $childSerializers;
        output.encodeLongElement(serialDesc, 0, self.timestamp);
        output.encodeLongElement(serialDesc, 1, self.version);
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.maxAge != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, IntSerializer.INSTANCE, self.maxAge);
        }
        output.encodeSerializableElement(serialDesc, 3, EntryFileV2$$serializer.INSTANCE, self.index);
        if (!output.shouldEncodeElementDefault(serialDesc, 4) && Intrinsics.areEqual(self.diffs, MapsKt.emptyMap())) {
            return;
        }
        output.encodeSerializableElement(serialDesc, 4, kSerializerArr[4], self.diffs);
    }

    public Entry(long j, long j2, Integer num, EntryFileV2 index, Map<String, EntryFileV2> diffs) {
        Intrinsics.checkNotNullParameter(index, "index");
        Intrinsics.checkNotNullParameter(diffs, "diffs");
        this.timestamp = j;
        this.version = j2;
        this.maxAge = num;
        this.index = index;
        this.diffs = diffs;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final long getVersion() {
        return this.version;
    }

    public final Integer getMaxAge() {
        return this.maxAge;
    }

    public final EntryFileV2 getIndex() {
        return this.index;
    }

    public /* synthetic */ Entry(long j, long j2, Integer num, EntryFileV2 entryFileV2, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, (i & 4) != 0 ? null : num, entryFileV2, (i & 16) != 0 ? MapsKt.emptyMap() : map);
    }

    public final Map<String, EntryFileV2> getDiffs() {
        return this.diffs;
    }

    public final EntryFileV2 getDiff(long timestamp) {
        return this.diffs.get(String.valueOf(timestamp));
    }
}
