package org.acra.collector;

import android.content.Context;
import ch.qos.logback.core.CoreConstants;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.acra.ReportField;
import org.acra.builder.ReportBuilder;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.acra.plugins.Plugin;

/* JADX INFO: compiled from: TimeCollector.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J0\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0015\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J(\u0010\u0016\u001a\u00020\u00172\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\bH\u0012R\u000e\u0010\u0005\u001a\u00020\u0006X\u0092\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0092\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/acra/collector/TimeCollector;", "Lorg/acra/collector/BaseReportFieldCollector;", "Lorg/acra/collector/ApplicationStartupCollector;", "<init>", "()V", "dateFormat", "Ljava/text/SimpleDateFormat;", "appStartDate", "Ljava/util/Calendar;", "collect", "", "reportField", "Lorg/acra/ReportField;", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "config", "Lorg/acra/config/CoreConfiguration;", "reportBuilder", "Lorg/acra/builder/ReportBuilder;", "target", "Lorg/acra/data/CrashReportData;", "collectApplicationStartUp", "shouldCollect", "", "getTimeString", "", "time", "acra-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class TimeCollector extends BaseReportFieldCollector implements ApplicationStartupCollector {
    private Calendar appStartDate;
    private final SimpleDateFormat dateFormat;

    /* JADX INFO: compiled from: TimeCollector.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ReportField.values().length];
            try {
                iArr[ReportField.USER_APP_START_DATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ReportField.USER_CRASH_DATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // org.acra.collector.BaseReportFieldCollector, org.acra.collector.Collector, org.acra.plugins.Plugin
    public /* bridge */ /* synthetic */ boolean enabled(CoreConfiguration coreConfiguration) {
        return Plugin.CC.$default$enabled(this, coreConfiguration);
    }

    public TimeCollector() {
        super(ReportField.USER_APP_START_DATE, ReportField.USER_CRASH_DATE);
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ", Locale.ENGLISH);
    }

    @Override // org.acra.collector.BaseReportFieldCollector
    public void collect(ReportField reportField, Context context, CoreConfiguration config, ReportBuilder reportBuilder, CrashReportData target) {
        Calendar gregorianCalendar;
        Intrinsics.checkNotNullParameter(reportField, "reportField");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(reportBuilder, "reportBuilder");
        Intrinsics.checkNotNullParameter(target, "target");
        int i = WhenMappings.$EnumSwitchMapping$0[reportField.ordinal()];
        if (i == 1) {
            gregorianCalendar = this.appStartDate;
        } else if (i == 2) {
            gregorianCalendar = new GregorianCalendar();
        } else {
            throw new IllegalArgumentException();
        }
        Intrinsics.checkNotNull(gregorianCalendar);
        target.put(reportField, getTimeString(gregorianCalendar));
    }

    @Override // org.acra.collector.ApplicationStartupCollector
    public void collectApplicationStartUp(Context context, CoreConfiguration config) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        if (config.getReportContent().contains(ReportField.USER_APP_START_DATE)) {
            this.appStartDate = new GregorianCalendar();
        }
    }

    @Override // org.acra.collector.BaseReportFieldCollector
    public boolean shouldCollect(Context context, CoreConfiguration config, ReportField collect, ReportBuilder reportBuilder) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(collect, "collect");
        Intrinsics.checkNotNullParameter(reportBuilder, "reportBuilder");
        return collect == ReportField.USER_CRASH_DATE || super.shouldCollect(context, config, collect, reportBuilder);
    }

    private String getTimeString(Calendar time) {
        String str = this.dateFormat.format(Long.valueOf(time.getTimeInMillis()));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }
}
