package androidx.compose.runtime;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: SlotTable.kt */
/* JADX INFO: loaded from: classes.dex */
public final class PrioritySet {
    private final List list;

    public PrioritySet(List list) {
        this.list = list;
    }

    public /* synthetic */ PrioritySet(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ArrayList() : list);
    }

    public final void add(int i) {
        if (!this.list.isEmpty()) {
            if (((Number) this.list.get(0)).intValue() == i) {
                return;
            }
            if (((Number) this.list.get(r0.size() - 1)).intValue() == i) {
                return;
            }
        }
        int size = this.list.size();
        this.list.add(Integer.valueOf(i));
        while (size > 0) {
            int i2 = ((size + 1) >>> 1) - 1;
            int iIntValue = ((Number) this.list.get(i2)).intValue();
            if (i <= iIntValue) {
                break;
            }
            this.list.set(size, Integer.valueOf(iIntValue));
            size = i2;
        }
        this.list.set(size, Integer.valueOf(i));
    }

    public final boolean isNotEmpty() {
        return !this.list.isEmpty();
    }

    public final int peek() {
        return ((Number) CollectionsKt.first(this.list)).intValue();
    }

    public final int takeMax() {
        int iIntValue;
        if (!(this.list.size() > 0)) {
            ComposerKt.composeImmediateRuntimeError("Set is empty");
        }
        int iIntValue2 = ((Number) this.list.get(0)).intValue();
        while (!this.list.isEmpty() && ((Number) this.list.get(0)).intValue() == iIntValue2) {
            List list = this.list;
            list.set(0, CollectionsKt.last(list));
            List list2 = this.list;
            list2.remove(list2.size() - 1);
            int size = this.list.size();
            int size2 = this.list.size() >>> 1;
            int i = 0;
            while (i < size2) {
                int iIntValue3 = ((Number) this.list.get(i)).intValue();
                int i2 = (i + 1) * 2;
                int i3 = i2 - 1;
                int iIntValue4 = ((Number) this.list.get(i3)).intValue();
                if (i2 >= size || (iIntValue = ((Number) this.list.get(i2)).intValue()) <= iIntValue4) {
                    if (iIntValue4 > iIntValue3) {
                        this.list.set(i, Integer.valueOf(iIntValue4));
                        this.list.set(i3, Integer.valueOf(iIntValue3));
                        i = i3;
                    }
                } else if (iIntValue > iIntValue3) {
                    this.list.set(i, Integer.valueOf(iIntValue));
                    this.list.set(i2, Integer.valueOf(iIntValue3));
                    i = i2;
                }
            }
        }
        return iIntValue2;
    }
}
