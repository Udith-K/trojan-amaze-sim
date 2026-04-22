package org.fdroid.index.v2;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: PackageV2.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 /2\u00020\u0001:\u0002./B»\u0001\u0012\"\b\u0002\u0010\u0002\u001a\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003j\u0004\u0018\u0001`\u0007\u0012\"\b\u0002\u0010\b\u001a\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003j\u0004\u0018\u0001`\u0007\u0012\"\b\u0002\u0010\t\u001a\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003j\u0004\u0018\u0001`\u0007\u0012\"\b\u0002\u0010\n\u001a\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003j\u0004\u0018\u0001`\u0007\u0012\"\b\u0002\u0010\u000b\u001a\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003j\u0004\u0018\u0001`\u0007¢\u0006\u0004\b\f\u0010\rB§\u0001\b\u0010\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003\u0012\u001a\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003\u0012\u001a\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003\u0012\u001a\u0010\n\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003\u0012\u001a\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\f\u0010\u0012J#\u0010\u001c\u001a\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003j\u0004\u0018\u0001`\u0007HÆ\u0003J#\u0010\u001d\u001a\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003j\u0004\u0018\u0001`\u0007HÆ\u0003J#\u0010\u001e\u001a\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003j\u0004\u0018\u0001`\u0007HÆ\u0003J#\u0010\u001f\u001a\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003j\u0004\u0018\u0001`\u0007HÆ\u0003J#\u0010 \u001a\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003j\u0004\u0018\u0001`\u0007HÆ\u0003J½\u0001\u0010!\u001a\u00020\u00002\"\b\u0002\u0010\u0002\u001a\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003j\u0004\u0018\u0001`\u00072\"\b\u0002\u0010\b\u001a\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003j\u0004\u0018\u0001`\u00072\"\b\u0002\u0010\t\u001a\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003j\u0004\u0018\u0001`\u00072\"\b\u0002\u0010\n\u001a\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003j\u0004\u0018\u0001`\u00072\"\b\u0002\u0010\u000b\u001a\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003j\u0004\u0018\u0001`\u0007HÆ\u0001J\u0013\u0010\"\u001a\u00020\u001a2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\u000fHÖ\u0001J\t\u0010%\u001a\u00020\u0004HÖ\u0001J%\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00002\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0001¢\u0006\u0002\b-R+\u0010\u0002\u001a\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003j\u0004\u0018\u0001`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R+\u0010\b\u001a\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003j\u0004\u0018\u0001`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R+\u0010\t\u001a\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003j\u0004\u0018\u0001`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R+\u0010\n\u001a\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003j\u0004\u0018\u0001`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R+\u0010\u000b\u001a\u001c\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0018\u00010\u0003j\u0004\u0018\u0001`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\u0019\u001a\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001b¨\u00060"}, d2 = {"Lorg/fdroid/index/v2/Screenshots;", "", "phone", "", "", "", "Lorg/fdroid/index/v2/FileV2;", "Lorg/fdroid/index/v2/LocalizedFileListV2;", "sevenInch", "tenInch", "wear", "tv", "<init>", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getPhone", "()Ljava/util/Map;", "getSevenInch", "getTenInch", "getWear", "getTv", "isNull", "", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$index_release", "$serializer", "Companion", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class Screenshots {
    private static final KSerializer[] $childSerializers;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Map<String, List<FileV2>> phone;
    private final Map<String, List<FileV2>> sevenInch;
    private final Map<String, List<FileV2>> tenInch;
    private final Map<String, List<FileV2>> tv;
    private final Map<String, List<FileV2>> wear;

    public Screenshots() {
        this((Map) null, (Map) null, (Map) null, (Map) null, (Map) null, 31, (DefaultConstructorMarker) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Screenshots copy$default(Screenshots screenshots, Map map, Map map2, Map map3, Map map4, Map map5, int i, Object obj) {
        if ((i & 1) != 0) {
            map = screenshots.phone;
        }
        if ((i & 2) != 0) {
            map2 = screenshots.sevenInch;
        }
        Map map6 = map2;
        if ((i & 4) != 0) {
            map3 = screenshots.tenInch;
        }
        Map map7 = map3;
        if ((i & 8) != 0) {
            map4 = screenshots.wear;
        }
        Map map8 = map4;
        if ((i & 16) != 0) {
            map5 = screenshots.tv;
        }
        return screenshots.copy(map, map6, map7, map8, map5);
    }

    public final Map<String, List<FileV2>> component1() {
        return this.phone;
    }

    public final Map<String, List<FileV2>> component2() {
        return this.sevenInch;
    }

    public final Map<String, List<FileV2>> component3() {
        return this.tenInch;
    }

    public final Map<String, List<FileV2>> component4() {
        return this.wear;
    }

    public final Map<String, List<FileV2>> component5() {
        return this.tv;
    }

    public final Screenshots copy(Map<String, ? extends List<FileV2>> phone, Map<String, ? extends List<FileV2>> sevenInch, Map<String, ? extends List<FileV2>> tenInch, Map<String, ? extends List<FileV2>> wear, Map<String, ? extends List<FileV2>> tv) {
        return new Screenshots(phone, sevenInch, tenInch, wear, tv);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Screenshots)) {
            return false;
        }
        Screenshots screenshots = (Screenshots) other;
        return Intrinsics.areEqual(this.phone, screenshots.phone) && Intrinsics.areEqual(this.sevenInch, screenshots.sevenInch) && Intrinsics.areEqual(this.tenInch, screenshots.tenInch) && Intrinsics.areEqual(this.wear, screenshots.wear) && Intrinsics.areEqual(this.tv, screenshots.tv);
    }

    public int hashCode() {
        Map<String, List<FileV2>> map = this.phone;
        int iHashCode = (map == null ? 0 : map.hashCode()) * 31;
        Map<String, List<FileV2>> map2 = this.sevenInch;
        int iHashCode2 = (iHashCode + (map2 == null ? 0 : map2.hashCode())) * 31;
        Map<String, List<FileV2>> map3 = this.tenInch;
        int iHashCode3 = (iHashCode2 + (map3 == null ? 0 : map3.hashCode())) * 31;
        Map<String, List<FileV2>> map4 = this.wear;
        int iHashCode4 = (iHashCode3 + (map4 == null ? 0 : map4.hashCode())) * 31;
        Map<String, List<FileV2>> map5 = this.tv;
        return iHashCode4 + (map5 != null ? map5.hashCode() : 0);
    }

    public String toString() {
        return "Screenshots(phone=" + this.phone + ", sevenInch=" + this.sevenInch + ", tenInch=" + this.tenInch + ", wear=" + this.wear + ", tv=" + this.tv + ")";
    }

    /* JADX INFO: compiled from: PackageV2.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lorg/fdroid/index/v2/Screenshots$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lorg/fdroid/index/v2/Screenshots;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer serializer() {
            return Screenshots$$serializer.INSTANCE;
        }
    }

    static {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        FileV2$$serializer fileV2$$serializer = FileV2$$serializer.INSTANCE;
        $childSerializers = new KSerializer[]{new LinkedHashMapSerializer(stringSerializer, new ArrayListSerializer(fileV2$$serializer)), new LinkedHashMapSerializer(stringSerializer, new ArrayListSerializer(fileV2$$serializer)), new LinkedHashMapSerializer(stringSerializer, new ArrayListSerializer(fileV2$$serializer)), new LinkedHashMapSerializer(stringSerializer, new ArrayListSerializer(fileV2$$serializer)), new LinkedHashMapSerializer(stringSerializer, new ArrayListSerializer(fileV2$$serializer))};
    }

    public /* synthetic */ Screenshots(int i, Map map, Map map2, Map map3, Map map4, Map map5, SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 1) == 0) {
            this.phone = null;
        } else {
            this.phone = map;
        }
        if ((i & 2) == 0) {
            this.sevenInch = null;
        } else {
            this.sevenInch = map2;
        }
        if ((i & 4) == 0) {
            this.tenInch = null;
        } else {
            this.tenInch = map3;
        }
        if ((i & 8) == 0) {
            this.wear = null;
        } else {
            this.wear = map4;
        }
        if ((i & 16) == 0) {
            this.tv = null;
        } else {
            this.tv = map5;
        }
    }

    public static final /* synthetic */ void write$Self$index_release(Screenshots self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer[] kSerializerArr = $childSerializers;
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.phone != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, kSerializerArr[0], self.phone);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.sevenInch != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, kSerializerArr[1], self.sevenInch);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.tenInch != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, kSerializerArr[2], self.tenInch);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || self.wear != null) {
            output.encodeNullableSerializableElement(serialDesc, 3, kSerializerArr[3], self.wear);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 4) && self.tv == null) {
            return;
        }
        output.encodeNullableSerializableElement(serialDesc, 4, kSerializerArr[4], self.tv);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Screenshots(Map<String, ? extends List<FileV2>> map, Map<String, ? extends List<FileV2>> map2, Map<String, ? extends List<FileV2>> map3, Map<String, ? extends List<FileV2>> map4, Map<String, ? extends List<FileV2>> map5) {
        this.phone = map;
        this.sevenInch = map2;
        this.tenInch = map3;
        this.wear = map4;
        this.tv = map5;
    }

    public /* synthetic */ Screenshots(Map map, Map map2, Map map3, Map map4, Map map5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : map, (i & 2) != 0 ? null : map2, (i & 4) != 0 ? null : map3, (i & 8) != 0 ? null : map4, (i & 16) != 0 ? null : map5);
    }

    public final Map<String, List<FileV2>> getPhone() {
        return this.phone;
    }

    public final Map<String, List<FileV2>> getSevenInch() {
        return this.sevenInch;
    }

    public final Map<String, List<FileV2>> getTenInch() {
        return this.tenInch;
    }

    public final Map<String, List<FileV2>> getWear() {
        return this.wear;
    }

    public final Map<String, List<FileV2>> getTv() {
        return this.tv;
    }

    public final boolean isNull() {
        return this.phone == null && this.sevenInch == null && this.tenInch == null && this.wear == null && this.tv == null;
    }
}
