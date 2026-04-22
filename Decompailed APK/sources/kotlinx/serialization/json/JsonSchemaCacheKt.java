package kotlinx.serialization.json;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.DescriptorSchemaCache;

/* JADX INFO: compiled from: JsonSchemaCache.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class JsonSchemaCacheKt {
    public static final DescriptorSchemaCache getSchemaCache(Json json) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        return json.get_schemaCache$kotlinx_serialization_json();
    }
}
