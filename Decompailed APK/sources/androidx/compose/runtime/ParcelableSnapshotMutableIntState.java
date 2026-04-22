package androidx.compose.runtime;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ParcelableSnapshotMutableIntState.android.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ParcelableSnapshotMutableIntState extends SnapshotMutableIntStateImpl implements Parcelable {
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<ParcelableSnapshotMutableIntState> CREATOR = new Parcelable.Creator() { // from class: androidx.compose.runtime.ParcelableSnapshotMutableIntState$Companion$CREATOR$1
        @Override // android.os.Parcelable.Creator
        public ParcelableSnapshotMutableIntState createFromParcel(Parcel parcel) {
            return new ParcelableSnapshotMutableIntState(parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        public ParcelableSnapshotMutableIntState[] newArray(int i) {
            return new ParcelableSnapshotMutableIntState[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ParcelableSnapshotMutableIntState(int i) {
        super(i);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(getIntValue());
    }

    /* JADX INFO: compiled from: ParcelableSnapshotMutableIntState.android.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
