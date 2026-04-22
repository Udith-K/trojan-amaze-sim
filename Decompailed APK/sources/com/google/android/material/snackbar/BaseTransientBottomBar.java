package com.google.android.material.snackbar;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$layout;
import com.google.android.material.R$styleable;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.behavior.SwipeDismissBehavior;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.internal.WindowUtils;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.snackbar.SnackbarManager;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.List;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseTransientBottomBar {
    private final AccessibilityManager accessibilityManager;
    private final int animationFadeInDuration;
    private final TimeInterpolator animationFadeInterpolator;
    private final int animationFadeOutDuration;
    private final TimeInterpolator animationScaleInterpolator;
    private final int animationSlideDuration;
    private final TimeInterpolator animationSlideInterpolator;
    private int appliedBottomMarginGestureInset;
    private Behavior behavior;
    private List callbacks;
    private final ContentViewCallback contentViewCallback;
    private final Context context;
    private int duration;
    private int extraBottomMarginAnchorView;
    private int extraBottomMarginGestureInset;
    private int extraBottomMarginWindowInset;
    private int extraLeftMarginWindowInset;
    private int extraRightMarginWindowInset;
    private boolean gestureInsetBottomIgnored;
    private boolean pendingShowingView;
    private final ViewGroup targetParent;
    protected final SnackbarBaseLayout view;
    private static final TimeInterpolator DEFAULT_ANIMATION_SLIDE_INTERPOLATOR = AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
    private static final TimeInterpolator DEFAULT_ANIMATION_FADE_INTERPOLATOR = AnimationUtils.LINEAR_INTERPOLATOR;
    private static final TimeInterpolator DEFAULT_ANIMATION_SCALE_INTERPOLATOR = AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR;
    private static final boolean USE_OFFSET_API = false;
    private static final int[] SNACKBAR_STYLE_ATTR = {R$attr.snackbarStyle};
    private static final String TAG = BaseTransientBottomBar.class.getSimpleName();
    static final Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                ((BaseTransientBottomBar) message.obj).showView();
                return true;
            }
            if (i != 1) {
                return false;
            }
            ((BaseTransientBottomBar) message.obj).hideView(message.arg1);
            return true;
        }
    });
    private boolean anchorViewLayoutListenerEnabled = false;
    private final Runnable bottomMarginGestureInsetRunnable = new Runnable() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.2
        @Override // java.lang.Runnable
        public void run() {
            BaseTransientBottomBar baseTransientBottomBar = BaseTransientBottomBar.this;
            if (baseTransientBottomBar.view == null || baseTransientBottomBar.context == null) {
                return;
            }
            int iHeight = (WindowUtils.getCurrentWindowBounds(BaseTransientBottomBar.this.context).height() - BaseTransientBottomBar.this.getViewAbsoluteBottom()) + ((int) BaseTransientBottomBar.this.view.getTranslationY());
            if (iHeight >= BaseTransientBottomBar.this.extraBottomMarginGestureInset) {
                BaseTransientBottomBar baseTransientBottomBar2 = BaseTransientBottomBar.this;
                baseTransientBottomBar2.appliedBottomMarginGestureInset = baseTransientBottomBar2.extraBottomMarginGestureInset;
                return;
            }
            ViewGroup.LayoutParams layoutParams = BaseTransientBottomBar.this.view.getLayoutParams();
            if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
                Log.w(BaseTransientBottomBar.TAG, "Unable to apply gesture inset because layout params are not MarginLayoutParams");
                return;
            }
            BaseTransientBottomBar baseTransientBottomBar3 = BaseTransientBottomBar.this;
            baseTransientBottomBar3.appliedBottomMarginGestureInset = baseTransientBottomBar3.extraBottomMarginGestureInset;
            ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin += BaseTransientBottomBar.this.extraBottomMarginGestureInset - iHeight;
            BaseTransientBottomBar.this.view.requestLayout();
        }
    };
    SnackbarManager.Callback managerCallback = new SnackbarManager.Callback() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.5
        @Override // com.google.android.material.snackbar.SnackbarManager.Callback
        public void show() {
            Handler handler2 = BaseTransientBottomBar.handler;
            handler2.sendMessage(handler2.obtainMessage(0, BaseTransientBottomBar.this));
        }

        @Override // com.google.android.material.snackbar.SnackbarManager.Callback
        public void dismiss(int i) {
            Handler handler2 = BaseTransientBottomBar.handler;
            handler2.sendMessage(handler2.obtainMessage(1, i, 0, BaseTransientBottomBar.this));
        }
    };

    public View getAnchorView() {
        return null;
    }

    protected BaseTransientBottomBar(Context context, ViewGroup viewGroup, View view, ContentViewCallback contentViewCallback) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null parent");
        }
        if (view == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null content");
        }
        if (contentViewCallback == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null callback");
        }
        this.targetParent = viewGroup;
        this.contentViewCallback = contentViewCallback;
        this.context = context;
        ThemeEnforcement.checkAppCompatTheme(context);
        SnackbarBaseLayout snackbarBaseLayout = (SnackbarBaseLayout) LayoutInflater.from(context).inflate(getSnackbarBaseLayoutResId(), viewGroup, false);
        this.view = snackbarBaseLayout;
        snackbarBaseLayout.setBaseTransientBottomBar(this);
        if (view instanceof SnackbarContentLayout) {
            SnackbarContentLayout snackbarContentLayout = (SnackbarContentLayout) view;
            snackbarContentLayout.updateActionTextColorAlphaIfNeeded(snackbarBaseLayout.getActionTextColorAlpha());
            snackbarContentLayout.setMaxInlineActionWidth(snackbarBaseLayout.getMaxInlineActionWidth());
        }
        snackbarBaseLayout.addView(view);
        ViewCompat.setAccessibilityLiveRegion(snackbarBaseLayout, 1);
        ViewCompat.setImportantForAccessibility(snackbarBaseLayout, 1);
        ViewCompat.setFitsSystemWindows(snackbarBaseLayout, true);
        ViewCompat.setOnApplyWindowInsetsListener(snackbarBaseLayout, new OnApplyWindowInsetsListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.3
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public WindowInsetsCompat onApplyWindowInsets(View view2, WindowInsetsCompat windowInsetsCompat) {
                BaseTransientBottomBar.this.extraBottomMarginWindowInset = windowInsetsCompat.getSystemWindowInsetBottom();
                BaseTransientBottomBar.this.extraLeftMarginWindowInset = windowInsetsCompat.getSystemWindowInsetLeft();
                BaseTransientBottomBar.this.extraRightMarginWindowInset = windowInsetsCompat.getSystemWindowInsetRight();
                BaseTransientBottomBar.this.updateMargins();
                return windowInsetsCompat;
            }
        });
        ViewCompat.setAccessibilityDelegate(snackbarBaseLayout, new AccessibilityDelegateCompat() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.4
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view2, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view2, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.addAction(PKIFailureInfo.badCertTemplate);
                accessibilityNodeInfoCompat.setDismissable(true);
            }

            @Override // androidx.core.view.AccessibilityDelegateCompat
            public boolean performAccessibilityAction(View view2, int i, Bundle bundle) {
                if (i == 1048576) {
                    BaseTransientBottomBar.this.dismiss();
                    return true;
                }
                return super.performAccessibilityAction(view2, i, bundle);
            }
        });
        this.accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        this.animationSlideDuration = MotionUtils.resolveThemeDuration(context, R$attr.motionDurationLong2, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        this.animationFadeInDuration = MotionUtils.resolveThemeDuration(context, R$attr.motionDurationLong2, 150);
        this.animationFadeOutDuration = MotionUtils.resolveThemeDuration(context, R$attr.motionDurationMedium1, 75);
        this.animationFadeInterpolator = MotionUtils.resolveThemeInterpolator(context, R$attr.motionEasingEmphasizedInterpolator, DEFAULT_ANIMATION_FADE_INTERPOLATOR);
        this.animationScaleInterpolator = MotionUtils.resolveThemeInterpolator(context, R$attr.motionEasingEmphasizedInterpolator, DEFAULT_ANIMATION_SCALE_INTERPOLATOR);
        this.animationSlideInterpolator = MotionUtils.resolveThemeInterpolator(context, R$attr.motionEasingEmphasizedInterpolator, DEFAULT_ANIMATION_SLIDE_INTERPOLATOR);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMargins() {
        ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            Log.w(TAG, "Unable to update margins because layout params are not MarginLayoutParams");
            return;
        }
        if (this.view.originalMargins == null) {
            Log.w(TAG, "Unable to update margins because original view margins are not set");
            return;
        }
        if (this.view.getParent() == null) {
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        int i = this.view.originalMargins.bottom + (getAnchorView() != null ? this.extraBottomMarginAnchorView : this.extraBottomMarginWindowInset);
        int i2 = this.view.originalMargins.left + this.extraLeftMarginWindowInset;
        int i3 = this.view.originalMargins.right + this.extraRightMarginWindowInset;
        int i4 = this.view.originalMargins.top;
        boolean z = (marginLayoutParams.bottomMargin == i && marginLayoutParams.leftMargin == i2 && marginLayoutParams.rightMargin == i3 && marginLayoutParams.topMargin == i4) ? false : true;
        if (z) {
            marginLayoutParams.bottomMargin = i;
            marginLayoutParams.leftMargin = i2;
            marginLayoutParams.rightMargin = i3;
            marginLayoutParams.topMargin = i4;
            this.view.requestLayout();
        }
        if ((z || this.appliedBottomMarginGestureInset != this.extraBottomMarginGestureInset) && Build.VERSION.SDK_INT >= 29 && shouldUpdateGestureInset()) {
            this.view.removeCallbacks(this.bottomMarginGestureInsetRunnable);
            this.view.post(this.bottomMarginGestureInsetRunnable);
        }
    }

    private boolean shouldUpdateGestureInset() {
        return this.extraBottomMarginGestureInset > 0 && !this.gestureInsetBottomIgnored && isSwipeDismissable();
    }

    private boolean isSwipeDismissable() {
        ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
        return (layoutParams instanceof CoordinatorLayout.LayoutParams) && (((CoordinatorLayout.LayoutParams) layoutParams).getBehavior() instanceof SwipeDismissBehavior);
    }

    protected int getSnackbarBaseLayoutResId() {
        return hasSnackbarStyleAttr() ? R$layout.mtrl_layout_snackbar : R$layout.design_layout_snackbar;
    }

    protected boolean hasSnackbarStyleAttr() {
        TypedArray typedArrayObtainStyledAttributes = this.context.obtainStyledAttributes(SNACKBAR_STYLE_ATTR);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, -1);
        typedArrayObtainStyledAttributes.recycle();
        return resourceId != -1;
    }

    public BaseTransientBottomBar setDuration(int i) {
        this.duration = i;
        return this;
    }

    public int getDuration() {
        return this.duration;
    }

    public Context getContext() {
        return this.context;
    }

    public void show() {
        SnackbarManager.getInstance().show(getDuration(), this.managerCallback);
    }

    public void dismiss() {
        dispatchDismiss(3);
    }

    protected void dispatchDismiss(int i) {
        SnackbarManager.getInstance().dismiss(this.managerCallback, i);
    }

    public boolean isShownOrQueued() {
        return SnackbarManager.getInstance().isCurrentOrNext(this.managerCallback);
    }

    protected SwipeDismissBehavior getNewBehavior() {
        return new Behavior();
    }

    final void showView() {
        if (this.view.getParent() == null) {
            ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                setUpBehavior((CoordinatorLayout.LayoutParams) layoutParams);
            }
            this.view.addToTargetParent(this.targetParent);
            recalculateAndUpdateMargins();
            this.view.setVisibility(4);
        }
        if (ViewCompat.isLaidOut(this.view)) {
            showViewImpl();
        } else {
            this.pendingShowingView = true;
        }
    }

    void onAttachedToWindow() {
        WindowInsets rootWindowInsets;
        if (Build.VERSION.SDK_INT < 29 || (rootWindowInsets = this.view.getRootWindowInsets()) == null) {
            return;
        }
        this.extraBottomMarginGestureInset = rootWindowInsets.getMandatorySystemGestureInsets().bottom;
        updateMargins();
    }

    void onDetachedFromWindow() {
        if (isShownOrQueued()) {
            handler.post(new Runnable() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.6
                @Override // java.lang.Runnable
                public void run() {
                    BaseTransientBottomBar.this.onViewHidden(3);
                }
            });
        }
    }

    void onLayoutChange() {
        if (this.pendingShowingView) {
            showViewImpl();
            this.pendingShowingView = false;
        }
    }

    private void showViewImpl() {
        if (shouldAnimate()) {
            animateViewIn();
            return;
        }
        if (this.view.getParent() != null) {
            this.view.setVisibility(0);
        }
        onViewShown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getViewAbsoluteBottom() {
        int[] iArr = new int[2];
        this.view.getLocationInWindow(iArr);
        return iArr[1] + this.view.getHeight();
    }

    private void setUpBehavior(CoordinatorLayout.LayoutParams layoutParams) {
        SwipeDismissBehavior newBehavior = this.behavior;
        if (newBehavior == null) {
            newBehavior = getNewBehavior();
        }
        if (newBehavior instanceof Behavior) {
            ((Behavior) newBehavior).setBaseTransientBottomBar(this);
        }
        newBehavior.setListener(new SwipeDismissBehavior.OnDismissListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.7
            @Override // com.google.android.material.behavior.SwipeDismissBehavior.OnDismissListener
            public void onDismiss(View view) {
                if (view.getParent() != null) {
                    view.setVisibility(8);
                }
                BaseTransientBottomBar.this.dispatchDismiss(0);
            }

            @Override // com.google.android.material.behavior.SwipeDismissBehavior.OnDismissListener
            public void onDragStateChanged(int i) {
                if (i == 0) {
                    SnackbarManager.getInstance().restoreTimeoutIfPaused(BaseTransientBottomBar.this.managerCallback);
                } else if (i == 1 || i == 2) {
                    SnackbarManager.getInstance().pauseTimeout(BaseTransientBottomBar.this.managerCallback);
                }
            }
        });
        layoutParams.setBehavior(newBehavior);
        if (getAnchorView() == null) {
            layoutParams.insetEdge = 80;
        }
    }

    private void recalculateAndUpdateMargins() {
        this.extraBottomMarginAnchorView = calculateBottomMarginForAnchorView();
        updateMargins();
    }

    private int calculateBottomMarginForAnchorView() {
        if (getAnchorView() == null) {
            return 0;
        }
        int[] iArr = new int[2];
        getAnchorView().getLocationOnScreen(iArr);
        int i = iArr[1];
        int[] iArr2 = new int[2];
        this.targetParent.getLocationOnScreen(iArr2);
        return (iArr2[1] + this.targetParent.getHeight()) - i;
    }

    void animateViewIn() {
        this.view.post(new Runnable() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.8
            @Override // java.lang.Runnable
            public void run() {
                SnackbarBaseLayout snackbarBaseLayout = BaseTransientBottomBar.this.view;
                if (snackbarBaseLayout == null) {
                    return;
                }
                if (snackbarBaseLayout.getParent() != null) {
                    BaseTransientBottomBar.this.view.setVisibility(0);
                }
                if (BaseTransientBottomBar.this.view.getAnimationMode() == 1) {
                    BaseTransientBottomBar.this.startFadeInAnimation();
                } else {
                    BaseTransientBottomBar.this.startSlideInAnimation();
                }
            }
        });
    }

    private void animateViewOut(int i) {
        if (this.view.getAnimationMode() == 1) {
            startFadeOutAnimation(i);
        } else {
            startSlideOutAnimation(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startFadeInAnimation() {
        ValueAnimator alphaAnimator = getAlphaAnimator(0.0f, 1.0f);
        ValueAnimator scaleAnimator = getScaleAnimator(0.8f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(alphaAnimator, scaleAnimator);
        animatorSet.setDuration(this.animationFadeInDuration);
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.9
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                BaseTransientBottomBar.this.onViewShown();
            }
        });
        animatorSet.start();
    }

    private void startFadeOutAnimation(final int i) {
        ValueAnimator alphaAnimator = getAlphaAnimator(1.0f, 0.0f);
        alphaAnimator.setDuration(this.animationFadeOutDuration);
        alphaAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.10
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                BaseTransientBottomBar.this.onViewHidden(i);
            }
        });
        alphaAnimator.start();
    }

    private ValueAnimator getAlphaAnimator(float... fArr) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fArr);
        valueAnimatorOfFloat.setInterpolator(this.animationFadeInterpolator);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.11
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                BaseTransientBottomBar.this.view.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        return valueAnimatorOfFloat;
    }

    private ValueAnimator getScaleAnimator(float... fArr) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fArr);
        valueAnimatorOfFloat.setInterpolator(this.animationScaleInterpolator);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.12
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                BaseTransientBottomBar.this.view.setScaleX(fFloatValue);
                BaseTransientBottomBar.this.view.setScaleY(fFloatValue);
            }
        });
        return valueAnimatorOfFloat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSlideInAnimation() {
        int translationYBottom = getTranslationYBottom();
        if (USE_OFFSET_API) {
            ViewCompat.offsetTopAndBottom(this.view, translationYBottom);
        } else {
            this.view.setTranslationY(translationYBottom);
        }
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(translationYBottom, 0);
        valueAnimator.setInterpolator(this.animationSlideInterpolator);
        valueAnimator.setDuration(this.animationSlideDuration);
        valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.13
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                BaseTransientBottomBar.this.contentViewCallback.animateContentIn(BaseTransientBottomBar.this.animationSlideDuration - BaseTransientBottomBar.this.animationFadeInDuration, BaseTransientBottomBar.this.animationFadeInDuration);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                BaseTransientBottomBar.this.onViewShown();
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(translationYBottom) { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.14
            private int previousAnimatedIntValue;
            final /* synthetic */ int val$translationYBottom;

            {
                this.val$translationYBottom = translationYBottom;
                this.previousAnimatedIntValue = translationYBottom;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                int iIntValue = ((Integer) valueAnimator2.getAnimatedValue()).intValue();
                if (BaseTransientBottomBar.USE_OFFSET_API) {
                    ViewCompat.offsetTopAndBottom(BaseTransientBottomBar.this.view, iIntValue - this.previousAnimatedIntValue);
                } else {
                    BaseTransientBottomBar.this.view.setTranslationY(iIntValue);
                }
                this.previousAnimatedIntValue = iIntValue;
            }
        });
        valueAnimator.start();
    }

    private void startSlideOutAnimation(final int i) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(0, getTranslationYBottom());
        valueAnimator.setInterpolator(this.animationSlideInterpolator);
        valueAnimator.setDuration(this.animationSlideDuration);
        valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.15
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                BaseTransientBottomBar.this.contentViewCallback.animateContentOut(0, BaseTransientBottomBar.this.animationFadeOutDuration);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                BaseTransientBottomBar.this.onViewHidden(i);
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.16
            private int previousAnimatedIntValue = 0;

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                int iIntValue = ((Integer) valueAnimator2.getAnimatedValue()).intValue();
                if (BaseTransientBottomBar.USE_OFFSET_API) {
                    ViewCompat.offsetTopAndBottom(BaseTransientBottomBar.this.view, iIntValue - this.previousAnimatedIntValue);
                } else {
                    BaseTransientBottomBar.this.view.setTranslationY(iIntValue);
                }
                this.previousAnimatedIntValue = iIntValue;
            }
        });
        valueAnimator.start();
    }

    private int getTranslationYBottom() {
        int height = this.view.getHeight();
        ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? height + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin : height;
    }

    final void hideView(int i) {
        if (shouldAnimate() && this.view.getVisibility() == 0) {
            animateViewOut(i);
        } else {
            onViewHidden(i);
        }
    }

    void onViewShown() {
        int size;
        SnackbarManager.getInstance().onShown(this.managerCallback);
        if (this.callbacks == null || r0.size() - 1 < 0) {
            return;
        }
        ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(this.callbacks.get(size));
        throw null;
    }

    void onViewHidden(int i) {
        int size;
        SnackbarManager.getInstance().onDismissed(this.managerCallback);
        if (this.callbacks != null && r2.size() - 1 >= 0) {
            ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(this.callbacks.get(size));
            throw null;
        }
        ViewParent parent = this.view.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.view);
        }
    }

    boolean shouldAnimate() {
        AccessibilityManager accessibilityManager = this.accessibilityManager;
        if (accessibilityManager == null) {
            return true;
        }
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList = accessibilityManager.getEnabledAccessibilityServiceList(1);
        return enabledAccessibilityServiceList != null && enabledAccessibilityServiceList.isEmpty();
    }

    protected static class SnackbarBaseLayout extends FrameLayout {
        private static final View.OnTouchListener consumeAllTouchListener = new View.OnTouchListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        };
        private final float actionTextColorAlpha;
        private boolean addingToTargetParent;
        private int animationMode;
        private final float backgroundOverlayColorAlpha;
        private ColorStateList backgroundTint;
        private PorterDuff.Mode backgroundTintMode;
        private BaseTransientBottomBar baseTransientBottomBar;
        private final int maxInlineActionWidth;
        private final int maxWidth;
        private Rect originalMargins;
        ShapeAppearanceModel shapeAppearanceModel;

        protected SnackbarBaseLayout(Context context, AttributeSet attributeSet) {
            super(MaterialThemeOverlay.wrap(context, attributeSet, 0, 0), attributeSet);
            Context context2 = getContext();
            TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, R$styleable.SnackbarLayout);
            if (typedArrayObtainStyledAttributes.hasValue(R$styleable.SnackbarLayout_elevation)) {
                ViewCompat.setElevation(this, typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SnackbarLayout_elevation, 0));
            }
            this.animationMode = typedArrayObtainStyledAttributes.getInt(R$styleable.SnackbarLayout_animationMode, 0);
            if (typedArrayObtainStyledAttributes.hasValue(R$styleable.SnackbarLayout_shapeAppearance) || typedArrayObtainStyledAttributes.hasValue(R$styleable.SnackbarLayout_shapeAppearanceOverlay)) {
                this.shapeAppearanceModel = ShapeAppearanceModel.builder(context2, attributeSet, 0, 0).build();
            }
            this.backgroundOverlayColorAlpha = typedArrayObtainStyledAttributes.getFloat(R$styleable.SnackbarLayout_backgroundOverlayColorAlpha, 1.0f);
            setBackgroundTintList(MaterialResources.getColorStateList(context2, typedArrayObtainStyledAttributes, R$styleable.SnackbarLayout_backgroundTint));
            setBackgroundTintMode(ViewUtils.parseTintMode(typedArrayObtainStyledAttributes.getInt(R$styleable.SnackbarLayout_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN));
            this.actionTextColorAlpha = typedArrayObtainStyledAttributes.getFloat(R$styleable.SnackbarLayout_actionTextColorAlpha, 1.0f);
            this.maxWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SnackbarLayout_android_maxWidth, -1);
            this.maxInlineActionWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.SnackbarLayout_maxActionInlineWidth, -1);
            typedArrayObtainStyledAttributes.recycle();
            setOnTouchListener(consumeAllTouchListener);
            setFocusable(true);
            if (getBackground() == null) {
                ViewCompat.setBackground(this, createThemedBackground());
            }
        }

        @Override // android.view.View
        public void setBackground(Drawable drawable) {
            setBackgroundDrawable(drawable);
        }

        @Override // android.view.View
        public void setBackgroundDrawable(Drawable drawable) {
            if (drawable != null && this.backgroundTint != null) {
                drawable = DrawableCompat.wrap(drawable.mutate());
                DrawableCompat.setTintList(drawable, this.backgroundTint);
                DrawableCompat.setTintMode(drawable, this.backgroundTintMode);
            }
            super.setBackgroundDrawable(drawable);
        }

        @Override // android.view.View
        public void setBackgroundTintList(ColorStateList colorStateList) {
            this.backgroundTint = colorStateList;
            if (getBackground() != null) {
                Drawable drawableWrap = DrawableCompat.wrap(getBackground().mutate());
                DrawableCompat.setTintList(drawableWrap, colorStateList);
                DrawableCompat.setTintMode(drawableWrap, this.backgroundTintMode);
                if (drawableWrap != getBackground()) {
                    super.setBackgroundDrawable(drawableWrap);
                }
            }
        }

        @Override // android.view.View
        public void setBackgroundTintMode(PorterDuff.Mode mode) {
            this.backgroundTintMode = mode;
            if (getBackground() != null) {
                Drawable drawableWrap = DrawableCompat.wrap(getBackground().mutate());
                DrawableCompat.setTintMode(drawableWrap, mode);
                if (drawableWrap != getBackground()) {
                    super.setBackgroundDrawable(drawableWrap);
                }
            }
        }

        @Override // android.view.View
        public void setOnClickListener(View.OnClickListener onClickListener) {
            setOnTouchListener(onClickListener != null ? null : consumeAllTouchListener);
            super.setOnClickListener(onClickListener);
        }

        @Override // android.widget.FrameLayout, android.view.View
        protected void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            if (this.maxWidth > 0) {
                int measuredWidth = getMeasuredWidth();
                int i3 = this.maxWidth;
                if (measuredWidth > i3) {
                    super.onMeasure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), i2);
                }
            }
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            BaseTransientBottomBar baseTransientBottomBar = this.baseTransientBottomBar;
            if (baseTransientBottomBar != null) {
                baseTransientBottomBar.onLayoutChange();
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            BaseTransientBottomBar baseTransientBottomBar = this.baseTransientBottomBar;
            if (baseTransientBottomBar != null) {
                baseTransientBottomBar.onAttachedToWindow();
            }
            ViewCompat.requestApplyInsets(this);
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            BaseTransientBottomBar baseTransientBottomBar = this.baseTransientBottomBar;
            if (baseTransientBottomBar != null) {
                baseTransientBottomBar.onDetachedFromWindow();
            }
        }

        @Override // android.view.View
        public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
            super.setLayoutParams(layoutParams);
            if (this.addingToTargetParent || !(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
                return;
            }
            updateOriginalMargins((ViewGroup.MarginLayoutParams) layoutParams);
            BaseTransientBottomBar baseTransientBottomBar = this.baseTransientBottomBar;
            if (baseTransientBottomBar != null) {
                baseTransientBottomBar.updateMargins();
            }
        }

        int getAnimationMode() {
            return this.animationMode;
        }

        void setAnimationMode(int i) {
            this.animationMode = i;
        }

        float getBackgroundOverlayColorAlpha() {
            return this.backgroundOverlayColorAlpha;
        }

        float getActionTextColorAlpha() {
            return this.actionTextColorAlpha;
        }

        int getMaxWidth() {
            return this.maxWidth;
        }

        int getMaxInlineActionWidth() {
            return this.maxInlineActionWidth;
        }

        void addToTargetParent(ViewGroup viewGroup) {
            this.addingToTargetParent = true;
            viewGroup.addView(this);
            this.addingToTargetParent = false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBaseTransientBottomBar(BaseTransientBottomBar baseTransientBottomBar) {
            this.baseTransientBottomBar = baseTransientBottomBar;
        }

        private void updateOriginalMargins(ViewGroup.MarginLayoutParams marginLayoutParams) {
            this.originalMargins = new Rect(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
        }

        private Drawable createThemedBackground() {
            int iLayer = MaterialColors.layer(this, R$attr.colorSurface, R$attr.colorOnSurface, getBackgroundOverlayColorAlpha());
            ShapeAppearanceModel shapeAppearanceModel = this.shapeAppearanceModel;
            Drawable drawableCreateMaterialShapeDrawableBackground = shapeAppearanceModel != null ? BaseTransientBottomBar.createMaterialShapeDrawableBackground(iLayer, shapeAppearanceModel) : BaseTransientBottomBar.createGradientDrawableBackground(iLayer, getResources());
            if (this.backgroundTint != null) {
                Drawable drawableWrap = DrawableCompat.wrap(drawableCreateMaterialShapeDrawableBackground);
                DrawableCompat.setTintList(drawableWrap, this.backgroundTint);
                return drawableWrap;
            }
            return DrawableCompat.wrap(drawableCreateMaterialShapeDrawableBackground);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static MaterialShapeDrawable createMaterialShapeDrawableBackground(int i, ShapeAppearanceModel shapeAppearanceModel) {
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(shapeAppearanceModel);
        materialShapeDrawable.setFillColor(ColorStateList.valueOf(i));
        return materialShapeDrawable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static GradientDrawable createGradientDrawableBackground(int i, Resources resources) {
        float dimension = resources.getDimension(R$dimen.mtrl_snackbar_background_corner_radius);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(dimension);
        gradientDrawable.setColor(i);
        return gradientDrawable;
    }

    public static class Behavior extends SwipeDismissBehavior<View> {
        private final BehaviorDelegate delegate = new BehaviorDelegate(this);

        /* JADX INFO: Access modifiers changed from: private */
        public void setBaseTransientBottomBar(BaseTransientBottomBar baseTransientBottomBar) {
            this.delegate.setBaseTransientBottomBar(baseTransientBottomBar);
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior
        public boolean canSwipeDismissView(View view) {
            return this.delegate.canSwipeDismissView(view);
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            this.delegate.onInterceptTouchEvent(coordinatorLayout, view, motionEvent);
            return super.onInterceptTouchEvent(coordinatorLayout, view, motionEvent);
        }
    }

    public static class BehaviorDelegate {
        private SnackbarManager.Callback managerCallback;

        public BehaviorDelegate(SwipeDismissBehavior swipeDismissBehavior) {
            swipeDismissBehavior.setStartAlphaSwipeDistance(0.1f);
            swipeDismissBehavior.setEndAlphaSwipeDistance(0.6f);
            swipeDismissBehavior.setSwipeDirection(0);
        }

        public void setBaseTransientBottomBar(BaseTransientBottomBar baseTransientBottomBar) {
            this.managerCallback = baseTransientBottomBar.managerCallback;
        }

        public boolean canSwipeDismissView(View view) {
            return view instanceof SnackbarBaseLayout;
        }

        public void onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                if (coordinatorLayout.isPointInChildBounds(view, (int) motionEvent.getX(), (int) motionEvent.getY())) {
                    SnackbarManager.getInstance().pauseTimeout(this.managerCallback);
                }
            } else if (actionMasked == 1 || actionMasked == 3) {
                SnackbarManager.getInstance().restoreTimeoutIfPaused(this.managerCallback);
            }
        }
    }
}
