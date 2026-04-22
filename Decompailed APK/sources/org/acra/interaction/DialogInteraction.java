package org.acra.interaction;

import android.content.Context;
import android.content.Intent;
import ch.qos.logback.core.CoreConstants;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.acra.ACRA;
import org.acra.config.ConfigUtils;
import org.acra.config.CoreConfiguration;
import org.acra.config.DialogConfiguration;
import org.acra.plugins.HasConfigPlugin;
import org.acra.prefs.SharedPreferencesFactory;

/* JADX INFO: compiled from: DialogInteraction.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u0000 \u000f2\u00020\u00012\u00020\u0002:\u0001\u000fB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J \u0010\r\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0012¨\u0006\u0010"}, d2 = {"Lorg/acra/interaction/DialogInteraction;", "Lorg/acra/plugins/HasConfigPlugin;", "Lorg/acra/interaction/ReportInteraction;", "<init>", "()V", "performInteraction", "", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "config", "Lorg/acra/config/CoreConfiguration;", "reportFile", "Ljava/io/File;", "createCrashReportDialogIntent", "Landroid/content/Intent;", "Companion", "acra-dialog_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class DialogInteraction extends HasConfigPlugin implements ReportInteraction {
    public static final String EXTRA_REPORT_CONFIG = "REPORT_CONFIG";
    public static final String EXTRA_REPORT_FILE = "REPORT_FILE";

    private Intent createCrashReportDialogIntent(Context context, CoreConfiguration config, File reportFile) {
        if (ACRA.DEV_LOGGING) {
            ACRA.log.d(ACRA.LOG_TAG, "Creating DialogIntent for " + reportFile);
        }
        Intent intent = new Intent(context, (Class<?>) ((DialogConfiguration) ConfigUtils.getPluginConfiguration(config, DialogConfiguration.class)).getReportDialogClass());
        intent.putExtra(EXTRA_REPORT_FILE, reportFile);
        intent.putExtra(EXTRA_REPORT_CONFIG, config);
        return intent;
    }

    public DialogInteraction() {
        super(DialogConfiguration.class);
    }

    @Override // org.acra.interaction.ReportInteraction
    public boolean performInteraction(Context context, CoreConfiguration config, File reportFile) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(reportFile, "reportFile");
        if (new SharedPreferencesFactory(context, config).create().getBoolean("acra.alwaysaccept", false)) {
            return true;
        }
        if (ACRA.DEV_LOGGING) {
            ACRA.log.d(ACRA.LOG_TAG, "Creating CrashReportDialog for " + reportFile);
        }
        Intent intentCreateCrashReportDialogIntent = createCrashReportDialogIntent(context, config, reportFile);
        intentCreateCrashReportDialogIntent.setFlags(268435456);
        context.startActivity(intentCreateCrashReportDialogIntent);
        return false;
    }
}
