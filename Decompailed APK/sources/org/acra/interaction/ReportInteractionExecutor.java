package org.acra.interaction;

import android.content.Context;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.acra.ACRA;
import org.acra.config.CoreConfiguration;

/* JADX INFO: compiled from: ReportInteractionExecutor.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class ReportInteractionExecutor {
    private final CoreConfiguration config;
    private final Context context;
    private final List reportInteractions;

    /* JADX INFO: Access modifiers changed from: private */
    public static final Boolean performInteractions$lambda$2$lambda$1(ReportInteraction reportInteraction, ReportInteractionExecutor reportInteractionExecutor, File file) {
        if (ACRA.DEV_LOGGING) {
            ACRA.log.d(ACRA.LOG_TAG, "Calling ReportInteraction of class " + reportInteraction.getClass().getName());
        }
        return Boolean.valueOf(reportInteraction.performInteraction(reportInteractionExecutor.context, reportInteractionExecutor.config, file));
    }

    public ReportInteractionExecutor(Context context, CoreConfiguration config) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        this.context = context;
        this.config = config;
        this.reportInteractions = config.getPluginLoader().loadEnabled(config, ReportInteraction.class);
    }

    public final boolean hasInteractions() {
        return !this.reportInteractions.isEmpty();
    }

    public final boolean performInteractions(final File reportFile) {
        Intrinsics.checkNotNullParameter(reportFile, "reportFile");
        ExecutorService executorServiceNewCachedThreadPool = Executors.newCachedThreadPool();
        List<ReportInteraction> list = this.reportInteractions;
        ArrayList<Future> arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (final ReportInteraction reportInteraction : list) {
            arrayList.add(executorServiceNewCachedThreadPool.submit(new Callable() { // from class: org.acra.interaction.ReportInteractionExecutor$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return ReportInteractionExecutor.performInteractions$lambda$2$lambda$1(reportInteraction, this, reportFile);
                }
            }));
        }
        boolean zBooleanValue = true;
        for (Future future : arrayList) {
            do {
                try {
                    Object obj = future.get();
                    Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                    zBooleanValue &= ((Boolean) obj).booleanValue();
                } catch (InterruptedException unused) {
                } catch (ExecutionException e) {
                    ACRA.log.w(ACRA.LOG_TAG, "Report interaction threw exception, will be ignored.", e);
                }
            } while (!future.isDone());
        }
        return zBooleanValue;
    }
}
