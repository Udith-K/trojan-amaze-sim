package androidx.compose.runtime;

/* JADX INFO: compiled from: Composer.kt */
/* JADX INFO: loaded from: classes.dex */
final class Invalidation {
    private Object instances;
    private final int location;
    private final RecomposeScopeImpl scope;

    public Invalidation(RecomposeScopeImpl recomposeScopeImpl, int i, Object obj) {
        this.scope = recomposeScopeImpl;
        this.location = i;
        this.instances = obj;
    }

    public final RecomposeScopeImpl getScope() {
        return this.scope;
    }

    public final int getLocation() {
        return this.location;
    }

    public final Object getInstances() {
        return this.instances;
    }

    public final void setInstances(Object obj) {
        this.instances = obj;
    }

    public final boolean isInvalid() {
        return this.scope.isInvalidFor(this.instances);
    }
}
