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

/* JADX INFO: compiled from: BaseReportFieldCollector.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J(\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J(\u0010\u000e\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J0\u0010\u000e\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0013H&R\u0018\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003X\u0092\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u0016"}, d2 = {"Lorg/acra/collector/BaseReportFieldCollector;", "Lorg/acra/collector/Collector;", "reportFields", "", "Lorg/acra/ReportField;", "<init>", "([Lorg/acra/ReportField;)V", "[Lorg/acra/ReportField;", "shouldCollect", "", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "config", "Lorg/acra/config/CoreConfiguration;", "collect", "reportBuilder", "Lorg/acra/builder/ReportBuilder;", "", "crashReportData", "Lorg/acra/data/CrashReportData;", "reportField", "target", "acra-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class BaseReportFieldCollector implements Collector {
    private final ReportField[] reportFields;

    public abstract void collect(ReportField reportField, Context context, CoreConfiguration config, ReportBuilder reportBuilder, CrashReportData target) throws Exception;

    @Override // org.acra.collector.Collector, org.acra.plugins.Plugin
    public /* bridge */ /* synthetic */ boolean enabled(CoreConfiguration coreConfiguration) {
        return Plugin.CC.$default$enabled(this, coreConfiguration);
    }

    @Override // org.acra.collector.Collector
    public /* synthetic */ Collector.Order getOrder() {
        return Collector.Order.NORMAL;
    }

    public BaseReportFieldCollector(ReportField... reportFields) {
        Intrinsics.checkNotNullParameter(reportFields, "reportFields");
        this.reportFields = reportFields;
    }

    public boolean shouldCollect(Context context, CoreConfiguration config, ReportField collect, ReportBuilder reportBuilder) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(collect, "collect");
        Intrinsics.checkNotNullParameter(reportBuilder, "reportBuilder");
        return config.getReportContent().contains(collect);
    }

    @Override // org.acra.collector.Collector
    public void collect(Context context, CoreConfiguration config, ReportBuilder reportBuilder, CrashReportData crashReportData) throws CollectorException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(reportBuilder, "reportBuilder");
        Intrinsics.checkNotNullParameter(crashReportData, "crashReportData");
        for (ReportField reportField : this.reportFields) {
            try {
                if (shouldCollect(context, config, reportField, reportBuilder)) {
                    collect(reportField, context, config, reportBuilder, crashReportData);
                }
            } catch (Exception e) {
                crashReportData.put(reportField, (String) null);
                throw new CollectorException("Error while retrieving " + reportField.name() + " data:" + e.getMessage(), e);
            }
        }
    }
}
