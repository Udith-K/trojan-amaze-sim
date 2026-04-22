package org.fdroid.download;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticBackport0;
import io.ktor.http.URLBuilderKt;
import io.ktor.http.URLParserException;
import io.ktor.http.URLUtilsKt;
import io.ktor.http.Url;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import mu.KotlinLogging;
import org.fdroid.fdroid.nearby.peers.BonjourPeer;

/* JADX INFO: compiled from: Mirror.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \"2\u00020\u0001:\u0001\"B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0003J\u0010\u0010\u0014\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0003J\u0006\u0010\u0016\u001a\u00020\u0006J\u0006\u0010\u0017\u001a\u00020\u0006J\u0006\u0010\u0018\u001a\u00020\u0006J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0006HÆ\u0003J)\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\fR\u001b\u0010\r\u001a\u00020\u000e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010¨\u0006#"}, d2 = {"Lorg/fdroid/download/Mirror;", "", "baseUrl", "", "countryCode", "isIpfsGateway", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getBaseUrl", "()Ljava/lang/String;", "getCountryCode", "()Z", "url", "Lio/ktor/http/Url;", "getUrl", "()Lio/ktor/http/Url;", "url$delegate", "Lkotlin/Lazy;", BonjourPeer.PATH, "getFDroidLinkUrl", "repoFingerprint", "isOnion", "isLocal", "isHttp", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "Companion", "download_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class Mirror {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String baseUrl;
    private final String countryCode;
    private final boolean isIpfsGateway;

    /* JADX INFO: renamed from: url$delegate, reason: from kotlin metadata */
    private final Lazy url;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Mirror(String baseUrl) {
        this(baseUrl, null, false, 6, null);
        Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Mirror(String baseUrl, String str) {
        this(baseUrl, str, false, 4, null);
        Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
    }

    public static /* synthetic */ Mirror copy$default(Mirror mirror, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = mirror.baseUrl;
        }
        if ((i & 2) != 0) {
            str2 = mirror.countryCode;
        }
        if ((i & 4) != 0) {
            z = mirror.isIpfsGateway;
        }
        return mirror.copy(str, str2, z);
    }

    public static final List<Mirror> fromStrings(List<String> list) {
        return INSTANCE.fromStrings(list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getBaseUrl() {
        return this.baseUrl;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getCountryCode() {
        return this.countryCode;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsIpfsGateway() {
        return this.isIpfsGateway;
    }

    public final Mirror copy(String baseUrl, String countryCode, boolean isIpfsGateway) {
        Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
        return new Mirror(baseUrl, countryCode, isIpfsGateway);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Mirror)) {
            return false;
        }
        Mirror mirror = (Mirror) other;
        return Intrinsics.areEqual(this.baseUrl, mirror.baseUrl) && Intrinsics.areEqual(this.countryCode, mirror.countryCode) && this.isIpfsGateway == mirror.isIpfsGateway;
    }

    public int hashCode() {
        int iHashCode = this.baseUrl.hashCode() * 31;
        String str = this.countryCode;
        return ((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.isIpfsGateway);
    }

    public String toString() {
        return "Mirror(baseUrl=" + this.baseUrl + ", countryCode=" + this.countryCode + ", isIpfsGateway=" + this.isIpfsGateway + ")";
    }

    public Mirror(String baseUrl, String str, boolean z) {
        Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
        this.baseUrl = baseUrl;
        this.countryCode = str;
        this.isIpfsGateway = z;
        this.url = LazyKt.lazy(new Function0() { // from class: org.fdroid.download.Mirror$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Mirror.url_delegate$lambda$4(this.f$0);
            }
        });
    }

    public /* synthetic */ Mirror(String str, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? false : z);
    }

    public final String getBaseUrl() {
        return this.baseUrl;
    }

    public final String getCountryCode() {
        return this.countryCode;
    }

    public final boolean isIpfsGateway() {
        return this.isIpfsGateway;
    }

    public final Url getUrl() {
        return (Url) this.url.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Url url_delegate$lambda$4(final Mirror mirror) {
        try {
            return URLUtilsKt.URLBuilder(StringsKt.trimEnd(mirror.baseUrl, '/')).build();
        } catch (URLParserException unused) {
            KotlinLogging.INSTANCE.logger(new Function0() { // from class: org.fdroid.download.Mirror$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            }).warn(new Function0() { // from class: org.fdroid.download.Mirror$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Mirror.url_delegate$lambda$4$lambda$1(this.f$0);
                }
            });
            return URLUtilsKt.URLBuilder("http://127.0.0.1:64335").build();
        } catch (IllegalArgumentException unused2) {
            KotlinLogging.INSTANCE.logger(new Function0() { // from class: org.fdroid.download.Mirror$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Unit.INSTANCE;
                }
            }).warn(new Function0() { // from class: org.fdroid.download.Mirror$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Mirror.url_delegate$lambda$4$lambda$3(this.f$0);
                }
            });
            return URLUtilsKt.URLBuilder("http://127.0.0.1:64335").build();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object url_delegate$lambda$4$lambda$1(Mirror mirror) {
        return "Someone gave us an invalid URL: " + mirror.baseUrl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object url_delegate$lambda$4$lambda$3(Mirror mirror) {
        return "Someone gave us an invalid URL: " + mirror.baseUrl;
    }

    public final Url getUrl(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        return URLBuilderKt.appendPathSegments$default(URLUtilsKt.URLBuilder(getUrl()), new String[]{StringsKt.trimStart(path, '/')}, false, 2, null).build();
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String getFDroidLinkUrl(java.lang.String r4) {
        /*
            r3 = this;
            if (r4 == 0) goto L15
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "?fingerprint="
            r0.append(r1)
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            if (r4 != 0) goto L17
        L15:
            java.lang.String r4 = ""
        L17:
            io.ktor.http.Url r0 = r3.getUrl()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "https://fdroid.link/#"
            r1.append(r2)
            r1.append(r0)
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.download.Mirror.getFDroidLinkUrl(java.lang.String):java.lang.String");
    }

    public final boolean isOnion() {
        return MirrorKt.isOnion(getUrl());
    }

    public final boolean isLocal() {
        return MirrorKt.isLocal(getUrl());
    }

    public final boolean isHttp() {
        return StringsKt.startsWith$default(getUrl().getProtocol().getName(), "http", false, 2, (Object) null);
    }

    /* JADX INFO: compiled from: Mirror.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005H\u0007¨\u0006\t"}, d2 = {"Lorg/fdroid/download/Mirror$Companion;", "", "<init>", "()V", "fromStrings", "", "Lorg/fdroid/download/Mirror;", "list", "", "download_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<Mirror> fromStrings(List<String> list) {
            Intrinsics.checkNotNullParameter(list, "list");
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(new Mirror((String) it.next(), null, false, 6, null));
            }
            return arrayList;
        }
    }
}
