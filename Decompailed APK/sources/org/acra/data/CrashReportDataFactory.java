package org.acra.data;

import android.content.Context;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import org.acra.ACRA;
import org.acra.builder.ReportBuilder;
import org.acra.collector.ApplicationStartupCollector;
import org.acra.collector.Collector;
import org.acra.collector.CollectorException;
import org.acra.config.CoreConfiguration;

/* JADX INFO: compiled from: CrashReportDataFactory.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class CrashReportDataFactory {
    private final List collectors;
    private final CoreConfiguration config;
    private final Context context;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void collect$lambda$10$lambda$9(Collector collector, CrashReportDataFactory crashReportDataFactory, ReportBuilder reportBuilder, CrashReportData crashReportData) {
        try {
            if (ACRA.DEV_LOGGING) {
                ACRA.log.d(ACRA.LOG_TAG, "Calling collector " + collector.getClass().getName());
            }
            collector.collect(crashReportDataFactory.context, crashReportDataFactory.config, reportBuilder, crashReportData);
            if (ACRA.DEV_LOGGING) {
                ACRA.log.d(ACRA.LOG_TAG, "Collector " + collector.getClass().getName() + " completed");
            }
        } catch (CollectorException e) {
            ACRA.log.w(ACRA.LOG_TAG, "", e);
        } catch (Throwable th) {
            ACRA.log.w(ACRA.LOG_TAG, "Error in collector " + collector.getClass().getSimpleName(), th);
        }
    }

    public CrashReportDataFactory(Context context, CoreConfiguration config) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        this.context = context;
        this.config = config;
        this.collectors = CollectionsKt.sortedWith(config.getPluginLoader().loadEnabled(config, Collector.class), new Comparator() { // from class: org.acra.data.CrashReportDataFactory$special$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ComparisonsKt.compareValues(this.this$0.getSafeOrder((Collector) obj), this.this$0.getSafeOrder((Collector) obj2));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Collector.Order getSafeOrder(Collector collector) {
        try {
            return collector.getOrder();
        } catch (Exception unused) {
            return Collector.Order.NORMAL;
        }
    }

    public final CrashReportData createCrashData(ReportBuilder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        ExecutorService executorServiceNewCachedThreadPool = this.config.getParallel() ? Executors.newCachedThreadPool() : Executors.newSingleThreadExecutor();
        CrashReportData crashReportData = new CrashReportData();
        List list = this.collectors;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : list) {
            Collector.Order safeOrder = getSafeOrder((Collector) obj);
            Object arrayList = linkedHashMap.get(safeOrder);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(safeOrder, arrayList);
            }
            ((List) arrayList).add(obj);
        }
        for (Map.Entry entry : MapsKt.toSortedMap(linkedHashMap).entrySet()) {
            Collector.Order order = (Collector.Order) entry.getKey();
            List list2 = (List) entry.getValue();
            if (ACRA.DEV_LOGGING) {
                ACRA.log.d(ACRA.LOG_TAG, "Starting collectors with priority " + order.name());
            }
            Intrinsics.checkNotNull(list2);
            Intrinsics.checkNotNull(executorServiceNewCachedThreadPool);
            collect(list2, executorServiceNewCachedThreadPool, builder, crashReportData);
            if (ACRA.DEV_LOGGING) {
                ACRA.log.d(ACRA.LOG_TAG, "Finished collectors with priority " + order.name());
            }
        }
        return crashReportData;
    }

    public final void collectStartUp() {
        for (Collector collector : this.collectors) {
            if (collector instanceof ApplicationStartupCollector) {
                try {
                    ((ApplicationStartupCollector) collector).collectApplicationStartUp(this.context, this.config);
                } catch (Throwable th) {
                    ACRA.log.w(ACRA.LOG_TAG, collector.getClass().getSimpleName() + " failed to collect its startup data", th);
                }
            }
        }
    }

    private final void collect(List list, ExecutorService executorService, final ReportBuilder reportBuilder, final CrashReportData crashReportData) {
        ArrayList<Future> arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            final Collector collector = (Collector) it.next();
            arrayList.add(executorService.submit(new Runnable() { // from class: org.acra.data.CrashReportDataFactory$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    CrashReportDataFactory.collect$lambda$10$lambda$9(collector, this, reportBuilder, crashReportData);
                }
            }));
        }
        for (Future future : arrayList) {
            while (!future.isDone()) {
                try {
                    future.get();
                } catch (InterruptedException unused) {
                } catch (ExecutionException unused2) {
                }
            }
        }
    }
}
