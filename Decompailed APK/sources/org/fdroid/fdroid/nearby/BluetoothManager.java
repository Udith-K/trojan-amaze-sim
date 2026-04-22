package org.fdroid.fdroid.nearby;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.Utils;
import org.fdroid.fdroid.nearby.peers.BluetoothPeer;

/* JADX INFO: loaded from: classes2.dex */
public class BluetoothManager {
    public static final String ACTION_FOUND = "BluetoothNewPeer";
    public static final String ACTION_STATUS = "BluetoothStatus";
    public static final String EXTRA_PEER = "extraBluetoothPeer";
    public static final String EXTRA_STATUS = "BluetoothStatusExtra";
    public static final int STATUS_ERROR = 65535;
    public static final int STATUS_STARTED = 1;
    public static final int STATUS_STARTING = 0;
    public static final int STATUS_STOPPED = 3;
    public static final int STATUS_STOPPING = 2;
    private static final int STOP = 5709;
    private static final String TAG = "BluetoothManager";
    private static BluetoothAdapter bluetoothAdapter;
    private static final BroadcastReceiver bluetoothDeviceFound = new BroadcastReceiver() { // from class: org.fdroid.fdroid.nearby.BluetoothManager.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context2, Intent intent) {
            BluetoothManager.sendFoundBroadcast(context2, (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"));
        }
    };
    private static WeakReference<Context> context;
    private static Handler handler;
    private static volatile HandlerThread handlerThread;

    public static void setName(Context context2, String str) {
    }

    public static void stop(Context context2) {
        context = new WeakReference<>(context2);
        if (handler == null || handlerThread == null || !handlerThread.isAlive()) {
            Log.w(TAG, "handlerThread is already stopped, doing nothing!");
            sendBroadcast(3, null);
        } else {
            sendBroadcast(2, null);
            handler.sendEmptyMessage(STOP);
        }
    }

    public static void start(final Context context2) {
        if (ContextCompat.checkSelfPermission(context2, "android.permission.BLUETOOTH_CONNECT") != 0) {
            return;
        }
        context = new WeakReference<>(context2);
        int i = 1;
        if (handlerThread != null && handlerThread.isAlive()) {
            sendBroadcast(1, null);
            return;
        }
        sendBroadcast(0, null);
        final BluetoothServer bluetoothServer = new BluetoothServer(context2.getFilesDir());
        handlerThread = new HandlerThread(TAG, i) { // from class: org.fdroid.fdroid.nearby.BluetoothManager.1
            @Override // android.os.HandlerThread
            protected void onLooperPrepared() {
                LocalBroadcastManager.getInstance(context2).registerReceiver(BluetoothManager.bluetoothDeviceFound, new IntentFilter("android.bluetooth.device.action.FOUND"));
                BluetoothManager.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                String name = BluetoothManager.bluetoothAdapter.getName();
                if (name != null) {
                    SwapService.putBluetoothNameBeforeSwap(name);
                }
                if (!BluetoothManager.bluetoothAdapter.enable()) {
                    BluetoothManager.sendBroadcast(65535, context2.getString(R.string.swap_error_cannot_start_bluetooth));
                    return;
                }
                bluetoothServer.start();
                if (BluetoothManager.bluetoothAdapter.startDiscovery()) {
                    BluetoothManager.sendBroadcast(1, null);
                } else {
                    BluetoothManager.sendBroadcast(65535, context2.getString(R.string.swap_error_cannot_start_bluetooth));
                }
                Iterator<BluetoothDevice> it = BluetoothManager.bluetoothAdapter.getBondedDevices().iterator();
                while (it.hasNext()) {
                    BluetoothManager.sendFoundBroadcast(context2, it.next());
                }
            }
        };
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper()) { // from class: org.fdroid.fdroid.nearby.BluetoothManager.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                LocalBroadcastManager.getInstance(context2).unregisterReceiver(BluetoothManager.bluetoothDeviceFound);
                bluetoothServer.close();
                if (BluetoothManager.bluetoothAdapter != null) {
                    BluetoothManager.bluetoothAdapter.cancelDiscovery();
                    if (!SwapService.wasBluetoothEnabledBeforeSwap()) {
                        BluetoothManager.bluetoothAdapter.disable();
                    }
                    String bluetoothNameBeforeSwap = SwapService.getBluetoothNameBeforeSwap();
                    if (bluetoothNameBeforeSwap != null) {
                        BluetoothManager.bluetoothAdapter.setName(bluetoothNameBeforeSwap);
                    }
                }
                BluetoothManager.handlerThread.quit();
                BluetoothManager.handlerThread = null;
                BluetoothManager.sendBroadcast(3, null);
            }
        };
    }

    public static void restart(Context context2) {
        stop(context2);
        try {
            handlerThread.join(AbstractComponentTracker.LINGERING_TIMEOUT);
        } catch (InterruptedException | NullPointerException unused) {
        }
        start(context2);
    }

    public static boolean isAlive() {
        return handlerThread != null && handlerThread.isAlive();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void sendBroadcast(int i, String str) {
        Intent intent = new Intent(ACTION_STATUS);
        intent.putExtra(EXTRA_STATUS, i);
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("android.intent.extra.TEXT", str);
        }
        LocalBroadcastManager.getInstance(context.get()).sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void sendFoundBroadcast(Context context2, BluetoothDevice bluetoothDevice) {
        BluetoothPeer bluetoothPeer = BluetoothPeer.getInstance(bluetoothDevice);
        if (bluetoothPeer == null) {
            Utils.debugLog(TAG, "IGNORING: " + bluetoothDevice);
            return;
        }
        Intent intent = new Intent(ACTION_FOUND);
        intent.putExtra(EXTRA_PEER, bluetoothPeer);
        intent.putExtra("android.bluetooth.device.extra.DEVICE", bluetoothDevice);
        LocalBroadcastManager.getInstance(context2).sendBroadcast(intent);
    }
}
