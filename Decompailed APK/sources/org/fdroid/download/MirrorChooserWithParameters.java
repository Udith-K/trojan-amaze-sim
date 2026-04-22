package org.fdroid.download;

import io.ktor.client.plugins.ResponseException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MirrorChooser.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH\u0016JD\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u000f\u001a\u00020\u00102\u0016\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0012j\b\u0012\u0004\u0012\u00020\b`\u0013H\u0002J,\u0010\u0014\u001a\u00020\u00152\n\u0010\u0016\u001a\u00060\u0017j\u0002`\u00182\u0006\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lorg/fdroid/download/MirrorChooserWithParameters;", "Lorg/fdroid/download/MirrorChooserImpl;", "mirrorParameterManager", "Lorg/fdroid/download/MirrorParameterManager;", "<init>", "(Lorg/fdroid/download/MirrorParameterManager;)V", "orderMirrors", "", "Lorg/fdroid/download/Mirror;", "downloadRequest", "Lorg/fdroid/download/DownloadRequest;", "sortMirrorsByLocation", "foreignMirrorsPreferred", "", "availableMirrorList", "currentLocation", "", "mirrorComparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "handleException", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "mirror", "mirrorIndex", "", "mirrorCount", "download_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class MirrorChooserWithParameters extends MirrorChooserImpl {
    private final MirrorParameterManager mirrorParameterManager;

    public MirrorChooserWithParameters() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public /* synthetic */ MirrorChooserWithParameters(MirrorParameterManager mirrorParameterManager, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : mirrorParameterManager);
    }

    public MirrorChooserWithParameters(MirrorParameterManager mirrorParameterManager) {
        this.mirrorParameterManager = mirrorParameterManager;
    }

    @Override // org.fdroid.download.MirrorChooser
    public List<Mirror> orderMirrors(final DownloadRequest downloadRequest) {
        Intrinsics.checkNotNullParameter(downloadRequest, "downloadRequest");
        Comparator<Mirror> comparator = new Comparator() { // from class: org.fdroid.download.MirrorChooserWithParameters$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return MirrorChooserWithParameters.orderMirrors$lambda$0(this.f$0, (Mirror) obj, (Mirror) obj2);
            }
        };
        ArrayList arrayList = new ArrayList();
        MirrorParameterManager mirrorParameterManager = this.mirrorParameterManager;
        if (mirrorParameterManager != null && mirrorParameterManager.getCurrentLocation().length() > 0) {
            arrayList.addAll(sortMirrorsByLocation(this.mirrorParameterManager.preferForeignMirrors(), downloadRequest.getMirrors(), this.mirrorParameterManager.getCurrentLocation(), comparator));
        } else {
            List mutableList = CollectionsKt.toMutableList((Collection) downloadRequest.getMirrors());
            Collections.shuffle(mutableList);
            arrayList.addAll(CollectionsKt.sortedWith(mutableList, comparator));
        }
        if (downloadRequest.getTryFirstMirror() != null && arrayList.size() > 1) {
            CollectionsKt.sortWith(arrayList, new Comparator() { // from class: org.fdroid.download.MirrorChooserWithParameters$orderMirrors$$inlined$sortBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Integer.valueOf(!Intrinsics.areEqual((Mirror) t, downloadRequest.getTryFirstMirror()) ? 1 : 0), Integer.valueOf(!Intrinsics.areEqual((Mirror) t2, downloadRequest.getTryFirstMirror()) ? 1 : 0));
                }
            });
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int orderMirrors$lambda$0(MirrorChooserWithParameters mirrorChooserWithParameters, Mirror mirror1, Mirror mirror2) {
        Intrinsics.checkNotNullParameter(mirror1, "mirror1");
        Intrinsics.checkNotNullParameter(mirror2, "mirror2");
        MirrorParameterManager mirrorParameterManager = mirrorChooserWithParameters.mirrorParameterManager;
        int mirrorErrorCount = mirrorParameterManager != null ? mirrorParameterManager.getMirrorErrorCount(mirror1.getBaseUrl()) : 0;
        MirrorParameterManager mirrorParameterManager2 = mirrorChooserWithParameters.mirrorParameterManager;
        return Intrinsics.compare(mirrorErrorCount, mirrorParameterManager2 != null ? mirrorParameterManager2.getMirrorErrorCount(mirror2.getBaseUrl()) : 0);
    }

    private final List<Mirror> sortMirrorsByLocation(boolean foreignMirrorsPreferred, List<Mirror> availableMirrorList, String currentLocation, Comparator<Mirror> mirrorComparator) {
        ArrayList arrayList = new ArrayList();
        List mutableList = CollectionsKt.toMutableList((Collection) availableMirrorList);
        Collections.shuffle(mutableList);
        List listSortedWith = CollectionsKt.sortedWith(mutableList, mirrorComparator);
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : listSortedWith) {
            Mirror mirror = (Mirror) obj;
            String countryCode = mirror.getCountryCode();
            if (countryCode != null && countryCode.length() != 0 && Intrinsics.areEqual(currentLocation, mirror.getCountryCode())) {
                arrayList2.add(obj);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : listSortedWith) {
            Mirror mirror2 = (Mirror) obj2;
            String countryCode2 = mirror2.getCountryCode();
            if (countryCode2 != null && countryCode2.length() != 0 && !Intrinsics.areEqual(currentLocation, mirror2.getCountryCode())) {
                arrayList3.add(obj2);
            }
        }
        ArrayList arrayList4 = new ArrayList();
        for (Object obj3 : listSortedWith) {
            String countryCode3 = ((Mirror) obj3).getCountryCode();
            if (countryCode3 == null || countryCode3.length() == 0) {
                arrayList4.add(obj3);
            }
        }
        if (foreignMirrorsPreferred) {
            arrayList.addAll(arrayList3);
            arrayList.addAll(arrayList4);
            arrayList.addAll(arrayList2);
        } else {
            arrayList.addAll(arrayList2);
            arrayList.addAll(arrayList4);
            arrayList.addAll(arrayList3);
        }
        return arrayList;
    }

    @Override // org.fdroid.download.MirrorChooserImpl
    public void handleException(Exception e, Mirror mirror, int mirrorIndex, int mirrorCount) throws Exception {
        MirrorParameterManager mirrorParameterManager;
        Intrinsics.checkNotNullParameter(e, "e");
        Intrinsics.checkNotNullParameter(mirror, "mirror");
        if (((e instanceof ResponseException) || (e instanceof IOException)) && (mirrorParameterManager = this.mirrorParameterManager) != null) {
            mirrorParameterManager.incrementMirrorErrorCount(mirror.getBaseUrl());
        }
        super.handleException(e, mirror, mirrorIndex, mirrorCount);
    }
}
