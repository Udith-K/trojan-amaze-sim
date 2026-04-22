package io.ktor.http;

import ch.qos.logback.core.CoreConstants;
import io.ktor.util.CharsetKt;
import io.ktor.util.TextKt;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: URLProtocol.kt */
/* JADX INFO: loaded from: classes.dex */
public final class URLProtocol {
    public static final Companion Companion = new Companion(null);
    private static final URLProtocol HTTP;
    private static final URLProtocol HTTPS;
    private static final URLProtocol SOCKS;
    private static final URLProtocol WS;
    private static final URLProtocol WSS;
    private static final Map byName;
    private final int defaultPort;
    private final String name;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof URLProtocol)) {
            return false;
        }
        URLProtocol uRLProtocol = (URLProtocol) obj;
        return Intrinsics.areEqual(this.name, uRLProtocol.name) && this.defaultPort == uRLProtocol.defaultPort;
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + this.defaultPort;
    }

    public String toString() {
        return "URLProtocol(name=" + this.name + ", defaultPort=" + this.defaultPort + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public URLProtocol(String name, int i) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        this.defaultPort = i;
        for (int i2 = 0; i2 < name.length(); i2++) {
            if (!CharsetKt.isLowerCase(name.charAt(i2))) {
                throw new IllegalArgumentException("All characters should be lower case");
            }
        }
    }

    public final int getDefaultPort() {
        return this.defaultPort;
    }

    public final String getName() {
        return this.name;
    }

    /* JADX INFO: compiled from: URLProtocol.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final URLProtocol getHTTP() {
            return URLProtocol.HTTP;
        }

        public final Map getByName() {
            return URLProtocol.byName;
        }

        public final URLProtocol createOrDefault(String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            String lowerCasePreservingASCIIRules = TextKt.toLowerCasePreservingASCIIRules(name);
            URLProtocol uRLProtocol = (URLProtocol) URLProtocol.Companion.getByName().get(lowerCasePreservingASCIIRules);
            return uRLProtocol == null ? new URLProtocol(lowerCasePreservingASCIIRules, 0) : uRLProtocol;
        }
    }

    static {
        URLProtocol uRLProtocol = new URLProtocol("http", 80);
        HTTP = uRLProtocol;
        URLProtocol uRLProtocol2 = new URLProtocol("https", 443);
        HTTPS = uRLProtocol2;
        URLProtocol uRLProtocol3 = new URLProtocol("ws", 80);
        WS = uRLProtocol3;
        URLProtocol uRLProtocol4 = new URLProtocol("wss", 443);
        WSS = uRLProtocol4;
        URLProtocol uRLProtocol5 = new URLProtocol("socks", 1080);
        SOCKS = uRLProtocol5;
        List listListOf = CollectionsKt.listOf((Object[]) new URLProtocol[]{uRLProtocol, uRLProtocol2, uRLProtocol3, uRLProtocol4, uRLProtocol5});
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(listListOf, 10)), 16));
        for (Object obj : listListOf) {
            linkedHashMap.put(((URLProtocol) obj).name, obj);
        }
        byName = linkedHashMap;
    }
}
