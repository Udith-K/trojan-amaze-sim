package androidx.compose.runtime;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ParcelableSnapshotMutableLongState.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ParcelableSnapshotMutableLongState extends SnapshotMutableLongStateImpl implements Parcelable {
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<ParcelableSnapshotMutableLongState> CREATOR = new Parcelable.Creator() { // from class: androidx.compose.runtime.ParcelableSnapshotMutableLongState$Companion$CREATOR$1
        @Override // android.os.Parcelable.Creator
        public ParcelableSnapshotMutableLongState createFromParcel(Parcel parcel) {
            return new ParcelableSnapshotMutableLongState(parcel.readLong());
        }

        @Override // android.os.Parcelable.Creator
        public ParcelableSnapshotMutableLongState[] newArray(int i) {
            return new ParcelableSnapshotMutableLongState[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ParcelableSnapshotMutableLongState(long j) {
        super(j);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(getLongValue());
    }

    /* JADX INFO: compiled from: ParcelableSnapshotMutableLongState.android.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
