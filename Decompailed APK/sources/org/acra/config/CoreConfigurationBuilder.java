package org.acra.config;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.acra.ReportField;
import org.acra.data.StringFormat;
import org.acra.file.Directory;
import org.acra.plugins.PluginLoader;

/* JADX INFO: compiled from: CoreConfigurationDsl.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class CoreConfigurationBuilder {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoreConfigurationBuilder.class, "sharedPreferencesName", "getSharedPreferencesName()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoreConfigurationBuilder.class, "includeDropBoxSystemTags", "getIncludeDropBoxSystemTags()Ljava/lang/Boolean;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoreConfigurationBuilder.class, "additionalDropBoxTags", "getAdditionalDropBoxTags()Ljava/util/List;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoreConfigurationBuilder.class, "dropboxCollectionMinutes", "getDropboxCollectionMinutes()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoreConfigurationBuilder.class, "logcatArguments", "getLogcatArguments()Ljava/util/List;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoreConfigurationBuilder.class, "reportContent", "getReportContent()Ljava/util/List;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoreConfigurationBuilder.class, "deleteUnapprovedReportsOnApplicationStart", "getDeleteUnapprovedReportsOnApplicationStart()Ljava/lang/Boolean;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoreConfigurationBuilder.class, "alsoReportToAndroidFramework", "getAlsoReportToAndroidFramework()Ljava/lang/Boolean;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoreConfigurationBuilder.class, "additionalSharedPreferences", "getAdditionalSharedPreferences()Ljava/util/List;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoreConfigurationBuilder.class, "logcatFilterByPid", "getLogcatFilterByPid()Ljava/lang/Boolean;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoreConfigurationBuilder.class, "logcatReadNonBlocking", "getLogcatReadNonBlocking()Ljava/lang/Boolean;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoreConfigurationBuilder.class, "sendReportsInDevMode", "getSendReportsInDevMode()Ljava/lang/Boolean;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoreConfigurationBuilder.class, "excludeMatchingSharedPreferencesKeys", "getExcludeMatchingSharedPreferencesKeys()Ljava/util/List;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoreConfigurationBuilder.class, "excludeMatchingSettingsKeys", "getExcludeMatchingSettingsKeys()Ljava/util/List;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoreConfigurationBuilder.class, "buildConfigClass", "getBuildConfigClass()Ljava/lang/Class;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoreConfigurationBuilder.class, "applicationLogFile", "getApplicationLogFile()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoreConfigurationBuilder.class, "applicationLogFileLines", "getApplicationLogFileLines()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoreConfigurationBuilder.class, "applicationLogFileDir", "getApplicationLogFileDir()Lorg/acra/file/Directory;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoreConfigurationBuilder.class, "retryPolicyClass", "getRetryPolicyClass()Ljava/lang/Class;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoreConfigurationBuilder.class, "stopServicesOnCrash", "getStopServicesOnCrash()Ljava/lang/Boolean;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoreConfigurationBuilder.class, "attachmentUris", "getAttachmentUris()Ljava/util/List;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoreConfigurationBuilder.class, "attachmentUriProvider", "getAttachmentUriProvider()Ljava/lang/Class;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoreConfigurationBuilder.class, "reportSendSuccessToast", "getReportSendSuccessToast()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoreConfigurationBuilder.class, "reportSendFailureToast", "getReportSendFailureToast()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoreConfigurationBuilder.class, "reportFormat", "getReportFormat()Lorg/acra/data/StringFormat;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoreConfigurationBuilder.class, "parallel", "getParallel()Ljava/lang/Boolean;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoreConfigurationBuilder.class, "pluginLoader", "getPluginLoader()Lorg/acra/plugins/PluginLoader;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CoreConfigurationBuilder.class, "pluginConfigurations", "getPluginConfigurations()Ljava/util/List;", 0))};
    private int _defaultsBitField0 = -1;
    private final ReadWriteProperty additionalDropBoxTags$delegate;
    private final ReadWriteProperty additionalSharedPreferences$delegate;
    private final ReadWriteProperty alsoReportToAndroidFramework$delegate;
    private final ReadWriteProperty applicationLogFile$delegate;
    private final ReadWriteProperty applicationLogFileDir$delegate;
    private final ReadWriteProperty applicationLogFileLines$delegate;
    private final ReadWriteProperty attachmentUriProvider$delegate;
    private final ReadWriteProperty attachmentUris$delegate;
    private final ReadWriteProperty buildConfigClass$delegate;
    private final ReadWriteProperty deleteUnapprovedReportsOnApplicationStart$delegate;
    private final ReadWriteProperty dropboxCollectionMinutes$delegate;
    private final ReadWriteProperty excludeMatchingSettingsKeys$delegate;
    private final ReadWriteProperty excludeMatchingSharedPreferencesKeys$delegate;
    private final ReadWriteProperty includeDropBoxSystemTags$delegate;
    private final ReadWriteProperty logcatArguments$delegate;
    private final ReadWriteProperty logcatFilterByPid$delegate;
    private final ReadWriteProperty logcatReadNonBlocking$delegate;
    private final ReadWriteProperty parallel$delegate;
    private final ReadWriteProperty pluginConfigurations$delegate;
    private final ReadWriteProperty pluginLoader$delegate;
    private final ReadWriteProperty reportContent$delegate;
    private final ReadWriteProperty reportFormat$delegate;
    private final ReadWriteProperty reportSendFailureToast$delegate;
    private final ReadWriteProperty reportSendSuccessToast$delegate;
    private final ReadWriteProperty retryPolicyClass$delegate;
    private final ReadWriteProperty sendReportsInDevMode$delegate;
    private final ReadWriteProperty sharedPreferencesName$delegate;
    private final ReadWriteProperty stopServicesOnCrash$delegate;

    public CoreConfigurationBuilder() {
        Delegates delegates = Delegates.INSTANCE;
        final Object obj = null;
        this.sharedPreferencesName$delegate = new ObservableProperty(obj) { // from class: org.acra.config.CoreConfigurationBuilder$special$$inlined$observable$1
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -2;
            }
        };
        this.includeDropBoxSystemTags$delegate = new ObservableProperty(obj) { // from class: org.acra.config.CoreConfigurationBuilder$special$$inlined$observable$2
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -3;
            }
        };
        this.additionalDropBoxTags$delegate = new ObservableProperty(obj) { // from class: org.acra.config.CoreConfigurationBuilder$special$$inlined$observable$3
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -5;
            }
        };
        this.dropboxCollectionMinutes$delegate = new ObservableProperty(obj) { // from class: org.acra.config.CoreConfigurationBuilder$special$$inlined$observable$4
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -9;
            }
        };
        this.logcatArguments$delegate = new ObservableProperty(obj) { // from class: org.acra.config.CoreConfigurationBuilder$special$$inlined$observable$5
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -17;
            }
        };
        this.reportContent$delegate = new ObservableProperty(obj) { // from class: org.acra.config.CoreConfigurationBuilder$special$$inlined$observable$6
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -33;
            }
        };
        this.deleteUnapprovedReportsOnApplicationStart$delegate = new ObservableProperty(obj) { // from class: org.acra.config.CoreConfigurationBuilder$special$$inlined$observable$7
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -65;
            }
        };
        this.alsoReportToAndroidFramework$delegate = new ObservableProperty(obj) { // from class: org.acra.config.CoreConfigurationBuilder$special$$inlined$observable$8
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -129;
            }
        };
        this.additionalSharedPreferences$delegate = new ObservableProperty(obj) { // from class: org.acra.config.CoreConfigurationBuilder$special$$inlined$observable$9
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -257;
            }
        };
        this.logcatFilterByPid$delegate = new ObservableProperty(obj) { // from class: org.acra.config.CoreConfigurationBuilder$special$$inlined$observable$10
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -513;
            }
        };
        this.logcatReadNonBlocking$delegate = new ObservableProperty(obj) { // from class: org.acra.config.CoreConfigurationBuilder$special$$inlined$observable$11
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -1025;
            }
        };
        this.sendReportsInDevMode$delegate = new ObservableProperty(obj) { // from class: org.acra.config.CoreConfigurationBuilder$special$$inlined$observable$12
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -2049;
            }
        };
        this.excludeMatchingSharedPreferencesKeys$delegate = new ObservableProperty(obj) { // from class: org.acra.config.CoreConfigurationBuilder$special$$inlined$observable$13
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -4097;
            }
        };
        this.excludeMatchingSettingsKeys$delegate = new ObservableProperty(obj) { // from class: org.acra.config.CoreConfigurationBuilder$special$$inlined$observable$14
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -8193;
            }
        };
        this.buildConfigClass$delegate = new ObservableProperty(obj) { // from class: org.acra.config.CoreConfigurationBuilder$special$$inlined$observable$15
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -16385;
            }
        };
        this.applicationLogFile$delegate = new ObservableProperty(obj) { // from class: org.acra.config.CoreConfigurationBuilder$special$$inlined$observable$16
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -32769;
            }
        };
        this.applicationLogFileLines$delegate = new ObservableProperty(obj) { // from class: org.acra.config.CoreConfigurationBuilder$special$$inlined$observable$17
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -65537;
            }
        };
        this.applicationLogFileDir$delegate = new ObservableProperty(obj) { // from class: org.acra.config.CoreConfigurationBuilder$special$$inlined$observable$18
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -131073;
            }
        };
        this.retryPolicyClass$delegate = new ObservableProperty(obj) { // from class: org.acra.config.CoreConfigurationBuilder$special$$inlined$observable$19
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -262145;
            }
        };
        this.stopServicesOnCrash$delegate = new ObservableProperty(obj) { // from class: org.acra.config.CoreConfigurationBuilder$special$$inlined$observable$20
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -524289;
            }
        };
        this.attachmentUris$delegate = new ObservableProperty(obj) { // from class: org.acra.config.CoreConfigurationBuilder$special$$inlined$observable$21
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -1048577;
            }
        };
        this.attachmentUriProvider$delegate = new ObservableProperty(obj) { // from class: org.acra.config.CoreConfigurationBuilder$special$$inlined$observable$22
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -2097153;
            }
        };
        this.reportSendSuccessToast$delegate = new ObservableProperty(obj) { // from class: org.acra.config.CoreConfigurationBuilder$special$$inlined$observable$23
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -4194305;
            }
        };
        this.reportSendFailureToast$delegate = new ObservableProperty(obj) { // from class: org.acra.config.CoreConfigurationBuilder$special$$inlined$observable$24
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -8388609;
            }
        };
        this.reportFormat$delegate = new ObservableProperty(obj) { // from class: org.acra.config.CoreConfigurationBuilder$special$$inlined$observable$25
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -16777217;
            }
        };
        this.parallel$delegate = new ObservableProperty(obj) { // from class: org.acra.config.CoreConfigurationBuilder$special$$inlined$observable$26
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -33554433;
            }
        };
        this.pluginLoader$delegate = new ObservableProperty(obj) { // from class: org.acra.config.CoreConfigurationBuilder$special$$inlined$observable$27
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -67108865;
            }
        };
        this.pluginConfigurations$delegate = new ObservableProperty(obj) { // from class: org.acra.config.CoreConfigurationBuilder$special$$inlined$observable$28
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -134217729;
            }
        };
    }

    public final String getSharedPreferencesName() {
        return (String) this.sharedPreferencesName$delegate.getValue(this, $$delegatedProperties[0]);
    }

    public final Boolean getIncludeDropBoxSystemTags() {
        return (Boolean) this.includeDropBoxSystemTags$delegate.getValue(this, $$delegatedProperties[1]);
    }

    public final List getAdditionalDropBoxTags() {
        return (List) this.additionalDropBoxTags$delegate.getValue(this, $$delegatedProperties[2]);
    }

    public final Integer getDropboxCollectionMinutes() {
        return (Integer) this.dropboxCollectionMinutes$delegate.getValue(this, $$delegatedProperties[3]);
    }

    public final List getLogcatArguments() {
        return (List) this.logcatArguments$delegate.getValue(this, $$delegatedProperties[4]);
    }

    public final List getReportContent() {
        return (List) this.reportContent$delegate.getValue(this, $$delegatedProperties[5]);
    }

    public final void setReportContent(List list) {
        this.reportContent$delegate.setValue(this, $$delegatedProperties[5], list);
    }

    public final Boolean getDeleteUnapprovedReportsOnApplicationStart() {
        return (Boolean) this.deleteUnapprovedReportsOnApplicationStart$delegate.getValue(this, $$delegatedProperties[6]);
    }

    public final Boolean getAlsoReportToAndroidFramework() {
        return (Boolean) this.alsoReportToAndroidFramework$delegate.getValue(this, $$delegatedProperties[7]);
    }

    public final List getAdditionalSharedPreferences() {
        return (List) this.additionalSharedPreferences$delegate.getValue(this, $$delegatedProperties[8]);
    }

    public final Boolean getLogcatFilterByPid() {
        return (Boolean) this.logcatFilterByPid$delegate.getValue(this, $$delegatedProperties[9]);
    }

    public final Boolean getLogcatReadNonBlocking() {
        return (Boolean) this.logcatReadNonBlocking$delegate.getValue(this, $$delegatedProperties[10]);
    }

    public final Boolean getSendReportsInDevMode() {
        return (Boolean) this.sendReportsInDevMode$delegate.getValue(this, $$delegatedProperties[11]);
    }

    public final List getExcludeMatchingSharedPreferencesKeys() {
        return (List) this.excludeMatchingSharedPreferencesKeys$delegate.getValue(this, $$delegatedProperties[12]);
    }

    public final List getExcludeMatchingSettingsKeys() {
        return (List) this.excludeMatchingSettingsKeys$delegate.getValue(this, $$delegatedProperties[13]);
    }

    public final Class getBuildConfigClass() {
        return (Class) this.buildConfigClass$delegate.getValue(this, $$delegatedProperties[14]);
    }

    public final String getApplicationLogFile() {
        return (String) this.applicationLogFile$delegate.getValue(this, $$delegatedProperties[15]);
    }

    public final Integer getApplicationLogFileLines() {
        return (Integer) this.applicationLogFileLines$delegate.getValue(this, $$delegatedProperties[16]);
    }

    public final Directory getApplicationLogFileDir() {
        return (Directory) this.applicationLogFileDir$delegate.getValue(this, $$delegatedProperties[17]);
    }

    public final Class getRetryPolicyClass() {
        return (Class) this.retryPolicyClass$delegate.getValue(this, $$delegatedProperties[18]);
    }

    public final Boolean getStopServicesOnCrash() {
        return (Boolean) this.stopServicesOnCrash$delegate.getValue(this, $$delegatedProperties[19]);
    }

    public final List getAttachmentUris() {
        return (List) this.attachmentUris$delegate.getValue(this, $$delegatedProperties[20]);
    }

    public final Class getAttachmentUriProvider() {
        return (Class) this.attachmentUriProvider$delegate.getValue(this, $$delegatedProperties[21]);
    }

    public final String getReportSendSuccessToast() {
        return (String) this.reportSendSuccessToast$delegate.getValue(this, $$delegatedProperties[22]);
    }

    public final String getReportSendFailureToast() {
        return (String) this.reportSendFailureToast$delegate.getValue(this, $$delegatedProperties[23]);
    }

    public final StringFormat getReportFormat() {
        return (StringFormat) this.reportFormat$delegate.getValue(this, $$delegatedProperties[24]);
    }

    public final Boolean getParallel() {
        return (Boolean) this.parallel$delegate.getValue(this, $$delegatedProperties[25]);
    }

    public final PluginLoader getPluginLoader() {
        return (PluginLoader) this.pluginLoader$delegate.getValue(this, $$delegatedProperties[26]);
    }

    public final List getPluginConfigurations() {
        return (List) this.pluginConfigurations$delegate.getValue(this, $$delegatedProperties[27]);
    }

    public final void setPluginConfigurations(List list) {
        this.pluginConfigurations$delegate.setValue(this, $$delegatedProperties[27], list);
    }

    public final CoreConfigurationBuilder withReportContent(ReportField... reportContent) {
        Intrinsics.checkNotNullParameter(reportContent, "reportContent");
        setReportContent(ArraysKt.toList(reportContent));
        return this;
    }

    public final CoreConfigurationBuilder withPluginConfigurations(Configuration... pluginConfigurations) {
        Intrinsics.checkNotNullParameter(pluginConfigurations, "pluginConfigurations");
        setPluginConfigurations(ArraysKt.toList(pluginConfigurations));
        return this;
    }

    public final CoreConfiguration build() throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        Class cls = Boolean.TYPE;
        Class cls2 = Integer.TYPE;
        Constructor constructor = CoreConfiguration.class.getConstructor(String.class, cls, List.class, cls2, List.class, List.class, cls, cls, List.class, cls, cls, cls, List.class, List.class, Class.class, String.class, cls2, Directory.class, Class.class, cls, List.class, Class.class, String.class, String.class, StringFormat.class, cls, PluginLoader.class, List.class, cls2, DefaultConstructorMarker.class);
        String sharedPreferencesName = getSharedPreferencesName();
        Boolean includeDropBoxSystemTags = getIncludeDropBoxSystemTags();
        Boolean boolValueOf = Boolean.valueOf(includeDropBoxSystemTags != null ? includeDropBoxSystemTags.booleanValue() : false);
        List additionalDropBoxTags = getAdditionalDropBoxTags();
        Integer dropboxCollectionMinutes = getDropboxCollectionMinutes();
        Integer numValueOf = Integer.valueOf(dropboxCollectionMinutes != null ? dropboxCollectionMinutes.intValue() : 0);
        List logcatArguments = getLogcatArguments();
        List reportContent = getReportContent();
        Boolean deleteUnapprovedReportsOnApplicationStart = getDeleteUnapprovedReportsOnApplicationStart();
        Boolean boolValueOf2 = Boolean.valueOf(deleteUnapprovedReportsOnApplicationStart != null ? deleteUnapprovedReportsOnApplicationStart.booleanValue() : false);
        Boolean alsoReportToAndroidFramework = getAlsoReportToAndroidFramework();
        Boolean boolValueOf3 = Boolean.valueOf(alsoReportToAndroidFramework != null ? alsoReportToAndroidFramework.booleanValue() : false);
        List additionalSharedPreferences = getAdditionalSharedPreferences();
        Boolean logcatFilterByPid = getLogcatFilterByPid();
        Boolean boolValueOf4 = Boolean.valueOf(logcatFilterByPid != null ? logcatFilterByPid.booleanValue() : false);
        Boolean logcatReadNonBlocking = getLogcatReadNonBlocking();
        Boolean boolValueOf5 = Boolean.valueOf(logcatReadNonBlocking != null ? logcatReadNonBlocking.booleanValue() : false);
        Boolean sendReportsInDevMode = getSendReportsInDevMode();
        Boolean boolValueOf6 = Boolean.valueOf(sendReportsInDevMode != null ? sendReportsInDevMode.booleanValue() : false);
        List excludeMatchingSharedPreferencesKeys = getExcludeMatchingSharedPreferencesKeys();
        List excludeMatchingSettingsKeys = getExcludeMatchingSettingsKeys();
        Class buildConfigClass = getBuildConfigClass();
        String applicationLogFile = getApplicationLogFile();
        Integer applicationLogFileLines = getApplicationLogFileLines();
        Integer numValueOf2 = Integer.valueOf(applicationLogFileLines != null ? applicationLogFileLines.intValue() : 0);
        Directory applicationLogFileDir = getApplicationLogFileDir();
        Class retryPolicyClass = getRetryPolicyClass();
        Boolean stopServicesOnCrash = getStopServicesOnCrash();
        Boolean boolValueOf7 = Boolean.valueOf(stopServicesOnCrash != null ? stopServicesOnCrash.booleanValue() : false);
        List attachmentUris = getAttachmentUris();
        Class attachmentUriProvider = getAttachmentUriProvider();
        String reportSendSuccessToast = getReportSendSuccessToast();
        String reportSendFailureToast = getReportSendFailureToast();
        StringFormat reportFormat = getReportFormat();
        Boolean parallel = getParallel();
        Object objNewInstance = constructor.newInstance(sharedPreferencesName, boolValueOf, additionalDropBoxTags, numValueOf, logcatArguments, reportContent, boolValueOf2, boolValueOf3, additionalSharedPreferences, boolValueOf4, boolValueOf5, boolValueOf6, excludeMatchingSharedPreferencesKeys, excludeMatchingSettingsKeys, buildConfigClass, applicationLogFile, numValueOf2, applicationLogFileDir, retryPolicyClass, boolValueOf7, attachmentUris, attachmentUriProvider, reportSendSuccessToast, reportSendFailureToast, reportFormat, Boolean.valueOf(parallel != null ? parallel.booleanValue() : false), getPluginLoader(), getPluginConfigurations(), Integer.valueOf(this._defaultsBitField0), null);
        Intrinsics.checkNotNullExpressionValue(objNewInstance, "newInstance(...)");
        return (CoreConfiguration) objNewInstance;
    }
}
