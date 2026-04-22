package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathEffect;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LayoutNodeDrawScope.kt */
/* JADX INFO: loaded from: classes.dex */
public final class LayoutNodeDrawScope implements DrawScope, ContentDrawScope {
    private final CanvasDrawScope canvasDrawScope;
    private DrawModifierNode drawNode;

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawArc-yD3GUKo */
    public void mo1470drawArcyD3GUKo(long j, float f, float f2, boolean z, long j2, long j3, float f3, DrawStyle drawStyle, ColorFilter colorFilter, int i) {
        this.canvasDrawScope.mo1470drawArcyD3GUKo(j, f, f2, z, j2, j3, f3, drawStyle, colorFilter, i);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawCircle-VaOC9Bg */
    public void mo1471drawCircleVaOC9Bg(long j, float f, long j2, float f2, DrawStyle drawStyle, ColorFilter colorFilter, int i) {
        this.canvasDrawScope.mo1471drawCircleVaOC9Bg(j, f, j2, f2, drawStyle, colorFilter, i);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawImage-AZ2fEMs */
    public void mo1472drawImageAZ2fEMs(ImageBitmap imageBitmap, long j, long j2, long j3, long j4, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i, int i2) {
        this.canvasDrawScope.mo1472drawImageAZ2fEMs(imageBitmap, j, j2, j3, j4, f, drawStyle, colorFilter, i, i2);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawImage-gbVJVH8 */
    public void mo1473drawImagegbVJVH8(ImageBitmap imageBitmap, long j, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i) {
        this.canvasDrawScope.mo1473drawImagegbVJVH8(imageBitmap, j, f, drawStyle, colorFilter, i);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawLine-1RTmtNc */
    public void mo1474drawLine1RTmtNc(Brush brush, long j, long j2, float f, int i, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i2) {
        this.canvasDrawScope.mo1474drawLine1RTmtNc(brush, j, j2, f, i, pathEffect, f2, colorFilter, i2);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawLine-NGM6Ib0 */
    public void mo1475drawLineNGM6Ib0(long j, long j2, long j3, float f, int i, PathEffect pathEffect, float f2, ColorFilter colorFilter, int i2) {
        this.canvasDrawScope.mo1475drawLineNGM6Ib0(j, j2, j3, f, i, pathEffect, f2, colorFilter, i2);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawPath-GBMwjPU */
    public void mo1476drawPathGBMwjPU(Path path, Brush brush, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i) {
        this.canvasDrawScope.mo1476drawPathGBMwjPU(path, brush, f, drawStyle, colorFilter, i);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawPath-LG529CI */
    public void mo1477drawPathLG529CI(Path path, long j, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i) {
        this.canvasDrawScope.mo1477drawPathLG529CI(path, j, f, drawStyle, colorFilter, i);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawRect-AsUm42w */
    public void mo1478drawRectAsUm42w(Brush brush, long j, long j2, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i) {
        this.canvasDrawScope.mo1478drawRectAsUm42w(brush, j, j2, f, drawStyle, colorFilter, i);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawRect-n-J9OG0 */
    public void mo1479drawRectnJ9OG0(long j, long j2, long j3, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i) {
        this.canvasDrawScope.mo1479drawRectnJ9OG0(j, j2, j3, f, drawStyle, colorFilter, i);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawRoundRect-ZuiqVtQ */
    public void mo1480drawRoundRectZuiqVtQ(Brush brush, long j, long j2, long j3, float f, DrawStyle drawStyle, ColorFilter colorFilter, int i) {
        this.canvasDrawScope.mo1480drawRoundRectZuiqVtQ(brush, j, j2, j3, f, drawStyle, colorFilter, i);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: drawRoundRect-u-Aw5IA */
    public void mo1481drawRoundRectuAw5IA(long j, long j2, long j3, long j4, DrawStyle drawStyle, float f, ColorFilter colorFilter, int i) {
        this.canvasDrawScope.mo1481drawRoundRectuAw5IA(j, j2, j3, j4, drawStyle, f, colorFilter, i);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: getCenter-F1C5BW0 */
    public long mo1482getCenterF1C5BW0() {
        return this.canvasDrawScope.mo1482getCenterF1C5BW0();
    }

    @Override // androidx.compose.ui.unit.Density
    public float getDensity() {
        return this.canvasDrawScope.getDensity();
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    public DrawContext getDrawContext() {
        return this.canvasDrawScope.getDrawContext();
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public float getFontScale() {
        return this.canvasDrawScope.getFontScale();
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    public LayoutDirection getLayoutDirection() {
        return this.canvasDrawScope.getLayoutDirection();
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* JADX INFO: renamed from: getSize-NH-jbRc */
    public long mo1483getSizeNHjbRc() {
        return this.canvasDrawScope.mo1483getSizeNHjbRc();
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: roundToPx-0680j_4 */
    public int mo204roundToPx0680j_4(float f) {
        return this.canvasDrawScope.mo204roundToPx0680j_4(f);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toDp-GaN1DYA */
    public float mo205toDpGaN1DYA(long j) {
        return this.canvasDrawScope.mo205toDpGaN1DYA(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public float mo206toDpu2uoSUM(float f) {
        return this.canvasDrawScope.mo206toDpu2uoSUM(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public float mo207toDpu2uoSUM(int i) {
        return this.canvasDrawScope.mo207toDpu2uoSUM(i);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDpSize-k-rfVVM */
    public long mo208toDpSizekrfVVM(long j) {
        return this.canvasDrawScope.mo208toDpSizekrfVVM(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx--R2X_6o */
    public float mo209toPxR2X_6o(long j) {
        return this.canvasDrawScope.mo209toPxR2X_6o(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx-0680j_4 */
    public float mo210toPx0680j_4(float f) {
        return this.canvasDrawScope.mo210toPx0680j_4(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSize-XkaWNTQ */
    public long mo211toSizeXkaWNTQ(long j) {
        return this.canvasDrawScope.mo211toSizeXkaWNTQ(j);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toSp-0xMU5do */
    public long mo212toSp0xMU5do(float f) {
        return this.canvasDrawScope.mo212toSp0xMU5do(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    public long mo213toSpkPz2Gy4(float f) {
        return this.canvasDrawScope.mo213toSpkPz2Gy4(f);
    }

    public LayoutNodeDrawScope(CanvasDrawScope canvasDrawScope) {
        this.canvasDrawScope = canvasDrawScope;
    }

    public /* synthetic */ LayoutNodeDrawScope(CanvasDrawScope canvasDrawScope, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new CanvasDrawScope() : canvasDrawScope);
    }

    /* JADX INFO: renamed from: draw-eZhPAX0$ui_release, reason: not valid java name */
    public final void m1821draweZhPAX0$ui_release(Canvas canvas, long j, NodeCoordinator nodeCoordinator, Modifier.Node node, GraphicsLayer graphicsLayer) {
        int iM1895constructorimpl = NodeKind.m1895constructorimpl(4);
        Modifier.Node nodePop = node;
        MutableVector mutableVector = null;
        while (nodePop != null) {
            if (nodePop instanceof DrawModifierNode) {
                m1822drawDirecteZhPAX0$ui_release(canvas, j, nodeCoordinator, (DrawModifierNode) nodePop, graphicsLayer);
            } else if ((nodePop.getKindSet$ui_release() & iM1895constructorimpl) != 0 && (nodePop instanceof DelegatingNode)) {
                int i = 0;
                for (Modifier.Node delegate$ui_release = ((DelegatingNode) nodePop).getDelegate$ui_release(); delegate$ui_release != null; delegate$ui_release = delegate$ui_release.getChild$ui_release()) {
                    if ((delegate$ui_release.getKindSet$ui_release() & iM1895constructorimpl) != 0) {
                        i++;
                        if (i == 1) {
                            nodePop = delegate$ui_release;
                        } else {
                            if (mutableVector == null) {
                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                            }
                            if (nodePop != null) {
                                mutableVector.add(nodePop);
                                nodePop = null;
                            }
                            mutableVector.add(delegate$ui_release);
                        }
                    }
                }
                if (i == 1) {
                }
            }
            nodePop = DelegatableNodeKt.pop(mutableVector);
        }
    }

    public final void performDraw(DrawModifierNode drawModifierNode, Canvas canvas, GraphicsLayer graphicsLayer) {
        NodeCoordinator nodeCoordinatorM1795requireCoordinator64DMado = DelegatableNodeKt.m1795requireCoordinator64DMado(drawModifierNode, NodeKind.m1895constructorimpl(4));
        nodeCoordinatorM1795requireCoordinator64DMado.getLayoutNode().getMDrawScope$ui_release().m1822drawDirecteZhPAX0$ui_release(canvas, IntSizeKt.m2483toSizeozmzZPI(nodeCoordinatorM1795requireCoordinator64DMado.mo1745getSizeYbymL2g()), nodeCoordinatorM1795requireCoordinator64DMado, drawModifierNode, graphicsLayer);
    }

    /* JADX INFO: renamed from: drawDirect-eZhPAX0$ui_release, reason: not valid java name */
    public final void m1822drawDirecteZhPAX0$ui_release(Canvas canvas, long j, NodeCoordinator nodeCoordinator, DrawModifierNode drawModifierNode, GraphicsLayer graphicsLayer) {
        DrawModifierNode drawModifierNode2 = this.drawNode;
        this.drawNode = drawModifierNode;
        CanvasDrawScope canvasDrawScope = this.canvasDrawScope;
        LayoutDirection layoutDirection = nodeCoordinator.getLayoutDirection();
        Density density = canvasDrawScope.getDrawContext().getDensity();
        LayoutDirection layoutDirection2 = canvasDrawScope.getDrawContext().getLayoutDirection();
        Canvas canvas2 = canvasDrawScope.getDrawContext().getCanvas();
        long jMo1487getSizeNHjbRc = canvasDrawScope.getDrawContext().mo1487getSizeNHjbRc();
        GraphicsLayer graphicsLayer2 = canvasDrawScope.getDrawContext().getGraphicsLayer();
        DrawContext drawContext = canvasDrawScope.getDrawContext();
        drawContext.setDensity(nodeCoordinator);
        drawContext.setLayoutDirection(layoutDirection);
        drawContext.setCanvas(canvas);
        drawContext.mo1488setSizeuvyYCjk(j);
        drawContext.setGraphicsLayer(graphicsLayer);
        canvas.save();
        try {
            drawModifierNode.draw(this);
            canvas.restore();
            DrawContext drawContext2 = canvasDrawScope.getDrawContext();
            drawContext2.setDensity(density);
            drawContext2.setLayoutDirection(layoutDirection2);
            drawContext2.setCanvas(canvas2);
            drawContext2.mo1488setSizeuvyYCjk(jMo1487getSizeNHjbRc);
            drawContext2.setGraphicsLayer(graphicsLayer2);
            this.drawNode = drawModifierNode2;
        } catch (Throwable th) {
            canvas.restore();
            DrawContext drawContext3 = canvasDrawScope.getDrawContext();
            drawContext3.setDensity(density);
            drawContext3.setLayoutDirection(layoutDirection2);
            drawContext3.setCanvas(canvas2);
            drawContext3.mo1488setSizeuvyYCjk(jMo1487getSizeNHjbRc);
            drawContext3.setGraphicsLayer(graphicsLayer2);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v5, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v9, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r5v6 */
    @Override // androidx.compose.ui.graphics.drawscope.ContentDrawScope
    public void drawContent() {
        Canvas canvas = getDrawContext().getCanvas();
        DrawModifierNode drawModifierNode = this.drawNode;
        Intrinsics.checkNotNull(drawModifierNode);
        ?? NextDrawNode = LayoutNodeDrawScopeKt.nextDrawNode(drawModifierNode);
        if (NextDrawNode == 0) {
            NodeCoordinator nodeCoordinatorM1795requireCoordinator64DMado = DelegatableNodeKt.m1795requireCoordinator64DMado(drawModifierNode, NodeKind.m1895constructorimpl(4));
            if (nodeCoordinatorM1795requireCoordinator64DMado.getTail() == drawModifierNode.getNode()) {
                nodeCoordinatorM1795requireCoordinator64DMado = nodeCoordinatorM1795requireCoordinator64DMado.getWrapped$ui_release();
                Intrinsics.checkNotNull(nodeCoordinatorM1795requireCoordinator64DMado);
            }
            nodeCoordinatorM1795requireCoordinator64DMado.performDraw(canvas, getDrawContext().getGraphicsLayer());
            return;
        }
        int iM1895constructorimpl = NodeKind.m1895constructorimpl(4);
        ?? mutableVector = 0;
        while (NextDrawNode != 0) {
            if (NextDrawNode instanceof DrawModifierNode) {
                performDraw((DrawModifierNode) NextDrawNode, canvas, getDrawContext().getGraphicsLayer());
            } else if ((NextDrawNode.getKindSet$ui_release() & iM1895constructorimpl) != 0 && (NextDrawNode instanceof DelegatingNode)) {
                Modifier.Node delegate$ui_release = ((DelegatingNode) NextDrawNode).getDelegate$ui_release();
                int i = 0;
                NextDrawNode = NextDrawNode;
                mutableVector = mutableVector;
                while (delegate$ui_release != null) {
                    if ((delegate$ui_release.getKindSet$ui_release() & iM1895constructorimpl) != 0) {
                        i++;
                        mutableVector = mutableVector;
                        if (i == 1) {
                            NextDrawNode = delegate$ui_release;
                        } else {
                            if (mutableVector == 0) {
                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                            }
                            if (NextDrawNode != 0) {
                                mutableVector.add(NextDrawNode);
                                NextDrawNode = 0;
                            }
                            mutableVector.add(delegate$ui_release);
                        }
                    }
                    delegate$ui_release = delegate$ui_release.getChild$ui_release();
                    NextDrawNode = NextDrawNode;
                    mutableVector = mutableVector;
                }
                if (i == 1) {
                }
            }
            NextDrawNode = DelegatableNodeKt.pop(mutableVector);
        }
    }
}
