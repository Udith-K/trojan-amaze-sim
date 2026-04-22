package androidx.compose.ui.graphics;

import android.graphics.Bitmap;

/* JADX INFO: compiled from: AndroidImageBitmap.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AndroidImageBitmap implements ImageBitmap {
    private final Bitmap bitmap;

    public AndroidImageBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public final Bitmap getBitmap$ui_graphics_release() {
        return this.bitmap;
    }

    @Override // androidx.compose.ui.graphics.ImageBitmap
    public int getWidth() {
        return this.bitmap.getWidth();
    }

    @Override // androidx.compose.ui.graphics.ImageBitmap
    public int getHeight() {
        return this.bitmap.getHeight();
    }

    @Override // androidx.compose.ui.graphics.ImageBitmap
    /* JADX INFO: renamed from: getConfig-_sVssgQ, reason: not valid java name */
    public int mo1217getConfig_sVssgQ() {
        return AndroidImageBitmap_androidKt.toImageConfig(this.bitmap.getConfig());
    }

    @Override // androidx.compose.ui.graphics.ImageBitmap
    public void prepareToDraw() {
        this.bitmap.prepareToDraw();
    }
}
