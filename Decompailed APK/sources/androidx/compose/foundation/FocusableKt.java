package androidx.compose.foundation;

import androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectableModifier;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: Focusable.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class FocusableKt {
    private static final FocusableKt$FocusableInNonTouchModeElement$1 FocusableInNonTouchModeElement;
    private static final InspectableModifier focusGroupInspectorInfo;

    public static final Modifier focusable(Modifier modifier, boolean z, MutableInteractionSource mutableInteractionSource) {
        Modifier focusableElement;
        if (z) {
            focusableElement = new FocusableElement(mutableInteractionSource);
        } else {
            focusableElement = Modifier.Companion;
        }
        return modifier.then(focusableElement);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.compose.foundation.FocusableKt$FocusableInNonTouchModeElement$1] */
    static {
        focusGroupInspectorInfo = new InspectableModifier(InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1() { // from class: androidx.compose.foundation.FocusableKt$special$$inlined$debugInspectorInfo$1
            public final void invoke(InspectorInfo inspectorInfo) {
                throw null;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
                invoke((InspectorInfo) null);
                return Unit.INSTANCE;
            }
        } : InspectableValueKt.getNoInspectorInfo());
        FocusableInNonTouchModeElement = new ModifierNodeElement() { // from class: androidx.compose.foundation.FocusableKt$FocusableInNonTouchModeElement$1
            public boolean equals(Object other) {
                return this == other;
            }

            @Override // androidx.compose.ui.node.ModifierNodeElement
            public void update(FocusableInNonTouchMode node) {
            }

            @Override // androidx.compose.ui.node.ModifierNodeElement
            /* JADX INFO: renamed from: create */
            public FocusableInNonTouchMode getNode() {
                return new FocusableInNonTouchMode();
            }

            public int hashCode() {
                return Focusable_androidKt.identityHashCode(this);
            }
        };
    }
}
