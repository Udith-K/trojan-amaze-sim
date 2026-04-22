package org.acra.collector;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.DropBoxManager;
import ch.qos.logback.core.CoreConstants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.builder.ReportBuilder;
import org.acra.collector.Collector;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.acra.plugins.Plugin;
import org.acra.prefs.SharedPreferencesFactory;
import org.acra.util.SystemServices;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DropBoxCollector.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0017\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0017J(\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0019"}, d2 = {"Lorg/acra/collector/DropBoxCollector;", "Lorg/acra/collector/BaseReportFieldCollector;", "<init>", "()V", "dateFormat", "Ljava/text/SimpleDateFormat;", "order", "Lorg/acra/collector/Collector$Order;", "getOrder", "()Lorg/acra/collector/Collector$Order;", "collect", "", "reportField", "Lorg/acra/ReportField;", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "config", "Lorg/acra/config/CoreConfiguration;", "reportBuilder", "Lorg/acra/builder/ReportBuilder;", "target", "Lorg/acra/data/CrashReportData;", "shouldCollect", "", "Companion", "acra-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class DropBoxCollector extends BaseReportFieldCollector {
    private static final String[] SYSTEM_TAGS = {"system_app_anr", "system_app_wtf", "system_app_crash", "system_server_anr", "system_server_wtf", "system_server_crash", "BATTERY_DISCHARGE_INFO", "SYSTEM_RECOVERY_LOG", "SYSTEM_BOOT", "SYSTEM_LAST_KMSG", "APANIC_CONSOLE", "APANIC_THREADS", "SYSTEM_RESTART", "SYSTEM_TOMBSTONE", "data_app_strictmode"};
    private final SimpleDateFormat dateFormat;

    @Override // org.acra.collector.BaseReportFieldCollector, org.acra.collector.Collector, org.acra.plugins.Plugin
    public /* bridge */ /* synthetic */ boolean enabled(CoreConfiguration coreConfiguration) {
        return Plugin.CC.$default$enabled(this, coreConfiguration);
    }

    public DropBoxCollector() {
        super(ReportField.DROPBOX);
        this.dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss", Locale.getDefault());
    }

    @Override // org.acra.collector.BaseReportFieldCollector, org.acra.collector.Collector
    public Collector.Order getOrder() {
        return Collector.Order.FIRST;
    }

    @Override // org.acra.collector.BaseReportFieldCollector
    @SuppressLint({"MissingPermission"})
    public void collect(ReportField reportField, Context context, CoreConfiguration config, ReportBuilder reportBuilder, CrashReportData target) throws Exception {
        Intrinsics.checkNotNullParameter(reportField, "reportField");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(reportBuilder, "reportBuilder");
        Intrinsics.checkNotNullParameter(target, "target");
        DropBoxManager dropBoxManager = SystemServices.getDropBoxManager(context);
        Calendar calendar = Calendar.getInstance();
        calendar.roll(12, -config.getDropboxCollectionMinutes());
        long timeInMillis = calendar.getTimeInMillis();
        this.dateFormat.format(calendar.getTime());
        ArrayList<String> arrayList = new ArrayList();
        if (config.getIncludeDropBoxSystemTags()) {
            CollectionsKt.addAll(arrayList, SYSTEM_TAGS);
        }
        List list = CollectionsKt.toList(config.getAdditionalDropBoxTags());
        if (!list.isEmpty()) {
            arrayList.addAll(list);
        }
        if (arrayList.isEmpty()) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        for (String str : arrayList) {
            StringBuilder sb = new StringBuilder();
            DropBoxManager.Entry nextEntry = dropBoxManager.getNextEntry(str, timeInMillis);
            if (nextEntry == null) {
                sb.append("Nothing.");
                sb.append('\n');
            } else {
                while (nextEntry != null) {
                    long timeMillis = nextEntry.getTimeMillis();
                    calendar.setTimeInMillis(timeMillis);
                    sb.append('@');
                    sb.append(this.dateFormat.format(calendar.getTime()));
                    sb.append('\n');
                    String text = nextEntry.getText(500);
                    if (text != null) {
                        sb.append("Text: ");
                        sb.append(text);
                        sb.append('\n');
                    } else {
                        sb.append("Not Text!");
                        sb.append('\n');
                    }
                    nextEntry.close();
                    nextEntry = dropBoxManager.getNextEntry(str, timeMillis);
                }
                try {
                    jSONObject.put(str, sb.toString());
                } catch (JSONException e) {
                    ACRA.log.w(ACRA.LOG_TAG, "Failed to collect data for tag " + str, e);
                    Unit unit = Unit.INSTANCE;
                }
            }
        }
        target.put(ReportField.DROPBOX, jSONObject);
    }

    @Override // org.acra.collector.BaseReportFieldCollector
    public boolean shouldCollect(Context context, CoreConfiguration config, ReportField collect, ReportBuilder reportBuilder) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(collect, "collect");
        Intrinsics.checkNotNullParameter(reportBuilder, "reportBuilder");
        return super.shouldCollect(context, config, collect, reportBuilder) && new SharedPreferencesFactory(context, config).create().getBoolean("acra.syslog.enable", true);
    }
}
