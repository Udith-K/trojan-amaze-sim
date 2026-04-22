package androidx.compose.foundation;

import androidx.compose.ui.draw.CacheDrawModifierNode;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.unit.Dp;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Border.kt */
/* JADX INFO: loaded from: classes.dex */
public final class BorderModifierNode extends DelegatingNode {
    private BorderCache borderCache;
    private Brush brush;
    private final CacheDrawModifierNode drawWithCacheModifierNode;
    private Shape shape;
    private float width;

    public /* synthetic */ BorderModifierNode(float f, Brush brush, Shape shape, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, brush, shape);
    }

    private BorderModifierNode(float f, Brush brush, Shape shape) {
        this.width = f;
        this.brush = brush;
        this.shape = shape;
        this.drawWithCacheModifierNode = (CacheDrawModifierNode) delegate(DrawModifierKt.CacheDrawModifierNode(new Function1() { // from class: androidx.compose.foundation.BorderModifierNode$drawWithCacheModifierNode$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final DrawResult invoke(CacheDrawScope cacheDrawScope) {
                if (cacheDrawScope.mo210toPx0680j_4(this.this$0.m108getWidthD9Ej5fM()) < 0.0f || Size.m1195getMinDimensionimpl(cacheDrawScope.m1077getSizeNHjbRc()) <= 0.0f) {
                    return BorderKt.drawContentWithoutBorder(cacheDrawScope);
                }
                float f2 = 2;
                float fMin = Math.min(Dp.m2418equalsimpl0(this.this$0.m108getWidthD9Ej5fM(), Dp.Companion.m2423getHairlineD9Ej5fM()) ? 1.0f : (float) Math.ceil(cacheDrawScope.mo210toPx0680j_4(this.this$0.m108getWidthD9Ej5fM())), (float) Math.ceil(Size.m1195getMinDimensionimpl(cacheDrawScope.m1077getSizeNHjbRc()) / f2));
                float f3 = fMin / f2;
                long jOffset = OffsetKt.Offset(f3, f3);
                long jSize = SizeKt.Size(Size.m1196getWidthimpl(cacheDrawScope.m1077getSizeNHjbRc()) - fMin, Size.m1194getHeightimpl(cacheDrawScope.m1077getSizeNHjbRc()) - fMin);
                boolean z = f2 * fMin > Size.m1195getMinDimensionimpl(cacheDrawScope.m1077getSizeNHjbRc());
                Outline outlineMo125createOutlinePq9zytI = this.this$0.getShape().mo125createOutlinePq9zytI(cacheDrawScope.m1077getSizeNHjbRc(), cacheDrawScope.getLayoutDirection(), cacheDrawScope);
                if (outlineMo125createOutlinePq9zytI instanceof Outline.Generic) {
                    BorderModifierNode borderModifierNode = this.this$0;
                    return borderModifierNode.drawGenericBorder(cacheDrawScope, borderModifierNode.getBrush(), (Outline.Generic) outlineMo125createOutlinePq9zytI, z, fMin);
                }
                if (outlineMo125createOutlinePq9zytI instanceof Outline.Rounded) {
                    BorderModifierNode borderModifierNode2 = this.this$0;
                    return borderModifierNode2.m107drawRoundRectBorderJqoCqck(cacheDrawScope, borderModifierNode2.getBrush(), (Outline.Rounded) outlineMo125createOutlinePq9zytI, jOffset, jSize, z, fMin);
                }
                if (outlineMo125createOutlinePq9zytI instanceof Outline.Rectangle) {
                    return BorderKt.m104drawRectBorderNsqcLGU(cacheDrawScope, this.this$0.getBrush(), jOffset, jSize, z, fMin);
                }
                throw new NoWhenBranchMatchedException();
            }
        }));
    }

    /* JADX INFO: renamed from: getWidth-D9Ej5fM, reason: not valid java name */
    public final float m108getWidthD9Ej5fM() {
        return this.width;
    }

    /* JADX INFO: renamed from: setWidth-0680j_4, reason: not valid java name */
    public final void m109setWidth0680j_4(float f) {
        if (Dp.m2418equalsimpl0(this.width, f)) {
            return;
        }
        this.width = f;
        this.drawWithCacheModifierNode.invalidateDrawCache();
    }

    public final Brush getBrush() {
        return this.brush;
    }

    public final void setBrush(Brush brush) {
        if (Intrinsics.areEqual(this.brush, brush)) {
            return;
        }
        this.brush = brush;
        this.drawWithCacheModifierNode.invalidateDrawCache();
    }

    public final Shape getShape() {
        return this.shape;
    }

    public final void setShape(Shape shape) {
        if (Intrinsics.areEqual(this.shape, shape)) {
            return;
        }
        this.shape = shape;
        this.drawWithCacheModifierNode.invalidateDrawCache();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00db  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final androidx.compose.ui.draw.DrawResult drawGenericBorder(androidx.compose.ui.draw.CacheDrawScope r48, final androidx.compose.ui.graphics.Brush r49, final androidx.compose.ui.graphics.Outline.Generic r50, boolean r51, float r52) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 721
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.BorderModifierNode.drawGenericBorder(androidx.compose.ui.draw.CacheDrawScope, androidx.compose.ui.graphics.Brush, androidx.compose.ui.graphics.Outline$Generic, boolean, float):androidx.compose.ui.draw.DrawResult");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: drawRoundRectBorder-JqoCqck, reason: not valid java name */
    public final DrawResult m107drawRoundRectBorderJqoCqck(CacheDrawScope cacheDrawScope, final Brush brush, Outline.Rounded rounded, final long j, final long j2, final boolean z, final float f) {
        if (RoundRectKt.isSimple(rounded.getRoundRect())) {
            final long jM1186getTopLeftCornerRadiuskKHJgLs = rounded.getRoundRect().m1186getTopLeftCornerRadiuskKHJgLs();
            final float f2 = f / 2;
            final Stroke stroke = new Stroke(f, 0.0f, 0, 0, null, 30, null);
            return cacheDrawScope.onDrawWithContent(new Function1() { // from class: androidx.compose.foundation.BorderModifierNode$drawRoundRectBorder$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) throws Throwable {
                    invoke((ContentDrawScope) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(ContentDrawScope contentDrawScope) throws Throwable {
                    long j3;
                    contentDrawScope.drawContent();
                    if (z) {
                        DrawScope.CC.m1507drawRoundRectZuiqVtQ$default(contentDrawScope, brush, 0L, 0L, jM1186getTopLeftCornerRadiuskKHJgLs, 0.0f, null, null, 0, 246, null);
                        return;
                    }
                    float fM1145getXimpl = CornerRadius.m1145getXimpl(jM1186getTopLeftCornerRadiuskKHJgLs);
                    float f3 = f2;
                    if (fM1145getXimpl < f3) {
                        float f4 = f;
                        float fM1196getWidthimpl = Size.m1196getWidthimpl(contentDrawScope.mo1483getSizeNHjbRc()) - f;
                        float fM1194getHeightimpl = Size.m1194getHeightimpl(contentDrawScope.mo1483getSizeNHjbRc()) - f;
                        int iM1288getDifferencertfAjoo = ClipOp.Companion.m1288getDifferencertfAjoo();
                        Brush brush2 = brush;
                        long j4 = jM1186getTopLeftCornerRadiuskKHJgLs;
                        DrawContext drawContext = contentDrawScope.getDrawContext();
                        long jMo1487getSizeNHjbRc = drawContext.mo1487getSizeNHjbRc();
                        drawContext.getCanvas().save();
                        try {
                            drawContext.getTransform().mo1490clipRectN_I0leg(f4, f4, fM1196getWidthimpl, fM1194getHeightimpl, iM1288getDifferencertfAjoo);
                            j3 = jMo1487getSizeNHjbRc;
                        } catch (Throwable th) {
                            th = th;
                            j3 = jMo1487getSizeNHjbRc;
                        }
                        try {
                            DrawScope.CC.m1507drawRoundRectZuiqVtQ$default(contentDrawScope, brush2, 0L, 0L, j4, 0.0f, null, null, 0, 246, null);
                            drawContext.getCanvas().restore();
                            drawContext.mo1488setSizeuvyYCjk(j3);
                            return;
                        } catch (Throwable th2) {
                            th = th2;
                            drawContext.getCanvas().restore();
                            drawContext.mo1488setSizeuvyYCjk(j3);
                            throw th;
                        }
                    }
                    DrawScope.CC.m1507drawRoundRectZuiqVtQ$default(contentDrawScope, brush, j, j2, BorderKt.m105shrinkKibmq7A(jM1186getTopLeftCornerRadiuskKHJgLs, f3), 0.0f, stroke, null, 0, 208, null);
                }
            });
        }
        if (this.borderCache == null) {
            this.borderCache = new BorderCache(null, null, null, null, 15, null);
        }
        BorderCache borderCache = this.borderCache;
        Intrinsics.checkNotNull(borderCache);
        final Path pathCreateRoundRectPath = BorderKt.createRoundRectPath(borderCache.obtainPath(), rounded.getRoundRect(), f, z);
        return cacheDrawScope.onDrawWithContent(new Function1() { // from class: androidx.compose.foundation.BorderModifierNode$drawRoundRectBorder$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((ContentDrawScope) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(ContentDrawScope contentDrawScope) {
                contentDrawScope.drawContent();
                DrawScope.CC.m1504drawPathGBMwjPU$default(contentDrawScope, pathCreateRoundRectPath, brush, 0.0f, null, null, 0, 60, null);
            }
        });
    }
}
