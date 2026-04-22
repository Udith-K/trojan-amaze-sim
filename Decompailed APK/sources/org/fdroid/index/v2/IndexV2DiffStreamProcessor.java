package org.fdroid.index.v2;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonDecoder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JvmStreamsKt;
import org.fdroid.index.IndexParser;

/* JADX INFO: compiled from: IndexV2DiffStreamProcessor.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0011B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J,\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\t0\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/fdroid/index/v2/IndexV2DiffStreamProcessor;", "Lorg/fdroid/index/v2/IndexV2StreamProcessor;", "indexStreamReceiver", "Lorg/fdroid/index/v2/IndexV2DiffStreamReceiver;", "json", "Lkotlinx/serialization/json/Json;", "<init>", "(Lorg/fdroid/index/v2/IndexV2DiffStreamReceiver;Lkotlinx/serialization/json/Json;)V", "process", "", "version", "", "inputStream", "Ljava/io/InputStream;", "onAppProcessed", "Lkotlin/Function1;", "", "IndexStreamSerializer", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class IndexV2DiffStreamProcessor implements IndexV2StreamProcessor {
    private final IndexV2DiffStreamReceiver indexStreamReceiver;
    private final Json json;

    public IndexV2DiffStreamProcessor(IndexV2DiffStreamReceiver indexStreamReceiver, Json json) {
        Intrinsics.checkNotNullParameter(indexStreamReceiver, "indexStreamReceiver");
        Intrinsics.checkNotNullParameter(json, "json");
        this.indexStreamReceiver = indexStreamReceiver;
        this.json = json;
    }

    public /* synthetic */ IndexV2DiffStreamProcessor(IndexV2DiffStreamReceiver indexV2DiffStreamReceiver, Json json, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(indexV2DiffStreamReceiver, (i & 2) != 0 ? IndexParser.getJson() : json);
    }

    @Override // org.fdroid.index.v2.IndexV2StreamProcessor
    public void process(long version, InputStream inputStream, Function1 onAppProcessed) {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        Intrinsics.checkNotNullParameter(onAppProcessed, "onAppProcessed");
        JvmStreamsKt.decodeFromStream(this.json, new IndexStreamSerializer(this, version, onAppProcessed), inputStream);
    }

    /* JADX INFO: compiled from: IndexV2DiffStreamProcessor.kt */
    private final class IndexStreamSerializer implements KSerializer {
        private int appsProcessed;
        private final SerialDescriptor descriptor;
        private final Function1 onAppProcessed;
        final /* synthetic */ IndexV2DiffStreamProcessor this$0;
        private final long version;

        public IndexStreamSerializer(IndexV2DiffStreamProcessor indexV2DiffStreamProcessor, long j, Function1 onAppProcessed) {
            Intrinsics.checkNotNullParameter(onAppProcessed, "onAppProcessed");
            this.this$0 = indexV2DiffStreamProcessor;
            this.version = j;
            this.onAppProcessed = onAppProcessed;
            this.descriptor = IndexV2.INSTANCE.serializer().getDescriptor();
        }

        @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
        public SerialDescriptor getDescriptor() {
            return this.descriptor;
        }

        @Override // kotlinx.serialization.DeserializationStrategy
        public IndexV2 deserialize(Decoder decoder) {
            Intrinsics.checkNotNullParameter(decoder, "decoder");
            if ((decoder instanceof JsonDecoder ? (JsonDecoder) decoder : null) == null) {
                throw new IllegalStateException("Can be deserialized only by JSON");
            }
            decoder.beginStructure(getDescriptor());
            int elementIndex = getDescriptor().getElementIndex("repo");
            int elementIndex2 = getDescriptor().getElementIndex("packages");
            JsonDecoder jsonDecoder = (JsonDecoder) decoder;
            int iDecodeElementIndex = jsonDecoder.decodeElementIndex(getDescriptor());
            if (iDecodeElementIndex == elementIndex) {
                diffRepo(this.version, jsonDecoder, iDecodeElementIndex);
                int iDecodeElementIndex2 = jsonDecoder.decodeElementIndex(getDescriptor());
                if (iDecodeElementIndex2 == elementIndex2) {
                    diffPackages(jsonDecoder, iDecodeElementIndex2);
                }
            } else if (iDecodeElementIndex == elementIndex2) {
                diffPackages(jsonDecoder, iDecodeElementIndex);
                int iDecodeElementIndex3 = jsonDecoder.decodeElementIndex(getDescriptor());
                if (iDecodeElementIndex3 == elementIndex) {
                    diffRepo(this.version, jsonDecoder, iDecodeElementIndex3);
                }
            } else {
                throw new IllegalStateException(("Unexpected startIndex: " + iDecodeElementIndex).toString());
            }
            int iDecodeElementIndex4 = 0;
            while (iDecodeElementIndex4 != -1) {
                iDecodeElementIndex4 = jsonDecoder.decodeElementIndex(getDescriptor());
            }
            jsonDecoder.endStructure(getDescriptor());
            this.this$0.indexStreamReceiver.onStreamEnded();
            return null;
        }

        private final void diffRepo(long j, JsonDecoder jsonDecoder, int i) {
            if (i != getDescriptor().getElementIndex("repo")) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            this.this$0.indexStreamReceiver.receiveRepoDiff(j, JsonElementKt.getJsonObject(jsonDecoder.decodeJsonElement()));
        }

        private final void diffPackages(JsonDecoder jsonDecoder, int i) {
            if (i != getDescriptor().getElementIndex("packages")) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            SerialDescriptor elementDescriptor = getDescriptor().getElementDescriptor(i);
            CompositeDecoder compositeDecoderBeginStructure = jsonDecoder.beginStructure(elementDescriptor);
            while (true) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(getDescriptor());
                if (iDecodeElementIndex != -1) {
                    readMapEntry(compositeDecoderBeginStructure, iDecodeElementIndex);
                    int i2 = this.appsProcessed + 1;
                    this.appsProcessed = i2;
                    this.onAppProcessed.invoke(Integer.valueOf(i2));
                } else {
                    compositeDecoderBeginStructure.endStructure(elementDescriptor);
                    return;
                }
            }
        }

        private final void readMapEntry(CompositeDecoder compositeDecoder, int i) {
            JsonObject jsonObject;
            String strDecodeStringElement = compositeDecoder.decodeStringElement(getDescriptor(), i);
            compositeDecoder.decodeElementIndex(getDescriptor());
            JsonElement jsonElement = (JsonElement) CompositeDecoder.DefaultImpls.decodeSerializableElement$default(compositeDecoder, getDescriptor(), i + 1, JsonElement.Companion.serializer(), null, 8, null);
            LinkedHashMap linkedHashMap = null;
            if (jsonElement instanceof JsonNull) {
                this.this$0.indexStreamReceiver.receivePackageMetadataDiff(strDecodeStringElement, null);
                return;
            }
            JsonElement jsonElement2 = (JsonElement) JsonElementKt.getJsonObject(jsonElement).get((Object) "metadata");
            if (jsonElement2 instanceof JsonNull) {
                this.this$0.indexStreamReceiver.receivePackageMetadataDiff(strDecodeStringElement, null);
            } else if (jsonElement2 instanceof JsonObject) {
                this.this$0.indexStreamReceiver.receivePackageMetadataDiff(strDecodeStringElement, (JsonObject) jsonElement2);
            }
            if (JsonElementKt.getJsonObject(jsonElement).get((Object) "versions") instanceof JsonNull) {
                this.this$0.indexStreamReceiver.receiveVersionsDiff(strDecodeStringElement, null);
                return;
            }
            JsonElement jsonElement3 = (JsonElement) JsonElementKt.getJsonObject(jsonElement).get((Object) "versions");
            if (jsonElement3 != null && (jsonObject = JsonElementKt.getJsonObject(jsonElement3)) != null) {
                LinkedHashMap linkedHashMap2 = new LinkedHashMap(MapsKt.mapCapacity(jsonObject.size()));
                for (Map.Entry entry : jsonObject.entrySet()) {
                    linkedHashMap2.put(entry.getKey(), entry.getValue() instanceof JsonNull ? null : JsonElementKt.getJsonObject((JsonElement) entry.getValue()));
                }
                linkedHashMap = linkedHashMap2;
            }
            if (linkedHashMap != null) {
                this.this$0.indexStreamReceiver.receiveVersionsDiff(strDecodeStringElement, linkedHashMap);
            }
        }

        @Override // kotlinx.serialization.SerializationStrategy
        public void serialize(Encoder encoder, IndexV2 indexV2) {
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            throw new IllegalStateException("Not implemented");
        }
    }
}
