package org.fdroid.database;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import androidx.compose.animation.ChangeSize$$ExternalSyntheticBackport0;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Repository.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u001f\b\u0086\b\u0018\u00002\u00020\u0001BS\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\nHÆ\u0003J\t\u0010&\u001a\u00020\fHÆ\u0003J\t\u0010'\u001a\u00020\u000eHÆ\u0003J_\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eHÆ\u0001J\u0013\u0010)\u001a\u00020\f2\b\u0010*\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010+\u001a\u00020\u000eHÖ\u0001J\t\u0010,\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001c\u0010\r\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f¨\u0006-"}, d2 = {"Lorg/fdroid/database/InitialRepository;", "", "name", "", "address", "mirrors", "", "description", "certificate", "version", "", "enabled", "", "weight", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;JZI)V", "getName", "()Ljava/lang/String;", "getAddress", "getMirrors", "()Ljava/util/List;", "getDescription", "getCertificate", "getVersion", "()J", "getEnabled", "()Z", "getWeight$annotations", "()V", "getWeight", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "toString", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class InitialRepository {
    private final String address;
    private final String certificate;
    private final String description;
    private final boolean enabled;
    private final List<String> mirrors;
    private final String name;
    private final long version;
    private final int weight;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public InitialRepository(String name, String address, String description, String certificate, long j, boolean z) {
        this(name, address, null, description, certificate, j, z, 0, 132, null);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(certificate, "certificate");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public InitialRepository(String name, String address, List<String> mirrors, String description, String certificate, long j, boolean z) {
        this(name, address, mirrors, description, certificate, j, z, 0, 128, null);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(mirrors, "mirrors");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(certificate, "certificate");
    }

    @Deprecated
    public static /* synthetic */ void getWeight$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getAddress() {
        return this.address;
    }

    public final List<String> component3() {
        return this.mirrors;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getCertificate() {
        return this.certificate;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getVersion() {
        return this.version;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getEnabled() {
        return this.enabled;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final int getWeight() {
        return this.weight;
    }

    public final InitialRepository copy(String name, String address, List<String> mirrors, String description, String certificate, long version, boolean enabled, int weight) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(mirrors, "mirrors");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(certificate, "certificate");
        return new InitialRepository(name, address, mirrors, description, certificate, version, enabled, weight);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InitialRepository)) {
            return false;
        }
        InitialRepository initialRepository = (InitialRepository) other;
        return Intrinsics.areEqual(this.name, initialRepository.name) && Intrinsics.areEqual(this.address, initialRepository.address) && Intrinsics.areEqual(this.mirrors, initialRepository.mirrors) && Intrinsics.areEqual(this.description, initialRepository.description) && Intrinsics.areEqual(this.certificate, initialRepository.certificate) && this.version == initialRepository.version && this.enabled == initialRepository.enabled && this.weight == initialRepository.weight;
    }

    public int hashCode() {
        return (((((((((((((this.name.hashCode() * 31) + this.address.hashCode()) * 31) + this.mirrors.hashCode()) * 31) + this.description.hashCode()) * 31) + this.certificate.hashCode()) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.version)) * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.enabled)) * 31) + this.weight;
    }

    public String toString() {
        return "InitialRepository(name=" + this.name + ", address=" + this.address + ", mirrors=" + this.mirrors + ", description=" + this.description + ", certificate=" + this.certificate + ", version=" + this.version + ", enabled=" + this.enabled + ", weight=" + this.weight + ")";
    }

    public InitialRepository(String name, String address, List<String> mirrors, String description, String certificate, long j, boolean z, int i) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(mirrors, "mirrors");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(certificate, "certificate");
        this.name = name;
        this.address = address;
        this.mirrors = mirrors;
        this.description = description;
        this.certificate = certificate;
        this.version = j;
        this.enabled = z;
        this.weight = i;
        RepositoryKt.validateCertificate(certificate);
    }

    public final String getName() {
        return this.name;
    }

    public final String getAddress() {
        return this.address;
    }

    public /* synthetic */ InitialRepository(String str, String str2, List list, String str3, String str4, long j, boolean z, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i2 & 4) != 0 ? CollectionsKt.emptyList() : list, str3, str4, j, z, (i2 & 128) != 0 ? 0 : i);
    }

    public final List<String> getMirrors() {
        return this.mirrors;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getCertificate() {
        return this.certificate;
    }

    public final long getVersion() {
        return this.version;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final int getWeight() {
        return this.weight;
    }
}
