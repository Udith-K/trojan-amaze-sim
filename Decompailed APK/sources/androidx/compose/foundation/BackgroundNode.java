package androidx.compose.foundation;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.OutlineKt;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.DrawModifierNodeKt;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;

/* JADX INFO: compiled from: Background.kt */
/* JADX INFO: loaded from: classes.dex */
final class BackgroundNode extends Modifier.Node implements DrawModifierNode, ObserverModifierNode {
    private float alpha;
    private Brush brush;
    private long color;
    private LayoutDirection lastLayoutDirection;
    private Outline lastOutline;
    private Shape lastShape;
    private long lastSize;
    private Shape shape;

    public /* synthetic */ BackgroundNode(long j, Brush brush, float f, Shape shape, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, brush, f, shape);
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public /* synthetic */ void onMeasureResultChanged() {
        DrawModifierNode.CC.$default$onMeasureResultChanged(this);
    }

    /* JADX INFO: renamed from: setColor-8_81llA, reason: not valid java name */
    public final void m98setColor8_81llA(long j) {
        this.color = j;
    }

    public final void setBrush(Brush brush) {
        this.brush = brush;
    }

    public final void setAlpha(float f) {
        this.alpha = f;
    }

    public final Shape getShape() {
        return this.shape;
    }

    public final void setShape(Shape shape) {
        this.shape = shape;
    }

    private BackgroundNode(long j, Brush brush, float f, Shape shape) {
        this.color = j;
        this.brush = brush;
        this.alpha = f;
        this.shape = shape;
        this.lastSize = Size.Companion.m1201getUnspecifiedNHjbRc();
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(ContentDrawScope contentDrawScope) {
        if (this.shape == RectangleShapeKt.getRectangleShape()) {
            drawRect(contentDrawScope);
        } else {
            drawOutline(contentDrawScope);
        }
        contentDrawScope.drawContent();
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public void onObservedReadsChanged() {
        this.lastSize = Size.Companion.m1201getUnspecifiedNHjbRc();
        this.lastLayoutDirection = null;
        this.lastOutline = null;
        this.lastShape = null;
        DrawModifierNodeKt.invalidateDraw(this);
    }

    private final void drawRect(ContentDrawScope contentDrawScope) {
        if (!Color.m1296equalsimpl0(this.color, Color.Companion.m1309getUnspecified0d7_KjU())) {
            DrawScope.CC.m1506drawRectnJ9OG0$default(contentDrawScope, this.color, 0L, 0L, 0.0f, null, null, 0, 126, null);
        }
        Brush brush = this.brush;
        if (brush != null) {
            DrawScope.CC.m1505drawRectAsUm42w$default(contentDrawScope, brush, 0L, 0L, this.alpha, null, null, 0, 118, null);
        }
    }

    private final void drawOutline(ContentDrawScope contentDrawScope) {
        Outline outline = getOutline(contentDrawScope);
        if (!Color.m1296equalsimpl0(this.color, Color.Companion.m1309getUnspecified0d7_KjU())) {
            OutlineKt.m1377drawOutlinewDX37Ww(contentDrawScope, outline, this.color, (60 & 4) != 0 ? 1.0f : 0.0f, (60 & 8) != 0 ? Fill.INSTANCE : null, (60 & 16) != 0 ? null : null, (60 & 32) != 0 ? DrawScope.Companion.m1508getDefaultBlendMode0nO6VwU() : 0);
        }
        Brush brush = this.brush;
        if (brush != null) {
            OutlineKt.m1376drawOutlinehn5TExg$default(contentDrawScope, outline, brush, this.alpha, null, null, 0, 56, null);
        }
    }

    private final Outline getOutline(final ContentDrawScope contentDrawScope) {
        final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        if (Size.m1193equalsimpl0(contentDrawScope.mo1483getSizeNHjbRc(), this.lastSize) && contentDrawScope.getLayoutDirection() == this.lastLayoutDirection && Intrinsics.areEqual(this.lastShape, this.shape)) {
            Outline outline = this.lastOutline;
            Intrinsics.checkNotNull(outline);
            ref$ObjectRef.element = outline;
        } else {
            ObserverModifierNodeKt.observeReads(this, new Function0() { // from class: androidx.compose.foundation.BackgroundNode.getOutline.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Object invoke() {
                    m99invoke();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m99invoke() {
                    ref$ObjectRef.element = this.getShape().mo125createOutlinePq9zytI(contentDrawScope.mo1483getSizeNHjbRc(), contentDrawScope.getLayoutDirection(), contentDrawScope);
                }
            });
        }
        this.lastOutline = (Outline) ref$ObjectRef.element;
        this.lastSize = contentDrawScope.mo1483getSizeNHjbRc();
        this.lastLayoutDirection = contentDrawScope.getLayoutDirection();
        this.lastShape = this.shape;
        Object obj = ref$ObjectRef.element;
        Intrinsics.checkNotNull(obj);
        return (Outline) obj;
    }
}
