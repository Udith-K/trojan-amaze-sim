package androidx.compose.ui.geometry;

/* JADX INFO: compiled from: RoundRect.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class RoundRectKt {
    public static final RoundRect RoundRect(float f, float f2, float f3, float f4, float f5, float f6) {
        long jCornerRadius = CornerRadiusKt.CornerRadius(f5, f6);
        return new RoundRect(f, f2, f3, f4, jCornerRadius, jCornerRadius, jCornerRadius, jCornerRadius, null);
    }

    /* JADX INFO: renamed from: RoundRect-gG7oq9Y, reason: not valid java name */
    public static final RoundRect m1189RoundRectgG7oq9Y(float f, float f2, float f3, float f4, long j) {
        return RoundRect(f, f2, f3, f4, CornerRadius.m1145getXimpl(j), CornerRadius.m1146getYimpl(j));
    }

    /* JADX INFO: renamed from: RoundRect-ZAM2FJo, reason: not valid java name */
    public static final RoundRect m1188RoundRectZAM2FJo(Rect rect, long j, long j2, long j3, long j4) {
        return new RoundRect(rect.getLeft(), rect.getTop(), rect.getRight(), rect.getBottom(), j, j2, j3, j4, null);
    }

    public static final Rect getBoundingRect(RoundRect roundRect) {
        return new Rect(roundRect.getLeft(), roundRect.getTop(), roundRect.getRight(), roundRect.getBottom());
    }

    public static final boolean isSimple(RoundRect roundRect) {
        return CornerRadius.m1145getXimpl(roundRect.m1186getTopLeftCornerRadiuskKHJgLs()) == CornerRadius.m1146getYimpl(roundRect.m1186getTopLeftCornerRadiuskKHJgLs()) && CornerRadius.m1145getXimpl(roundRect.m1186getTopLeftCornerRadiuskKHJgLs()) == CornerRadius.m1145getXimpl(roundRect.m1187getTopRightCornerRadiuskKHJgLs()) && CornerRadius.m1145getXimpl(roundRect.m1186getTopLeftCornerRadiuskKHJgLs()) == CornerRadius.m1146getYimpl(roundRect.m1187getTopRightCornerRadiuskKHJgLs()) && CornerRadius.m1145getXimpl(roundRect.m1186getTopLeftCornerRadiuskKHJgLs()) == CornerRadius.m1145getXimpl(roundRect.m1185getBottomRightCornerRadiuskKHJgLs()) && CornerRadius.m1145getXimpl(roundRect.m1186getTopLeftCornerRadiuskKHJgLs()) == CornerRadius.m1146getYimpl(roundRect.m1185getBottomRightCornerRadiuskKHJgLs()) && CornerRadius.m1145getXimpl(roundRect.m1186getTopLeftCornerRadiuskKHJgLs()) == CornerRadius.m1145getXimpl(roundRect.m1184getBottomLeftCornerRadiuskKHJgLs()) && CornerRadius.m1145getXimpl(roundRect.m1186getTopLeftCornerRadiuskKHJgLs()) == CornerRadius.m1146getYimpl(roundRect.m1184getBottomLeftCornerRadiuskKHJgLs());
    }
}
