package org.slf4j.event;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Marker;
import org.slf4j.helpers.SubstituteLogger;

/* JADX INFO: loaded from: classes2.dex */
public class SubstituteLoggingEvent implements LoggingEvent {
    Object[] argArray;
    Level level;
    SubstituteLogger logger;
    String loggerName;
    List markers;
    String message;
    String threadName;
    Throwable throwable;
    long timeStamp;

    @Override // org.slf4j.event.LoggingEvent
    public Level getLevel() {
        return this.level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override // org.slf4j.event.LoggingEvent
    public List getMarkers() {
        return this.markers;
    }

    public void addMarker(Marker marker) {
        if (marker == null) {
            return;
        }
        if (this.markers == null) {
            this.markers = new ArrayList(2);
        }
        this.markers.add(marker);
    }

    public void setLoggerName(String str) {
        this.loggerName = str;
    }

    public SubstituteLogger getLogger() {
        return this.logger;
    }

    public void setLogger(SubstituteLogger substituteLogger) {
        this.logger = substituteLogger;
    }

    @Override // org.slf4j.event.LoggingEvent
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    @Override // org.slf4j.event.LoggingEvent
    public Object[] getArgumentArray() {
        return this.argArray;
    }

    public void setArgumentArray(Object[] objArr) {
        this.argArray = objArr;
    }

    public void setTimeStamp(long j) {
        this.timeStamp = j;
    }

    public void setThreadName(String str) {
        this.threadName = str;
    }

    @Override // org.slf4j.event.LoggingEvent
    public Throwable getThrowable() {
        return this.throwable;
    }

    public void setThrowable(Throwable th) {
        this.throwable = th;
    }
}
