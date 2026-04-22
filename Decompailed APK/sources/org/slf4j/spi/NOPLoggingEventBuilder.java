package org.slf4j.spi;

/* JADX INFO: loaded from: classes2.dex */
public class NOPLoggingEventBuilder implements LoggingEventBuilder {
    static final NOPLoggingEventBuilder SINGLETON = new NOPLoggingEventBuilder();

    private NOPLoggingEventBuilder() {
    }

    public static LoggingEventBuilder singleton() {
        return SINGLETON;
    }
}
