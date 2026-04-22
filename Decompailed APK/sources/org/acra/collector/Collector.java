package org.acra.collector;

import android.content.Context;
import ch.qos.logback.core.CoreConstants;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.acra.builder.ReportBuilder;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.acra.plugins.Plugin;

/* JADX INFO: compiled from: Collector.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0001\u0010J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&R\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0011À\u0006\u0001"}, d2 = {"Lorg/acra/collector/Collector;", "Lorg/acra/plugins/Plugin;", "collect", "", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "config", "Lorg/acra/config/CoreConfiguration;", "reportBuilder", "Lorg/acra/builder/ReportBuilder;", "crashReportData", "Lorg/acra/data/CrashReportData;", "order", "Lorg/acra/collector/Collector$Order;", "getOrder", "()Lorg/acra/collector/Collector$Order;", "Order", "acra-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface Collector extends Plugin {
    void collect(Context context, CoreConfiguration config, ReportBuilder reportBuilder, CrashReportData crashReportData) throws CollectorException;

    @Override // org.acra.plugins.Plugin
    /* bridge */ /* synthetic */ boolean enabled(CoreConfiguration coreConfiguration);

    Order getOrder();

    /* JADX INFO: renamed from: org.acra.collector.Collector$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Collector.kt */
    public abstract /* synthetic */ class CC {
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: Collector.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lorg/acra/collector/Collector$Order;", "", "<init>", "(Ljava/lang/String;I)V", "FIRST", "EARLY", "NORMAL", "LATE", "LAST", "acra-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Order {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ Order[] $VALUES;
        public static final Order FIRST = new Order("FIRST", 0);
        public static final Order EARLY = new Order("EARLY", 1);
        public static final Order NORMAL = new Order("NORMAL", 2);
        public static final Order LATE = new Order("LATE", 3);
        public static final Order LAST = new Order("LAST", 4);

        private static final /* synthetic */ Order[] $values() {
            return new Order[]{FIRST, EARLY, NORMAL, LATE, LAST};
        }

        public static EnumEntries getEntries() {
            return $ENTRIES;
        }

        private Order(String str, int i) {
        }

        static {
            Order[] orderArr$values = $values();
            $VALUES = orderArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(orderArr$values);
        }

        public static Order valueOf(String str) {
            return (Order) Enum.valueOf(Order.class, str);
        }

        public static Order[] values() {
            return (Order[]) $VALUES.clone();
        }
    }
}
