package io.ktor.http;

import io.ktor.http.Parameters;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Query.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class QueryKt {
    public static /* synthetic */ Parameters parseQueryString$default(String str, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = 1000;
        }
        if ((i3 & 8) != 0) {
            z = true;
        }
        return parseQueryString(str, i, i2, z);
    }

    public static final Parameters parseQueryString(String query, int i, int i2, boolean z) {
        Intrinsics.checkNotNullParameter(query, "query");
        if (i > StringsKt.getLastIndex(query)) {
            return Parameters.Companion.getEmpty();
        }
        Parameters.Companion companion = Parameters.Companion;
        ParametersBuilder parametersBuilderParametersBuilder$default = ParametersKt.ParametersBuilder$default(0, 1, null);
        parse(parametersBuilderParametersBuilder$default, query, i, i2, z);
        return parametersBuilderParametersBuilder$default.build();
    }

    private static final void parse(ParametersBuilder parametersBuilder, String str, int i, int i2, boolean z) {
        int i3;
        int i4;
        int lastIndex = StringsKt.getLastIndex(str);
        int i5 = 0;
        if (i <= lastIndex) {
            int i6 = 0;
            int i7 = -1;
            int i8 = i;
            int i9 = i8;
            while (i6 != i2) {
                char cCharAt = str.charAt(i8);
                if (cCharAt == '&') {
                    appendParam(parametersBuilder, str, i9, i7, i8, z);
                    i6++;
                    i7 = -1;
                    i9 = i8 + 1;
                } else if (cCharAt == '=' && i7 == -1) {
                    i7 = i8;
                }
                if (i8 != lastIndex) {
                    i8++;
                } else {
                    i4 = i9;
                    i3 = i7;
                    i5 = i6;
                }
            }
            return;
        }
        i3 = -1;
        i4 = i;
        if (i5 == i2) {
            return;
        }
        appendParam(parametersBuilder, str, i4, i3, str.length(), z);
    }

    private static final void appendParam(ParametersBuilder parametersBuilder, String str, int i, int i2, int i3, boolean z) {
        String strSubstring;
        String strSubstring2;
        String strSubstring3;
        if (i2 == -1) {
            int iTrimStart = trimStart(i, i3, str);
            int iTrimEnd = trimEnd(iTrimStart, i3, str);
            if (iTrimEnd > iTrimStart) {
                if (z) {
                    strSubstring3 = CodecsKt.decodeURLQueryComponent$default(str, iTrimStart, iTrimEnd, false, null, 12, null);
                } else {
                    strSubstring3 = str.substring(iTrimStart, iTrimEnd);
                    Intrinsics.checkNotNullExpressionValue(strSubstring3, "this as java.lang.String…ing(startIndex, endIndex)");
                }
                parametersBuilder.appendAll(strSubstring3, CollectionsKt.emptyList());
                return;
            }
            return;
        }
        int iTrimStart2 = trimStart(i, i2, str);
        int iTrimEnd2 = trimEnd(iTrimStart2, i2, str);
        if (iTrimEnd2 > iTrimStart2) {
            if (z) {
                strSubstring = CodecsKt.decodeURLQueryComponent$default(str, iTrimStart2, iTrimEnd2, false, null, 12, null);
            } else {
                strSubstring = str.substring(iTrimStart2, iTrimEnd2);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            int iTrimStart3 = trimStart(i2 + 1, i3, str);
            int iTrimEnd3 = trimEnd(iTrimStart3, i3, str);
            if (z) {
                strSubstring2 = CodecsKt.decodeURLQueryComponent$default(str, iTrimStart3, iTrimEnd3, true, null, 8, null);
            } else {
                strSubstring2 = str.substring(iTrimStart3, iTrimEnd3);
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            parametersBuilder.append(strSubstring, strSubstring2);
        }
    }

    private static final int trimEnd(int i, int i2, CharSequence charSequence) {
        while (i2 > i && CharsKt.isWhitespace(charSequence.charAt(i2 - 1))) {
            i2--;
        }
        return i2;
    }

    private static final int trimStart(int i, int i2, CharSequence charSequence) {
        while (i < i2 && CharsKt.isWhitespace(charSequence.charAt(i))) {
            i++;
        }
        return i;
    }
}
