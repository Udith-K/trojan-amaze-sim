package ch.qos.logback.core.spi;

import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.util.COWArrayList;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class FilterAttachableImpl implements FilterAttachable {
    COWArrayList filterList = new COWArrayList(new Filter[0]);

    @Override // ch.qos.logback.core.spi.FilterAttachable
    public void addFilter(Filter filter) {
        this.filterList.add(filter);
    }

    @Override // ch.qos.logback.core.spi.FilterAttachable
    public void clearAllFilters() {
        this.filterList.clear();
    }

    @Override // ch.qos.logback.core.spi.FilterAttachable
    public List<Filter> getCopyOfAttachedFiltersList() {
        return new ArrayList(this.filterList);
    }

    @Override // ch.qos.logback.core.spi.FilterAttachable
    public FilterReply getFilterChainDecision(Object obj) {
        for (Filter filter : (Filter[]) this.filterList.asTypedArray()) {
            FilterReply filterReplyDecide = filter.decide(obj);
            if (filterReplyDecide == FilterReply.DENY || filterReplyDecide == FilterReply.ACCEPT) {
                return filterReplyDecide;
            }
        }
        return FilterReply.NEUTRAL;
    }
}
