package io.ktor.http;

import ch.qos.logback.core.CoreConstants;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: HttpHeaderValueParser.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class HttpHeaderValueParserKt {
    public static final List parseHeaderValue(String str) {
        return parseHeaderValue(str, false);
    }

    public static final List parseHeaderValue(String str, boolean z) {
        if (str == null) {
            return CollectionsKt.emptyList();
        }
        Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: io.ktor.http.HttpHeaderValueParserKt$parseHeaderValue$items$1
            @Override // kotlin.jvm.functions.Function0
            public final ArrayList invoke() {
                return new ArrayList();
            }
        });
        int headerValueItem = 0;
        while (headerValueItem <= StringsKt.getLastIndex(str)) {
            headerValueItem = parseHeaderValueItem(str, headerValueItem, lazy, z);
        }
        return valueOrEmpty(lazy);
    }

    private static final List valueOrEmpty(Lazy lazy) {
        return lazy.isInitialized() ? (List) lazy.getValue() : CollectionsKt.emptyList();
    }

    private static final String subtrim(String str, int i, int i2) {
        String strSubstring = str.substring(i, i2);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return StringsKt.trim(strSubstring).toString();
    }

    private static final int parseHeaderValueItem(String str, int i, Lazy lazy, boolean z) {
        Lazy lazy2 = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: io.ktor.http.HttpHeaderValueParserKt$parseHeaderValueItem$parameters$1
            @Override // kotlin.jvm.functions.Function0
            public final ArrayList invoke() {
                return new ArrayList();
            }
        });
        Integer numValueOf = z ? Integer.valueOf(i) : null;
        int headerValueParameter = i;
        while (headerValueParameter <= StringsKt.getLastIndex(str)) {
            char cCharAt = str.charAt(headerValueParameter);
            if (cCharAt == ',') {
                ((ArrayList) lazy.getValue()).add(new HeaderValue(subtrim(str, i, numValueOf != null ? numValueOf.intValue() : headerValueParameter), valueOrEmpty(lazy2)));
                return headerValueParameter + 1;
            }
            if (cCharAt == ';') {
                if (numValueOf == null) {
                    numValueOf = Integer.valueOf(headerValueParameter);
                }
                headerValueParameter = parseHeaderValueParameter(str, headerValueParameter + 1, lazy2);
            } else {
                headerValueParameter = z ? parseHeaderValueParameter(str, headerValueParameter, lazy2) : headerValueParameter + 1;
            }
        }
        ((ArrayList) lazy.getValue()).add(new HeaderValue(subtrim(str, i, numValueOf != null ? numValueOf.intValue() : headerValueParameter), valueOrEmpty(lazy2)));
        return headerValueParameter;
    }

    private static final void parseHeaderValueParameter$addParam(Lazy lazy, String str, int i, int i2, String str2) {
        String strSubtrim = subtrim(str, i, i2);
        if (strSubtrim.length() == 0) {
            return;
        }
        ((ArrayList) lazy.getValue()).add(new HeaderValueParam(strSubtrim, str2));
    }

    private static final int parseHeaderValueParameter(String str, int i, Lazy lazy) {
        int i2 = i;
        while (i2 <= StringsKt.getLastIndex(str)) {
            char cCharAt = str.charAt(i2);
            if (cCharAt == '=') {
                Pair headerValueParameterValue = parseHeaderValueParameterValue(str, i2 + 1);
                int iIntValue = ((Number) headerValueParameterValue.component1()).intValue();
                parseHeaderValueParameter$addParam(lazy, str, i, i2, (String) headerValueParameterValue.component2());
                return iIntValue;
            }
            if (cCharAt == ';' || cCharAt == ',') {
                parseHeaderValueParameter$addParam(lazy, str, i, i2, "");
                return i2;
            }
            i2++;
        }
        parseHeaderValueParameter$addParam(lazy, str, i, i2, "");
        return i2;
    }

    private static final Pair parseHeaderValueParameterValue(String str, int i) {
        if (str.length() == i) {
            return TuplesKt.to(Integer.valueOf(i), "");
        }
        if (str.charAt(i) == '\"') {
            return parseHeaderValueParameterValueQuoted(str, i + 1);
        }
        int i2 = i;
        while (i2 <= StringsKt.getLastIndex(str)) {
            char cCharAt = str.charAt(i2);
            if (cCharAt == ';' || cCharAt == ',') {
                return TuplesKt.to(Integer.valueOf(i2), subtrim(str, i, i2));
            }
            i2++;
        }
        return TuplesKt.to(Integer.valueOf(i2), subtrim(str, i, i2));
    }

    private static final Pair parseHeaderValueParameterValueQuoted(String str, int i) {
        StringBuilder sb = new StringBuilder();
        while (i <= StringsKt.getLastIndex(str)) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\"' && nextIsSemicolonOrEnd(str, i)) {
                Integer numValueOf = Integer.valueOf(i + 1);
                String string = sb.toString();
                Intrinsics.checkNotNullExpressionValue(string, "builder.toString()");
                return TuplesKt.to(numValueOf, string);
            }
            if (cCharAt == '\\' && i < StringsKt.getLastIndex(str) - 2) {
                sb.append(str.charAt(i + 1));
                i += 2;
            } else {
                sb.append(cCharAt);
                i++;
            }
        }
        Integer numValueOf2 = Integer.valueOf(i);
        String string2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "builder.toString()");
        return TuplesKt.to(numValueOf2, CoreConstants.DOUBLE_QUOTE_CHAR + string2);
    }

    private static final boolean nextIsSemicolonOrEnd(String str, int i) {
        int i2 = i + 1;
        while (i2 < str.length() && str.charAt(i2) == ' ') {
            i2++;
        }
        return i2 == str.length() || str.charAt(i2) == ';';
    }
}
