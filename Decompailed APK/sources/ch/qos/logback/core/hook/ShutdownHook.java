package ch.qos.logback.core.hook;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.ContextAware;
import ch.qos.logback.core.status.Status;

/* JADX INFO: loaded from: classes.dex */
public interface ShutdownHook extends Runnable, ContextAware {
    @Override // ch.qos.logback.core.spi.ContextAware
    /* synthetic */ void addError(String str);

    @Override // ch.qos.logback.core.spi.ContextAware
    /* synthetic */ void addError(String str, Throwable th);

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

    @Override // ch.qos.logback.core.spi.ContextAware
    /* synthetic */ Context getContext();

    @Override // ch.qos.logback.core.spi.ContextAware
    /* synthetic */ void setContext(Context context);
}
