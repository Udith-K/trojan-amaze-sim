package ch.qos.logback.core.spi;

import ch.qos.logback.core.Appender;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public interface AppenderAttachable {
    void addAppender(Appender appender);

    void detachAndStopAllAppenders();

    boolean detachAppender(Appender appender);

    boolean detachAppender(String str);

    Appender getAppender(String str);

    boolean isAttached(Appender appender);

    Iterator<Appender> iteratorForAppenders();
}
