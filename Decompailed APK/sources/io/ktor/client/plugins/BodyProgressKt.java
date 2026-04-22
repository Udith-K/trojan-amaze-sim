package io.ktor.client.plugins;

import io.ktor.client.plugins.observer.DelegatedCallKt;
import io.ktor.client.statement.HttpResponse;
import io.ktor.client.utils.ByteChannelUtilsKt;
import io.ktor.http.HttpMessagePropertiesKt;
import io.ktor.util.AttributeKey;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BodyProgress.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class BodyProgressKt {
    private static final AttributeKey UploadProgressListenerAttributeKey = new AttributeKey("UploadProgressListenerAttributeKey");
    private static final AttributeKey DownloadProgressListenerAttributeKey = new AttributeKey("DownloadProgressListenerAttributeKey");

    public static final HttpResponse withObservableDownload(HttpResponse httpResponse, Function3 listener) {
        Intrinsics.checkNotNullParameter(httpResponse, "<this>");
        Intrinsics.checkNotNullParameter(listener, "listener");
        return DelegatedCallKt.wrapWithContent(httpResponse.getCall(), ByteChannelUtilsKt.observable(httpResponse.getContent(), httpResponse.getCoroutineContext(), HttpMessagePropertiesKt.contentLength(httpResponse), listener)).getResponse();
    }
}
