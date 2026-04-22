package androidx.compose.ui.node;

import java.util.Comparator;

/* JADX INFO: compiled from: JvmTreeSet.jvm.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TreeSet extends java.util.TreeSet {
    public /* bridge */ int getSize() {
        return super.size();
    }

    @Override // java.util.TreeSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final /* bridge */ int size() {
        return getSize();
    }

    public TreeSet(Comparator comparator) {
        super(comparator);
    }
}
