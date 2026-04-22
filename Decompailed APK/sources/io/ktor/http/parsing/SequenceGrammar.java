package io.ktor.http.parsing;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ParserDsl.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SequenceGrammar extends Grammar implements ComplexGrammar {
    private final List grammars;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SequenceGrammar(List sourceGrammars) {
        super(null);
        Intrinsics.checkNotNullParameter(sourceGrammars, "sourceGrammars");
        ArrayList arrayList = new ArrayList();
        for (Object obj : sourceGrammars) {
            if (obj instanceof SequenceGrammar) {
                CollectionsKt.addAll(arrayList, ((ComplexGrammar) obj).getGrammars());
            } else {
                arrayList.add(obj);
            }
        }
        this.grammars = arrayList;
    }

    @Override // io.ktor.http.parsing.ComplexGrammar
    public List getGrammars() {
        return this.grammars;
    }
}
