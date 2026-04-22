package org.fdroid.index.v2;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: PackageV2.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u001a\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\fR\u0014\u0010\u000f\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0005¨\u0006\u0011"}, d2 = {"Lorg/fdroid/index/v2/PackageManifest;", "", "minSdkVersion", "", "getMinSdkVersion", "()Ljava/lang/Integer;", "maxSdkVersion", "getMaxSdkVersion", "featureNames", "", "", "getFeatureNames", "()Ljava/util/List;", "nativecode", "getNativecode", "targetSdkVersion", "getTargetSdkVersion", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface PackageManifest {
    List<String> getFeatureNames();

    Integer getMaxSdkVersion();

    Integer getMinSdkVersion();

    List<String> getNativecode();

    Integer getTargetSdkVersion();
}
