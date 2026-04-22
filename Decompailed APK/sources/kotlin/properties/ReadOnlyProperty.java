package kotlin.properties;

import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: Interfaces.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface ReadOnlyProperty {
    Object getValue(Object obj, KProperty kProperty);
}
