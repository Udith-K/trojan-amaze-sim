package javax.jmdns.impl;

import ch.qos.logback.core.CoreConstants;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.jmdns.ServiceInfo;
import javax.jmdns.impl.DNSRecord;
import javax.jmdns.impl.DNSStatefulObject;
import javax.jmdns.impl.constants.DNSRecordClass;
import javax.jmdns.impl.constants.DNSRecordType;
import javax.jmdns.impl.constants.DNSState;
import javax.jmdns.impl.tasks.DNSTask;
import javax.jmdns.impl.util.ByteWrangler;
import org.fdroid.fdroid.nearby.peers.BonjourPeer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class ServiceInfoImpl extends ServiceInfo implements DNSListener, DNSStatefulObject {
    private static Logger logger = LoggerFactory.getLogger(ServiceInfoImpl.class.getName());
    private String _application;
    private String _domain;
    private final Set _ipv4Addresses;
    private final Set _ipv6Addresses;
    private transient String _key;
    private String _name;
    private boolean _needTextAnnouncing;
    private boolean _persistent;
    private int _port;
    private int _priority;
    private Map _props;
    private String _protocol;
    private String _server;
    private final ServiceInfoState _state;
    private String _subtype;
    private byte[] _text;
    private int _weight;

    private static final class ServiceInfoState extends DNSStatefulObject.DefaultImplementation {
        private final ServiceInfoImpl _info;

        public ServiceInfoState(ServiceInfoImpl serviceInfoImpl) {
            this._info = serviceInfoImpl;
        }

        @Override // javax.jmdns.impl.DNSStatefulObject.DefaultImplementation
        protected void setTask(DNSTask dNSTask) {
            super.setTask(dNSTask);
            if (this._task == null && this._info.needTextAnnouncing()) {
                lock();
                try {
                    if (this._task == null && this._info.needTextAnnouncing()) {
                        if (this._state.isAnnounced()) {
                            setState(DNSState.ANNOUNCING_1);
                            if (getDns() != null) {
                                getDns().startAnnouncer();
                            }
                        }
                        this._info.setNeedTextAnnouncing(false);
                    }
                    unlock();
                } catch (Throwable th) {
                    unlock();
                    throw th;
                }
            }
        }

        @Override // javax.jmdns.impl.DNSStatefulObject.DefaultImplementation
        public void setDns(JmDNSImpl jmDNSImpl) {
            super.setDns(jmDNSImpl);
        }
    }

    public ServiceInfoImpl(String str, String str2, String str3, int i, int i2, int i3, boolean z, Map map) {
        this(decodeQualifiedNameMap(str, str2, str3), i, i2, i3, z, ByteWrangler.textFromProperties(map));
    }

    public ServiceInfoImpl(String str, String str2, String str3, int i, int i2, int i3, boolean z, byte[] bArr) {
        this(decodeQualifiedNameMap(str, str2, str3), i, i2, i3, z, bArr);
    }

    public ServiceInfoImpl(Map map, int i, int i2, int i3, boolean z, Map map2) {
        this(map, i, i2, i3, z, ByteWrangler.textFromProperties(map2));
    }

    ServiceInfoImpl(Map map, int i, int i2, int i3, boolean z, String str) {
        this(map, i, i2, i3, z, (byte[]) null);
        try {
            this._text = ByteWrangler.encodeText(str);
            this._server = str;
        } catch (IOException e) {
            throw new RuntimeException("Unexpected exception: " + e);
        }
    }

    ServiceInfoImpl(Map map, int i, int i2, int i3, boolean z, byte[] bArr) {
        Map mapCheckQualifiedNameMap = checkQualifiedNameMap(map);
        this._domain = (String) mapCheckQualifiedNameMap.get(ServiceInfo.Fields.Domain);
        this._protocol = (String) mapCheckQualifiedNameMap.get(ServiceInfo.Fields.Protocol);
        this._application = (String) mapCheckQualifiedNameMap.get(ServiceInfo.Fields.Application);
        this._name = (String) mapCheckQualifiedNameMap.get(ServiceInfo.Fields.Instance);
        this._subtype = (String) mapCheckQualifiedNameMap.get(ServiceInfo.Fields.Subtype);
        this._port = i;
        this._weight = i2;
        this._priority = i3;
        this._text = bArr;
        setNeedTextAnnouncing(false);
        this._state = new ServiceInfoState(this);
        this._persistent = z;
        this._ipv4Addresses = Collections.synchronizedSet(new LinkedHashSet());
        this._ipv6Addresses = Collections.synchronizedSet(new LinkedHashSet());
    }

    ServiceInfoImpl(ServiceInfo serviceInfo) {
        this._ipv4Addresses = Collections.synchronizedSet(new LinkedHashSet());
        this._ipv6Addresses = Collections.synchronizedSet(new LinkedHashSet());
        if (serviceInfo != null) {
            this._domain = serviceInfo.getDomain();
            this._protocol = serviceInfo.getProtocol();
            this._application = serviceInfo.getApplication();
            this._name = serviceInfo.getName();
            this._subtype = serviceInfo.getSubtype();
            this._port = serviceInfo.getPort();
            this._weight = serviceInfo.getWeight();
            this._priority = serviceInfo.getPriority();
            this._text = serviceInfo.getTextBytes();
            this._persistent = serviceInfo.isPersistent();
            for (Inet6Address inet6Address : serviceInfo.getInet6Addresses()) {
                this._ipv6Addresses.add(inet6Address);
            }
            for (Inet4Address inet4Address : serviceInfo.getInet4Addresses()) {
                this._ipv4Addresses.add(inet4Address);
            }
        }
        this._state = new ServiceInfoState(this);
    }

    public static Map decodeQualifiedNameMap(String str, String str2, String str3) {
        Map mapDecodeQualifiedNameMapForType = decodeQualifiedNameMapForType(str);
        mapDecodeQualifiedNameMapForType.put(ServiceInfo.Fields.Instance, str2);
        mapDecodeQualifiedNameMapForType.put(ServiceInfo.Fields.Subtype, str3);
        return checkQualifiedNameMap(mapDecodeQualifiedNameMapForType);
    }

    public static Map decodeQualifiedNameMapForType(String str) {
        String strRemoveSeparators;
        String strSubstring;
        String str2;
        int iIndexOf;
        String strSubstring2;
        String strSubstring3;
        String strSubstring4;
        String lowerCase = str.toLowerCase();
        String strRemoveSeparators2 = "";
        if (lowerCase.contains("in-addr.arpa") || lowerCase.contains("ip6.arpa")) {
            int iIndexOf2 = lowerCase.contains("in-addr.arpa") ? lowerCase.indexOf("in-addr.arpa") : lowerCase.indexOf("ip6.arpa");
            strRemoveSeparators = removeSeparators(str.substring(0, iIndexOf2));
            strSubstring = str.substring(iIndexOf2);
        } else if (!lowerCase.contains("_") && lowerCase.contains(".")) {
            int iIndexOf3 = lowerCase.indexOf(46);
            strRemoveSeparators = removeSeparators(str.substring(0, iIndexOf3));
            strSubstring = removeSeparators(str.substring(iIndexOf3));
        } else {
            if ((!lowerCase.startsWith("_") || lowerCase.startsWith("_services")) && (iIndexOf = lowerCase.indexOf("._")) > 0) {
                strSubstring2 = str.substring(0, iIndexOf);
                int i = iIndexOf + 1;
                if (i < lowerCase.length()) {
                    strSubstring3 = lowerCase.substring(i);
                    str = str.substring(i);
                } else {
                    strSubstring3 = lowerCase;
                }
            } else {
                strSubstring3 = lowerCase;
                strSubstring2 = "";
            }
            int iLastIndexOf = strSubstring3.lastIndexOf("._");
            if (iLastIndexOf > 0) {
                int i2 = iLastIndexOf + 2;
                strSubstring4 = str.substring(i2, strSubstring3.indexOf(46, i2));
            } else {
                strSubstring4 = "";
            }
            if (strSubstring4.length() > 0) {
                int iIndexOf4 = strSubstring3.indexOf("_" + strSubstring4.toLowerCase() + ".");
                int length = strSubstring4.length() + iIndexOf4 + 2;
                int length2 = strSubstring3.length() - (strSubstring3.endsWith(".") ? 1 : 0);
                String strSubstring5 = length2 > length ? str.substring(length, length2) : "";
                if (iIndexOf4 > 0) {
                    lowerCase = str.substring(0, iIndexOf4 - 1);
                    strSubstring = strSubstring5;
                } else {
                    strSubstring = strSubstring5;
                    lowerCase = "";
                }
            } else {
                strSubstring = "";
            }
            int iIndexOf5 = lowerCase.toLowerCase().indexOf("._sub");
            if (iIndexOf5 > 0) {
                strRemoveSeparators2 = removeSeparators(lowerCase.substring(0, iIndexOf5));
                lowerCase = lowerCase.substring(iIndexOf5 + 5);
            }
            strRemoveSeparators = strSubstring2;
            String str3 = strRemoveSeparators2;
            strRemoveSeparators2 = strSubstring4;
            str2 = str3;
            HashMap map = new HashMap(5);
            map.put(ServiceInfo.Fields.Domain, removeSeparators(strSubstring));
            map.put(ServiceInfo.Fields.Protocol, strRemoveSeparators2);
            map.put(ServiceInfo.Fields.Application, removeSeparators(lowerCase));
            map.put(ServiceInfo.Fields.Instance, strRemoveSeparators);
            map.put(ServiceInfo.Fields.Subtype, str2);
            return map;
        }
        lowerCase = "";
        str2 = lowerCase;
        HashMap map2 = new HashMap(5);
        map2.put(ServiceInfo.Fields.Domain, removeSeparators(strSubstring));
        map2.put(ServiceInfo.Fields.Protocol, strRemoveSeparators2);
        map2.put(ServiceInfo.Fields.Application, removeSeparators(lowerCase));
        map2.put(ServiceInfo.Fields.Instance, strRemoveSeparators);
        map2.put(ServiceInfo.Fields.Subtype, str2);
        return map2;
    }

    protected static Map checkQualifiedNameMap(Map map) {
        HashMap map2 = new HashMap(5);
        ServiceInfo.Fields fields = ServiceInfo.Fields.Domain;
        String str = "local";
        String str2 = map.containsKey(fields) ? (String) map.get(fields) : "local";
        if (str2 != null && str2.length() != 0) {
            str = str2;
        }
        map2.put(fields, removeSeparators(str));
        ServiceInfo.Fields fields2 = ServiceInfo.Fields.Protocol;
        String str3 = "tcp";
        String str4 = map.containsKey(fields2) ? (String) map.get(fields2) : "tcp";
        if (str4 != null && str4.length() != 0) {
            str3 = str4;
        }
        map2.put(fields2, removeSeparators(str3));
        ServiceInfo.Fields fields3 = ServiceInfo.Fields.Application;
        String str5 = "";
        String str6 = map.containsKey(fields3) ? (String) map.get(fields3) : "";
        if (str6 == null || str6.length() == 0) {
            str6 = "";
        }
        map2.put(fields3, removeSeparators(str6));
        ServiceInfo.Fields fields4 = ServiceInfo.Fields.Instance;
        String str7 = map.containsKey(fields4) ? (String) map.get(fields4) : "";
        if (str7 == null || str7.length() == 0) {
            str7 = "";
        }
        map2.put(fields4, removeSeparators(str7));
        ServiceInfo.Fields fields5 = ServiceInfo.Fields.Subtype;
        String str8 = map.containsKey(fields5) ? (String) map.get(fields5) : "";
        if (str8 != null && str8.length() != 0) {
            str5 = str8;
        }
        map2.put(fields5, removeSeparators(str5));
        return map2;
    }

    private static String removeSeparators(String str) {
        if (str == null) {
            return "";
        }
        String strTrim = str.trim();
        if (strTrim.startsWith(".")) {
            strTrim = strTrim.substring(1);
        }
        if (strTrim.startsWith("_")) {
            strTrim = strTrim.substring(1);
        }
        return strTrim.endsWith(".") ? strTrim.substring(0, strTrim.length() - 1) : strTrim;
    }

    @Override // javax.jmdns.ServiceInfo
    public String getType() {
        String str;
        String domain = getDomain();
        String protocol = getProtocol();
        String application = getApplication();
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (application.length() > 0) {
            str = "_" + application + ".";
        } else {
            str = "";
        }
        sb.append(str);
        if (protocol.length() > 0) {
            str2 = "_" + protocol + ".";
        }
        sb.append(str2);
        sb.append(domain);
        sb.append(".");
        return sb.toString();
    }

    public String getTypeWithSubtype() {
        String str;
        String subtype = getSubtype();
        StringBuilder sb = new StringBuilder();
        if (subtype.length() > 0) {
            str = "_" + subtype + "._sub.";
        } else {
            str = "";
        }
        sb.append(str);
        sb.append(getType());
        return sb.toString();
    }

    @Override // javax.jmdns.ServiceInfo
    public String getName() {
        String str = this._name;
        return str != null ? str : "";
    }

    @Override // javax.jmdns.ServiceInfo
    public String getKey() {
        if (this._key == null) {
            this._key = getQualifiedName().toLowerCase();
        }
        return this._key;
    }

    void setName(String str) {
        this._name = str;
        this._key = null;
    }

    @Override // javax.jmdns.ServiceInfo
    public String getQualifiedName() {
        String str;
        String str2;
        String domain = getDomain();
        String protocol = getProtocol();
        String application = getApplication();
        String name = getName();
        StringBuilder sb = new StringBuilder();
        String str3 = "";
        if (name.length() > 0) {
            str = name + ".";
        } else {
            str = "";
        }
        sb.append(str);
        if (application.length() > 0) {
            str2 = "_" + application + ".";
        } else {
            str2 = "";
        }
        sb.append(str2);
        if (protocol.length() > 0) {
            str3 = "_" + protocol + ".";
        }
        sb.append(str3);
        sb.append(domain);
        sb.append(".");
        return sb.toString();
    }

    @Override // javax.jmdns.ServiceInfo
    public String getServer() {
        String str = this._server;
        return str != null ? str : "";
    }

    void setServer(String str) {
        this._server = str;
    }

    void addAddress(Inet4Address inet4Address) {
        this._ipv4Addresses.add(inet4Address);
    }

    void addAddress(Inet6Address inet6Address) {
        this._ipv6Addresses.add(inet6Address);
    }

    @Override // javax.jmdns.ServiceInfo
    public InetAddress[] getInetAddresses() {
        ArrayList arrayList = new ArrayList(this._ipv4Addresses.size() + this._ipv6Addresses.size());
        arrayList.addAll(this._ipv4Addresses);
        arrayList.addAll(this._ipv6Addresses);
        return (InetAddress[]) arrayList.toArray(new InetAddress[arrayList.size()]);
    }

    @Override // javax.jmdns.ServiceInfo
    public Inet4Address[] getInet4Addresses() {
        Set set = this._ipv4Addresses;
        return (Inet4Address[]) set.toArray(new Inet4Address[set.size()]);
    }

    @Override // javax.jmdns.ServiceInfo
    public Inet6Address[] getInet6Addresses() {
        Set set = this._ipv6Addresses;
        return (Inet6Address[]) set.toArray(new Inet6Address[set.size()]);
    }

    @Override // javax.jmdns.ServiceInfo
    public int getPort() {
        return this._port;
    }

    @Override // javax.jmdns.ServiceInfo
    public int getPriority() {
        return this._priority;
    }

    @Override // javax.jmdns.ServiceInfo
    public int getWeight() {
        return this._weight;
    }

    @Override // javax.jmdns.ServiceInfo
    public byte[] getTextBytes() {
        byte[] bArr = this._text;
        return (bArr == null || bArr.length <= 0) ? ByteWrangler.EMPTY_TXT : bArr;
    }

    public String getURL() {
        return getURL("http");
    }

    public String getURL(String str) {
        String[] uRLs = getURLs(str);
        if (uRLs.length > 0) {
            return uRLs[0];
        }
        return str + "://null:" + getPort();
    }

    public String[] getURLs(String str) {
        InetAddress[] inetAddresses = getInetAddresses();
        ArrayList arrayList = new ArrayList(inetAddresses.length);
        for (InetAddress inetAddress : inetAddresses) {
            String hostAddress = inetAddress.getHostAddress();
            if (inetAddress instanceof Inet6Address) {
                hostAddress = "[" + hostAddress + "]";
            }
            String string = str + "://" + hostAddress + ":" + getPort();
            String propertyString = getPropertyString(BonjourPeer.PATH);
            if (propertyString != null) {
                if (propertyString.indexOf("://") >= 0) {
                    string = propertyString;
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(string);
                    if (!propertyString.startsWith("/")) {
                        propertyString = "/" + propertyString;
                    }
                    sb.append(propertyString);
                    string = sb.toString();
                }
            }
            arrayList.add(string);
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public synchronized byte[] getPropertyBytes(String str) {
        return (byte[]) getProperties().get(str);
    }

    @Override // javax.jmdns.ServiceInfo
    public synchronized String getPropertyString(String str) {
        byte[] bArr = (byte[]) getProperties().get(str);
        if (bArr == null) {
            return null;
        }
        if (bArr == ByteWrangler.NO_VALUE) {
            return "true";
        }
        return ByteWrangler.readUTF(bArr, 0, bArr.length);
    }

    @Override // javax.jmdns.ServiceInfo
    public String getApplication() {
        String str = this._application;
        return str != null ? str : "";
    }

    @Override // javax.jmdns.ServiceInfo
    public String getDomain() {
        String str = this._domain;
        return str != null ? str : "local";
    }

    @Override // javax.jmdns.ServiceInfo
    public String getProtocol() {
        String str = this._protocol;
        return str != null ? str : "tcp";
    }

    @Override // javax.jmdns.ServiceInfo
    public String getSubtype() {
        String str = this._subtype;
        return str != null ? str : "";
    }

    public Map getQualifiedNameMap() {
        HashMap map = new HashMap(5);
        map.put(ServiceInfo.Fields.Domain, getDomain());
        map.put(ServiceInfo.Fields.Protocol, getProtocol());
        map.put(ServiceInfo.Fields.Application, getApplication());
        map.put(ServiceInfo.Fields.Instance, getName());
        map.put(ServiceInfo.Fields.Subtype, getSubtype());
        return map;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0029 A[Catch: all -> 0x0018, TRY_LEAVE, TryCatch #1 {, blocks: (B:3:0x0001, B:5:0x0005, B:7:0x000b, B:8:0x0010, B:14:0x0022, B:13:0x001b, B:15:0x0024, B:18:0x0029), top: B:25:0x0001, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    synchronized java.util.Map getProperties() {
        /*
            r4 = this;
            monitor-enter(r4)
            java.util.Map r0 = r4._props     // Catch: java.lang.Throwable -> L18
            if (r0 != 0) goto L24
            byte[] r0 = r4.getTextBytes()     // Catch: java.lang.Throwable -> L18
            if (r0 == 0) goto L24
            java.util.Hashtable r0 = new java.util.Hashtable     // Catch: java.lang.Throwable -> L18
            r0.<init>()     // Catch: java.lang.Throwable -> L18
            byte[] r1 = r4.getTextBytes()     // Catch: java.lang.Throwable -> L18 java.lang.Exception -> L1a
            javax.jmdns.impl.util.ByteWrangler.readProperties(r0, r1)     // Catch: java.lang.Throwable -> L18 java.lang.Exception -> L1a
            goto L22
        L18:
            r0 = move-exception
            goto L2f
        L1a:
            r1 = move-exception
            org.slf4j.Logger r2 = javax.jmdns.impl.ServiceInfoImpl.logger     // Catch: java.lang.Throwable -> L18
            java.lang.String r3 = "Malformed TXT Field "
            r2.warn(r3, r1)     // Catch: java.lang.Throwable -> L18
        L22:
            r4._props = r0     // Catch: java.lang.Throwable -> L18
        L24:
            java.util.Map r0 = r4._props     // Catch: java.lang.Throwable -> L18
            if (r0 == 0) goto L29
            goto L2d
        L29:
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch: java.lang.Throwable -> L18
        L2d:
            monitor-exit(r4)
            return r0
        L2f:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L18
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.jmdns.impl.ServiceInfoImpl.getProperties():java.util.Map");
    }

    @Override // javax.jmdns.impl.DNSListener
    public void updateRecord(DNSCache dNSCache, long j, DNSEntry dNSEntry) {
        boolean zHandleUpdateRecord;
        if (!(dNSEntry instanceof DNSRecord)) {
            logger.trace("DNSEntry is not of type 'DNSRecord' but of type {}", dNSEntry == null ? "null" : dNSEntry.getClass().getSimpleName());
            return;
        }
        DNSRecord dNSRecord = (DNSRecord) dNSEntry;
        if (dNSRecord.isExpired(j)) {
            zHandleUpdateRecord = handleExpiredRecord(dNSRecord);
        } else {
            zHandleUpdateRecord = handleUpdateRecord(dNSCache, j, dNSRecord);
        }
        if (zHandleUpdateRecord) {
            JmDNSImpl dns = getDns();
            if (dns != null) {
                if (hasData()) {
                    dns.handleServiceResolved(new ServiceEventImpl(dns, getType(), getName(), clone()));
                }
            } else {
                logger.debug("JmDNS not available.");
            }
        }
        synchronized (this) {
            notifyAll();
        }
    }

    /* JADX INFO: renamed from: javax.jmdns.impl.ServiceInfoImpl$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$javax$jmdns$impl$constants$DNSRecordType;

        static {
            int[] iArr = new int[DNSRecordType.values().length];
            $SwitchMap$javax$jmdns$impl$constants$DNSRecordType = iArr;
            try {
                iArr[DNSRecordType.TYPE_A.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$javax$jmdns$impl$constants$DNSRecordType[DNSRecordType.TYPE_AAAA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$javax$jmdns$impl$constants$DNSRecordType[DNSRecordType.TYPE_SRV.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$javax$jmdns$impl$constants$DNSRecordType[DNSRecordType.TYPE_TXT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$javax$jmdns$impl$constants$DNSRecordType[DNSRecordType.TYPE_PTR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private boolean handleExpiredRecord(DNSRecord dNSRecord) {
        int i = AnonymousClass1.$SwitchMap$javax$jmdns$impl$constants$DNSRecordType[dNSRecord.getRecordType().ordinal()];
        if (i == 1 || i == 2) {
            if (!dNSRecord.getName().equalsIgnoreCase(getServer())) {
                return false;
            }
            DNSRecord.Address address = (DNSRecord.Address) dNSRecord;
            if (DNSRecordType.TYPE_A.equals(dNSRecord.getRecordType())) {
                Inet4Address inet4Address = (Inet4Address) address.getAddress();
                if (this._ipv4Addresses.remove(inet4Address)) {
                    logger.debug("Removed expired IPv4: {}", inet4Address);
                    return true;
                }
                logger.debug("Expired IPv4 not in this service: {}", inet4Address);
                return false;
            }
            Inet6Address inet6Address = (Inet6Address) address.getAddress();
            if (this._ipv6Addresses.remove(inet6Address)) {
                logger.debug("Removed expired IPv6: {}", inet6Address);
                return true;
            }
            logger.debug("Expired IPv6 not in this service: {}", inet6Address);
            return false;
        }
        logger.trace("Unhandled expired record: {}", dNSRecord);
        return false;
    }

    private boolean handleUpdateRecord(DNSCache dNSCache, long j, DNSRecord dNSRecord) {
        int i = AnonymousClass1.$SwitchMap$javax$jmdns$impl$constants$DNSRecordType[dNSRecord.getRecordType().ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i == 4) {
                        if (dNSRecord.getName().equalsIgnoreCase(getQualifiedName())) {
                            this._text = ((DNSRecord.Text) dNSRecord).getText();
                            this._props = null;
                            return true;
                        }
                    } else if (i == 5 && getSubtype().length() == 0 && dNSRecord.getSubtype().length() != 0) {
                        this._subtype = dNSRecord.getSubtype();
                        return true;
                    }
                } else if (dNSRecord.getName().equalsIgnoreCase(getQualifiedName())) {
                    DNSRecord.Service service = (DNSRecord.Service) dNSRecord;
                    String str = this._server;
                    boolean z = str == null || !str.equalsIgnoreCase(service.getServer());
                    this._server = service.getServer();
                    this._port = service.getPort();
                    this._weight = service.getWeight();
                    this._priority = service.getPriority();
                    if (!z) {
                        return true;
                    }
                    this._ipv4Addresses.clear();
                    this._ipv6Addresses.clear();
                    Iterator it = dNSCache.getDNSEntryList(this._server, DNSRecordType.TYPE_A, DNSRecordClass.CLASS_IN).iterator();
                    while (it.hasNext()) {
                        updateRecord(dNSCache, j, (DNSEntry) it.next());
                    }
                    Iterator it2 = dNSCache.getDNSEntryList(this._server, DNSRecordType.TYPE_AAAA, DNSRecordClass.CLASS_IN).iterator();
                    while (it2.hasNext()) {
                        updateRecord(dNSCache, j, (DNSEntry) it2.next());
                    }
                }
            } else if (dNSRecord.getName().equalsIgnoreCase(getServer())) {
                DNSRecord.Address address = (DNSRecord.Address) dNSRecord;
                if (address.getAddress() instanceof Inet6Address) {
                    if (this._ipv6Addresses.add((Inet6Address) address.getAddress())) {
                        return true;
                    }
                }
            }
        } else if (dNSRecord.getName().equalsIgnoreCase(getServer())) {
            DNSRecord.Address address2 = (DNSRecord.Address) dNSRecord;
            if (address2.getAddress() instanceof Inet4Address) {
                if (this._ipv4Addresses.add((Inet4Address) address2.getAddress())) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001e  */
    @Override // javax.jmdns.ServiceInfo
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean hasData() {
        /*
            r1 = this;
            monitor-enter(r1)
            java.lang.String r0 = r1.getServer()     // Catch: java.lang.Throwable -> L1c
            if (r0 == 0) goto L1e
            boolean r0 = r1.hasInetAddress()     // Catch: java.lang.Throwable -> L1c
            if (r0 == 0) goto L1e
            byte[] r0 = r1.getTextBytes()     // Catch: java.lang.Throwable -> L1c
            if (r0 == 0) goto L1e
            byte[] r0 = r1.getTextBytes()     // Catch: java.lang.Throwable -> L1c
            int r0 = r0.length     // Catch: java.lang.Throwable -> L1c
            if (r0 <= 0) goto L1e
            r0 = 1
            goto L1f
        L1c:
            r0 = move-exception
            goto L21
        L1e:
            r0 = 0
        L1f:
            monitor-exit(r1)
            return r0
        L21:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L1c
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.jmdns.impl.ServiceInfoImpl.hasData():boolean");
    }

    private final boolean hasInetAddress() {
        return this._ipv4Addresses.size() > 0 || this._ipv6Addresses.size() > 0;
    }

    @Override // javax.jmdns.impl.DNSStatefulObject
    public boolean advanceState(DNSTask dNSTask) {
        return this._state.advanceState(dNSTask);
    }

    public boolean revertState() {
        return this._state.revertState();
    }

    public boolean cancelState() {
        return this._state.cancelState();
    }

    public boolean recoverState() {
        return this._state.recoverState();
    }

    public void removeAssociationWithTask(DNSTask dNSTask) {
        this._state.removeAssociationWithTask(dNSTask);
    }

    public void associateWithTask(DNSTask dNSTask, DNSState dNSState) {
        this._state.associateWithTask(dNSTask, dNSState);
    }

    public boolean isAssociatedWithTask(DNSTask dNSTask, DNSState dNSState) {
        return this._state.isAssociatedWithTask(dNSTask, dNSState);
    }

    public boolean isProbing() {
        return this._state.isProbing();
    }

    public boolean isAnnouncing() {
        return this._state.isAnnouncing();
    }

    public boolean isAnnounced() {
        return this._state.isAnnounced();
    }

    public boolean waitForCanceled(long j) {
        return this._state.waitForCanceled(j);
    }

    public int hashCode() {
        return getQualifiedName().hashCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof ServiceInfoImpl) && getQualifiedName().equals(((ServiceInfoImpl) obj).getQualifiedName());
    }

    @Override // javax.jmdns.ServiceInfo
    public ServiceInfoImpl clone() {
        ServiceInfoImpl serviceInfoImpl = new ServiceInfoImpl(getQualifiedNameMap(), this._port, this._weight, this._priority, this._persistent, this._text);
        for (Inet6Address inet6Address : getInet6Addresses()) {
            serviceInfoImpl._ipv6Addresses.add(inet6Address);
        }
        for (Inet4Address inet4Address : getInet4Addresses()) {
            serviceInfoImpl._ipv4Addresses.add(inet4Address);
        }
        return serviceInfoImpl;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        sb.append(getClass().getSimpleName());
        sb.append('@');
        sb.append(System.identityHashCode(this));
        sb.append(" name: '");
        if (getName().length() > 0) {
            sb.append(getName());
            sb.append(CoreConstants.DOT);
        }
        sb.append(getTypeWithSubtype());
        sb.append("' address: '");
        InetAddress[] inetAddresses = getInetAddresses();
        if (inetAddresses.length > 0) {
            for (InetAddress inetAddress : inetAddresses) {
                sb.append(inetAddress);
                sb.append(CoreConstants.COLON_CHAR);
                sb.append(getPort());
                sb.append(' ');
            }
        } else {
            sb.append("(null):");
            sb.append(getPort());
        }
        sb.append("' status: '");
        sb.append(this._state.toString());
        sb.append(isPersistent() ? "' is persistent," : "',");
        if (hasData()) {
            sb.append(" has data");
        } else {
            sb.append(" has NO data");
        }
        if (getTextBytes().length > 0) {
            Map properties = getProperties();
            if (!properties.isEmpty()) {
                for (Map.Entry entry : properties.entrySet()) {
                    String utf = ByteWrangler.readUTF((byte[]) entry.getValue());
                    sb.append("\n\t");
                    sb.append((String) entry.getKey());
                    sb.append(": ");
                    sb.append(utf);
                }
            } else {
                sb.append(", empty");
            }
        }
        sb.append(']');
        return sb.toString();
    }

    public Collection answers(DNSRecordClass dNSRecordClass, boolean z, int i, HostInfo hostInfo) {
        ArrayList arrayList = new ArrayList();
        if (dNSRecordClass == DNSRecordClass.CLASS_ANY || dNSRecordClass == DNSRecordClass.CLASS_IN) {
            if (getSubtype().length() > 0) {
                arrayList.add(new DNSRecord.Pointer(getTypeWithSubtype(), DNSRecordClass.CLASS_IN, false, i, getQualifiedName()));
            }
            String type = getType();
            DNSRecordClass dNSRecordClass2 = DNSRecordClass.CLASS_IN;
            arrayList.add(new DNSRecord.Pointer(type, dNSRecordClass2, false, i, getQualifiedName()));
            arrayList.add(new DNSRecord.Service(getQualifiedName(), dNSRecordClass2, z, i, this._priority, this._weight, this._port, hostInfo.getName()));
            arrayList.add(new DNSRecord.Text(getQualifiedName(), dNSRecordClass2, z, i, getTextBytes()));
        }
        return arrayList;
    }

    void _setText(byte[] bArr) {
        this._text = bArr;
        this._props = null;
    }

    public void setDns(JmDNSImpl jmDNSImpl) {
        this._state.setDns(jmDNSImpl);
    }

    public JmDNSImpl getDns() {
        return this._state.getDns();
    }

    @Override // javax.jmdns.ServiceInfo
    public boolean isPersistent() {
        return this._persistent;
    }

    public void setNeedTextAnnouncing(boolean z) {
        this._needTextAnnouncing = z;
        if (z) {
            this._state.setTask(null);
        }
    }

    public boolean needTextAnnouncing() {
        return this._needTextAnnouncing;
    }

    @Override // javax.jmdns.ServiceInfo
    public boolean hasSameAddresses(ServiceInfo serviceInfo) {
        if (serviceInfo == null) {
            return false;
        }
        if (serviceInfo instanceof ServiceInfoImpl) {
            ServiceInfoImpl serviceInfoImpl = (ServiceInfoImpl) serviceInfo;
            return this._ipv4Addresses.size() == serviceInfoImpl._ipv4Addresses.size() && this._ipv6Addresses.size() == serviceInfoImpl._ipv6Addresses.size() && this._ipv4Addresses.equals(serviceInfoImpl._ipv4Addresses) && this._ipv6Addresses.equals(serviceInfoImpl._ipv6Addresses);
        }
        InetAddress[] inetAddresses = getInetAddresses();
        InetAddress[] inetAddresses2 = serviceInfo.getInetAddresses();
        return inetAddresses.length == inetAddresses2.length && new HashSet(Arrays.asList(inetAddresses)).equals(new HashSet(Arrays.asList(inetAddresses2)));
    }
}
