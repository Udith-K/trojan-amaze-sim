package org.slf4j;

import org.slf4j.event.Level;
import org.slf4j.spi.DefaultLoggingEventBuilder;
import org.slf4j.spi.LoggingEventBuilder;
import org.slf4j.spi.NOPLoggingEventBuilder;

/* JADX INFO: loaded from: classes2.dex */
public interface Logger {
    void debug(String str);

    void debug(String str, Object obj);

    void debug(String str, Object obj, Object obj2);

    void debug(String str, Throwable th);

    void debug(String str, Object... objArr);

    void error(String str);

    void error(String str, Throwable th);

    String getName();

    void info(String str);

    void info(String str, Throwable th);

    boolean isDebugEnabled();

    boolean isEnabledForLevel(Level level);

    boolean isErrorEnabled();

    boolean isInfoEnabled();

    boolean isTraceEnabled();

    boolean isWarnEnabled();

    LoggingEventBuilder makeLoggingEventBuilder(Level level);

    void trace(String str);

    void trace(String str, Object obj);

    void trace(String str, Object obj, Object obj2);

    void trace(String str, Object... objArr);

    void warn(String str);

    void warn(String str, Object obj);

    void warn(String str, Object obj, Object obj2);

    void warn(String str, Throwable th);

    void warn(String str, Object... objArr);

    /* JADX INFO: renamed from: org.slf4j.Logger$-CC, reason: invalid class name */
    public abstract /* synthetic */ class CC {
        public static LoggingEventBuilder $default$makeLoggingEventBuilder(Logger logger, Level level) {
            return new DefaultLoggingEventBuilder(logger, level);
        }

        public static LoggingEventBuilder $default$atLevel(Logger logger, Level level) {
            if (logger.isEnabledForLevel(level)) {
                return logger.makeLoggingEventBuilder(level);
            }
            return NOPLoggingEventBuilder.singleton();
        }

        public static boolean $default$isEnabledForLevel(Logger logger, Level level) {
            int i = level.toInt();
            if (i == 0) {
                return logger.isTraceEnabled();
            }
            if (i == 10) {
                return logger.isDebugEnabled();
            }
            if (i == 20) {
                return logger.isInfoEnabled();
            }
            if (i == 30) {
                return logger.isWarnEnabled();
            }
            if (i == 40) {
                return logger.isErrorEnabled();
            }
            throw new IllegalArgumentException("Level [" + level + "] not recognized.");
        }

        public static LoggingEventBuilder $default$atTrace(Logger logger) {
            if (logger.isTraceEnabled()) {
                return logger.makeLoggingEventBuilder(Level.TRACE);
            }
            return NOPLoggingEventBuilder.singleton();
        }

        public static LoggingEventBuilder $default$atDebug(Logger logger) {
            if (logger.isDebugEnabled()) {
                return logger.makeLoggingEventBuilder(Level.DEBUG);
            }
            return NOPLoggingEventBuilder.singleton();
        }

        public static LoggingEventBuilder $default$atInfo(Logger logger) {
            if (logger.isInfoEnabled()) {
                return logger.makeLoggingEventBuilder(Level.INFO);
            }
            return NOPLoggingEventBuilder.singleton();
        }

        public static LoggingEventBuilder $default$atWarn(Logger logger) {
            if (logger.isWarnEnabled()) {
                return logger.makeLoggingEventBuilder(Level.WARN);
            }
            return NOPLoggingEventBuilder.singleton();
        }

        public static LoggingEventBuilder $default$atError(Logger logger) {
            if (logger.isErrorEnabled()) {
                return logger.makeLoggingEventBuilder(Level.ERROR);
            }
            return NOPLoggingEventBuilder.singleton();
        }
    }
}
