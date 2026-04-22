package androidx.compose.foundation;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OverscrollConfiguration.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class OverscrollConfiguration {
    private final PaddingValues drawPadding;
    private final long glowColor;

    public /* synthetic */ OverscrollConfiguration(long j, PaddingValues paddingValues, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, paddingValues);
    }

    private OverscrollConfiguration(long j, PaddingValues paddingValues) {
        this.glowColor = j;
        this.drawPadding = paddingValues;
    }

    public /* synthetic */ OverscrollConfiguration(long j, PaddingValues paddingValues, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? ColorKt.Color(4284900966L) : j, (i & 2) != 0 ? PaddingKt.m259PaddingValuesYgX7TsA$default(0.0f, 0.0f, 3, null) : paddingValues, null);
    }

    /* JADX INFO: renamed from: getGlowColor-0d7_KjU, reason: not valid java name */
    public final long m141getGlowColor0d7_KjU() {
        return this.glowColor;
    }

    public final PaddingValues getDrawPadding() {
        return this.drawPadding;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(OverscrollConfiguration.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.foundation.OverscrollConfiguration");
        OverscrollConfiguration overscrollConfiguration = (OverscrollConfiguration) obj;
        return Color.m1296equalsimpl0(this.glowColor, overscrollConfiguration.glowColor) && Intrinsics.areEqual(this.drawPadding, overscrollConfiguration.drawPadding);
    }

    public int hashCode() {
        return (Color.m1302hashCodeimpl(this.glowColor) * 31) + this.drawPadding.hashCode();
    }

    public String toString() {
        return "OverscrollConfiguration(glowColor=" + ((Object) Color.m1303toStringimpl(this.glowColor)) + ", drawPadding=" + this.drawPadding + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
