package org.slf4j.helpers;

import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.event.Level;
import org.slf4j.spi.LoggingEventBuilder;

/* JADX INFO: loaded from: classes2.dex */
abstract class NamedLoggerBase implements Logger, Serializable {
    @Override // org.slf4j.Logger
    public /* synthetic */ boolean isEnabledForLevel(Level level) {
        return Logger.CC.$default$isEnabledForLevel(this, level);
    }

    @Override // org.slf4j.Logger
    public /* synthetic */ LoggingEventBuilder makeLoggingEventBuilder(Level level) {
        return Logger.CC.$default$makeLoggingEventBuilder(this, level);
    }

    NamedLoggerBase() {
    }
}
