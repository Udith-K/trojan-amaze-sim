package org.fdroid.fdroid.installer;

import android.content.Context;
import android.text.TextUtils;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.data.Apk;
import org.fdroid.fdroid.data.App;

/* JADX INFO: loaded from: classes2.dex */
public class InstallerFactory {
    private static final String TAG = "InstallerFactory";

    public static Installer create(Context context, App app, Apk apk) {
        if (TextUtils.isEmpty(apk.packageName)) {
            throw new IllegalArgumentException("Apk.packageName must not be empty: " + apk);
        }
        if (!apk.isApk()) {
            Utils.debugLog(TAG, "Using FileInstaller for non-apk file");
            return new FileInstaller(context, app, apk);
        }
        if (PrivilegedInstaller.isDefault(context)) {
            Utils.debugLog(TAG, "privileged extension correctly installed -> PrivilegedInstaller");
            return new PrivilegedInstaller(context, app, apk);
        }
        if (SessionInstallManager.isTargetSdkSupported(apk.targetSdkVersion) && SessionInstallManager.canBeUsed(context)) {
            Utils.debugLog(TAG, "using experimental SessionInstaller, because app targets " + apk.targetSdkVersion);
            return new SessionInstaller(context, app, apk);
        }
        return new DefaultInstaller(context, app, apk);
    }
}
