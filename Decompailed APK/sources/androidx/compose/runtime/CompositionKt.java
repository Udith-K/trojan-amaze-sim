package androidx.compose.runtime;

import androidx.collection.MutableIntList;
import java.util.List;

/* JADX INFO: compiled from: Composition.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class CompositionKt {
    private static final Object PendingApplyNoModifications = new Object();
    private static final CompositionServiceKey CompositionImplServiceKey = new CompositionServiceKey() { // from class: androidx.compose.runtime.CompositionKt$CompositionImplServiceKey$1
    };

    public static final Composition Composition(Applier applier, CompositionContext compositionContext) {
        return new CompositionImpl(compositionContext, applier, null, 4, null);
    }

    public static final ReusableComposition ReusableComposition(Applier applier, CompositionContext compositionContext) {
        return new CompositionImpl(compositionContext, applier, null, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void swap(List list, int i, int i2) {
        Object obj = list.get(i);
        list.set(i, list.get(i2));
        list.set(i2, obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void swap(MutableIntList mutableIntList, int i, int i2) {
        int i3 = mutableIntList.get(i);
        mutableIntList.set(i, mutableIntList.get(i2));
        mutableIntList.set(i2, i3);
    }
}
