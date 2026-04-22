package kotlinx.serialization.json;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;

/* JADX INFO: compiled from: JsonElement.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class JsonPrimitive extends JsonElement {
    public static final Companion Companion = new Companion(null);

    public /* synthetic */ JsonPrimitive(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract String getContent();

    /* JADX INFO: compiled from: JsonElement.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer serializer() {
            return JsonPrimitiveSerializer.INSTANCE;
        }
    }

    private JsonPrimitive() {
        super(null);
    }

    public String toString() {
        return getContent();
    }
}
