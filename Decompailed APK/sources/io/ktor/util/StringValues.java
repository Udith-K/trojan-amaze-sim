package io.ktor.util;

import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StringValues.kt */
/* JADX INFO: loaded from: classes.dex */
public interface StringValues {
    Set entries();

    void forEach(Function2 function2);

    String get(String str);

    List getAll(String str);

    boolean getCaseInsensitiveName();

    boolean isEmpty();

    Set names();

    /* JADX INFO: compiled from: StringValues.kt */
    public static final class DefaultImpls {
        public static String get(StringValues stringValues, String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            List all = stringValues.getAll(name);
            if (all != null) {
                return (String) kotlin.collections.CollectionsKt.firstOrNull(all);
            }
            return null;
        }

        public static void forEach(StringValues stringValues, Function2 body) {
            Intrinsics.checkNotNullParameter(body, "body");
            for (Map.Entry entry : stringValues.entries()) {
                body.invoke((String) entry.getKey(), (List) entry.getValue());
            }
        }
    }
}
