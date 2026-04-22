package ch.qos.logback.core;

import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.FilterAttachableImpl;
import ch.qos.logback.core.spi.FilterReply;
import ch.qos.logback.core.status.WarnStatus;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class AppenderBase extends ContextAwareBase implements Appender {
    static final int ALLOWED_REPEATS = 5;
    protected String name;
    protected volatile boolean started = false;
    private boolean guard = false;
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
    public synchronized void doAppend(Object obj) {
        if (this.guard) {
            return;
        }
        try {
            try {
                this.guard = true;
            } catch (Exception e) {
                int i = this.exceptionCount;
                this.exceptionCount = i + 1;
                if (i < 5) {
                    addError("Appender [" + this.name + "] failed to append.", e);
                }
            }
            if (this.started) {
                if (getFilterChainDecision(obj) == FilterReply.DENY) {
                    return;
                }
                append(obj);
                return;
            }
            int i2 = this.statusRepeatCount;
            this.statusRepeatCount = i2 + 1;
            if (i2 < 5) {
                addStatus(new WarnStatus("Attempted to append to non started appender [" + this.name + "].", this));
            }
        } finally {
            this.guard = false;
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

    @Override // ch.qos.logback.core.Appender, ch.qos.logback.core.spi.LifeCycle
    public void start() {
        this.started = true;
    }

    @Override // ch.qos.logback.core.Appender, ch.qos.logback.core.spi.LifeCycle
    public void stop() {
        this.started = false;
    }

    public String toString() {
        return getClass().getName() + "[" + this.name + "]";
    }
}
