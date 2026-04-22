package org.fdroid.index;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.index.v1.Localized;
import org.fdroid.index.v2.AntiFeatureV2;
import org.fdroid.index.v2.CategoryV2;
import org.fdroid.index.v2.ReleaseChannelV2;

/* JADX INFO: compiled from: IndexConverter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010&\n\u0000\u001aP\u0010\u0003\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0005*\b\u0012\u0004\u0012\u00020\u00010\u00062\"\u0010\u0007\u001a\u001e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H\u00050\bj\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H\u0005`\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H\u00050\u000bH\u0000\u001a6\u0010\u0003\u001a\u00020\u0004*\b\u0012\u0004\u0012\u00020\u00010\f2\"\u0010\u0007\u001a\u001e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\r0\bj\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\r`\tH\u0000\u001aL\u0010\u0003\u001a\u00020\u0004*\u001e\u0012\u0004\u0012\u00020\u0001\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u000ej\u0002`\u000f0\u000e2\"\u0010\u0007\u001a\u001e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00100\bj\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0010`\tH\u0000\u001a\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00120\u000e\u001aN\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H\u0005\u0018\u00010\u000e\"\u0004\b\u0000\u0010\u0005*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00140\u000e2 \u0010\u0015\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00140\u0016\u0012\u0006\u0012\u0004\u0018\u0001H\u00050\u000bH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"RELEASE_CHANNEL_BETA", "", "DEFAULT_LOCALE", "mapInto", "", "T", "", "map", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "valueGetter", "Lkotlin/Function1;", "", "Lorg/fdroid/index/v2/CategoryV2;", "", "Lorg/fdroid/index/v2/LocalizedTextV2;", "Lorg/fdroid/index/v2/AntiFeatureV2;", "getV1ReleaseChannels", "Lorg/fdroid/index/v2/ReleaseChannelV2;", "mapValuesNotNull", "Lorg/fdroid/index/v1/Localized;", "transform", "", "index_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class IndexConverterKt {
    public static final String DEFAULT_LOCALE = "en-US";
    public static final String RELEASE_CHANNEL_BETA = "Beta";

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> void mapInto(Collection<String> collection, HashMap<String, T> map, Function1 valueGetter) {
        Intrinsics.checkNotNullParameter(collection, "<this>");
        Intrinsics.checkNotNullParameter(map, "map");
        Intrinsics.checkNotNullParameter(valueGetter, "valueGetter");
        for (String str : collection) {
            if (!map.containsKey(str)) {
                map.put(str, valueGetter.invoke(str));
            }
        }
    }

    public static final void mapInto(List<String> list, HashMap<String, CategoryV2> map) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(map, "map");
        mapInto(list, map, new Function1() { // from class: org.fdroid.index.IndexConverterKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return IndexConverterKt.mapInto$lambda$1((String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CategoryV2 mapInto$lambda$1(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return new CategoryV2((Map) null, MapsKt.mapOf(TuplesKt.to(DEFAULT_LOCALE, key)), (Map) null, 5, (DefaultConstructorMarker) null);
    }

    public static final void mapInto(Map<String, ? extends Map<String, String>> map, HashMap<String, AntiFeatureV2> map2) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(map2, "map");
        mapInto(map.keySet(), map2, new Function1() { // from class: org.fdroid.index.IndexConverterKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return IndexConverterKt.mapInto$lambda$2((String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AntiFeatureV2 mapInto$lambda$2(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return new AntiFeatureV2((Map) null, MapsKt.mapOf(TuplesKt.to(DEFAULT_LOCALE, key)), (Map) null, 5, (DefaultConstructorMarker) null);
    }

    public static final Map<String, ReleaseChannelV2> getV1ReleaseChannels() {
        return MapsKt.mapOf(TuplesKt.to("Beta", new ReleaseChannelV2(MapsKt.mapOf(TuplesKt.to(DEFAULT_LOCALE, "Beta")), MapsKt.emptyMap())));
    }

    public static final <T> Map<String, T> mapValuesNotNull(Map<String, Localized> map, Function1 transform) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(transform, "transform");
        LinkedHashMap linkedHashMap = new LinkedHashMap(map.size());
        for (Map.Entry<String, Localized> entry : map.entrySet()) {
            Object objInvoke = transform.invoke(entry);
            if (objInvoke != null) {
                linkedHashMap.put(entry.getKey(), objInvoke);
            }
        }
        if (linkedHashMap.isEmpty()) {
            return null;
        }
        return linkedHashMap;
    }
}
