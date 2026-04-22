package javax.jmdns.impl.tasks.resolver;

import java.io.IOException;
import java.util.Iterator;
import javax.jmdns.impl.DNSCache;
import javax.jmdns.impl.DNSEntry;
import javax.jmdns.impl.DNSOutgoing;
import javax.jmdns.impl.DNSQuestion;
import javax.jmdns.impl.DNSRecord;
import javax.jmdns.impl.JmDNSImpl;
import javax.jmdns.impl.ServiceInfoImpl;
import javax.jmdns.impl.constants.DNSRecordClass;
import javax.jmdns.impl.constants.DNSRecordType;

/* JADX INFO: loaded from: classes.dex */
public class ServiceInfoResolver extends DNSResolverTask {
    private final ServiceInfoImpl _info;

    public ServiceInfoResolver(JmDNSImpl jmDNSImpl, ServiceInfoImpl serviceInfoImpl) {
        super(jmDNSImpl);
        this._info = serviceInfoImpl;
        serviceInfoImpl.setDns(getDns());
        getDns().addListener(serviceInfoImpl, DNSQuestion.newQuestion(serviceInfoImpl.getQualifiedName(), DNSRecordType.TYPE_ANY, DNSRecordClass.CLASS_IN, false));
    }

    @Override // javax.jmdns.impl.tasks.DNSTask
    public String getName() {
        StringBuilder sb = new StringBuilder();
        sb.append("ServiceInfoResolver(");
        sb.append(getDns() != null ? getDns().getName() : "");
        sb.append(")");
        return sb.toString();
    }

    @Override // java.util.TimerTask
    public boolean cancel() {
        boolean zCancel = super.cancel();
        if (!this._info.isPersistent()) {
            getDns().removeListener(this._info);
        }
        return zCancel;
    }

    @Override // javax.jmdns.impl.tasks.resolver.DNSResolverTask
    protected DNSOutgoing addAnswers(DNSOutgoing dNSOutgoing) throws IOException {
        if (!this._info.hasData()) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            DNSCache cache = getDns().getCache();
            String qualifiedName = this._info.getQualifiedName();
            DNSRecordType dNSRecordType = DNSRecordType.TYPE_SRV;
            DNSRecordClass dNSRecordClass = DNSRecordClass.CLASS_IN;
            dNSOutgoing = addAnswer(addAnswer(dNSOutgoing, (DNSRecord) cache.getDNSEntry(qualifiedName, dNSRecordType, dNSRecordClass), jCurrentTimeMillis), (DNSRecord) getDns().getCache().getDNSEntry(this._info.getQualifiedName(), DNSRecordType.TYPE_TXT, dNSRecordClass), jCurrentTimeMillis);
            if (this._info.getServer().length() > 0) {
                Iterator it = getDns().getCache().getDNSEntryList(this._info.getServer(), DNSRecordType.TYPE_A, dNSRecordClass).iterator();
                while (it.hasNext()) {
                    dNSOutgoing = addAnswer(dNSOutgoing, (DNSRecord) ((DNSEntry) it.next()), jCurrentTimeMillis);
                }
                Iterator it2 = getDns().getCache().getDNSEntryList(this._info.getServer(), DNSRecordType.TYPE_AAAA, DNSRecordClass.CLASS_IN).iterator();
                while (it2.hasNext()) {
                    dNSOutgoing = addAnswer(dNSOutgoing, (DNSRecord) ((DNSEntry) it2.next()), jCurrentTimeMillis);
                }
            }
        }
        return dNSOutgoing;
    }

    @Override // javax.jmdns.impl.tasks.resolver.DNSResolverTask
    protected DNSOutgoing addQuestions(DNSOutgoing dNSOutgoing) throws IOException {
        if (this._info.hasData()) {
            return dNSOutgoing;
        }
        String qualifiedName = this._info.getQualifiedName();
        DNSRecordType dNSRecordType = DNSRecordType.TYPE_SRV;
        DNSRecordClass dNSRecordClass = DNSRecordClass.CLASS_IN;
        DNSOutgoing dNSOutgoingAddQuestion = addQuestion(addQuestion(dNSOutgoing, DNSQuestion.newQuestion(qualifiedName, dNSRecordType, dNSRecordClass, false)), DNSQuestion.newQuestion(this._info.getQualifiedName(), DNSRecordType.TYPE_TXT, dNSRecordClass, false));
        return this._info.getServer().length() > 0 ? addQuestion(addQuestion(dNSOutgoingAddQuestion, DNSQuestion.newQuestion(this._info.getServer(), DNSRecordType.TYPE_A, dNSRecordClass, false)), DNSQuestion.newQuestion(this._info.getServer(), DNSRecordType.TYPE_AAAA, dNSRecordClass, false)) : dNSOutgoingAddQuestion;
    }

    @Override // javax.jmdns.impl.tasks.resolver.DNSResolverTask
    protected String description() {
        StringBuilder sb = new StringBuilder();
        sb.append("querying service info: ");
        ServiceInfoImpl serviceInfoImpl = this._info;
        sb.append(serviceInfoImpl != null ? serviceInfoImpl.getQualifiedName() : "null");
        return sb.toString();
    }
}
