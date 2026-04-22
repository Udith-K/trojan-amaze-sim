package kellinwood.logging;

import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class LoggerManager {
    static LoggerFactory factory = new NullLoggerFactory();
    static Map loggers = new TreeMap();

    public static LoggerInterface getLogger(String str) {
        LoggerInterface loggerInterface = (LoggerInterface) loggers.get(str);
        if (loggerInterface != null) {
            return loggerInterface;
        }
        LoggerInterface logger = factory.getLogger(str);
        loggers.put(str, logger);
        return logger;
    }
}
