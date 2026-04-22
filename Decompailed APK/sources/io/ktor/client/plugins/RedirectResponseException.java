package io.ktor.client.plugins;

import ch.qos.logback.core.CoreConstants;
import io.ktor.client.statement.HttpResponse;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultResponseValidation.kt */
/* JADX INFO: loaded from: classes.dex */
public final class RedirectResponseException extends ResponseException {
    private final String message;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RedirectResponseException(HttpResponse response, String cachedResponseText) {
        super(response, cachedResponseText);
        Intrinsics.checkNotNullParameter(response, "response");
        Intrinsics.checkNotNullParameter(cachedResponseText, "cachedResponseText");
        this.message = "Unhandled redirect: " + response.getCall().getRequest().getMethod().getValue() + ' ' + response.getCall().getRequest().getUrl() + ". Status: " + response.getStatus() + ". Text: \"" + cachedResponseText + CoreConstants.DOUBLE_QUOTE_CHAR;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }
}
