package com.google.android.material.badge;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.R$attr;
import com.google.android.material.R$id;
import com.google.android.material.R$string;
import com.google.android.material.R$style;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.badge.BadgeState;
import com.google.android.material.internal.TextDrawableHelper;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.lang.ref.WeakReference;
import java.text.NumberFormat;

/* JADX INFO: loaded from: classes.dex */
public class BadgeDrawable extends Drawable implements TextDrawableHelper.TextDrawableDelegate {
    private static final int DEFAULT_STYLE = R$style.Widget_MaterialComponents_Badge;
    private static final int DEFAULT_THEME_ATTR = R$attr.badgeStyle;
    private WeakReference anchorViewRef;
    private final Rect badgeBounds;
    private float badgeCenterX;
    private float badgeCenterY;
    private final WeakReference contextRef;
    private float cornerRadius;
    private WeakReference customBadgeParentRef;
    private float halfBadgeHeight;
    private float halfBadgeWidth;
    private int maxBadgeNumber;
    private final MaterialShapeDrawable shapeDrawable;
    private final BadgeState state;
    private final TextDrawableHelper textDrawableHelper;

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    BadgeState.State getSavedState() {
        return this.state.getOverridingState();
    }

    static BadgeDrawable createFromSavedState(Context context, BadgeState.State state) {
        return new BadgeDrawable(context, 0, DEFAULT_THEME_ATTR, DEFAULT_STYLE, state);
    }

    public static BadgeDrawable create(Context context) {
        return new BadgeDrawable(context, 0, DEFAULT_THEME_ATTR, DEFAULT_STYLE, null);
    }

    public void setVisible(boolean z) {
        this.state.setVisible(z);
        onVisibilityUpdated();
    }

    private void onVisibilityUpdated() {
        boolean zIsVisible = this.state.isVisible();
        setVisible(zIsVisible, false);
        if (!BadgeUtils.USE_COMPAT_PARENT || getCustomBadgeParent() == null || zIsVisible) {
            return;
        }
        ((ViewGroup) getCustomBadgeParent().getParent()).invalidate();
    }

    private void restoreState() {
        onBadgeShapeAppearanceUpdated();
        onBadgeTextAppearanceUpdated();
        onMaxBadgeLengthUpdated();
        onBadgeContentUpdated();
        onAlphaUpdated();
        onBackgroundColorUpdated();
        onBadgeTextColorUpdated();
        onBadgeGravityUpdated();
        updateCenterAndBounds();
        onVisibilityUpdated();
    }

    private BadgeDrawable(Context context, int i, int i2, int i3, BadgeState.State state) {
        int badgeShapeAppearanceResId;
        int badgeShapeAppearanceOverlayResId;
        this.contextRef = new WeakReference(context);
        ThemeEnforcement.checkMaterialTheme(context);
        this.badgeBounds = new Rect();
        TextDrawableHelper textDrawableHelper = new TextDrawableHelper(this);
        this.textDrawableHelper = textDrawableHelper;
        textDrawableHelper.getTextPaint().setTextAlign(Paint.Align.CENTER);
        BadgeState badgeState = new BadgeState(context, i, i2, i3, state);
        this.state = badgeState;
        if (hasBadgeContent()) {
            badgeShapeAppearanceResId = badgeState.getBadgeWithTextShapeAppearanceResId();
        } else {
            badgeShapeAppearanceResId = badgeState.getBadgeShapeAppearanceResId();
        }
        if (hasBadgeContent()) {
            badgeShapeAppearanceOverlayResId = badgeState.getBadgeWithTextShapeAppearanceOverlayResId();
        } else {
            badgeShapeAppearanceOverlayResId = badgeState.getBadgeShapeAppearanceOverlayResId();
        }
        this.shapeDrawable = new MaterialShapeDrawable(ShapeAppearanceModel.builder(context, badgeShapeAppearanceResId, badgeShapeAppearanceOverlayResId).build());
        restoreState();
    }

    public void updateBadgeCoordinates(View view, FrameLayout frameLayout) {
        this.anchorViewRef = new WeakReference(view);
        boolean z = BadgeUtils.USE_COMPAT_PARENT;
        if (z && frameLayout == null) {
            tryWrapAnchorInCompatParent(view);
        } else {
            this.customBadgeParentRef = new WeakReference(frameLayout);
        }
        if (!z) {
            updateAnchorParentToNotClip(view);
        }
        updateCenterAndBounds();
        invalidateSelf();
    }

    private boolean isAnchorViewWrappedInCompatParent() {
        FrameLayout customBadgeParent = getCustomBadgeParent();
        return customBadgeParent != null && customBadgeParent.getId() == R$id.mtrl_anchor_parent;
    }

    public FrameLayout getCustomBadgeParent() {
        WeakReference weakReference = this.customBadgeParentRef;
        if (weakReference != null) {
            return (FrameLayout) weakReference.get();
        }
        return null;
    }

    private void tryWrapAnchorInCompatParent(final View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup == null || viewGroup.getId() != R$id.mtrl_anchor_parent) {
            WeakReference weakReference = this.customBadgeParentRef;
            if (weakReference == null || weakReference.get() != viewGroup) {
                updateAnchorParentToNotClip(view);
                final FrameLayout frameLayout = new FrameLayout(view.getContext());
                frameLayout.setId(R$id.mtrl_anchor_parent);
                frameLayout.setClipChildren(false);
                frameLayout.setClipToPadding(false);
                frameLayout.setLayoutParams(view.getLayoutParams());
                frameLayout.setMinimumWidth(view.getWidth());
                frameLayout.setMinimumHeight(view.getHeight());
                int iIndexOfChild = viewGroup.indexOfChild(view);
                viewGroup.removeViewAt(iIndexOfChild);
                view.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                frameLayout.addView(view);
                viewGroup.addView(frameLayout, iIndexOfChild);
                this.customBadgeParentRef = new WeakReference(frameLayout);
                frameLayout.post(new Runnable() { // from class: com.google.android.material.badge.BadgeDrawable.1
                    @Override // java.lang.Runnable
                    public void run() {
                        BadgeDrawable.this.updateBadgeCoordinates(view, frameLayout);
                    }
                });
            }
        }
    }

    private static void updateAnchorParentToNotClip(View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        viewGroup.setClipChildren(false);
        viewGroup.setClipToPadding(false);
    }

    private void onBackgroundColorUpdated() {
        ColorStateList colorStateListValueOf = ColorStateList.valueOf(this.state.getBackgroundColor());
        if (this.shapeDrawable.getFillColor() != colorStateListValueOf) {
            this.shapeDrawable.setFillColor(colorStateListValueOf);
            invalidateSelf();
        }
    }

    private void onBadgeTextColorUpdated() {
        this.textDrawableHelper.getTextPaint().setColor(this.state.getBadgeTextColor());
        invalidateSelf();
    }

    public boolean hasNumber() {
        return !this.state.hasText() && this.state.hasNumber();
    }

    public int getNumber() {
        if (this.state.hasNumber()) {
            return this.state.getNumber();
        }
        return 0;
    }

    public void setNumber(int i) {
        int iMax = Math.max(0, i);
        if (this.state.getNumber() != iMax) {
            this.state.setNumber(iMax);
            onNumberUpdated();
        }
    }

    public void clearNumber() {
        if (this.state.hasNumber()) {
            this.state.clearNumber();
            onNumberUpdated();
        }
    }

    private void onNumberUpdated() {
        if (hasText()) {
            return;
        }
        onBadgeContentUpdated();
    }

    public boolean hasText() {
        return this.state.hasText();
    }

    public String getText() {
        return this.state.getText();
    }

    public int getMaxCharacterCount() {
        return this.state.getMaxCharacterCount();
    }

    public int getMaxNumber() {
        return this.state.getMaxNumber();
    }

    private void onMaxBadgeLengthUpdated() {
        updateMaxBadgeNumber();
        this.textDrawableHelper.setTextSizeDirty(true);
        updateCenterAndBounds();
        invalidateSelf();
    }

    private void onBadgeGravityUpdated() {
        WeakReference weakReference = this.anchorViewRef;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        View view = (View) this.anchorViewRef.get();
        WeakReference weakReference2 = this.customBadgeParentRef;
        updateBadgeCoordinates(view, weakReference2 != null ? (FrameLayout) weakReference2.get() : null);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.state.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.state.setAlpha(i);
        onAlphaUpdated();
    }

    private void onAlphaUpdated() {
        this.textDrawableHelper.getTextPaint().setAlpha(getAlpha());
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.badgeBounds.height();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.badgeBounds.width();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (getBounds().isEmpty() || getAlpha() == 0 || !isVisible()) {
            return;
        }
        this.shapeDrawable.draw(canvas);
        if (hasBadgeContent()) {
            drawBadgeContent(canvas);
        }
    }

    @Override // com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public void onTextSizeChange() {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable, com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate
    public boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    public CharSequence getContentDescription() {
        if (!isVisible()) {
            return null;
        }
        if (hasText()) {
            return getTextContentDescription();
        }
        if (hasNumber()) {
            return getNumberContentDescription();
        }
        return getEmptyContentDescription();
    }

    private String getNumberContentDescription() {
        Context context;
        if (this.state.getContentDescriptionQuantityStrings() == 0 || (context = (Context) this.contextRef.get()) == null) {
            return null;
        }
        if (this.maxBadgeNumber == -2 || getNumber() <= this.maxBadgeNumber) {
            return context.getResources().getQuantityString(this.state.getContentDescriptionQuantityStrings(), getNumber(), Integer.valueOf(getNumber()));
        }
        return context.getString(this.state.getContentDescriptionExceedsMaxBadgeNumberStringResource(), Integer.valueOf(this.maxBadgeNumber));
    }

    private CharSequence getTextContentDescription() {
        CharSequence contentDescriptionForText = this.state.getContentDescriptionForText();
        return contentDescriptionForText != null ? contentDescriptionForText : getText();
    }

    private CharSequence getEmptyContentDescription() {
        return this.state.getContentDescriptionNumberless();
    }

    public int getHorizontalOffset() {
        return this.state.getHorizontalOffsetWithoutText();
    }

    private void onBadgeTextAppearanceUpdated() {
        TextAppearance textAppearance;
        Context context = (Context) this.contextRef.get();
        if (context == null || this.textDrawableHelper.getTextAppearance() == (textAppearance = new TextAppearance(context, this.state.getTextAppearanceResId()))) {
            return;
        }
        this.textDrawableHelper.setTextAppearance(textAppearance, context);
        onBadgeTextColorUpdated();
        updateCenterAndBounds();
        invalidateSelf();
    }

    private void onBadgeShapeAppearanceUpdated() {
        int badgeShapeAppearanceResId;
        int badgeShapeAppearanceOverlayResId;
        Context context = (Context) this.contextRef.get();
        if (context == null) {
            return;
        }
        MaterialShapeDrawable materialShapeDrawable = this.shapeDrawable;
        if (hasBadgeContent()) {
            badgeShapeAppearanceResId = this.state.getBadgeWithTextShapeAppearanceResId();
        } else {
            badgeShapeAppearanceResId = this.state.getBadgeShapeAppearanceResId();
        }
        if (hasBadgeContent()) {
            badgeShapeAppearanceOverlayResId = this.state.getBadgeWithTextShapeAppearanceOverlayResId();
        } else {
            badgeShapeAppearanceOverlayResId = this.state.getBadgeShapeAppearanceOverlayResId();
        }
        materialShapeDrawable.setShapeAppearanceModel(ShapeAppearanceModel.builder(context, badgeShapeAppearanceResId, badgeShapeAppearanceOverlayResId).build());
        invalidateSelf();
    }

    private void updateCenterAndBounds() {
        Context context = (Context) this.contextRef.get();
        WeakReference weakReference = this.anchorViewRef;
        View view = weakReference != null ? (View) weakReference.get() : null;
        if (context == null || view == null) {
            return;
        }
        Rect rect = new Rect();
        rect.set(this.badgeBounds);
        Rect rect2 = new Rect();
        view.getDrawingRect(rect2);
        WeakReference weakReference2 = this.customBadgeParentRef;
        ViewGroup viewGroup = weakReference2 != null ? (ViewGroup) weakReference2.get() : null;
        if (viewGroup != null || BadgeUtils.USE_COMPAT_PARENT) {
            if (viewGroup == null) {
                viewGroup = (ViewGroup) view.getParent();
            }
            viewGroup.offsetDescendantRectToMyCoords(view, rect2);
        }
        calculateCenterAndBounds(rect2, view);
        BadgeUtils.updateBadgeBounds(this.badgeBounds, this.badgeCenterX, this.badgeCenterY, this.halfBadgeWidth, this.halfBadgeHeight);
        float f = this.cornerRadius;
        if (f != -1.0f) {
            this.shapeDrawable.setCornerSize(f);
        }
        if (rect.equals(this.badgeBounds)) {
            return;
        }
        this.shapeDrawable.setBounds(this.badgeBounds);
    }

    private int getTotalVerticalOffsetForState() {
        int verticalOffsetWithoutText = this.state.getVerticalOffsetWithoutText();
        if (hasBadgeContent()) {
            verticalOffsetWithoutText = this.state.getVerticalOffsetWithText();
            Context context = (Context) this.contextRef.get();
            if (context != null) {
                verticalOffsetWithoutText = AnimationUtils.lerp(verticalOffsetWithoutText, verticalOffsetWithoutText - this.state.getLargeFontVerticalOffsetAdjustment(), AnimationUtils.lerp(0.0f, 1.0f, 0.3f, 1.0f, MaterialResources.getFontScale(context) - 1.0f));
            }
        }
        if (this.state.offsetAlignmentMode == 0) {
            verticalOffsetWithoutText -= Math.round(this.halfBadgeHeight);
        }
        return verticalOffsetWithoutText + this.state.getAdditionalVerticalOffset();
    }

    private int getTotalHorizontalOffsetForState() {
        int horizontalOffsetWithoutText;
        if (hasBadgeContent()) {
            horizontalOffsetWithoutText = this.state.getHorizontalOffsetWithText();
        } else {
            horizontalOffsetWithoutText = this.state.getHorizontalOffsetWithoutText();
        }
        if (this.state.offsetAlignmentMode == 1) {
            horizontalOffsetWithoutText += hasBadgeContent() ? this.state.horizontalInsetWithText : this.state.horizontalInset;
        }
        return horizontalOffsetWithoutText + this.state.getAdditionalHorizontalOffset();
    }

    private void calculateCenterAndBounds(Rect rect, View view) {
        float f;
        float f2;
        float f3 = hasBadgeContent() ? this.state.badgeWithTextRadius : this.state.badgeRadius;
        this.cornerRadius = f3;
        if (f3 != -1.0f) {
            this.halfBadgeWidth = f3;
            this.halfBadgeHeight = f3;
        } else {
            this.halfBadgeWidth = Math.round((hasBadgeContent() ? this.state.badgeWithTextWidth : this.state.badgeWidth) / 2.0f);
            this.halfBadgeHeight = Math.round((hasBadgeContent() ? this.state.badgeWithTextHeight : this.state.badgeHeight) / 2.0f);
        }
        if (hasBadgeContent()) {
            String badgeContent = getBadgeContent();
            this.halfBadgeWidth = Math.max(this.halfBadgeWidth, (this.textDrawableHelper.getTextWidth(badgeContent) / 2.0f) + this.state.getBadgeHorizontalPadding());
            float fMax = Math.max(this.halfBadgeHeight, (this.textDrawableHelper.getTextHeight(badgeContent) / 2.0f) + this.state.getBadgeVerticalPadding());
            this.halfBadgeHeight = fMax;
            this.halfBadgeWidth = Math.max(this.halfBadgeWidth, fMax);
        }
        int totalVerticalOffsetForState = getTotalVerticalOffsetForState();
        int badgeGravity = this.state.getBadgeGravity();
        if (badgeGravity == 8388691 || badgeGravity == 8388693) {
            this.badgeCenterY = rect.bottom - totalVerticalOffsetForState;
        } else {
            this.badgeCenterY = rect.top + totalVerticalOffsetForState;
        }
        int totalHorizontalOffsetForState = getTotalHorizontalOffsetForState();
        int badgeGravity2 = this.state.getBadgeGravity();
        if (badgeGravity2 == 8388659 || badgeGravity2 == 8388691) {
            if (ViewCompat.getLayoutDirection(view) == 0) {
                f = (rect.left - this.halfBadgeWidth) + totalHorizontalOffsetForState;
            } else {
                f = (rect.right + this.halfBadgeWidth) - totalHorizontalOffsetForState;
            }
            this.badgeCenterX = f;
        } else {
            if (ViewCompat.getLayoutDirection(view) == 0) {
                f2 = (rect.right + this.halfBadgeWidth) - totalHorizontalOffsetForState;
            } else {
                f2 = (rect.left - this.halfBadgeWidth) + totalHorizontalOffsetForState;
            }
            this.badgeCenterX = f2;
        }
        if (this.state.isAutoAdjustedToGrandparentBounds()) {
            autoAdjustWithinGrandparentBounds(view);
        }
    }

    private void autoAdjustWithinGrandparentBounds(View view) {
        float y;
        float x;
        View customBadgeParent = getCustomBadgeParent();
        if (customBadgeParent == null) {
            if (!(view.getParent() instanceof View)) {
                return;
            }
            float y2 = view.getY();
            x = view.getX();
            customBadgeParent = (View) view.getParent();
            y = y2;
        } else if (!isAnchorViewWrappedInCompatParent()) {
            y = 0.0f;
            x = 0.0f;
        } else {
            if (!(customBadgeParent.getParent() instanceof View)) {
                return;
            }
            y = customBadgeParent.getY();
            x = customBadgeParent.getX();
            customBadgeParent = (View) customBadgeParent.getParent();
        }
        float topCutOff = getTopCutOff(customBadgeParent, y);
        float leftCutOff = getLeftCutOff(customBadgeParent, x);
        float bottomCutOff = getBottomCutOff(customBadgeParent, y);
        float rightCutoff = getRightCutoff(customBadgeParent, x);
        if (topCutOff < 0.0f) {
            this.badgeCenterY += Math.abs(topCutOff);
        }
        if (leftCutOff < 0.0f) {
            this.badgeCenterX += Math.abs(leftCutOff);
        }
        if (bottomCutOff > 0.0f) {
            this.badgeCenterY -= Math.abs(bottomCutOff);
        }
        if (rightCutoff > 0.0f) {
            this.badgeCenterX -= Math.abs(rightCutoff);
        }
    }

    private float getTopCutOff(View view, float f) {
        return (this.badgeCenterY - this.halfBadgeHeight) + view.getY() + f;
    }

    private float getLeftCutOff(View view, float f) {
        return (this.badgeCenterX - this.halfBadgeWidth) + view.getX() + f;
    }

    private float getBottomCutOff(View view, float f) {
        if (!(view.getParent() instanceof View)) {
            return 0.0f;
        }
        return ((this.badgeCenterY + this.halfBadgeHeight) - (((View) view.getParent()).getHeight() - view.getY())) + f;
    }

    private float getRightCutoff(View view, float f) {
        if (!(view.getParent() instanceof View)) {
            return 0.0f;
        }
        return ((this.badgeCenterX + this.halfBadgeWidth) - (((View) view.getParent()).getWidth() - view.getX())) + f;
    }

    private void drawBadgeContent(Canvas canvas) {
        String badgeContent = getBadgeContent();
        if (badgeContent != null) {
            Rect rect = new Rect();
            this.textDrawableHelper.getTextPaint().getTextBounds(badgeContent, 0, badgeContent.length(), rect);
            float fExactCenterY = this.badgeCenterY - rect.exactCenterY();
            canvas.drawText(badgeContent, this.badgeCenterX, rect.bottom <= 0 ? (int) fExactCenterY : Math.round(fExactCenterY), this.textDrawableHelper.getTextPaint());
        }
    }

    private boolean hasBadgeContent() {
        return hasText() || hasNumber();
    }

    private String getBadgeContent() {
        if (hasText()) {
            return getTextBadgeText();
        }
        if (hasNumber()) {
            return getNumberBadgeText();
        }
        return null;
    }

    private String getTextBadgeText() {
        String text = getText();
        int maxCharacterCount = getMaxCharacterCount();
        if (maxCharacterCount == -2 || text == null || text.length() <= maxCharacterCount) {
            return text;
        }
        Context context = (Context) this.contextRef.get();
        if (context == null) {
            return "";
        }
        return String.format(context.getString(R$string.m3_exceed_max_badge_text_suffix), text.substring(0, maxCharacterCount - 1), "…");
    }

    private String getNumberBadgeText() {
        if (this.maxBadgeNumber == -2 || getNumber() <= this.maxBadgeNumber) {
            return NumberFormat.getInstance(this.state.getNumberLocale()).format(getNumber());
        }
        Context context = (Context) this.contextRef.get();
        if (context == null) {
            return "";
        }
        return String.format(this.state.getNumberLocale(), context.getString(R$string.mtrl_exceed_max_badge_number_suffix), Integer.valueOf(this.maxBadgeNumber), "+");
    }

    private void onBadgeContentUpdated() {
        this.textDrawableHelper.setTextSizeDirty(true);
        onBadgeShapeAppearanceUpdated();
        updateCenterAndBounds();
        invalidateSelf();
    }

    private void updateMaxBadgeNumber() {
        if (getMaxCharacterCount() != -2) {
            this.maxBadgeNumber = ((int) Math.pow(10.0d, ((double) getMaxCharacterCount()) - 1.0d)) - 1;
        } else {
            this.maxBadgeNumber = getMaxNumber();
        }
    }
}
