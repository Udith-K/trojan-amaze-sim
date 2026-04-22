package org.acra.sender;

import android.content.Context;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.acra.ACRA;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;

/* JADX INFO: compiled from: ReportSender.kt */
/* JADX INFO: loaded from: classes2.dex */
public interface ReportSender {
    public static final Companion Companion = Companion.$$INSTANCE;

    boolean requiresForeground();

    void send(Context context, CrashReportData crashReportData);

    void send(Context context, CrashReportData crashReportData, Bundle bundle);

    /* JADX INFO: compiled from: ReportSender.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public final List loadSenders(Context context, CoreConfiguration config) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(config, "config");
            if (ACRA.DEV_LOGGING) {
                ACRA.log.d(ACRA.LOG_TAG, "Using PluginLoader to find ReportSender factories");
            }
            List listLoadEnabled = config.getPluginLoader().loadEnabled(config, ReportSenderFactory.class);
            if (ACRA.DEV_LOGGING) {
                ACRA.log.d(ACRA.LOG_TAG, "reportSenderFactories : " + listLoadEnabled);
            }
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listLoadEnabled, 10));
            Iterator it = listLoadEnabled.iterator();
            while (it.hasNext()) {
                ReportSender reportSenderCreate = ((ReportSenderFactory) it.next()).create(context, config);
                if (ACRA.DEV_LOGGING) {
                    ACRA.log.d(ACRA.LOG_TAG, "Adding reportSender : " + reportSenderCreate);
                }
                arrayList.add(reportSenderCreate);
            }
            return arrayList;
        }

        private Companion() {
        }

        public final boolean hasForegroundSenders(Context context, CoreConfiguration config) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(config, "config");
            List listLoadSenders = loadSenders(context, config);
            if ((listLoadSenders instanceof Collection) && listLoadSenders.isEmpty()) {
                return false;
            }
            Iterator it = listLoadSenders.iterator();
            while (it.hasNext()) {
                if (((ReportSender) it.next()).requiresForeground()) {
                    return true;
                }
            }
            return false;
        }

        public final boolean hasBackgroundSenders(Context context, CoreConfiguration config) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(config, "config");
            List listLoadSenders = loadSenders(context, config);
            if ((listLoadSenders instanceof Collection) && listLoadSenders.isEmpty()) {
                return false;
            }
            Iterator it = listLoadSenders.iterator();
            while (it.hasNext()) {
                if (!((ReportSender) it.next()).requiresForeground()) {
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX INFO: renamed from: org.acra.sender.ReportSender$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: ReportSender.kt */
    public abstract /* synthetic */ class CC {
        public static void $default$send(ReportSender reportSender, Context context, CrashReportData errorContent, Bundle extras) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(errorContent, "errorContent");
            Intrinsics.checkNotNullParameter(extras, "extras");
            reportSender.send(context, errorContent);
        }

        public static boolean $default$requiresForeground(ReportSender reportSender) {
            return false;
        }
    }
}
