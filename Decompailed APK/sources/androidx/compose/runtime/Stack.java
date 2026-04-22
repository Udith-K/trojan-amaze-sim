package androidx.compose.runtime;

import java.util.ArrayList;

/* JADX INFO: compiled from: Stack.kt */
/* JADX INFO: loaded from: classes.dex */
public final class Stack {
    private final ArrayList backing = new ArrayList();

    public final int getSize() {
        return this.backing.size();
    }

    public final boolean push(Object obj) {
        return this.backing.add(obj);
    }

    public final Object pop() {
        return this.backing.remove(getSize() - 1);
    }

    public final Object peek() {
        return this.backing.get(getSize() - 1);
    }

    public final Object peek(int i) {
        return this.backing.get(i);
    }

    public final boolean isEmpty() {
        return this.backing.isEmpty();
    }

    public final boolean isNotEmpty() {
        return !isEmpty();
    }

    public final void clear() {
        this.backing.clear();
    }

    public final Object[] toArray() {
        int size = this.backing.size();
        Object[] objArr = new Object[size];
        for (int i = 0; i < size; i++) {
            objArr[i] = this.backing.get(i);
        }
        return objArr;
    }
}
