package org.fdroid.index.v1;

import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import org.fdroid.index.v2.AntiFeatureV2;
import org.fdroid.index.v2.CategoryV2;
import org.fdroid.index.v2.MetadataV2;
import org.fdroid.index.v2.PackageVersionV2;
import org.fdroid.index.v2.ReleaseChannelV2;
import org.fdroid.index.v2.RepoV2;

/* JADX INFO: compiled from: IndexV1StreamReceiver.kt */
/* JADX INFO: loaded from: classes2.dex */
@Deprecated
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J$\u0010\u0002\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000e0\rH&JD\u0010\u000f\u001a\u00020\u00032\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00110\r2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00130\r2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00150\rH&J\u001a\u0010\u0016\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\b\u0010\u0017\u001a\u0004\u0018\u00010\tH&¨\u0006\u0018"}, d2 = {"Lorg/fdroid/index/v1/IndexV1StreamReceiver;", "", "receive", "", "repo", "Lorg/fdroid/index/v2/RepoV2;", "version", "", "packageName", "", "m", "Lorg/fdroid/index/v2/MetadataV2;", "v", "", "Lorg/fdroid/index/v2/PackageVersionV2;", "updateRepo", "antiFeatures", "Lorg/fdroid/index/v2/AntiFeatureV2;", "categories", "Lorg/fdroid/index/v2/CategoryV2;", "releaseChannels", "Lorg/fdroid/index/v2/ReleaseChannelV2;", "updateAppMetadata", "preferredSigner", "index_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface IndexV1StreamReceiver {
    void receive(String packageName, Map<String, PackageVersionV2> v);

    void receive(String packageName, MetadataV2 m);

    void receive(RepoV2 repo, long version);

    void updateAppMetadata(String packageName, String preferredSigner);

    void updateRepo(Map<String, AntiFeatureV2> antiFeatures, Map<String, CategoryV2> categories, Map<String, ReleaseChannelV2> releaseChannels);
}
