package org.acra.config;

import android.content.Context;
import ch.qos.logback.core.CoreConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.acra.builder.LastActivityManager;
import org.acra.builder.ReportBuilder;
import org.acra.data.CrashReportData;
import org.acra.plugins.Plugin;

/* JADX INFO: compiled from: ReportingAdministrator.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J \u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J \u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J*\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0013À\u0006\u0001"}, d2 = {"Lorg/acra/config/ReportingAdministrator;", "Lorg/acra/plugins/Plugin;", "shouldStartCollecting", "", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "config", "Lorg/acra/config/CoreConfiguration;", "reportBuilder", "Lorg/acra/builder/ReportBuilder;", "shouldSendReport", "crashReportData", "Lorg/acra/data/CrashReportData;", "notifyReportDropped", "", "shouldFinishActivity", "lastActivityManager", "Lorg/acra/builder/LastActivityManager;", "shouldKillApplication", "acra-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface ReportingAdministrator extends Plugin {

    /* JADX INFO: renamed from: org.acra.config.ReportingAdministrator$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: ReportingAdministrator.kt */
    public abstract /* synthetic */ class CC {
        public static void $default$notifyReportDropped(ReportingAdministrator reportingAdministrator, Context context, CoreConfiguration config) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(config, "config");
        }

        public static boolean $default$shouldFinishActivity(ReportingAdministrator reportingAdministrator, Context context, CoreConfiguration config, LastActivityManager lastActivityManager) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(config, "config");
            Intrinsics.checkNotNullParameter(lastActivityManager, "lastActivityManager");
            return true;
        }

        public static boolean $default$shouldKillApplication(ReportingAdministrator reportingAdministrator, Context context, CoreConfiguration config, ReportBuilder reportBuilder, CrashReportData crashReportData) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(config, "config");
            Intrinsics.checkNotNullParameter(reportBuilder, "reportBuilder");
            return true;
        }

        public static boolean $default$shouldSendReport(ReportingAdministrator reportingAdministrator, Context context, CoreConfiguration config, CrashReportData crashReportData) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(config, "config");
            Intrinsics.checkNotNullParameter(crashReportData, "crashReportData");
            return true;
        }

        public static boolean $default$shouldStartCollecting(ReportingAdministrator reportingAdministrator, Context context, CoreConfiguration config, ReportBuilder reportBuilder) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(config, "config");
            Intrinsics.checkNotNullParameter(reportBuilder, "reportBuilder");
            return true;
        }
    }

    @Override // org.acra.plugins.Plugin
    /* bridge */ /* synthetic */ boolean enabled(CoreConfiguration coreConfiguration);

    void notifyReportDropped(Context context, CoreConfiguration config);

    boolean shouldFinishActivity(Context context, CoreConfiguration config, LastActivityManager lastActivityManager);

    boolean shouldKillApplication(Context context, CoreConfiguration config, ReportBuilder reportBuilder, CrashReportData crashReportData);

    boolean shouldSendReport(Context context, CoreConfiguration config, CrashReportData crashReportData);

    boolean shouldStartCollecting(Context context, CoreConfiguration config, ReportBuilder reportBuilder);
}
