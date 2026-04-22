package ch.qos.logback.core.spi;

import ch.qos.logback.core.Appender;
import ch.qos.logback.core.util.COWArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class AppenderAttachableImpl implements AppenderAttachable {
    static final long START = System.currentTimeMillis();
    private final COWArrayList appenderList = new COWArrayList(new Appender[0]);

    @Override // ch.qos.logback.core.spi.AppenderAttachable
    public void addAppender(Appender appender) {
        if (appender == null) {
            throw new IllegalArgumentException("Null argument disallowed");
        }
        this.appenderList.addIfAbsent(appender);
    }

    public int appendLoopOnAppenders(Object obj) {
        int i = 0;
        for (Appender appender : (Appender[]) this.appenderList.asTypedArray()) {
            appender.doAppend(obj);
            i++;
        }
        return i;
    }

    @Override // ch.qos.logback.core.spi.AppenderAttachable
    public void detachAndStopAllAppenders() {
        Iterator<Object> it = this.appenderList.iterator();
        while (it.hasNext()) {
            ((Appender) it.next()).stop();
        }
        this.appenderList.clear();
    }

    @Override // ch.qos.logback.core.spi.AppenderAttachable
    public boolean detachAppender(Appender appender) {
        if (appender == null) {
            return false;
        }
        return this.appenderList.remove(appender);
    }

    @Override // ch.qos.logback.core.spi.AppenderAttachable
    public boolean detachAppender(String str) {
        if (str == null) {
            return false;
        }
        for (Appender appender : this.appenderList) {
            if (str.equals(appender.getName())) {
                return this.appenderList.remove(appender);
            }
        }
        return false;
    }

    @Override // ch.qos.logback.core.spi.AppenderAttachable
    public Appender getAppender(String str) {
        if (str == null) {
            return null;
        }
        for (Appender appender : this.appenderList) {
            if (str.equals(appender.getName())) {
                return appender;
            }
        }
        return null;
    }

    @Override // ch.qos.logback.core.spi.AppenderAttachable
    public boolean isAttached(Appender appender) {
        if (appender == null) {
            return false;
        }
        Iterator<Object> it = this.appenderList.iterator();
        while (it.hasNext()) {
            if (((Appender) it.next()) == appender) {
                return true;
            }
        }
        return false;
    }

    @Override // ch.qos.logback.core.spi.AppenderAttachable
    public Iterator<Appender> iteratorForAppenders() {
        return this.appenderList.iterator();
    }
}
