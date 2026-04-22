package org.acra.collector;

import android.content.Context;
import ch.qos.logback.core.CoreConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.acra.ReportField;
import org.acra.builder.ReportBuilder;
import org.acra.collector.Collector;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.acra.plugins.Plugin;
import org.json.JSONObject;

/* JADX INFO: compiled from: ThreadCollector.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"Lorg/acra/collector/ThreadCollector;", "Lorg/acra/collector/BaseReportFieldCollector;", "<init>", "()V", "order", "Lorg/acra/collector/Collector$Order;", "getOrder", "()Lorg/acra/collector/Collector$Order;", "collect", "", "reportField", "Lorg/acra/ReportField;", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "config", "Lorg/acra/config/CoreConfiguration;", "reportBuilder", "Lorg/acra/builder/ReportBuilder;", "target", "Lorg/acra/data/CrashReportData;", "acra-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class ThreadCollector extends BaseReportFieldCollector {
    @Override // org.acra.collector.BaseReportFieldCollector, org.acra.collector.Collector, org.acra.plugins.Plugin
    public /* bridge */ /* synthetic */ boolean enabled(CoreConfiguration coreConfiguration) {
        return Plugin.CC.$default$enabled(this, coreConfiguration);
    }

    public ThreadCollector() {
        super(ReportField.THREAD_DETAILS);
    }

    @Override // org.acra.collector.BaseReportFieldCollector, org.acra.collector.Collector
    public Collector.Order getOrder() {
        return Collector.Order.LATE;
    }

    @Override // org.acra.collector.BaseReportFieldCollector
    public void collect(ReportField reportField, Context context, CoreConfiguration config, ReportBuilder reportBuilder, CrashReportData target) throws Exception {
        Intrinsics.checkNotNullParameter(reportField, "reportField");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(reportBuilder, "reportBuilder");
        Intrinsics.checkNotNullParameter(target, "target");
        Thread uncaughtExceptionThread = reportBuilder.getUncaughtExceptionThread();
        if (uncaughtExceptionThread != null) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", uncaughtExceptionThread.getId());
            jSONObject.put("name", uncaughtExceptionThread.getName());
            jSONObject.put("priority", uncaughtExceptionThread.getPriority());
            ThreadGroup threadGroup = uncaughtExceptionThread.getThreadGroup();
            if (threadGroup != null) {
                jSONObject.put("groupName", threadGroup);
            }
            target.put(ReportField.THREAD_DETAILS, jSONObject);
            return;
        }
        target.put(ReportField.THREAD_DETAILS, (String) null);
    }
}
