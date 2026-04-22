package androidx.compose.material3;

import androidx.compose.ui.graphics.Color;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Switch.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SwitchColors {
    private final long checkedBorderColor;
    private final long checkedIconColor;
    private final long checkedThumbColor;
    private final long checkedTrackColor;
    private final long disabledCheckedBorderColor;
    private final long disabledCheckedIconColor;
    private final long disabledCheckedThumbColor;
    private final long disabledCheckedTrackColor;
    private final long disabledUncheckedBorderColor;
    private final long disabledUncheckedIconColor;
    private final long disabledUncheckedThumbColor;
    private final long disabledUncheckedTrackColor;
    private final long uncheckedBorderColor;
    private final long uncheckedIconColor;
    private final long uncheckedThumbColor;
    private final long uncheckedTrackColor;

    public /* synthetic */ SwitchColors(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, j4, j5, j6, j7, j8, j9, j10, j11, j12, j13, j14, j15, j16);
    }

    private SwitchColors(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16) {
        this.checkedThumbColor = j;
        this.checkedTrackColor = j2;
        this.checkedBorderColor = j3;
        this.checkedIconColor = j4;
        this.uncheckedThumbColor = j5;
        this.uncheckedTrackColor = j6;
        this.uncheckedBorderColor = j7;
        this.uncheckedIconColor = j8;
        this.disabledCheckedThumbColor = j9;
        this.disabledCheckedTrackColor = j10;
        this.disabledCheckedBorderColor = j11;
        this.disabledCheckedIconColor = j12;
        this.disabledUncheckedThumbColor = j13;
        this.disabledUncheckedTrackColor = j14;
        this.disabledUncheckedBorderColor = j15;
        this.disabledUncheckedIconColor = j16;
    }

    /* JADX INFO: renamed from: thumbColor-WaAFU9c$material3_release, reason: not valid java name */
    public final long m742thumbColorWaAFU9c$material3_release(boolean z, boolean z2) {
        return z ? z2 ? this.checkedThumbColor : this.uncheckedThumbColor : z2 ? this.disabledCheckedThumbColor : this.disabledUncheckedThumbColor;
    }

    /* JADX INFO: renamed from: trackColor-WaAFU9c$material3_release, reason: not valid java name */
    public final long m743trackColorWaAFU9c$material3_release(boolean z, boolean z2) {
        return z ? z2 ? this.checkedTrackColor : this.uncheckedTrackColor : z2 ? this.disabledCheckedTrackColor : this.disabledUncheckedTrackColor;
    }

    /* JADX INFO: renamed from: borderColor-WaAFU9c$material3_release, reason: not valid java name */
    public final long m740borderColorWaAFU9c$material3_release(boolean z, boolean z2) {
        return z ? z2 ? this.checkedBorderColor : this.uncheckedBorderColor : z2 ? this.disabledCheckedBorderColor : this.disabledUncheckedBorderColor;
    }

    /* JADX INFO: renamed from: iconColor-WaAFU9c$material3_release, reason: not valid java name */
    public final long m741iconColorWaAFU9c$material3_release(boolean z, boolean z2) {
        return z ? z2 ? this.checkedIconColor : this.uncheckedIconColor : z2 ? this.disabledCheckedIconColor : this.disabledUncheckedIconColor;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SwitchColors)) {
            return false;
        }
        SwitchColors switchColors = (SwitchColors) obj;
        return Color.m1296equalsimpl0(this.checkedThumbColor, switchColors.checkedThumbColor) && Color.m1296equalsimpl0(this.checkedTrackColor, switchColors.checkedTrackColor) && Color.m1296equalsimpl0(this.checkedBorderColor, switchColors.checkedBorderColor) && Color.m1296equalsimpl0(this.checkedIconColor, switchColors.checkedIconColor) && Color.m1296equalsimpl0(this.uncheckedThumbColor, switchColors.uncheckedThumbColor) && Color.m1296equalsimpl0(this.uncheckedTrackColor, switchColors.uncheckedTrackColor) && Color.m1296equalsimpl0(this.uncheckedBorderColor, switchColors.uncheckedBorderColor) && Color.m1296equalsimpl0(this.uncheckedIconColor, switchColors.uncheckedIconColor) && Color.m1296equalsimpl0(this.disabledCheckedThumbColor, switchColors.disabledCheckedThumbColor) && Color.m1296equalsimpl0(this.disabledCheckedTrackColor, switchColors.disabledCheckedTrackColor) && Color.m1296equalsimpl0(this.disabledCheckedBorderColor, switchColors.disabledCheckedBorderColor) && Color.m1296equalsimpl0(this.disabledCheckedIconColor, switchColors.disabledCheckedIconColor) && Color.m1296equalsimpl0(this.disabledUncheckedThumbColor, switchColors.disabledUncheckedThumbColor) && Color.m1296equalsimpl0(this.disabledUncheckedTrackColor, switchColors.disabledUncheckedTrackColor) && Color.m1296equalsimpl0(this.disabledUncheckedBorderColor, switchColors.disabledUncheckedBorderColor) && Color.m1296equalsimpl0(this.disabledUncheckedIconColor, switchColors.disabledUncheckedIconColor);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((Color.m1302hashCodeimpl(this.checkedThumbColor) * 31) + Color.m1302hashCodeimpl(this.checkedTrackColor)) * 31) + Color.m1302hashCodeimpl(this.checkedBorderColor)) * 31) + Color.m1302hashCodeimpl(this.checkedIconColor)) * 31) + Color.m1302hashCodeimpl(this.uncheckedThumbColor)) * 31) + Color.m1302hashCodeimpl(this.uncheckedTrackColor)) * 31) + Color.m1302hashCodeimpl(this.uncheckedBorderColor)) * 31) + Color.m1302hashCodeimpl(this.uncheckedIconColor)) * 31) + Color.m1302hashCodeimpl(this.disabledCheckedThumbColor)) * 31) + Color.m1302hashCodeimpl(this.disabledCheckedTrackColor)) * 31) + Color.m1302hashCodeimpl(this.disabledCheckedBorderColor)) * 31) + Color.m1302hashCodeimpl(this.disabledCheckedIconColor)) * 31) + Color.m1302hashCodeimpl(this.disabledUncheckedThumbColor)) * 31) + Color.m1302hashCodeimpl(this.disabledUncheckedTrackColor)) * 31) + Color.m1302hashCodeimpl(this.disabledUncheckedBorderColor)) * 31) + Color.m1302hashCodeimpl(this.disabledUncheckedIconColor);
    }
}
