package org.fdroid.fdroid;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;
import ch.qos.logback.core.CoreConstants;
import com.google.common.collect.Lists;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.fdroid.fdroid.data.Apk;
import org.fdroid.fdroid.data.DBHelper;
import org.fdroid.fdroid.installer.PrivilegedInstaller;
import org.fdroid.fdroid.views.main.MainActivity;

/* JADX INFO: loaded from: classes2.dex */
public final class Preferences implements SharedPreferences.OnSharedPreferenceChangeListener, IPreferencesIpfs {
    private static final int DEFAULT_LAST_UPDATE_CHECK = -1;
    private static final boolean DEFAULT_PANIC_EXIT = true;
    public static final String DEFAULT_PROXY_HOST = "127.0.0.1";
    public static final int DEFAULT_PROXY_PORT = 8118;
    private static final boolean DEFAULT_SHOW_NFC_DURING_SWAP = true;
    private static final boolean IGNORED_B = false;
    private static final int IGNORED_I = -1;

    @Deprecated
    private static final String OLD_PREF_CACHE_APK = "cacheDownloaded";

    @Deprecated
    private static final String OLD_PREF_UPDATE_INTERVAL = "updateInterval";

    @Deprecated
    private static final String OLD_PREF_UPDATE_ON_WIFI_ONLY = "updateOnWifiOnly";
    public static final int OVER_NETWORK_ALWAYS = 2;
    public static final int OVER_NETWORK_NEVER = 0;
    private static final int OVER_NETWORK_ON_DEMAND = 1;
    public static final String PREF_AUTO_DOWNLOAD_INSTALL_UPDATES = "updateAutoDownload";
    private static final String PREF_BOTTOM_NAVIGATION_VIEW_NAME = "bottomNavigationViewName";
    private static final String PREF_DEFAULT_REPO_ADDRESSES = "defaultRepoAddresses";
    public static final String PREF_DNS_CACHE = "dnsCache";
    public static final String PREF_ENABLE_PROXY = "enableProxy";
    public static final String PREF_EXPERT = "expert";
    public static final String PREF_FORCE_OLD_INDEX = "forceOldIndex";
    public static final String PREF_FORCE_OLD_INSTALLER = "forceOldInstaller";
    public static final String PREF_FORCE_TOUCH_APPS = "ignoreTouchscreen";
    private static final String PREF_HIDE_ALL_NOTIFICATIONS = "hideAllNotifications";
    private static final String PREF_HIDE_ON_LONG_PRESS_SEARCH = "hideOnLongPressSearch";
    public static final String PREF_IPFSGW_DISABLED_DEFAULTS_LIST = "ipfsGwDisabledDefaultsList";
    public static final String PREF_IPFSGW_USER_LIST = "ipfsGwUserList";
    public static final String PREF_KEEP_CACHE_TIME = "keepCacheFor";
    public static final String PREF_KEEP_INSTALL_HISTORY = "keepInstallHistory";
    public static final String PREF_LANGUAGE = "language";
    private static final String PREF_LAST_UPDATE_CHECK = "lastUpdateCheck";
    public static final String PREF_LOCAL_REPO_HTTPS = "localRepoHttps";
    public static final String PREF_LOCAL_REPO_NAME = "localRepoName";
    public static final String PREF_MIRROR_ERROR_DATA = "mirrorErrorData";
    public static final String PREF_OVER_DATA = "overData";
    public static final String PREF_OVER_WIFI = "overWifi";
    public static final String PREF_PANIC_EXIT = "pref_panic_exit";
    public static final String PREF_PANIC_HIDE = "pref_panic_hide";
    public static final String PREF_PANIC_RESET_REPOS = "pref_panic_reset_repos";
    private static final String PREF_PANIC_TMP_SELECTED_SET = "panicTmpSelectedSet";
    private static final String PREF_PANIC_WIPE_SET = "panicWipeSet";
    public static final String PREF_PREFER_FOREIGN = "preferForeign";
    public static final String PREF_PREVENT_SCREENSHOTS = "preventScreenshots";
    public static final String PREF_PRIVILEGED_INSTALLER = "privilegedInstaller";
    private static final String PREF_PROMPT_TO_SEND_CRASH_REPORTS = "promptToSendCrashReports";
    public static final String PREF_PROXY_HOST = "proxyHost";
    public static final String PREF_PROXY_PORT = "proxyPort";
    private static final String PREF_SCAN_REMOVABLE_STORAGE = "scanRemovableStorage";
    public static final String PREF_SEND_TO_FDROID_METRICS = "sendToFdroidMetrics";
    private static final String PREF_SEND_VERSION_AND_UUID_TO_SERVERS = "sendVersionAndUUIDToServers";
    public static final String PREF_SHOW_ANTI_FEATURES = "showAntiFeatures";
    public static final String PREF_SHOW_INCOMPAT_VERSIONS = "incompatibleVersions";
    private static final String PREF_SHOW_NFC_DURING_SWAP = "showNfcDuringSwap";
    public static final String PREF_THEME = "theme";
    private static final String PREF_UNSTABLE_UPDATES = "unstableUpdates";
    public static final String PREF_UPDATE_INTERVAL = "updateIntervalSeekBarPosition";
    public static final String PREF_UPDATE_NOTIFICATION_ENABLED = "updateNotify";
    public static final String PREF_USE_DNS_CACHE = "useDnsCache";
    public static final String PREF_USE_IPFS_GATEWAYS = "useIpfsGateways";
    public static final String PREF_USE_PURE_BLACK_DARK_THEME = "usePureBlackDarkTheme";
    public static final String PREF_USE_TOR = "useTor";
    private static final String TAG = "Preferences";
    private static Preferences instance;
    private final SharedPreferences preferences;
    private Set<String> showAppsWithAntiFeatures;
    public static final List<String> DEFAULT_IPFS_GATEWAYS = Collections.singletonList("https://gateway.ipfs.io/ipfs/");
    public static final long UPDATE_INTERVAL_DISABLED = Long.MAX_VALUE;
    public static final long[] UPDATE_INTERVAL_VALUES = {UPDATE_INTERVAL_DISABLED, 1209600000, CoreConstants.MILLIS_IN_ONE_WEEK, CoreConstants.MILLIS_IN_ONE_DAY, 43200000, 14400000, CoreConstants.MILLIS_IN_ONE_HOUR};
    private final Map<String, Boolean> initialized = new HashMap();
    private final List<ChangeListener> showAppsRequiringAntiFeaturesListeners = new ArrayList();
    private final List<ChangeListener> localRepoNameListeners = new ArrayList();
    private final List<ChangeListener> localRepoHttpsListeners = new ArrayList();
    private final List<ChangeListener> unstableUpdatesListeners = new ArrayList();
    private final List<ChangeListener> showIncompatibleListeners = new ArrayList();

    public interface ChangeListener {
        void onPreferenceChange();
    }

    public enum Theme {
        light,
        dark,
        followSystem,
        night,
        lightWithDarkActionBar
    }

    public boolean isLocalRepoHttpsEnabled() {
        return false;
    }

    private Preferences(Context context) {
        PreferenceManager.setDefaultValues(context, R.xml.preferences, true);
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.preferences = defaultSharedPreferences;
        defaultSharedPreferences.registerOnSharedPreferenceChangeListener(this);
        SharedPreferences.Editor editorEdit = defaultSharedPreferences.edit();
        if (defaultSharedPreferences.getString(PREF_LOCAL_REPO_NAME, null) == null) {
            editorEdit.putString(PREF_LOCAL_REPO_NAME, getDefaultLocalRepoName());
        }
        if (!defaultSharedPreferences.contains(PREF_AUTO_DOWNLOAD_INSTALL_UPDATES)) {
            editorEdit.putBoolean(PREF_AUTO_DOWNLOAD_INSTALL_UPDATES, PrivilegedInstaller.isExtensionInstalledCorrectly(context) != 1);
        }
        editorEdit.apply();
    }

    private boolean isInitialized(String str) {
        return this.initialized.containsKey(str) && this.initialized.get(str).booleanValue();
    }

    private void initialize(String str) {
        this.initialized.put(str, Boolean.TRUE);
    }

    private void uninitialize(String str) {
        this.initialized.put(str, Boolean.FALSE);
    }

    boolean promptToSendCrashReports() {
        return this.preferences.getBoolean(PREF_PROMPT_TO_SEND_CRASH_REPORTS, false);
    }

    public boolean isForceOldIndexEnabled() {
        return this.preferences.getBoolean(PREF_FORCE_OLD_INDEX, false);
    }

    public boolean forceOldInstaller() {
        return this.preferences.getBoolean(PREF_FORCE_OLD_INSTALLER, false);
    }

    public boolean isPrivilegedInstallerEnabled() {
        return this.preferences.getBoolean(PREF_PRIVILEGED_INSTALLER, true);
    }

    public long getUpdateInterval() {
        return UPDATE_INTERVAL_VALUES[this.preferences.getInt(PREF_UPDATE_INTERVAL, -1)];
    }

    @SuppressLint({"ApplySharedPref"})
    public void migrateOldPreferences() {
        SharedPreferences.Editor editorEdit = this.preferences.edit();
        if (migrateUpdateIntervalStringToInt(editorEdit) || migrateOnlyOnWifi(editorEdit)) {
            editorEdit.commit();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean migrateUpdateIntervalStringToInt(android.content.SharedPreferences.Editor r7) {
        /*
            r6 = this;
            android.content.SharedPreferences r0 = r6.preferences
            java.lang.String r1 = "updateInterval"
            boolean r0 = r0.contains(r1)
            r2 = 0
            if (r0 != 0) goto Lc
            return r2
        Lc:
            android.content.SharedPreferences r0 = r6.preferences
            r3 = 24
            java.lang.String r3 = java.lang.String.valueOf(r3)
            java.lang.String r0 = r0.getString(r1, r3)
            java.lang.String r3 = "1"
            boolean r3 = r3.equals(r0)
            r4 = 1
            if (r3 == 0) goto L23
            r2 = 6
            goto L5e
        L23:
            java.lang.String r3 = "4"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L2d
            r2 = 5
            goto L5e
        L2d:
            java.lang.String r3 = "12"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L37
            r2 = 4
            goto L5e
        L37:
            java.lang.String r3 = "24"
            boolean r3 = r3.equals(r0)
            r5 = 3
            if (r3 == 0) goto L42
        L40:
            r2 = r5
            goto L5e
        L42:
            java.lang.String r3 = "168"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L4c
            r2 = 2
            goto L5e
        L4c:
            java.lang.String r3 = "336"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L56
            r2 = r4
            goto L5e
        L56:
            java.lang.String r3 = "0"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L40
        L5e:
            java.lang.String r0 = "updateIntervalSeekBarPosition"
            android.content.SharedPreferences$Editor r7 = r7.putInt(r0, r2)
            r7.remove(r1)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.fdroid.Preferences.migrateUpdateIntervalStringToInt(android.content.SharedPreferences$Editor):boolean");
    }

    private boolean migrateOnlyOnWifi(SharedPreferences.Editor editor) {
        if (!this.preferences.contains(OLD_PREF_UPDATE_ON_WIFI_ONLY)) {
            return false;
        }
        editor.putInt(PREF_OVER_WIFI, 2).putInt(PREF_OVER_DATA, !this.preferences.getBoolean(OLD_PREF_UPDATE_ON_WIFI_ONLY, true) ? 1 : 0).remove(OLD_PREF_UPDATE_ON_WIFI_ONLY);
        return true;
    }

    public long getKeepCacheTime() {
        String string;
        long millis;
        string = this.preferences.getString(PREF_KEEP_CACHE_TIME, null);
        millis = TimeUnit.DAYS.toMillis(1L);
        string.hashCode();
        switch (string) {
            case "2592000":
            case "2147483647":
            case "3600":
            case "86400":
            case "31449600":
            case "604800":
                SharedPreferences.Editor editorEdit = this.preferences.edit();
                editorEdit.remove(PREF_KEEP_CACHE_TIME);
                editorEdit.apply();
                return millis;
            default:
                if (this.preferences.contains(OLD_PREF_CACHE_APK)) {
                    if (this.preferences.getBoolean(OLD_PREF_CACHE_APK, false)) {
                        string = String.valueOf(UPDATE_INTERVAL_DISABLED);
                    }
                    SharedPreferences.Editor editorEdit2 = this.preferences.edit();
                    editorEdit2.remove(OLD_PREF_CACHE_APK);
                    editorEdit2.putString(PREF_KEEP_CACHE_TIME, string);
                    editorEdit2.apply();
                }
                try {
                    return Long.parseLong(string);
                } catch (NumberFormatException unused) {
                    return millis;
                }
        }
    }

    public long getLastUpdateCheck() {
        return this.preferences.getLong(PREF_LAST_UPDATE_CHECK, -1L);
    }

    void setLastUpdateCheck(long j) {
        this.preferences.edit().putLong(PREF_LAST_UPDATE_CHECK, j).apply();
    }

    public String getBottomNavigationViewName() {
        return this.preferences.getString(PREF_BOTTOM_NAVIGATION_VIEW_NAME, MainActivity.EXTRA_VIEW_LATEST);
    }

    public void setBottomNavigationViewName(String str) {
        this.preferences.edit().putString(PREF_BOTTOM_NAVIGATION_VIEW_NAME, str).apply();
    }

    public boolean isIndexNeverUpdated() {
        return getLastUpdateCheck() == -1;
    }

    private boolean getUnstableUpdates() {
        return this.preferences.getBoolean(PREF_UNSTABLE_UPDATES, false);
    }

    public String getReleaseChannel() {
        return getUnstableUpdates() ? "Beta" : Apk.RELEASE_CHANNEL_STABLE;
    }

    public List<String> getBackendReleaseChannels() {
        if (getUnstableUpdates()) {
            return Collections.singletonList("Beta");
        }
        return null;
    }

    public void setUnstableUpdates(boolean z) {
        this.preferences.edit().putBoolean(PREF_UNSTABLE_UPDATES, z).apply();
    }

    boolean isKeepingInstallHistory() {
        return this.preferences.getBoolean(PREF_KEEP_INSTALL_HISTORY, false);
    }

    public boolean isSendingToFDroidMetrics() {
        return isKeepingInstallHistory() && this.preferences.getBoolean(PREF_SEND_TO_FDROID_METRICS, false);
    }

    public boolean showIncompatibleVersions() {
        return this.preferences.getBoolean(PREF_SHOW_INCOMPAT_VERSIONS, false);
    }

    public boolean showNfcDuringSwap() {
        return this.preferences.getBoolean(PREF_SHOW_NFC_DURING_SWAP, true);
    }

    public void setShowNfcDuringSwap(boolean z) {
        this.preferences.edit().putBoolean(PREF_SHOW_NFC_DURING_SWAP, z).apply();
    }

    public boolean expertMode() {
        return this.preferences.getBoolean(PREF_EXPERT, false);
    }

    public void setExpertMode(boolean z) {
        this.preferences.edit().putBoolean(PREF_EXPERT, z).apply();
    }

    boolean forceTouchApps() {
        return this.preferences.getBoolean(PREF_FORCE_TOUCH_APPS, false);
    }

    public Theme getTheme() {
        return Theme.valueOf(this.preferences.getString(PREF_THEME, null));
    }

    public boolean isPureBlack() {
        return this.preferences.getBoolean(PREF_USE_PURE_BLACK_DARK_THEME, false);
    }

    private String getDefaultLocalRepoName() {
        return (Build.BRAND + " " + Build.MODEL + new Random().nextInt(9999)).replaceAll(" ", "-");
    }

    public String getLanguage() {
        return this.preferences.getString(PREF_LANGUAGE, "");
    }

    void clearLanguage() {
        this.preferences.edit().remove(PREF_LANGUAGE).apply();
    }

    public String getLocalRepoName() {
        return this.preferences.getString(PREF_LOCAL_REPO_NAME, getDefaultLocalRepoName());
    }

    public boolean isScanRemovableStorageEnabled() {
        return this.preferences.getBoolean(PREF_SCAN_REMOVABLE_STORAGE, true);
    }

    public boolean isUpdateNotificationEnabled() {
        return this.preferences.getBoolean(PREF_UPDATE_NOTIFICATION_ENABLED, true);
    }

    boolean isAutoDownloadEnabled() {
        return this.preferences.getBoolean(PREF_AUTO_DOWNLOAD_INSTALL_UPDATES, false);
    }

    public boolean isBackgroundDownloadAllowed() {
        return FDroidApp.networkState == 2 ? getOverWifi() == 2 : FDroidApp.networkState == 1 && getOverData() == 2;
    }

    public boolean isOnDemandDownloadAllowed() {
        return (FDroidApp.networkState == 2 || FDroidApp.networkState == 3) ? getOverWifi() != 0 : FDroidApp.networkState == 1 && getOverData() != 0;
    }

    public int getOverWifi() {
        return this.preferences.getInt(PREF_OVER_WIFI, -1);
    }

    public int getOverData() {
        return this.preferences.getInt(PREF_OVER_DATA, -1);
    }

    void setDefaultForDataOnlyConnection(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) ContextCompat.getSystemService(context, ConnectivityManager.class);
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnectedOrConnecting() || activeNetworkInfo.getType() != 0 || connectivityManager.getNetworkInfo(1).isConnectedOrConnecting()) {
            return;
        }
        this.preferences.edit().putInt(PREF_OVER_DATA, 2).apply();
    }

    public void setDnsCacheEnabledValue(boolean z) {
        this.preferences.edit().putBoolean(PREF_USE_DNS_CACHE, z).apply();
    }

    public boolean isDnsCacheEnabled() {
        return this.preferences.getBoolean(PREF_USE_DNS_CACHE, false);
    }

    public void setDnsCache(HashMap<String, List<InetAddress>> map) {
        HashMap<String, List<String>> map2 = new HashMap<>();
        for (String str : map.keySet()) {
            ArrayList arrayList = new ArrayList();
            Iterator<InetAddress> it = map.get(str).iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getHostAddress());
            }
            map2.put(str, arrayList);
        }
        this.preferences.edit().putString(PREF_DNS_CACHE, listMapToString(map2)).apply();
    }

    public HashMap<String, List<InetAddress>> getDnsCache() {
        HashMap<String, List<InetAddress>> map = new HashMap<>();
        String string = this.preferences.getString(PREF_DNS_CACHE, "");
        if (string != null && !string.isEmpty()) {
            HashMap<String, List<String>> mapStringToListMap = stringToListMap(string);
            for (String str : mapStringToListMap.keySet()) {
                ArrayList arrayList = new ArrayList();
                for (String str2 : mapStringToListMap.get(str)) {
                    try {
                        arrayList.add(InetAddress.getByName(str2));
                    } catch (UnknownHostException e) {
                        Log.e(TAG, "Exception thrown when converting " + str2, e);
                    }
                }
                map.put(str, arrayList);
            }
        }
        return map;
    }

    private String listMapToString(HashMap<String, List<String>> map) {
        String str = "";
        for (String str2 : map.keySet()) {
            if (!str.isEmpty()) {
                str = str + "\n";
            }
            str = str + str2;
            for (String str3 : map.get(str2)) {
                if (!str.isEmpty()) {
                    str = str + " ";
                }
                str = str + str3;
            }
        }
        return str;
    }

    private HashMap<String, List<String>> stringToListMap(String str) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str2 : str.split("\n")) {
            ArrayList arrayListNewArrayList = Lists.newArrayList(str2.split(" "));
            map.put(arrayListNewArrayList.remove(0), arrayListNewArrayList);
        }
        return map;
    }

    private String intMapToString(Map<String, Integer> map) {
        String str = "";
        for (String str2 : map.keySet()) {
            Integer num = map.get(str2);
            if (str2 == null || str2.isEmpty()) {
                Utils.debugLog(TAG, "Don't serialize record with null key");
            } else if (num == null) {
                Utils.debugLog(TAG, "Don't serialize null value for: " + str2);
            } else {
                if (!str.isEmpty()) {
                    str = str + "\n";
                }
                str = str + str2 + " " + num;
            }
        }
        return str;
    }

    private Map<String, Integer> stringToIntMap(String str) {
        HashMap map = new HashMap();
        for (String str2 : str.split("\n")) {
            String[] strArrSplit = str2.split(" ");
            String str3 = strArrSplit[0];
            int iValueOf = 0;
            if (str3 == null || str3.isEmpty()) {
                Utils.debugLog(TAG, "Serialized map entry key is missing: " + str2);
            } else {
                if (strArrSplit.length <= 1) {
                    Utils.debugLog(TAG, "Serialized map entry value is missing: " + str2);
                } else {
                    try {
                        iValueOf = Integer.valueOf(strArrSplit[1]);
                    } catch (NumberFormatException unused) {
                        Utils.debugLog(TAG, "Serialized map entry value can't be parsed: " + str2);
                    }
                }
                map.put(str3, iValueOf);
            }
        }
        return map;
    }

    public void setPreferForeignValue(boolean z) {
        this.preferences.edit().putBoolean(PREF_PREFER_FOREIGN, z).apply();
    }

    public boolean isPreferForeignSet() {
        return this.preferences.getBoolean(PREF_PREFER_FOREIGN, false);
    }

    public void setMirrorErrorData(Map<String, Integer> map) {
        this.preferences.edit().putString(PREF_MIRROR_ERROR_DATA, intMapToString(map)).apply();
    }

    public Map<String, Integer> getMirrorErrorData() {
        HashMap map = new HashMap();
        String string = this.preferences.getString(PREF_MIRROR_ERROR_DATA, "");
        return (string == null || string.isEmpty()) ? map : stringToIntMap(string);
    }

    public boolean isTorEnabled() {
        return this.preferences.getBoolean(PREF_USE_TOR, false);
    }

    boolean isProxyEnabled() {
        return this.preferences.getBoolean(PREF_ENABLE_PROXY, false);
    }

    public String getProxyHost() {
        return this.preferences.getString(PREF_PROXY_HOST, DEFAULT_PROXY_HOST);
    }

    public int getProxyPort() {
        String string = this.preferences.getString(PREF_PROXY_PORT, String.valueOf(DEFAULT_PROXY_PORT));
        try {
            try {
                return Math.min(Integer.parseInt(string), 65535);
            } catch (NumberFormatException unused) {
                return Math.min(Integer.parseInt(string.replaceAll("[^0-9]", "")), 65535);
            }
        } catch (Exception unused2) {
            return DEFAULT_PROXY_PORT;
        }
    }

    @Override // org.fdroid.fdroid.IPreferencesIpfs
    public boolean isIpfsEnabled() {
        return this.preferences.getBoolean(PREF_USE_IPFS_GATEWAYS, false);
    }

    @Override // org.fdroid.fdroid.IPreferencesIpfs
    /* JADX INFO: renamed from: setIpfsEnabled */
    public void mo3125setIpfsEnabled(boolean z) {
        this.preferences.edit().putBoolean(PREF_USE_IPFS_GATEWAYS, z).apply();
    }

    @Override // org.fdroid.fdroid.IPreferencesIpfs
    public List<String> getIpfsGwUserList() {
        return Utils.parseJsonStringArray(this.preferences.getString(PREF_IPFSGW_USER_LIST, "[]"));
    }

    @Override // org.fdroid.fdroid.IPreferencesIpfs
    /* JADX INFO: renamed from: setIpfsGwUserList */
    public void mo3127setIpfsGwUserList(List<String> list) {
        this.preferences.edit().putString(PREF_IPFSGW_USER_LIST, Utils.toJsonStringArray(list)).apply();
    }

    @Override // org.fdroid.fdroid.IPreferencesIpfs
    public List<String> getIpfsGwDisabledDefaults() {
        return Utils.parseJsonStringArray(this.preferences.getString(PREF_IPFSGW_DISABLED_DEFAULTS_LIST, "[]"));
    }

    @Override // org.fdroid.fdroid.IPreferencesIpfs
    /* JADX INFO: renamed from: setIpfsGwDisabledDefaults */
    public void mo3126setIpfsGwDisabledDefaults(List<String> list) {
        this.preferences.edit().putString(PREF_IPFSGW_DISABLED_DEFAULTS_LIST, Utils.toJsonStringArray(list)).apply();
    }

    public boolean preventScreenshots() {
        return this.preferences.getBoolean(PREF_PREVENT_SCREENSHOTS, false);
    }

    public boolean panicExit() {
        return this.preferences.getBoolean(PREF_PANIC_EXIT, true);
    }

    public boolean panicHide() {
        return this.preferences.getBoolean(PREF_PANIC_HIDE, false);
    }

    public boolean panicResetRepos() {
        return this.preferences.getBoolean(PREF_PANIC_RESET_REPOS, false);
    }

    public boolean hideOnLongPressSearch() {
        return this.preferences.getBoolean(PREF_HIDE_ON_LONG_PRESS_SEARCH, false);
    }

    public Set<String> getPanicTmpSelectedSet() {
        return this.preferences.getStringSet(PREF_PANIC_TMP_SELECTED_SET, Collections.emptySet());
    }

    public void setPanicTmpSelectedSet(Set<String> set) {
        this.preferences.edit().putStringSet(PREF_PANIC_TMP_SELECTED_SET, set).apply();
    }

    public Set<String> getPanicWipeSet() {
        return this.preferences.getStringSet(PREF_PANIC_WIPE_SET, Collections.emptySet());
    }

    public void setPanicWipeSet(Set<String> set) {
        this.preferences.edit().putStringSet(PREF_PANIC_WIPE_SET, set).apply();
    }

    boolean hideAllNotifications() {
        return this.preferences.getBoolean(PREF_HIDE_ALL_NOTIFICATIONS, false);
    }

    boolean sendVersionAndUUIDToServers() {
        return this.preferences.getBoolean(PREF_SEND_VERSION_AND_UUID_TO_SERVERS, false);
    }

    public Set<String> showAppsWithAntiFeatures() {
        if (!isInitialized(PREF_SHOW_ANTI_FEATURES)) {
            initialize(PREF_SHOW_ANTI_FEATURES);
            this.showAppsWithAntiFeatures = this.preferences.getStringSet(PREF_SHOW_ANTI_FEATURES, null);
        }
        return this.showAppsWithAntiFeatures;
    }

    public List<String> getActiveIpfsGateways() {
        List<String> ipfsGwUserList = getIpfsGwUserList();
        List<String> ipfsGwDisabledDefaults = getIpfsGwDisabledDefaults();
        for (String str : DEFAULT_IPFS_GATEWAYS) {
            if (!ipfsGwDisabledDefaults.contains(str)) {
                ipfsGwUserList.add(str);
            }
        }
        return ipfsGwUserList;
    }

    private void setPrefDefaultRepoAddresses(Set<String> set) {
        this.preferences.edit().putStringSet(PREF_DEFAULT_REPO_ADDRESSES, set).apply();
    }

    public Set<String> getDefaultRepoAddresses(Context context) {
        Set<String> setSingleton = Collections.singleton("empty");
        Set<String> stringSet = this.preferences.getStringSet(PREF_DEFAULT_REPO_ADDRESSES, setSingleton);
        if (stringSet != setSingleton) {
            return stringSet;
        }
        Utils.debugLog(TAG, "Parsing XML to get default repo addresses...");
        HashSet hashSet = new HashSet(DBHelper.getDefaultRepoAddresses(context));
        setPrefDefaultRepoAddresses(hashSet);
        return hashSet;
    }

    public void registerAppsRequiringAntiFeaturesChangeListener(ChangeListener changeListener) {
        this.showAppsRequiringAntiFeaturesListeners.add(changeListener);
    }

    public void unregisterAppsRequiringAntiFeaturesChangeListener(ChangeListener changeListener) {
        this.showAppsRequiringAntiFeaturesListeners.remove(changeListener);
    }

    void registerUnstableUpdatesChangeListener(ChangeListener changeListener) {
        this.unstableUpdatesListeners.add(changeListener);
    }

    public void registerShowIncompatibleListener(ChangeListener changeListener) {
        this.showIncompatibleListeners.add(changeListener);
    }

    public void unregisterShowIncompatibleListener(ChangeListener changeListener) {
        this.showIncompatibleListeners.remove(changeListener);
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        Utils.debugLog(TAG, "Invalidating preference '" + str + "'.");
        uninitialize(str);
        str.hashCode();
        switch (str) {
            case "localRepoHttps":
                Iterator<ChangeListener> it = this.localRepoHttpsListeners.iterator();
                while (it.hasNext()) {
                    it.next().onPreferenceChange();
                }
                break;
            case "unstableUpdates":
                Iterator<ChangeListener> it2 = this.unstableUpdatesListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onPreferenceChange();
                }
                break;
            case "localRepoName":
                Iterator<ChangeListener> it3 = this.localRepoNameListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onPreferenceChange();
                }
                break;
            case "incompatibleVersions":
                Iterator<ChangeListener> it4 = this.showIncompatibleListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onPreferenceChange();
                }
                break;
            case "showAntiFeatures":
                Iterator<ChangeListener> it5 = this.showAppsRequiringAntiFeaturesListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onPreferenceChange();
                }
                break;
        }
    }

    public void registerLocalRepoHttpsListeners(ChangeListener changeListener) {
        this.localRepoHttpsListeners.add(changeListener);
    }

    public void unregisterLocalRepoHttpsListeners(ChangeListener changeListener) {
        this.localRepoHttpsListeners.remove(changeListener);
    }

    public static void setupForTests(Context context) {
        instance = null;
        setup(context);
    }

    public static void setup(Context context) {
        if (instance != null) {
            Log.e(TAG, "Attempted to reinitialize preferences after it has already been initialized in FDroidApp");
            throw new RuntimeException("Attempted to reinitialize preferences after it has already been initialized in FDroidApp");
        }
        instance = new Preferences(context);
    }

    public static Preferences get() {
        Preferences preferences = instance;
        if (preferences != null) {
            return preferences;
        }
        Log.e(TAG, "Attempted to access preferences before it has been initialized in FDroidApp");
        throw new RuntimeException("Attempted to access preferences before it has been initialized in FDroidApp");
    }
}
