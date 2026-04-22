package ch.qos.logback.core.spi;

import ch.qos.logback.core.filter.Filter;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface FilterAttachable {
    void addFilter(Filter filter);

    void clearAllFilters();

    List<Filter> getCopyOfAttachedFiltersList();

    FilterReply getFilterChainDecision(Object obj);
}
