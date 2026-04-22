package io.ktor.http;

import ch.qos.logback.core.CoreConstants;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: HttpHeaderValueParser.kt */
/* JADX INFO: loaded from: classes.dex */
public final class HeaderValueParam {
    private final boolean escapeValue;
    private final String name;
    private final String value;

    public String toString() {
        return "HeaderValueParam(name=" + this.name + ", value=" + this.value + ", escapeValue=" + this.escapeValue + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public HeaderValueParam(String name, String value, boolean z) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        this.name = name;
        this.value = value;
        this.escapeValue = z;
    }

    public final String getName() {
        return this.name;
    }

    public final String getValue() {
        return this.value;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public HeaderValueParam(String name, String value) {
        this(name, value, false);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
    }

    public boolean equals(Object obj) {
        if (obj instanceof HeaderValueParam) {
            HeaderValueParam headerValueParam = (HeaderValueParam) obj;
            if (StringsKt.equals(headerValueParam.name, this.name, true) && StringsKt.equals(headerValueParam.value, this.value, true)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String str = this.name;
        Locale locale = Locale.ROOT;
        String lowerCase = str.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        int iHashCode = lowerCase.hashCode();
        String lowerCase2 = this.value.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        return iHashCode + (iHashCode * 31) + lowerCase2.hashCode();
    }
}
