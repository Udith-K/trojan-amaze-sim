package androidx.compose.foundation.lazy;

import androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent;
import androidx.compose.foundation.lazy.layout.MutableIntervalList;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;

/* JADX INFO: compiled from: LazyListIntervalContent.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LazyListIntervalContent extends LazyLayoutIntervalContent implements LazyListScope {
    private List _headerIndexes;
    private final MutableIntervalList intervals = new MutableIntervalList();

    public LazyListIntervalContent(Function1 function1) {
        function1.invoke(this);
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent
    public MutableIntervalList getIntervals() {
        return this.intervals;
    }

    public final List getHeaderIndexes() {
        List list = this._headerIndexes;
        return list == null ? CollectionsKt.emptyList() : list;
    }

    @Override // androidx.compose.foundation.lazy.LazyListScope
    public void items(int i, Function1 function1, Function1 function12, Function4 function4) {
        getIntervals().addInterval(i, new LazyListInterval(function1, function12, function4));
    }

    @Override // androidx.compose.foundation.lazy.LazyListScope
    public void item(final Object obj, final Object obj2, final Function3 function3) {
        getIntervals().addInterval(1, new LazyListInterval(obj != null ? new Function1() { // from class: androidx.compose.foundation.lazy.LazyListIntervalContent.item.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj3) {
                return invoke(((Number) obj3).intValue());
            }

            public final Object invoke(int i) {
                return obj;
            }
        } : null, new Function1() { // from class: androidx.compose.foundation.lazy.LazyListIntervalContent.item.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj3) {
                return invoke(((Number) obj3).intValue());
            }

            public final Object invoke(int i) {
                return obj2;
            }
        }, ComposableLambdaKt.composableLambdaInstance(-1010194746, true, new Function4() { // from class: androidx.compose.foundation.lazy.LazyListIntervalContent.item.3
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Object invoke(Object obj3, Object obj4, Object obj5, Object obj6) {
                invoke((LazyItemScope) obj3, ((Number) obj4).intValue(), (Composer) obj5, ((Number) obj6).intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(LazyItemScope lazyItemScope, int i, Composer composer, int i2) {
                if ((i2 & 6) == 0) {
                    i2 |= composer.changed(lazyItemScope) ? 4 : 2;
                }
                if ((i2 & 131) == 130 && composer.getSkipping()) {
                    composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1010194746, i2, -1, "androidx.compose.foundation.lazy.LazyListIntervalContent.item.<anonymous> (LazyListIntervalContent.kt:58)");
                }
                function3.invoke(lazyItemScope, composer, Integer.valueOf(i2 & 14));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        })));
    }
}
