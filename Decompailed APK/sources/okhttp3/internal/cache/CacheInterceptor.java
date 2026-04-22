package okhttp3.internal.cache;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheStrategy;
import okhttp3.internal.connection.RealCall;

/* JADX INFO: compiled from: CacheInterceptor.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class CacheInterceptor implements Interceptor {
    public static final Companion Companion = new Companion(null);

    public CacheInterceptor(Cache cache) {
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        EventListener eventListener$okhttp;
        Intrinsics.checkNotNullParameter(chain, "chain");
        Call call = chain.call();
        CacheStrategy cacheStrategyCompute = new CacheStrategy.Factory(System.currentTimeMillis(), chain.request(), null).compute();
        Request networkRequest = cacheStrategyCompute.getNetworkRequest();
        Response cacheResponse = cacheStrategyCompute.getCacheResponse();
        RealCall realCall = call instanceof RealCall ? (RealCall) call : null;
        if (realCall == null || (eventListener$okhttp = realCall.getEventListener$okhttp()) == null) {
            eventListener$okhttp = EventListener.NONE;
        }
        if (networkRequest == null && cacheResponse == null) {
            Response responseBuild = new Response.Builder().request(chain.request()).protocol(Protocol.HTTP_1_1).code(504).message("Unsatisfiable Request (only-if-cached)").body(Util.EMPTY_RESPONSE).sentRequestAtMillis(-1L).receivedResponseAtMillis(System.currentTimeMillis()).build();
            eventListener$okhttp.satisfactionFailure(call, responseBuild);
            return responseBuild;
        }
        if (networkRequest == null) {
            Intrinsics.checkNotNull(cacheResponse);
            Response responseBuild2 = cacheResponse.newBuilder().cacheResponse(Companion.stripBody(cacheResponse)).build();
            eventListener$okhttp.cacheHit(call, responseBuild2);
            return responseBuild2;
        }
        if (cacheResponse != null) {
            eventListener$okhttp.cacheConditionalHit(call, cacheResponse);
        }
        Response responseProceed = chain.proceed(networkRequest);
        if (cacheResponse != null) {
            if (responseProceed != null && responseProceed.code() == 304) {
                Response.Builder builderNewBuilder = cacheResponse.newBuilder();
                Companion companion = Companion;
                builderNewBuilder.headers(companion.combine(cacheResponse.headers(), responseProceed.headers())).sentRequestAtMillis(responseProceed.sentRequestAtMillis()).receivedResponseAtMillis(responseProceed.receivedResponseAtMillis()).cacheResponse(companion.stripBody(cacheResponse)).networkResponse(companion.stripBody(responseProceed)).build();
                ResponseBody responseBodyBody = responseProceed.body();
                Intrinsics.checkNotNull(responseBodyBody);
                responseBodyBody.close();
                Intrinsics.checkNotNull(null);
                throw null;
            }
            ResponseBody responseBodyBody2 = cacheResponse.body();
            if (responseBodyBody2 != null) {
                Util.closeQuietly(responseBodyBody2);
            }
        }
        Intrinsics.checkNotNull(responseProceed);
        Response.Builder builderNewBuilder2 = responseProceed.newBuilder();
        Companion companion2 = Companion;
        return builderNewBuilder2.cacheResponse(companion2.stripBody(cacheResponse)).networkResponse(companion2.stripBody(responseProceed)).build();
    }

    /* JADX INFO: compiled from: CacheInterceptor.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Response stripBody(Response response) {
            return (response != null ? response.body() : null) != null ? response.newBuilder().body(null).build() : response;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Headers combine(Headers headers, Headers headers2) {
            Headers.Builder builder = new Headers.Builder();
            int size = headers.size();
            for (int i = 0; i < size; i++) {
                String strName = headers.name(i);
                String strValue = headers.value(i);
                if ((!StringsKt.equals("Warning", strName, true) || !StringsKt.startsWith$default(strValue, "1", false, 2, (Object) null)) && (isContentSpecificHeader(strName) || !isEndToEnd(strName) || headers2.get(strName) == null)) {
                    builder.addLenient$okhttp(strName, strValue);
                }
            }
            int size2 = headers2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                String strName2 = headers2.name(i2);
                if (!isContentSpecificHeader(strName2) && isEndToEnd(strName2)) {
                    builder.addLenient$okhttp(strName2, headers2.value(i2));
                }
            }
            return builder.build();
        }

        private final boolean isEndToEnd(String str) {
            return (StringsKt.equals("Connection", str, true) || StringsKt.equals("Keep-Alive", str, true) || StringsKt.equals("Proxy-Authenticate", str, true) || StringsKt.equals("Proxy-Authorization", str, true) || StringsKt.equals("TE", str, true) || StringsKt.equals("Trailers", str, true) || StringsKt.equals("Transfer-Encoding", str, true) || StringsKt.equals("Upgrade", str, true)) ? false : true;
        }

        private final boolean isContentSpecificHeader(String str) {
            return StringsKt.equals("Content-Length", str, true) || StringsKt.equals("Content-Encoding", str, true) || StringsKt.equals("Content-Type", str, true);
        }
    }
}
