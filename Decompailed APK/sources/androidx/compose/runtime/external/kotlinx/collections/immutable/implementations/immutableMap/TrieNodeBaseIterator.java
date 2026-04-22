package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.CommonFunctionsKt;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: PersistentHashMapContentIterators.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class TrieNodeBaseIterator implements Iterator, KMappedMarker {
    private Object[] buffer = TrieNode.Companion.getEMPTY$runtime_release().getBuffer$runtime_release();
    private int dataSize;
    private int index;

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    protected final Object[] getBuffer() {
        return this.buffer;
    }

    protected final int getIndex() {
        return this.index;
    }

    protected final void setIndex(int i) {
        this.index = i;
    }

    public final void reset(Object[] objArr, int i, int i2) {
        this.buffer = objArr;
        this.dataSize = i;
        this.index = i2;
    }

    public final void reset(Object[] objArr, int i) {
        reset(objArr, i, 0);
    }

    public final boolean hasNextKey() {
        return this.index < this.dataSize;
    }

    public final Object currentKey() {
        CommonFunctionsKt.m1053assert(hasNextKey());
        return this.buffer[this.index];
    }

    public final void moveToNextKey() {
        CommonFunctionsKt.m1053assert(hasNextKey());
        this.index += 2;
    }

    public final boolean hasNextNode() {
        CommonFunctionsKt.m1053assert(this.index >= this.dataSize);
        return this.index < this.buffer.length;
    }

    public final TrieNode currentNode() {
        CommonFunctionsKt.m1053assert(hasNextNode());
        Object obj = this.buffer[this.index];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode<K of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNodeBaseIterator, V of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNodeBaseIterator>");
        return (TrieNode) obj;
    }

    public final void moveToNextNode() {
        CommonFunctionsKt.m1053assert(hasNextNode());
        this.index++;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return hasNextKey();
    }
}
