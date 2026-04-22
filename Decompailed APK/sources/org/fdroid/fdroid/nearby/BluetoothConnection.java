package org.fdroid.fdroid.nearby;

import android.bluetooth.BluetoothSocket;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.fdroid.fdroid.Utils;

/* JADX INFO: loaded from: classes2.dex */
public class BluetoothConnection {
    private static final String TAG = "BluetoothConnection";
    private InputStream input;
    private OutputStream output;
    private final BluetoothSocket socket;

    BluetoothConnection(BluetoothSocket bluetoothSocket) {
        this.socket = bluetoothSocket;
    }

    public InputStream getInputStream() {
        return this.input;
    }

    public OutputStream getOutputStream() {
        return this.output;
    }

    public void open() throws IOException {
        if (!this.socket.isConnected()) {
            this.socket.connect();
        }
        this.input = new BufferedInputStream(this.socket.getInputStream());
        this.output = new BufferedOutputStream(this.socket.getOutputStream());
        Utils.debugLog(TAG, "Opened connection to Bluetooth device");
    }

    public void closeQuietly() {
        Utils.closeQuietly(this.input);
        Utils.closeQuietly(this.output);
        Utils.closeQuietly(this.socket);
    }

    public void close() {
        closeQuietly();
    }
}
