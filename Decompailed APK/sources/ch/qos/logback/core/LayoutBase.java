package ch.qos.logback.core;

import ch.qos.logback.core.spi.ContextAwareBase;
import fi.iki.elonen.NanoHTTPD;

/* JADX INFO: loaded from: classes.dex */
public abstract class LayoutBase extends ContextAwareBase implements Layout {
    String fileFooter;
    String fileHeader;
    String presentationFooter;
    String presentationHeader;
    protected boolean started;

    public abstract /* synthetic */ String doLayout(Object obj);

    @Override // ch.qos.logback.core.Layout
    public String getContentType() {
        return NanoHTTPD.MIME_PLAINTEXT;
    }

    @Override // ch.qos.logback.core.spi.ContextAwareBase, ch.qos.logback.core.spi.ContextAware
    public Context getContext() {
        return this.context;
    }

    @Override // ch.qos.logback.core.Layout
    public String getFileFooter() {
        return this.fileFooter;
    }

    @Override // ch.qos.logback.core.Layout
    public String getFileHeader() {
        return this.fileHeader;
    }

    @Override // ch.qos.logback.core.Layout
    public String getPresentationFooter() {
        return this.presentationFooter;
    }

    @Override // ch.qos.logback.core.Layout
    public String getPresentationHeader() {
        return this.presentationHeader;
    }

    @Override // ch.qos.logback.core.Layout, ch.qos.logback.core.spi.LifeCycle
    public boolean isStarted() {
        return this.started;
    }

    @Override // ch.qos.logback.core.spi.ContextAwareBase, ch.qos.logback.core.spi.ContextAware
    public void setContext(Context context) {
        this.context = context;
    }

    public void setFileFooter(String str) {
        this.fileFooter = str;
    }

    public void setFileHeader(String str) {
        this.fileHeader = str;
    }

    public void setPresentationFooter(String str) {
        this.presentationFooter = str;
    }

    public void setPresentationHeader(String str) {
        this.presentationHeader = str;
    }

    @Override // ch.qos.logback.core.Layout, ch.qos.logback.core.spi.LifeCycle
    public void start() {
        this.started = true;
    }

    @Override // ch.qos.logback.core.Layout, ch.qos.logback.core.spi.LifeCycle
    public void stop() {
        this.started = false;
    }
}
