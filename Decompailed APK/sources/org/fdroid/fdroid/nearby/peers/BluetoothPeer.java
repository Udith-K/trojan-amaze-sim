package org.fdroid.fdroid.nearby.peers;

import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import ch.qos.logback.core.CoreConstants;
import org.fdroid.fdroid.R;

/* JADX INFO: loaded from: classes2.dex */
public class BluetoothPeer implements Peer {
    private static final String BLUETOOTH_NAME_TAG = "FDroid:";
    public static final Parcelable.Creator<BluetoothPeer> CREATOR = new Parcelable.Creator<BluetoothPeer>() { // from class: org.fdroid.fdroid.nearby.peers.BluetoothPeer.1
        @Override // android.os.Parcelable.Creator
        public BluetoothPeer createFromParcel(Parcel parcel) {
            return new BluetoothPeer(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public BluetoothPeer[] newArray(int i) {
            return new BluetoothPeer[i];
        }
    };
    private final BluetoothDevice device;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // org.fdroid.fdroid.nearby.peers.Peer
    public String getFingerprint() {
        return null;
    }

    @Override // org.fdroid.fdroid.nearby.peers.Peer
    public boolean shouldPromptForSwapBack() {
        return false;
    }

    public static BluetoothPeer getInstance(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null || bluetoothDevice.getName() == null) {
            return null;
        }
        if (bluetoothDevice.getBluetoothClass().getDeviceClass() == 272 || bluetoothDevice.getBluetoothClass().getDeviceClass() == 276 || bluetoothDevice.getBluetoothClass().getDeviceClass() == 524) {
            return new BluetoothPeer(bluetoothDevice);
        }
        return null;
    }

    private BluetoothPeer(BluetoothDevice bluetoothDevice) {
        this.device = bluetoothDevice;
    }

    public String toString() {
        String name = getName();
        return name == null ? "null" : name;
    }

    @Override // org.fdroid.fdroid.nearby.peers.Peer
    public String getName() {
        String name = this.device.getName();
        if (name == null) {
            return null;
        }
        return name.replaceAll("^FDroid:", "");
    }

    @Override // org.fdroid.fdroid.nearby.peers.Peer
    public int getIcon() {
        return R.drawable.ic_bluetooth;
    }

    @Override // org.fdroid.fdroid.nearby.peers.Peer
    public boolean equals(Object obj) {
        return (obj instanceof BluetoothPeer) && TextUtils.equals(((BluetoothPeer) obj).device.getAddress(), this.device.getAddress());
    }

    public int hashCode() {
        return this.device.getAddress().hashCode();
    }

    @Override // org.fdroid.fdroid.nearby.peers.Peer
    public String getRepoAddress() {
        return "bluetooth://" + this.device.getAddress().replace(CoreConstants.COLON_CHAR, CoreConstants.DASH_CHAR) + "/fdroid/repo";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.device, 0);
    }

    private BluetoothPeer(Parcel parcel) {
        this.device = (BluetoothDevice) parcel.readParcelable(BluetoothDevice.class.getClassLoader());
    }
}
