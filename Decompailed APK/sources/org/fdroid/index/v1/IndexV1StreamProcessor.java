package org.fdroid.index.v1;

import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonDecoder;
import kotlinx.serialization.json.JvmStreamsKt;
import org.fdroid.index.IndexConverterKt;
import org.fdroid.index.IndexParser;
import org.fdroid.index.v1.IndexV1StreamProcessor;
import org.fdroid.index.v2.AntiFeatureV2;
import org.fdroid.index.v2.CategoryV2;
import org.fdroid.index.v2.PackageVersionV2;

/* JADX INFO: compiled from: IndexV1StreamProcessor.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0010B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lorg/fdroid/index/v1/IndexV1StreamProcessor;", "", "indexStreamReceiver", "Lorg/fdroid/index/v1/IndexV1StreamReceiver;", "lastTimestamp", "", "locale", "", "json", "Lkotlinx/serialization/json/Json;", "<init>", "(Lorg/fdroid/index/v1/IndexV1StreamReceiver;JLjava/lang/String;Lkotlinx/serialization/json/Json;)V", "process", "", "inputStream", "Ljava/io/InputStream;", "IndexStreamSerializer", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class IndexV1StreamProcessor {
    private final IndexV1StreamReceiver indexStreamReceiver;
    private final Json json;
    private final long lastTimestamp;
    private final String locale;

    public IndexV1StreamProcessor(IndexV1StreamReceiver indexStreamReceiver, long j, String locale, Json json) {
        Intrinsics.checkNotNullParameter(indexStreamReceiver, "indexStreamReceiver");
        Intrinsics.checkNotNullParameter(locale, "locale");
        Intrinsics.checkNotNullParameter(json, "json");
        this.indexStreamReceiver = indexStreamReceiver;
        this.lastTimestamp = j;
        this.locale = locale;
        this.json = json;
    }

    public /* synthetic */ IndexV1StreamProcessor(IndexV1StreamReceiver indexV1StreamReceiver, long j, String str, Json json, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(indexV1StreamReceiver, j, (i & 4) != 0 ? IndexConverterKt.DEFAULT_LOCALE : str, (i & 8) != 0 ? IndexParser.getJson() : json);
    }

    public final void process(InputStream inputStream) throws SerializationException, OldIndexException {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        JvmStreamsKt.decodeFromStream(this.json, new IndexStreamSerializer(), inputStream);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: IndexV1StreamProcessor.kt */
    final class IndexStreamSerializer implements KSerializer {
        private final SerialDescriptor descriptor = IndexV1.INSTANCE.serializer().getDescriptor();

        public IndexStreamSerializer() {
        }

        @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
        public SerialDescriptor getDescriptor() {
            return this.descriptor;
        }

        @Override // kotlinx.serialization.DeserializationStrategy
        public IndexV1 deserialize(Decoder decoder) throws OldIndexException {
            Intrinsics.checkNotNullParameter(decoder, "decoder");
            if ((decoder instanceof JsonDecoder ? (JsonDecoder) decoder : null) == null) {
                throw new IllegalStateException("Can be deserialized only by JSON");
            }
            decoder.beginStructure(getDescriptor());
            JsonDecoder jsonDecoder = (JsonDecoder) decoder;
            deserializeRepo(jsonDecoder, jsonDecoder.decodeElementIndex(getDescriptor()));
            int iDecodeElementIndex = jsonDecoder.decodeElementIndex(getDescriptor());
            if (iDecodeElementIndex == -1) {
                updateRepoData(MapsKt.emptyMap());
                jsonDecoder.endStructure(getDescriptor());
                return null;
            }
            deserializeRequests(jsonDecoder, iDecodeElementIndex);
            int iDecodeElementIndex2 = jsonDecoder.decodeElementIndex(getDescriptor());
            if (iDecodeElementIndex2 == -1) {
                updateRepoData(MapsKt.emptyMap());
                jsonDecoder.endStructure(getDescriptor());
                return null;
            }
            Map mapDeserializeApps = deserializeApps(jsonDecoder, iDecodeElementIndex2);
            int iDecodeElementIndex3 = jsonDecoder.decodeElementIndex(getDescriptor());
            if (iDecodeElementIndex3 == -1) {
                updateRepoData(mapDeserializeApps);
                jsonDecoder.endStructure(getDescriptor());
                return null;
            }
            deserializePackages(jsonDecoder, iDecodeElementIndex3, mapDeserializeApps);
            jsonDecoder.endStructure(getDescriptor());
            updateRepoData(mapDeserializeApps);
            return null;
        }

        private final void deserializeRepo(JsonDecoder jsonDecoder, int i) throws OldIndexException {
            if (i != getDescriptor().getElementIndex("repo")) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            RepoV1 repoV1 = (RepoV1) jsonDecoder.decodeSerializableValue(RepoV1.INSTANCE.serializer());
            if (IndexV1StreamProcessor.this.lastTimestamp < repoV1.getTimestamp()) {
                IndexV1StreamProcessor.this.indexStreamReceiver.receive(repoV1.toRepoV2(IndexConverterKt.DEFAULT_LOCALE, MapsKt.emptyMap(), MapsKt.emptyMap(), MapsKt.emptyMap()), repoV1.getVersion());
                return;
            }
            throw new OldIndexException(IndexV1StreamProcessor.this.lastTimestamp == repoV1.getTimestamp(), "Old repo " + repoV1.getAddress() + " " + repoV1.getTimestamp());
        }

        private final void deserializeRequests(JsonDecoder jsonDecoder, int i) {
            if (i != getDescriptor().getElementIndex("requests")) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            jsonDecoder.decodeSerializableValue(Requests.INSTANCE.serializer());
        }

        private final Map deserializeApps(JsonDecoder jsonDecoder, int i) {
            if (i != getDescriptor().getElementIndex("apps")) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            HashMap map = new HashMap();
            SerialDescriptor elementDescriptor = getDescriptor().getElementDescriptor(i);
            CompositeDecoder compositeDecoderBeginStructure = jsonDecoder.beginStructure(elementDescriptor);
            while (true) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(getDescriptor());
                if (iDecodeElementIndex != -1) {
                    AppV1 appV1 = (AppV1) CompositeDecoder.DefaultImpls.decodeSerializableElement$default(jsonDecoder, getDescriptor(), iDecodeElementIndex, AppV1.INSTANCE.serializer(), null, 8, null);
                    Long longOrNull = null;
                    IndexV1StreamProcessor.this.indexStreamReceiver.receive(appV1.getPackageName(), appV1.toMetadataV2(null, IndexV1StreamProcessor.this.locale));
                    String packageName = appV1.getPackageName();
                    List<String> antiFeatures = appV1.getAntiFeatures();
                    LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(antiFeatures, 10)), 16));
                    for (Object obj : antiFeatures) {
                        linkedHashMap.put(obj, MapsKt.emptyMap());
                    }
                    Map<String, Localized> localized = appV1.getLocalized();
                    Map mapMapValuesNotNull = localized != null ? IndexConverterKt.mapValuesNotNull(localized, new Function1() { // from class: org.fdroid.index.v1.IndexV1StreamProcessor$IndexStreamSerializer$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return IndexV1StreamProcessor.IndexStreamSerializer.deserializeApps$lambda$1((Map.Entry) obj2);
                        }
                    }) : null;
                    String suggestedVersionCode = appV1.getSuggestedVersionCode();
                    if (suggestedVersionCode != null) {
                        longOrNull = StringsKt.toLongOrNull(suggestedVersionCode);
                    }
                    map.put(packageName, new AppData(linkedHashMap, mapMapValuesNotNull, longOrNull, appV1.getCategories()));
                } else {
                    compositeDecoderBeginStructure.endStructure(elementDescriptor);
                    return map;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String deserializeApps$lambda$1(Map.Entry it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return ((Localized) it.getValue()).getWhatsNew();
        }

        private final void deserializePackages(JsonDecoder jsonDecoder, int i, Map map) {
            if (i != getDescriptor().getElementIndex("packages")) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            SerialDescriptor elementDescriptor = getDescriptor().getElementDescriptor(i);
            CompositeDecoder compositeDecoderBeginStructure = jsonDecoder.beginStructure(elementDescriptor);
            while (true) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(getDescriptor());
                if (iDecodeElementIndex != -1) {
                    Intrinsics.checkNotNull(compositeDecoderBeginStructure, "null cannot be cast to non-null type kotlinx.serialization.json.JsonDecoder");
                    readPackageMapEntry((JsonDecoder) compositeDecoderBeginStructure, iDecodeElementIndex, map);
                } else {
                    compositeDecoderBeginStructure.endStructure(elementDescriptor);
                    return;
                }
            }
        }

        private final void readPackageMapEntry(JsonDecoder jsonDecoder, int i, Map map) {
            Map mapEmptyMap;
            AppData appData;
            Long suggestedVersionCode;
            String strDecodeStringElement = jsonDecoder.decodeStringElement(getDescriptor(), i);
            jsonDecoder.decodeElementIndex(getDescriptor());
            HashMap map2 = new HashMap();
            SerialDescriptor descriptor = BuiltinSerializersKt.ListSerializer(PackageV1.INSTANCE.serializer()).getDescriptor();
            CompositeDecoder compositeDecoderBeginStructure = jsonDecoder.beginStructure(descriptor);
            boolean z = true;
            while (compositeDecoderBeginStructure.decodeElementIndex(getDescriptor()) != -1) {
                PackageV1 packageV1 = (PackageV1) CompositeDecoder.DefaultImpls.decodeSerializableElement$default(jsonDecoder, getDescriptor(), i + 1, PackageV1.INSTANCE.serializer(), null, 8, null);
                Long versionCode = packageV1.getVersionCode();
                long jLongValue = 0;
                long jLongValue2 = versionCode != null ? versionCode.longValue() : 0L;
                AppData appData2 = (AppData) map.get(strDecodeStringElement);
                if (appData2 != null && (suggestedVersionCode = appData2.getSuggestedVersionCode()) != null) {
                    jLongValue = suggestedVersionCode.longValue();
                }
                List<String> listListOf = jLongValue2 > jLongValue ? CollectionsKt.listOf("Beta") : CollectionsKt.emptyList();
                AppData appData3 = (AppData) map.get(strDecodeStringElement);
                if (appData3 == null || (mapEmptyMap = appData3.getAntiFeatures()) == null) {
                    mapEmptyMap = MapsKt.emptyMap();
                }
                Map whatsNew = null;
                if (jLongValue == jLongValue2 && (appData = (AppData) map.get(strDecodeStringElement)) != null) {
                    whatsNew = appData.getWhatsNew();
                }
                PackageVersionV2 packageVersionV2 = packageV1.toPackageVersionV2(listListOf, mapEmptyMap, whatsNew);
                if (z) {
                    IndexV1StreamProcessor.this.indexStreamReceiver.updateAppMetadata(strDecodeStringElement, packageV1.getSigner());
                }
                map2.put(packageVersionV2.getFile().getSha256(), packageVersionV2);
                z = false;
            }
            IndexV1StreamProcessor.this.indexStreamReceiver.receive(strDecodeStringElement, map2);
            compositeDecoderBeginStructure.endStructure(descriptor);
        }

        private final void updateRepoData(Map map) {
            HashMap map2 = new HashMap();
            HashMap map3 = new HashMap();
            for (AppData appData : map.values()) {
                IndexConverterKt.mapInto((Map<String, ? extends Map<String, String>>) appData.getAntiFeatures(), (HashMap<String, AntiFeatureV2>) map2);
                IndexConverterKt.mapInto((List<String>) appData.getCategories(), (HashMap<String, CategoryV2>) map3);
            }
            IndexV1StreamProcessor.this.indexStreamReceiver.updateRepo(map2, map3, IndexConverterKt.getV1ReleaseChannels());
        }

        @Override // kotlinx.serialization.SerializationStrategy
        public void serialize(Encoder encoder, IndexV1 indexV1) {
            Intrinsics.checkNotNullParameter(encoder, "encoder");
            throw new IllegalStateException("Not implemented");
        }
    }
}
