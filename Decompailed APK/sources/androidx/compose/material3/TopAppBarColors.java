package androidx.compose.material3;

import androidx.compose.animation.core.EasingKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: AppBar.kt */
/* JADX INFO: loaded from: classes.dex */
public final class TopAppBarColors {
    private final long actionIconContentColor;
    private final long containerColor;
    private final long navigationIconContentColor;
    private final long scrolledContainerColor;
    private final long titleContentColor;

    public /* synthetic */ TopAppBarColors(long j, long j2, long j3, long j4, long j5, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, j2, j3, j4, j5);
    }

    private TopAppBarColors(long j, long j2, long j3, long j4, long j5) {
        this.containerColor = j;
        this.scrolledContainerColor = j2;
        this.navigationIconContentColor = j3;
        this.titleContentColor = j4;
        this.actionIconContentColor = j5;
    }

    /* JADX INFO: renamed from: getNavigationIconContentColor-0d7_KjU, reason: not valid java name */
    public final long m776getNavigationIconContentColor0d7_KjU() {
        return this.navigationIconContentColor;
    }

    /* JADX INFO: renamed from: getTitleContentColor-0d7_KjU, reason: not valid java name */
    public final long m777getTitleContentColor0d7_KjU() {
        return this.titleContentColor;
    }

    /* JADX INFO: renamed from: getActionIconContentColor-0d7_KjU, reason: not valid java name */
    public final long m775getActionIconContentColor0d7_KjU() {
        return this.actionIconContentColor;
    }

    /* JADX INFO: renamed from: containerColor-vNxB06k$material3_release, reason: not valid java name */
    public final long m774containerColorvNxB06k$material3_release(float f) {
        return ColorKt.m1314lerpjxsXWHM(this.containerColor, this.scrolledContainerColor, EasingKt.getFastOutLinearInEasing().transform(f));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TopAppBarColors)) {
            return false;
        }
        TopAppBarColors topAppBarColors = (TopAppBarColors) obj;
        return Color.m1296equalsimpl0(this.containerColor, topAppBarColors.containerColor) && Color.m1296equalsimpl0(this.scrolledContainerColor, topAppBarColors.scrolledContainerColor) && Color.m1296equalsimpl0(this.navigationIconContentColor, topAppBarColors.navigationIconContentColor) && Color.m1296equalsimpl0(this.titleContentColor, topAppBarColors.titleContentColor) && Color.m1296equalsimpl0(this.actionIconContentColor, topAppBarColors.actionIconContentColor);
    }

    public int hashCode() {
        return (((((((Color.m1302hashCodeimpl(this.containerColor) * 31) + Color.m1302hashCodeimpl(this.scrolledContainerColor)) * 31) + Color.m1302hashCodeimpl(this.navigationIconContentColor)) * 31) + Color.m1302hashCodeimpl(this.titleContentColor)) * 31) + Color.m1302hashCodeimpl(this.actionIconContentColor);
    }
}
