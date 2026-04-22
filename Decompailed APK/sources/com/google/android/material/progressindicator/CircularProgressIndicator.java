package com.google.android.material.progressindicator;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;

/* JADX INFO: loaded from: classes.dex */
public class CircularProgressIndicator extends BaseProgressIndicator {
    public static final int DEF_STYLE_RES = R$style.Widget_MaterialComponents_CircularProgressIndicator;

    public CircularProgressIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.circularProgressIndicatorStyle);
    }

    public CircularProgressIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, DEF_STYLE_RES);
        initializeDrawables();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public CircularProgressIndicatorSpec createSpec(Context context, AttributeSet attributeSet) {
        return new CircularProgressIndicatorSpec(context, attributeSet);
    }

    private void initializeDrawables() {
        CircularDrawingDelegate circularDrawingDelegate = new CircularDrawingDelegate((CircularProgressIndicatorSpec) this.spec);
        setIndeterminateDrawable(IndeterminateDrawable.createCircularDrawable(getContext(), (CircularProgressIndicatorSpec) this.spec, circularDrawingDelegate));
        setProgressDrawable(DeterminateDrawable.createCircularDrawable(getContext(), (CircularProgressIndicatorSpec) this.spec, circularDrawingDelegate));
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setTrackThickness(int i) {
        super.setTrackThickness(i);
        ((CircularProgressIndicatorSpec) this.spec).validateSpec();
    }

    public int getIndicatorInset() {
        return ((CircularProgressIndicatorSpec) this.spec).indicatorInset;
    }

    public void setIndicatorInset(int i) {
        BaseProgressIndicatorSpec baseProgressIndicatorSpec = this.spec;
        if (((CircularProgressIndicatorSpec) baseProgressIndicatorSpec).indicatorInset != i) {
            ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec).indicatorInset = i;
            invalidate();
        }
    }

    public int getIndicatorSize() {
        return ((CircularProgressIndicatorSpec) this.spec).indicatorSize;
    }

    public void setIndicatorSize(int i) {
        int iMax = Math.max(i, getTrackThickness() * 2);
        BaseProgressIndicatorSpec baseProgressIndicatorSpec = this.spec;
        if (((CircularProgressIndicatorSpec) baseProgressIndicatorSpec).indicatorSize != iMax) {
            ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec).indicatorSize = iMax;
            ((CircularProgressIndicatorSpec) baseProgressIndicatorSpec).validateSpec();
            requestLayout();
            invalidate();
        }
    }

    public int getIndicatorDirection() {
        return ((CircularProgressIndicatorSpec) this.spec).indicatorDirection;
    }

    public void setIndicatorDirection(int i) {
        ((CircularProgressIndicatorSpec) this.spec).indicatorDirection = i;
        invalidate();
    }
}
