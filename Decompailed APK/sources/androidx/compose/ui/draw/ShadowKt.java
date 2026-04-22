package androidx.compose.ui.draw;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerScopeKt;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Dp;

/* JADX INFO: compiled from: Shadow.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ShadowKt {
    /* JADX INFO: renamed from: shadow-s4CzXII$default, reason: not valid java name */
    public static /* synthetic */ Modifier m1086shadows4CzXII$default(Modifier modifier, float f, Shape shape, boolean z, long j, long j2, int i, Object obj) {
        boolean z2;
        Shape rectangleShape = (i & 2) != 0 ? RectangleShapeKt.getRectangleShape() : shape;
        if ((i & 4) != 0) {
            z2 = false;
            if (Dp.m2415compareTo0680j_4(f, Dp.m2416constructorimpl(0)) > 0) {
                z2 = true;
            }
        } else {
            z2 = z;
        }
        return m1085shadows4CzXII(modifier, f, rectangleShape, z2, (i & 8) != 0 ? GraphicsLayerScopeKt.getDefaultShadowColor() : j, (i & 16) != 0 ? GraphicsLayerScopeKt.getDefaultShadowColor() : j2);
    }

    /* JADX INFO: renamed from: shadow-s4CzXII, reason: not valid java name */
    public static final Modifier m1085shadows4CzXII(Modifier modifier, float f, Shape shape, boolean z, long j, long j2) {
        return (Dp.m2415compareTo0680j_4(f, Dp.m2416constructorimpl((float) 0)) > 0 || z) ? modifier.then(new ShadowGraphicsLayerElement(f, shape, z, j, j2, null)) : modifier;
    }
}
