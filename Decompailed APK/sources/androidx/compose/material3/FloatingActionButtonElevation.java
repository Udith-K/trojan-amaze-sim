package androidx.compose.material3;

import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.unit.Dp;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: FloatingActionButton.kt */
/* JADX INFO: loaded from: classes.dex */
public class FloatingActionButtonElevation {
    private final float defaultElevation;
    private final float focusedElevation;
    private final float hoveredElevation;
    private final float pressedElevation;

    public /* synthetic */ FloatingActionButtonElevation(float f, float f2, float f3, float f4, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4);
    }

    private FloatingActionButtonElevation(float f, float f2, float f3, float f4) {
        this.defaultElevation = f;
        this.pressedElevation = f2;
        this.focusedElevation = f3;
        this.hoveredElevation = f4;
    }

    public final State shadowElevation$material3_release(InteractionSource interactionSource, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-424810125, i, -1, "androidx.compose.material3.FloatingActionButtonElevation.shadowElevation (FloatingActionButton.kt:516)");
        }
        State stateAnimateElevation = animateElevation(interactionSource, composer, i & 126);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return stateAnimateElevation;
    }

    /* JADX INFO: renamed from: tonalElevation-D9Ej5fM$material3_release, reason: not valid java name */
    public final float m680tonalElevationD9Ej5fM$material3_release() {
        return this.defaultElevation;
    }

    private final State animateElevation(InteractionSource interactionSource, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1845106002, i, -1, "androidx.compose.material3.FloatingActionButtonElevation.animateElevation (FloatingActionButton.kt:525)");
        }
        int i2 = i & 14;
        int i3 = i2 ^ 6;
        boolean z = (i3 > 4 && composer.changed(interactionSource)) || (i & 6) == 4;
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = new FloatingActionButtonElevationAnimatable(this.defaultElevation, this.pressedElevation, this.hoveredElevation, this.focusedElevation, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        FloatingActionButtonElevationAnimatable floatingActionButtonElevationAnimatable = (FloatingActionButtonElevationAnimatable) objRememberedValue;
        boolean zChangedInstance = composer.changedInstance(floatingActionButtonElevationAnimatable) | ((((i & 112) ^ 48) > 32 && composer.changed(this)) || (i & 48) == 32);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue2 == Composer.Companion.getEmpty()) {
            objRememberedValue2 = new FloatingActionButtonElevation$animateElevation$1$1(floatingActionButtonElevationAnimatable, this, null);
            composer.updateRememberedValue(objRememberedValue2);
        }
        EffectsKt.LaunchedEffect(this, (Function2) objRememberedValue2, composer, (i >> 3) & 14);
        boolean zChangedInstance2 = composer.changedInstance(floatingActionButtonElevationAnimatable) | ((i3 > 4 && composer.changed(interactionSource)) || (i & 6) == 4);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChangedInstance2 || objRememberedValue3 == Composer.Companion.getEmpty()) {
            objRememberedValue3 = new FloatingActionButtonElevation$animateElevation$2$1(interactionSource, floatingActionButtonElevationAnimatable, null);
            composer.updateRememberedValue(objRememberedValue3);
        }
        EffectsKt.LaunchedEffect(interactionSource, (Function2) objRememberedValue3, composer, i2);
        State stateAsState = floatingActionButtonElevationAnimatable.asState();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return stateAsState;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof FloatingActionButtonElevation)) {
            return false;
        }
        FloatingActionButtonElevation floatingActionButtonElevation = (FloatingActionButtonElevation) obj;
        if (Dp.m2418equalsimpl0(this.defaultElevation, floatingActionButtonElevation.defaultElevation) && Dp.m2418equalsimpl0(this.pressedElevation, floatingActionButtonElevation.pressedElevation) && Dp.m2418equalsimpl0(this.focusedElevation, floatingActionButtonElevation.focusedElevation)) {
            return Dp.m2418equalsimpl0(this.hoveredElevation, floatingActionButtonElevation.hoveredElevation);
        }
        return false;
    }

    public int hashCode() {
        return (((((Dp.m2419hashCodeimpl(this.defaultElevation) * 31) + Dp.m2419hashCodeimpl(this.pressedElevation)) * 31) + Dp.m2419hashCodeimpl(this.focusedElevation)) * 31) + Dp.m2419hashCodeimpl(this.hoveredElevation);
    }
}
