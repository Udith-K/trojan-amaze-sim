package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.interaction.DragInteraction$Start;
import androidx.compose.foundation.interaction.FocusInteraction$Focus;
import androidx.compose.foundation.interaction.HoverInteraction$Enter;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.unit.Dp;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Card.kt */
/* JADX INFO: loaded from: classes.dex */
public final class CardElevation {
    private final float defaultElevation;
    private final float disabledElevation;
    private final float draggedElevation;
    private final float focusedElevation;
    private final float hoveredElevation;
    private final float pressedElevation;

    public /* synthetic */ CardElevation(float f, float f2, float f3, float f4, float f5, float f6, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, f5, f6);
    }

    private CardElevation(float f, float f2, float f3, float f4, float f5, float f6) {
        this.defaultElevation = f;
        this.pressedElevation = f2;
        this.focusedElevation = f3;
        this.hoveredElevation = f4;
        this.draggedElevation = f5;
        this.disabledElevation = f6;
    }

    public final State shadowElevation$material3_release(boolean z, InteractionSource interactionSource, Composer composer, int i) {
        composer.startReplaceGroup(-1763481333);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1763481333, i, -1, "androidx.compose.material3.CardElevation.shadowElevation (Card.kt:659)");
        }
        composer.startReplaceGroup(-734838460);
        if (interactionSource != null) {
            composer.endReplaceGroup();
            State stateAnimateElevation = animateElevation(z, interactionSource, composer, i & 1022);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceGroup();
            return stateAnimateElevation;
        }
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Dp.m2414boximpl(this.defaultElevation), null, 2, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        MutableState mutableState = (MutableState) objRememberedValue;
        composer.endReplaceGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return mutableState;
    }

    private final State animateElevation(boolean z, InteractionSource interactionSource, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1421890746, i, -1, "androidx.compose.material3.CardElevation.animateElevation (Card.kt:670)");
        }
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.Companion;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = SnapshotStateKt.mutableStateListOf();
            composer.updateRememberedValue(objRememberedValue);
        }
        SnapshotStateList snapshotStateList = (SnapshotStateList) objRememberedValue;
        boolean z2 = true;
        boolean z3 = (((i & 112) ^ 48) > 32 && composer.changed(interactionSource)) || (i & 48) == 32;
        Object objRememberedValue2 = composer.rememberedValue();
        if (z3 || objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new CardElevation$animateElevation$1$1(interactionSource, snapshotStateList, null);
            composer.updateRememberedValue(objRememberedValue2);
        }
        EffectsKt.LaunchedEffect(interactionSource, (Function2) objRememberedValue2, composer, (i >> 3) & 14);
        Interaction interaction = (Interaction) CollectionsKt.lastOrNull((List) snapshotStateList);
        float f = !z ? this.disabledElevation : interaction instanceof PressInteraction.Press ? this.pressedElevation : interaction instanceof HoverInteraction$Enter ? this.hoveredElevation : interaction instanceof FocusInteraction$Focus ? this.focusedElevation : interaction instanceof DragInteraction$Start ? this.draggedElevation : this.defaultElevation;
        Object objRememberedValue3 = composer.rememberedValue();
        if (objRememberedValue3 == companion.getEmpty()) {
            objRememberedValue3 = new Animatable(Dp.m2414boximpl(f), VectorConvertersKt.getVectorConverter(Dp.Companion), null, null, 12, null);
            composer.updateRememberedValue(objRememberedValue3);
        }
        Animatable animatable = (Animatable) objRememberedValue3;
        Dp dpM2414boximpl = Dp.m2414boximpl(f);
        boolean zChangedInstance = composer.changedInstance(animatable) | composer.changed(f) | ((((i & 14) ^ 6) > 4 && composer.changed(z)) || (i & 6) == 4);
        if ((((i & 896) ^ 384) <= 256 || !composer.changed(this)) && (i & 384) != 256) {
            z2 = false;
        }
        boolean zChangedInstance2 = zChangedInstance | z2 | composer.changedInstance(interaction);
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChangedInstance2 || objRememberedValue4 == companion.getEmpty()) {
            Object cardElevation$animateElevation$2$1 = new CardElevation$animateElevation$2$1(animatable, f, z, this, interaction, null);
            composer.updateRememberedValue(cardElevation$animateElevation$2$1);
            objRememberedValue4 = cardElevation$animateElevation$2$1;
        }
        EffectsKt.LaunchedEffect(dpM2414boximpl, (Function2) objRememberedValue4, composer, 0);
        State stateAsState = animatable.asState();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return stateAsState;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CardElevation)) {
            return false;
        }
        CardElevation cardElevation = (CardElevation) obj;
        return Dp.m2418equalsimpl0(this.defaultElevation, cardElevation.defaultElevation) && Dp.m2418equalsimpl0(this.pressedElevation, cardElevation.pressedElevation) && Dp.m2418equalsimpl0(this.focusedElevation, cardElevation.focusedElevation) && Dp.m2418equalsimpl0(this.hoveredElevation, cardElevation.hoveredElevation) && Dp.m2418equalsimpl0(this.disabledElevation, cardElevation.disabledElevation);
    }

    public int hashCode() {
        return (((((((Dp.m2419hashCodeimpl(this.defaultElevation) * 31) + Dp.m2419hashCodeimpl(this.pressedElevation)) * 31) + Dp.m2419hashCodeimpl(this.focusedElevation)) * 31) + Dp.m2419hashCodeimpl(this.hoveredElevation)) * 31) + Dp.m2419hashCodeimpl(this.disabledElevation);
    }
}
