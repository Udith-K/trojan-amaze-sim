package org.slf4j.helpers;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.slf4j.IMarkerFactory;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes2.dex */
public class BasicMarkerFactory implements IMarkerFactory {
    private final ConcurrentMap markerMap = new ConcurrentHashMap();

    @Override // org.slf4j.IMarkerFactory
    public Marker getMarker(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Marker name cannot be null");
        }
        Marker marker = (Marker) this.markerMap.get(str);
        if (marker != null) {
            return marker;
        }
        BasicMarker basicMarker = new BasicMarker(str);
        Marker marker2 = (Marker) this.markerMap.putIfAbsent(str, basicMarker);
        return marker2 != null ? marker2 : basicMarker;
    }
}
