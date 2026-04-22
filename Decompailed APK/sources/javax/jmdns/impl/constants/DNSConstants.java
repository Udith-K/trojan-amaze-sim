package javax.jmdns.impl.constants;

/* JADX INFO: loaded from: classes.dex */
public abstract class DNSConstants {
    public static final int ANNOUNCED_RENEWAL_TTL_INTERVAL;
    public static final int DNS_TTL;
    public static final int MDNS_PORT = Integer.getInteger("net.mdns.port", 5353).intValue();

    static {
        int iIntValue = Integer.getInteger("net.dns.ttl", 3600).intValue();
        DNS_TTL = iIntValue;
        ANNOUNCED_RENEWAL_TTL_INTERVAL = iIntValue * 500;
    }
}
