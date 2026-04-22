package androidx.compose.foundation;

import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.Key_androidKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNode_androidKt;

/* JADX INFO: compiled from: Clickable.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class Clickable_androidKt {
    private static final long TapIndicationDelay = ViewConfiguration.getTapTimeout();

    public static final boolean isComposeRootInScrollableContainer(DelegatableNode delegatableNode) {
        return isInScrollableViewGroup(DelegatableNode_androidKt.requireView(delegatableNode));
    }

    private static final boolean isInScrollableViewGroup(View view) {
        ViewParent parent = view.getParent();
        while (parent != null && (parent instanceof ViewGroup)) {
            ViewGroup viewGroup = (ViewGroup) parent;
            if (viewGroup.shouldDelayChildPressedState()) {
                return true;
            }
            parent = viewGroup.getParent();
        }
        return false;
    }

    public static final long getTapIndicationDelay() {
        return TapIndicationDelay;
    }

    /* JADX INFO: renamed from: isPress-ZmokQxo, reason: not valid java name */
    public static final boolean m124isPressZmokQxo(KeyEvent keyEvent) {
        return KeyEventType.m1622equalsimpl0(KeyEvent_androidKt.m1627getTypeZmokQxo(keyEvent), KeyEventType.Companion.m1623getKeyDownCS__XNY()) && m123isEnterZmokQxo(keyEvent);
    }

    /* JADX INFO: renamed from: isClick-ZmokQxo, reason: not valid java name */
    public static final boolean m122isClickZmokQxo(KeyEvent keyEvent) {
        return KeyEventType.m1622equalsimpl0(KeyEvent_androidKt.m1627getTypeZmokQxo(keyEvent), KeyEventType.Companion.m1624getKeyUpCS__XNY()) && m123isEnterZmokQxo(keyEvent);
    }

    /* JADX INFO: renamed from: isEnter-ZmokQxo, reason: not valid java name */
    private static final boolean m123isEnterZmokQxo(KeyEvent keyEvent) {
        int iM1632getNativeKeyCodeYVgTNJs = Key_androidKt.m1632getNativeKeyCodeYVgTNJs(KeyEvent_androidKt.m1626getKeyZmokQxo(keyEvent));
        return iM1632getNativeKeyCodeYVgTNJs == 23 || iM1632getNativeKeyCodeYVgTNJs == 66 || iM1632getNativeKeyCodeYVgTNJs == 160;
    }
}
