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
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \"2\u00020\u0001:\u0002!\"B9\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u0005\u0012\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u0005¢\u0006\u0004\b\u0007\u0010\bBG\b\u0010\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u0007\u0010\rJ\u0019\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u0005HÆ\u0003J\u0019\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u0005HÆ\u0003J=\u0010\u0013\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u00052\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\nHÖ\u0001J\t\u0010\u0018\u001a\u00020\u0004HÖ\u0001J%\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0001¢\u0006\u0002\b R!\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR!\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f¨\u0006#"}, d2 = {"Lorg/fdroid/index/v2/ReleaseChannelV2;", "", "name", "", "", "Lorg/fdroid/index/v2/LocalizedTextV2;", "description", "<init>", "(Ljava/util/Map;Ljava/util/Map;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/Map;Ljava/util/Map;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getName", "()Ljava/util/Map;", "getDescription", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$index_release", "$serializer", "Companion", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class ReleaseChannelV2 {
    private static final KSerializer[] $childSerializers;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Map<String, String> description;
    private final Map<String, String> name;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ReleaseChannelV2 copy$default(ReleaseChannelV2 releaseChannelV2, Map map, Map map2, int i, Object obj) {
        if ((i & 1) != 0) {
            map = releaseChannelV2.name;
        }
        if ((i & 2) != 0) {
            map2 = releaseChannelV2.description;
        }
        return releaseChannelV2.copy(map, map2);
    }

    public final Map<String, String> component1() {
        return this.name;
    }

    public final Map<String, String> component2() {
        return this.description;
    }

    public final ReleaseChannelV2 copy(Map<String, String> name, Map<String, String> description) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(description, "description");
        return new ReleaseChannelV2(name, description);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ReleaseChannelV2)) {
            return false;
        }
        ReleaseChannelV2 releaseChannelV2 = (ReleaseChannelV2) other;
        return Intrinsics.areEqual(this.name, releaseChannelV2.name) && Intrinsics.areEqual(this.description, releaseChannelV2.description);
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + this.description.hashCode();
    }

    public String toString() {
        return "ReleaseChannelV2(name=" + this.name + ", description=" + this.description + ")";
    }

    /* JADX INFO: compiled from: IndexV2.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lorg/fdroid/index/v2/ReleaseChannelV2$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lorg/fdroid/index/v2/ReleaseChannelV2;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer serializer() {
            return ReleaseChannelV2$$serializer.INSTANCE;
        }
    }

    static {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        $childSerializers = new KSerializer[]{new LinkedHashMapSerializer(stringSerializer, stringSerializer), new LinkedHashMapSerializer(stringSerializer, stringSerializer)};
    }

    public /* synthetic */ ReleaseChannelV2(int i, Map map, Map map2, SerializationConstructorMarker serializationConstructorMarker) {
        if (1 != (i & 1)) {
            PluginExceptionsKt.throwMissingFieldException(i, 1, ReleaseChannelV2$$serializer.INSTANCE.getDescriptor());
        }
        this.name = map;
        if ((i & 2) == 0) {
            this.description = MapsKt.emptyMap();
        } else {
            this.description = map2;
        }
    }

    public static final /* synthetic */ void write$Self$index_release(ReleaseChannelV2 self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer[] kSerializerArr = $childSerializers;
        output.encodeSerializableElement(serialDesc, 0, kSerializerArr[0], self.name);
        if (!output.shouldEncodeElementDefault(serialDesc, 1) && Intrinsics.areEqual(self.description, MapsKt.emptyMap())) {
            return;
        }
        output.encodeSerializableElement(serialDesc, 1, kSerializerArr[1], self.description);
    }

    public ReleaseChannelV2(Map<String, String> name, Map<String, String> description) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(description, "description");
        this.name = name;
        this.description = description;
    }

    public final Map<String, String> getName() {
        return this.name;
    }

    public /* synthetic */ ReleaseChannelV2(Map map, Map map2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(map, (i & 2) != 0 ? MapsKt.emptyMap() : map2);
    }

    public final Map<String, String> getDescription() {
        return this.description;
    }
}
