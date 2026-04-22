package org.fdroid.fdroid.nearby;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.util.HashMap;
import java.util.Iterator;
import org.fdroid.fdroid.views.main.NearbyViewBinder;

/* JADX INFO: loaded from: classes2.dex */
public class UsbDeviceDetachedReceiver extends BroadcastReceiver {
    public static final String TAG = "UsbDeviceDetachedReceiv";
    static final HashMap<Uri, ContentObserver> contentObservers = new HashMap<>();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || TextUtils.isEmpty(intent.getAction()) || !"android.hardware.usb.action.USB_DEVICE_DETACHED".equals(intent.getAction())) {
            Log.i(TAG, "ignoring irrelevant intent: " + intent);
            return;
        }
        Log.i(TAG, "handling intent: " + intent);
        ContentResolver contentResolver = context.getContentResolver();
        NearbyViewBinder.updateUsbOtg(context);
        Iterator<ContentObserver> it = contentObservers.values().iterator();
        while (it.hasNext()) {
            contentResolver.unregisterContentObserver(it.next());
        }
    }
}
