package io.ktor.http;

import io.ktor.http.parsing.Grammar;
import io.ktor.http.parsing.Parser;
import io.ktor.http.parsing.ParserDslKt;
import io.ktor.http.parsing.PrimitivesKt;
import io.ktor.http.parsing.regex.RegexParserGeneratorKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IpParser.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class IpParserKt {
    private static final Parser IP_PARSER;
    private static final Grammar IPv4address;
    private static final Grammar IPv6address;

    public static final boolean hostIsIp(String host) {
        Intrinsics.checkNotNullParameter(host, "host");
        return IP_PARSER.match(host);
    }

    static {
        Grammar grammarThen = ParserDslKt.then(ParserDslKt.then(ParserDslKt.then(ParserDslKt.then(ParserDslKt.then(ParserDslKt.then(PrimitivesKt.getDigits(), "."), PrimitivesKt.getDigits()), "."), PrimitivesKt.getDigits()), "."), PrimitivesKt.getDigits());
        IPv4address = grammarThen;
        Grammar grammarThen2 = ParserDslKt.then(ParserDslKt.then("[", ParserDslKt.atLeastOne(ParserDslKt.or(PrimitivesKt.getHex(), ":"))), "]");
        IPv6address = grammarThen2;
        IP_PARSER = RegexParserGeneratorKt.buildRegexParser(ParserDslKt.or(grammarThen, grammarThen2));
    }
}
