package androidx.compose.foundation;

import androidx.compose.foundation.Indication;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DrawModifierNode;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* JADX INFO: compiled from: Indication.kt */
/* JADX INFO: loaded from: classes.dex */
final class DefaultDebugIndication implements IndicationNodeFactory {
    public static final DefaultDebugIndication INSTANCE = new DefaultDebugIndication();

    public boolean equals(Object obj) {
        return obj == this;
    }

    public int hashCode() {
        return -1;
    }

    @Override // androidx.compose.foundation.Indication
    public /* synthetic */ IndicationInstance rememberUpdatedInstance(InteractionSource interactionSource, Composer composer, int i) {
        return Indication.CC.$default$rememberUpdatedInstance(this, interactionSource, composer, i);
    }

    private DefaultDebugIndication() {
    }

    @Override // androidx.compose.foundation.IndicationNodeFactory
    public DelegatableNode create(InteractionSource interactionSource) {
        return new DefaultDebugIndicationInstance(interactionSource);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: Indication.kt */
    static final class DefaultDebugIndicationInstance extends Modifier.Node implements DrawModifierNode {
        private final InteractionSource interactionSource;
        private boolean isFocused;
        private boolean isHovered;
        private boolean isPressed;

        @Override // androidx.compose.ui.node.DrawModifierNode
        public /* synthetic */ void onMeasureResultChanged() {
            DrawModifierNode.CC.$default$onMeasureResultChanged(this);
        }

        public DefaultDebugIndicationInstance(InteractionSource interactionSource) {
            this.interactionSource = interactionSource;
        }

        @Override // androidx.compose.ui.Modifier.Node
        public void onAttach() {
            BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new DefaultDebugIndication$DefaultDebugIndicationInstance$onAttach$1(this, null), 3, null);
        }

        @Override // androidx.compose.ui.node.DrawModifierNode
        public void draw(ContentDrawScope contentDrawScope) {
            contentDrawScope.drawContent();
            if (this.isPressed) {
                DrawScope.CC.m1506drawRectnJ9OG0$default(contentDrawScope, Color.m1294copywmQWz5c$default(Color.Companion.m1305getBlack0d7_KjU(), 0.3f, 0.0f, 0.0f, 0.0f, 14, null), 0L, contentDrawScope.mo1483getSizeNHjbRc(), 0.0f, null, null, 0, 122, null);
            } else if (this.isHovered || this.isFocused) {
                DrawScope.CC.m1506drawRectnJ9OG0$default(contentDrawScope, Color.m1294copywmQWz5c$default(Color.Companion.m1305getBlack0d7_KjU(), 0.1f, 0.0f, 0.0f, 0.0f, 14, null), 0L, contentDrawScope.mo1483getSizeNHjbRc(), 0.0f, null, null, 0, 122, null);
            }
        }
    }
}
