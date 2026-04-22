package org.fdroid.fdroid.installer;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Build;
import androidx.core.content.FileProvider;
import java.io.File;
import java.io.IOException;
import org.fdroid.fdroid.data.Apk;
import org.fdroid.fdroid.data.SanitizedFile;

/* JADX INFO: loaded from: classes2.dex */
public class ApkFileProvider extends FileProvider {
    private static final String AUTHORITY = "org.fdroid.fdroid.installer.ApkFileProvider";

    public static Uri getSafeUri(Context context, PackageInfo packageInfo) throws IOException {
        return getSafeUri(context, ApkCache.copyInstalledApkToFiles(context, packageInfo), true);
    }

    static Uri getSafeUri(Context context, Uri uri, Apk apk) throws IOException {
        return getSafeUri(context, ApkCache.copyApkFromCacheToFiles(context, new File(uri.getPath()), apk), Build.VERSION.SDK_INT >= 24 && apk.isApk());
    }

    private static Uri getSafeUri(Context context, SanitizedFile sanitizedFile, boolean z) {
        if (z) {
            Uri uriForFile = FileProvider.getUriForFile(context, AUTHORITY, sanitizedFile);
            context.grantUriPermission("org.fdroid.fdroid.privileged", uriForFile, 1);
            context.grantUriPermission("com.android.bluetooth", uriForFile, 1);
            context.grantUriPermission("com.mediatek.bluetooth", uriForFile, 1);
            return uriForFile;
        }
        sanitizedFile.setReadable(true, false);
        return Uri.fromFile(sanitizedFile);
    }
}
