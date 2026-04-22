package com.google.android.material.progressindicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import com.google.android.material.R$drawable;
import com.google.android.material.progressindicator.DrawingDelegate;

/* JADX INFO: loaded from: classes.dex */
public final class IndeterminateDrawable extends DrawableWithAnimatedVisibilityChange {
    private IndeterminateAnimatorDelegate animatorDelegate;
    private DrawingDelegate drawingDelegate;
    private Drawable staticDummyDrawable;

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getAlpha() {
        return super.getAlpha();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getOpacity() {
        return super.getOpacity();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public /* bridge */ /* synthetic */ boolean hideNow() {
        return super.hideNow();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public /* bridge */ /* synthetic */ boolean isHiding() {
        return super.isHiding();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Animatable
    public /* bridge */ /* synthetic */ boolean isRunning() {
        return super.isRunning();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public /* bridge */ /* synthetic */ boolean isShowing() {
        return super.isShowing();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public /* bridge */ /* synthetic */ void registerAnimationCallback(Animatable2Compat$AnimationCallback animatable2Compat$AnimationCallback) {
        super.registerAnimationCallback(animatable2Compat$AnimationCallback);
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setAlpha(int i) {
        super.setAlpha(i);
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean setVisible(boolean z, boolean z2) {
        return super.setVisible(z, z2);
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public /* bridge */ /* synthetic */ boolean setVisible(boolean z, boolean z2, boolean z3) {
        return super.setVisible(z, z2, z3);
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Animatable
    public /* bridge */ /* synthetic */ void start() {
        super.start();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange, android.graphics.drawable.Animatable
    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    public /* bridge */ /* synthetic */ boolean unregisterAnimationCallback(Animatable2Compat$AnimationCallback animatable2Compat$AnimationCallback) {
        return super.unregisterAnimationCallback(animatable2Compat$AnimationCallback);
    }

    IndeterminateDrawable(Context context, BaseProgressIndicatorSpec baseProgressIndicatorSpec, DrawingDelegate drawingDelegate, IndeterminateAnimatorDelegate indeterminateAnimatorDelegate) {
        super(context, baseProgressIndicatorSpec);
        setDrawingDelegate(drawingDelegate);
        setAnimatorDelegate(indeterminateAnimatorDelegate);
    }

    static IndeterminateDrawable createLinearDrawable(Context context, LinearProgressIndicatorSpec linearProgressIndicatorSpec, LinearDrawingDelegate linearDrawingDelegate) {
        IndeterminateAnimatorDelegate linearIndeterminateDisjointAnimatorDelegate;
        if (linearProgressIndicatorSpec.indeterminateAnimationType == 0) {
            linearIndeterminateDisjointAnimatorDelegate = new LinearIndeterminateContiguousAnimatorDelegate(linearProgressIndicatorSpec);
        } else {
            linearIndeterminateDisjointAnimatorDelegate = new LinearIndeterminateDisjointAnimatorDelegate(context, linearProgressIndicatorSpec);
        }
        return new IndeterminateDrawable(context, linearProgressIndicatorSpec, linearDrawingDelegate, linearIndeterminateDisjointAnimatorDelegate);
    }

    static IndeterminateDrawable createCircularDrawable(Context context, CircularProgressIndicatorSpec circularProgressIndicatorSpec, CircularDrawingDelegate circularDrawingDelegate) {
        IndeterminateDrawable indeterminateDrawable = new IndeterminateDrawable(context, circularProgressIndicatorSpec, circularDrawingDelegate, new CircularIndeterminateAnimatorDelegate(circularProgressIndicatorSpec));
        indeterminateDrawable.setStaticDummyDrawable(VectorDrawableCompat.create(context.getResources(), R$drawable.indeterminate_static, null));
        return indeterminateDrawable;
    }

    @Override // com.google.android.material.progressindicator.DrawableWithAnimatedVisibilityChange
    boolean setVisibleInternal(boolean z, boolean z2, boolean z3) {
        Drawable drawable;
        boolean visibleInternal = super.setVisibleInternal(z, z2, z3);
        if (isSystemAnimatorDisabled() && (drawable = this.staticDummyDrawable) != null) {
            return drawable.setVisible(z, z2);
        }
        if (!isRunning()) {
            this.animatorDelegate.cancelAnimatorImmediately();
        }
        if (z && z3) {
            this.animatorDelegate.startAnimator();
        }
        return visibleInternal;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.drawingDelegate.getPreferredWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.drawingDelegate.getPreferredHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable;
        Rect rect = new Rect();
        if (!getBounds().isEmpty() && isVisible() && canvas.getClipBounds(rect)) {
            if (isSystemAnimatorDisabled() && (drawable = this.staticDummyDrawable) != null) {
                drawable.setBounds(getBounds());
                DrawableCompat.setTint(this.staticDummyDrawable, this.baseSpec.indicatorColors[0]);
                this.staticDummyDrawable.draw(canvas);
                return;
            }
            canvas.save();
            this.drawingDelegate.validateSpecAndAdjustCanvas(canvas, getBounds(), getGrowFraction(), isShowing(), isHiding());
            int i = this.baseSpec.indicatorTrackGapSize;
            int alpha = getAlpha();
            if (i == 0) {
                this.drawingDelegate.fillTrack(canvas, this.paint, 0.0f, 1.0f, this.baseSpec.trackColor, alpha, 0);
            } else {
                DrawingDelegate.ActiveIndicator activeIndicator = (DrawingDelegate.ActiveIndicator) this.animatorDelegate.activeIndicators.get(0);
                DrawingDelegate.ActiveIndicator activeIndicator2 = (DrawingDelegate.ActiveIndicator) this.animatorDelegate.activeIndicators.get(r3.size() - 1);
                DrawingDelegate drawingDelegate = this.drawingDelegate;
                if (drawingDelegate instanceof LinearDrawingDelegate) {
                    drawingDelegate.fillTrack(canvas, this.paint, 0.0f, activeIndicator.startFraction, this.baseSpec.trackColor, alpha, i);
                    this.drawingDelegate.fillTrack(canvas, this.paint, activeIndicator2.endFraction, 1.0f, this.baseSpec.trackColor, alpha, i);
                } else {
                    alpha = 0;
                    drawingDelegate.fillTrack(canvas, this.paint, activeIndicator2.endFraction, 1.0f + activeIndicator.startFraction, this.baseSpec.trackColor, 0, i);
                }
            }
            for (int i2 = 0; i2 < this.animatorDelegate.activeIndicators.size(); i2++) {
                DrawingDelegate.ActiveIndicator activeIndicator3 = (DrawingDelegate.ActiveIndicator) this.animatorDelegate.activeIndicators.get(i2);
                this.drawingDelegate.fillIndicator(canvas, this.paint, activeIndicator3, getAlpha());
                if (i2 > 0 && i > 0) {
                    this.drawingDelegate.fillTrack(canvas, this.paint, ((DrawingDelegate.ActiveIndicator) this.animatorDelegate.activeIndicators.get(i2 - 1)).endFraction, activeIndicator3.startFraction, this.baseSpec.trackColor, alpha, i);
                }
            }
            canvas.restore();
        }
    }

    private boolean isSystemAnimatorDisabled() {
        AnimatorDurationScaleProvider animatorDurationScaleProvider = this.animatorDurationScaleProvider;
        return animatorDurationScaleProvider != null && animatorDurationScaleProvider.getSystemAnimatorDurationScale(this.context.getContentResolver()) == 0.0f;
    }

    public void setStaticDummyDrawable(Drawable drawable) {
        this.staticDummyDrawable = drawable;
    }

    IndeterminateAnimatorDelegate getAnimatorDelegate() {
        return this.animatorDelegate;
    }

    void setAnimatorDelegate(IndeterminateAnimatorDelegate indeterminateAnimatorDelegate) {
        this.animatorDelegate = indeterminateAnimatorDelegate;
        indeterminateAnimatorDelegate.registerDrawable(this);
    }

    DrawingDelegate getDrawingDelegate() {
        return this.drawingDelegate;
    }

    void setDrawingDelegate(DrawingDelegate drawingDelegate) {
        this.drawingDelegate = drawingDelegate;
    }
}
