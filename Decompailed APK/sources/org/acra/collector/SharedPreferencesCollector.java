package org.acra.collector;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.joran.action.Action;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import org.acra.ReportField;
import org.acra.builder.ReportBuilder;
import org.acra.config.CoreConfiguration;
import org.acra.data.CrashReportData;
import org.acra.plugins.Plugin;
import org.acra.prefs.SharedPreferencesFactory;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: SharedPreferencesCollector.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0004\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0012J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0012¨\u0006\u0015"}, d2 = {"Lorg/acra/collector/SharedPreferencesCollector;", "Lorg/acra/collector/BaseReportFieldCollector;", "<init>", "()V", "collect", "", "reportField", "Lorg/acra/ReportField;", CoreConstants.CONTEXT_SCOPE_VALUE, "Landroid/content/Context;", "config", "Lorg/acra/config/CoreConfiguration;", "reportBuilder", "Lorg/acra/builder/ReportBuilder;", "target", "Lorg/acra/data/CrashReportData;", "Lorg/json/JSONObject;", "filteredKey", "", Action.KEY_ATTRIBUTE, "", "acra-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class SharedPreferencesCollector extends BaseReportFieldCollector {

    /* JADX INFO: compiled from: SharedPreferencesCollector.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ReportField.values().length];
            try {
                iArr[ReportField.USER_EMAIL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ReportField.SHARED_PREFERENCES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // org.acra.collector.BaseReportFieldCollector, org.acra.collector.Collector, org.acra.plugins.Plugin
    public /* bridge */ /* synthetic */ boolean enabled(CoreConfiguration coreConfiguration) {
        return Plugin.CC.$default$enabled(this, coreConfiguration);
    }

    public SharedPreferencesCollector() {
        super(ReportField.USER_EMAIL, ReportField.SHARED_PREFERENCES);
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
            target.put(ReportField.USER_EMAIL, new SharedPreferencesFactory(context, config).create().getString("acra.user.email", null));
        } else {
            if (i == 2) {
                target.put(ReportField.SHARED_PREFERENCES, collect(context, config));
                return;
            }
            throw new IllegalArgumentException();
        }
    }

    private JSONObject collect(Context context, CoreConfiguration config) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        TreeMap treeMap = new TreeMap();
        treeMap.put(CoreConstants.DEFAULT_CONTEXT_NAME, PreferenceManager.getDefaultSharedPreferences(context));
        for (String str : config.getAdditionalSharedPreferences()) {
            treeMap.put(str, context.getSharedPreferences(str, 0));
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            String str2 = (String) entry.getKey();
            Map<String, ?> all = ((SharedPreferences) entry.getValue()).getAll();
            if (all.isEmpty()) {
                jSONObject.put(str2, "empty");
            } else {
                Iterator<String> it = all.keySet().iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    Intrinsics.checkNotNullExpressionValue(next, "next(...)");
                    if (filteredKey(config, next)) {
                        it.remove();
                    }
                }
                jSONObject.put(str2, new JSONObject(all));
            }
        }
        return jSONObject;
    }

    private boolean filteredKey(CoreConfiguration config, String key) {
        Iterator it = config.getExcludeMatchingSharedPreferencesKeys().iterator();
        while (it.hasNext()) {
            if (new Regex((String) it.next()).matches(key)) {
                return true;
            }
        }
        return false;
    }
}
