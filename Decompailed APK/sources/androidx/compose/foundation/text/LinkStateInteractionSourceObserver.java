package androidx.compose.foundation.text;

import androidx.collection.MutableObjectList;
import androidx.compose.foundation.interaction.FocusInteraction$Focus;
import androidx.compose.foundation.interaction.FocusInteraction$Unfocus;
import androidx.compose.foundation.interaction.HoverInteraction$Enter;
import androidx.compose.foundation.interaction.HoverInteraction$Exit;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.SnapshotIntStateKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: LinkStateInteractionSourceObserver.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LinkStateInteractionSourceObserver {
    private final int Focused = 1;
    private final int Hovered = 2;
    private final int Pressed = 4;
    private final MutableIntState interactionState = SnapshotIntStateKt.mutableIntStateOf(0);

    public final boolean isFocused() {
        return (this.interactionState.getIntValue() & this.Focused) != 0;
    }

    public final boolean isHovered() {
        return (this.interactionState.getIntValue() & this.Hovered) != 0;
    }

    public final boolean isPressed() {
        return (this.interactionState.getIntValue() & this.Pressed) != 0;
    }

    public final Object collectInteractionsForLinks(InteractionSource interactionSource, Continuation continuation) {
        final MutableObjectList mutableObjectList = new MutableObjectList(0, 1, null);
        Object objCollect = interactionSource.getInteractions().collect(new FlowCollector() { // from class: androidx.compose.foundation.text.LinkStateInteractionSourceObserver.collectInteractionsForLinks.2
            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Interaction interaction, Continuation continuation2) {
                int i;
                if (interaction instanceof HoverInteraction$Enter ? true : interaction instanceof FocusInteraction$Focus ? true : interaction instanceof PressInteraction.Press) {
                    mutableObjectList.add(interaction);
                } else if (interaction instanceof HoverInteraction$Exit) {
                    mutableObjectList.remove(((HoverInteraction$Exit) interaction).getEnter());
                } else if (interaction instanceof FocusInteraction$Unfocus) {
                    mutableObjectList.remove(((FocusInteraction$Unfocus) interaction).getFocus());
                } else if (interaction instanceof PressInteraction.Release) {
                    mutableObjectList.remove(((PressInteraction.Release) interaction).getPress());
                } else if (interaction instanceof PressInteraction.Cancel) {
                    mutableObjectList.remove(((PressInteraction.Cancel) interaction).getPress());
                }
                MutableObjectList mutableObjectList2 = mutableObjectList;
                LinkStateInteractionSourceObserver linkStateInteractionSourceObserver = this;
                Object[] objArr = mutableObjectList2.content;
                int i2 = mutableObjectList2._size;
                int i3 = 0;
                for (int i4 = 0; i4 < i2; i4++) {
                    Interaction interaction2 = (Interaction) objArr[i4];
                    if (interaction2 instanceof HoverInteraction$Enter) {
                        i = linkStateInteractionSourceObserver.Hovered;
                    } else if (interaction2 instanceof FocusInteraction$Focus) {
                        i = linkStateInteractionSourceObserver.Focused;
                    } else if (interaction2 instanceof PressInteraction.Press) {
                        i = linkStateInteractionSourceObserver.Pressed;
                    }
                    i3 |= i;
                }
                this.interactionState.setIntValue(i3);
                return Unit.INSTANCE;
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }
}
