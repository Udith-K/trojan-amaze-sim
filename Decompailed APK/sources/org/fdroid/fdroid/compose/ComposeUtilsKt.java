package org.fdroid.fdroid.compose;

import android.content.Context;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import com.google.android.material.color.MaterialColors;
import kotlin.Metadata;

/* JADX INFO: compiled from: ComposeUtils.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u0017\u0010\u0000\u001a\u00020\u00012\b\b\u0001\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"colorAttribute", "Landroidx/compose/ui/graphics/Color;", "attrColor", "", "(ILandroidx/compose/runtime/Composer;I)J", "app_fullRelease"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ComposeUtilsKt {
    public static final long colorAttribute(int i, Composer composer, int i2) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-765379242, i2, -1, "org.fdroid.fdroid.compose.colorAttribute (ComposeUtils.kt:125)");
        }
        long jColor = ColorKt.Color(MaterialColors.getColor((Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext()), i, 0));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return jColor;
    }
}
