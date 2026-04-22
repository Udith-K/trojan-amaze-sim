package androidx.compose.ui.semantics;

import androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.compose.ui.autofill.ContentType;
import java.util.Collection;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: SemanticsProperties.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SemanticsProperties {
    public static final SemanticsProperties INSTANCE = new SemanticsProperties();
    private static final SemanticsPropertyKey ContentDescription = SemanticsPropertiesKt.AccessibilityKey("ContentDescription", new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$ContentDescription$1
        @Override // kotlin.jvm.functions.Function2
        public final List invoke(List list, List list2) {
            List mutableList;
            if (list == null || (mutableList = CollectionsKt.toMutableList((Collection) list)) == null) {
                return list2;
            }
            mutableList.addAll(list2);
            return mutableList;
        }
    });
    private static final SemanticsPropertyKey StateDescription = SemanticsPropertiesKt.AccessibilityKey("StateDescription");
    private static final SemanticsPropertyKey ProgressBarRangeInfo = SemanticsPropertiesKt.AccessibilityKey("ProgressBarRangeInfo");
    private static final SemanticsPropertyKey PaneTitle = SemanticsPropertiesKt.AccessibilityKey("PaneTitle", new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$PaneTitle$1
        @Override // kotlin.jvm.functions.Function2
        public final String invoke(String str, String str2) {
            throw new IllegalStateException("merge function called on unmergeable property PaneTitle.");
        }
    });
    private static final SemanticsPropertyKey SelectableGroup = SemanticsPropertiesKt.AccessibilityKey("SelectableGroup");
    private static final SemanticsPropertyKey CollectionInfo = SemanticsPropertiesKt.AccessibilityKey("CollectionInfo");
    private static final SemanticsPropertyKey CollectionItemInfo = SemanticsPropertiesKt.AccessibilityKey("CollectionItemInfo");
    private static final SemanticsPropertyKey Heading = SemanticsPropertiesKt.AccessibilityKey("Heading");
    private static final SemanticsPropertyKey Disabled = SemanticsPropertiesKt.AccessibilityKey("Disabled");
    private static final SemanticsPropertyKey LiveRegion = SemanticsPropertiesKt.AccessibilityKey("LiveRegion");
    private static final SemanticsPropertyKey Focused = SemanticsPropertiesKt.AccessibilityKey("Focused");
    private static final SemanticsPropertyKey IsTraversalGroup = SemanticsPropertiesKt.AccessibilityKey("IsTraversalGroup");
    private static final SemanticsPropertyKey InvisibleToUser = new SemanticsPropertyKey("InvisibleToUser", new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$InvisibleToUser$1
        @Override // kotlin.jvm.functions.Function2
        public final Unit invoke(Unit unit, Unit unit2) {
            return unit;
        }
    });
    private static final SemanticsPropertyKey ContentType = new SemanticsPropertyKey("ContentType", new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$ContentType$1
        public final ContentType invoke(ContentType contentType, ContentType contentType2) {
            return contentType;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
            ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(obj2);
            invoke((ContentType) null, (ContentType) null);
            return null;
        }
    });
    private static final SemanticsPropertyKey ContentDataType = new SemanticsPropertyKey("ContentDataType", new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$ContentDataType$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
            ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(obj2);
            throw null;
        }
    });
    private static final SemanticsPropertyKey TraversalIndex = SemanticsPropertiesKt.AccessibilityKey("TraversalIndex", new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$TraversalIndex$1
        public final Float invoke(Float f, float f2) {
            return f;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke((Float) obj, ((Number) obj2).floatValue());
        }
    });
    private static final SemanticsPropertyKey HorizontalScrollAxisRange = SemanticsPropertiesKt.AccessibilityKey("HorizontalScrollAxisRange");
    private static final SemanticsPropertyKey VerticalScrollAxisRange = SemanticsPropertiesKt.AccessibilityKey("VerticalScrollAxisRange");
    private static final SemanticsPropertyKey IsPopup = SemanticsPropertiesKt.AccessibilityKey("IsPopup", new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$IsPopup$1
        @Override // kotlin.jvm.functions.Function2
        public final Unit invoke(Unit unit, Unit unit2) {
            throw new IllegalStateException("merge function called on unmergeable property IsPopup. A popup should not be a child of a clickable/focusable node.");
        }
    });
    private static final SemanticsPropertyKey IsDialog = SemanticsPropertiesKt.AccessibilityKey("IsDialog", new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$IsDialog$1
        @Override // kotlin.jvm.functions.Function2
        public final Unit invoke(Unit unit, Unit unit2) {
            throw new IllegalStateException("merge function called on unmergeable property IsDialog. A dialog should not be a child of a clickable/focusable node.");
        }
    });
    private static final SemanticsPropertyKey Role = SemanticsPropertiesKt.AccessibilityKey("Role", new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$Role$1
        /* JADX INFO: renamed from: invoke-qtA-w6s, reason: not valid java name */
        public final Role m2013invokeqtAw6s(Role role, int i) {
            return role;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return m2013invokeqtAw6s((Role) obj, ((Role) obj2).m2002unboximpl());
        }
    });
    private static final SemanticsPropertyKey TestTag = new SemanticsPropertyKey("TestTag", false, new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$TestTag$1
        @Override // kotlin.jvm.functions.Function2
        public final String invoke(String str, String str2) {
            return str;
        }
    });
    private static final SemanticsPropertyKey Text = SemanticsPropertiesKt.AccessibilityKey("Text", new Function2() { // from class: androidx.compose.ui.semantics.SemanticsProperties$Text$1
        @Override // kotlin.jvm.functions.Function2
        public final List invoke(List list, List list2) {
            List mutableList;
            if (list == null || (mutableList = CollectionsKt.toMutableList((Collection) list)) == null) {
                return list2;
            }
            mutableList.addAll(list2);
            return mutableList;
        }
    });
    private static final SemanticsPropertyKey TextSubstitution = new SemanticsPropertyKey("TextSubstitution", null, 2, null);
    private static final SemanticsPropertyKey IsShowingTextSubstitution = new SemanticsPropertyKey("IsShowingTextSubstitution", null, 2, null);
    private static final SemanticsPropertyKey EditableText = SemanticsPropertiesKt.AccessibilityKey("EditableText");
    private static final SemanticsPropertyKey TextSelectionRange = SemanticsPropertiesKt.AccessibilityKey("TextSelectionRange");
    private static final SemanticsPropertyKey ImeAction = SemanticsPropertiesKt.AccessibilityKey("ImeAction");
    private static final SemanticsPropertyKey Selected = SemanticsPropertiesKt.AccessibilityKey("Selected");
    private static final SemanticsPropertyKey ToggleableState = SemanticsPropertiesKt.AccessibilityKey("ToggleableState");
    private static final SemanticsPropertyKey Password = SemanticsPropertiesKt.AccessibilityKey("Password");
    private static final SemanticsPropertyKey Error = SemanticsPropertiesKt.AccessibilityKey("Error");
    private static final SemanticsPropertyKey IndexForKey = new SemanticsPropertyKey("IndexForKey", null, 2, null);
    private static final SemanticsPropertyKey IsEditable = new SemanticsPropertyKey("IsEditable", null, 2, null);
    private static final SemanticsPropertyKey MaxTextLength = new SemanticsPropertyKey("MaxTextLength", null, 2, null);
    public static final int $stable = 8;

    private SemanticsProperties() {
    }

    public final SemanticsPropertyKey getContentDescription() {
        return ContentDescription;
    }

    public final SemanticsPropertyKey getStateDescription() {
        return StateDescription;
    }

    public final SemanticsPropertyKey getProgressBarRangeInfo() {
        return ProgressBarRangeInfo;
    }

    public final SemanticsPropertyKey getPaneTitle() {
        return PaneTitle;
    }

    public final SemanticsPropertyKey getSelectableGroup() {
        return SelectableGroup;
    }

    public final SemanticsPropertyKey getCollectionInfo() {
        return CollectionInfo;
    }

    public final SemanticsPropertyKey getCollectionItemInfo() {
        return CollectionItemInfo;
    }

    public final SemanticsPropertyKey getHeading() {
        return Heading;
    }

    public final SemanticsPropertyKey getDisabled() {
        return Disabled;
    }

    public final SemanticsPropertyKey getLiveRegion() {
        return LiveRegion;
    }

    public final SemanticsPropertyKey getFocused() {
        return Focused;
    }

    public final SemanticsPropertyKey getIsTraversalGroup() {
        return IsTraversalGroup;
    }

    public final SemanticsPropertyKey getInvisibleToUser() {
        return InvisibleToUser;
    }

    public final SemanticsPropertyKey getContentType$ui_release() {
        return ContentType;
    }

    public final SemanticsPropertyKey getContentDataType$ui_release() {
        return ContentDataType;
    }

    public final SemanticsPropertyKey getTraversalIndex() {
        return TraversalIndex;
    }

    public final SemanticsPropertyKey getHorizontalScrollAxisRange() {
        return HorizontalScrollAxisRange;
    }

    public final SemanticsPropertyKey getVerticalScrollAxisRange() {
        return VerticalScrollAxisRange;
    }

    public final SemanticsPropertyKey getIsPopup() {
        return IsPopup;
    }

    public final SemanticsPropertyKey getRole() {
        return Role;
    }

    public final SemanticsPropertyKey getTestTag() {
        return TestTag;
    }

    public final SemanticsPropertyKey getText() {
        return Text;
    }

    public final SemanticsPropertyKey getTextSubstitution() {
        return TextSubstitution;
    }

    public final SemanticsPropertyKey getIsShowingTextSubstitution() {
        return IsShowingTextSubstitution;
    }

    public final SemanticsPropertyKey getEditableText() {
        return EditableText;
    }

    public final SemanticsPropertyKey getTextSelectionRange() {
        return TextSelectionRange;
    }

    public final SemanticsPropertyKey getImeAction() {
        return ImeAction;
    }

    public final SemanticsPropertyKey getSelected() {
        return Selected;
    }

    public final SemanticsPropertyKey getToggleableState() {
        return ToggleableState;
    }

    public final SemanticsPropertyKey getPassword() {
        return Password;
    }

    public final SemanticsPropertyKey getError() {
        return Error;
    }

    public final SemanticsPropertyKey getIndexForKey() {
        return IndexForKey;
    }

    public final SemanticsPropertyKey getIsEditable() {
        return IsEditable;
    }

    public final SemanticsPropertyKey getMaxTextLength() {
        return MaxTextLength;
    }
}
