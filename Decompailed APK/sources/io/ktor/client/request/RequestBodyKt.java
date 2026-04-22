package io.ktor.client.request;

import io.ktor.util.AttributeKey;

/* JADX INFO: compiled from: RequestBody.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class RequestBodyKt {
    private static final AttributeKey BodyTypeAttributeKey = new AttributeKey("BodyTypeAttributeKey");

    public static final AttributeKey getBodyTypeAttributeKey() {
        return BodyTypeAttributeKey;
    }
}
