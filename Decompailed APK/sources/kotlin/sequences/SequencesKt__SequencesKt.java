package kotlin.sequences;

import java.util.Iterator;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: Sequences.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class SequencesKt__SequencesKt extends SequencesKt__SequencesJVMKt {
    public static Sequence asSequence(final Iterator it) {
        Intrinsics.checkNotNullParameter(it, "<this>");
        return constrainOnce(new Sequence() { // from class: kotlin.sequences.SequencesKt__SequencesKt$asSequence$$inlined$Sequence$1
            @Override // kotlin.sequences.Sequence
            public Iterator iterator() {
                return it;
            }
        });
    }

    public static Sequence sequenceOf(Object... elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return ArraysKt.asSequence(elements);
    }

    public static Sequence emptySequence() {
        return EmptySequence.INSTANCE;
    }

    public static final Sequence flatten(Sequence sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return flatten$SequencesKt__SequencesKt(sequence, new Function1() { // from class: kotlin.sequences.SequencesKt__SequencesKt.flatten.1
            @Override // kotlin.jvm.functions.Function1
            public final Iterator invoke(Sequence it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.iterator();
            }
        });
    }

    private static final Sequence flatten$SequencesKt__SequencesKt(Sequence sequence, Function1 function1) {
        if (sequence instanceof TransformingSequence) {
            return ((TransformingSequence) sequence).flatten$kotlin_stdlib(function1);
        }
        return new FlatteningSequence(sequence, new Function1() { // from class: kotlin.sequences.SequencesKt__SequencesKt.flatten.3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return obj;
            }
        }, function1);
    }

    public static final Sequence constrainOnce(Sequence sequence) {
        Intrinsics.checkNotNullParameter(sequence, "<this>");
        return sequence instanceof ConstrainedOnceSequence ? sequence : new ConstrainedOnceSequence(sequence);
    }

    public static Sequence generateSequence(final Function0 nextFunction) {
        Intrinsics.checkNotNullParameter(nextFunction, "nextFunction");
        return constrainOnce(new GeneratorSequence(nextFunction, new Function1() { // from class: kotlin.sequences.SequencesKt__SequencesKt.generateSequence.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return nextFunction.invoke();
            }
        }));
    }

    public static Sequence generateSequence(final Object obj, Function1 nextFunction) {
        Intrinsics.checkNotNullParameter(nextFunction, "nextFunction");
        if (obj == null) {
            return EmptySequence.INSTANCE;
        }
        return new GeneratorSequence(new Function0() { // from class: kotlin.sequences.SequencesKt__SequencesKt.generateSequence.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return obj;
            }
        }, nextFunction);
    }
}
