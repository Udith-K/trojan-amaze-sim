package androidx.compose.ui.platform.accessibility;

import androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.semantics.CollectionInfo;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: CollectionInfo.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class CollectionInfo_androidKt {
    public static final void setCollectionInfo(SemanticsNode semanticsNode, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        SemanticsConfiguration config = semanticsNode.getConfig();
        SemanticsProperties semanticsProperties = SemanticsProperties.INSTANCE;
        CollectionInfo collectionInfo = (CollectionInfo) SemanticsConfigurationKt.getOrNull(config, semanticsProperties.getCollectionInfo());
        if (collectionInfo != null) {
            accessibilityNodeInfoCompat.setCollectionInfo(toAccessibilityCollectionInfo(collectionInfo));
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (SemanticsConfigurationKt.getOrNull(semanticsNode.getConfig(), semanticsProperties.getSelectableGroup()) != null) {
            List replacedChildren$ui_release = semanticsNode.getReplacedChildren$ui_release();
            int size = replacedChildren$ui_release.size();
            for (int i = 0; i < size; i++) {
                SemanticsNode semanticsNode2 = (SemanticsNode) replacedChildren$ui_release.get(i);
                if (semanticsNode2.getConfig().contains(SemanticsProperties.INSTANCE.getSelected())) {
                    arrayList.add(semanticsNode2);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        boolean zCalculateIfHorizontallyStacked = calculateIfHorizontallyStacked(arrayList);
        accessibilityNodeInfoCompat.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(zCalculateIfHorizontallyStacked ? 1 : arrayList.size(), zCalculateIfHorizontallyStacked ? arrayList.size() : 1, false, 0));
    }

    public static final void setCollectionItemInfo(SemanticsNode semanticsNode, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        SemanticsConfiguration config = semanticsNode.getConfig();
        SemanticsProperties semanticsProperties = SemanticsProperties.INSTANCE;
        ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(SemanticsConfigurationKt.getOrNull(config, semanticsProperties.getCollectionItemInfo()));
        SemanticsNode parent = semanticsNode.getParent();
        if (parent == null || SemanticsConfigurationKt.getOrNull(parent.getConfig(), semanticsProperties.getSelectableGroup()) == null) {
            return;
        }
        CollectionInfo collectionInfo = (CollectionInfo) SemanticsConfigurationKt.getOrNull(parent.getConfig(), semanticsProperties.getCollectionInfo());
        if ((collectionInfo == null || !isLazyCollection(collectionInfo)) && semanticsNode.getConfig().contains(semanticsProperties.getSelected())) {
            ArrayList arrayList = new ArrayList();
            List replacedChildren$ui_release = parent.getReplacedChildren$ui_release();
            int size = replacedChildren$ui_release.size();
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                SemanticsNode semanticsNode2 = (SemanticsNode) replacedChildren$ui_release.get(i2);
                if (semanticsNode2.getConfig().contains(SemanticsProperties.INSTANCE.getSelected())) {
                    arrayList.add(semanticsNode2);
                    if (semanticsNode2.getLayoutNode$ui_release().getPlaceOrder$ui_release() < semanticsNode.getLayoutNode$ui_release().getPlaceOrder$ui_release()) {
                        i++;
                    }
                }
            }
            if (arrayList.isEmpty()) {
                return;
            }
            boolean zCalculateIfHorizontallyStacked = calculateIfHorizontallyStacked(arrayList);
            AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompatObtain = AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(zCalculateIfHorizontallyStacked ? 0 : i, 1, zCalculateIfHorizontallyStacked ? i : 0, 1, false, ((Boolean) semanticsNode.getConfig().getOrElse(SemanticsProperties.INSTANCE.getSelected(), new Function0() { // from class: androidx.compose.ui.platform.accessibility.CollectionInfo_androidKt$setCollectionItemInfo$itemInfo$1
                @Override // kotlin.jvm.functions.Function0
                public final Boolean invoke() {
                    return Boolean.FALSE;
                }
            })).booleanValue());
            if (collectionItemInfoCompatObtain != null) {
                accessibilityNodeInfoCompat.setCollectionItemInfo(collectionItemInfoCompatObtain);
            }
        }
    }

    public static final boolean hasCollectionInfo(SemanticsNode semanticsNode) {
        SemanticsConfiguration config = semanticsNode.getConfig();
        SemanticsProperties semanticsProperties = SemanticsProperties.INSTANCE;
        return (SemanticsConfigurationKt.getOrNull(config, semanticsProperties.getCollectionInfo()) == null && SemanticsConfigurationKt.getOrNull(semanticsNode.getConfig(), semanticsProperties.getSelectableGroup()) == null) ? false : true;
    }

    private static final boolean calculateIfHorizontallyStacked(List list) {
        List listEmptyList;
        long jM1168unboximpl;
        if (list.size() < 2) {
            return true;
        }
        if (list.size() == 0 || list.size() == 1) {
            listEmptyList = CollectionsKt.emptyList();
        } else {
            listEmptyList = new ArrayList();
            Object obj = list.get(0);
            int lastIndex = CollectionsKt.getLastIndex(list);
            int i = 0;
            while (i < lastIndex) {
                i++;
                Object obj2 = list.get(i);
                SemanticsNode semanticsNode = (SemanticsNode) obj2;
                SemanticsNode semanticsNode2 = (SemanticsNode) obj;
                listEmptyList.add(Offset.m1150boximpl(OffsetKt.Offset(Math.abs(Offset.m1159getXimpl(semanticsNode2.getBoundsInRoot().m1178getCenterF1C5BW0()) - Offset.m1159getXimpl(semanticsNode.getBoundsInRoot().m1178getCenterF1C5BW0())), Math.abs(Offset.m1160getYimpl(semanticsNode2.getBoundsInRoot().m1178getCenterF1C5BW0()) - Offset.m1160getYimpl(semanticsNode.getBoundsInRoot().m1178getCenterF1C5BW0())))));
                obj = obj2;
            }
        }
        if (listEmptyList.size() == 1) {
            jM1168unboximpl = ((Offset) CollectionsKt.first(listEmptyList)).m1168unboximpl();
        } else {
            if (listEmptyList.isEmpty()) {
                throw new UnsupportedOperationException("Empty collection can't be reduced.");
            }
            Object objFirst = CollectionsKt.first(listEmptyList);
            int lastIndex2 = CollectionsKt.getLastIndex(listEmptyList);
            if (1 <= lastIndex2) {
                int i2 = 1;
                while (true) {
                    objFirst = Offset.m1150boximpl(Offset.m1164plusMKHz9U(((Offset) objFirst).m1168unboximpl(), ((Offset) listEmptyList.get(i2)).m1168unboximpl()));
                    if (i2 == lastIndex2) {
                        break;
                    }
                    i2++;
                }
            }
            jM1168unboximpl = ((Offset) objFirst).m1168unboximpl();
        }
        return Offset.m1160getYimpl(jM1168unboximpl) < Offset.m1159getXimpl(jM1168unboximpl);
    }

    private static final boolean isLazyCollection(CollectionInfo collectionInfo) {
        return collectionInfo.getRowCount() < 0 || collectionInfo.getColumnCount() < 0;
    }

    private static final AccessibilityNodeInfoCompat.CollectionInfoCompat toAccessibilityCollectionInfo(CollectionInfo collectionInfo) {
        return AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(collectionInfo.getRowCount(), collectionInfo.getColumnCount(), false, 0);
    }
}
