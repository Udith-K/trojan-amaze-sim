package androidx.compose.ui.graphics.vector;

import androidx.compose.ui.graphics.drawscope.DrawScope;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Vector.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class VNode {
    private Function1 invalidateListener;

    public /* synthetic */ VNode(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract void draw(DrawScope drawScope);

    private VNode() {
    }

    public Function1 getInvalidateListener$ui_release() {
        return this.invalidateListener;
    }

    public void setInvalidateListener$ui_release(Function1 function1) {
        this.invalidateListener = function1;
    }

    public final void invalidate() {
        Function1 invalidateListener$ui_release = getInvalidateListener$ui_release();
        if (invalidateListener$ui_release != null) {
            invalidateListener$ui_release.invoke(this);
        }
    }
}
