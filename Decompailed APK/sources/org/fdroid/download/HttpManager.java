package org.fdroid.download;

import ch.qos.logback.core.joran.action.Action;
import io.ktor.client.HttpClient;
import io.ktor.client.HttpClientConfig;
import io.ktor.client.HttpClientKt;
import io.ktor.client.engine.HttpClientEngineConfig;
import io.ktor.client.engine.HttpClientEngineFactory;
import io.ktor.client.plugins.HttpTimeout;
import io.ktor.client.plugins.HttpTimeoutKt;
import io.ktor.client.plugins.ResponseException;
import io.ktor.client.plugins.UserAgent;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestKt;
import io.ktor.client.request.UtilsKt;
import io.ktor.client.statement.HttpResponse;
import io.ktor.client.statement.HttpStatement;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpMessageBuilder;
import io.ktor.http.HttpMethod;
import io.ktor.http.URLUtilsKt;
import io.ktor.http.Url;
import io.ktor.http.content.NullBody;
import io.ktor.util.reflect.TypeInfoJvmKt;
import java.io.IOException;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$LongRef;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;
import kotlin.text.StringsKt;
import mu.KLogger;
import mu.KotlinLogging;
import okhttp3.Dns;
import okhttp3.HttpUrl;
import org.bouncycastle.asn1.BERTags;

/* JADX INFO: compiled from: HttpManager.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 B2\u00020\u0001:\u0001BBi\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\f\b\u0002\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\u001a\u0010\u001d\u001a\u00020\u00152\u0010\b\u0002\u0010\u0005\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007H\u0002J$\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020!2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0003H\u0086@¢\u0006\u0002\u0010#J*\u0010$\u001a\u00020%2\u0006\u0010 \u001a\u00020!2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010(\u001a\u00020)H\u0087@¢\u0006\u0002\u0010*J.\u0010+\u001a\u00020,2\u0006\u0010 \u001a\u00020!2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u0010&\u001a\u00020'H\u0082@¢\u0006\u0002\u00101J$\u00102\u001a\u0002032\u0006\u0010 \u001a\u00020!2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'H\u0080@¢\u0006\u0004\b4\u00105J$\u00106\u001a\u0002072\u0006\u0010 \u001a\u00020!2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'H\u0080@¢\u0006\u0004\b8\u00105J0\u00109\u001a\u00020%2\u0006\u0010/\u001a\u00020\u00032\u0006\u0010:\u001a\u00020\u00032\u0010\b\u0002\u0010;\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007H\u0086@¢\u0006\u0002\u0010<J$\u0010=\u001a\u00020%2\u000e\u0010\u0005\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u00072\n\b\u0002\u0010-\u001a\u0004\u0018\u00010.H\u0002J\u0014\u0010>\u001a\u00020%*\u00020?2\u0006\u0010 \u001a\u00020!H\u0002J\f\u0010@\u001a\u00020%*\u00020AH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R.\u0010\u0017\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u00072\u000e\u0010\u0016\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\"\u0010\u001a\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u001c\u0018\u00010\u001bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"Lorg/fdroid/download/HttpManager;", "", "userAgent", "", "queryString", "proxyConfig", "Ljava/net/Proxy;", "Lio/ktor/client/engine/ProxyConfig;", "customDns", "Lokhttp3/Dns;", "mirrorParameterManager", "Lorg/fdroid/download/MirrorParameterManager;", "highTimeouts", "", "mirrorChooser", "Lorg/fdroid/download/MirrorChooser;", "httpClientEngineFactory", "Lio/ktor/client/engine/HttpClientEngineFactory;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/net/Proxy;Lokhttp3/Dns;Lorg/fdroid/download/MirrorParameterManager;ZLorg/fdroid/download/MirrorChooser;Lio/ktor/client/engine/HttpClientEngineFactory;)V", "httpClient", "Lio/ktor/client/HttpClient;", Action.VALUE_ATTRIBUTE, "currentProxy", "getCurrentProxy$download_release", "()Ljava/net/Proxy;", "parameters", "", "Lkotlin/Pair;", "getNewHttpClient", "head", "Lorg/fdroid/download/HeadInfo;", "request", "Lorg/fdroid/download/DownloadRequest;", "eTag", "(Lorg/fdroid/download/DownloadRequest;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "get", "", "skipFirstBytes", "", "receiver", "Lorg/fdroid/download/BytesReceiver;", "(Lorg/fdroid/download/DownloadRequest;Ljava/lang/Long;Lorg/fdroid/download/BytesReceiver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getHttpStatement", "Lio/ktor/client/statement/HttpStatement;", "mirror", "Lorg/fdroid/download/Mirror;", "url", "Lio/ktor/http/Url;", "(Lorg/fdroid/download/DownloadRequest;Lorg/fdroid/download/Mirror;Lio/ktor/http/Url;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getChannel", "Lio/ktor/utils/io/ByteReadChannel;", "getChannel$download_release", "(Lorg/fdroid/download/DownloadRequest;Ljava/lang/Long;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBytes", "", "getBytes$download_release", "post", "json", "proxy", "(Ljava/lang/String;Ljava/lang/String;Ljava/net/Proxy;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resetProxyIfNeeded", "basicAuth", "Lio/ktor/http/HttpMessageBuilder;", "addQueryParameters", "Lio/ktor/client/request/HttpRequestBuilder;", "Companion", "download_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class HttpManager {
    public static final int READ_BUFFER = 8192;
    private static final long TIMEOUT_MILLIS_HIGH = 300000;
    private Proxy currentProxy;
    private final boolean highTimeouts;
    private HttpClient httpClient;
    private final HttpClientEngineFactory httpClientEngineFactory;
    private final MirrorChooser mirrorChooser;
    private final MirrorParameterManager mirrorParameterManager;
    private final List<Pair> parameters;
    private final String userAgent;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final KLogger log = KotlinLogging.INSTANCE.logger(new Function0() { // from class: org.fdroid.download.HttpManager$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Unit.INSTANCE;
        }
    });

    /* JADX INFO: renamed from: org.fdroid.download.HttpManager$head$1, reason: invalid class name */
    /* JADX INFO: compiled from: HttpManager.kt */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpManager.this.head(null, null, this);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public HttpManager(String userAgent) {
        this(userAgent, null, null, null, null, false, null, null, 254, null);
        Intrinsics.checkNotNullParameter(userAgent, "userAgent");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public HttpManager(String userAgent, String str) {
        this(userAgent, str, null, null, null, false, null, null, 252, null);
        Intrinsics.checkNotNullParameter(userAgent, "userAgent");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public HttpManager(String userAgent, String str, Proxy proxy) {
        this(userAgent, str, proxy, null, null, false, null, null, 248, null);
        Intrinsics.checkNotNullParameter(userAgent, "userAgent");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public HttpManager(String userAgent, String str, Proxy proxy, Dns dns) {
        this(userAgent, str, proxy, dns, null, false, null, null, 240, null);
        Intrinsics.checkNotNullParameter(userAgent, "userAgent");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public HttpManager(String userAgent, String str, Proxy proxy, Dns dns, MirrorParameterManager mirrorParameterManager) {
        this(userAgent, str, proxy, dns, mirrorParameterManager, false, null, null, BERTags.FLAGS, null);
        Intrinsics.checkNotNullParameter(userAgent, "userAgent");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public HttpManager(String userAgent, String str, Proxy proxy, Dns dns, MirrorParameterManager mirrorParameterManager, boolean z) {
        this(userAgent, str, proxy, dns, mirrorParameterManager, z, null, null, 192, null);
        Intrinsics.checkNotNullParameter(userAgent, "userAgent");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public HttpManager(String userAgent, String str, Proxy proxy, Dns dns, MirrorParameterManager mirrorParameterManager, boolean z, MirrorChooser mirrorChooser) {
        this(userAgent, str, proxy, dns, mirrorParameterManager, z, mirrorChooser, null, 128, null);
        Intrinsics.checkNotNullParameter(userAgent, "userAgent");
        Intrinsics.checkNotNullParameter(mirrorChooser, "mirrorChooser");
    }

    public final Object get(DownloadRequest downloadRequest, BytesReceiver bytesReceiver, Continuation continuation) throws NoResumeException, CancellationException, ResponseException {
        return get$default(this, downloadRequest, null, bytesReceiver, continuation, 2, null);
    }

    public HttpManager(String userAgent, String str, Proxy proxy, Dns dns, MirrorParameterManager mirrorParameterManager, boolean z, MirrorChooser mirrorChooser, HttpClientEngineFactory httpClientEngineFactory) {
        ArrayList arrayList;
        List listSplit$default;
        Intrinsics.checkNotNullParameter(userAgent, "userAgent");
        Intrinsics.checkNotNullParameter(mirrorChooser, "mirrorChooser");
        Intrinsics.checkNotNullParameter(httpClientEngineFactory, "httpClientEngineFactory");
        this.userAgent = userAgent;
        this.mirrorParameterManager = mirrorParameterManager;
        this.highTimeouts = z;
        this.mirrorChooser = mirrorChooser;
        this.httpClientEngineFactory = httpClientEngineFactory;
        this.httpClient = getNewHttpClient(proxy);
        if (str == null || (listSplit$default = StringsKt.split$default((CharSequence) str, new char[]{'&'}, false, 0, 6, (Object) null)) == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSplit$default, 10));
            Iterator it = listSplit$default.iterator();
            while (it.hasNext()) {
                List listSplit$default2 = StringsKt.split$default((CharSequence) it.next(), new char[]{'='}, false, 0, 6, (Object) null);
                arrayList.add(new Pair((String) listSplit$default2.get(0), (String) listSplit$default2.get(1)));
            }
        }
        this.parameters = arrayList;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ HttpManager(String str, String str2, Proxy proxy, Dns dns, MirrorParameterManager mirrorParameterManager, boolean z, MirrorChooser mirrorChooser, HttpClientEngineFactory httpClientEngineFactory, int i, DefaultConstructorMarker defaultConstructorMarker) {
        String str3 = (i & 2) != 0 ? null : str2;
        Proxy proxy2 = (i & 4) != 0 ? null : proxy;
        Dns dns2 = (i & 8) != 0 ? null : dns;
        MirrorParameterManager mirrorParameterManager2 = (i & 16) == 0 ? mirrorParameterManager : null;
        this(str, str3, proxy2, dns2, mirrorParameterManager2, (i & 32) != 0 ? false : z, (i & 64) != 0 ? new MirrorChooserWithParameters(mirrorParameterManager2) : mirrorChooser, (i & 128) != 0 ? HttpManagerKt.getHttpClientEngineFactory(dns2) : httpClientEngineFactory);
    }

    /* JADX INFO: compiled from: HttpManager.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/fdroid/download/HttpManager$Companion;", "", "<init>", "()V", "log", "Lmu/KLogger;", "getLog$download_release", "()Lmu/KLogger;", "READ_BUFFER", "", "TIMEOUT_MILLIS_HIGH", "", "isInvalidHttpUrl", "", "url", "", "download_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KLogger getLog$download_release() {
            return HttpManager.log;
        }

        public final boolean isInvalidHttpUrl(String url) {
            Intrinsics.checkNotNullParameter(url, "url");
            return HttpUrl.Companion.parse(url) == null;
        }
    }

    /* JADX INFO: renamed from: getCurrentProxy$download_release, reason: from getter */
    public final Proxy getCurrentProxy() {
        return this.currentProxy;
    }

    static /* synthetic */ HttpClient getNewHttpClient$default(HttpManager httpManager, Proxy proxy, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getNewHttpClient");
        }
        if ((i & 1) != 0) {
            proxy = null;
        }
        return httpManager.getNewHttpClient(proxy);
    }

    private final HttpClient getNewHttpClient(final Proxy proxyConfig) {
        this.currentProxy = proxyConfig;
        return HttpClientKt.HttpClient(this.httpClientEngineFactory, new Function1() { // from class: org.fdroid.download.HttpManager$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HttpManager.getNewHttpClient$lambda$4(proxyConfig, this, (HttpClientConfig) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getNewHttpClient$lambda$4(final Proxy proxy, final HttpManager httpManager, HttpClientConfig HttpClient) {
        Intrinsics.checkNotNullParameter(HttpClient, "$this$HttpClient");
        HttpClient.setFollowRedirects(false);
        HttpClient.setExpectSuccess(true);
        HttpClient.engine(new Function1() { // from class: org.fdroid.download.HttpManager$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HttpManager.getNewHttpClient$lambda$4$lambda$1(proxy, (HttpClientEngineConfig) obj);
            }
        });
        HttpClient.install(UserAgent.Plugin, new Function1() { // from class: org.fdroid.download.HttpManager$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HttpManager.getNewHttpClient$lambda$4$lambda$2(this.f$0, (UserAgent.Config) obj);
            }
        });
        HttpClient.install(HttpTimeout.Plugin, new Function1() { // from class: org.fdroid.download.HttpManager$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return HttpManager.getNewHttpClient$lambda$4$lambda$3(this.f$0, proxy, (HttpTimeout.HttpTimeoutCapabilityConfiguration) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getNewHttpClient$lambda$4$lambda$1(Proxy proxy, HttpClientEngineConfig engine) {
        Intrinsics.checkNotNullParameter(engine, "$this$engine");
        engine.setPipelining(true);
        engine.setProxy(proxy);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getNewHttpClient$lambda$4$lambda$2(HttpManager httpManager, UserAgent.Config install) {
        Intrinsics.checkNotNullParameter(install, "$this$install");
        install.setAgent(httpManager.userAgent);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getNewHttpClient$lambda$4$lambda$3(HttpManager httpManager, Proxy proxy, HttpTimeout.HttpTimeoutCapabilityConfiguration install) {
        Intrinsics.checkNotNullParameter(install, "$this$install");
        if (httpManager.highTimeouts || ProxyKt.isTor(proxy)) {
            install.setConnectTimeoutMillis(Long.valueOf(TIMEOUT_MILLIS_HIGH));
            install.setSocketTimeoutMillis(Long.valueOf(TIMEOUT_MILLIS_HIGH));
            install.setRequestTimeoutMillis(Long.valueOf(TIMEOUT_MILLIS_HIGH));
        }
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Object head$default(HttpManager httpManager, DownloadRequest downloadRequest, String str, Continuation continuation, int i, Object obj) throws NotFoundException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: head");
        }
        if ((i & 2) != 0) {
            str = null;
        }
        return httpManager.head(downloadRequest, str, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object head(org.fdroid.download.DownloadRequest r6, java.lang.String r7, kotlin.coroutines.Continuation r8) throws org.fdroid.download.NotFoundException {
        /*
            r5 = this;
            boolean r0 = r8 instanceof org.fdroid.download.HttpManager.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            org.fdroid.download.HttpManager$head$1 r0 = (org.fdroid.download.HttpManager.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            org.fdroid.download.HttpManager$head$1 r0 = new org.fdroid.download.HttpManager$head$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L39
            if (r2 != r4) goto L31
            java.lang.Object r6 = r0.L$0
            r7 = r6
            java.lang.String r7 = (java.lang.String) r7
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: io.ktor.client.plugins.ResponseException -> L2f
            goto L4e
        L2f:
            r6 = move-exception
            goto L9b
        L31:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L39:
            kotlin.ResultKt.throwOnFailure(r8)
            org.fdroid.download.MirrorChooser r8 = r5.mirrorChooser     // Catch: io.ktor.client.plugins.ResponseException -> L2f
            org.fdroid.download.HttpManager$head$response$1 r2 = new org.fdroid.download.HttpManager$head$response$1     // Catch: io.ktor.client.plugins.ResponseException -> L2f
            r2.<init>(r5, r6, r3)     // Catch: io.ktor.client.plugins.ResponseException -> L2f
            r0.L$0 = r7     // Catch: io.ktor.client.plugins.ResponseException -> L2f
            r0.label = r4     // Catch: io.ktor.client.plugins.ResponseException -> L2f
            java.lang.Object r8 = r8.mirrorRequest(r6, r2, r0)     // Catch: io.ktor.client.plugins.ResponseException -> L2f
            if (r8 != r1) goto L4e
            return r1
        L4e:
            io.ktor.client.statement.HttpResponse r8 = (io.ktor.client.statement.HttpResponse) r8     // Catch: io.ktor.client.plugins.ResponseException -> L2f
            java.lang.Long r6 = io.ktor.http.HttpMessagePropertiesKt.contentLength(r8)
            io.ktor.http.Headers r0 = r8.getHeaders()
            io.ktor.http.HttpHeaders r1 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r2 = r1.getLastModified()
            java.lang.String r0 = r0.get(r2)
            if (r7 == 0) goto L89
            io.ktor.http.Headers r2 = r8.getHeaders()
            java.lang.String r3 = r1.getETag()
            java.lang.String r2 = r2.get(r3)
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r7)
            if (r7 == 0) goto L89
            org.fdroid.download.HeadInfo r7 = new org.fdroid.download.HeadInfo
            io.ktor.http.Headers r8 = r8.getHeaders()
            java.lang.String r1 = r1.getETag()
            java.lang.String r8 = r8.get(r1)
            r1 = 0
            r7.<init>(r1, r8, r6, r0)
            return r7
        L89:
            org.fdroid.download.HeadInfo r7 = new org.fdroid.download.HeadInfo
            io.ktor.http.Headers r8 = r8.getHeaders()
            java.lang.String r1 = r1.getETag()
            java.lang.String r8 = r8.get(r1)
            r7.<init>(r4, r8, r6, r0)
            return r7
        L9b:
            mu.KLogger r7 = org.fdroid.download.HttpManager.log
            org.fdroid.download.HttpManager$$ExternalSyntheticLambda1 r8 = new org.fdroid.download.HttpManager$$ExternalSyntheticLambda1
            r8.<init>()
            r7.warn(r8)
            io.ktor.client.statement.HttpResponse r6 = r6.getResponse()
            io.ktor.http.HttpStatusCode r6 = r6.getStatus()
            io.ktor.http.HttpStatusCode$Companion r7 = io.ktor.http.HttpStatusCode.Companion
            io.ktor.http.HttpStatusCode r7 = r7.getNotFound()
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r7)
            if (r6 != 0) goto Lba
            return r3
        Lba:
            org.fdroid.download.NotFoundException r6 = new org.fdroid.download.NotFoundException
            r6.<init>(r3, r4, r3)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.download.HttpManager.head(org.fdroid.download.DownloadRequest, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object head$lambda$5(ResponseException responseException) {
        return "Error getting HEAD: " + responseException.getResponse().getStatus();
    }

    public static /* synthetic */ Object get$default(HttpManager httpManager, DownloadRequest downloadRequest, Long l, BytesReceiver bytesReceiver, Continuation continuation, int i, Object obj) throws NoResumeException, CancellationException, ResponseException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: get");
        }
        if ((i & 2) != 0) {
            l = null;
        }
        return httpManager.get(downloadRequest, l, bytesReceiver, continuation);
    }

    /* JADX INFO: renamed from: org.fdroid.download.HttpManager$get$2, reason: invalid class name */
    /* JADX INFO: compiled from: HttpManager.kt */
    static final class AnonymousClass2 extends SuspendLambda implements Function3 {
        final /* synthetic */ BytesReceiver $receiver;
        final /* synthetic */ DownloadRequest $request;
        final /* synthetic */ Ref$LongRef $skipBytes;
        /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(DownloadRequest downloadRequest, Ref$LongRef ref$LongRef, BytesReceiver bytesReceiver, Continuation continuation) {
            super(3, continuation);
            this.$request = downloadRequest;
            this.$skipBytes = ref$LongRef;
            this.$receiver = bytesReceiver;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Mirror mirror, Url url, Continuation continuation) {
            AnonymousClass2 anonymousClass2 = HttpManager.this.new AnonymousClass2(this.$request, this.$skipBytes, this.$receiver, continuation);
            anonymousClass2.L$0 = mirror;
            anonymousClass2.L$1 = url;
            return anonymousClass2.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: org.fdroid.download.HttpManager$get$2$1, reason: invalid class name */
        /* JADX INFO: compiled from: HttpManager.kt */
        static final class AnonymousClass1 extends SuspendLambda implements Function2 {
            final /* synthetic */ BytesReceiver $receiver;
            final /* synthetic */ Ref$LongRef $skipBytes;
            /* synthetic */ Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(Ref$LongRef ref$LongRef, BytesReceiver bytesReceiver, Continuation continuation) {
                super(2, continuation);
                this.$skipBytes = ref$LongRef;
                this.$receiver = bytesReceiver;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$skipBytes, this.$receiver, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(HttpResponse httpResponse, Continuation continuation) {
                return ((AnonymousClass1) create(httpResponse, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:26:0x009e  */
            /* JADX WARN: Removed duplicated region for block: B:32:0x00bc  */
            /* JADX WARN: Removed duplicated region for block: B:37:0x00e2  */
            /* JADX WARN: Removed duplicated region for block: B:38:0x00e4  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x00d4 -> B:36:0x00d7). Please report as a decompilation issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r15) throws org.fdroid.download.NoResumeException {
                /*
                    Method dump skipped, instruction units count: 239
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: org.fdroid.download.HttpManager.AnonymousClass2.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws IOException {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Mirror mirror = (Mirror) this.L$0;
                Url url = (Url) this.L$1;
                HttpManager httpManager = HttpManager.this;
                DownloadRequest downloadRequest = this.$request;
                long j = this.$skipBytes.element;
                this.L$0 = null;
                this.label = 1;
                obj = httpManager.getHttpStatement(downloadRequest, mirror, url, j, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                ResultKt.throwOnFailure(obj);
            }
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$skipBytes, this.$receiver, null);
            this.label = 2;
            if (((HttpStatement) obj).execute(anonymousClass1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final Object get(DownloadRequest downloadRequest, Long l, BytesReceiver bytesReceiver, Continuation continuation) throws NoResumeException, CancellationException, ResponseException {
        Ref$LongRef ref$LongRef = new Ref$LongRef();
        ref$LongRef.element = l != null ? l.longValue() : 0L;
        Object objMirrorRequest = this.mirrorChooser.mirrorRequest(downloadRequest, new AnonymousClass2(downloadRequest, ref$LongRef, bytesReceiver, null), continuation);
        return objMirrorRequest == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMirrorRequest : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object getHttpStatement(DownloadRequest downloadRequest, Mirror mirror, final Url url, long j, Continuation continuation) throws IOException {
        resetProxyIfNeeded(downloadRequest.getProxy(), mirror);
        log.debug(new Function0() { // from class: org.fdroid.download.HttpManager$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return HttpManager.getHttpStatement$lambda$6(url);
            }
        });
        HttpClient httpClient = this.httpClient;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        URLUtilsKt.takeFrom(httpRequestBuilder.getUrl(), url);
        addQueryParameters(httpRequestBuilder);
        basicAuth(httpRequestBuilder, downloadRequest);
        if (mirror.isOnion()) {
            HttpTimeoutKt.timeout(httpRequestBuilder, new Function1() { // from class: org.fdroid.download.HttpManager$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return HttpManager.getHttpStatement$lambda$8$lambda$7((HttpTimeout.HttpTimeoutCapabilityConfiguration) obj);
                }
            });
        }
        if (j > 0) {
            UtilsKt.header(httpRequestBuilder, HttpHeaders.INSTANCE.getRange(), "bytes=" + j + "-");
        }
        httpRequestBuilder.setMethod(HttpMethod.Companion.getGet());
        return new HttpStatement(httpRequestBuilder, httpClient);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object getHttpStatement$lambda$6(Url url) {
        return "GET " + url;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getHttpStatement$lambda$8$lambda$7(HttpTimeout.HttpTimeoutCapabilityConfiguration timeout) {
        Intrinsics.checkNotNullParameter(timeout, "$this$timeout");
        timeout.setConnectTimeoutMillis(20000L);
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Object getChannel$download_release$default(HttpManager httpManager, DownloadRequest downloadRequest, Long l, Continuation continuation, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getChannel");
        }
        if ((i & 2) != 0) {
            l = null;
        }
        return httpManager.getChannel$download_release(downloadRequest, l, continuation);
    }

    public final Object getChannel$download_release(DownloadRequest downloadRequest, Long l, Continuation continuation) {
        return this.mirrorChooser.mirrorRequest(downloadRequest, new HttpManager$getChannel$2(this, downloadRequest, l, null), continuation);
    }

    public static /* synthetic */ Object getBytes$download_release$default(HttpManager httpManager, DownloadRequest downloadRequest, Long l, Continuation continuation, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getBytes");
        }
        if ((i & 2) != 0) {
            l = null;
        }
        return httpManager.getBytes$download_release(downloadRequest, l, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r4v0, types: [org.fdroid.download.HttpManager] */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.io.ByteArrayOutputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getBytes$download_release(org.fdroid.download.DownloadRequest r5, java.lang.Long r6, kotlin.coroutines.Continuation r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof org.fdroid.download.HttpManager$getBytes$1
            if (r0 == 0) goto L13
            r0 = r7
            org.fdroid.download.HttpManager$getBytes$1 r0 = (org.fdroid.download.HttpManager$getBytes$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            org.fdroid.download.HttpManager$getBytes$1 r0 = new org.fdroid.download.HttpManager$getBytes$1
            r0.<init>(r4, r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            java.lang.Object r5 = r0.L$1
            java.io.Closeable r5 = (java.io.Closeable) r5
            java.lang.Object r6 = r0.L$0
            java.io.ByteArrayOutputStream r6 = (java.io.ByteArrayOutputStream) r6
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L31
            goto L57
        L31:
            r6 = move-exception
            goto L69
        L33:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3b:
            kotlin.ResultKt.throwOnFailure(r7)
            java.io.ByteArrayOutputStream r7 = new java.io.ByteArrayOutputStream
            r7.<init>()
            org.fdroid.download.HttpManager$getBytes$2$1 r2 = new org.fdroid.download.HttpManager$getBytes$2$1     // Catch: java.lang.Throwable -> L67
            r2.<init>()     // Catch: java.lang.Throwable -> L67
            r0.L$0 = r7     // Catch: java.lang.Throwable -> L67
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L67
            r0.label = r3     // Catch: java.lang.Throwable -> L67
            java.lang.Object r5 = r4.get(r5, r6, r2, r0)     // Catch: java.lang.Throwable -> L67
            if (r5 != r1) goto L55
            return r1
        L55:
            r5 = r7
            r6 = r5
        L57:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L31
            r7 = 0
            kotlin.io.CloseableKt.closeFinally(r5, r7)
            byte[] r5 = r6.toByteArray()
            java.lang.String r6 = "toByteArray(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            return r5
        L67:
            r6 = move-exception
            r5 = r7
        L69:
            throw r6     // Catch: java.lang.Throwable -> L6a
        L6a:
            r7 = move-exception
            kotlin.io.CloseableKt.closeFinally(r5, r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.download.HttpManager.getBytes$download_release(org.fdroid.download.DownloadRequest, java.lang.Long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object post$default(HttpManager httpManager, String str, String str2, Proxy proxy, Continuation continuation, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: post");
        }
        if ((i & 4) != 0) {
            proxy = null;
        }
        return httpManager.post(str, str2, proxy, continuation);
    }

    public final Object post(String str, String str2, Proxy proxy, Continuation continuation) throws IOException {
        resetProxyIfNeeded$default(this, proxy, null, 2, null);
        HttpClient httpClient = this.httpClient;
        HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
        addQueryParameters(httpRequestBuilder);
        HttpRequestKt.url(httpRequestBuilder, str);
        UtilsKt.header(httpRequestBuilder, HttpHeaders.INSTANCE.getContentType(), "application/json; utf-8");
        if (str2 == null) {
            httpRequestBuilder.setBody(NullBody.INSTANCE);
            KType kTypeTypeOf = Reflection.typeOf(String.class);
            httpRequestBuilder.setBodyType(TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(kTypeTypeOf), Reflection.getOrCreateKotlinClass(String.class), kTypeTypeOf));
        } else {
            httpRequestBuilder.setBody(str2);
            KType kTypeTypeOf2 = Reflection.typeOf(String.class);
            httpRequestBuilder.setBodyType(TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(kTypeTypeOf2), Reflection.getOrCreateKotlinClass(String.class), kTypeTypeOf2));
        }
        httpRequestBuilder.setMethod(HttpMethod.Companion.getPost());
        Object objExecute = new HttpStatement(httpRequestBuilder, httpClient).execute(continuation);
        return objExecute == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objExecute : Unit.INSTANCE;
    }

    static /* synthetic */ void resetProxyIfNeeded$default(HttpManager httpManager, Proxy proxy, Mirror mirror, int i, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: resetProxyIfNeeded");
        }
        if ((i & 2) != 0) {
            mirror = null;
        }
        httpManager.resetProxyIfNeeded(proxy, mirror);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetProxyIfNeeded(final Proxy proxyConfig, final Mirror mirror) throws IOException {
        if (MirrorKt.isLocal(mirror) && proxyConfig != null) {
            if (this.currentProxy != null) {
                log.debug(new Function0() { // from class: org.fdroid.download.HttpManager$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return HttpManager.resetProxyIfNeeded$lambda$11(mirror);
                    }
                });
            }
            proxyConfig = null;
        }
        if (Intrinsics.areEqual(this.currentProxy, proxyConfig)) {
            return;
        }
        log.debug(new Function0() { // from class: org.fdroid.download.HttpManager$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return HttpManager.resetProxyIfNeeded$lambda$12(this.f$0, proxyConfig);
            }
        });
        this.httpClient.close();
        this.httpClient = getNewHttpClient(proxyConfig);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object resetProxyIfNeeded$lambda$11(Mirror mirror) {
        return "Forcing mirror to null, because mirror is local: " + mirror;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object resetProxyIfNeeded$lambda$12(HttpManager httpManager, Proxy proxy) {
        return "Switching proxy from [" + httpManager.currentProxy + "] to [" + proxy + "]";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void basicAuth(HttpMessageBuilder httpMessageBuilder, DownloadRequest downloadRequest) {
        if (downloadRequest.getHasCredentials()) {
            String username = downloadRequest.getUsername();
            Intrinsics.checkNotNull(username);
            String password = downloadRequest.getPassword();
            Intrinsics.checkNotNull(password);
            UtilsKt.basicAuth(httpMessageBuilder, username, password);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addQueryParameters(HttpRequestBuilder httpRequestBuilder) {
        List<Pair> list = this.parameters;
        if (list != null) {
            for (Pair pair : list) {
                UtilsKt.parameter(httpRequestBuilder, (String) pair.component1(), (String) pair.component2());
            }
        }
    }
}
