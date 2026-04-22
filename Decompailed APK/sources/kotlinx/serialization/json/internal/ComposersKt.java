package kotlinx.serialization.json.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;

/* JADX INFO: compiled from: Composers.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ComposersKt {
    public static final Composer Composer(JsonWriter sb, Json json) {
        Intrinsics.checkNotNullParameter(sb, "sb");
        Intrinsics.checkNotNullParameter(json, "json");
        return json.getConfiguration().getPrettyPrint() ? new ComposerWithPrettyPrint(sb, json) : new Composer(sb);
    }
}
