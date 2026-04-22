package org.fdroid.fdroid.net;

import android.util.Log;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.fdroid.fdroid.Preferences;

/* JADX INFO: loaded from: classes2.dex */
public final class DnsCache {
    private static final int DELAY_TIME = 1;
    private static final TimeUnit DELAY_UNIT = TimeUnit.SECONDS;
    private static final String TAG = "DnsCache";
    private static DnsCache instance;
    private volatile HashMap<String, List<InetAddress>> cache;
    private volatile boolean writeScheduled = false;
    private final Runnable delayedWrite = new Runnable() { // from class: org.fdroid.fdroid.net.DnsCache$$ExternalSyntheticLambda0
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$0();
        }
    };
    private final ScheduledExecutorService writeExecutor = Executors.newSingleThreadScheduledExecutor();

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        Preferences.get().setDnsCache(this.cache);
        this.writeScheduled = false;
    }

    private DnsCache() {
        this.cache = Preferences.get().getDnsCache();
        if (this.cache == null) {
            this.cache = new HashMap<>();
        }
    }

    public static void setup() {
        if (instance != null) {
            Log.e(TAG, "DnsCache can only be initialized once");
            throw new RuntimeException("DnsCache can only be initialized once");
        }
        instance = new DnsCache();
    }

    public static DnsCache get() {
        DnsCache dnsCache = instance;
        if (dnsCache != null) {
            return dnsCache;
        }
        Log.e(TAG, "DnsCache must be initialized first");
        throw new RuntimeException("DnsCache must be initialized first");
    }

    public void insert(String str, List<InetAddress> list) {
        this.cache.put(str, list);
        if (this.writeScheduled) {
            return;
        }
        this.writeScheduled = true;
        this.writeExecutor.schedule(this.delayedWrite, 1L, DELAY_UNIT);
    }

    public List<InetAddress> remove(String str) {
        List<InetAddress> listRemove = this.cache.remove(str);
        if (!this.writeScheduled && listRemove != null) {
            this.writeScheduled = true;
            this.writeExecutor.schedule(this.delayedWrite, 1L, DELAY_UNIT);
        }
        return listRemove;
    }

    public List<InetAddress> lookup(String str) {
        if (Preferences.get().isDnsCacheEnabled() && this.cache.keySet().contains(str)) {
            return this.cache.get(str);
        }
        return null;
    }
}
