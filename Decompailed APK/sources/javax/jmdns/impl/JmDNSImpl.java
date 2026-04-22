package javax.jmdns.impl;

import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.jmdns.impl.DNSRecord;
import javax.jmdns.impl.DNSTaskStarter;
import javax.jmdns.impl.ListenerStatus;
import javax.jmdns.impl.NameRegister;
import javax.jmdns.impl.constants.DNSConstants;
import javax.jmdns.impl.constants.DNSRecordClass;
import javax.jmdns.impl.constants.DNSRecordType;
import javax.jmdns.impl.constants.DNSState;
import javax.jmdns.impl.tasks.DNSTask;
import javax.jmdns.impl.util.NamedThreadFactory;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class JmDNSImpl extends JmDNS implements DNSStatefulObject, DNSTaskStarter {
    private final DNSCache _cache;
    private volatile InetAddress _group;
    private Thread _incomingListener;
    private long _lastThrottleIncrement;
    private final List _listeners;
    private HostInfo _localHost;
    private final String _name;
    private DNSIncoming _plannedAnswer;
    private final ConcurrentMap _serviceCollectors;
    final ConcurrentMap _serviceListeners;
    private final ConcurrentMap _serviceTypes;
    private final ConcurrentMap _services;
    protected Thread _shutdown;
    private volatile MulticastSocket _socket;
    private int _throttle;
    private final Set _typeListeners;
    private static Logger logger = LoggerFactory.getLogger(JmDNSImpl.class.getName());
    private static final Random _random = new Random();
    private final ExecutorService _executor = Executors.newSingleThreadExecutor(new NamedThreadFactory("JmDNS"));
    private final ReentrantLock _ioLock = new ReentrantLock();
    private final Object _recoverLock = new Object();

    public enum Operation {
        Remove,
        Update,
        Add,
        RegisterServiceType,
        Noop
    }

    public JmDNS.Delegate getDelegate() {
        return null;
    }

    public JmDNSImpl getDns() {
        return this;
    }

    public static class ServiceTypeEntry extends AbstractMap implements Cloneable {
        private final Set _entrySet = new HashSet();
        private final String _type;

        private static class SubTypeEntry implements Map.Entry, Serializable, Cloneable {
            private final String _key;
            private final String _value;

            public SubTypeEntry clone() {
                return this;
            }

            public SubTypeEntry(String str) {
                str = str == null ? "" : str;
                this._value = str;
                this._key = str.toLowerCase();
            }

            @Override // java.util.Map.Entry
            public String getKey() {
                return this._key;
            }

            @Override // java.util.Map.Entry
            public String getValue() {
                return this._value;
            }

            @Override // java.util.Map.Entry
            public String setValue(String str) {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.Map.Entry
            public boolean equals(Object obj) {
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                return getKey().equals(entry.getKey()) && getValue().equals(entry.getValue());
            }

            @Override // java.util.Map.Entry
            public int hashCode() {
                String str = this._key;
                int iHashCode = str == null ? 0 : str.hashCode();
                String str2 = this._value;
                return iHashCode ^ (str2 != null ? str2.hashCode() : 0);
            }

            public String toString() {
                return this._key + "=" + this._value;
            }
        }

        public ServiceTypeEntry(String str) {
            this._type = str;
        }

        public String getType() {
            return this._type;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set entrySet() {
            return this._entrySet;
        }

        public boolean contains(String str) {
            return str != null && containsKey(str.toLowerCase());
        }

        public boolean add(String str) {
            if (str == null || contains(str)) {
                return false;
            }
            this._entrySet.add(new SubTypeEntry(str));
            return true;
        }

        @Override // java.util.AbstractMap
        public ServiceTypeEntry clone() {
            ServiceTypeEntry serviceTypeEntry = new ServiceTypeEntry(getType());
            Iterator it = entrySet().iterator();
            while (it.hasNext()) {
                serviceTypeEntry.add((String) ((Map.Entry) it.next()).getValue());
            }
            return serviceTypeEntry;
        }

        @Override // java.util.AbstractMap
        public String toString() {
            StringBuilder sb = new StringBuilder(200);
            if (isEmpty()) {
                sb.append("empty");
            } else {
                Iterator it = values().iterator();
                while (it.hasNext()) {
                    sb.append((String) it.next());
                    sb.append(", ");
                }
                sb.setLength(sb.length() - 2);
            }
            return sb.toString();
        }
    }

    public JmDNSImpl(InetAddress inetAddress, String str) throws IOException {
        logger.debug("JmDNS instance created");
        this._cache = new DNSCache(100);
        this._listeners = Collections.synchronizedList(new ArrayList());
        this._serviceListeners = new ConcurrentHashMap();
        this._typeListeners = Collections.synchronizedSet(new HashSet());
        this._serviceCollectors = new ConcurrentHashMap();
        this._services = new ConcurrentHashMap(20);
        this._serviceTypes = new ConcurrentHashMap(20);
        HostInfo hostInfoNewHostInfo = HostInfo.newHostInfo(inetAddress, this, str);
        this._localHost = hostInfoNewHostInfo;
        this._name = str == null ? hostInfoNewHostInfo.getName() : str;
        openMulticastSocket(getLocalHost());
        start(getServices().values());
        startReaper();
    }

    private void start(Collection collection) {
        if (this._incomingListener == null) {
            SocketListener socketListener = new SocketListener(this);
            this._incomingListener = socketListener;
            socketListener.start();
        }
        startProber();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            try {
                registerService(new ServiceInfoImpl((ServiceInfo) it.next()));
            } catch (Exception e) {
                logger.warn("start() Registration exception ", (Throwable) e);
            }
        }
    }

    private void openMulticastSocket(HostInfo hostInfo) throws IOException {
        if (this._group == null) {
            if (hostInfo.getInetAddress() instanceof Inet6Address) {
                this._group = InetAddress.getByName("FF02::FB");
            } else {
                this._group = InetAddress.getByName("224.0.0.251");
            }
        }
        if (this._socket != null) {
            closeMulticastSocket();
        }
        int i = DNSConstants.MDNS_PORT;
        this._socket = new MulticastSocket(i);
        if (hostInfo != null && hostInfo.getInterface() != null) {
            InetSocketAddress inetSocketAddress = new InetSocketAddress(this._group, i);
            this._socket.setNetworkInterface(hostInfo.getInterface());
            logger.trace("Trying to joinGroup({}, {})", inetSocketAddress, hostInfo.getInterface());
            this._socket.joinGroup(inetSocketAddress, hostInfo.getInterface());
        } else {
            logger.trace("Trying to joinGroup({})", this._group);
            this._socket.joinGroup(this._group);
        }
        this._socket.setTimeToLive(GF2Field.MASK);
    }

    private void closeMulticastSocket() {
        logger.debug("closeMulticastSocket()");
        if (this._socket != null) {
            try {
                try {
                    this._socket.leaveGroup(this._group);
                } catch (Exception e) {
                    logger.warn("closeMulticastSocket() Close socket exception ", (Throwable) e);
                }
            } catch (SocketException unused) {
            }
            this._socket.close();
            while (true) {
                Thread thread = this._incomingListener;
                if (thread == null || !thread.isAlive()) {
                    break;
                }
                synchronized (this) {
                    try {
                        Thread thread2 = this._incomingListener;
                        if (thread2 != null && thread2.isAlive()) {
                            logger.debug("closeMulticastSocket(): waiting for jmDNS monitor");
                            wait(1000L);
                        }
                    } catch (InterruptedException unused2) {
                    }
                }
                this._socket = null;
            }
            this._incomingListener = null;
            this._socket = null;
        }
    }

    @Override // javax.jmdns.impl.DNSStatefulObject
    public boolean advanceState(DNSTask dNSTask) {
        return this._localHost.advanceState(dNSTask);
    }

    public boolean revertState() {
        return this._localHost.revertState();
    }

    public boolean cancelState() {
        return this._localHost.cancelState();
    }

    public boolean recoverState() {
        return this._localHost.recoverState();
    }

    public void associateWithTask(DNSTask dNSTask, DNSState dNSState) {
        this._localHost.associateWithTask(dNSTask, dNSState);
    }

    public void removeAssociationWithTask(DNSTask dNSTask) {
        this._localHost.removeAssociationWithTask(dNSTask);
    }

    public boolean isAssociatedWithTask(DNSTask dNSTask, DNSState dNSState) {
        return this._localHost.isAssociatedWithTask(dNSTask, dNSState);
    }

    public boolean isProbing() {
        return this._localHost.isProbing();
    }

    public boolean isAnnounced() {
        return this._localHost.isAnnounced();
    }

    public boolean isCanceling() {
        return this._localHost.isCanceling();
    }

    public boolean isCanceled() {
        return this._localHost.isCanceled();
    }

    public boolean isClosing() {
        return this._localHost.isClosing();
    }

    public boolean isClosed() {
        return this._localHost.isClosed();
    }

    public boolean waitForCanceled(long j) {
        return this._localHost.waitForCanceled(j);
    }

    public DNSCache getCache() {
        return this._cache;
    }

    public String getName() {
        return this._name;
    }

    public HostInfo getLocalHost() {
        return this._localHost;
    }

    public InetAddress getInetAddress() {
        return this._localHost.getInetAddress();
    }

    ServiceInfoImpl resolveServiceInfo(String str, String str2, String str3, boolean z) {
        cleanCache();
        String lowerCase = str.toLowerCase();
        registerServiceType(str);
        if (this._serviceCollectors.putIfAbsent(lowerCase, new ServiceCollector(str)) == null) {
            addServiceListener(lowerCase, (ServiceListener) this._serviceCollectors.get(lowerCase), true);
        }
        ServiceInfoImpl serviceInfoFromCache = getServiceInfoFromCache(str, str2, str3, z);
        startServiceInfoResolver(serviceInfoFromCache);
        return serviceInfoFromCache;
    }

    ServiceInfoImpl getServiceInfoFromCache(String str, String str2, String str3, boolean z) {
        ServiceInfoImpl serviceInfoImpl;
        byte[] bArr;
        String server;
        ServiceInfo serviceInfo;
        ServiceInfo serviceInfo2;
        ServiceInfo serviceInfo3;
        ServiceInfo serviceInfo4;
        ServiceInfoImpl serviceInfoImpl2 = new ServiceInfoImpl(str, str2, str3, 0, 0, 0, z, (byte[]) null);
        DNSCache cache = getCache();
        DNSRecordClass dNSRecordClass = DNSRecordClass.CLASS_ANY;
        DNSEntry dNSEntry = cache.getDNSEntry(new DNSRecord.Pointer(str, dNSRecordClass, false, 0, serviceInfoImpl2.getQualifiedName()));
        if (!(dNSEntry instanceof DNSRecord) || (serviceInfoImpl = (ServiceInfoImpl) ((DNSRecord) dNSEntry).getServiceInfo(z)) == null) {
            return serviceInfoImpl2;
        }
        Map qualifiedNameMap = serviceInfoImpl.getQualifiedNameMap();
        DNSEntry dNSEntry2 = getCache().getDNSEntry(serviceInfoImpl2.getQualifiedName(), DNSRecordType.TYPE_SRV, dNSRecordClass);
        if ((dNSEntry2 instanceof DNSRecord) && (serviceInfo4 = ((DNSRecord) dNSEntry2).getServiceInfo(z)) != null) {
            ServiceInfoImpl serviceInfoImpl3 = new ServiceInfoImpl(qualifiedNameMap, serviceInfo4.getPort(), serviceInfo4.getWeight(), serviceInfo4.getPriority(), z, (byte[]) null);
            byte[] textBytes = serviceInfo4.getTextBytes();
            server = serviceInfo4.getServer();
            bArr = textBytes;
            serviceInfoImpl = serviceInfoImpl3;
        } else {
            bArr = null;
            server = "";
        }
        Iterator it = getCache().getDNSEntryList(server, DNSRecordType.TYPE_A, dNSRecordClass).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DNSEntry dNSEntry3 = (DNSEntry) it.next();
            if ((dNSEntry3 instanceof DNSRecord) && (serviceInfo3 = ((DNSRecord) dNSEntry3).getServiceInfo(z)) != null) {
                for (Inet4Address inet4Address : serviceInfo3.getInet4Addresses()) {
                    serviceInfoImpl.addAddress(inet4Address);
                }
                serviceInfoImpl._setText(serviceInfo3.getTextBytes());
            }
        }
        for (DNSEntry dNSEntry4 : getCache().getDNSEntryList(server, DNSRecordType.TYPE_AAAA, DNSRecordClass.CLASS_ANY)) {
            if ((dNSEntry4 instanceof DNSRecord) && (serviceInfo2 = ((DNSRecord) dNSEntry4).getServiceInfo(z)) != null) {
                for (Inet6Address inet6Address : serviceInfo2.getInet6Addresses()) {
                    serviceInfoImpl.addAddress(inet6Address);
                }
                serviceInfoImpl._setText(serviceInfo2.getTextBytes());
            }
        }
        DNSEntry dNSEntry5 = getCache().getDNSEntry(serviceInfoImpl.getQualifiedName(), DNSRecordType.TYPE_TXT, DNSRecordClass.CLASS_ANY);
        if ((dNSEntry5 instanceof DNSRecord) && (serviceInfo = ((DNSRecord) dNSEntry5).getServiceInfo(z)) != null) {
            serviceInfoImpl._setText(serviceInfo.getTextBytes());
        }
        if (serviceInfoImpl.getTextBytes().length == 0) {
            serviceInfoImpl._setText(bArr);
        }
        return serviceInfoImpl.hasData() ? serviceInfoImpl : serviceInfoImpl2;
    }

    void handleServiceResolved(final ServiceEvent serviceEvent) {
        ArrayList<ListenerStatus.ServiceListenerStatus> arrayList;
        List list = (List) this._serviceListeners.get(serviceEvent.getType().toLowerCase());
        if (list == null || list.isEmpty() || serviceEvent.getInfo() == null || !serviceEvent.getInfo().hasData()) {
            return;
        }
        synchronized (list) {
            arrayList = new ArrayList(list);
        }
        for (final ListenerStatus.ServiceListenerStatus serviceListenerStatus : arrayList) {
            this._executor.submit(new Runnable() { // from class: javax.jmdns.impl.JmDNSImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    serviceListenerStatus.serviceResolved(serviceEvent);
                }
            });
        }
    }

    @Override // javax.jmdns.JmDNS
    public void addServiceListener(String str, ServiceListener serviceListener) {
        addServiceListener(str, serviceListener, false);
    }

    private void addServiceListener(String str, ServiceListener serviceListener, boolean z) {
        ListenerStatus.ServiceListenerStatus serviceListenerStatus = new ListenerStatus.ServiceListenerStatus(serviceListener, z);
        String lowerCase = str.toLowerCase();
        List list = (List) this._serviceListeners.get(lowerCase);
        if (list == null) {
            if (this._serviceListeners.putIfAbsent(lowerCase, new LinkedList()) == null && this._serviceCollectors.putIfAbsent(lowerCase, new ServiceCollector(str)) == null) {
                addServiceListener(lowerCase, (ServiceListener) this._serviceCollectors.get(lowerCase), true);
            }
            list = (List) this._serviceListeners.get(lowerCase);
        }
        if (list != null) {
            synchronized (list) {
                try {
                    if (!list.contains(serviceListenerStatus)) {
                        list.add(serviceListenerStatus);
                    }
                } finally {
                }
            }
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = getCache().allValues().iterator();
        while (it.hasNext()) {
            DNSRecord dNSRecord = (DNSRecord) ((DNSEntry) it.next());
            if (dNSRecord.getRecordType() == DNSRecordType.TYPE_SRV && dNSRecord.getKey().endsWith(lowerCase)) {
                arrayList.add(new ServiceEventImpl(this, dNSRecord.getType(), toUnqualifiedName(dNSRecord.getType(), dNSRecord.getName()), dNSRecord.getServiceInfo()));
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            serviceListenerStatus.serviceAdded((ServiceEvent) it2.next());
        }
        startServiceResolver(str);
    }

    public void removeServiceListener(String str, ServiceListener serviceListener) {
        String lowerCase = str.toLowerCase();
        List list = (List) this._serviceListeners.get(lowerCase);
        if (list != null) {
            synchronized (list) {
                try {
                    list.remove(new ListenerStatus.ServiceListenerStatus(serviceListener, false));
                    if (list.isEmpty()) {
                        this._serviceListeners.remove(lowerCase, list);
                    }
                } finally {
                }
            }
        }
    }

    @Override // javax.jmdns.JmDNS
    public void registerService(ServiceInfo serviceInfo) {
        if (isClosing() || isClosed()) {
            throw new IllegalStateException("This DNS is closed.");
        }
        ServiceInfoImpl serviceInfoImpl = (ServiceInfoImpl) serviceInfo;
        if (serviceInfoImpl.getDns() != null) {
            if (serviceInfoImpl.getDns() != this) {
                throw new IllegalStateException("A service information can only be registered with a single instamce of JmDNS.");
            }
            if (this._services.get(serviceInfoImpl.getKey()) != null) {
                throw new IllegalStateException("A service information can only be registered once.");
            }
        }
        serviceInfoImpl.setDns(this);
        registerServiceType(serviceInfoImpl.getTypeWithSubtype());
        serviceInfoImpl.recoverState();
        serviceInfoImpl.setServer(this._localHost.getName());
        serviceInfoImpl.addAddress(this._localHost.getInet4Address());
        serviceInfoImpl.addAddress(this._localHost.getInet6Address());
        makeServiceNameUnique(serviceInfoImpl);
        while (this._services.putIfAbsent(serviceInfoImpl.getKey(), serviceInfoImpl) != null) {
            makeServiceNameUnique(serviceInfoImpl);
        }
        startProber();
        logger.debug("registerService() JmDNS registered service as {}", serviceInfoImpl);
    }

    @Override // javax.jmdns.JmDNS
    public void unregisterService(ServiceInfo serviceInfo) {
        ServiceInfoImpl serviceInfoImpl = (ServiceInfoImpl) this._services.get(serviceInfo.getKey());
        if (serviceInfoImpl != null) {
            serviceInfoImpl.cancelState();
            startCanceler();
            serviceInfoImpl.waitForCanceled(5000L);
            this._services.remove(serviceInfoImpl.getKey(), serviceInfoImpl);
            logger.debug("unregisterService() JmDNS {} unregistered service as {}", getName(), serviceInfoImpl);
            return;
        }
        logger.warn("{} removing unregistered service info: {}", getName(), serviceInfo.getKey());
    }

    @Override // javax.jmdns.JmDNS
    public void unregisterAllServices() {
        logger.debug("unregisterAllServices()");
        for (ServiceInfo serviceInfo : this._services.values()) {
            if (serviceInfo != null) {
                logger.debug("Cancelling service info: {}", serviceInfo);
                ((ServiceInfoImpl) serviceInfo).cancelState();
            }
        }
        startCanceler();
        for (Map.Entry entry : this._services.entrySet()) {
            ServiceInfo serviceInfo2 = (ServiceInfo) entry.getValue();
            if (serviceInfo2 != null) {
                String str = (String) entry.getKey();
                logger.debug("Wait for service info cancel: {}", serviceInfo2);
                ((ServiceInfoImpl) serviceInfo2).waitForCanceled(5000L);
                this._services.remove(str, serviceInfo2);
            }
        }
    }

    public boolean registerServiceType(String str) {
        boolean z;
        ServiceTypeEntry serviceTypeEntry;
        boolean z2 = true;
        Map mapDecodeQualifiedNameMapForType = ServiceInfoImpl.decodeQualifiedNameMapForType(str);
        String str2 = (String) mapDecodeQualifiedNameMapForType.get(ServiceInfo.Fields.Domain);
        String str3 = (String) mapDecodeQualifiedNameMapForType.get(ServiceInfo.Fields.Protocol);
        String str4 = (String) mapDecodeQualifiedNameMapForType.get(ServiceInfo.Fields.Application);
        String str5 = (String) mapDecodeQualifiedNameMapForType.get(ServiceInfo.Fields.Subtype);
        StringBuilder sb = new StringBuilder();
        sb.append(str4.length() > 0 ? "_" + str4 + "." : "");
        sb.append(str3.length() > 0 ? "_" + str3 + "." : "");
        sb.append(str2);
        sb.append(".");
        String string = sb.toString();
        String lowerCase = string.toLowerCase();
        logger.debug("{} registering service type: {} as: {}{}{}", getName(), str, string, str5.length() > 0 ? " subtype: " : "", str5.length() > 0 ? str5 : "");
        ListenerStatus.ServiceTypeListenerStatus serviceTypeListenerStatus = null;
        if (this._serviceTypes.containsKey(lowerCase) || str4.toLowerCase().equals("dns-sd") || str2.toLowerCase().endsWith("in-addr.arpa") || str2.toLowerCase().endsWith("ip6.arpa")) {
            z = false;
        } else {
            z = this._serviceTypes.putIfAbsent(lowerCase, new ServiceTypeEntry(string)) == null;
            if (z) {
                Set set = this._typeListeners;
                ListenerStatus.ServiceTypeListenerStatus[] serviceTypeListenerStatusArr = (ListenerStatus.ServiceTypeListenerStatus[]) set.toArray(new ListenerStatus.ServiceTypeListenerStatus[set.size()]);
                ServiceEventImpl serviceEventImpl = new ServiceEventImpl(this, string, "", null);
                for (ListenerStatus.ServiceTypeListenerStatus serviceTypeListenerStatus2 : serviceTypeListenerStatusArr) {
                    this._executor.submit(new Runnable(serviceTypeListenerStatus, serviceEventImpl) { // from class: javax.jmdns.impl.JmDNSImpl.2
                        final /* synthetic */ ServiceEvent val$event;

                        {
                            this.val$event = serviceEventImpl;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            throw null;
                        }
                    });
                }
            }
        }
        if (str5.length() <= 0 || (serviceTypeEntry = (ServiceTypeEntry) this._serviceTypes.get(lowerCase)) == null || serviceTypeEntry.contains(str5)) {
            return z;
        }
        synchronized (serviceTypeEntry) {
            try {
                if (serviceTypeEntry.contains(str5)) {
                    z2 = z;
                } else {
                    serviceTypeEntry.add(str5);
                    Set set2 = this._typeListeners;
                    ListenerStatus.ServiceTypeListenerStatus[] serviceTypeListenerStatusArr2 = (ListenerStatus.ServiceTypeListenerStatus[]) set2.toArray(new ListenerStatus.ServiceTypeListenerStatus[set2.size()]);
                    ServiceEventImpl serviceEventImpl2 = new ServiceEventImpl(this, "_" + str5 + "._sub." + string, "", null);
                    for (ListenerStatus.ServiceTypeListenerStatus serviceTypeListenerStatus3 : serviceTypeListenerStatusArr2) {
                        this._executor.submit(new Runnable(serviceTypeListenerStatus, serviceEventImpl2) { // from class: javax.jmdns.impl.JmDNSImpl.3
                            final /* synthetic */ ServiceEvent val$event;

                            {
                                this.val$event = serviceEventImpl2;
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                throw null;
                            }
                        });
                    }
                }
            } finally {
            }
        }
        return z2;
    }

    private boolean makeServiceNameUnique(ServiceInfoImpl serviceInfoImpl) {
        boolean z;
        String key = serviceInfoImpl.getKey();
        long jCurrentTimeMillis = System.currentTimeMillis();
        do {
            for (DNSEntry dNSEntry : getCache().getDNSEntryList(serviceInfoImpl.getKey())) {
                if (DNSRecordType.TYPE_SRV.equals(dNSEntry.getRecordType()) && !dNSEntry.isExpired(jCurrentTimeMillis)) {
                    DNSRecord.Service service = (DNSRecord.Service) dNSEntry;
                    if (service.getPort() != serviceInfoImpl.getPort() || !service.getServer().equals(this._localHost.getName())) {
                        logger.debug("makeServiceNameUnique() JmDNS.makeServiceNameUnique srv collision:{} s.server={} {} equals:{}", dNSEntry, service.getServer(), this._localHost.getName(), Boolean.valueOf(service.getServer().equals(this._localHost.getName())));
                        serviceInfoImpl.setName(NameRegister.Factory.getRegistry().incrementName(this._localHost.getInetAddress(), serviceInfoImpl.getName(), NameRegister.NameType.SERVICE));
                        z = true;
                        break;
                    }
                }
            }
            z = false;
            ServiceInfo serviceInfo = (ServiceInfo) this._services.get(serviceInfoImpl.getKey());
            if (serviceInfo != null && serviceInfo != serviceInfoImpl) {
                serviceInfoImpl.setName(NameRegister.Factory.getRegistry().incrementName(this._localHost.getInetAddress(), serviceInfoImpl.getName(), NameRegister.NameType.SERVICE));
                z = true;
            }
        } while (z);
        return !key.equals(serviceInfoImpl.getKey());
    }

    public void addListener(DNSListener dNSListener, DNSQuestion dNSQuestion) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        this._listeners.add(dNSListener);
        if (dNSQuestion != null) {
            for (DNSEntry dNSEntry : getCache().getDNSEntryList(dNSQuestion.getName().toLowerCase())) {
                if (dNSQuestion.answeredBy(dNSEntry) && !dNSEntry.isExpired(jCurrentTimeMillis)) {
                    dNSListener.updateRecord(getCache(), jCurrentTimeMillis, dNSEntry);
                }
            }
        }
    }

    public void removeListener(DNSListener dNSListener) {
        this._listeners.remove(dNSListener);
    }

    public void renewServiceCollector(String str) {
        if (this._serviceCollectors.containsKey(str.toLowerCase())) {
            startServiceResolver(str);
        }
    }

    public void updateRecord(long j, DNSRecord dNSRecord, Operation operation) {
        ArrayList arrayList;
        List<ListenerStatus.ServiceListenerStatus> listEmptyList;
        synchronized (this._listeners) {
            arrayList = new ArrayList(this._listeners);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((DNSListener) it.next()).updateRecord(getCache(), j, dNSRecord);
        }
        if (DNSRecordType.TYPE_PTR.equals(dNSRecord.getRecordType()) || (DNSRecordType.TYPE_SRV.equals(dNSRecord.getRecordType()) && Operation.Remove.equals(operation))) {
            final ServiceEvent serviceEvent = dNSRecord.getServiceEvent(this);
            if (serviceEvent.getInfo() == null || !serviceEvent.getInfo().hasData()) {
                ServiceInfoImpl serviceInfoFromCache = getServiceInfoFromCache(serviceEvent.getType(), serviceEvent.getName(), "", false);
                if (serviceInfoFromCache.hasData()) {
                    serviceEvent = new ServiceEventImpl(this, serviceEvent.getType(), serviceEvent.getName(), serviceInfoFromCache);
                }
            }
            List list = (List) this._serviceListeners.get(serviceEvent.getType().toLowerCase());
            if (list != null) {
                synchronized (list) {
                    listEmptyList = new ArrayList(list);
                }
            } else {
                listEmptyList = Collections.emptyList();
            }
            logger.trace("{}.updating record for event: {} list {} operation: {}", getName(), serviceEvent, listEmptyList, operation);
            if (listEmptyList.isEmpty()) {
                return;
            }
            int i = AnonymousClass7.$SwitchMap$javax$jmdns$impl$JmDNSImpl$Operation[operation.ordinal()];
            if (i == 1) {
                for (final ListenerStatus.ServiceListenerStatus serviceListenerStatus : listEmptyList) {
                    if (serviceListenerStatus.isSynchronous()) {
                        serviceListenerStatus.serviceAdded(serviceEvent);
                    } else {
                        this._executor.submit(new Runnable() { // from class: javax.jmdns.impl.JmDNSImpl.4
                            @Override // java.lang.Runnable
                            public void run() {
                                serviceListenerStatus.serviceAdded(serviceEvent);
                            }
                        });
                    }
                }
                return;
            }
            if (i != 2) {
                return;
            }
            for (final ListenerStatus.ServiceListenerStatus serviceListenerStatus2 : listEmptyList) {
                if (serviceListenerStatus2.isSynchronous()) {
                    serviceListenerStatus2.serviceRemoved(serviceEvent);
                } else {
                    this._executor.submit(new Runnable() { // from class: javax.jmdns.impl.JmDNSImpl.5
                        @Override // java.lang.Runnable
                        public void run() {
                            serviceListenerStatus2.serviceRemoved(serviceEvent);
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: renamed from: javax.jmdns.impl.JmDNSImpl$7, reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$javax$jmdns$impl$JmDNSImpl$Operation;

        static {
            int[] iArr = new int[Operation.values().length];
            $SwitchMap$javax$jmdns$impl$JmDNSImpl$Operation = iArr;
            try {
                iArr[Operation.Add.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$javax$jmdns$impl$JmDNSImpl$Operation[Operation.Remove.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    void handleRecord(DNSRecord dNSRecord, long j) {
        Operation operation = Operation.Noop;
        boolean zIsExpired = dNSRecord.isExpired(j);
        logger.debug("{} handle response: {}", getName(), dNSRecord);
        if (!dNSRecord.isServicesDiscoveryMetaQuery() && !dNSRecord.isDomainDiscoveryQuery()) {
            boolean zIsUnique = dNSRecord.isUnique();
            DNSRecord dNSRecord2 = (DNSRecord) getCache().getDNSEntry(dNSRecord);
            logger.debug("{} handle response cached record: {}", getName(), dNSRecord2);
            if (zIsUnique) {
                for (DNSEntry dNSEntry : getCache().getDNSEntryList(dNSRecord.getKey())) {
                    if (dNSRecord.getRecordType().equals(dNSEntry.getRecordType()) && dNSRecord.getRecordClass().equals(dNSEntry.getRecordClass())) {
                        DNSRecord dNSRecord3 = (DNSRecord) dNSEntry;
                        if (isOlderThanOneSecond(dNSRecord3, j)) {
                            logger.trace("setWillExpireSoon() on: {}", dNSEntry);
                            dNSRecord3.setWillExpireSoon(j);
                        }
                    }
                }
            }
            if (dNSRecord2 != null) {
                if (zIsExpired) {
                    if (dNSRecord.getTTL() == 0) {
                        operation = Operation.Noop;
                        logger.trace("Record is expired - setWillExpireSoon() on:\n\t{}", dNSRecord2);
                        dNSRecord2.setWillExpireSoon(j);
                    } else {
                        operation = Operation.Remove;
                        logger.trace("Record is expired - removeDNSEntry() on:\n\t{}", dNSRecord2);
                        getCache().removeDNSEntry(dNSRecord2);
                    }
                } else if (!dNSRecord.sameValue(dNSRecord2) || (!dNSRecord.sameSubtype(dNSRecord2) && dNSRecord.getSubtype().length() > 0)) {
                    if (dNSRecord.isSingleValued()) {
                        operation = Operation.Update;
                        logger.trace("Record (singleValued) has changed - replaceDNSEntry() on:\n\t{}\n\t{}", dNSRecord, dNSRecord2);
                        getCache().replaceDNSEntry(dNSRecord, dNSRecord2);
                    } else {
                        operation = Operation.Add;
                        logger.trace("Record (multiValue) has changed - addDNSEntry on:\n\t{}", dNSRecord);
                        getCache().addDNSEntry(dNSRecord);
                    }
                } else {
                    dNSRecord2.resetTTL(dNSRecord);
                    dNSRecord = dNSRecord2;
                }
            } else if (!zIsExpired) {
                operation = Operation.Add;
                logger.trace("Record not cached - addDNSEntry on:\n\t{}", dNSRecord);
                getCache().addDNSEntry(dNSRecord);
            }
        }
        if (dNSRecord.getRecordType() == DNSRecordType.TYPE_PTR) {
            if (dNSRecord.isServicesDiscoveryMetaQuery()) {
                if (zIsExpired) {
                    return;
                }
                registerServiceType(((DNSRecord.Pointer) dNSRecord).getAlias());
                return;
            } else if (registerServiceType(dNSRecord.getName()) && operation == Operation.Noop) {
                operation = Operation.RegisterServiceType;
            }
        }
        if (operation != Operation.Noop) {
            updateRecord(j, dNSRecord, operation);
        }
    }

    private boolean isOlderThanOneSecond(DNSRecord dNSRecord, long j) {
        return dNSRecord.getCreated() < j - 1000;
    }

    void handleResponse(DNSIncoming dNSIncoming) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        boolean zHandleResponse = false;
        boolean zHandleResponse2 = false;
        for (DNSRecord dNSRecord : aRecordsLast(dNSIncoming.getAllAnswers())) {
            handleRecord(dNSRecord, jCurrentTimeMillis);
            if (DNSRecordType.TYPE_A.equals(dNSRecord.getRecordType()) || DNSRecordType.TYPE_AAAA.equals(dNSRecord.getRecordType())) {
                zHandleResponse |= dNSRecord.handleResponse(this);
            } else {
                zHandleResponse2 |= dNSRecord.handleResponse(this);
            }
        }
        if (zHandleResponse || zHandleResponse2) {
            startProber();
        }
    }

    private List aRecordsLast(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        ArrayList arrayList2 = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            DNSRecord dNSRecord = (DNSRecord) it.next();
            if (dNSRecord.getRecordType().equals(DNSRecordType.TYPE_A) || dNSRecord.getRecordType().equals(DNSRecordType.TYPE_AAAA)) {
                arrayList2.add(dNSRecord);
            } else {
                arrayList.add(dNSRecord);
            }
        }
        arrayList.addAll(arrayList2);
        return arrayList;
    }

    void handleQuery(DNSIncoming dNSIncoming, InetAddress inetAddress, int i) {
        logger.debug("{} handle query: {}", getName(), dNSIncoming);
        long jCurrentTimeMillis = System.currentTimeMillis() + 120;
        Iterator it = dNSIncoming.getAllAnswers().iterator();
        boolean zHandleQuery = false;
        while (it.hasNext()) {
            zHandleQuery |= ((DNSRecord) it.next()).handleQuery(this, jCurrentTimeMillis);
        }
        ioLock();
        try {
            DNSIncoming dNSIncoming2 = this._plannedAnswer;
            if (dNSIncoming2 != null) {
                dNSIncoming2.append(dNSIncoming);
            } else {
                DNSIncoming dNSIncomingM2635clone = dNSIncoming.clone();
                if (dNSIncoming.isTruncated()) {
                    this._plannedAnswer = dNSIncomingM2635clone;
                }
                startResponder(dNSIncomingM2635clone, inetAddress, i);
            }
            ioUnlock();
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            Iterator it2 = dNSIncoming.getAnswers().iterator();
            while (it2.hasNext()) {
                handleRecord((DNSRecord) it2.next(), jCurrentTimeMillis2);
            }
            if (zHandleQuery) {
                startProber();
            }
        } catch (Throwable th) {
            ioUnlock();
            throw th;
        }
    }

    public void respondToQuery(DNSIncoming dNSIncoming) {
        ioLock();
        try {
            if (this._plannedAnswer == dNSIncoming) {
                this._plannedAnswer = null;
            }
        } finally {
            ioUnlock();
        }
    }

    public void send(DNSOutgoing dNSOutgoing) throws IOException {
        InetAddress address;
        int port;
        if (dNSOutgoing.isEmpty()) {
            return;
        }
        if (dNSOutgoing.getDestination() != null) {
            address = dNSOutgoing.getDestination().getAddress();
            port = dNSOutgoing.getDestination().getPort();
        } else {
            address = this._group;
            port = DNSConstants.MDNS_PORT;
        }
        byte[] bArrData = dNSOutgoing.data();
        DatagramPacket datagramPacket = new DatagramPacket(bArrData, bArrData.length, address, port);
        if (logger.isTraceEnabled()) {
            try {
                DNSIncoming dNSIncoming = new DNSIncoming(datagramPacket);
                if (logger.isTraceEnabled()) {
                    logger.trace("send({}) JmDNS out:{}", getName(), dNSIncoming.print(true));
                }
            } catch (IOException e) {
                logger.debug(getClass().toString(), ".send(" + getName() + ") - JmDNS can not parse what it sends!!!", e);
            }
        }
        MulticastSocket multicastSocket = this._socket;
        if (multicastSocket == null || multicastSocket.isClosed()) {
            return;
        }
        multicastSocket.send(datagramPacket);
    }

    @Override // javax.jmdns.impl.DNSTaskStarter
    public void purgeTimer() {
        DNSTaskStarter.Factory.getInstance().getStarter(getDns()).purgeTimer();
    }

    @Override // javax.jmdns.impl.DNSTaskStarter
    public void purgeStateTimer() {
        DNSTaskStarter.Factory.getInstance().getStarter(getDns()).purgeStateTimer();
    }

    @Override // javax.jmdns.impl.DNSTaskStarter
    public void cancelTimer() {
        DNSTaskStarter.Factory.getInstance().getStarter(getDns()).cancelTimer();
    }

    @Override // javax.jmdns.impl.DNSTaskStarter
    public void cancelStateTimer() {
        DNSTaskStarter.Factory.getInstance().getStarter(getDns()).cancelStateTimer();
    }

    @Override // javax.jmdns.impl.DNSTaskStarter
    public void startProber() {
        DNSTaskStarter.Factory.getInstance().getStarter(getDns()).startProber();
    }

    @Override // javax.jmdns.impl.DNSTaskStarter
    public void startAnnouncer() {
        DNSTaskStarter.Factory.getInstance().getStarter(getDns()).startAnnouncer();
    }

    @Override // javax.jmdns.impl.DNSTaskStarter
    public void startRenewer() {
        DNSTaskStarter.Factory.getInstance().getStarter(getDns()).startRenewer();
    }

    @Override // javax.jmdns.impl.DNSTaskStarter
    public void startCanceler() {
        DNSTaskStarter.Factory.getInstance().getStarter(getDns()).startCanceler();
    }

    @Override // javax.jmdns.impl.DNSTaskStarter
    public void startReaper() {
        DNSTaskStarter.Factory.getInstance().getStarter(getDns()).startReaper();
    }

    @Override // javax.jmdns.impl.DNSTaskStarter
    public void startServiceInfoResolver(ServiceInfoImpl serviceInfoImpl) {
        DNSTaskStarter.Factory.getInstance().getStarter(getDns()).startServiceInfoResolver(serviceInfoImpl);
    }

    @Override // javax.jmdns.impl.DNSTaskStarter
    public void startServiceResolver(String str) {
        DNSTaskStarter.Factory.getInstance().getStarter(getDns()).startServiceResolver(str);
    }

    @Override // javax.jmdns.impl.DNSTaskStarter
    public void startResponder(DNSIncoming dNSIncoming, InetAddress inetAddress, int i) {
        DNSTaskStarter.Factory.getInstance().getStarter(getDns()).startResponder(dNSIncoming, inetAddress, i);
    }

    public void recover() {
        logger.debug("{}.recover()", getName());
        if (isClosing() || isClosed() || isCanceling() || isCanceled()) {
            return;
        }
        synchronized (this._recoverLock) {
            try {
                if (cancelState()) {
                    String str = getName() + ".recover()";
                    logger.debug("{} thread {}", str, Thread.currentThread().getName());
                    new Thread(str) { // from class: javax.jmdns.impl.JmDNSImpl.6
                        @Override // java.lang.Thread, java.lang.Runnable
                        public void run() {
                            JmDNSImpl.this.__recover();
                        }
                    }.start();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    void __recover() {
        logger.debug("{}.recover() Cleanning up", getName());
        logger.warn("RECOVERING");
        purgeTimer();
        ArrayList arrayList = new ArrayList(getServices().values());
        unregisterAllServices();
        disposeServiceCollectors();
        waitForCanceled(5000L);
        purgeStateTimer();
        closeMulticastSocket();
        getCache().clear();
        logger.debug("{}.recover() All is clean", getName());
        if (isCanceled()) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((ServiceInfoImpl) ((ServiceInfo) it.next())).recoverState();
            }
            recoverState();
            try {
                openMulticastSocket(getLocalHost());
                start(arrayList);
            } catch (Exception e) {
                logger.warn(getName() + ".recover() Start services exception ", (Throwable) e);
            }
            logger.warn("{}.recover() We are back!", getName());
            return;
        }
        logger.warn("{}.recover() Could not recover we are Down!", getName());
        getDelegate();
    }

    public void cleanCache() {
        getCache().logCachedContent();
        long jCurrentTimeMillis = System.currentTimeMillis();
        HashSet hashSet = new HashSet();
        for (DNSEntry dNSEntry : getCache().allValues()) {
            try {
                DNSRecord dNSRecord = (DNSRecord) dNSEntry;
                if (dNSRecord.isExpired(jCurrentTimeMillis)) {
                    updateRecord(jCurrentTimeMillis, dNSRecord, Operation.Remove);
                    logger.trace("Removing DNSEntry from cache: {}", dNSEntry);
                    getCache().removeDNSEntry(dNSRecord);
                } else if (dNSRecord.isStaleAndShouldBeRefreshed(jCurrentTimeMillis)) {
                    dNSRecord.incrementRefreshPercentage();
                    String lowerCase = dNSRecord.getServiceInfo().getType().toLowerCase();
                    if (hashSet.add(lowerCase)) {
                        renewServiceCollector(lowerCase);
                    }
                }
            } catch (Exception e) {
                logger.warn(getName() + ".Error while reaping records: " + dNSEntry, (Throwable) e);
                logger.warn(toString());
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (isClosing()) {
            return;
        }
        logger.debug("Cancelling JmDNS: {}", this);
        if (cancelState()) {
            logger.debug("Canceling the timer");
            cancelTimer();
            unregisterAllServices();
            disposeServiceCollectors();
            logger.debug("Wait for JmDNS cancel: {}", this);
            waitForCanceled(5000L);
            logger.debug("Canceling the state timer");
            cancelStateTimer();
            this._executor.shutdown();
            closeMulticastSocket();
            if (this._shutdown != null) {
                Runtime.getRuntime().removeShutdownHook(this._shutdown);
            }
            DNSTaskStarter.Factory.getInstance().disposeStarter(getDns());
            logger.debug("JmDNS closed.");
        }
        advanceState(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v10, types: [java.util.AbstractMap, javax.jmdns.impl.JmDNSImpl$ServiceTypeEntry] */
    /* JADX WARN: Type inference failed for: r3v12, types: [java.lang.String] */
    public String toString() {
        StringBuilder sb = new StringBuilder(2048);
        sb.append("\n");
        sb.append("\t---- Local Host -----");
        sb.append("\n\t");
        sb.append(this._localHost);
        sb.append("\n\t---- Services -----");
        for (Map.Entry entry : this._services.entrySet()) {
            sb.append("\n\t\tService: ");
            sb.append((String) entry.getKey());
            sb.append(": ");
            sb.append(entry.getValue());
        }
        sb.append("\n");
        sb.append("\t---- Types ----");
        for (Object obj : this._serviceTypes.values()) {
            sb.append("\n\t\tType: ");
            sb.append(obj.getType());
            sb.append(": ");
            if (obj.isEmpty()) {
                obj = "no subtypes";
            }
            sb.append(obj);
        }
        sb.append("\n");
        sb.append(this._cache.toString());
        sb.append("\n");
        sb.append("\t---- Service Collectors ----");
        for (Map.Entry entry2 : this._serviceCollectors.entrySet()) {
            sb.append("\n\t\tService Collector: ");
            sb.append((String) entry2.getKey());
            sb.append(": ");
            sb.append(entry2.getValue());
        }
        sb.append("\n");
        sb.append("\t---- Service Listeners ----");
        for (Map.Entry entry3 : this._serviceListeners.entrySet()) {
            sb.append("\n\t\tService Listener: ");
            sb.append((String) entry3.getKey());
            sb.append(": ");
            sb.append(entry3.getValue());
        }
        return sb.toString();
    }

    private void disposeServiceCollectors() {
        logger.debug("disposeServiceCollectors()");
        for (Map.Entry entry : this._serviceCollectors.entrySet()) {
            ServiceCollector serviceCollector = (ServiceCollector) entry.getValue();
            if (serviceCollector != null) {
                String str = (String) entry.getKey();
                removeServiceListener(str, serviceCollector);
                this._serviceCollectors.remove(str, serviceCollector);
            }
        }
    }

    private static class ServiceCollector implements ServiceListener {
        private final String _type;
        private final ConcurrentMap _infos = new ConcurrentHashMap();
        private final ConcurrentMap _events = new ConcurrentHashMap();
        private volatile boolean _needToWaitForInfos = true;

        public ServiceCollector(String str) {
            this._type = str;
        }

        @Override // javax.jmdns.ServiceListener
        public void serviceAdded(ServiceEvent serviceEvent) {
            synchronized (this) {
                try {
                    ServiceInfo info2 = serviceEvent.getInfo();
                    if (info2 != null && info2.hasData()) {
                        this._infos.put(serviceEvent.getName(), info2);
                    } else {
                        ServiceInfoImpl serviceInfoImplResolveServiceInfo = ((JmDNSImpl) serviceEvent.getDNS()).resolveServiceInfo(serviceEvent.getType(), serviceEvent.getName(), info2 != null ? info2.getSubtype() : "", true);
                        if (serviceInfoImplResolveServiceInfo != null) {
                            this._infos.put(serviceEvent.getName(), serviceInfoImplResolveServiceInfo);
                        } else {
                            this._events.put(serviceEvent.getName(), serviceEvent);
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // javax.jmdns.ServiceListener
        public void serviceRemoved(ServiceEvent serviceEvent) {
            synchronized (this) {
                this._infos.remove(serviceEvent.getName());
                this._events.remove(serviceEvent.getName());
            }
        }

        @Override // javax.jmdns.ServiceListener
        public void serviceResolved(ServiceEvent serviceEvent) {
            synchronized (this) {
                this._infos.put(serviceEvent.getName(), serviceEvent.getInfo());
                this._events.remove(serviceEvent.getName());
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("\n\tType: ");
            sb.append(this._type);
            if (this._infos.isEmpty()) {
                sb.append("\n\tNo services collected.");
            } else {
                sb.append("\n\tServices");
                for (Map.Entry entry : this._infos.entrySet()) {
                    sb.append("\n\t\tService: ");
                    sb.append((String) entry.getKey());
                    sb.append(": ");
                    sb.append(entry.getValue());
                }
            }
            if (this._events.isEmpty()) {
                sb.append("\n\tNo event queued.");
            } else {
                sb.append("\n\tEvents");
                for (Map.Entry entry2 : this._events.entrySet()) {
                    sb.append("\n\t\tEvent: ");
                    sb.append((String) entry2.getKey());
                    sb.append(": ");
                    sb.append(entry2.getValue());
                }
            }
            return sb.toString();
        }
    }

    static String toUnqualifiedName(String str, String str2) {
        String lowerCase = str.toLowerCase();
        String lowerCase2 = str2.toLowerCase();
        return (!lowerCase2.endsWith(lowerCase) || lowerCase2.equals(lowerCase)) ? str2 : str2.substring(0, (str2.length() - str.length()) - 1);
    }

    public Map getServices() {
        return this._services;
    }

    public void setLastThrottleIncrement(long j) {
        this._lastThrottleIncrement = j;
    }

    public long getLastThrottleIncrement() {
        return this._lastThrottleIncrement;
    }

    public void setThrottle(int i) {
        this._throttle = i;
    }

    public int getThrottle() {
        return this._throttle;
    }

    public static Random getRandom() {
        return _random;
    }

    public void ioLock() {
        this._ioLock.lock();
    }

    public void ioUnlock() {
        this._ioLock.unlock();
    }

    public Map getServiceTypes() {
        return this._serviceTypes;
    }

    public MulticastSocket getSocket() {
        return this._socket;
    }

    public InetAddress getGroup() {
        return this._group;
    }
}
