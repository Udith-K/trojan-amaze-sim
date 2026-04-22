package io.ktor.http;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Url.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class UrlKt {
    public static final String getAuthority(Url url) {
        Intrinsics.checkNotNullParameter(url, "<this>");
        StringBuilder sb = new StringBuilder();
        sb.append(getEncodedUserAndPassword(url));
        if (url.getSpecifiedPort() == 0 || url.getSpecifiedPort() == url.getProtocol().getDefaultPort()) {
            sb.append(url.getHost());
        } else {
            sb.append(URLUtilsKt.getHostWithPort(url));
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public static final String getEncodedUserAndPassword(Url url) {
        Intrinsics.checkNotNullParameter(url, "<this>");
        StringBuilder sb = new StringBuilder();
        URLUtilsKt.appendUserAndPassword(sb, url.getEncodedUser(), url.getEncodedPassword());
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
