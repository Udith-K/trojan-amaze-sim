package io.ktor.util;

import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Text.kt */
/* JADX INFO: loaded from: classes.dex */
public final class CaseInsensitiveString {
    private final String content;
    private final int hash;

    public CaseInsensitiveString(String content) {
        Intrinsics.checkNotNullParameter(content, "content");
        this.content = content;
        String lowerCase = content.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        this.hash = lowerCase.hashCode();
    }

    public final String getContent() {
        return this.content;
    }

    public boolean equals(Object obj) {
        String str;
        CaseInsensitiveString caseInsensitiveString = obj instanceof CaseInsensitiveString ? (CaseInsensitiveString) obj : null;
        return (caseInsensitiveString == null || (str = caseInsensitiveString.content) == null || !StringsKt.equals(str, this.content, true)) ? false : true;
    }

    public int hashCode() {
        return this.hash;
    }

    public String toString() {
        return this.content;
    }
}
