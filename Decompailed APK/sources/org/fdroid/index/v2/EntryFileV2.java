package org.fdroid.index.v2;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.Json;
import org.fdroid.IndexFile;
import org.fdroid.index.IndexParser;

/* JADX INFO: compiled from: IndexV2.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 /2\u00020\u0001:\u0002/0B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bBI\b\u0010\u0012\u0006\u0010\f\u001a\u00020\t\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\n\u0010\u000fJ\b\u0010\u001a\u001a\u00020\u0003H\u0016J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\tHÆ\u0003J=\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$HÖ\u0003J\t\u0010%\u001a\u00020\tHÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001J%\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00002\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0001¢\u0006\u0002\b.R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0016X\u0097\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u00061"}, d2 = {"Lorg/fdroid/index/v2/EntryFileV2;", "Lorg/fdroid/IndexFile;", "name", "", "sha256", "size", "", "ipfsCidV1", "numPackages", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;I)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;JLjava/lang/String;ILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getName", "()Ljava/lang/String;", "getSha256", "getSize", "()Ljava/lang/Long;", "getIpfsCidV1$annotations", "()V", "getIpfsCidV1", "getNumPackages", "()I", "serialize", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$index_release", "Companion", "$serializer", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class EntryFileV2 implements IndexFile {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String ipfsCidV1;
    private final String name;
    private final int numPackages;
    private final String sha256;
    private final long size;

    public static /* synthetic */ EntryFileV2 copy$default(EntryFileV2 entryFileV2, String str, String str2, long j, String str3, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = entryFileV2.name;
        }
        if ((i2 & 2) != 0) {
            str2 = entryFileV2.sha256;
        }
        String str4 = str2;
        if ((i2 & 4) != 0) {
            j = entryFileV2.size;
        }
        long j2 = j;
        if ((i2 & 8) != 0) {
            str3 = entryFileV2.ipfsCidV1;
        }
        String str5 = str3;
        if ((i2 & 16) != 0) {
            i = entryFileV2.numPackages;
        }
        return entryFileV2.copy(str, str4, j2, str5, i);
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
    public final long getSize() {
        return this.size;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getIpfsCidV1() {
        return this.ipfsCidV1;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getNumPackages() {
        return this.numPackages;
    }

    public final EntryFileV2 copy(String name, String sha256, long size, String ipfsCidV1, int numPackages) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(sha256, "sha256");
        return new EntryFileV2(name, sha256, size, ipfsCidV1, numPackages);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EntryFileV2)) {
            return false;
        }
        EntryFileV2 entryFileV2 = (EntryFileV2) other;
        return Intrinsics.areEqual(this.name, entryFileV2.name) && Intrinsics.areEqual(this.sha256, entryFileV2.sha256) && this.size == entryFileV2.size && Intrinsics.areEqual(this.ipfsCidV1, entryFileV2.ipfsCidV1) && this.numPackages == entryFileV2.numPackages;
    }

    public int hashCode() {
        int iHashCode = ((((this.name.hashCode() * 31) + this.sha256.hashCode()) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.size)) * 31;
        String str = this.ipfsCidV1;
        return ((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.numPackages;
    }

    public String toString() {
        return "EntryFileV2(name=" + this.name + ", sha256=" + this.sha256 + ", size=" + this.size + ", ipfsCidV1=" + this.ipfsCidV1 + ", numPackages=" + this.numPackages + ")";
    }

    public /* synthetic */ EntryFileV2(int i, String str, String str2, long j, String str3, int i2, SerializationConstructorMarker serializationConstructorMarker) {
        if (23 != (i & 23)) {
            PluginExceptionsKt.throwMissingFieldException(i, 23, EntryFileV2$$serializer.INSTANCE.getDescriptor());
        }
        this.name = str;
        this.sha256 = str2;
        this.size = j;
        if ((i & 8) == 0) {
            this.ipfsCidV1 = null;
        } else {
            this.ipfsCidV1 = str3;
        }
        this.numPackages = i2;
    }

    public static final /* synthetic */ void write$Self$index_release(EntryFileV2 self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeStringElement(serialDesc, 0, self.getName());
        output.encodeStringElement(serialDesc, 1, self.getSha256());
        output.encodeLongElement(serialDesc, 2, self.getSize().longValue());
        if (output.shouldEncodeElementDefault(serialDesc, 3) || self.getIpfsCidV1() != null) {
            output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.getIpfsCidV1());
        }
        output.encodeIntElement(serialDesc, 4, self.numPackages);
    }

    public EntryFileV2(String name, String sha256, long j, String str, int i) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(sha256, "sha256");
        this.name = name;
        this.sha256 = sha256;
        this.size = j;
        this.ipfsCidV1 = str;
        this.numPackages = i;
    }

    public /* synthetic */ EntryFileV2(String str, String str2, long j, String str3, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, j, (i2 & 8) != 0 ? null : str3, i);
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
        return Long.valueOf(this.size);
    }

    @Override // org.fdroid.IndexFile
    public String getIpfsCidV1() {
        return this.ipfsCidV1;
    }

    public final int getNumPackages() {
        return this.numPackages;
    }

    /* JADX INFO: compiled from: IndexV2.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t¨\u0006\n"}, d2 = {"Lorg/fdroid/index/v2/EntryFileV2$Companion;", "", "<init>", "()V", "deserialize", "Lorg/fdroid/index/v2/EntryFileV2;", "string", "", "serializer", "Lkotlinx/serialization/KSerializer;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer serializer() {
            return EntryFileV2$$serializer.INSTANCE;
        }

        public final EntryFileV2 deserialize(String string) {
            Intrinsics.checkNotNullParameter(string, "string");
            Json json = IndexParser.getJson();
            KSerializer kSerializerSerializer = SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(EntryFileV2.class));
            Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
            return (EntryFileV2) json.decodeFromString(kSerializerSerializer, string);
        }
    }

    @Override // org.fdroid.IndexFile
    public String serialize() {
        Json json = IndexParser.getJson();
        KSerializer kSerializerSerializer = SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(EntryFileV2.class));
        Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        return json.encodeToString(kSerializerSerializer, this);
    }
}
