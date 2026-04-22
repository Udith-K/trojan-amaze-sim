package androidx.compose.foundation.lazy;

import androidx.compose.foundation.lazy.layout.NearestRangeKeyIndexMap;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.reflect.KProperty0;

/* JADX INFO: compiled from: LazyListItemProvider.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class LazyListItemProviderKt {
    public static final Function0 rememberLazyListItemProviderLambda(final LazyListState lazyListState, Function1 function1, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-343736148, i, -1, "androidx.compose.foundation.lazy.rememberLazyListItemProviderLambda (LazyListItemProvider.kt:43)");
        }
        final State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function1, composer, (i >> 3) & 14);
        boolean z = (((i & 14) ^ 6) > 4 && composer.changed(lazyListState)) || (i & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.Companion.getEmpty()) {
            final LazyItemScopeImpl lazyItemScopeImpl = new LazyItemScopeImpl();
            final State stateDerivedStateOf = SnapshotStateKt.derivedStateOf(SnapshotStateKt.referentialEqualityPolicy(), new Function0() { // from class: androidx.compose.foundation.lazy.LazyListItemProviderKt$rememberLazyListItemProviderLambda$1$intervalContentState$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final LazyListIntervalContent invoke() {
                    return new LazyListIntervalContent((Function1) stateRememberUpdatedState.getValue());
                }
            });
            final State stateDerivedStateOf2 = SnapshotStateKt.derivedStateOf(SnapshotStateKt.referentialEqualityPolicy(), new Function0() { // from class: androidx.compose.foundation.lazy.LazyListItemProviderKt$rememberLazyListItemProviderLambda$1$itemProviderState$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final LazyListItemProviderImpl invoke() {
                    LazyListIntervalContent lazyListIntervalContent = (LazyListIntervalContent) stateDerivedStateOf.getValue();
                    return new LazyListItemProviderImpl(lazyListState, lazyListIntervalContent, lazyItemScopeImpl, new NearestRangeKeyIndexMap(lazyListState.getNearestRange$foundation_release(), lazyListIntervalContent));
                }
            });
            objRememberedValue = new PropertyReference0Impl(stateDerivedStateOf2) { // from class: androidx.compose.foundation.lazy.LazyListItemProviderKt$rememberLazyListItemProviderLambda$1$1
                @Override // kotlin.reflect.KProperty0
                public Object get() {
                    return ((State) this.receiver).getValue();
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        KProperty0 kProperty0 = (KProperty0) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return kProperty0;
    }
}
