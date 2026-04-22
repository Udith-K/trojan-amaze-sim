package org.fdroid.repo;

import androidx.core.os.LocaleListCompat;
import java.util.ArrayList;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.fdroid.LocaleChooser;
import org.fdroid.database.AppOverviewItem;
import org.fdroid.database.LocalizedIcon;
import org.fdroid.database.Repository;
import org.fdroid.database.RepositoryKt;
import org.fdroid.database.RepositoryPreferences;
import org.fdroid.index.IndexFormatVersion;
import org.fdroid.index.v2.FileV2;
import org.fdroid.index.v2.IndexV2StreamReceiver;
import org.fdroid.index.v2.PackageV2;
import org.fdroid.index.v2.PackageVersionV2;
import org.fdroid.index.v2.RepoV2;

/* JADX INFO: compiled from: RepoV2StreamReceiver.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0010\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/fdroid/repo/RepoV2StreamReceiver;", "Lorg/fdroid/index/v2/IndexV2StreamReceiver;", "receiver", "Lorg/fdroid/repo/RepoPreviewReceiver;", "certificate", "", "username", "password", "<init>", "(Lorg/fdroid/repo/RepoPreviewReceiver;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "locales", "Landroidx/core/os/LocaleListCompat;", "receive", "", "repo", "Lorg/fdroid/index/v2/RepoV2;", "version", "", "packageName", "p", "Lorg/fdroid/index/v2/PackageV2;", "onStreamEnded", "Companion", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class RepoV2StreamReceiver implements IndexV2StreamReceiver {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String certificate;
    private final LocaleListCompat locales;
    private final String password;
    private final RepoPreviewReceiver receiver;
    private final String username;

    @Override // org.fdroid.index.v2.IndexV2StreamReceiver
    public void onStreamEnded() {
    }

    public RepoV2StreamReceiver(RepoPreviewReceiver receiver, String certificate, String str, String str2) {
        Intrinsics.checkNotNullParameter(receiver, "receiver");
        Intrinsics.checkNotNullParameter(certificate, "certificate");
        this.receiver = receiver;
        this.certificate = certificate;
        this.username = str;
        this.password = str2;
        LocaleListCompat localeListCompat = LocaleListCompat.getDefault();
        Intrinsics.checkNotNullExpressionValue(localeListCompat, "getDefault(...)");
        this.locales = localeListCompat;
    }

    /* JADX INFO: compiled from: RepoV2StreamReceiver.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J:\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\rJ\u001e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016¨\u0006\u0017"}, d2 = {"Lorg/fdroid/repo/RepoV2StreamReceiver$Companion;", "", "<init>", "()V", "getRepository", "Lorg/fdroid/database/Repository;", "repo", "Lorg/fdroid/index/v2/RepoV2;", "version", "", "formatVersion", "Lorg/fdroid/index/IndexFormatVersion;", "certificate", "", "username", "password", "getAppOverViewItem", "Lorg/fdroid/database/AppOverviewItem;", "packageName", "p", "Lorg/fdroid/index/v2/PackageV2;", "locales", "Landroidx/core/os/LocaleListCompat;", "database_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Repository getRepository(RepoV2 repo, long version, IndexFormatVersion formatVersion, String certificate, String username, String password) {
            Intrinsics.checkNotNullParameter(repo, "repo");
            Intrinsics.checkNotNullParameter(formatVersion, "formatVersion");
            Intrinsics.checkNotNullParameter(certificate, "certificate");
            return new Repository(RepositoryKt.toCoreRepository$default(repo, 0L, version, formatVersion, certificate, 1, null), RepositoryKt.toMirrors(repo.getMirrors(), 0L), RepositoryKt.toRepoAntiFeatures(repo.getAntiFeatures(), 0L), RepositoryKt.toRepoCategories(repo.getCategories(), 0L), RepositoryKt.toRepoReleaseChannel(repo.getReleaseChannels(), 0L), new RepositoryPreferences(0L, 0, true, null, null, null, null, username, password, 120, null));
        }

        public final AppOverviewItem getAppOverViewItem(String packageName, PackageV2 p, LocaleListCompat locales) {
            ArrayList arrayList;
            Intrinsics.checkNotNullParameter(packageName, "packageName");
            Intrinsics.checkNotNullParameter(p, "p");
            Intrinsics.checkNotNullParameter(locales, "locales");
            long added = p.getMetadata().getAdded();
            long lastUpdated = p.getMetadata().getLastUpdated();
            LocaleChooser localeChooser = LocaleChooser.INSTANCE;
            String str = (String) localeChooser.getBestLocale(p.getMetadata().getName(), locales);
            String str2 = (String) localeChooser.getBestLocale(p.getMetadata().getSummary(), locales);
            PackageVersionV2 packageVersionV2 = (PackageVersionV2) CollectionsKt.lastOrNull(p.getVersions().values());
            Map<String, Map<String, String>> antiFeatures = packageVersionV2 != null ? packageVersionV2.getAntiFeatures() : null;
            Map<String, FileV2> icon = p.getMetadata().getIcon();
            if (icon != null) {
                ArrayList arrayList2 = new ArrayList(icon.size());
                for (Map.Entry<String, FileV2> entry : icon.entrySet()) {
                    String key = entry.getKey();
                    FileV2 value = entry.getValue();
                    ArrayList arrayList3 = arrayList2;
                    arrayList3.add(new LocalizedIcon(0L, packageName, "icon", key, value.getName(), value.getSha256(), value.getSize(), value.getIpfsCidV1()));
                    arrayList2 = arrayList3;
                }
                arrayList = arrayList2;
            } else {
                arrayList = null;
            }
            return new AppOverviewItem(0L, packageName, added, lastUpdated, str, str2, antiFeatures, arrayList, true);
        }
    }

    @Override // org.fdroid.index.v2.IndexV2StreamReceiver
    public void receive(RepoV2 repo, long version) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        this.receiver.onRepoReceived(INSTANCE.getRepository(repo, version, IndexFormatVersion.TWO, this.certificate, this.username, this.password));
    }

    @Override // org.fdroid.index.v2.IndexV2StreamReceiver
    public void receive(String packageName, PackageV2 p) {
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(p, "p");
        this.receiver.onAppReceived(INSTANCE.getAppOverViewItem(packageName, p, this.locales));
    }
}
