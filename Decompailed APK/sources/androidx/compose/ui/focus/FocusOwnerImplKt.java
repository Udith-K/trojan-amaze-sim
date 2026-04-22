package androidx.compose.ui.focus;

import androidx.compose.ui.focus.FocusDirection;

/* JADX INFO: compiled from: FocusOwnerImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class FocusOwnerImplKt {
    /* JADX INFO: renamed from: is1dFocusSearch-3ESFkO8, reason: not valid java name */
    public static final boolean m1116is1dFocusSearch3ESFkO8(int i) {
        FocusDirection.Companion companion = FocusDirection.Companion;
        if (FocusDirection.m1091equalsimpl0(i, companion.m1099getNextdhqQ8s())) {
            return true;
        }
        return FocusDirection.m1091equalsimpl0(i, companion.m1100getPreviousdhqQ8s());
    }
}
