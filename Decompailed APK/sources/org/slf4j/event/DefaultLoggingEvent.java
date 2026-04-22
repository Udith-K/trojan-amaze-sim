package org.slf4j.event;

import java.util.List;
import org.slf4j.Logger;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultLoggingEvent implements LoggingEvent {
    List arguments;
    Level level;
    Logger logger;
    List markers;
    String message;
    Throwable throwable;

    public DefaultLoggingEvent(Level level, Logger logger) {
        this.logger = logger;
        this.level = level;
    }

    @Override // org.slf4j.event.LoggingEvent
    public List getMarkers() {
        return this.markers;
    }

    @Override // org.slf4j.event.LoggingEvent
    public Object[] getArgumentArray() {
        List list = this.arguments;
        if (list == null) {
            return null;
        }
        return list.toArray();
    }

    @Override // org.slf4j.event.LoggingEvent
    public Level getLevel() {
        return this.level;
    }

    @Override // org.slf4j.event.LoggingEvent
    public String getMessage() {
        return this.message;
    }

    @Override // org.slf4j.event.LoggingEvent
    public Throwable getThrowable() {
        return this.throwable;
    }
}
