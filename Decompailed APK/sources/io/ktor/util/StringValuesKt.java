package io.ktor.util;

import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StringValues.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class StringValuesKt {
    public static final StringValuesBuilder appendAll(StringValuesBuilder stringValuesBuilder, StringValuesBuilder builder) {
        Intrinsics.checkNotNullParameter(stringValuesBuilder, "<this>");
        Intrinsics.checkNotNullParameter(builder, "builder");
        for (Map.Entry entry : builder.entries()) {
            stringValuesBuilder.appendAll((String) entry.getKey(), (List) entry.getValue());
        }
        return stringValuesBuilder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean entriesEquals(Set set, Set set2) {
        return Intrinsics.areEqual(set, set2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int entriesHashCode(Set set, int i) {
        return (i * 31) + set.hashCode();
    }
}
