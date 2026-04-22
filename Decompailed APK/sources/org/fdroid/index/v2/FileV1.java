package org.fdroid.index.v2;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.Json;
import org.fdroid.IndexFile;
import org.fdroid.index.IndexParser;

/* JADX INFO: compiled from: PackageV2.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 -2\u00020\u0001:\u0002-.B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\b\u0010\tBC\b\u0010\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\b\u0010\u000eJ\b\u0010\u0018\u001a\u00020\u0003H\u0016J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0013J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J:\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u001eJ\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\t\u0010#\u001a\u00020\u000bHÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001J%\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00002\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0001¢\u0006\u0002\b,R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0096\u0004¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0016X\u0097\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0010¨\u0006/"}, d2 = {"Lorg/fdroid/index/v2/FileV1;", "Lorg/fdroid/IndexFile;", "name", "", "sha256", "size", "", "ipfsCidV1", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getName", "()Ljava/lang/String;", "getSha256", "getSize", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getIpfsCidV1$annotations", "()V", "getIpfsCidV1", "serialize", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;)Lorg/fdroid/index/v2/FileV1;", "equals", "", "other", "", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$index_release", "Companion", "$serializer", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class FileV1 implements IndexFile {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String ipfsCidV1;
    private final String name;
    private final String sha256;
    private final Long size;

    public static /* synthetic */ FileV1 copy$default(FileV1 fileV1, String str, String str2, Long l, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = fileV1.name;
        }
        if ((i & 2) != 0) {
            str2 = fileV1.sha256;
        }
        if ((i & 4) != 0) {
            l = fileV1.size;
        }
        if ((i & 8) != 0) {
            str3 = fileV1.ipfsCidV1;
        }
        return fileV1.copy(str, str2, l, str3);
    }

    public static final FileV1 deserialize(String str) {
        return INSTANCE.deserialize(str);
    }

    public static /* synthetic */ void getIpfsCidV1$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSha256() {
        return this.sha256;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Long getSize() {
        return this.size;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getIpfsCidV1() {
        return this.ipfsCidV1;
    }

    public final FileV1 copy(String name, String sha256, Long size, String ipfsCidV1) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(sha256, "sha256");
        return new FileV1(name, sha256, size, ipfsCidV1);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FileV1)) {
            return false;
        }
        FileV1 fileV1 = (FileV1) other;
        return Intrinsics.areEqual(this.name, fileV1.name) && Intrinsics.areEqual(this.sha256, fileV1.sha256) && Intrinsics.areEqual(this.size, fileV1.size) && Intrinsics.areEqual(this.ipfsCidV1, fileV1.ipfsCidV1);
    }

    public int hashCode() {
        int iHashCode = ((this.name.hashCode() * 31) + this.sha256.hashCode()) * 31;
        Long l = this.size;
        int iHashCode2 = (iHashCode + (l == null ? 0 : l.hashCode())) * 31;
        String str = this.ipfsCidV1;
        return iHashCode2 + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "FileV1(name=" + this.name + ", sha256=" + this.sha256 + ", size=" + this.size + ", ipfsCidV1=" + this.ipfsCidV1 + ")";
    }

    public /* synthetic */ FileV1(int i, String str, String str2, Long l, String str3, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i, 3, FileV1$$serializer.INSTANCE.getDescriptor());
        }
        this.name = str;
        this.sha256 = str2;
        if ((i & 4) == 0) {
            this.size = null;
        } else {
            this.size = l;
        }
        if ((i & 8) == 0) {
            this.ipfsCidV1 = null;
        } else {
            this.ipfsCidV1 = str3;
        }
    }

    public static final /* synthetic */ void write$Self$index_release(FileV1 self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeStringElement(serialDesc, 0, self.getName());
        output.encodeStringElement(serialDesc, 1, self.getSha256());
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.getSize() != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, LongSerializer.INSTANCE, self.getSize());
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 3) && self.getIpfsCidV1() == null) {
            return;
        }
        output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.getIpfsCidV1());
    }

    public FileV1(String name, String sha256, Long l, String str) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(sha256, "sha256");
        this.name = name;
        this.sha256 = sha256;
        this.size = l;
        this.ipfsCidV1 = str;
    }

    public /* synthetic */ FileV1(String str, String str2, Long l, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? null : l, (i & 8) != 0 ? null : str3);
    }

    @Override // org.fdroid.IndexFile
    public String getName() {
        return this.name;
    }

    @Override // org.fdroid.IndexFile
    public String getSha256() {
        return this.sha256;
    }

    @Override // org.fdroid.IndexFile
    public Long getSize() {
        return this.size;
    }

    @Override // org.fdroid.IndexFile
    public String getIpfsCidV1() {
        return this.ipfsCidV1;
    }

    /* JADX INFO: compiled from: PackageV2.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007J\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t¨\u0006\n"}, d2 = {"Lorg/fdroid/index/v2/FileV1$Companion;", "", "<init>", "()V", "deserialize", "Lorg/fdroid/index/v2/FileV1;", "string", "", "serializer", "Lkotlinx/serialization/KSerializer;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer serializer() {
            return FileV1$$serializer.INSTANCE;
        }

        public final FileV1 deserialize(String string) {
            if (string == null) {
                return null;
            }
            Json json = IndexParser.getJson();
            KSerializer kSerializerSerializer = SerializersKt.serializer(json.getSerializersModule(), Reflection.nullableTypeOf(FileV1.class));
            Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
            return (FileV1) json.decodeFromString(kSerializerSerializer, string);
        }
    }

    @Override // org.fdroid.IndexFile
    public String serialize() {
        Json json = IndexParser.getJson();
        KSerializer kSerializerSerializer = SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(FileV1.class));
        Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        return json.encodeToString(kSerializerSerializer, this);
    }
}
