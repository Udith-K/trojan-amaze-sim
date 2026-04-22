package org.fdroid.index.v1;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.bouncycastle.i18n.ErrorBundle;

/* JADX INFO: compiled from: AppV1.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 F2\u00020\u0001:\u0002EFBÍ\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0012\u0010\u0013BÅ\u0001\b\u0010\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\u0004\b\u0012\u0010\u0018J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010.\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tHÆ\u0003J\u0011\u0010/\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tHÆ\u0003J\u0011\u00100\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tHÆ\u0003J\u0011\u00101\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tHÆ\u0003J\u0011\u00102\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tHÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0003HÆ\u0003JÏ\u0001\u00107\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t2\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t2\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u00108\u001a\u0002092\b\u0010:\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010;\u001a\u00020\u0015HÖ\u0001J\t\u0010<\u001a\u00020\u0003HÖ\u0001J%\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020\u00002\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020CH\u0001¢\u0006\u0002\bDR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001aR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0019\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b!\u0010 R\u0019\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010 R\u0019\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b#\u0010 R\u0019\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b$\u0010 R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001aR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001aR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001aR\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001a¨\u0006G"}, d2 = {"Lorg/fdroid/index/v1/Localized;", "", "description", "", "name", "icon", "whatsNew", "video", "phoneScreenshots", "", "sevenInchScreenshots", "tenInchScreenshots", "wearScreenshots", "tvScreenshots", "featureGraphic", "promoGraphic", "tvBanner", ErrorBundle.SUMMARY_ENTRY, "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getDescription", "()Ljava/lang/String;", "getName", "getIcon", "getWhatsNew", "getVideo", "getPhoneScreenshots", "()Ljava/util/List;", "getSevenInchScreenshots", "getTenInchScreenshots", "getWearScreenshots", "getTvScreenshots", "getFeatureGraphic", "getPromoGraphic", "getTvBanner", "getSummary", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$index_release", "$serializer", "Companion", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@Serializable
public final /* data */ class Localized {
    private static final KSerializer[] $childSerializers;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String description;
    private final String featureGraphic;
    private final String icon;
    private final String name;
    private final List<String> phoneScreenshots;
    private final String promoGraphic;
    private final List<String> sevenInchScreenshots;
    private final String summary;
    private final List<String> tenInchScreenshots;
    private final String tvBanner;
    private final List<String> tvScreenshots;
    private final String video;
    private final List<String> wearScreenshots;
    private final String whatsNew;

    public Localized() {
        this((String) null, (String) null, (String) null, (String) null, (String) null, (List) null, (List) null, (List) null, (List) null, (List) null, (String) null, (String) null, (String) null, (String) null, 16383, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    public final List<String> component10() {
        return this.tvScreenshots;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getFeatureGraphic() {
        return this.featureGraphic;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getPromoGraphic() {
        return this.promoGraphic;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getTvBanner() {
        return this.tvBanner;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getSummary() {
        return this.summary;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getIcon() {
        return this.icon;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getWhatsNew() {
        return this.whatsNew;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getVideo() {
        return this.video;
    }

    public final List<String> component6() {
        return this.phoneScreenshots;
    }

    public final List<String> component7() {
        return this.sevenInchScreenshots;
    }

    public final List<String> component8() {
        return this.tenInchScreenshots;
    }

    public final List<String> component9() {
        return this.wearScreenshots;
    }

    public final Localized copy(String description, String name, String icon, String whatsNew, String video, List<String> phoneScreenshots, List<String> sevenInchScreenshots, List<String> tenInchScreenshots, List<String> wearScreenshots, List<String> tvScreenshots, String featureGraphic, String promoGraphic, String tvBanner, String summary) {
        return new Localized(description, name, icon, whatsNew, video, phoneScreenshots, sevenInchScreenshots, tenInchScreenshots, wearScreenshots, tvScreenshots, featureGraphic, promoGraphic, tvBanner, summary);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Localized)) {
            return false;
        }
        Localized localized = (Localized) other;
        return Intrinsics.areEqual(this.description, localized.description) && Intrinsics.areEqual(this.name, localized.name) && Intrinsics.areEqual(this.icon, localized.icon) && Intrinsics.areEqual(this.whatsNew, localized.whatsNew) && Intrinsics.areEqual(this.video, localized.video) && Intrinsics.areEqual(this.phoneScreenshots, localized.phoneScreenshots) && Intrinsics.areEqual(this.sevenInchScreenshots, localized.sevenInchScreenshots) && Intrinsics.areEqual(this.tenInchScreenshots, localized.tenInchScreenshots) && Intrinsics.areEqual(this.wearScreenshots, localized.wearScreenshots) && Intrinsics.areEqual(this.tvScreenshots, localized.tvScreenshots) && Intrinsics.areEqual(this.featureGraphic, localized.featureGraphic) && Intrinsics.areEqual(this.promoGraphic, localized.promoGraphic) && Intrinsics.areEqual(this.tvBanner, localized.tvBanner) && Intrinsics.areEqual(this.summary, localized.summary);
    }

    public int hashCode() {
        String str = this.description;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.name;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.icon;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.whatsNew;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.video;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        List<String> list = this.phoneScreenshots;
        int iHashCode6 = (iHashCode5 + (list == null ? 0 : list.hashCode())) * 31;
        List<String> list2 = this.sevenInchScreenshots;
        int iHashCode7 = (iHashCode6 + (list2 == null ? 0 : list2.hashCode())) * 31;
        List<String> list3 = this.tenInchScreenshots;
        int iHashCode8 = (iHashCode7 + (list3 == null ? 0 : list3.hashCode())) * 31;
        List<String> list4 = this.wearScreenshots;
        int iHashCode9 = (iHashCode8 + (list4 == null ? 0 : list4.hashCode())) * 31;
        List<String> list5 = this.tvScreenshots;
        int iHashCode10 = (iHashCode9 + (list5 == null ? 0 : list5.hashCode())) * 31;
        String str6 = this.featureGraphic;
        int iHashCode11 = (iHashCode10 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.promoGraphic;
        int iHashCode12 = (iHashCode11 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.tvBanner;
        int iHashCode13 = (iHashCode12 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.summary;
        return iHashCode13 + (str9 != null ? str9.hashCode() : 0);
    }

    public String toString() {
        return "Localized(description=" + this.description + ", name=" + this.name + ", icon=" + this.icon + ", whatsNew=" + this.whatsNew + ", video=" + this.video + ", phoneScreenshots=" + this.phoneScreenshots + ", sevenInchScreenshots=" + this.sevenInchScreenshots + ", tenInchScreenshots=" + this.tenInchScreenshots + ", wearScreenshots=" + this.wearScreenshots + ", tvScreenshots=" + this.tvScreenshots + ", featureGraphic=" + this.featureGraphic + ", promoGraphic=" + this.promoGraphic + ", tvBanner=" + this.tvBanner + ", summary=" + this.summary + ")";
    }

    /* JADX INFO: compiled from: AppV1.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lorg/fdroid/index/v1/Localized$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lorg/fdroid/index/v1/Localized;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer serializer() {
            return Localized$$serializer.INSTANCE;
        }
    }

    static {
        StringSerializer stringSerializer = StringSerializer.INSTANCE;
        $childSerializers = new KSerializer[]{null, null, null, null, null, new ArrayListSerializer(stringSerializer), new ArrayListSerializer(stringSerializer), new ArrayListSerializer(stringSerializer), new ArrayListSerializer(stringSerializer), new ArrayListSerializer(stringSerializer), null, null, null, null};
    }

    public /* synthetic */ Localized(int i, String str, String str2, String str3, String str4, String str5, List list, List list2, List list3, List list4, List list5, String str6, String str7, String str8, String str9, SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 1) == 0) {
            this.description = null;
        } else {
            this.description = str;
        }
        if ((i & 2) == 0) {
            this.name = null;
        } else {
            this.name = str2;
        }
        if ((i & 4) == 0) {
            this.icon = null;
        } else {
            this.icon = str3;
        }
        if ((i & 8) == 0) {
            this.whatsNew = null;
        } else {
            this.whatsNew = str4;
        }
        if ((i & 16) == 0) {
            this.video = null;
        } else {
            this.video = str5;
        }
        if ((i & 32) == 0) {
            this.phoneScreenshots = null;
        } else {
            this.phoneScreenshots = list;
        }
        if ((i & 64) == 0) {
            this.sevenInchScreenshots = null;
        } else {
            this.sevenInchScreenshots = list2;
        }
        if ((i & 128) == 0) {
            this.tenInchScreenshots = null;
        } else {
            this.tenInchScreenshots = list3;
        }
        if ((i & 256) == 0) {
            this.wearScreenshots = null;
        } else {
            this.wearScreenshots = list4;
        }
        if ((i & 512) == 0) {
            this.tvScreenshots = null;
        } else {
            this.tvScreenshots = list5;
        }
        if ((i & 1024) == 0) {
            this.featureGraphic = null;
        } else {
            this.featureGraphic = str6;
        }
        if ((i & 2048) == 0) {
            this.promoGraphic = null;
        } else {
            this.promoGraphic = str7;
        }
        if ((i & PKIFailureInfo.certConfirmed) == 0) {
            this.tvBanner = null;
        } else {
            this.tvBanner = str8;
        }
        if ((i & 8192) == 0) {
            this.summary = null;
        } else {
            this.summary = str9;
        }
    }

    public static final /* synthetic */ void write$Self$index_release(Localized self, CompositeEncoder output, SerialDescriptor serialDesc) {
        KSerializer[] kSerializerArr = $childSerializers;
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.description != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.description);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.name != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.name);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.icon != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.icon);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 3) || self.whatsNew != null) {
            output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.whatsNew);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 4) || self.video != null) {
            output.encodeNullableSerializableElement(serialDesc, 4, StringSerializer.INSTANCE, self.video);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 5) || self.phoneScreenshots != null) {
            output.encodeNullableSerializableElement(serialDesc, 5, kSerializerArr[5], self.phoneScreenshots);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 6) || self.sevenInchScreenshots != null) {
            output.encodeNullableSerializableElement(serialDesc, 6, kSerializerArr[6], self.sevenInchScreenshots);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 7) || self.tenInchScreenshots != null) {
            output.encodeNullableSerializableElement(serialDesc, 7, kSerializerArr[7], self.tenInchScreenshots);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 8) || self.wearScreenshots != null) {
            output.encodeNullableSerializableElement(serialDesc, 8, kSerializerArr[8], self.wearScreenshots);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 9) || self.tvScreenshots != null) {
            output.encodeNullableSerializableElement(serialDesc, 9, kSerializerArr[9], self.tvScreenshots);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 10) || self.featureGraphic != null) {
            output.encodeNullableSerializableElement(serialDesc, 10, StringSerializer.INSTANCE, self.featureGraphic);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 11) || self.promoGraphic != null) {
            output.encodeNullableSerializableElement(serialDesc, 11, StringSerializer.INSTANCE, self.promoGraphic);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 12) || self.tvBanner != null) {
            output.encodeNullableSerializableElement(serialDesc, 12, StringSerializer.INSTANCE, self.tvBanner);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 13) && self.summary == null) {
            return;
        }
        output.encodeNullableSerializableElement(serialDesc, 13, StringSerializer.INSTANCE, self.summary);
    }

    public Localized(String str, String str2, String str3, String str4, String str5, List<String> list, List<String> list2, List<String> list3, List<String> list4, List<String> list5, String str6, String str7, String str8, String str9) {
        this.description = str;
        this.name = str2;
        this.icon = str3;
        this.whatsNew = str4;
        this.video = str5;
        this.phoneScreenshots = list;
        this.sevenInchScreenshots = list2;
        this.tenInchScreenshots = list3;
        this.wearScreenshots = list4;
        this.tvScreenshots = list5;
        this.featureGraphic = str6;
        this.promoGraphic = str7;
        this.tvBanner = str8;
        this.summary = str9;
    }

    public /* synthetic */ Localized(String str, String str2, String str3, String str4, String str5, List list, List list2, List list3, List list4, List list5, String str6, String str7, String str8, String str9, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : list, (i & 64) != 0 ? null : list2, (i & 128) != 0 ? null : list3, (i & 256) != 0 ? null : list4, (i & 512) != 0 ? null : list5, (i & 1024) != 0 ? null : str6, (i & 2048) != 0 ? null : str7, (i & PKIFailureInfo.certConfirmed) != 0 ? null : str8, (i & 8192) == 0 ? str9 : null);
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getName() {
        return this.name;
    }

    public final String getIcon() {
        return this.icon;
    }

    public final String getWhatsNew() {
        return this.whatsNew;
    }

    public final String getVideo() {
        return this.video;
    }

    public final List<String> getPhoneScreenshots() {
        return this.phoneScreenshots;
    }

    public final List<String> getSevenInchScreenshots() {
        return this.sevenInchScreenshots;
    }

    public final List<String> getTenInchScreenshots() {
        return this.tenInchScreenshots;
    }

    public final List<String> getWearScreenshots() {
        return this.wearScreenshots;
    }

    public final List<String> getTvScreenshots() {
        return this.tvScreenshots;
    }

    public final String getFeatureGraphic() {
        return this.featureGraphic;
    }

    public final String getPromoGraphic() {
        return this.promoGraphic;
    }

    public final String getTvBanner() {
        return this.tvBanner;
    }

    public final String getSummary() {
        return this.summary;
    }
}
