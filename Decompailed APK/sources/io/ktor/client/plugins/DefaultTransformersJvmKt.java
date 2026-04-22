package io.ktor.client.plugins;

import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.statement.HttpResponseContainer;
import io.ktor.client.statement.HttpResponseKt;
import io.ktor.client.statement.HttpResponsePipeline;
import io.ktor.http.ContentType;
import io.ktor.http.HttpHeaders;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.util.reflect.TypeInfo;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.jvm.javaio.BlockingKt;
import io.ktor.utils.io.jvm.javaio.ReadingKt;
import java.io.IOException;
import java.io.InputStream;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: DefaultTransformersJvm.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class DefaultTransformersJvmKt {

    /* JADX INFO: renamed from: io.ktor.client.plugins.DefaultTransformersJvmKt$platformResponseDefaultTransformers$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DefaultTransformersJvm.kt */
    static final class C01811 extends SuspendLambda implements Function3 {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C01811(Continuation continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(PipelineContext pipelineContext, HttpResponseContainer httpResponseContainer, Continuation continuation) {
            C01811 c01811 = new C01811(continuation);
            c01811.L$0 = pipelineContext;
            c01811.L$1 = httpResponseContainer;
            return c01811.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final PipelineContext pipelineContext = (PipelineContext) this.L$0;
                HttpResponseContainer httpResponseContainer = (HttpResponseContainer) this.L$1;
                TypeInfo typeInfoComponent1 = httpResponseContainer.component1();
                Object objComponent2 = httpResponseContainer.component2();
                if (!(objComponent2 instanceof ByteReadChannel)) {
                    return Unit.INSTANCE;
                }
                if (Intrinsics.areEqual(typeInfoComponent1.getType(), Reflection.getOrCreateKotlinClass(InputStream.class))) {
                    final InputStream inputStream = BlockingKt.toInputStream((ByteReadChannel) objComponent2, (Job) ((HttpClientCall) pipelineContext.getContext()).getCoroutineContext().get(Job.Key));
                    HttpResponseContainer httpResponseContainer2 = new HttpResponseContainer(typeInfoComponent1, new InputStream() { // from class: io.ktor.client.plugins.DefaultTransformersJvmKt$platformResponseDefaultTransformers$1$response$1
                        @Override // java.io.InputStream
                        public int read() {
                            return inputStream.read();
                        }

                        @Override // java.io.InputStream
                        public int read(byte[] b, int i2, int i3) {
                            Intrinsics.checkNotNullParameter(b, "b");
                            return inputStream.read(b, i2, i3);
                        }

                        @Override // java.io.InputStream
                        public int available() {
                            return inputStream.available();
                        }

                        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
                        public void close() throws IOException {
                            super.close();
                            inputStream.close();
                            HttpResponseKt.complete(((HttpClientCall) pipelineContext.getContext()).getResponse());
                        }
                    });
                    this.L$0 = null;
                    this.label = 1;
                    if (pipelineContext.proceedWith(httpResponseContainer2, this) == coroutine_suspended) {
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

    public static final void platformResponseDefaultTransformers(HttpClient httpClient) {
        Intrinsics.checkNotNullParameter(httpClient, "<this>");
        httpClient.getResponsePipeline().intercept(HttpResponsePipeline.Phases.getParse(), new C01811(null));
    }

    public static final OutgoingContent platformRequestDefaultTransform(ContentType contentType, HttpRequestBuilder context, Object body) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(body, "body");
        if (body instanceof InputStream) {
            return new OutgoingContent.ReadChannelContent(context, contentType, body) { // from class: io.ktor.client.plugins.DefaultTransformersJvmKt.platformRequestDefaultTransform.1
                final /* synthetic */ Object $body;
                private final Long contentLength;
                private final ContentType contentType;

                {
                    this.$body = body;
                    String str = context.getHeaders().get(HttpHeaders.INSTANCE.getContentLength());
                    this.contentLength = str != null ? Long.valueOf(Long.parseLong(str)) : null;
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
                    return ReadingKt.toByteReadChannelWithArrayPool$default((InputStream) this.$body, null, null, 3, null);
                }
            };
        }
        return null;
    }
}
