package androidx.compose.runtime;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ValueHolders.kt */
/* JADX INFO: loaded from: classes.dex */
public final class StaticValueHolder implements ValueHolder {
    private final Object value;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof StaticValueHolder) && Intrinsics.areEqual(this.value, ((StaticValueHolder) obj).value);
    }

    public int hashCode() {
        Object obj = this.value;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public String toString() {
        return "StaticValueHolder(value=" + this.value + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public StaticValueHolder(Object obj) {
        this.value = obj;
    }

    public final Object getValue() {
        return this.value;
    }

    @Override // androidx.compose.runtime.ValueHolder
    public Object readValue(PersistentCompositionLocalMap persistentCompositionLocalMap) {
        return this.value;
    }
}
