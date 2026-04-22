package androidx.compose.ui.node;

import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;

/* JADX INFO: compiled from: PointerInputModifierNode.kt */
/* JADX INFO: loaded from: classes.dex */
public interface PointerInputModifierNode extends DelegatableNode {
    boolean interceptOutOfBoundsChildEvents();

    void onCancelPointerInput();

    void onDensityChange();

    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY */
    void mo84onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pointerEventPass, long j);

    void onViewConfigurationChange();

    boolean sharePointerInputWithSiblings();

    /* JADX INFO: renamed from: androidx.compose.ui.node.PointerInputModifierNode$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: PointerInputModifierNode.kt */
    public abstract /* synthetic */ class CC {
        public static boolean $default$interceptOutOfBoundsChildEvents(PointerInputModifierNode pointerInputModifierNode) {
            return false;
        }

        public static boolean $default$sharePointerInputWithSiblings(PointerInputModifierNode pointerInputModifierNode) {
            return false;
        }
    }
}
