package androidx.compose.foundation.selection;

import androidx.compose.foundation.ClickableNode;
import androidx.compose.foundation.IndicationNodeFactory;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.state.ToggleableStateKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Toggleable.kt */
/* JADX INFO: loaded from: classes.dex */
final class ToggleableNode extends ClickableNode {
    private final Function0 _onClick;
    private Function1 onValueChange;
    private boolean value;

    public /* synthetic */ ToggleableNode(boolean z, MutableInteractionSource mutableInteractionSource, IndicationNodeFactory indicationNodeFactory, boolean z2, Role role, Function1 function1, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, mutableInteractionSource, indicationNodeFactory, z2, role, function1);
    }

    private ToggleableNode(final boolean z, MutableInteractionSource mutableInteractionSource, IndicationNodeFactory indicationNodeFactory, boolean z2, Role role, final Function1 function1) {
        super(mutableInteractionSource, indicationNodeFactory, z2, null, role, new Function0() { // from class: androidx.compose.foundation.selection.ToggleableNode.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m363invoke();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m363invoke() {
                function1.invoke(Boolean.valueOf(!z));
            }
        }, null);
        this.value = z;
        this.onValueChange = function1;
        this._onClick = new Function0() { // from class: androidx.compose.foundation.selection.ToggleableNode$_onClick$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Object invoke() {
                m364invoke();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m364invoke() {
                this.this$0.onValueChange.invoke(Boolean.valueOf(!this.this$0.value));
            }
        };
    }

    /* JADX INFO: renamed from: update-QzZPfjk, reason: not valid java name */
    public final void m362updateQzZPfjk(boolean z, MutableInteractionSource mutableInteractionSource, IndicationNodeFactory indicationNodeFactory, boolean z2, Role role, Function1 function1) {
        if (this.value != z) {
            this.value = z;
            SemanticsModifierNodeKt.invalidateSemantics(this);
        }
        this.onValueChange = function1;
        super.m119updateQzZPfjk(mutableInteractionSource, indicationNodeFactory, z2, (String) null, role, this._onClick);
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    public void applyAdditionalSemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setToggleableState(semanticsPropertyReceiver, ToggleableStateKt.ToggleableState(this.value));
    }
}
