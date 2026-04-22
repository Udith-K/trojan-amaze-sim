package org.fdroid.fdroid.net;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import okhttp3.Dns;
import org.fdroid.fdroid.Preferences;

/* JADX INFO: loaded from: classes2.dex */
public class DnsWithCache implements Dns {
    @Override // okhttp3.Dns
    public List<InetAddress> lookup(String str) throws UnknownHostException {
        if (!Preferences.get().isDnsCacheEnabled()) {
            return Dns.SYSTEM.lookup(str);
        }
        DnsCache dnsCache = DnsCache.get();
        List<InetAddress> listLookup = dnsCache.lookup(str);
        if (listLookup != null) {
            return listLookup;
        }
        List listLookup2 = Dns.SYSTEM.lookup(str);
        dnsCache.insert(str, listLookup2);
        return listLookup2;
    }
}
