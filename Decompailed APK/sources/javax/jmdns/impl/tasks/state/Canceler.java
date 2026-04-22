package javax.jmdns.impl.tasks.state;

import java.io.IOException;
import java.util.Iterator;
import java.util.Timer;
import javax.jmdns.impl.DNSIncoming;
import javax.jmdns.impl.DNSOutgoing;
import javax.jmdns.impl.DNSRecord;
import javax.jmdns.impl.JmDNSImpl;
import javax.jmdns.impl.ServiceInfoImpl;
import javax.jmdns.impl.constants.DNSRecordClass;
import javax.jmdns.impl.constants.DNSState;

/* JADX INFO: loaded from: classes.dex */
public class Canceler extends DNSStateTask {
    @Override // javax.jmdns.impl.tasks.state.DNSStateTask
    protected boolean checkRunCondition() {
        return true;
    }

    public Canceler(JmDNSImpl jmDNSImpl) {
        super(jmDNSImpl, 0);
        DNSState dNSState = DNSState.CANCELING_1;
        setTaskState(dNSState);
        associate(dNSState);
    }

    @Override // javax.jmdns.impl.tasks.DNSTask
    public String getName() {
        StringBuilder sb = new StringBuilder();
        sb.append("Canceler(");
        sb.append(getDns() != null ? getDns().getName() : "");
        sb.append(")");
        return sb.toString();
    }

    @Override // javax.jmdns.impl.tasks.DNSTask
    public String toString() {
        return super.toString() + " state: " + getTaskState();
    }

    public void start(Timer timer) {
        timer.schedule(this, 0L, 1000L);
    }

    @Override // java.util.TimerTask
    public boolean cancel() {
        removeAssociation();
        return super.cancel();
    }

    @Override // javax.jmdns.impl.tasks.state.DNSStateTask
    public String getTaskDescription() {
        return "canceling";
    }

    @Override // javax.jmdns.impl.tasks.state.DNSStateTask
    protected DNSOutgoing createOugoing() {
        return new DNSOutgoing(33792);
    }

    @Override // javax.jmdns.impl.tasks.state.DNSStateTask
    protected DNSOutgoing buildOutgoingForDNS(DNSOutgoing dNSOutgoing) throws IOException {
        Iterator it = getDns().getLocalHost().answers(DNSRecordClass.CLASS_ANY, true, getTTL()).iterator();
        while (it.hasNext()) {
            dNSOutgoing = addAnswer(dNSOutgoing, (DNSIncoming) null, (DNSRecord) it.next());
        }
        return dNSOutgoing;
    }

    @Override // javax.jmdns.impl.tasks.state.DNSStateTask
    protected DNSOutgoing buildOutgoingForInfo(ServiceInfoImpl serviceInfoImpl, DNSOutgoing dNSOutgoing) throws IOException {
        Iterator it = serviceInfoImpl.answers(DNSRecordClass.CLASS_ANY, true, getTTL(), getDns().getLocalHost()).iterator();
        while (it.hasNext()) {
            dNSOutgoing = addAnswer(dNSOutgoing, (DNSIncoming) null, (DNSRecord) it.next());
        }
        return dNSOutgoing;
    }

    @Override // javax.jmdns.impl.tasks.state.DNSStateTask
    protected void recoverTask(Throwable th) {
        getDns().recover();
    }

    @Override // javax.jmdns.impl.tasks.state.DNSStateTask
    protected void advanceTask() {
        setTaskState(getTaskState().advance());
        if (getTaskState().isCanceling()) {
            return;
        }
        cancel();
    }
}
