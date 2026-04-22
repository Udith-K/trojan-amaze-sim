package io.ktor.client.plugins;

import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestPipeline;
import io.ktor.client.statement.HttpResponseContainer;
import io.ktor.client.statement.HttpResponsePipeline;
import io.ktor.http.ContentType;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpMessageBuilder;
import io.ktor.http.HttpMessagePropertiesKt;
import io.ktor.http.content.OutgoingContent;
import io.ktor.http.content.TextContent;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.utils.io.ByteReadChannel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.slf4j.Logger;

/* JADX INFO: compiled from: DefaultTransform.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class DefaultTransformKt {
    private static final Logger LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.defaultTransformers");

    /* JADX INFO: renamed from: io.ktor.client.plugins.DefaultTransformKt$defaultTransformers$1, reason: invalid class name */
    /* JADX INFO: compiled from: DefaultTransform.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function3 {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        AnonymousClass1(Continuation continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(PipelineContext pipelineContext, Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(continuation);
            anonymousClass1.L$0 = pipelineContext;
            anonymousClass1.L$1 = obj;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            OutgoingContent outgoingContentPlatformRequestDefaultTransform;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final PipelineContext pipelineContext = (PipelineContext) this.L$0;
                final Object obj2 = this.L$1;
                HeadersBuilder headers = ((HttpRequestBuilder) pipelineContext.getContext()).getHeaders();
                HttpHeaders httpHeaders = HttpHeaders.INSTANCE;
                if (headers.get(httpHeaders.getAccept()) == null) {
                    ((HttpRequestBuilder) pipelineContext.getContext()).getHeaders().append(httpHeaders.getAccept(), "*/*");
                }
                final ContentType contentType = HttpMessagePropertiesKt.contentType((HttpMessageBuilder) pipelineContext.getContext());
                if (obj2 instanceof String) {
                    String str = (String) obj2;
                    if (contentType == null) {
                        contentType = ContentType.Text.INSTANCE.getPlain();
                    }
                    outgoingContentPlatformRequestDefaultTransform = new TextContent(str, contentType, null, 4, null);
                } else if (obj2 instanceof byte[]) {
                    outgoingContentPlatformRequestDefaultTransform = new OutgoingContent.ByteArrayContent(contentType, obj2) { // from class: io.ktor.client.plugins.DefaultTransformKt$defaultTransformers$1$content$1
                        final /* synthetic */ Object $body;
                        private final long contentLength;
                        private final ContentType contentType;

                        {
                            this.$body = obj2;
                            this.contentType = contentType == null ? ContentType.Application.INSTANCE.getOctetStream() : contentType;
                            this.contentLength = ((byte[]) obj2).length;
                        }

                        @Override // io.ktor.http.content.OutgoingContent
                        public ContentType getContentType() {
                            return this.contentType;
                        }

                        @Override // io.ktor.http.content.OutgoingContent
                        public Long getContentLength() {
                            return Long.valueOf(this.contentLength);
                        }

                        @Override // io.ktor.http.content.OutgoingContent.ByteArrayContent
                        public byte[] bytes() {
                            return (byte[]) this.$body;
                        }
                    };
                } else if (obj2 instanceof ByteReadChannel) {
                    outgoingContentPlatformRequestDefaultTransform = new OutgoingContent.ReadChannelContent(pipelineContext, contentType, obj2) { // from class: io.ktor.client.plugins.DefaultTransformKt$defaultTransformers$1$content$2
                        final /* synthetic */ Object $body;
                        private final Long contentLength;
                        private final ContentType contentType;

                        {
                            this.$body = obj2;
                            String str2 = ((HttpRequestBuilder) pipelineContext.getContext()).getHeaders().get(HttpHeaders.INSTANCE.getContentLength());
                            this.contentLength = str2 != null ? Long.valueOf(Long.parseLong(str2)) : null;
                            this.contentType = contentType == null ? ContentType.Application.INSTANCE.getOctetStream() : contentType;
                        }

                        @Override // io.ktor.http.content.OutgoingContent
                        public Long getContentLength() {
                            return this.contentLength;
                        }

                        @Override // io.ktor.http.content.OutgoingContent
                        public ContentType getContentType() {
                            return this.contentType;
                        }

                        @Override // io.ktor.http.content.OutgoingContent.ReadChannelContent
                        public ByteReadChannel readFrom() {
                            return (ByteReadChannel) this.$body;
                        }
                    };
                } else {
                    outgoingContentPlatformRequestDefaultTransform = obj2 instanceof OutgoingContent ? (OutgoingContent) obj2 : DefaultTransformersJvmKt.platformRequestDefaultTransform(contentType, (HttpRequestBuilder) pipelineContext.getContext(), obj2);
                }
                if ((outgoingContentPlatformRequestDefaultTransform != null ? outgoingContentPlatformRequestDefaultTransform.getContentType() : null) != null) {
                    ((HttpRequestBuilder) pipelineContext.getContext()).getHeaders().remove(httpHeaders.getContentType());
                    DefaultTransformKt.LOGGER.trace("Transformed with default transformers request body for " + ((HttpRequestBuilder) pipelineContext.getContext()).getUrl() + " from " + Reflection.getOrCreateKotlinClass(obj2.getClass()));
                    this.L$0 = null;
                    this.label = 1;
                    if (pipelineContext.proceedWith(outgoingContentPlatformRequestDefaultTransform, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public static final void defaultTransformers(HttpClient httpClient) {
        Intrinsics.checkNotNullParameter(httpClient, "<this>");
        httpClient.getRequestPipeline().intercept(HttpRequestPipeline.Phases.getRender(), new AnonymousClass1(null));
        httpClient.getResponsePipeline().intercept(HttpResponsePipeline.Phases.getParse(), new AnonymousClass2(null));
        DefaultTransformersJvmKt.platformResponseDefaultTransformers(httpClient);
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.DefaultTransformKt$defaultTransformers$2, reason: invalid class name */
    /* JADX INFO: compiled from: DefaultTransform.kt */
    static final class AnonymousClass2 extends SuspendLambda implements Function3 {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        Object L$2;
        Object L$3;
        int label;

        AnonymousClass2(Continuation continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(PipelineContext pipelineContext, HttpResponseContainer httpResponseContainer, Continuation continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(continuation);
            anonymousClass2.L$0 = pipelineContext;
            anonymousClass2.L$1 = httpResponseContainer;
            return anonymousClass2.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:35:0x015e A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:36:0x015f  */
        /* JADX WARN: Removed duplicated region for block: B:50:0x01b3 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:51:0x01b4  */
        /* JADX WARN: Removed duplicated region for block: B:63:0x01f7  */
        /* JADX WARN: Removed duplicated region for block: B:72:0x0228  */
        /* JADX WARN: Removed duplicated region for block: B:75:0x022c  */
        /* JADX WARN: Removed duplicated region for block: B:79:0x0264 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:80:0x0265  */
        /* JADX WARN: Removed duplicated region for block: B:99:0x02f7  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r17) {
            /*
                Method dump skipped, instruction units count: 836
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.DefaultTransformKt.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }
}
