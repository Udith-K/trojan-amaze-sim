package org.acra.startup;

import android.content.Context;
import ch.qos.logback.core.CoreConstants;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import org.acra.config.CoreConfiguration;
import org.acra.plugins.Plugin;

/* JADX INFO: compiled from: UnapprovedStartupProcessor.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016¨\u0006\r"}, d2 = {"Lorg/acra/startup/UnapprovedStartupProcessor;", "Lorg/acra/startup/StartupProcessor;", "<init>", "()V", "processReports", "", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "config", "Lorg/acra/config/CoreConfiguration;", "reports", "", "Lorg/acra/startup/Report;", "acra-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class UnapprovedStartupProcessor implements StartupProcessor {
    @Override // org.acra.startup.StartupProcessor, org.acra.plugins.Plugin
    public /* bridge */ /* synthetic */ boolean enabled(CoreConfiguration coreConfiguration) {
        return Plugin.CC.$default$enabled(this, coreConfiguration);
    }

    @Override // org.acra.startup.StartupProcessor
    public void processReports(Context context, CoreConfiguration config, List<Report> reports) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(reports, "reports");
        if (config.getDeleteUnapprovedReportsOnApplicationStart()) {
            ArrayList arrayList = new ArrayList();
            for (Report report : reports) {
                if (!report.getApproved()) {
                    arrayList.add(report);
                }
            }
            if (arrayList.isEmpty()) {
                return;
            }
            if (arrayList.size() > 1) {
                CollectionsKt.sortWith(arrayList, new Comparator() { // from class: org.acra.startup.UnapprovedStartupProcessor$processReports$$inlined$sortBy$1
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        return ComparisonsKt.compareValues(Long.valueOf(((Report) obj).getFile().lastModified()), Long.valueOf(((Report) obj2).getFile().lastModified()));
                    }
                });
            }
            int size = arrayList.size() - 1;
            for (int i = 0; i < size; i++) {
                ((Report) arrayList.get(i)).setDelete(true);
            }
            ((Report) arrayList.get(arrayList.size() - 1)).setApprove(true);
        }
    }
}
