package javax.jmdns.impl.tasks.state;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.jmdns.ServiceInfo;
import javax.jmdns.impl.DNSOutgoing;
import javax.jmdns.impl.DNSStatefulObject;
import javax.jmdns.impl.JmDNSImpl;
import javax.jmdns.impl.ServiceInfoImpl;
import javax.jmdns.impl.constants.DNSConstants;
import javax.jmdns.impl.constants.DNSState;
import javax.jmdns.impl.tasks.DNSTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public abstract class DNSStateTask extends DNSTask {
    private DNSState _taskState;
    private final int _ttl;
    static Logger logger = LoggerFactory.getLogger(DNSStateTask.class.getName());
    private static int _defaultTTL = DNSConstants.DNS_TTL;

    protected abstract void advanceTask();

    protected abstract DNSOutgoing buildOutgoingForDNS(DNSOutgoing dNSOutgoing);

    protected abstract DNSOutgoing buildOutgoingForInfo(ServiceInfoImpl serviceInfoImpl, DNSOutgoing dNSOutgoing);

    protected abstract boolean checkRunCondition();

    protected abstract DNSOutgoing createOugoing();

    public abstract String getTaskDescription();

    protected abstract void recoverTask(Throwable th);

    public static int defaultTTL() {
        return _defaultTTL;
    }

    public DNSStateTask(JmDNSImpl jmDNSImpl, int i) {
        super(jmDNSImpl);
        this._taskState = null;
        this._ttl = i;
    }

    public int getTTL() {
        return this._ttl;
    }

    protected void associate(DNSState dNSState) {
        synchronized (getDns()) {
            getDns().associateWithTask(this, dNSState);
        }
        Iterator it = getDns().getServices().values().iterator();
        while (it.hasNext()) {
            ((ServiceInfoImpl) ((ServiceInfo) it.next())).associateWithTask(this, dNSState);
        }
    }

    protected void removeAssociation() {
        synchronized (getDns()) {
            getDns().removeAssociationWithTask(this);
        }
        Iterator it = getDns().getServices().values().iterator();
        while (it.hasNext()) {
            ((ServiceInfoImpl) ((ServiceInfo) it.next())).removeAssociationWithTask(this);
        }
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        DNSOutgoing dNSOutgoingCreateOugoing = createOugoing();
        try {
            if (!checkRunCondition()) {
                cancel();
                return;
            }
            ArrayList arrayList = new ArrayList();
            synchronized (getDns()) {
                try {
                    if (getDns().isAssociatedWithTask(this, getTaskState())) {
                        logger.debug("{}.run() JmDNS {} {}", getName(), getTaskDescription(), getDns().getName());
                        arrayList.add(getDns());
                        dNSOutgoingCreateOugoing = buildOutgoingForDNS(dNSOutgoingCreateOugoing);
                    }
                } finally {
                }
            }
            Iterator it = getDns().getServices().values().iterator();
            while (it.hasNext()) {
                ServiceInfoImpl serviceInfoImpl = (ServiceInfoImpl) ((ServiceInfo) it.next());
                synchronized (serviceInfoImpl) {
                    try {
                        if (serviceInfoImpl.isAssociatedWithTask(this, getTaskState())) {
                            logger.debug("{}.run() JmDNS {} {}", getName(), getTaskDescription(), serviceInfoImpl.getQualifiedName());
                            arrayList.add(serviceInfoImpl);
                            dNSOutgoingCreateOugoing = buildOutgoingForInfo(serviceInfoImpl, dNSOutgoingCreateOugoing);
                        }
                    } finally {
                    }
                }
            }
            if (!dNSOutgoingCreateOugoing.isEmpty()) {
                logger.debug("{}.run() JmDNS {} #{}", getName(), getTaskDescription(), getTaskState());
                getDns().send(dNSOutgoingCreateOugoing);
                advanceObjectsState(arrayList);
            } else {
                advanceObjectsState(arrayList);
                cancel();
                return;
            }
        } catch (Throwable th) {
            logger.warn(getName() + ".run() exception ", th);
            recoverTask(th);
        }
        advanceTask();
    }

    protected void advanceObjectsState(List list) {
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                DNSStatefulObject dNSStatefulObject = (DNSStatefulObject) it.next();
                synchronized (dNSStatefulObject) {
                    dNSStatefulObject.advanceState(this);
                }
            }
        }
    }

    protected DNSState getTaskState() {
        return this._taskState;
    }

    protected void setTaskState(DNSState dNSState) {
        this._taskState = dNSState;
    }
}
