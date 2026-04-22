package androidx.compose.foundation.lazy;

import androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;

/* JADX INFO: compiled from: LazyListIntervalContent.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LazyListInterval implements LazyLayoutIntervalContent.Interval {
    private final Function4 item;
    private final Function1 key;
    private final Function1 type;

    public LazyListInterval(Function1 function1, Function1 function12, Function4 function4) {
        this.key = function1;
        this.type = function12;
        this.item = function4;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent.Interval
    public Function1 getKey() {
        return this.key;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent.Interval
    public Function1 getType() {
        return this.type;
    }

    public final Function4 getItem() {
        return this.item;
    }
}
