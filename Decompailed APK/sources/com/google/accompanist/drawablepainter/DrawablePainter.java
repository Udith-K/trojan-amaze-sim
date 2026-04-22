package com.google.accompanist.drawablepainter;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.AndroidColorFilter_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;

/* JADX INFO: compiled from: DrawablePainter.kt */
/* JADX INFO: loaded from: classes.dex */
public final class DrawablePainter extends Painter implements RememberObserver {
    private final Lazy callback$delegate;
    private final MutableState drawInvalidateTick$delegate;
    private final Drawable drawable;
    private final MutableState drawableIntrinsicSize$delegate;

    /* JADX INFO: compiled from: DrawablePainter.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LayoutDirection.values().length];
            try {
                iArr[LayoutDirection.Ltr.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LayoutDirection.Rtl.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public final Drawable getDrawable() {
        return this.drawable;
    }

    public DrawablePainter(Drawable drawable) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        this.drawable = drawable;
        this.drawInvalidateTick$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(0, null, 2, null);
        this.drawableIntrinsicSize$delegate = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Size.m1190boximpl(DrawablePainterKt.getIntrinsicSize(drawable)), null, 2, null);
        this.callback$delegate = LazyKt.lazy(new Function0() { // from class: com.google.accompanist.drawablepainter.DrawablePainter$callback$2
            {
                super(0);
            }

            /* JADX WARN: Type inference failed for: r0v0, types: [com.google.accompanist.drawablepainter.DrawablePainter$callback$2$1] */
            @Override // kotlin.jvm.functions.Function0
            public final AnonymousClass1 invoke() {
                final DrawablePainter drawablePainter = this.this$0;
                return new Drawable.Callback() { // from class: com.google.accompanist.drawablepainter.DrawablePainter$callback$2.1
                    @Override // android.graphics.drawable.Drawable.Callback
                    public void invalidateDrawable(Drawable d) {
                        Intrinsics.checkNotNullParameter(d, "d");
                        DrawablePainter drawablePainter2 = drawablePainter;
                        drawablePainter2.setDrawInvalidateTick(drawablePainter2.getDrawInvalidateTick() + 1);
                        DrawablePainter drawablePainter3 = drawablePainter;
                        drawablePainter3.m2594setDrawableIntrinsicSizeuvyYCjk(DrawablePainterKt.getIntrinsicSize(drawablePainter3.getDrawable()));
                    }

                    @Override // android.graphics.drawable.Drawable.Callback
                    public void scheduleDrawable(Drawable d, Runnable what, long j) {
                        Intrinsics.checkNotNullParameter(d, "d");
                        Intrinsics.checkNotNullParameter(what, "what");
                        DrawablePainterKt.getMAIN_HANDLER().postAtTime(what, j);
                    }

                    @Override // android.graphics.drawable.Drawable.Callback
                    public void unscheduleDrawable(Drawable d, Runnable what) {
                        Intrinsics.checkNotNullParameter(d, "d");
                        Intrinsics.checkNotNullParameter(what, "what");
                        DrawablePainterKt.getMAIN_HANDLER().removeCallbacks(what);
                    }
                };
            }
        });
        if (drawable.getIntrinsicWidth() < 0 || drawable.getIntrinsicHeight() < 0) {
            return;
        }
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getDrawInvalidateTick() {
        return ((Number) this.drawInvalidateTick$delegate.getValue()).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDrawInvalidateTick(int i) {
        this.drawInvalidateTick$delegate.setValue(Integer.valueOf(i));
    }

    /* JADX INFO: renamed from: getDrawableIntrinsicSize-NH-jbRc, reason: not valid java name */
    private final long m2593getDrawableIntrinsicSizeNHjbRc() {
        return ((Size) this.drawableIntrinsicSize$delegate.getValue()).m1200unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setDrawableIntrinsicSize-uvyYCjk, reason: not valid java name */
    public final void m2594setDrawableIntrinsicSizeuvyYCjk(long j) {
        this.drawableIntrinsicSize$delegate.setValue(Size.m1190boximpl(j));
    }

    private final Drawable.Callback getCallback() {
        return (Drawable.Callback) this.callback$delegate.getValue();
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onRemembered() {
        this.drawable.setCallback(getCallback());
        this.drawable.setVisible(true, true);
        Object obj = this.drawable;
        if (obj instanceof Animatable) {
            ((Animatable) obj).start();
        }
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onAbandoned() {
        onForgotten();
    }

    @Override // androidx.compose.runtime.RememberObserver
    public void onForgotten() {
        Object obj = this.drawable;
        if (obj instanceof Animatable) {
            ((Animatable) obj).stop();
        }
        this.drawable.setVisible(false, false);
        this.drawable.setCallback(null);
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    protected boolean applyAlpha(float f) {
        this.drawable.setAlpha(RangesKt.coerceIn(MathKt.roundToInt(f * GF2Field.MASK), 0, GF2Field.MASK));
        return true;
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    protected boolean applyColorFilter(ColorFilter colorFilter) {
        this.drawable.setColorFilter(colorFilter != null ? AndroidColorFilter_androidKt.asAndroidColorFilter(colorFilter) : null);
        return true;
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    protected boolean applyLayoutDirection(LayoutDirection layoutDirection) {
        Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
        Drawable drawable = this.drawable;
        int i = WhenMappings.$EnumSwitchMapping$0[layoutDirection.ordinal()];
        int i2 = 1;
        if (i == 1) {
            i2 = 0;
        } else if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        return drawable.setLayoutDirection(i2);
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    /* JADX INFO: renamed from: getIntrinsicSize-NH-jbRc */
    public long mo1548getIntrinsicSizeNHjbRc() {
        return m2593getDrawableIntrinsicSizeNHjbRc();
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    protected void onDraw(DrawScope drawScope) {
        Intrinsics.checkNotNullParameter(drawScope, "<this>");
        Canvas canvas = drawScope.getDrawContext().getCanvas();
        getDrawInvalidateTick();
        this.drawable.setBounds(0, 0, MathKt.roundToInt(Size.m1196getWidthimpl(drawScope.mo1483getSizeNHjbRc())), MathKt.roundToInt(Size.m1194getHeightimpl(drawScope.mo1483getSizeNHjbRc())));
        try {
            canvas.save();
            this.drawable.draw(AndroidCanvas_androidKt.getNativeCanvas(canvas));
        } finally {
            canvas.restore();
        }
    }
}
