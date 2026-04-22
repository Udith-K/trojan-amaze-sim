package javax.jmdns;

import androidx.appcompat.app.ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0;
import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicReference;
import javax.jmdns.impl.NetworkTopologyDiscoveryImpl;

/* JADX INFO: loaded from: classes.dex */
public interface NetworkTopologyDiscovery {
    InetAddress[] getInetAddresses();

    public static final class Factory {
        private static final AtomicReference _databaseClassDelegate = new AtomicReference();
        private static volatile NetworkTopologyDiscovery _instance;

        protected static NetworkTopologyDiscovery newNetworkTopologyDiscovery() {
            ToolbarActionBar$$ExternalSyntheticThrowCCEIfNotNull0.m(_databaseClassDelegate.get());
            return new NetworkTopologyDiscoveryImpl();
        }

        public static NetworkTopologyDiscovery getInstance() {
            if (_instance == null) {
                synchronized (Factory.class) {
                    try {
                        if (_instance == null) {
                            _instance = newNetworkTopologyDiscovery();
                        }
                    } finally {
                    }
                }
            }
            return _instance;
        }
    }
}
