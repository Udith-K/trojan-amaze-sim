package androidx.compose.foundation;

import android.graphics.Canvas;
import android.widget.EdgeEffect;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.platform.InspectorValueInfo;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: AndroidOverscroll.android.kt */
/* JADX INFO: loaded from: classes.dex */
final class DrawGlowOverscrollModifier extends InspectorValueInfo implements DrawModifier {
    private final EdgeEffectWrapper edgeEffectWrapper;
    private final OverscrollConfiguration overscrollConfig;
    private final AndroidEdgeEffectOverscrollEffect overscrollEffect;

    @Override // androidx.compose.ui.Modifier
    public /* synthetic */ boolean all(Function1 function1) {
        return ((Boolean) function1.invoke(this)).booleanValue();
    }

    @Override // androidx.compose.ui.Modifier
    public /* synthetic */ Object foldIn(Object obj, Function2 function2) {
        return function2.invoke(obj, this);
    }

    @Override // androidx.compose.ui.Modifier
    public /* synthetic */ Modifier then(Modifier modifier) {
        return Modifier.CC.$default$then(this, modifier);
    }

    public DrawGlowOverscrollModifier(AndroidEdgeEffectOverscrollEffect androidEdgeEffectOverscrollEffect, EdgeEffectWrapper edgeEffectWrapper, OverscrollConfiguration overscrollConfiguration, Function1 function1) {
        super(function1);
        this.overscrollEffect = androidEdgeEffectOverscrollEffect;
        this.edgeEffectWrapper = edgeEffectWrapper;
        this.overscrollConfig = overscrollConfiguration;
    }

    @Override // androidx.compose.ui.draw.DrawModifier
    public void draw(ContentDrawScope contentDrawScope) {
        this.overscrollEffect.m95updateSizeuvyYCjk$foundation_release(contentDrawScope.mo1483getSizeNHjbRc());
        if (Size.m1198isEmptyimpl(contentDrawScope.mo1483getSizeNHjbRc())) {
            contentDrawScope.drawContent();
            return;
        }
        contentDrawScope.drawContent();
        this.overscrollEffect.getRedrawSignal$foundation_release().getValue();
        Canvas nativeCanvas = AndroidCanvas_androidKt.getNativeCanvas(contentDrawScope.getDrawContext().getCanvas());
        EdgeEffectWrapper edgeEffectWrapper = this.edgeEffectWrapper;
        boolean zDrawLeftGlow = edgeEffectWrapper.isLeftAnimating() ? drawLeftGlow(contentDrawScope, edgeEffectWrapper.getOrCreateLeftEffect(), nativeCanvas) : false;
        if (edgeEffectWrapper.isTopAnimating()) {
            zDrawLeftGlow = drawTopGlow(contentDrawScope, edgeEffectWrapper.getOrCreateTopEffect(), nativeCanvas) || zDrawLeftGlow;
        }
        if (edgeEffectWrapper.isRightAnimating()) {
            zDrawLeftGlow = drawRightGlow(contentDrawScope, edgeEffectWrapper.getOrCreateRightEffect(), nativeCanvas) || zDrawLeftGlow;
        }
        if (edgeEffectWrapper.isBottomAnimating()) {
            zDrawLeftGlow = drawBottomGlow(contentDrawScope, edgeEffectWrapper.getOrCreateBottomEffect(), nativeCanvas) || zDrawLeftGlow;
        }
        if (zDrawLeftGlow) {
            this.overscrollEffect.invalidateOverscroll$foundation_release();
        }
    }

    private final boolean drawLeftGlow(DrawScope drawScope, EdgeEffect edgeEffect, Canvas canvas) {
        return m131drawWithRotationAndOffsetubNVwUQ(270.0f, OffsetKt.Offset(-Size.m1194getHeightimpl(drawScope.mo1483getSizeNHjbRc()), drawScope.mo210toPx0680j_4(this.overscrollConfig.getDrawPadding().mo253calculateLeftPaddingu2uoSUM(drawScope.getLayoutDirection()))), edgeEffect, canvas);
    }

    private final boolean drawTopGlow(DrawScope drawScope, EdgeEffect edgeEffect, Canvas canvas) {
        return m131drawWithRotationAndOffsetubNVwUQ(0.0f, OffsetKt.Offset(0.0f, drawScope.mo210toPx0680j_4(this.overscrollConfig.getDrawPadding().mo255calculateTopPaddingD9Ej5fM())), edgeEffect, canvas);
    }

    private final boolean drawRightGlow(DrawScope drawScope, EdgeEffect edgeEffect, Canvas canvas) {
        return m131drawWithRotationAndOffsetubNVwUQ(90.0f, OffsetKt.Offset(0.0f, (-MathKt.roundToInt(Size.m1196getWidthimpl(drawScope.mo1483getSizeNHjbRc()))) + drawScope.mo210toPx0680j_4(this.overscrollConfig.getDrawPadding().mo254calculateRightPaddingu2uoSUM(drawScope.getLayoutDirection()))), edgeEffect, canvas);
    }

    private final boolean drawBottomGlow(DrawScope drawScope, EdgeEffect edgeEffect, Canvas canvas) {
        return m131drawWithRotationAndOffsetubNVwUQ(180.0f, OffsetKt.Offset(-Size.m1196getWidthimpl(drawScope.mo1483getSizeNHjbRc()), (-Size.m1194getHeightimpl(drawScope.mo1483getSizeNHjbRc())) + drawScope.mo210toPx0680j_4(this.overscrollConfig.getDrawPadding().mo252calculateBottomPaddingD9Ej5fM())), edgeEffect, canvas);
    }

    /* JADX INFO: renamed from: drawWithRotationAndOffset-ubNVwUQ, reason: not valid java name */
    private final boolean m131drawWithRotationAndOffsetubNVwUQ(float f, long j, EdgeEffect edgeEffect, Canvas canvas) {
        int iSave = canvas.save();
        canvas.rotate(f);
        canvas.translate(Offset.m1159getXimpl(j), Offset.m1160getYimpl(j));
        boolean zDraw = edgeEffect.draw(canvas);
        canvas.restoreToCount(iSave);
        return zDraw;
    }
}
