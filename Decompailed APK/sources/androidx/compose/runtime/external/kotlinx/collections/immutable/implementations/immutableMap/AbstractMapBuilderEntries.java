package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import java.util.Map;
import kotlin.collections.AbstractMutableSet;

/* JADX INFO: compiled from: PersistentHashMapBuilderContentViews.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractMapBuilderEntries extends AbstractMutableSet {
    public abstract boolean containsEntry(Map.Entry entry);

    public abstract boolean removeEntry(Map.Entry entry);

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            return contains((Map.Entry) obj);
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final /* bridge */ boolean remove(Object obj) {
        if (obj instanceof Map.Entry) {
            return remove((Map.Entry) obj);
        }
        return false;
    }

    public final boolean contains(Map.Entry entry) {
        if ((entry instanceof Object ? entry : null) instanceof Map.Entry) {
            return containsEntry(entry);
        }
        return false;
    }

    public final boolean remove(Map.Entry entry) {
        if ((entry instanceof Object ? entry : null) instanceof Map.Entry) {
            return removeEntry(entry);
        }
        return false;
    }
}
