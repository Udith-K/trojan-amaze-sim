package org.fdroid.fdroid.work;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import ch.qos.logback.core.CoreConstants;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.fdroid.download.HttpPoster;
import org.fdroid.fdroid.Preferences;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.compat.LocaleCompat;
import org.fdroid.fdroid.installer.InstallHistoryService;
import org.fdroid.fdroid.net.DownloaderFactory;
import org.fdroid.fdroid.work.FDroidMetricsWorker;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class FDroidMetricsWorker extends Worker {
    public static final String TAG = "FDroidMetricsWorker";
    static SimpleDateFormat weekFormatter = new SimpleDateFormat("yyyy ww", Locale.ENGLISH);
    private static final ArrayList<MatomoEvent> EVENTS = new ArrayList<>();

    static boolean isTimestampInReportingWeek(long j, long j2) {
        return j < j2 && j2 < CoreConstants.MILLIS_IN_ONE_WEEK + j;
    }

    public FDroidMetricsWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    public static void schedule(Context context) {
        WorkManager workManager = WorkManager.getInstance(context);
        long millis = TimeUnit.DAYS.toMillis(7L);
        Constraints.Builder requiresBatteryNotLow = new Constraints.Builder().setRequiresCharging(true).setRequiresBatteryNotLow(true);
        if (Build.VERSION.SDK_INT >= 24) {
            requiresBatteryNotLow.setTriggerContentMaxDelay(millis, TimeUnit.MILLISECONDS);
            requiresBatteryNotLow.setRequiresDeviceIdle(true);
        }
        workManager.enqueueUniquePeriodicWork(TAG, ExistingPeriodicWorkPolicy.REPLACE, (PeriodicWorkRequest) ((PeriodicWorkRequest.Builder) new PeriodicWorkRequest.Builder(FDroidMetricsWorker.class, millis, TimeUnit.MILLISECONDS).setConstraints(requiresBatteryNotLow.build())).build());
        Utils.debugLog(TAG, "Scheduled periodic work");
    }

    public static void cancel(Context context) {
        WorkManager.getInstance(context).cancelUniqueWork(TAG);
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        String strGenerateReport = generateReport(getApplicationContext());
        if (strGenerateReport != null) {
            try {
                new HttpPoster(DownloaderFactory.HTTP_MANAGER, "https://metrics.cleaninsights.org/cleaninsights.php").post(strGenerateReport);
            } catch (IOException e) {
                e.printStackTrace();
                return ListenableWorker.Result.retry();
            }
        }
        return ListenableWorker.Result.success();
    }

    static long toCleanInsightsTimestamp(long j) {
        return toCleanInsightsTimestamp(j, j);
    }

    static long toCleanInsightsTimestamp(long j, long j2) {
        return (((j2 / CoreConstants.MILLIS_IN_ONE_WEEK) * CoreConstants.MILLIS_IN_ONE_WEEK) + (j2 - j)) / 1000;
    }

    static long getReportingWeekStart() {
        return getReportingWeekStart(System.currentTimeMillis());
    }

    static long getReportingWeekStart(long j) {
        try {
            Date date = new Date(j - CoreConstants.MILLIS_IN_ONE_WEEK);
            SimpleDateFormat simpleDateFormat = weekFormatter;
            return simpleDateFormat.parse(simpleDateFormat.format(date)).getTime();
        } catch (ParseException unused) {
            return 0L;
        }
    }

    static Collection<? extends MatomoEvent> parseInstallHistoryCsv(Context context, long j) {
        try {
            List lines = FileUtils.readLines(InstallHistoryService.getInstallHistoryFile(context), Charset.defaultCharset());
            ArrayList<RawEvent> arrayList = new ArrayList(lines.size());
            Iterator it = lines.iterator();
            while (it.hasNext()) {
                RawEvent rawEvent = new RawEvent(((String) it.next()).split(","));
                if (isTimestampInReportingWeek(j, rawEvent.timestamp)) {
                    arrayList.add(rawEvent);
                }
            }
            Collections.sort(arrayList, new Comparator() { // from class: org.fdroid.fdroid.work.FDroidMetricsWorker$$ExternalSyntheticLambda0
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return FDroidMetricsWorker.lambda$parseInstallHistoryCsv$0((FDroidMetricsWorker.RawEvent) obj, (FDroidMetricsWorker.RawEvent) obj2);
                }
            });
            ArrayList arrayList2 = new ArrayList();
            RawEvent rawEvent2 = new RawEvent(new String[]{"0", "", "0", ""});
            for (RawEvent rawEvent3 : arrayList) {
                if (!rawEvent2.equals(rawEvent3)) {
                    arrayList2.add(new MatomoEvent(rawEvent3));
                    rawEvent2 = rawEvent3;
                }
            }
            return arrayList2;
        } catch (IOException unused) {
            return Collections.emptyList();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$parseInstallHistoryCsv$0(RawEvent rawEvent, RawEvent rawEvent2) {
        int iCompareTo = rawEvent.applicationId.compareTo(rawEvent2.applicationId);
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        int iCompare = Long.compare(rawEvent.versionCode, rawEvent2.versionCode);
        return iCompare != 0 ? iCompare : Long.compare(rawEvent.timestamp, rawEvent2.timestamp);
    }

    public static String generateReport(Context context) {
        long reportingWeekStart = getReportingWeekStart();
        CleanInsightsReport cleanInsightsReport = new CleanInsightsReport();
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
        Collections.sort(installedPackages, new Comparator() { // from class: org.fdroid.fdroid.work.FDroidMetricsWorker$$ExternalSyntheticLambda1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return FDroidMetricsWorker.lambda$generateReport$1((PackageInfo) obj, (PackageInfo) obj2);
            }
        });
        ArrayList<MatomoEvent> arrayList = EVENTS;
        arrayList.add(getDeviceEvent(reportingWeekStart, "isPrivilegedInstallerEnabled", Boolean.valueOf(Preferences.get().isPrivilegedInstallerEnabled())));
        arrayList.add(getDeviceEvent(reportingWeekStart, "Build.VERSION.SDK_INT", Integer.valueOf(Build.VERSION.SDK_INT)));
        arrayList.add(getDeviceEvent(reportingWeekStart, "Build.SUPPORTED_ABIS", Arrays.toString(Build.SUPPORTED_ABIS)));
        for (PackageInfo packageInfo : installedPackages) {
            if (isTimestampInReportingWeek(reportingWeekStart, packageInfo.firstInstallTime)) {
                addFirstInstallEvent(packageManager, packageInfo);
            }
            if (isTimestampInReportingWeek(reportingWeekStart, packageInfo.lastUpdateTime)) {
                addLastUpdateTimeEvent(packageManager, packageInfo);
            }
        }
        ArrayList<MatomoEvent> arrayList2 = EVENTS;
        arrayList2.addAll(parseInstallHistoryCsv(context, reportingWeekStart));
        cleanInsightsReport.events = (MatomoEvent[]) arrayList2.toArray(new MatomoEvent[0]);
        try {
            return cleanInsightsReport.getJsonString();
        } catch (JSONException e) {
            Log.e(TAG, "Error getting json string", e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$generateReport$1(PackageInfo packageInfo, PackageInfo packageInfo2) {
        return packageInfo.packageName.compareTo(packageInfo2.packageName);
    }

    private static class CleanInsightsReport {
        MatomoEvent[] events;
        final long idsite;
        final String lang;
        final String ua;

        private CleanInsightsReport() {
            this.events = new MatomoEvent[0];
            this.idsite = 3L;
            this.lang = LocaleCompat.getDefault().getLanguage();
            this.ua = Utils.getUserAgent();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getJsonString() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            for (MatomoEvent matomoEvent : this.events) {
                jSONArray.put(matomoEvent.getJSONObject());
            }
            jSONObject.put("events", jSONArray);
            jSONObject.put("idsite", 3L);
            jSONObject.put("lang", this.lang);
            jSONObject.put("ua", this.ua);
            return jSONObject.toString(2);
        }
    }

    private static void addFirstInstallEvent(PackageManager packageManager, PackageInfo packageInfo) {
        addInstallerEvent(packageManager, packageInfo, "PackageInfo.firstInstall", packageInfo.firstInstallTime);
    }

    private static void addLastUpdateTimeEvent(PackageManager packageManager, PackageInfo packageInfo) {
        addInstallerEvent(packageManager, packageInfo, "PackageInfo.lastUpdateTime", packageInfo.lastUpdateTime);
    }

    private static void addInstallerEvent(PackageManager packageManager, PackageInfo packageInfo, String str, long j) {
        MatomoEvent matomoEvent = new MatomoEvent(j);
        matomoEvent.category = "APK";
        matomoEvent.action = str;
        matomoEvent.name = packageManager.getInstallerPackageName(packageInfo.packageName);
        matomoEvent.times = 1L;
        for (MatomoEvent matomoEvent2 : EVENTS) {
            if (matomoEvent2.equals(matomoEvent)) {
                matomoEvent2.times++;
                return;
            }
        }
        EVENTS.add(matomoEvent);
    }

    private static MatomoEvent getDeviceEvent(long j, String str, Object obj) {
        MatomoEvent matomoEvent = new MatomoEvent(j);
        matomoEvent.category = "device";
        matomoEvent.action = str;
        matomoEvent.name = String.valueOf(obj);
        matomoEvent.times = 1L;
        return matomoEvent;
    }

    static class MatomoEvent {
        String action;
        String category;
        String name;
        final long period_end;
        final long period_start;
        long times;

        MatomoEvent(long j) {
            this.times = 0L;
            long cleanInsightsTimestamp = FDroidMetricsWorker.toCleanInsightsTimestamp(j);
            this.period_end = cleanInsightsTimestamp;
            this.period_start = cleanInsightsTimestamp - 604800;
        }

        MatomoEvent(RawEvent rawEvent) {
            this(rawEvent.timestamp);
            this.category = "package";
            this.action = rawEvent.action;
            this.name = rawEvent.applicationId;
            this.times = 1L;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public JSONObject getJSONObject() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("category", this.category);
            jSONObject.put("action", this.action);
            jSONObject.put("name", this.name);
            jSONObject.put("period_start", this.period_start);
            jSONObject.put("period_end", this.period_end);
            jSONObject.put("times", this.times);
            return jSONObject;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            MatomoEvent matomoEvent = (MatomoEvent) obj;
            return this.period_start == matomoEvent.period_start && this.period_end == matomoEvent.period_end && TextUtils.equals(this.category, matomoEvent.category) && TextUtils.equals(this.action, matomoEvent.action) && TextUtils.equals(this.name, matomoEvent.name);
        }

        public int hashCode() {
            return Objects.hash(this.category, this.action, this.name, Long.valueOf(this.period_start), Long.valueOf(this.period_end), Long.valueOf(this.times));
        }
    }

    static class RawEvent {
        final String action;
        final String applicationId;
        final long timestamp;
        final long versionCode;

        RawEvent(String[] strArr) {
            this.timestamp = Long.parseLong(strArr[0]);
            this.applicationId = strArr[1];
            this.versionCode = Long.parseLong(strArr[2]);
            this.action = strArr[3];
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            RawEvent rawEvent = (RawEvent) obj;
            return this.versionCode == rawEvent.versionCode && this.applicationId.equals(rawEvent.applicationId) && this.action.equals(rawEvent.action);
        }

        public int hashCode() {
            return Objects.hash(this.applicationId, Long.valueOf(this.versionCode), this.action);
        }

        public String toString() {
            return "RawEvent{timestamp=" + this.timestamp + ", applicationId='" + this.applicationId + CoreConstants.SINGLE_QUOTE_CHAR + ", versionCode=" + this.versionCode + ", action='" + this.action + CoreConstants.SINGLE_QUOTE_CHAR + CoreConstants.CURLY_RIGHT;
        }
    }
}
