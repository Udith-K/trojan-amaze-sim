package androidx.compose.foundation.layout;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticBackport0;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Padding.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0017\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BK\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u001a\u0010\u001a\u001a\u00020\b2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u0096\u0002¢\u0006\u0004\b\u001a\u0010\u001bR(\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u0004\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R(\u0010\u0005\u001a\u00020\u00038\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u0005\u0010\u001c\u001a\u0004\b!\u0010\u001e\"\u0004\b\"\u0010 R(\u0010\u0006\u001a\u00020\u00038\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u0006\u0010\u001c\u001a\u0004\b#\u0010\u001e\"\u0004\b$\u0010 R(\u0010\u0007\u001a\u00020\u00038\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u0007\u0010\u001c\u001a\u0004\b%\u0010\u001e\"\u0004\b&\u0010 R\"\u0010\t\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010'\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R#\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n8\u0006¢\u0006\f\n\u0004\b\r\u0010,\u001a\u0004\b-\u0010.\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006/"}, d2 = {"Landroidx/compose/foundation/layout/PaddingElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/layout/PaddingNode;", "Landroidx/compose/ui/unit/Dp;", "start", "top", "end", "bottom", "", "rtlAware", "Lkotlin/Function1;", "Landroidx/compose/ui/platform/InspectorInfo;", "", "inspectorInfo", "<init>", "(FFFFZLkotlin/jvm/functions/Function1;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "create", "()Landroidx/compose/foundation/layout/PaddingNode;", "node", "update", "(Landroidx/compose/foundation/layout/PaddingNode;)V", "", "hashCode", "()I", "", "other", "equals", "(Ljava/lang/Object;)Z", "F", "getStart-D9Ej5fM", "()F", "setStart-0680j_4", "(F)V", "getTop-D9Ej5fM", "setTop-0680j_4", "getEnd-D9Ej5fM", "setEnd-0680j_4", "getBottom-D9Ej5fM", "setBottom-0680j_4", "Z", "getRtlAware", "()Z", "setRtlAware", "(Z)V", "Lkotlin/jvm/functions/Function1;", "getInspectorInfo", "()Lkotlin/jvm/functions/Function1;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class PaddingElement extends ModifierNodeElement {
    private float bottom;
    private float end;
    private final Function1 inspectorInfo;
    private boolean rtlAware;
    private float start;
    private float top;

    public /* synthetic */ PaddingElement(float f, float f2, float f3, float f4, boolean z, Function1 function1, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, z, function1);
    }

    private PaddingElement(float f, float f2, float f3, float f4, boolean z, Function1 function1) {
        this.start = f;
        this.top = f2;
        this.end = f3;
        this.bottom = f4;
        this.rtlAware = z;
        this.inspectorInfo = function1;
        if (f >= 0.0f || Dp.m2418equalsimpl0(f, Dp.Companion.m2425getUnspecifiedD9Ej5fM())) {
            float f5 = this.top;
            if (f5 >= 0.0f || Dp.m2418equalsimpl0(f5, Dp.Companion.m2425getUnspecifiedD9Ej5fM())) {
                float f6 = this.end;
                if (f6 >= 0.0f || Dp.m2418equalsimpl0(f6, Dp.Companion.m2425getUnspecifiedD9Ej5fM())) {
                    float f7 = this.bottom;
                    if (f7 >= 0.0f || Dp.m2418equalsimpl0(f7, Dp.Companion.m2425getUnspecifiedD9Ej5fM())) {
                        return;
                    }
                }
            }
        }
        throw new IllegalArgumentException("Padding must be non-negative");
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    /* JADX INFO: renamed from: create */
    public PaddingNode getNode() {
        return new PaddingNode(this.start, this.top, this.end, this.bottom, this.rtlAware, null);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(PaddingNode node) {
        node.m270setStart0680j_4(this.start);
        node.m271setTop0680j_4(this.top);
        node.m269setEnd0680j_4(this.end);
        node.m268setBottom0680j_4(this.bottom);
        node.setRtlAware(this.rtlAware);
    }

    public int hashCode() {
        return (((((((Dp.m2419hashCodeimpl(this.start) * 31) + Dp.m2419hashCodeimpl(this.top)) * 31) + Dp.m2419hashCodeimpl(this.end)) * 31) + Dp.m2419hashCodeimpl(this.bottom)) * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.rtlAware);
    }

    public boolean equals(Object other) {
        PaddingElement paddingElement = other instanceof PaddingElement ? (PaddingElement) other : null;
        return paddingElement != null && Dp.m2418equalsimpl0(this.start, paddingElement.start) && Dp.m2418equalsimpl0(this.top, paddingElement.top) && Dp.m2418equalsimpl0(this.end, paddingElement.end) && Dp.m2418equalsimpl0(this.bottom, paddingElement.bottom) && this.rtlAware == paddingElement.rtlAware;
    }
}
