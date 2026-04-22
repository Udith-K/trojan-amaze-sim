package androidx.compose.foundation.lazy.layout;

import androidx.compose.ui.layout.SubcomposeSlotReusePolicy;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LazyLayout.kt */
/* JADX INFO: loaded from: classes.dex */
final class LazyLayoutItemReusePolicy implements SubcomposeSlotReusePolicy {
    private final Map countPerType = new LinkedHashMap();
    private final LazyLayoutItemContentFactory factory;

    public LazyLayoutItemReusePolicy(LazyLayoutItemContentFactory lazyLayoutItemContentFactory) {
        this.factory = lazyLayoutItemContentFactory;
    }

    @Override // androidx.compose.ui.layout.SubcomposeSlotReusePolicy
    public void getSlotsToRetain(SubcomposeSlotReusePolicy.SlotIdsSet slotIdsSet) {
        this.countPerType.clear();
        Iterator it = slotIdsSet.iterator();
        while (it.hasNext()) {
            Object contentType = this.factory.getContentType(it.next());
            Integer num = (Integer) this.countPerType.get(contentType);
            int iIntValue = num != null ? num.intValue() : 0;
            if (iIntValue == 7) {
                it.remove();
            } else {
                this.countPerType.put(contentType, Integer.valueOf(iIntValue + 1));
            }
        }
    }

    @Override // androidx.compose.ui.layout.SubcomposeSlotReusePolicy
    public boolean areCompatible(Object obj, Object obj2) {
        return Intrinsics.areEqual(this.factory.getContentType(obj), this.factory.getContentType(obj2));
    }
}
