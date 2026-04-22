package org.slf4j.helpers;

import java.util.Map;
import org.slf4j.spi.MDCAdapter;

/* JADX INFO: loaded from: classes2.dex */
public class NOPMDCAdapter implements MDCAdapter {
    @Override // org.slf4j.spi.MDCAdapter
    public Map getCopyOfContextMap() {
        return null;
    }
}
