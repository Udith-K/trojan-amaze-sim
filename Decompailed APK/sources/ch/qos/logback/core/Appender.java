package ch.qos.logback.core;

import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.ContextAware;
import ch.qos.logback.core.spi.FilterAttachable;
import ch.qos.logback.core.spi.FilterReply;
import ch.qos.logback.core.spi.LifeCycle;
import ch.qos.logback.core.status.Status;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface Appender extends LifeCycle, ContextAware, FilterAttachable {
    @Override // ch.qos.logback.core.spi.ContextAware
    /* synthetic */ void addError(String str);

    @Override // ch.qos.logback.core.spi.ContextAware
    /* synthetic */ void addError(String str, Throwable th);

    @Override // ch.qos.logback.core.spi.FilterAttachable
    /* synthetic */ void addFilter(Filter filter);

    @Override // ch.qos.logback.core.spi.ContextAware
    /* synthetic */ void addInfo(String str);

    @Override // ch.qos.logback.core.spi.ContextAware
    /* synthetic */ void addInfo(String str, Throwable th);

    @Override // ch.qos.logback.core.spi.ContextAware
    /* synthetic */ void addStatus(Status status);

    @Override // ch.qos.logback.core.spi.ContextAware
    /* synthetic */ void addWarn(String str);

    @Override // ch.qos.logback.core.spi.ContextAware
    /* synthetic */ void addWarn(String str, Throwable th);

    @Override // ch.qos.logback.core.spi.FilterAttachable
    /* synthetic */ void clearAllFilters();

    void doAppend(Object obj) throws LogbackException;

    @Override // ch.qos.logback.core.spi.ContextAware
    /* synthetic */ Context getContext();

    @Override // ch.qos.logback.core.spi.FilterAttachable
    /* synthetic */ List getCopyOfAttachedFiltersList();

    @Override // ch.qos.logback.core.spi.FilterAttachable
    /* synthetic */ FilterReply getFilterChainDecision(Object obj);

    String getName();

    @Override // ch.qos.logback.core.spi.LifeCycle
    /* synthetic */ boolean isStarted();

    @Override // ch.qos.logback.core.spi.ContextAware
    /* synthetic */ void setContext(Context context);

    void setName(String str);

    @Override // ch.qos.logback.core.spi.LifeCycle
    /* synthetic */ void start();

    @Override // ch.qos.logback.core.spi.LifeCycle
    /* synthetic */ void stop();
}
