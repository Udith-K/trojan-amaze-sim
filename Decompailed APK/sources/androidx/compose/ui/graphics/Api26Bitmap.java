package androidx.compose.ui.graphics;

import android.graphics.Bitmap;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.compose.ui.graphics.colorspace.ColorSpaces;

/* JADX INFO: compiled from: AndroidImageBitmap.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class Api26Bitmap {
    public static final Api26Bitmap INSTANCE = new Api26Bitmap();

    private Api26Bitmap() {
    }

    public static final ColorSpace composeColorSpace$ui_graphics_release(Bitmap bitmap) {
        ColorSpace composeColorSpace;
        android.graphics.ColorSpace colorSpace = bitmap.getColorSpace();
        return (colorSpace == null || (composeColorSpace = AndroidColorSpace_androidKt.toComposeColorSpace(colorSpace)) == null) ? ColorSpaces.INSTANCE.getSrgb() : composeColorSpace;
    }
}
