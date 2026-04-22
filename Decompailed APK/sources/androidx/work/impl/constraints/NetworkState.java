package androidx.work.impl.constraints;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticBackport0;
import ch.qos.logback.core.CoreConstants;

/* JADX INFO: compiled from: NetworkState.kt */
/* JADX INFO: loaded from: classes.dex */
public final class NetworkState {
    private final boolean isConnected;
    private final boolean isMetered;
    private final boolean isNotRoaming;
    private final boolean isValidated;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NetworkState)) {
            return false;
        }
        NetworkState networkState = (NetworkState) obj;
        return this.isConnected == networkState.isConnected && this.isValidated == networkState.isValidated && this.isMetered == networkState.isMetered && this.isNotRoaming == networkState.isNotRoaming;
    }

    public int hashCode() {
        return (((((ChangeSize$$ExternalSyntheticBackport0.m(this.isConnected) * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.isValidated)) * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.isMetered)) * 31) + ChangeSize$$ExternalSyntheticBackport0.m(this.isNotRoaming);
    }

    public String toString() {
        return "NetworkState(isConnected=" + this.isConnected + ", isValidated=" + this.isValidated + ", isMetered=" + this.isMetered + ", isNotRoaming=" + this.isNotRoaming + CoreConstants.RIGHT_PARENTHESIS_CHAR;
    }

    public NetworkState(boolean z, boolean z2, boolean z3, boolean z4) {
        this.isConnected = z;
        this.isValidated = z2;
        this.isMetered = z3;
        this.isNotRoaming = z4;
    }

    public final boolean isConnected() {
        return this.isConnected;
    }

    public final boolean isValidated() {
        return this.isValidated;
    }

    public final boolean isMetered() {
        return this.isMetered;
    }

    public final boolean isNotRoaming() {
        return this.isNotRoaming;
    }
}
