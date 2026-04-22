package io.ktor.client.statement;

import ch.qos.logback.core.CoreConstants;
import io.ktor.util.reflect.TypeInfo;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HttpResponsePipeline.kt */
/* JADX INFO: loaded from: classes.dex */
public final class HttpResponseContainer {
    private final TypeInfo expectedType;
    private final Object response;

    public final TypeInfo component1() {
        return this.expectedType;
    }

    public final Object component2() {
        return this.response;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HttpResponseContainer)) {
            return false;
        }
        HttpResponseContainer httpResponseContainer = (HttpResponseContainer) obj;
        return Intrinsics.areEqual(this.expectedType, httpResponseContainer.expectedType) && Intrinsics.areEqual(this.response, httpResponseContainer.response);
    }

    public int hashCode() {
        return (this.expectedType.hashCode() * 31) + this.response.hashCode();
    }

    public String toString() {
        return "HttpResponseContainer(expectedType=" + this.expectedType + ", response=" + this.response + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public HttpResponseContainer(TypeInfo expectedType, Object response) {
        Intrinsics.checkNotNullParameter(expectedType, "expectedType");
        Intrinsics.checkNotNullParameter(response, "response");
        this.expectedType = expectedType;
        this.response = response;
    }

    public final Object getResponse() {
        return this.response;
    }
}
