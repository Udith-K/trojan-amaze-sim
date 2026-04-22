package io.ktor.http.parsing;

import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ParserDsl.kt */
/* JADX INFO: loaded from: classes.dex */
public final class AtLeastOne extends Grammar implements SimpleGrammar {
    private final Grammar grammar;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AtLeastOne(Grammar grammar) {
        super(null);
        Intrinsics.checkNotNullParameter(grammar, "grammar");
        this.grammar = grammar;
    }

    @Override // io.ktor.http.parsing.SimpleGrammar
    public Grammar getGrammar() {
        return this.grammar;
    }
}
