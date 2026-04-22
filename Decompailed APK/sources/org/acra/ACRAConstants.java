package org.acra;

import java.util.List;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: ACRAConstants.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class ACRAConstants {
    public static final List DEFAULT_REPORT_FIELDS;
    public static final ACRAConstants INSTANCE = new ACRAConstants();
    public static final String SILENT_SUFFIX;

    private ACRAConstants() {
    }

    static {
        ReportField reportField = ReportField.IS_SILENT;
        SILENT_SUFFIX = "-" + reportField;
        DEFAULT_REPORT_FIELDS = CollectionsKt.listOf((Object[]) new ReportField[]{ReportField.REPORT_ID, ReportField.APP_VERSION_CODE, ReportField.APP_VERSION_NAME, ReportField.PACKAGE_NAME, ReportField.FILE_PATH, ReportField.PHONE_MODEL, ReportField.BRAND, ReportField.PRODUCT, ReportField.ANDROID_VERSION, ReportField.BUILD, ReportField.TOTAL_MEM_SIZE, ReportField.AVAILABLE_MEM_SIZE, ReportField.BUILD_CONFIG, ReportField.CUSTOM_DATA, reportField, ReportField.STACK_TRACE, ReportField.INITIAL_CONFIGURATION, ReportField.CRASH_CONFIGURATION, ReportField.DISPLAY, ReportField.USER_COMMENT, ReportField.USER_EMAIL, ReportField.USER_APP_START_DATE, ReportField.USER_CRASH_DATE, ReportField.DUMPSYS_MEMINFO, ReportField.LOGCAT, ReportField.INSTALLATION_ID, ReportField.DEVICE_FEATURES, ReportField.ENVIRONMENT, ReportField.SHARED_PREFERENCES});
    }
}
