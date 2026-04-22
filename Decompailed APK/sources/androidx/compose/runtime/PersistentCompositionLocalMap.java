package androidx.compose.runtime;

import java.util.Map;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableMap;

/* JADX INFO: compiled from: CompositionLocalMap.kt */
/* JADX INFO: loaded from: classes.dex */
public interface PersistentCompositionLocalMap extends Map, KMappedMarker, CompositionLocalMap, CompositionLocalAccessorScope {

    /* JADX INFO: compiled from: CompositionLocalMap.kt */
    public interface Builder extends Map, KMutableMap {
        PersistentCompositionLocalMap build();
    }

    Builder builder();

    PersistentCompositionLocalMap putValue(CompositionLocal compositionLocal, ValueHolder valueHolder);

    /* JADX INFO: renamed from: androidx.compose.runtime.PersistentCompositionLocalMap$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: CompositionLocalMap.kt */
    public abstract /* synthetic */ class CC {
    }
}
