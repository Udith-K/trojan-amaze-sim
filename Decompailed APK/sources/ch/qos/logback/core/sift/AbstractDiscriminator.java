package ch.qos.logback.core.sift;

import ch.qos.logback.core.spi.ContextAwareBase;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractDiscriminator extends ContextAwareBase implements Discriminator {
    protected boolean started;

    public abstract /* synthetic */ String getDiscriminatingValue(Object obj);

    public abstract /* synthetic */ String getKey();

    @Override // ch.qos.logback.core.sift.Discriminator, ch.qos.logback.core.spi.LifeCycle
    public boolean isStarted() {
        return this.started;
    }

    public void start() {
        this.started = true;
    }

    @Override // ch.qos.logback.core.sift.Discriminator, ch.qos.logback.core.spi.LifeCycle
    public void stop() {
        this.started = false;
    }
}
