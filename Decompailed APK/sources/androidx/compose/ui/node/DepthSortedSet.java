package androidx.compose.ui.node;

import androidx.compose.ui.internal.InlineClassHelperKt;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DepthSortedSet.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DepthSortedSet {
    private final Comparator DepthComparator;
    private final boolean extraAssertions;
    private final Lazy mapOfOriginalDepth$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: androidx.compose.ui.node.DepthSortedSet$mapOfOriginalDepth$2
        @Override // kotlin.jvm.functions.Function0
        public final Map invoke() {
            return new LinkedHashMap();
        }
    });
    private final TreeSet set;

    public DepthSortedSet(boolean z) {
        this.extraAssertions = z;
        Comparator comparator = new Comparator() { // from class: androidx.compose.ui.node.DepthSortedSet$DepthComparator$1
            @Override // java.util.Comparator
            public int compare(LayoutNode layoutNode, LayoutNode layoutNode2) {
                int iCompare = Intrinsics.compare(layoutNode.getDepth$ui_release(), layoutNode2.getDepth$ui_release());
                return iCompare != 0 ? iCompare : Intrinsics.compare(layoutNode.hashCode(), layoutNode2.hashCode());
            }
        };
        this.DepthComparator = comparator;
        this.set = new TreeSet(comparator);
    }

    private final Map getMapOfOriginalDepth() {
        return (Map) this.mapOfOriginalDepth$delegate.getValue();
    }

    public final boolean contains(LayoutNode layoutNode) {
        boolean zContains = this.set.contains(layoutNode);
        if (this.extraAssertions) {
            if (!(zContains == getMapOfOriginalDepth().containsKey(layoutNode))) {
                InlineClassHelperKt.throwIllegalStateException("inconsistency in TreeSet");
            }
        }
        return zContains;
    }

    public final void add(LayoutNode layoutNode) {
        if (!layoutNode.isAttached()) {
            InlineClassHelperKt.throwIllegalStateException("DepthSortedSet.add called on an unattached node");
        }
        if (this.extraAssertions) {
            Integer num = (Integer) getMapOfOriginalDepth().get(layoutNode);
            if (num == null) {
                getMapOfOriginalDepth().put(layoutNode, Integer.valueOf(layoutNode.getDepth$ui_release()));
            } else {
                if (!(num.intValue() == layoutNode.getDepth$ui_release())) {
                    InlineClassHelperKt.throwIllegalStateException("invalid node depth");
                }
            }
        }
        this.set.add(layoutNode);
    }

    public final boolean remove(LayoutNode layoutNode) {
        if (!layoutNode.isAttached()) {
            InlineClassHelperKt.throwIllegalStateException("DepthSortedSet.remove called on an unattached node");
        }
        boolean zRemove = this.set.remove(layoutNode);
        if (this.extraAssertions) {
            if (!Intrinsics.areEqual((Integer) getMapOfOriginalDepth().remove(layoutNode), zRemove ? Integer.valueOf(layoutNode.getDepth$ui_release()) : null)) {
                InlineClassHelperKt.throwIllegalStateException("invalid node depth");
            }
        }
        return zRemove;
    }

    public final LayoutNode pop() {
        LayoutNode layoutNode = (LayoutNode) this.set.first();
        remove(layoutNode);
        return layoutNode;
    }

    public final boolean isEmpty() {
        return this.set.isEmpty();
    }

    public String toString() {
        return this.set.toString();
    }
}
