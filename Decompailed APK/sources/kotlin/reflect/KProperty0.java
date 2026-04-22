package kotlin.reflect;

import kotlin.jvm.functions.Function0;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: KProperty.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface KProperty0 extends KProperty, Function0 {

    /* JADX INFO: compiled from: KProperty.kt */
    public interface Getter extends KProperty.Getter, Function0 {
    }

    Object get();

    @Override // kotlin.reflect.KProperty
    Getter getGetter();
}
