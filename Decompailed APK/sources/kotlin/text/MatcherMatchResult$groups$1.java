package kotlin.text;

import java.util.Iterator;
import kotlin.collections.AbstractCollection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: Regex.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class MatcherMatchResult$groups$1 extends AbstractCollection implements MatchGroupCollection {
    final /* synthetic */ MatcherMatchResult this$0;

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return false;
    }

    MatcherMatchResult$groups$1(MatcherMatchResult matcherMatchResult) {
        this.this$0 = matcherMatchResult;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object obj) {
        if (obj == null ? true : obj instanceof MatchGroup) {
            return contains((MatchGroup) obj);
        }
        return false;
    }

    public /* bridge */ boolean contains(MatchGroup matchGroup) {
        return super.contains((Object) matchGroup);
    }

    @Override // kotlin.collections.AbstractCollection
    public int getSize() {
        return this.this$0.getMatchResult().groupCount() + 1;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator iterator() {
        return SequencesKt.map(CollectionsKt.asSequence(CollectionsKt.getIndices(this)), new Function1() { // from class: kotlin.text.MatcherMatchResult$groups$1$iterator$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return invoke(((Number) obj).intValue());
            }

            public final MatchGroup invoke(int i) {
                return this.this$0.get(i);
            }
        }).iterator();
    }

    @Override // kotlin.text.MatchGroupCollection
    public MatchGroup get(int i) {
        IntRange intRangeRange = RegexKt.range(this.this$0.getMatchResult(), i);
        if (intRangeRange.getStart().intValue() < 0) {
            return null;
        }
        String strGroup = this.this$0.getMatchResult().group(i);
        Intrinsics.checkNotNullExpressionValue(strGroup, "group(...)");
        return new MatchGroup(strGroup, intRangeRange);
    }
}
