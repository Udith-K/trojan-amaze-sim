package kotlinx.serialization.json;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.json.internal.StringOpsKt;

/* JADX INFO: compiled from: JsonElement.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class JsonLiteral extends JsonPrimitive {
    private final String content;
    private final boolean isString;

    public boolean isString() {
        return this.isString;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonLiteral(Object body, boolean z) {
        super(null);
        Intrinsics.checkNotNullParameter(body, "body");
        this.isString = z;
        this.content = body.toString();
    }

    @Override // kotlinx.serialization.json.JsonPrimitive
    public String getContent() {
        return this.content;
    }

    @Override // kotlinx.serialization.json.JsonPrimitive
    public String toString() {
        if (!isString()) {
            return getContent();
        }
        StringBuilder sb = new StringBuilder();
        StringOpsKt.printQuoted(sb, getContent());
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(JsonLiteral.class), Reflection.getOrCreateKotlinClass(obj.getClass()))) {
            return false;
        }
        JsonLiteral jsonLiteral = (JsonLiteral) obj;
        return isString() == jsonLiteral.isString() && Intrinsics.areEqual(getContent(), jsonLiteral.getContent());
    }

    public int hashCode() {
        return (ChangeSize$$ExternalSyntheticBackport0.m(isString()) * 31) + getContent().hashCode();
    }
}
