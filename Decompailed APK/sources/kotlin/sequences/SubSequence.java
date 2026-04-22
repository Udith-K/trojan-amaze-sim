package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: compiled from: Sequences.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class SubSequence implements Sequence, DropTakeSequence {
    private final int endIndex;
    private final Sequence sequence;
    private final int startIndex;

    public SubSequence(Sequence sequence, int i, int i2) {
        Intrinsics.checkNotNullParameter(sequence, "sequence");
        this.sequence = sequence;
        this.startIndex = i;
        this.endIndex = i2;
        if (i < 0) {
            throw new IllegalArgumentException(("startIndex should be non-negative, but is " + i).toString());
        }
        if (i2 < 0) {
            throw new IllegalArgumentException(("endIndex should be non-negative, but is " + i2).toString());
        }
        if (i2 >= i) {
            return;
        }
        throw new IllegalArgumentException(("endIndex should be not less than startIndex, but was " + i2 + " < " + i).toString());
    }

    private final int getCount() {
        return this.endIndex - this.startIndex;
    }

    @Override // kotlin.sequences.DropTakeSequence
    public Sequence drop(int i) {
        return i >= getCount() ? SequencesKt.emptySequence() : new SubSequence(this.sequence, this.startIndex + i, this.endIndex);
    }

    @Override // kotlin.sequences.DropTakeSequence
    public Sequence take(int i) {
        if (i >= getCount()) {
            return this;
        }
        Sequence sequence = this.sequence;
        int i2 = this.startIndex;
        return new SubSequence(sequence, i2, i + i2);
    }

    /* JADX INFO: renamed from: kotlin.sequences.SubSequence$iterator$1, reason: invalid class name */
    /* JADX INFO: compiled from: Sequences.kt */
    public static final class AnonymousClass1 implements Iterator, KMappedMarker {
        private final Iterator iterator;
        private int position;

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        AnonymousClass1() {
            this.iterator = SubSequence.this.sequence.iterator();
        }

        private final void drop() {
            while (this.position < SubSequence.this.startIndex && this.iterator.hasNext()) {
                this.iterator.next();
                this.position++;
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            drop();
            return this.position < SubSequence.this.endIndex && this.iterator.hasNext();
        }

        @Override // java.util.Iterator
        public Object next() {
            drop();
            if (this.position >= SubSequence.this.endIndex) {
                throw new NoSuchElementException();
            }
            this.position++;
            return this.iterator.next();
        }
    }

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return new AnonymousClass1();
    }
}
