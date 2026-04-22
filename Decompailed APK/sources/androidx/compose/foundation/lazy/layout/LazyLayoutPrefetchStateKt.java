package androidx.compose.foundation.lazy.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.ConstraintsKt;

/* JADX INFO: compiled from: LazyLayoutPrefetchState.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class LazyLayoutPrefetchStateKt {
    private static final long ZeroConstraints = ConstraintsKt.Constraints$default(0, 0, 0, 0, 5, null);

    public static final Modifier traversablePrefetchState(Modifier modifier, LazyLayoutPrefetchState lazyLayoutPrefetchState) {
        Modifier modifierThen;
        return (lazyLayoutPrefetchState == null || (modifierThen = modifier.then(new TraversablePrefetchStateModifierElement(lazyLayoutPrefetchState))) == null) ? modifier : modifierThen;
    }
}
