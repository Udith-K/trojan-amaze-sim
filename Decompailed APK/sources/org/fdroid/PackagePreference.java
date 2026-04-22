package org.fdroid;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: UpdateChecker.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/fdroid/PackagePreference;", "", "ignoreVersionCodeUpdate", "", "getIgnoreVersionCodeUpdate", "()J", "releaseChannels", "", "", "getReleaseChannels", "()Ljava/util/List;", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface PackagePreference {
    long getIgnoreVersionCodeUpdate();

    List<String> getReleaseChannels();
}
