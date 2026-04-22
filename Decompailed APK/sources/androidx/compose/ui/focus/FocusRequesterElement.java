package androidx.compose.ui.focus;

import androidx.compose.ui.node.ModifierNodeElement;
import ch.qos.logback.core.CoreConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FocusRequesterModifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000e\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u001a\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013HÖ\u0003¢\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Landroidx/compose/ui/focus/FocusRequesterElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/ui/focus/FocusRequesterNode;", "Landroidx/compose/ui/focus/FocusRequester;", "focusRequester", "<init>", "(Landroidx/compose/ui/focus/FocusRequester;)V", "create", "()Landroidx/compose/ui/focus/FocusRequesterNode;", "node", "", "update", "(Landroidx/compose/ui/focus/FocusRequesterNode;)V", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroidx/compose/ui/focus/FocusRequester;", "getFocusRequester", "()Landroidx/compose/ui/focus/FocusRequester;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class FocusRequesterElement extends ModifierNodeElement {
    private final FocusRequester focusRequester;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof FocusRequesterElement) && Intrinsics.areEqual(this.focusRequester, ((FocusRequesterElement) other).focusRequester);
    }

    public int hashCode() {
        return this.focusRequester.hashCode();
    }

    public String toString() {
        return "FocusRequesterElement(focusRequester=" + this.focusRequester + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public FocusRequesterElement(FocusRequester focusRequester) {
        this.focusRequester = focusRequester;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    /* JADX INFO: renamed from: create */
    public FocusRequesterNode getNode() {
        return new FocusRequesterNode(this.focusRequester);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(FocusRequesterNode node) {
        node.getFocusRequester().getFocusRequesterNodes$ui_release().remove(node);
        node.setFocusRequester(this.focusRequester);
        node.getFocusRequester().getFocusRequesterNodes$ui_release().add(node);
    }
}
