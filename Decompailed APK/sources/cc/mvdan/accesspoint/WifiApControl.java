package cc.mvdan.accesspoint;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class WifiApControl {
    private static Method getWifiApConfigurationMethod;
    private static Method getWifiApStateMethod;
    private static WifiApControl instance;
    private static Method isWifiApEnabledMethod;
    private static Method setWifiApEnabledMethod;
    private final String deviceName;
    private final WifiManager wm;

    private WifiApControl(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        this.wm = wifiManager;
        this.deviceName = getDeviceName(wifiManager);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static cc.mvdan.accesspoint.WifiApControl getInstance(android.content.Context r12) {
        /*
            cc.mvdan.accesspoint.WifiApControl r0 = cc.mvdan.accesspoint.WifiApControl.instance
            if (r0 != 0) goto L82
            boolean r0 = android.provider.Settings.System.canWrite(r12)
            r1 = 0
            java.lang.String r2 = "WifiApControl"
            if (r0 != 0) goto L13
            java.lang.String r12 = "6.0 or later, but haven't been granted WRITE_SETTINGS!"
            android.util.Log.e(r2, r12)
            return r1
        L13:
            java.lang.Class<android.net.wifi.WifiManager> r0 = android.net.wifi.WifiManager.class
            java.lang.reflect.Method[] r0 = r0.getDeclaredMethods()     // Catch: java.lang.Throwable -> L39
            int r3 = r0.length     // Catch: java.lang.Throwable -> L39
            r4 = 0
            r5 = r4
        L1c:
            if (r5 >= r3) goto L71
            r6 = r0[r5]     // Catch: java.lang.Throwable -> L39
            java.lang.String r7 = r6.getName()     // Catch: java.lang.Throwable -> L39
            int r8 = r7.hashCode()     // Catch: java.lang.Throwable -> L39
            r9 = 3
            r10 = 2
            r11 = 1
            switch(r8) {
                case -144339141: goto L4f;
                case 591399831: goto L45;
                case 678347635: goto L3b;
                case 2135709180: goto L2f;
                default: goto L2e;
            }     // Catch: java.lang.Throwable -> L39
        L2e:
            goto L59
        L2f:
            java.lang.String r8 = "getWifiApConfiguration"
            boolean r7 = r7.equals(r8)     // Catch: java.lang.Throwable -> L39
            if (r7 == 0) goto L59
            r7 = r4
            goto L5a
        L39:
            r12 = move-exception
            goto L7c
        L3b:
            java.lang.String r8 = "isWifiApEnabled"
            boolean r7 = r7.equals(r8)     // Catch: java.lang.Throwable -> L39
            if (r7 == 0) goto L59
            r7 = r10
            goto L5a
        L45:
            java.lang.String r8 = "getWifiApState"
            boolean r7 = r7.equals(r8)     // Catch: java.lang.Throwable -> L39
            if (r7 == 0) goto L59
            r7 = r11
            goto L5a
        L4f:
            java.lang.String r8 = "setWifiApEnabled"
            boolean r7 = r7.equals(r8)     // Catch: java.lang.Throwable -> L39
            if (r7 == 0) goto L59
            r7 = r9
            goto L5a
        L59:
            r7 = -1
        L5a:
            if (r7 == 0) goto L6c
            if (r7 == r11) goto L69
            if (r7 == r10) goto L66
            if (r7 == r9) goto L63
            goto L6e
        L63:
            cc.mvdan.accesspoint.WifiApControl.setWifiApEnabledMethod = r6     // Catch: java.lang.Throwable -> L39
            goto L6e
        L66:
            cc.mvdan.accesspoint.WifiApControl.isWifiApEnabledMethod = r6     // Catch: java.lang.Throwable -> L39
            goto L6e
        L69:
            cc.mvdan.accesspoint.WifiApControl.getWifiApStateMethod = r6     // Catch: java.lang.Throwable -> L39
            goto L6e
        L6c:
            cc.mvdan.accesspoint.WifiApControl.getWifiApConfigurationMethod = r6     // Catch: java.lang.Throwable -> L39
        L6e:
            int r5 = r5 + 1
            goto L1c
        L71:
            cc.mvdan.accesspoint.WifiApControl r0 = new cc.mvdan.accesspoint.WifiApControl     // Catch: java.lang.Throwable -> L39
            r0.<init>(r12)     // Catch: java.lang.Throwable -> L39
            cc.mvdan.accesspoint.WifiApControl.instance = r0     // Catch: java.lang.Throwable -> L39
            r0.isEnabled()     // Catch: java.lang.Throwable -> L39
            goto L82
        L7c:
            java.lang.String r0 = "WifiManager failed to init"
            android.util.Log.e(r2, r0, r12)
            return r1
        L82:
            cc.mvdan.accesspoint.WifiApControl r12 = cc.mvdan.accesspoint.WifiApControl.instance
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: cc.mvdan.accesspoint.WifiApControl.getInstance(android.content.Context):cc.mvdan.accesspoint.WifiApControl");
    }

    private static String getDeviceName(WifiManager wifiManager) {
        Log.w("WifiApControl", "6.0 or later, unaccessible MAC - falling back to the default device name: wlan0");
        return "wlan0";
    }

    private static Object invokeQuietly(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            Log.e("WifiApControl", "", e);
            return null;
        }
    }

    public boolean isWifiApEnabled() {
        Object objInvokeQuietly = invokeQuietly(isWifiApEnabledMethod, this.wm, new Object[0]);
        if (objInvokeQuietly == null) {
            return false;
        }
        return ((Boolean) objInvokeQuietly).booleanValue();
    }

    public boolean isEnabled() {
        return isWifiApEnabled();
    }

    public WifiConfiguration getWifiApConfiguration() {
        Object objInvokeQuietly = invokeQuietly(getWifiApConfigurationMethod, this.wm, new Object[0]);
        if (objInvokeQuietly == null) {
            return null;
        }
        return (WifiConfiguration) objInvokeQuietly;
    }

    public WifiConfiguration getConfiguration() {
        return getWifiApConfiguration();
    }

    public boolean setWifiApEnabled(WifiConfiguration wifiConfiguration, boolean z) {
        Object objInvokeQuietly = invokeQuietly(setWifiApEnabledMethod, this.wm, wifiConfiguration, Boolean.valueOf(z));
        if (objInvokeQuietly == null) {
            return false;
        }
        return ((Boolean) objInvokeQuietly).booleanValue();
    }

    public boolean setEnabled(WifiConfiguration wifiConfiguration, boolean z) {
        return setWifiApEnabled(wifiConfiguration, z);
    }

    public boolean enable() {
        return setEnabled(getConfiguration(), true);
    }

    public boolean disable() {
        return setEnabled(null, false);
    }
}
