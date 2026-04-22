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

/* JADX INFO: compiled from: DeleteForever.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class DeleteForeverKt {
    private static ImageVector _deleteForever;

    public static final ImageVector getDeleteForever(Icons.Filled filled) {
        ImageVector imageVector = _deleteForever;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.DeleteForever", Dp.m2416constructorimpl(24.0f), Dp.m2416constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.m1305getBlack0d7_KjU(), null);
        int iM1415getButtKaPHkGw = StrokeCap.Companion.m1415getButtKaPHkGw();
        int iM1422getBevelLxFBmk8 = StrokeJoin.Companion.m1422getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(6.0f, 19.0f);
        pathBuilder.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder.horizontalLineToRelative(8.0f);
        pathBuilder.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilder.lineTo(18.0f, 7.0f);
        pathBuilder.lineTo(6.0f, 7.0f);
        pathBuilder.verticalLineToRelative(12.0f);
        pathBuilder.close();
        pathBuilder.moveTo(8.46f, 11.88f);
        pathBuilder.lineToRelative(1.41f, -1.41f);
        pathBuilder.lineTo(12.0f, 12.59f);
        pathBuilder.lineToRelative(2.12f, -2.12f);
        pathBuilder.lineToRelative(1.41f, 1.41f);
        pathBuilder.lineTo(13.41f, 14.0f);
        pathBuilder.lineToRelative(2.12f, 2.12f);
        pathBuilder.lineToRelative(-1.41f, 1.41f);
        pathBuilder.lineTo(12.0f, 15.41f);
        pathBuilder.lineToRelative(-2.12f, 2.12f);
        pathBuilder.lineToRelative(-1.41f, -1.41f);
        pathBuilder.lineTo(10.59f, 14.0f);
        pathBuilder.lineToRelative(-2.13f, -2.12f);
        pathBuilder.close();
        pathBuilder.moveTo(15.5f, 4.0f);
        pathBuilder.lineToRelative(-1.0f, -1.0f);
        pathBuilder.horizontalLineToRelative(-5.0f);
        pathBuilder.lineToRelative(-1.0f, 1.0f);
        pathBuilder.lineTo(5.0f, 4.0f);
        pathBuilder.verticalLineToRelative(2.0f);
        pathBuilder.horizontalLineToRelative(14.0f);
        pathBuilder.lineTo(19.0f, 4.0f);
        pathBuilder.close();
        ImageVector imageVectorBuild = builder.m1558addPathoIyEayM(pathBuilder.getNodes(), (14336 & 2) != 0 ? VectorKt.getDefaultFillType() : defaultFillType, (14336 & 4) != 0 ? "" : "", (14336 & 8) != 0 ? null : solidColor, (14336 & 16) != 0 ? 1.0f : 1.0f, (14336 & 32) == 0 ? null : null, (14336 & 64) != 0 ? 1.0f : 1.0f, (14336 & 128) != 0 ? 0.0f : 1.0f, (14336 & 256) != 0 ? VectorKt.getDefaultStrokeLineCap() : iM1415getButtKaPHkGw, (14336 & 512) != 0 ? VectorKt.getDefaultStrokeLineJoin() : iM1422getBevelLxFBmk8, (14336 & 1024) != 0 ? 4.0f : 1.0f, (14336 & 2048) != 0 ? 0.0f : 0.0f, (14336 & PKIFailureInfo.certConfirmed) == 0 ? 0.0f : 1.0f, (14336 & 8192) == 0 ? 0.0f : 0.0f).build();
        _deleteForever = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
