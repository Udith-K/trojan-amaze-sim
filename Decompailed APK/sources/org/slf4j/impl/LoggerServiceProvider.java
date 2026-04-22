package org.slf4j.impl;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.classic.util.LogbackMDCAdapter;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.status.StatusUtil;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.ILoggerFactory;
import org.slf4j.IMarkerFactory;
import org.slf4j.helpers.BasicMarkerFactory;
import org.slf4j.helpers.Util;
import org.slf4j.spi.MDCAdapter;
import org.slf4j.spi.SLF4JServiceProvider;

/* JADX INFO: loaded from: classes2.dex */
public class LoggerServiceProvider implements SLF4JServiceProvider {
    public static String REQUESTED_API_VERSION = "2.0.7";
    private LoggerContext defaultLoggerContext;
    private IMarkerFactory markerFactory;
    private MDCAdapter mdcAdapter;

    private void initializeLoggerContext() {
        try {
            try {
                new ContextInitializer(this.defaultLoggerContext).autoConfig();
            } catch (JoranException e) {
                Util.report("Failed to auto configure default logger context", e);
            }
            if (StatusUtil.contextHasStatusListener(this.defaultLoggerContext)) {
                return;
            }
            StatusPrinter.printInCaseOfErrorsOrWarnings(this.defaultLoggerContext);
        } catch (Exception e2) {
            Util.report("Failed to instantiate [" + LoggerContext.class.getName() + "]", e2);
        }
    }

    @Override // org.slf4j.spi.SLF4JServiceProvider
    public ILoggerFactory getLoggerFactory() {
        return this.defaultLoggerContext;
    }

    @Override // org.slf4j.spi.SLF4JServiceProvider
    public MDCAdapter getMDCAdapter() {
        return this.mdcAdapter;
    }

    @Override // org.slf4j.spi.SLF4JServiceProvider
    public IMarkerFactory getMarkerFactory() {
        return this.markerFactory;
    }

    @Override // org.slf4j.spi.SLF4JServiceProvider
    public String getRequestedApiVersion() {
        return REQUESTED_API_VERSION;
    }

    @Override // org.slf4j.spi.SLF4JServiceProvider
    public void initialize() {
        LoggerContext loggerContext = new LoggerContext();
        this.defaultLoggerContext = loggerContext;
        loggerContext.setName(CoreConstants.DEFAULT_CONTEXT_NAME);
        initializeLoggerContext();
        this.defaultLoggerContext.start();
        this.markerFactory = new BasicMarkerFactory();
        this.mdcAdapter = new LogbackMDCAdapter();
    }
}
