package io.ktor.http.parsing.regex;

import ch.qos.logback.core.CoreConstants;
import io.ktor.http.parsing.AtLeastOne;
import io.ktor.http.parsing.ComplexGrammar;
import io.ktor.http.parsing.Grammar;
import io.ktor.http.parsing.OrGrammar;
import io.ktor.http.parsing.Parser;
import io.ktor.http.parsing.RangeGrammar;
import io.ktor.http.parsing.RawGrammar;
import io.ktor.http.parsing.SimpleGrammar;
import io.ktor.http.parsing.StringGrammar;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

/* JADX INFO: compiled from: RegexParserGenerator.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class RegexParserGeneratorKt {
    public static final Parser buildRegexParser(Grammar grammar) {
        Intrinsics.checkNotNullParameter(grammar, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        return new RegexParser(new Regex(toRegex$default(grammar, linkedHashMap, 0, false, 6, null).getRegex()), linkedHashMap);
    }

    static /* synthetic */ GrammarRegex toRegex$default(Grammar grammar, Map map, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 1;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return toRegex(grammar, map, i, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final GrammarRegex toRegex(Grammar grammar, Map map, int i, boolean z) {
        if (grammar instanceof StringGrammar) {
            return new GrammarRegex(Regex.Companion.escape(((StringGrammar) grammar).getValue()), 0, false, 6, null);
        }
        if (grammar instanceof RawGrammar) {
            return new GrammarRegex(((RawGrammar) grammar).getValue(), 0, false, 6, null);
        }
        if (grammar instanceof ComplexGrammar) {
            StringBuilder sb = new StringBuilder();
            int groupsCount = z ? i + 1 : i;
            int i2 = 0;
            for (Object obj : ((ComplexGrammar) grammar).getGrammars()) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                GrammarRegex regex = toRegex((Grammar) obj, map, groupsCount, true);
                if (i2 != 0 && (grammar instanceof OrGrammar)) {
                    sb.append("|");
                }
                sb.append(regex.getRegex());
                groupsCount += regex.getGroupsCount();
                i2 = i3;
            }
            int i4 = groupsCount - i;
            if (z) {
                i4--;
            }
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "expression.toString()");
            return new GrammarRegex(string, i4, z);
        }
        if (grammar instanceof SimpleGrammar) {
            if (!(grammar instanceof AtLeastOne)) {
                throw new IllegalStateException(("Unsupported simple grammar element: " + grammar).toString());
            }
            GrammarRegex regex2 = toRegex(((SimpleGrammar) grammar).getGrammar(), map, i, true);
            return new GrammarRegex(regex2.getRegex() + '+', regex2.getGroupsCount(), false, 4, null);
        }
        if (!(grammar instanceof RangeGrammar)) {
            throw new IllegalStateException(("Unsupported grammar element: " + grammar).toString());
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append('[');
        RangeGrammar rangeGrammar = (RangeGrammar) grammar;
        sb2.append(rangeGrammar.getFrom());
        sb2.append(CoreConstants.DASH_CHAR);
        sb2.append(rangeGrammar.getTo());
        sb2.append(']');
        return new GrammarRegex(sb2.toString(), 0, false, 6, null);
    }
}
