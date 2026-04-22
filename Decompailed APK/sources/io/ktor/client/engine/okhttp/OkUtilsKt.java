package io.ktor.client.engine.okhttp;

import io.ktor.client.plugins.HttpTimeoutKt;
import io.ktor.client.request.HttpRequestData;
import io.ktor.http.Headers;
import io.ktor.http.HttpProtocolVersion;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;

/* JADX INFO: compiled from: OkUtils.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class OkUtilsKt {

    /* JADX INFO: compiled from: OkUtils.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Protocol.values().length];
            try {
                iArr[Protocol.HTTP_1_0.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Protocol.HTTP_1_1.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Protocol.SPDY_3.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Protocol.HTTP_2.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[Protocol.H2_PRIOR_KNOWLEDGE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[Protocol.QUIC.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final Headers fromOkHttp(final okhttp3.Headers headers) {
        Intrinsics.checkNotNullParameter(headers, "<this>");
        return new Headers() { // from class: io.ktor.client.engine.okhttp.OkUtilsKt.fromOkHttp.1
            private final boolean caseInsensitiveName = true;

            @Override // io.ktor.util.StringValues
            public void forEach(Function2 function2) {
                Headers.DefaultImpls.forEach(this, function2);
            }

            @Override // io.ktor.util.StringValues
            public String get(String str) {
                return Headers.DefaultImpls.get(this, str);
            }

            @Override // io.ktor.util.StringValues
            public boolean getCaseInsensitiveName() {
                return this.caseInsensitiveName;
            }

            @Override // io.ktor.util.StringValues
            public List getAll(String name) {
                Intrinsics.checkNotNullParameter(name, "name");
                List listValues = headers.values(name);
                if (listValues.isEmpty()) {
                    return null;
                }
                return listValues;
            }

            @Override // io.ktor.util.StringValues
            public Set names() {
                return headers.names();
            }

            @Override // io.ktor.util.StringValues
            public Set entries() {
                return headers.toMultimap().entrySet();
            }
        };
    }

    public static final HttpProtocolVersion fromOkHttp(Protocol protocol) {
        Intrinsics.checkNotNullParameter(protocol, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[protocol.ordinal()]) {
            case 1:
                return HttpProtocolVersion.Companion.getHTTP_1_0();
            case 2:
                return HttpProtocolVersion.Companion.getHTTP_1_1();
            case 3:
                return HttpProtocolVersion.Companion.getSPDY_3();
            case 4:
                return HttpProtocolVersion.Companion.getHTTP_2_0();
            case 5:
                return HttpProtocolVersion.Companion.getHTTP_2_0();
            case 6:
                return HttpProtocolVersion.Companion.getQUIC();
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Throwable mapOkHttpException(HttpRequestData httpRequestData, IOException iOException) {
        Throwable thSocketTimeoutException;
        if (iOException instanceof StreamAdapterIOException) {
            thSocketTimeoutException = iOException.getCause();
            if (thSocketTimeoutException == null) {
                return iOException;
            }
        } else {
            if (!(iOException instanceof SocketTimeoutException)) {
                return iOException;
            }
            if (isConnectException(iOException)) {
                thSocketTimeoutException = HttpTimeoutKt.ConnectTimeoutException(httpRequestData, iOException);
            } else {
                thSocketTimeoutException = HttpTimeoutKt.SocketTimeoutException(httpRequestData, iOException);
            }
        }
        return thSocketTimeoutException;
    }

    private static final boolean isConnectException(IOException iOException) {
        String message = iOException.getMessage();
        return message != null && StringsKt.contains((CharSequence) message, (CharSequence) "connect", true);
    }

    public static final Object execute(OkHttpClient okHttpClient, Request request, HttpRequestData httpRequestData, Continuation continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final Call callNewCall = okHttpClient.newCall(request);
        callNewCall.enqueue(new OkHttpCallback(httpRequestData, cancellableContinuationImpl));
        cancellableContinuationImpl.invokeOnCancellation(new Function1() { // from class: io.ktor.client.engine.okhttp.OkUtilsKt$execute$2$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Throwable) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable th) {
                callNewCall.cancel();
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
