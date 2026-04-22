package io.ktor.http;

import io.ktor.util.StringValuesBuilderImpl;

/* JADX INFO: compiled from: Parameters.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ParametersBuilderImpl extends StringValuesBuilderImpl implements ParametersBuilder {
    public ParametersBuilderImpl(int i) {
        super(true, i);
    }

    @Override // io.ktor.http.ParametersBuilder
    public Parameters build() {
        return new ParametersImpl(getValues());
    }
}
