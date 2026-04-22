package androidx.compose.foundation.lazy.layout;

import android.os.Parcel;
import android.os.Parcelable;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Lazy.android.kt */
/* JADX INFO: loaded from: classes.dex */
final class DefaultLazyKey implements Parcelable {
    private final int index;
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<DefaultLazyKey> CREATOR = new Parcelable.Creator() { // from class: androidx.compose.foundation.lazy.layout.DefaultLazyKey$Companion$CREATOR$1
        @Override // android.os.Parcelable.Creator
        public DefaultLazyKey createFromParcel(Parcel parcel) {
            return new DefaultLazyKey(parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        public DefaultLazyKey[] newArray(int i) {
            return new DefaultLazyKey[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DefaultLazyKey) && this.index == ((DefaultLazyKey) obj).index;
    }

    public int hashCode() {
        return this.index;
    }

    public String toString() {
        return "DefaultLazyKey(index=" + this.index + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public DefaultLazyKey(int i) {
        this.index = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.index);
    }

    /* JADX INFO: compiled from: Lazy.android.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
