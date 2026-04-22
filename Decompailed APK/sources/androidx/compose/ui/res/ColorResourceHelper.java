package androidx.compose.ui.res;

import android.content.Context;
import androidx.compose.ui.graphics.ColorKt;

/* JADX INFO: compiled from: ColorResources.android.kt */
/* JADX INFO: loaded from: classes.dex */
final class ColorResourceHelper {
    public static final ColorResourceHelper INSTANCE = new ColorResourceHelper();

    private ColorResourceHelper() {
    }

    /* JADX INFO: renamed from: getColor-WaAFU9c, reason: not valid java name */
    public final long m1995getColorWaAFU9c(Context context, int i) {
        return ColorKt.Color(context.getResources().getColor(i, context.getTheme()));
    }
}
