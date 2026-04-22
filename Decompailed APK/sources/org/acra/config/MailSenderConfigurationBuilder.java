package org.acra.config;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: MailSenderConfigurationDsl.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class MailSenderConfigurationBuilder {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(MailSenderConfigurationBuilder.class, "enabled", "getEnabled()Ljava/lang/Boolean;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(MailSenderConfigurationBuilder.class, "reportAsFile", "getReportAsFile()Ljava/lang/Boolean;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(MailSenderConfigurationBuilder.class, "reportFileName", "getReportFileName()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(MailSenderConfigurationBuilder.class, "subject", "getSubject()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(MailSenderConfigurationBuilder.class, "body", "getBody()Ljava/lang/String;", 0))};
    private int _defaultsBitField0 = -1;
    private final ReadWriteProperty body$delegate;
    private final ReadWriteProperty enabled$delegate;
    private String mailTo;
    private final ReadWriteProperty reportAsFile$delegate;
    private final ReadWriteProperty reportFileName$delegate;
    private final ReadWriteProperty subject$delegate;

    public MailSenderConfigurationBuilder() {
        Delegates delegates = Delegates.INSTANCE;
        final Object obj = null;
        this.enabled$delegate = new ObservableProperty(obj) { // from class: org.acra.config.MailSenderConfigurationBuilder$special$$inlined$observable$1
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -2;
            }
        };
        this.reportAsFile$delegate = new ObservableProperty(obj) { // from class: org.acra.config.MailSenderConfigurationBuilder$special$$inlined$observable$2
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -5;
            }
        };
        this.reportFileName$delegate = new ObservableProperty(obj) { // from class: org.acra.config.MailSenderConfigurationBuilder$special$$inlined$observable$3
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -9;
            }
        };
        this.subject$delegate = new ObservableProperty(obj) { // from class: org.acra.config.MailSenderConfigurationBuilder$special$$inlined$observable$4
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -17;
            }
        };
        this.body$delegate = new ObservableProperty(obj) { // from class: org.acra.config.MailSenderConfigurationBuilder$special$$inlined$observable$5
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -33;
            }
        };
    }

    public final Boolean getEnabled() {
        return (Boolean) this.enabled$delegate.getValue(this, $$delegatedProperties[0]);
    }

    public final Boolean getReportAsFile() {
        return (Boolean) this.reportAsFile$delegate.getValue(this, $$delegatedProperties[1]);
    }

    public final String getReportFileName() {
        return (String) this.reportFileName$delegate.getValue(this, $$delegatedProperties[2]);
    }

    public final void setReportFileName(String str) {
        this.reportFileName$delegate.setValue(this, $$delegatedProperties[2], str);
    }

    public final String getSubject() {
        return (String) this.subject$delegate.getValue(this, $$delegatedProperties[3]);
    }

    public final void setSubject(String str) {
        this.subject$delegate.setValue(this, $$delegatedProperties[3], str);
    }

    public final String getBody() {
        return (String) this.body$delegate.getValue(this, $$delegatedProperties[4]);
    }

    public final MailSenderConfigurationBuilder withMailTo(String mailTo) {
        Intrinsics.checkNotNullParameter(mailTo, "mailTo");
        this.mailTo = mailTo;
        return this;
    }

    public final MailSenderConfigurationBuilder withReportFileName(String reportFileName) {
        Intrinsics.checkNotNullParameter(reportFileName, "reportFileName");
        setReportFileName(reportFileName);
        return this;
    }

    public final MailSenderConfigurationBuilder withSubject(String str) {
        setSubject(str);
        return this;
    }

    public final MailSenderConfiguration build() throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        if (this.mailTo == null) {
            throw new IllegalStateException("mailTo must be assigned.");
        }
        Class cls = Boolean.TYPE;
        Constructor constructor = MailSenderConfiguration.class.getConstructor(cls, String.class, cls, String.class, String.class, String.class, Integer.TYPE, DefaultConstructorMarker.class);
        Boolean enabled = getEnabled();
        Boolean boolValueOf = Boolean.valueOf(enabled != null ? enabled.booleanValue() : false);
        String str = this.mailTo;
        Boolean reportAsFile = getReportAsFile();
        Object objNewInstance = constructor.newInstance(boolValueOf, str, Boolean.valueOf(reportAsFile != null ? reportAsFile.booleanValue() : false), getReportFileName(), getSubject(), getBody(), Integer.valueOf(this._defaultsBitField0), null);
        Intrinsics.checkNotNullExpressionValue(objNewInstance, "newInstance(...)");
        return (MailSenderConfiguration) objNewInstance;
    }
}
