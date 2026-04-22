package mu;

import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/* JADX INFO: compiled from: KMarkerFactory.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class KMarkerFactory {
    public static final KMarkerFactory INSTANCE = new KMarkerFactory();

    private KMarkerFactory() {
    }

    public final Marker getMarker(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        Marker marker = MarkerFactory.getMarker(name);
        Intrinsics.checkNotNullExpressionValue(marker, "MarkerFactory.getMarker(name)");
        return marker;
    }
}
