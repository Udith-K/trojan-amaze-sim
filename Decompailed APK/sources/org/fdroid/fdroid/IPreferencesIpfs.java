package org.fdroid.fdroid;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IPreferencesIpfs {
    List<String> getIpfsGwDisabledDefaults();

    List<String> getIpfsGwUserList();

    boolean isIpfsEnabled();

    /* JADX INFO: renamed from: setIpfsEnabled */
    void mo3125setIpfsEnabled(boolean z);

    /* JADX INFO: renamed from: setIpfsGwDisabledDefaults */
    void mo3126setIpfsGwDisabledDefaults(List<String> list);

    /* JADX INFO: renamed from: setIpfsGwUserList */
    void mo3127setIpfsGwUserList(List<String> list);
}
