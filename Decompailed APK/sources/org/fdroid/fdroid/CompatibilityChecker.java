package org.fdroid.fdroid;

import android.content.Context;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.fdroid.CompatibilityCheckerUtils;
import org.fdroid.fdroid.data.Apk;

/* JADX INFO: loaded from: classes2.dex */
public class CompatibilityChecker {
    public static final String TAG = "Compatibility";
    private final Context context;
    private final String[] cpuAbis;
    private final Set<String> features;
    private final boolean forceTouchApps = Preferences.get().forceTouchApps();

    public CompatibilityChecker(Context context) {
        this.context = context.getApplicationContext();
        PackageManager packageManager = context.getPackageManager();
        this.features = new HashSet();
        if (packageManager != null && packageManager.getSystemAvailableFeatures() != null) {
            for (FeatureInfo featureInfo : packageManager.getSystemAvailableFeatures()) {
                this.features.add(featureInfo.name);
            }
        }
        this.cpuAbis = Build.SUPPORTED_ABIS;
    }

    private boolean compatibleApi(String[] strArr) {
        if (strArr == null) {
            return true;
        }
        for (String str : this.cpuAbis) {
            for (String str2 : strArr) {
                if (str2.equals(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<String> getIncompatibleReasons(Apk apk) {
        ArrayList arrayList = new ArrayList();
        int i = Build.VERSION.SDK_INT;
        int i2 = apk.minSdkVersion;
        if (i < i2) {
            arrayList.add(this.context.getString(R.string.minsdk_or_later, Utils.getAndroidVersionName(i2)));
        } else {
            int i3 = apk.maxSdkVersion;
            if (i > i3) {
                arrayList.add(this.context.getString(R.string.up_to_maxsdk, Utils.getAndroidVersionName(i3)));
            }
        }
        int iMinInstallableTargetSdk = CompatibilityCheckerUtils.INSTANCE.minInstallableTargetSdk();
        if (apk.targetSdkVersion < iMinInstallableTargetSdk) {
            arrayList.add(this.context.getString(R.string.targetsdk_or_later, Utils.getAndroidVersionName(iMinInstallableTargetSdk)));
        }
        String[] strArr = apk.features;
        if (strArr != null) {
            for (String str : strArr) {
                if ((!this.forceTouchApps || !"android.hardware.touchscreen".equals(str)) && !this.features.contains(str)) {
                    Collections.addAll(arrayList, str.split(","));
                }
            }
        }
        if (!compatibleApi(apk.nativecode)) {
            Collections.addAll(arrayList, apk.nativecode);
        }
        return arrayList;
    }
}
