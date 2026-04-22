package androidx.compose.ui.focus;

import android.view.FocusFinder;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.unit.LayoutDirection;

/* JADX INFO: compiled from: FocusInteropUtils.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class FocusInteropUtils_androidKt {
    public static final FocusDirection toFocusDirection(int i) {
        if (i == 1) {
            return FocusDirection.m1088boximpl(FocusDirection.Companion.m1100getPreviousdhqQ8s());
        }
        if (i == 2) {
            return FocusDirection.m1088boximpl(FocusDirection.Companion.m1099getNextdhqQ8s());
        }
        if (i == 17) {
            return FocusDirection.m1088boximpl(FocusDirection.Companion.m1098getLeftdhqQ8s());
        }
        if (i == 33) {
            return FocusDirection.m1088boximpl(FocusDirection.Companion.m1102getUpdhqQ8s());
        }
        if (i == 66) {
            return FocusDirection.m1088boximpl(FocusDirection.Companion.m1101getRightdhqQ8s());
        }
        if (i != 130) {
            return null;
        }
        return FocusDirection.m1088boximpl(FocusDirection.Companion.m1095getDowndhqQ8s());
    }

    /* JADX INFO: renamed from: toAndroidFocusDirection-3ESFkO8, reason: not valid java name */
    public static final Integer m1103toAndroidFocusDirection3ESFkO8(int i) {
        FocusDirection.Companion companion = FocusDirection.Companion;
        if (FocusDirection.m1091equalsimpl0(i, companion.m1102getUpdhqQ8s())) {
            return 33;
        }
        if (FocusDirection.m1091equalsimpl0(i, companion.m1095getDowndhqQ8s())) {
            return 130;
        }
        if (FocusDirection.m1091equalsimpl0(i, companion.m1098getLeftdhqQ8s())) {
            return 17;
        }
        if (FocusDirection.m1091equalsimpl0(i, companion.m1101getRightdhqQ8s())) {
            return 66;
        }
        if (FocusDirection.m1091equalsimpl0(i, companion.m1099getNextdhqQ8s())) {
            return 2;
        }
        return FocusDirection.m1091equalsimpl0(i, companion.m1100getPreviousdhqQ8s()) ? 1 : null;
    }

    public static final LayoutDirection toLayoutDirection(int i) {
        if (i == 0) {
            return LayoutDirection.Ltr;
        }
        if (i != 1) {
            return null;
        }
        return LayoutDirection.Rtl;
    }

    public static final Rect calculateBoundingRect(View view) {
        int[] tempCoordinates = FocusInteropUtils.Companion.getTempCoordinates();
        view.getLocationInWindow(tempCoordinates);
        int i = tempCoordinates[0];
        return new Rect(i, tempCoordinates[1], i + view.getWidth(), tempCoordinates[1] + view.getHeight());
    }

    public static final boolean requestInteropFocus(View view, Integer num, android.graphics.Rect rect) {
        if (num == null) {
            return view.requestFocus();
        }
        if (!(view instanceof ViewGroup)) {
            return view.requestFocus(num.intValue(), rect);
        }
        ViewGroup viewGroup = (ViewGroup) view;
        if (viewGroup.isFocused()) {
            return true;
        }
        if ((!viewGroup.isFocusable() || view.hasFocus()) && !(view instanceof AndroidComposeView)) {
            if (rect != null) {
                View viewFindNextFocusFromRect = FocusFinder.getInstance().findNextFocusFromRect(viewGroup, rect, num.intValue());
                return viewFindNextFocusFromRect != null ? viewFindNextFocusFromRect.requestFocus(num.intValue(), rect) : view.requestFocus(num.intValue(), rect);
            }
            View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(viewGroup, view.hasFocus() ? view.findFocus() : null, num.intValue());
            return viewFindNextFocus != null ? viewFindNextFocus.requestFocus(num.intValue()) : view.requestFocus(num.intValue());
        }
        return view.requestFocus(num.intValue(), rect);
    }
}
