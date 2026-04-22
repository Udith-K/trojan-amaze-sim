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
import org.bouncycastle.i18n.TextBundle;

/* JADX INFO: compiled from: DialogConfigurationDsl.kt */
/* JADX INFO: loaded from: classes2.dex */
public final class DialogConfigurationBuilder {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(DialogConfigurationBuilder.class, "enabled", "getEnabled()Ljava/lang/Boolean;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(DialogConfigurationBuilder.class, "reportDialogClass", "getReportDialogClass()Ljava/lang/Class;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(DialogConfigurationBuilder.class, "positiveButtonText", "getPositiveButtonText()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(DialogConfigurationBuilder.class, "negativeButtonText", "getNegativeButtonText()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(DialogConfigurationBuilder.class, "commentPrompt", "getCommentPrompt()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(DialogConfigurationBuilder.class, "emailPrompt", "getEmailPrompt()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(DialogConfigurationBuilder.class, "resIcon", "getResIcon()Ljava/lang/Integer;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(DialogConfigurationBuilder.class, TextBundle.TEXT_ENTRY, "getText()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(DialogConfigurationBuilder.class, "title", "getTitle()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(DialogConfigurationBuilder.class, "resTheme", "getResTheme()Ljava/lang/Integer;", 0))};
    private int _defaultsBitField0 = -1;
    private final ReadWriteProperty commentPrompt$delegate;
    private final ReadWriteProperty emailPrompt$delegate;
    private final ReadWriteProperty enabled$delegate;
    private final ReadWriteProperty negativeButtonText$delegate;
    private final ReadWriteProperty positiveButtonText$delegate;
    private final ReadWriteProperty reportDialogClass$delegate;
    private final ReadWriteProperty resIcon$delegate;
    private final ReadWriteProperty resTheme$delegate;
    private final ReadWriteProperty text$delegate;
    private final ReadWriteProperty title$delegate;

    public DialogConfigurationBuilder() {
        Delegates delegates = Delegates.INSTANCE;
        final Object obj = null;
        this.enabled$delegate = new ObservableProperty(obj) { // from class: org.acra.config.DialogConfigurationBuilder$special$$inlined$observable$1
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -2;
            }
        };
        this.reportDialogClass$delegate = new ObservableProperty(obj) { // from class: org.acra.config.DialogConfigurationBuilder$special$$inlined$observable$2
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -3;
            }
        };
        this.positiveButtonText$delegate = new ObservableProperty(obj) { // from class: org.acra.config.DialogConfigurationBuilder$special$$inlined$observable$3
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -5;
            }
        };
        this.negativeButtonText$delegate = new ObservableProperty(obj) { // from class: org.acra.config.DialogConfigurationBuilder$special$$inlined$observable$4
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -9;
            }
        };
        this.commentPrompt$delegate = new ObservableProperty(obj) { // from class: org.acra.config.DialogConfigurationBuilder$special$$inlined$observable$5
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -17;
            }
        };
        this.emailPrompt$delegate = new ObservableProperty(obj) { // from class: org.acra.config.DialogConfigurationBuilder$special$$inlined$observable$6
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -33;
            }
        };
        this.resIcon$delegate = new ObservableProperty(obj) { // from class: org.acra.config.DialogConfigurationBuilder$special$$inlined$observable$7
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -65;
            }
        };
        this.text$delegate = new ObservableProperty(obj) { // from class: org.acra.config.DialogConfigurationBuilder$special$$inlined$observable$8
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -129;
            }
        };
        this.title$delegate = new ObservableProperty(obj) { // from class: org.acra.config.DialogConfigurationBuilder$special$$inlined$observable$9
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -257;
            }
        };
        this.resTheme$delegate = new ObservableProperty(obj) { // from class: org.acra.config.DialogConfigurationBuilder$special$$inlined$observable$10
            @Override // kotlin.properties.ObservableProperty
            protected void afterChange(KProperty property, Object obj2, Object obj3) {
                Intrinsics.checkNotNullParameter(property, "property");
                this._defaultsBitField0 &= -513;
            }
        };
    }

    public final Boolean getEnabled() {
        return (Boolean) this.enabled$delegate.getValue(this, $$delegatedProperties[0]);
    }

    public final Class getReportDialogClass() {
        return (Class) this.reportDialogClass$delegate.getValue(this, $$delegatedProperties[1]);
    }

    public final String getPositiveButtonText() {
        return (String) this.positiveButtonText$delegate.getValue(this, $$delegatedProperties[2]);
    }

    public final String getNegativeButtonText() {
        return (String) this.negativeButtonText$delegate.getValue(this, $$delegatedProperties[3]);
    }

    public final String getCommentPrompt() {
        return (String) this.commentPrompt$delegate.getValue(this, $$delegatedProperties[4]);
    }

    public final void setCommentPrompt(String str) {
        this.commentPrompt$delegate.setValue(this, $$delegatedProperties[4], str);
    }

    public final String getEmailPrompt() {
        return (String) this.emailPrompt$delegate.getValue(this, $$delegatedProperties[5]);
    }

    public final Integer getResIcon() {
        return (Integer) this.resIcon$delegate.getValue(this, $$delegatedProperties[6]);
    }

    public final String getText() {
        return (String) this.text$delegate.getValue(this, $$delegatedProperties[7]);
    }

    public final void setText(String str) {
        this.text$delegate.setValue(this, $$delegatedProperties[7], str);
    }

    public final String getTitle() {
        return (String) this.title$delegate.getValue(this, $$delegatedProperties[8]);
    }

    public final void setTitle(String str) {
        this.title$delegate.setValue(this, $$delegatedProperties[8], str);
    }

    public final Integer getResTheme() {
        return (Integer) this.resTheme$delegate.getValue(this, $$delegatedProperties[9]);
    }

    public final void setResTheme(Integer num) {
        this.resTheme$delegate.setValue(this, $$delegatedProperties[9], num);
    }

    public final DialogConfigurationBuilder withCommentPrompt(String str) {
        setCommentPrompt(str);
        return this;
    }

    public final DialogConfigurationBuilder withText(String str) {
        setText(str);
        return this;
    }

    public final DialogConfigurationBuilder withTitle(String str) {
        setTitle(str);
        return this;
    }

    public final DialogConfigurationBuilder withResTheme(Integer num) {
        setResTheme(num);
        return this;
    }

    public final DialogConfiguration build() throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        if (getReportDialogClass() == null && getText() == null) {
            throw new IllegalStateException("One of reportDialogClass, text must be assigned.");
        }
        Constructor constructor = DialogConfiguration.class.getConstructor(Boolean.TYPE, Class.class, String.class, String.class, String.class, String.class, Integer.class, String.class, String.class, Integer.class, Integer.TYPE, DefaultConstructorMarker.class);
        Boolean enabled = getEnabled();
        Object objNewInstance = constructor.newInstance(Boolean.valueOf(enabled != null ? enabled.booleanValue() : false), getReportDialogClass(), getPositiveButtonText(), getNegativeButtonText(), getCommentPrompt(), getEmailPrompt(), getResIcon(), getText(), getTitle(), getResTheme(), Integer.valueOf(this._defaultsBitField0), null);
        Intrinsics.checkNotNullExpressionValue(objNewInstance, "newInstance(...)");
        return (DialogConfiguration) objNewInstance;
    }
}
