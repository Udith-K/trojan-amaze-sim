package javax.jmdns.impl.tasks;

import ch.qos.logback.core.spi.AbstractComponentTracker;
import java.util.Timer;
import javax.jmdns.impl.JmDNSImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class RecordReaper extends DNSTask {
    static Logger logger = LoggerFactory.getLogger(RecordReaper.class.getName());

    public RecordReaper(JmDNSImpl jmDNSImpl) {
        super(jmDNSImpl);
    }

    @Override // javax.jmdns.impl.tasks.DNSTask
    public String getName() {
        StringBuilder sb = new StringBuilder();
        sb.append("RecordReaper(");
        sb.append(getDns() != null ? getDns().getName() : "");
        sb.append(")");
        return sb.toString();
    }

    public void start(Timer timer) {
        if (getDns().isCanceling() || getDns().isCanceled()) {
            return;
        }
        timer.schedule(this, AbstractComponentTracker.LINGERING_TIMEOUT, AbstractComponentTracker.LINGERING_TIMEOUT);
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        if (getDns().isCanceling() || getDns().isCanceled()) {
            return;
        }
        logger.trace("{}.run() JmDNS reaping cache", getName());
        getDns().cleanCache();
    }
}
