package io.ktor.util;

import java.util.List;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Attributes.kt */
/* JADX INFO: loaded from: classes.dex */
public interface Attributes {
    Object computeIfAbsent(AttributeKey attributeKey, Function0 function0);

    boolean contains(AttributeKey attributeKey);

    Object get(AttributeKey attributeKey);

    List getAllKeys();

    Object getOrNull(AttributeKey attributeKey);

    void put(AttributeKey attributeKey, Object obj);

    void remove(AttributeKey attributeKey);

    /* JADX INFO: compiled from: Attributes.kt */
    public static final class DefaultImpls {
        public static Object get(Attributes attributes, AttributeKey key) {
            Intrinsics.checkNotNullParameter(key, "key");
            Object orNull = attributes.getOrNull(key);
            if (orNull != null) {
                return orNull;
            }
            throw new IllegalStateException("No instance for key " + key);
        }
    }
}
