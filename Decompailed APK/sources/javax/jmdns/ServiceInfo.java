package javax.jmdns;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.Map;
import javax.jmdns.impl.ServiceInfoImpl;

/* JADX INFO: loaded from: classes.dex */
public abstract class ServiceInfo implements Cloneable {

    public enum Fields {
        Domain,
        Protocol,
        Application,
        Instance,
        Subtype
    }

    public abstract ServiceInfo clone();

    public abstract String getApplication();

    public abstract String getDomain();

    public abstract Inet4Address[] getInet4Addresses();

    public abstract Inet6Address[] getInet6Addresses();

    public abstract InetAddress[] getInetAddresses();

    public abstract String getKey();

    public abstract String getName();

    public abstract int getPort();

    public abstract int getPriority();

    public abstract String getPropertyString(String str);

    public abstract String getProtocol();

    public abstract String getQualifiedName();

    public abstract String getServer();

    public abstract String getSubtype();

    public abstract byte[] getTextBytes();

    public abstract String getType();

    public abstract int getWeight();

    public abstract boolean hasData();

    public abstract boolean hasSameAddresses(ServiceInfo serviceInfo);

    public abstract boolean isPersistent();

    public static ServiceInfo create(String str, String str2, int i, int i2, int i3, Map map) {
        return new ServiceInfoImpl(str, str2, "", i, i2, i3, false, map);
    }
}
