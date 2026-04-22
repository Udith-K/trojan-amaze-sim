package kotlinx.serialization.json.internal;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: StringOps.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class StringOpsKt {
    private static final byte[] ESCAPE_MARKERS;
    private static final String[] ESCAPE_STRINGS;

    private static final char toHexChar(int i) {
        int i2 = i & 15;
        return (char) (i2 < 10 ? i2 + 48 : i2 + 87);
    }

    static {
        String[] strArr = new String[93];
        for (int i = 0; i < 32; i++) {
            strArr[i] = "\\u" + toHexChar(i >> 12) + toHexChar(i >> 8) + toHexChar(i >> 4) + toHexChar(i);
        }
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        ESCAPE_STRINGS = strArr;
        byte[] bArr = new byte[93];
        for (int i2 = 0; i2 < 32; i2++) {
            bArr[i2] = 1;
        }
        bArr[34] = 34;
        bArr[92] = 92;
        bArr[9] = 116;
        bArr[8] = 98;
        bArr[10] = 110;
        bArr[13] = 114;
        bArr[12] = 102;
        ESCAPE_MARKERS = bArr;
    }

    public static final String[] getESCAPE_STRINGS() {
        return ESCAPE_STRINGS;
    }

    public static final byte[] getESCAPE_MARKERS() {
        return ESCAPE_MARKERS;
    }

    public static final void printQuoted(StringBuilder sb, String value) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(value, "value");
        sb.append(CoreConstants.DOUBLE_QUOTE_CHAR);
        int length = value.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = value.charAt(i2);
            String[] strArr = ESCAPE_STRINGS;
            if (cCharAt < strArr.length && strArr[cCharAt] != null) {
                sb.append((CharSequence) value, i, i2);
                sb.append(strArr[cCharAt]);
                i = i2 + 1;
            }
        }
        if (i != 0) {
            sb.append((CharSequence) value, i, value.length());
        } else {
            sb.append(value);
        }
        sb.append(CoreConstants.DOUBLE_QUOTE_CHAR);
    }

    public static final Boolean toBooleanStrictOrNull(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (StringsKt.equals(str, "true", true)) {
            return Boolean.TRUE;
        }
        if (StringsKt.equals(str, "false", true)) {
            return Boolean.FALSE;
        }
        return null;
    }
}
