package org.acra.collector;

import android.content.Context;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import ch.qos.logback.core.CoreConstants;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.builder.ReportBuilder;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.acra.plugins.Plugin;
import org.acra.util.StreamReader;

/* JADX INFO: compiled from: MemoryInfoCollector.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J0\u0010\n\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0012J\b\u0010\u0014\u001a\u00020\u0015H\u0012J\b\u0010\u0016\u001a\u00020\u0015H\u0012¨\u0006\u0017"}, d2 = {"Lorg/acra/collector/MemoryInfoCollector;", "Lorg/acra/collector/BaseReportFieldCollector;", "<init>", "()V", "shouldCollect", "", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "config", "Lorg/acra/config/CoreConfiguration;", "collect", "Lorg/acra/ReportField;", "reportBuilder", "Lorg/acra/builder/ReportBuilder;", "", "reportField", "target", "Lorg/acra/data/CrashReportData;", "collectMemInfo", "", "getAvailableInternalMemorySize", "", "getTotalInternalMemorySize", "acra-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class MemoryInfoCollector extends BaseReportFieldCollector {

    /* JADX INFO: compiled from: MemoryInfoCollector.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ReportField.values().length];
            try {
                iArr[ReportField.DUMPSYS_MEMINFO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ReportField.TOTAL_MEM_SIZE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ReportField.AVAILABLE_MEM_SIZE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // org.acra.collector.BaseReportFieldCollector, org.acra.collector.Collector, org.acra.plugins.Plugin
    public /* bridge */ /* synthetic */ boolean enabled(CoreConfiguration coreConfiguration) {
        return Plugin.CC.$default$enabled(this, coreConfiguration);
    }

    public MemoryInfoCollector() {
        super(ReportField.DUMPSYS_MEMINFO, ReportField.TOTAL_MEM_SIZE, ReportField.AVAILABLE_MEM_SIZE);
    }

    @Override // org.acra.collector.BaseReportFieldCollector
    public boolean shouldCollect(Context context, CoreConfiguration config, ReportField collect, ReportBuilder reportBuilder) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(collect, "collect");
        Intrinsics.checkNotNullParameter(reportBuilder, "reportBuilder");
        return super.shouldCollect(context, config, collect, reportBuilder) && !(reportBuilder.getException() instanceof OutOfMemoryError);
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
            target.put(ReportField.DUMPSYS_MEMINFO, collectMemInfo());
        } else if (i == 2) {
            target.put(ReportField.TOTAL_MEM_SIZE, getTotalInternalMemorySize());
        } else {
            if (i == 3) {
                target.put(ReportField.AVAILABLE_MEM_SIZE, getAvailableInternalMemorySize());
                return;
            }
            throw new IllegalArgumentException();
        }
    }

    private String collectMemInfo() {
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add("dumpsys");
            arrayList.add("meminfo");
            arrayList.add(String.valueOf(Process.myPid()));
            InputStream inputStream = Runtime.getRuntime().exec((String[]) arrayList.toArray(new String[0])).getInputStream();
            Intrinsics.checkNotNullExpressionValue(inputStream, "getInputStream(...)");
            return new StreamReader(inputStream, 0, 0, null, 14, null).read();
        } catch (IOException e) {
            ACRA.log.e(ACRA.LOG_TAG, "MemoryInfoCollector.meminfo could not retrieve data", e);
            return null;
        }
    }

    private long getAvailableInternalMemorySize() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return statFs.getBlockSizeLong() * statFs.getAvailableBlocksLong();
    }

    private long getTotalInternalMemorySize() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return statFs.getBlockSizeLong() * statFs.getBlockCountLong();
    }
}
