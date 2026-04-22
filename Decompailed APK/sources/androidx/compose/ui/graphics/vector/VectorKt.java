package androidx.compose.ui.graphics.vector;

import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.BlendModeColorFilter;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.PathFillType;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import java.util.List;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: Vector.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class VectorKt {
    private static final List EmptyPath = CollectionsKt.emptyList();
    private static final int DefaultStrokeLineCap = StrokeCap.Companion.m1415getButtKaPHkGw();
    private static final int DefaultStrokeLineJoin = StrokeJoin.Companion.m1423getMiterLxFBmk8();
    private static final int DefaultTintBlendMode = BlendMode.Companion.m1274getSrcIn0nO6VwU();
    private static final long DefaultTintColor = Color.Companion.m1308getTransparent0d7_KjU();
    private static final int DefaultFillType = PathFillType.Companion.m1388getNonZeroRgk1Os();

    public static final List getEmptyPath() {
        return EmptyPath;
    }

    public static final int getDefaultStrokeLineCap() {
        return DefaultStrokeLineCap;
    }

    public static final int getDefaultStrokeLineJoin() {
        return DefaultStrokeLineJoin;
    }

    public static final int getDefaultFillType() {
        return DefaultFillType;
    }

    /* JADX INFO: renamed from: rgbEqual--OWjLjI, reason: not valid java name */
    public static final boolean m1566rgbEqualOWjLjI(long j, long j2) {
        return Color.m1301getRedimpl(j) == Color.m1301getRedimpl(j2) && Color.m1300getGreenimpl(j) == Color.m1300getGreenimpl(j2) && Color.m1298getBlueimpl(j) == Color.m1298getBlueimpl(j2);
    }

    public static final boolean tintableWithAlphaMask(ColorFilter colorFilter) {
        if (colorFilter instanceof BlendModeColorFilter) {
            BlendModeColorFilter blendModeColorFilter = (BlendModeColorFilter) colorFilter;
            int iM1278getBlendMode0nO6VwU = blendModeColorFilter.m1278getBlendMode0nO6VwU();
            BlendMode.Companion companion = BlendMode.Companion;
            if (BlendMode.m1246equalsimpl0(iM1278getBlendMode0nO6VwU, companion.m1274getSrcIn0nO6VwU()) || BlendMode.m1246equalsimpl0(blendModeColorFilter.m1278getBlendMode0nO6VwU(), companion.m1276getSrcOver0nO6VwU())) {
                return true;
            }
        } else if (colorFilter == null) {
            return true;
        }
        return false;
    }
}
