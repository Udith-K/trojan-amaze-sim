package kotlin.reflect;

import kotlin.jvm.functions.Function1;
import kotlin.reflect.KMutableProperty;

/* JADX INFO: compiled from: KProperty.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface KMutableProperty0 extends KProperty0, KMutableProperty {

    /* JADX INFO: compiled from: KProperty.kt */
    public interface Setter extends KMutableProperty.Setter, Function1 {
    }

    @Override // kotlin.reflect.KMutableProperty
    Setter getSetter();
}
