package androidx.compose.ui.graphics;

import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.compose.ui.graphics.colorspace.ColorSpaces;

/* JADX INFO: compiled from: ImageBitmap.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ImageBitmapKt {
    /* JADX INFO: renamed from: ImageBitmap-x__-hDU$default, reason: not valid java name */
    public static /* synthetic */ ImageBitmap m1353ImageBitmapx__hDU$default(int i, int i2, int i3, boolean z, ColorSpace colorSpace, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i3 = ImageBitmapConfig.Companion.m1348getArgb8888_sVssgQ();
        }
        if ((i4 & 8) != 0) {
            z = true;
        }
        if ((i4 & 16) != 0) {
            colorSpace = ColorSpaces.INSTANCE.getSrgb();
        }
        return m1352ImageBitmapx__hDU(i, i2, i3, z, colorSpace);
    }

    /* JADX INFO: renamed from: ImageBitmap-x__-hDU, reason: not valid java name */
    public static final ImageBitmap m1352ImageBitmapx__hDU(int i, int i2, int i3, boolean z, ColorSpace colorSpace) {
        return AndroidImageBitmap_androidKt.m1218ActualImageBitmapx__hDU(i, i2, i3, z, colorSpace);
    }
}
