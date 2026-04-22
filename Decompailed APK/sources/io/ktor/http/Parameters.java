package io.ktor.http;

import io.ktor.util.StringValues;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Parameters.kt */
/* JADX INFO: loaded from: classes.dex */
public interface Parameters extends StringValues {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: Parameters.kt */
    public static final class DefaultImpls {
        public static void forEach(Parameters parameters, Function2 body) {
            Intrinsics.checkNotNullParameter(body, "body");
            StringValues.DefaultImpls.forEach(parameters, body);
        }
    }

    /* JADX INFO: compiled from: Parameters.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final Parameters Empty = EmptyParameters.INSTANCE;

        private Companion() {
        }

        public final Parameters getEmpty() {
            return Empty;
        }
    }
}
