package org.fdroid.fdroid;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.core.util.Supplier;
import androidx.work.Configuration;
import com.bumptech.glide.Glide;
import info.guardianproject.netcipher.NetCipher;
import info.guardianproject.netcipher.proxy.OrbotHelper;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.exceptions.UndeliverableException;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.IOException;
import java.lang.Thread;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.ByteBuffer;
import java.security.Security;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;
import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.config.CoreConfigurationBuilder;
import org.acra.config.DialogConfigurationBuilder;
import org.acra.config.MailSenderConfigurationBuilder;
import org.apache.commons.net.util.SubnetUtils;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.fdroid.database.FDroidDatabase;
import org.fdroid.database.Repository;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.data.App;
import org.fdroid.fdroid.data.DBHelper;
import org.fdroid.fdroid.installer.ApkFileProvider;
import org.fdroid.fdroid.installer.InstallHistoryService;
import org.fdroid.fdroid.installer.SessionInstallManager;
import org.fdroid.fdroid.nearby.PublicSourceDirProvider;
import org.fdroid.fdroid.nearby.SDCardScannerService;
import org.fdroid.fdroid.nearby.WifiStateChangeService;
import org.fdroid.fdroid.net.ConnectivityMonitorService;
import org.fdroid.fdroid.net.DnsCache;
import org.fdroid.fdroid.net.DownloaderFactory;
import org.fdroid.fdroid.panic.HidingManager;
import org.fdroid.fdroid.receiver.DeviceStorageReceiver;
import org.fdroid.fdroid.work.CleanCacheWorker;
import org.fdroid.fdroid.work.RepoUpdateWorker;
import org.fdroid.index.IndexFormatVersion;
import org.fdroid.index.RepoManager;
import org.fdroid.index.RepoUriBuilder;

/* JADX INFO: loaded from: classes2.dex */
public class FDroidApp extends Application implements Configuration.Provider {
    private static final String ACRA_ID = "org.fdroid.fdroid:acra";
    private static final String TAG = "FDroidApp";
    public static volatile String bssid;
    public static volatile boolean generateNewPort;
    private static FDroidApp instance;
    public static volatile String ipAddressString;
    public static volatile int port;
    public static volatile String queryString;
    public static volatile Repository repo;
    private static RepoManager repoManager;
    private static RepoUpdateManager repoUpdateManager;
    public static volatile SessionInstallManager sessionInstallManager;
    public static volatile String ssid;
    public static volatile SubnetUtils.SubnetInfo subnetInfo;
    private NotificationHelper notificationHelper;
    public static final String SYSTEM_DIR_NAME = Environment.getRootDirectory().getAbsolutePath();
    public static volatile int networkState = 0;
    public static final SubnetUtils.SubnetInfo UNSET_SUBNET_INFO = new SubnetUtils("0.0.0.0/32").getInfo();
    private static final BouncyCastleProvider BOUNCYCASTLE_PROVIDER = new BouncyCastleProvider();

    static {
        enableBouncyCastle();
        RxJavaPlugins.setErrorHandler(new Consumer() { // from class: org.fdroid.fdroid.FDroidApp$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                FDroidApp.lambda$static$0((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$static$0(Throwable th) throws Throwable {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
        if (th instanceof UndeliverableException) {
            th = th.getCause();
        }
        if ((th instanceof InterruptedException) || (uncaughtExceptionHandler = Thread.currentThread().getUncaughtExceptionHandler()) == null || th == null) {
            return;
        }
        uncaughtExceptionHandler.uncaughtException(Thread.currentThread(), th);
    }

    public void applyPureBlackBackgroundInDarkTheme(AppCompatActivity appCompatActivity) {
        if (Preferences.get().isPureBlack()) {
            appCompatActivity.setTheme(R.style.Theme_App_Black);
        }
    }

    /* JADX INFO: renamed from: org.fdroid.fdroid.FDroidApp$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$fdroid$fdroid$Preferences$Theme;

        static {
            int[] iArr = new int[Preferences.Theme.values().length];
            $SwitchMap$org$fdroid$fdroid$Preferences$Theme = iArr;
            try {
                iArr[Preferences.Theme.dark.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$fdroid$fdroid$Preferences$Theme[Preferences.Theme.light.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static void applyTheme() {
        int i = AnonymousClass1.$SwitchMap$org$fdroid$fdroid$Preferences$Theme[Preferences.get().getTheme().ordinal()];
        if (i == 1) {
            AppCompatDelegate.setDefaultNightMode(2);
            return;
        }
        if (i == 2) {
            AppCompatDelegate.setDefaultNightMode(1);
        } else if (Build.VERSION.SDK_INT <= 28) {
            AppCompatDelegate.setDefaultNightMode(3);
        } else {
            AppCompatDelegate.setDefaultNightMode(-1);
        }
    }

    public void setSecureWindow(AppCompatActivity appCompatActivity) {
        if (Preferences.get().preventScreenshots()) {
            appCompatActivity.getWindow().addFlags(8192);
        }
    }

    private static void enableBouncyCastle() {
        if (Build.VERSION.SDK_INT >= 31) {
            Security.removeProvider(BouncyCastleProvider.PROVIDER_NAME);
        }
        Security.addProvider(BOUNCYCASTLE_PROVIDER);
    }

    public static void initWifiSettings() {
        if (generateNewPort) {
            port = new Random().nextInt(8888) + 1024;
            generateNewPort = false;
        } else {
            port = 8888;
        }
        ipAddressString = null;
        subnetInfo = UNSET_SUBNET_INFO;
        ssid = "";
        bssid = "";
        repo = null;
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(android.content.res.Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Languages.setLanguage(this);
        App.systemLocaleList = null;
        updateLanguagesIfNecessary();
    }

    private void updateLanguagesIfNecessary() {
        SharedPreferences atStartTimeSharedPreferences = getAtStartTimeSharedPreferences();
        String string = atStartTimeSharedPreferences.getString("lastLocale", null);
        String string2 = App.getLocales().toString();
        if (!TextUtils.equals(string, string2)) {
            Log.i(TAG, "Locales changed. Old: " + string + " New: " + string2);
            onLanguageChanged(getApplicationContext());
        }
        atStartTimeSharedPreferences.edit().putString("lastLocale", string2).apply();
    }

    public static void onLanguageChanged(Context context) {
        final FDroidDatabase db = DBHelper.getDb(context);
        Single.fromCallable(new Callable() { // from class: org.fdroid.fdroid.FDroidApp$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return FDroidApp.lambda$onLanguageChanged$1(db);
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$onLanguageChanged$1(FDroidDatabase fDroidDatabase) throws Exception {
        long jCurrentTimeMillis = System.currentTimeMillis();
        fDroidDatabase.afterLocalesChanged(App.getLocales());
        Log.d(TAG, "Updating DB locales took: " + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms");
        return Boolean.TRUE;
    }

    @Override // android.app.Application, android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        if (i >= 40) {
            clearImageLoaderMemoryCache();
        }
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        clearImageLoaderMemoryCache();
    }

    private void clearImageLoaderMemoryCache() {
        Glide.get(getApplicationContext()).clearMemory();
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        instance = this;
        Preferences.setup(this);
        DnsCache.setup();
        Languages.setLanguage(this);
        Preferences preferences = Preferences.get();
        if (preferences.promptToSendCrashReports()) {
            ACRA.init(this, new CoreConfigurationBuilder().withReportContent(ReportField.USER_COMMENT, ReportField.PACKAGE_NAME, ReportField.APP_VERSION_NAME, ReportField.ANDROID_VERSION, ReportField.PRODUCT, ReportField.BRAND, ReportField.PHONE_MODEL, ReportField.DISPLAY, ReportField.TOTAL_MEM_SIZE, ReportField.AVAILABLE_MEM_SIZE, ReportField.CUSTOM_DATA, ReportField.STACK_TRACE_HASH, ReportField.STACK_TRACE).withPluginConfigurations(new MailSenderConfigurationBuilder().withMailTo(BuildConfig.ACRA_REPORT_EMAIL).withReportFileName(BuildConfig.ACRA_REPORT_FILE_NAME).withSubject(String.format(Locale.ENGLISH, "%s %s: Crash Report", BuildConfig.APPLICATION_ID, BuildConfig.VERSION_NAME)).build(), new DialogConfigurationBuilder().withResTheme(Integer.valueOf(R.style.Theme_App)).withTitle(getString(R.string.crash_dialog_title)).withText(getString(R.string.crash_dialog_text)).withCommentPrompt(getString(R.string.crash_dialog_comment_prompt)).build()));
        }
        if (isAcraProcess() || HidingManager.isHidden(this)) {
            Log.i(TAG, "This is the ACRA process (or we are hidden), not starting...");
            return;
        }
        registerReceiver(new DeviceStorageReceiver(), new IntentFilter("android.intent.action.DEVICE_STORAGE_LOW"));
        WifiStateChangeService.registerReceiver(this);
        applyTheme();
        configureProxy(preferences);
        preferences.registerUnstableUpdatesChangeListener(new Preferences.ChangeListener() { // from class: org.fdroid.fdroid.FDroidApp$$ExternalSyntheticLambda3
            @Override // org.fdroid.fdroid.Preferences.ChangeListener
            public final void onPreferenceChange() {
                this.f$0.lambda$onCreate$2();
            }
        });
        CleanCacheWorker.schedule(this);
        sessionInstallManager = new SessionInstallManager(getApplicationContext());
        this.notificationHelper = new NotificationHelper(getApplicationContext());
        if (preferences.isIndexNeverUpdated()) {
            preferences.setDefaultForDataOnlyConnection(this);
        }
        networkState = ConnectivityMonitorService.getNetworkState(this);
        ConnectivityMonitorService.registerAndStart(this);
        Utils.debugLog(TAG, "RepoUpdateWorker.scheduleOrCancel()");
        RepoUpdateWorker.scheduleOrCancel(getApplicationContext());
        initWifiSettings();
        WifiStateChangeService.start(this, null);
        preferences.registerLocalRepoHttpsListeners(new Preferences.ChangeListener() { // from class: org.fdroid.fdroid.FDroidApp$$ExternalSyntheticLambda4
            @Override // org.fdroid.fdroid.Preferences.ChangeListener
            public final void onPreferenceChange() {
                this.f$0.lambda$onCreate$3();
            }
        });
        if (preferences.isKeepingInstallHistory()) {
            InstallHistoryService.register(this);
        }
        String string = getString(R.string.install_history_reader_packageName);
        if (!TextUtils.equals(string, getString(R.string.install_history_reader_packageName_UNSET))) {
            grantUriPermission(string, InstallHistoryService.LOG_URI, 67);
        }
        SharedPreferences atStartTimeSharedPreferences = getAtStartTimeSharedPreferences();
        int i = Build.VERSION.SDK_INT;
        if (i != atStartTimeSharedPreferences.getInt("build-version", i)) {
            Utils.runOffUiThread(new Supplier() { // from class: org.fdroid.fdroid.FDroidApp$$ExternalSyntheticLambda5
                @Override // androidx.core.util.Supplier
                public final Object get() {
                    return this.f$0.lambda$onCreate$4();
                }
            }, new androidx.core.util.Consumer() { // from class: org.fdroid.fdroid.FDroidApp$$ExternalSyntheticLambda6
                @Override // androidx.core.util.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$onCreate$5((Boolean) obj);
                }
            });
        }
        atStartTimeSharedPreferences.edit().putInt("build-version", i).apply();
        if (!preferences.isIndexNeverUpdated()) {
            updateLanguagesIfNecessary();
        }
        if (preferences.sendVersionAndUUIDToServers()) {
            queryString = atStartTimeSharedPreferences.getString("http-downloader-query-string", null);
            if (queryString == null) {
                UUID uuidRandomUUID = UUID.randomUUID();
                ByteBuffer byteBufferAllocate = ByteBuffer.allocate(16);
                byteBufferAllocate.putLong(uuidRandomUUID.getMostSignificantBits());
                byteBufferAllocate.putLong(uuidRandomUUID.getLeastSignificantBits());
                String strEncodeToString = Base64.encodeToString(byteBufferAllocate.array(), 11);
                StringBuilder sb = new StringBuilder("id=");
                sb.append(strEncodeToString);
                String strEncode = Uri.encode(Utils.getVersionName(this));
                if (strEncode != null) {
                    sb.append("&client_version=");
                    sb.append(strEncode);
                }
                queryString = sb.toString();
                atStartTimeSharedPreferences.edit().putString("http-downloader-query-string", queryString).apply();
            }
        } else {
            atStartTimeSharedPreferences.edit().remove("http-downloader-query-string").apply();
        }
        if (Preferences.get().isScanRemovableStorageEnabled()) {
            SDCardScannerService.scan(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$2() {
        AppUpdateStatusManager.getInstance(this).checkForUpdates();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$3() {
        WifiStateChangeService.start(getApplicationContext(), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$onCreate$4() {
        DBHelper.resetTransient(getApplicationContext());
        return Boolean.TRUE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreate$5(Boolean bool) {
        RepoUpdateWorker.updateNow(getApplicationContext());
    }

    private boolean isAcraProcess() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) ContextCompat.getSystemService(this, ActivityManager.class)).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        int iMyPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == iMyPid && ACRA_ID.equals(runningAppProcessInfo.processName)) {
                return true;
            }
        }
        return false;
    }

    private SharedPreferences getAtStartTimeSharedPreferences() {
        return getSharedPreferences("at-start-time", 0);
    }

    public void sendViaBluetooth(AppCompatActivity appCompatActivity, int i, String str) {
        String str2;
        Intent intent;
        if (i == 0) {
            return;
        }
        String str3 = null;
        try {
            PackageManager packageManager = getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 128);
            intent = new Intent("android.intent.action.SEND");
            try {
                intent.setType(PublicSourceDirProvider.SHARE_APK_MIME_TYPE);
                intent.putExtra("android.intent.extra.STREAM", ApkFileProvider.getSafeUri(this, packageInfo));
                str2 = null;
                for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 0)) {
                    try {
                        str2 = resolveInfo.activityInfo.packageName;
                        if (!"com.android.bluetooth".equals(str2) && !"com.mediatek.bluetooth".equals(str2)) {
                        }
                        str3 = resolveInfo.activityInfo.name;
                    } catch (PackageManager.NameNotFoundException e) {
                        e = e;
                        Log.e(TAG, "Could not get application info to send via bluetooth", e);
                    } catch (IOException e2) {
                        e = e2;
                        ACRA.getErrorReporter().handleException(new RuntimeException("Error preparing file to send via Bluetooth", e), false);
                    }
                }
            } catch (PackageManager.NameNotFoundException e3) {
                e = e3;
                str2 = null;
            } catch (IOException e4) {
                e = e4;
                str2 = null;
            }
        } catch (PackageManager.NameNotFoundException e5) {
            e = e5;
            str2 = null;
            intent = null;
        } catch (IOException e6) {
            e = e6;
            str2 = null;
            intent = null;
        }
        if (intent != null) {
            if (str3 != null) {
                intent.setClassName(str2, str3);
                appCompatActivity.startActivity(intent);
            } else {
                Toast.makeText(this, R.string.bluetooth_activity_not_found, 0).show();
                appCompatActivity.startActivity(Intent.createChooser(intent, getString(R.string.choose_bt_send)));
            }
        }
    }

    public static void configureProxy(Preferences preferences) {
        if (preferences.isTorEnabled()) {
            NetCipher.useTor();
        } else if (preferences.isProxyEnabled()) {
            NetCipher.setProxy(new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(preferences.getProxyHost(), preferences.getProxyPort())));
        } else {
            NetCipher.clearProxy();
        }
    }

    public static void checkStartTor(Context context, Preferences preferences) {
        if (preferences.isTorEnabled()) {
            OrbotHelper.requestStartTor(context);
        }
    }

    public static Repository createSwapRepo(String str, String str2) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (str2 == null) {
            str2 = "d0ef";
        }
        return new Repository(42L, str, jCurrentTimeMillis, IndexFormatVersion.ONE, str2, 20001L, 42, jCurrentTimeMillis);
    }

    public static Context getInstance() {
        return instance;
    }

    public static RepoManager getRepoManager(Context context) {
        if (repoManager == null) {
            Context applicationContext = context.getApplicationContext();
            repoManager = new RepoManager(applicationContext, DBHelper.getDb(applicationContext), DownloaderFactory.INSTANCE, DownloaderFactory.HTTP_MANAGER, new RepoUriBuilder() { // from class: org.fdroid.fdroid.FDroidApp$$ExternalSyntheticLambda0
                @Override // org.fdroid.index.RepoUriBuilder
                public final Uri getUri(Repository repository, String[] strArr) {
                    return FDroidApp.lambda$getRepoManager$6(repository, strArr);
                }
            });
        }
        return repoManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Uri lambda$getRepoManager$6(Repository repository, String[] strArr) {
        return Utils.getUri(Utils.getRepoAddress(repository), strArr);
    }

    public static RepoUpdateManager getRepoUpdateManager(Context context) {
        if (repoUpdateManager == null) {
            Context applicationContext = context.getApplicationContext();
            repoUpdateManager = new RepoUpdateManager(applicationContext, DBHelper.getDb(applicationContext), getRepoManager(applicationContext));
        }
        return repoUpdateManager;
    }

    @Override // androidx.work.Configuration.Provider
    public Configuration getWorkManagerConfiguration() {
        return new Configuration.Builder().setJobSchedulerJobIdRange(0, PKIFailureInfo.certConfirmed).setMinimumLoggingLevel(6).build();
    }
}
