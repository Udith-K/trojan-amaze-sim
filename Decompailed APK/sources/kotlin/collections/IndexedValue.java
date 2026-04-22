package kotlin.collections;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IndexedValue.kt */
/* JADX INFO: loaded from: classes.dex */
public final class IndexedValue {
    private final int index;
    private final Object value;

    public final int component1() {
        return this.index;
    }

    public final Object component2() {
        return this.value;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IndexedValue)) {
            return false;
        }
        IndexedValue indexedValue = (IndexedValue) obj;
        return this.index == indexedValue.index && Intrinsics.areEqual(this.value, indexedValue.value);
    }

    public int hashCode() {
        int i = this.index * 31;
        Object obj = this.value;
        return i + (obj == null ? 0 : obj.hashCode());
    }

    public String toString() {
        return "IndexedValue(index=" + this.index + ", value=" + this.value + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public IndexedValue(int i, Object obj) {
        this.index = i;
        this.value = obj;
    }

    public final int getIndex() {
        return this.index;
    }

    public final Object getValue() {
        return this.value;
    }
}
