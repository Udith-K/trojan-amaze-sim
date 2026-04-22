package androidx.compose.foundation;

import android.graphics.Canvas;
import android.graphics.RecordingCanvas;
import android.graphics.RenderNode;
import android.widget.EdgeEffect;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.platform.InspectorValueInfo;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: AndroidOverscroll.android.kt */
/* JADX INFO: loaded from: classes.dex */
final class DrawStretchOverscrollModifier extends InspectorValueInfo implements DrawModifier {
    private RenderNode _renderNode;
    private final EdgeEffectWrapper edgeEffectWrapper;
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

    public DrawStretchOverscrollModifier(AndroidEdgeEffectOverscrollEffect androidEdgeEffectOverscrollEffect, EdgeEffectWrapper edgeEffectWrapper, Function1 function1) {
        super(function1);
        this.overscrollEffect = androidEdgeEffectOverscrollEffect;
        this.edgeEffectWrapper = edgeEffectWrapper;
    }

    private final RenderNode getRenderNode() {
        RenderNode renderNode = this._renderNode;
        if (renderNode != null) {
            return renderNode;
        }
        RenderNode renderNodeM = DrawStretchOverscrollModifier$$ExternalSyntheticApiModelOutline0.m("AndroidEdgeEffectOverscrollEffect");
        this._renderNode = renderNodeM;
        return renderNodeM;
    }

    @Override // androidx.compose.ui.draw.DrawModifier
    public void draw(ContentDrawScope contentDrawScope) {
        boolean zDrawLeftStretch;
        float f;
        float f2;
        this.overscrollEffect.m95updateSizeuvyYCjk$foundation_release(contentDrawScope.mo1483getSizeNHjbRc());
        if (Size.m1198isEmptyimpl(contentDrawScope.mo1483getSizeNHjbRc())) {
            contentDrawScope.drawContent();
            return;
        }
        this.overscrollEffect.getRedrawSignal$foundation_release().getValue();
        float fMo210toPx0680j_4 = contentDrawScope.mo210toPx0680j_4(ClipScrollableContainerKt.getMaxSupportedElevation());
        Canvas nativeCanvas = AndroidCanvas_androidKt.getNativeCanvas(contentDrawScope.getDrawContext().getCanvas());
        EdgeEffectWrapper edgeEffectWrapper = this.edgeEffectWrapper;
        boolean zShouldDrawVerticalStretch = shouldDrawVerticalStretch();
        boolean zShouldDrawHorizontalStretch = shouldDrawHorizontalStretch();
        if (zShouldDrawVerticalStretch && zShouldDrawHorizontalStretch) {
            getRenderNode().setPosition(0, 0, nativeCanvas.getWidth(), nativeCanvas.getHeight());
        } else if (zShouldDrawVerticalStretch) {
            getRenderNode().setPosition(0, 0, nativeCanvas.getWidth() + (MathKt.roundToInt(fMo210toPx0680j_4) * 2), nativeCanvas.getHeight());
        } else if (zShouldDrawHorizontalStretch) {
            getRenderNode().setPosition(0, 0, nativeCanvas.getWidth(), nativeCanvas.getHeight() + (MathKt.roundToInt(fMo210toPx0680j_4) * 2));
        } else {
            contentDrawScope.drawContent();
            return;
        }
        RecordingCanvas recordingCanvasBeginRecording = getRenderNode().beginRecording();
        if (edgeEffectWrapper.isLeftNegationStretched()) {
            EdgeEffect orCreateLeftEffectNegation = edgeEffectWrapper.getOrCreateLeftEffectNegation();
            drawRightStretch(orCreateLeftEffectNegation, recordingCanvasBeginRecording);
            orCreateLeftEffectNegation.finish();
        }
        if (edgeEffectWrapper.isLeftAnimating()) {
            EdgeEffect orCreateLeftEffect = edgeEffectWrapper.getOrCreateLeftEffect();
            zDrawLeftStretch = drawLeftStretch(orCreateLeftEffect, recordingCanvasBeginRecording);
            if (edgeEffectWrapper.isLeftStretched()) {
                float fM1160getYimpl = Offset.m1160getYimpl(this.overscrollEffect.m94displacementF1C5BW0$foundation_release());
                EdgeEffectCompat edgeEffectCompat = EdgeEffectCompat.INSTANCE;
                edgeEffectCompat.onPullDistanceCompat(edgeEffectWrapper.getOrCreateLeftEffectNegation(), edgeEffectCompat.getDistanceCompat(orCreateLeftEffect), 1 - fM1160getYimpl);
            }
        } else {
            zDrawLeftStretch = false;
        }
        if (edgeEffectWrapper.isTopNegationStretched()) {
            EdgeEffect orCreateTopEffectNegation = edgeEffectWrapper.getOrCreateTopEffectNegation();
            drawBottomStretch(orCreateTopEffectNegation, recordingCanvasBeginRecording);
            orCreateTopEffectNegation.finish();
        }
        if (edgeEffectWrapper.isTopAnimating()) {
            EdgeEffect orCreateTopEffect = edgeEffectWrapper.getOrCreateTopEffect();
            zDrawLeftStretch = drawTopStretch(orCreateTopEffect, recordingCanvasBeginRecording) || zDrawLeftStretch;
            if (edgeEffectWrapper.isTopStretched()) {
                float fM1159getXimpl = Offset.m1159getXimpl(this.overscrollEffect.m94displacementF1C5BW0$foundation_release());
                EdgeEffectCompat edgeEffectCompat2 = EdgeEffectCompat.INSTANCE;
                edgeEffectCompat2.onPullDistanceCompat(edgeEffectWrapper.getOrCreateTopEffectNegation(), edgeEffectCompat2.getDistanceCompat(orCreateTopEffect), fM1159getXimpl);
            }
        }
        if (edgeEffectWrapper.isRightNegationStretched()) {
            EdgeEffect orCreateRightEffectNegation = edgeEffectWrapper.getOrCreateRightEffectNegation();
            drawLeftStretch(orCreateRightEffectNegation, recordingCanvasBeginRecording);
            orCreateRightEffectNegation.finish();
        }
        if (edgeEffectWrapper.isRightAnimating()) {
            EdgeEffect orCreateRightEffect = edgeEffectWrapper.getOrCreateRightEffect();
            zDrawLeftStretch = drawRightStretch(orCreateRightEffect, recordingCanvasBeginRecording) || zDrawLeftStretch;
            if (edgeEffectWrapper.isRightStretched()) {
                float fM1160getYimpl2 = Offset.m1160getYimpl(this.overscrollEffect.m94displacementF1C5BW0$foundation_release());
                EdgeEffectCompat edgeEffectCompat3 = EdgeEffectCompat.INSTANCE;
                edgeEffectCompat3.onPullDistanceCompat(edgeEffectWrapper.getOrCreateRightEffectNegation(), edgeEffectCompat3.getDistanceCompat(orCreateRightEffect), fM1160getYimpl2);
            }
        }
        if (edgeEffectWrapper.isBottomNegationStretched()) {
            EdgeEffect orCreateBottomEffectNegation = edgeEffectWrapper.getOrCreateBottomEffectNegation();
            drawTopStretch(orCreateBottomEffectNegation, recordingCanvasBeginRecording);
            orCreateBottomEffectNegation.finish();
        }
        if (edgeEffectWrapper.isBottomAnimating()) {
            EdgeEffect orCreateBottomEffect = edgeEffectWrapper.getOrCreateBottomEffect();
            boolean z = drawBottomStretch(orCreateBottomEffect, recordingCanvasBeginRecording) || zDrawLeftStretch;
            if (edgeEffectWrapper.isBottomStretched()) {
                float fM1159getXimpl2 = Offset.m1159getXimpl(this.overscrollEffect.m94displacementF1C5BW0$foundation_release());
                EdgeEffectCompat edgeEffectCompat4 = EdgeEffectCompat.INSTANCE;
                edgeEffectCompat4.onPullDistanceCompat(edgeEffectWrapper.getOrCreateBottomEffectNegation(), edgeEffectCompat4.getDistanceCompat(orCreateBottomEffect), 1 - fM1159getXimpl2);
            }
            zDrawLeftStretch = z;
        }
        if (zDrawLeftStretch) {
            this.overscrollEffect.invalidateOverscroll$foundation_release();
        }
        float f3 = zShouldDrawHorizontalStretch ? 0.0f : fMo210toPx0680j_4;
        if (zShouldDrawVerticalStretch) {
            fMo210toPx0680j_4 = 0.0f;
        }
        LayoutDirection layoutDirection = contentDrawScope.getLayoutDirection();
        androidx.compose.ui.graphics.Canvas Canvas = AndroidCanvas_androidKt.Canvas(recordingCanvasBeginRecording);
        long jMo1483getSizeNHjbRc = contentDrawScope.mo1483getSizeNHjbRc();
        Density density = contentDrawScope.getDrawContext().getDensity();
        LayoutDirection layoutDirection2 = contentDrawScope.getDrawContext().getLayoutDirection();
        androidx.compose.ui.graphics.Canvas canvas = contentDrawScope.getDrawContext().getCanvas();
        long jMo1487getSizeNHjbRc = contentDrawScope.getDrawContext().mo1487getSizeNHjbRc();
        GraphicsLayer graphicsLayer = contentDrawScope.getDrawContext().getGraphicsLayer();
        DrawContext drawContext = contentDrawScope.getDrawContext();
        drawContext.setDensity(contentDrawScope);
        drawContext.setLayoutDirection(layoutDirection);
        drawContext.setCanvas(Canvas);
        drawContext.mo1488setSizeuvyYCjk(jMo1483getSizeNHjbRc);
        drawContext.setGraphicsLayer(null);
        Canvas.save();
        try {
            contentDrawScope.getDrawContext().getTransform().translate(f3, fMo210toPx0680j_4);
            try {
                contentDrawScope.drawContent();
                Canvas.restore();
                DrawContext drawContext2 = contentDrawScope.getDrawContext();
                drawContext2.setDensity(density);
                drawContext2.setLayoutDirection(layoutDirection2);
                drawContext2.setCanvas(canvas);
                drawContext2.mo1488setSizeuvyYCjk(jMo1487getSizeNHjbRc);
                drawContext2.setGraphicsLayer(graphicsLayer);
                getRenderNode().endRecording();
                int iSave = nativeCanvas.save();
                nativeCanvas.translate(f, f2);
                nativeCanvas.drawRenderNode(getRenderNode());
                nativeCanvas.restoreToCount(iSave);
            } finally {
                contentDrawScope.getDrawContext().getTransform().translate(-f3, -fMo210toPx0680j_4);
            }
        } catch (Throwable th) {
            Canvas.restore();
            DrawContext drawContext3 = contentDrawScope.getDrawContext();
            drawContext3.setDensity(density);
            drawContext3.setLayoutDirection(layoutDirection2);
            drawContext3.setCanvas(canvas);
            drawContext3.mo1488setSizeuvyYCjk(jMo1487getSizeNHjbRc);
            drawContext3.setGraphicsLayer(graphicsLayer);
            throw th;
        }
    }

    private final boolean shouldDrawVerticalStretch() {
        EdgeEffectWrapper edgeEffectWrapper = this.edgeEffectWrapper;
        return edgeEffectWrapper.isTopAnimating() || edgeEffectWrapper.isTopNegationStretched() || edgeEffectWrapper.isBottomAnimating() || edgeEffectWrapper.isBottomNegationStretched();
    }

    private final boolean shouldDrawHorizontalStretch() {
        EdgeEffectWrapper edgeEffectWrapper = this.edgeEffectWrapper;
        return edgeEffectWrapper.isLeftAnimating() || edgeEffectWrapper.isLeftNegationStretched() || edgeEffectWrapper.isRightAnimating() || edgeEffectWrapper.isRightNegationStretched();
    }

    private final boolean drawLeftStretch(EdgeEffect edgeEffect, Canvas canvas) {
        return drawWithRotation(270.0f, edgeEffect, canvas);
    }

    private final boolean drawTopStretch(EdgeEffect edgeEffect, Canvas canvas) {
        return drawWithRotation(0.0f, edgeEffect, canvas);
    }

    private final boolean drawRightStretch(EdgeEffect edgeEffect, Canvas canvas) {
        return drawWithRotation(90.0f, edgeEffect, canvas);
    }

    private final boolean drawBottomStretch(EdgeEffect edgeEffect, Canvas canvas) {
        return drawWithRotation(180.0f, edgeEffect, canvas);
    }

    private final boolean drawWithRotation(float f, EdgeEffect edgeEffect, Canvas canvas) {
        if (f == 0.0f) {
            return edgeEffect.draw(canvas);
        }
        int iSave = canvas.save();
        canvas.rotate(f);
        boolean zDraw = edgeEffect.draw(canvas);
        canvas.restoreToCount(iSave);
        return zDraw;
    }
}
