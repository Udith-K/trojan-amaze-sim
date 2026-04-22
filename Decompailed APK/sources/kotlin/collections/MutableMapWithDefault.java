package kotlin.collections;

import java.util.Map;
import kotlin.jvm.internal.markers.KMutableMap;

/* JADX INFO: compiled from: MapWithDefault.kt */
/* JADX INFO: loaded from: classes.dex */
interface MutableMapWithDefault extends Map, MapWithDefault, KMutableMap {
    Map getMap();
}
