package org.acra.config;

import java.io.Serializable;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.acra.ACRAConstants;
import org.acra.ReportField;
import org.acra.attachment.AttachmentUriProvider;
import org.acra.attachment.DefaultAttachmentProvider;
import org.acra.data.StringFormat;
import org.acra.file.Directory;
import org.acra.plugins.PluginLoader;
import org.acra.plugins.ServicePluginLoader;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;

/* JADX INFO: compiled from: CoreConfiguration.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class CoreConfiguration implements Serializable, Configuration {
    private final List additionalDropBoxTags;
    private final List additionalSharedPreferences;
    private final boolean alsoReportToAndroidFramework;
    private final String applicationLogFile;
    private final Directory applicationLogFileDir;
    private final int applicationLogFileLines;
    private final Class attachmentUriProvider;
    private final List attachmentUris;
    private final Class buildConfigClass;
    private final boolean deleteUnapprovedReportsOnApplicationStart;
    private final int dropboxCollectionMinutes;
    private final List excludeMatchingSettingsKeys;
    private final List excludeMatchingSharedPreferencesKeys;
    private final boolean includeDropBoxSystemTags;
    private final List logcatArguments;
    private final boolean logcatFilterByPid;
    private final boolean logcatReadNonBlocking;
    private final boolean parallel;
    private final List pluginConfigurations;
    private final PluginLoader pluginLoader;
    private final List reportContent;
    private final StringFormat reportFormat;
    private final String reportSendFailureToast;
    private final String reportSendSuccessToast;
    private final Class retryPolicyClass;
    private final boolean sendReportsInDevMode;
    private final String sharedPreferencesName;
    private final boolean stopServicesOnCrash;

    public CoreConfiguration() {
        this(null, false, null, 0, null, null, false, false, null, false, false, false, null, null, null, null, 0, null, null, false, null, null, null, null, null, false, null, null, 268435455, null);
    }

    @Override // org.acra.config.Configuration
    /* JADX INFO: renamed from: enabled */
    public boolean getEnabled() {
        return true;
    }

    public CoreConfiguration(String str, boolean z, List<String> additionalDropBoxTags, int i, List<String> logcatArguments, List<? extends ReportField> reportContent, boolean z2, boolean z3, List<String> additionalSharedPreferences, boolean z4, boolean z5, boolean z6, List<String> excludeMatchingSharedPreferencesKeys, List<String> excludeMatchingSettingsKeys, Class<?> cls, String str2, int i2, Directory applicationLogFileDir, Class<? extends RetryPolicy> retryPolicyClass, boolean z7, List<String> attachmentUris, Class<? extends AttachmentUriProvider> attachmentUriProvider, String str3, String str4, StringFormat reportFormat, boolean z8, PluginLoader pluginLoader, List<? extends Configuration> pluginConfigurations) {
        Intrinsics.checkNotNullParameter(additionalDropBoxTags, "additionalDropBoxTags");
        Intrinsics.checkNotNullParameter(logcatArguments, "logcatArguments");
        Intrinsics.checkNotNullParameter(reportContent, "reportContent");
        Intrinsics.checkNotNullParameter(additionalSharedPreferences, "additionalSharedPreferences");
        Intrinsics.checkNotNullParameter(excludeMatchingSharedPreferencesKeys, "excludeMatchingSharedPreferencesKeys");
        Intrinsics.checkNotNullParameter(excludeMatchingSettingsKeys, "excludeMatchingSettingsKeys");
        Intrinsics.checkNotNullParameter(applicationLogFileDir, "applicationLogFileDir");
        Intrinsics.checkNotNullParameter(retryPolicyClass, "retryPolicyClass");
        Intrinsics.checkNotNullParameter(attachmentUris, "attachmentUris");
        Intrinsics.checkNotNullParameter(attachmentUriProvider, "attachmentUriProvider");
        Intrinsics.checkNotNullParameter(reportFormat, "reportFormat");
        Intrinsics.checkNotNullParameter(pluginLoader, "pluginLoader");
        Intrinsics.checkNotNullParameter(pluginConfigurations, "pluginConfigurations");
        this.sharedPreferencesName = str;
        this.includeDropBoxSystemTags = z;
        this.additionalDropBoxTags = additionalDropBoxTags;
        this.dropboxCollectionMinutes = i;
        this.logcatArguments = logcatArguments;
        this.reportContent = reportContent;
        this.deleteUnapprovedReportsOnApplicationStart = z2;
        this.alsoReportToAndroidFramework = z3;
        this.additionalSharedPreferences = additionalSharedPreferences;
        this.logcatFilterByPid = z4;
        this.logcatReadNonBlocking = z5;
        this.sendReportsInDevMode = z6;
        this.excludeMatchingSharedPreferencesKeys = excludeMatchingSharedPreferencesKeys;
        this.excludeMatchingSettingsKeys = excludeMatchingSettingsKeys;
        this.buildConfigClass = cls;
        this.applicationLogFile = str2;
        this.applicationLogFileLines = i2;
        this.applicationLogFileDir = applicationLogFileDir;
        this.retryPolicyClass = retryPolicyClass;
        this.stopServicesOnCrash = z7;
        this.attachmentUris = attachmentUris;
        this.attachmentUriProvider = attachmentUriProvider;
        this.reportSendSuccessToast = str3;
        this.reportSendFailureToast = str4;
        this.reportFormat = reportFormat;
        this.parallel = z8;
        this.pluginLoader = pluginLoader;
        this.pluginConfigurations = pluginConfigurations;
    }

    public final String getSharedPreferencesName() {
        return this.sharedPreferencesName;
    }

    public final boolean getIncludeDropBoxSystemTags() {
        return this.includeDropBoxSystemTags;
    }

    public /* synthetic */ CoreConfiguration(String str, boolean z, List list, int i, List list2, List list3, boolean z2, boolean z3, List list4, boolean z4, boolean z5, boolean z6, List list5, List list6, Class cls, String str2, int i2, Directory directory, Class cls2, boolean z7, List list7, Class cls3, String str3, String str4, StringFormat stringFormat, boolean z8, PluginLoader pluginLoader, List list8, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? null : str, (i3 & 2) != 0 ? false : z, (i3 & 4) != 0 ? CollectionsKt.emptyList() : list, (i3 & 8) != 0 ? 5 : i, (i3 & 16) != 0 ? CollectionsKt.listOf((Object[]) new String[]{"-t", "100", "-v", "time"}) : list2, (i3 & 32) != 0 ? CollectionsKt.toList(ACRAConstants.DEFAULT_REPORT_FIELDS) : list3, (i3 & 64) != 0 ? true : z2, (i3 & 128) != 0 ? false : z3, (i3 & 256) != 0 ? CollectionsKt.emptyList() : list4, (i3 & 512) != 0 ? true : z4, (i3 & 1024) != 0 ? false : z5, (i3 & 2048) != 0 ? true : z6, (i3 & PKIFailureInfo.certConfirmed) != 0 ? CollectionsKt.emptyList() : list5, (i3 & 8192) != 0 ? CollectionsKt.emptyList() : list6, (i3 & 16384) != 0 ? null : cls, (i3 & 32768) != 0 ? null : str2, (i3 & PKIFailureInfo.notAuthorized) != 0 ? 100 : i2, (i3 & PKIFailureInfo.unsupportedVersion) != 0 ? Directory.FILES_LEGACY : directory, (i3 & PKIFailureInfo.transactionIdInUse) != 0 ? DefaultRetryPolicy.class : cls2, (i3 & PKIFailureInfo.signerNotTrusted) != 0 ? false : z7, (i3 & PKIFailureInfo.badCertTemplate) != 0 ? CollectionsKt.emptyList() : list7, (i3 & PKIFailureInfo.badSenderNonce) != 0 ? DefaultAttachmentProvider.class : cls3, (i3 & 4194304) != 0 ? null : str3, (i3 & 8388608) != 0 ? null : str4, (i3 & 16777216) != 0 ? StringFormat.JSON : stringFormat, (i3 & 33554432) != 0 ? true : z8, (i3 & 67108864) != 0 ? new ServicePluginLoader() : pluginLoader, (i3 & 134217728) != 0 ? CollectionsKt.emptyList() : list8);
    }

    public final List getAdditionalDropBoxTags() {
        return this.additionalDropBoxTags;
    }

    public final int getDropboxCollectionMinutes() {
        return this.dropboxCollectionMinutes;
    }

    public final List getLogcatArguments() {
        return this.logcatArguments;
    }

    public final List getReportContent() {
        return this.reportContent;
    }

    public final boolean getDeleteUnapprovedReportsOnApplicationStart() {
        return this.deleteUnapprovedReportsOnApplicationStart;
    }

    public final boolean getAlsoReportToAndroidFramework() {
        return this.alsoReportToAndroidFramework;
    }

    public final List getAdditionalSharedPreferences() {
        return this.additionalSharedPreferences;
    }

    public final boolean getLogcatReadNonBlocking() {
        return this.logcatReadNonBlocking;
    }

    public final boolean getSendReportsInDevMode() {
        return this.sendReportsInDevMode;
    }

    public final List getExcludeMatchingSharedPreferencesKeys() {
        return this.excludeMatchingSharedPreferencesKeys;
    }

    public final List getExcludeMatchingSettingsKeys() {
        return this.excludeMatchingSettingsKeys;
    }

    public final Class getBuildConfigClass() {
        return this.buildConfigClass;
    }

    public final String getApplicationLogFile() {
        return this.applicationLogFile;
    }

    public final int getApplicationLogFileLines() {
        return this.applicationLogFileLines;
    }

    public final Directory getApplicationLogFileDir() {
        return this.applicationLogFileDir;
    }

    public final Class getRetryPolicyClass() {
        return this.retryPolicyClass;
    }

    public final boolean getStopServicesOnCrash() {
        return this.stopServicesOnCrash;
    }

    public final List getAttachmentUris() {
        return this.attachmentUris;
    }

    public final Class getAttachmentUriProvider() {
        return this.attachmentUriProvider;
    }

    public final String getReportSendSuccessToast() {
        return this.reportSendSuccessToast;
    }

    public final String getReportSendFailureToast() {
        return this.reportSendFailureToast;
    }

    public final StringFormat getReportFormat() {
        return this.reportFormat;
    }

    public final boolean getParallel() {
        return this.parallel;
    }

    public final PluginLoader getPluginLoader() {
        return this.pluginLoader;
    }

    public final List getPluginConfigurations() {
        return this.pluginConfigurations;
    }
}
