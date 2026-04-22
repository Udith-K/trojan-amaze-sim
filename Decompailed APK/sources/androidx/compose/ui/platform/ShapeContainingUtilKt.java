package androidx.compose.ui.platform;

import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathOperation;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: compiled from: ShapeContainingUtil.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ShapeContainingUtilKt {
    public static /* synthetic */ boolean isInOutline$default(Outline outline, float f, float f2, Path path, Path path2, int i, Object obj) {
        if ((i & 8) != 0) {
            path = null;
        }
        if ((i & 16) != 0) {
            path2 = null;
        }
        return isInOutline(outline, f, f2, path, path2);
    }

    public static final boolean isInOutline(Outline outline, float f, float f2, Path path, Path path2) {
        if (outline instanceof Outline.Rectangle) {
            return isInRectangle(((Outline.Rectangle) outline).getRect(), f, f2);
        }
        if (outline instanceof Outline.Rounded) {
            return isInRoundedRect((Outline.Rounded) outline, f, f2, path, path2);
        }
        if (outline instanceof Outline.Generic) {
            return isInPath(((Outline.Generic) outline).getPath(), f, f2, path, path2);
        }
        throw new NoWhenBranchMatchedException();
    }

    private static final boolean isInRectangle(Rect rect, float f, float f2) {
        return rect.getLeft() <= f && f < rect.getRight() && rect.getTop() <= f2 && f2 < rect.getBottom();
    }

    private static final boolean isInRoundedRect(Outline.Rounded rounded, float f, float f2, Path path, Path path2) {
        RoundRect roundRect = rounded.getRoundRect();
        if (f < roundRect.getLeft() || f >= roundRect.getRight() || f2 < roundRect.getTop() || f2 >= roundRect.getBottom()) {
            return false;
        }
        if (!cornersFit(roundRect)) {
            Path Path = path2 == null ? AndroidPath_androidKt.Path() : path2;
            Path.CC.addRoundRect$default(Path, roundRect, null, 2, null);
            return isInPath(Path, f, f2, path, path2);
        }
        float fM1145getXimpl = CornerRadius.m1145getXimpl(roundRect.m1186getTopLeftCornerRadiuskKHJgLs()) + roundRect.getLeft();
        float fM1146getYimpl = CornerRadius.m1146getYimpl(roundRect.m1186getTopLeftCornerRadiuskKHJgLs()) + roundRect.getTop();
        float right = roundRect.getRight() - CornerRadius.m1145getXimpl(roundRect.m1187getTopRightCornerRadiuskKHJgLs());
        float fM1146getYimpl2 = CornerRadius.m1146getYimpl(roundRect.m1187getTopRightCornerRadiuskKHJgLs()) + roundRect.getTop();
        float right2 = roundRect.getRight() - CornerRadius.m1145getXimpl(roundRect.m1185getBottomRightCornerRadiuskKHJgLs());
        float bottom = roundRect.getBottom() - CornerRadius.m1146getYimpl(roundRect.m1185getBottomRightCornerRadiuskKHJgLs());
        float bottom2 = roundRect.getBottom() - CornerRadius.m1146getYimpl(roundRect.m1184getBottomLeftCornerRadiuskKHJgLs());
        float fM1145getXimpl2 = CornerRadius.m1145getXimpl(roundRect.m1184getBottomLeftCornerRadiuskKHJgLs()) + roundRect.getLeft();
        if (f < fM1145getXimpl && f2 < fM1146getYimpl) {
            return m1986isWithinEllipseVE1yxkc(f, f2, roundRect.m1186getTopLeftCornerRadiuskKHJgLs(), fM1145getXimpl, fM1146getYimpl);
        }
        if (f < fM1145getXimpl2 && f2 > bottom2) {
            return m1986isWithinEllipseVE1yxkc(f, f2, roundRect.m1184getBottomLeftCornerRadiuskKHJgLs(), fM1145getXimpl2, bottom2);
        }
        if (f > right && f2 < fM1146getYimpl2) {
            return m1986isWithinEllipseVE1yxkc(f, f2, roundRect.m1187getTopRightCornerRadiuskKHJgLs(), right, fM1146getYimpl2);
        }
        if (f <= right2 || f2 <= bottom) {
            return true;
        }
        return m1986isWithinEllipseVE1yxkc(f, f2, roundRect.m1185getBottomRightCornerRadiuskKHJgLs(), right2, bottom);
    }

    private static final boolean cornersFit(RoundRect roundRect) {
        return CornerRadius.m1145getXimpl(roundRect.m1186getTopLeftCornerRadiuskKHJgLs()) + CornerRadius.m1145getXimpl(roundRect.m1187getTopRightCornerRadiuskKHJgLs()) <= roundRect.getWidth() && CornerRadius.m1145getXimpl(roundRect.m1184getBottomLeftCornerRadiuskKHJgLs()) + CornerRadius.m1145getXimpl(roundRect.m1185getBottomRightCornerRadiuskKHJgLs()) <= roundRect.getWidth() && CornerRadius.m1146getYimpl(roundRect.m1186getTopLeftCornerRadiuskKHJgLs()) + CornerRadius.m1146getYimpl(roundRect.m1184getBottomLeftCornerRadiuskKHJgLs()) <= roundRect.getHeight() && CornerRadius.m1146getYimpl(roundRect.m1187getTopRightCornerRadiuskKHJgLs()) + CornerRadius.m1146getYimpl(roundRect.m1185getBottomRightCornerRadiuskKHJgLs()) <= roundRect.getHeight();
    }

    /* JADX INFO: renamed from: isWithinEllipse-VE1yxkc, reason: not valid java name */
    private static final boolean m1986isWithinEllipseVE1yxkc(float f, float f2, long j, float f3, float f4) {
        float f5 = f - f3;
        float f6 = f2 - f4;
        float fM1145getXimpl = CornerRadius.m1145getXimpl(j);
        float fM1146getYimpl = CornerRadius.m1146getYimpl(j);
        return ((f5 * f5) / (fM1145getXimpl * fM1145getXimpl)) + ((f6 * f6) / (fM1146getYimpl * fM1146getYimpl)) <= 1.0f;
    }

    private static final boolean isInPath(Path path, float f, float f2, Path path2, Path path3) {
        Rect rect = new Rect(f - 0.005f, f2 - 0.005f, f + 0.005f, f2 + 0.005f);
        if (path2 == null) {
            path2 = AndroidPath_androidKt.Path();
        }
        Path.CC.addRect$default(path2, rect, null, 2, null);
        if (path3 == null) {
            path3 = AndroidPath_androidKt.Path();
        }
        path3.mo1241opN5in7k0(path, path2, PathOperation.Companion.m1392getIntersectb3I0S0c());
        boolean zIsEmpty = path3.isEmpty();
        path3.reset();
        path2.reset();
        return !zIsEmpty;
    }
}
