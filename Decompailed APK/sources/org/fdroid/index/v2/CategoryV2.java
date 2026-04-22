package org.fdroid.index.v2;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* JADX INFO: compiled from: IndexV2.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 '2\u00020\u0001:\u0002&'BS\u0012\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u0002`\u0006\u0012\u0016\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\b\u0012\u0018\b\u0002\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\b¢\u0006\u0004\b\n\u0010\u000bB]\b\u0010\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003\u0012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\n\u0010\u0010J\u0019\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u0002`\u0006HÆ\u0003J\u0019\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\bHÆ\u0003J\u0019\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\bHÆ\u0003JW\u0010\u0018\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u0002`\u00062\u0018\b\u0002\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\b2\u0018\b\u0002\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\bHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\rHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0004HÖ\u0001J%\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0001¢\u0006\u0002\b%R!\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003j\u0002`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R!\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R!\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012¨\u0006("}, d2 = {"Lorg/fdroid/index/v2/CategoryV2;", "", "icon", "", "", "Lorg/fdroid/index/v2/FileV2;", "Lorg/fdroid/index/v2/LocalizedFileV2;", "name", "Lorg/fdroid/index/v2/LocalizedTextV2;", "description", "<init>", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/Map;Ljava/util/Map;Ljava/util/Map;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getIcon", "()Ljava/util/Map;", "getName", "getDescription", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$index_release", "$serializer", "Companion", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class CategoryV2 {
    private static final KSerializer[] $childSerializers;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Map<String, String> description;
    private final Map<String, FileV2> icon;
    private final Map<String, String> name;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CategoryV2 copy$default(CategoryV2 categoryV2, Map map, Map map2, Map map3, int i, Object obj) {
        if ((i & 1) != 0) {
            map = categoryV2.icon;
        }
        if ((i & 2) != 0) {
            map2 = categoryV2.name;
        }
        if ((i & 4) != 0) {
            map3 = categoryV2.description;
        }
        return categoryV2.copy(map, map2, map3);
    }

    public final Map<String, FileV2> component1() {
        return this.icon;
    }

    public final Map<String, String> component2() {
        return this.name;
    }

    public final Map<String, String> component3() {
        return this.description;
    }

    public final CategoryV2 copy(Map<String, FileV2> icon, Map<String, String> name, Map<String, String> description) {
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(description, "description");
        return new CategoryV2(icon, name, description);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CategoryV2)) {
            return false;
        }
        CategoryV2 categoryV2 = (CategoryV2) other;
        return Intrinsics.areEqual(this.icon, categoryV2.icon) && Intrinsics.areEqual(this.name, categoryV2.name) && Intrinsics.areEqual(this.description, categoryV2.description);
    }

    public int hashCode() {
        return (((this.icon.hashCode() * 31) + this.name.hashCode()) * 31) + this.description.hashCode();
    }

    public String toString() {
        return "CategoryV2(icon=" + this.icon + ", name=" + this.name + ", description=" + this.description + ")";
    }

    /* JADX INFO: compiled from: IndexV2.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lorg/fdroid/index/v2/CategoryV2$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lorg/fdroid/index/v2/CategoryV2;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer serializer() {
            return CategoryV2$$serializer.INSTANCE;
        }
    }

    static {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        $childSerializers = new KSerializer[]{new LinkedHashMapSerializer(stringSerializer, FileV2$$serializer.INSTANCE), new LinkedHashMapSerializer(stringSerializer, stringSerializer), new LinkedHashMapSerializer(stringSerializer, stringSerializer)};
    }

    public /* synthetic */ CategoryV2(int i, Map map, Map map2, Map map3, SerializationConstructorMarker serializationConstructorMarker) {
        if (2 != (i & 2)) {
            PluginExceptionsKt.throwMissingFieldException(i, 2, CategoryV2$$serializer.INSTANCE.getDescriptor());
        }
        this.icon = (i & 1) == 0 ? MapsKt.emptyMap() : map;
        this.name = map2;
        if ((i & 4) == 0) {
            this.description = MapsKt.emptyMap();
        } else {
            this.description = map3;
        }
    }

    public static final /* synthetic */ void write$Self$index_release(CategoryV2 self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer[] kSerializerArr = $childSerializers;
        if (output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual(self.icon, MapsKt.emptyMap())) {
            output.encodeSerializableElement(serialDesc, 0, kSerializerArr[0], self.icon);
        }
        output.encodeSerializableElement(serialDesc, 1, kSerializerArr[1], self.name);
        if (!output.shouldEncodeElementDefault(serialDesc, 2) && Intrinsics.areEqual(self.description, MapsKt.emptyMap())) {
            return;
        }
        output.encodeSerializableElement(serialDesc, 2, kSerializerArr[2], self.description);
    }

    public CategoryV2(Map<String, FileV2> icon, Map<String, String> name, Map<String, String> description) {
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(description, "description");
        this.icon = icon;
        this.name = name;
        this.description = description;
    }

    public /* synthetic */ CategoryV2(Map map, Map map2, Map map3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? MapsKt.emptyMap() : map, map2, (i & 4) != 0 ? MapsKt.emptyMap() : map3);
    }

    public final Map<String, FileV2> getIcon() {
        return this.icon;
    }

    public final Map<String, String> getName() {
        return this.name;
    }

    public final Map<String, String> getDescription() {
        return this.description;
    }
}
