package org.acra.collector;

import android.content.Context;
import android.text.TextUtils;
import ch.qos.logback.core.CoreConstants;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import org.acra.ReportField;
import org.acra.builder.ReportBuilder;
import org.acra.collector.Collector;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.acra.plugins.Plugin;

/* JADX INFO: compiled from: StacktraceCollector.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J(\u0010\u0014\u001a\u00020\u00152\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u001c\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0012J\u0012\u0010\u001b\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0012R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u001c"}, d2 = {"Lorg/acra/collector/StacktraceCollector;", "Lorg/acra/collector/BaseReportFieldCollector;", "<init>", "()V", "order", "Lorg/acra/collector/Collector$Order;", "getOrder", "()Lorg/acra/collector/Collector$Order;", "collect", "", "reportField", "Lorg/acra/ReportField;", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "config", "Lorg/acra/config/CoreConfiguration;", "reportBuilder", "Lorg/acra/builder/ReportBuilder;", "target", "Lorg/acra/data/CrashReportData;", "shouldCollect", "", "getStackTrace", "", "msg", "th", "", "getStackTraceHash", "acra-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class StacktraceCollector extends BaseReportFieldCollector {

    /* JADX INFO: compiled from: StacktraceCollector.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ReportField.values().length];
            try {
                iArr[ReportField.STACK_TRACE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ReportField.STACK_TRACE_HASH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // org.acra.collector.BaseReportFieldCollector, org.acra.collector.Collector, org.acra.plugins.Plugin
    public /* bridge */ /* synthetic */ boolean enabled(CoreConfiguration coreConfiguration) {
        return Plugin.CC.$default$enabled(this, coreConfiguration);
    }

    public StacktraceCollector() {
        super(ReportField.STACK_TRACE, ReportField.STACK_TRACE_HASH);
    }

    @Override // org.acra.collector.BaseReportFieldCollector, org.acra.collector.Collector
    public Collector.Order getOrder() {
        return Collector.Order.FIRST;
    }

    @Override // org.acra.collector.BaseReportFieldCollector
    public void collect(ReportField reportField, Context context, CoreConfiguration config, ReportBuilder reportBuilder, CrashReportData target) {
        Intrinsics.checkNotNullParameter(reportField, "reportField");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(reportBuilder, "reportBuilder");
        Intrinsics.checkNotNullParameter(target, "target");
        int i = WhenMappings.$EnumSwitchMapping$0[reportField.ordinal()];
        if (i == 1) {
            target.put(ReportField.STACK_TRACE, getStackTrace(reportBuilder.getMessage(), reportBuilder.getException()));
        } else {
            if (i == 2) {
                target.put(ReportField.STACK_TRACE_HASH, getStackTraceHash(reportBuilder.getException()));
                return;
            }
            throw new IllegalArgumentException();
        }
    }

    @Override // org.acra.collector.BaseReportFieldCollector
    public boolean shouldCollect(Context context, CoreConfiguration config, ReportField collect, ReportBuilder reportBuilder) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(collect, "collect");
        Intrinsics.checkNotNullParameter(reportBuilder, "reportBuilder");
        return collect == ReportField.STACK_TRACE || super.shouldCollect(context, config, collect, reportBuilder);
    }

    private String getStackTrace(String msg, Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        if (msg != null) {
            try {
                if (!TextUtils.isEmpty(msg)) {
                    printWriter.println(msg);
                }
            } finally {
            }
        }
        if (th != null) {
            th.printStackTrace(printWriter);
        }
        String string = stringWriter.toString();
        CloseableKt.closeFinally(printWriter, null);
        return string;
    }

    private String getStackTraceHash(Throwable th) {
        StringBuilder sb = new StringBuilder();
        while (th != null) {
            Iterator it = ArrayIteratorKt.iterator(th.getStackTrace());
            while (it.hasNext()) {
                StackTraceElement stackTraceElement = (StackTraceElement) it.next();
                sb.append(stackTraceElement.getClassName());
                sb.append(stackTraceElement.getMethodName());
            }
            th = th.getCause();
        }
        String hexString = Integer.toHexString(sb.toString().hashCode());
        Intrinsics.checkNotNullExpressionValue(hexString, "toHexString(...)");
        return hexString;
    }
}
