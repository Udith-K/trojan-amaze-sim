package kotlin.text;

import java.util.Iterator;
import java.util.regex.Matcher;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: Regex.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class RegexKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final MatchResult findNext(Matcher matcher, int i, CharSequence charSequence) {
        if (matcher.find(i)) {
            return new MatcherMatchResult(matcher, charSequence);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MatchResult matchEntire(Matcher matcher, CharSequence charSequence) {
        if (matcher.matches()) {
            return new MatcherMatchResult(matcher, charSequence);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IntRange range(java.util.regex.MatchResult matchResult) {
        return RangesKt.until(matchResult.start(), matchResult.end());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IntRange range(java.util.regex.MatchResult matchResult, int i) {
        return RangesKt.until(matchResult.start(i), matchResult.end(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int toInt(Iterable iterable) {
        Iterator it = iterable.iterator();
        int value = 0;
        while (it.hasNext()) {
            value |= ((FlagEnum) it.next()).getValue();
        }
        return value;
    }
}
