package org.fdroid.fdroid.nearby;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

/* JADX INFO: loaded from: classes2.dex */
public class BluetoothClient {
    private static final String TAG = "BluetoothClient";
    private final BluetoothDevice device;

    public BluetoothClient(String str) {
        this.device = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(str);
    }

    public BluetoothConnection openConnection() throws Throwable {
        BluetoothConnection bluetoothConnection;
        Throwable th;
        try {
            bluetoothConnection = new BluetoothConnection(this.device.createInsecureRfcommSocketToServiceRecord(BluetoothConstants.fdroidUuid()));
        } catch (Throwable th2) {
            bluetoothConnection = null;
            th = th2;
        }
        try {
            bluetoothConnection.open();
            bluetoothConnection.closeQuietly();
            return bluetoothConnection;
        } catch (Throwable th3) {
            th = th3;
            if (bluetoothConnection != null) {
                bluetoothConnection.closeQuietly();
            }
            throw th;
        }
    }
}
