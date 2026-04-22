package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.lazy.layout.IntervalList;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: LazyLayoutIntervalContent.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class LazyLayoutIntervalContent {

    /* JADX INFO: compiled from: LazyLayoutIntervalContent.kt */
    public interface Interval {
        Function1 getKey();

        Function1 getType();
    }

    public abstract IntervalList getIntervals();

    public final int getItemCount() {
        return getIntervals().getSize();
    }

    public final Object getContentType(int i) {
        IntervalList.Interval interval = getIntervals().get(i);
        return ((Interval) interval.getValue()).getType().invoke(Integer.valueOf(i - interval.getStartIndex()));
    }

    public final Object getKey(int i) {
        Object objInvoke;
        IntervalList.Interval interval = getIntervals().get(i);
        int startIndex = i - interval.getStartIndex();
        Function1 key = ((Interval) interval.getValue()).getKey();
        return (key == null || (objInvoke = key.invoke(Integer.valueOf(startIndex))) == null) ? Lazy_androidKt.getDefaultLazyLayoutKey(i) : objInvoke;
    }
}
