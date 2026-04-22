package androidx.compose.foundation.selection;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticBackport0;
import androidx.compose.foundation.IndicationNodeFactory;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.semantics.Role;
import ch.qos.logback.core.joran.action.Action;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Toggleable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BI\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0018\u001a\u00020\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0096\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u000f\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001cR\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u001dR\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u001eR\u0016\u0010\b\u001a\u0004\u0018\u00010\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\u001fR\u0014\u0010\t\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u001dR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u000b\u0010 R \u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010!\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\""}, d2 = {"Landroidx/compose/foundation/selection/ToggleableElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/selection/ToggleableNode;", "", Action.VALUE_ATTRIBUTE, "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Landroidx/compose/foundation/IndicationNodeFactory;", "indicationNodeFactory", "enabled", "Landroidx/compose/ui/semantics/Role;", "role", "Lkotlin/Function1;", "", "onValueChange", "<init>", "(ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/IndicationNodeFactory;ZLandroidx/compose/ui/semantics/Role;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "create", "()Landroidx/compose/foundation/selection/ToggleableNode;", "node", "update", "(Landroidx/compose/foundation/selection/ToggleableNode;)V", "", "other", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Z", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "Landroidx/compose/foundation/IndicationNodeFactory;", "Landroidx/compose/ui/semantics/Role;", "Lkotlin/jvm/functions/Function1;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ToggleableElement extends ModifierNodeElement {
    private final boolean enabled;
    private final IndicationNodeFactory indicationNodeFactory;
    private final MutableInteractionSource interactionSource;
    private final Function1 onValueChange;
    private final Role role;
    private final boolean value;

    public /* synthetic */ ToggleableElement(boolean z, MutableInteractionSource mutableInteractionSource, IndicationNodeFactory indicationNodeFactory, boolean z2, Role role, Function1 function1, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, mutableInteractionSource, indicationNodeFactory, z2, role, function1);
    }

    private ToggleableElement(boolean z, MutableInteractionSource mutableInteractionSource, IndicationNodeFactory indicationNodeFactory, boolean z2, Role role, Function1 function1) {
        this.value = z;
        this.interactionSource = mutableInteractionSource;
        this.indicationNodeFactory = indicationNodeFactory;
        this.enabled = z2;
        this.role = role;
        this.onValueChange = function1;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    /* JADX INFO: renamed from: create */
    public ToggleableNode getNode() {
        return new ToggleableNode(this.value, this.interactionSource, this.indicationNodeFactory, this.enabled, this.role, this.onValueChange, null);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(ToggleableNode node) {
        node.m362updateQzZPfjk(this.value, this.interactionSource, this.indicationNodeFactory, this.enabled, this.role, this.onValueChange);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || ToggleableElement.class != other.getClass()) {
            return false;
        }
        ToggleableElement toggleableElement = (ToggleableElement) other;
        return this.value == toggleableElement.value && Intrinsics.areEqual(this.interactionSource, toggleableElement.interactionSource) && Intrinsics.areEqual(this.indicationNodeFactory, toggleableElement.indicationNodeFactory) && this.enabled == toggleableElement.enabled && Intrinsics.areEqual(this.role, toggleableElement.role) && this.onValueChange == toggleableElement.onValueChange;
    }

    public int hashCode() {
        int iM = ChangeSize$$ExternalSyntheticBackport0.m(this.value) * 31;
        MutableInteractionSource mutableInteractionSource = this.interactionSource;
        int iHashCode = (iM + (mutableInteractionSource != null ? mutableInteractionSource.hashCode() : 0)) * 31;
        IndicationNodeFactory indicationNodeFactory = this.indicationNodeFactory;
        int iHashCode2 = (((iHashCode + (indicationNodeFactory != null ? indicationNodeFactory.hashCode() : 0)) * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.enabled)) * 31;
        Role role = this.role;
        return ((iHashCode2 + (role != null ? Role.m2000hashCodeimpl(role.m2002unboximpl()) : 0)) * 31) + this.onValueChange.hashCode();
    }
}
