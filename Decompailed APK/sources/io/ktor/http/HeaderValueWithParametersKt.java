package io.ktor.http;

import ch.qos.logback.core.CoreConstants;
import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: HeaderValueWithParameters.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class HeaderValueWithParametersKt {
    private static final Set HeaderFieldValueSeparators = SetsKt.setOf((Object[]) new Character[]{Character.valueOf(CoreConstants.LEFT_PARENTHESIS_CHAR), Character.valueOf(CoreConstants.RIGHT_PARENTHESIS_CHAR), '<', '>', '@', Character.valueOf(CoreConstants.COMMA_CHAR), ';', Character.valueOf(CoreConstants.COLON_CHAR), Character.valueOf(CoreConstants.ESCAPE_CHAR), Character.valueOf(CoreConstants.DOUBLE_QUOTE_CHAR), '/', '[', ']', '?', '=', Character.valueOf(CoreConstants.CURLY_LEFT), Character.valueOf(CoreConstants.CURLY_RIGHT), ' ', '\t', '\n', '\r'});

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean needQuotes(String str) {
        if (str.length() == 0) {
            return true;
        }
        if (isQuoted(str)) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (HeaderFieldValueSeparators.contains(Character.valueOf(str.charAt(i)))) {
                return true;
            }
        }
        return false;
    }

    private static final boolean isQuoted(String str) {
        if (str.length() < 2 || StringsKt.first(str) != '\"' || StringsKt.last(str) != '\"') {
            return false;
        }
        int i = 1;
        do {
            int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str, CoreConstants.DOUBLE_QUOTE_CHAR, i, false, 4, (Object) null);
            if (iIndexOf$default == StringsKt.getLastIndex(str)) {
                break;
            }
            int i2 = 0;
            for (int i3 = iIndexOf$default - 1; str.charAt(i3) == '\\'; i3--) {
                i2++;
            }
            if (i2 % 2 == 0) {
                return false;
            }
            i = iIndexOf$default + 1;
        } while (i < str.length());
        return true;
    }

    public static final String quote(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        StringBuilder sb = new StringBuilder();
        quoteTo(str, sb);
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    private static final void quoteTo(String str, StringBuilder sb) {
        sb.append("\"");
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\\') {
                sb.append("\\\\");
            } else if (cCharAt == '\n') {
                sb.append("\\n");
            } else if (cCharAt == '\r') {
                sb.append("\\r");
            } else if (cCharAt == '\t') {
                sb.append("\\t");
            } else if (cCharAt == '\"') {
                sb.append("\\\"");
            } else {
                sb.append(cCharAt);
            }
        }
        sb.append("\"");
    }
}
