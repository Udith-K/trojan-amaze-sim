package javax.jmdns.impl;

import ch.qos.logback.core.CoreConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.jmdns.impl.constants.DNSRecordClass;
import javax.jmdns.impl.constants.DNSRecordType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class DNSCache extends ConcurrentHashMap {
    private static Logger logger = LoggerFactory.getLogger(DNSCache.class.getName());

    public DNSCache(DNSCache dNSCache) {
        this(dNSCache != null ? dNSCache.size() : 1024);
        if (dNSCache != null) {
            putAll(dNSCache);
        }
    }

    public DNSCache(int i) {
        super(i);
    }

    @Override // java.util.AbstractMap
    protected Object clone() {
        return new DNSCache(this);
    }

    public Collection allValues() {
        ArrayList arrayList = new ArrayList();
        for (V v : values()) {
            if (v != null) {
                arrayList.addAll(v);
            }
        }
        return arrayList;
    }

    public Collection getDNSEntryList(String str) {
        ArrayList arrayList;
        Collection collection_getDNSEntryList = _getDNSEntryList(str);
        if (collection_getDNSEntryList != null) {
            synchronized (collection_getDNSEntryList) {
                arrayList = new ArrayList(collection_getDNSEntryList);
            }
            return arrayList;
        }
        return Collections.emptyList();
    }

    private Collection _getDNSEntryList(String str) {
        return (Collection) get(str != null ? str.toLowerCase() : null);
    }

    public DNSEntry getDNSEntry(DNSEntry dNSEntry) {
        Collection collection_getDNSEntryList;
        DNSEntry dNSEntry2 = null;
        if (dNSEntry != null && (collection_getDNSEntryList = _getDNSEntryList(dNSEntry.getKey())) != null) {
            synchronized (collection_getDNSEntryList) {
                try {
                    Iterator it = collection_getDNSEntryList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        DNSEntry dNSEntry3 = (DNSEntry) it.next();
                        if (dNSEntry3.isSameEntry(dNSEntry)) {
                            dNSEntry2 = dNSEntry3;
                            break;
                        }
                    }
                } finally {
                }
            }
        }
        return dNSEntry2;
    }

    public DNSEntry getDNSEntry(String str, DNSRecordType dNSRecordType, DNSRecordClass dNSRecordClass) {
        Collection collection_getDNSEntryList = _getDNSEntryList(str);
        DNSEntry dNSEntry = null;
        if (collection_getDNSEntryList != null) {
            synchronized (collection_getDNSEntryList) {
                try {
                    Iterator it = collection_getDNSEntryList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        DNSEntry dNSEntry2 = (DNSEntry) it.next();
                        if (dNSEntry2.matchRecordType(dNSRecordType) && dNSEntry2.matchRecordClass(dNSRecordClass)) {
                            dNSEntry = dNSEntry2;
                            break;
                        }
                    }
                } finally {
                }
            }
        }
        return dNSEntry;
    }

    public Collection getDNSEntryList(String str, DNSRecordType dNSRecordType, DNSRecordClass dNSRecordClass) {
        ArrayList arrayList;
        Collection collection_getDNSEntryList = _getDNSEntryList(str);
        if (collection_getDNSEntryList != null) {
            synchronized (collection_getDNSEntryList) {
                try {
                    arrayList = new ArrayList(collection_getDNSEntryList);
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        DNSEntry dNSEntry = (DNSEntry) it.next();
                        if (!dNSEntry.matchRecordType(dNSRecordType) || !dNSEntry.matchRecordClass(dNSRecordClass)) {
                            it.remove();
                        }
                    }
                } finally {
                }
            }
            return arrayList;
        }
        return Collections.emptyList();
    }

    public boolean addDNSEntry(DNSEntry dNSEntry) {
        if (dNSEntry == null) {
            return false;
        }
        List list = (List) get(dNSEntry.getKey());
        if (list == null) {
            putIfAbsent(dNSEntry.getKey(), new ArrayList());
            list = (List) get(dNSEntry.getKey());
        }
        synchronized (list) {
            list.add(dNSEntry);
        }
        return true;
    }

    public boolean removeDNSEntry(DNSEntry dNSEntry) {
        List list;
        if (dNSEntry == null || (list = (List) get(dNSEntry.getKey())) == null) {
            return false;
        }
        synchronized (list) {
            list.remove(dNSEntry);
        }
        return false;
    }

    public boolean replaceDNSEntry(DNSEntry dNSEntry, DNSEntry dNSEntry2) {
        if (dNSEntry == null || dNSEntry2 == null || !dNSEntry.getKey().equals(dNSEntry2.getKey())) {
            return false;
        }
        List list = (List) get(dNSEntry.getKey());
        if (list == null) {
            putIfAbsent(dNSEntry.getKey(), new ArrayList());
            list = (List) get(dNSEntry.getKey());
        }
        synchronized (list) {
            list.remove(dNSEntry2);
            list.add(dNSEntry);
        }
        return true;
    }

    @Override // java.util.concurrent.ConcurrentHashMap, java.util.AbstractMap
    public synchronized String toString() {
        StringBuilder sb;
        try {
            sb = new StringBuilder(2000);
            sb.append("\n\t---- cache ----");
            Iterator it = entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                sb.append("\n\n\t\tname '");
                sb.append((String) entry.getKey());
                sb.append(CoreConstants.SINGLE_QUOTE_CHAR);
                List<DNSEntry> list = (List) entry.getValue();
                if (list != null && !list.isEmpty()) {
                    synchronized (list) {
                        try {
                            for (DNSEntry dNSEntry : list) {
                                sb.append("\n\t\t\t");
                                sb.append(dNSEntry.toString());
                            }
                        } finally {
                        }
                    }
                } else {
                    sb.append(" : no entries");
                }
            }
        } catch (Throwable th) {
            throw th;
        }
        return sb.toString();
    }

    public void logCachedContent() {
        if (logger.isTraceEnabled()) {
            logger.trace("Cached DNSEntries: {}", toString());
        }
    }
}
