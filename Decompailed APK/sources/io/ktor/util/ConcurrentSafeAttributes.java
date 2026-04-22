package io.ktor.util;

import java.util.concurrent.ConcurrentHashMap;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AttributesJvm.kt */
/* JADX INFO: loaded from: classes.dex */
final class ConcurrentSafeAttributes extends AttributesJvmBase {
    private final ConcurrentHashMap map = new ConcurrentHashMap();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.ktor.util.AttributesJvmBase
    public ConcurrentHashMap getMap() {
        return this.map;
    }

    @Override // io.ktor.util.Attributes
    public Object computeIfAbsent(AttributeKey key, Function0 block) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(block, "block");
        Object obj = getMap().get(key);
        if (obj != null) {
            return obj;
        }
        Object objInvoke = block.invoke();
        Object objPutIfAbsent = getMap().putIfAbsent(key, objInvoke);
        if (objPutIfAbsent != null) {
            objInvoke = objPutIfAbsent;
        }
        Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type T of io.ktor.util.ConcurrentSafeAttributes.computeIfAbsent");
        return objInvoke;
    }
}
