package org.fdroid.fdroid.nearby.peers;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import javax.jmdns.ServiceInfo;
import javax.jmdns.impl.FDroidServiceInfo;
import org.fdroid.fdroid.FDroidApp;

/* JADX INFO: loaded from: classes2.dex */
public class BonjourPeer extends WifiPeer {
    public static final Parcelable.Creator<BonjourPeer> CREATOR = new Parcelable.Creator<BonjourPeer>() { // from class: org.fdroid.fdroid.nearby.peers.BonjourPeer.1
        @Override // android.os.Parcelable.Creator
        public BonjourPeer createFromParcel(Parcel parcel) {
            return new BonjourPeer(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public BonjourPeer[] newArray(int i) {
            return new BonjourPeer[i];
        }
    };
    public static final String FINGERPRINT = "fingerprint";
    public static final String NAME = "name";
    public static final String PATH = "path";
    private static final String TAG = "BonjourPeer";
    public static final String TYPE = "type";
    private final FDroidServiceInfo serviceInfo;

    @Override // org.fdroid.fdroid.nearby.peers.WifiPeer, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static BonjourPeer getInstance(ServiceInfo serviceInfo) {
        String propertyString = serviceInfo.getPropertyString(TYPE);
        String propertyString2 = serviceInfo.getPropertyString(FINGERPRINT);
        if (propertyString == null || !propertyString.startsWith("fdroidrepo") || FDroidApp.repo == null || TextUtils.equals(FDroidApp.repo.getFingerprint(), propertyString2)) {
            return null;
        }
        return new BonjourPeer(serviceInfo);
    }

    private BonjourPeer(ServiceInfo serviceInfo) {
        FDroidServiceInfo fDroidServiceInfo = new FDroidServiceInfo(serviceInfo);
        this.serviceInfo = fDroidServiceInfo;
        this.name = serviceInfo.getDomain();
        this.uri = Uri.parse(fDroidServiceInfo.getRepoAddress());
        this.shouldPromptForSwapBack = true;
    }

    public String toString() {
        return getName();
    }

    @Override // org.fdroid.fdroid.nearby.peers.WifiPeer, org.fdroid.fdroid.nearby.peers.Peer
    public String getName() {
        return this.serviceInfo.getName();
    }

    @Override // org.fdroid.fdroid.nearby.peers.WifiPeer
    public int hashCode() {
        String fingerprint = getFingerprint();
        if (fingerprint == null) {
            return 0;
        }
        return fingerprint.hashCode();
    }

    @Override // org.fdroid.fdroid.nearby.peers.WifiPeer, org.fdroid.fdroid.nearby.peers.Peer
    public String getRepoAddress() {
        return this.serviceInfo.getRepoAddress();
    }

    @Override // org.fdroid.fdroid.nearby.peers.WifiPeer, org.fdroid.fdroid.nearby.peers.Peer
    public String getFingerprint() {
        return this.serviceInfo.getFingerprint();
    }

    @Override // org.fdroid.fdroid.nearby.peers.WifiPeer, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.serviceInfo, i);
    }

    private BonjourPeer(Parcel parcel) {
        this((ServiceInfo) parcel.readParcelable(FDroidServiceInfo.class.getClassLoader()));
    }
}
