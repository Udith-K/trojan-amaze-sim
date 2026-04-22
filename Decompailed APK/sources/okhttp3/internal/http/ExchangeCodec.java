package okhttp3.internal.http;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.connection.RealConnection;
import okio.Sink;
import okio.Source;

/* JADX INFO: compiled from: ExchangeCodec.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface ExchangeCodec {
    void cancel();

    Sink createRequestBody(Request request, long j);

    void finishRequest();

    void flushRequest();

    RealConnection getConnection();

    Source openResponseBodySource(Response response);

    Response.Builder readResponseHeaders(boolean z);

    long reportedContentLength(Response response);

    void writeRequestHeaders(Request request);
}
