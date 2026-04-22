package com.bumptech.glide.integration.compose;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import androidx.compose.ui.graphics.AndroidImageBitmap_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.painter.BitmapPainter;
import androidx.compose.ui.graphics.painter.ColorPainter;
import androidx.compose.ui.graphics.painter.Painter;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Painter.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class PainterKt {
    public static final Painter toPainter(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
            return new BitmapPainter(AndroidImageBitmap_androidKt.asImageBitmap(bitmap), 0L, 0L, 6, null);
        }
        if (drawable instanceof ColorDrawable) {
            return new ColorPainter(ColorKt.Color(((ColorDrawable) drawable).getColor()), null);
        }
        if (drawable == null) {
            return new ColorPainter(Color.Companion.m1308getTransparent0d7_KjU(), null);
        }
        Drawable drawableMutate = drawable.mutate();
        Intrinsics.checkNotNullExpressionValue(drawableMutate, "mutate()");
        return new DrawablePainter(drawableMutate);
    }
}
