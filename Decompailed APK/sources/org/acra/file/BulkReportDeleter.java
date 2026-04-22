package org.acra.file;

import android.content.Context;
import java.io.File;
import java.util.Comparator;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import org.acra.ACRA;

/* JADX INFO: compiled from: BulkReportDeleter.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class BulkReportDeleter {
    private final ReportLocator reportLocator;

    public BulkReportDeleter(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.reportLocator = new ReportLocator(context);
    }

    public final void deleteReports(boolean z, int i) {
        List listSortedWith = ArraysKt.sortedWith(z ? this.reportLocator.getApprovedReports() : this.reportLocator.getUnapprovedReports(), new Comparator() { // from class: org.acra.file.BulkReportDeleter$deleteReports$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ComparisonsKt.compareValues(Long.valueOf(((File) obj).lastModified()), Long.valueOf(((File) obj2).lastModified()));
            }
        });
        int size = listSortedWith.size() - i;
        for (int i2 = 0; i2 < size; i2++) {
            if (!((File) listSortedWith.get(i2)).delete()) {
                ACRA.log.w(ACRA.LOG_TAG, "Could not delete report : " + listSortedWith.get(i2));
            }
        }
    }
}
