package org.fdroid.database;

import android.content.res.Resources;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.fdroid.LocaleChooser;
import org.fdroid.fdroid.nearby.peers.BonjourPeer;
import org.fdroid.index.v2.FileV2;
import org.fdroid.index.v2.MetadataV2;

/* JADX INFO: compiled from: App.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a0\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0000\u001a:\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f*\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f0\u000ej\u0002`\u00102\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0006H\u0000\u001a&\u0010\u0012\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000ej\u0004\u0018\u0001`\u0010*\b\u0012\u0004\u0012\u00020\u00130\fH\u0000\u001a@\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\f*\u0018\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\f0\u000ej\u0002`\u00162\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0006H\u0000\u001a,\u0010\u0014\u001a\u00020\u0015*\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0000¨\u0006\u0018"}, d2 = {"toAppMetadata", "Lorg/fdroid/database/AppMetadata;", "Lorg/fdroid/index/v2/MetadataV2;", "repoId", "", "packageName", "", "isCompatible", "", "locales", "Landroidx/core/os/LocaleListCompat;", "toLocalizedFile", "", "Lorg/fdroid/database/LocalizedFile;", "", "Lorg/fdroid/index/v2/FileV2;", "Lorg/fdroid/index/v2/LocalizedFileV2;", BonjourPeer.TYPE, "toLocalizedFileV2", "Lorg/fdroid/database/IFile;", "toLocalizedFileList", "Lorg/fdroid/database/LocalizedFileList;", "Lorg/fdroid/index/v2/LocalizedFileListV2;", "locale", "database_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AppKt {
    public static final List<LocalizedFileList> toLocalizedFileList(Map<String, ? extends List<FileV2>> map, long j, String packageName, String type) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(type, "type");
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, ? extends List<FileV2>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<FileV2> value = entry.getValue();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(value, 10));
            Iterator<T> it = value.iterator();
            while (it.hasNext()) {
                arrayList2.add(toLocalizedFileList((FileV2) it.next(), j, packageName, type, key));
            }
            CollectionsKt.addAll(arrayList, arrayList2);
        }
        return arrayList;
    }

    public static /* synthetic */ AppMetadata toAppMetadata$default(MetadataV2 metadataV2, long j, String str, boolean z, LocaleListCompat localeListCompat, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        boolean z2 = z;
        if ((i & 8) != 0) {
            localeListCompat = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
        }
        return toAppMetadata(metadataV2, j, str, z2, localeListCompat);
    }

    public static final AppMetadata toAppMetadata(MetadataV2 metadataV2, long j, String packageName, boolean z, LocaleListCompat locales) {
        Intrinsics.checkNotNullParameter(metadataV2, "<this>");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(locales, "locales");
        long added = metadataV2.getAdded();
        long lastUpdated = metadataV2.getLastUpdated();
        Map<String, String> name = metadataV2.getName();
        Map<String, String> summary = metadataV2.getSummary();
        Map<String, String> description = metadataV2.getDescription();
        LocaleChooser localeChooser = LocaleChooser.INSTANCE;
        return new AppMetadata(j, packageName, added, lastUpdated, name, summary, description, (String) localeChooser.getBestLocale(metadataV2.getName(), locales), (String) localeChooser.getBestLocale(metadataV2.getSummary(), locales), metadataV2.getWebSite(), metadataV2.getChangelog(), metadataV2.getLicense(), metadataV2.getSourceCode(), metadataV2.getIssueTracker(), metadataV2.getTranslation(), metadataV2.getPreferredSigner(), metadataV2.getVideo(), metadataV2.getAuthorName(), metadataV2.getAuthorEmail(), metadataV2.getAuthorWebSite(), metadataV2.getAuthorPhone(), metadataV2.getDonate(), metadataV2.getLiberapayID(), metadataV2.getLiberapay(), metadataV2.getOpenCollective(), metadataV2.getBitcoin(), metadataV2.getLitecoin(), metadataV2.getFlattrID(), metadataV2.getCategories(), z);
    }

    public static final List<LocalizedFile> toLocalizedFile(Map<String, FileV2> map, long j, String packageName, String type) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(type, "type");
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry<String, FileV2> entry : map.entrySet()) {
            String key = entry.getKey();
            FileV2 value = entry.getValue();
            arrayList.add(new LocalizedFile(j, packageName, type, key, value.getName(), value.getSha256(), value.getSize(), value.getIpfsCidV1()));
        }
        return arrayList;
    }

    public static final LocalizedFileList toLocalizedFileList(FileV2 fileV2, long j, String packageName, String type, String locale) {
        Intrinsics.checkNotNullParameter(fileV2, "<this>");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(locale, "locale");
        return new LocalizedFileList(j, packageName, type, locale, fileV2.getName(), fileV2.getSha256(), fileV2.getSize(), fileV2.getIpfsCidV1());
    }

    public static final Map<String, FileV2> toLocalizedFileV2(List<? extends IFile> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list, 10)), 16));
        for (IFile iFile : list) {
            Pair pair = TuplesKt.to(iFile.getLocale(), new FileV2(iFile.getName(), iFile.getSha256(), iFile.getSize(), iFile.getIpfsCidV1()));
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        if (linkedHashMap.isEmpty()) {
            return null;
        }
        return linkedHashMap;
    }
}
