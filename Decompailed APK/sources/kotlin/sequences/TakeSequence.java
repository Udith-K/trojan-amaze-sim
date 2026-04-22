package kotlin.sequences;

import ch.qos.logback.core.CoreConstants;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: Sequences.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class TakeSequence implements Sequence, DropTakeSequence {
    private final int count;
    private final Sequence sequence;

    public TakeSequence(Sequence sequence, int i) {
        Intrinsics.checkNotNullParameter(sequence, "sequence");
        this.sequence = sequence;
        this.count = i;
        if (i >= 0) {
            return;
        }
        throw new IllegalArgumentException(("count must be non-negative, but was " + i + CoreConstants.DOT).toString());
    }

    @Override // kotlin.sequences.DropTakeSequence
    public Sequence drop(int i) {
        int i2 = this.count;
        return i >= i2 ? SequencesKt.emptySequence() : new SubSequence(this.sequence, i, i2);
    }

    @Override // kotlin.sequences.DropTakeSequence
    public Sequence take(int i) {
        return i >= this.count ? this : new TakeSequence(this.sequence, i);
    }

    /* JADX INFO: renamed from: kotlin.sequences.TakeSequence$iterator$1, reason: invalid class name */
    /* JADX INFO: compiled from: Sequences.kt */
    public static final class AnonymousClass1 implements Iterator, KMappedMarker {
        private final Iterator iterator;
        private int left;

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        AnonymousClass1(TakeSequence takeSequence) {
            this.left = takeSequence.count;
            this.iterator = takeSequence.sequence.iterator();
        }

        @Override // java.util.Iterator
        public Object next() {
            int i = this.left;
            if (i == 0) {
                throw new NoSuchElementException();
            }
            this.left = i - 1;
            return this.iterator.next();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.left > 0 && this.iterator.hasNext();
        }
    }

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return new AnonymousClass1(this);
    }
}
