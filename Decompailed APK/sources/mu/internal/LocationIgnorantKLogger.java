package mu.internal;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import mu.KLogger;
import org.slf4j.Logger;
import org.slf4j.event.Level;
import org.slf4j.spi.LoggingEventBuilder;

/* JADX INFO: compiled from: LocationIgnorantKLogger.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class LocationIgnorantKLogger implements KLogger, Logger {
    private final Logger underlyingLogger;

    @Override // org.slf4j.Logger
    public void debug(String str) {
        this.underlyingLogger.debug(str);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object obj) {
        this.underlyingLogger.debug(str, obj);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object obj, Object obj2) {
        this.underlyingLogger.debug(str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Throwable th) {
        this.underlyingLogger.debug(str, th);
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object... objArr) {
        this.underlyingLogger.debug(str, objArr);
    }

    @Override // org.slf4j.Logger
    public void error(String str) {
        this.underlyingLogger.error(str);
    }

    @Override // org.slf4j.Logger
    public void error(String str, Throwable th) {
        this.underlyingLogger.error(str, th);
    }

    @Override // org.slf4j.Logger
    public String getName() {
        return this.underlyingLogger.getName();
    }

    @Override // org.slf4j.Logger
    public void info(String str) {
        this.underlyingLogger.info(str);
    }

    @Override // org.slf4j.Logger
    public void info(String str, Throwable th) {
        this.underlyingLogger.info(str, th);
    }

    @Override // org.slf4j.Logger
    public boolean isDebugEnabled() {
        return this.underlyingLogger.isDebugEnabled();
    }

    @Override // org.slf4j.Logger
    public /* synthetic */ boolean isEnabledForLevel(Level level) {
        return Logger.CC.$default$isEnabledForLevel(this, level);
    }

    @Override // org.slf4j.Logger
    public boolean isErrorEnabled() {
        return this.underlyingLogger.isErrorEnabled();
    }

    @Override // org.slf4j.Logger
    public boolean isInfoEnabled() {
        return this.underlyingLogger.isInfoEnabled();
    }

    @Override // org.slf4j.Logger
    public boolean isTraceEnabled() {
        return this.underlyingLogger.isTraceEnabled();
    }

    @Override // org.slf4j.Logger
    public boolean isWarnEnabled() {
        return this.underlyingLogger.isWarnEnabled();
    }

    @Override // org.slf4j.Logger
    public /* synthetic */ LoggingEventBuilder makeLoggingEventBuilder(Level level) {
        return Logger.CC.$default$makeLoggingEventBuilder(this, level);
    }

    @Override // org.slf4j.Logger
    public void trace(String str) {
        this.underlyingLogger.trace(str);
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Object obj) {
        this.underlyingLogger.trace(str, obj);
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Object obj, Object obj2) {
        this.underlyingLogger.trace(str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Object... objArr) {
        this.underlyingLogger.trace(str, objArr);
    }

    @Override // org.slf4j.Logger
    public void warn(String str) {
        this.underlyingLogger.warn(str);
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object obj) {
        this.underlyingLogger.warn(str, obj);
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object obj, Object obj2) {
        this.underlyingLogger.warn(str, obj, obj2);
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Throwable th) {
        this.underlyingLogger.warn(str, th);
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object... objArr) {
        this.underlyingLogger.warn(str, objArr);
    }

    public LocationIgnorantKLogger(Logger underlyingLogger) {
        Intrinsics.checkNotNullParameter(underlyingLogger, "underlyingLogger");
        this.underlyingLogger = underlyingLogger;
    }

    @Override // mu.KLogger
    public void debug(Function0 msg) throws Exception {
        String errorLog;
        Intrinsics.checkNotNullParameter(msg, "msg");
        if (isDebugEnabled()) {
            try {
                errorLog = String.valueOf(msg.invoke());
            } catch (Exception e) {
                errorLog = ErrorMessageProducer.INSTANCE.getErrorLog(e);
            }
            debug(errorLog);
        }
    }

    @Override // mu.KLogger
    public void info(Function0 msg) throws Exception {
        String errorLog;
        Intrinsics.checkNotNullParameter(msg, "msg");
        if (isInfoEnabled()) {
            try {
                errorLog = String.valueOf(msg.invoke());
            } catch (Exception e) {
                errorLog = ErrorMessageProducer.INSTANCE.getErrorLog(e);
            }
            info(errorLog);
        }
    }

    @Override // mu.KLogger
    public void warn(Function0 msg) throws Exception {
        String errorLog;
        Intrinsics.checkNotNullParameter(msg, "msg");
        if (isWarnEnabled()) {
            try {
                errorLog = String.valueOf(msg.invoke());
            } catch (Exception e) {
                errorLog = ErrorMessageProducer.INSTANCE.getErrorLog(e);
            }
            warn(errorLog);
        }
    }

    @Override // mu.KLogger
    public void error(Function0 msg) throws Exception {
        String errorLog;
        Intrinsics.checkNotNullParameter(msg, "msg");
        if (isErrorEnabled()) {
            try {
                errorLog = String.valueOf(msg.invoke());
            } catch (Exception e) {
                errorLog = ErrorMessageProducer.INSTANCE.getErrorLog(e);
            }
            error(errorLog);
        }
    }

    @Override // mu.KLogger
    public void warn(Throwable th, Function0 msg) throws Exception {
        String errorLog;
        Intrinsics.checkNotNullParameter(msg, "msg");
        if (isWarnEnabled()) {
            try {
                errorLog = String.valueOf(msg.invoke());
            } catch (Exception e) {
                errorLog = ErrorMessageProducer.INSTANCE.getErrorLog(e);
            }
            warn(errorLog, th);
        }
    }

    @Override // mu.KLogger
    public void error(Throwable th, Function0 msg) throws Exception {
        String errorLog;
        Intrinsics.checkNotNullParameter(msg, "msg");
        if (isErrorEnabled()) {
            try {
                errorLog = String.valueOf(msg.invoke());
            } catch (Exception e) {
                errorLog = ErrorMessageProducer.INSTANCE.getErrorLog(e);
            }
            error(errorLog, th);
        }
    }
}
