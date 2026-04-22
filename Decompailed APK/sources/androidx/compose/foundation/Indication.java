package androidx.compose.foundation;

import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;

/* JADX INFO: compiled from: Indication.kt */
/* JADX INFO: loaded from: classes.dex */
public interface Indication {
    IndicationInstance rememberUpdatedInstance(InteractionSource interactionSource, Composer composer, int i);

    /* JADX INFO: renamed from: androidx.compose.foundation.Indication$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Indication.kt */
    public abstract /* synthetic */ class CC {
        public static IndicationInstance $default$rememberUpdatedInstance(Indication indication, InteractionSource interactionSource, Composer composer, int i) {
            composer.startReplaceGroup(1257603829);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1257603829, i, -1, "androidx.compose.foundation.Indication.rememberUpdatedInstance (Indication.kt:75)");
            }
            NoIndicationInstance noIndicationInstance = NoIndicationInstance.INSTANCE;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceGroup();
            return noIndicationInstance;
        }
    }
}
