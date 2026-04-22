package androidx.compose.ui.draw;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticBackport0;
import androidx.compose.ui.graphics.BlockGraphicsLayerModifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.unit.Dp;
import ch.qos.logback.core.CoreConstants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Shadow.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0011\b\u0080\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B/\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0019\u001a\u00020\u0018HÖ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001c\u001a\u00020\u001bHÖ\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u001a\u0010 \u001a\u00020\u00072\b\u0010\u001f\u001a\u0004\u0018\u00010\u001eHÖ\u0003¢\u0006\u0004\b \u0010!R\u001d\u0010\u0004\u001a\u00020\u00038\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0004\u0010\"\u001a\u0004\b#\u0010$R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010%\u001a\u0004\b&\u0010'R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010(\u001a\u0004\b)\u0010*R\u001d\u0010\n\u001a\u00020\t8\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\n\u0010+\u001a\u0004\b,\u0010-R\u001d\u0010\u000b\u001a\u00020\t8\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u000b\u0010+\u001a\u0004\b.\u0010-\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006/"}, d2 = {"Landroidx/compose/ui/draw/ShadowGraphicsLayerElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/ui/graphics/BlockGraphicsLayerModifier;", "Landroidx/compose/ui/unit/Dp;", "elevation", "Landroidx/compose/ui/graphics/Shape;", "shape", "", "clip", "Landroidx/compose/ui/graphics/Color;", "ambientColor", "spotColor", "<init>", "(FLandroidx/compose/ui/graphics/Shape;ZJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "", "createBlock", "()Lkotlin/jvm/functions/Function1;", "create", "()Landroidx/compose/ui/graphics/BlockGraphicsLayerModifier;", "node", "update", "(Landroidx/compose/ui/graphics/BlockGraphicsLayerModifier;)V", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "equals", "(Ljava/lang/Object;)Z", "F", "getElevation-D9Ej5fM", "()F", "Landroidx/compose/ui/graphics/Shape;", "getShape", "()Landroidx/compose/ui/graphics/Shape;", "Z", "getClip", "()Z", "J", "getAmbientColor-0d7_KjU", "()J", "getSpotColor-0d7_KjU", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class ShadowGraphicsLayerElement extends ModifierNodeElement {
    private final long ambientColor;
    private final boolean clip;
    private final float elevation;
    private final Shape shape;
    private final long spotColor;

    public /* synthetic */ ShadowGraphicsLayerElement(float f, Shape shape, boolean z, long j, long j2, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, shape, z, j, j2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ShadowGraphicsLayerElement)) {
            return false;
        }
        ShadowGraphicsLayerElement shadowGraphicsLayerElement = (ShadowGraphicsLayerElement) other;
        return Dp.m2418equalsimpl0(this.elevation, shadowGraphicsLayerElement.elevation) && Intrinsics.areEqual(this.shape, shadowGraphicsLayerElement.shape) && this.clip == shadowGraphicsLayerElement.clip && Color.m1296equalsimpl0(this.ambientColor, shadowGraphicsLayerElement.ambientColor) && Color.m1296equalsimpl0(this.spotColor, shadowGraphicsLayerElement.spotColor);
    }

    public int hashCode() {
        return (((((((Dp.m2419hashCodeimpl(this.elevation) * 31) + this.shape.hashCode()) * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.clip)) * 31) + Color.m1302hashCodeimpl(this.ambientColor)) * 31) + Color.m1302hashCodeimpl(this.spotColor);
    }

    public String toString() {
        return "ShadowGraphicsLayerElement(elevation=" + ((Object) Dp.m2420toStringimpl(this.elevation)) + ", shape=" + this.shape + ", clip=" + this.clip + ", ambientColor=" + ((Object) Color.m1303toStringimpl(this.ambientColor)) + ", spotColor=" + ((Object) Color.m1303toStringimpl(this.spotColor)) + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    /* JADX INFO: renamed from: getElevation-D9Ej5fM, reason: not valid java name and from getter */
    public final float getElevation() {
        return this.elevation;
    }

    public final Shape getShape() {
        return this.shape;
    }

    public final boolean getClip() {
        return this.clip;
    }

    /* JADX INFO: renamed from: getAmbientColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getAmbientColor() {
        return this.ambientColor;
    }

    /* JADX INFO: renamed from: getSpotColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getSpotColor() {
        return this.spotColor;
    }

    private ShadowGraphicsLayerElement(float f, Shape shape, boolean z, long j, long j2) {
        this.elevation = f;
        this.shape = shape;
        this.clip = z;
        this.ambientColor = j;
        this.spotColor = j2;
    }

    private final Function1 createBlock() {
        return new Function1() { // from class: androidx.compose.ui.draw.ShadowGraphicsLayerElement.createBlock.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((GraphicsLayerScope) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(GraphicsLayerScope graphicsLayerScope) {
                graphicsLayerScope.setShadowElevation(graphicsLayerScope.mo210toPx0680j_4(ShadowGraphicsLayerElement.this.getElevation()));
                graphicsLayerScope.setShape(ShadowGraphicsLayerElement.this.getShape());
                graphicsLayerScope.setClip(ShadowGraphicsLayerElement.this.getClip());
                graphicsLayerScope.mo1336setAmbientShadowColor8_81llA(ShadowGraphicsLayerElement.this.getAmbientColor());
                graphicsLayerScope.mo1338setSpotShadowColor8_81llA(ShadowGraphicsLayerElement.this.getSpotColor());
            }
        };
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    /* JADX INFO: renamed from: create */
    public BlockGraphicsLayerModifier getNode() {
        return new BlockGraphicsLayerModifier(createBlock());
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(BlockGraphicsLayerModifier node) {
        node.setLayerBlock(createBlock());
        node.invalidateLayerBlock();
    }
}
