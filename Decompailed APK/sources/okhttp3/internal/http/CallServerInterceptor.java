package okhttp3.internal.http;

import java.io.IOException;
import java.net.ProtocolException;
import kotlin.ExceptionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.http2.ConnectionShutdownException;
import okio.BufferedSink;
import okio.Okio;

/* JADX INFO: compiled from: CallServerInterceptor.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class CallServerInterceptor implements Interceptor {
    private final boolean forWebSocket;

    private final boolean shouldIgnoreAndWaitForRealResponse(int i) {
        if (i == 100) {
            return true;
        }
        return 102 <= i && i < 200;
    }

    public CallServerInterceptor(boolean z) {
        this.forWebSocket = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v13, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v23 */
    /* JADX WARN: Type inference failed for: r9v24 */
    /* JADX WARN: Type inference failed for: r9v25 */
    /* JADX WARN: Type inference failed for: r9v26 */
    /* JADX WARN: Type inference failed for: r9v27 */
    /* JADX WARN: Type inference failed for: r9v28 */
    /* JADX WARN: Type inference failed for: r9v29 */
    /* JADX WARN: Type inference failed for: r9v3, types: [java.lang.Object, okhttp3.Response$Builder] */
    /* JADX WARN: Type inference failed for: r9v30 */
    /* JADX WARN: Type inference failed for: r9v4, types: [okhttp3.Response$Builder] */
    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        boolean z;
        ?? r9;
        ?? responseHeaders;
        Response responseBuild;
        Response.Builder builder;
        Intrinsics.checkNotNullParameter(chain, "chain");
        RealInterceptorChain realInterceptorChain = (RealInterceptorChain) chain;
        Exchange exchange$okhttp = realInterceptorChain.getExchange$okhttp();
        Intrinsics.checkNotNull(exchange$okhttp);
        Request request$okhttp = realInterceptorChain.getRequest$okhttp();
        RequestBody requestBodyBody = request$okhttp.body();
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            exchange$okhttp.writeRequestHeaders(request$okhttp);
            ?? PermitsRequestBody = HttpMethod.permitsRequestBody(request$okhttp.method());
            try {
                if (PermitsRequestBody != 0 && requestBodyBody != null) {
                    if (StringsKt.equals("100-continue", request$okhttp.header("Expect"), true)) {
                        exchange$okhttp.flushRequest();
                        Response.Builder responseHeaders2 = exchange$okhttp.readResponseHeaders(true);
                        try {
                            exchange$okhttp.responseHeadersStart();
                            z = false;
                            builder = responseHeaders2;
                        } catch (IOException e) {
                            e = e;
                            z = true;
                            r9 = responseHeaders2;
                            if (e instanceof ConnectionShutdownException) {
                                throw e;
                            }
                            responseHeaders = r9;
                            if (!exchange$okhttp.getHasFailure$okhttp()) {
                                throw e;
                            }
                        }
                    } else {
                        z = true;
                        builder = null;
                    }
                    if (builder == null) {
                        if (requestBodyBody.isDuplex()) {
                            exchange$okhttp.flushRequest();
                            requestBodyBody.writeTo(Okio.buffer(exchange$okhttp.createRequestBody(request$okhttp, true)));
                            PermitsRequestBody = builder;
                        } else {
                            BufferedSink bufferedSinkBuffer = Okio.buffer(exchange$okhttp.createRequestBody(request$okhttp, false));
                            requestBodyBody.writeTo(bufferedSinkBuffer);
                            bufferedSinkBuffer.close();
                            PermitsRequestBody = builder;
                        }
                    } else {
                        exchange$okhttp.noRequestBody();
                        PermitsRequestBody = builder;
                        if (!exchange$okhttp.getConnection$okhttp().isMultiplexed$okhttp()) {
                            exchange$okhttp.noNewExchangesOnConnection();
                            PermitsRequestBody = builder;
                        }
                    }
                } else {
                    exchange$okhttp.noRequestBody();
                    z = true;
                    PermitsRequestBody = 0;
                }
                if (requestBodyBody == null || !requestBodyBody.isDuplex()) {
                    exchange$okhttp.finishRequest();
                }
                e = null;
                responseHeaders = PermitsRequestBody;
            } catch (IOException e2) {
                e = e2;
                r9 = PermitsRequestBody;
            }
        } catch (IOException e3) {
            e = e3;
            z = true;
            r9 = 0;
        }
        if (responseHeaders == 0) {
            try {
                responseHeaders = exchange$okhttp.readResponseHeaders(false);
                Intrinsics.checkNotNull(responseHeaders);
                if (z) {
                    exchange$okhttp.responseHeadersStart();
                    z = false;
                }
            } catch (IOException e4) {
                if (e != null) {
                    ExceptionsKt.addSuppressed(e, e4);
                    throw e;
                }
                throw e4;
            }
        }
        Response responseBuild2 = responseHeaders.request(request$okhttp).handshake(exchange$okhttp.getConnection$okhttp().handshake()).sentRequestAtMillis(jCurrentTimeMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
        int iCode = responseBuild2.code();
        if (shouldIgnoreAndWaitForRealResponse(iCode)) {
            Response.Builder responseHeaders3 = exchange$okhttp.readResponseHeaders(false);
            Intrinsics.checkNotNull(responseHeaders3);
            if (z) {
                exchange$okhttp.responseHeadersStart();
            }
            responseBuild2 = responseHeaders3.request(request$okhttp).handshake(exchange$okhttp.getConnection$okhttp().handshake()).sentRequestAtMillis(jCurrentTimeMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
            iCode = responseBuild2.code();
        }
        exchange$okhttp.responseHeadersEnd(responseBuild2);
        if (this.forWebSocket && iCode == 101) {
            responseBuild = responseBuild2.newBuilder().body(Util.EMPTY_RESPONSE).build();
        } else {
            responseBuild = responseBuild2.newBuilder().body(exchange$okhttp.openResponseBody(responseBuild2)).build();
        }
        if (StringsKt.equals("close", responseBuild.request().header("Connection"), true) || StringsKt.equals("close", Response.header$default(responseBuild, "Connection", null, 2, null), true)) {
            exchange$okhttp.noNewExchangesOnConnection();
        }
        if (iCode == 204 || iCode == 205) {
            ResponseBody responseBodyBody = responseBuild.body();
            if ((responseBodyBody != null ? responseBodyBody.contentLength() : -1L) > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("HTTP ");
                sb.append(iCode);
                sb.append(" had non-zero Content-Length: ");
                ResponseBody responseBodyBody2 = responseBuild.body();
                sb.append(responseBodyBody2 != null ? Long.valueOf(responseBodyBody2.contentLength()) : null);
                throw new ProtocolException(sb.toString());
            }
        }
        return responseBuild;
    }
}
