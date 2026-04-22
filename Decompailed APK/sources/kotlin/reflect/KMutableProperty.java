package kotlin.reflect;

import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: KProperty.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface KMutableProperty extends KProperty {

    /* JADX INFO: compiled from: KProperty.kt */
    public interface Setter extends KProperty.Accessor, KFunction {
    }

    Setter getSetter();
}
