package androidx.compose.ui.semantics;

import kotlin.Function;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: SemanticsProperties.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SemanticsActions {
    public static final int $stable;
    private static final SemanticsPropertyKey ClearTextSubstitution;
    private static final SemanticsPropertyKey Collapse;
    private static final SemanticsPropertyKey CopyText;
    private static final SemanticsPropertyKey CustomActions;
    private static final SemanticsPropertyKey CutText;
    private static final SemanticsPropertyKey Dismiss;
    private static final SemanticsPropertyKey Expand;
    private static final SemanticsPropertyKey GetScrollViewportLength;
    private static final SemanticsPropertyKey GetTextLayoutResult;
    public static final SemanticsActions INSTANCE = new SemanticsActions();
    private static final SemanticsPropertyKey InsertTextAtCursor;
    private static final SemanticsPropertyKey OnAutofillText;
    private static final SemanticsPropertyKey OnClick;
    private static final SemanticsPropertyKey OnImeAction;
    private static final SemanticsPropertyKey OnLongClick;
    private static final SemanticsPropertyKey PageDown;
    private static final SemanticsPropertyKey PageLeft;
    private static final SemanticsPropertyKey PageRight;
    private static final SemanticsPropertyKey PageUp;
    private static final SemanticsPropertyKey PasteText;
    private static final SemanticsPropertyKey PerformImeAction;
    private static final SemanticsPropertyKey RequestFocus;
    private static final SemanticsPropertyKey ScrollBy;
    private static final SemanticsPropertyKey ScrollByOffset;
    private static final SemanticsPropertyKey ScrollToIndex;
    private static final SemanticsPropertyKey SetProgress;
    private static final SemanticsPropertyKey SetSelection;
    private static final SemanticsPropertyKey SetText;
    private static final SemanticsPropertyKey SetTextSubstitution;
    private static final SemanticsPropertyKey ShowTextSubstitution;

    private SemanticsActions() {
    }

    public final SemanticsPropertyKey getGetTextLayoutResult() {
        return GetTextLayoutResult;
    }

    public final SemanticsPropertyKey getOnClick() {
        return OnClick;
    }

    public final SemanticsPropertyKey getOnLongClick() {
        return OnLongClick;
    }

    public final SemanticsPropertyKey getScrollBy() {
        return ScrollBy;
    }

    public final SemanticsPropertyKey getScrollByOffset() {
        return ScrollByOffset;
    }

    public final SemanticsPropertyKey getScrollToIndex() {
        return ScrollToIndex;
    }

    public final SemanticsPropertyKey getSetProgress() {
        return SetProgress;
    }

    public final SemanticsPropertyKey getSetSelection() {
        return SetSelection;
    }

    public final SemanticsPropertyKey getSetText() {
        return SetText;
    }

    public final SemanticsPropertyKey getSetTextSubstitution() {
        return SetTextSubstitution;
    }

    public final SemanticsPropertyKey getShowTextSubstitution() {
        return ShowTextSubstitution;
    }

    public final SemanticsPropertyKey getClearTextSubstitution() {
        return ClearTextSubstitution;
    }

    public final SemanticsPropertyKey getInsertTextAtCursor() {
        return InsertTextAtCursor;
    }

    public final SemanticsPropertyKey getOnImeAction() {
        return OnImeAction;
    }

    public final SemanticsPropertyKey getCopyText() {
        return CopyText;
    }

    public final SemanticsPropertyKey getCutText() {
        return CutText;
    }

    public final SemanticsPropertyKey getPasteText() {
        return PasteText;
    }

    public final SemanticsPropertyKey getExpand() {
        return Expand;
    }

    public final SemanticsPropertyKey getCollapse() {
        return Collapse;
    }

    public final SemanticsPropertyKey getDismiss() {
        return Dismiss;
    }

    public final SemanticsPropertyKey getRequestFocus() {
        return RequestFocus;
    }

    public final SemanticsPropertyKey getCustomActions() {
        return CustomActions;
    }

    public final SemanticsPropertyKey getPageUp() {
        return PageUp;
    }

    public final SemanticsPropertyKey getPageLeft() {
        return PageLeft;
    }

    public final SemanticsPropertyKey getPageDown() {
        return PageDown;
    }

    public final SemanticsPropertyKey getPageRight() {
        return PageRight;
    }

    public final SemanticsPropertyKey getGetScrollViewportLength() {
        return GetScrollViewportLength;
    }

    static {
        SemanticsPropertiesKt$ActionPropertyKey$1 semanticsPropertiesKt$ActionPropertyKey$1 = new Function2() { // from class: androidx.compose.ui.semantics.SemanticsPropertiesKt$ActionPropertyKey$1
            @Override // kotlin.jvm.functions.Function2
            public final AccessibilityAction invoke(AccessibilityAction accessibilityAction, AccessibilityAction accessibilityAction2) {
                String label;
                Function action;
                if (accessibilityAction == null || (label = accessibilityAction.getLabel()) == null) {
                    label = accessibilityAction2.getLabel();
                }
                if (accessibilityAction == null || (action = accessibilityAction.getAction()) == null) {
                    action = accessibilityAction2.getAction();
                }
                return new AccessibilityAction(label, action);
            }
        };
        GetTextLayoutResult = SemanticsPropertiesKt.AccessibilityKey("GetTextLayoutResult", semanticsPropertiesKt$ActionPropertyKey$1);
        OnClick = SemanticsPropertiesKt.AccessibilityKey("OnClick", semanticsPropertiesKt$ActionPropertyKey$1);
        OnLongClick = SemanticsPropertiesKt.AccessibilityKey("OnLongClick", semanticsPropertiesKt$ActionPropertyKey$1);
        ScrollBy = SemanticsPropertiesKt.AccessibilityKey("ScrollBy", semanticsPropertiesKt$ActionPropertyKey$1);
        ScrollByOffset = new SemanticsPropertyKey("ScrollByOffset", null, 2, null);
        ScrollToIndex = SemanticsPropertiesKt.AccessibilityKey("ScrollToIndex", semanticsPropertiesKt$ActionPropertyKey$1);
        OnAutofillText = SemanticsPropertiesKt.AccessibilityKey("OnAutofillText", semanticsPropertiesKt$ActionPropertyKey$1);
        SetProgress = SemanticsPropertiesKt.AccessibilityKey("SetProgress", semanticsPropertiesKt$ActionPropertyKey$1);
        SetSelection = SemanticsPropertiesKt.AccessibilityKey("SetSelection", semanticsPropertiesKt$ActionPropertyKey$1);
        SetText = SemanticsPropertiesKt.AccessibilityKey("SetText", semanticsPropertiesKt$ActionPropertyKey$1);
        SetTextSubstitution = SemanticsPropertiesKt.AccessibilityKey("SetTextSubstitution", semanticsPropertiesKt$ActionPropertyKey$1);
        ShowTextSubstitution = SemanticsPropertiesKt.AccessibilityKey("ShowTextSubstitution", semanticsPropertiesKt$ActionPropertyKey$1);
        ClearTextSubstitution = SemanticsPropertiesKt.AccessibilityKey("ClearTextSubstitution", semanticsPropertiesKt$ActionPropertyKey$1);
        InsertTextAtCursor = SemanticsPropertiesKt.AccessibilityKey("InsertTextAtCursor", semanticsPropertiesKt$ActionPropertyKey$1);
        OnImeAction = SemanticsPropertiesKt.AccessibilityKey("PerformImeAction", semanticsPropertiesKt$ActionPropertyKey$1);
        PerformImeAction = SemanticsPropertiesKt.AccessibilityKey("PerformImeAction", semanticsPropertiesKt$ActionPropertyKey$1);
        CopyText = SemanticsPropertiesKt.AccessibilityKey("CopyText", semanticsPropertiesKt$ActionPropertyKey$1);
        CutText = SemanticsPropertiesKt.AccessibilityKey("CutText", semanticsPropertiesKt$ActionPropertyKey$1);
        PasteText = SemanticsPropertiesKt.AccessibilityKey("PasteText", semanticsPropertiesKt$ActionPropertyKey$1);
        Expand = SemanticsPropertiesKt.AccessibilityKey("Expand", semanticsPropertiesKt$ActionPropertyKey$1);
        Collapse = SemanticsPropertiesKt.AccessibilityKey("Collapse", semanticsPropertiesKt$ActionPropertyKey$1);
        Dismiss = SemanticsPropertiesKt.AccessibilityKey("Dismiss", semanticsPropertiesKt$ActionPropertyKey$1);
        RequestFocus = SemanticsPropertiesKt.AccessibilityKey("RequestFocus", semanticsPropertiesKt$ActionPropertyKey$1);
        CustomActions = SemanticsPropertiesKt.AccessibilityKey("CustomActions");
        PageUp = SemanticsPropertiesKt.AccessibilityKey("PageUp", semanticsPropertiesKt$ActionPropertyKey$1);
        PageLeft = SemanticsPropertiesKt.AccessibilityKey("PageLeft", semanticsPropertiesKt$ActionPropertyKey$1);
        PageDown = SemanticsPropertiesKt.AccessibilityKey("PageDown", semanticsPropertiesKt$ActionPropertyKey$1);
        PageRight = SemanticsPropertiesKt.AccessibilityKey("PageRight", semanticsPropertiesKt$ActionPropertyKey$1);
        GetScrollViewportLength = SemanticsPropertiesKt.AccessibilityKey("GetScrollViewportLength", semanticsPropertiesKt$ActionPropertyKey$1);
        $stable = 8;
    }
}
