package io.ktor.http;

import io.ktor.http.URLBuilder;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: URLBuilderJvm.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class URLBuilderJvmKt {
    public static final String getOrigin(URLBuilder.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return "http://localhost";
    }
}
