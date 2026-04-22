package javax.jmdns.impl;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.jmdns.ServiceInfo;
import javax.jmdns.impl.util.ByteWrangler;
import org.fdroid.fdroid.nearby.peers.BonjourPeer;

/* JADX INFO: loaded from: classes.dex */
public class FDroidServiceInfo extends ServiceInfoImpl implements Parcelable {
    public static final Parcelable.Creator<FDroidServiceInfo> CREATOR = new Parcelable.Creator() { // from class: javax.jmdns.impl.FDroidServiceInfo.1
        @Override // android.os.Parcelable.Creator
        public FDroidServiceInfo createFromParcel(Parcel parcel) {
            return new FDroidServiceInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public FDroidServiceInfo[] newArray(int i) {
            return new FDroidServiceInfo[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public FDroidServiceInfo(ServiceInfo serviceInfo) {
        super(serviceInfo);
    }

    public String getFingerprint() {
        byte[] propertyBytes = getPropertyBytes(BonjourPeer.FINGERPRINT);
        if (propertyBytes == null || propertyBytes.length == 0) {
            return null;
        }
        String utf = ByteWrangler.readUTF(propertyBytes, 0, propertyBytes.length);
        if (TextUtils.isEmpty(utf)) {
            return null;
        }
        return utf;
    }

    public String getRepoAddress() {
        return getURL();
    }

    private static byte[] readBytes(Parcel parcel) {
        byte[] bArr = new byte[parcel.readInt()];
        parcel.readByteArray(bArr);
        return bArr;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FDroidServiceInfo(Parcel parcel) {
        super(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readByte() != 0, readBytes(parcel));
        int i = parcel.readInt();
        for (int i2 = 0; i2 < i; i2++) {
            try {
                addAddress((Inet4Address) InetAddress.getByAddress(readBytes(parcel)));
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        int i3 = parcel.readInt();
        for (int i4 = 0; i4 < i3; i4++) {
            try {
                addAddress((Inet6Address) InetAddress.getByAddress(readBytes(parcel)));
            } catch (UnknownHostException e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getType());
        parcel.writeString(getName());
        parcel.writeString(getSubtype());
        parcel.writeInt(getPort());
        parcel.writeInt(getWeight());
        parcel.writeInt(getPriority());
        parcel.writeByte(isPersistent() ? (byte) 1 : (byte) 0);
        parcel.writeInt(getTextBytes().length);
        parcel.writeByteArray(getTextBytes());
        parcel.writeInt(getInet4Addresses().length);
        for (int i2 = 0; i2 < getInet4Addresses().length; i2++) {
            Inet4Address inet4Address = getInet4Addresses()[i2];
            parcel.writeInt(inet4Address.getAddress().length);
            parcel.writeByteArray(inet4Address.getAddress());
        }
        parcel.writeInt(getInet6Addresses().length);
        for (int i3 = 0; i3 < getInet6Addresses().length; i3++) {
            Inet6Address inet6Address = getInet6Addresses()[i3];
            parcel.writeInt(inet6Address.getAddress().length);
            parcel.writeByteArray(inet6Address.getAddress());
        }
    }
}
