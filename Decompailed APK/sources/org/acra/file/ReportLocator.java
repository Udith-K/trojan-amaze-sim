package org.acra.file;

import android.content.Context;
import java.io.File;
import java.util.Comparator;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReportLocator.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class ReportLocator {
    public static final Companion Companion = new Companion(null);
    private final Context context;

    public ReportLocator(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public final File getUnapprovedFolder() {
        File dir = this.context.getDir("ACRA-unapproved", 0);
        Intrinsics.checkNotNullExpressionValue(dir, "getDir(...)");
        return dir;
    }

    public final File[] getUnapprovedReports() {
        File[] fileArrListFiles = getUnapprovedFolder().listFiles();
        return fileArrListFiles == null ? new File[0] : fileArrListFiles;
    }

    public final File getApprovedFolder() {
        File dir = this.context.getDir("ACRA-approved", 0);
        Intrinsics.checkNotNullExpressionValue(dir, "getDir(...)");
        return dir;
    }

    public final File[] getApprovedReports() {
        List listSortedWith;
        File[] fileArr;
        File[] fileArrListFiles = getApprovedFolder().listFiles();
        return (fileArrListFiles == null || (listSortedWith = ArraysKt.sortedWith(fileArrListFiles, new Comparator() { // from class: org.acra.file.ReportLocator$special$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ComparisonsKt.compareValues(Long.valueOf(((File) obj).lastModified()), Long.valueOf(((File) obj2).lastModified()));
            }
        })) == null || (fileArr = (File[]) listSortedWith.toArray(new File[0])) == null) ? new File[0] : fileArr;
    }

    /* JADX INFO: compiled from: ReportLocator.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
