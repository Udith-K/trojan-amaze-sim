package org.acra.collector;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.joran.action.Action;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.builder.ReportBuilder;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.acra.plugins.Plugin;
import org.json.JSONObject;

/* JADX INFO: compiled from: SettingsCollector.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J$\u0010\u0010\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0013H\u0012J\u001a\u0010\u0014\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0012¨\u0006\u0019"}, d2 = {"Lorg/acra/collector/SettingsCollector;", "Lorg/acra/collector/BaseReportFieldCollector;", "<init>", "()V", "collect", "", "reportField", "Lorg/acra/ReportField;", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "config", "Lorg/acra/config/CoreConfiguration;", "reportBuilder", "Lorg/acra/builder/ReportBuilder;", "target", "Lorg/acra/data/CrashReportData;", "collectSettings", "Lorg/json/JSONObject;", "settings", "Ljava/lang/Class;", "isAuthorized", "", Action.KEY_ATTRIBUTE, "Ljava/lang/reflect/Field;", "Companion", "acra-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class SettingsCollector extends BaseReportFieldCollector {
    private static final String ERROR = "Error: ";

    /* JADX INFO: compiled from: SettingsCollector.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ReportField.values().length];
            try {
                iArr[ReportField.SETTINGS_SYSTEM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ReportField.SETTINGS_SECURE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ReportField.SETTINGS_GLOBAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // org.acra.collector.BaseReportFieldCollector, org.acra.collector.Collector, org.acra.plugins.Plugin
    public /* bridge */ /* synthetic */ boolean enabled(CoreConfiguration coreConfiguration) {
        return Plugin.CC.$default$enabled(this, coreConfiguration);
    }

    public SettingsCollector() {
        super(ReportField.SETTINGS_SYSTEM, ReportField.SETTINGS_SECURE, ReportField.SETTINGS_GLOBAL);
    }

    @Override // org.acra.collector.BaseReportFieldCollector
    public void collect(ReportField reportField, Context context, CoreConfiguration config, ReportBuilder reportBuilder, CrashReportData target) throws Exception {
        Intrinsics.checkNotNullParameter(reportField, "reportField");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(reportBuilder, "reportBuilder");
        Intrinsics.checkNotNullParameter(target, "target");
        int i = WhenMappings.$EnumSwitchMapping$0[reportField.ordinal()];
        if (i == 1) {
            target.put(ReportField.SETTINGS_SYSTEM, collectSettings(context, config, Settings.System.class));
        } else if (i == 2) {
            target.put(ReportField.SETTINGS_SECURE, collectSettings(context, config, Settings.Secure.class));
        } else {
            if (i == 3) {
                target.put(ReportField.SETTINGS_GLOBAL, collectSettings(context, config, Settings.Global.class));
                return;
            }
            throw new IllegalArgumentException();
        }
    }

    private JSONObject collectSettings(Context context, CoreConfiguration config, Class<?> settings) throws NoSuchMethodException {
        JSONObject jSONObject = new JSONObject();
        Field[] fields = settings.getFields();
        Method method = settings.getMethod("getString", ContentResolver.class, String.class);
        Iterator it = ArrayIteratorKt.iterator(fields);
        while (it.hasNext()) {
            Field field = (Field) it.next();
            if (!field.isAnnotationPresent(Deprecated.class) && Intrinsics.areEqual(field.getType(), String.class) && isAuthorized(config, field)) {
                try {
                    Object objInvoke = method.invoke(null, context.getContentResolver(), field.get(null));
                    if (objInvoke != null) {
                        jSONObject.put(field.getName(), objInvoke);
                    }
                } catch (Exception e) {
                    ACRA.log.w(ACRA.LOG_TAG, ERROR, e);
                }
            }
        }
        return jSONObject;
    }

    private boolean isAuthorized(CoreConfiguration config, Field key) {
        if (key != null) {
            String name = key.getName();
            Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
            if (!StringsKt.startsWith$default(name, "WIFI_AP", false, 2, (Object) null)) {
                for (String str : config.getExcludeMatchingSettingsKeys()) {
                    String name2 = key.getName();
                    Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
                    if (new Regex(str).matches(name2)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}
