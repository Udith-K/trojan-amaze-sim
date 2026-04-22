package com.google.android.material.floatingactionbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.DescendantOffsetUtils;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class ExtendedFloatingActionButton extends MaterialButton implements CoordinatorLayout.AttachedBehavior {
    private static final int DEF_STYLE_RES = R$style.Widget_MaterialComponents_ExtendedFloatingActionButton_Icon;
    static final Property HEIGHT;
    static final Property PADDING_END;
    static final Property PADDING_START;
    static final Property WIDTH;

    public static abstract class OnChangedCallback {
    }

    static /* synthetic */ void access$400(ExtendedFloatingActionButton extendedFloatingActionButton, int i, OnChangedCallback onChangedCallback) {
        throw null;
    }

    static {
        Class<Float> cls = Float.class;
        WIDTH = new Property(cls, "width") { // from class: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.6
            @Override // android.util.Property
            public void set(View view, Float f) {
                view.getLayoutParams().width = f.intValue();
                view.requestLayout();
            }

            @Override // android.util.Property
            public Float get(View view) {
                return Float.valueOf(view.getLayoutParams().width);
            }
        };
        HEIGHT = new Property(cls, "height") { // from class: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.7
            @Override // android.util.Property
            public void set(View view, Float f) {
                view.getLayoutParams().height = f.intValue();
                view.requestLayout();
            }

            @Override // android.util.Property
            public Float get(View view) {
                return Float.valueOf(view.getLayoutParams().height);
            }
        };
        PADDING_START = new Property(cls, "paddingStart") { // from class: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.8
            @Override // android.util.Property
            public void set(View view, Float f) {
                ViewCompat.setPaddingRelative(view, f.intValue(), view.getPaddingTop(), ViewCompat.getPaddingEnd(view), view.getPaddingBottom());
            }

            @Override // android.util.Property
            public Float get(View view) {
                return Float.valueOf(ViewCompat.getPaddingStart(view));
            }
        };
        PADDING_END = new Property(cls, "paddingEnd") { // from class: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.9
            @Override // android.util.Property
            public void set(View view, Float f) {
                ViewCompat.setPaddingRelative(view, ViewCompat.getPaddingStart(view), view.getPaddingTop(), f.intValue(), view.getPaddingBottom());
            }

            @Override // android.util.Property
            public Float get(View view) {
                return Float.valueOf(ViewCompat.getPaddingEnd(view));
            }
        };
    }

    protected static class ExtendedFloatingActionButtonBehavior<T extends ExtendedFloatingActionButton> extends CoordinatorLayout.Behavior {
        private boolean autoHideEnabled;
        private boolean autoShrinkEnabled;
        private Rect tmpRect;

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean getInsetDodgeRect(CoordinatorLayout coordinatorLayout, View view, Rect rect) {
            ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(view);
            return getInsetDodgeRect(coordinatorLayout, (ExtendedFloatingActionButton) null, rect);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
            ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(view);
            return onDependentViewChanged(coordinatorLayout, (ExtendedFloatingActionButton) null, view2);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
            ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(view);
            return onLayoutChild(coordinatorLayout, (ExtendedFloatingActionButton) null, i);
        }

        public ExtendedFloatingActionButtonBehavior() {
            this.autoHideEnabled = false;
            this.autoShrinkEnabled = true;
        }

        public ExtendedFloatingActionButtonBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ExtendedFloatingActionButton_Behavior_Layout);
            this.autoHideEnabled = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoHide, false);
            this.autoShrinkEnabled = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoShrink, true);
            typedArrayObtainStyledAttributes.recycle();
        }

        public boolean getInsetDodgeRect(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, Rect rect) {
            return super.getInsetDodgeRect(coordinatorLayout, (View) extendedFloatingActionButton, rect);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
            if (layoutParams.dodgeInsetEdges == 0) {
                layoutParams.dodgeInsetEdges = 80;
            }
        }

        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, View view) {
            if (view instanceof AppBarLayout) {
                updateFabVisibilityForAppBarLayout(coordinatorLayout, (AppBarLayout) view, extendedFloatingActionButton);
                return false;
            }
            if (!isBottomSheet(view)) {
                return false;
            }
            updateFabVisibilityForBottomSheet(view, extendedFloatingActionButton);
            return false;
        }

        private static boolean isBottomSheet(View view) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                return ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior() instanceof BottomSheetBehavior;
            }
            return false;
        }

        private boolean shouldUpdateVisibility(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            throw null;
        }

        private boolean updateFabVisibilityForAppBarLayout(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!shouldUpdateVisibility(appBarLayout, extendedFloatingActionButton)) {
                return false;
            }
            if (this.tmpRect == null) {
                this.tmpRect = new Rect();
            }
            Rect rect = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                shrinkOrHide(extendedFloatingActionButton);
                return true;
            }
            extendOrShow(extendedFloatingActionButton);
            return true;
        }

        private boolean updateFabVisibilityForBottomSheet(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (shouldUpdateVisibility(view, extendedFloatingActionButton)) {
                throw null;
            }
            return false;
        }

        protected void shrinkOrHide(ExtendedFloatingActionButton extendedFloatingActionButton) {
            ExtendedFloatingActionButton.access$400(extendedFloatingActionButton, this.autoShrinkEnabled ? 2 : 1, null);
        }

        protected void extendOrShow(ExtendedFloatingActionButton extendedFloatingActionButton) {
            ExtendedFloatingActionButton.access$400(extendedFloatingActionButton, this.autoShrinkEnabled ? 3 : 0, null);
        }

        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, int i) {
            List dependencies = coordinatorLayout.getDependencies(extendedFloatingActionButton);
            int size = dependencies.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view = (View) dependencies.get(i2);
                if (view instanceof AppBarLayout) {
                    if (updateFabVisibilityForAppBarLayout(coordinatorLayout, (AppBarLayout) view, extendedFloatingActionButton)) {
                        break;
                    }
                } else {
                    if (isBottomSheet(view) && updateFabVisibilityForBottomSheet(view, extendedFloatingActionButton)) {
                        break;
                    }
                }
            }
            coordinatorLayout.onLayoutChild(extendedFloatingActionButton, i);
            return true;
        }
    }
}
