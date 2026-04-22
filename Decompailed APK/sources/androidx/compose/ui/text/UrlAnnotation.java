package androidx.compose.ui.text;

import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UrlAnnotation.kt */
/* JADX INFO: loaded from: classes.dex */
public final class UrlAnnotation {
    private final String url;

    public UrlAnnotation(String str) {
        this.url = str;
    }

    public final String getUrl() {
        return this.url;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof UrlAnnotation) && Intrinsics.areEqual(this.url, ((UrlAnnotation) obj).url);
    }

    public int hashCode() {
        return this.url.hashCode();
    }

    public String toString() {
        return "UrlAnnotation(url=" + this.url + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }
}
