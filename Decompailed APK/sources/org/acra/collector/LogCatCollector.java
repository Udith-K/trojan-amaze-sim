package org.acra.collector;

import android.content.Context;
import android.os.Process;
import ch.qos.logback.core.CoreConstants;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.builder.ReportBuilder;
import org.acra.collector.Collector;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.acra.log.ACRALog;
import org.acra.plugins.Plugin;
import org.acra.prefs.SharedPreferencesFactory;
import org.acra.util.StreamReader;

/* JADX INFO: compiled from: LogCatCollector.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0017\u0018\u0000  2\u00020\u0001:\u0001 B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\tH\u0012J(\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J0\u0010\u0011\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J6\u0010\u0019\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u001b2\u0014\u0010\u001c\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0012R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006!"}, d2 = {"Lorg/acra/collector/LogCatCollector;", "Lorg/acra/collector/BaseReportFieldCollector;", "<init>", "()V", "order", "Lorg/acra/collector/Collector$Order;", "getOrder", "()Lorg/acra/collector/Collector$Order;", "collectLogCat", "", "config", "Lorg/acra/config/CoreConfiguration;", "bufferName", "shouldCollect", "", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "collect", "Lorg/acra/ReportField;", "reportBuilder", "Lorg/acra/builder/ReportBuilder;", "", "reportField", "target", "Lorg/acra/data/CrashReportData;", "streamToString", "input", "Ljava/io/InputStream;", "filter", "Lkotlin/Function1;", "limit", "", "Companion", "acra-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class LogCatCollector extends BaseReportFieldCollector {
    private static final int READ_TIMEOUT = 3000;

    /* JADX INFO: compiled from: LogCatCollector.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ReportField.values().length];
            try {
                iArr[ReportField.LOGCAT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ReportField.EVENTSLOG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ReportField.RADIOLOG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // org.acra.collector.BaseReportFieldCollector, org.acra.collector.Collector, org.acra.plugins.Plugin
    public /* bridge */ /* synthetic */ boolean enabled(CoreConfiguration coreConfiguration) {
        return Plugin.CC.$default$enabled(this, coreConfiguration);
    }

    public LogCatCollector() {
        super(ReportField.LOGCAT, ReportField.EVENTSLOG, ReportField.RADIOLOG);
    }

    @Override // org.acra.collector.BaseReportFieldCollector, org.acra.collector.Collector
    public Collector.Order getOrder() {
        return Collector.Order.FIRST;
    }

    private String collectLogCat(CoreConfiguration config, String bufferName) throws IOException {
        Process.myPid();
        ArrayList arrayList = new ArrayList();
        arrayList.add("logcat");
        if (bufferName != null) {
            arrayList.add("-b");
            arrayList.add(bufferName);
        }
        List list = CollectionsKt.toList(config.getLogcatArguments());
        int iIndexOf = list.indexOf("-t");
        int i = -1;
        if (iIndexOf > -1 && iIndexOf < list.size()) {
            i = Integer.parseInt((String) list.get(iIndexOf + 1));
        }
        arrayList.addAll(list);
        Process processStart = new ProcessBuilder(new String[0]).command(arrayList).redirectErrorStream(true).start();
        if (ACRA.DEV_LOGGING) {
            ACRALog aCRALog = ACRA.log;
            String str = ACRA.LOG_TAG;
            if (bufferName == null) {
                bufferName = CoreConstants.DEFAULT_CONTEXT_NAME;
            }
            aCRALog.d(str, "Retrieving logcat output (buffer:" + bufferName + ")...");
        }
        try {
            InputStream inputStream = processStart.getInputStream();
            Intrinsics.checkNotNullExpressionValue(inputStream, "getInputStream(...)");
            return streamToString(config, inputStream, null, i);
        } finally {
            processStart.destroy();
        }
    }

    private static final boolean collectLogCat$lambda$2$lambda$1(String str, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return StringsKt.contains$default((CharSequence) it, (CharSequence) str, false, 2, (Object) null);
    }

    @Override // org.acra.collector.BaseReportFieldCollector
    public boolean shouldCollect(Context context, CoreConfiguration config, ReportField collect, ReportBuilder reportBuilder) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(collect, "collect");
        Intrinsics.checkNotNullParameter(reportBuilder, "reportBuilder");
        return super.shouldCollect(context, config, collect, reportBuilder) && new SharedPreferencesFactory(context, config).create().getBoolean("acra.syslog.enable", true);
    }

    @Override // org.acra.collector.BaseReportFieldCollector
    public void collect(ReportField reportField, Context context, CoreConfiguration config, ReportBuilder reportBuilder, CrashReportData target) throws IOException {
        String str;
        Intrinsics.checkNotNullParameter(reportField, "reportField");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(reportBuilder, "reportBuilder");
        Intrinsics.checkNotNullParameter(target, "target");
        int i = WhenMappings.$EnumSwitchMapping$0[reportField.ordinal()];
        if (i == 1) {
            str = null;
        } else if (i == 2) {
            str = "events";
        } else if (i == 3) {
            str = "radio";
        } else {
            throw new IllegalArgumentException();
        }
        target.put(reportField, collectLogCat(config, str));
    }

    private String streamToString(CoreConfiguration config, InputStream input, Function1 filter, int limit) throws IOException {
        StreamReader limit2 = new StreamReader(input, 0, 0, null, 14, null).setFilter(filter).setLimit(limit);
        if (config.getLogcatReadNonBlocking()) {
            limit2.setTimeout(READ_TIMEOUT);
        }
        return limit2.read();
    }
}
