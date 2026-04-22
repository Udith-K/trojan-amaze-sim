package androidx.compose.ui.platform;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.ui.R$id;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ViewLayerContainer.android.kt */
/* JADX INFO: loaded from: classes.dex */
public class DrawChildContainer extends ViewGroup {
    private boolean isDrawing;

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
    }

    public DrawChildContainer(Context context) {
        super(context);
        setClipChildren(false);
        setTag(R$id.hide_in_inspector_tag, Boolean.TRUE);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(0, 0);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        int childCount = super.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            Intrinsics.checkNotNull(childAt, "null cannot be cast to non-null type androidx.compose.ui.platform.ViewLayer");
            if (((ViewLayer) childAt).isInvalidated()) {
                this.isDrawing = true;
                try {
                    super.dispatchDraw(canvas);
                    return;
                } finally {
                    this.isDrawing = false;
                }
            }
        }
    }

    @Override // android.view.ViewGroup
    public int getChildCount() {
        if (this.isDrawing) {
            return super.getChildCount();
        }
        return 0;
    }

    public final void drawChild$ui_release(androidx.compose.ui.graphics.Canvas canvas, View view, long j) {
        super.drawChild(AndroidCanvas_androidKt.getNativeCanvas(canvas), view, j);
    }
}
