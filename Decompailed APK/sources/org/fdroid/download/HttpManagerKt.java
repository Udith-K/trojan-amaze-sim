package org.fdroid.download;

import android.util.Log;
import io.ktor.client.engine.HttpClientEngine;
import io.ktor.client.engine.HttpClientEngineFactory;
import io.ktor.client.engine.okhttp.OkHttp;
import io.ktor.client.engine.okhttp.OkHttpConfig;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ConnectionSpec;
import okhttp3.Dns;
import okhttp3.OkHttpClient;
import okhttp3.internal.tls.OkHostnameVerifier;
import org.fdroid.download.HttpManagerKt;

/* JADX INFO: compiled from: HttpManager.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u0006\u0012\u0002\b\u00030\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0000\u001a\u001a\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@¢\u0006\u0002\u0010\t\u001a\u001a\u0010\n\u001a\u00020\u000b*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@¢\u0006\u0002\u0010\t¨\u0006\f"}, d2 = {"getHttpClientEngineFactory", "Lio/ktor/client/engine/HttpClientEngineFactory;", "customDns", "Lokhttp3/Dns;", "getInputStream", "Ljava/io/InputStream;", "Lorg/fdroid/download/HttpManager;", "request", "Lorg/fdroid/download/DownloadRequest;", "(Lorg/fdroid/download/HttpManager;Lorg/fdroid/download/DownloadRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDigestInputStream", "Ljava/security/DigestInputStream;", "download_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class HttpManagerKt {

    /* JADX INFO: renamed from: org.fdroid.download.HttpManagerKt$getDigestInputStream$1, reason: invalid class name */
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
            return HttpManagerKt.getDigestInputStream(null, null, this);
        }
    }

    /* JADX INFO: renamed from: org.fdroid.download.HttpManagerKt$getInputStream$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HttpManager.kt */
    static final class C02011 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C02011(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpManagerKt.getInputStream(null, null, this);
        }
    }

    /* JADX INFO: renamed from: org.fdroid.download.HttpManagerKt$getHttpClientEngineFactory$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HttpManager.kt */
    @Metadata(d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J#\u0010\u0007\u001a\u00020\u00062\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00040\u0003H\u0016¢\u0006\u0004\b\u0007\u0010\bR\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"org/fdroid/download/HttpManagerKt$getHttpClientEngineFactory$1", "Lio/ktor/client/engine/HttpClientEngineFactory;", "Lio/ktor/client/engine/okhttp/OkHttpConfig;", "Lkotlin/Function1;", "", "block", "Lio/ktor/client/engine/HttpClientEngine;", "create", "(Lkotlin/jvm/functions/Function1;)Lio/ktor/client/engine/HttpClientEngine;", "", "Lokhttp3/ConnectionSpec;", "connectionSpecs", "Ljava/util/List;", "download_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class C02001 implements HttpClientEngineFactory {
        final /* synthetic */ Dns $customDns;
        private final List<ConnectionSpec> connectionSpecs = CollectionsKt.listOf((Object[]) new ConnectionSpec[]{ConnectionSpec.RESTRICTED_TLS, ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT});

        C02001(Dns dns) {
            this.$customDns = dns;
        }

        @Override // io.ktor.client.engine.HttpClientEngineFactory
        public HttpClientEngine create(final Function1 block) {
            Intrinsics.checkNotNullParameter(block, "block");
            OkHttp okHttp = OkHttp.INSTANCE;
            final Dns dns = this.$customDns;
            return okHttp.create(new Function1() { // from class: org.fdroid.download.HttpManagerKt$getHttpClientEngineFactory$1$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return HttpManagerKt.C02001.create$lambda$2(block, dns, this, (OkHttpConfig) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit create$lambda$2(Function1 function1, final Dns dns, final C02001 c02001, final OkHttpConfig create) {
            Intrinsics.checkNotNullParameter(create, "$this$create");
            function1.invoke(create);
            create.config(new Function1() { // from class: org.fdroid.download.HttpManagerKt$getHttpClientEngineFactory$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return HttpManagerKt.C02001.create$lambda$2$lambda$1(create, dns, c02001, (OkHttpClient.Builder) obj);
                }
            });
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit create$lambda$2$lambda$1(OkHttpConfig okHttpConfig, Dns dns, C02001 c02001, OkHttpClient.Builder config) {
            Intrinsics.checkNotNullParameter(config, "$this$config");
            if (ProxyKt.isTor(okHttpConfig.getProxy())) {
                config.dns(new NoDns());
            } else if (dns != null) {
                config.dns(dns);
            }
            config.hostnameVerifier(new HostnameVerifier() { // from class: org.fdroid.download.HttpManagerKt$getHttpClientEngineFactory$1$$ExternalSyntheticLambda2
                @Override // javax.net.ssl.HostnameVerifier
                public final boolean verify(String str, SSLSession sSLSession) {
                    return HttpManagerKt.C02001.create$lambda$2$lambda$1$lambda$0(str, sSLSession);
                }
            });
            config.connectionSpecs(c02001.connectionSpecs);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean create$lambda$2$lambda$1$lambda$0(String str, SSLSession sSLSession) {
            if (sSLSession != null) {
                try {
                    SSLSessionContext sessionContext = sSLSession.getSessionContext();
                    if (sessionContext != null) {
                        sessionContext.setSessionTimeout(10);
                    }
                } catch (NullPointerException e) {
                    Log.e("HttpManager", "Error setting session timeout: ", e);
                }
            }
            OkHostnameVerifier okHostnameVerifier = OkHostnameVerifier.INSTANCE;
            Intrinsics.checkNotNull(str);
            Intrinsics.checkNotNull(sSLSession);
            return okHostnameVerifier.verify(str, sSLSession);
        }
    }

    public static final HttpClientEngineFactory getHttpClientEngineFactory(Dns dns) {
        return new C02001(dns);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object getInputStream(org.fdroid.download.HttpManager r8, org.fdroid.download.DownloadRequest r9, kotlin.coroutines.Continuation r10) {
        /*
            boolean r0 = r10 instanceof org.fdroid.download.HttpManagerKt.C02011
            if (r0 == 0) goto L14
            r0 = r10
            org.fdroid.download.HttpManagerKt$getInputStream$1 r0 = (org.fdroid.download.HttpManagerKt.C02011) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r4 = r0
            goto L1a
        L14:
            org.fdroid.download.HttpManagerKt$getInputStream$1 r0 = new org.fdroid.download.HttpManagerKt$getInputStream$1
            r0.<init>(r10)
            goto L12
        L1a:
            java.lang.Object r10 = r4.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            r7 = 1
            if (r1 == 0) goto L33
            if (r1 != r7) goto L2b
            kotlin.ResultKt.throwOnFailure(r10)
            goto L44
        L2b:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L33:
            kotlin.ResultKt.throwOnFailure(r10)
            r4.label = r7
            r3 = 0
            r5 = 2
            r6 = 0
            r1 = r8
            r2 = r9
            java.lang.Object r10 = org.fdroid.download.HttpManager.getChannel$download_release$default(r1, r2, r3, r4, r5, r6)
            if (r10 != r0) goto L44
            return r0
        L44:
            io.ktor.utils.io.ByteReadChannel r10 = (io.ktor.utils.io.ByteReadChannel) r10
            r8 = 0
            java.io.InputStream r8 = io.ktor.utils.io.jvm.javaio.BlockingKt.toInputStream$default(r10, r8, r7, r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.download.HttpManagerKt.getInputStream(org.fdroid.download.HttpManager, org.fdroid.download.DownloadRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object getDigestInputStream(org.fdroid.download.HttpManager r9, org.fdroid.download.DownloadRequest r10, kotlin.coroutines.Continuation r11) {
        /*
            boolean r0 = r11 instanceof org.fdroid.download.HttpManagerKt.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r11
            org.fdroid.download.HttpManagerKt$getDigestInputStream$1 r0 = (org.fdroid.download.HttpManagerKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r4 = r0
            goto L1a
        L14:
            org.fdroid.download.HttpManagerKt$getDigestInputStream$1 r0 = new org.fdroid.download.HttpManagerKt$getDigestInputStream$1
            r0.<init>(r11)
            goto L12
        L1a:
            java.lang.Object r11 = r4.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            r7 = 1
            if (r1 == 0) goto L37
            if (r1 != r7) goto L2f
            java.lang.Object r9 = r4.L$0
            java.security.MessageDigest r9 = (java.security.MessageDigest) r9
            kotlin.ResultKt.throwOnFailure(r11)
            goto L53
        L2f:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L37:
            kotlin.ResultKt.throwOnFailure(r11)
            java.lang.String r11 = "SHA-256"
            java.security.MessageDigest r11 = java.security.MessageDigest.getInstance(r11)
            r4.L$0 = r11
            r4.label = r7
            r3 = 0
            r5 = 2
            r6 = 0
            r1 = r9
            r2 = r10
            java.lang.Object r9 = org.fdroid.download.HttpManager.getChannel$download_release$default(r1, r2, r3, r4, r5, r6)
            if (r9 != r0) goto L50
            return r0
        L50:
            r8 = r11
            r11 = r9
            r9 = r8
        L53:
            io.ktor.utils.io.ByteReadChannel r11 = (io.ktor.utils.io.ByteReadChannel) r11
            r10 = 0
            java.io.InputStream r10 = io.ktor.utils.io.jvm.javaio.BlockingKt.toInputStream$default(r11, r10, r7, r10)
            java.security.DigestInputStream r11 = new java.security.DigestInputStream
            r11.<init>(r10, r9)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.download.HttpManagerKt.getDigestInputStream(org.fdroid.download.HttpManager, org.fdroid.download.DownloadRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
