package ch.qos.logback.core;

import ch.qos.logback.core.spi.ContextAware;
import ch.qos.logback.core.spi.LifeCycle;
import ch.qos.logback.core.status.Status;

/* JADX INFO: loaded from: classes.dex */
public interface Layout extends ContextAware, LifeCycle {
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

    String doLayout(Object obj);

    String getContentType();

    @Override // ch.qos.logback.core.spi.ContextAware
    /* synthetic */ Context getContext();

    String getFileFooter();

    String getFileHeader();

    String getPresentationFooter();

    String getPresentationHeader();

    @Override // ch.qos.logback.core.spi.LifeCycle
    /* synthetic */ boolean isStarted();

    @Override // ch.qos.logback.core.spi.ContextAware
    /* synthetic */ void setContext(Context context);

    @Override // ch.qos.logback.core.spi.LifeCycle
    /* synthetic */ void start();

    @Override // ch.qos.logback.core.spi.LifeCycle
    /* synthetic */ void stop();
}
