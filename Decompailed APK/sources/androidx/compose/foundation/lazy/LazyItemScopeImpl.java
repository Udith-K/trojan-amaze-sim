package androidx.compose.foundation.lazy;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.lazy.layout.LazyLayoutAnimateItemElement;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.ui.Modifier;

/* JADX INFO: compiled from: LazyItemScopeImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LazyItemScopeImpl implements LazyItemScope {
    private MutableIntState maxWidthState = SnapshotIntStateKt.mutableIntStateOf(Integer.MAX_VALUE);
    private MutableIntState maxHeightState = SnapshotIntStateKt.mutableIntStateOf(Integer.MAX_VALUE);

    public final void setMaxSize(int i, int i2) {
        this.maxWidthState.setIntValue(i);
        this.maxHeightState.setIntValue(i2);
    }

    @Override // androidx.compose.foundation.lazy.LazyItemScope
    public Modifier animateItem(Modifier modifier, FiniteAnimationSpec finiteAnimationSpec, FiniteAnimationSpec finiteAnimationSpec2, FiniteAnimationSpec finiteAnimationSpec3) {
        return (finiteAnimationSpec == null && finiteAnimationSpec2 == null && finiteAnimationSpec3 == null) ? modifier : modifier.then(new LazyLayoutAnimateItemElement(finiteAnimationSpec, finiteAnimationSpec2, finiteAnimationSpec3));
    }
}
