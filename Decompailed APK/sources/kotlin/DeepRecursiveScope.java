package kotlin;

import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: DeepRecursive.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class DeepRecursiveScope {
    public /* synthetic */ DeepRecursiveScope(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract Object callRecursive(Object obj, Continuation continuation);

    private DeepRecursiveScope() {
    }
}
