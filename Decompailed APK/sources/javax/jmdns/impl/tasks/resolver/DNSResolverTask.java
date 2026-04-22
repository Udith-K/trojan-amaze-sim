package javax.jmdns.impl.tasks.resolver;

import java.util.Timer;
import javax.jmdns.impl.DNSOutgoing;
import javax.jmdns.impl.JmDNSImpl;
import javax.jmdns.impl.tasks.DNSTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public abstract class DNSResolverTask extends DNSTask {
    private static Logger logger = LoggerFactory.getLogger(DNSResolverTask.class.getName());
    protected int _count;

    protected abstract DNSOutgoing addAnswers(DNSOutgoing dNSOutgoing);

    protected abstract DNSOutgoing addQuestions(DNSOutgoing dNSOutgoing);

    protected abstract String description();

    public DNSResolverTask(JmDNSImpl jmDNSImpl) {
        super(jmDNSImpl);
        this._count = 0;
    }

    @Override // javax.jmdns.impl.tasks.DNSTask
    public String toString() {
        return super.toString() + " count: " + this._count;
    }

    public void start(Timer timer) {
        if (getDns().isCanceling() || getDns().isCanceled()) {
            return;
        }
        timer.schedule(this, 225L, 225L);
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        try {
            if (!getDns().isCanceling() && !getDns().isCanceled()) {
                int i = this._count;
                this._count = i + 1;
                if (i < 3) {
                    logger.debug("{}.run() JmDNS {}", getName(), description());
                    DNSOutgoing dNSOutgoingAddQuestions = addQuestions(new DNSOutgoing(0));
                    if (getDns().isAnnounced()) {
                        dNSOutgoingAddQuestions = addAnswers(dNSOutgoingAddQuestions);
                    }
                    if (dNSOutgoingAddQuestions.isEmpty()) {
                        return;
                    }
                    getDns().send(dNSOutgoingAddQuestions);
                    return;
                }
                cancel();
                return;
            }
            cancel();
        } catch (Throwable th) {
            logger.warn(getName() + ".run() exception ", th);
            getDns().recover();
        }
    }
}
