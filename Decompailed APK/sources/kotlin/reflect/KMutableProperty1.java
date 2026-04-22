package kotlin.reflect;

import kotlin.jvm.functions.Function2;
import kotlin.reflect.KMutableProperty;

/* JADX INFO: compiled from: KProperty.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface KMutableProperty1 extends KProperty1, KMutableProperty {

    /* JADX INFO: compiled from: KProperty.kt */
    public interface Setter extends KMutableProperty.Setter, Function2 {
    }

    @Override // kotlin.reflect.KMutableProperty
    Setter getSetter();
}
