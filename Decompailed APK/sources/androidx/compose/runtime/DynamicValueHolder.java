package androidx.compose.runtime;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ValueHolders.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DynamicValueHolder implements ValueHolder {
    private final MutableState state;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DynamicValueHolder) && Intrinsics.areEqual(this.state, ((DynamicValueHolder) obj).state);
    }

    public int hashCode() {
        return this.state.hashCode();
    }

    public String toString() {
        return "DynamicValueHolder(state=" + this.state + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public DynamicValueHolder(MutableState mutableState) {
        this.state = mutableState;
    }

    public final MutableState getState() {
        return this.state;
    }

    @Override // androidx.compose.runtime.ValueHolder
    public Object readValue(PersistentCompositionLocalMap persistentCompositionLocalMap) {
        return this.state.getValue();
    }
}
