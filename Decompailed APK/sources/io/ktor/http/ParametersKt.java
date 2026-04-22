package io.ktor.http;

/* JADX INFO: compiled from: Parameters.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ParametersKt {
    public static final ParametersBuilder ParametersBuilder(int i) {
        return new ParametersBuilderImpl(i);
    }

    public static /* synthetic */ ParametersBuilder ParametersBuilder$default(int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 8;
        }
        return ParametersBuilder(i);
    }
}
