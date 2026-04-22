package kotlin.sequences;

/* JADX INFO: compiled from: Sequences.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface DropTakeSequence extends Sequence {
    Sequence drop(int i);

    Sequence take(int i);
}
