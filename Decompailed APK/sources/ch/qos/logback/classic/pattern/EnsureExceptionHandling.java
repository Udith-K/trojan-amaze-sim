package ch.qos.logback.classic.pattern;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.pattern.Converter;
import ch.qos.logback.core.pattern.ConverterUtil;
import ch.qos.logback.core.pattern.PostCompileProcessor;

/* JADX INFO: loaded from: classes.dex */
public class EnsureExceptionHandling implements PostCompileProcessor {
    public boolean chainHandlesThrowable(Converter converter) {
        while (converter != null) {
            if (converter instanceof ThrowableHandlingConverter) {
                return true;
            }
            converter = converter.getNext();
        }
        return false;
    }

    @Override // ch.qos.logback.core.pattern.PostCompileProcessor
    public void process(Context context, Converter converter) {
        if (converter == null) {
            throw new IllegalArgumentException("cannot process empty chain");
        }
        if (chainHandlesThrowable(converter)) {
            return;
        }
        ConverterUtil.findTail(converter).setNext(((LoggerContext) context).isPackagingDataEnabled() ? new ExtendedThrowableProxyConverter() : new ThrowableProxyConverter());
    }
}
