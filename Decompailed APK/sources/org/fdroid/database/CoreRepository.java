package org.fdroid.database;

import androidx.collection.LongObjectMap$$ExternalSyntheticBackport0;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.index.IndexFormatVersion;
import org.fdroid.index.v2.FileV2;

/* JADX INFO: compiled from: Repository.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b%\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0081\b\u0018\u0000 <2\u00020\u0001:\u0001<B£\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005j\u0002`\u0007\u0012\u001a\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t\u0018\u00010\u0005j\u0004\u0018\u0001`\n\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\u0018\b\u0002\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005j\u0002`\u0007\u0012\u0006\u0010\u0014\u001a\u00020\u0006¢\u0006\u0004\b\u0015\u0010\u0016J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\u0019\u0010+\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005j\u0002`\u0007HÆ\u0003J\u001d\u0010,\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t\u0018\u00010\u0005j\u0004\u0018\u0001`\nHÆ\u0003J\t\u0010-\u001a\u00020\u0006HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\u0010\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010!J\u000b\u00101\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\u0010\u00102\u001a\u0004\u0018\u00010\u0012HÆ\u0003¢\u0006\u0002\u0010&J\u0019\u00103\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005j\u0002`\u0007HÆ\u0003J\t\u00104\u001a\u00020\u0006HÆ\u0003J¸\u0001\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005j\u0002`\u00072\u001c\b\u0002\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t\u0018\u00010\u0005j\u0004\u0018\u0001`\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00062\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\r\u001a\u00020\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0018\b\u0002\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005j\u0002`\u00072\b\b\u0002\u0010\u0014\u001a\u00020\u0006HÆ\u0001¢\u0006\u0002\u00106J\u0013\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010:\u001a\u00020\u0012HÖ\u0001J\t\u0010;\u001a\u00020\u0006HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R!\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005j\u0002`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR%\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t\u0018\u00010\u0005j\u0004\u0018\u0001`\n¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001dR\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b \u0010!R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0015\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\n\n\u0002\u0010'\u001a\u0004\b%\u0010&R!\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005j\u0002`\u0007¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001aR\u0011\u0010\u0014\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001d¨\u0006="}, d2 = {"Lorg/fdroid/database/CoreRepository;", "", "repoId", "", "name", "", "", "Lorg/fdroid/index/v2/LocalizedTextV2;", "icon", "Lorg/fdroid/index/v2/FileV2;", "Lorg/fdroid/index/v2/LocalizedFileV2;", "address", "webBaseUrl", "timestamp", "version", "formatVersion", "Lorg/fdroid/index/IndexFormatVersion;", "maxAge", "", "description", "certificate", "<init>", "(JLjava/util/Map;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;JLjava/lang/Long;Lorg/fdroid/index/IndexFormatVersion;Ljava/lang/Integer;Ljava/util/Map;Ljava/lang/String;)V", "getRepoId", "()J", "getName", "()Ljava/util/Map;", "getIcon", "getAddress", "()Ljava/lang/String;", "getWebBaseUrl", "getTimestamp", "getVersion", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getFormatVersion", "()Lorg/fdroid/index/IndexFormatVersion;", "getMaxAge", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getDescription", "getCertificate", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "(JLjava/util/Map;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;JLjava/lang/Long;Lorg/fdroid/index/IndexFormatVersion;Ljava/lang/Integer;Ljava/util/Map;Ljava/lang/String;)Lorg/fdroid/database/CoreRepository;", "equals", "", "other", "hashCode", "toString", "Companion", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class CoreRepository {
    public static final String TABLE = "CoreRepository";
    private final String address;
    private final String certificate;
    private final Map<String, String> description;
    private final IndexFormatVersion formatVersion;
    private final Map<String, FileV2> icon;
    private final Integer maxAge;
    private final Map<String, String> name;
    private final long repoId;
    private final long timestamp;
    private final Long version;
    private final String webBaseUrl;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getRepoId() {
        return this.repoId;
    }

    public final Map<String, String> component10() {
        return this.description;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getCertificate() {
        return this.certificate;
    }

    public final Map<String, String> component2() {
        return this.name;
    }

    public final Map<String, FileV2> component3() {
        return this.icon;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getAddress() {
        return this.address;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getWebBaseUrl() {
        return this.webBaseUrl;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getTimestamp() {
        return this.timestamp;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Long getVersion() {
        return this.version;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final IndexFormatVersion getFormatVersion() {
        return this.formatVersion;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final Integer getMaxAge() {
        return this.maxAge;
    }

    public final CoreRepository copy(long repoId, Map<String, String> name, Map<String, FileV2> icon, String address, String webBaseUrl, long timestamp, Long version, IndexFormatVersion formatVersion, Integer maxAge, Map<String, String> description, String certificate) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(certificate, "certificate");
        return new CoreRepository(repoId, name, icon, address, webBaseUrl, timestamp, version, formatVersion, maxAge, description, certificate);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CoreRepository)) {
            return false;
        }
        CoreRepository coreRepository = (CoreRepository) other;
        return this.repoId == coreRepository.repoId && Intrinsics.areEqual(this.name, coreRepository.name) && Intrinsics.areEqual(this.icon, coreRepository.icon) && Intrinsics.areEqual(this.address, coreRepository.address) && Intrinsics.areEqual(this.webBaseUrl, coreRepository.webBaseUrl) && this.timestamp == coreRepository.timestamp && Intrinsics.areEqual(this.version, coreRepository.version) && this.formatVersion == coreRepository.formatVersion && Intrinsics.areEqual(this.maxAge, coreRepository.maxAge) && Intrinsics.areEqual(this.description, coreRepository.description) && Intrinsics.areEqual(this.certificate, coreRepository.certificate);
    }

    public int hashCode() {
        int iM = ((LongObjectMap$$ExternalSyntheticBackport0.m(this.repoId) * 31) + this.name.hashCode()) * 31;
        Map<String, FileV2> map = this.icon;
        int iHashCode = (((iM + (map == null ? 0 : map.hashCode())) * 31) + this.address.hashCode()) * 31;
        String str = this.webBaseUrl;
        int iHashCode2 = (((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + LongObjectMap$$ExternalSyntheticBackport0.m(this.timestamp)) * 31;
        Long l = this.version;
        int iHashCode3 = (iHashCode2 + (l == null ? 0 : l.hashCode())) * 31;
        IndexFormatVersion indexFormatVersion = this.formatVersion;
        int iHashCode4 = (iHashCode3 + (indexFormatVersion == null ? 0 : indexFormatVersion.hashCode())) * 31;
        Integer num = this.maxAge;
        return ((((iHashCode4 + (num != null ? num.hashCode() : 0)) * 31) + this.description.hashCode()) * 31) + this.certificate.hashCode();
    }

    public String toString() {
        return "CoreRepository(repoId=" + this.repoId + ", name=" + this.name + ", icon=" + this.icon + ", address=" + this.address + ", webBaseUrl=" + this.webBaseUrl + ", timestamp=" + this.timestamp + ", version=" + this.version + ", formatVersion=" + this.formatVersion + ", maxAge=" + this.maxAge + ", description=" + this.description + ", certificate=" + this.certificate + ")";
    }

    public CoreRepository(long j, Map<String, String> name, Map<String, FileV2> map, String address, String str, long j2, Long l, IndexFormatVersion indexFormatVersion, Integer num, Map<String, String> description, String certificate) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(certificate, "certificate");
        this.repoId = j;
        this.name = name;
        this.icon = map;
        this.address = address;
        this.webBaseUrl = str;
        this.timestamp = j2;
        this.version = l;
        this.formatVersion = indexFormatVersion;
        this.maxAge = num;
        this.description = description;
        this.certificate = certificate;
    }

    public final long getRepoId() {
        return this.repoId;
    }

    public /* synthetic */ CoreRepository(long j, Map map, Map map2, String str, String str2, long j2, Long l, IndexFormatVersion indexFormatVersion, Integer num, Map map3, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0L : j, (i & 2) != 0 ? MapsKt.emptyMap() : map, map2, str, (i & 16) != 0 ? null : str2, j2, l, indexFormatVersion, num, (i & 512) != 0 ? MapsKt.emptyMap() : map3, str3);
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

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final Long getVersion() {
        return this.version;
    }

    public final IndexFormatVersion getFormatVersion() {
        return this.formatVersion;
    }

    public final Integer getMaxAge() {
        return this.maxAge;
    }

    public final Map<String, String> getDescription() {
        return this.description;
    }

    public final String getCertificate() {
        return this.certificate;
    }
}
