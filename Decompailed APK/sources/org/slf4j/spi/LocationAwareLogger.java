package org.slf4j.spi;

import org.slf4j.Logger;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes2.dex */
public interface LocationAwareLogger extends Logger {
    void log(Marker marker, String str, int i, String str2, Object[] objArr, Throwable th);
}
