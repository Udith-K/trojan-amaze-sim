package io.ktor.client.engine.okhttp;

import io.ktor.client.call.UnsupportedContentTypeException;
import io.ktor.client.engine.UtilsKt;
import io.ktor.client.plugins.HttpTimeout;
import io.ktor.client.plugins.HttpTimeoutKt;
import io.ktor.client.request.HttpRequestData;
import io.ktor.http.HttpHeaders;
import io.ktor.http.content.OutgoingContent;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.CoroutinesKt;
import io.ktor.utils.io.WriterScope;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.GlobalScope;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.internal.http.HttpMethod;
import okio.BufferedSource;

/* JADX INFO: compiled from: OkHttpEngine.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class OkHttpEngineKt {

    /* JADX INFO: renamed from: io.ktor.client.engine.okhttp.OkHttpEngineKt$toChannel$1, reason: invalid class name */
    /* JADX INFO: compiled from: OkHttpEngine.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ CoroutineContext $context;
        final /* synthetic */ HttpRequestData $requestData;
        final /* synthetic */ BufferedSource $this_toChannel;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(BufferedSource bufferedSource, CoroutineContext coroutineContext, HttpRequestData httpRequestData, Continuation continuation) {
            super(2, continuation);
            this.$this_toChannel = bufferedSource;
            this.$context = coroutineContext;
            this.$requestData = httpRequestData;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_toChannel, this.$context, this.$requestData, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(WriterScope writerScope, Continuation continuation) {
            return ((AnonymousClass1) create(writerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x005a A[Catch: all -> 0x008f, TryCatch #3 {all -> 0x008f, blocks: (B:24:0x0087, B:15:0x0054, B:17:0x005a, B:19:0x0060, B:21:0x0064, B:27:0x0093), top: B:48:0x0087 }] */
        /* JADX WARN: Removed duplicated region for block: B:40:0x00b0  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x00b6  */
        /* JADX WARN: Removed duplicated region for block: B:46:0x0097 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0084 -> B:48:0x0087). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r18) throws java.lang.Throwable {
            /*
                r17 = this;
                r7 = r17
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r7.label
                r8 = 1
                r9 = 0
                if (r1 == 0) goto L3c
                if (r1 != r8) goto L34
                java.lang.Object r1 = r7.L$5
                kotlin.jvm.internal.Ref$IntRef r1 = (kotlin.jvm.internal.Ref$IntRef) r1
                java.lang.Object r2 = r7.L$4
                okio.BufferedSource r2 = (okio.BufferedSource) r2
                java.lang.Object r3 = r7.L$3
                io.ktor.client.request.HttpRequestData r3 = (io.ktor.client.request.HttpRequestData) r3
                java.lang.Object r4 = r7.L$2
                kotlin.coroutines.CoroutineContext r4 = (kotlin.coroutines.CoroutineContext) r4
                java.lang.Object r5 = r7.L$1
                java.io.Closeable r5 = (java.io.Closeable) r5
                java.lang.Object r6 = r7.L$0
                io.ktor.utils.io.WriterScope r6 = (io.ktor.utils.io.WriterScope) r6
                kotlin.ResultKt.throwOnFailure(r18)     // Catch: java.lang.Throwable -> L30
                r10 = r1
                r11 = r2
                r12 = r3
                r13 = r4
                r14 = r5
                r15 = r6
                goto L87
            L30:
                r0 = move-exception
                r1 = r0
                goto L9e
            L34:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
                r0.<init>(r1)
                throw r0
            L3c:
                kotlin.ResultKt.throwOnFailure(r18)
                java.lang.Object r1 = r7.L$0
                io.ktor.utils.io.WriterScope r1 = (io.ktor.utils.io.WriterScope) r1
                okio.BufferedSource r5 = r7.$this_toChannel
                kotlin.coroutines.CoroutineContext r2 = r7.$context
                io.ktor.client.request.HttpRequestData r3 = r7.$requestData
                kotlin.jvm.internal.Ref$IntRef r4 = new kotlin.jvm.internal.Ref$IntRef     // Catch: java.lang.Throwable -> L30
                r4.<init>()     // Catch: java.lang.Throwable -> L30
                r15 = r1
                r13 = r2
                r12 = r3
                r10 = r4
                r11 = r5
                r14 = r11
            L54:
                boolean r1 = r11.isOpen()     // Catch: java.lang.Throwable -> L8f
                if (r1 == 0) goto L93
                boolean r1 = kotlinx.coroutines.JobKt.isActive(r13)     // Catch: java.lang.Throwable -> L8f
                if (r1 == 0) goto L93
                int r1 = r10.element     // Catch: java.lang.Throwable -> L8f
                if (r1 < 0) goto L93
                io.ktor.utils.io.ByteWriteChannel r1 = r15.getChannel()     // Catch: java.lang.Throwable -> L8f
                io.ktor.client.engine.okhttp.OkHttpEngineKt$toChannel$1$1$1 r3 = new io.ktor.client.engine.okhttp.OkHttpEngineKt$toChannel$1$1$1     // Catch: java.lang.Throwable -> L8f
                r3.<init>()     // Catch: java.lang.Throwable -> L8f
                r7.L$0 = r15     // Catch: java.lang.Throwable -> L8f
                r7.L$1 = r14     // Catch: java.lang.Throwable -> L8f
                r7.L$2 = r13     // Catch: java.lang.Throwable -> L8f
                r7.L$3 = r12     // Catch: java.lang.Throwable -> L8f
                r7.L$4 = r11     // Catch: java.lang.Throwable -> L8f
                r7.L$5 = r10     // Catch: java.lang.Throwable -> L8f
                r7.label = r8     // Catch: java.lang.Throwable -> L8f
                r2 = 0
                r5 = 1
                r6 = 0
                r4 = r17
                java.lang.Object r1 = io.ktor.utils.io.ByteWriteChannel.DefaultImpls.write$default(r1, r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L8f
                if (r1 != r0) goto L87
                return r0
            L87:
                io.ktor.utils.io.ByteWriteChannel r1 = r15.getChannel()     // Catch: java.lang.Throwable -> L8f
                r1.flush()     // Catch: java.lang.Throwable -> L8f
                goto L54
            L8f:
                r0 = move-exception
                r1 = r0
                r5 = r14
                goto L9e
            L93:
                kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L8f
                if (r14 == 0) goto Lae
                r14.close()     // Catch: java.lang.Throwable -> L9b
                goto Lae
            L9b:
                r0 = move-exception
                r9 = r0
                goto Lae
            L9e:
                if (r5 == 0) goto La9
                r5.close()     // Catch: java.lang.Throwable -> La4
                goto La9
            La4:
                r0 = move-exception
                r2 = r0
                kotlin.ExceptionsKt.addSuppressed(r1, r2)
            La9:
                r16 = r9
                r9 = r1
                r1 = r16
            Lae:
                if (r9 != 0) goto Lb6
                kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            Lb6:
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.engine.okhttp.OkHttpEngineKt.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ByteReadChannel toChannel(BufferedSource bufferedSource, CoroutineContext coroutineContext, HttpRequestData httpRequestData) {
        return CoroutinesKt.writer$default(GlobalScope.INSTANCE, coroutineContext, false, new AnonymousClass1(bufferedSource, coroutineContext, httpRequestData, null), 2, null).getChannel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Throwable mapExceptions(Throwable th, HttpRequestData httpRequestData) {
        return th instanceof SocketTimeoutException ? HttpTimeoutKt.SocketTimeoutException(httpRequestData, th) : th;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Request convertToOkHttpRequest(HttpRequestData httpRequestData, CoroutineContext coroutineContext) {
        final Request.Builder builder = new Request.Builder();
        builder.url(httpRequestData.getUrl().toString());
        UtilsKt.mergeHeaders(httpRequestData.getHeaders(), httpRequestData.getBody(), new Function2() { // from class: io.ktor.client.engine.okhttp.OkHttpEngineKt$convertToOkHttpRequest$1$1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((String) obj, (String) obj2);
                return Unit.INSTANCE;
            }

            public final void invoke(String key, String value) {
                Intrinsics.checkNotNullParameter(key, "key");
                Intrinsics.checkNotNullParameter(value, "value");
                if (Intrinsics.areEqual(key, HttpHeaders.INSTANCE.getContentLength())) {
                    return;
                }
                builder.addHeader(key, value);
            }
        });
        builder.method(httpRequestData.getMethod().getValue(), HttpMethod.permitsRequestBody(httpRequestData.getMethod().getValue()) ? convertToOkHttpBody(httpRequestData.getBody(), coroutineContext) : null);
        return builder.build();
    }

    public static final RequestBody convertToOkHttpBody(final OutgoingContent outgoingContent, CoroutineContext callContext) {
        Intrinsics.checkNotNullParameter(outgoingContent, "<this>");
        Intrinsics.checkNotNullParameter(callContext, "callContext");
        if (outgoingContent instanceof OutgoingContent.ByteArrayContent) {
            byte[] bArrBytes = ((OutgoingContent.ByteArrayContent) outgoingContent).bytes();
            return RequestBody.Companion.create(bArrBytes, MediaType.Companion.parse(String.valueOf(outgoingContent.getContentType())), 0, bArrBytes.length);
        }
        if (outgoingContent instanceof OutgoingContent.ReadChannelContent) {
            return new StreamRequestBody(outgoingContent.getContentLength(), new Function0() { // from class: io.ktor.client.engine.okhttp.OkHttpEngineKt.convertToOkHttpBody.2
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final ByteReadChannel invoke() {
                    return ((OutgoingContent.ReadChannelContent) outgoingContent).readFrom();
                }
            });
        }
        if (outgoingContent instanceof OutgoingContent.NoContent) {
            return RequestBody.Companion.create(new byte[0], null, 0, 0);
        }
        throw new UnsupportedContentTypeException(outgoingContent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final OkHttpClient.Builder setupTimeoutAttributes(OkHttpClient.Builder builder, HttpTimeout.HttpTimeoutCapabilityConfiguration httpTimeoutCapabilityConfiguration) {
        Long connectTimeoutMillis = httpTimeoutCapabilityConfiguration.getConnectTimeoutMillis();
        if (connectTimeoutMillis != null) {
            builder.connectTimeout(HttpTimeoutKt.convertLongTimeoutToLongWithInfiniteAsZero(connectTimeoutMillis.longValue()), TimeUnit.MILLISECONDS);
        }
        Long socketTimeoutMillis = httpTimeoutCapabilityConfiguration.getSocketTimeoutMillis();
        if (socketTimeoutMillis != null) {
            long jLongValue = socketTimeoutMillis.longValue();
            long jConvertLongTimeoutToLongWithInfiniteAsZero = HttpTimeoutKt.convertLongTimeoutToLongWithInfiniteAsZero(jLongValue);
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            builder.readTimeout(jConvertLongTimeoutToLongWithInfiniteAsZero, timeUnit);
            builder.writeTimeout(HttpTimeoutKt.convertLongTimeoutToLongWithInfiniteAsZero(jLongValue), timeUnit);
        }
        return builder;
    }
}
