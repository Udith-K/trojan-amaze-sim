package org.acra.collector;

import android.content.Context;
import android.os.Build;
import ch.qos.logback.core.CoreConstants;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.acra.ReportField;
import org.acra.builder.ReportBuilder;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.acra.plugins.Plugin;
import org.acra.util.Installation;

/* JADX INFO: compiled from: SimpleValuesCollector.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0017\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J(\u0010\u0010\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\tH\u0012¨\u0006\u0015"}, d2 = {"Lorg/acra/collector/SimpleValuesCollector;", "Lorg/acra/collector/BaseReportFieldCollector;", "<init>", "()V", "collect", "", "reportField", "Lorg/acra/ReportField;", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "config", "Lorg/acra/config/CoreConfiguration;", "reportBuilder", "Lorg/acra/builder/ReportBuilder;", "target", "Lorg/acra/data/CrashReportData;", "shouldCollect", "", "getApplicationFilePath", "", "Companion", "acra-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class SimpleValuesCollector extends BaseReportFieldCollector {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: SimpleValuesCollector.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ReportField.values().length];
            try {
                iArr[ReportField.IS_SILENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ReportField.REPORT_ID.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ReportField.INSTALLATION_ID.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ReportField.PACKAGE_NAME.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ReportField.PHONE_MODEL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[ReportField.ANDROID_VERSION.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[ReportField.BRAND.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[ReportField.PRODUCT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[ReportField.FILE_PATH.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[ReportField.USER_IP.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // org.acra.collector.BaseReportFieldCollector, org.acra.collector.Collector, org.acra.plugins.Plugin
    public /* bridge */ /* synthetic */ boolean enabled(CoreConfiguration coreConfiguration) {
        return Plugin.CC.$default$enabled(this, coreConfiguration);
    }

    public SimpleValuesCollector() {
        super(ReportField.IS_SILENT, ReportField.REPORT_ID, ReportField.INSTALLATION_ID, ReportField.PACKAGE_NAME, ReportField.PHONE_MODEL, ReportField.ANDROID_VERSION, ReportField.BRAND, ReportField.PRODUCT, ReportField.FILE_PATH, ReportField.USER_IP);
    }

    @Override // org.acra.collector.BaseReportFieldCollector
    public void collect(ReportField reportField, Context context, CoreConfiguration config, ReportBuilder reportBuilder, CrashReportData target) throws Exception {
        Intrinsics.checkNotNullParameter(reportField, "reportField");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(reportBuilder, "reportBuilder");
        Intrinsics.checkNotNullParameter(target, "target");
        switch (WhenMappings.$EnumSwitchMapping$0[reportField.ordinal()]) {
            case 1:
                target.put(ReportField.IS_SILENT, reportBuilder.isSendSilently());
                return;
            case 2:
                target.put(ReportField.REPORT_ID, UUID.randomUUID().toString());
                return;
            case 3:
                target.put(ReportField.INSTALLATION_ID, Installation.id(context));
                return;
            case 4:
                target.put(ReportField.PACKAGE_NAME, context.getPackageName());
                return;
            case 5:
                target.put(ReportField.PHONE_MODEL, Build.MODEL);
                return;
            case 6:
                target.put(ReportField.ANDROID_VERSION, Build.VERSION.RELEASE);
                return;
            case 7:
                target.put(ReportField.BRAND, Build.BRAND);
                return;
            case 8:
                target.put(ReportField.PRODUCT, Build.PRODUCT);
                return;
            case 9:
                target.put(ReportField.FILE_PATH, getApplicationFilePath(context));
                return;
            case 10:
                target.put(ReportField.USER_IP, INSTANCE.getLocalIpAddress());
                return;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override // org.acra.collector.BaseReportFieldCollector
    public boolean shouldCollect(Context context, CoreConfiguration config, ReportField collect, ReportBuilder reportBuilder) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(collect, "collect");
        Intrinsics.checkNotNullParameter(reportBuilder, "reportBuilder");
        return collect == ReportField.IS_SILENT || collect == ReportField.REPORT_ID || super.shouldCollect(context, config, collect, reportBuilder);
    }

    private String getApplicationFilePath(Context context) {
        String absolutePath = context.getFilesDir().getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
        return absolutePath;
    }

    /* JADX INFO: compiled from: SimpleValuesCollector.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String getLocalIpAddress() throws SocketException {
            StringBuilder sb = new StringBuilder();
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            boolean z = true;
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddressNextElement = inetAddresses.nextElement();
                    if (!inetAddressNextElement.isLoopbackAddress()) {
                        if (!z) {
                            sb.append('\n');
                        }
                        sb.append(inetAddressNextElement.getHostAddress());
                        z = false;
                    }
                }
            }
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            return string;
        }
    }
}
