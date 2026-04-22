package ch.qos.logback.core.pattern;

import ch.qos.logback.core.Context;

/* JADX INFO: loaded from: classes.dex */
public interface PostCompileProcessor {
    void process(Context context, Converter converter);
}
