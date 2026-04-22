package io.ktor.utils.io.pool;

import io.ktor.utils.io.pool.ObjectPool;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* JADX INFO: compiled from: DefaultPool.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class DefaultPool implements ObjectPool {
    public static final Companion Companion = new Companion(null);
    private static final AtomicLongFieldUpdater Top;
    private final int capacity;
    private final AtomicReferenceArray instances;
    private final int maxIndex;
    private final int[] next;
    private final int shift;
    private volatile long top;

    protected Object clearInstance(Object instance) {
        Intrinsics.checkNotNullParameter(instance, "instance");
        return instance;
    }

    protected void disposeInstance(Object instance) {
        Intrinsics.checkNotNullParameter(instance, "instance");
    }

    protected abstract Object produceInstance();

    protected void validateInstance(Object instance) {
        Intrinsics.checkNotNullParameter(instance, "instance");
    }

    public DefaultPool(int i) {
        this.capacity = i;
        if (i <= 0) {
            throw new IllegalArgumentException(("capacity should be positive but it is " + i).toString());
        }
        if (i > 536870911) {
            throw new IllegalArgumentException(("capacity should be less or equal to 536870911 but it is " + i).toString());
        }
        int iHighestOneBit = Integer.highestOneBit((i * 4) - 1) * 2;
        this.maxIndex = iHighestOneBit;
        this.shift = Integer.numberOfLeadingZeros(iHighestOneBit) + 1;
        this.instances = new AtomicReferenceArray(iHighestOneBit + 1);
        this.next = new int[iHighestOneBit + 1];
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        ObjectPool.DefaultImpls.close(this);
    }

    @Override // io.ktor.utils.io.pool.ObjectPool
    public final Object borrow() {
        Object objClearInstance;
        Object objTryPop = tryPop();
        return (objTryPop == null || (objClearInstance = clearInstance(objTryPop)) == null) ? produceInstance() : objClearInstance;
    }

    @Override // io.ktor.utils.io.pool.ObjectPool
    public final void recycle(Object instance) {
        Intrinsics.checkNotNullParameter(instance, "instance");
        validateInstance(instance);
        if (tryPush(instance)) {
            return;
        }
        disposeInstance(instance);
    }

    @Override // io.ktor.utils.io.pool.ObjectPool
    public final void dispose() {
        while (true) {
            Object objTryPop = tryPop();
            if (objTryPop == null) {
                return;
            } else {
                disposeInstance(objTryPop);
            }
        }
    }

    private final boolean tryPush(Object obj) {
        int iIdentityHashCode = ((System.identityHashCode(obj) * (-1640531527)) >>> this.shift) + 1;
        for (int i = 0; i < 8; i++) {
            if (DefaultPool$$ExternalSyntheticBackportWithForwarding0.m(this.instances, iIdentityHashCode, null, obj)) {
                pushTop(iIdentityHashCode);
                return true;
            }
            iIdentityHashCode--;
            if (iIdentityHashCode == 0) {
                iIdentityHashCode = this.maxIndex;
            }
        }
        return false;
    }

    private final Object tryPop() {
        int iPopTop = popTop();
        if (iPopTop == 0) {
            return null;
        }
        return this.instances.getAndSet(iPopTop, null);
    }

    private final void pushTop(int i) {
        long j;
        long j2;
        if (i <= 0) {
            throw new IllegalArgumentException("index should be positive");
        }
        do {
            j = this.top;
            j2 = ((j >> 32) & BodyPartID.bodyIdMax) + 1;
            this.next[i] = (int) (BodyPartID.bodyIdMax & j);
        } while (!Top.compareAndSet(this, j, (j2 << 32) | ((long) i)));
    }

    private final int popTop() {
        long j;
        long j2;
        int i;
        do {
            j = this.top;
            if (j == 0) {
                return 0;
            }
            j2 = ((j >> 32) & BodyPartID.bodyIdMax) + 1;
            i = (int) (BodyPartID.bodyIdMax & j);
            if (i == 0) {
                return 0;
            }
        } while (!Top.compareAndSet(this, j, (j2 << 32) | ((long) this.next[i])));
        return i;
    }

    /* JADX INFO: compiled from: DefaultPool.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        AtomicLongFieldUpdater atomicLongFieldUpdaterNewUpdater = AtomicLongFieldUpdater.newUpdater(DefaultPool.class, new MutablePropertyReference1Impl() { // from class: io.ktor.utils.io.pool.DefaultPool$Companion$Top$1
            @Override // kotlin.jvm.internal.MutablePropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return Long.valueOf(((DefaultPool) obj).top);
            }
        }.getName());
        Intrinsics.checkNotNullExpressionValue(atomicLongFieldUpdaterNewUpdater, "newUpdater(Owner::class.java, p.name)");
        Top = atomicLongFieldUpdaterNewUpdater;
    }
}
