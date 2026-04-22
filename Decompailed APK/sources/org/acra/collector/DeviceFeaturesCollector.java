package org.acra.collector;

import android.content.Context;
import android.content.pm.FeatureInfo;
import ch.qos.logback.core.CoreConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.acra.ReportField;
import org.acra.builder.ReportBuilder;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.acra.plugins.Plugin;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DeviceFeaturesCollector.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¨\u0006\u0010"}, d2 = {"Lorg/acra/collector/DeviceFeaturesCollector;", "Lorg/acra/collector/BaseReportFieldCollector;", "<init>", "()V", "collect", "", "reportField", "Lorg/acra/ReportField;", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "config", "Lorg/acra/config/CoreConfiguration;", "reportBuilder", "Lorg/acra/builder/ReportBuilder;", "target", "Lorg/acra/data/CrashReportData;", "acra-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class DeviceFeaturesCollector extends BaseReportFieldCollector {
    @Override // org.acra.collector.BaseReportFieldCollector, org.acra.collector.Collector, org.acra.plugins.Plugin
    public /* bridge */ /* synthetic */ boolean enabled(CoreConfiguration coreConfiguration) {
        return Plugin.CC.$default$enabled(this, coreConfiguration);
    }

    public DeviceFeaturesCollector() {
        super(ReportField.DEVICE_FEATURES);
    }

    @Override // org.acra.collector.BaseReportFieldCollector
    public void collect(ReportField reportField, Context context, CoreConfiguration config, ReportBuilder reportBuilder, CrashReportData target) throws JSONException {
        Intrinsics.checkNotNullParameter(reportField, "reportField");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(reportBuilder, "reportBuilder");
        Intrinsics.checkNotNullParameter(target, "target");
        JSONObject jSONObject = new JSONObject();
        FeatureInfo[] systemAvailableFeatures = context.getPackageManager().getSystemAvailableFeatures();
        Intrinsics.checkNotNullExpressionValue(systemAvailableFeatures, "getSystemAvailableFeatures(...)");
        for (FeatureInfo featureInfo : systemAvailableFeatures) {
            String str = featureInfo.name;
            if (str != null) {
                jSONObject.put(str, true);
            } else {
                jSONObject.put("glEsVersion", featureInfo.getGlEsVersion());
            }
        }
        target.put(ReportField.DEVICE_FEATURES, jSONObject);
    }
}
