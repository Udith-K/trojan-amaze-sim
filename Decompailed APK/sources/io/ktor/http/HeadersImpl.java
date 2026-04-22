package io.ktor.http;

import io.ktor.util.StringValuesImpl;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Headers.kt */
/* JADX INFO: loaded from: classes.dex */
public final class HeadersImpl extends StringValuesImpl implements Headers {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HeadersImpl(Map values) {
        super(true, values);
        Intrinsics.checkNotNullParameter(values, "values");
    }

    public String toString() {
        return "Headers " + entries();
    }
}
