package ch.qos.logback.core.encoder;

import ch.qos.logback.core.spi.ContextAwareBase;

/* JADX INFO: loaded from: classes.dex */
public abstract class EncoderBase extends ContextAwareBase implements Encoder {
    protected boolean started;

    @Override // ch.qos.logback.core.encoder.Encoder
    public abstract /* synthetic */ byte[] encode(Object obj);

    @Override // ch.qos.logback.core.encoder.Encoder
    public abstract /* synthetic */ byte[] footerBytes();

    @Override // ch.qos.logback.core.encoder.Encoder
    public abstract /* synthetic */ byte[] headerBytes();

    @Override // ch.qos.logback.core.encoder.Encoder, ch.qos.logback.core.spi.LifeCycle
    public boolean isStarted() {
        return this.started;
    }

    public void start() {
        this.started = true;
    }

    @Override // ch.qos.logback.core.encoder.Encoder, ch.qos.logback.core.spi.LifeCycle
    public void stop() {
        this.started = false;
    }
}
