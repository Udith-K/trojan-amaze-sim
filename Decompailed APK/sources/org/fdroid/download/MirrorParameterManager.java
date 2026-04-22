package org.fdroid.download;

import kotlin.Metadata;

/* JADX INFO: compiled from: MirrorParameterManager.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u0005H&¨\u0006\u000b"}, d2 = {"Lorg/fdroid/download/MirrorParameterManager;", "", "incrementMirrorErrorCount", "", "mirrorUrl", "", "getMirrorErrorCount", "", "preferForeignMirrors", "", "getCurrentLocation", "download_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface MirrorParameterManager {
    String getCurrentLocation();

    int getMirrorErrorCount(String mirrorUrl);

    void incrementMirrorErrorCount(String mirrorUrl);

    boolean preferForeignMirrors();
}
