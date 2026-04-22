package androidx.gridlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.LogPrinter;
import android.util.Pair;
import android.util.Printer;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import androidx.gridlayout.R$dimen;
import androidx.gridlayout.R$styleable;
import androidx.legacy.widget.Space;
import ch.qos.logback.core.CoreConstants;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class GridLayout extends ViewGroup {
    public static final Alignment BASELINE;
    public static final Alignment BOTTOM;
    public static final Alignment CENTER;
    public static final Alignment END;
    public static final Alignment FILL;
    private static final Alignment LEADING;
    public static final Alignment LEFT;
    public static final Alignment RIGHT;
    public static final Alignment START;
    public static final Alignment TOP;
    private static final Alignment TRAILING;
    int mAlignmentMode;
    int mDefaultGap;
    final Axis mHorizontalAxis;
    int mLastLayoutParamsHashCode;
    int mOrientation;
    Printer mPrinter;
    boolean mUseDefaultMargins;
    final Axis mVerticalAxis;
    static final Printer LOG_PRINTER = new LogPrinter(3, GridLayout.class.getName());
    static final Printer NO_PRINTER = new Printer() { // from class: androidx.gridlayout.widget.GridLayout.1
        @Override // android.util.Printer
        public void println(String str) {
        }
    };
    private static final int ORIENTATION = R$styleable.GridLayout_orientation;
    private static final int ROW_COUNT = R$styleable.GridLayout_rowCount;
    private static final int COLUMN_COUNT = R$styleable.GridLayout_columnCount;
    private static final int USE_DEFAULT_MARGINS = R$styleable.GridLayout_useDefaultMargins;
    private static final int ALIGNMENT_MODE = R$styleable.GridLayout_alignmentMode;
    private static final int ROW_ORDER_PRESERVED = R$styleable.GridLayout_rowOrderPreserved;
    private static final int COLUMN_ORDER_PRESERVED = R$styleable.GridLayout_columnOrderPreserved;
    static final Alignment UNDEFINED_ALIGNMENT = new Alignment() { // from class: androidx.gridlayout.widget.GridLayout.2
        @Override // androidx.gridlayout.widget.GridLayout.Alignment
        public int getAlignmentValue(View view, int i, int i2) {
            return Integer.MIN_VALUE;
        }

        @Override // androidx.gridlayout.widget.GridLayout.Alignment
        int getGravityOffset(View view, int i) {
            return Integer.MIN_VALUE;
        }

        @Override // androidx.gridlayout.widget.GridLayout.Alignment
        String getDebugString() {
            return "UNDEFINED";
        }
    };

    static boolean canStretch(int i) {
        return (i & 2) != 0;
    }

    static {
        Alignment alignment = new Alignment() { // from class: androidx.gridlayout.widget.GridLayout.3
            @Override // androidx.gridlayout.widget.GridLayout.Alignment
            public int getAlignmentValue(View view, int i, int i2) {
                return 0;
            }

            @Override // androidx.gridlayout.widget.GridLayout.Alignment
            int getGravityOffset(View view, int i) {
                return 0;
            }

            @Override // androidx.gridlayout.widget.GridLayout.Alignment
            String getDebugString() {
                return "LEADING";
            }
        };
        LEADING = alignment;
        Alignment alignment2 = new Alignment() { // from class: androidx.gridlayout.widget.GridLayout.4
            @Override // androidx.gridlayout.widget.GridLayout.Alignment
            public int getAlignmentValue(View view, int i, int i2) {
                return i;
            }

            @Override // androidx.gridlayout.widget.GridLayout.Alignment
            int getGravityOffset(View view, int i) {
                return i;
            }

            @Override // androidx.gridlayout.widget.GridLayout.Alignment
            String getDebugString() {
                return "TRAILING";
            }
        };
        TRAILING = alignment2;
        TOP = alignment;
        BOTTOM = alignment2;
        START = alignment;
        END = alignment2;
        LEFT = createSwitchingAlignment(alignment, alignment2);
        RIGHT = createSwitchingAlignment(alignment2, alignment);
        CENTER = new Alignment() { // from class: androidx.gridlayout.widget.GridLayout.6
            @Override // androidx.gridlayout.widget.GridLayout.Alignment
            public int getAlignmentValue(View view, int i, int i2) {
                return i >> 1;
            }

            @Override // androidx.gridlayout.widget.GridLayout.Alignment
            int getGravityOffset(View view, int i) {
                return i >> 1;
            }

            @Override // androidx.gridlayout.widget.GridLayout.Alignment
            String getDebugString() {
                return "CENTER";
            }
        };
        BASELINE = new Alignment() { // from class: androidx.gridlayout.widget.GridLayout.7
            @Override // androidx.gridlayout.widget.GridLayout.Alignment
            int getGravityOffset(View view, int i) {
                return 0;
            }

            @Override // androidx.gridlayout.widget.GridLayout.Alignment
            public int getAlignmentValue(View view, int i, int i2) {
                if (view.getVisibility() == 8) {
                    return 0;
                }
                int baseline = view.getBaseline();
                if (baseline == -1) {
                    return Integer.MIN_VALUE;
                }
                return baseline;
            }

            @Override // androidx.gridlayout.widget.GridLayout.Alignment
            public Bounds getBounds() {
                return new Bounds() { // from class: androidx.gridlayout.widget.GridLayout.7.1
                    private int size;

                    @Override // androidx.gridlayout.widget.GridLayout.Bounds
                    protected void reset() {
                        super.reset();
                        this.size = Integer.MIN_VALUE;
                    }

                    @Override // androidx.gridlayout.widget.GridLayout.Bounds
                    protected void include(int i, int i2) {
                        super.include(i, i2);
                        this.size = Math.max(this.size, i + i2);
                    }

                    @Override // androidx.gridlayout.widget.GridLayout.Bounds
                    protected int size(boolean z) {
                        return Math.max(super.size(z), this.size);
                    }

                    @Override // androidx.gridlayout.widget.GridLayout.Bounds
                    protected int getOffset(GridLayout gridLayout, View view, Alignment alignment3, int i, boolean z) {
                        return Math.max(0, super.getOffset(gridLayout, view, alignment3, i, z));
                    }
                };
            }

            @Override // androidx.gridlayout.widget.GridLayout.Alignment
            String getDebugString() {
                return "BASELINE";
            }
        };
        FILL = new Alignment() { // from class: androidx.gridlayout.widget.GridLayout.8
            @Override // androidx.gridlayout.widget.GridLayout.Alignment
            public int getAlignmentValue(View view, int i, int i2) {
                return Integer.MIN_VALUE;
            }

            @Override // androidx.gridlayout.widget.GridLayout.Alignment
            int getGravityOffset(View view, int i) {
                return 0;
            }

            @Override // androidx.gridlayout.widget.GridLayout.Alignment
            public int getSizeInCell(View view, int i, int i2) {
                return i2;
            }

            @Override // androidx.gridlayout.widget.GridLayout.Alignment
            String getDebugString() {
                return "FILL";
            }
        };
    }

    public GridLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mHorizontalAxis = new Axis(true);
        this.mVerticalAxis = new Axis(false);
        this.mOrientation = 0;
        this.mUseDefaultMargins = false;
        this.mAlignmentMode = 1;
        this.mLastLayoutParamsHashCode = 0;
        this.mPrinter = LOG_PRINTER;
        this.mDefaultGap = context.getResources().getDimensionPixelOffset(R$dimen.default_gap);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.GridLayout);
        try {
            setRowCount(typedArrayObtainStyledAttributes.getInt(ROW_COUNT, Integer.MIN_VALUE));
            setColumnCount(typedArrayObtainStyledAttributes.getInt(COLUMN_COUNT, Integer.MIN_VALUE));
            setOrientation(typedArrayObtainStyledAttributes.getInt(ORIENTATION, 0));
            setUseDefaultMargins(typedArrayObtainStyledAttributes.getBoolean(USE_DEFAULT_MARGINS, false));
            setAlignmentMode(typedArrayObtainStyledAttributes.getInt(ALIGNMENT_MODE, 1));
            setRowOrderPreserved(typedArrayObtainStyledAttributes.getBoolean(ROW_ORDER_PRESERVED, true));
            setColumnOrderPreserved(typedArrayObtainStyledAttributes.getBoolean(COLUMN_ORDER_PRESERVED, true));
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public GridLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public void setOrientation(int i) {
        if (this.mOrientation != i) {
            this.mOrientation = i;
            invalidateStructure();
            requestLayout();
        }
    }

    public int getRowCount() {
        return this.mVerticalAxis.getCount();
    }

    public void setRowCount(int i) {
        this.mVerticalAxis.setCount(i);
        invalidateStructure();
        requestLayout();
    }

    public int getColumnCount() {
        return this.mHorizontalAxis.getCount();
    }

    public void setColumnCount(int i) {
        this.mHorizontalAxis.setCount(i);
        invalidateStructure();
        requestLayout();
    }

    public boolean getUseDefaultMargins() {
        return this.mUseDefaultMargins;
    }

    public void setUseDefaultMargins(boolean z) {
        this.mUseDefaultMargins = z;
        requestLayout();
    }

    public int getAlignmentMode() {
        return this.mAlignmentMode;
    }

    public void setAlignmentMode(int i) {
        this.mAlignmentMode = i;
        requestLayout();
    }

    public void setRowOrderPreserved(boolean z) {
        this.mVerticalAxis.setOrderPreserved(z);
        invalidateStructure();
        requestLayout();
    }

    public void setColumnOrderPreserved(boolean z) {
        this.mHorizontalAxis.setOrderPreserved(z);
        invalidateStructure();
        requestLayout();
    }

    public Printer getPrinter() {
        return this.mPrinter;
    }

    public void setPrinter(Printer printer) {
        if (printer == null) {
            printer = NO_PRINTER;
        }
        this.mPrinter = printer;
    }

    static int max2(int[] iArr, int i) {
        for (int i2 : iArr) {
            i = Math.max(i, i2);
        }
        return i;
    }

    static Object[] append(Object[] objArr, Object[] objArr2) {
        Object[] objArr3 = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), objArr.length + objArr2.length);
        System.arraycopy(objArr, 0, objArr3, 0, objArr.length);
        System.arraycopy(objArr2, 0, objArr3, objArr.length, objArr2.length);
        return objArr3;
    }

    static Alignment getAlignment(int i, boolean z) {
        int i2 = (i & (z ? 7 : 112)) >> (z ? 0 : 4);
        if (i2 == 1) {
            return CENTER;
        }
        if (i2 == 3) {
            return z ? LEFT : TOP;
        }
        if (i2 == 5) {
            return z ? RIGHT : BOTTOM;
        }
        if (i2 == 7) {
            return FILL;
        }
        if (i2 == 8388611) {
            return START;
        }
        if (i2 == 8388613) {
            return END;
        }
        return UNDEFINED_ALIGNMENT;
    }

    private int getDefaultMargin(View view, boolean z, boolean z2) {
        if (view.getClass() == Space.class || view.getClass() == android.widget.Space.class) {
            return 0;
        }
        return this.mDefaultGap / 2;
    }

    private int getDefaultMargin(View view, boolean z, boolean z2, boolean z3) {
        return getDefaultMargin(view, z2, z3);
    }

    private int getDefaultMargin(View view, LayoutParams layoutParams, boolean z, boolean z2) {
        boolean z3 = false;
        if (!this.mUseDefaultMargins) {
            return 0;
        }
        Spec spec = z ? layoutParams.columnSpec : layoutParams.rowSpec;
        Axis axis = z ? this.mHorizontalAxis : this.mVerticalAxis;
        Interval interval = spec.span;
        if (!((z && isLayoutRtlCompat()) ? !z2 : z2) ? interval.max == axis.getCount() : interval.min == 0) {
            z3 = true;
        }
        return getDefaultMargin(view, z3, z, z2);
    }

    int getMargin1(View view, boolean z, boolean z2) {
        int i;
        LayoutParams layoutParams = getLayoutParams(view);
        if (z) {
            i = z2 ? ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin : ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin;
        } else {
            i = z2 ? ((ViewGroup.MarginLayoutParams) layoutParams).topMargin : ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        }
        return i == Integer.MIN_VALUE ? getDefaultMargin(view, layoutParams, z, z2) : i;
    }

    private boolean isLayoutRtlCompat() {
        return ViewCompat.getLayoutDirection(this) == 1;
    }

    private int getMargin(View view, boolean z, boolean z2) {
        if (this.mAlignmentMode == 1) {
            return getMargin1(view, z, z2);
        }
        Axis axis = z ? this.mHorizontalAxis : this.mVerticalAxis;
        int[] leadingMargins = z2 ? axis.getLeadingMargins() : axis.getTrailingMargins();
        LayoutParams layoutParams = getLayoutParams(view);
        Interval interval = (z ? layoutParams.columnSpec : layoutParams.rowSpec).span;
        return leadingMargins[z2 ? interval.min : interval.max];
    }

    private int getTotalMargin(View view, boolean z) {
        return getMargin(view, z, true) + getMargin(view, z, false);
    }

    private static boolean fits(int[] iArr, int i, int i2, int i3) {
        if (i3 > iArr.length) {
            return false;
        }
        while (i2 < i3) {
            if (iArr[i2] > i) {
                return false;
            }
            i2++;
        }
        return true;
    }

    private static void procrusteanFill(int[] iArr, int i, int i2, int i3) {
        int length = iArr.length;
        Arrays.fill(iArr, Math.min(i, length), Math.min(i2, length), i3);
    }

    private static void setCellGroup(LayoutParams layoutParams, int i, int i2, int i3, int i4) {
        layoutParams.setRowSpecSpan(new Interval(i, i2 + i));
        layoutParams.setColumnSpecSpan(new Interval(i3, i4 + i3));
    }

    private static int clip(Interval interval, boolean z, int i) {
        int size = interval.size();
        if (i == 0) {
            return size;
        }
        return Math.min(size, i - (z ? Math.min(interval.min, i) : 0));
    }

    private void validateLayoutParams() {
        boolean z = this.mOrientation == 0;
        int i = (z ? this.mHorizontalAxis : this.mVerticalAxis).definedCount;
        if (i == Integer.MIN_VALUE) {
            i = 0;
        }
        int[] iArr = new int[i];
        int childCount = getChildCount();
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < childCount; i4++) {
            LayoutParams layoutParams = (LayoutParams) getChildAt(i4).getLayoutParams();
            Spec spec = z ? layoutParams.rowSpec : layoutParams.columnSpec;
            Interval interval = spec.span;
            boolean z2 = spec.startDefined;
            int size = interval.size();
            if (z2) {
                i2 = interval.min;
            }
            Spec spec2 = z ? layoutParams.columnSpec : layoutParams.rowSpec;
            Interval interval2 = spec2.span;
            boolean z3 = spec2.startDefined;
            int iClip = clip(interval2, z3, i);
            if (z3) {
                i3 = interval2.min;
            }
            if (i != 0) {
                if (!z2 || !z3) {
                    while (true) {
                        int i5 = i3 + iClip;
                        if (fits(iArr, i2, i3, i5)) {
                            break;
                        }
                        if (z3) {
                            i2++;
                        } else if (i5 <= i) {
                            i3++;
                        } else {
                            i2++;
                            i3 = 0;
                        }
                    }
                }
                procrusteanFill(iArr, i3, i3 + iClip, i2 + size);
            }
            if (z) {
                setCellGroup(layoutParams, i2, size, i3, iClip);
            } else {
                setCellGroup(layoutParams, i3, iClip, i2, size);
            }
            i3 += iClip;
        }
    }

    private void invalidateStructure() {
        this.mLastLayoutParamsHashCode = 0;
        Axis axis = this.mHorizontalAxis;
        if (axis != null) {
            axis.invalidateStructure();
        }
        Axis axis2 = this.mVerticalAxis;
        if (axis2 != null) {
            axis2.invalidateStructure();
        }
        invalidateValues();
    }

    private void invalidateValues() {
        Axis axis = this.mHorizontalAxis;
        if (axis == null || this.mVerticalAxis == null) {
            return;
        }
        axis.invalidateValues();
        this.mVerticalAxis.invalidateValues();
    }

    final LayoutParams getLayoutParams(View view) {
        return (LayoutParams) view.getLayoutParams();
    }

    static void handleInvalidParams(String str) {
        throw new IllegalArgumentException(str + ". ");
    }

    private void checkLayoutParams(LayoutParams layoutParams, boolean z) {
        String str = z ? "column" : "row";
        Interval interval = (z ? layoutParams.columnSpec : layoutParams.rowSpec).span;
        int i = interval.min;
        if (i != Integer.MIN_VALUE && i < 0) {
            handleInvalidParams(str + " indices must be positive");
        }
        int i2 = (z ? this.mHorizontalAxis : this.mVerticalAxis).definedCount;
        if (i2 != Integer.MIN_VALUE) {
            if (interval.max > i2) {
                handleInvalidParams(str + " indices (start + span) mustn't exceed the " + str + " count");
            }
            if (interval.size() > i2) {
                handleInvalidParams(str + " span mustn't exceed the " + str + " count");
            }
        }
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (!(layoutParams instanceof LayoutParams)) {
            return false;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        checkLayoutParams(layoutParams2, true);
        checkLayoutParams(layoutParams2, false);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    private int computeLayoutParamsHashCode() {
        int childCount = getChildCount();
        int iHashCode = 1;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                iHashCode = (iHashCode * 31) + ((LayoutParams) childAt.getLayoutParams()).hashCode();
            }
        }
        return iHashCode;
    }

    private void consistencyCheck() {
        int i = this.mLastLayoutParamsHashCode;
        if (i == 0) {
            validateLayoutParams();
            this.mLastLayoutParamsHashCode = computeLayoutParamsHashCode();
        } else if (i != computeLayoutParamsHashCode()) {
            this.mPrinter.println("The fields of some layout parameters were modified in between layout operations. Check the javadoc for GridLayout.LayoutParams#rowSpec.");
            invalidateStructure();
            consistencyCheck();
        }
    }

    private void measureChildWithMargins2(View view, int i, int i2, int i3, int i4) {
        view.measure(ViewGroup.getChildMeasureSpec(i, getTotalMargin(view, true), i3), ViewGroup.getChildMeasureSpec(i2, getTotalMargin(view, false), i4));
    }

    private void measureChildrenWithMargins(int i, int i2, boolean z) {
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = getLayoutParams(childAt);
                if (z) {
                    measureChildWithMargins2(childAt, i, i2, ((ViewGroup.MarginLayoutParams) layoutParams).width, ((ViewGroup.MarginLayoutParams) layoutParams).height);
                } else {
                    boolean z2 = this.mOrientation == 0;
                    Spec spec = z2 ? layoutParams.columnSpec : layoutParams.rowSpec;
                    if (spec.getAbsoluteAlignment(z2) == FILL) {
                        Interval interval = spec.span;
                        int[] locations = (z2 ? this.mHorizontalAxis : this.mVerticalAxis).getLocations();
                        int totalMargin = (locations[interval.max] - locations[interval.min]) - getTotalMargin(childAt, z2);
                        if (z2) {
                            measureChildWithMargins2(childAt, i, i2, totalMargin, ((ViewGroup.MarginLayoutParams) layoutParams).height);
                        } else {
                            measureChildWithMargins2(childAt, i, i2, ((ViewGroup.MarginLayoutParams) layoutParams).width, totalMargin);
                        }
                    }
                }
            }
        }
    }

    static int adjust(int i, int i2) {
        return View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i2 + i), View.MeasureSpec.getMode(i));
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int measure;
        int measure2;
        consistencyCheck();
        invalidateValues();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int iAdjust = adjust(i, -paddingLeft);
        int iAdjust2 = adjust(i2, -paddingTop);
        measureChildrenWithMargins(iAdjust, iAdjust2, true);
        if (this.mOrientation == 0) {
            measure = this.mHorizontalAxis.getMeasure(iAdjust);
            measureChildrenWithMargins(iAdjust, iAdjust2, false);
            measure2 = this.mVerticalAxis.getMeasure(iAdjust2);
        } else {
            int measure3 = this.mVerticalAxis.getMeasure(iAdjust2);
            measureChildrenWithMargins(iAdjust, iAdjust2, false);
            measure = this.mHorizontalAxis.getMeasure(iAdjust);
            measure2 = measure3;
        }
        setMeasuredDimension(View.resolveSizeAndState(Math.max(measure + paddingLeft, getSuggestedMinimumWidth()), i, 0), View.resolveSizeAndState(Math.max(measure2 + paddingTop, getSuggestedMinimumHeight()), i2, 0));
    }

    private int getMeasurement(View view, boolean z) {
        return z ? view.getMeasuredWidth() : view.getMeasuredHeight();
    }

    final int getMeasurementIncludingMargin(View view, boolean z) {
        if (view.getVisibility() == 8) {
            return 0;
        }
        return getMeasurement(view, z) + getTotalMargin(view, z);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
        invalidateStructure();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int[] iArr;
        GridLayout gridLayout = this;
        consistencyCheck();
        int i5 = i3 - i;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        gridLayout.mHorizontalAxis.layout((i5 - paddingLeft) - paddingRight);
        gridLayout.mVerticalAxis.layout(((i4 - i2) - paddingTop) - paddingBottom);
        int[] locations = gridLayout.mHorizontalAxis.getLocations();
        int[] locations2 = gridLayout.mVerticalAxis.getLocations();
        int childCount = getChildCount();
        boolean z2 = false;
        int i6 = 0;
        while (i6 < childCount) {
            View childAt = gridLayout.getChildAt(i6);
            if (childAt.getVisibility() == 8) {
                iArr = locations;
            } else {
                LayoutParams layoutParams = gridLayout.getLayoutParams(childAt);
                Spec spec = layoutParams.columnSpec;
                Spec spec2 = layoutParams.rowSpec;
                Interval interval = spec.span;
                Interval interval2 = spec2.span;
                int i7 = locations[interval.min];
                int i8 = locations2[interval2.min];
                int i9 = locations[interval.max] - i7;
                int i10 = locations2[interval2.max] - i8;
                int measurement = gridLayout.getMeasurement(childAt, true);
                int measurement2 = gridLayout.getMeasurement(childAt, z2);
                Alignment absoluteAlignment = spec.getAbsoluteAlignment(true);
                Alignment absoluteAlignment2 = spec2.getAbsoluteAlignment(z2);
                Bounds bounds = (Bounds) gridLayout.mHorizontalAxis.getGroupBounds().getValue(i6);
                Bounds bounds2 = (Bounds) gridLayout.mVerticalAxis.getGroupBounds().getValue(i6);
                iArr = locations;
                int gravityOffset = absoluteAlignment.getGravityOffset(childAt, i9 - bounds.size(true));
                int gravityOffset2 = absoluteAlignment2.getGravityOffset(childAt, i10 - bounds2.size(true));
                int margin = gridLayout.getMargin(childAt, true, true);
                int margin2 = gridLayout.getMargin(childAt, false, true);
                int margin3 = gridLayout.getMargin(childAt, true, false);
                int i11 = margin + margin3;
                int margin4 = margin2 + gridLayout.getMargin(childAt, false, false);
                int offset = bounds.getOffset(this, childAt, absoluteAlignment, measurement + i11, true);
                int offset2 = bounds2.getOffset(this, childAt, absoluteAlignment2, measurement2 + margin4, false);
                int sizeInCell = absoluteAlignment.getSizeInCell(childAt, measurement, i9 - i11);
                int sizeInCell2 = absoluteAlignment2.getSizeInCell(childAt, measurement2, i10 - margin4);
                int i12 = i7 + gravityOffset + offset;
                int i13 = !isLayoutRtlCompat() ? paddingLeft + margin + i12 : (((i5 - sizeInCell) - paddingRight) - margin3) - i12;
                int i14 = paddingTop + i8 + gravityOffset2 + offset2 + margin2;
                if (sizeInCell != childAt.getMeasuredWidth() || sizeInCell2 != childAt.getMeasuredHeight()) {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(sizeInCell, 1073741824), View.MeasureSpec.makeMeasureSpec(sizeInCell2, 1073741824));
                }
                childAt.layout(i13, i14, sizeInCell + i13, sizeInCell2 + i14);
            }
            i6++;
            z2 = false;
            gridLayout = this;
            locations = iArr;
        }
    }

    final class Axis {
        public Arc[] arcs;
        PackedMap backwardLinks;
        public int[] deltas;
        PackedMap forwardLinks;
        PackedMap groupBounds;
        public boolean hasWeights;
        public final boolean horizontal;
        public int[] leadingMargins;
        public int[] locations;
        public int[] trailingMargins;
        public int definedCount = Integer.MIN_VALUE;
        private int maxIndex = Integer.MIN_VALUE;
        public boolean groupBoundsValid = false;
        public boolean forwardLinksValid = false;
        public boolean backwardLinksValid = false;
        public boolean leadingMarginsValid = false;
        public boolean trailingMarginsValid = false;
        public boolean arcsValid = false;
        public boolean locationsValid = false;
        public boolean hasWeightsValid = false;
        boolean orderPreserved = true;
        private MutableInt parentMin = new MutableInt(0);
        private MutableInt parentMax = new MutableInt(-100000);

        Axis(boolean z) {
            this.horizontal = z;
        }

        private int calculateMaxIndex() {
            int childCount = GridLayout.this.getChildCount();
            int iMax = -1;
            for (int i = 0; i < childCount; i++) {
                LayoutParams layoutParams = GridLayout.this.getLayoutParams(GridLayout.this.getChildAt(i));
                Interval interval = (this.horizontal ? layoutParams.columnSpec : layoutParams.rowSpec).span;
                iMax = Math.max(Math.max(Math.max(iMax, interval.min), interval.max), interval.size());
            }
            if (iMax == -1) {
                return Integer.MIN_VALUE;
            }
            return iMax;
        }

        private int getMaxIndex() {
            if (this.maxIndex == Integer.MIN_VALUE) {
                this.maxIndex = Math.max(0, calculateMaxIndex());
            }
            return this.maxIndex;
        }

        public int getCount() {
            return Math.max(this.definedCount, getMaxIndex());
        }

        public void setCount(int i) {
            if (i != Integer.MIN_VALUE && i < getMaxIndex()) {
                StringBuilder sb = new StringBuilder();
                sb.append(this.horizontal ? "column" : "row");
                sb.append("Count must be greater than or equal to the maximum of all grid indices ");
                sb.append("(and spans) defined in the LayoutParams of each child");
                GridLayout.handleInvalidParams(sb.toString());
            }
            this.definedCount = i;
        }

        public void setOrderPreserved(boolean z) {
            this.orderPreserved = z;
            invalidateStructure();
        }

        private PackedMap createGroupBounds() {
            Assoc assocOf = Assoc.of(Spec.class, Bounds.class);
            int childCount = GridLayout.this.getChildCount();
            for (int i = 0; i < childCount; i++) {
                LayoutParams layoutParams = GridLayout.this.getLayoutParams(GridLayout.this.getChildAt(i));
                boolean z = this.horizontal;
                Spec spec = z ? layoutParams.columnSpec : layoutParams.rowSpec;
                assocOf.put(spec, spec.getAbsoluteAlignment(z).getBounds());
            }
            return assocOf.pack();
        }

        private void computeGroupBounds() {
            for (Bounds bounds : (Bounds[]) this.groupBounds.values) {
                bounds.reset();
            }
            int childCount = GridLayout.this.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = GridLayout.this.getChildAt(i);
                LayoutParams layoutParams = GridLayout.this.getLayoutParams(childAt);
                boolean z = this.horizontal;
                Spec spec = z ? layoutParams.columnSpec : layoutParams.rowSpec;
                ((Bounds) this.groupBounds.getValue(i)).include(GridLayout.this, childAt, spec, this, GridLayout.this.getMeasurementIncludingMargin(childAt, z) + (spec.weight == 0.0f ? 0 : getDeltas()[i]));
            }
        }

        public PackedMap getGroupBounds() {
            if (this.groupBounds == null) {
                this.groupBounds = createGroupBounds();
            }
            if (!this.groupBoundsValid) {
                computeGroupBounds();
                this.groupBoundsValid = true;
            }
            return this.groupBounds;
        }

        private PackedMap createLinks(boolean z) {
            Assoc assocOf = Assoc.of(Interval.class, MutableInt.class);
            Spec[] specArr = (Spec[]) getGroupBounds().keys;
            int length = specArr.length;
            for (int i = 0; i < length; i++) {
                assocOf.put(z ? specArr[i].span : specArr[i].span.inverse(), new MutableInt());
            }
            return assocOf.pack();
        }

        private void computeLinks(PackedMap packedMap, boolean z) {
            for (MutableInt mutableInt : (MutableInt[]) packedMap.values) {
                mutableInt.reset();
            }
            Bounds[] boundsArr = (Bounds[]) getGroupBounds().values;
            for (int i = 0; i < boundsArr.length; i++) {
                int size = boundsArr[i].size(z);
                MutableInt mutableInt2 = (MutableInt) packedMap.getValue(i);
                int i2 = mutableInt2.value;
                if (!z) {
                    size = -size;
                }
                mutableInt2.value = Math.max(i2, size);
            }
        }

        private PackedMap getForwardLinks() {
            if (this.forwardLinks == null) {
                this.forwardLinks = createLinks(true);
            }
            if (!this.forwardLinksValid) {
                computeLinks(this.forwardLinks, true);
                this.forwardLinksValid = true;
            }
            return this.forwardLinks;
        }

        private PackedMap getBackwardLinks() {
            if (this.backwardLinks == null) {
                this.backwardLinks = createLinks(false);
            }
            if (!this.backwardLinksValid) {
                computeLinks(this.backwardLinks, false);
                this.backwardLinksValid = true;
            }
            return this.backwardLinks;
        }

        private void include(List list, Interval interval, MutableInt mutableInt, boolean z) {
            if (interval.size() == 0) {
                return;
            }
            if (z) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (((Arc) it.next()).span.equals(interval)) {
                        return;
                    }
                }
            }
            list.add(new Arc(interval, mutableInt));
        }

        private void include(List list, Interval interval, MutableInt mutableInt) {
            include(list, interval, mutableInt, true);
        }

        Arc[][] groupArcsByFirstVertex(Arc[] arcArr) {
            int count = getCount() + 1;
            Arc[][] arcArr2 = new Arc[count][];
            int[] iArr = new int[count];
            for (Arc arc : arcArr) {
                int i = arc.span.min;
                iArr[i] = iArr[i] + 1;
            }
            for (int i2 = 0; i2 < count; i2++) {
                arcArr2[i2] = new Arc[iArr[i2]];
            }
            Arrays.fill(iArr, 0);
            for (Arc arc2 : arcArr) {
                int i3 = arc2.span.min;
                Arc[] arcArr3 = arcArr2[i3];
                int i4 = iArr[i3];
                iArr[i3] = i4 + 1;
                arcArr3[i4] = arc2;
            }
            return arcArr2;
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [androidx.gridlayout.widget.GridLayout$Axis$1] */
        private Arc[] topologicalSort(Arc[] arcArr) {
            return new Object(arcArr) { // from class: androidx.gridlayout.widget.GridLayout.Axis.1
                Arc[][] arcsByVertex;
                int cursor;
                Arc[] result;
                final /* synthetic */ Arc[] val$arcs;
                int[] visited;

                {
                    this.val$arcs = arcArr;
                    this.result = new Arc[arcArr.length];
                    this.cursor = r0.length - 1;
                    this.arcsByVertex = Axis.this.groupArcsByFirstVertex(arcArr);
                    this.visited = new int[Axis.this.getCount() + 1];
                }

                void walk(int i) {
                    int[] iArr = this.visited;
                    if (iArr[i] != 0) {
                        return;
                    }
                    iArr[i] = 1;
                    for (Arc arc : this.arcsByVertex[i]) {
                        walk(arc.span.max);
                        Arc[] arcArr2 = this.result;
                        int i2 = this.cursor;
                        this.cursor = i2 - 1;
                        arcArr2[i2] = arc;
                    }
                    this.visited[i] = 2;
                }

                Arc[] sort() {
                    int length = this.arcsByVertex.length;
                    for (int i = 0; i < length; i++) {
                        walk(i);
                    }
                    return this.result;
                }
            }.sort();
        }

        private Arc[] topologicalSort(List list) {
            return topologicalSort((Arc[]) list.toArray(new Arc[list.size()]));
        }

        private void addComponentSizes(List list, PackedMap packedMap) {
            int i = 0;
            while (true) {
                Object[] objArr = packedMap.keys;
                if (i >= ((Interval[]) objArr).length) {
                    return;
                }
                include(list, ((Interval[]) objArr)[i], ((MutableInt[]) packedMap.values)[i], false);
                i++;
            }
        }

        private Arc[] createArcs() {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            addComponentSizes(arrayList, getForwardLinks());
            addComponentSizes(arrayList2, getBackwardLinks());
            if (this.orderPreserved) {
                int i = 0;
                while (i < getCount()) {
                    int i2 = i + 1;
                    include(arrayList, new Interval(i, i2), new MutableInt(0));
                    i = i2;
                }
            }
            int count = getCount();
            include(arrayList, new Interval(0, count), this.parentMin, false);
            include(arrayList2, new Interval(count, 0), this.parentMax, false);
            return (Arc[]) GridLayout.append(topologicalSort(arrayList), topologicalSort(arrayList2));
        }

        private void computeArcs() {
            getForwardLinks();
            getBackwardLinks();
        }

        public Arc[] getArcs() {
            if (this.arcs == null) {
                this.arcs = createArcs();
            }
            if (!this.arcsValid) {
                computeArcs();
                this.arcsValid = true;
            }
            return this.arcs;
        }

        private boolean relax(int[] iArr, Arc arc) {
            if (!arc.valid) {
                return false;
            }
            Interval interval = arc.span;
            int i = interval.min;
            int i2 = interval.max;
            int i3 = iArr[i] + arc.value.value;
            if (i3 <= iArr[i2]) {
                return false;
            }
            iArr[i2] = i3;
            return true;
        }

        private void init(int[] iArr) {
            Arrays.fill(iArr, 0);
        }

        private String arcsToString(List list) {
            StringBuilder sb;
            String str = this.horizontal ? "x" : "y";
            StringBuilder sb2 = new StringBuilder();
            Iterator it = list.iterator();
            boolean z = true;
            while (it.hasNext()) {
                Arc arc = (Arc) it.next();
                if (z) {
                    z = false;
                } else {
                    sb2.append(", ");
                }
                Interval interval = arc.span;
                int i = interval.min;
                int i2 = interval.max;
                int i3 = arc.value.value;
                if (i < i2) {
                    sb = new StringBuilder();
                    sb.append(str);
                    sb.append(i2);
                    sb.append("-");
                    sb.append(str);
                    sb.append(i);
                    sb.append(">=");
                } else {
                    sb = new StringBuilder();
                    sb.append(str);
                    sb.append(i);
                    sb.append("-");
                    sb.append(str);
                    sb.append(i2);
                    sb.append("<=");
                    i3 = -i3;
                }
                sb.append(i3);
                sb2.append(sb.toString());
            }
            return sb2.toString();
        }

        private void logError(String str, Arc[] arcArr, boolean[] zArr) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (int i = 0; i < arcArr.length; i++) {
                Arc arc = arcArr[i];
                if (zArr[i]) {
                    arrayList.add(arc);
                }
                if (!arc.valid) {
                    arrayList2.add(arc);
                }
            }
            GridLayout.this.mPrinter.println(str + " constraints: " + arcsToString(arrayList) + " are inconsistent; permanently removing: " + arcsToString(arrayList2) + ". ");
        }

        private boolean solve(Arc[] arcArr, int[] iArr) {
            return solve(arcArr, iArr, true);
        }

        private boolean solve(Arc[] arcArr, int[] iArr, boolean z) {
            String str = this.horizontal ? "horizontal" : "vertical";
            int count = getCount() + 1;
            boolean[] zArr = null;
            for (int i = 0; i < arcArr.length; i++) {
                init(iArr);
                for (int i2 = 0; i2 < count; i2++) {
                    boolean zRelax = false;
                    for (Arc arc : arcArr) {
                        zRelax |= relax(iArr, arc);
                    }
                    if (!zRelax) {
                        if (zArr != null) {
                            logError(str, arcArr, zArr);
                        }
                        return true;
                    }
                }
                if (!z) {
                    return false;
                }
                boolean[] zArr2 = new boolean[arcArr.length];
                for (int i3 = 0; i3 < count; i3++) {
                    int length = arcArr.length;
                    for (int i4 = 0; i4 < length; i4++) {
                        zArr2[i4] = zArr2[i4] | relax(iArr, arcArr[i4]);
                    }
                }
                if (i == 0) {
                    zArr = zArr2;
                }
                int i5 = 0;
                while (true) {
                    if (i5 >= arcArr.length) {
                        break;
                    }
                    if (zArr2[i5]) {
                        Arc arc2 = arcArr[i5];
                        Interval interval = arc2.span;
                        if (interval.min >= interval.max) {
                            arc2.valid = false;
                            break;
                        }
                    }
                    i5++;
                }
            }
            return true;
        }

        private void computeMargins(boolean z) {
            int[] iArr = z ? this.leadingMargins : this.trailingMargins;
            int childCount = GridLayout.this.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = GridLayout.this.getChildAt(i);
                if (childAt.getVisibility() != 8) {
                    LayoutParams layoutParams = GridLayout.this.getLayoutParams(childAt);
                    boolean z2 = this.horizontal;
                    Interval interval = (z2 ? layoutParams.columnSpec : layoutParams.rowSpec).span;
                    int i2 = z ? interval.min : interval.max;
                    iArr[i2] = Math.max(iArr[i2], GridLayout.this.getMargin1(childAt, z2, z));
                }
            }
        }

        public int[] getLeadingMargins() {
            if (this.leadingMargins == null) {
                this.leadingMargins = new int[getCount() + 1];
            }
            if (!this.leadingMarginsValid) {
                computeMargins(true);
                this.leadingMarginsValid = true;
            }
            return this.leadingMargins;
        }

        public int[] getTrailingMargins() {
            if (this.trailingMargins == null) {
                this.trailingMargins = new int[getCount() + 1];
            }
            if (!this.trailingMarginsValid) {
                computeMargins(false);
                this.trailingMarginsValid = true;
            }
            return this.trailingMargins;
        }

        private boolean solve(int[] iArr) {
            return solve(getArcs(), iArr);
        }

        private boolean computeHasWeights() {
            int childCount = GridLayout.this.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = GridLayout.this.getChildAt(i);
                if (childAt.getVisibility() != 8) {
                    LayoutParams layoutParams = GridLayout.this.getLayoutParams(childAt);
                    if ((this.horizontal ? layoutParams.columnSpec : layoutParams.rowSpec).weight != 0.0f) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean hasWeights() {
            if (!this.hasWeightsValid) {
                this.hasWeights = computeHasWeights();
                this.hasWeightsValid = true;
            }
            return this.hasWeights;
        }

        public int[] getDeltas() {
            if (this.deltas == null) {
                this.deltas = new int[GridLayout.this.getChildCount()];
            }
            return this.deltas;
        }

        private void shareOutDelta(int i, float f) {
            Arrays.fill(this.deltas, 0);
            int childCount = GridLayout.this.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = GridLayout.this.getChildAt(i2);
                if (childAt.getVisibility() != 8) {
                    LayoutParams layoutParams = GridLayout.this.getLayoutParams(childAt);
                    float f2 = (this.horizontal ? layoutParams.columnSpec : layoutParams.rowSpec).weight;
                    if (f2 != 0.0f) {
                        int iRound = Math.round((i * f2) / f);
                        this.deltas[i2] = iRound;
                        i -= iRound;
                        f -= f2;
                    }
                }
            }
        }

        private void solveAndDistributeSpace(int[] iArr) {
            Arrays.fill(getDeltas(), 0);
            solve(iArr);
            boolean z = true;
            int childCount = (this.parentMin.value * GridLayout.this.getChildCount()) + 1;
            if (childCount < 2) {
                return;
            }
            float fCalculateTotalWeight = calculateTotalWeight();
            int i = -1;
            int i2 = 0;
            while (i2 < childCount) {
                int i3 = (int) ((((long) i2) + ((long) childCount)) / 2);
                invalidateValues();
                shareOutDelta(i3, fCalculateTotalWeight);
                boolean zSolve = solve(getArcs(), iArr, false);
                if (zSolve) {
                    i2 = i3 + 1;
                    i = i3;
                } else {
                    childCount = i3;
                }
                z = zSolve;
            }
            if (i <= 0 || z) {
                return;
            }
            invalidateValues();
            shareOutDelta(i, fCalculateTotalWeight);
            solve(iArr);
        }

        private float calculateTotalWeight() {
            int childCount = GridLayout.this.getChildCount();
            float f = 0.0f;
            for (int i = 0; i < childCount; i++) {
                View childAt = GridLayout.this.getChildAt(i);
                if (childAt.getVisibility() != 8) {
                    LayoutParams layoutParams = GridLayout.this.getLayoutParams(childAt);
                    f += (this.horizontal ? layoutParams.columnSpec : layoutParams.rowSpec).weight;
                }
            }
            return f;
        }

        private void computeLocations(int[] iArr) {
            if (!hasWeights()) {
                solve(iArr);
            } else {
                solveAndDistributeSpace(iArr);
            }
            if (this.orderPreserved) {
                return;
            }
            int i = iArr[0];
            int length = iArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                iArr[i2] = iArr[i2] - i;
            }
        }

        public int[] getLocations() {
            if (this.locations == null) {
                this.locations = new int[getCount() + 1];
            }
            if (!this.locationsValid) {
                computeLocations(this.locations);
                this.locationsValid = true;
            }
            return this.locations;
        }

        private int size(int[] iArr) {
            return iArr[getCount()];
        }

        private void setParentConstraints(int i, int i2) {
            this.parentMin.value = i;
            this.parentMax.value = -i2;
            this.locationsValid = false;
        }

        private int getMeasure(int i, int i2) {
            setParentConstraints(i, i2);
            return size(getLocations());
        }

        public int getMeasure(int i) {
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            if (mode == Integer.MIN_VALUE) {
                return getMeasure(0, size);
            }
            if (mode == 0) {
                return getMeasure(0, 100000);
            }
            if (mode != 1073741824) {
                return 0;
            }
            return getMeasure(size, size);
        }

        public void layout(int i) {
            setParentConstraints(i, i);
            getLocations();
        }

        public void invalidateStructure() {
            this.maxIndex = Integer.MIN_VALUE;
            this.groupBounds = null;
            this.forwardLinks = null;
            this.backwardLinks = null;
            this.leadingMargins = null;
            this.trailingMargins = null;
            this.arcs = null;
            this.locations = null;
            this.deltas = null;
            this.hasWeightsValid = false;
            invalidateValues();
        }

        public void invalidateValues() {
            this.groupBoundsValid = false;
            this.forwardLinksValid = false;
            this.backwardLinksValid = false;
            this.leadingMarginsValid = false;
            this.trailingMarginsValid = false;
            this.arcsValid = false;
            this.locationsValid = false;
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        private static final int BOTTOM_MARGIN;
        private static final int COLUMN;
        private static final int COLUMN_SPAN;
        private static final int COLUMN_WEIGHT;
        private static final Interval DEFAULT_SPAN;
        private static final int DEFAULT_SPAN_SIZE;
        private static final int GRAVITY;
        private static final int LEFT_MARGIN;
        private static final int MARGIN;
        private static final int RIGHT_MARGIN;
        private static final int ROW;
        private static final int ROW_SPAN;
        private static final int ROW_WEIGHT;
        private static final int TOP_MARGIN;
        public Spec columnSpec;
        public Spec rowSpec;

        static {
            Interval interval = new Interval(Integer.MIN_VALUE, -2147483647);
            DEFAULT_SPAN = interval;
            DEFAULT_SPAN_SIZE = interval.size();
            MARGIN = R$styleable.GridLayout_Layout_android_layout_margin;
            LEFT_MARGIN = R$styleable.GridLayout_Layout_android_layout_marginLeft;
            TOP_MARGIN = R$styleable.GridLayout_Layout_android_layout_marginTop;
            RIGHT_MARGIN = R$styleable.GridLayout_Layout_android_layout_marginRight;
            BOTTOM_MARGIN = R$styleable.GridLayout_Layout_android_layout_marginBottom;
            COLUMN = R$styleable.GridLayout_Layout_layout_column;
            COLUMN_SPAN = R$styleable.GridLayout_Layout_layout_columnSpan;
            COLUMN_WEIGHT = R$styleable.GridLayout_Layout_layout_columnWeight;
            ROW = R$styleable.GridLayout_Layout_layout_row;
            ROW_SPAN = R$styleable.GridLayout_Layout_layout_rowSpan;
            ROW_WEIGHT = R$styleable.GridLayout_Layout_layout_rowWeight;
            GRAVITY = R$styleable.GridLayout_Layout_layout_gravity;
        }

        private LayoutParams(int i, int i2, int i3, int i4, int i5, int i6, Spec spec, Spec spec2) {
            super(i, i2);
            Spec spec3 = Spec.UNDEFINED;
            this.rowSpec = spec3;
            this.columnSpec = spec3;
            setMargins(i3, i4, i5, i6);
            this.rowSpec = spec;
            this.columnSpec = spec2;
        }

        public LayoutParams(Spec spec, Spec spec2) {
            this(-2, -2, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, spec, spec2);
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public LayoutParams() {
            Spec spec = Spec.UNDEFINED;
            this(spec, spec);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            Spec spec = Spec.UNDEFINED;
            this.rowSpec = spec;
            this.columnSpec = spec;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            Spec spec = Spec.UNDEFINED;
            this.rowSpec = spec;
            this.columnSpec = spec;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            Spec spec = Spec.UNDEFINED;
            this.rowSpec = spec;
            this.columnSpec = spec;
            this.rowSpec = layoutParams.rowSpec;
            this.columnSpec = layoutParams.columnSpec;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            Spec spec = Spec.UNDEFINED;
            this.rowSpec = spec;
            this.columnSpec = spec;
            reInitSuper(context, attributeSet);
            init(context, attributeSet);
        }

        private void reInitSuper(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.GridLayout_Layout);
            try {
                int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(MARGIN, Integer.MIN_VALUE);
                ((ViewGroup.MarginLayoutParams) this).leftMargin = typedArrayObtainStyledAttributes.getDimensionPixelSize(LEFT_MARGIN, dimensionPixelSize);
                ((ViewGroup.MarginLayoutParams) this).topMargin = typedArrayObtainStyledAttributes.getDimensionPixelSize(TOP_MARGIN, dimensionPixelSize);
                ((ViewGroup.MarginLayoutParams) this).rightMargin = typedArrayObtainStyledAttributes.getDimensionPixelSize(RIGHT_MARGIN, dimensionPixelSize);
                ((ViewGroup.MarginLayoutParams) this).bottomMargin = typedArrayObtainStyledAttributes.getDimensionPixelSize(BOTTOM_MARGIN, dimensionPixelSize);
            } finally {
                typedArrayObtainStyledAttributes.recycle();
            }
        }

        private void init(Context context, AttributeSet attributeSet) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.GridLayout_Layout);
            try {
                int i = typedArrayObtainStyledAttributes.getInt(GRAVITY, 0);
                int i2 = typedArrayObtainStyledAttributes.getInt(COLUMN, Integer.MIN_VALUE);
                int i3 = COLUMN_SPAN;
                int i4 = DEFAULT_SPAN_SIZE;
                this.columnSpec = GridLayout.spec(i2, typedArrayObtainStyledAttributes.getInt(i3, i4), GridLayout.getAlignment(i, true), typedArrayObtainStyledAttributes.getFloat(COLUMN_WEIGHT, 0.0f));
                this.rowSpec = GridLayout.spec(typedArrayObtainStyledAttributes.getInt(ROW, Integer.MIN_VALUE), typedArrayObtainStyledAttributes.getInt(ROW_SPAN, i4), GridLayout.getAlignment(i, false), typedArrayObtainStyledAttributes.getFloat(ROW_WEIGHT, 0.0f));
            } finally {
                typedArrayObtainStyledAttributes.recycle();
            }
        }

        @Override // android.view.ViewGroup.LayoutParams
        protected void setBaseAttributes(TypedArray typedArray, int i, int i2) {
            ((ViewGroup.MarginLayoutParams) this).width = typedArray.getLayoutDimension(i, -2);
            ((ViewGroup.MarginLayoutParams) this).height = typedArray.getLayoutDimension(i2, -2);
        }

        final void setRowSpecSpan(Interval interval) {
            this.rowSpec = this.rowSpec.copyWriteSpan(interval);
        }

        final void setColumnSpecSpan(Interval interval) {
            this.columnSpec = this.columnSpec.copyWriteSpan(interval);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            LayoutParams layoutParams = (LayoutParams) obj;
            return this.columnSpec.equals(layoutParams.columnSpec) && this.rowSpec.equals(layoutParams.rowSpec);
        }

        public int hashCode() {
            return (this.rowSpec.hashCode() * 31) + this.columnSpec.hashCode();
        }
    }

    static final class Arc {
        public final Interval span;
        public boolean valid = true;
        public final MutableInt value;

        public Arc(Interval interval, MutableInt mutableInt) {
            this.span = interval;
            this.value = mutableInt;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.span);
            sb.append(" ");
            sb.append(!this.valid ? "+>" : "->");
            sb.append(" ");
            sb.append(this.value);
            return sb.toString();
        }
    }

    static final class MutableInt {
        public int value;

        public MutableInt() {
            reset();
        }

        public MutableInt(int i) {
            this.value = i;
        }

        public void reset() {
            this.value = Integer.MIN_VALUE;
        }

        public String toString() {
            return Integer.toString(this.value);
        }
    }

    static final class Assoc extends ArrayList {
        private final Class keyType;
        private final Class valueType;

        private Assoc(Class cls, Class cls2) {
            this.keyType = cls;
            this.valueType = cls2;
        }

        public static Assoc of(Class cls, Class cls2) {
            return new Assoc(cls, cls2);
        }

        public void put(Object obj, Object obj2) {
            add(Pair.create(obj, obj2));
        }

        /* JADX WARN: Multi-variable type inference failed */
        public PackedMap pack() {
            int size = size();
            Object[] objArr = (Object[]) Array.newInstance((Class<?>) this.keyType, size);
            Object[] objArr2 = (Object[]) Array.newInstance((Class<?>) this.valueType, size);
            for (int i = 0; i < size; i++) {
                objArr[i] = ((Pair) get(i)).first;
                objArr2[i] = ((Pair) get(i)).second;
            }
            return new PackedMap(objArr, objArr2);
        }
    }

    static final class PackedMap {
        public final int[] index;
        public final Object[] keys;
        public final Object[] values;

        PackedMap(Object[] objArr, Object[] objArr2) {
            int[] iArrCreateIndex = createIndex(objArr);
            this.index = iArrCreateIndex;
            this.keys = compact(objArr, iArrCreateIndex);
            this.values = compact(objArr2, iArrCreateIndex);
        }

        public Object getValue(int i) {
            return this.values[this.index[i]];
        }

        private static int[] createIndex(Object[] objArr) {
            int length = objArr.length;
            int[] iArr = new int[length];
            HashMap map = new HashMap();
            for (int i = 0; i < length; i++) {
                Object obj = objArr[i];
                Integer numValueOf = (Integer) map.get(obj);
                if (numValueOf == null) {
                    numValueOf = Integer.valueOf(map.size());
                    map.put(obj, numValueOf);
                }
                iArr[i] = numValueOf.intValue();
            }
            return iArr;
        }

        private static Object[] compact(Object[] objArr, int[] iArr) {
            int length = objArr.length;
            Object[] objArr2 = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), GridLayout.max2(iArr, -1) + 1);
            for (int i = 0; i < length; i++) {
                objArr2[iArr[i]] = objArr[i];
            }
            return objArr2;
        }
    }

    static class Bounds {
        public int after;
        public int before;
        public int flexibility;

        Bounds() {
            reset();
        }

        protected void reset() {
            this.before = Integer.MIN_VALUE;
            this.after = Integer.MIN_VALUE;
            this.flexibility = 2;
        }

        protected void include(int i, int i2) {
            this.before = Math.max(this.before, i);
            this.after = Math.max(this.after, i2);
        }

        protected int size(boolean z) {
            if (z || !GridLayout.canStretch(this.flexibility)) {
                return this.before + this.after;
            }
            return 100000;
        }

        protected int getOffset(GridLayout gridLayout, View view, Alignment alignment, int i, boolean z) {
            return this.before - alignment.getAlignmentValue(view, i, ViewGroupCompat.getLayoutMode(gridLayout));
        }

        protected final void include(GridLayout gridLayout, View view, Spec spec, Axis axis, int i) {
            this.flexibility &= spec.getFlexibility();
            int alignmentValue = spec.getAbsoluteAlignment(axis.horizontal).getAlignmentValue(view, i, ViewGroupCompat.getLayoutMode(gridLayout));
            include(alignmentValue, i - alignmentValue);
        }

        public String toString() {
            return "Bounds{before=" + this.before + ", after=" + this.after + CoreConstants.CURLY_RIGHT;
        }
    }

    static final class Interval {
        public final int max;
        public final int min;

        public Interval(int i, int i2) {
            this.min = i;
            this.max = i2;
        }

        int size() {
            return this.max - this.min;
        }

        Interval inverse() {
            return new Interval(this.max, this.min);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Interval.class != obj.getClass()) {
                return false;
            }
            Interval interval = (Interval) obj;
            return this.max == interval.max && this.min == interval.min;
        }

        public int hashCode() {
            return (this.min * 31) + this.max;
        }

        public String toString() {
            return "[" + this.min + ", " + this.max + "]";
        }
    }

    public static class Spec {
        static final Spec UNDEFINED = GridLayout.spec(Integer.MIN_VALUE);
        final Alignment alignment;
        final Interval span;
        final boolean startDefined;
        final float weight;

        private Spec(boolean z, Interval interval, Alignment alignment, float f) {
            this.startDefined = z;
            this.span = interval;
            this.alignment = alignment;
            this.weight = f;
        }

        Spec(boolean z, int i, int i2, Alignment alignment, float f) {
            this(z, new Interval(i, i2 + i), alignment, f);
        }

        public Alignment getAbsoluteAlignment(boolean z) {
            Alignment alignment = this.alignment;
            if (alignment != GridLayout.UNDEFINED_ALIGNMENT) {
                return alignment;
            }
            if (this.weight == 0.0f) {
                return z ? GridLayout.START : GridLayout.BASELINE;
            }
            return GridLayout.FILL;
        }

        final Spec copyWriteSpan(Interval interval) {
            return new Spec(this.startDefined, interval, this.alignment, this.weight);
        }

        final int getFlexibility() {
            return (this.alignment == GridLayout.UNDEFINED_ALIGNMENT && this.weight == 0.0f) ? 0 : 2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Spec spec = (Spec) obj;
            return this.alignment.equals(spec.alignment) && this.span.equals(spec.span);
        }

        public int hashCode() {
            return (this.span.hashCode() * 31) + this.alignment.hashCode();
        }
    }

    public static Spec spec(int i, int i2, Alignment alignment, float f) {
        return new Spec(i != Integer.MIN_VALUE, i, i2, alignment, f);
    }

    public static Spec spec(int i, int i2, Alignment alignment) {
        return spec(i, i2, alignment, 0.0f);
    }

    public static Spec spec(int i, int i2) {
        return spec(i, i2, UNDEFINED_ALIGNMENT);
    }

    public static Spec spec(int i) {
        return spec(i, 1);
    }

    public static abstract class Alignment {
        abstract int getAlignmentValue(View view, int i, int i2);

        abstract String getDebugString();

        abstract int getGravityOffset(View view, int i);

        int getSizeInCell(View view, int i, int i2) {
            return i;
        }

        Alignment() {
        }

        Bounds getBounds() {
            return new Bounds();
        }

        public String toString() {
            return "Alignment:" + getDebugString();
        }
    }

    private static Alignment createSwitchingAlignment(final Alignment alignment, final Alignment alignment2) {
        return new Alignment() { // from class: androidx.gridlayout.widget.GridLayout.5
            @Override // androidx.gridlayout.widget.GridLayout.Alignment
            int getGravityOffset(View view, int i) {
                return (ViewCompat.getLayoutDirection(view) == 1 ? alignment2 : alignment).getGravityOffset(view, i);
            }

            @Override // androidx.gridlayout.widget.GridLayout.Alignment
            public int getAlignmentValue(View view, int i, int i2) {
                return (ViewCompat.getLayoutDirection(view) == 1 ? alignment2 : alignment).getAlignmentValue(view, i, i2);
            }

            @Override // androidx.gridlayout.widget.GridLayout.Alignment
            String getDebugString() {
                return "SWITCHING[L:" + alignment.getDebugString() + ", R:" + alignment2.getDebugString() + "]";
            }
        };
    }
}
