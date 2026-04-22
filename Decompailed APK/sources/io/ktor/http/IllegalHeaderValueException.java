package io.ktor.http;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HttpHeaders.kt */
/* JADX INFO: loaded from: classes.dex */
public final class IllegalHeaderValueException extends IllegalArgumentException {
    private final String headerValue;
    private final int position;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IllegalHeaderValueException(String headerValue, int i) {
        super("Header value '" + headerValue + "' contains illegal character '" + headerValue.charAt(i) + "' (code " + (headerValue.charAt(i) & 255) + CoreConstants.RIGHT_PARENTHESIS_CHAR);
        Intrinsics.checkNotNullParameter(headerValue, "headerValue");
        this.headerValue = headerValue;
        this.position = i;
    }
}
