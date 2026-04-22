package org.fdroid.index;

import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JvmStreamsKt;
import org.fdroid.index.v1.IndexV1;
import org.fdroid.index.v2.Entry;
import org.fdroid.index.v2.IndexV2;

/* JADX INFO: compiled from: IndexParser.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\t"}, d2 = {"parseV1", "Lorg/fdroid/index/v1/IndexV1;", "Lorg/fdroid/index/IndexParser;", "inputStream", "Ljava/io/InputStream;", "parseV2", "Lorg/fdroid/index/v2/IndexV2;", "parseEntry", "Lorg/fdroid/index/v2/Entry;", "index_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class IndexParserKt {
    public static final IndexV1 parseV1(IndexParser indexParser, InputStream inputStream) {
        Intrinsics.checkNotNullParameter(indexParser, "<this>");
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        Json json = IndexParser.getJson();
        KSerializer kSerializerSerializer = SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(IndexV1.class));
        Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        return (IndexV1) JvmStreamsKt.decodeFromStream(json, kSerializerSerializer, inputStream);
    }

    public static final IndexV2 parseV2(IndexParser indexParser, InputStream inputStream) {
        Intrinsics.checkNotNullParameter(indexParser, "<this>");
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        Json json = IndexParser.getJson();
        KSerializer kSerializerSerializer = SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(IndexV2.class));
        Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        return (IndexV2) JvmStreamsKt.decodeFromStream(json, kSerializerSerializer, inputStream);
    }

    public static final Entry parseEntry(IndexParser indexParser, InputStream inputStream) {
        Intrinsics.checkNotNullParameter(indexParser, "<this>");
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        Json json = IndexParser.getJson();
        KSerializer kSerializerSerializer = SerializersKt.serializer(json.getSerializersModule(), Reflection.typeOf(Entry.class));
        Intrinsics.checkNotNull(kSerializerSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        return (Entry) JvmStreamsKt.decodeFromStream(json, kSerializerSerializer, inputStream);
    }
}
