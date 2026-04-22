package okhttp3.internal.http;

import kotlin.jvm.internal.Intrinsics;
import org.fdroid.fdroid.nearby.httpish.Request;

/* JADX INFO: compiled from: HttpMethod.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class HttpMethod {
    public static final HttpMethod INSTANCE = new HttpMethod();

    private HttpMethod() {
    }

    public static final boolean requiresRequestBody(String method) {
        Intrinsics.checkNotNullParameter(method, "method");
        return Intrinsics.areEqual(method, "POST") || Intrinsics.areEqual(method, "PUT") || Intrinsics.areEqual(method, "PATCH") || Intrinsics.areEqual(method, "PROPPATCH") || Intrinsics.areEqual(method, "REPORT");
    }

    public static final boolean permitsRequestBody(String method) {
        Intrinsics.checkNotNullParameter(method, "method");
        return (Intrinsics.areEqual(method, Request.Methods.GET) || Intrinsics.areEqual(method, Request.Methods.HEAD)) ? false : true;
    }

    public final boolean redirectsWithBody(String method) {
        Intrinsics.checkNotNullParameter(method, "method");
        return Intrinsics.areEqual(method, "PROPFIND");
    }

    public final boolean redirectsToGet(String method) {
        Intrinsics.checkNotNullParameter(method, "method");
        return !Intrinsics.areEqual(method, "PROPFIND");
    }
}
