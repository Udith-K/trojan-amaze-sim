package org.fdroid;

import kotlin.Metadata;

/* JADX INFO: compiled from: IndexFile.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\b\u0010\u000e\u001a\u00020\u0003H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0014\u0010\b\u001a\u0004\u0018\u00010\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u0005¨\u0006\u000f"}, d2 = {"Lorg/fdroid/IndexFile;", "", "name", "", "getName", "()Ljava/lang/String;", "sha256", "getSha256", "size", "", "getSize", "()Ljava/lang/Long;", "ipfsCidV1", "getIpfsCidV1", "serialize", "download_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface IndexFile {
    String getIpfsCidV1();

    String getName();

    String getSha256();

    Long getSize();

    String serialize();
}
