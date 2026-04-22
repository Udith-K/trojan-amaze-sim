package io.ktor.utils.io.internal;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.KotlinNothingValueException;

/* JADX INFO: compiled from: RingBufferCapacity.kt */
/* JADX INFO: loaded from: classes.dex */
public final class RingBufferCapacity {
    public static final /* synthetic */ AtomicIntegerFieldUpdater _availableForRead$FU$internal = AtomicIntegerFieldUpdater.newUpdater(RingBufferCapacity.class, "_availableForRead$internal");
    public static final /* synthetic */ AtomicIntegerFieldUpdater _availableForWrite$FU$internal = AtomicIntegerFieldUpdater.newUpdater(RingBufferCapacity.class, "_availableForWrite$internal");
    static final /* synthetic */ AtomicIntegerFieldUpdater _pendingToFlush$FU = AtomicIntegerFieldUpdater.newUpdater(RingBufferCapacity.class, "_pendingToFlush");
    public volatile /* synthetic */ int _availableForWrite$internal;
    private final int totalCapacity;
    public volatile /* synthetic */ int _availableForRead$internal = 0;
    volatile /* synthetic */ int _pendingToFlush = 0;

    public RingBufferCapacity(int i) {
        this.totalCapacity = i;
        this._availableForWrite$internal = i;
    }

    public final void resetForWrite() {
        this._availableForRead$internal = 0;
        this._pendingToFlush = 0;
        this._availableForWrite$internal = this.totalCapacity;
    }

    public final void resetForRead() {
        this._availableForRead$internal = this.totalCapacity;
        this._availableForWrite$internal = 0;
        this._pendingToFlush = 0;
    }

    private final Void completeReadOverflow(int i, int i2, int i3) {
        throw new IllegalArgumentException("Completed read overflow: " + i + " + " + i3 + " = " + i2 + " > " + this.totalCapacity);
    }

    private final Void completeWriteOverflow(int i, int i2) {
        throw new IllegalArgumentException("Complete write overflow: " + i + " + " + i2 + " > " + this.totalCapacity);
    }

    public final boolean flush() {
        int andSet = _pendingToFlush$FU.getAndSet(this, 0);
        return andSet == 0 ? this._availableForRead$internal > 0 : _availableForRead$FU$internal.addAndGet(this, andSet) > 0;
    }

    public final void forceLockForRelease() {
        _availableForWrite$FU$internal.getAndSet(this, 0);
    }

    public final boolean isEmpty() {
        return this._availableForWrite$internal == this.totalCapacity;
    }

    public final boolean isFull() {
        return this._availableForWrite$internal == 0;
    }

    public String toString() {
        return "RingBufferCapacity[read: " + this._availableForRead$internal + ", write: " + this._availableForWrite$internal + ", flush: " + this._pendingToFlush + ", capacity: " + this.totalCapacity + ']';
    }

    public final boolean tryReadExact(int i) {
        int i2;
        do {
            i2 = this._availableForRead$internal;
            if (i2 < i) {
                return false;
            }
        } while (!_availableForRead$FU$internal.compareAndSet(this, i2, i2 - i));
        return true;
    }

    public final int tryReadAtMost(int i) {
        int i2;
        int iMin;
        do {
            i2 = this._availableForRead$internal;
            iMin = Math.min(i, i2);
            if (iMin == 0) {
                return 0;
            }
        } while (!_availableForRead$FU$internal.compareAndSet(this, i2, i2 - iMin));
        return Math.min(i, i2);
    }

    public final int tryWriteAtLeast(int i) {
        int i2;
        do {
            i2 = this._availableForWrite$internal;
            if (i2 < i) {
                return 0;
            }
        } while (!_availableForWrite$FU$internal.compareAndSet(this, i2, 0));
        return i2;
    }

    public final int tryWriteAtMost(int i) {
        int i2;
        int iMin;
        do {
            i2 = this._availableForWrite$internal;
            iMin = Math.min(i, i2);
            if (iMin == 0) {
                return 0;
            }
        } while (!_availableForWrite$FU$internal.compareAndSet(this, i2, i2 - iMin));
        return Math.min(i, i2);
    }

    public final void completeRead(int i) {
        int i2;
        int i3;
        do {
            i2 = this._availableForWrite$internal;
            i3 = i2 + i;
            if (i3 > this.totalCapacity) {
                completeReadOverflow(i2, i3, i);
                throw new KotlinNothingValueException();
            }
        } while (!_availableForWrite$FU$internal.compareAndSet(this, i2, i3));
    }

    public final void completeWrite(int i) {
        int i2;
        int i3;
        do {
            i2 = this._pendingToFlush;
            i3 = i2 + i;
            if (i3 > this.totalCapacity) {
                completeWriteOverflow(i2, i);
                throw new KotlinNothingValueException();
            }
        } while (!_pendingToFlush$FU.compareAndSet(this, i2, i3));
    }

    public final boolean tryLockForRelease() {
        int i;
        do {
            i = this._availableForWrite$internal;
            if (this._pendingToFlush > 0 || this._availableForRead$internal > 0 || i != this.totalCapacity) {
                return false;
            }
        } while (!_availableForWrite$FU$internal.compareAndSet(this, i, 0));
        return true;
    }
}
