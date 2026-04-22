package ch.qos.logback.core.sift;

import ch.qos.logback.core.Appender;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.helpers.NOPAppender;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import ch.qos.logback.core.spi.ContextAwareImpl;

/* JADX INFO: loaded from: classes.dex */
public class AppenderTracker extends AbstractComponentTracker {
    final AppenderFactory appenderFactory;
    final Context context;
    final ContextAwareImpl contextAware;
    int nopaWarningCount = 0;

    public AppenderTracker(Context context, AppenderFactory appenderFactory) {
        this.context = context;
        this.appenderFactory = appenderFactory;
        this.contextAware = new ContextAwareImpl(context, this);
    }

    private NOPAppender buildNOPAppender(String str) {
        int i = this.nopaWarningCount;
        if (i < 4) {
            this.nopaWarningCount = i + 1;
            this.contextAware.addError("Building NOPAppender for discriminating value [" + str + "]");
        }
        NOPAppender nOPAppender = new NOPAppender();
        nOPAppender.setContext(this.context);
        nOPAppender.start();
        return nOPAppender;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ch.qos.logback.core.spi.AbstractComponentTracker
    public Appender buildComponent(String str) {
        Appender appenderBuildAppender;
        try {
            appenderBuildAppender = this.appenderFactory.buildAppender(this.context, str);
        } catch (JoranException unused) {
            this.contextAware.addError("Error while building appender with discriminating value [" + str + "]");
            appenderBuildAppender = null;
        }
        return appenderBuildAppender == null ? buildNOPAppender(str) : appenderBuildAppender;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ch.qos.logback.core.spi.AbstractComponentTracker
    public boolean isComponentStale(Appender appender) {
        return !appender.isStarted();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ch.qos.logback.core.spi.AbstractComponentTracker
    public void processPriorToRemoval(Appender appender) {
        appender.stop();
    }
}
