package androidx.compose.ui.graphics;

import android.graphics.PorterDuffColorFilter;
import android.os.Build;

/* JADX INFO: compiled from: AndroidColorFilter.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AndroidColorFilter_androidKt {
    public static final android.graphics.ColorFilter asAndroidColorFilter(ColorFilter colorFilter) {
        return colorFilter.getNativeColorFilter$ui_graphics_release();
    }

    /* JADX INFO: renamed from: actualTintColorFilter-xETnrds, reason: not valid java name */
    public static final android.graphics.ColorFilter m1216actualTintColorFilterxETnrds(long j, int i) {
        if (Build.VERSION.SDK_INT >= 29) {
            return BlendModeColorFilterHelper.INSTANCE.m1279BlendModeColorFilterxETnrds(j, i);
        }
        return new PorterDuffColorFilter(ColorKt.m1316toArgb8_81llA(j), AndroidBlendMode_androidKt.m1206toPorterDuffModes9anfk8(i));
    }
}
