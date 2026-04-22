package androidx.compose.material.icons.filled;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: ExpandLess.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ExpandLessKt {
    private static ImageVector _expandLess;

    public static final ImageVector getExpandLess(Icons.Filled filled) {
        ImageVector imageVector = _expandLess;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.ExpandLess", Dp.m2416constructorimpl(24.0f), Dp.m2416constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.m1305getBlack0d7_KjU(), null);
        int iM1415getButtKaPHkGw = StrokeCap.Companion.m1415getButtKaPHkGw();
        int iM1422getBevelLxFBmk8 = StrokeJoin.Companion.m1422getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(12.0f, 8.0f);
        pathBuilder.lineToRelative(-6.0f, 6.0f);
        pathBuilder.lineToRelative(1.41f, 1.41f);
        pathBuilder.lineTo(12.0f, 10.83f);
        pathBuilder.lineToRelative(4.59f, 4.58f);
        pathBuilder.lineTo(18.0f, 14.0f);
        pathBuilder.close();
        ImageVector imageVectorBuild = builder.m1558addPathoIyEayM(pathBuilder.getNodes(), (14336 & 2) != 0 ? VectorKt.getDefaultFillType() : defaultFillType, (14336 & 4) != 0 ? "" : "", (14336 & 8) != 0 ? null : solidColor, (14336 & 16) != 0 ? 1.0f : 1.0f, (14336 & 32) == 0 ? null : null, (14336 & 64) != 0 ? 1.0f : 1.0f, (14336 & 128) != 0 ? 0.0f : 1.0f, (14336 & 256) != 0 ? VectorKt.getDefaultStrokeLineCap() : iM1415getButtKaPHkGw, (14336 & 512) != 0 ? VectorKt.getDefaultStrokeLineJoin() : iM1422getBevelLxFBmk8, (14336 & 1024) != 0 ? 4.0f : 1.0f, (14336 & 2048) != 0 ? 0.0f : 0.0f, (14336 & PKIFailureInfo.certConfirmed) == 0 ? 0.0f : 1.0f, (14336 & 8192) == 0 ? 0.0f : 0.0f).build();
        _expandLess = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
