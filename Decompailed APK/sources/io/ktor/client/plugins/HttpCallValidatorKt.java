package io.ktor.client.plugins;

import io.ktor.client.HttpClientConfig;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.request.HttpRequest;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.http.Headers;
import io.ktor.http.HttpMethod;
import io.ktor.http.Url;
import io.ktor.util.AttributeKey;
import io.ktor.util.Attributes;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;

/* JADX INFO: compiled from: HttpCallValidator.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class HttpCallValidatorKt {
    private static final Logger LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.HttpCallValidator");
    private static final AttributeKey ExpectSuccessAttributeKey = new AttributeKey("ExpectSuccessAttributeKey");

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v0, types: [io.ktor.client.plugins.HttpCallValidatorKt$HttpRequest$1] */
    public static final AnonymousClass1 HttpRequest(HttpRequestBuilder httpRequestBuilder) {
        return new HttpRequest() { // from class: io.ktor.client.plugins.HttpCallValidatorKt.HttpRequest.1
            private final Attributes attributes;
            private final Headers headers;
            private final HttpMethod method;
            private final Url url;

            {
                this.method = this.$builder.getMethod();
                this.url = this.$builder.getUrl().build();
                this.attributes = this.$builder.getAttributes();
                this.headers = this.$builder.getHeaders().build();
            }

            @Override // io.ktor.client.request.HttpRequest, kotlinx.coroutines.CoroutineScope
            public CoroutineContext getCoroutineContext() {
                return HttpRequest.DefaultImpls.getCoroutineContext(this);
            }

            @Override // io.ktor.client.request.HttpRequest
            public HttpClientCall getCall() {
                throw new IllegalStateException("Call is not initialized");
            }

            @Override // io.ktor.client.request.HttpRequest
            public HttpMethod getMethod() {
                return this.method;
            }

            @Override // io.ktor.client.request.HttpRequest
            public Url getUrl() {
                return this.url;
            }

            @Override // io.ktor.client.request.HttpRequest
            public Attributes getAttributes() {
                return this.attributes;
            }

            @Override // io.ktor.http.HttpMessage
            public Headers getHeaders() {
                return this.headers;
            }
        };
    }

    public static final void HttpResponseValidator(HttpClientConfig httpClientConfig, Function1 block) {
        Intrinsics.checkNotNullParameter(httpClientConfig, "<this>");
        Intrinsics.checkNotNullParameter(block, "block");
        httpClientConfig.install(HttpCallValidator.Companion, block);
    }

    public static final AttributeKey getExpectSuccessAttributeKey() {
        return ExpectSuccessAttributeKey;
    }
}
