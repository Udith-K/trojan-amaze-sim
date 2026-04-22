package info.guardianproject.panic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class PanicResponder {
    public static String getConnectIntentSender(Activity activity) {
        if (PanicUtils.checkForIntentWithAction(activity, "info.guardianproject.panic.action.CONNECT")) {
            return PanicUtils.getCallingPackageName(activity);
        }
        return null;
    }

    public static boolean checkForDisconnectIntent(Activity activity) {
        if (!PanicUtils.checkForIntentWithAction(activity, "info.guardianproject.panic.action.DISCONNECT")) {
            return false;
        }
        if (TextUtils.equals(PanicUtils.getCallingPackageName(activity), getTriggerPackageName(activity))) {
            setTriggerPackageName(activity, null);
        }
        return true;
    }

    public static String getTriggerPackageName(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("panicResponderTriggerPackageName", null);
    }

    public static void setTriggerPackageName(Activity activity) {
        String str = activity.getIntent().getPackage();
        String callingPackageName = PanicUtils.getCallingPackageName(activity);
        if (str == null && callingPackageName == null) {
            return;
        }
        setTriggerPackageName(activity, callingPackageName);
    }

    public static void setTriggerPackageName(Activity activity, String str) {
        PackageManager packageManager = activity.getPackageManager();
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        String string = defaultSharedPreferences.getString("panicResponderTriggerPackageName", null);
        if (!TextUtils.isEmpty(string)) {
            Intent intent = new Intent("info.guardianproject.panic.action.DISCONNECT");
            intent.setPackage(string);
            if (packageManager.queryIntentActivities(intent, 0).size() > 0) {
                activity.startActivityForResult(intent, 0);
            }
        }
        if (TextUtils.isEmpty(str) || str.equals("DEFAULT")) {
            defaultSharedPreferences.edit().remove("panicResponderTriggerPackageName").apply();
            return;
        }
        defaultSharedPreferences.edit().putString("panicResponderTriggerPackageName", str).apply();
        Intent intent2 = new Intent("info.guardianproject.panic.action.CONNECT");
        intent2.setPackage(str);
        if (packageManager.queryIntentActivities(intent2, 0).size() > 0) {
            activity.startActivityForResult(intent2, 0);
        }
    }

    public static List resolveTriggerApps(PackageManager packageManager) {
        List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(new Intent("info.guardianproject.panic.action.CONNECT"), 0);
        if (listQueryIntentActivities.size() == 0) {
            return listQueryIntentActivities;
        }
        ArrayList arrayList = new ArrayList(listQueryIntentActivities.size());
        List<ResolveInfo> listQueryIntentActivities2 = packageManager.queryIntentActivities(new Intent("info.guardianproject.panic.action.TRIGGER"), 0);
        HashSet hashSet = new HashSet(listQueryIntentActivities2.size());
        Iterator<ResolveInfo> it = listQueryIntentActivities2.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().activityInfo.packageName);
        }
        for (ResolveInfo resolveInfo : listQueryIntentActivities) {
            if (!hashSet.contains(resolveInfo.activityInfo.packageName)) {
                arrayList.add(resolveInfo);
            }
        }
        return arrayList;
    }

    public static boolean receivedTriggerFromConnectedApp(Activity activity) {
        if (!Panic.isTriggerIntent(activity.getIntent())) {
            return false;
        }
        String callingPackageName = PanicUtils.getCallingPackageName(activity);
        return !TextUtils.isEmpty(callingPackageName) && TextUtils.equals(callingPackageName, getTriggerPackageName(activity));
    }
}
