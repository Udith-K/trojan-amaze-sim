package org.fdroid.index;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import org.fdroid.index.v1.IndexV1;
import org.fdroid.index.v2.Entry;
import org.fdroid.index.v2.IndexV2;

/* JADX INFO: compiled from: IndexParser.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\fH\u0007R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lorg/fdroid/index/IndexParser;", "", "<init>", "()V", "jsonInstance", "Lkotlinx/serialization/json/Json;", "json", "getJson", "()Lkotlinx/serialization/json/Json;", "parseV1", "Lorg/fdroid/index/v1/IndexV1;", "str", "", "parseV2", "Lorg/fdroid/index/v2/IndexV2;", "parseEntry", "Lorg/fdroid/index/v2/Entry;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class IndexParser {
    public static final IndexParser INSTANCE = new IndexParser();
    private static volatile Json jsonInstance;

    private IndexParser() {
    }

    public static final Json getJson() {
        Json jsonJson$default;
        Json json = jsonInstance;
        if (json != null) {
            return json;
        }
        synchronized (INSTANCE) {
            jsonJson$default = JsonKt.Json$default(null, new Function1() { // from class: org.fdroid.index.IndexParser$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return IndexParser._get_json_$lambda$1$lambda$0((JsonBuilder) obj);
                }
            }, 1, null);
        }
        return jsonJson$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit _get_json_$lambda$1$lambda$0(JsonBuilder Json) {
        Intrinsics.checkNotNullParameter(Json, "$this$Json");
        Json.setIgnoreUnknownKeys(true);
        return Unit.INSTANCE;
    }

    public static final IndexV1 parseV1(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        Json json = getJson();
        KSerializer kSerializerSerializer = SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(IndexV1.class));
        Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        return (IndexV1) json.decodeFromString(kSerializerSerializer, str);
    }

    public static final IndexV2 parseV2(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        Json json = getJson();
        KSerializer kSerializerSerializer = SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(IndexV2.class));
        Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        return (IndexV2) json.decodeFromString(kSerializerSerializer, str);
    }

    public static final Entry parseEntry(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        Json json = getJson();
        KSerializer kSerializerSerializer = SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(Entry.class));
        Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        return (Entry) json.decodeFromString(kSerializerSerializer, str);
    }
}
