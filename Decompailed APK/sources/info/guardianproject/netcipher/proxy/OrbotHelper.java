package info.guardianproject.netcipher.proxy;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import org.fdroid.fdroid.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public abstract class OrbotHelper {
    public static boolean isOrbotInstalled(Context context) {
        return isAppInstalled(context, "org.torproject.android");
    }

    public static boolean isTorServicesInstalled(Context context) {
        return isAppInstalled(context, "org.torproject.torservices");
    }

    private static boolean isAppInstalled(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean requestStartTor(final Context context) {
        Intent torStartIntent = getTorStartIntent(context);
        String str = torStartIntent.getPackage();
        if (str == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 26) {
            context.sendBroadcast(torStartIntent);
        } else if ("org.torproject.torservices".equals(str)) {
            torStartIntent.setComponent(new ComponentName("org.torproject.torservices", "org.torproject.torservices.WakeUpService"));
            context.bindService(torStartIntent, new ServiceConnection() { // from class: info.guardianproject.netcipher.proxy.OrbotHelper.1
                @Override // android.content.ServiceConnection
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    Context context2 = context;
                    context2.sendBroadcast(OrbotHelper.getTorStartIntent(context2));
                    context.unbindService(this);
                }

                @Override // android.content.ServiceConnection
                public void onServiceDisconnected(ComponentName componentName) {
                    Context context2 = context;
                    context2.sendBroadcast(OrbotHelper.getTorStartIntent(context2));
                }
            }, 1);
        } else {
            if (!"org.torproject.android".equals(str)) {
                return false;
            }
            context.sendBroadcast(torStartIntent);
        }
        return true;
    }

    public static Intent getOrbotInstallIntent(Context context) {
        String str;
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("market://details?id=org.torproject.android"));
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 0)) {
            Log.i("OrbotHelper", "market: " + resolveInfo.activityInfo.packageName);
            if (TextUtils.equals(resolveInfo.activityInfo.packageName, BuildConfig.APPLICATION_ID) || TextUtils.equals(resolveInfo.activityInfo.packageName, "com.android.vending")) {
                str = resolveInfo.activityInfo.packageName;
                break;
            }
        }
        str = null;
        if (str == null) {
            intent.setData(Uri.parse("https://f-droid.org/repository/browse/?fdid=org.torproject.android"));
        } else {
            intent.setPackage(str);
        }
        return intent;
    }

    public static Intent getTorStartIntent(Context context) {
        Intent intent = new Intent("org.torproject.android.intent.action.START");
        intent.putExtra("org.torproject.android.intent.extra.PACKAGE_NAME", context.getPackageName());
        if (isTorServicesInstalled(context)) {
            intent.setPackage("org.torproject.torservices");
        } else if (isOrbotInstalled(context)) {
            intent.setPackage("org.torproject.android");
        }
        return intent;
    }
}
