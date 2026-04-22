package io.ktor.client.utils;

import io.ktor.http.content.OutgoingContent;

/* JADX INFO: compiled from: Content.kt */
/* JADX INFO: loaded from: classes.dex */
public final class EmptyContent extends OutgoingContent.NoContent {
    public static final EmptyContent INSTANCE = new EmptyContent();
    private static final long contentLength = 0;

    private EmptyContent() {
    }

    @Override // io.ktor.http.content.OutgoingContent
    public Long getContentLength() {
        return Long.valueOf(contentLength);
    }

    public String toString() {
        return "EmptyContent";
    }
}
