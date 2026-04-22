package ch.qos.logback.core;

import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.FilterAttachableImpl;
import ch.qos.logback.core.spi.FilterReply;
import ch.qos.logback.core.status.WarnStatus;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class UnsynchronizedAppenderBase extends ContextAwareBase implements Appender {
    static final int ALLOWED_REPEATS = 3;
    protected String name;
    protected boolean started = false;
    private ThreadLocal<Boolean> guard = new ThreadLocal<>();
    private FilterAttachableImpl fai = new FilterAttachableImpl();
    private int statusRepeatCount = 0;
    private int exceptionCount = 0;

    @Override // ch.qos.logback.core.Appender, ch.qos.logback.core.spi.FilterAttachable
    public void addFilter(Filter filter) {
        this.fai.addFilter(filter);
    }

    protected abstract void append(Object obj);

    @Override // ch.qos.logback.core.Appender, ch.qos.logback.core.spi.FilterAttachable
    public void clearAllFilters() {
        this.fai.clearAllFilters();
    }

    @Override // ch.qos.logback.core.Appender
    public void doAppend(Object obj) {
        Boolean bool = Boolean.TRUE;
        if (bool.equals(this.guard.get())) {
            return;
        }
        try {
            try {
                this.guard.set(bool);
            } catch (Exception e) {
                int i = this.exceptionCount;
                this.exceptionCount = i + 1;
                if (i < 3) {
                    addError("Appender [" + this.name + "] failed to append.", e);
                }
            }
            if (!this.started) {
                int i2 = this.statusRepeatCount;
                this.statusRepeatCount = i2 + 1;
                if (i2 < 3) {
                    addStatus(new WarnStatus("Attempted to append to non started appender [" + this.name + "].", this));
                }
            } else if (getFilterChainDecision(obj) != FilterReply.DENY) {
                append(obj);
            }
        } finally {
            this.guard.set(Boolean.FALSE);
        }
    }

    @Override // ch.qos.logback.core.Appender, ch.qos.logback.core.spi.FilterAttachable
    public List<Filter> getCopyOfAttachedFiltersList() {
        return this.fai.getCopyOfAttachedFiltersList();
    }

    @Override // ch.qos.logback.core.Appender, ch.qos.logback.core.spi.FilterAttachable
    public FilterReply getFilterChainDecision(Object obj) {
        return this.fai.getFilterChainDecision(obj);
    }

    @Override // ch.qos.logback.core.Appender
    public String getName() {
        return this.name;
    }

    @Override // ch.qos.logback.core.Appender, ch.qos.logback.core.spi.LifeCycle
    public boolean isStarted() {
        return this.started;
    }

    @Override // ch.qos.logback.core.Appender
    public void setName(String str) {
        this.name = str;
    }

    public void start() {
        this.started = true;
    }

    public void stop() {
        this.started = false;
    }

    public String toString() {
        return getClass().getName() + "[" + this.name + "]";
    }
}
