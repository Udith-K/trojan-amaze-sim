package org.fdroid.fdroid.privileged.views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ScrollView;

/* JADX INFO: loaded from: classes2.dex */
public class CaffeinatedScrollView extends ScrollView {
    private int bottomSlop;
    private Runnable fullScrollAction;

    public CaffeinatedScrollView(Context context) {
        super(context);
    }

    public CaffeinatedScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    public boolean awakenScrollBars() {
        return super.awakenScrollBars();
    }

    void setFullScrollAction(Runnable runnable) {
        this.fullScrollAction = runnable;
        this.bottomSlop = (int) (getResources().getDisplayMetrics().density * 4.0f);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        checkFullScrollAction();
    }

    @Override // android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        checkFullScrollAction();
    }

    private void checkFullScrollAction() {
        if (this.fullScrollAction == null || getChildAt(0).getBottom() - ((getScrollY() + getHeight()) - getPaddingBottom()) >= this.bottomSlop) {
            return;
        }
        this.fullScrollAction.run();
        this.fullScrollAction = null;
    }
}
