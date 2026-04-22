package kotlinx.coroutines.internal;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: Atomic.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AtomicOp extends OpDescriptor {
    private static final AtomicReferenceFieldUpdater _consensus$FU = AtomicReferenceFieldUpdater.newUpdater(AtomicOp.class, Object.class, "_consensus");
    private volatile Object _consensus = AtomicKt.NO_DECISION;

    public abstract void complete(Object obj, Object obj2);

    public abstract Object prepare(Object obj);

    private final Object decide(Object obj) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _consensus$FU;
        Object obj2 = atomicReferenceFieldUpdater.get(this);
        Object obj3 = AtomicKt.NO_DECISION;
        return obj2 != obj3 ? obj2 : AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater, this, obj3, obj) ? obj : atomicReferenceFieldUpdater.get(this);
    }

    @Override // kotlinx.coroutines.internal.OpDescriptor
    public final Object perform(Object obj) {
        Object objDecide = _consensus$FU.get(this);
        if (objDecide == AtomicKt.NO_DECISION) {
            objDecide = decide(prepare(obj));
        }
        complete(obj, objDecide);
        return objDecide;
    }
}
