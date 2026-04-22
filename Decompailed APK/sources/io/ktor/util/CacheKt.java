package io.ktor.util;

import java.util.Collections;
import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Cache.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class CacheKt {
    public static final Map createLRUCache(Function1 supplier, Function1 close, int i) {
        Intrinsics.checkNotNullParameter(supplier, "supplier");
        Intrinsics.checkNotNullParameter(close, "close");
        Map mapSynchronizedMap = Collections.synchronizedMap(new LRUCache(supplier, close, i));
        Intrinsics.checkNotNullExpressionValue(mapSynchronizedMap, "synchronizedMap(LRUCache…upplier, close, maxSize))");
        return mapSynchronizedMap;
    }
}
