package io.ktor.http;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HttpProtocolVersion.kt */
/* JADX INFO: loaded from: classes.dex */
public final class HttpProtocolVersion {
    private final int major;
    private final int minor;
    private final String name;
    public static final Companion Companion = new Companion(null);
    private static final HttpProtocolVersion HTTP_2_0 = new HttpProtocolVersion("HTTP", 2, 0);
    private static final HttpProtocolVersion HTTP_1_1 = new HttpProtocolVersion("HTTP", 1, 1);
    private static final HttpProtocolVersion HTTP_1_0 = new HttpProtocolVersion("HTTP", 1, 0);
    private static final HttpProtocolVersion SPDY_3 = new HttpProtocolVersion("SPDY", 3, 0);
    private static final HttpProtocolVersion QUIC = new HttpProtocolVersion("QUIC", 1, 0);

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HttpProtocolVersion)) {
            return false;
        }
        HttpProtocolVersion httpProtocolVersion = (HttpProtocolVersion) obj;
        return Intrinsics.areEqual(this.name, httpProtocolVersion.name) && this.major == httpProtocolVersion.major && this.minor == httpProtocolVersion.minor;
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + this.major) * 31) + this.minor;
    }

    /* JADX INFO: compiled from: HttpProtocolVersion.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final HttpProtocolVersion getHTTP_2_0() {
            return HttpProtocolVersion.HTTP_2_0;
        }

        public final HttpProtocolVersion getHTTP_1_1() {
            return HttpProtocolVersion.HTTP_1_1;
        }

        public final HttpProtocolVersion getHTTP_1_0() {
            return HttpProtocolVersion.HTTP_1_0;
        }

        public final HttpProtocolVersion getSPDY_3() {
            return HttpProtocolVersion.SPDY_3;
        }

        public final HttpProtocolVersion getQUIC() {
            return HttpProtocolVersion.QUIC;
        }
    }

    public HttpProtocolVersion(String name, int i, int i2) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        this.major = i;
        this.minor = i2;
    }

    public String toString() {
        return this.name + '/' + this.major + CoreConstants.DOT + this.minor;
    }
}
