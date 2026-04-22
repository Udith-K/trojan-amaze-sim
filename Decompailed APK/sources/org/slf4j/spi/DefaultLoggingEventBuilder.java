package org.slf4j.spi;

import org.slf4j.Logger;
import org.slf4j.event.DefaultLoggingEvent;
import org.slf4j.event.Level;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultLoggingEventBuilder implements LoggingEventBuilder {
    protected Logger logger;
    protected DefaultLoggingEvent loggingEvent;

    public DefaultLoggingEventBuilder(Logger logger, Level level) {
        this.logger = logger;
        this.loggingEvent = new DefaultLoggingEvent(level, logger);
    }
}
