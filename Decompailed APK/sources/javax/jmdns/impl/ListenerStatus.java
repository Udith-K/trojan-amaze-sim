package javax.jmdns.impl;

import java.util.EventListener;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public abstract class ListenerStatus {
    private final EventListener _listener;
    private final boolean _synch;

    public static class ServiceTypeListenerStatus extends ListenerStatus {
    }

    public static class ServiceListenerStatus extends ListenerStatus {
        private static Logger logger = LoggerFactory.getLogger(ServiceListenerStatus.class.getName());
        private final ConcurrentMap _addedServices;

        public ServiceListenerStatus(ServiceListener serviceListener, boolean z) {
            super(serviceListener, z);
            this._addedServices = new ConcurrentHashMap(32);
        }

        void serviceAdded(ServiceEvent serviceEvent) {
            if (this._addedServices.putIfAbsent(serviceEvent.getName() + "." + serviceEvent.getType(), serviceEvent.getInfo().clone()) == null) {
                ((ServiceListener) getListener()).serviceAdded(serviceEvent);
                ServiceInfo info2 = serviceEvent.getInfo();
                if (info2 == null || !info2.hasData()) {
                    return;
                }
                ((ServiceListener) getListener()).serviceResolved(serviceEvent);
                return;
            }
            logger.debug("Service Added called for a service already added: {}", serviceEvent);
        }

        void serviceRemoved(ServiceEvent serviceEvent) {
            String str = serviceEvent.getName() + "." + serviceEvent.getType();
            ConcurrentMap concurrentMap = this._addedServices;
            if (concurrentMap.remove(str, concurrentMap.get(str))) {
                ((ServiceListener) getListener()).serviceRemoved(serviceEvent);
            } else {
                logger.debug("Service Removed called for a service already removed: {}", serviceEvent);
            }
        }

        synchronized void serviceResolved(ServiceEvent serviceEvent) {
            try {
                ServiceInfo info2 = serviceEvent.getInfo();
                if (info2 != null && info2.hasData()) {
                    String str = serviceEvent.getName() + "." + serviceEvent.getType();
                    ServiceInfo serviceInfo = (ServiceInfo) this._addedServices.get(str);
                    if (_sameInfo(info2, serviceInfo)) {
                        logger.debug("Service Resolved called for a service already resolved: {}", serviceEvent);
                    } else if (serviceInfo == null) {
                        if (this._addedServices.putIfAbsent(str, info2.clone()) == null) {
                            ((ServiceListener) getListener()).serviceResolved(serviceEvent);
                        }
                    } else if (this._addedServices.replace(str, serviceInfo, info2.clone())) {
                        ((ServiceListener) getListener()).serviceResolved(serviceEvent);
                    }
                } else {
                    logger.warn("Service Resolved called for an unresolved event: {}", serviceEvent);
                }
            } catch (Throwable th) {
                throw th;
            }
        }

        private static final boolean _sameInfo(ServiceInfo serviceInfo, ServiceInfo serviceInfo2) {
            if (serviceInfo == null || serviceInfo2 == null || !serviceInfo.equals(serviceInfo2)) {
                return false;
            }
            byte[] textBytes = serviceInfo.getTextBytes();
            byte[] textBytes2 = serviceInfo2.getTextBytes();
            if (textBytes.length != textBytes2.length) {
                return false;
            }
            for (int i = 0; i < textBytes.length; i++) {
                if (textBytes[i] != textBytes2[i]) {
                    return false;
                }
            }
            return serviceInfo.hasSameAddresses(serviceInfo2);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(2048);
            sb.append("[Status for ");
            sb.append(((ServiceListener) getListener()).toString());
            if (this._addedServices.isEmpty()) {
                sb.append(" no type event ");
            } else {
                sb.append(" (");
                Iterator it = this._addedServices.keySet().iterator();
                while (it.hasNext()) {
                    sb.append(((String) it.next()) + ", ");
                }
                sb.append(") ");
            }
            sb.append("]");
            return sb.toString();
        }
    }

    public ListenerStatus(EventListener eventListener, boolean z) {
        this._listener = eventListener;
        this._synch = z;
    }

    public EventListener getListener() {
        return this._listener;
    }

    public boolean isSynchronous() {
        return this._synch;
    }

    public int hashCode() {
        return getListener().hashCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof ListenerStatus) && getListener().equals(((ListenerStatus) obj).getListener());
    }
}
