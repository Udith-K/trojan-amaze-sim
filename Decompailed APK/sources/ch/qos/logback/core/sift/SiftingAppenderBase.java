package ch.qos.logback.core.sift;

import ch.qos.logback.core.Appender;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.spi.ComponentTracker;
import ch.qos.logback.core.util.Duration;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class SiftingAppenderBase extends AppenderBase {
    AppenderFactory appenderFactory;
    protected AppenderTracker appenderTracker;
    Discriminator discriminator;
    Duration timeout = new Duration(ComponentTracker.DEFAULT_TIMEOUT);
    int maxAppenderCount = Integer.MAX_VALUE;

    @Override // ch.qos.logback.core.AppenderBase
    protected void append(Object obj) {
        if (isStarted()) {
            String discriminatingValue = this.discriminator.getDiscriminatingValue(obj);
            long timestamp = getTimestamp(obj);
            Appender appender = (Appender) this.appenderTracker.getOrCreate(discriminatingValue, timestamp);
            if (eventMarksEndOfLife(obj)) {
                this.appenderTracker.endOfLife(discriminatingValue);
            }
            this.appenderTracker.removeStaleComponents(timestamp);
            appender.doAppend(obj);
        }
    }

    protected abstract boolean eventMarksEndOfLife(Object obj);

    public AppenderTracker getAppenderTracker() {
        return this.appenderTracker;
    }

    public Discriminator getDiscriminator() {
        return this.discriminator;
    }

    public String getDiscriminatorKey() {
        Discriminator discriminator = this.discriminator;
        if (discriminator != null) {
            return discriminator.getKey();
        }
        return null;
    }

    public int getMaxAppenderCount() {
        return this.maxAppenderCount;
    }

    public Duration getTimeout() {
        return this.timeout;
    }

    protected abstract long getTimestamp(Object obj);

    public void setAppenderFactory(AppenderFactory appenderFactory) {
        this.appenderFactory = appenderFactory;
    }

    public void setDiscriminator(Discriminator discriminator) {
        this.discriminator = discriminator;
    }

    public void setMaxAppenderCount(int i) {
        this.maxAppenderCount = i;
    }

    public void setTimeout(Duration duration) {
        this.timeout = duration;
    }

    @Override // ch.qos.logback.core.AppenderBase, ch.qos.logback.core.Appender, ch.qos.logback.core.spi.LifeCycle
    public void start() {
        int i;
        if (this.discriminator == null) {
            addError("Missing discriminator. Aborting");
            i = 1;
        } else {
            i = 0;
        }
        if (!this.discriminator.isStarted()) {
            addError("Discriminator has not started successfully. Aborting");
            i++;
        }
        AppenderFactory appenderFactory = this.appenderFactory;
        if (appenderFactory == null) {
            addError("AppenderFactory has not been set. Aborting");
            i++;
        } else {
            AppenderTracker appenderTracker = new AppenderTracker(this.context, appenderFactory);
            this.appenderTracker = appenderTracker;
            appenderTracker.setMaxComponents(this.maxAppenderCount);
            this.appenderTracker.setTimeout(this.timeout.getMilliseconds());
        }
        if (i == 0) {
            super.start();
        }
    }

    @Override // ch.qos.logback.core.AppenderBase, ch.qos.logback.core.Appender, ch.qos.logback.core.spi.LifeCycle
    public void stop() {
        Iterator<Object> it = this.appenderTracker.allComponents().iterator();
        while (it.hasNext()) {
            ((Appender) it.next()).stop();
        }
    }
}
