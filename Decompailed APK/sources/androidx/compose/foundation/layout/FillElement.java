package androidx.compose.foundation.layout;

import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Size.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\b\u0002\u0018\u0000 \u001c2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001cB\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0096\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0019R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u001aR\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\u001b¨\u0006\u001d"}, d2 = {"Landroidx/compose/foundation/layout/FillElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/layout/FillNode;", "Landroidx/compose/foundation/layout/Direction;", "direction", "", "fraction", "", "inspectorName", "<init>", "(Landroidx/compose/foundation/layout/Direction;FLjava/lang/String;)V", "create", "()Landroidx/compose/foundation/layout/FillNode;", "node", "", "update", "(Landroidx/compose/foundation/layout/FillNode;)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "Landroidx/compose/foundation/layout/Direction;", "F", "Ljava/lang/String;", "Companion", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class FillElement extends ModifierNodeElement {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Direction direction;
    private final float fraction;
    private final String inspectorName;

    public FillElement(Direction direction, float f, String str) {
        this.direction = direction;
        this.fraction = f;
        this.inspectorName = str;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    /* JADX INFO: renamed from: create */
    public FillNode getNode() {
        return new FillNode(this.direction, this.fraction);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(FillNode node) {
        node.setDirection(this.direction);
        node.setFraction(this.fraction);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FillElement)) {
            return false;
        }
        FillElement fillElement = (FillElement) other;
        return this.direction == fillElement.direction && this.fraction == fillElement.fraction;
    }

    public int hashCode() {
        return (this.direction.hashCode() * 31) + Float.floatToIntBits(this.fraction);
    }

    /* JADX INFO: compiled from: Size.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FillElement width(float f) {
            return new FillElement(Direction.Horizontal, f, "fillMaxWidth");
        }

        public final FillElement height(float f) {
            return new FillElement(Direction.Vertical, f, "fillMaxHeight");
        }

        public final FillElement size(float f) {
            return new FillElement(Direction.Both, f, "fillMaxSize");
        }
    }
}
