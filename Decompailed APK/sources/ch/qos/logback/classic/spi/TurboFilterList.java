package ch.qos.logback.classic.spi;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes.dex */
public final class TurboFilterList extends CopyOnWriteArrayList {
    private static final long serialVersionUID = 1;

    public FilterReply getTurboFilterChainDecision(List<Marker> list, Logger logger, Level level, String str, Object[] objArr, Throwable th) {
        if (size() == 1) {
            try {
                return ((TurboFilter) get(0)).decide(list, logger, level, str, objArr, th);
            } catch (IndexOutOfBoundsException unused) {
                return FilterReply.NEUTRAL;
            }
        }
        for (Object obj : toArray()) {
            FilterReply filterReplyDecide = ((TurboFilter) obj).decide(list, logger, level, str, objArr, th);
            if (filterReplyDecide == FilterReply.DENY || filterReplyDecide == FilterReply.ACCEPT) {
                return filterReplyDecide;
            }
        }
        return FilterReply.NEUTRAL;
    }
}
