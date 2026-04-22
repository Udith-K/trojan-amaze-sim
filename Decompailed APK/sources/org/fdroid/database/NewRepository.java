package org.fdroid.database;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.index.IndexFormatVersion;
import org.fdroid.index.v2.FileV2;

/* JADX INFO: compiled from: Repository.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bi\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u0005\u0012\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0003j\u0002`\b\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\u0004\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u000f\u0010\u0010J\u0019\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u0005HÆ\u0003J\u0019\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0003j\u0002`\bHÆ\u0003J\t\u0010\u001d\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\t\u0010\u001f\u001a\u00020\u0004HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0004HÆ\u0003Ju\u0010\"\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u00052\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0003j\u0002`\b2\b\b\u0002\u0010\t\u001a\u00020\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020'HÖ\u0001J\t\u0010(\u001a\u00020\u0004HÖ\u0001R!\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R!\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0003j\u0002`\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015¨\u0006)"}, d2 = {"Lorg/fdroid/database/NewRepository;", "", "name", "", "", "Lorg/fdroid/index/v2/LocalizedTextV2;", "icon", "Lorg/fdroid/index/v2/FileV2;", "Lorg/fdroid/index/v2/LocalizedFileV2;", "address", "formatVersion", "Lorg/fdroid/index/IndexFormatVersion;", "certificate", "username", "password", "<init>", "(Ljava/util/Map;Ljava/util/Map;Ljava/lang/String;Lorg/fdroid/index/IndexFormatVersion;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/util/Map;", "getIcon", "getAddress", "()Ljava/lang/String;", "getFormatVersion", "()Lorg/fdroid/index/IndexFormatVersion;", "getCertificate", "getUsername", "getPassword", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class NewRepository {
    private final String address;
    private final String certificate;
    private final IndexFormatVersion formatVersion;
    private final Map<String, FileV2> icon;
    private final Map<String, String> name;
    private final String password;
    private final String username;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ NewRepository copy$default(NewRepository newRepository, Map map, Map map2, String str, IndexFormatVersion indexFormatVersion, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            map = newRepository.name;
        }
        if ((i & 2) != 0) {
            map2 = newRepository.icon;
        }
        Map map3 = map2;
        if ((i & 4) != 0) {
            str = newRepository.address;
        }
        String str5 = str;
        if ((i & 8) != 0) {
            indexFormatVersion = newRepository.formatVersion;
        }
        IndexFormatVersion indexFormatVersion2 = indexFormatVersion;
        if ((i & 16) != 0) {
            str2 = newRepository.certificate;
        }
        String str6 = str2;
        if ((i & 32) != 0) {
            str3 = newRepository.username;
        }
        String str7 = str3;
        if ((i & 64) != 0) {
            str4 = newRepository.password;
        }
        return newRepository.copy(map, map3, str5, indexFormatVersion2, str6, str7, str4);
    }

    public final Map<String, String> component1() {
        return this.name;
    }

    public final Map<String, FileV2> component2() {
        return this.icon;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getAddress() {
        return this.address;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final IndexFormatVersion getFormatVersion() {
        return this.formatVersion;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getCertificate() {
        return this.certificate;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getUsername() {
        return this.username;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getPassword() {
        return this.password;
    }

    public final NewRepository copy(Map<String, String> name, Map<String, FileV2> icon, String address, IndexFormatVersion formatVersion, String certificate, String username, String password) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(certificate, "certificate");
        return new NewRepository(name, icon, address, formatVersion, certificate, username, password);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NewRepository)) {
            return false;
        }
        NewRepository newRepository = (NewRepository) other;
        return Intrinsics.areEqual(this.name, newRepository.name) && Intrinsics.areEqual(this.icon, newRepository.icon) && Intrinsics.areEqual(this.address, newRepository.address) && this.formatVersion == newRepository.formatVersion && Intrinsics.areEqual(this.certificate, newRepository.certificate) && Intrinsics.areEqual(this.username, newRepository.username) && Intrinsics.areEqual(this.password, newRepository.password);
    }

    public int hashCode() {
        int iHashCode = ((((this.name.hashCode() * 31) + this.icon.hashCode()) * 31) + this.address.hashCode()) * 31;
        IndexFormatVersion indexFormatVersion = this.formatVersion;
        int iHashCode2 = (((iHashCode + (indexFormatVersion == null ? 0 : indexFormatVersion.hashCode())) * 31) + this.certificate.hashCode()) * 31;
        String str = this.username;
        int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.password;
        return iHashCode3 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "NewRepository(name=" + this.name + ", icon=" + this.icon + ", address=" + this.address + ", formatVersion=" + this.formatVersion + ", certificate=" + this.certificate + ", username=" + this.username + ", password=" + this.password + ")";
    }

    public NewRepository(Map<String, String> name, Map<String, FileV2> icon, String address, IndexFormatVersion indexFormatVersion, String certificate, String str, String str2) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(certificate, "certificate");
        this.name = name;
        this.icon = icon;
        this.address = address;
        this.formatVersion = indexFormatVersion;
        this.certificate = certificate;
        this.username = str;
        this.password = str2;
    }

    public /* synthetic */ NewRepository(Map map, Map map2, String str, IndexFormatVersion indexFormatVersion, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(map, map2, str, indexFormatVersion, str2, (i & 32) != 0 ? null : str3, (i & 64) != 0 ? null : str4);
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

    public final IndexFormatVersion getFormatVersion() {
        return this.formatVersion;
    }

    public final String getCertificate() {
        return this.certificate;
    }

    public final String getUsername() {
        return this.username;
    }

    public final String getPassword() {
        return this.password;
    }
}
