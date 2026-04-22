package org.fdroid.fdroid.privileged;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IPrivilegedCallback extends IInterface {
    public static final String DESCRIPTOR = "org.fdroid.fdroid.privileged.IPrivilegedCallback";

    public static class Default implements IPrivilegedCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // org.fdroid.fdroid.privileged.IPrivilegedCallback
        public void handleResult(String str, int i) throws RemoteException {
        }
    }

    void handleResult(String str, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IPrivilegedCallback {
        static final int TRANSACTION_handleResult = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, IPrivilegedCallback.DESCRIPTOR);
        }

        public static IPrivilegedCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(IPrivilegedCallback.DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IPrivilegedCallback)) {
                return (IPrivilegedCallback) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(IPrivilegedCallback.DESCRIPTOR);
            }
            if (i == 1598968902) {
                parcel2.writeString(IPrivilegedCallback.DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                handleResult(parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IPrivilegedCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IPrivilegedCallback.DESCRIPTOR;
            }

            @Override // org.fdroid.fdroid.privileged.IPrivilegedCallback
            public void handleResult(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(IPrivilegedCallback.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
