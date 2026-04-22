package ch.qos.logback.classic.pattern;

import ch.qos.logback.classic.spi.ILoggingEvent;
import java.util.List;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes.dex */
public class MarkerConverter extends ClassicConverter {
    private static String EMPTY = "";

    @Override // ch.qos.logback.core.pattern.Converter
    public String convert(ILoggingEvent iLoggingEvent) {
        List<Marker> markers = iLoggingEvent.getMarkers();
        return (markers == null || markers.isEmpty()) ? EMPTY : markers.toString();
    }
}
