package kotlinx.serialization.json;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.functions.Function0;
import kotlinx.serialization.KSerializer;

/* JADX INFO: compiled from: JsonElement.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class JsonNull extends JsonPrimitive {
    public static final JsonNull INSTANCE = new JsonNull();
    private static final String content = "null";
    private static final /* synthetic */ Lazy $cachedSerializer$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: kotlinx.serialization.json.JsonNull$$cachedSerializer$delegate$1
        @Override // kotlin.jvm.functions.Function0
        public final KSerializer invoke() {
            return JsonNullSerializer.INSTANCE;
        }
    });

    private JsonNull() {
        super(null);
    }

    @Override // kotlinx.serialization.json.JsonPrimitive
    public String getContent() {
        return content;
    }
}
