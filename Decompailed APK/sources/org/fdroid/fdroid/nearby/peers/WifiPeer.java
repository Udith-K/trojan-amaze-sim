package org.fdroid.fdroid.nearby.peers;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import org.fdroid.fdroid.R;
import org.fdroid.fdroid.nearby.NewRepoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class WifiPeer implements Peer {
    public static final Parcelable.Creator<WifiPeer> CREATOR = new Parcelable.Creator<WifiPeer>() { // from class: org.fdroid.fdroid.nearby.peers.WifiPeer.1
        @Override // android.os.Parcelable.Creator
        public WifiPeer createFromParcel(Parcel parcel) {
            return new WifiPeer(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public WifiPeer[] newArray(int i) {
            return new WifiPeer[i];
        }
    };
    protected String name;
    protected boolean shouldPromptForSwapBack;
    protected Uri uri;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WifiPeer() {
    }

    public WifiPeer(NewRepoConfig newRepoConfig) {
        this(newRepoConfig.getRepoUri(), newRepoConfig.getHost(), !newRepoConfig.preventFurtherSwaps());
    }

    private WifiPeer(Uri uri, String str, boolean z) {
        this.name = str;
        this.uri = uri;
        this.shouldPromptForSwapBack = z;
    }

    @Override // org.fdroid.fdroid.nearby.peers.Peer
    public boolean equals(Object obj) {
        if (obj instanceof BluetoothPeer) {
            return false;
        }
        String fingerprint = getFingerprint();
        if ((this instanceof BonjourPeer) && (obj instanceof BonjourPeer)) {
            return TextUtils.equals(getFingerprint(), ((BonjourPeer) obj).getFingerprint());
        }
        WifiPeer wifiPeer = (WifiPeer) obj;
        if (TextUtils.isEmpty(fingerprint) || !TextUtils.equals(getFingerprint(), wifiPeer.getFingerprint())) {
            return TextUtils.equals(getRepoAddress(), wifiPeer.getRepoAddress());
        }
        return true;
    }

    public int hashCode() {
        return (this.uri.getHost() + this.uri.getPort()).hashCode();
    }

    @Override // org.fdroid.fdroid.nearby.peers.Peer
    public String getName() {
        return this.name;
    }

    @Override // org.fdroid.fdroid.nearby.peers.Peer
    public int getIcon() {
        return R.drawable.ic_wifi;
    }

    @Override // org.fdroid.fdroid.nearby.peers.Peer
    public String getRepoAddress() {
        return this.uri.toString();
    }

    @Override // org.fdroid.fdroid.nearby.peers.Peer
    public String getFingerprint() {
        return this.uri.getQueryParameter(BonjourPeer.FINGERPRINT);
    }

    @Override // org.fdroid.fdroid.nearby.peers.Peer
    public boolean shouldPromptForSwapBack() {
        return this.shouldPromptForSwapBack;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.uri.toString());
        parcel.writeByte(this.shouldPromptForSwapBack ? (byte) 1 : (byte) 0);
    }

    private WifiPeer(Parcel parcel) {
        this(Uri.parse(parcel.readString()), parcel.readString(), parcel.readByte() == 1);
    }
}
