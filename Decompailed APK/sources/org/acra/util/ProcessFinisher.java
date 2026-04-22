package org.acra.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.acra.ACRA;
import org.acra.builder.LastActivityManager;
import org.acra.config.CoreConfiguration;
import org.acra.util.SystemServices;

/* JADX INFO: compiled from: ProcessFinisher.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class ProcessFinisher {
    private final CoreConfiguration config;
    private final Context context;
    private final LastActivityManager lastActivityManager;

    public final void finishLastActivity(Thread thread) {
        if (ACRA.DEV_LOGGING) {
            ACRA.log.d(ACRA.LOG_TAG, "Finishing activities prior to killing the Process");
        }
        boolean z = false;
        for (final Activity activity : this.lastActivityManager.getLastActivities()) {
            Runnable runnable = new Runnable() { // from class: org.acra.util.ProcessFinisher$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ProcessFinisher.finishLastActivity$lambda$2(activity);
                }
            };
            if (thread == activity.getMainLooper().getThread()) {
                runnable.run();
            } else {
                activity.runOnUiThread(runnable);
                z = true;
            }
        }
        if (z) {
            this.lastActivityManager.waitForAllActivitiesDestroy(100);
        }
        this.lastActivityManager.clearLastActivities();
    }

    public ProcessFinisher(Context context, CoreConfiguration config, LastActivityManager lastActivityManager) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(lastActivityManager, "lastActivityManager");
        this.context = context;
        this.config = config;
        this.lastActivityManager = lastActivityManager;
    }

    public final void endApplication() {
        stopServices();
        killProcessAndExit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void finishLastActivity$lambda$2(Activity activity) {
        activity.finish();
        if (ACRA.DEV_LOGGING) {
            ACRA.log.d(ACRA.LOG_TAG, "Finished " + activity.getClass());
        }
    }

    private final void stopServices() {
        if (this.config.getStopServicesOnCrash()) {
            try {
                List<ActivityManager.RunningServiceInfo> runningServices = SystemServices.getActivityManager(this.context).getRunningServices(Integer.MAX_VALUE);
                int iMyPid = Process.myPid();
                for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
                    if (runningServiceInfo.pid == iMyPid) {
                        String className = runningServiceInfo.service.getClassName();
                        Intrinsics.checkNotNullExpressionValue(className, "getClassName(...)");
                        if (!StringsKt.startsWith$default(className, "org.acra", false, 2, (Object) null)) {
                            try {
                                Intent intent = new Intent();
                                intent.setComponent(runningServiceInfo.service);
                                this.context.stopService(intent);
                            } catch (SecurityException unused) {
                                if (ACRA.DEV_LOGGING) {
                                    ACRA.log.d(ACRA.LOG_TAG, "Unable to stop Service " + runningServiceInfo.service.getClassName() + ". Permission denied");
                                }
                            }
                        }
                    }
                }
            } catch (SystemServices.ServiceNotReachedException e) {
                ACRA.log.e(ACRA.LOG_TAG, "Unable to stop services", e);
            }
        }
    }

    private final void killProcessAndExit() {
        Process.killProcess(Process.myPid());
        System.exit(10);
        throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
    }
}
