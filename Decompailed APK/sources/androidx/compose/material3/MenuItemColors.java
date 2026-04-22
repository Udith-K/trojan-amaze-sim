package androidx.compose.material3;

import androidx.compose.ui.graphics.Color;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Menu.kt */
/* JADX INFO: loaded from: classes.dex */
public final class MenuItemColors {
    private final long disabledLeadingIconColor;
    private final long disabledTextColor;
    private final long disabledTrailingIconColor;
    private final long leadingIconColor;
    private final long textColor;
    private final long trailingIconColor;

    public /* synthetic */ MenuItemColors(long j, long j2, long j3, long j4, long j5, long j6, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, j4, j5, j6);
    }

    private MenuItemColors(long j, long j2, long j3, long j4, long j5, long j6) {
        this.textColor = j;
        this.leadingIconColor = j2;
        this.trailingIconColor = j3;
        this.disabledTextColor = j4;
        this.disabledLeadingIconColor = j5;
        this.disabledTrailingIconColor = j6;
    }

    /* JADX INFO: renamed from: textColor-vNxB06k$material3_release, reason: not valid java name */
    public final long m697textColorvNxB06k$material3_release(boolean z) {
        return z ? this.textColor : this.disabledTextColor;
    }

    /* JADX INFO: renamed from: leadingIconColor-vNxB06k$material3_release, reason: not valid java name */
    public final long m696leadingIconColorvNxB06k$material3_release(boolean z) {
        return z ? this.leadingIconColor : this.disabledLeadingIconColor;
    }

    /* JADX INFO: renamed from: trailingIconColor-vNxB06k$material3_release, reason: not valid java name */
    public final long m698trailingIconColorvNxB06k$material3_release(boolean z) {
        return z ? this.trailingIconColor : this.disabledTrailingIconColor;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof MenuItemColors)) {
            return false;
        }
        MenuItemColors menuItemColors = (MenuItemColors) obj;
        return Color.m1296equalsimpl0(this.textColor, menuItemColors.textColor) && Color.m1296equalsimpl0(this.leadingIconColor, menuItemColors.leadingIconColor) && Color.m1296equalsimpl0(this.trailingIconColor, menuItemColors.trailingIconColor) && Color.m1296equalsimpl0(this.disabledTextColor, menuItemColors.disabledTextColor) && Color.m1296equalsimpl0(this.disabledLeadingIconColor, menuItemColors.disabledLeadingIconColor) && Color.m1296equalsimpl0(this.disabledTrailingIconColor, menuItemColors.disabledTrailingIconColor);
    }

    public int hashCode() {
        return (((((((((Color.m1302hashCodeimpl(this.textColor) * 31) + Color.m1302hashCodeimpl(this.leadingIconColor)) * 31) + Color.m1302hashCodeimpl(this.trailingIconColor)) * 31) + Color.m1302hashCodeimpl(this.disabledTextColor)) * 31) + Color.m1302hashCodeimpl(this.disabledLeadingIconColor)) * 31) + Color.m1302hashCodeimpl(this.disabledTrailingIconColor);
    }
}
