package ch.qos.logback.core.spi;

import java.util.Collection;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public interface ComponentTracker {
    public static final int DEFAULT_MAX_COMPONENTS = Integer.MAX_VALUE;
    public static final long DEFAULT_TIMEOUT = 1800000;

    Collection<Object> allComponents();

    Set<String> allKeys();

    void endOfLife(String str);

    Object find(String str);

    int getComponentCount();

    Object getOrCreate(String str, long j);

    void removeStaleComponents(long j);
}
