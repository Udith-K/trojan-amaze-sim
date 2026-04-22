package com.google.accompanist.drawablepainter;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.painter.ColorPainter;
import androidx.compose.ui.graphics.painter.Painter;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DrawablePainter.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class DrawablePainterKt {
    private static final Lazy MAIN_HANDLER$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: com.google.accompanist.drawablepainter.DrawablePainterKt$MAIN_HANDLER$2
        @Override // kotlin.jvm.functions.Function0
        public final Handler invoke() {
            return new Handler(Looper.getMainLooper());
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    public static final Handler getMAIN_HANDLER() {
        return (Handler) MAIN_HANDLER$delegate.getValue();
    }

    public static final Painter rememberDrawablePainter(Drawable drawable, Composer composer, int i) {
        Object drawablePainter;
        composer.startReplaceableGroup(1756822313);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1756822313, i, -1, "com.google.accompanist.drawablepainter.rememberDrawablePainter (DrawablePainter.kt:151)");
        }
        composer.startReplaceableGroup(-1791785024);
        boolean zChanged = composer.changed(drawable);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
            if (drawable == null) {
                objRememberedValue = EmptyPainter.INSTANCE;
            } else {
                if (drawable instanceof ColorDrawable) {
                    drawablePainter = new ColorPainter(ColorKt.Color(((ColorDrawable) drawable).getColor()), null);
                } else {
                    Drawable drawableMutate = drawable.mutate();
                    Intrinsics.checkNotNullExpressionValue(drawableMutate, "mutate(...)");
                    drawablePainter = new DrawablePainter(drawableMutate);
                }
                objRememberedValue = drawablePainter;
            }
            composer.updateRememberedValue(objRememberedValue);
        }
        Painter painter = (Painter) objRememberedValue;
        composer.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return painter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long getIntrinsicSize(Drawable drawable) {
        if (drawable.getIntrinsicWidth() >= 0 && drawable.getIntrinsicHeight() >= 0) {
            return SizeKt.Size(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
        return Size.Companion.m1201getUnspecifiedNHjbRc();
    }
}
