package org.acra.interaction;

import android.content.Context;
import ch.qos.logback.core.CoreConstants;
import java.io.File;
import kotlin.Metadata;
import org.acra.config.CoreConfiguration;
import org.acra.plugins.Plugin;

/* JADX INFO: compiled from: ReportInteraction.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lorg/acra/interaction/ReportInteraction;", "Lorg/acra/plugins/Plugin;", "performInteraction", "", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "config", "Lorg/acra/config/CoreConfiguration;", "reportFile", "Ljava/io/File;", "acra-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface ReportInteraction extends Plugin {

    /* JADX INFO: renamed from: org.acra.interaction.ReportInteraction$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: ReportInteraction.kt */
    public abstract /* synthetic */ class CC {
    }

    @Override // org.acra.plugins.Plugin
    /* bridge */ /* synthetic */ boolean enabled(CoreConfiguration coreConfiguration);

    boolean performInteraction(Context context, CoreConfiguration config, File reportFile);
}
