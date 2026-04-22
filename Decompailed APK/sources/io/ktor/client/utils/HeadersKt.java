package io.ktor.client.utils;

import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: headers.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class HeadersKt {
    public static final Headers buildHeaders(Function1 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        HeadersBuilder headersBuilder = new HeadersBuilder(0, 1, null);
        block.invoke(headersBuilder);
        return headersBuilder.build();
    }
}
