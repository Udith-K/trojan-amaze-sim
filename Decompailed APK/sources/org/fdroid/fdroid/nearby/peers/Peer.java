package org.fdroid.fdroid.nearby.peers;

import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public interface Peer extends Parcelable {
    boolean equals(Object obj);

    String getFingerprint();

    int getIcon();

    String getName();

    String getRepoAddress();

    boolean shouldPromptForSwapBack();
}
