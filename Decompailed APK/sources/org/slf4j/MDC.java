package org.slf4j;

import org.slf4j.helpers.NOPMDCAdapter;
import org.slf4j.helpers.Reporter;
import org.slf4j.spi.MDCAdapter;
import org.slf4j.spi.SLF4JServiceProvider;

/* JADX INFO: loaded from: classes2.dex */
public abstract class MDC {
    static MDCAdapter mdcAdapter;

    static {
        SLF4JServiceProvider provider = LoggerFactory.getProvider();
        if (provider != null) {
            mdcAdapter = provider.getMDCAdapter();
            return;
        }
        Reporter.error("Failed to find provider.");
        Reporter.error("Defaulting to no-operation MDCAdapter implementation.");
        mdcAdapter = new NOPMDCAdapter();
    }

    public static MDCAdapter getMDCAdapter() {
        return mdcAdapter;
    }
}
