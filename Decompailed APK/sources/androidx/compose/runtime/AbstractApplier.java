package androidx.compose.runtime;

import androidx.compose.runtime.Applier;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: Applier.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractApplier implements Applier {
    private Object current;
    private final Object root;
    private final List stack = new ArrayList();

    @Override // androidx.compose.runtime.Applier
    public /* synthetic */ void onBeginChanges() {
        Applier.CC.$default$onBeginChanges(this);
    }

    protected abstract void onClear();

    @Override // androidx.compose.runtime.Applier
    public /* synthetic */ void onEndChanges() {
        Applier.CC.$default$onEndChanges(this);
    }

    public AbstractApplier(Object obj) {
        this.root = obj;
        this.current = obj;
    }

    public final Object getRoot() {
        return this.root;
    }

    @Override // androidx.compose.runtime.Applier
    public Object getCurrent() {
        return this.current;
    }

    protected void setCurrent(Object obj) {
        this.current = obj;
    }

    @Override // androidx.compose.runtime.Applier
    public void down(Object obj) {
        this.stack.add(getCurrent());
        setCurrent(obj);
    }

    @Override // androidx.compose.runtime.Applier
    public void up() {
        if (this.stack.isEmpty()) {
            PreconditionsKt.throwIllegalStateException("empty stack");
        }
        setCurrent(this.stack.remove(r0.size() - 1));
    }

    @Override // androidx.compose.runtime.Applier
    public final void clear() {
        this.stack.clear();
        setCurrent(this.root);
        onClear();
    }
}
