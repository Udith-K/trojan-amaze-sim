package io.ktor.util;

import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AttributesJvm.kt */
/* JADX INFO: loaded from: classes.dex */
final class HashMapAttributes extends AttributesJvmBase {
    private final Map map = new HashMap();

    @Override // io.ktor.util.AttributesJvmBase
    protected Map getMap() {
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
        Object objPut = getMap().put(key, objInvoke);
        if (objPut != null) {
            objInvoke = objPut;
        }
        Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type T of io.ktor.util.HashMapAttributes.computeIfAbsent");
        return objInvoke;
    }
}
