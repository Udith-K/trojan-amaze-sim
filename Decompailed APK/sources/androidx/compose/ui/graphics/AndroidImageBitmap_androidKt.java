package androidx.compose.ui.graphics;

import android.graphics.Bitmap;
import android.os.Build;
import android.util.DisplayMetrics;
import androidx.compose.ui.graphics.ImageBitmapConfig;
import androidx.compose.ui.graphics.colorspace.ColorSpace;

/* JADX INFO: compiled from: AndroidImageBitmap.android.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AndroidImageBitmap_androidKt {
    public static final ImageBitmap asImageBitmap(Bitmap bitmap) {
        return new AndroidImageBitmap(bitmap);
    }

    /* JADX INFO: renamed from: ActualImageBitmap-x__-hDU, reason: not valid java name */
    public static final ImageBitmap m1218ActualImageBitmapx__hDU(int i, int i2, int i3, boolean z, ColorSpace colorSpace) {
        Bitmap bitmapCreateBitmap;
        Bitmap.Config configM1219toBitmapConfig1JJdX4A = m1219toBitmapConfig1JJdX4A(i3);
        if (Build.VERSION.SDK_INT < 26) {
            bitmapCreateBitmap = Bitmap.createBitmap((DisplayMetrics) null, i, i2, configM1219toBitmapConfig1JJdX4A);
            bitmapCreateBitmap.setHasAlpha(z);
        } else {
            bitmapCreateBitmap = Bitmap.createBitmap((DisplayMetrics) null, i, i2, m1219toBitmapConfig1JJdX4A(i3), z, AndroidColorSpace_androidKt.toAndroidColorSpace(colorSpace));
        }
        return new AndroidImageBitmap(bitmapCreateBitmap);
    }

    public static final Bitmap asAndroidBitmap(ImageBitmap imageBitmap) {
        if (imageBitmap instanceof AndroidImageBitmap) {
            return ((AndroidImageBitmap) imageBitmap).getBitmap$ui_graphics_release();
        }
        throw new UnsupportedOperationException("Unable to obtain android.graphics.Bitmap");
    }

    /* JADX INFO: renamed from: toBitmapConfig-1JJdX4A, reason: not valid java name */
    public static final Bitmap.Config m1219toBitmapConfig1JJdX4A(int i) {
        ImageBitmapConfig.Companion companion = ImageBitmapConfig.Companion;
        if (ImageBitmapConfig.m1343equalsimpl0(i, companion.m1348getArgb8888_sVssgQ())) {
            return Bitmap.Config.ARGB_8888;
        }
        if (ImageBitmapConfig.m1343equalsimpl0(i, companion.m1347getAlpha8_sVssgQ())) {
            return Bitmap.Config.ALPHA_8;
        }
        if (ImageBitmapConfig.m1343equalsimpl0(i, companion.m1351getRgb565_sVssgQ())) {
            return Bitmap.Config.RGB_565;
        }
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 26 && ImageBitmapConfig.m1343equalsimpl0(i, companion.m1349getF16_sVssgQ())) {
            return Bitmap.Config.RGBA_F16;
        }
        if (i2 >= 26 && ImageBitmapConfig.m1343equalsimpl0(i, companion.m1350getGpu_sVssgQ())) {
            return Bitmap.Config.HARDWARE;
        }
        return Bitmap.Config.ARGB_8888;
    }

    public static final int toImageConfig(Bitmap.Config config) {
        if (config == Bitmap.Config.ALPHA_8) {
            return ImageBitmapConfig.Companion.m1347getAlpha8_sVssgQ();
        }
        if (config == Bitmap.Config.RGB_565) {
            return ImageBitmapConfig.Companion.m1351getRgb565_sVssgQ();
        }
        if (config == Bitmap.Config.ARGB_4444) {
            return ImageBitmapConfig.Companion.m1348getArgb8888_sVssgQ();
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 26 && config == Bitmap.Config.RGBA_F16) {
            return ImageBitmapConfig.Companion.m1349getF16_sVssgQ();
        }
        if (i >= 26 && config == Bitmap.Config.HARDWARE) {
            return ImageBitmapConfig.Companion.m1350getGpu_sVssgQ();
        }
        return ImageBitmapConfig.Companion.m1348getArgb8888_sVssgQ();
    }
}
