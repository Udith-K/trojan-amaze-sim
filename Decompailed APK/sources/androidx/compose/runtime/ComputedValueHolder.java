package androidx.compose.runtime;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ValueHolders.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ComputedValueHolder implements ValueHolder {
    private final Function1 compute;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ComputedValueHolder) && Intrinsics.areEqual(this.compute, ((ComputedValueHolder) obj).compute);
    }

    public int hashCode() {
        return this.compute.hashCode();
    }

    public String toString() {
        return "ComputedValueHolder(compute=" + this.compute + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public ComputedValueHolder(Function1 function1) {
        this.compute = function1;
    }

    public final Function1 getCompute() {
        return this.compute;
    }

    @Override // androidx.compose.runtime.ValueHolder
    public Object readValue(PersistentCompositionLocalMap persistentCompositionLocalMap) {
        return this.compute.invoke(persistentCompositionLocalMap);
    }
}
