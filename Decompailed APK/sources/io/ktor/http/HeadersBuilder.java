package io.ktor.http;

import io.ktor.util.StringValuesBuilderImpl;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Headers.kt */
/* JADX INFO: loaded from: classes.dex */
public final class HeadersBuilder extends StringValuesBuilderImpl {
    public HeadersBuilder(int i) {
        super(true, i);
    }

    public /* synthetic */ HeadersBuilder(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 8 : i);
    }

    public Headers build() {
        return new HeadersImpl(getValues());
    }

    @Override // io.ktor.util.StringValuesBuilderImpl
    protected void validateName(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        super.validateName(name);
        HttpHeaders.INSTANCE.checkHeaderName(name);
    }

    @Override // io.ktor.util.StringValuesBuilderImpl
    protected void validateValue(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        super.validateValue(value);
        HttpHeaders.INSTANCE.checkHeaderValue(value);
    }
}
