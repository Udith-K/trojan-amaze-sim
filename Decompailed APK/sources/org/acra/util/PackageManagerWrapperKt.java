package org.acra.util;

import android.content.pm.PackageInfo;
import android.os.Build;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PackageManagerWrapper.kt */
/* JADX INFO: loaded from: classes2.dex */
public abstract class PackageManagerWrapperKt {
    public static final long getVersionCodeLong(PackageInfo packageInfo) {
        Intrinsics.checkNotNullParameter(packageInfo, "<this>");
        if (Build.VERSION.SDK_INT >= 28) {
            return packageInfo.getLongVersionCode();
        }
        return packageInfo.versionCode;
    }
}
