package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.modifier.ModifierLocal;
import androidx.compose.ui.modifier.ModifierLocalReadScope;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BackwardsCompatNode.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class BackwardsCompatNodeKt {
    private static final BackwardsCompatNodeKt$DetachedModifierLocalReadScope$1 DetachedModifierLocalReadScope = new ModifierLocalReadScope() { // from class: androidx.compose.ui.node.BackwardsCompatNodeKt$DetachedModifierLocalReadScope$1
        @Override // androidx.compose.ui.modifier.ModifierLocalReadScope
        public Object getCurrent(ModifierLocal modifierLocal) {
            return modifierLocal.getDefaultFactory$ui_release().invoke();
        }
    };
    private static final Function1 onDrawCacheReadsChanged = new Function1() { // from class: androidx.compose.ui.node.BackwardsCompatNodeKt$onDrawCacheReadsChanged$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((BackwardsCompatNode) obj);
            return Unit.INSTANCE;
        }

        public final void invoke(BackwardsCompatNode backwardsCompatNode) {
            backwardsCompatNode.onDrawCacheReadsChanged$ui_release();
        }
    };
    private static final Function1 updateModifierLocalConsumer = new Function1() { // from class: androidx.compose.ui.node.BackwardsCompatNodeKt$updateModifierLocalConsumer$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((BackwardsCompatNode) obj);
            return Unit.INSTANCE;
        }

        public final void invoke(BackwardsCompatNode backwardsCompatNode) {
            backwardsCompatNode.updateModifierLocalConsumer();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isChainUpdate(BackwardsCompatNode backwardsCompatNode) {
        Modifier.Node tail$ui_release = DelegatableNodeKt.requireLayoutNode(backwardsCompatNode).getNodes$ui_release().getTail$ui_release();
        Intrinsics.checkNotNull(tail$ui_release, "null cannot be cast to non-null type androidx.compose.ui.node.TailModifierNode");
        return ((TailModifierNode) tail$ui_release).getAttachHasBeenRun();
    }
}
