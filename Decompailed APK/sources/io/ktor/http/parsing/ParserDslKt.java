package io.ktor.http.parsing;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ParserDsl.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ParserDslKt {
    public static final Grammar then(String str, Grammar grammar) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(grammar, "grammar");
        return then(new StringGrammar(str), grammar);
    }

    public static final Grammar then(Grammar grammar, Grammar grammar2) {
        Intrinsics.checkNotNullParameter(grammar, "<this>");
        Intrinsics.checkNotNullParameter(grammar2, "grammar");
        return new SequenceGrammar(CollectionsKt.listOf((Object[]) new Grammar[]{grammar, grammar2}));
    }

    public static final Grammar then(Grammar grammar, String value) {
        Intrinsics.checkNotNullParameter(grammar, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        return then(grammar, new StringGrammar(value));
    }

    public static final Grammar or(Grammar grammar, Grammar grammar2) {
        Intrinsics.checkNotNullParameter(grammar, "<this>");
        Intrinsics.checkNotNullParameter(grammar2, "grammar");
        return new OrGrammar(CollectionsKt.listOf((Object[]) new Grammar[]{grammar, grammar2}));
    }

    public static final Grammar or(Grammar grammar, String value) {
        Intrinsics.checkNotNullParameter(grammar, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        return or(grammar, new StringGrammar(value));
    }

    public static final Grammar atLeastOne(Grammar grammar) {
        Intrinsics.checkNotNullParameter(grammar, "grammar");
        return new AtLeastOne(grammar);
    }

    public static final Grammar to(char c, char c2) {
        return new RangeGrammar(c, c2);
    }
}
