package androidx.activity.result;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import ch.qos.logback.core.CoreConstants;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ActivityResult.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ActivityResult implements Parcelable {
    private final Intent data;
    private final int resultCode;
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<ActivityResult> CREATOR = new Parcelable.Creator() { // from class: androidx.activity.result.ActivityResult$Companion$CREATOR$1
        @Override // android.os.Parcelable.Creator
        public ActivityResult createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new ActivityResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public ActivityResult[] newArray(int i) {
            return new ActivityResult[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ActivityResult(int i, Intent intent) {
        this.resultCode = i;
        this.data = intent;
    }

    public final int getResultCode() {
        return this.resultCode;
    }

    public final Intent getData() {
        return this.data;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ActivityResult(Parcel parcel) {
        this(parcel.readInt(), parcel.readInt() == 0 ? null : (Intent) Intent.CREATOR.createFromParcel(parcel));
        Intrinsics.checkNotNullParameter(parcel, "parcel");
    }

    public String toString() {
        return "ActivityResult{resultCode=" + Companion.resultCodeToString(this.resultCode) + ", data=" + this.data + CoreConstants.CURLY_RIGHT;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int i) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeInt(this.resultCode);
        dest.writeInt(this.data == null ? 0 : 1);
        Intent intent = this.data;
        if (intent != null) {
            intent.writeToParcel(dest, i);
        }
    }

    /* JADX INFO: compiled from: ActivityResult.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String resultCodeToString(int i) {
            if (i == -1) {
                return "RESULT_OK";
            }
            if (i == 0) {
                return "RESULT_CANCELED";
            }
            return String.valueOf(i);
        }
    }
}
