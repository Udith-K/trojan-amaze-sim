package mu.internal;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import mu.KLogger;
import mu.KMarkerFactory;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.event.Level;
import org.slf4j.spi.LocationAwareLogger;
import org.slf4j.spi.LoggingEventBuilder;

/* JADX INFO: compiled from: LocationAwareKLogger.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class LocationAwareKLogger implements KLogger, Logger {
    private final Marker CATCHING;
    private final Marker ENTRY;
    private final Marker EXIT;
    private final String EXITMESSAGE;
    private final String EXITONLY;
    private final Marker THROWING;
    private final String fqcn;
    private final LocationAwareLogger underlyingLogger;

    @Override // org.slf4j.Logger
    public String getName() {
        return this.underlyingLogger.getName();
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

    public LocationAwareKLogger(LocationAwareLogger underlyingLogger) {
        Intrinsics.checkNotNullParameter(underlyingLogger, "underlyingLogger");
        this.underlyingLogger = underlyingLogger;
        String name = LocationAwareKLogger.class.getName();
        Intrinsics.checkNotNullExpressionValue(name, "LocationAwareKLogger::class.java.name");
        this.fqcn = name;
        KMarkerFactory kMarkerFactory = KMarkerFactory.INSTANCE;
        this.ENTRY = kMarkerFactory.getMarker("ENTRY");
        this.EXIT = kMarkerFactory.getMarker("EXIT");
        this.THROWING = kMarkerFactory.getMarker("THROWING");
        this.CATCHING = kMarkerFactory.getMarker("CATCHING");
        this.EXITONLY = "exit";
        this.EXITMESSAGE = "exit with ({})";
    }

    public LocationAwareLogger getUnderlyingLogger() {
        return this.underlyingLogger;
    }

    @Override // org.slf4j.Logger
    public void trace(String str) {
        if (getUnderlyingLogger().isTraceEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 0, str, null, null);
        }
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Object obj) {
        if (getUnderlyingLogger().isTraceEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 0, str, new Object[]{obj}, null);
        }
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Object obj, Object obj2) {
        if (getUnderlyingLogger().isTraceEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 0, str, new Object[]{obj, obj2}, null);
        }
    }

    @Override // org.slf4j.Logger
    public void trace(String str, Object[] argArray) {
        Intrinsics.checkNotNullParameter(argArray, "argArray");
        if (getUnderlyingLogger().isTraceEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 0, str, argArray, null);
        }
    }

    @Override // org.slf4j.Logger
    public void debug(String str) {
        if (getUnderlyingLogger().isDebugEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 10, str, null, null);
        }
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object obj) {
        if (getUnderlyingLogger().isDebugEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 10, str, new Object[]{obj}, null);
        }
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object obj, Object obj2) {
        if (getUnderlyingLogger().isDebugEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 10, str, new Object[]{obj, obj2}, null);
        }
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Object[] argArray) {
        Intrinsics.checkNotNullParameter(argArray, "argArray");
        if (getUnderlyingLogger().isDebugEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 10, str, argArray, null);
        }
    }

    @Override // org.slf4j.Logger
    public void debug(String str, Throwable th) {
        if (getUnderlyingLogger().isDebugEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 10, str, null, th);
        }
    }

    @Override // org.slf4j.Logger
    public void info(String str) {
        if (getUnderlyingLogger().isInfoEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 20, str, null, null);
        }
    }

    @Override // org.slf4j.Logger
    public void info(String str, Throwable th) {
        if (getUnderlyingLogger().isInfoEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 20, str, null, th);
        }
    }

    @Override // org.slf4j.Logger
    public void warn(String str) {
        if (getUnderlyingLogger().isWarnEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 30, str, null, null);
        }
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object obj) {
        if (getUnderlyingLogger().isWarnEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 30, str, new Object[]{obj}, null);
        }
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object obj, Object obj2) {
        if (getUnderlyingLogger().isWarnEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 30, str, new Object[]{obj, obj2}, null);
        }
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Object[] argArray) {
        Intrinsics.checkNotNullParameter(argArray, "argArray");
        if (getUnderlyingLogger().isWarnEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 30, str, argArray, null);
        }
    }

    @Override // org.slf4j.Logger
    public void warn(String str, Throwable th) {
        if (getUnderlyingLogger().isWarnEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 30, str, null, th);
        }
    }

    @Override // org.slf4j.Logger
    public void error(String str) {
        if (getUnderlyingLogger().isErrorEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 40, str, null, null);
        }
    }

    @Override // org.slf4j.Logger
    public void error(String str, Throwable th) {
        if (getUnderlyingLogger().isErrorEnabled()) {
            getUnderlyingLogger().log(null, this.fqcn, 40, str, null, th);
        }
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
