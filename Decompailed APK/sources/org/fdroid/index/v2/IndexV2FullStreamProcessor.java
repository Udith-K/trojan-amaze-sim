package org.fdroid.index.v2;

import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonDecoder;
import kotlinx.serialization.json.JvmStreamsKt;
import org.fdroid.index.IndexParser;

/* JADX INFO: compiled from: IndexV2FullStreamProcessor.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0011B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J,\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\t0\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/fdroid/index/v2/IndexV2FullStreamProcessor;", "Lorg/fdroid/index/v2/IndexV2StreamProcessor;", "indexStreamReceiver", "Lorg/fdroid/index/v2/IndexV2StreamReceiver;", "json", "Lkotlinx/serialization/json/Json;", "<init>", "(Lorg/fdroid/index/v2/IndexV2StreamReceiver;Lkotlinx/serialization/json/Json;)V", "process", "", "version", "", "inputStream", "Ljava/io/InputStream;", "onAppProcessed", "Lkotlin/Function1;", "", "IndexStreamSerializer", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class IndexV2FullStreamProcessor implements IndexV2StreamProcessor {
    private final IndexV2StreamReceiver indexStreamReceiver;
    private final Json json;

    public IndexV2FullStreamProcessor(IndexV2StreamReceiver indexStreamReceiver, Json json) {
        Intrinsics.checkNotNullParameter(indexStreamReceiver, "indexStreamReceiver");
        Intrinsics.checkNotNullParameter(json, "json");
        this.indexStreamReceiver = indexStreamReceiver;
        this.json = json;
    }

    public /* synthetic */ IndexV2FullStreamProcessor(IndexV2StreamReceiver indexV2StreamReceiver, Json json, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(indexV2StreamReceiver, (i & 2) != 0 ? IndexParser.getJson() : json);
    }

    @Override // org.fdroid.index.v2.IndexV2StreamProcessor
    public void process(long version, InputStream inputStream, Function1 onAppProcessed) throws IllegalStateException, SerializationException {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        Intrinsics.checkNotNullParameter(onAppProcessed, "onAppProcessed");
        JvmStreamsKt.decodeFromStream(this.json, new IndexStreamSerializer(this, version, onAppProcessed), inputStream);
    }

    /* JADX INFO: compiled from: IndexV2FullStreamProcessor.kt */
    private final class IndexStreamSerializer implements KSerializer {
        private int appsProcessed;
        private final SerialDescriptor descriptor;
        private final Function1 onAppProcessed;
        final /* synthetic */ IndexV2FullStreamProcessor this$0;
        private final long version;

        public IndexStreamSerializer(IndexV2FullStreamProcessor indexV2FullStreamProcessor, long j, Function1 onAppProcessed) {
            Intrinsics.checkNotNullParameter(onAppProcessed, "onAppProcessed");
            this.this$0 = indexV2FullStreamProcessor;
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
                deserializeRepo(jsonDecoder, iDecodeElementIndex);
                int iDecodeElementIndex2 = jsonDecoder.decodeElementIndex(getDescriptor());
                if (iDecodeElementIndex2 == elementIndex2) {
                    deserializePackages(jsonDecoder, iDecodeElementIndex2);
                }
            } else if (iDecodeElementIndex == elementIndex2) {
                deserializePackages(jsonDecoder, iDecodeElementIndex);
                int iDecodeElementIndex3 = jsonDecoder.decodeElementIndex(getDescriptor());
                if (iDecodeElementIndex3 == elementIndex) {
                    deserializeRepo(jsonDecoder, iDecodeElementIndex3);
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

        private final void deserializeRepo(JsonDecoder jsonDecoder, int i) {
            if (i != getDescriptor().getElementIndex("repo")) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            this.this$0.indexStreamReceiver.receive((RepoV2) jsonDecoder.decodeSerializableValue(RepoV2.INSTANCE.serializer()), this.version);
        }

        private final void deserializePackages(JsonDecoder jsonDecoder, int i) {
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
            String strDecodeStringElement = compositeDecoder.decodeStringElement(getDescriptor(), i);
            compositeDecoder.decodeElementIndex(getDescriptor());
            this.this$0.indexStreamReceiver.receive(strDecodeStringElement, (PackageV2) CompositeDecoder.DefaultImpls.decodeSerializableElement$default(compositeDecoder, getDescriptor(), i + 1, PackageV2.INSTANCE.serializer(), null, 8, null));
        }

        @Override // kotlinx.serialization.SerializationStrategy
        public void serialize(Encoder encoder, IndexV2 indexV2) {
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            throw new IllegalStateException("Not implemented");
        }
    }
}
