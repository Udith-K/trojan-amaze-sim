package kotlinx.serialization.json.internal;

import ch.qos.logback.core.CoreConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonNames;
import kotlinx.serialization.json.JsonSchemaCacheKt;
import kotlinx.serialization.json.internal.DescriptorSchemaCache;

/* JADX INFO: compiled from: JsonNamesMap.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class JsonNamesMapKt {
    private static final DescriptorSchemaCache.Key JsonAlternativeNamesKey = new DescriptorSchemaCache.Key();

    public static final DescriptorSchemaCache.Key getJsonAlternativeNamesKey() {
        return JsonAlternativeNamesKey;
    }

    private static final void buildAlternativeNamesMap$putOrThrow(Map map, SerialDescriptor serialDescriptor, String str, int i) {
        if (map.containsKey(str)) {
            throw new JsonException("The suggested name '" + str + "' for property " + serialDescriptor.getElementName(i) + " is already one of the names for property " + serialDescriptor.getElementName(((Number) MapsKt.getValue(map, str)).intValue()) + " in " + serialDescriptor);
        }
        map.put(str, Integer.valueOf(i));
    }

    public static final Map buildAlternativeNamesMap(SerialDescriptor serialDescriptor) {
        String[] strArrNames;
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        int elementsCount = serialDescriptor.getElementsCount();
        Map mapCreateMapForCache = null;
        for (int i = 0; i < elementsCount; i++) {
            List elementAnnotations = serialDescriptor.getElementAnnotations(i);
            ArrayList arrayList = new ArrayList();
            for (Object obj : elementAnnotations) {
                if (obj instanceof JsonNames) {
                    arrayList.add(obj);
                }
            }
            JsonNames jsonNames = (JsonNames) CollectionsKt.singleOrNull((List) arrayList);
            if (jsonNames != null && (strArrNames = jsonNames.names()) != null) {
                for (String str : strArrNames) {
                    if (mapCreateMapForCache == null) {
                        mapCreateMapForCache = CreateMapForCacheKt.createMapForCache(serialDescriptor.getElementsCount());
                    }
                    Intrinsics.checkNotNull(mapCreateMapForCache);
                    buildAlternativeNamesMap$putOrThrow(mapCreateMapForCache, serialDescriptor, str, i);
                }
            }
        }
        return mapCreateMapForCache == null ? MapsKt.emptyMap() : mapCreateMapForCache;
    }

    public static final int getJsonNameIndex(SerialDescriptor serialDescriptor, Json json, String name) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(name, "name");
        int elementIndex = serialDescriptor.getElementIndex(name);
        if (elementIndex != -3 || !json.getConfiguration().getUseAlternativeNames()) {
            return elementIndex;
        }
        Integer num = (Integer) ((Map) JsonSchemaCacheKt.getSchemaCache(json).getOrPut(serialDescriptor, JsonAlternativeNamesKey, new JsonNamesMapKt$getJsonNameIndex$alternativeNamesMap$1(serialDescriptor))).get(name);
        if (num != null) {
            return num.intValue();
        }
        return -3;
    }

    public static /* synthetic */ int getJsonNameIndexOrThrow$default(SerialDescriptor serialDescriptor, Json json, String str, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = "";
        }
        return getJsonNameIndexOrThrow(serialDescriptor, json, str, str2);
    }

    public static final int getJsonNameIndexOrThrow(SerialDescriptor serialDescriptor, Json json, String name, String suffix) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        int jsonNameIndex = getJsonNameIndex(serialDescriptor, json, name);
        if (jsonNameIndex != -3) {
            return jsonNameIndex;
        }
        throw new SerializationException(serialDescriptor.getSerialName() + " does not contain element with name '" + name + CoreConstants.SINGLE_QUOTE_CHAR + suffix);
    }
}
