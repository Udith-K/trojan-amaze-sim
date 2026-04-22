package kotlinx.serialization.json;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;

/* JADX INFO: compiled from: JsonElement.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class JsonElement {
    public static final Companion Companion = new Companion(null);

    public /* synthetic */ JsonElement(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: JsonElement.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer serializer() {
            return JsonElementSerializer.INSTANCE;
        }
    }

    private JsonElement() {
    }
}
