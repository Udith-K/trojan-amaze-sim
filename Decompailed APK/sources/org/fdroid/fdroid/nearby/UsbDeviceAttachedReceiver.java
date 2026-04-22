package org.fdroid.fdroid.nearby;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.UriPermission;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import java.util.Iterator;
import org.fdroid.fdroid.views.main.NearbyViewBinder;

/* JADX INFO: loaded from: classes2.dex */
public class UsbDeviceAttachedReceiver extends BroadcastReceiver {
    public static final String TAG = "UsbDeviceAttachedReceiv";

    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, Intent intent) {
        if (intent == null || TextUtils.isEmpty(intent.getAction()) || !"android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(intent.getAction())) {
            Log.i(TAG, "ignoring irrelevant intent: " + intent);
            return;
        }
        Log.i(TAG, "handling intent: " + intent);
        ContentResolver contentResolver = context.getContentResolver();
        Iterator<UriPermission> it = contentResolver.getPersistedUriPermissions().iterator();
        while (it.hasNext()) {
            Uri uri = it.next().getUri();
            ContentObserver contentObserver = new ContentObserver(new Handler()) { // from class: org.fdroid.fdroid.nearby.UsbDeviceAttachedReceiver.1
                @Override // android.database.ContentObserver
                public void onChange(boolean z, Uri uri2) {
                    NearbyViewBinder.updateUsbOtg(context);
                }
            };
            contentResolver.registerContentObserver(uri, true, contentObserver);
            UsbDeviceDetachedReceiver.contentObservers.put(uri, contentObserver);
        }
    }
}
