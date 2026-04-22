package io.ktor.http.content;

import ch.qos.logback.core.CoreConstants;
import io.ktor.http.ContentType;
import io.ktor.http.ContentTypesKt;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.content.OutgoingContent;
import io.ktor.utils.io.charsets.CharsetJVMKt;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: TextContent.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TextContent extends OutgoingContent.ByteArrayContent {
    private final byte[] bytes;
    private final ContentType contentType;
    private final HttpStatusCode status;
    private final String text;

    public /* synthetic */ TextContent(String str, ContentType contentType, HttpStatusCode httpStatusCode, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, contentType, (i & 4) != 0 ? null : httpStatusCode);
    }

    @Override // io.ktor.http.content.OutgoingContent
    public ContentType getContentType() {
        return this.contentType;
    }

    public TextContent(String text, ContentType contentType, HttpStatusCode httpStatusCode) {
        byte[] bArrEncodeToByteArray;
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(contentType, "contentType");
        this.text = text;
        this.contentType = contentType;
        this.status = httpStatusCode;
        Charset charset = ContentTypesKt.charset(getContentType());
        charset = charset == null ? Charsets.UTF_8 : charset;
        if (Intrinsics.areEqual(charset, Charsets.UTF_8)) {
            bArrEncodeToByteArray = StringsKt.encodeToByteArray(text);
        } else {
            CharsetEncoder charsetEncoderNewEncoder = charset.newEncoder();
            Intrinsics.checkNotNullExpressionValue(charsetEncoderNewEncoder, "charset.newEncoder()");
            bArrEncodeToByteArray = CharsetJVMKt.encodeToByteArray(charsetEncoderNewEncoder, text, 0, text.length());
        }
        this.bytes = bArrEncodeToByteArray;
    }

    @Override // io.ktor.http.content.OutgoingContent
    public Long getContentLength() {
        return Long.valueOf(this.bytes.length);
    }

    @Override // io.ktor.http.content.OutgoingContent.ByteArrayContent
    public byte[] bytes() {
        return this.bytes;
    }

    public String toString() {
        return "TextContent[" + getContentType() + "] \"" + StringsKt.take(this.text, 30) + CoreConstants.DOUBLE_QUOTE_CHAR;
    }
}
