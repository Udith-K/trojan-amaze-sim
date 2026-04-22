package androidx.compose.foundation.gestures;

import androidx.compose.foundation.gestures.ContentInViewNode;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.geometry.Rect;
import java.util.concurrent.CancellationException;
import kotlin.Result;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlinx.coroutines.CancellableContinuation;

/* JADX INFO: compiled from: BringIntoViewRequestPriorityQueue.kt */
/* JADX INFO: loaded from: classes.dex */
public final class BringIntoViewRequestPriorityQueue {
    public static final int $stable = MutableVector.$stable;
    private final MutableVector requests = new MutableVector(new ContentInViewNode.Request[16], 0);

    public final boolean enqueue(final ContentInViewNode.Request request) {
        Rect rect = (Rect) request.getCurrentBounds().invoke();
        if (rect == null) {
            CancellableContinuation continuation = request.getContinuation();
            Result.Companion companion = Result.Companion;
            continuation.resumeWith(Result.m2639constructorimpl(Unit.INSTANCE));
            return false;
        }
        request.getContinuation().invokeOnCancellation(new Function1() { // from class: androidx.compose.foundation.gestures.BringIntoViewRequestPriorityQueue.enqueue.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Throwable) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable th) {
                BringIntoViewRequestPriorityQueue.this.requests.remove(request);
            }
        });
        IntRange intRange = new IntRange(0, this.requests.getSize() - 1);
        int first = intRange.getFirst();
        int last = intRange.getLast();
        if (first <= last) {
            while (true) {
                Rect rect2 = (Rect) ((ContentInViewNode.Request) this.requests.getContent()[last]).getCurrentBounds().invoke();
                if (rect2 != null) {
                    Rect rectIntersect = rect.intersect(rect2);
                    if (Intrinsics.areEqual(rectIntersect, rect)) {
                        this.requests.add(last + 1, request);
                        return true;
                    }
                    if (!Intrinsics.areEqual(rectIntersect, rect2)) {
                        CancellationException cancellationException = new CancellationException("bringIntoView call interrupted by a newer, non-overlapping call");
                        int size = this.requests.getSize() - 1;
                        if (size <= last) {
                            while (true) {
                                ((ContentInViewNode.Request) this.requests.getContent()[last]).getContinuation().cancel(cancellationException);
                                if (size == last) {
                                    break;
                                }
                                size++;
                            }
                        }
                    }
                }
                if (last == first) {
                    break;
                }
                last--;
            }
        }
        this.requests.add(0, request);
        return true;
    }

    public final void resumeAndRemoveAll() {
        IntRange intRange = new IntRange(0, this.requests.getSize() - 1);
        int first = intRange.getFirst();
        int last = intRange.getLast();
        if (first <= last) {
            while (true) {
                ((ContentInViewNode.Request) this.requests.getContent()[first]).getContinuation().resumeWith(Result.m2639constructorimpl(Unit.INSTANCE));
                if (first == last) {
                    break;
                } else {
                    first++;
                }
            }
        }
        this.requests.clear();
    }

    public final void cancelAndRemoveAll(Throwable th) {
        MutableVector mutableVector = this.requests;
        int size = mutableVector.getSize();
        CancellableContinuation[] cancellableContinuationArr = new CancellableContinuation[size];
        for (int i = 0; i < size; i++) {
            cancellableContinuationArr[i] = ((ContentInViewNode.Request) mutableVector.getContent()[i]).getContinuation();
        }
        for (int i2 = 0; i2 < size; i2++) {
            cancellableContinuationArr[i2].cancel(th);
        }
        if (!this.requests.isEmpty()) {
            throw new IllegalStateException("uncancelled requests present");
        }
    }
}
