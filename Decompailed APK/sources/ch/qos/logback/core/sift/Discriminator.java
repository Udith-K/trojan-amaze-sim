package ch.qos.logback.core.sift;

import ch.qos.logback.core.spi.LifeCycle;

/* JADX INFO: loaded from: classes.dex */
public interface Discriminator extends LifeCycle {
    String getDiscriminatingValue(Object obj);

    String getKey();

    @Override // ch.qos.logback.core.spi.LifeCycle
    /* synthetic */ boolean isStarted();

    @Override // ch.qos.logback.core.spi.LifeCycle
    /* synthetic */ void start();

    @Override // ch.qos.logback.core.spi.LifeCycle
    /* synthetic */ void stop();
}
