package io.ktor.http;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HttpHeaders.kt */
/* JADX INFO: loaded from: classes.dex */
public final class IllegalHeaderNameException extends IllegalArgumentException {
    private final String headerName;
    private final int position;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IllegalHeaderNameException(String headerName, int i) {
        super("Header name '" + headerName + "' contains illegal character '" + headerName.charAt(i) + "' (code " + (headerName.charAt(i) & 255) + CoreConstants.RIGHT_PARENTHESIS_CHAR);
        Intrinsics.checkNotNullParameter(headerName, "headerName");
        this.headerName = headerName;
        this.position = i;
    }
}
