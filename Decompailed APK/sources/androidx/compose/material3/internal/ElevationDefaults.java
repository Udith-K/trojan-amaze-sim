package androidx.compose.material3.internal;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.foundation.interaction.DragInteraction$Start;
import androidx.compose.foundation.interaction.FocusInteraction$Focus;
import androidx.compose.foundation.interaction.HoverInteraction$Enter;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.PressInteraction;

/* JADX INFO: compiled from: Elevation.kt */
/* JADX INFO: loaded from: classes.dex */
final class ElevationDefaults {
    public static final ElevationDefaults INSTANCE = new ElevationDefaults();

    private ElevationDefaults() {
    }

    public final AnimationSpec incomingAnimationSpecForInteraction(Interaction interaction) {
        if ((interaction instanceof PressInteraction.Press) || (interaction instanceof DragInteraction$Start) || (interaction instanceof HoverInteraction$Enter) || (interaction instanceof FocusInteraction$Focus)) {
            return ElevationKt.DefaultIncomingSpec;
        }
        return null;
    }

    public final AnimationSpec outgoingAnimationSpecForInteraction(Interaction interaction) {
        if (!(interaction instanceof PressInteraction.Press) && !(interaction instanceof DragInteraction$Start)) {
            if (interaction instanceof HoverInteraction$Enter) {
                return ElevationKt.HoveredOutgoingSpec;
            }
            if (interaction instanceof FocusInteraction$Focus) {
                return ElevationKt.DefaultOutgoingSpec;
            }
            return null;
        }
        return ElevationKt.DefaultOutgoingSpec;
    }
}
