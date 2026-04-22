package io.ktor.http;

import kotlin.text.StringsKt;

/* JADX INFO: compiled from: HttpHeaders.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class HttpHeadersKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isDelimiter(char c) {
        return StringsKt.contains$default((CharSequence) "\"(),/:;<=>?@[\\]{}", c, false, 2, (Object) null);
    }
}
