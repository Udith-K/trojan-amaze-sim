package org.fdroid.fdroid.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.core.content.ContextCompat;
import org.fdroid.fdroid.R;

/* JADX INFO: loaded from: classes2.dex */
public class SeekBarForegroundThumb extends AppCompatSeekBar {
    private Drawable tickMark;

    public SeekBarForegroundThumb(Context context) {
        super(context);
        init(context);
    }

    public SeekBarForegroundThumb(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public SeekBarForegroundThumb(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        this.tickMark = ContextCompat.getDrawable(context, R.drawable.seekbar_tickmark);
    }

    @Override // androidx.appcompat.widget.AppCompatSeekBar, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawTickMarks(canvas);
    }

    private void drawTickMarks(Canvas canvas) {
        if (this.tickMark != null) {
            int max = getMax();
            if (max > 1) {
                int intrinsicWidth = this.tickMark.getIntrinsicWidth();
                int intrinsicHeight = this.tickMark.getIntrinsicHeight();
                int intrinsicWidth2 = getThumb().getIntrinsicWidth() / 2;
                int i = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i2 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.tickMark.setBounds(-i, -i2, i, i2);
                float width = ((((getWidth() - getPaddingLeft()) - getPaddingRight()) + (getThumbOffset() * 2)) - (intrinsicWidth2 * 2)) / max;
                int iSave = canvas.save();
                canvas.translate((getPaddingLeft() - getThumbOffset()) + intrinsicWidth2, getHeight() / 2);
                for (int i3 = 0; i3 <= max; i3++) {
                    if (i3 != getProgress()) {
                        this.tickMark.draw(canvas);
                    }
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(iSave);
            }
        }
    }
}
