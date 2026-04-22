package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: predefinedEnhancementInfo.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class TypeEnhancementInfo {
    private final Map map;

    public TypeEnhancementInfo(Map map) {
        Intrinsics.checkNotNullParameter(map, "map");
        this.map = map;
    }

    public final Map getMap() {
        return this.map;
    }

    public final TypeEnhancementInfo copyForWarnings() {
        Map map = this.map;
        LinkedHashMap linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(map.size()));
        for (Map.Entry entry : map.entrySet()) {
            linkedHashMap.put(entry.getKey(), JavaTypeQualifiers.copy$default((JavaTypeQualifiers) entry.getValue(), null, null, false, true, 7, null));
        }
        return new TypeEnhancementInfo(linkedHashMap);
    }
}
