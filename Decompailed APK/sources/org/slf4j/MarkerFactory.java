package org.slf4j;

import org.slf4j.helpers.BasicMarkerFactory;
import org.slf4j.helpers.Reporter;
import org.slf4j.spi.SLF4JServiceProvider;

/* JADX INFO: loaded from: classes2.dex */
public abstract class MarkerFactory {
    static IMarkerFactory MARKER_FACTORY;

    static {
        SLF4JServiceProvider provider = LoggerFactory.getProvider();
        if (provider != null) {
            MARKER_FACTORY = provider.getMarkerFactory();
            return;
        }
        Reporter.error("Failed to find provider");
        Reporter.error("Defaulting to BasicMarkerFactory.");
        MARKER_FACTORY = new BasicMarkerFactory();
    }

    public static Marker getMarker(String str) {
        return MARKER_FACTORY.getMarker(str);
    }
}
