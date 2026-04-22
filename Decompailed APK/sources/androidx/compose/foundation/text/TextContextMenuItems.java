package androidx.compose.foundation.text;

import android.R;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.res.StringResources_androidKt;

/* JADX INFO: compiled from: ContextMenu.android.kt */
/* JADX INFO: loaded from: classes.dex */
public enum TextContextMenuItems {
    Cut(R.string.cut),
    Copy(R.string.copy),
    Paste(R.string.paste),
    SelectAll(R.string.selectAll);

    private final int stringId;

    TextContextMenuItems(int i) {
        this.stringId = i;
    }

    public final String resolvedString(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-309609081, i, -1, "androidx.compose.foundation.text.TextContextMenuItems.resolvedString (ContextMenu.android.kt:89)");
        }
        String strStringResource = StringResources_androidKt.stringResource(this.stringId, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return strStringResource;
    }
}
