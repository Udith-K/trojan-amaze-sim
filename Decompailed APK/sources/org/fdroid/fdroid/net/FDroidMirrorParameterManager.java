package org.fdroid.fdroid.net;

import android.telephony.TelephonyManager;
import androidx.core.os.LocaleListCompat;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.fdroid.download.MirrorParameterManager;
import org.fdroid.fdroid.FDroidApp;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.data.App;

/* JADX INFO: loaded from: classes2.dex */
public class FDroidMirrorParameterManager implements MirrorParameterManager {
    private static final int DELAY_TIME = 5;
    private static final TimeUnit DELAY_UNIT = TimeUnit.SECONDS;
    private final AtomicBoolean writeErrorScheduled = new AtomicBoolean(false);
    private final ScheduledExecutorService writeErrorExecutor = Executors.newSingleThreadScheduledExecutor();
    private final ConcurrentHashMap<String, Integer> errorCache = new ConcurrentHashMap<>(Preferences.get().getMirrorErrorData());
    private final Runnable delayedErrorWrite = new Runnable() { // from class: org.fdroid.fdroid.net.FDroidMirrorParameterManager$$ExternalSyntheticLambda0
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$0();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        if (this.writeErrorScheduled.compareAndSet(true, false)) {
            Preferences.get().setMirrorErrorData(Collections.unmodifiableMap(new HashMap(this.errorCache)));
        }
    }

    public void updateErrorCacheAndPrefs(String str, Integer num) {
        this.errorCache.put(str, num);
        if (this.writeErrorScheduled.compareAndSet(false, true)) {
            this.writeErrorExecutor.schedule(this.delayedErrorWrite, 5L, DELAY_UNIT);
        }
    }

    @Override // org.fdroid.download.MirrorParameterManager
    public void incrementMirrorErrorCount(String str) {
        if (this.errorCache.containsKey(str)) {
            updateErrorCacheAndPrefs(str, Integer.valueOf(this.errorCache.get(str).intValue() + 1));
        } else {
            updateErrorCacheAndPrefs(str, 1);
        }
    }

    @Override // org.fdroid.download.MirrorParameterManager
    public int getMirrorErrorCount(String str) {
        if (this.errorCache.containsKey(str)) {
            return this.errorCache.get(str).intValue();
        }
        return 0;
    }

    @Override // org.fdroid.download.MirrorParameterManager
    public boolean preferForeignMirrors() {
        return Preferences.get().isPreferForeignSet();
    }

    @Override // org.fdroid.download.MirrorParameterManager
    public String getCurrentLocation() {
        TelephonyManager telephonyManager = (TelephonyManager) FDroidApp.getInstance().getSystemService("phone");
        if (telephonyManager.getSimCountryIso() != null) {
            return telephonyManager.getSimCountryIso();
        }
        if (telephonyManager.getNetworkCountryIso() != null) {
            return telephonyManager.getNetworkCountryIso();
        }
        LocaleListCompat locales = App.getLocales();
        if (locales != null && locales.size() > 0) {
            return locales.get(0).getCountry();
        }
        return "";
    }
}
