package org.fdroid.fdroid.privileged.views;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import org.fdroid.fdroid.data.Apk;

/* JADX INFO: loaded from: classes2.dex */
public class AppDiff {
    public final PackageInfo apkPackageInfo;
    public final ApplicationInfo installedApplicationInfo;

    public AppDiff(Context context, Apk apk) {
        String str;
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = new PackageInfo();
        this.apkPackageInfo = packageInfo;
        packageInfo.packageName = apk.packageName;
        packageInfo.applicationInfo = new ApplicationInfo();
        packageInfo.requestedPermissions = apk.requestedPermissions;
        String str2 = packageInfo.packageName;
        String[] strArrCanonicalToCurrentPackageNames = packageManager.canonicalToCurrentPackageNames(new String[]{str2});
        if (strArrCanonicalToCurrentPackageNames != null && strArrCanonicalToCurrentPackageNames.length > 0 && (str = strArrCanonicalToCurrentPackageNames[0]) != null) {
            packageInfo.packageName = str;
            packageInfo.applicationInfo.packageName = str;
            str2 = str;
        }
        ApplicationInfo applicationInfo = null;
        try {
            ApplicationInfo applicationInfo2 = packageManager.getApplicationInfo(str2, 8192);
            if ((applicationInfo2.flags & 8388608) != 0) {
                applicationInfo = applicationInfo2;
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        this.installedApplicationInfo = applicationInfo;
    }
}
