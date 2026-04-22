package javax.jmdns;

import java.util.EventObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class ServiceEvent extends EventObject implements Cloneable {
    public abstract JmDNS getDNS();

    public abstract ServiceInfo getInfo();

    public abstract String getName();

    public abstract String getType();

    public ServiceEvent(Object obj) {
        super(obj);
    }
}
