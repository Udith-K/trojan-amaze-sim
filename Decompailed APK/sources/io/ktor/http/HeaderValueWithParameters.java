package io.ktor.http;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: HeaderValueWithParameters.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class HeaderValueWithParameters {
    public static final Companion Companion = new Companion(null);
    private final String content;
    private final List parameters;

    public HeaderValueWithParameters(String content, List parameters) {
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        this.content = content;
        this.parameters = parameters;
    }

    protected final String getContent() {
        return this.content;
    }

    public final List getParameters() {
        return this.parameters;
    }

    public final String parameter(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        int lastIndex = CollectionsKt.getLastIndex(this.parameters);
        if (lastIndex < 0) {
            return null;
        }
        int i = 0;
        while (true) {
            HeaderValueParam headerValueParam = (HeaderValueParam) this.parameters.get(i);
            if (StringsKt.equals(headerValueParam.getName(), name, true)) {
                return headerValueParam.getValue();
            }
            if (i == lastIndex) {
                return null;
            }
            i++;
        }
    }

    public String toString() {
        if (this.parameters.isEmpty()) {
            return this.content;
        }
        int length = this.content.length();
        int i = 0;
        int length2 = 0;
        for (HeaderValueParam headerValueParam : this.parameters) {
            length2 += headerValueParam.getName().length() + headerValueParam.getValue().length() + 3;
        }
        StringBuilder sb = new StringBuilder(length + length2);
        sb.append(this.content);
        int lastIndex = CollectionsKt.getLastIndex(this.parameters);
        if (lastIndex >= 0) {
            while (true) {
                HeaderValueParam headerValueParam2 = (HeaderValueParam) this.parameters.get(i);
                sb.append("; ");
                sb.append(headerValueParam2.getName());
                sb.append("=");
                String value = headerValueParam2.getValue();
                if (HeaderValueWithParametersKt.needQuotes(value)) {
                    sb.append(HeaderValueWithParametersKt.quote(value));
                } else {
                    sb.append(value);
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "{\n            val size =…   }.toString()\n        }");
        return string;
    }

    /* JADX INFO: compiled from: HeaderValueWithParameters.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
