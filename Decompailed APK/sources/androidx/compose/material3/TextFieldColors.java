package androidx.compose.material3;

import androidx.compose.foundation.text.selection.TextSelectionColors;
import androidx.compose.ui.graphics.Color;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextFieldDefaults.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TextFieldColors {
    private final long cursorColor;
    private final long disabledContainerColor;
    private final long disabledIndicatorColor;
    private final long disabledLabelColor;
    private final long disabledLeadingIconColor;
    private final long disabledPlaceholderColor;
    private final long disabledPrefixColor;
    private final long disabledSuffixColor;
    private final long disabledSupportingTextColor;
    private final long disabledTextColor;
    private final long disabledTrailingIconColor;
    private final long errorContainerColor;
    private final long errorCursorColor;
    private final long errorIndicatorColor;
    private final long errorLabelColor;
    private final long errorLeadingIconColor;
    private final long errorPlaceholderColor;
    private final long errorPrefixColor;
    private final long errorSuffixColor;
    private final long errorSupportingTextColor;
    private final long errorTextColor;
    private final long errorTrailingIconColor;
    private final long focusedContainerColor;
    private final long focusedIndicatorColor;
    private final long focusedLabelColor;
    private final long focusedLeadingIconColor;
    private final long focusedPlaceholderColor;
    private final long focusedPrefixColor;
    private final long focusedSuffixColor;
    private final long focusedSupportingTextColor;
    private final long focusedTextColor;
    private final long focusedTrailingIconColor;
    private final TextSelectionColors textSelectionColors;
    private final long unfocusedContainerColor;
    private final long unfocusedIndicatorColor;
    private final long unfocusedLabelColor;
    private final long unfocusedLeadingIconColor;
    private final long unfocusedPlaceholderColor;
    private final long unfocusedPrefixColor;
    private final long unfocusedSuffixColor;
    private final long unfocusedSupportingTextColor;
    private final long unfocusedTextColor;
    private final long unfocusedTrailingIconColor;

    public /* synthetic */ TextFieldColors(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, TextSelectionColors textSelectionColors, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, textSelectionColors, j11, j12, j13, j14, j15, j16, j17, j18, j19, j20, j21, j22, j23, j24, j25, j26, j27, j28, j29, j30, j31, j32, j33, j34, j35, j36, j37, j38, j39, j40, j41, j42);
    }

    private TextFieldColors(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, TextSelectionColors textSelectionColors, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42) {
        this.focusedTextColor = j;
        this.unfocusedTextColor = j2;
        this.disabledTextColor = j3;
        this.errorTextColor = j4;
        this.focusedContainerColor = j5;
        this.unfocusedContainerColor = j6;
        this.disabledContainerColor = j7;
        this.errorContainerColor = j8;
        this.cursorColor = j9;
        this.errorCursorColor = j10;
        this.textSelectionColors = textSelectionColors;
        this.focusedIndicatorColor = j11;
        this.unfocusedIndicatorColor = j12;
        this.disabledIndicatorColor = j13;
        this.errorIndicatorColor = j14;
        this.focusedLeadingIconColor = j15;
        this.unfocusedLeadingIconColor = j16;
        this.disabledLeadingIconColor = j17;
        this.errorLeadingIconColor = j18;
        this.focusedTrailingIconColor = j19;
        this.unfocusedTrailingIconColor = j20;
        this.disabledTrailingIconColor = j21;
        this.errorTrailingIconColor = j22;
        this.focusedLabelColor = j23;
        this.unfocusedLabelColor = j24;
        this.disabledLabelColor = j25;
        this.errorLabelColor = j26;
        this.focusedPlaceholderColor = j27;
        this.unfocusedPlaceholderColor = j28;
        this.disabledPlaceholderColor = j29;
        this.errorPlaceholderColor = j30;
        this.focusedSupportingTextColor = j31;
        this.unfocusedSupportingTextColor = j32;
        this.disabledSupportingTextColor = j33;
        this.errorSupportingTextColor = j34;
        this.focusedPrefixColor = j35;
        this.unfocusedPrefixColor = j36;
        this.disabledPrefixColor = j37;
        this.errorPrefixColor = j38;
        this.focusedSuffixColor = j39;
        this.unfocusedSuffixColor = j40;
        this.disabledSuffixColor = j41;
        this.errorSuffixColor = j42;
    }

    public final TextSelectionColors getTextSelectionColors() {
        return this.textSelectionColors;
    }

    /* JADX INFO: renamed from: copy-ejIjP34, reason: not valid java name */
    public final TextFieldColors m745copyejIjP34(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, TextSelectionColors textSelectionColors, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, long j33, long j34, long j35, long j36, long j37, long j38, long j39, long j40, long j41, long j42) {
        return new TextFieldColors(j != 16 ? j : this.focusedTextColor, j2 != 16 ? j2 : this.unfocusedTextColor, j3 != 16 ? j3 : this.disabledTextColor, j4 != 16 ? j4 : this.errorTextColor, j5 != 16 ? j5 : this.focusedContainerColor, j6 != 16 ? j6 : this.unfocusedContainerColor, j7 != 16 ? j7 : this.disabledContainerColor, j8 != 16 ? j8 : this.errorContainerColor, j9 != 16 ? j9 : this.cursorColor, j10 != 16 ? j10 : this.errorCursorColor, takeOrElse$material3_release(textSelectionColors, new Function0() { // from class: androidx.compose.material3.TextFieldColors$copy$11
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final TextSelectionColors invoke() {
                return this.this$0.getTextSelectionColors();
            }
        }), j11 != 16 ? j11 : this.focusedIndicatorColor, j12 != 16 ? j12 : this.unfocusedIndicatorColor, j13 != 16 ? j13 : this.disabledIndicatorColor, j14 != 16 ? j14 : this.errorIndicatorColor, j15 != 16 ? j15 : this.focusedLeadingIconColor, j16 != 16 ? j16 : this.unfocusedLeadingIconColor, j17 != 16 ? j17 : this.disabledLeadingIconColor, j18 != 16 ? j18 : this.errorLeadingIconColor, j19 != 16 ? j19 : this.focusedTrailingIconColor, j20 != 16 ? j20 : this.unfocusedTrailingIconColor, j21 != 16 ? j21 : this.disabledTrailingIconColor, j22 != 16 ? j22 : this.errorTrailingIconColor, j23 != 16 ? j23 : this.focusedLabelColor, j24 != 16 ? j24 : this.unfocusedLabelColor, j25 != 16 ? j25 : this.disabledLabelColor, j26 != 16 ? j26 : this.errorLabelColor, j27 != 16 ? j27 : this.focusedPlaceholderColor, j28 != 16 ? j28 : this.unfocusedPlaceholderColor, j29 != 16 ? j29 : this.disabledPlaceholderColor, j30 != 16 ? j30 : this.errorPlaceholderColor, j31 != 16 ? j31 : this.focusedSupportingTextColor, j32 != 16 ? j32 : this.unfocusedSupportingTextColor, j33 != 16 ? j33 : this.disabledSupportingTextColor, j34 != 16 ? j34 : this.errorSupportingTextColor, j35 != 16 ? j35 : this.focusedPrefixColor, j36 != 16 ? j36 : this.unfocusedPrefixColor, j37 != 16 ? j37 : this.disabledPrefixColor, j38 != 16 ? j38 : this.errorPrefixColor, j39 != 16 ? j39 : this.focusedSuffixColor, j40 != 16 ? j40 : this.unfocusedSuffixColor, j41 != 16 ? j41 : this.disabledSuffixColor, j42 != 16 ? j42 : this.errorSuffixColor, null);
    }

    public final TextSelectionColors takeOrElse$material3_release(TextSelectionColors textSelectionColors, Function0 function0) {
        return textSelectionColors == null ? (TextSelectionColors) function0.invoke() : textSelectionColors;
    }

    /* JADX INFO: renamed from: leadingIconColor-XeAY9LY$material3_release, reason: not valid java name */
    public final long m749leadingIconColorXeAY9LY$material3_release(boolean z, boolean z2, boolean z3) {
        if (!z) {
            return this.disabledLeadingIconColor;
        }
        if (z2) {
            return this.errorLeadingIconColor;
        }
        if (z3) {
            return this.focusedLeadingIconColor;
        }
        return this.unfocusedLeadingIconColor;
    }

    /* JADX INFO: renamed from: trailingIconColor-XeAY9LY$material3_release, reason: not valid java name */
    public final long m755trailingIconColorXeAY9LY$material3_release(boolean z, boolean z2, boolean z3) {
        if (!z) {
            return this.disabledTrailingIconColor;
        }
        if (z2) {
            return this.errorTrailingIconColor;
        }
        if (z3) {
            return this.focusedTrailingIconColor;
        }
        return this.unfocusedTrailingIconColor;
    }

    /* JADX INFO: renamed from: indicatorColor-XeAY9LY$material3_release, reason: not valid java name */
    public final long m747indicatorColorXeAY9LY$material3_release(boolean z, boolean z2, boolean z3) {
        if (!z) {
            return this.disabledIndicatorColor;
        }
        if (z2) {
            return this.errorIndicatorColor;
        }
        if (z3) {
            return this.focusedIndicatorColor;
        }
        return this.unfocusedIndicatorColor;
    }

    /* JADX INFO: renamed from: containerColor-XeAY9LY$material3_release, reason: not valid java name */
    public final long m744containerColorXeAY9LY$material3_release(boolean z, boolean z2, boolean z3) {
        if (!z) {
            return this.disabledContainerColor;
        }
        if (z2) {
            return this.errorContainerColor;
        }
        if (z3) {
            return this.focusedContainerColor;
        }
        return this.unfocusedContainerColor;
    }

    /* JADX INFO: renamed from: placeholderColor-XeAY9LY$material3_release, reason: not valid java name */
    public final long m750placeholderColorXeAY9LY$material3_release(boolean z, boolean z2, boolean z3) {
        if (!z) {
            return this.disabledPlaceholderColor;
        }
        if (z2) {
            return this.errorPlaceholderColor;
        }
        if (z3) {
            return this.focusedPlaceholderColor;
        }
        return this.unfocusedPlaceholderColor;
    }

    /* JADX INFO: renamed from: labelColor-XeAY9LY$material3_release, reason: not valid java name */
    public final long m748labelColorXeAY9LY$material3_release(boolean z, boolean z2, boolean z3) {
        if (!z) {
            return this.disabledLabelColor;
        }
        if (z2) {
            return this.errorLabelColor;
        }
        if (z3) {
            return this.focusedLabelColor;
        }
        return this.unfocusedLabelColor;
    }

    /* JADX INFO: renamed from: textColor-XeAY9LY$material3_release, reason: not valid java name */
    public final long m754textColorXeAY9LY$material3_release(boolean z, boolean z2, boolean z3) {
        if (!z) {
            return this.disabledTextColor;
        }
        if (z2) {
            return this.errorTextColor;
        }
        if (z3) {
            return this.focusedTextColor;
        }
        return this.unfocusedTextColor;
    }

    /* JADX INFO: renamed from: supportingTextColor-XeAY9LY$material3_release, reason: not valid java name */
    public final long m753supportingTextColorXeAY9LY$material3_release(boolean z, boolean z2, boolean z3) {
        if (!z) {
            return this.disabledSupportingTextColor;
        }
        if (z2) {
            return this.errorSupportingTextColor;
        }
        if (z3) {
            return this.focusedSupportingTextColor;
        }
        return this.unfocusedSupportingTextColor;
    }

    /* JADX INFO: renamed from: prefixColor-XeAY9LY$material3_release, reason: not valid java name */
    public final long m751prefixColorXeAY9LY$material3_release(boolean z, boolean z2, boolean z3) {
        if (!z) {
            return this.disabledPrefixColor;
        }
        if (z2) {
            return this.errorPrefixColor;
        }
        if (z3) {
            return this.focusedPrefixColor;
        }
        return this.unfocusedPrefixColor;
    }

    /* JADX INFO: renamed from: suffixColor-XeAY9LY$material3_release, reason: not valid java name */
    public final long m752suffixColorXeAY9LY$material3_release(boolean z, boolean z2, boolean z3) {
        if (!z) {
            return this.disabledSuffixColor;
        }
        if (z2) {
            return this.errorSuffixColor;
        }
        if (z3) {
            return this.focusedSuffixColor;
        }
        return this.unfocusedSuffixColor;
    }

    /* JADX INFO: renamed from: cursorColor-vNxB06k$material3_release, reason: not valid java name */
    public final long m746cursorColorvNxB06k$material3_release(boolean z) {
        return z ? this.errorCursorColor : this.cursorColor;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TextFieldColors)) {
            return false;
        }
        TextFieldColors textFieldColors = (TextFieldColors) obj;
        return Color.m1296equalsimpl0(this.focusedTextColor, textFieldColors.focusedTextColor) && Color.m1296equalsimpl0(this.unfocusedTextColor, textFieldColors.unfocusedTextColor) && Color.m1296equalsimpl0(this.disabledTextColor, textFieldColors.disabledTextColor) && Color.m1296equalsimpl0(this.errorTextColor, textFieldColors.errorTextColor) && Color.m1296equalsimpl0(this.focusedContainerColor, textFieldColors.focusedContainerColor) && Color.m1296equalsimpl0(this.unfocusedContainerColor, textFieldColors.unfocusedContainerColor) && Color.m1296equalsimpl0(this.disabledContainerColor, textFieldColors.disabledContainerColor) && Color.m1296equalsimpl0(this.errorContainerColor, textFieldColors.errorContainerColor) && Color.m1296equalsimpl0(this.cursorColor, textFieldColors.cursorColor) && Color.m1296equalsimpl0(this.errorCursorColor, textFieldColors.errorCursorColor) && Intrinsics.areEqual(this.textSelectionColors, textFieldColors.textSelectionColors) && Color.m1296equalsimpl0(this.focusedIndicatorColor, textFieldColors.focusedIndicatorColor) && Color.m1296equalsimpl0(this.unfocusedIndicatorColor, textFieldColors.unfocusedIndicatorColor) && Color.m1296equalsimpl0(this.disabledIndicatorColor, textFieldColors.disabledIndicatorColor) && Color.m1296equalsimpl0(this.errorIndicatorColor, textFieldColors.errorIndicatorColor) && Color.m1296equalsimpl0(this.focusedLeadingIconColor, textFieldColors.focusedLeadingIconColor) && Color.m1296equalsimpl0(this.unfocusedLeadingIconColor, textFieldColors.unfocusedLeadingIconColor) && Color.m1296equalsimpl0(this.disabledLeadingIconColor, textFieldColors.disabledLeadingIconColor) && Color.m1296equalsimpl0(this.errorLeadingIconColor, textFieldColors.errorLeadingIconColor) && Color.m1296equalsimpl0(this.focusedTrailingIconColor, textFieldColors.focusedTrailingIconColor) && Color.m1296equalsimpl0(this.unfocusedTrailingIconColor, textFieldColors.unfocusedTrailingIconColor) && Color.m1296equalsimpl0(this.disabledTrailingIconColor, textFieldColors.disabledTrailingIconColor) && Color.m1296equalsimpl0(this.errorTrailingIconColor, textFieldColors.errorTrailingIconColor) && Color.m1296equalsimpl0(this.focusedLabelColor, textFieldColors.focusedLabelColor) && Color.m1296equalsimpl0(this.unfocusedLabelColor, textFieldColors.unfocusedLabelColor) && Color.m1296equalsimpl0(this.disabledLabelColor, textFieldColors.disabledLabelColor) && Color.m1296equalsimpl0(this.errorLabelColor, textFieldColors.errorLabelColor) && Color.m1296equalsimpl0(this.focusedPlaceholderColor, textFieldColors.focusedPlaceholderColor) && Color.m1296equalsimpl0(this.unfocusedPlaceholderColor, textFieldColors.unfocusedPlaceholderColor) && Color.m1296equalsimpl0(this.disabledPlaceholderColor, textFieldColors.disabledPlaceholderColor) && Color.m1296equalsimpl0(this.errorPlaceholderColor, textFieldColors.errorPlaceholderColor) && Color.m1296equalsimpl0(this.focusedSupportingTextColor, textFieldColors.focusedSupportingTextColor) && Color.m1296equalsimpl0(this.unfocusedSupportingTextColor, textFieldColors.unfocusedSupportingTextColor) && Color.m1296equalsimpl0(this.disabledSupportingTextColor, textFieldColors.disabledSupportingTextColor) && Color.m1296equalsimpl0(this.errorSupportingTextColor, textFieldColors.errorSupportingTextColor) && Color.m1296equalsimpl0(this.focusedPrefixColor, textFieldColors.focusedPrefixColor) && Color.m1296equalsimpl0(this.unfocusedPrefixColor, textFieldColors.unfocusedPrefixColor) && Color.m1296equalsimpl0(this.disabledPrefixColor, textFieldColors.disabledPrefixColor) && Color.m1296equalsimpl0(this.errorPrefixColor, textFieldColors.errorPrefixColor) && Color.m1296equalsimpl0(this.focusedSuffixColor, textFieldColors.focusedSuffixColor) && Color.m1296equalsimpl0(this.unfocusedSuffixColor, textFieldColors.unfocusedSuffixColor) && Color.m1296equalsimpl0(this.disabledSuffixColor, textFieldColors.disabledSuffixColor) && Color.m1296equalsimpl0(this.errorSuffixColor, textFieldColors.errorSuffixColor);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((Color.m1302hashCodeimpl(this.focusedTextColor) * 31) + Color.m1302hashCodeimpl(this.unfocusedTextColor)) * 31) + Color.m1302hashCodeimpl(this.disabledTextColor)) * 31) + Color.m1302hashCodeimpl(this.errorTextColor)) * 31) + Color.m1302hashCodeimpl(this.focusedContainerColor)) * 31) + Color.m1302hashCodeimpl(this.unfocusedContainerColor)) * 31) + Color.m1302hashCodeimpl(this.disabledContainerColor)) * 31) + Color.m1302hashCodeimpl(this.errorContainerColor)) * 31) + Color.m1302hashCodeimpl(this.cursorColor)) * 31) + Color.m1302hashCodeimpl(this.errorCursorColor)) * 31) + this.textSelectionColors.hashCode()) * 31) + Color.m1302hashCodeimpl(this.focusedIndicatorColor)) * 31) + Color.m1302hashCodeimpl(this.unfocusedIndicatorColor)) * 31) + Color.m1302hashCodeimpl(this.disabledIndicatorColor)) * 31) + Color.m1302hashCodeimpl(this.errorIndicatorColor)) * 31) + Color.m1302hashCodeimpl(this.focusedLeadingIconColor)) * 31) + Color.m1302hashCodeimpl(this.unfocusedLeadingIconColor)) * 31) + Color.m1302hashCodeimpl(this.disabledLeadingIconColor)) * 31) + Color.m1302hashCodeimpl(this.errorLeadingIconColor)) * 31) + Color.m1302hashCodeimpl(this.focusedTrailingIconColor)) * 31) + Color.m1302hashCodeimpl(this.unfocusedTrailingIconColor)) * 31) + Color.m1302hashCodeimpl(this.disabledTrailingIconColor)) * 31) + Color.m1302hashCodeimpl(this.errorTrailingIconColor)) * 31) + Color.m1302hashCodeimpl(this.focusedLabelColor)) * 31) + Color.m1302hashCodeimpl(this.unfocusedLabelColor)) * 31) + Color.m1302hashCodeimpl(this.disabledLabelColor)) * 31) + Color.m1302hashCodeimpl(this.errorLabelColor)) * 31) + Color.m1302hashCodeimpl(this.focusedPlaceholderColor)) * 31) + Color.m1302hashCodeimpl(this.unfocusedPlaceholderColor)) * 31) + Color.m1302hashCodeimpl(this.disabledPlaceholderColor)) * 31) + Color.m1302hashCodeimpl(this.errorPlaceholderColor)) * 31) + Color.m1302hashCodeimpl(this.focusedSupportingTextColor)) * 31) + Color.m1302hashCodeimpl(this.unfocusedSupportingTextColor)) * 31) + Color.m1302hashCodeimpl(this.disabledSupportingTextColor)) * 31) + Color.m1302hashCodeimpl(this.errorSupportingTextColor)) * 31) + Color.m1302hashCodeimpl(this.focusedPrefixColor)) * 31) + Color.m1302hashCodeimpl(this.unfocusedPrefixColor)) * 31) + Color.m1302hashCodeimpl(this.disabledPrefixColor)) * 31) + Color.m1302hashCodeimpl(this.errorPrefixColor)) * 31) + Color.m1302hashCodeimpl(this.focusedSuffixColor)) * 31) + Color.m1302hashCodeimpl(this.unfocusedSuffixColor)) * 31) + Color.m1302hashCodeimpl(this.disabledSuffixColor)) * 31) + Color.m1302hashCodeimpl(this.errorSuffixColor);
    }
}
