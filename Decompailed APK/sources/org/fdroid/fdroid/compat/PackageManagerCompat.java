package org.fdroid.fdroid.compat;

/* JADX INFO: loaded from: classes2.dex */
public class PackageManagerCompat {
    private static final String TAG = "PackageManagerCompat";

    /* JADX WARN: Removed duplicated region for block: B:14:0x001d A[Catch: IllegalArgumentException -> 0x0019, SecurityException -> 0x001b, TryCatch #2 {IllegalArgumentException -> 0x0019, SecurityException -> 0x001b, blocks: (B:7:0x000d, B:9:0x0013, B:15:0x0022, B:14:0x001d), top: B:19:0x000d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void setInstaller(android.content.Context r3, android.content.pm.PackageManager r4, java.lang.String r5) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 30
            if (r0 < r1) goto L7
            return
        L7:
            r1 = 24
            java.lang.String r2 = "PackageManagerCompat"
            if (r0 < r1) goto L1d
            boolean r3 = org.fdroid.fdroid.installer.PrivilegedInstaller.isDefault(r3)     // Catch: java.lang.IllegalArgumentException -> L19 java.lang.SecurityException -> L1b
            if (r3 == 0) goto L1d
            java.lang.String r3 = "org.fdroid.fdroid.privileged"
            r4.setInstallerPackageName(r5, r3)     // Catch: java.lang.IllegalArgumentException -> L19 java.lang.SecurityException -> L1b
            goto L22
        L19:
            r3 = move-exception
            goto L3c
        L1b:
            r3 = move-exception
            goto L3c
        L1d:
            java.lang.String r3 = "org.fdroid.fdroid"
            r4.setInstallerPackageName(r5, r3)     // Catch: java.lang.IllegalArgumentException -> L19 java.lang.SecurityException -> L1b
        L22:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.IllegalArgumentException -> L19 java.lang.SecurityException -> L1b
            r3.<init>()     // Catch: java.lang.IllegalArgumentException -> L19 java.lang.SecurityException -> L1b
            java.lang.String r4 = "Installer package name for "
            r3.append(r4)     // Catch: java.lang.IllegalArgumentException -> L19 java.lang.SecurityException -> L1b
            r3.append(r5)     // Catch: java.lang.IllegalArgumentException -> L19 java.lang.SecurityException -> L1b
            java.lang.String r4 = " set successfully"
            r3.append(r4)     // Catch: java.lang.IllegalArgumentException -> L19 java.lang.SecurityException -> L1b
            java.lang.String r3 = r3.toString()     // Catch: java.lang.IllegalArgumentException -> L19 java.lang.SecurityException -> L1b
            org.fdroid.fdroid.Utils.debugLog(r2, r3)     // Catch: java.lang.IllegalArgumentException -> L19 java.lang.SecurityException -> L1b
            goto L50
        L3c:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r0 = "Could not set installer package name for "
            r4.append(r0)
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.e(r2, r4, r3)
        L50:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.fdroid.fdroid.compat.PackageManagerCompat.setInstaller(android.content.Context, android.content.pm.PackageManager, java.lang.String):void");
    }
}
