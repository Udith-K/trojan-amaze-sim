package kotlin.reflect;

import kotlin.jvm.functions.Function1;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: KProperty.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface KProperty1 extends KProperty, Function1 {

    /* JADX INFO: compiled from: KProperty.kt */
    public interface Getter extends KProperty.Getter, Function1 {
    }

    Object get(Object obj);

    @Override // kotlin.reflect.KProperty
    Getter getGetter();
}
