package androidx.compose.ui.hapticfeedback;

import android.view.View;
import androidx.compose.ui.hapticfeedback.HapticFeedbackType;

/* JADX INFO: compiled from: PlatformHapticFeedback.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class PlatformHapticFeedback implements HapticFeedback {
    private final View view;

    public PlatformHapticFeedback(View view) {
        this.view = view;
    }

    @Override // androidx.compose.ui.hapticfeedback.HapticFeedback
    /* JADX INFO: renamed from: performHapticFeedback-CdsT49E */
    public void mo1578performHapticFeedbackCdsT49E(int i) {
        HapticFeedbackType.Companion companion = HapticFeedbackType.Companion;
        if (HapticFeedbackType.m1580equalsimpl0(i, companion.m1581getLongPress5zf0vsI())) {
            this.view.performHapticFeedback(0);
        } else if (HapticFeedbackType.m1580equalsimpl0(i, companion.m1582getTextHandleMove5zf0vsI())) {
            this.view.performHapticFeedback(9);
        }
    }
}
